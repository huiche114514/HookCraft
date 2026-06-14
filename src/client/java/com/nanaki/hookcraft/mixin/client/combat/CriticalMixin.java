package com.nanaki.hookcraft.mixin.client.combat;

import com.nanaki.hookcraft.modules.combat.Critical;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
@Environment(EnvType.CLIENT)
public class CriticalMixin {
    @Inject(method = "isCriticalHit", at = @At("HEAD"), cancellable = true)
    private void CriticalHook(Entity target, CallbackInfoReturnable<Boolean> cir) {
        if (Critical.CurrentCriticalState()) {
            cir.setReturnValue(true);
        }
    }
}
