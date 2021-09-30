package quek.undergarden.world.gen.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import org.apache.commons.lang3.mutable.MutableBoolean;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGFluids;

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
                Blocks.ICE
        );
        this.liquids = ImmutableSet.of(
                Fluids.WATER,
                UGFluids.VIRULENT_MIX_SOURCE.get()
        );
    }

    @Override
    protected float getThickness(Random pRandom) {
        float f = pRandom.nextFloat() * 2.0F + pRandom.nextFloat();
        if (pRandom.nextInt(10) == 0) {
            f *= pRandom.nextFloat() * pRandom.nextFloat() * 3.0F + 1.0F;
        }

        return f * 3;
    }

    @Override
    protected boolean carveBlock(CarvingContext pContext, CaveCarverConfiguration pConfig, ChunkAccess pChunk, Function<BlockPos, Biome> pBiomeAccessor, BitSet pCarvingMask, Random pRandom, BlockPos.MutableBlockPos pPos, BlockPos.MutableBlockPos pCheckPos, Aquifer pAquifer, MutableBoolean pReachedSurface) {
        if(this.canReplaceBlock(pChunk.getBlockState(pPos))) {
            BlockState blockState;
            if(pPos.getY() <= pContext.getMinGenY()) {
                blockState = UGFluids.VIRULENT_MIX_SOURCE.get().defaultFluidState().createLegacyBlock();
            }
            else {
                blockState = CAVE_AIR;
            }

            pChunk.setBlockState(pPos, blockState, false);
            return true;
        }
        else {
            return false;
        }
    }


}
