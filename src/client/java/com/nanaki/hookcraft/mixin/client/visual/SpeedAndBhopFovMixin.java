package com.nanaki.hookcraft.mixin.client.visual;

import com.nanaki.hookcraft.modules.movement.Bhop;
import com.nanaki.hookcraft.modules.movement.Speed;
import com.nanaki.hookcraft.modules.visual.NoFov;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class SpeedAndBhopFovMixin {
    @Inject(method = "getFov", at = @At("HEAD"), cancellable = true)
    private void SpeedFovHook(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Float> cir) {
        if (Speed.CurrentSpeedState() || Bhop.CurrentBHopState()) {
            float NoFovValue = NoFov.FovValue;
            cir.setReturnValue(NoFovValue + 10);
        }
    }
}
