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
    public void apply(Random random, ChunkAccess chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderBaseConfiguration config) {
        this.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, config.getTopMaterial(), config.getUnderMaterial(), config.getUnderwaterMaterial(), seaLevel);
    }

    protected void buildSurface(Random random, ChunkAccess chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, BlockState top, BlockState middle, BlockState bottom, int sealevel) {
        BlockState topBlock = top;
        BlockState middleBlock = middle;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int i = -1;
        int basin = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int xPos = x & 15;
        int zPos = z & 15;

        for(int y = startHeight; y >= 0; --y) {
            pos.set(xPos, y, zPos);
            BlockState blockstate2 = chunkIn.getBlockState(pos);
            if (blockstate2.isAir()) {
                i = -1;
            }
            else if (blockstate2.is(defaultBlock.getBlock())) {
                if (i == -1) {
                    if (basin <= 0) {
                        topBlock = Blocks.AIR.defaultBlockState();
                        middleBlock = defaultBlock;
                    } else if (y >= sealevel - 4 && y <= sealevel + 1) {
                        topBlock = top;
                        middleBlock = middle;
                    }

                    if (y < sealevel && (topBlock == null || topBlock.isAir())) {
                        topBlock = Blocks.ICE.defaultBlockState();
                        pos.set(xPos, y, zPos);
                    }

                    i = basin;
                    if (y >= sealevel - 1) {
                        chunkIn.setBlockState(pos, topBlock, false);
                    } else if (y < sealevel - 7 - basin) {
                        topBlock = Blocks.AIR.defaultBlockState();
                        middleBlock = defaultBlock;
                        chunkIn.setBlockState(pos, bottom, false);
                    } else {
                        chunkIn.setBlockState(pos, middleBlock, false);
                    }
                } else if (i > 0) {
                    --i;
                    chunkIn.setBlockState(pos, middleBlock, false);
                }
            }
            //replaces all default blocks (stone in Overworld, Depthrock in Undergarden etc) with Shiverstone
            if(chunkIn.getBlockState(pos).is(defaultBlock.getBlock())) {
                chunkIn.setBlockState(pos, UGBlocks.SHIVERSTONE.get().defaultBlockState(), false);
            }
            //replaces all water with ice
            if (chunkIn.getBlockState(pos).is(Blocks.WATER)) {
                chunkIn.setBlockState(pos, Blocks.ICE.defaultBlockState(), false);
            }
        }

    }
}
