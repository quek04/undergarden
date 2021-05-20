package quek.undergarden.world.gen.trunkplacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.trunkplacer.MegaJungleTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
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
    public List<FoliagePlacer.Foliage> placeTrunk(IWorldGenerationReader world, Random rand, int y, BlockPos pos, Set<BlockPos> posSet, MutableBoundingBox boundingBox, BaseTreeFeatureConfig config) {
        BlockPos blockpos = pos.below();
        TreeFeature.setBlockKnownShape(world, blockpos, UGBlocks.DEEPSOIL.get().defaultBlockState());
        TreeFeature.setBlockKnownShape(world, blockpos.east(), UGBlocks.DEEPSOIL.get().defaultBlockState());
        TreeFeature.setBlockKnownShape(world, blockpos.south(), UGBlocks.DEEPSOIL.get().defaultBlockState());
        TreeFeature.setBlockKnownShape(world, blockpos.south().east(), UGBlocks.DEEPSOIL.get().defaultBlockState());
        return super.placeTrunk(world, rand, y, pos, posSet, boundingBox, config);
    }
}
