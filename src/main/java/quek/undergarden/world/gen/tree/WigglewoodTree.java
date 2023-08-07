package quek.undergarden.world.gen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import quek.undergarden.registry.UGConfiguredFeatures;

import javax.annotation.Nullable;

public class WigglewoodTree extends AbstractTreeGrower {

	@Nullable
	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hive) {
		return random.nextInt(10) == 0 ? UGConfiguredFeatures.TALL_WIGGLEWOOD_TREE : UGConfiguredFeatures.WIGGLEWOOD_TREE;
	}
}