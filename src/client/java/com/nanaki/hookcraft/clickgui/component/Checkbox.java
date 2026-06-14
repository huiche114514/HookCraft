package com.nanaki.hookcraft.clickgui.component;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Checkbox {
    public String name;
    private final Supplier<Boolean> getter;
    private final Consumer<Boolean> setter;

    public Checkbox(String name, Supplier<Boolean> getter, Consumer<Boolean> setter) {
        this.name = name;
        this.getter = getter;
        this.setter = setter;
    }

    public boolean getValue() {
        return getter.get();
    }

    public void toggle() {
        setter.accept(!getValue());
    }
}