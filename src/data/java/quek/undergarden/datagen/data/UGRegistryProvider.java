package quek.undergarden.datagen.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import quek.undergarden.UGRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.*;
import quek.undergarden.registry.custom.UGStonebornTradeSets;
import quek.undergarden.registry.custom.UGStonebornTrades;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class UGRegistryProvider extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
		.add(Registries.BIOME, UGBiomes::bootstrap)
		.add(Registries.CONFIGURED_FEATURE, UGConfiguredFeatures::bootstrap)
		.add(Registries.CONFIGURED_CARVER, UGConfiguredCarvers::bootstrap)
		.add(Registries.DIMENSION_TYPE, UGDimensions::bootstrapType)
		.add(Registries.LEVEL_STEM, UGDimensions::bootstrapStem)
		.add(Registries.NOISE_SETTINGS, UGDimensions::bootstrapNoise)
		.add(Registries.PLACED_FEATURE, UGPlacedFeatures::bootstrap)
		.add(Registries.PROCESSOR_LIST, UGStructures::bootstrapProcessors)
		.add(Registries.STRUCTURE, UGStructures::bootstrapStructures)
		.add(Registries.STRUCTURE_SET, UGStructures::bootstrapSets)
		.add(Registries.TEMPLATE_POOL, UGStructures::bootstrapPools)
		.add(Registries.DAMAGE_TYPE, UGDamageSources::bootstrap)
		.add(Registries.TRIM_MATERIAL, UGTrimMaterials::bootstrap)
		.add(Registries.ENCHANTMENT, UGEnchantments::bootstrap)
		.add(Registries.JUKEBOX_SONG, UGJukeboxSongs::bootstrap)
		.add(UGRegistries.Keys.STONEBORN_TRADE, UGStonebornTrades::bootstrap)
		.add(UGRegistries.Keys.STONEBORN_TRADE_SET, UGStonebornTradeSets::bootstrap);

	public UGRegistryProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
		super(output, future, BUILDER, Set.of("minecraft", Undergarden.MODID));
	}
}
