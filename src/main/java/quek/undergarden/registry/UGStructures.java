package quek.undergarden.registry;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.structure.CatacombsStructure;

import java.util.HashMap;
import java.util.Map;

public class UGStructures {

    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Undergarden.MODID);

    public static final RegistryObject<StructureFeature<JigsawConfiguration>> CATACOMBS = STRUCTURES.register("catacombs", () -> new CatacombsStructure(JigsawConfiguration.CODEC));

    public static void registerStructures() {
        setupStructure(CATACOMBS.get(), new StructureFeatureConfiguration(24, 8, 276320045), true);
    }

    private static <F extends StructureFeature<?>> void setupStructure(F structure, StructureFeatureConfiguration structureConfig, boolean transformSurroundingLand) {
        StructureFeature.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

        if(transformSurroundingLand) {
            StructureFeature.NOISE_AFFECTING_FEATURES = ImmutableList.<StructureFeature<?>>builder()
                            .addAll(StructureFeature.NOISE_AFFECTING_FEATURES)
                            .add(structure)
                            .build();
        }

        StructureSettings.DEFAULTS = ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
                        .putAll(StructureSettings.DEFAULTS)
                        .put(structure, structureConfig)
                        .build();

        BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<StructureFeature<?>, StructureFeatureConfiguration> structureMap = settings.getValue().structureSettings().structureConfig();

            if(structureMap instanceof ImmutableMap) {
                Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureConfig);
                settings.getValue().structureSettings().structureConfig = tempMap;
            }
            else {
                structureMap.put(structure, structureConfig);
            }
        });
    }

    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerLevel level) {
            ChunkGenerator chunkGenerator = level.getChunkSource().getGenerator();
            if (chunkGenerator instanceof FlatLevelSource && level.dimension().equals(Level.OVERWORLD)) {
                return;
            }

            StructureSettings structureSettings = chunkGenerator.getSettings();

            HashMap<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> ugStructureToMultiMap = new HashMap<>();

            for(Map.Entry<ResourceKey<Biome>, Biome> biomeEntry : level.registryAccess().ownedRegistryOrThrow(Registry.BIOME_REGISTRY).entrySet()) {
                if(biomeEntry.getKey().equals(UGBiomes.ANCIENT_SEA)) {
                    associateBiomeToConfiguredStructure(ugStructureToMultiMap, UGConfiguredStructures.CATACOMBS, biomeEntry.getKey());
                }
            }

            ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> tempStructureToMultiMap = ImmutableMap.builder();
            structureSettings.configuredStructures.entrySet().stream().filter(entry -> !ugStructureToMultiMap.containsKey(entry.getKey())).forEach(tempStructureToMultiMap::put);

            ugStructureToMultiMap.forEach((key, value) -> tempStructureToMultiMap.put(key, ImmutableMultimap.copyOf(value)));

            structureSettings.configuredStructures = tempStructureToMultiMap.build();

            Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureSettings.structureConfig());
            tempMap.putIfAbsent(UGStructures.CATACOMBS.get(), StructureSettings.DEFAULTS.get(UGStructures.CATACOMBS.get()));
            structureSettings.structureConfig = tempMap;
        }
    }

    private static void associateBiomeToConfiguredStructure(Map<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> ugStructureToMultiMap, ConfiguredStructureFeature<?, ?> configuredStructureFeature, ResourceKey<Biome> biomeRegistryKey) {
        ugStructureToMultiMap.putIfAbsent(configuredStructureFeature.feature, HashMultimap.create());
        HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> configuredStructureToBiomeMultiMap = ugStructureToMultiMap.get(configuredStructureFeature.feature);
        if(configuredStructureToBiomeMultiMap.containsValue(biomeRegistryKey)) {
            Undergarden.LOGGER.error("""
                    Detected 2 ConfiguredStructureFeatures that share the same base StructureFeature trying to be added to same biome. One will be prevented from spawning.
                    This issue happens with vanilla too and is why a Snowy Village and Plains Village cannot spawn in the same biome because they both use the Village base structure.
                    The two conflicting ConfiguredStructures are: {}, {}
                    The biome that is attempting to be shared: {}
                """,
                    BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureFeature),
                    BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureToBiomeMultiMap.entries().stream().filter(e -> e.getValue() == biomeRegistryKey).findFirst().get().getKey()),
                    biomeRegistryKey
            );
        }
        else {
            configuredStructureToBiomeMultiMap.put(configuredStructureFeature, biomeRegistryKey);
        }
    }
}