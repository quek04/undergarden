package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.HugeBrownMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class HugeInkMushroomFeature extends HugeBrownMushroomFeature {

    public HugeInkMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected void makeCap(LevelAccessor level, RandomSource random, BlockPos pos, int shroomHeight, BlockPos.MutableBlockPos mutablePos, HugeMushroomFeatureConfiguration config) {
        int capRadius = config.foliageRadius;

        for(int j = -capRadius; j <= capRadius; ++j) {
            for(int k = -capRadius; k <= capRadius; ++k) {
                boolean flag = j == -capRadius;
                boolean flag1 = j == capRadius;
                boolean flag2 = k == -capRadius;
                boolean flag3 = k == capRadius;
                boolean flag4 = flag || flag1;
                boolean flag5 = flag2 || flag3;
                if (!flag4 || !flag5) {
                    mutablePos.setWithOffset(pos, j, shroomHeight, k);
                    if (!level.getBlockState(mutablePos).isSolidRender(level, mutablePos)) {
                        boolean flag6 = flag || flag5 && j == 1 - capRadius;
                        boolean flag7 = flag1 || flag5 && j == capRadius - 1;
                        boolean flag8 = flag2 || flag4 && k == 1 - capRadius;
                        boolean flag9 = flag3 || flag4 && k == capRadius - 1;
                        BlockState blockstate = config.capProvider.getState(random, pos);
                        if (blockstate.hasProperty(HugeMushroomBlock.WEST) && blockstate.hasProperty(HugeMushroomBlock.EAST) && blockstate.hasProperty(HugeMushroomBlock.NORTH) && blockstate.hasProperty(HugeMushroomBlock.SOUTH)) {
                            blockstate = blockstate.setValue(HugeMushroomBlock.WEST, flag6).setValue(HugeMushroomBlock.EAST, flag7).setValue(HugeMushroomBlock.NORTH, flag8).setValue(HugeMushroomBlock.SOUTH, flag9);
                        }

                        this.setBlock(level, mutablePos, blockstate);
                        if(random.nextInt(10) == 0) {
                            this.setBlock(level, mutablePos.below(), UGBlocks.SEEPING_INK.get().defaultBlockState());
                        }
                    }
                }
            }
        }
    }
}