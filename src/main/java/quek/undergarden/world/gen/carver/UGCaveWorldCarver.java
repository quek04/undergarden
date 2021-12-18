package quek.undergarden.world.gen.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.material.Fluids;
import org.apache.commons.lang3.mutable.MutableBoolean;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGFluids;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Function;

public class UGCaveWorldCarver extends CaveWorldCarver {

    public UGCaveWorldCarver(Codec<CaveCarverConfiguration> configCodec) {
        super(configCodec);
        this.replaceableBlocks = ImmutableSet.of(
                UGBlocks.DEPTHROCK.get(),
                UGBlocks.SHIVERSTONE.get(),
                UGBlocks.DEEPTURF_BLOCK.get(),
                UGBlocks.ASHEN_DEEPTURF_BLOCK.get(),
                UGBlocks.FROZEN_DEEPTURF_BLOCK.get(),
                UGBlocks.DEEPSOIL.get(),
                UGBlocks.DEPTHROCK_COAL_ORE.get(),
                UGBlocks.DEPTHROCK_IRON_ORE.get(),
                UGBlocks.DEPTHROCK_GOLD_ORE.get(),
                UGBlocks.DEPTHROCK_DIAMOND_ORE.get(),
                UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get(),
                UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get(),
                UGBlocks.DEPTHROCK_UTHERIUM_ORE.get(),
                UGBlocks.DEPTHROCK_REGALIUM_ORE.get(),
                UGBlocks.SEDIMENT.get()
        );
        this.liquids = ImmutableSet.of(
                Fluids.WATER,
                UGFluids.VIRULENT_MIX_SOURCE.get()
        );
    }

    @Override
    protected float getThickness(Random random) {
        return super.getThickness(random) * 3;
    }

    @Override
    protected boolean carveBlock(CarvingContext context, CaveCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Biome> biomeAccessor, CarvingMask carvingMask, BlockPos.MutableBlockPos pos, BlockPos.MutableBlockPos checkPos, Aquifer aquifer, MutableBoolean reachedSurface) {
        BlockState blockstate = chunk.getBlockState(pos);
        if (blockstate.is(UGBlocks.DEEPTURF_BLOCK.get()) || blockstate.is(UGBlocks.FROZEN_DEEPTURF_BLOCK.get()) || blockstate.is(UGBlocks.ASHEN_DEEPTURF_BLOCK.get())) {
            reachedSurface.setTrue();
        }

        if (!this.canReplaceBlock(blockstate)) {
            return false;
        }
        else {
            BlockState carveState = this.getCarveState(context, config, pos, aquifer);
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
    private BlockState getCarveState(CarvingContext context, CaveCarverConfiguration config, BlockPos pos, Aquifer aquifer) {
        if (pos.getY() <= config.lavaLevel.resolveY(context)) {
            return UGFluids.VIRULENT_MIX_SOURCE.get().defaultFluidState().createLegacyBlock();
        }
        else {
            return aquifer.computeSubstance(pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D);
        }
    }
}