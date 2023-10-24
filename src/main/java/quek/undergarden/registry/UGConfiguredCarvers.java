package quek.undergarden.registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import quek.undergarden.Undergarden;

@SuppressWarnings("unused")
public class UGConfiguredCarvers {

	public static final ResourceKey<ConfiguredWorldCarver<?>> UNDERGARDEN_CAVE = ResourceKey.create(Registries.CONFIGURED_CARVER, new ResourceLocation(Undergarden.MODID, "undergarden_cave"));

	public static void bootstrap(BootstapContext<ConfiguredWorldCarver<?>> context) {
		HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
		context.register(UNDERGARDEN_CAVE, UGCarvers.UNDERGARDEN_CAVE.get().configured(new CaveCarverConfiguration(0.5F, UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.belowTop(5)), ConstantFloat.of(0.5F), VerticalAnchor.absolute(11), BuiltInRegistries.BLOCK.getOrCreateTag(UGTags.Blocks.UNDERGARDEN_CARVER_REPLACEABLES), ConstantFloat.of(1.0F), ConstantFloat.of(1.0F), ConstantFloat.of(-0.7F))));
	}
}