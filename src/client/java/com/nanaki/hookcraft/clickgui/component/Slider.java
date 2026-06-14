package com.nanaki.hookcraft.clickgui.component;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Slider {
    public String name;
    private final Supplier<Double> getter;
    private final Consumer<Double> setter;
    public double min, max;

    public Slider(String name, double min, double max, Supplier<Double> getter, Consumer<Double> setter) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.getter = getter;
        this.setter = setter;
    }

    public double getValue() {
        return getter.get();
    }

    public String getDisplayValue() {
        return String.format("%.2f", getter.get());
    }

    public void setValue(double value) {
        double clamped = Math.clamp(value, min, max);
        setter.accept(clamped);
    }
}