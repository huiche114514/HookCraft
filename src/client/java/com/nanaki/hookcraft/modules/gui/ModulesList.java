package com.nanaki.hookcraft.modules.gui;

import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.config.ModulesListText;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.util.List;

public class ModulesList {
    public static void ModulesListMain(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        boolean currentModulesListState = Config.Gui.modulesList;
        if (currentModulesListState) {

            TextRenderer tr = client.textRenderer;

            List<ModulesListText> modules = ModulesListText.getAllModules();

            int rightMargin = 10;
            int y = 10;
            int TextHeight = tr.fontHeight + 2;

            for (ModulesListText m : modules) {
                if (m.enabled()) {
                    int textWidth = tr.getWidth(m.name());
                    int x = client.getWindow().getScaledWidth() - textWidth - rightMargin;
                    context.drawTextWithShadow(
                            tr,
                            m.name(),
                            x,
                            y,
                            0xFFFFFFFF
                    );
                    y += TextHeight;
                }
            }
        }
    }
}