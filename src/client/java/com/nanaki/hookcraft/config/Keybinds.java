package com.nanaki.hookcraft.config;

import com.nanaki.hookcraft.clickgui.ClickGUI;
import com.nanaki.hookcraft.modules.Module;
import com.nanaki.hookcraft.modules.ModuleManager;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Keybinds {
    public static KeyBinding toggleBhop;
    public static KeyBinding toggleSpeed;
    public static KeyBinding toggleKillAura;
    public static KeyBinding toggleClickGui;

    public static void register() {
        toggleBhop = new KeyBinding(
                "key.movement.bhop",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                null
        );
        KeyBindingHelper.registerKeyBinding(toggleBhop);

        toggleSpeed = new KeyBinding(
                "key.movement.speed",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                null
        );
        KeyBindingHelper.registerKeyBinding(toggleSpeed);

        toggleKillAura = new KeyBinding(
                "key.combat.killaura",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                null
        );
        KeyBindingHelper.registerKeyBinding(toggleKillAura);

        toggleClickGui = new KeyBinding(
                "key.hud.clickgui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                null
        );
        KeyBindingHelper.registerKeyBinding(toggleClickGui);
    }

    public static void tick() {
        if (toggleBhop != null && toggleBhop.wasPressed()) {
            Module module = ModuleManager.getModuleByName("Bhop");
            if (module != null) {
                module.setEnabled(!module.isEnabled());
            }
        }

        if (toggleSpeed != null && toggleSpeed.wasPressed()) {
            Module module = ModuleManager.getModuleByName("Speed");
            if (module != null) {
                module.setEnabled(!module.isEnabled());
            }
        }

        if (toggleKillAura != null && toggleKillAura.wasPressed()) {
            Module module = ModuleManager.getModuleByName("KillAura");
            if (module != null) {
                module.setEnabled(!module.isEnabled());
            }
        }

        if (toggleClickGui != null && toggleClickGui.wasPressed()) {
            MinecraftClient.getInstance().setScreen(new ClickGUI());
        }
    }
}