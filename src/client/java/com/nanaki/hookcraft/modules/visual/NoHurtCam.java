package com.nanaki.hookcraft.modules.visual;

import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.modules.Module;
import com.nanaki.hookcraft.utils.SendMessage;

public class NoHurtCam extends Module {
    public static boolean CurrentNoHurtCamState() {
        return Config.Visual.noHurtCam;
    }

    public NoHurtCam() {
        super(Category.VISUAL, NoHurtCam::CurrentNoHurtCamState, enabled -> Config.Visual.noHurtCam = enabled);
    }

    @Override
    public boolean isEnabled() {
        return CurrentNoHurtCamState();
    }

    @Override
    public void setEnabled(boolean enabled) {
        Config.Visual.noHurtCam = enabled;
        SendMessage.createMessage("NoHurtCam", CurrentNoHurtCamState());
    }
}