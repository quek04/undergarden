package quek.undergarden.world.gen.foliageplacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import quek.undergarden.registry.UGFoliagePlacers;

import java.util.function.BiConsumer;

public class ConeFoliagePlacer extends FoliagePlacer {

    public static final Codec<ConeFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> foliagePlacerParts(instance).apply(instance, ConeFoliagePlacer::new));

    public ConeFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return UGFoliagePlacers.CONE.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int height, int radius, int offset) {
        tryPlaceLeaf(level, blockSetter, random, config, attachment.pos());
        circle(-1, 1, level, blockSetter, random, config, attachment.pos());
        circle(-2, 1, level, blockSetter, random, config, attachment.pos());
        circle(-3, 2, level, blockSetter, random, config, attachment.pos());
        circle(-4, 3, level, blockSetter, random, config, attachment.pos());
    }

    private void circle(final int centerY, final int radius, LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, TreeConfiguration config, BlockPos pos) {
        int d = (5 - radius * 4)/4;
        int x = 0;
        int z = radius;
        BlockPos.MutableBlockPos posMutable = new BlockPos.MutableBlockPos();

        do {
            posMutable.setWithOffset(pos, x + 1, centerY, z);
            tryPlaceLeaf(level, blockSetter, random, config, posMutable);

            posMutable.setWithOffset(pos, x, centerY, z);
            tryPlaceLeaf(level, blockSetter, random, config, posMutable);

            posMutable.setWithOffset(pos, x + 1, centerY, -z);
            tryPlaceLeaf(level, blockSetter, random, config, posMutable);

            posMutable.setWithOffset(pos, x, centerY, -z);
            tryPlaceLeaf(level, blockSetter, random, config, posMutable);

            posMutable.setWithOffset(pos, -x - 1, centerY, z);
            tryPlaceLeaf(level, blockSetter, random, config, posMutable);

            posMutable.setWithOffset(pos, -x, centerY, z);
            tryPlaceLeaf(level, blockSetter, random, config, posMutable);

            posMutable.setWithOffset(pos, -x - 1, centerY, -z);
            tryPlaceLeaf(level, blockSetter, random, config, posMutable);

            posMutable.setWithOffset(pos, -x, centerY, -z);
            tryPlaceLeaf(level, blockSetter, random, config, posMutable);


            posMutable.setWithOffset(pos, z, centerY, x);
            tryPlaceLeaf(level, blockSetter, random, config, posMutable);

            posMutable.setWithOffset(pos, z, centerY, -x);
            tryPlaceLeaf(level, blockSetter, random, config, posMutable);

            posMutable.setWithOffset(pos, -z, centerY, x);
            tryPlaceLeaf(level, blockSetter, random, config, posMutable);

            posMutable.setWithOffset(pos, -z, centerY, -x);
            tryPlaceLeaf(level, blockSetter, random, config, posMutable);

            if (d < 0) {
                d += 2 * x + 1;
            } else {
                d += 2 * (x - z) + 1;
                z--;
            }
            x++;
        } while (x <= z);

    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return 7;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return false;
    }
}
