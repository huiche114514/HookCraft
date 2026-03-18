package com.nanaki.hookcraft.modules.gui;

import net.minecraft.client.gui.DrawContext;

public class Gui {
    public static void GuiState(DrawContext context) {
        ModulesList.ModulesListMain(context);
    }
}
