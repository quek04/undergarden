package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import quek.undergarden.block.MushroomVeilBlock;
import quek.undergarden.registry.UGBlocks;

import java.util.ArrayList;
import java.util.List;

public class VeilMushroomFeature extends AbstractHugeMushroomFeature {

	public VeilMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	protected int getTreeRadiusForHeight(int trunkHeight, int treeHeight, int leafRadius, int yo) {
		int radius = 0;
		if (yo < treeHeight && yo >= treeHeight - 3) {
			radius = leafRadius;
		} else if (yo == treeHeight) {
			radius = leafRadius;
		}

		return radius;
	}

	@Override
	protected void makeCap(WorldGenLevel level, RandomSource random, BlockPos origin, int treeHeight, BlockPos.MutableBlockPos blockPos, HugeMushroomFeatureConfiguration config) {
		int radius = config.foliageRadius();
		for (int dy = treeHeight - 4; dy <= treeHeight; dy++) {
			int center = radius / 2;

			for (int dx = -radius; dx <= radius; dx++) {
				for (int dz = -radius; dz <= radius; dz++) {
					boolean minX = dx == -radius;
					boolean maxX = dx == radius;
					boolean minZ = dz == -radius;
					boolean maxZ = dz == radius;
					boolean xEdge = minX || maxX;
					boolean zEdge = minZ || maxZ;
					if (dy >= treeHeight - 2 || xEdge != zEdge) {
						this.createMushroomBlock(level, origin, blockPos, random, config, dx, dy, dz, center);
					} else if (dy == treeHeight - 4 && Math.abs(dx) == radius - 1 && Math.abs(dz) == radius - 1) {
						//place corners on bigger ring
						this.createMushroomBlock(level, origin, blockPos, random, config, dx, dy, dz, center);
					}


				}
			}

			//add veil to outer skirt
			if (dy == treeHeight - 4) {
				int veilRadius = radius - 1;
				for (int dx = -veilRadius; dx <= veilRadius; dx++) {
					for (int dz = -veilRadius; dz <= veilRadius; dz++) {
						boolean minX = dx == -veilRadius;
						boolean maxX = dx == veilRadius;
						boolean minZ = dz == -veilRadius;
						boolean maxZ = dz == veilRadius;
						boolean xEdge = minX || maxX;
						boolean zEdge = minZ || maxZ;
						if (xEdge != zEdge) {
							blockPos.setWithOffset(origin, dx, dy, dz);
							if (level.isStateAtPosition(blockPos, BlockBehaviour.BlockStateBase::isAir)) {
								this.addHangingVeil(blockPos, level, random);
							}
						}
					}
				}
			}

			if (dy != treeHeight - 2) {
				radius--;
			}
		}
	}

	private void createMushroomBlock(WorldGenLevel level, BlockPos origin, BlockPos.MutableBlockPos blockPos, RandomSource random, HugeMushroomFeatureConfiguration config, int dx, int dy, int dz, int center) {
		blockPos.setWithOffset(origin, dx, dy, dz);
		BlockState state = config.capProvider().getState(level, random, origin);
		if (state.hasProperty(HugeMushroomBlock.WEST) && state.hasProperty(HugeMushroomBlock.EAST) && state.hasProperty(HugeMushroomBlock.NORTH) && state.hasProperty(HugeMushroomBlock.SOUTH)) {
			state = state.setValue(HugeMushroomBlock.WEST, dx <= -center).setValue(HugeMushroomBlock.EAST, dx >= center).setValue(HugeMushroomBlock.NORTH, dz <= -center).setValue(HugeMushroomBlock.SOUTH, dz >= center);
		}

		this.placeMushroomBlock(level, blockPos, state);
	}

	private void addHangingVeil(BlockPos.MutableBlockPos posMutable, WorldGenLevel level, RandomSource random) {
		List<Direction> validDirections = new ArrayList<>();
		for (Direction direction : Direction.values()) {
			if (direction.getAxis() != Direction.Axis.Y && MushroomVeilBlock.isAcceptableNeighbour(level, posMutable.relative(direction), direction)) {
				validDirections.add(direction);
			}
		}

		int length = 4 - random.nextInt(3);
		for (int i = 0; i <= length; i++) {
			if (level.isStateAtPosition(posMutable, BlockBehaviour.BlockStateBase::isAir)) {
				if (i == length || !level.isStateAtPosition(posMutable.below(), BlockBehaviour.BlockStateBase::isAir)) {
					this.setBlock(level, posMutable, this.createVeil(UGBlocks.MUSHROOM_VEIL.get().defaultBlockState().setValue(MushroomVeilBlock.END, true), validDirections, false));
					break;
				}
				this.setBlock(level, posMutable, this.createVeil(UGBlocks.MUSHROOM_VEIL.get().defaultBlockState(), validDirections, i == 0));
			}
			posMutable.move(Direction.DOWN);
		}
	}

	private BlockState createVeil(BlockState veil, List<Direction> validDirections, boolean addTop) {
		for (Direction dir : validDirections) {
			veil = veil.setValue(MushroomVeilBlock.getPropertyForFace(dir), true);
		}
		if (addTop) veil = veil.setValue(VineBlock.UP, true);
		return veil;
	}

	@Override
	protected int getTreeHeight(RandomSource random) {
		return random.nextInt(3) + 9;
	}
}
