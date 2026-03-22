package com.nanaki.hookcraft.modules.visual;

import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.utils.SendMessage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class NightVision {
    public static boolean CurrentNightVisionState() {
        return Config.Visual.nightVision;
    }
    public static void NightVisionMain() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;

        if (CurrentNightVisionState()) {
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
        SendMessage.sendStateMessage("NightVision", CurrentNightVisionState());
    }
}