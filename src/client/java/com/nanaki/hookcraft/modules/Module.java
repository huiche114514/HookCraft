package com.nanaki.hookcraft.modules;

import com.nanaki.hookcraft.clickgui.setting.Setting;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Module {
    private final String name;
    private final Category category;
    private final List<Setting<?>> settings = new ArrayList<>();

    private final Supplier<Boolean> getter;
    private final Consumer<Boolean> setter;

    public Module(Category category, Supplier<Boolean> getter, Consumer<Boolean> setter) {
        this.category = category;
        this.getter = getter;
        this.setter = setter;

        String className = this.getClass().getSimpleName();
        this.name = className.substring(0, 1).toUpperCase() + className.substring(1);
    }

    protected <T> Setting<T> addSetting(Setting<T> setting) {
        this.settings.add(setting);
        return setting;
    }

    public void onUpdate() {
    }

    public void onRenderHUD(DrawContext context) {
    }

    public boolean isEnabled() {
        return getter.get();
    }

    public void setEnabled(boolean enabled) {
        if (isEnabled() == enabled) return;
        setter.accept(enabled);
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public List<Setting<?>> getSettings() {
        return settings;
    }

    public enum Category {
        COMBAT, MOVEMENT, VISUAL, HUD
    }
}