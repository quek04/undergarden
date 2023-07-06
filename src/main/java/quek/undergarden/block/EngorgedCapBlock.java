package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import quek.undergarden.registry.UGParticleTypes;

public class EngorgedCapBlock extends Block {
	public EngorgedCapBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (level.getRandom().nextInt(15) == 0) {
			this.trySpawnDripParticles(level, pos, state);
		}
	}

	private void trySpawnDripParticles(Level level, BlockPos pos, BlockState state) {
		if (state.getFluidState().isEmpty() && !(level.getRandom().nextFloat() < 0.3F)) {
			VoxelShape voxelshape = state.getCollisionShape(level, pos);
			double d0 = voxelshape.max(Direction.Axis.Y);
			if (d0 >= 1.0D && !state.is(BlockTags.IMPERMEABLE)) {
				double d1 = voxelshape.min(Direction.Axis.Y);
				if (d1 > 0.0D) {
					this.spawnParticle(level, pos, voxelshape, (double) pos.getY() + d1 - 0.1D);
				} else {
					BlockPos blockpos = pos.below();
					BlockState blockstate = level.getBlockState(blockpos);
					VoxelShape voxelshape1 = blockstate.getCollisionShape(level, blockpos);
					double d2 = voxelshape1.max(Direction.Axis.Y);
					if ((d2 < 1.0D || !blockstate.isCollisionShapeFullBlock(level, blockpos)) && blockstate.getFluidState().isEmpty()) {
						this.spawnParticle(level, pos, voxelshape, (double) pos.getY() - 0.1D);
					}
				}
			}
		}
	}

	private void spawnParticle(Level level, BlockPos pos, VoxelShape shape, double y) {
		this.spawnFluidParticle(level, (double) pos.getX() + shape.min(Direction.Axis.X), (double) pos.getX() + shape.max(Direction.Axis.X), (double) pos.getZ() + shape.min(Direction.Axis.Z), (double) pos.getZ() + shape.max(Direction.Axis.Z), y);
	}

	private void spawnFluidParticle(Level level, double x1, double x2, double z1, double z2, double y) {
		level.addParticle(UGParticleTypes.DRIPPING_BLOOD.get(), Mth.lerp(level.getRandom().nextDouble(), x1, x2), y, Mth.lerp(level.getRandom().nextDouble(), z1, z2), 0.0D, 0.0D, 0.0D);
	}
}
