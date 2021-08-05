package quek.undergarden.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
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
    public void place(ISeedReader world, Random rand, List<BlockPos> p_225576_3_, List<BlockPos> p_225576_4_, Set<BlockPos> p_225576_5_, MutableBoundingBox boundingBox) {
        p_225576_4_.forEach((pos) -> {
            if(rand.nextInt(10) == 0) {
                this.placeHangingLeaves(world, rand, pos.below());
            }
        });
    }

    private void placeHangingLeaves(IWorld world, Random rand, BlockPos pos) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

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

    public static void placeHangingLeavesColumn(IWorld world, BlockPos.Mutable posMutable, int length) {
        for(int i = 0; i <= length; ++i) {
            if (world.isEmptyBlock(posMutable)) {
                if (i == length || !world.isEmptyBlock(posMutable.below())) {
                    world.setBlock(posMutable, UGBlocks.HANGING_GRONGLE_LEAVES_TOP.get().defaultBlockState().setValue(AbstractTopPlantBlock.AGE, 25), 2);
                    break;
                }

                world.setBlock(posMutable, UGBlocks.HANGING_GRONGLE_LEAVES.get().defaultBlockState(), 2);
            }
            posMutable.move(Direction.DOWN);
        }
    }
}