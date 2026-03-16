package com.nanaki.hookcraft.modules.combat;

import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.utils.SendMessage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;

import java.util.List;

public class KillAura {

    public static void KillAuraMain() {

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null || client.world == null) return;

        boolean currentKillAuraState = Config.Combat.killAura;

        if (currentKillAuraState) {

            double range = 3.5;

            Box box = client.player.getBoundingBox().expand(range);

            List<Entity> entities = client.world.getOtherEntities(
                    client.player,
                    box
            );

            LivingEntity target = null;
            double closest = range;

            for (Entity entity : entities) {

                if (!(entity instanceof LivingEntity living)) continue;

                if (living == client.player) continue;
                if (living.isDead()) continue;

                double distance = client.player.distanceTo(living);

                if (distance < closest) {
                    closest = distance;
                    target = living;
                }
            }

            if (target != null) {

                if (client.player.getAttackCooldownProgress(0) >= 1) {

                    if (client.interactionManager != null) {
                        client.interactionManager.attackEntity(
                                client.player,
                                target
                        );
                    }

                    client.player.swingHand(Hand.MAIN_HAND);
                }
            }

        }

        SendMessage.sendStateMessage("KillAura", currentKillAuraState);
    }
}