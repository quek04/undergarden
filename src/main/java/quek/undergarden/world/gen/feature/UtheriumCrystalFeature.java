package quek.undergarden.world.gen.feature;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.LargeDripstoneConfiguration;
import quek.undergarden.registry.UGBlocks;

import javax.annotation.Nullable;
import java.util.Optional;

public class UtheriumCrystalFeature extends Feature<UtheriumCrystalConfiguration> {

	private static final ImmutableList<Block> CAN_PLACE_ON = ImmutableList.of(UGBlocks.DREADROCK.get(), UGBlocks.DREADROCK_ROGDORIUM_ORE.get(), UGBlocks.DREADROCK_UTHERIUM_ORE.get(), UGBlocks.UTHERIUM_GROWTH.get(), UGBlocks.COARSE_DEEPSOIL.get());

	public UtheriumCrystalFeature(Codec<UtheriumCrystalConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<UtheriumCrystalConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos blockpos = context.origin();
		UtheriumCrystalConfiguration baseConfig = context.config();
		RandomSource random = context.random();
		if (!canPlaceAt(level, blockpos.mutable())) {
			return false;
		} else {
			if (random.nextFloat() < baseConfig.crystalChance()) {
				LargeDripstoneConfiguration config = baseConfig.crystalConfig();
				Optional<Column> optional = Column.scan(level, blockpos, config.floorToCeilingSearchRange, DripstoneUtils::isEmptyOrWater, state -> CAN_PLACE_ON.contains(state.getBlock()));
				if (optional.isPresent() && optional.get() instanceof Column.Range range) {
					if (range.height() < 4) {
						return false;
					} else {
						int i = (int) ((float) range.height() * config.maxColumnRadiusToCaveHeightRatio);
						int j = Mth.clamp(i, config.columnRadius.getMinValue(), config.columnRadius.getMaxValue());
						int k = Mth.randomBetweenInclusive(random, config.columnRadius.getMinValue(), j);
						LargeCrystal crystal = makeCrystal(blockpos.atY(range.ceiling() - 1), false, random, k, config.stalactiteBluntness, config.heightScale);
						LargeCrystal crystal1 = makeCrystal(blockpos.atY(range.floor() + 1), true, random, k, config.stalagmiteBluntness, config.heightScale);

						boolean flag = crystal.moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(level);
						boolean flag1 = crystal1.moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(level);
						if (flag) {
							crystal.placeBlocks(level, random);
						}

						if (flag1) {
							crystal1.placeBlocks(level, random);
						}

						return true;
					}
				} else {
					LargeCrystal crystal = makeCrystal(blockpos, true, random, config.columnRadius.sample(random), config.stalagmiteBluntness, config.heightScale);

					boolean flag = crystal.moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(level);
					if (flag) {
						crystal.placeBlocks(level, random);
					}
					return true;
				}
			} else {
				ColumnFeatureConfiguration config = baseConfig.clusterConfig();
				int heightSample = config.height().sample(random);
				boolean flag = random.nextFloat() < 0.9F;
				int k = Math.min(heightSample, flag ? 5 : 8);
				int l = flag ? 50 : 15;
				boolean flag1 = false;

				for (BlockPos blockpos1 : BlockPos.randomBetweenClosed(random, l, blockpos.getX() - k, blockpos.getY(), blockpos.getZ() - k, blockpos.getX() + k, blockpos.getY(), blockpos.getZ() + k)) {
					int i1 = heightSample - blockpos1.distManhattan(blockpos);
					if (i1 >= 0) {
						flag1 |= this.placeColumn(level, blockpos1, i1, config.reach().sample(random));
					}
				}

				return flag1;
			}
		}
	}

	private static LargeCrystal makeCrystal(BlockPos rootPos, boolean up, RandomSource random, int radius, FloatProvider bluntness, FloatProvider scale) {
		return new LargeCrystal(rootPos, up, radius, bluntness.sample(random), scale.sample(random));
	}

	private boolean placeColumn(LevelAccessor accessor, BlockPos pos, int distance, int reach) {
		boolean flag = false;

		for (BlockPos blockpos : BlockPos.betweenClosed(pos.getX() - reach, pos.getY(), pos.getZ() - reach, pos.getX() + reach, pos.getY(), pos.getZ() + reach)) {
			int i = blockpos.distManhattan(pos);
			BlockPos blockpos1 = isAir(accessor, blockpos) ? findSurface(accessor, blockpos.mutable(), i) : findAir(accessor, blockpos.mutable(), i);
			if (blockpos1 != null) {
				int j = distance - i / 2;

				for (BlockPos.MutableBlockPos mutable = blockpos1.mutable(); j >= 0; --j) {
					if (isAir(accessor, mutable)) {
						this.setBlock(accessor, mutable, UGBlocks.UTHERIUM_GROWTH.get().defaultBlockState());
						mutable.move(Direction.UP);
						flag = true;
					} else {
						if (!accessor.getBlockState(mutable).is(UGBlocks.UTHERIUM_GROWTH.get())) {
							break;
						}

						mutable.move(Direction.UP);
					}
				}
			}
		}

		return flag;
	}

