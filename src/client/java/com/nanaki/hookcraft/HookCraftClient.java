package com.nanaki.hookcraft;

import com.nanaki.hookcraft.config.Keybinds;
import com.nanaki.hookcraft.config.ModulesListText;
import com.nanaki.hookcraft.modules.combat.Combat;
import com.nanaki.hookcraft.modules.gui.Gui;
import com.nanaki.hookcraft.modules.movement.Movement;
import com.nanaki.hookcraft.modules.visual.Visual;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class HookCraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Keybinds.register();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Keybinds.tick();
            ModulesListText.getAllModules();
            Combat.CombatModulesState();
            Movement.MovementModulesState();
            Visual.VisualModulesState();
        });
        HudRenderCallback.EVENT.register((context, tickDelta) -> {
            Gui.GuiState(context);
        });
    }
}