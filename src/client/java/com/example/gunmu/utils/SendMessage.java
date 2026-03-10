package com.example.gunmu.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class SendMessage {
    public static void sendMessage(String text) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null && client.player != null) {
            client.inGameHud.getChatHud().addMessage(Text.literal(text));
        }
    }
}
