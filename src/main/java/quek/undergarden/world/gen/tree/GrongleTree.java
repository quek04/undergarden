package quek.undergarden.world.gen.tree;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import quek.undergarden.registry.UGConfiguredFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class GrongleTree extends AbstractMegaTreeGrower {

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredMegaFeature(Random random) {
        return UGConfiguredFeatures.GRONGLE_TREE.getHolder().get();
    }

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean hive) {
        return UGConfiguredFeatures.SMALL_GRONGLE_TREE.getHolder().get();
    }
}