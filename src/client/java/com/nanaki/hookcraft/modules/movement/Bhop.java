package com.nanaki.hookcraft.modules.movement;

import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.utils.SendMessage;
import net.minecraft.client.MinecraftClient;

public class Bhop {

    private static boolean wasOnGround = false;
    private static int jumpTicks = 0;
    private static boolean hasJumped = false;
    public static double bhopSpeedValue = 0.75;

    public static boolean CurrentBHopState() {
        return Config.Movement.bhop;
    }

    public static void BhopMain() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;

        var player = client.player;

        boolean isMoving = player.input.playerInput.forward() ||
                player.input.playerInput.backward() ||
                player.input.playerInput.left() ||
                player.input.playerInput.right();

        if (CurrentBHopState()) {
            if (player.isOnGround() && isMoving && !hasJumped) {
                player.jump();
                hasJumped = true;
                jumpTicks = 4;
            }

            if (player.isOnGround() && !wasOnGround && isMoving && hasJumped) {
                if (jumpTicks <= 0) {
                    player.jump();
                    jumpTicks = 4;
                }
            }

            if (!player.isOnGround()) {
                hasJumped = true;
            }

            if (!isMoving) {
                hasJumped = false;
            }

            wasOnGround = player.isOnGround();
            if (jumpTicks > 0) jumpTicks--;

        } else {
            wasOnGround = false;
            jumpTicks = 0;
            hasJumped = false;
        }

        SendMessage.sendStateMessage("Bhop", CurrentBHopState());
    }
}