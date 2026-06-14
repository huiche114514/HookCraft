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

            int rightMargin = 10;
            int y = 10;
            int textHeight = tr.fontHeight + 2;

            java.util.List<Module> enabledModules = ModuleManager.getModules().stream()
                    .filter(Module::isEnabled)
                    .sorted((m1, m2) -> Integer.compare(tr.getWidth(m2.getName()), tr.getWidth(m1.getName())))
                    .toList();

            for (Module m : enabledModules) {
                int textWidth = tr.getWidth(m.getName());
                int x = client.getWindow().getScaledWidth() - textWidth - rightMargin;
                context.drawTextWithShadow(tr, m.getName(), x, y, 0xFFFFFFFF);
                y += textHeight;
            }
        }
    }
}