package quek.undergarden.world.gen.treedecorator;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import quek.undergarden.block.GrongletBlock;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGTreeDecoratorTypes;

public class GrongletTrunkDecorator extends TreeDecorator {

	public static final GrongletTrunkDecorator INSTANCE = new GrongletTrunkDecorator();
	public static final MapCodec<GrongletTrunkDecorator> CODEC = MapCodec.unit(() -> INSTANCE);

	@Override
	protected TreeDecoratorType<?> type() {
		return UGTreeDecoratorTypes.GRONGLET_TRUNK_DECORATOR.get();
	}

	@Override
	public void place(TreeDecorator.Context context) {
		RandomSource random = context.random();

		context.logs().forEach((pos) -> {
			for (Direction direction : Direction.Plane.HORIZONTAL) {
				BlockPos newPos = pos.offset(direction.getStepX(), 0, direction.getStepZ());
				if (random.nextInt(50) == 0) {
					if (context.isAir(newPos)) {
						placeGronglet(context, newPos, direction);
					}
				}
			}
		});
	}

	private void placeGronglet(TreeDecorator.Context context, BlockPos pos, Direction direction) {
		context.setBlock(pos, UGBlocks.GRONGLET.get().defaultBlockState().setValue(GrongletBlock.FACING, direction));
	}
}
