package com.nanaki.hookcraft.mixin.client.movement;

import com.nanaki.hookcraft.modules.movement.Bhop;
import com.nanaki.hookcraft.utils.MovementInput;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
@Environment(EnvType.CLIENT)
public class BhopMixin {
    @Unique
    private static final double bhopSpeedValue = 1.0;
    @Unique
    private static final double maxSpeed = 1.2;
    @Unique
    private static final double tickSpeedPlus = 0.08;

    @Inject(method = "tickMovement", at = @At("RETURN"))
    private void BhopHook(CallbackInfo ci) {
        var player = (ClientPlayerEntity) (Object) this;

        if (Bhop.CurrentBHopState()) {
            if (!player.isOnGround() && MovementInput.IsMoving()) {
                Vec3d vel = player.getVelocity();

                double currentSpeed = Math.sqrt(vel.x * vel.x + vel.z * vel.z);
                double newSpeed = Math.min(currentSpeed + tickSpeedPlus, bhopSpeedValue);
                newSpeed = Math.min(newSpeed, maxSpeed);

                double inputX = player.input.playerInput.left() ? 1 : (player.input.playerInput.right() ? -1 : 0);
                double inputZ = player.input.playerInput.forward() ? 1 : (player.input.playerInput.backward() ? -1 : 0);

                double length = Math.sqrt(inputX * inputX + inputZ * inputZ);
                if (length != 0) {
                    inputX /= length;
                    inputZ /= length;
                }

                float yaw = player.getYaw();
                double radYaw = Math.toRadians(yaw);
                double sin = Math.sin(radYaw);
                double cos = Math.cos(radYaw);

                double newVelX = inputX * cos - inputZ * sin;
                double newVelZ = inputX * sin + inputZ * cos;

                player.setVelocity(newVelX * newSpeed, vel.y, newVelZ * newSpeed);
            }
        }
    }
}