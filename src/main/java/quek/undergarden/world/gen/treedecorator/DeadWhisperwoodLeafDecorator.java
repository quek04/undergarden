package quek.undergarden.world.gen.treedecorator;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGTreeDecoratorTypes;

public class DeadWhisperwoodLeafDecorator extends TreeDecorator {

	public static final DeadWhisperwoodLeafDecorator INSTANCE = new DeadWhisperwoodLeafDecorator();
	public static final MapCodec<DeadWhisperwoodLeafDecorator> CODEC = MapCodec.unit(() -> INSTANCE);

	@Override
	protected TreeDecoratorType<?> type() {
		return UGTreeDecoratorTypes.DEAD_WHISPERWOOD_LEAF_DECORATOR.get();
	}

	@Override
	public void place(Context context) {
		context.leaves().forEach((pos -> {
			BlockPos downPos = pos.below();
			BlockPos down2Pos = downPos.below();
			if (context.isAir(downPos) && context.isAir(down2Pos)) {
				context.setBlock(downPos, UGBlocks.HANGING_DEAD_WHISPERWOOD_LEAVES.get().defaultBlockState());
				context.setBlock(down2Pos, UGBlocks.HANGING_DEAD_WHISPERWOOD_LEAVES.get().defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));
			}
		}));
	}
}