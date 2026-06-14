package com.nanaki.hookcraft.modules.combat;

import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.modules.Module;
import com.nanaki.hookcraft.utils.SendMessage;

public class NoDelay extends Module {
    public static boolean CurrentNoDelayState() {
        return Config.Combat.noDelay;
    }

    public NoDelay() {
        super(Category.COMBAT, NoDelay::CurrentNoDelayState, enabled -> Config.Combat.noDelay = enabled);
    }

    @Override
    public boolean isEnabled() {
        return CurrentNoDelayState();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        SendMessage.createMessage("NoDelay", CurrentNoDelayState());
    }
}
