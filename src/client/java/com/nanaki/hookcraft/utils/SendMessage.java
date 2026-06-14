package com.nanaki.hookcraft.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class SendMessage {
    public static void showMessage(String text) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null && client.player != null) {
            client.inGameHud.getChatHud().addMessage(Text.literal(text));
        }
    }

    public static void createMessage(String ModulesName, boolean currentState) {
        if (currentState) {
            showMessage("HookCraft: " + ModulesName + "_ON");
        } else {
            showMessage("HookCraft: " + ModulesName + "_OFF");
        }
    }
}