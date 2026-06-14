package com.nanaki.hookcraft.mixin.client.visual;

import com.nanaki.hookcraft.modules.ModuleManager;
import com.nanaki.hookcraft.modules.visual.NoFov;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
@Environment(EnvType.CLIENT)
public class NoFovMixin {
    @Inject(method = "getFov", at = @At("HEAD"), cancellable = true)
    private void NoFovHook(Camera camera, float tickProgress, boolean changingFov, CallbackInfoReturnable<Float> cir) {
        if (NoFov.CurrentNoFovState()) {
            NoFov noFovModule = (NoFov) ModuleManager.getModuleByName("NoFov");
            if (noFovModule != null) {
                float value = noFovModule.fovSetting.getValue().floatValue();
                cir.setReturnValue(value);
            }
        }
    }
}