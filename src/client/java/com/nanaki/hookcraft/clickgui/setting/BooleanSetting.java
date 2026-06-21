package com.nanaki.hookcraft.clickgui.setting;

public class BooleanSetting extends Setting<Boolean> {
    private BooleanSetting(String name, boolean defaultValue) {
        super(name, defaultValue);
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }

    public static class Builder {
        private String name;
        private boolean defaultValue;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder defaultValue(boolean defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public BooleanSetting build() {
            return new BooleanSetting(name, defaultValue);
        }
    }
}