package quek.undergarden.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class CloggrumLanternBlock extends FaceAttachedHorizontalDirectionalBlock implements SimpleWaterloggedBlock {

	public static final MapCodec<CloggrumLanternBlock> CODEC = simpleCodec(CloggrumLanternBlock::new);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	private static final VoxelShape FLOOR_SHAPE = Shapes.or(Block.box(7, 0, 7, 9, 3, 9), Block.box(4, 3, 4, 12, 13, 12));
	private static final VoxelShape CEILING_SHAPE = Shapes.or(Block.box(7, 13, 7, 9, 16, 9), Block.box(4, 3, 4, 12, 13, 12));
	private static final VoxelShape NORTH_WALL_SHAPE = Shapes.or(Block.box(7, 0, 9, 9, 3, 11), Block.box(7, 0, 11, 9, 2, 16), Block.box(4, 3, 6, 12, 13, 14));
	private static final VoxelShape WEST_WALL_SHAPE = Shapes.or(Block.box(9, 0, 7, 11, 3, 9), Block.box(11, 0, 7, 16, 2, 9), Block.box(6, 3, 4, 14, 13, 12));
	private static final VoxelShape SOUTH_WALL_SHAPE = Shapes.or(Block.box(7, 0, 5, 9, 3, 7), Block.box(7, 0, 0, 9, 2, 5), Block.box(4, 3, 2, 12, 13, 10));
	private static final VoxelShape EAST_WALL_SHAPE = Shapes.or(Block.box(5, 0, 7, 7, 3, 9), Block.box(0, 0, 7, 5, 2, 9), Block.box(2, 3, 4, 10, 13, 12));

	public CloggrumLanternBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FACE, AttachFace.FLOOR).setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
	}

	@Override
	protected MapCodec<? extends FaceAttachedHorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		Direction direction = getConnectedDirection(state).getOpposite();
		return Block.canSupportCenter(level, pos.relative(direction), direction.getOpposite());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return switch (getConnectedDirection(state)) {
			case UP -> FLOOR_SHAPE;
			case NORTH -> NORTH_WALL_SHAPE;
			case EAST -> EAST_WALL_SHAPE;
			case SOUTH -> SOUTH_WALL_SHAPE;
			case WEST -> WEST_WALL_SHAPE;
			default -> CEILING_SHAPE;
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACE, FACING, WATERLOGGED);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
		for (Direction direction : context.getNearestLookingDirections()) {
			BlockState blockstate;
			if (direction.getAxis() == Direction.Axis.Y) {
				blockstate = this.defaultBlockState().setValue(FACE, direction == Direction.UP ? AttachFace.CEILING : AttachFace.FLOOR).setValue(FACING, context.getHorizontalDirection());
			} else {
				blockstate = this.defaultBlockState().setValue(FACE, AttachFace.WALL).setValue(FACING, direction.getOpposite());
			}

			if (blockstate.canSurvive(context.getLevel(), context.getClickedPos())) {
				return blockstate.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
			}
		}
		return null;
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbor, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		if (state.getValue(WATERLOGGED)) {
			ticks.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		return getConnectedDirection(state).getOpposite() == directionToNeighbor && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, ticks, pos, directionToNeighbor, neighborPos, neighborState, random);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType type) {
		return false;
	}
}