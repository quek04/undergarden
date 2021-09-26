package quek.undergarden.world.gen.trunkplacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGTrunkPlacerTypes;

import java.util.List;
import java.util.Random;
import java.util.Set;

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
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedRW world, Random rand, int y, BlockPos pos, Set<BlockPos> posSet, BoundingBox boundingBox, TreeConfiguration config) {
        BlockPos blockpos = pos.below();
        TreeFeature.setBlockKnownShape(world, blockpos, UGBlocks.DEEPSOIL.get().defaultBlockState());
        TreeFeature.setBlockKnownShape(world, blockpos.east(), UGBlocks.DEEPSOIL.get().defaultBlockState());
        TreeFeature.setBlockKnownShape(world, blockpos.south(), UGBlocks.DEEPSOIL.get().defaultBlockState());
        TreeFeature.setBlockKnownShape(world, blockpos.south().east(), UGBlocks.DEEPSOIL.get().defaultBlockState());
        return super.placeTrunk(world, rand, y, pos, posSet, boundingBox, config);
    }
}
