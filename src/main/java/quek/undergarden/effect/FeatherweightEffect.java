package quek.undergarden.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingKnockBackEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import quek.undergarden.registry.UGEffects;

@Mod.EventBusSubscriber
public class FeatherweightEffect extends MobEffect {

	public FeatherweightEffect() {
		super(MobEffectCategory.HARMFUL, 13158655);
	}

	@SubscribeEvent
	public static void applyFeatherweight(LivingKnockBackEvent event) {
		LivingEntity entity = event.getEntity();

		if (entity.hasEffect(UGEffects.FEATHERWEIGHT.get())) {
			int amplifier = (entity.getEffect(UGEffects.FEATHERWEIGHT.get()).getAmplifier() + 2);

			event.setStrength(event.getStrength() * amplifier);
		}
	}
}
