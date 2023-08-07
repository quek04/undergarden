package quek.undergarden.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGTreeDecoratorTypes;

public class GrongleLeafDecorator extends TreeDecorator {

	public static final GrongleLeafDecorator INSTANCE = new GrongleLeafDecorator();
	public static final Codec<GrongleLeafDecorator> CODEC = Codec.unit(() -> INSTANCE);

	@Override
	protected TreeDecoratorType<?> type() {
		return UGTreeDecoratorTypes.GRONGLE_LEAF_DECORATOR.get();
	}

	@Override
	public void place(TreeDecorator.Context context) {
		RandomSource random = context.random();

		context.leaves().forEach((pos -> {
			if (random.nextInt(3) == 0) {
				BlockPos downPos = pos.below();
				BlockPos down2Pos = downPos.below();
				if (context.isAir(downPos) && context.isAir(down2Pos)) {
					context.setBlock(downPos, UGBlocks.HANGING_GRONGLE_LEAVES.get().defaultBlockState());
					context.setBlock(down2Pos, UGBlocks.HANGING_GRONGLE_LEAVES.get().defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));
				}
			}
		}));
	}
}