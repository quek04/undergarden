package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.AbstractBigMushroomFeature;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public abstract class UGBigMushroomFeature extends AbstractBigMushroomFeature {

    public UGBigMushroomFeature(Codec<BigMushroomFeatureConfig> codec) {
        super(codec);
    }

    private static boolean isDeepturf(IWorldGenerationBaseReader world, BlockPos pos) {
        return world.hasBlockState(pos, (state) -> {
            Block block = state.getBlock();
            return block == UGBlocks.deepturf_block.get() || block == UGBlocks.deepsoil.get();
        });
    }

    @Override
    protected boolean func_227209_a_(IWorld world, BlockPos pos, int p_227209_3_, BlockPos.Mutable posMutable, BigMushroomFeatureConfig config) {
        int i = pos.getY();
        if (i >= 1 && i + p_227209_3_ + 1 < 256) {
            //if (!isDeepturf(world, pos)) {
            //    return false;
            //}
            //else {
                for(int j = 0; j <= p_227209_3_; ++j) {
                    int k = this.func_225563_a_(-1, -1, config.field_227274_c_, j);

                    for(int l = -k; l <= k; ++l) {
                        for(int i1 = -k; i1 <= k; ++i1) {
                            BlockState blockstate = world.getBlockState(posMutable.setAndOffset(pos, l, j, i1));
                            if (!blockstate.isAir(world, posMutable.setAndOffset(pos, l, j, i1)) && !blockstate.isIn(BlockTags.LEAVES)) {
                                return false;
                            }
                        }
                    }
                }

                return true;
            //}
        } else {
            return false;
        }
    }

    @Override
    public boolean func_241855_a(ISeedReader seedReader, ChunkGenerator chunkGenerator, Random random, BlockPos pos, BigMushroomFeatureConfig config) {
        int i = this.func_227211_a_(random);
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        if (!this.func_227209_a_(seedReader, pos, i, blockpos$mutable, config)) {
            return false;
        } else {
            this.func_225564_a_(seedReader, random, pos, i, blockpos$mutable, config);
            this.func_227210_a_(seedReader, random, pos, config, i, blockpos$mutable);
            return true;
        }
    }

    @Override
    protected abstract int func_225563_a_(int p_225563_1_, int p_225563_2_, int p_225563_3_, int p_225563_4_);

    @Override
    protected abstract void func_225564_a_(IWorld world, Random random, BlockPos pos, int p_225564_4_, BlockPos.Mutable posMutable, BigMushroomFeatureConfig config);

    @Override //stalk size
    protected int func_227211_a_(Random random) {
        int i = random.nextInt(6) + 6;
        if (random.nextInt(12) == 0) {
            i *= 2;
        }

        return i;
    }
}
