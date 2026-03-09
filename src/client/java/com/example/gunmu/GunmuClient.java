package com.example.gunmu;

import com.example.gunmu.config.Keybinds;
import com.example.gunmu.modules.combat.Combat;
import com.example.gunmu.modules.movement.Movement;
import com.example.gunmu.modules.visual.Visual;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class GunmuClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Keybinds.register();
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			Keybinds.tick();
			Combat.CombatModulesState();
			Movement.MovementModulesState();
			Visual.VisualModulesState();
		});
	}
}