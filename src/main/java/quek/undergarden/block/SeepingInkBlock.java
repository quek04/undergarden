package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGBlocks;

public class SeepingInkBlock extends Block {

	public SeepingInkBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		return !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
		return reader.getBlockState(pos.above()).isFaceSturdy(reader, pos.above(), Direction.DOWN);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (level.getRandom().nextInt(level.getBlockState(pos.above()).is(UGBlocks.INK_MUSHROOM_CAP.get()) ? 5 : 15) == 0 && level.getBlockState(pos.below()).isAir()) {
			level.addParticle(UGParticleTypes.DRIPPING_INK.get(), pos.getX() + 0.55D, pos.getY() + 0.1D, pos.getZ() + 0.45D, 0.0F, 0.0F, 0.0F);
		}
	}
}