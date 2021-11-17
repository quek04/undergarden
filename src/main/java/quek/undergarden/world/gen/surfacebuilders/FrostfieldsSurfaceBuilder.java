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
    public void apply(Random random, ChunkAccess chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, int minSurfaceLevel, long seed, SurfaceBuilderBaseConfiguration config) {
        double d0 = 0.0D;
        double d1 = 0.0D;
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        float f = biome.getTemperature(mutablePos.set(x, 31, z));
        double icebergNoise = Math.min(Math.abs(noise), this.icebergNoise.getValue((double)x * 0.1D, (double)z * 0.1D, false) * 15.0D);
        if (icebergNoise > 1.8D) {
            double icebergRoofNoise = Math.abs(this.icebergRoofNoise.getValue((double)x * 0.09765625D, (double)z * 0.09765625D, false));
            d0 = icebergNoise * icebergNoise * 1.2D;
            double d5 = Math.ceil(icebergRoofNoise * 40.0D) + 14.0D;
            if (d0 > d5) {
                d0 = d5;
            }

            if (f > 0.1F) {
                d0 -= 2.0D;
            }

            if (d0 > 2.0D) {
                d1 = (double)seaLevel - d0 - 7.0D;
                d0 = d0 + (double)seaLevel;
            } else {
                d0 = 0.0D;
            }
        }

        SurfaceBuilderConfiguration surfaceConfig = biome.getGenerationSettings().getSurfaceBuilderConfig();
        BlockState underMaterial = surfaceConfig.getUnderMaterial();
        BlockState topMaterial = surfaceConfig.getTopMaterial();
        BlockState blockstate1 = underMaterial;
        BlockState blockstate2 = topMaterial;
        int noiseIDK = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int k = -1;
        int l = 0;
        int i1 = 2 + random.nextInt(4);
        int j1 = seaLevel + 18 + random.nextInt(10);

        for(int y = Math.max(height, (int)d0 + 1); y >= minSurfaceLevel; --y) {
            mutablePos.set(x, y, z);
            if (chunk.getBlockState(mutablePos).isAir() && y < (int)d0 && random.nextDouble() > 0.01D) {
                chunk.setBlockState(mutablePos, Blocks.PACKED_ICE.defaultBlockState(), false);
            }
            else if (chunk.getBlockState(mutablePos).getMaterial() == Material.WATER && y > (int)d1 && y < seaLevel && d1 != 0.0D && random.nextDouble() > 0.15D) {
                chunk.setBlockState(mutablePos, Blocks.PACKED_ICE.defaultBlockState(), false);
            }

            BlockState blockstate3 = chunk.getBlockState(mutablePos);
            if (blockstate3.isAir()) {
                k = -1;
            }
            else if (!blockstate3.is(defaultBlock.getBlock())) {
                if (blockstate3.is(Blocks.PACKED_ICE) && l <= i1 && y > j1) {
                    chunk.setBlockState(mutablePos, Blocks.SNOW_BLOCK.defaultBlockState(), false);
                    ++l;
                }
            }
            else if (k == -1) {
                if (noiseIDK <= 0) {
                    blockstate2 = Blocks.AIR.defaultBlockState();
                    blockstate1 = defaultBlock;
                }
                else if (y <= seaLevel) {
                    blockstate2 = UGBlocks.SEDIMENT.get().defaultBlockState();
                    blockstate1 = UGBlocks.SEDIMENT.get().defaultBlockState();
                }

                if (y < seaLevel && (blockstate2 == null || blockstate2.isAir())) {
                    if (biome.getTemperature(mutablePos.set(x, y, z)) < 0.15F) {
                        blockstate2 = Blocks.AIR.defaultBlockState();
                    }
                    else {
                        blockstate2 = defaultFluid;
                    }
                }

                k = noiseIDK;
                if (y >= seaLevel - 1) {
                    chunk.setBlockState(mutablePos, blockstate2, false);
                }
                else if (y < seaLevel - 7 - noiseIDK) {
                    blockstate2 = Blocks.AIR.defaultBlockState();
                    blockstate1 = defaultBlock;
                    chunk.setBlockState(mutablePos, UGBlocks.SEDIMENT.get().defaultBlockState(), false);
                }
                else {
                    chunk.setBlockState(mutablePos, blockstate1, false);
                }
            }
            else if (k > 0) {
                --k;
                chunk.setBlockState(mutablePos, blockstate1, false);
//                if (k == 0 && blockstate1.is(Blocks.SAND) && j > 1) {
//                    k = pRandom.nextInt(4) + Math.max(0, k1 - 63);
//                    blockstate1 = blockstate1.is(Blocks.RED_SAND) ? Blocks.RED_SANDSTONE.defaultBlockState() : Blocks.SANDSTONE.defaultBlockState();
//                }
            }
            if(chunk.getBlockState(mutablePos).is(defaultBlock.getBlock())) {
                chunk.setBlockState(mutablePos, UGBlocks.SHIVERSTONE.get().defaultBlockState(), false);
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