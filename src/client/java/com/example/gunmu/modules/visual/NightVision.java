package com.example.gunmu.modules.visual;

import com.example.gunmu.config.Config;
import com.example.gunmu.utils.SendMessage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class NightVision {

    public static void NightVisionState() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;

        boolean currentNightVisionState = Config.Visual.nightVision;

        if (currentNightVisionState) {
            client.player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.NIGHT_VISION,
                    Integer.MAX_VALUE,
                    0,
                    false,
                    false,
                    true
            ));
        } else {
            client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        }
        SendMessage.sendStateMessage("NightVision",currentNightVisionState);
    }
}