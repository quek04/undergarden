package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class BloodMushroomFeature extends UGBigMushroomFeature {

    public BloodMushroomFeature(Codec<BigMushroomFeatureConfig> codec) {
        super(codec);
    }

    @Override
    protected int func_225563_a_(int p_225563_1_, int p_225563_2_, int p_225563_3_, int p_225563_4_) {
        int i = 0;
        if (p_225563_4_ < p_225563_2_ && p_225563_4_ >= p_225563_2_ - 3) {
            i = p_225563_3_;
        } else if (p_225563_4_ == p_225563_2_) {
            i = p_225563_3_;
        }

        return i;
    }

    @Override //cap
    protected void func_225564_a_(IWorld world, Random random, BlockPos pos, int p_225564_4_, BlockPos.Mutable posMutable, BigMushroomFeatureConfig config) {
        for(int i = p_225564_4_ - 3; i <= p_225564_4_; ++i) {
            int j = i < p_225564_4_ ? config.field_227274_c_ : config.field_227274_c_ - 1;
            int k = config.field_227274_c_ - 2;

            for(int l = -j; l <= j; ++l) {
                for(int i1 = -j; i1 <= j; ++i1) {
                    boolean flag = l == -j;
                    boolean flag1 = l == j;
                    boolean flag2 = i1 == -j;
                    boolean flag3 = i1 == j;
                    boolean flag4 = flag || flag1;
                    boolean flag5 = flag2 || flag3;
                    if (i >= p_225564_4_ || flag4 != flag5) {
                        posMutable.setAndOffset(pos, l, i, i1);
                        if (world.getBlockState(posMutable).canBeReplacedByLeaves(world, posMutable)) {
                            if(!(random.nextInt(10) == 0)) {
                                this.setBlockState(world, posMutable, config.field_227272_a_.getBlockState(random, pos).with(HugeMushroomBlock.UP, i >= p_225564_4_ - 1).with(HugeMushroomBlock.WEST, l < -k).with(HugeMushroomBlock.EAST, l > k).with(HugeMushroomBlock.NORTH, i1 < -k).with(HugeMushroomBlock.SOUTH, i1 > k));
                            }
                            else this.setBlockState(world, posMutable, UGBlocks.blood_mushroom_globule.get().getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override //stalk
    protected void func_227210_a_(IWorld world, Random random, BlockPos pos, BigMushroomFeatureConfig config, int p_227210_5_, BlockPos.Mutable posMutable) {
        for(int i = 0; i < p_227210_5_; ++i) {
            posMutable.setPos(pos).move(Direction.UP, i);
            if (world.getBlockState(posMutable).canBeReplacedByLogs(world, posMutable)) {
                this.setBlockState(world, posMutable, config.field_227273_b_.getBlockState(random, pos));
            }
        }

    }
}
