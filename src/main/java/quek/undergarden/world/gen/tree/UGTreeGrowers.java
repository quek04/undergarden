package quek.undergarden.world.gen.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import quek.undergarden.registry.UGConfiguredFeatures;

import java.util.Optional;

public class UGTreeGrowers {
	public static final TreeGrower SMOGSTEM = new TreeGrower(
		"smogstem",
		Optional.of(UGConfiguredFeatures.WIDE_SMOGSTEM_TREE),
		Optional.of(UGConfiguredFeatures.SMOGSTEM_TREE),
		Optional.empty()
	);
	public static final TreeGrower WIGGLEWOOD = new TreeGrower(
		"wigglewood",
		Optional.empty(),
		Optional.of(UGConfiguredFeatures.WIGGLEWOOD_TREE),
		Optional.empty()
	);
	public static final TreeGrower GRONGLE = new TreeGrower(
		"grongle",
		Optional.of(UGConfiguredFeatures.GRONGLE_TREE),
		Optional.of(UGConfiguredFeatures.SMALL_GRONGLE_TREE),
		Optional.empty()
	);
}
