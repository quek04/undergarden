package quek.undergarden.world.gen.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
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
import java.util.BitSet;
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
    protected boolean carveBlock(CarvingContext context, CaveCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Biome> biomeAccessor, BitSet carvingMask, Random random, BlockPos.MutableBlockPos pos, BlockPos.MutableBlockPos checkPos, Aquifer aquifer, MutableBoolean reachedSurface) {
        BlockState blockstate = chunk.getBlockState(pos);
        BlockState blockstate1 = chunk.getBlockState(checkPos.setWithOffset(pos, Direction.UP));
        if (blockstate.is(UGBlocks.DEEPTURF_BLOCK.get()) || blockstate.is(UGBlocks.FROZEN_DEEPTURF_BLOCK.get()) || blockstate.is(UGBlocks.ASHEN_DEEPTURF_BLOCK.get())) {
            reachedSurface.setTrue();
        }

        if (!this.canReplaceBlock(blockstate, blockstate1) && !config.debugSettings.isDebugMode()) {
            return false;
        }
        else {
            BlockState blockstate2 = this.getCarveState(context, config, pos);
            if (blockstate2 == null) {
                return false;
            }
            else {
                chunk.setBlockState(pos, blockstate2, false);
                if (reachedSurface.isTrue()) {
                    checkPos.setWithOffset(pos, Direction.DOWN);
                    if (chunk.getBlockState(checkPos).is(UGBlocks.DEEPSOIL.get())) {
                        chunk.setBlockState(checkPos, biomeAccessor.apply(pos).getGenerationSettings().getSurfaceBuilderConfig().getTopMaterial(), false);
                    }
                }

                return true;
            }
        }
    }

    @Nullable
    private BlockState getCarveState(CarvingContext pContext, CaveCarverConfiguration pConfig, BlockPos pPos) {
        if (pPos.getY() <= pConfig.lavaLevel.resolveY(pContext)) {
            return UGFluids.VIRULENT_MIX_SOURCE.get().defaultFluidState().createLegacyBlock();
        }
        else {
            return AIR;
        }
    }
}