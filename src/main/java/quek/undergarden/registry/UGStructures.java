package quek.undergarden.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
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

    private static <F extends StructureFeature<?>> void setupStructure(F structure, StructureFeatureConfiguration structureSeparationSettings, boolean transformSurroundingLand) {
        StructureFeature.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

        if(transformSurroundingLand) {
            StructureFeature.NOISE_AFFECTING_FEATURES =
                    ImmutableList.<StructureFeature<?>>builder()
                            .addAll(StructureFeature.NOISE_AFFECTING_FEATURES)
                            .add(structure)
                            .build();
        }

        StructureSettings.DEFAULTS =
                ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
                        .putAll(StructureSettings.DEFAULTS)
                        .put(structure, structureSeparationSettings)
                        .build();
    }

    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerLevel level) {
            ChunkGenerator chunkGenerator = level.getChunkSource().getGenerator();
            if (chunkGenerator instanceof FlatLevelSource && level.dimension().equals(Level.OVERWORLD)) {
                return;
            }
            StructureSettings worldStructureConfig = chunkGenerator.getSettings();
            Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(worldStructureConfig.structureConfig());
            tempMap.putIfAbsent(CATACOMBS.get(), StructureSettings.DEFAULTS.get(CATACOMBS.get()));
            worldStructureConfig.structureConfig = tempMap;
        }
    }
}