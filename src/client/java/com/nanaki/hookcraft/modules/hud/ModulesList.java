package com.nanaki.hookcraft.modules.hud;

import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.modules.Module;
import com.nanaki.hookcraft.modules.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class ModulesList extends Module {
    public ModulesList() {
        super(Category.HUD, ModulesList::CurrentModulesListState, enabled -> Config.Hud.modulesList = enabled);
    }

    public static boolean CurrentModulesListState() {
        return Config.Hud.modulesList;
    }

    @Override
    public boolean isEnabled() {
        return CurrentModulesListState();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }

    @Override
    public void onRenderHUD(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;

        if (CurrentModulesListState()) {
            TextRenderer tr = client.textRenderer;
            if (tr == null) return;

            java.util.List<Module> enabledModules = ModuleManager.getModules().stream()
                    .filter(Module::isEnabled)
                    .sorted((m1, m2) -> Integer.compare(tr.getWidth(m2.getName()), tr.getWidth(m1.getName())))
                    .toList();

            if (enabledModules.isEmpty()) return;

            int rightMargin = 10;
            int currentY = 10;
            int rowHeight = tr.fontHeight + 4;
            int paddingX = 5;

            int screenWidth = client.getWindow().getScaledWidth();

            for (Module m : enabledModules) {
                int textWidth = tr.getWidth(m.getName());
                int bgWidth = textWidth + (paddingX * 2);
                int bgX = screenWidth - bgWidth - rightMargin;

                context.fill(bgX, currentY, screenWidth - rightMargin, currentY + rowHeight, 0x6CFFFFFF);

                int textX = bgX + paddingX;
                int textY = currentY + (rowHeight / 2 - tr.fontHeight / 2);
                context.drawText(tr, m.getName(), textX, textY, 0xFFFFFFFF, false);

                currentY += rowHeight;
            }
        }
    }
}