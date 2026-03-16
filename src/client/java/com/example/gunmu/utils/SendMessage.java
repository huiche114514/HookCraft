package com.example.gunmu.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import java.util.HashMap;
import java.util.Map;

public class SendMessage {

    private static final Map<String, Boolean> lastStates = new HashMap<>();

    public static void sendMessage(String text) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null && client.player != null) {
            client.inGameHud.getChatHud().addMessage(Text.literal(text));
        }
    }

    public static void sendStateMessage(String ModulesName, boolean currentState) {
        Boolean lastState = lastStates.get(ModulesName);

        if (lastState == null || currentState != lastState) {
            if (currentState) {
                sendMessage(ModulesName+"_ON");
            } else {
                sendMessage(ModulesName+"_OFF");
            }
            lastStates.put(ModulesName, currentState);
        }
    }
}