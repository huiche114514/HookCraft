package com.nanaki.hookcraft.modules.visual;

import com.nanaki.hookcraft.clickgui.setting.DoubleSetting;
import com.nanaki.hookcraft.clickgui.setting.Setting;
import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.modules.Module;
import com.nanaki.hookcraft.utils.SendMessage;

public class NoFov extends Module {
    public static boolean CurrentNoFovState() {
        return Config.Visual.noFov;
    }

    public final Setting<Double> fovSetting = addSetting(new DoubleSetting.Builder()
            .name("Value")
            .defaultValue(120.0)
            .min(60.0)
            .max(150.0)
            .build()
    );

    public NoFov() {
        super(Category.VISUAL, NoFov::CurrentNoFovState, enabled -> Config.Visual.noFov = enabled);
    }

    @Override
    public boolean isEnabled() {
        return CurrentNoFovState();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        SendMessage.createMessage("NoFov", CurrentNoFovState());
    }
}