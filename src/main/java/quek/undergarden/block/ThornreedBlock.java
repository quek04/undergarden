package quek.undergarden.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import org.jspecify.annotations.Nullable;
import quek.undergarden.registry.UGTags;

public class ThornreedBlock extends Block implements SimpleWaterloggedBlock {
	public static final MapCodec<ThornreedBlock> CODEC = simpleCodec(ThornreedBlock::new);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final VoxelShape SHAPE = Block.column(12.0, 0.0, 16.0);

	@Override
	public MapCodec<ThornreedBlock> codec() {
		return CODEC;
	}

	public ThornreedBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!state.canSurvive(level, pos)) {
			level.destroyBlock(pos, true);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}

	@Override
	protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
		if (entity instanceof LivingEntity && !entity.is(UGTags.Entities.IMMUNE_TO_THORNREED)) {
			if (level instanceof ServerLevel serverLevel) {
				entity.hurtServer(serverLevel, level.damageSources().cactus(), 1.0F);
			}
		}
	}

	@Override
	public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState replacedFluidState = context.getLevel().getFluidState(context.getClickedPos());
		boolean isWaterSource = replacedFluidState.is(Fluids.WATER);
		return super.getStateForPlacement(context).setValue(WATERLOGGED, isWaterSource);
	}

	@Override
	protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (level.isEmptyBlock(pos.above()) || level.getFluidState(pos.above()).is(Fluids.WATER)) {
			int height = 1;

			while (level.getBlockState(pos.below(height)).is(this)) {
				height++;
			}

			if (height < 5) {
				if (CommonHooks.canCropGrow(level, pos, state, true)) {
					level.setBlockAndUpdate(pos.above(), state.setValue(WATERLOGGED, level.getFluidState(pos.above()).is(Fluids.WATER)));
					CommonHooks.fireCropGrowPost(level, pos.above(), this.defaultBlockState());
					level.setBlock(pos, state.setValue(WATERLOGGED, level.getFluidState(pos.above()).is(Fluids.WATER)), 260);
				}
			}
		}
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
		if (state.getValue(WATERLOGGED)) {
			ticks.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		return state.canSurvive(level, pos) ? super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random) : Blocks.AIR.defaultBlockState();
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockState stateBelow = level.getBlockState(pos.below());
		if (stateBelow.is(this)) {
			return true;
		} else {
			var soilDecision = stateBelow.canSustainPlant(level, pos.below(), Direction.UP, state);
			if (!soilDecision.isDefault()) return soilDecision.isTrue();
			if (stateBelow.is(UGTags.Blocks.SUPPORTS_THORNREED)) {
				if (level.getFluidState(pos).is(Fluids.WATER)) return true;
				BlockPos below = pos.below();

				for (Direction direction : Direction.Plane.HORIZONTAL) {
					BlockState blockState = level.getBlockState(below.relative(direction));
					FluidState fluidState = level.getFluidState(below.relative(direction));
					// Neo: Allow the fluid state to dynamically decide whether it hydrates here if the tag-based checks fail
					if (fluidState.is(UGTags.Fluids.SUPPORTS_THORNREED_ADJACENTLY) || blockState.is(UGTags.Blocks.SUPPORTS_THORNREED_ADJACENTLY) || state.canBeHydrated(level, pos, fluidState, below.relative(direction))) {
						return true;
					}
				}
			}

			return false;
		}
	}

	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
}