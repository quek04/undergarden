package quek.undergarden.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGParticleTypes;

public class GooeyEffect extends MobEffect {

	public GooeyEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		BlockState blockstate = UGBlocks.GOO.get().defaultBlockState();
		BlockPos pos = entity.blockPosition();

		if (entity.level().isEmptyBlock(pos) && blockstate.canSurvive(entity.level(), pos)) {
			entity.level().setBlockAndUpdate(pos, blockstate);
		}
		return true;
	}

	@Override
	public ParticleOptions createParticleOptions(MobEffectInstance instance) {
		return UGParticleTypes.FALLING_GOO.get();
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}
}