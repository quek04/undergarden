package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class SmogVentFeature extends Feature<NoFeatureConfig> {

    public SmogVentFeature(Codec<NoFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override //this code is a mess
    public boolean func_241855_a(ISeedReader worldIn, ChunkGenerator p_241855_2_, Random rand, BlockPos pos, NoFeatureConfig p_241855_5_) {
        while (worldIn.isAirBlock(pos) && pos.getY() > 2) {
            pos = pos.down();
        }

        if (worldIn.isAirBlock(pos.up()) && worldIn.getBlockState(pos).getBlock() == UGBlocks.ashen_deepturf.get()) {
            pos = pos.up(rand.nextInt(4));
            int ventHeight = 7;
            int j = ventHeight / 4 + rand.nextInt(2);
            if (j > 1 && rand.nextInt(60) == 0) {
                pos = pos.up(10 + rand.nextInt(30));
            }

            for (int k = 0; k < ventHeight; ++k) {
                float f = (1.0F - (float) k / (float) ventHeight) * (float) j;
                int l = MathHelper.ceil(f);

                for (int i1 = -l; i1 <= l; ++i1) {
                    float f1 = (float) MathHelper.abs(i1) - 0.25F;

                    for (int j1 = -l; j1 <= l; ++j1) {
                        float f2 = (float) MathHelper.abs(j1) - 0.25F;
                        if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > f * f)) && (i1 != -l && i1 != l && j1 != -l && j1 != l || !(rand.nextFloat() > 0.75F))) {
                            BlockState blockstate = worldIn.getBlockState(pos.add(i1, k, j1));
                            Block block = blockstate.getBlock();
                            BlockPos ventPos = new BlockPos(pos.getX(), pos.getY() + 7, pos.getZ()); //
                            if (blockstate.isAir(worldIn, pos.add(i1, k, j1)) || block == UGBlocks.ashen_deepturf.get()) {
                                this.setBlockState(worldIn, pos.add(i1, k, j1), UGBlocks.depthrock.get().getDefaultState());
                            }
                            this.setBlockState(worldIn, ventPos, UGBlocks.smog_vent.get().getDefaultState());

                            if (k != 0 && l > 1) {
                                blockstate = worldIn.getBlockState(pos.add(i1, -k, j1));
                                block = blockstate.getBlock();
                                if (blockstate.isAir(worldIn, pos.add(i1, -k, j1)) || block == UGBlocks.ashen_deepturf.get()) {
                                    this.setBlockState(worldIn, pos.add(i1, -k, j1), UGBlocks.depthrock.get().getDefaultState());
                                }
                            }
                        }
                    }
                }
            }

            int k1 = j - 1;
            if (k1 < 0) {
                k1 = 0;
            } else if (k1 > 1) {
                k1 = 1;
            }

            for(int l1 = -k1; l1 <= k1; ++l1) {
                for(int i2 = -k1; i2 <= k1; ++i2) {
                    BlockPos blockpos = pos.add(l1, -1, i2);
                    BlockState blockstate1 = worldIn.getBlockState(blockpos);
                    Block block1 = blockstate1.getBlock();
                    if (!blockstate1.isAir(worldIn, blockpos) && block1 != UGBlocks.ashen_deepturf.get() && block1 != UGBlocks.deepsoil.get() && block1 != UGBlocks.depthrock.get()) {
                        break;
                    }

                    worldIn.setBlockState(blockpos, UGBlocks.depthrock.get().getDefaultState(), 1);
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

}
