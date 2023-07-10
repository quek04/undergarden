package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class HangingGrongleLeavesBlock extends DoublePlantBlock {

	private static final VoxelShape LOWER_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	private static final VoxelShape UPPER_SHAPE = Block.box(1.0D, 5.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	public HangingGrongleLeavesBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		Vec3 offset = state.getOffset(level, pos);
		return state.getValue(HALF) == DoubleBlockHalf.LOWER ? LOWER_SHAPE.move(offset.x, offset.y, offset.z) : UPPER_SHAPE.move(offset.x, offset.y, offset.z);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.is(BlockTags.LEAVES);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
		DoubleBlockHalf half = state.getValue(HALF);
		if (facing.getAxis() != Direction.Axis.Y || half == DoubleBlockHalf.LOWER != (facing == Direction.DOWN) || facingState.is(this) && facingState.getValue(HALF) != half) {
			return half == DoubleBlockHalf.LOWER && facing == Direction.UP && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : state;
		} else {
			return Blocks.AIR.defaultBlockState();
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos pos = context.getClickedPos();
		Level level = context.getLevel();
		return level.getBlockState(pos.below()).canBeReplaced(context) ? this.defaultBlockState() : null;
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack pStack) {
		BlockPos downPos = pos.below();
		level.setBlock(downPos, copyWaterloggedFrom(level, downPos, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER)), 3);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockPos upPos = pos.above();
		BlockState upState = level.getBlockState(upPos);
		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			return this.mayPlaceOn(level.getBlockState(upPos), level, pos);
		} else {
			return upState.is(this) && upState.getValue(HALF) == DoubleBlockHalf.LOWER;
		}
	}
}