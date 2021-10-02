package quek.undergarden.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGTreeDecoratorTypes;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

public class GrongleLeafDecorator extends TreeDecorator {

    public static final GrongleLeafDecorator INSTANCE = new GrongleLeafDecorator();
    public static final Codec<GrongleLeafDecorator> CODEC = Codec.unit(() -> INSTANCE);

    @Override
    protected TreeDecoratorType<?> type() {
        return UGTreeDecoratorTypes.GRONGLE_LEAF_DECORATOR.get();
    }

    @Override
    public void place(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, Random pRandom, List<BlockPos> pLogPositions, List<BlockPos> pLeafPositions) {
        pLeafPositions.forEach((blockPos -> {
            if(pRandom.nextInt(10) == 0) {
                this.placeHangingLeaves(pBlockSetter, pLevel, pRandom, blockPos);
            }
        }));
    }

    private void placeHangingLeaves(BiConsumer<BlockPos, BlockState> blockSetter, LevelSimulatedReader level, Random random, BlockPos pos) {
        BlockPos.MutableBlockPos posMutable = new BlockPos.MutableBlockPos();

        for(int i = 0; i < 100; ++i) {
            posMutable.setWithOffset(pos, random.nextInt(8) - random.nextInt(8), random.nextInt(2) - random.nextInt(7), random.nextInt(8) - random.nextInt(8));
            if (Feature.isAir(level, posMutable)) {
                //BlockState blockstate = level.getBlockState(posMutable.above());
                //if (blockstate.is(UGBlocks.GRONGLE_LEAVES.get())) {
                    int length = random.nextInt(3);
                    placeHangingLeavesColumn(blockSetter, level, posMutable, length);
                //}
            }
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