package quek.undergarden.world.gen.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
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
    public boolean func_225555_a_(IChunk p_225555_1_, Function<BlockPos, Biome> p_225555_2_, Random p_225555_3_, int p_225555_4_, int p_225555_5_, int p_225555_6_, int p_225555_7_, int p_225555_8_, BitSet p_225555_9_, ProbabilityConfig p_225555_10_) {
        int i = (this.func_222704_c() * 2 - 1) * 32;
        int j = p_225555_3_.nextInt(p_225555_3_.nextInt(p_225555_3_.nextInt(this.func_222724_a()) + 1) + 1);

        for(int k = 0; k < j; ++k) {
            double d0 = (double)(p_225555_5_ * 32 + p_225555_3_.nextInt(16));
            double d1 = (double)this.generateCaveStartY(p_225555_3_);
            double d2 = (double)(p_225555_6_ * 32 + p_225555_3_.nextInt(16));
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
}
