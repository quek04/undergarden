package quek.undergarden.world;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import quek.undergarden.registry.UndergardenBlocks;

import java.util.Random;
import java.util.function.Function;

public class DefaultUndergardenSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
    private static final BlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();
    private static final BlockState DEEPTURF = UndergardenBlocks.deepturf.get().getDefaultState();
    private static final BlockState DEPTHROCK = UndergardenBlocks.depthrock.get().getDefaultState();
    private static final BlockState GRAVEL = Blocks.GRAVEL.getDefaultState();

    protected OctavesNoiseGenerator octaves_gen;


    public DefaultUndergardenSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> config) {
        super(config);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        int i = seaLevel + 1;
        int j = x & 15;
        int k = z & 15;
        double d0 = 0.03125D;
        boolean flag = this.octaves_gen.func_205563_a((double)x * 0.03125D, (double)z * 0.03125D, 0.0D) * 75.0D + random.nextDouble() > 0.0D;
        boolean flag1 = this.octaves_gen.func_205563_a((double)x * 0.03125D, 109.0D, (double)z * 0.03125D) * 75.0D + random.nextDouble() > 0.0D;
        int l = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        int i1 = -1;
        BlockState blockstate = DEEPTURF;
        BlockState blockstate1 = DEPTHROCK;

        for(int j1 = 127; j1 >= 0; --j1) {
            blockpos$mutable.setPos(j, j1, k);
            BlockState blockstate2 = chunkIn.getBlockState(blockpos$mutable);
            if (blockstate2.getBlock() != null && !blockstate2.isAir()) {
                if (blockstate2.getBlock() == defaultBlock.getBlock()) {
                    if (i1 == -1) {
                        if (l <= 0) {
                            blockstate = CAVE_AIR;
                            blockstate1 = DEPTHROCK;
                        } else if (j1 >= i - 4 && j1 <= i + 1) {
                            blockstate = DEEPTURF;
                            blockstate1 = DEPTHROCK;
                            if (flag1) {
                                blockstate = GRAVEL;
                                blockstate1 = DEPTHROCK;
                            }
                        }

                        if (j1 < i && (blockstate == null || blockstate.isAir())) {
                            blockstate = defaultFluid;
                        }

                        i1 = l;
                        if (j1 >= i - 1) {
                            chunkIn.setBlockState(blockpos$mutable, blockstate, false);
                        } else {
                            chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
                        }
                    } else if (i1 > 0) {
                        --i1;
                        chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
                    }
                }
            } else {
                i1 = -1;
            }
        }

    }
}
