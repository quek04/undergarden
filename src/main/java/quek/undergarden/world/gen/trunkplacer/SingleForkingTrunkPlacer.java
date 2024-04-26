package quek.undergarden.world.gen.trunkplacer;

import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import quek.undergarden.registry.UGTrunkPlacerTypes;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;

public class SingleForkingTrunkPlacer extends TrunkPlacer {

	public static final MapCodec<SingleForkingTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
			trunkPlacerParts(instance).apply(instance, SingleForkingTrunkPlacer::new));

	public SingleForkingTrunkPlacer(int baseHeight, int heightRandomA, int heightRandomB) {
		super(baseHeight, heightRandomA, heightRandomB);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return UGTrunkPlacerTypes.SINGLE_FORKING_TRUNK_PLACER.get();
	}

	@Override
	public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
		setDirtAt(level, blockSetter, random, pos.below(), config);
		List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
		Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
		int i = freeTreeHeight - random.nextInt(4) - 1;
		int j = 3 - random.nextInt(3);
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
		int x = pos.getX();
		int y = pos.getZ();
		OptionalInt optionalInt = OptionalInt.empty();

		for (int i1 = 0; i1 < freeTreeHeight; ++i1) {
			int j1 = pos.getY() + i1;
			if (i1 >= i && j > 0) {
				x += direction.getStepX();
				y += direction.getStepZ();
				--j;
			}

			if (this.placeLog(level, blockSetter, random, mutablePos.set(x, j1, y), config)) {
				optionalInt = OptionalInt.of(j1 + 1);
			}
		}

		if (optionalInt.isPresent()) {
			list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(x, optionalInt.getAsInt(), y), 1, false));
		}

		return list;
	}
}
