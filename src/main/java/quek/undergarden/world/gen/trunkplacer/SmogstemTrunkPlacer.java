package quek.undergarden.world.gen.trunkplacer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import quek.undergarden.registry.UGTrunkPlacerTypes;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class SmogstemTrunkPlacer extends TrunkPlacer {

    public static final Codec<SmogstemTrunkPlacer> CODEC = RecordCodecBuilder.create((me) ->
            trunkPlacerParts(me).apply(me, SmogstemTrunkPlacer::new));

    public SmogstemTrunkPlacer(int baseHeight, int firstRandHeight, int secondRandHeight) {
        super(baseHeight, firstRandHeight, secondRandHeight);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return UGTrunkPlacerTypes.SMOGSTEM_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedRW world, Random rand, int yMaybe, BlockPos pos, Set<BlockPos> posSet, BoundingBox boundingBox, TreeConfiguration config) {
        BlockGetter reader = (BlockGetter) world;
        int treeBaseHeight = config.trunkPlacer.getTreeHeight(rand);
        int j = treeBaseHeight / 8 + rand.nextInt(2);

        for (int k = 0; k < treeBaseHeight; ++k) {
            float thiccness = (1.0F - (float) k / (float) treeBaseHeight)*j;
            int l = Mth.ceil(treeBaseHeight);

            for (int i1 = -l; i1 <= l; ++i1) {
                float f1 = (float) Mth.abs(i1) - 0.25F;

                for (int j1 = -l; j1 <= l; ++j1) {
                    float f2 = (float) Mth.abs(j1) - 0.25F;
                    if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > thiccness * thiccness)) && (i1 != -l && i1 != l && j1 != -l && j1 != l || !(rand.nextFloat() > 0.75F))) {
                        BlockState blockstate = reader.getBlockState(pos.offset(i1, k, j1));
                        if (blockstate.isAir((BlockGetter) world, pos)) {
                            placeLog(world, rand, pos.offset(i1, k, j1), posSet, boundingBox, config);
                        }

                        if (k != 0 && l > 1) {
                            blockstate = reader.getBlockState(pos.offset(i1, -k, j1));
                            if (blockstate.isAir((BlockGetter) world, pos)) {
                                placeLog(world, rand, pos.offset(i1, k, j1), posSet, boundingBox, config);
                            }
                        }
                    }
                }
            }
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(treeBaseHeight), 0, false));
    }
}
