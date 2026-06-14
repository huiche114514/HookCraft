package com.nanaki.hookcraft.clickgui.setting;

public class DoubleSetting extends Setting<Double> {
    private final double min;
    private final double max;

    private DoubleSetting(String name, double defaultValue, double min, double max) {
        super(name, defaultValue);
        this.min = min;
        this.max = max;
    }

    @Override
    public void setValue(Double value) {
        this.value = Math.clamp(value, min, max);
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public static class Builder {
        private String name;
        private double defaultValue;
        private double min;
        private double max;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder defaultValue(double defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public Builder min(double min) {
            this.min = min;
            return this;
        }

        public Builder max(double max) {
            this.max = max;
            return this;
        }

        public DoubleSetting build() {
            return new DoubleSetting(name, defaultValue, min, max);
        }
    }
}
