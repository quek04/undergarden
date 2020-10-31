package quek.undergarden.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.registry.UGEffects;

@Mod.EventBusSubscriber
public class BrittlenessEffect extends Effect {

    public BrittlenessEffect() {
        super(EffectType.HARMFUL, 9843250);
    }

    @SubscribeEvent
    public static void applyBrittleness(LivingDamageEvent event) {
        LivingEntity entity = event.getEntityLiving();
        DamageSource source = event.getSource();
        float damage = event.getAmount();

        if (entity.isPotionActive(UGEffects.brittleness.get()) && source != DamageSource.OUT_OF_WORLD) {
            int amplifier = (entity.getActivePotionEffect(UGEffects.brittleness.get()).getAmplifier() + 1) + (entity.getTotalArmorValue() / 2) * 2;

            event.setAmount(damage * amplifier);
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration > 0;
    }
}
