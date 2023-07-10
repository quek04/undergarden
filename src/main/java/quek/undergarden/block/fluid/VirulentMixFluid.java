package quek.undergarden.block.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import quek.undergarden.registry.UGSoundEvents;

public abstract class VirulentMixFluid extends ForgeFlowingFluid {

	public VirulentMixFluid(Properties properties) {
		super(properties);
	}

	@Override
	protected void animateTick(Level level, BlockPos pos, FluidState state, RandomSource random) {
		if (random.nextInt(200) == 0) {
			level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), UGSoundEvents.VIRULENT_BUBBLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
		}
		if (!state.isSource() && random.nextInt(64) == 0) {
			level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), UGSoundEvents.VIRULENT_FLOW.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
		}
	}

	@Override
	protected boolean isRandomlyTicking() {
		return true;
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
