package quek.undergarden.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import quek.undergarden.registry.UGEffects;

public class VirulenceEffect extends MobEffect {

	public VirulenceEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
		if (entity.getHealth() > 2.0F && !entity.hasEffect(UGEffects.VIRULENT_RESISTANCE)) {
			entity.hurtServer(level, entity.damageSources().magic(), 2.0F);
		}
		return true;
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		int j = 50 >> amplifier;
		if (j > 0) {
			return duration % j == 0;
		} else {
			return true;
		}
	}
}