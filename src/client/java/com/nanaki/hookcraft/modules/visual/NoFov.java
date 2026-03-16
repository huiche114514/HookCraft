package com.nanaki.hookcraft.modules.visual;


import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.utils.SendMessage;

public class NoFov {
    public static boolean currentNoFovState() {
        return Config.Visual.noFov;
    }

    public static void NoFovMain() {
        boolean currentNoFovState = Config.Visual.noFov;
        SendMessage.sendStateMessage("NoFov", currentNoFovState);
    }
}
