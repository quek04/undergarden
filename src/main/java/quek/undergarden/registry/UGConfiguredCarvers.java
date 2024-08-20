package quek.undergarden.registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import quek.undergarden.Undergarden;

public class UGConfiguredCarvers {

	public static final ResourceKey<ConfiguredWorldCarver<?>> UNDERGARDEN_CAVE = ResourceKey.create(Registries.CONFIGURED_CARVER, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "undergarden_cave"));

	public static void bootstrap(BootstrapContext<ConfiguredWorldCarver<?>> context) {
		HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
		context.register(UNDERGARDEN_CAVE, UGCarvers.UNDERGARDEN_CAVE.get().configured(
			new CaveCarverConfiguration(
				0.5F,
				UniformHeight.of(VerticalAnchor.aboveBottom(5), VerticalAnchor.belowTop(5)), //y
				ConstantFloat.of(0.5F), //y scale
				VerticalAnchor.absolute(11), //liquid level
				blocks.getOrThrow(UGTags.Blocks.UNDERGARDEN_CARVER_REPLACEABLES), //replaceable blocks
				UniformFloat.of(0.7F, 1.4F), //horizontal radius multiplier
				UniformFloat.of(0.8F, 1.3F), //vertical radius multiplier
				UniformFloat.of(-1.0F, -0.4F) //floor level
			)
		));
	}
}