	@Nullable
	private static BlockPos findSurface(LevelAccessor accessor, BlockPos.MutableBlockPos pos, int distance) {
		while (pos.getY() > accessor.getMinBuildHeight() + 1 && distance > 0) {
			--distance;
			if (canPlaceAt(accessor, pos)) {
				return pos;
			}

			pos.move(Direction.DOWN);
		}

		return null;
	}

	private static boolean canPlaceAt(LevelAccessor accessor, BlockPos.MutableBlockPos pos) {
		if (!isAir(accessor, pos)) {
			return false;
		} else {
			BlockState blockstate = accessor.getBlockState(pos.move(Direction.DOWN));
			pos.move(Direction.UP);
			return !blockstate.isAir() && CAN_PLACE_ON.contains(blockstate.getBlock());
		}
	}

	@Nullable
	private static BlockPos findAir(LevelAccessor accessor, BlockPos.MutableBlockPos pos, int distance) {
		while (pos.getY() < accessor.getMaxBuildHeight() && distance > 0) {
			--distance;
			BlockState blockstate = accessor.getBlockState(pos);
			if (!CAN_PLACE_ON.contains(blockstate.getBlock())) {
				return null;
			}

			if (blockstate.isAir()) {
				return pos;
			}

			pos.move(Direction.UP);
		}

		return null;
	}

	private static boolean isAir(LevelAccessor accessor, BlockPos pos) {
		BlockState blockstate = accessor.getBlockState(pos);
		return blockstate.isAir();
	}

	static final class LargeCrystal {
		private BlockPos root;
		private final boolean pointingUp;
		private int radius;
		private final double bluntness;
		private final double scale;

		LargeCrystal(BlockPos pRoot, boolean pPointingUp, int radius, double pBluntness, double pScale) {
			this.root = pRoot;
			this.pointingUp = pPointingUp;
			this.radius = radius;
			this.bluntness = pBluntness;
			this.scale = pScale;
		}

		boolean moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(WorldGenLevel level) {
			while (this.radius > 1) {
				BlockPos.MutableBlockPos pos = this.root.mutable();
				int i = Math.min(10, this.getHeightAtRadius(0.0F));

				for (int j = 0; j < i; ++j) {
					if (level.getBlockState(pos).is(Blocks.LAVA)) {
						return false;
					}

					if (DripstoneUtils.isCircleMostlyEmbeddedInStone(level, pos, this.radius)) {
						this.root = pos;
						return true;
					}

					pos.move(this.pointingUp ? Direction.DOWN : Direction.UP);
				}

				this.radius /= 2;
			}

			return false;
		}

		private int getHeightAtRadius(float radius) {
			return (int) DripstoneUtils.getDripstoneHeight((double) radius, (double) this.radius, this.scale, this.bluntness);
		}

		void placeBlocks(WorldGenLevel level, RandomSource random) {
			for (int i = -this.radius; i <= this.radius; ++i) {
				for (int j = -this.radius; j <= this.radius; ++j) {
					float f = Mth.sqrt((float) (i * i + j * j));
					if (!(f > (float) this.radius)) {
						int k = this.getHeightAtRadius(f);
						if (k > 0) {
							if ((double) random.nextFloat() < 0.2D) {
								k = (int) ((float) k * Mth.randomBetween(random, 0.8F, 1.0F));
							}

							BlockPos.MutableBlockPos pos = this.root.offset(i, 0, j).mutable();
							boolean flag = false;
							int l = this.pointingUp ? level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ()) : Integer.MAX_VALUE;

							for (int i1 = 0; i1 < k && pos.getY() < l; ++i1) {
								if (level.getBlockState(pos).isAir()) {
									flag = true;
									level.setBlock(pos, UGBlocks.UTHERIUM_GROWTH.get().defaultBlockState(), 2);
								} else if (flag && level.getBlockState(pos).is(BlockTags.BASE_STONE_OVERWORLD)) {
									break;
								}

								pos.move(this.pointingUp ? Direction.UP : Direction.DOWN);
							}
						}
					}
				}
			}
		}
	}
}
