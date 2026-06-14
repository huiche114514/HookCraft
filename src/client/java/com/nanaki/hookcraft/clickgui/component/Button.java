package com.nanaki.hookcraft.clickgui.component;

import com.nanaki.hookcraft.clickgui.setting.BooleanSetting;
import com.nanaki.hookcraft.clickgui.setting.DoubleSetting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Button {
    public String name;
    private final Supplier<Boolean> getter;
    private final Consumer<Boolean> setter;
    public List<Slider> sliders = new ArrayList<>();
    public List<Checkbox> checkboxes = new ArrayList<>();
    public boolean extended = false;

    public Button(String name, Supplier<Boolean> getter, Consumer<Boolean> setter) {
        this.name = name;
        this.getter = getter;
        this.setter = setter;
    }

    public void addSlider(DoubleSetting setting) {
        this.sliders.add(new Slider(setting.getName(), setting.getMin(), setting.getMax(), setting::getValue, setting::setValue));
    }

    public void addCheckbox(BooleanSetting setting) {
        this.checkboxes.add(new Checkbox(setting.getName(), setting::getValue, setting::setValue));
    }

    public boolean isEnabled() {
        return getter.get();
    }

    public void toggle() {
        setter.accept(!isEnabled());
    }
}