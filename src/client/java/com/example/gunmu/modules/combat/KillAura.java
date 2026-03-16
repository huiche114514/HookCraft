package com.example.gunmu.modules.combat;

import com.example.gunmu.config.Config;
import com.example.gunmu.utils.SendMessage;
import net.minecraft.client.MinecraftClient;

public class KillAura {
    public static void KillAuraState() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;

        boolean currentKillAuraState = Config.Combat.killAura;

        if (currentKillAuraState) {

        } else {

        }
        SendMessage.sendStateMessage("KillAura",currentKillAuraState);
    }
}
