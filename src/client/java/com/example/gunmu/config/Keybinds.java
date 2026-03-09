package com.example.gunmu.config;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Keybinds {

    public static KeyBinding toggleNightVision;
    public static KeyBinding toggleSpeed;

    public static void register() {
        toggleNightVision = new KeyBinding(
                "key.gunmu.toggle_night_vision",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                null
        );

        KeyBindingHelper.registerKeyBinding(toggleNightVision);

        toggleSpeed = new KeyBinding(
                "key.gunmu.toggle_speed",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                null
        );

        KeyBindingHelper.registerKeyBinding(toggleSpeed);
    }

    public static void tick() {
        if (toggleNightVision == null) return;

        while (toggleNightVision.wasPressed()) {
            Config.Visual.nightVision =
                    !Config.Visual.nightVision;
        }

        if (toggleSpeed == null) return;

        while (toggleSpeed.wasPressed()) {
            Config.Movement.speed =
                    !Config.Movement.speed;
        }
    }
}