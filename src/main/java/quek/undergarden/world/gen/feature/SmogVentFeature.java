package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class SmogVentFeature extends Feature<NoneFeatureConfiguration> {

    public SmogVentFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(WorldGenLevel worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoneFeatureConfiguration config) {
        while (worldIn.isEmptyBlock(pos) && pos.getY() > 2) {
            pos = pos.below();
        }

        if (worldIn.isEmptyBlock(pos.above()) && worldIn.getBlockState(pos).getBlock() == UGBlocks.ASHEN_DEEPTURF_BLOCK.get()) {
            pos = pos.above(rand.nextInt(4));
            int ventHeight = 7;
            int j = ventHeight / 4 + rand.nextInt(2);

            for (int k = 0; k < ventHeight; ++k) {
                float f = (1.0F - (float) k / (float) ventHeight) * (float) j;
                int l = Mth.ceil(f);

                for (int i1 = -l; i1 <= l; ++i1) {
                    float f1 = (float) Mth.abs(i1) - 0.25F;

                    for (int j1 = -l; j1 <= l; ++j1) {
                        float f2 = (float) Mth.abs(j1) - 0.25F;
                        if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > f * f)) && (i1 != -l && i1 != l && j1 != -l && j1 != l || !(rand.nextFloat() > 0.75F))) {
                            BlockState blockstate = worldIn.getBlockState(pos.offset(i1, k, j1));
                            Block block = blockstate.getBlock();
                            BlockPos ventPos = new BlockPos(pos.getX(), pos.getY() + 7, pos.getZ()); //
                            if (blockstate.isAir(worldIn, pos.offset(i1, k, j1)) || block == UGBlocks.ASHEN_DEEPTURF_BLOCK.get()) {
                                this.setBlock(worldIn, pos.offset(i1, k, j1), UGBlocks.DEPTHROCK.get().defaultBlockState());
                            }
                            this.setBlock(worldIn, ventPos, UGBlocks.SMOG_VENT.get().defaultBlockState());

                            if (k != 0 && l > 1) {
                                blockstate = worldIn.getBlockState(pos.offset(i1, -k, j1));
                                block = blockstate.getBlock();
                                if (blockstate.isAir(worldIn, pos.offset(i1, -k, j1)) || block == UGBlocks.ASHEN_DEEPTURF_BLOCK.get()) {
                                    this.setBlock(worldIn, pos.offset(i1, -k, j1), UGBlocks.DEPTHROCK.get().defaultBlockState());
                                }
                            }
                        }
                    }
                }
            }

            int k1 = j - 1;

            for(int l1 = -k1; l1 <= k1; ++l1) {
                for(int i2 = -k1; i2 <= k1; ++i2) {
                    BlockPos blockpos = pos.offset(l1, -1, i2);
                    BlockState blockstate1 = worldIn.getBlockState(blockpos);
                    Block block1 = blockstate1.getBlock();
                    if (!blockstate1.isAir(worldIn, blockpos) && block1 != UGBlocks.ASHEN_DEEPTURF_BLOCK.get() && block1 != UGBlocks.DEEPSOIL.get() && block1 != UGBlocks.DEPTHROCK.get()) {
                        break;
                    }

                    worldIn.setBlock(blockpos, UGBlocks.DEPTHROCK.get().defaultBlockState(), 1);
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

}
