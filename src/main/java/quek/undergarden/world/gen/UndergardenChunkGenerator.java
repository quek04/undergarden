package quek.undergarden.world.gen;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.WorldGenRegion;
import quek.undergarden.UndergardenConfig;

import java.util.List;
import java.util.Random;

public class UndergardenChunkGenerator extends NoiseChunkGenerator<UndergardenGenerationSettings> {
    private final double[] field_222573_h = this.func_222572_j();
    private static final float[] field_222576_h = Util.make(new float[25], (p_222575_0_) -> {
        for(int i = -2; i <= 2; ++i) {
            for(int j = -2; j <= 2; ++j) {
                float f = 10.0F / MathHelper.sqrt((float)(i * i + j * j) + 0.2F);
                p_222575_0_[i + 2 + (j + 2) * 5] = f;
            }
        }

    });
    private final OctavesNoiseGenerator depthNoise;

    public UndergardenChunkGenerator(IWorld world, BiomeProvider provider, UndergardenGenerationSettings generationSettings) {
        super(world, provider, 8, 8, 256, generationSettings, false);
        this.depthNoise = new OctavesNoiseGenerator(this.randomSeed, 15, 0);
    }

    @Override
    protected void fillNoiseColumn(double[] noiseColumn, int noiseX, int noiseZ) {
        this.calcNoiseColumn(noiseColumn, noiseX, noiseZ, 684.412F, 684.412F, 8.555149841308594D, 4.277574920654297D, 3, -10);
    }

    @Override
    protected double[] getBiomeNoiseColumn(int noiseX, int noiseZ) {
        double[] adouble = new double[2];
        float f = 0.0F;
        float f1 = 0.0F;
        float f2 = 0.0F;
        int i = 2;
        int j = this.getSeaLevel();
        float f3 = this.biomeProvider.getNoiseBiome(noiseX, j, noiseZ).getDepth();

        for(int k = -2; k <= 2; ++k) {
            for(int l = -2; l <= 2; ++l) {
                Biome biome = this.biomeProvider.getNoiseBiome(noiseX + k, j, noiseZ + l);
                float depth = biome.getDepth();
                float scale = biome.getScale();

                float f6 = field_222576_h[k + 2 + (l + 2) * 5] / (depth + 2.0F);
                if (biome.getDepth() > f3) {
                    f6 /= 2.0F;
                }

                f += scale * f6;
                f1 += depth * f6;
                f2 += f6;
            }
        }

        f = f / f2;
        f1 = f1 / f2;
        f = f * 0.9F + 0.1F;
        f1 = (f1 * 4.0F - 1.0F) / 8.0F;
        adouble[0] = (double)f1 + this.getNoiseDepthAt(noiseX, noiseZ);
        adouble[1] = (double)f;
        return adouble;
    }

    private double getNoiseDepthAt(int noiseX, int noiseZ) {
        double d0 = this.depthNoise.getValue((double)(noiseX * 200), 10.0D, (double)(noiseZ * 200), 1.0D, 0.0D, true) * 65535.0D / 8000.0D;
        if (d0 < 0.0D) {
            d0 = -d0 * 0.3D;
        }

        d0 = d0 * 3.0D - 2.0D;
        if (d0 < 0.0D) {
            d0 = d0 / 28.0D;
        } else {
            if (d0 > 1.0D) {
                d0 = 1.0D;
            }

            d0 = d0 / 40.0D;
        }

        return d0;
    }

    @Override
    protected double func_222545_a(double p_222545_1_, double p_222545_3_, int p_222545_5_) {
        return this.field_222573_h[p_222545_5_];
    }

