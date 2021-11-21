package quek.undergarden.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
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
        leafPositions.forEach((pos -> {
           if(random.nextInt(3) == 0) {
                BlockPos downPos = pos.below();
                BlockPos down2Pos = downPos.below();
                if(Feature.isAir(level, downPos) && Feature.isAir(level, down2Pos)) {
                    blockSetter.accept(downPos, UGBlocks.HANGING_GRONGLE_LEAVES.get().defaultBlockState());
                    blockSetter.accept(down2Pos, UGBlocks.HANGING_GRONGLE_LEAVES.get().defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));
                }
            }
        }));
    }
}