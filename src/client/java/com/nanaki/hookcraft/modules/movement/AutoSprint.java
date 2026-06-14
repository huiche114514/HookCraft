package com.nanaki.hookcraft.modules.movement;

import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.modules.Module;
import com.nanaki.hookcraft.utils.SendMessage;

public class AutoSprint extends Module {
    public static boolean CurrentAutoSprintState() {
        return Config.Movement.autoSprint;
    }

    public AutoSprint() {
        super(Category.MOVEMENT, AutoSprint::CurrentAutoSprintState, enabled -> Config.Movement.autoSprint = enabled);
    }

    @Override
    public boolean isEnabled() {
        return CurrentAutoSprintState();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        SendMessage.createMessage("AutoSprint", CurrentAutoSprintState());
    }
}