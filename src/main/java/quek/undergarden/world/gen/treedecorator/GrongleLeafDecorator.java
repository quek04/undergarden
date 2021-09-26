package quek.undergarden.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
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

public class GrongleLeafDecorator extends TreeDecorator {

    public static final GrongleLeafDecorator INSTANCE = new GrongleLeafDecorator();
    public static final Codec<GrongleLeafDecorator> CODEC = Codec.unit(() -> INSTANCE);

    @Override
    protected TreeDecoratorType<?> type() {
        return UGTreeDecoratorTypes.GRONGLE_LEAF_DECORATOR.get();
    }

    @Override
    public void place(WorldGenLevel world, Random rand, List<BlockPos> p_225576_3_, List<BlockPos> p_225576_4_, Set<BlockPos> p_225576_5_, BoundingBox boundingBox) {
        p_225576_4_.forEach((pos) -> {
            if(rand.nextInt(10) == 0) {
                this.placeHangingLeaves(world, rand, pos.below());
            }
        });
    }

    private void placeHangingLeaves(LevelAccessor world, Random rand, BlockPos pos) {
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

        for(int i = 0; i < 100; ++i) {
            blockpos$mutable.setWithOffset(pos, rand.nextInt(8) - rand.nextInt(8), rand.nextInt(2) - rand.nextInt(7), rand.nextInt(8) - rand.nextInt(8));
            if (world.isEmptyBlock(blockpos$mutable)) {
                BlockState blockstate = world.getBlockState(blockpos$mutable.above());
                if (blockstate.is(UGBlocks.GRONGLE_LEAVES.get())) {
                    int length = rand.nextInt(3);
                    placeHangingLeavesColumn(world, blockpos$mutable, length);
                }
            }
        }
    }

    public static void placeHangingLeavesColumn(LevelAccessor world, BlockPos.MutableBlockPos posMutable, int length) {
        for(int i = 0; i <= length; ++i) {
            if (world.isEmptyBlock(posMutable)) {
                if (i == length || !world.isEmptyBlock(posMutable.below())) {
                    world.setBlock(posMutable, UGBlocks.HANGING_GRONGLE_LEAVES_TOP.get().defaultBlockState().setValue(GrowingPlantHeadBlock.AGE, 25), 2);
                    break;
                }

                world.setBlock(posMutable, UGBlocks.HANGING_GRONGLE_LEAVES.get().defaultBlockState(), 2);
            }
            posMutable.move(Direction.DOWN);
        }
    }
}