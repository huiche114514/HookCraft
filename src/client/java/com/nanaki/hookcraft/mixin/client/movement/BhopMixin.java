package com.nanaki.hookcraft.mixin.client.movement;

import com.nanaki.hookcraft.modules.movement.Bhop;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class BhopMixin {
    @Unique
    private static final double maxAirSpeed = 1.2;
    @Unique
    private static final double airAcceleration = 0.08;

    @Inject(method = "tickMovement", at = @At("RETURN"))
    private void BhopHook(CallbackInfo ci) {
        var player = (ClientPlayerEntity) (Object) this;

        if (Bhop.CurrentBHopState()) {
            if (!player.isOnGround()) {
                boolean isMoving = player.input.playerInput.forward() ||
                        player.input.playerInput.backward() ||
                        player.input.playerInput.left() ||
                        player.input.playerInput.right();

                if (isMoving) {
                    Vec3d vel = player.getVelocity();
                    double currentSpeed = Math.sqrt(vel.x * vel.x + vel.z * vel.z);
                    double targetSpeed = Bhop.bhopSpeedValue;

                    Vec3d movementInput = new Vec3d(
                            player.input.playerInput.left() ? 1 : (player.input.playerInput.right() ? -1 : 0),
                            0,
                            player.input.playerInput.forward() ? 1 : (player.input.playerInput.backward() ? -1 : 0)
                    );

                    double len = movementInput.horizontalLength();
                    if (len > 0) {
                        movementInput = movementInput.multiply(1.0 / len);
                    }

                    float yaw = player.getYaw();
                    double radYaw = Math.toRadians(yaw);

                    double newVelX = movementInput.x * Math.cos(radYaw) - movementInput.z * Math.sin(radYaw);
                    double newVelZ = movementInput.x * Math.sin(radYaw) + movementInput.z * Math.cos(radYaw);

                    double newSpeed = Math.min(currentSpeed + airAcceleration, targetSpeed);
                    newSpeed = Math.min(newSpeed, maxAirSpeed);

                    player.setVelocity(newVelX * newSpeed, vel.y, newVelZ * newSpeed);
                }
            }
        }
    }
}