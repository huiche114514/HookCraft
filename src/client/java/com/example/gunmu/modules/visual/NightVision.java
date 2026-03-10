package com.example.gunmu.modules.visual;

import com.example.gunmu.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import static com.example.gunmu.utils.SendMessage.sendMessage;

public class NightVision {

    private static boolean lastNightVisionState = false;

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

        if (currentNightVisionState != lastNightVisionState) {
            if (currentNightVisionState) {
                sendMessage("NightVision_ON");
            } else {
                sendMessage("NightVision_OFF");
            }
            lastNightVisionState = currentNightVisionState;
        }
    }
}