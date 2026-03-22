package com.nanaki.hookcraft.modules.visual;


import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.utils.SendMessage;

public class NoFov {
    public static float FovValue = 120f;
    public static boolean CurrentNoFovState() {
        return Config.Visual.noFov;
    }

    public static void NoFovMain() {
        SendMessage.sendStateMessage("NoFov", CurrentNoFovState());
    }
}
