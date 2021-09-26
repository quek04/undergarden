package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import quek.undergarden.block.GlowingKelpTopBlock;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class GlowingKelpFeature extends Feature<NoneFeatureConfiguration> {

    public GlowingKelpFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(WorldGenLevel worldIn, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoneFeatureConfiguration config) {
        int i = 0;
        int ocean_y = 32;
        BlockPos blockpos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        if(pos.getY() < ocean_y) {
            if (worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER) {
                BlockState kelp = UGBlocks.GLOWING_KELP.get().defaultBlockState();
                BlockState kelp_top = UGBlocks.GLOWING_KELP_PLANT.get().defaultBlockState();
                int k = 1 + rand.nextInt(10);

                for(int l = 0; l <= k; ++l) {
                    if (worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER && worldIn.getBlockState(blockpos.above()).getBlock() == Blocks.WATER && kelp_top.canSurvive(worldIn, blockpos)) {
                        if (l == k) {
                            worldIn.setBlock(blockpos, kelp.setValue(GlowingKelpTopBlock.AGE, rand.nextInt(4) + 20), 2);
                            ++i;
                        } else {
                            worldIn.setBlock(blockpos, kelp_top, 2);
                        }
                    } else if (l > 0) {
                        BlockPos blockpos1 = blockpos.below();
                        if (kelp.canSurvive(worldIn, blockpos1) && worldIn.getBlockState(blockpos1.below()).getBlock() != UGBlocks.GLOWING_KELP.get()) {
                            worldIn.setBlock(blockpos1, kelp.setValue(GlowingKelpTopBlock.AGE, rand.nextInt(4) + 20), 2);
                            ++i;
                        }
                        break;
                    }

                    blockpos = blockpos.above();
                }
            }
        }
        return i > 0;
    }

}
