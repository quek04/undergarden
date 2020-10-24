package quek.undergarden.world.gen.tree;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BushFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class WigglewoodTree extends Tree {

    @Nullable
    @Override
    public ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return UndergardenFeatures.undergarden_tree.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UndergardenBlocks.wigglewood_log.get().getDefaultState()),
                        new SimpleBlockStateProvider(UndergardenBlocks.wigglewood_leaves.get().getDefaultState()),
                        new BushFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 0),
                        new ForkyTrunkPlacer(3, 0, 0),
                        new TwoLayerFeature(1, 0, 2)))
                        .setIgnoreVines().build());
    }
}
