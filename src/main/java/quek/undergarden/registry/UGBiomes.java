package quek.undergarden.registry;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.attribute.*;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import quek.undergarden.Undergarden;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public class UGBiomes {

	public static final BiFunction<Holder<SoundEvent>, Holder<SoundEvent>, AmbientSounds> DEFAULT_AMBIENCE = (ambience, addition) -> new AmbientSounds(Optional.of(ambience), Optional.of(new AmbientMoodSettings(UGSoundEvents.MOOD, 6000, 8, 2)), List.of(new AmbientAdditionsSettings(addition, 0.00555D)));
	public static final BiFunction<Holder<SoundEvent>, Holder<SoundEvent>, AmbientSounds> FROST_AMBIENCE = (ambience, addition) -> new AmbientSounds(Optional.of(ambience), Optional.of(new AmbientMoodSettings(UGSoundEvents.FROST_MOOD, 6000, 8, 2)), List.of(new AmbientAdditionsSettings(addition, 0.002775D)));

	public static final ResourceKey<Biome> ANCIENT_SEA = create("ancient_sea");
	public static final ResourceKey<Biome> BARREN_ABYSS = create("barren_abyss");
	public static final ResourceKey<Biome> BLOOD_MUSHROOM_BOG = create("blood_mushroom_bog");
	public static final ResourceKey<Biome> DEAD_SEA = create("dead_sea");
	public static final ResourceKey<Biome> DENSE_FOREST = create("dense_forest");
	public static final ResourceKey<Biome> FORGOTTEN_FIELD = create("forgotten_field");
	public static final ResourceKey<Biome> FROSTFIELDS = create("frostfields");
	public static final ResourceKey<Biome> FROSTY_SMOGSTEM_FOREST = create("frosty_smogstem_forest");
	public static final ResourceKey<Biome> GRONGLEGROWTH = create("gronglegrowth");
	public static final ResourceKey<Biome> ICY_SEA = create("icy_sea");
	public static final ResourceKey<Biome> INDIGO_MUSHROOM_BOG = create("indigo_mushroom_bog");
	public static final ResourceKey<Biome> INK_MUSHROOM_BOG = create("ink_mushroom_bog");
	public static final ResourceKey<Biome> SMOGSTEM_FOREST = create("smogstem_forest");
	public static final ResourceKey<Biome> SMOG_SPIRES = create("smog_spires");
	public static final ResourceKey<Biome> VEIL_MUSHROOM_BOG = create("veil_mushroom_bog");
	public static final ResourceKey<Biome> WIGGLEWOOD_FOREST = create("wigglewood_forest");
	public static final ResourceKey<Biome> DEPTHS = create("depths");
	public static final ResourceKey<Biome> INFECTED_DEPTHS = create("infected_depths");
	public static final ResourceKey<Biome> PUFF_MUSHROOM_FOREST = create("puff_mushroom_forest");
	public static final ResourceKey<Biome> ROGDORIUM_GROVE = create("rogdorium_grove");

	private static ResourceKey<Biome> create(String name) {
		return ResourceKey.create(Registries.BIOME, Undergarden.prefix(name));
	}

	public static void bootstrap(BootstrapContext<Biome> context) {
		HolderGetter<PlacedFeature> featureGetter = context.lookup(Registries.PLACED_FEATURE);
		HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);
		context.register(ANCIENT_SEA, new Biome.BiomeBuilder()
			.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEPTHROCK_PEBBLE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DROOPVINE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.GLITTERKELP_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.THORNREED_PATCH)
				.build())
			.mobSpawnSettings(addNormalRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
				.addSpawn(MobCategory.WATER_CREATURE, 10, new MobSpawnSettings.SpawnerData(UGEntityTypes.GWIB.get(), 1, 1))
				.addSpawn(MobCategory.WATER_CREATURE, 10, new MobSpawnSettings.SpawnerData(UGEntityTypes.UNDERGAR.get(), 1, 2))
				.addSpawn(MobCategory.WATER_AMBIENT, 5, new MobSpawnSettings.SpawnerData(UGEntityTypes.GWIBLING.get(), 3, 6))
				.addSpawn(MobCategory.AMBIENT, 5, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 4, 8))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, DEFAULT_AMBIENCE.apply(UGSoundEvents.SEA_AMBIENCE, UGSoundEvents.SEA_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 1186057)
				.build())
			.specialEffects(generateColors(4477507))
			.build());

		context.register(BARREN_ABYSS, new Biome.BiomeBuilder()
			.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEPTHROCK_PEBBLE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TWISTYBUSH_PATCH)
				.build())
			.mobSpawnSettings(addDangerousRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
				.creatureGenerationProbability(0.5F)
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.SMOG_MOG.get(), 2, 4))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.2F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, DEFAULT_AMBIENCE.apply(UGSoundEvents.ABYSS_AMBIENCE, UGSoundEvents.ABYSS_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 2565927)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.ASH, 0.118093334F)))
				.build())
			.specialEffects(generateColors(7568503))
			.build());

		context.register(BLOOD_MUSHROOM_BOG, new Biome.BiomeBuilder()
			.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEPTHROCK_PEBBLE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TALL_DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DROOPVINE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.GLOOMGOURD_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.HUGE_BLOOD_MUSHROOM)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.THORNREED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TWISTYBUSH_PATCH)
				.build())
			.mobSpawnSettings(addNormalRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
				.addSpawn(MobCategory.AMBIENT, 5, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 4, 8))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, DEFAULT_AMBIENCE.apply(UGSoundEvents.ABYSS_AMBIENCE, UGSoundEvents.BOG_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 1248522)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.ASH, 0.118093334F)))
				.build())
			.specialEffects(generateColors(6180396))
			.build());

		context.register(DEAD_SEA, new Biome.BiomeBuilder()
			.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TWISTYBUSH_PATCH)
				.build())
			.mobSpawnSettings(addDangerousRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
				.addSpawn(MobCategory.AMBIENT, 5, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 4, 8))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, DEFAULT_AMBIENCE.apply(UGSoundEvents.ABYSS_AMBIENCE, UGSoundEvents.ABYSS_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 1248522)
				.build())
			.specialEffects(generateColors(7568503))
			.build());

		context.register(DENSE_FOREST, new Biome.BiomeBuilder()
			.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, UGPlacedFeatures.DEPTHROCK_ROCK)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, UGPlacedFeatures.SHIVERSTONE_ROCK)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEPTHROCK_PEBBLE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.UNDERBEAN_BUSH_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TALL_DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TALL_SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DROOPVINE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.GLOOMGOURD_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SMOGSTEM_TREE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.WIGGLEWOOD_TREE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TALL_SMOGSTEM_TREE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TALL_WIGGLEWOOD_TREE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.AMOROUS_BRISTLE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.MISERABELL_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.BUTTERBUNCH_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.THORNREED_PATCH)
				.build())
			.mobSpawnSettings(addNormalRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
				.creatureGenerationProbability(0.5F)
				.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.STONEBORN.get(), 1, 3))
				.addSpawn(MobCategory.AMBIENT, 5, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 4, 8))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.GLOOMPER.get(), 4, 4))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.BRUTE.get(), 4, 4))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.MOG.get(), 4, 4))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.DWELLER.get(), 4, 4))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, DEFAULT_AMBIENCE.apply(UGSoundEvents.UNDERGARDEN_AMBIENCE, UGSoundEvents.DENSE_FOREST_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 1186057)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.025F)))
				.build())
			.specialEffects(generateColors(4224322))
			.build());

		context.register(FORGOTTEN_FIELD, new Biome.BiomeBuilder()
			.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, UGPlacedFeatures.RUINS)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, UGPlacedFeatures.DEPTHROCK_ROCK)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, UGPlacedFeatures.SHIVERSTONE_ROCK)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEPTHROCK_PEBBLE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.UNDERBEAN_BUSH_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TALL_SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DROOPVINE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.GLOOMGOURD_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SMOGSTEM_TREE_SPARSE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.THORNREED_PATCH)
				.build())
			.mobSpawnSettings(addNormalRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
				.creatureGenerationProbability(0.5F)
				.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.STONEBORN.get(), 1, 3))
				.addSpawn(MobCategory.AMBIENT, 5, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 4, 8))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.GLOOMPER.get(), 4, 4))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.BRUTE.get(), 4, 4))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.MOG.get(), 4, 4))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.DWELLER.get(), 4, 4))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, DEFAULT_AMBIENCE.apply(UGSoundEvents.UNDERGARDEN_AMBIENCE, UGSoundEvents.FIELDS_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 1186057)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.025F)))
				.build())
			.specialEffects(generateColors(5993819))
			.build());

		context.register(FROSTFIELDS, new Biome.BiomeBuilder()
			.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, UGPlacedFeatures.DEPTHROCK_ROCK)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, UGPlacedFeatures.ICE_PILLAR)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.ICE_ORE)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.FROSTSTEEL_ORE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TALL_SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.FROZEN_DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.THORNREED_PATCH)
				.build())
			.mobSpawnSettings(addNormalRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
				.creatureGenerationProbability(0.5F)
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.BRUTE.get(), 4, 4))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.14F)
			.temperatureAdjustment(Biome.TemperatureModifier.FROZEN)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, FROST_AMBIENCE.apply(UGSoundEvents.FROST_AMBIENCE, UGSoundEvents.FROSTFIELDS_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 2565927)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.118093334F)))
				.build())
			.specialEffects(generateColors(14609908))
			.build());

		context.register(FROSTY_SMOGSTEM_FOREST, new Biome.BiomeBuilder()
			.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, UGPlacedFeatures.DEPTHROCK_ROCK)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, UGPlacedFeatures.ICE_PILLAR)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.ICE_ORE)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.FROSTSTEEL_ORE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TALL_SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.FROZEN_DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SMOGSTEM_TREE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.WIDE_SMOGSTEM_TREE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.THORNREED_PATCH)
				.build())
			.mobSpawnSettings(addNormalRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
				.creatureGenerationProbability(0.5F)
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.BRUTE.get(), 4, 4))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.14F)
			.temperatureAdjustment(Biome.TemperatureModifier.FROZEN)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, FROST_AMBIENCE.apply(UGSoundEvents.FROST_AMBIENCE, UGSoundEvents.SMOGSTEM_FOREST_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 2565927)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.118093334F)))
				.build())
			.specialEffects(generateColors(14609908))
			.build());

		context.register(GRONGLEGROWTH, new Biome.BiomeBuilder()
			.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, UGPlacedFeatures.DEPTHROCK_ROCK)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, UGPlacedFeatures.SHIVERSTONE_ROCK)
				.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, UGPlacedFeatures.GRONGLEGROWTH_DELTA)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEPTHROCK_PEBBLE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.UNDERBEAN_BUSH_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DROOPVINE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.GLOOMGOURD_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.GRONGLE_TREE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SMALL_GRONGLE_TREE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.GRONGLE_BUSH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.AMOROUS_BRISTLE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.BUTTERBUNCH_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.THORNREED_PATCH)
				.build())
			.mobSpawnSettings(addNormalRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
				.creatureGenerationProbability(0.5F)
				.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.STONEBORN.get(), 1, 3))
				.addSpawn(MobCategory.AMBIENT, 5, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 4, 8))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.GLOOMPER.get(), 4, 4))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.BRUTE.get(), 4, 4))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.MOG.get(), 4, 4))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.DWELLER.get(), 4, 4))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, FROST_AMBIENCE.apply(UGSoundEvents.UNDERGARDEN_AMBIENCE, UGSoundEvents.GRONGLEGROWTH_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 1186057)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(UGParticleTypes.GRONGLE_SPORE.get(), 0.05F)))
				.build())
			.specialEffects(generateColors(4103962))
			.build());

		context.register(ICY_SEA, new Biome.BiomeBuilder()
			.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, MiscOverworldPlacements.ICEBERG_PACKED)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, UGPlacedFeatures.ICE_PILLAR)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.ICE_ORE)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.FROSTSTEEL_ORE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TALL_SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.GLITTERKELP_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.FROZEN_DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.THORNREED_PATCH)
				.build())
			.mobSpawnSettings(addNormalRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
				.addSpawn(MobCategory.WATER_CREATURE, 10, new MobSpawnSettings.SpawnerData(UGEntityTypes.GWIB.get(), 1, 1))
				.addSpawn(MobCategory.WATER_AMBIENT, 5, new MobSpawnSettings.SpawnerData(UGEntityTypes.GWIBLING.get(), 3, 6))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.14F)
			.temperatureAdjustment(Biome.TemperatureModifier.FROZEN)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, FROST_AMBIENCE.apply(UGSoundEvents.SEA_AMBIENCE, UGSoundEvents.FROSTFIELDS_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 2565927)
				.build())
			.specialEffects(generateColors(14609908))
			.build());

		context.register(INDIGO_MUSHROOM_BOG, new Biome.BiomeBuilder()
			.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
				.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, UGPlacedFeatures.BOG_DELTA)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEPTHROCK_PEBBLE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TALL_DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DROOPVINE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.GLOOMGOURD_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.HUGE_INDIGO_MUSHROOM)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SMOGSTEM_BUSH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.THORNREED_PATCH)
				.build())
			.mobSpawnSettings(addNormalRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
				.creatureGenerationProbability(0.5F)
				.addSpawn(MobCategory.AMBIENT, 5, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 4, 8))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.GLOOMPER.get(), 8, 8))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, DEFAULT_AMBIENCE.apply(UGSoundEvents.UNDERGARDEN_AMBIENCE, UGSoundEvents.BOG_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 2432083)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.05F)))
				.build())
			.specialEffects(generateColors(4212845))
			.build());

		context.register(INK_MUSHROOM_BOG, new Biome.BiomeBuilder()
			.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
				.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, UGPlacedFeatures.BOG_DELTA)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEPTHROCK_PEBBLE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.GLOOMGOURD_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.HUGE_INK_MUSHROOM)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.THORNREED_PATCH)
				.build())
			.mobSpawnSettings(addNormalRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
				.creatureGenerationProbability(0.5F)
				.addSpawn(MobCategory.AMBIENT, 5, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 4, 8))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, DEFAULT_AMBIENCE.apply(UGSoundEvents.UNDERGARDEN_AMBIENCE, UGSoundEvents.BOG_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 1640729)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.05F)))
				.build())
			.specialEffects(generateColors(4075847))
			.build());

		context.register(SMOG_SPIRES, new Biome.BiomeBuilder()
			.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, UGPlacedFeatures.SMOG_VENT)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEPTHROCK_PEBBLE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.ASHEN_DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.BLISTERBERRY_BUSH_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TWISTYBUSH_PATCH)
				.build())
			.mobSpawnSettings(addDangerousRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
				.creatureGenerationProbability(0.5F)
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.DWELLER.get(), 8, 8))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.SMOG_MOG.get(), 2, 4))
				.addSpawn(MobCategory.AMBIENT, 5, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 4, 8))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(2.0F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, DEFAULT_AMBIENCE.apply(UGSoundEvents.SPIRES_AMBIENCE, UGSoundEvents.SPIRES_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 2565927)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.ASH, 0.118093334F)))
				.build())
			.specialEffects(generateColors(7568503))
			.build());

		context.register(SMOGSTEM_FOREST, new Biome.BiomeBuilder()
			.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, UGPlacedFeatures.DEPTHROCK_ROCK)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEPTHROCK_PEBBLE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.UNDERBEAN_BUSH_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DROOPVINE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.GLOOMGOURD_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SMOGSTEM_TREE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.HUGE_INDIGO_MUSHROOM_SMOGSTEM_FOREST)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.WIDE_SMOGSTEM_TREE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.AMOROUS_BRISTLE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.MISERABELL_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.THORNREED_PATCH)
				.build())
			.mobSpawnSettings(addNormalRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
				.creatureGenerationProbability(0.5F)
				.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.STONEBORN.get(), 1, 3))
				.addSpawn(MobCategory.AMBIENT, 5, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 4, 8))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.GLOOMPER.get(), 4, 4))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.BRUTE.get(), 4, 4))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.MOG.get(), 4, 4))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.DWELLER.get(), 4, 4))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, DEFAULT_AMBIENCE.apply(UGSoundEvents.UNDERGARDEN_AMBIENCE, UGSoundEvents.SMOGSTEM_FOREST_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 595225)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.025F)))
				.build())
			.specialEffects(generateColors(5928296))
			.build());

		context.register(VEIL_MUSHROOM_BOG, new Biome.BiomeBuilder()
			.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
				.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, UGPlacedFeatures.BOG_DELTA)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEPTHROCK_PEBBLE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DROOPVINE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.GLOOMGOURD_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.HUGE_VEIL_MUSHROOM)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.THORNREED_PATCH)
				.build())
			.mobSpawnSettings(addNormalRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
				.creatureGenerationProbability(0.5F)
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.GLOOMPER.get(), 8, 8))
				.addSpawn(MobCategory.AMBIENT, 5, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 4, 8))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, DEFAULT_AMBIENCE.apply(UGSoundEvents.UNDERGARDEN_AMBIENCE, UGSoundEvents.BOG_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 1643784)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.05F)))
				.build())
			.specialEffects(generateColors(7696730))
			.build());

		context.register(WIGGLEWOOD_FOREST, new Biome.BiomeBuilder()
			.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, UGPlacedFeatures.SHIVERSTONE_ROCK)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEPTHROCK_PEBBLE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.UNDERBEAN_BUSH_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DROOPVINE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.GLOOMGOURD_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.WIGGLEWOOD_TREE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TALL_WIGGLEWOOD_TREE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.MISERABELL_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.BUTTERBUNCH_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.THORNREED_PATCH)
				.build())
			.mobSpawnSettings(addNormalRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
				.creatureGenerationProbability(0.5F)
				.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.STONEBORN.get(), 1, 3))
				.addSpawn(MobCategory.AMBIENT, 5, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 4, 8))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.GLOOMPER.get(), 4, 4))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.BRUTE.get(), 4, 4))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.MOG.get(), 4, 4))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.DWELLER.get(), 4, 4))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, DEFAULT_AMBIENCE.apply(UGSoundEvents.UNDERGARDEN_AMBIENCE, UGSoundEvents.WIGGLEWOOD_FOREST_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 1643784)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.025F)))
				.build())
			.specialEffects(generateColors(7304538))
			.build());

		context.register(DEPTHS, new Biome.BiomeBuilder()
			.generationSettings(addDepthsOresAndCaves(new BiomeGenerationSettings.Builder(featureGetter, carverGetter))
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, UGPlacedFeatures.ANCIENT_ROOT)
				.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, UGPlacedFeatures.UTHERIUM_GROWTH)
				.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, UGPlacedFeatures.CEILING_UTHERIUM_GROWTH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.HUGE_PUFF_MUSHROOM_SPARSE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.PUFF_MUSHROOM_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH_SPARSE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TWISTYBUSH_PATCH)
				.build())
			.mobSpawnSettings(addDangerousRotspawn(new MobSpawnSettings.Builder())
				.creatureGenerationProbability(0.9999999F)
				.addSpawn(MobCategory.valueOf("UNDERGARDEN_STUPID_MOB_CATEGORY"), 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.GREATER_DWELLER.get(), 2, 4))
				.addSpawn(MobCategory.MONSTER, 50, new MobSpawnSettings.SpawnerData(UGEntityTypes.DENIZEN.get(), 1, 4))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, DEFAULT_AMBIENCE.apply(UGSoundEvents.ABYSS_AMBIENCE, UGSoundEvents.ABYSS_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 0)
				.set(EnvironmentAttributes.FOG_END_DISTANCE, 100.0F)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.MYCELIUM, 0.025F)))
				.build())
			.specialEffects(generateColors(7568503))
			.build());

		context.register(INFECTED_DEPTHS, new Biome.BiomeBuilder()
			.generationSettings(addDepthsOresAndCaves(new BiomeGenerationSettings.Builder(featureGetter, carverGetter))
				.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, UGPlacedFeatures.UTHERIUM_GROWTH_EXTRA)
				.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, UGPlacedFeatures.CEILING_UTHERIUM_GROWTH)
				.build())
			.mobSpawnSettings(addDangerousRotspawn(new MobSpawnSettings.Builder())
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, DEFAULT_AMBIENCE.apply(UGSoundEvents.ABYSS_AMBIENCE, UGSoundEvents.ABYSS_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 3276800)
				.set(EnvironmentAttributes.FOG_END_DISTANCE, 100.0F)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.MYCELIUM, 0.025F)))
				.build())
			.specialEffects(generateColors(7568503))
			.build());

		context.register(PUFF_MUSHROOM_FOREST, new Biome.BiomeBuilder()
			.generationSettings(addDepthsOresAndCaves(new BiomeGenerationSettings.Builder(featureGetter, carverGetter))
				.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, UGPlacedFeatures.UTHERIUM_GROWTH)
				.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, UGPlacedFeatures.CEILING_UTHERIUM_GROWTH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.HUGE_PUFF_MUSHROOM)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.PUFF_MUSHROOM_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TWISTYBUSH_PATCH)
				.build())
			.mobSpawnSettings(addDangerousRotspawn(new MobSpawnSettings.Builder())
				.creatureGenerationProbability(0.9999999F)
				.addSpawn(MobCategory.valueOf("UNDERGARDEN_STUPID_MOB_CATEGORY"), 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.GREATER_DWELLER.get(), 2, 4))
				.addSpawn(MobCategory.MONSTER, 50, new MobSpawnSettings.SpawnerData(UGEntityTypes.DENIZEN.get(), 1, 4))
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, DEFAULT_AMBIENCE.apply(UGSoundEvents.ABYSS_AMBIENCE, UGSoundEvents.ABYSS_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 0)
				.set(EnvironmentAttributes.FOG_END_DISTANCE, 100.0F)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.MYCELIUM, 0.025F)))
				.build())
			.specialEffects(generateColors(6312510))
			.build());

		context.register(ROGDORIUM_GROVE, new Biome.BiomeBuilder()
			.generationSettings(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)
				.addCarver(UGConfiguredCarvers.UNDERGARDEN_CAVE)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.ROGDORIUM_ORE_EXTRA)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, UGPlacedFeatures.ANCIENT_ROOT_EXTRA)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TALL_DEEPTURF_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.TALL_SHIMMERWEED_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DROOPVINE_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.MISERABELL_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SMOGSTEM_TREE_SPARSE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.SMOGSTEM_BUSH)
				.build())
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.putAttributes(EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.AMBIENT_SOUNDS, DEFAULT_AMBIENCE.apply(UGSoundEvents.ABYSS_AMBIENCE, UGSoundEvents.ABYSS_AMBIENT_ADDITION))
				.set(EnvironmentAttributes.FOG_COLOR, 4479879)
				.set(EnvironmentAttributes.FOG_END_DISTANCE, 100.0F)
				.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(UGParticleTypes.ROGDORIUM_WISP.get(), 0.01F)))
				.build())
			.specialEffects(generateColors(1784489))
			.build());
	}

	private static BiomeGenerationSettings.Builder addOresAndCaves(BiomeGenerationSettings.Builder builder) {
		return builder
			.addCarver(UGConfiguredCarvers.UNDERGARDEN_CAVE)
			.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.DEEPSOIL_ORE)
			.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.SEDIMENT_ORE)
			.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.SHIVERSTONE_ORE)
			.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.COAL_ORE)
			.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.IRON_ORE)
			.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.GOLD_ORE)
			.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.DIAMOND_ORE)
			.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.CLOGGRUM_ORE)
			.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.UTHERIUM_ORE)
			.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.REGALIUM_ORE);
	}

	private static BiomeGenerationSettings.Builder addDepthsOresAndCaves(BiomeGenerationSettings.Builder builder) {
		return builder
			.addCarver(UGConfiguredCarvers.UNDERGARDEN_CAVE)
			.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.ROGDORIUM_ORE)
			.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UGPlacedFeatures.UTHERIUM_ORE);
	}

	private static BiomeGenerationSettings.Builder addShroomPatches(BiomeGenerationSettings.Builder builder) {
		return builder
			.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.BLOOD_MUSHROOM_PATCH)
			.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.INDIGO_MUSHROOM_PATCH)
			.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.INK_MUSHROOM_PATCH)
			.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.VEIL_MUSHROOM_PATCH);
	}

	private static MobSpawnSettings.Builder addCaveMobs(MobSpawnSettings.Builder builder) {
		return builder
			.addSpawn(MobCategory.MONSTER, 50, new MobSpawnSettings.SpawnerData(UGEntityTypes.NARGOYLE.get(), 1, 1))
			.addSpawn(MobCategory.MONSTER, 50, new MobSpawnSettings.SpawnerData(UGEntityTypes.MUNCHER.get(), 1, 1))
			.addSpawn(MobCategory.MONSTER, 50, new MobSpawnSettings.SpawnerData(UGEntityTypes.SPLOOGIE.get(), 1, 1));
	}

	private static MobSpawnSettings.Builder addNormalRotspawn(MobSpawnSettings.Builder builder) {
		return builder
			.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.ROTLING.get(), 2, 4))
			.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.ROTWALKER.get(), 4, 4))
			.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.ROTBELCHER.get(), 4, 4));
	}

	private static MobSpawnSettings.Builder addDangerousRotspawn(MobSpawnSettings.Builder builder) {
		return builder
			.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.ROTLING.get(), 2, 4))
			.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.ROTWALKER.get(), 4, 4))
			.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.ROTBELCHER.get(), 4, 4))
			.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(UGEntityTypes.ROTBEAST.get(), 1, 2));
	}

	private static BiomeSpecialEffects generateColors(int grass) {
		return new BiomeSpecialEffects.Builder().waterColor(342306).grassColorOverride(grass).foliageColorOverride(grass).build();
	}

	public static BiomeSource buildBiomeSource(HolderGetter<Biome> biomes) {
		List<Pair<Climate.ParameterPoint, Holder<Biome>>> params = new ArrayList<>();

		createForBiomeAndSea(params, biomes, Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.75F, 0.0F), FORGOTTEN_FIELD, ANCIENT_SEA);
		createForBiomeAndSea(params, biomes, Climate.parameters(0.0F, 0.0F, -0.5F, 0.75F, 0.0F, -0.7F, 0.0F), INK_MUSHROOM_BOG, ANCIENT_SEA);
		createForBiomeAndSea(params, biomes, Climate.parameters(0.0F, 0.0F, -0.2F, 0.85F, 0.0F, -0.65F, 0.0F), INDIGO_MUSHROOM_BOG, ANCIENT_SEA);
		createForBiomeAndSea(params, biomes, Climate.parameters(0.0F, 0.0F, -0.4F, 0.8F, 0.0F, -0.6F, 0.0F), VEIL_MUSHROOM_BOG, DEAD_SEA);
		createForBiomeAndSea(params, biomes, Climate.parameters(0.0F, 0.0F, -0.65F, 0.85F, 0.0F, -0.6F, 0.0F), BLOOD_MUSHROOM_BOG, DEAD_SEA);
		createForBiomeAndSea(params, biomes, Climate.parameters(0.2F, 0.0F, -0.3F, -0.6F, 0.0F, 0.75F, 0.0F), GRONGLEGROWTH, ANCIENT_SEA);
		createForBiomeAndSea(params, biomes, Climate.parameters(Climate.Parameter.point(0.0F), Climate.Parameter.point(-0.2F), Climate.Parameter.point(-0.15F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.span(-1.0F, -0.8F), 0.0F), BARREN_ABYSS, DEAD_SEA);
		createForBiomeAndSea(params, biomes, Climate.parameters(Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(-0.2F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.span(-1.0F, -0.825F), 0.0F), SMOG_SPIRES, DEAD_SEA);
		createForBiomeAndSea(params, biomes, Climate.parameters(0.0F, 0.0F, -0.2F, 0.0F, 0.0F, 0.45F, 0.0F), WIGGLEWOOD_FOREST, ANCIENT_SEA);
		createForBiomeAndSea(params, biomes, Climate.parameters(0.0F, 0.0F, -0.15F, 0.0F, 0.0F, 0.45F, 0.0F), DENSE_FOREST, ANCIENT_SEA);
		createForBiomeAndSea(params, biomes, Climate.parameters(0.0F, 0.0F, 0.1F, 0.0F, 0.0F, 0.6F, 0.0F), SMOGSTEM_FOREST, ANCIENT_SEA);
		createForBiomeAndSea(params, biomes, Climate.parameters(0.0F, 0.0F, 0.4F, 0.0F, 0.0F, 0.85F, 0.0F), FROSTY_SMOGSTEM_FOREST, ICY_SEA);
		createForBiomeAndSea(params, biomes, Climate.parameters(0.0F, 0.0F, 0.9F, 0.0F, 0.0F, 1.0F, 0.0F), FROSTFIELDS, ICY_SEA);

		params.add(Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(DEPTHS)));
		params.add(Pair.of(Climate.parameters(Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.span(0.7F, 1.0F), Climate.Parameter.point(-2.0F), Climate.Parameter.point(0.0F), 0.0F), biomes.getOrThrow(INFECTED_DEPTHS)));
		params.add(Pair.of(Climate.parameters(1.5F, 0.0F, 0.0F, 0.0F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(PUFF_MUSHROOM_FOREST)));
		params.add(Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, -1.0F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(ROGDORIUM_GROVE)));

		return MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(params));
	}

	private static void createForBiomeAndSea(List<Pair<Climate.ParameterPoint, Holder<Biome>>> list, HolderGetter<Biome> biomes, Climate.ParameterPoint parameters, ResourceKey<Biome> mainBiome, ResourceKey<Biome> sea) {
		list.add(Pair.of(parameters, biomes.getOrThrow(mainBiome)));
		list.add(Pair.of(Climate.parameters(parameters.temperature(), parameters.humidity(), parameters.continentalness(), parameters.erosion(), Climate.Parameter.point(-1.0F), parameters.weirdness(), parameters.offset()), biomes.getOrThrow(sea)));
	}
}