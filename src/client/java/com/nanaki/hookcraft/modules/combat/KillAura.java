package com.nanaki.hookcraft.modules.combat;

import com.nanaki.hookcraft.clickgui.setting.DoubleSetting;
import com.nanaki.hookcraft.clickgui.setting.Setting;
import com.nanaki.hookcraft.config.Config;
import com.nanaki.hookcraft.modules.Module;
import com.nanaki.hookcraft.utils.SendMessage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;

import java.util.List;

public class KillAura extends Module {
    private long lastAttackTime = 0;

    public static boolean CurrentKillAuraState() {
        return Config.Combat.killAura;
    }

    public final Setting<Double> cpsSetting = addSetting(new DoubleSetting.Builder()
            .name("CPS")
            .defaultValue(10.0)
            .min(1.0)
            .max(20.0)
            .build());

    public final Setting<Double> rangeSetting = addSetting(new DoubleSetting.Builder()
            .name("Range")
            .defaultValue(4.0)
            .min(1.0)
            .max(20.0)
            .build());

    public KillAura() {
        super(Category.COMBAT, KillAura::CurrentKillAuraState, enabled -> Config.Combat.killAura = enabled);
    }

    @Override
    public boolean isEnabled() {
        return CurrentKillAuraState();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        SendMessage.createMessage("KillAura", CurrentKillAuraState());
    }

    @Override
    public void onUpdate() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null || client.world == null) return;

        if (CurrentKillAuraState()) {
            double currentRange = rangeSetting.getValue();
            double currentCps = cpsSetting.getValue();

            long attackInterval = (long) (1000.0 / currentCps);
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastAttackTime >= attackInterval) {
                Box box = client.player.getBoundingBox().expand(currentRange);
                List<Entity> entities = client.world.getOtherEntities(client.player, box);
                LivingEntity target = null;
                double closest = currentRange;

                for (Entity entity : entities) {
                    if (!(entity instanceof LivingEntity living)) continue;
                    if (living == client.player || living.isDead()) continue;

                    double distance = client.player.distanceTo(living);
                    if (distance < closest) {
                        closest = distance;
                        target = living;
                    }
                }

                if (target != null && client.interactionManager != null) {
                    client.interactionManager.attackEntity(client.player, target);
                    client.player.swingHand(Hand.MAIN_HAND);
                    lastAttackTime = currentTime;
                }
            }
        }
    }
}