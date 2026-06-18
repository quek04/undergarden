package quek.undergarden.block.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import quek.undergarden.registry.UGParticleTypes;
import quek.undergarden.registry.UGSoundEvents;

public abstract class VirulentMixFluid extends BaseFlowingFluid {

	public VirulentMixFluid(Properties properties) {
		super(properties);
	}

	@Override
	protected void animateTick(Level level, BlockPos pos, FluidState state, RandomSource random) {
		if (state.isSource()) {
			BlockPos above = pos.above();
			if (level.isEmptyBlock(above) && random.nextInt(500) == 0) {
				level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), UGSoundEvents.VIRULENT_BUBBLE.get(), SoundSource.BLOCKS, 0.7F + random.nextFloat() * 0.2F, 0.8F + random.nextFloat() * 0.3F, false);
			}
		}
		if (!state.isSource() && !state.getValue(FALLING) && random.nextInt(64) == 0) {
			level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), UGSoundEvents.VIRULENT_FLOW.get(), SoundSource.BLOCKS, 0.75F + random.nextFloat() * 0.25F, random.nextFloat() + 0.5F, false);
		}
	}

	@Override
	protected ParticleOptions getDripParticle() {
		return UGParticleTypes.DRIPPING_VIRULENT.get();
	}

	public static class Flowing extends VirulentMixFluid {
		public Flowing(Properties properties) {
			super(properties);
			this.registerDefaultState(this.getStateDefinition().any().setValue(LEVEL, 7));
		}

		@Override
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		@Override
		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		@Override
		public boolean isSource(FluidState state) {
			return false;
		}
	}

	public static class Source extends VirulentMixFluid {
		public Source(Properties properties) {
			super(properties);
		}

		@Override
		public int getAmount(FluidState state) {
			return 8;
		}

		@Override
		public boolean isSource(FluidState state) {
			return true;
		}
	}
}
