package quek.undergarden.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGTreeDecoratorTypes;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class GrongleLeafDecorator extends TreeDecorator {

    public static final GrongleLeafDecorator INSTANCE = new GrongleLeafDecorator();
    public static final Codec<GrongleLeafDecorator> CODEC = Codec.unit(() -> INSTANCE);

    @Override
    protected TreeDecoratorType<?> type() {
        return UGTreeDecoratorTypes.GRONGLE_LEAF_DECORATOR.get();
    }

    @Override
    public void place(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, Random random, List<BlockPos> logPositions, List<BlockPos> leafPositions) {
        leafPositions.forEach((blockPos -> {
            //if(Feature.isAir(level, blockPos)) {
                if(level.isStateAtPosition(blockPos, blockState -> blockState.is(BlockTags.LEAVES))) {
                    this.placeHangingLeaves(blockSetter, level, random, blockPos);
                }
           // }
        }));
    }

    private void placeHangingLeaves(BiConsumer<BlockPos, BlockState> blockSetter, LevelSimulatedReader level, Random random, BlockPos pos) {
        BlockPos.MutableBlockPos posMutable = new BlockPos.MutableBlockPos();

        for(int i = 0; i < 100; ++i) {
            posMutable.setWithOffset(pos, random.nextInt(8) - random.nextInt(8), random.nextInt(2) - random.nextInt(7), random.nextInt(8) - random.nextInt(8));
            //if (Feature.isAir(level, posMutable)) {
                //if (level.isStateAtPosition(posMutable.above(), blockState -> blockState.is(BlockTags.LEAVES))) {
                    int length = random.nextInt(3);
                    placeHangingLeavesColumn(blockSetter, level, posMutable, length);
                //}
            //}
        }
    }

    public static void placeHangingLeavesColumn(BiConsumer<BlockPos, BlockState> blockSetter, LevelSimulatedReader level, BlockPos.MutableBlockPos posMutable, int length) {
        for(int i = 0; i <= length; ++i) {
            if (Feature.isAir(level, posMutable)) {
                if (i == length || !Feature.isAir(level, posMutable.below())) {
                    blockSetter.accept(posMutable, UGBlocks.HANGING_GRONGLE_LEAVES_TOP.get().defaultBlockState().setValue(GrowingPlantHeadBlock.AGE, 25));
                    break;
                }

                blockSetter.accept(posMutable, UGBlocks.HANGING_GRONGLE_LEAVES.get().defaultBlockState());
            }
            posMutable.move(Direction.DOWN);
        }
    }
}