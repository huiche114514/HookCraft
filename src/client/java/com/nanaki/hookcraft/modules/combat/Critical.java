package com.nanaki.hookcraft.modules.combat;

import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.modules.Module;
import com.nanaki.hookcraft.utils.SendMessage;

public class Critical extends Module {
    public static boolean CurrentCriticalState() {
        return Config.Combat.critical;
    }

    public Critical() {
        super(Category.COMBAT, Critical::CurrentCriticalState, enabled -> Config.Combat.critical = enabled);
    }

    @Override
    public boolean isEnabled() {
        return CurrentCriticalState();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        SendMessage.createMessage("Critical", CurrentCriticalState());
    }
}
