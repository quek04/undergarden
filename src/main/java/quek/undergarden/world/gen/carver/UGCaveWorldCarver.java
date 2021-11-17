package quek.undergarden.world.gen.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.BaseStoneSource;
import net.minecraft.world.level.levelgen.SingleBaseStoneSource;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
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
                UGBlocks.COAL_ORE.get(),
                UGBlocks.IRON_ORE.get(),
                UGBlocks.GOLD_ORE.get(),
                UGBlocks.DIAMOND_ORE.get(),
                UGBlocks.CLOGGRUM_ORE.get(),
                UGBlocks.FROSTSTEEL_ORE.get(),
                UGBlocks.UTHERIUM_ORE.get(),
                UGBlocks.REGALIUM_ORE.get(),
                UGBlocks.SEDIMENT.get()
        );
        this.liquids = ImmutableSet.of(
                Fluids.WATER,
                UGFluids.VIRULENT_MIX_SOURCE.get()
        );
    }

    @Override
    protected float getThickness(Random pRandom) {
        return super.getThickness(pRandom) * 3;
    }

    protected boolean carveBlock(CarvingContext pContext, CaveCarverConfiguration pConfig, ChunkAccess pChunk, Function<BlockPos, Biome> pBiomeAccessor, BitSet pCarvingMask, Random pRandom, BlockPos.MutableBlockPos pPos, BlockPos.MutableBlockPos pCheckPos, Aquifer pAquifer, MutableBoolean pReachedSurface) {
        BlockState blockstate = pChunk.getBlockState(pPos);
        BlockState blockstate1 = pChunk.getBlockState(pCheckPos.setWithOffset(pPos, Direction.UP));
        if (blockstate.is(UGBlocks.DEEPTURF_BLOCK.get()) || blockstate.is(UGBlocks.FROZEN_DEEPTURF_BLOCK.get()) || blockstate.is(UGBlocks.ASHEN_DEEPTURF_BLOCK.get())) {
            pReachedSurface.setTrue();
        }

        if (!this.canReplaceBlock(blockstate, blockstate1) && !pConfig.debugSettings.isDebugMode()) {
            return false;
        }
        else {
            BlockState blockstate2 = this.getCarveState(pContext, pConfig, pPos, pAquifer);
            if (blockstate2 == null) {
                return false;
            }
            else {
                pChunk.setBlockState(pPos, blockstate2, false);
                if (pReachedSurface.isTrue()) {
                    pCheckPos.setWithOffset(pPos, Direction.DOWN);
                    if (pChunk.getBlockState(pCheckPos).is(UGBlocks.DEEPSOIL.get())) {
                        pChunk.setBlockState(pCheckPos, pBiomeAccessor.apply(pPos).getGenerationSettings().getSurfaceBuilderConfig().getTopMaterial(), false);
                    }
                }

                return true;
            }
        }
    }

    @Nullable
    private BlockState getCarveState(CarvingContext pContext, CaveCarverConfiguration pConfig, BlockPos pPos, Aquifer pAquifer) {
        if (pPos.getY() <= pConfig.lavaLevel.resolveY(pContext)) {
            return UGFluids.VIRULENT_MIX_SOURCE.get().defaultFluidState().createLegacyBlock();
        }
        else {
            return AIR;
        }
    }
}