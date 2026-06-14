package com.nanaki.hookcraft.modules.movement;

import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.modules.Module;
import com.nanaki.hookcraft.utils.MovementInput;
import com.nanaki.hookcraft.utils.SendMessage;
import net.minecraft.client.MinecraftClient;

public class Bhop extends Module {
    public static boolean CurrentBHopState() {
        return Config.Movement.bhop;
    }

    public Bhop() {
        super(Category.MOVEMENT, Bhop::CurrentBHopState, enabled -> Config.Movement.bhop = enabled);
    }

    @Override
    public boolean isEnabled() {
        return CurrentBHopState();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        SendMessage.createMessage("Bhop", CurrentBHopState());
    }

    @Override
    public void onUpdate() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;

        if (CurrentBHopState()) {
            if (client.player.isOnGround() && MovementInput.IsMoving()) {
                client.player.jump();
            }
        }
    }
}