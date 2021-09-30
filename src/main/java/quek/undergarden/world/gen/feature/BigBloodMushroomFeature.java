package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class BigBloodMushroomFeature extends UGBigMushroomFeature {

    public BigBloodMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected int getTreeRadiusForHeight(int p_225563_1_, int p_225563_2_, int p_225563_3_, int p_225563_4_) {
        int i = 0;
        if (p_225563_4_ < p_225563_2_ && p_225563_4_ >= p_225563_2_ - 3) {
            i = p_225563_3_;
        } else if (p_225563_4_ == p_225563_2_) {
            i = p_225563_3_;
        }

        return i;
    }

    @Override //cap
    protected void makeCap(LevelAccessor level, Random random, BlockPos pos, int p_225564_4_, BlockPos.MutableBlockPos posMutable, HugeMushroomFeatureConfiguration config) {
        for(int i = p_225564_4_ - 3; i <= p_225564_4_; ++i) {
            int j = i < p_225564_4_ ? config.foliageRadius : config.foliageRadius - 1;
            int k = config.foliageRadius - 2;

            for(int l = -j; l <= j; ++l) {
                for(int i1 = -j; i1 <= j; ++i1) {
                    boolean flag = l == -j;
                    boolean flag1 = l == j;
                    boolean flag2 = i1 == -j;
                    boolean flag3 = i1 == j;
                    boolean flag4 = flag || flag1;
                    boolean flag5 = flag2 || flag3;
                    if (i >= p_225564_4_ || flag4 != flag5) {
                        posMutable.setWithOffset(pos, l, i, i1);
                        if (level.getBlockState(posMutable).isSolidRender(level, posMutable)) {
                            if(!(random.nextInt(10) == 0)) {
                                this.setBlock(level, posMutable, config.capProvider.getState(random, pos).setValue(HugeMushroomBlock.UP, i >= p_225564_4_ - 1).setValue(HugeMushroomBlock.WEST, l < -k).setValue(HugeMushroomBlock.EAST, l > k).setValue(HugeMushroomBlock.NORTH, i1 < -k).setValue(HugeMushroomBlock.SOUTH, i1 > k));
                            }
                            else this.setBlock(level, posMutable, UGBlocks.BLOOD_MUSHROOM_GLOBULE.get().defaultBlockState());
                        }
                    }
                }
            }
        }
    }
}
