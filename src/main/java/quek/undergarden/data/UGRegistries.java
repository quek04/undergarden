package quek.undergarden.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.*;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class UGRegistries extends DatapackBuiltinEntriesProvider {

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
			.add(Registries.DAMAGE_TYPE, UGDamageSources::bootstrap);

	private UGRegistries(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
		super(output, future, BUILDER, Set.of("minecraft", Undergarden.MODID));
	}

	public static void addProviders(boolean isServer, DataGenerator generator, PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
		generator.addProvider(isServer, new UGRegistries(output, provider));
		generator.addProvider(isServer, new UGBiomeTags(output, provider.thenApply(r -> append(r, BUILDER)), helper));
		generator.addProvider(isServer, new UGDamageTypeTags(output, provider.thenApply(r -> append(r, BUILDER)), helper));
	}

	private static HolderLookup.Provider append(HolderLookup.Provider original, RegistrySetBuilder builder) {
		return builder.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), original);
	}
}
