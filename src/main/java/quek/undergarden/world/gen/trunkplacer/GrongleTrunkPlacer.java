package quek.undergarden.world.gen.trunkplacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGTrunkPlacerTypes;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class GrongleTrunkPlacer extends MegaJungleTrunkPlacer {

    public static final Codec<GrongleTrunkPlacer> CODEC = RecordCodecBuilder.create((me) ->
            trunkPlacerParts(me).apply(me, GrongleTrunkPlacer::new));

    public GrongleTrunkPlacer(int baseHeight, int firstRandHeight, int secondRandHeight) {
        super(baseHeight, firstRandHeight, secondRandHeight);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return UGTrunkPlacerTypes.GRONGLE_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, Random random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
        BlockPos pos2 = pos.below();
        BlockState deepsoil = UGBlocks.DEEPSOIL.get().defaultBlockState();
        blockSetter.accept(pos2, deepsoil);
        blockSetter.accept(pos2.east(), deepsoil);
        blockSetter.accept(pos2.south(), deepsoil);
        blockSetter.accept(pos2.south().east(), deepsoil);
        return super.placeTrunk(level, blockSetter, random, freeTreeHeight, pos, config);
    }
}