package quek.undergarden.world.gen.tree;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import quek.undergarden.registry.UGConfiguredFeatures;

import javax.annotation.Nullable;

public class GrongleTree extends AbstractMegaTreeGrower {

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random) {
        return UGConfiguredFeatures.GRONGLE_TREE.getHolder().get();
    }

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hive) {
        return UGConfiguredFeatures.SMALL_GRONGLE_TREE.getHolder().get();
    }
}