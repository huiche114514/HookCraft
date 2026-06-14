package com.nanaki.hookcraft.modules.visual;

import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.modules.Module;
import com.nanaki.hookcraft.utils.SendMessage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class NightVision extends Module {
    public static boolean CurrentNightVisionState() {
        return Config.Visual.nightVision;
    }

    public NightVision() {
        super(Category.VISUAL, NightVision::CurrentNightVisionState, enabled -> Config.Visual.nightVision = enabled);
    }

    @Override
    public boolean isEnabled() {
        return CurrentNightVisionState();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

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
        SendMessage.createMessage("NightVision", CurrentNightVisionState());
    }
}