package com.nanaki.hookcraft.modules.movement;

import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.utils.SendMessage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.attribute.EntityAttributes;

public class Speed {

    private static double oldSpeed = -1.0;

    public static void SpeedMain() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;

        var speedAttr = client.player.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
        if (speedAttr == null) return;

        boolean currentSpeedState = Config.Movement.speed;

        if (currentSpeedState) {
            if (oldSpeed < 0) oldSpeed = speedAttr.getBaseValue();
            speedAttr.setBaseValue(0.3);
        } else {
            if (oldSpeed >= 0) {
                speedAttr.setBaseValue(oldSpeed);
                oldSpeed = -1.0;
            }
        }
        SendMessage.sendStateMessage("Speed", currentSpeedState);
    }
}