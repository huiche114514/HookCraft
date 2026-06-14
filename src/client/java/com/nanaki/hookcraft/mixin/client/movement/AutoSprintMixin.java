package com.nanaki.hookcraft.mixin.client.movement;

import com.nanaki.hookcraft.modules.movement.AutoSprint;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
@Environment(EnvType.CLIENT)
public class AutoSprintMixin {
    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void AutoSprintHook(CallbackInfo ci) {
        ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;
        if (AutoSprint.CurrentAutoSprintState()) {
            player.setSprinting(true);
        }
    }
}