    private double[] func_222572_j() {
        double[] adouble = new double[this.noiseSizeY()];

        for(int i = 0; i < this.noiseSizeY(); ++i) {
            adouble[i] = Math.cos((double)i * Math.PI * 6.0D / (double)this.noiseSizeY()) * 2.0D;
            double d0 = i;
            if (i > this.noiseSizeY() / 2) {
                d0 = this.noiseSizeY() - 1 - i;
            }

            if (d0 < 4.0D) { //4
                d0 = 4.0D - d0;
                adouble[i] -= d0 * d0 * d0 * 10.0D;
            }
        }

        return adouble;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EntityClassification creatureType, BlockPos pos) {
        /*
        if (creatureType == EntityClassification.MONSTER) {
            if (Feature.NETHER_BRIDGE.isPositionInsideStructure(this.world, pos)) {
                return Feature.NETHER_BRIDGE.getSpawnList();
            }

            if (Feature.NETHER_BRIDGE.isPositionInStructure(this.world, pos) && this.world.getBlockState(pos.down()).getBlock() == Blocks.NETHER_BRICKS) {
                return Feature.NETHER_BRIDGE.getSpawnList();
            }
        }
        */
        return super.getPossibleCreatures(creatureType, pos);
    }

    @Override
    public int getGroundHeight() {
        return 33;
    }

    @Override
    public int getMaxHeight() {
        return 256;
    }

    @Override
    public int getSeaLevel() {
        return 32;
    }

    @Override
    public void generateSurface(WorldGenRegion p_225551_1_, IChunk p_225551_2_) {
        ChunkPos chunkpos = p_225551_2_.getPos();
        int i = chunkpos.x;
        int j = chunkpos.z;
        SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
        sharedseedrandom.setBaseChunkSeed(i, j);
        ChunkPos chunkpos1 = p_225551_2_.getPos();
        int k = chunkpos1.getXStart();
        int l = chunkpos1.getZStart();
        double d0 = 0.0625D;
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(int i1 = 0; i1 < 16; ++i1) {
            for(int j1 = 0; j1 < 16; ++j1) {
                int k1 = k + i1;
                int l1 = l + j1;
                int i2 = p_225551_2_.getTopBlockY(Heightmap.Type.WORLD_SURFACE_WG, i1, j1) + 1;
                double d1 = this.surfaceDepthNoise.noiseAt((double)k1 * 0.0625D, (double)l1 * 0.0625D, 0.0625D, (double)i1 * 0.0625D) * 15.0D;
                p_225551_1_.getBiome(blockpos$mutable.setPos(k + i1, i2, l + j1)).buildSurface(sharedseedrandom, p_225551_2_, k1, l1, i2, d1, this.getSettings().getDefaultBlock(), this.getSettings().getDefaultFluid(), this.getSeaLevel(), this.world.getSeed());
            }
        }

        this.makeBedrock(p_225551_2_, sharedseedrandom);
    }

    @Override
    protected void makeBedrock(IChunk chunkIn, Random rand) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        int i = chunkIn.getPos().getXStart();
        int j = chunkIn.getPos().getZStart();
        UndergardenGenerationSettings t = this.getSettings();
        int k = t.getBedrockFloorHeight();
        int l = t.getBedrockRoofHeight();

        for(BlockPos blockpos : BlockPos.getAllInBoxMutable(i, 0, j, i + 15, 0, j + 15)) {
            //roof
            if (l > 0) {
                for(int i1 = l; i1 >= l - 4; --i1) {
                    if (i1 >= l - rand.nextInt(5)) {
                        if(UndergardenConfig.genObsidianRoof.get()) {
                            chunkIn.setBlockState(blockpos$mutable.setPos(blockpos.getX(), i1, blockpos.getZ()), Blocks.OBSIDIAN.getDefaultState(), false);
                        }
                        else if(!UndergardenConfig.genObsidianRoof.get()) {
                            chunkIn.setBlockState(blockpos$mutable.setPos(blockpos.getX(), i1, blockpos.getZ()), Blocks.BEDROCK.getDefaultState(), false);
                        }
                    }
                }
            }

            //floor
            if (k < 256) {
                for(int j1 = k + 4; j1 >= k; --j1) {
                    if (j1 <= k + rand.nextInt(5)) {
                        if(UndergardenConfig.genObsidianFloor.get()) {
                            chunkIn.setBlockState(blockpos$mutable.setPos(blockpos.getX(), j1, blockpos.getZ()), Blocks.OBSIDIAN.getDefaultState(), false);
                        }
                        else if(!UndergardenConfig.genObsidianFloor.get()) {
                            chunkIn.setBlockState(blockpos$mutable.setPos(blockpos.getX(), j1, blockpos.getZ()), Blocks.BEDROCK.getDefaultState(), false);
                        }
                    }
                }
            }
        }

    }
}
