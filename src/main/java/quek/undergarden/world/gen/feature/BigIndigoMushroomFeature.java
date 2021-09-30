package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

import java.util.Random;

public class BigIndigoMushroomFeature extends UGBigMushroomFeature {

    public BigIndigoMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected void makeCap(LevelAccessor world, Random random, BlockPos pos, int p_225564_4_, BlockPos.MutableBlockPos posMutable, HugeMushroomFeatureConfiguration config) {
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
                    posMutable.setWithOffset(pos, j, p_225564_4_, k);
                    if (world.getBlockState(posMutable).isSolidRender(world, posMutable)) {
                        boolean flag6 = flag || flag5 && j == 1 - i;
                        boolean flag7 = flag1 || flag5 && j == i - 1;
                        boolean flag8 = flag2 || flag4 && k == 1 - i;
                        boolean flag9 = flag3 || flag4 && k == i - 1;
                        this.setBlock(world, posMutable, config.capProvider.getState(random, pos).setValue(HugeMushroomBlock.WEST, flag6).setValue(HugeMushroomBlock.EAST, flag7).setValue(HugeMushroomBlock.NORTH, flag8).setValue(HugeMushroomBlock.SOUTH, flag9));
                     }
                }
            }
        }

    }

    @Override
    protected int getTreeRadiusForHeight(int p_225563_1_, int p_225563_2_, int p_225563_3_, int p_225563_4_) {
        return p_225563_4_ <= 3 ? 0 : p_225563_3_;
    }
}
