package com.nanaki.hookcraft.modules.movement;

import com.nanaki.hookcraft.clickgui.setting.DoubleSetting;
import com.nanaki.hookcraft.clickgui.setting.Setting;
import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.modules.Module;
import com.nanaki.hookcraft.utils.SendMessage;

public class Speed extends Module {
    public static boolean CurrentSpeedState() {
        return Config.Movement.speed;
    }

    public final Setting<Double> speedSetting = addSetting(new DoubleSetting.Builder()
            .name("Speed")
            .defaultValue(0.3)
            .min(0.1)
            .max(1.0)
            .build()
    );

    public Speed() {
        super(Category.MOVEMENT, Speed::CurrentSpeedState, enabled -> Config.Movement.speed = enabled);
    }

    @Override
    public boolean isEnabled() {
        return CurrentSpeedState();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        SendMessage.createMessage("Speed", CurrentSpeedState());
    }
}