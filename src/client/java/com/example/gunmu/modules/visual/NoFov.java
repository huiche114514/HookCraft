package com.example.gunmu.modules.visual;

import com.example.gunmu.config.Config;
import net.minecraft.client.MinecraftClient;

import static com.example.gunmu.utils.SendMessage.sendMessage;

public class NoFov {
    private static boolean lastNoFovState = false;

    public static void NoFovState() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;

        boolean currentNoFovState = Config.Visual.noFov;

    }
}
