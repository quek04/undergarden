package quek.undergarden.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import quek.undergarden.event.UthericInfectionEvents;
import quek.undergarden.registry.UGAttachments;

public class PurityEffect extends MobEffect {
	public PurityEffect() {
		super(MobEffectCategory.BENEFICIAL, 8236977);
	}

	@Override
	public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
		if (entity.tickCount % (200 / (amplifier + 1)) == 0 && !entity.level().isClientSide()) {
			float data = entity.getData(UGAttachments.UTHERIC_INFECTION);
			if (data > 0) {
				entity.setData(UGAttachments.UTHERIC_INFECTION.get(), data - (amplifier + 1));
				UthericInfectionEvents.sendInfectionSyncPacket(entity);
			}
		}
		return true;
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}
}