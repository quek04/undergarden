package quek.undergarden.world.gen.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import quek.undergarden.registry.UndergardenBlocks;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class UndergardenCaveWorldCarver extends CaveWorldCarver {

    public UndergardenCaveWorldCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> p_i49929_1_) {
        super(p_i49929_1_, 128);
        this.carvableBlocks = ImmutableSet.of(
                UndergardenBlocks.depthrock.get(),
                UndergardenBlocks.deepturf_block.get(),
                UndergardenBlocks.deepsoil.get(),
                UndergardenBlocks.coal_ore.get(),
                UndergardenBlocks.cloggrum_ore.get(),
                UndergardenBlocks.froststeel_ore.get(),
                UndergardenBlocks.utherium_ore.get(),
                Blocks.WATER
        );
        this.carvableFluids = ImmutableSet.of(
                Fluids.WATER
        );
    }

    @Override
    public boolean shouldCarve(Random rand, int chunkX, int chunkZ, ProbabilityConfig config) {
        return rand.nextFloat() <= config.probability;
    }

    @Override
    public boolean func_225555_a_(IChunk p_225555_1_, Function<BlockPos, Biome> p_225555_2_, Random p_225555_3_, int p_225555_4_, int p_225555_5_, int p_225555_6_, int p_225555_7_, int p_225555_8_, BitSet p_225555_9_, ProbabilityConfig p_225555_10_) {
        int i = (this.func_222704_c() * 2 - 1) * 16;
        int j = p_225555_3_.nextInt(p_225555_3_.nextInt(p_225555_3_.nextInt(this.func_222724_a()) + 1) + 1);

        for(int k = 0; k < j; ++k) {
            double d0 = (double)(p_225555_5_ * 16 + p_225555_3_.nextInt(16));
            double d1 = (double)this.generateCaveStartY(p_225555_3_);
            double d2 = (double)(p_225555_6_ * 16 + p_225555_3_.nextInt(16));
            int l = 1;
            if (p_225555_3_.nextInt(4) == 0) {
                double d3 = 0.5D;
                float f1 = 1.0F + p_225555_3_.nextFloat() * 6.0F;
                this.func_227205_a_(p_225555_1_, p_225555_2_, p_225555_3_.nextLong(), p_225555_4_, p_225555_7_, p_225555_8_, d0, d1, d2, f1, 0.5D, p_225555_9_);
                l += p_225555_3_.nextInt(4);
            }

            for(int k1 = 0; k1 < l; ++k1) {
                float f = p_225555_3_.nextFloat() * ((float)Math.PI * 2F);
                float f3 = (p_225555_3_.nextFloat() - 0.5F) / 4.0F;
                float f2 = this.generateCaveRadius(p_225555_3_);
                int i1 = i - p_225555_3_.nextInt(i / 4);
                int j1 = 0;
                this.func_227206_a_(p_225555_1_, p_225555_2_, p_225555_3_.nextLong(), p_225555_4_, p_225555_7_, p_225555_8_, d0, d1, d2, f2, f, f3, 0, i1, this.func_222725_b(), p_225555_9_);
            }
        }

        return true;
    }

    @Override
    protected int func_222724_a() {
        return 15;
    }

    @Override
    protected float generateCaveRadius(Random rand) {
        float f = rand.nextFloat() * 2.0F + rand.nextFloat();
        if (rand.nextInt(10) == 0) {
            f *= rand.nextFloat() * rand.nextFloat() * 3.0F + 1.0F;
        }

        return f;
    }

    @Override
    protected double func_222725_b() {
        return 1.0D;
    }

    @Override
    protected int generateCaveStartY(Random p_222726_1_) {
        return p_222726_1_.nextInt(p_222726_1_.nextInt(120) + 8);
    }

    @Override
    protected void func_227205_a_(IChunk p_227205_1_, Function<BlockPos, Biome> p_227205_2_, long p_227205_3_, int p_227205_5_, int p_227205_6_, int p_227205_7_, double p_227205_8_, double p_227205_10_, double p_227205_12_, float p_227205_14_, double p_227205_15_, BitSet p_227205_17_) {
        double d0 = 1.5D + (double)(MathHelper.sin(((float)Math.PI / 2F)) * p_227205_14_);
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
            double d0 = 1.5D + (double)(MathHelper.sin((float)Math.PI * (float)j / (float)p_227206_18_) * p_227206_14_);
            double d1 = d0 * p_227206_19_;
            float f2 = MathHelper.cos(p_227206_16_);
            p_227206_8_ += (double)(MathHelper.cos(p_227206_15_) * f2);
            p_227206_10_ += (double)MathHelper.sin(p_227206_16_);
            p_227206_12_ += (double)(MathHelper.sin(p_227206_15_) * f2);
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
