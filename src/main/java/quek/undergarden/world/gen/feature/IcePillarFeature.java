package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class IcePillarFeature extends Feature<NoneFeatureConfiguration> {

    public IcePillarFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        BlockPos blockpos = pContext.origin();
        WorldGenLevel worldgenlevel = pContext.level();
        Random random = pContext.random();
        if (worldgenlevel.isEmptyBlock(blockpos) && !worldgenlevel.isEmptyBlock(blockpos.above())) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = blockpos.mutable();
            BlockPos.MutableBlockPos blockpos$mutableblockpos1 = blockpos.mutable();
            boolean flag = true;
            boolean flag1 = true;
            boolean flag2 = true;
            boolean flag3 = true;

            while(worldgenlevel.isEmptyBlock(blockpos$mutableblockpos)) {
                if (worldgenlevel.isOutsideBuildHeight(blockpos$mutableblockpos)) {
                    return true;
                }

                worldgenlevel.setBlock(blockpos$mutableblockpos, Blocks.PACKED_ICE.defaultBlockState(), 2);
                flag = flag && this.placeHangOff(worldgenlevel, random, blockpos$mutableblockpos1.setWithOffset(blockpos$mutableblockpos, Direction.NORTH));
                flag1 = flag1 && this.placeHangOff(worldgenlevel, random, blockpos$mutableblockpos1.setWithOffset(blockpos$mutableblockpos, Direction.SOUTH));
                flag2 = flag2 && this.placeHangOff(worldgenlevel, random, blockpos$mutableblockpos1.setWithOffset(blockpos$mutableblockpos, Direction.WEST));
                flag3 = flag3 && this.placeHangOff(worldgenlevel, random, blockpos$mutableblockpos1.setWithOffset(blockpos$mutableblockpos, Direction.EAST));
                blockpos$mutableblockpos.move(Direction.DOWN);
            }

            blockpos$mutableblockpos.move(Direction.UP);
            this.placeBaseHangOff(worldgenlevel, random, blockpos$mutableblockpos1.setWithOffset(blockpos$mutableblockpos, Direction.NORTH));
            this.placeBaseHangOff(worldgenlevel, random, blockpos$mutableblockpos1.setWithOffset(blockpos$mutableblockpos, Direction.SOUTH));
            this.placeBaseHangOff(worldgenlevel, random, blockpos$mutableblockpos1.setWithOffset(blockpos$mutableblockpos, Direction.WEST));
            this.placeBaseHangOff(worldgenlevel, random, blockpos$mutableblockpos1.setWithOffset(blockpos$mutableblockpos, Direction.EAST));
            blockpos$mutableblockpos.move(Direction.DOWN);
            BlockPos.MutableBlockPos blockpos$mutableblockpos2 = new BlockPos.MutableBlockPos();

            for(int i = -3; i < 4; ++i) {
                for(int j = -3; j < 4; ++j) {
                    int k = Mth.abs(i) * Mth.abs(j);
                    if (random.nextInt(10) < 10 - k) {
                        blockpos$mutableblockpos2.set(blockpos$mutableblockpos.offset(i, 0, j));
                        int l = 3;

                        while(worldgenlevel.isEmptyBlock(blockpos$mutableblockpos1.setWithOffset(blockpos$mutableblockpos2, Direction.DOWN))) {
                            blockpos$mutableblockpos2.move(Direction.DOWN);
                            --l;
                            if (l <= 0) {
                                break;
                            }
                        }

                        if (!worldgenlevel.isEmptyBlock(blockpos$mutableblockpos1.setWithOffset(blockpos$mutableblockpos2, Direction.DOWN))) {
                            worldgenlevel.setBlock(blockpos$mutableblockpos2, Blocks.PACKED_ICE.defaultBlockState(), 2);
                        }
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    private void placeBaseHangOff(LevelAccessor world, Random rand, BlockPos pos) {
        if (rand.nextBoolean()) {
            world.setBlock(pos, Blocks.PACKED_ICE.defaultBlockState(), 2);
        }

    }

    private boolean placeHangOff(LevelAccessor world, Random rand, BlockPos pos) {
        if (rand.nextInt(10) != 0) {
            world.setBlock(pos, Blocks.PACKED_ICE.defaultBlockState(), 2);
            return true;
        }
        else {
            return false;
        }
    }
}