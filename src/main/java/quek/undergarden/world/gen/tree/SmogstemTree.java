package quek.undergarden.world.gen.tree;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import quek.undergarden.registry.UGConfiguredFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class SmogstemTree extends AbstractMegaTreeGrower {

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean hive) {
        return UGConfiguredFeatures.SMOGSTEM_TREE.getHolder().get();
    }

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredMegaFeature(Random pRandom) {
        return UGConfiguredFeatures.WIDE_SMOGSTEM_TREE.getHolder().get();
    }
}