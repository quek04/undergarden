package quek.undergarden.world.gen.trunkplacer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import quek.undergarden.registry.UGTrunkPlacerTypes;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

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
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, Random random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
        BlockGetter blockGetter = (BlockGetter) level;
        int treeBaseHeight = config.trunkPlacer.getTreeHeight(random);
        int j = treeBaseHeight / 8 + random.nextInt(2);

        for (int y = 0; y < treeBaseHeight; ++y) {
            float thiccness = (1.0F - (float) y / (float) treeBaseHeight)*j;
            int l = Mth.ceil(treeBaseHeight);

            for (int i1 = -l; i1 <= l; ++i1) {
                float f1 = (float) Mth.abs(i1) - 0.25F;

                for (int j1 = -l; j1 <= l; ++j1) {
                    float f2 = (float) Mth.abs(j1) - 0.25F;
                    if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > thiccness * thiccness)) && (i1 != -l && i1 != l && j1 != -l && j1 != l || !(random.nextFloat() > 0.75F))) {
                        BlockState blockstate = blockGetter.getBlockState(pos.offset(i1, y, j1));
                        if (blockstate.isAir()) {
                            placeLog(level, blockSetter, random, pos.offset(i1, y, j1), config);
                        }

                        if (y != 0 && l > 1) {
                            blockstate = blockGetter.getBlockState(pos.offset(i1, -y, j1));
                            if (blockstate.isAir()) {
                                placeLog(level, blockSetter, random, pos.offset(i1, y, j1), config);
                            }
                        }
                    }
                }
            }
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(treeBaseHeight), 0, false));
    }
}