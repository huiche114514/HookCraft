package com.nanaki.hookcraft;

import com.nanaki.hookcraft.config.Keybinds;
import com.nanaki.hookcraft.modules.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.util.Identifier;

public class HookCraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Keybinds.register();
        ModuleManager.init();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Keybinds.tick();
            ModuleManager.onClientTick();
        });

        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Identifier.of("hookcraft", "modules_list"),
                (context, tickDelta) -> ModuleManager.onRenderHUD(context)
        );
    }
}