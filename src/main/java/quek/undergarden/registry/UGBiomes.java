package quek.undergarden.registry;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;

public class UGBiomes {

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

	private static ResourceKey<Biome> create(String name) {
		return ResourceKey.create(Registries.BIOME, new ResourceLocation(Undergarden.MODID, name));
	}

	public static void bootstrap(BootstapContext<Biome> context) {
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
						.build())
				.mobSpawnSettings(addRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
						.addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.GWIB.get(), 10, 1, 1))
						.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(UGEntityTypes.GWIBLING.get(), 5, 3, 6))
						.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 5, 4, 8))
						.build())
				.hasPrecipitation(false)
				.downfall(0.0F)
				.temperature(0.8F)
				.specialEffects(addMusicAndAmbience(generateColors(new BiomeSpecialEffects.Builder(), 1186057, 4477507), UGSoundEvents.SEA_AMBIENCE, UGSoundEvents.SEA_AMBIENT_ADDITION).build())
				.build());

		context.register(BARREN_ABYSS, new Biome.BiomeBuilder()
				.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
						.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEPTHROCK_PEBBLE_PATCH)
						.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH)
						.build())
				.mobSpawnSettings(addRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
						.creatureGenerationProbability(0.5F)
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.SMOG_MOG.get(), 100, 2, 4))
						.build())
				.hasPrecipitation(false)
				.downfall(0.0F)
				.temperature(0.2F)
				.specialEffects(addMusicAndAmbience(generateColors(new BiomeSpecialEffects.Builder(), 2565927, 7568503), UGSoundEvents.ABYSS_AMBIENCE, UGSoundEvents.ABYSS_AMBIENT_ADDITION)
						.ambientParticle(new AmbientParticleSettings(ParticleTypes.ASH, 0.118093334F))
						.build()
				)
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
						.build())
				.mobSpawnSettings(addRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
						.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 5, 4, 8))
						.build())
				.hasPrecipitation(false)
				.downfall(0.0F)
				.temperature(0.8F)
				.specialEffects(addMusicAndAmbience(generateColors(new BiomeSpecialEffects.Builder(), 1248522, 6180396), UGSoundEvents.ABYSS_AMBIENCE, UGSoundEvents.BOG_AMBIENT_ADDITION)
						.ambientParticle(new AmbientParticleSettings(ParticleTypes.ASH, 0.118093334F))
						.build()
				)
				.build());

		context.register(DEAD_SEA, new Biome.BiomeBuilder()
				.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
						.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH)
						.build())
				.mobSpawnSettings(addRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
						.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 5, 4, 8))
						.build())
				.hasPrecipitation(false)
				.downfall(0.0F)
				.temperature(0.8F)
				.specialEffects(addMusicAndAmbience(generateColors(new BiomeSpecialEffects.Builder(), 2565927, 7568503), UGSoundEvents.ABYSS_AMBIENCE, UGSoundEvents.ABYSS_AMBIENT_ADDITION).build())
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
						.build())
				.mobSpawnSettings(addRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
						.creatureGenerationProbability(0.5F)
						.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(UGEntityTypes.STONEBORN.get(), 100, 1, 3))
						.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 5, 4, 8))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.GLOOMPER.get(), 100, 4, 4))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.BRUTE.get(), 100, 4, 4))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.MOG.get(), 100, 4, 4))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.DWELLER.get(), 100, 4, 4))
						.build())
				.hasPrecipitation(false)
				.downfall(0.0F)
				.temperature(0.8F)
				.specialEffects(addMusicAndAmbience(generateColors(new BiomeSpecialEffects.Builder(), 1186057, 4224322), UGSoundEvents.UNDERGARDEN_AMBIENCE, UGSoundEvents.DENSE_FOREST_AMBIENT_ADDITION)
						.ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.025F))
						.build())
				.build());

		context.register(FORGOTTEN_FIELD, new Biome.BiomeBuilder()
				.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
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
						.build())
				.mobSpawnSettings(addRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
						.creatureGenerationProbability(0.5F)
						.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(UGEntityTypes.STONEBORN.get(), 100, 1, 3))
						.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 5, 4, 8))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.GLOOMPER.get(), 100, 4, 4))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.BRUTE.get(), 100, 4, 4))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.MOG.get(), 100, 4, 4))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.DWELLER.get(), 100, 4, 4))
						.build())
				.hasPrecipitation(false)
				.downfall(0.0F)
				.temperature(0.8F)
				.specialEffects(addMusicAndAmbience(generateColors(new BiomeSpecialEffects.Builder(), 1186057, 5993819), UGSoundEvents.UNDERGARDEN_AMBIENCE, UGSoundEvents.FIELDS_AMBIENT_ADDITION)
						.ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.025F))
						.build())
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
						.build())
				.mobSpawnSettings(addRotspawn(addCaveMobs(new MobSpawnSettings.Builder())).build())
				.hasPrecipitation(false)
				.downfall(0.0F)
				.temperature(0.14F)
				.temperatureAdjustment(Biome.TemperatureModifier.FROZEN)
				.specialEffects(addMusicAndAmbience(generateColors(new BiomeSpecialEffects.Builder(), 2565927, 14609908), UGSoundEvents.FROST_MOOD, UGSoundEvents.FROST_AMBIENCE, UGSoundEvents.FROSTFIELDS_AMBIENT_ADDITION, 0.002775D)
						.ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.118093334F))
						.build())
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
						.build())
				.mobSpawnSettings(addRotspawn(addCaveMobs(new MobSpawnSettings.Builder())).build())
				.hasPrecipitation(false)
				.downfall(0.0F)
				.temperature(0.14F)
				.temperatureAdjustment(Biome.TemperatureModifier.FROZEN)
				.specialEffects(addMusicAndAmbience(generateColors(new BiomeSpecialEffects.Builder(), 2565927, 14609908), UGSoundEvents.FROST_MOOD, UGSoundEvents.FROST_AMBIENCE, UGSoundEvents.SMOGSTEM_FOREST_AMBIENT_ADDITION, 0.002775D)
						.ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.118093334F))
						.build())
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
						.build())
				.mobSpawnSettings(addRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
						.creatureGenerationProbability(0.5F)
						.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(UGEntityTypes.STONEBORN.get(), 100, 1, 3))
						.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 5, 4, 8))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.GLOOMPER.get(), 100, 4, 4))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.BRUTE.get(), 100, 4, 4))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.MOG.get(), 100, 4, 4))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.DWELLER.get(), 100, 4, 4))
						.build())
				.hasPrecipitation(false)
				.downfall(0.0F)
				.temperature(0.8F)
				.specialEffects(addMusicAndAmbience(generateColors(new BiomeSpecialEffects.Builder(), 1186057, 4103962), UGSoundEvents.UNDERGARDEN_AMBIENCE, UGSoundEvents.GRONGLEGROWTH_AMBIENT_ADDITION)
						.ambientParticle(new AmbientParticleSettings(UGParticleTypes.GRONGLE_SPORE.get(), 0.05F))
						.build())
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
						.build())
				.mobSpawnSettings(addRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
						.addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.GWIB.get(), 10, 1, 1))
						.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(UGEntityTypes.GWIBLING.get(), 5, 3, 6))
						.build())
				.hasPrecipitation(false)
				.downfall(0.0F)
				.temperature(0.14F)
				.temperatureAdjustment(Biome.TemperatureModifier.FROZEN)
				.specialEffects(addMusicAndAmbience(generateColors(new BiomeSpecialEffects.Builder(), 2565927, 14609908), UGSoundEvents.FROST_MOOD, UGSoundEvents.SEA_AMBIENCE, UGSoundEvents.FROSTFIELDS_AMBIENT_ADDITION, 0.002775D).build())
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
						.build())
				.mobSpawnSettings(addRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
						.creatureGenerationProbability(0.5F)
						.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 5, 4, 8))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.GLOOMPER.get(), 100, 8, 8))
						.build())
				.hasPrecipitation(false)
				.downfall(0.0F)
				.temperature(0.8F)
				.specialEffects(addMusicAndAmbience(generateColors(new BiomeSpecialEffects.Builder(), 2432083, 4212845), UGSoundEvents.UNDERGARDEN_AMBIENCE, UGSoundEvents.BOG_AMBIENT_ADDITION)
						.ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.05F))
						.build())
				.build());

		context.register(INK_MUSHROOM_BOG, new Biome.BiomeBuilder()
				.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
						.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, UGPlacedFeatures.BOG_DELTA)
						.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEEPTURF_PATCH)
						.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEPTHROCK_PEBBLE_PATCH)
						.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH)
						.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.GLOOMGOURD_PATCH)
						.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.HUGE_INK_MUSHROOM)
						.build())
				.mobSpawnSettings(addRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
						.creatureGenerationProbability(0.5F)
						.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 5, 4, 8))
						.build())
				.hasPrecipitation(false)
				.downfall(0.0F)
				.temperature(0.8F)
				.specialEffects(addMusicAndAmbience(generateColors(new BiomeSpecialEffects.Builder(), 1640729, 4075847), UGSoundEvents.UNDERGARDEN_AMBIENCE, UGSoundEvents.BOG_AMBIENT_ADDITION)
						.ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.05F))
						.build())
				.build());

		context.register(SMOG_SPIRES, new Biome.BiomeBuilder()
				.generationSettings(addOresAndCaves(addShroomPatches(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)))
						.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, UGPlacedFeatures.SMOG_VENT)
						.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DEPTHROCK_PEBBLE_PATCH)
						.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.DITCHBULB_PATCH)
						.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.ASHEN_DEEPTURF_PATCH)
						.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.BLISTERBERRY_BUSH_PATCH)
						.build())
				.mobSpawnSettings(addRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
						.creatureGenerationProbability(0.5F)
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.DWELLER.get(), 100, 8, 8))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.SMOG_MOG.get(), 100, 2, 4))
						.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 5, 4, 8))
						.build())
				.hasPrecipitation(false)
				.downfall(0.0F)
				.temperature(2.0F)
				.specialEffects(addMusicAndAmbience(generateColors(new BiomeSpecialEffects.Builder(), 2565927, 7568503), UGSoundEvents.SPIRES_AMBIENCE, UGSoundEvents.SPIRES_AMBIENT_ADDITION)
						.ambientParticle(new AmbientParticleSettings(ParticleTypes.ASH, 0.118093334F))
						.build())
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
						.build())
				.mobSpawnSettings(addRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
						.creatureGenerationProbability(0.5F)
						.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(UGEntityTypes.STONEBORN.get(), 100, 1, 3))
						.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 5, 4, 8))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.GLOOMPER.get(), 100, 4, 4))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.BRUTE.get(), 100, 4, 4))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.MOG.get(), 100, 4, 4))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.DWELLER.get(), 100, 4, 4))
						.build())
				.hasPrecipitation(false)
				.downfall(0.0F)
				.temperature(0.8F)
				.specialEffects(addMusicAndAmbience(generateColors(new BiomeSpecialEffects.Builder(), 595225, 5928296), UGSoundEvents.UNDERGARDEN_AMBIENCE, UGSoundEvents.SMOGSTEM_FOREST_AMBIENT_ADDITION)
						.ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.025F))
						.build())
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
						.build())
				.mobSpawnSettings(addRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
						.creatureGenerationProbability(0.5F)
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.GLOOMPER.get(), 100, 8, 8))
						.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 5, 4, 8))
						.build())
				.hasPrecipitation(false)
				.downfall(0.0F)
				.temperature(0.8F)
				.specialEffects(addMusicAndAmbience(generateColors(new BiomeSpecialEffects.Builder(), 1643784, 7696730), UGSoundEvents.UNDERGARDEN_AMBIENCE, UGSoundEvents.BOG_AMBIENT_ADDITION)
						.ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.05F))
						.build())
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
						.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.MISERABELL_PATCH)
						.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.BUTTERBUNCH_PATCH)
						.build())
				.mobSpawnSettings(addRotspawn(addCaveMobs(new MobSpawnSettings.Builder()))
						.creatureGenerationProbability(0.5F)
						.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(UGEntityTypes.STONEBORN.get(), 100, 1, 3))
						.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(UGEntityTypes.SCINTLING.get(), 5, 4, 8))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.GLOOMPER.get(), 100, 4, 4))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.BRUTE.get(), 100, 4, 4))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.MOG.get(), 100, 4, 4))
						.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(UGEntityTypes.DWELLER.get(), 100, 4, 4))
						.build())
				.hasPrecipitation(false)
				.downfall(0.0F)
				.temperature(0.8F)
				.specialEffects(addMusicAndAmbience(generateColors(new BiomeSpecialEffects.Builder(), 1643784, 7304538), UGSoundEvents.UNDERGARDEN_AMBIENCE, UGSoundEvents.WIGGLEWOOD_FOREST_AMBIENT_ADDITION)
						.ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.025F))
						.build())
				.build());
	}

	private static BiomeGenerationSettings.Builder addOresAndCaves(BiomeGenerationSettings.Builder builder) {
		return builder
				.addCarver(GenerationStep.Carving.AIR, UGConfiguredCarvers.UNDERGARDEN_CAVE)
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

	private static BiomeGenerationSettings.Builder addShroomPatches(BiomeGenerationSettings.Builder builder) {
		return builder
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.BLOOD_MUSHROOM_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.INDIGO_MUSHROOM_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.INK_MUSHROOM_PATCH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UGPlacedFeatures.VEIL_MUSHROOM_PATCH);
	}

	private static MobSpawnSettings.Builder addCaveMobs(MobSpawnSettings.Builder builder) {
		return builder
				.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(UGEntityTypes.NARGOYLE.get(), 50, 1, 1))
				.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(UGEntityTypes.MUNCHER.get(), 50, 1, 1))
				.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(UGEntityTypes.SPLOOGIE.get(), 50, 1, 1));
	}

	private static MobSpawnSettings.Builder addRotspawn(MobSpawnSettings.Builder builder) {
		return builder
				.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(UGEntityTypes.ROTLING.get(), 100, 2, 4))
				.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(UGEntityTypes.ROTWALKER.get(), 100, 4, 4))
				.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(UGEntityTypes.ROTBEAST.get(), 100, 1, 2));
	}

	private static BiomeSpecialEffects.Builder addMusicAndAmbience(BiomeSpecialEffects.Builder builder, RegistryObject<SoundEvent> ambient, RegistryObject<SoundEvent> addition) {
		return addMusicAndAmbience(builder, UGSoundEvents.MOOD, ambient, addition, 0.00555D);
	}

	private static BiomeSpecialEffects.Builder addMusicAndAmbience(BiomeSpecialEffects.Builder builder, RegistryObject<SoundEvent> mood, RegistryObject<SoundEvent> ambient, RegistryObject<SoundEvent> addition, double additionInterval) {
		return builder
				.ambientAdditionsSound(new AmbientAdditionsSettings(addition.getHolder().get(), additionInterval))
				.ambientMoodSound(new AmbientMoodSettings(mood.getHolder().get(), 6000, 8, 2))
				.ambientLoopSound(ambient.getHolder().get())
				.backgroundMusic(new Music(UGSoundEvents.UNDERGARDEN_MUSIC.getHolder().get(), 12000, 24000, true));
	}

	private static BiomeSpecialEffects.Builder generateColors(BiomeSpecialEffects.Builder builder, int skyFog, int grass) {
		return builder
				.skyColor(1186057)
				.fogColor(skyFog)
				.waterColor(342306)
				.waterFogColor(332810)
				.grassColorOverride(grass)
				.foliageColorOverride(grass);
	}

	public static BiomeSource buildBiomeSource(HolderGetter<Biome> biomes) {
		return MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(ImmutableList.of(
				Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(FORGOTTEN_FIELD)),
				Pair.of(Climate.parameters(-1.0F, -0.4F, -0.9F, -0.7F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(FROSTFIELDS)),
				Pair.of(Climate.parameters(1.0F, 0.0F, 0.0F, 0.0F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(SMOGSTEM_FOREST)),
				Pair.of(Climate.parameters(0.0F, -0.4F, 0.0F, 0.0F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(WIGGLEWOOD_FOREST)),
				Pair.of(Climate.parameters(1.0F, 0.4F, 0.0F, 0.0F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(DENSE_FOREST)),
				Pair.of(Climate.parameters(0.0F, 0.0F, 0.9F, 0.0F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(GRONGLEGROWTH)),
				Pair.of(Climate.parameters(-1.0F, -0.4F, -0.9F, -0.7F, -2.0F, 0.5F, 0.0F), biomes.getOrThrow(FROSTY_SMOGSTEM_FOREST)),
				Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.7F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(BARREN_ABYSS)),
				Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 1.0F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(SMOG_SPIRES)),
				Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, -2.0F, 1.0F, 0.0F), biomes.getOrThrow(INK_MUSHROOM_BOG)),
				Pair.of(Climate.parameters(1.0F, 0.0F, 0.0F, 0.0F, -2.0F, 1.0F, 0.0F), biomes.getOrThrow(INDIGO_MUSHROOM_BOG)),
				Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, -2.0F, -1.0F, 0.0F), biomes.getOrThrow(VEIL_MUSHROOM_BOG)),
				Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.7F, -2.0F, -1.0F, 0.0F), biomes.getOrThrow(BLOOD_MUSHROOM_BOG)),
				Pair.of(Climate.parameters(Climate.Parameter.span(0.0F, 1.0F), Climate.Parameter.span(0.0F, 0.4F), Climate.Parameter.span(0.0F, 0.9F), Climate.Parameter.point(0.0F), Climate.Parameter.point(2.0F), Climate.Parameter.span(-1.0F, 1.0F), 0.0F), biomes.getOrThrow(ANCIENT_SEA)),
				Pair.of(Climate.parameters(Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.span(0.7F, 1.0F), Climate.Parameter.point(2.0F), Climate.Parameter.point(0.0F), 0.0F), biomes.getOrThrow(DEAD_SEA)),
				Pair.of(Climate.parameters(Climate.Parameter.point(-1.0F), Climate.Parameter.point(-0.4F), Climate.Parameter.point(-0.9F), Climate.Parameter.point(-0.7F), Climate.Parameter.point(2.0F), Climate.Parameter.span(0.0F, 0.5F), 0.0F), biomes.getOrThrow(ICY_SEA))
		)));
	}
}