package quek.undergarden.world.gen.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.material.Fluids;
import org.apache.commons.lang3.mutable.MutableBoolean;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGFluids;

import javax.annotation.Nullable;
import java.util.function.Function;

public class UGCaveWorldCarver extends CaveWorldCarver {

    public UGCaveWorldCarver(Codec<CaveCarverConfiguration> configCodec) {
        super(configCodec);
        this.liquids = ImmutableSet.of(
                Fluids.WATER
        );
    }

    @Override
    protected float getThickness(RandomSource random) {
        return super.getThickness(random) * 3;
    }

    @Override
    protected boolean carveEllipsoid(CarvingContext context, CaveCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> biomeAccessor, Aquifer aquifer, double x, double y, double z, double horizontalRadius, double verticalRadius, CarvingMask carvingMask, WorldCarver.CarveSkipChecker skipChecker) {
        ChunkPos chunkPos = chunk.getPos();
        double middleX = chunkPos.getMiddleBlockX();
        double middleZ = chunkPos.getMiddleBlockZ();
        double d2 = 16.0D + horizontalRadius * 2.0D;
        if (!(Math.abs(x - middleX) > d2) && !(Math.abs(z - middleZ) > d2)) {
            int minX = chunkPos.getMinBlockX();
            int minZ = chunkPos.getMinBlockZ();
            int k = Math.max(Mth.floor(x - horizontalRadius) - minX - 1, 0);
            int l = Math.min(Mth.floor(x + horizontalRadius) - minX, 15);
            int i1 = Math.max(Mth.floor(y - verticalRadius) - 1, context.getMinGenY() + 1);
            int j1 = chunk.isUpgrading() ? 0 : 7;
            int k1 = Math.min(Mth.floor(y + verticalRadius) + 1, context.getMinGenY() + context.getGenDepth() - 1 - j1);
            int l1 = Math.max(Mth.floor(z - horizontalRadius) - minZ - 1, 0);
            int i2 = Math.min(Mth.floor(z + horizontalRadius) - minZ, 15);
            if (this.hasDisallowedLiquid(chunk, k, l, i1, k1, l1, i2)) {
                return false;
            }
            else {
                boolean flag = false;
                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
                BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

                for(int j2 = k; j2 <= l; ++j2) {
                    int k2 = chunkPos.getBlockX(j2);
                    double d3 = ((double)k2 + 0.5D - x) / horizontalRadius;

                    for(int l2 = l1; l2 <= i2; ++l2) {
                        int i3 = chunkPos.getBlockZ(l2);
                        double d4 = ((double)i3 + 0.5D - z) / horizontalRadius;
                        if (!(d3 * d3 + d4 * d4 >= 1.0D)) {
                            MutableBoolean mutableboolean = new MutableBoolean(false);

                            for(int j3 = k1; j3 > i1; --j3) {
                                double d5 = ((double)j3 - 0.5D - y) / verticalRadius;
                                if (!skipChecker.shouldSkip(context, d3, d5, d4, j3) && !carvingMask.get(j2, j3, l2)) {
                                    carvingMask.set(j2, j3, l2);
                                    blockpos$mutableblockpos.set(k2, j3, i3);
                                    flag |= this.carveBlock(context, config, chunk, biomeAccessor, carvingMask, blockpos$mutableblockpos, blockpos$mutableblockpos1, aquifer, mutableboolean);
                                }
                            }
                        }
                    }
                }

                return flag;
            }
        }
        else {
            return false;
        }
    }

    @Override
    protected boolean carveBlock(CarvingContext context, CaveCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> biomeAccessor, CarvingMask carvingMask, BlockPos.MutableBlockPos pos, BlockPos.MutableBlockPos checkPos, Aquifer aquifer, MutableBoolean reachedSurface) {
        BlockState chunkState = chunk.getBlockState(pos);
        if (chunkState.is(UGBlocks.DEEPTURF_BLOCK.get()) || chunkState.is(UGBlocks.FROZEN_DEEPTURF_BLOCK.get()) || chunkState.is(UGBlocks.ASHEN_DEEPTURF_BLOCK.get())) {
            reachedSurface.setTrue();
        }

        if (!this.canReplaceBlock(config, chunkState)) {
            return false;
        }
        else {
            BlockState carveState = this.getCarveState(context, config, pos);
            if (carveState == null) {
                return false;
            }
            else {
                chunk.setBlockState(pos, carveState, false);
                if (aquifer.shouldScheduleFluidUpdate() && !carveState.getFluidState().isEmpty()) {
                    chunk.markPosForPostprocessing(pos);
                }

                if (reachedSurface.isTrue()) {
                    checkPos.setWithOffset(pos, Direction.DOWN);
                    if (chunk.getBlockState(checkPos).is(UGBlocks.DEEPSOIL.get())) {
                        context.topMaterial(biomeAccessor, chunk, checkPos, !carveState.getFluidState().isEmpty()).ifPresent((state) -> {
                            chunk.setBlockState(checkPos, state, false);
                            if (!state.getFluidState().isEmpty()) {
                                chunk.markPosForPostprocessing(checkPos);
                            }
                        });
                    }
                }
                return true;
            }
        }
    }

    @Nullable
    private BlockState getCarveState(CarvingContext context, CaveCarverConfiguration config, BlockPos pos) {
        if (pos.getY() <= config.lavaLevel.resolveY(context)) {
            return UGFluids.VIRULENT_MIX_SOURCE.get().defaultFluidState().createLegacyBlock();
        }
        else {
            return CAVE_AIR;
        }
    }

    protected boolean hasDisallowedLiquid(ChunkAccess chunk, int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
        ChunkPos chunkpos = chunk.getPos();
        int minBlockX = chunkpos.getMinBlockX();
        int minBlockZ = chunkpos.getMinBlockZ();
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for(int x = minX; x <= maxX; ++x) {
            for(int z = minZ; z <= maxZ; ++z) {
                for(int y = minY - 1; y <= maxY + 1; ++y) {
                    mutablePos.set(minBlockX + x, y, minBlockZ + z);
                    if (this.liquids.contains(chunk.getFluidState(mutablePos).getType())) {
                        return true;
                    }

                    if (y != maxY + 1 && !isEdge(x, z, minX, maxX, minZ, maxZ)) {
                        y = maxY;
                    }
                }
            }
        }

        return false;
    }

    private static boolean isEdge(int x, int z, int minX, int maxX, int minZ, int maxZ) {
        return x == minX || x == maxX || z == minZ || z == maxZ;
    }
}