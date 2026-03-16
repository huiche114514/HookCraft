package com.nanaki.hookcraft.config;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Keybinds {

    public static KeyBinding toggleNightVision;
    public static KeyBinding toggleSpeed;
    public static KeyBinding toggleKillAura;
    public static KeyBinding toggleNoFov;

    public static void register() {
        toggleNightVision = new KeyBinding(
                "key.gunmu.toggle_night_vision",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                null
        );
        KeyBindingHelper.registerKeyBinding(toggleNightVision);

        toggleNoFov = new KeyBinding(
                "key.gunmu.toggle_nofov",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_X,
                null
        );
        KeyBindingHelper.registerKeyBinding(toggleNoFov);

        toggleSpeed = new KeyBinding(
                "key.gunmu.toggle_speed",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                null
        );
        KeyBindingHelper.registerKeyBinding(toggleSpeed);

        toggleKillAura = new KeyBinding(
                "key.gunmu.toggle_killaura",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                null
        );
        KeyBindingHelper.registerKeyBinding(toggleKillAura);
    }

    public static void tick() {
        if (toggleNightVision != null && toggleNightVision.wasPressed()) {
            Config.Visual.nightVision = !Config.Visual.nightVision;
        }

        if (toggleNoFov != null && toggleNoFov.wasPressed()) {
            Config.Visual.noFov = !Config.Visual.noFov;
        }

        if (toggleSpeed != null && toggleSpeed.wasPressed()) {
            Config.Movement.speed = !Config.Movement.speed;
        }

        if (toggleKillAura != null && toggleKillAura.wasPressed()) {
            Config.Combat.killAura = !Config.Combat.killAura;
        }
    }
}