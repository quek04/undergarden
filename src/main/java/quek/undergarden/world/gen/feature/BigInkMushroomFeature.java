package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class BigInkMushroomFeature extends UGBigMushroomFeature {

    public BigInkMushroomFeature(Codec<BigMushroomFeatureConfig> codec) {
        super(codec);
    }

    @Override
    protected void func_225564_a_(IWorld world, Random random, BlockPos pos, int y, BlockPos.Mutable posMutable, BigMushroomFeatureConfig config) {
        int i = config.foliageRadius;

        for(int j = -i; j <= i; ++j) {
            for(int k = -i; k <= i; ++k) {
                boolean flag = j == -i;
                boolean flag1 = j == i;
                boolean flag2 = k == -i;
                boolean flag3 = k == i;
                boolean flag4 = flag || flag1;
                boolean flag5 = flag2 || flag3;
                if (!flag4 || !flag5) {
                    posMutable.setAndOffset(pos, j, y, k);
                    if (world.getBlockState(posMutable).canBeReplacedByLeaves(world, posMutable)) {
                        boolean flag6 = flag || flag5 && j == 1 - i;
                        boolean flag7 = flag1 || flag5 && j == i - 1;
                        boolean flag8 = flag2 || flag4 && k == 1 - i;
                        boolean flag9 = flag3 || flag4 && k == i - 1;
                        this.setBlockState(world, posMutable, config.capProvider.getBlockState(random, pos).with(HugeMushroomBlock.WEST, flag6).with(HugeMushroomBlock.EAST, flag7).with(HugeMushroomBlock.NORTH, flag8).with(HugeMushroomBlock.SOUTH, flag9));

                        if(random.nextInt(10) == 0) {
                            this.setBlockState(world, posMutable.down(1), UGBlocks.seeping_ink.get().getDefaultState());
                        }
                    }
                }
            }
        }

    }

    @Override
    protected int func_225563_a_(int p_225563_1_, int p_225563_2_, int p_225563_3_, int p_225563_4_) {
        return p_225563_4_ <= 3 ? 0 : p_225563_3_;
    }
}
