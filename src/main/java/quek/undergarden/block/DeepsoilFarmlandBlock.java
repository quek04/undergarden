package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.FarmlandWaterManager;
import net.neoforged.neoforge.common.util.TriState;
import quek.undergarden.registry.UGBlocks;

import javax.annotation.Nullable;

public class DeepsoilFarmlandBlock extends FarmBlock {

	public DeepsoilFarmlandBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, 0));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? UGBlocks.DEEPSOIL.get().defaultBlockState() : super.getStateForPlacement(context);
	}

	@Override
	public TriState canSustainPlant(BlockState state, BlockGetter level, BlockPos soilPosition, Direction facing, BlockState plant) {
		Block plantBlock = plant.getBlock();
		return plantBlock instanceof CropBlock || plantBlock instanceof StemBlock ? TriState.TRUE : super.canSustainPlant(state, level, soilPosition, facing, plant);
	}

	public static void turnToDeepsoil(@Nullable Entity entity, BlockState state, Level level, BlockPos pos) {
		level.setBlockAndUpdate(pos, pushEntitiesUp(state, UGBlocks.DEEPSOIL.get().defaultBlockState(), level, pos));
		level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, state));
	}

	private static boolean isNearWater(LevelReader level, BlockPos pos) {
		BlockState state = level.getBlockState(pos);
		for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
			if (state.canBeHydrated(level, pos, level.getFluidState(blockpos), blockpos)) {
				return true;
			}
		}
		return FarmlandWaterManager.hasBlockWaterTicket(level, pos);
	}

	private static boolean shouldMaintainFarmland(BlockGetter level, BlockPos pos) {
		return level.getBlockState(pos.above()).is(BlockTags.MAINTAINS_FARMLAND);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!state.canSurvive(level, pos)) {
			turnToDeepsoil(null, state, level, pos);
		}
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		int moistness = state.getValue(MOISTURE);
		if (!isNearWater(level, pos) && !level.isRainingAt(pos.above())) {
			if (moistness > 0) {
				level.setBlock(pos, state.setValue(MOISTURE, moistness - 1), 2);
			} else if (!shouldMaintainFarmland(level, pos)) {
				turnToDeepsoil(null, state, level, pos);
			}
		} else if (moistness < 7) {
			level.setBlock(pos, state.setValue(MOISTURE, 7), 2);
		}

	}

	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDamage) {
		if (!level.isClientSide() && CommonHooks.onFarmlandTrample(level, pos, UGBlocks.DEEPSOIL.get().defaultBlockState(), fallDamage, entity)) {
			turnToDeepsoil(entity, state, level, pos);
		}

		entity.causeFallDamage(fallDamage, 1.0F, level.damageSources().fall());
	}
}