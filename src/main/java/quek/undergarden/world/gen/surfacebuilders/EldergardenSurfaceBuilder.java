package quek.undergarden.world.gen.surfacebuilders;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class EldergardenSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public EldergardenSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        BlockPos.Mutable block = new BlockPos.Mutable();
        int xPos = x & 15;
        int zPos = z & 15;

        for(int y = 256; y >= 0; y--) {
            block.setPos(xPos, y, zPos);

            if(chunkIn.getBlockState(block).isIn(UGBlocks.depthrock.get())) {
                chunkIn.setBlockState(block, UGBlocks.shiverstone.get().getDefaultState(), false);
            }
        }
    }
}
