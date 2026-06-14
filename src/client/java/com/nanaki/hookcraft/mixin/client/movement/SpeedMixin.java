package com.nanaki.hookcraft.mixin.client.movement;

import com.nanaki.hookcraft.modules.ModuleManager;
import com.nanaki.hookcraft.modules.movement.Speed;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
@Environment(EnvType.CLIENT)
public class SpeedMixin {
    @Inject(method = "getMovementSpeed", at = @At("RETURN"), cancellable = true)
    private void SpeedHook(CallbackInfoReturnable<Float> cir) {
        if (Speed.CurrentSpeedState()) {
            Speed speedModule = (Speed) ModuleManager.getModuleByName("Speed");
            if (speedModule != null) {
                float value = speedModule.speedSetting.getValue().floatValue();
                cir.setReturnValue(value);
            }
        }
    }
}