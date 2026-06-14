package com.nanaki.hookcraft.modules.movement;

import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.modules.Module;
import com.nanaki.hookcraft.utils.SendMessage;

public class Velocity extends Module {
    public static boolean CurrentVelocityState() {
        return Config.Movement.velocity;
    }

    public Velocity() {
        super(Category.MOVEMENT, Velocity::CurrentVelocityState, enabled -> Config.Movement.velocity = enabled);
    }

    @Override
    public boolean isEnabled() {
        return CurrentVelocityState();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        SendMessage.createMessage("Velocity", CurrentVelocityState());
    }
}