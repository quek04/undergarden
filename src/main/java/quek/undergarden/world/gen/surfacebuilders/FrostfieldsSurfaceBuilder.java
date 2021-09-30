package quek.undergarden.world.gen.surfacebuilders;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class FrostfieldsSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderBaseConfiguration> {

    public FrostfieldsSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> codec) {
        super(codec);
    }

    @Override
    public void apply(Random pRandom, ChunkAccess pChunk, Biome pBiome, int pX, int pZ, int pHeight, double pNoise, BlockState pDefaultBlock, BlockState pDefaultFluid, int pSeaLevel, int pMinSurfaceLevel, long pSeed, SurfaceBuilderBaseConfiguration pConfig) {

    }

    protected void apply(Random pRandom, ChunkAccess pChunk, Biome pBiome, int pX, int pZ, int pHeight, double pNoise, BlockState pDefaultBlock, BlockState pDefaultFluid, BlockState pTopMaterial, BlockState pUnderMaterial, BlockState pUnderwaterMaterial, int pSeaLevel, int pMinSurfaceLevel) {
        BlockState topBlock = pTopMaterial;
        BlockState middleBlock = pUnderMaterial;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int i = -1;
        int basin = (int)(pNoise / 3.0D + 3.0D + pRandom.nextDouble() * 0.25D);
        int xPos = pX & 15;
        int zPos = pZ & 15;

        for(int y = pHeight; y >= 0; --y) {
            pos.set(xPos, y, zPos);
            BlockState blockstate2 = pChunk.getBlockState(pos);
            if (blockstate2.isAir()) {
                i = -1;
            }
            else if (blockstate2.is(pDefaultBlock.getBlock())) {
                if (i == -1) {
                    if (basin <= 0) {
                        topBlock = Blocks.AIR.defaultBlockState();
                        middleBlock = pDefaultBlock;
                    } else if (y >= pSeaLevel - 4 && y <= pSeaLevel + 1) {
                        topBlock = pTopMaterial;
                        middleBlock = pUnderMaterial;
                    }

                    if (y < pSeaLevel && (topBlock == null || topBlock.isAir())) {
                        topBlock = Blocks.ICE.defaultBlockState();
                        pos.set(xPos, y, zPos);
                    }

                    i = basin;
                    if (y >= pSeaLevel - 1) {
                        pChunk.setBlockState(pos, topBlock, false);
                    } else if (y < pSeaLevel - 7 - basin) {
                        topBlock = Blocks.AIR.defaultBlockState();
                        middleBlock = pDefaultBlock;
                        pChunk.setBlockState(pos, bottom, false);
                    } else {
                        pChunk.setBlockState(pos, middleBlock, false);
                    }
                } else if (i > 0) {
                    --i;
                    pChunk.setBlockState(pos, middleBlock, false);
                }
            }
            //replaces all default blocks (stone in Overworld, Depthrock in Undergarden etc) with Shiverstone
            if(pChunk.getBlockState(pos).is(pDefaultBlock.getBlock())) {
                pChunk.setBlockState(pos, UGBlocks.SHIVERSTONE.get().defaultBlockState(), false);
            }
            //replaces all water with ice
            if (pChunk.getBlockState(pos).is(pDefaultFluid.getBlock())) {
                pChunk.setBlockState(pos, Blocks.ICE.defaultBlockState(), false);
            }
        }

    }
}
