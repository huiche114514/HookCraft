package com.nanaki.hookcraft.mixin.client.movement;

import com.nanaki.hookcraft.modules.movement.Velocity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
@Environment(EnvType.CLIENT)
public class VelocityMixin {
    @Inject(method = "takeKnockback", at = @At("HEAD"), cancellable = true)
    private void VelocityHook(double strength, double x, double z, CallbackInfo ci) {
        if (Velocity.CurrentVelocityState()) {
            ci.cancel();
        }
    }
}
