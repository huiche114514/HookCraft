package com.nanaki.hookcraft.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public record ModulesListText(String name, boolean enabled) {
    public static List<ModulesListText> getAllModules() {
        List<ModulesListText> modules = new ArrayList<>();
        try {
            for (Class<?> categoryClass : Config.class.getDeclaredClasses()) {
                for (Field field : categoryClass.getDeclaredFields()) {
                    if (field.getType() != boolean.class) continue;
                    boolean value = field.getBoolean(null);
                    String moduleName = field.getName();
                    moduleName = moduleName.substring(0, 1).toUpperCase() + moduleName.substring(1);
                    modules.add(new ModulesListText(moduleName, value));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modules;
    }
}