package quek.undergarden.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import quek.undergarden.registry.UGBlocks;

import java.util.function.BiConsumer;

public class SpreadingDeepturfBlock extends SpreadingSnowyDirtBlock implements BonemealableBlock {

	public static final MapCodec<SpreadingDeepturfBlock> CODEC = simpleCodec(SpreadingDeepturfBlock::new);

	public SpreadingDeepturfBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends SpreadingSnowyDirtBlock> codec() {
		return CODEC;
	}

	private static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
		BlockPos blockpos = pos.above();
		return canBeGrass(state, level, pos) && !level.getFluidState(blockpos).is(FluidTags.WATER);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!canBeGrass(state, level, pos)) {
			if (!level.isAreaLoaded(pos, 3))
				return;
			level.setBlockAndUpdate(pos, UGBlocks.DEEPSOIL.get().defaultBlockState());
		} else {
			if (!level.isAreaLoaded(pos, 3)) return;
			BlockState blockstate = this.defaultBlockState();

			for (int i = 0; i < 4; ++i) {
				BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
				if (level.getBlockState(blockpos).is(UGBlocks.DEEPSOIL.get()) && canPropagate(blockstate, level, blockpos)) {
					level.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY, level.getBlockState(blockpos.above()).is(Blocks.SNOW)));
				}
			}
		}
	}

	@Override
	public boolean onTreeGrow(BlockState state, LevelReader level, BiConsumer<BlockPos, BlockState> placeFunction, RandomSource randomSource, BlockPos pos, TreeConfiguration config) {
		placeFunction.accept(pos, UGBlocks.DEEPSOIL.get().defaultBlockState());
		return true;
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
		return level.getBlockState(pos.above()).isAir();
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		BlockPos blockpos = pos.above();

		label48:
		for (int i = 0; i < 128; ++i) {
			BlockPos blockpos1 = blockpos;

			for (int j = 0; j < i / 16; ++j) {
				blockpos1 = blockpos1.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
				if (!level.getBlockState(blockpos1.below()).is(this) || level.getBlockState(blockpos1).isCollisionShapeFullBlock(level, blockpos1)) {
					continue label48;
				}
			}

			BlockState blockstate2 = level.getBlockState(blockpos1);
			if (blockstate2.is(deepturfOrShimmerweed(random).getBlock()) && random.nextInt(10) == 0) {
				((BonemealableBlock) deepturfOrShimmerweed(random).getBlock()).performBonemeal(level, random, blockpos1, blockstate2);
			}

			if (blockstate2.isAir()) {
				if (deepturfOrShimmerweed(random).canSurvive(level, blockpos1)) {
					level.setBlock(blockpos1, deepturfOrShimmerweed(random), 3);
				}
			}
		}
	}

	private static BlockState deepturfOrShimmerweed(RandomSource random) {
		if (random.nextInt(10) == 0) {
			return UGBlocks.SHIMMERWEED.get().defaultBlockState();
		} else return UGBlocks.DEEPTURF.get().defaultBlockState();
	}
}