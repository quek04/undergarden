package quek.undergarden.world.gen.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import org.apache.commons.lang3.mutable.MutableBoolean;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGFluids;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class UGCaveWorldCarver extends CaveWorldCarver {

    public UGCaveWorldCarver(Codec<ProbabilityFeatureConfiguration> configCodec) {
        super(configCodec, 300);
        this.replaceableBlocks = ImmutableSet.of(
                UGBlocks.DEPTHROCK.get(),
                UGBlocks.SHIVERSTONE.get(),
                UGBlocks.DEEPTURF_BLOCK.get(),
                UGBlocks.ASHEN_DEEPTURF_BLOCK.get(),
                UGBlocks.FROZEN_DEEPTURF_BLOCK.get(),
                UGBlocks.DEEPSOIL.get(),
                UGBlocks.COAL_ORE.get(),
                UGBlocks.IRON_ORE.get(),
                UGBlocks.GOLD_ORE.get(),
                UGBlocks.DIAMOND_ORE.get(),
                UGBlocks.CLOGGRUM_ORE.get(),
                UGBlocks.FROSTSTEEL_ORE.get(),
                UGBlocks.UTHERIUM_ORE.get(),
                UGBlocks.REGALIUM_ORE.get(),
                Blocks.ICE
        );
        this.liquids = ImmutableSet.of(
                Fluids.WATER,
                UGFluids.VIRULENT_MIX_SOURCE.get()
        );
    }

    @Override
    protected boolean carveBlock(ChunkAccess chunk, Function<BlockPos, Biome> p_230358_2_, BitSet carvingMask, Random rand, BlockPos.MutableBlockPos p_230358_5_, BlockPos.MutableBlockPos p_230358_6_, BlockPos.MutableBlockPos p_230358_7_, int p_230358_8_, int p_230358_9_, int p_230358_10_, int posX, int posZ, int p_230358_13_, int y, int p_230358_15_, MutableBoolean isSurface) {
        int i = p_230358_13_ | p_230358_15_ << 4 | y << 8;
        if (carvingMask.get(i)) {
            return false;
        } else {
            carvingMask.set(i);
            p_230358_5_.set(posX, y, posZ);
            BlockState blockstate = chunk.getBlockState(p_230358_5_);
            BlockState blockstate1 = chunk.getBlockState(p_230358_6_.setWithOffset(p_230358_5_, Direction.UP));

            if (!this.canReplaceBlock(blockstate, blockstate1)) {
                return false;
            } else {
                if (y < 11) {
                    chunk.setBlockState(p_230358_5_, UGBlocks.VIRULENT_MIX.get().defaultBlockState(), false);
                } else {
                    chunk.setBlockState(p_230358_5_, CAVE_AIR, false);
                    if (isSurface.isTrue()) {
                        p_230358_7_.setWithOffset(p_230358_5_, Direction.DOWN);
                        if (chunk.getBlockState(p_230358_7_).is(UGBlocks.DEEPSOIL.get())) {
                            chunk.setBlockState(p_230358_7_, p_230358_2_.apply(p_230358_5_).getGenerationSettings().getSurfaceBuilderConfig().getTopMaterial(), false);
                        }
                    }
                }

                return true;
            }
        }
    }

    @Override
    public boolean isStartChunk(Random rand, int chunkX, int chunkZ, ProbabilityFeatureConfiguration config) {
        return rand.nextFloat() <= config.probability;
    }

    @Override
    public boolean carve(ChunkAccess chunk, Function<BlockPos, Biome> biomePos, Random rand, int seaLevel, int chunkXOffset, int chunkZOffset, int chunkX, int chunkZ, BitSet carvingMask, ProbabilityFeatureConfiguration config) {
        int i = (this.getRange() * 2 - 1) * 16;
        int j = rand.nextInt(rand.nextInt(rand.nextInt(this.getCaveBound()) + 1) + 1);

        for(int k = 0; k < j; ++k) {
            double d0 = (chunkXOffset * 16 + rand.nextInt(16));
            double d1 = this.getCaveY(rand);
            double d2 = (chunkZOffset * 16 + rand.nextInt(16));
            int l = 1;
            if (rand.nextInt(4) == 0) {
                float f1 = 1.0F + rand.nextFloat() * 6.0F;
                this.genRoom(chunk, biomePos, rand.nextLong(), seaLevel, chunkX, chunkZ, d0, d1, d2, f1, 0.5D, carvingMask);
                l += rand.nextInt(4);
            }

            for(int k1 = 0; k1 < l; ++k1) {
                float f = rand.nextFloat() * ((float)Math.PI * 2F);
                float f3 = (rand.nextFloat() - 0.5F) / 4.0F;
                float f2 = this.getThickness(rand);
                int i1 = i - rand.nextInt(i / 4);
                this.genTunnel(chunk, biomePos, rand.nextLong(), seaLevel, chunkX, chunkZ, d0, d1, d2, f2, f, f3, 0, i1, this.getYScale(), carvingMask);
            }
        }

        return true;
    }

    @Override
    protected int getCaveBound() {
        return 15;
    }

    @Override
    protected float getThickness(Random p_230359_1_) {
        float f = p_230359_1_.nextFloat() * 2.0F + p_230359_1_.nextFloat();
        if (p_230359_1_.nextInt(10) == 0) {
            f *= p_230359_1_.nextFloat() * p_230359_1_.nextFloat() * 3.0F + 1.0F;
        }

        return f * 3;
    }

    @Override
    protected double getYScale() {
        return 1.0D;
    }

    @Override
    protected int getCaveY(Random p_230361_1_) {
        return p_230361_1_.nextInt(p_230361_1_.nextInt(250) + 6);
    }

    @Override
    protected void genRoom(ChunkAccess p_227205_1_, Function<BlockPos, Biome> p_227205_2_, long p_227205_3_, int seaLevel, int chunkX, int chunkZ, double randOffsetXCoord, double startY, double randOffsetZCoord, float p_227205_14_, double p_227205_15_, BitSet carvingMask) {
        double d0 = 1.5D + (Mth.sin(((float)Math.PI / 2F)) * p_227205_14_);
        double d1 = d0 * p_227205_15_;
        this.carveSphere(p_227205_1_, p_227205_2_, p_227205_3_, seaLevel, chunkX, chunkZ, randOffsetXCoord + 1.0D, startY, randOffsetZCoord, d0, d1, carvingMask);
    }

    @Override
    protected void genTunnel(ChunkAccess chunk, Function<BlockPos, Biome> biomePos, long seed, int seaLevel, int chunkX, int chunkZ, double randOffsetXCoord, double startY, double randOffsetZCoord, float caveRadius, float pitch, float p_227206_16_, int p_227206_17_, int p_227206_18_, double p_227206_19_, BitSet p_227206_21_) {
        Random random = new Random(seed);
        int i = random.nextInt(p_227206_18_ / 2) + p_227206_18_ / 4;
        boolean flag = random.nextInt(6) == 0;
        float f = 0.0F;
        float f1 = 0.0F;

        for(int j = p_227206_17_; j < p_227206_18_; ++j) {
            double d0 = 1.5D + (Mth.sin((float)Math.PI * (float)j / (float)p_227206_18_) * caveRadius);
            double d1 = d0 * p_227206_19_;
            float f2 = Mth.cos(p_227206_16_);
            randOffsetXCoord += (Mth.cos(pitch) * f2);
            startY += Mth.sin(p_227206_16_);
            randOffsetZCoord += (Mth.sin(pitch) * f2);
            p_227206_16_ = p_227206_16_ * (flag ? 0.92F : 0.7F);
            p_227206_16_ = p_227206_16_ + f1 * 0.1F;
            pitch += f * 0.1F;
            f1 = f1 * 0.9F;
            f = f * 0.75F;
            f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f = f + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
            if (j == i && caveRadius > 1.0F) {
                this.genTunnel(chunk, biomePos, random.nextLong(), seaLevel, chunkX, chunkZ, randOffsetXCoord, startY, randOffsetZCoord, random.nextFloat() * 0.5F + 0.5F, pitch - ((float)Math.PI / 2F), p_227206_16_ / 3.0F, j, p_227206_18_, 1.0D, p_227206_21_);
                this.genTunnel(chunk, biomePos, random.nextLong(), seaLevel, chunkX, chunkZ, randOffsetXCoord, startY, randOffsetZCoord, random.nextFloat() * 0.5F + 0.5F, pitch + ((float)Math.PI / 2F), p_227206_16_ / 3.0F, j, p_227206_18_, 1.0D, p_227206_21_);
                return;
            }

            if (random.nextInt(4) != 0) {
                if (!this.canReach(chunkX, chunkZ, randOffsetXCoord, randOffsetZCoord, j, p_227206_18_, caveRadius)) {
                    return;
                }

                this.carveSphere(chunk, biomePos, seed, seaLevel, chunkX, chunkZ, randOffsetXCoord, startY, randOffsetZCoord, d0, d1, p_227206_21_);
            }
        }

    }

    @Override
    protected boolean skip(double p_222708_1_, double p_222708_3_, double p_222708_5_, int p_222708_7_) {
        return p_222708_3_ <= -0.7D || p_222708_1_ * p_222708_1_ + p_222708_3_ * p_222708_3_ + p_222708_5_ * p_222708_5_ >= 1.0D;
    }
}
