package quek.undergarden.world.gen.surfacebuilders;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderConfiguration;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import net.minecraft.world.level.material.Material;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;
import java.util.stream.IntStream;

public class FrostfieldsSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderBaseConfiguration> {

    private PerlinSimplexNoise icebergNoise;
    private PerlinSimplexNoise icebergRoofNoise;
    private long seed;

    public FrostfieldsSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> codec) {
        super(codec);
    }

    @Override
    public void apply(Random pRandom, ChunkAccess pChunk, Biome pBiome, int pX, int pZ, int pHeight, double pNoise, BlockState pDefaultBlock, BlockState pDefaultFluid, int pSeaLevel, int pMinSurfaceLevel, long pSeed, SurfaceBuilderBaseConfiguration pConfig) {
        double d0 = 0.0D;
        double d1 = 0.0D;
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        float f = pBiome.getTemperature(mutablePos.set(pX, 31, pZ));
        double icebergNoise = Math.min(Math.abs(pNoise), this.icebergNoise.getValue((double)pX * 0.1D, (double)pZ * 0.1D, false) * 15.0D);
        if (icebergNoise > 1.8D) {
            double icebergRoofNoise = Math.abs(this.icebergRoofNoise.getValue((double)pX * 0.09765625D, (double)pZ * 0.09765625D, false));
            d0 = icebergNoise * icebergNoise * 1.2D;
            double d5 = Math.ceil(icebergRoofNoise * 40.0D) + 14.0D;
            if (d0 > d5) {
                d0 = d5;
            }

            if (f > 0.1F) {
                d0 -= 2.0D;
            }

            if (d0 > 2.0D) {
                d1 = (double)pSeaLevel - d0 - 7.0D;
                d0 = d0 + (double)pSeaLevel;
            } else {
                d0 = 0.0D;
            }
        }

        int x = pX & 15;
        int z = pZ & 15;
        SurfaceBuilderConfiguration config = pBiome.getGenerationSettings().getSurfaceBuilderConfig();
        BlockState underMaterial = config.getUnderMaterial();
        BlockState topMaterial = config.getTopMaterial();
        BlockState blockstate1 = underMaterial;
        BlockState blockstate2 = topMaterial;
        int noise = (int)(pNoise / 3.0D + 3.0D + pRandom.nextDouble() * 0.25D);
        int k = -1;
        int l = 0;
        int i1 = 2 + pRandom.nextInt(4);
        int j1 = pSeaLevel + 18 + pRandom.nextInt(10);

        for(int y = Math.max(pHeight, (int)d0 + 1); y >= pMinSurfaceLevel; --y) {
            mutablePos.set(x, y, z);
            if (pChunk.getBlockState(mutablePos).isAir() && y < (int)d0 && pRandom.nextDouble() > 0.01D) {
                pChunk.setBlockState(mutablePos, Blocks.PACKED_ICE.defaultBlockState(), false);
            }
            else if (pChunk.getBlockState(mutablePos).getMaterial() == Material.WATER && y > (int)d1 && y < pSeaLevel && d1 != 0.0D && pRandom.nextDouble() > 0.15D) {
                pChunk.setBlockState(mutablePos, Blocks.PACKED_ICE.defaultBlockState(), false);
            }

            BlockState blockstate3 = pChunk.getBlockState(mutablePos);
            if (blockstate3.isAir()) {
                k = -1;
            }
            else if (!blockstate3.is(pDefaultBlock.getBlock())) {
                if (blockstate3.is(Blocks.PACKED_ICE) && l <= i1 && y > j1) {
                    pChunk.setBlockState(mutablePos, Blocks.SNOW_BLOCK.defaultBlockState(), false);
                    ++l;
                }
            }
            else if (k == -1) {
                if (noise <= 0) {
                    blockstate2 = Blocks.AIR.defaultBlockState();
                    blockstate1 = pDefaultBlock;
                }
                else if (y >= pSeaLevel - 4 && y <= pSeaLevel + 1) {
                    blockstate2 = topMaterial;
                    blockstate1 = underMaterial;
                }

                if (y < pSeaLevel && (blockstate2 == null || blockstate2.isAir())) {
                    if (pBiome.getTemperature(mutablePos.set(pX, y, pZ)) < 0.15F) {
                        blockstate2 = Blocks.AIR.defaultBlockState();
                    }
                    else {
                        blockstate2 = pDefaultFluid;
                    }
                }

                k = noise;
                if (y >= pSeaLevel - 1) {
                    pChunk.setBlockState(mutablePos, blockstate2, false);
                }
                else if (y < pSeaLevel - 7 - noise) {
                    blockstate2 = Blocks.AIR.defaultBlockState();
                    blockstate1 = pDefaultBlock;
                    pChunk.setBlockState(mutablePos, UGBlocks.SEDIMENT.get().defaultBlockState(), false);
                }
                else {
                    pChunk.setBlockState(mutablePos, blockstate1, false);
                }
            }
            else if (k > 0) {
                --k;
                pChunk.setBlockState(mutablePos, blockstate1, false);
//                if (k == 0 && blockstate1.is(Blocks.SAND) && j > 1) {
//                    k = pRandom.nextInt(4) + Math.max(0, k1 - 63);
//                    blockstate1 = blockstate1.is(Blocks.RED_SAND) ? Blocks.RED_SANDSTONE.defaultBlockState() : Blocks.SANDSTONE.defaultBlockState();
//                }
            }
            if(pChunk.getBlockState(mutablePos).is(pDefaultBlock.getBlock())) {
                pChunk.setBlockState(mutablePos, UGBlocks.SHIVERSTONE.get().defaultBlockState(), false);
            }
        }
    }

    @Override
    public void initNoise(long pSeed) {
        if (this.seed != pSeed || this.icebergNoise == null || this.icebergRoofNoise == null) {
            WorldgenRandom worldgenrandom = new WorldgenRandom(pSeed);
            this.icebergNoise = new PerlinSimplexNoise(worldgenrandom, IntStream.rangeClosed(-3, 0));
            this.icebergRoofNoise = new PerlinSimplexNoise(worldgenrandom, ImmutableList.of(0));
        }

        this.seed = pSeed;
    }
}