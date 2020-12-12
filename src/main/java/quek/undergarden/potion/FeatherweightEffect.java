package quek.undergarden.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.registry.UGEffects;

@Mod.EventBusSubscriber
public class FeatherweightEffect extends Effect {

    public FeatherweightEffect() {
        super(EffectType.HARMFUL, 13158655);
    }

    @SubscribeEvent
    public static void applyFeatherweight(LivingKnockBackEvent event) {
        LivingEntity entity = event.getEntityLiving();

        if(entity.isPotionActive(UGEffects.FEATHERWEIGHT.get())) {
            int amplifier = (entity.getActivePotionEffect(UGEffects.FEATHERWEIGHT.get()).getAmplifier() + 2);

            event.setStrength(event.getStrength() * amplifier);
        }
    }
}
