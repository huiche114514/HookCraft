package com.nanaki.hookcraft.modules;

import com.nanaki.hookcraft.modules.combat.Critical;
import com.nanaki.hookcraft.modules.combat.KillAura;
import com.nanaki.hookcraft.modules.combat.NoDelay;
import com.nanaki.hookcraft.modules.combat.Range;
import com.nanaki.hookcraft.modules.hud.ModulesList;
import com.nanaki.hookcraft.modules.movement.AutoSprint;
import com.nanaki.hookcraft.modules.movement.Bhop;
import com.nanaki.hookcraft.modules.movement.Speed;
import com.nanaki.hookcraft.modules.movement.Velocity;
import com.nanaki.hookcraft.modules.visual.NightVision;
import com.nanaki.hookcraft.modules.visual.NoFov;
import com.nanaki.hookcraft.modules.visual.NoHurtCam;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private static final List<Module> modules = new ArrayList<>();

    public static void init() {
        modules.add(new Critical());
        modules.add(new KillAura());
        modules.add(new NoDelay());
        modules.add(new Range());

        modules.add(new AutoSprint());
        modules.add(new Bhop());
        modules.add(new Speed());
        modules.add(new Velocity());

        modules.add(new NightVision());
        modules.add(new NoFov());
        modules.add(new NoHurtCam());

        modules.add(new ModulesList());
    }

    public static List<Module> getModules() {
        return modules;
    }

    public static List<Module> getModulesByCategory(Module.Category category) {
        return modules.stream()
                .filter(m -> m.getCategory() == category)
                .collect(Collectors.toList());
    }

    public static Module getModuleByName(String name) {
        for (Module module : modules) {
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }
        }
        return null;
    }

    public static void onClientTick() {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.onUpdate();
            }
        }
    }

    public static void onRenderHUD(DrawContext context) {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.onRenderHUD(context);
            }
        }
    }
}