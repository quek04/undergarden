package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.HugeRedMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class HugeVeilMushroomFeature extends HugeRedMushroomFeature {

    public HugeVeilMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected void makeCap(LevelAccessor level, Random random, BlockPos pos, int shroomHeight, BlockPos.MutableBlockPos posMutable, HugeMushroomFeatureConfiguration config) {
        for(int i = shroomHeight - 3; i <= shroomHeight; ++i) {
            int j = i < shroomHeight ? config.foliageRadius : config.foliageRadius - 1;
            int k = config.foliageRadius - 2;

            for(int l = -j; l <= j; ++l) {
                for(int i1 = -j; i1 <= j; ++i1) {
                    boolean flag = l == -j;
                    boolean flag1 = l == j;
                    boolean flag2 = i1 == -j;
                    boolean flag3 = i1 == j;
                    boolean flag4 = flag || flag1;
                    boolean flag5 = flag2 || flag3;
                    if (i >= shroomHeight || flag4 != flag5) {
                        posMutable.setWithOffset(pos, l, i, i1);
                        if (!level.getBlockState(posMutable).isSolidRender(level, posMutable)) {
                            BlockState state = config.capProvider.getState(random, pos);
                            if (state.hasProperty(HugeMushroomBlock.WEST) && state.hasProperty(HugeMushroomBlock.EAST) && state.hasProperty(HugeMushroomBlock.NORTH) && state.hasProperty(HugeMushroomBlock.SOUTH) && state.hasProperty(HugeMushroomBlock.UP)) {
                                state = state.setValue(HugeMushroomBlock.UP, i >= shroomHeight - 1).setValue(HugeMushroomBlock.WEST, l < -k).setValue(HugeMushroomBlock.EAST, l > k).setValue(HugeMushroomBlock.NORTH, i1 < -k).setValue(HugeMushroomBlock.SOUTH, i1 > k);
                            }

                            this.setBlock(level, posMutable, state);
                            if(random.nextInt(5) == 0) {
                                doVeil(posMutable, level, random);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void doVeil(BlockPos pos, LevelAccessor level, Random random) {
        BlockPos.MutableBlockPos posMutable = pos.mutable().move(Direction.DOWN);
        if (level.isEmptyBlock(posMutable)) {
            int i = Mth.nextInt(random, 1, 5);
            if (random.nextInt(7) == 0) {
                i *= 2;
            }

            placeVeil(level, random, posMutable, i, 23, 25);
        }
    }

    public static void placeVeil(LevelAccessor level, Random random, BlockPos.MutableBlockPos posMutable, int length, int min, int max) {
        for(int i = 0; i <= length; ++i) {
            if (level.isEmptyBlock(posMutable)) {
                if (i == length || !level.isEmptyBlock(posMutable.below())) {
                    level.setBlock(posMutable, UGBlocks.MUSHROOM_VEIL.get().defaultBlockState().setValue(GrowingPlantHeadBlock.AGE, Mth.nextInt(random, min, max)), 2);
                    break;
                }

                level.setBlock(posMutable, UGBlocks.MUSHROOM_VEIL_PLANT.get().defaultBlockState(), 2);
            }

            posMutable.move(Direction.DOWN);
        }
    }
}