package quek.undergarden.world.gen.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import org.apache.commons.lang3.mutable.MutableBoolean;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenFluids;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class UndergardenCaveWorldCarver extends CaveWorldCarver {

    public UndergardenCaveWorldCarver(Codec<ProbabilityConfig> configCodec) {
        super(configCodec, 256);
        this.carvableBlocks = ImmutableSet.of(
                UndergardenBlocks.depthrock.get(),
                UndergardenBlocks.shiverstone.get(),
                UndergardenBlocks.deepturf_block.get(),
                UndergardenBlocks.ashen_deepturf.get(),
                UndergardenBlocks.deepsoil.get(),
                UndergardenBlocks.coal_ore.get(),
                UndergardenBlocks.iron_ore.get(),
                UndergardenBlocks.gold_ore.get(),
                UndergardenBlocks.diamond_ore.get(),
                UndergardenBlocks.cloggrum_ore.get(),
                UndergardenBlocks.froststeel_ore.get(),
                UndergardenBlocks.utherium_ore.get(),
                UndergardenBlocks.regalium_ore.get()
        );
        this.carvableFluids = ImmutableSet.of(
                Fluids.WATER,
                UndergardenFluids.virulent_source
        );
    }

    @Override
    protected boolean func_230358_a_(IChunk chunk, Function<BlockPos, Biome> p_230358_2_, BitSet p_230358_3_, Random rand, BlockPos.Mutable p_230358_5_, BlockPos.Mutable p_230358_6_, BlockPos.Mutable p_230358_7_, int p_230358_8_, int p_230358_9_, int p_230358_10_, int p_230358_11_, int p_230358_12_, int p_230358_13_, int y, int p_230358_15_, MutableBoolean p_230358_16_) {
        int i = p_230358_13_ | p_230358_15_ << 4 | y << 8;
        if (p_230358_3_.get(i)) {
            return false;
        } else {
            p_230358_3_.set(i);
            p_230358_5_.setPos(p_230358_11_, y, p_230358_12_);
            BlockState blockstate = chunk.getBlockState(p_230358_5_);
            BlockState blockstate1 = chunk.getBlockState(p_230358_6_.func_239622_a_(p_230358_5_, Direction.UP));

            if (!this.canCarveBlock(blockstate, blockstate1)) {
                return false;
            } else {
                if (y < 11) {
                    chunk.setBlockState(p_230358_5_, UndergardenBlocks.virulent_mix.get().getDefaultState(), false);
                } else {
                    chunk.setBlockState(p_230358_5_, CAVE_AIR, false);
                    if (p_230358_16_.isTrue()) {
                        p_230358_7_.func_239622_a_(p_230358_5_, Direction.DOWN);
                        if (chunk.getBlockState(p_230358_7_).isIn(UndergardenBlocks.deepsoil.get())) {
                            chunk.setBlockState(p_230358_7_, p_230358_2_.apply(p_230358_5_).func_242440_e().func_242502_e().getTop(), false);
                        }
                    }
                }

                return true;
            }
        }
    }

    @Override
    public boolean shouldCarve(Random rand, int chunkX, int chunkZ, ProbabilityConfig config) {
        return rand.nextFloat() <= config.probability;
    }

    @Override
    public boolean func_225555_a_(IChunk p_225555_1_, Function<BlockPos, Biome> p_225555_2_, Random p_225555_3_, int p_225555_4_, int p_225555_5_, int p_225555_6_, int p_225555_7_, int p_225555_8_, BitSet p_225555_9_, ProbabilityConfig p_225555_10_) {
        int i = (this.func_222704_c() * 2 - 1) * 16;
        int j = p_225555_3_.nextInt(p_225555_3_.nextInt(p_225555_3_.nextInt(this.func_230357_a_()) + 1) + 1);

        for(int k = 0; k < j; ++k) {
            double d0 = (p_225555_5_ * 16 + p_225555_3_.nextInt(16));
            double d1 = this.func_230361_b_(p_225555_3_);
            double d2 = (p_225555_6_ * 16 + p_225555_3_.nextInt(16));
            int l = 1;
            if (p_225555_3_.nextInt(4) == 0) {
                float f1 = 1.0F + p_225555_3_.nextFloat() * 6.0F;
                this.func_227205_a_(p_225555_1_, p_225555_2_, p_225555_3_.nextLong(), p_225555_4_, p_225555_7_, p_225555_8_, d0, d1, d2, f1, 0.5D, p_225555_9_);
                l += p_225555_3_.nextInt(4);
            }

            for(int k1 = 0; k1 < l; ++k1) {
                float f = p_225555_3_.nextFloat() * ((float)Math.PI * 2F);
                float f3 = (p_225555_3_.nextFloat() - 0.5F) / 4.0F;
                float f2 = this.func_230359_a_(p_225555_3_);
                int i1 = i - p_225555_3_.nextInt(i / 4);
                this.func_227206_a_(p_225555_1_, p_225555_2_, p_225555_3_.nextLong(), p_225555_4_, p_225555_7_, p_225555_8_, d0, d1, d2, f2, f, f3, 0, i1, this.func_230360_b_(), p_225555_9_);
            }
        }

        return true;
    }

    @Override
    protected int func_230357_a_() {
        return 15;
    }

    @Override
    protected float func_230359_a_(Random p_230359_1_) {
        float f = p_230359_1_.nextFloat() * 2.0F + p_230359_1_.nextFloat();
        if (p_230359_1_.nextInt(10) == 0) {
            f *= p_230359_1_.nextFloat() * p_230359_1_.nextFloat() * 3.0F + 1.0F;
        }

        return f * 3;
    }

    @Override
    protected double func_230360_b_() {
        return 1.0D;
    }

    @Override
    protected int func_230361_b_(Random p_230361_1_) {
        return p_230361_1_.nextInt(p_230361_1_.nextInt(250) + 6);
    }

    @Override
    protected void func_227205_a_(IChunk p_227205_1_, Function<BlockPos, Biome> p_227205_2_, long p_227205_3_, int p_227205_5_, int p_227205_6_, int p_227205_7_, double p_227205_8_, double p_227205_10_, double p_227205_12_, float p_227205_14_, double p_227205_15_, BitSet p_227205_17_) {
        double d0 = 1.5D + (MathHelper.sin(((float)Math.PI / 2F)) * p_227205_14_);
        double d1 = d0 * p_227205_15_;
        this.func_227208_a_(p_227205_1_, p_227205_2_, p_227205_3_, p_227205_5_, p_227205_6_, p_227205_7_, p_227205_8_ + 1.0D, p_227205_10_, p_227205_12_, d0, d1, p_227205_17_);
    }

    @Override
    protected void func_227206_a_(IChunk p_227206_1_, Function<BlockPos, Biome> p_227206_2_, long p_227206_3_, int p_227206_5_, int p_227206_6_, int p_227206_7_, double p_227206_8_, double p_227206_10_, double p_227206_12_, float p_227206_14_, float p_227206_15_, float p_227206_16_, int p_227206_17_, int p_227206_18_, double p_227206_19_, BitSet p_227206_21_) {
        Random random = new Random(p_227206_3_);
        int i = random.nextInt(p_227206_18_ / 2) + p_227206_18_ / 4;
        boolean flag = random.nextInt(6) == 0;
        float f = 0.0F;
        float f1 = 0.0F;

        for(int j = p_227206_17_; j < p_227206_18_; ++j) {
            double d0 = 1.5D + (MathHelper.sin((float)Math.PI * (float)j / (float)p_227206_18_) * p_227206_14_);
            double d1 = d0 * p_227206_19_;
            float f2 = MathHelper.cos(p_227206_16_);
            p_227206_8_ += (MathHelper.cos(p_227206_15_) * f2);
            p_227206_10_ += MathHelper.sin(p_227206_16_);
            p_227206_12_ += (MathHelper.sin(p_227206_15_) * f2);
            p_227206_16_ = p_227206_16_ * (flag ? 0.92F : 0.7F);
            p_227206_16_ = p_227206_16_ + f1 * 0.1F;
            p_227206_15_ += f * 0.1F;
            f1 = f1 * 0.9F;
            f = f * 0.75F;
            f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f = f + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
            if (j == i && p_227206_14_ > 1.0F) {
                this.func_227206_a_(p_227206_1_, p_227206_2_, random.nextLong(), p_227206_5_, p_227206_6_, p_227206_7_, p_227206_8_, p_227206_10_, p_227206_12_, random.nextFloat() * 0.5F + 0.5F, p_227206_15_ - ((float)Math.PI / 2F), p_227206_16_ / 3.0F, j, p_227206_18_, 1.0D, p_227206_21_);
                this.func_227206_a_(p_227206_1_, p_227206_2_, random.nextLong(), p_227206_5_, p_227206_6_, p_227206_7_, p_227206_8_, p_227206_10_, p_227206_12_, random.nextFloat() * 0.5F + 0.5F, p_227206_15_ + ((float)Math.PI / 2F), p_227206_16_ / 3.0F, j, p_227206_18_, 1.0D, p_227206_21_);
                return;
            }

            if (random.nextInt(4) != 0) {
                if (!this.func_222702_a(p_227206_6_, p_227206_7_, p_227206_8_, p_227206_12_, j, p_227206_18_, p_227206_14_)) {
                    return;
                }

                this.func_227208_a_(p_227206_1_, p_227206_2_, p_227206_3_, p_227206_5_, p_227206_6_, p_227206_7_, p_227206_8_, p_227206_10_, p_227206_12_, d0, d1, p_227206_21_);
            }
        }

    }

    @Override
    protected boolean func_222708_a(double p_222708_1_, double p_222708_3_, double p_222708_5_, int p_222708_7_) {
        return p_222708_3_ <= -0.7D || p_222708_1_ * p_222708_1_ + p_222708_3_ * p_222708_3_ + p_222708_5_ * p_222708_5_ >= 1.0D;
    }
}
