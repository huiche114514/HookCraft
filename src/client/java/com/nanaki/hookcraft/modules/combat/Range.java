package com.nanaki.hookcraft.modules.combat;

import com.nanaki.hookcraft.clickgui.setting.BooleanSetting;
import com.nanaki.hookcraft.clickgui.setting.DoubleSetting;
import com.nanaki.hookcraft.clickgui.setting.Setting;
import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.modules.Module;
import com.nanaki.hookcraft.utils.SendMessage;

public class Range extends Module {
    public static boolean CurrentRangeState() {
        return Config.Combat.range;
    }

    public final Setting<Boolean> maxRangeSetting = addSetting(new BooleanSetting.Builder()
            .name("512 Block")
            .defaultValue(false)
            .build()
    );

    public final Setting<Double> rangeSetting = addSetting(new DoubleSetting.Builder()
            .name("Value")
            .defaultValue(3)
            .min(3)
            .max(20)
            .build()
    );

    public Range() {
        super(Category.COMBAT, Range::CurrentRangeState, enabled -> Config.Combat.range = enabled);
    }

    @Override
    public boolean isEnabled() {
        return CurrentRangeState();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        SendMessage.createMessage("Range", CurrentRangeState());
    }
}
