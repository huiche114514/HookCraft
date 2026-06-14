package com.nanaki.hookcraft.clickgui.setting;

public abstract class Setting<T> {
    private final String name;
    protected T value;

    public Setting(String name, T defaultValue) {
        this.name = name;
        this.value = defaultValue;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public abstract void setValue(T value);
}
