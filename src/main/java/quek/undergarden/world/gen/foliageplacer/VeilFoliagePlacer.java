package quek.undergarden.world.gen.foliageplacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import quek.undergarden.block.MushroomVeilBlock;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGFoliagePlacers;

public class VeilFoliagePlacer extends FoliagePlacer {

	public static final Codec<VeilFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> foliagePlacerParts(instance).apply(instance, VeilFoliagePlacer::new));

	public VeilFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return UGFoliagePlacers.VEIL.get();
	}

	@Override
	protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int height, int radius, int offset) {
		tryPlaceLeaf(level, blockSetter, random, config, attachment.pos());
		circle(-1, radius - 2, false, level, blockSetter, random, config, attachment.pos());
		circle(-2, radius - 2, false, level, blockSetter, random, config, attachment.pos());
		circle(-3, radius - 1, false, level, blockSetter, random, config, attachment.pos());
		circle(-4, radius, true, level, blockSetter, random, config, attachment.pos());
	}

	private void circle(final int centerY, final int radius, boolean veil, LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, BlockPos pos) {
		int d = (5 - radius * 4) / 4;
		int x = 0;
		int z = radius;
		BlockPos.MutableBlockPos posMutable = new BlockPos.MutableBlockPos();

		do {
			posMutable.setWithOffset(pos, x + 1, centerY, z);
			tryPlaceLeaf(level, blockSetter, random, config, posMutable);
			if (veil) {
				posMutable.setWithOffset(pos, x, centerY, z - 1);
				if (x == 1 || x == 2) {
					addHangingVeil(posMutable, VineBlock.SOUTH, VineBlock.EAST, level, blockSetter, random);
				} else {
					addHangingVeil(posMutable, VineBlock.SOUTH, level, blockSetter, random);
				}
			}
			posMutable.setWithOffset(pos, x, centerY, z);
			tryPlaceLeaf(level, blockSetter, random, config, posMutable);

			posMutable.setWithOffset(pos, x + 1, centerY, -z);
			tryPlaceLeaf(level, blockSetter, random, config, posMutable);
			if (veil) {
				posMutable.setWithOffset(pos, x, centerY, -z + 1);
				if (x == 1 || x == 2) {
					addHangingVeil(posMutable, VineBlock.NORTH, VineBlock.EAST, level, blockSetter, random);
				} else {
					addHangingVeil(posMutable, VineBlock.NORTH, level, blockSetter, random);
				}
			}
			posMutable.setWithOffset(pos, x, centerY, -z);
			tryPlaceLeaf(level, blockSetter, random, config, posMutable);

			posMutable.setWithOffset(pos, -x - 1, centerY, z);
			tryPlaceLeaf(level, blockSetter, random, config, posMutable);
			if (veil) {
				posMutable.setWithOffset(pos, -x, centerY, z - 1);
				if (x == 1 || x == 2) {
					addHangingVeil(posMutable, VineBlock.SOUTH, VineBlock.WEST, level, blockSetter, random);
				}
			}
			posMutable.setWithOffset(pos, -x, centerY, z);
			tryPlaceLeaf(level, blockSetter, random, config, posMutable);

			posMutable.setWithOffset(pos, -x - 1, centerY, -z);
			tryPlaceLeaf(level, blockSetter, random, config, posMutable);
			if (veil) {
				posMutable.setWithOffset(pos, -x, centerY, -z + 1);
				if (x == 1 || x == 2) {
					addHangingVeil(posMutable, VineBlock.NORTH, VineBlock.WEST, level, blockSetter, random);
				}
			}
			posMutable.setWithOffset(pos, -x, centerY, -z);
			tryPlaceLeaf(level, blockSetter, random, config, posMutable);


			posMutable.setWithOffset(pos, z, centerY, x);
			tryPlaceLeaf(level, blockSetter, random, config, posMutable);
			if (veil) {
				posMutable.setWithOffset(pos, z - 1, centerY, x);
				if (x == 0) {
					addHangingVeil(posMutable, VineBlock.EAST, level, blockSetter, random);
				}
			}

			posMutable.setWithOffset(pos, z, centerY, -x);
			tryPlaceLeaf(level, blockSetter, random, config, posMutable);

			posMutable.setWithOffset(pos, -z, centerY, x);
			tryPlaceLeaf(level, blockSetter, random, config, posMutable);
			if (veil) {
				posMutable.setWithOffset(pos, -z + 1, centerY, x);
				if (x == 0) {
					addHangingVeil(posMutable, VineBlock.WEST, level, blockSetter, random);
				}
			}

			posMutable.setWithOffset(pos, -z, centerY, -x);
			tryPlaceLeaf(level, blockSetter, random, config, posMutable);

			if (d < 0) {
				d += 2 * x + 1;
			} else {
				d += 2 * (x - z) + 1;
				z--;
			}
			x++;
		} while (x <= z);

	}

	private void addHangingVeil(BlockPos.MutableBlockPos posMutable, BooleanProperty side, LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random) {
		int length = 4 - random.nextInt(3);
		blockSetter.set(posMutable, UGBlocks.MUSHROOM_VEIL.get().defaultBlockState().setValue(side, true).setValue(VineBlock.UP, true));
		for (int i = 0; i <= length; i++) {
			if (level.isStateAtPosition(posMutable, BlockBehaviour.BlockStateBase::isAir)) {
				if (i == length || !level.isStateAtPosition(posMutable.below(), BlockBehaviour.BlockStateBase::isAir)) {
					blockSetter.set(posMutable, UGBlocks.MUSHROOM_VEIL.get().defaultBlockState().setValue(side, true).setValue(MushroomVeilBlock.END, true));
					break;
				}
				blockSetter.set(posMutable, UGBlocks.MUSHROOM_VEIL.get().defaultBlockState().setValue(side, true));
			}
			posMutable.move(Direction.DOWN);
		}
	}

	private void addHangingVeil(BlockPos.MutableBlockPos posMutable, BooleanProperty side, BooleanProperty side2, LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random) {
		int length = 4 - random.nextInt(3);
		blockSetter.set(posMutable, UGBlocks.MUSHROOM_VEIL.get().defaultBlockState().setValue(side, true).setValue(side2, true).setValue(VineBlock.UP, true));
		for (int i = 0; i <= length; i++) {
			if (level.isStateAtPosition(posMutable, BlockBehaviour.BlockStateBase::isAir)) {
				if (i == length || !level.isStateAtPosition(posMutable.below(), BlockBehaviour.BlockStateBase::isAir)) {
					blockSetter.set(posMutable, UGBlocks.MUSHROOM_VEIL.get().defaultBlockState().setValue(side, true).setValue(side2, true).setValue(MushroomVeilBlock.END, true));
					break;
				}
				blockSetter.set(posMutable, UGBlocks.MUSHROOM_VEIL.get().defaultBlockState().setValue(side, true).setValue(side2, true));
			}
			posMutable.move(Direction.DOWN);
		}
	}

	@Override
	public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
		return 0;
	}

	@Override
	protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
		return false;
	}
}
