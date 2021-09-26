package quek.undergarden.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.registry.UGEffects;

@Mod.EventBusSubscriber
public class FeatherweightEffect extends MobEffect {

    public FeatherweightEffect() {
        super(MobEffectCategory.HARMFUL, 13158655);
    }

    @SubscribeEvent
    public static void applyFeatherweight(LivingKnockBackEvent event) {
        LivingEntity entity = event.getEntityLiving();

        if(entity.hasEffect(UGEffects.FEATHERWEIGHT.get())) {
            int amplifier = (entity.getEffect(UGEffects.FEATHERWEIGHT.get()).getAmplifier() + 2);

            event.setStrength(event.getStrength() * amplifier);
        }
    }
}
