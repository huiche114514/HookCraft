package com.nanaki.hookcraft.modules.movement;

import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.utils.SendMessage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.attribute.EntityAttributes;

public class Speed {

    private static double oldSpeed = -1.0;
    public static double speedValue = 0.3;

    public static boolean CurrentSpeedState() {
        return Config.Movement.speed;
    }

    public static void SpeedMain() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;

        var speedAttr = client.player.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
        if (speedAttr == null) return;

        if (CurrentSpeedState()) {
            if (oldSpeed < 0) oldSpeed = speedAttr.getBaseValue();
            speedAttr.setBaseValue(speedValue);

        } else {
            if (oldSpeed >= 0) {
                speedAttr.setBaseValue(oldSpeed);
                oldSpeed = -1.0;
            }
        }
        SendMessage.sendStateMessage("Speed", CurrentSpeedState());
    }
}