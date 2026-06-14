package com.nanaki.hookcraft.mixin.client.visual;

import com.nanaki.hookcraft.modules.visual.NoHurtCam;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
@Environment(EnvType.CLIENT)
public class NoHurtCamMixin {
    @Inject(method = "tiltViewWhenHurt", at = @At("HEAD"), cancellable = true)
    private void NoHurtCamHook(MatrixStack matrices, float tickProgress, CallbackInfo ci) {
        if (NoHurtCam.CurrentNoHurtCamState()) {
            ci.cancel();
        }
    }
}
