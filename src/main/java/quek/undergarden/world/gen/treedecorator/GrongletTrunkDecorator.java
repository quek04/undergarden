package quek.undergarden.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import quek.undergarden.block.GrongletBlock;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGTreeDecoratorTypes;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class GrongletTrunkDecorator extends TreeDecorator {

    public static final GrongletTrunkDecorator INSTANCE = new GrongletTrunkDecorator();
    public static final Codec<GrongletTrunkDecorator> CODEC = Codec.unit(() -> INSTANCE);

    @Override
    protected TreeDecoratorType<?> type() {
        return UGTreeDecoratorTypes.GRONGLET_TRUNK_DECORATOR.get();
    }

    @Override
    public void place(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, Random random, List<BlockPos> logPositions, List<BlockPos> leafPositions) {
        logPositions.forEach((pos) -> {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockPos newPos = pos.offset(direction.getStepX(), 0, direction.getStepZ());
                if (random.nextInt(50) == 0) {
                    if (Feature.isAir(level, newPos)) {
                        placeGronglet(blockSetter, newPos, direction);
                    }
                }
            }
        });
    }

    private void placeGronglet(BiConsumer<BlockPos, BlockState> blockSetter, BlockPos pos, Direction direction) {
        blockSetter.accept(pos, UGBlocks.GRONGLET.get().defaultBlockState().setValue(GrongletBlock.FACING, direction));
    }
}
