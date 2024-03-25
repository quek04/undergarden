package quek.undergarden.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.PacketDistributor;
import quek.undergarden.network.UthericInfectionPacket;
import quek.undergarden.registry.UGAttachments;

public class PurityEffect extends MobEffect {
	public PurityEffect() {
		super(MobEffectCategory.BENEFICIAL, 8236977);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity.tickCount % 20 == 0 && !entity.level().isClientSide()) {
			int data = entity.getData(UGAttachments.UTHERIC_INFECTION);
			if (data > 0) {
				entity.setData(UGAttachments.UTHERIC_INFECTION.get(), data - (amplifier + 1));
				PacketDistributor.TRACKING_ENTITY_AND_SELF.with(entity).send(new UthericInfectionPacket(entity.getId(), entity.getData(UGAttachments.UTHERIC_INFECTION)));
			}
		}
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
		return true;
	}
}