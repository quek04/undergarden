package quek.undergarden.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import quek.undergarden.component.UndergardenData;
import quek.undergarden.registry.UGAttachments;

public class PurityEffect extends MobEffect {
	public PurityEffect() {
		super(MobEffectCategory.BENEFICIAL, 8236977);
	}

	@Override
	public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
		if (entity.tickCount % Mth.ceil(200.0F / (amplifier + 1)) == 0 && !entity.level().isClientSide()) {
			UndergardenData data = entity.getData(UGAttachments.UNDERGARDEN_DATA);
			if (data.uthericInfection() > 0) {
				entity.setData(UGAttachments.UNDERGARDEN_DATA, data.setInfectionLevel(data.uthericInfection() - (amplifier + 1)));
				entity.syncData(UGAttachments.UNDERGARDEN_DATA);
			}
		}
		return true;
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}
}