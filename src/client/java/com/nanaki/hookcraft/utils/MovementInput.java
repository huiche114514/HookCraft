package com.nanaki.hookcraft.utils;

import net.minecraft.client.MinecraftClient;

public class MovementInput {
    public static boolean IsMoving() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return false;

        var input = client.player.input.playerInput;

        return input.forward() || input.backward() || input.left() || input.right();
    }
}
