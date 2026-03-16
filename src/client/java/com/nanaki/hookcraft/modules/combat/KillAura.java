package com.nanaki.hookcraft.modules.combat;

import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.utils.SendMessage;
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
