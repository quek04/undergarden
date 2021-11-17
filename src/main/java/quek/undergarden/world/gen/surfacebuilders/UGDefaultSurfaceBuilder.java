package quek.undergarden.world.gen.surfacebuilders;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.surfacebuilders.NetherSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class UGDefaultSurfaceBuilder extends NetherSurfaceBuilder {

    public UGDefaultSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> codec) {
        super(codec);
    }

    @Override
    public void apply(Random random, ChunkAccess chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, int minSurfaceLevel, long seed, SurfaceBuilderBaseConfiguration config) {
        int l = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        int i1 = -1;
        BlockState topBlock = config.getTopMaterial();
        BlockState underBlock = config.getUnderMaterial();

        for(int y = height; y >= minSurfaceLevel; --y) {
            mutablePos.set(x, y, z);
            BlockState chunkBlockState = chunk.getBlockState(mutablePos);
            if (chunkBlockState.isAir()) {
                i1 = -1;
            }
            else if (chunkBlockState.is(defaultBlock.getBlock())) {
                if (i1 == -1) {
                    boolean flag2 = false;
                    if (l <= 0) {
                        flag2 = true;
                        underBlock = UGBlocks.SEDIMENT.get().defaultBlockState();
                    }
                    else if (y <= seaLevel) {
                        topBlock = UGBlocks.SEDIMENT.get().defaultBlockState();
                        underBlock = UGBlocks.SEDIMENT.get().defaultBlockState();
                    }

                    if (y < seaLevel && flag2) {
                        topBlock = defaultFluid;
                    }

                    i1 = l;
                    if (y >= seaLevel - 1) {
                        chunk.setBlockState(mutablePos, topBlock, false);
                    }
                    else {
                        chunk.setBlockState(mutablePos, underBlock, false);
                    }
                }
                else if (i1 > 0) {
                    --i1;
                    chunk.setBlockState(mutablePos, underBlock, false);
                }
            }
        }
    }
}