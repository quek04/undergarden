package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.world.gen.feature.config.AncientRootConfiguration;

public class AncientRootFeature extends Feature<AncientRootConfiguration> {

	public AncientRootFeature(Codec<AncientRootConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<AncientRootConfiguration> context) {
		BlockPos pos = context.origin();
		WorldGenLevel level = context.level();
		RandomSource random = context.random();
		if (level.isEmptyBlock(pos) && !level.isEmptyBlock(pos.above())) {
			BlockPos.MutableBlockPos posMutable = pos.mutable();
			BlockPos.MutableBlockPos posMutable2 = pos.mutable();

			while (level.isEmptyBlock(posMutable) && level.getFluidState(posMutable).isEmpty()) {
				if (level.isOutsideBuildHeight(posMutable)) {
					return true;
				}

				level.setBlock(posMutable, rootState(random, context), 2);
				level.setBlock(posMutable2.setWithOffset(posMutable, Direction.NORTH), rootState(random, context), 2);
				level.setBlock(posMutable2.setWithOffset(posMutable, Direction.SOUTH), rootState(random, context), 2);
				level.setBlock(posMutable2.setWithOffset(posMutable, Direction.WEST), rootState(random, context), 2);
				level.setBlock(posMutable2.setWithOffset(posMutable, Direction.EAST), rootState(random, context), 2);
				if (random.nextInt(3) == 0) {
					posMutable.move(Direction.NORTH);
					level.setBlock(posMutable2.setWithOffset(posMutable, Direction.NORTH), rootState(random, context), 2);
				}
				if (random.nextInt(3) == 0) {
					posMutable.move(Direction.SOUTH);
					level.setBlock(posMutable2.setWithOffset(posMutable, Direction.SOUTH), rootState(random, context), 2);
				}
				if (random.nextInt(3) == 0) {
					posMutable.move(Direction.WEST);
					level.setBlock(posMutable2.setWithOffset(posMutable, Direction.WEST), rootState(random, context), 2);
				}
				if (random.nextInt(3) == 0) {
					posMutable.move(Direction.EAST);
					level.setBlock(posMutable2.setWithOffset(posMutable, Direction.EAST), rootState(random, context), 2);
				}
				posMutable.move(Direction.DOWN);
			}

			return true;
		} else {
			return false;
		}
	}

	private BlockState rootState(RandomSource random, FeaturePlaceContext<AncientRootConfiguration> context) {
		return random.nextInt(context.config().rogdoricProbability()) == 0 ? UGBlocks.ROGDORIC_ANCIENT_ROOT.get().defaultBlockState() : UGBlocks.ANCIENT_ROOT.get().defaultBlockState();
	}
}
