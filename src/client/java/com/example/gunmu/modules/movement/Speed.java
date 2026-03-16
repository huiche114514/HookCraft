package com.example.gunmu.modules.movement;

import com.example.gunmu.config.Config;
import com.example.gunmu.utils.SendMessage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.attribute.EntityAttributes;

public class Speed {

    private static double oldSpeed = -1.0;

    public static void SpeedState() {
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
        SendMessage.sendStateMessage("Speed",currentSpeedState);
    }
}