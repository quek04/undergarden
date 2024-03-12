package quek.undergarden.world.gen.feature;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.BasaltColumnsFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;
import quek.undergarden.registry.UGBlocks;

import javax.annotation.Nullable;

public class UtheriumGrowthColumnsFeature extends BasaltColumnsFeature {
	private static final ImmutableList<Block> CAN_PLACE_ON = ImmutableList.of(UGBlocks.DREADROCK.get(), UGBlocks.DREADROCK_ROGDORIUM_ORE.get());
	public UtheriumGrowthColumnsFeature(Codec<ColumnFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<ColumnFeatureConfiguration> context) {
		BlockPos blockpos = context.origin();
		WorldGenLevel level = context.level();
		RandomSource random = context.random();
		ColumnFeatureConfiguration config = context.config();
		if (!canPlaceAt(level, blockpos.mutable())) {
			return false;
		} else {
			int heightSample = config.height().sample(random);
			boolean flag = random.nextFloat() < 0.9F;
			int k = Math.min(heightSample, flag ? 5 : 8);
			int l = flag ? 50 : 15;
			boolean flag1 = false;

			for(BlockPos blockpos1 : BlockPos.randomBetweenClosed(random, l, blockpos.getX() - k, blockpos.getY(), blockpos.getZ() - k, blockpos.getX() + k, blockpos.getY(), blockpos.getZ() + k)) {
				int i1 = heightSample - blockpos1.distManhattan(blockpos);
				if (i1 >= 0) {
					flag1 |= this.placeColumn(level, blockpos1, i1, config.reach().sample(random));
				}
			}

			return flag1;
		}
	}

	private boolean placeColumn(LevelAccessor pLevel, BlockPos pPos, int pDistance, int pReach) {
		boolean flag = false;

		for(BlockPos blockpos : BlockPos.betweenClosed(pPos.getX() - pReach, pPos.getY(), pPos.getZ() - pReach, pPos.getX() + pReach, pPos.getY(), pPos.getZ() + pReach)) {
			int i = blockpos.distManhattan(pPos);
			BlockPos blockpos1 = isAir(pLevel, blockpos) ? findSurface(pLevel, blockpos.mutable(), i) : findAir(pLevel, blockpos.mutable(), i);
			if (blockpos1 != null) {
				int j = pDistance - i / 2;

				for(BlockPos.MutableBlockPos blockpos$mutableblockpos = blockpos1.mutable(); j >= 0; --j) {
					if (isAir(pLevel, blockpos$mutableblockpos)) {
						this.setBlock(pLevel, blockpos$mutableblockpos, UGBlocks.UTHERIUM_GROWTH.get().defaultBlockState());
						blockpos$mutableblockpos.move(Direction.UP);
						flag = true;
					} else {
						if (!pLevel.getBlockState(blockpos$mutableblockpos).is(UGBlocks.UTHERIUM_GROWTH.get())) {
							break;
						}

						blockpos$mutableblockpos.move(Direction.UP);
					}
				}
			}
		}

		return flag;
	}

	@Nullable
	private static BlockPos findSurface(LevelAccessor pLevel, BlockPos.MutableBlockPos pPos, int pDistance) {
		while(pPos.getY() > pLevel.getMinBuildHeight() + 1 && pDistance > 0) {
			--pDistance;
			if (canPlaceAt(pLevel, pPos)) {
				return pPos;
			}

			pPos.move(Direction.DOWN);
		}

		return null;
	}

	private static boolean canPlaceAt(LevelAccessor pLevel, BlockPos.MutableBlockPos pPos) {
		if (!isAir(pLevel, pPos)) {
			return false;
		} else {
			BlockState blockstate = pLevel.getBlockState(pPos.move(Direction.DOWN));
			pPos.move(Direction.UP);
			return !blockstate.isAir() && CAN_PLACE_ON.contains(blockstate.getBlock());
		}
	}

	@Nullable
	private static BlockPos findAir(LevelAccessor pLevel, BlockPos.MutableBlockPos pPos, int pDistance) {
		while(pPos.getY() < pLevel.getMaxBuildHeight() && pDistance > 0) {
			--pDistance;
			BlockState blockstate = pLevel.getBlockState(pPos);
			if (!CAN_PLACE_ON.contains(blockstate.getBlock())) {
				return null;
			}

			if (blockstate.isAir()) {
				return pPos;
			}

			pPos.move(Direction.UP);
		}

		return null;
	}

	private static boolean isAir(LevelAccessor pLevel, BlockPos pPos) {
		BlockState blockstate = pLevel.getBlockState(pPos);
		return blockstate.isAir();
	}
}