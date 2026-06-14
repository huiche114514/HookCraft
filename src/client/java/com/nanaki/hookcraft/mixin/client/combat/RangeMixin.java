package com.nanaki.hookcraft.mixin.client.combat;

import com.nanaki.hookcraft.modules.ModuleManager;
import com.nanaki.hookcraft.modules.combat.Range;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.LivingEntity;

@Mixin(LivingEntity.class)
public class RangeMixin {
    @Inject(method = {"getAttributeValue"}, at = @At("RETURN"), cancellable = true)
    private static void RangeHook(RegistryEntry<EntityAttribute> attribute, CallbackInfoReturnable<Double> cir) {
        if (Range.CurrentRangeState()) {
            Range rangeModules = (Range) ModuleManager.getModuleByName("Range");
            if (rangeModules != null) {
                boolean state = rangeModules.maxRangeSetting.getValue();
                double value = rangeModules.rangeSetting.getValue();
                if (attribute == EntityAttributes.BLOCK_INTERACTION_RANGE || attribute == EntityAttributes.ENTITY_INTERACTION_RANGE) {
                    if (state) {
                        cir.setReturnValue(512.0);
                    } else {
                        cir.setReturnValue(value);
                    }
                 }
            }
        }
    }
}
