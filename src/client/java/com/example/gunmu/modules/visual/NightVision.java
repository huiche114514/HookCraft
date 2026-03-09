package com.example.gunmu.modules.visual;

import com.example.gunmu.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class NightVision {

    public static void NightVisionState() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;

        if (Config.Visual.nightVision) {
            client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 220, 0, false, false));
        } else {
            client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        }
    }
}