package com.nanaki.hookcraft.mixin.client.combat;


import com.nanaki.hookcraft.modules.combat.NoDelay;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
@Environment(EnvType.CLIENT)
public class NoDelayMixin {
    @Inject(method = "getAttackCooldownProgress", at = @At("HEAD"), cancellable = true)
    private void NoDelayHook(float baseTime, CallbackInfoReturnable<Float> cir) {
        if (NoDelay.CurrentNoDelayState()) {
            cir.setReturnValue(1.0F);
        }
    }
}
