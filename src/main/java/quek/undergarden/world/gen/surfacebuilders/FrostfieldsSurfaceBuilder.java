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
        this.surfaceBuilder(pRandom, pChunk, pBiome, pX, pZ, pHeight, pNoise, pDefaultBlock, pDefaultFluid, pConfig.getTopMaterial(), pConfig.getUnderMaterial(), pConfig.getUnderwaterMaterial(), pSeaLevel, pMinSurfaceLevel);
    }

    protected void surfaceBuilder(Random pRandom, ChunkAccess pChunk, Biome pBiome, int pX, int pZ, int pHeight, double pNoise, BlockState pDefaultBlock, BlockState pDefaultFluid, BlockState pTopMaterial, BlockState pUnderMaterial, BlockState pUnderwaterMaterial, int pSeaLevel, int pMinSurfaceLevel) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int i = (int)(pNoise / 3.0D + 3.0D + pRandom.nextDouble() * 0.25D);
        if (i == 0) {
            boolean flag = false;

            for(int height = pHeight; height >= pMinSurfaceLevel; --height) {
                pos.set(pX, height, pZ);
                BlockState blockstate = pChunk.getBlockState(pos);
                if (blockstate.isAir()) {
                    flag = false;
                }
                else if (blockstate.is(pDefaultBlock.getBlock())) {
                    if (!flag) {
                        BlockState blockstate1;
                        if (height >= pSeaLevel) {
                            blockstate1 = Blocks.AIR.defaultBlockState();
                        }
                        else if (height == pSeaLevel - 1) {
                            blockstate1 = pBiome.getTemperature(pos) < 0.15F ? Blocks.ICE.defaultBlockState() : pDefaultFluid;
                        }
                        else if (height >= pSeaLevel - (7 + i)) {
                            blockstate1 = pDefaultBlock;
                        }
                        else {
                            blockstate1 = pUnderwaterMaterial;
                        }

                        pChunk.setBlockState(pos, blockstate1, false);
                    }

                    flag = true;
                }
            }
        }
        else {
            BlockState underMaterial = pUnderMaterial;
            int k = -1;

            for(int height = pHeight; height >= pMinSurfaceLevel; --height) {
                pos.set(pX, height, pZ);
                BlockState blockstate4 = pChunk.getBlockState(pos);
                if (blockstate4.isAir()) {
                    k = -1;
                }
                else if (blockstate4.is(pDefaultBlock.getBlock())) {
                    if (k == -1) {
                        k = i;
                        BlockState blockstate2;
                        if (height >= pSeaLevel + 2) {
                            blockstate2 = pTopMaterial;
                        }
                        else if (height >= pSeaLevel - 1) {
                            underMaterial = pUnderMaterial;
                            blockstate2 = pTopMaterial;
                        }
                        else if (height >= pSeaLevel - 4) {
                            underMaterial = pUnderMaterial;
                            blockstate2 = pUnderMaterial;
                        }
                        else if (height >= pSeaLevel - (7 + i)) {
                            blockstate2 = underMaterial;
                        }
                        else {
                            underMaterial = pDefaultBlock;
                            blockstate2 = pUnderwaterMaterial;
                        }

                        pChunk.setBlockState(pos, blockstate2, false);
                    }
                    else if (k > 0) {
                        --k;
                        pChunk.setBlockState(pos, underMaterial, false);
                    }
                    //replaces all default blocks (stone in Overworld, Depthrock in Undergarden etc) with Shiverstone
                    if(pChunk.getBlockState(pos).is(pDefaultBlock.getBlock())) {
                        pChunk.setBlockState(pos, UGBlocks.SHIVERSTONE.get().defaultBlockState(), false);
                    }
                    //replaces all default fluids with ice
                    if (pChunk.getBlockState(pos).is(pDefaultFluid.getBlock())) {
                        pChunk.setBlockState(pos, Blocks.ICE.defaultBlockState(), false);
                    }
                }
            }
        }
    }
}