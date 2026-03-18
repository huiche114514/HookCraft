package com.nanaki.hookcraft.mixin.client.visual;

import com.nanaki.hookcraft.modules.visual.NoFov;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class NoFovMixin {
    @Inject(method = "getFov", at = @At("HEAD"), cancellable = true)
    private void NoFovHook(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Float> cir) {
        if (NoFov.currentNoFovState()) {
            cir.setReturnValue(120f);
        }
    }
}