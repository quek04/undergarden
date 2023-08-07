package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGParticleTypes;

public class SeepingInkBlock extends Block {

	public static final VoxelShape SHAPE = Block.box(2.0F, 2.0F, 2.0F, 14.0F, 16.0F, 14.0F);

	public SeepingInkBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor accessor, BlockPos currentPos, BlockPos facingPos) {
		return !state.canSurvive(accessor, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, accessor, currentPos, facingPos);
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