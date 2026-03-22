package quek.undergarden.registry;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.structure.BiggerJigsawStructure;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UGStructures {

	public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, Undergarden.MODID);

	public static final DeferredHolder<StructureType<?>, StructureType<BiggerJigsawStructure>> BIGGER_JIGSAW = STRUCTURES.register("bigger_jigsaw", () -> () -> BiggerJigsawStructure.CODEC);

	//catacombs
	public static final ResourceKey<Structure> CATACOMBS = ResourceKey.create(Registries.STRUCTURE, Undergarden.prefix("catacombs"));
	public static final ResourceKey<StructureSet> CATACOMBS_SET = ResourceKey.create(Registries.STRUCTURE_SET, Undergarden.prefix("catacombs"));

	public static final ResourceKey<StructureTemplatePool> CATACOMBS_START = ResourceKey.create(Registries.TEMPLATE_POOL, Undergarden.prefix("catacombs/catacombs_entrance"));
	public static final ResourceKey<StructureTemplatePool> CATACOMBS_STAIR = ResourceKey.create(Registries.TEMPLATE_POOL, Undergarden.prefix("catacombs/stair_pool"));
	public static final ResourceKey<StructureTemplatePool> CATACOMBS_CHEST = ResourceKey.create(Registries.TEMPLATE_POOL, Undergarden.prefix("catacombs/chest_pool"));
	public static final ResourceKey<StructureTemplatePool> CATACOMBS_INTERIOR = ResourceKey.create(Registries.TEMPLATE_POOL, Undergarden.prefix("catacombs/interior_pool"));
	public static final ResourceKey<StructureTemplatePool> CATACOMBS_TUNNEL = ResourceKey.create(Registries.TEMPLATE_POOL, Undergarden.prefix("catacombs/tunnel_pool"));
	public static final ResourceKey<StructureTemplatePool> CATACOMBS_TUNNEL_FALLBACK = ResourceKey.create(Registries.TEMPLATE_POOL, Undergarden.prefix("catacombs/tunnel_fallback_pool"));
	public static final ResourceKey<StructureTemplatePool> CATACOMBS_WAY = ResourceKey.create(Registries.TEMPLATE_POOL, Undergarden.prefix("catacombs/way_pool"));

	public static final ResourceKey<StructureProcessorList> CATACOMBS_DEGRADATION = ResourceKey.create(Registries.PROCESSOR_LIST, Undergarden.prefix("catacombs_degradation"));

	//forgotten vestige
	public static final ResourceKey<Structure> FORGOTTEN_VESTIGE = ResourceKey.create(Registries.STRUCTURE, Undergarden.prefix("forgotten_vestige"));
	public static final ResourceKey<StructureSet> FORGOTTEN_VESTIGE_SET = ResourceKey.create(Registries.STRUCTURE_SET, Undergarden.prefix("forgotten_vestige"));

	public static final ResourceKey<StructureTemplatePool> FORGOTTEN_VESTIGE_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, Undergarden.prefix("forgotten_vestige"));
	public static final ResourceKey<StructureTemplatePool> FORGOTTEN_VESTIGE_HOUSE_POT_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, Undergarden.prefix("forgotten_vestige/house_pot_pool"));

	public static final ResourceKey<StructureProcessorList> FORGOTTEN_VESTIGE_DEGRADATION = ResourceKey.create(Registries.PROCESSOR_LIST, Undergarden.prefix("forgotten_vestige_degradation"));

	//denizen camps
	public static final ResourceKey<Structure> DENIZEN_CAMP = ResourceKey.create(Registries.STRUCTURE, Undergarden.prefix("denizen_camp"));
	public static final ResourceKey<StructureSet> DENIZEN_CAMP_SET = ResourceKey.create(Registries.STRUCTURE_SET, Undergarden.prefix("denizen_camp"));

	public static final ResourceKey<StructureTemplatePool> DENIZEN_CAMP_TOTEM_CIRCLE_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, Undergarden.prefix("denizen_camp/totem_circle_pool"));
	public static final ResourceKey<StructureTemplatePool> DENIZEN_CAMP_TOTEM_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, Undergarden.prefix("denizen_camp/totem_pool"));
	public static final ResourceKey<StructureTemplatePool> DENIZEN_CAMP_ROAD_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, Undergarden.prefix("denizen_camp/road_pool"));
	public static final ResourceKey<StructureTemplatePool> DENIZEN_CAMP_HANGOUT_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, Undergarden.prefix("denizen_camp/hangout_pool"));

	public static final ResourceKey<StructureProcessorList> DENIZEN_CAMP_ROAD_PROCESSOR = ResourceKey.create(Registries.PROCESSOR_LIST, Undergarden.prefix("denizen_camp_road_processor"));
	public static final ResourceKey<StructureProcessorList> DENIZEN_CAMP_WOOD_PROCESSOR = ResourceKey.create(Registries.PROCESSOR_LIST, Undergarden.prefix("denizen_camp_wood_processor"));

	//depleted mine
	public static final ResourceKey<Structure> DEPLETED_MINE = ResourceKey.create(Registries.STRUCTURE, Undergarden.prefix("depleted_mine"));
	public static final ResourceKey<StructureSet> DEPLETED_MINE_SET = ResourceKey.create(Registries.STRUCTURE_SET, Undergarden.prefix("depleted_mine"));

	public static final ResourceKey<StructureTemplatePool> DEPLETED_MINE_WALKWAY_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, Undergarden.prefix("depleted_mine/walkway_pool"));
	public static final ResourceKey<StructureTemplatePool> DEPLETED_MINE_CLUTTER_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, Undergarden.prefix("depleted_mine/clutter_pool"));

	public static final ResourceKey<StructureProcessorList> DEPLETED_MINE_LANTERN_PROCESSOR = ResourceKey.create(Registries.PROCESSOR_LIST, Undergarden.prefix("depleted_mine_lantern_processor"));

	public static void bootstrapStructures(BootstrapContext<Structure> context) {
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
		HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);

		context.register(CATACOMBS, new BiggerJigsawStructure(
			new Structure.StructureSettings.Builder(biomes.getOrThrow(UGTags.Biomes.HAS_CATACOMBS))
				.spawnOverrides(
					Map.of(
						MobCategory.MONSTER,
						new StructureSpawnOverride(
							StructureSpawnOverride.BoundingBoxType.PIECE,
							WeightedList.of(new MobSpawnSettings.SpawnerData(UGEntityTypes.FORGOTTEN.get(), 1, 4))
						)
					)
				)
				.generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
				.terrainAdapation(TerrainAdjustment.NONE)
				.build(),
			pools.getOrThrow(CATACOMBS_START),
			Optional.empty(),
			15,
			ConstantHeight.of(VerticalAnchor.aboveBottom(112)),
			Optional.empty(),
			new JigsawStructure.MaxDistance(116),
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.IGNORE_WATERLOGGING
		));
		context.register(FORGOTTEN_VESTIGE, new BiggerJigsawStructure(
			new Structure.StructureSettings.Builder(biomes.getOrThrow(UGTags.Biomes.HAS_FORGOTTEN_VESTIGE))
				.generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
				.terrainAdapation(TerrainAdjustment.BEARD_THIN)
				.build(),
			pools.getOrThrow(FORGOTTEN_VESTIGE_POOL),
			Optional.empty(),
			5,
			UniformHeight.of(VerticalAnchor.absolute(32), VerticalAnchor.TOP),
			Optional.empty(),
			new JigsawStructure.MaxDistance(10),
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.APPLY_WATERLOGGING
		));
		context.register(DENIZEN_CAMP, new BiggerJigsawStructure(
			new Structure.StructureSettings.Builder(biomes.getOrThrow(UGTags.Biomes.HAS_DENIZEN_CAMP))
				.spawnOverrides(
					Map.of(
						MobCategory.MONSTER,
						new StructureSpawnOverride(
							StructureSpawnOverride.BoundingBoxType.STRUCTURE,
							WeightedList.of(new MobSpawnSettings.SpawnerData(UGEntityTypes.DENIZEN.get(), 1, 1))
						)
					)
				)
				.generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
				.terrainAdapation(TerrainAdjustment.BEARD_THIN)
				.build(),
			pools.getOrThrow(DENIZEN_CAMP_TOTEM_CIRCLE_POOL),
			Optional.empty(),
			5,
			UniformHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(0)),
			Optional.empty(),
			new JigsawStructure.MaxDistance(25),
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.IGNORE_WATERLOGGING
		));
		context.register(DEPLETED_MINE, new JigsawStructure(
			new Structure.StructureSettings.Builder(biomes.getOrThrow(UGTags.Biomes.HAS_DEPLETED_MINE))
				.spawnOverrides(
					Map.of(
						MobCategory.MONSTER,
						new StructureSpawnOverride(
							StructureSpawnOverride.BoundingBoxType.PIECE,
							WeightedList.of(new MobSpawnSettings.SpawnerData(UGEntityTypes.FORGOTTEN.get(), 1, 1))
						)
					)
				)
				.generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
				.terrainAdapation(TerrainAdjustment.ENCAPSULATE)
				.build(),
			pools.getOrThrow(DEPLETED_MINE_WALKWAY_POOL),
			Optional.empty(),
			10,
			UniformHeight.of(VerticalAnchor.absolute(-54), VerticalAnchor.absolute(-31)),
			false,
			Optional.empty(),
			new JigsawStructure.MaxDistance(80),
			List.of(),
			new DimensionPadding(10),
			LiquidSettings.IGNORE_WATERLOGGING
		));
	}

	public static void bootstrapSets(BootstrapContext<StructureSet> context) {
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);
		context.register(CATACOMBS_SET, new StructureSet(structures.getOrThrow(CATACOMBS), new RandomSpreadStructurePlacement(24, 12, RandomSpreadType.LINEAR, 276320045)));
		context.register(FORGOTTEN_VESTIGE_SET, new StructureSet(structures.getOrThrow(FORGOTTEN_VESTIGE), new RandomSpreadStructurePlacement(Vec3i.ZERO, StructurePlacement.FrequencyReductionMethod.DEFAULT, 0.85F, 276320046, Optional.empty(), 3, 1, RandomSpreadType.LINEAR)));
		context.register(DENIZEN_CAMP_SET, new StructureSet(structures.getOrThrow(DENIZEN_CAMP), new RandomSpreadStructurePlacement(12, 6, RandomSpreadType.LINEAR, 27630047)));
		context.register(DEPLETED_MINE_SET, new StructureSet(structures.getOrThrow(DEPLETED_MINE), new RandomSpreadStructurePlacement(Vec3i.ZERO, StructurePlacement.FrequencyReductionMethod.LEGACY_TYPE_3, 0.004F, 50002673, Optional.empty(), 1, 0, RandomSpreadType.LINEAR)));
	}

	public static void bootstrapPools(BootstrapContext<StructureTemplatePool> context) {
		Holder<StructureTemplatePool> emptyPool = context.lookup(Registries.TEMPLATE_POOL).getOrThrow(Pools.EMPTY);
		HolderGetter<StructureProcessorList> processors = context.lookup(Registries.PROCESSOR_LIST);

		context.register(CATACOMBS_START, new StructureTemplatePool(emptyPool, ImmutableList.of(
			Pair.of(StructurePoolElement.single(name("catacombs/entrance1"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/entrance2"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/entrance3"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/entrance4"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/entrance5"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1)
		), StructureTemplatePool.Projection.RIGID));
		context.register(CATACOMBS_STAIR, new StructureTemplatePool(emptyPool, ImmutableList.of(
			Pair.of(StructurePoolElement.single(name("catacombs/stair"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1)
		), StructureTemplatePool.Projection.RIGID));
		context.register(CATACOMBS_CHEST, new StructureTemplatePool(emptyPool, ImmutableList.of(
			Pair.of(StructurePoolElement.single("minecraft:empty"), 2),
			Pair.of(StructurePoolElement.single(name("catacombs/chest")), 2),
			Pair.of(StructurePoolElement.single(name("catacombs/chest_forgotten")), 1)
		), StructureTemplatePool.Projection.RIGID));
		context.register(CATACOMBS_INTERIOR, new StructureTemplatePool(emptyPool, ImmutableList.of(
			//Pair.of(StructurePoolElement.single(name("catacombs/interior1"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/interior2"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/interior3"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			//Pair.of(StructurePoolElement.single(name("catacombs/interior4"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/interior4_new"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/interior_altar"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/interior_grass"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/interior_jail"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/interior_morgue"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1)
		), StructureTemplatePool.Projection.RIGID));
		context.register(CATACOMBS_TUNNEL, new StructureTemplatePool(context.lookup(Registries.TEMPLATE_POOL).getOrThrow(CATACOMBS_TUNNEL_FALLBACK), ImmutableList.of(
			//Pair.of(StructurePoolElement.single(name("catacombs/way_pool"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel1"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel2"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			//Pair.of(StructurePoolElement.single(name("catacombs/tunnel3"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel3_new"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel4"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel5"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel6"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel7"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),

			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_altar"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_altar2"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_altar_shiverstone"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_bend_altar"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_bend_candle_grass"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_bend_dirt"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_bend_grass_bars"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_bend_water"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_candle"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_candle_dirt"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_chiseled"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_cloggrum"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_dirt"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_grass"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_grass_dirt"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_grass_pillars"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_hidden"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_morgue1"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_morgue2"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_rock"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/altar"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/altar_pool"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),

			Pair.of(StructurePoolElement.single(name("catacombs/room1"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/room2"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/room3"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/room4"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/room5"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),

			Pair.of(StructurePoolElement.single(name("catacombs/3way_candle"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/3way_candle_grass"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/3way_chiseled"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/3way_cloggrum"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/3way_dirt"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/3way_morgue"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/big3way_intersection"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/big3way_intersection_grass"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/big_bend_morgue"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),

			Pair.of(StructurePoolElement.single(name("catacombs/4way_arches"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/4way_arches2"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/4way_arches3"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/4way_cross"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/4way_smog_vent"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),

			Pair.of(StructurePoolElement.single(name("catacombs/intersection_altar"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/intersection_dirt"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/intersection_grass_pillars"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),

			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_guardian"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1)
		), StructureTemplatePool.Projection.RIGID));
		context.register(CATACOMBS_TUNNEL_FALLBACK, new StructureTemplatePool(emptyPool, ImmutableList.of(
			Pair.of(StructurePoolElement.single(name("catacombs/tunnel_dead_end"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1)
		), StructureTemplatePool.Projection.RIGID));
		context.register(CATACOMBS_WAY, new StructureTemplatePool(emptyPool, ImmutableList.of(
			//Pair.of(StructurePoolElement.singlename("catacombs/entrance"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/4way"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/3way"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/2way"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("catacombs/1way"), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(FORGOTTEN_VESTIGE_POOL, new StructureTemplatePool(emptyPool, ImmutableList.of(
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/aqueduct_1"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/aqueduct_2"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/aqueduct_3"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/arch_1"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/arch_2"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/arch_3"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/arch_4"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/arch_5"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/arch_6"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/face_1"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/face_2"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/face_3"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/house_1"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/house_2"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/house_3"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/house_4"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/house_5"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock/house_6"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/shiverstone/arch_1"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/shiverstone/arch_2"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/shiverstone/arch_3"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/shiverstone/arch_4"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/shiverstone/arch_5"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/shiverstone/arch_6"), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(FORGOTTEN_VESTIGE_HOUSE_POT_POOL, new StructureTemplatePool(emptyPool, ImmutableList.of(
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/depthrock_pot")), 2),
			Pair.of(StructurePoolElement.single(name("forgotten_vestige/mysterious_pot")), 1),
			Pair.of(StructurePoolElement.single("minecraft:empty"), 3)
		), StructureTemplatePool.Projection.RIGID));

		context.register(DENIZEN_CAMP_TOTEM_CIRCLE_POOL, new StructureTemplatePool(emptyPool, ImmutableList.of(
			Pair.of(StructurePoolElement.single(name("denizen_camp/totem_circle/circle_1"), processors.getOrThrow(DENIZEN_CAMP_WOOD_PROCESSOR)), 1),
			Pair.of(StructurePoolElement.single(name("denizen_camp/totem_circle/circle_2"), processors.getOrThrow(DENIZEN_CAMP_WOOD_PROCESSOR)), 1)
		), StructureTemplatePool.Projection.RIGID));
		context.register(DENIZEN_CAMP_TOTEM_POOL, new StructureTemplatePool(emptyPool, ImmutableList.of(
			Pair.of(StructurePoolElement.single("minecraft:empty"), 1),
			Pair.of(StructurePoolElement.single(name("denizen_camp/totem/totem_1"), processors.getOrThrow(DENIZEN_CAMP_WOOD_PROCESSOR)), 1),
			Pair.of(StructurePoolElement.single(name("denizen_camp/totem/totem_2"), processors.getOrThrow(DENIZEN_CAMP_WOOD_PROCESSOR)), 1)
		), StructureTemplatePool.Projection.RIGID));
		context.register(DENIZEN_CAMP_ROAD_POOL, new StructureTemplatePool(emptyPool, ImmutableList.of(
			Pair.of(StructurePoolElement.single(name("denizen_camp/totem_circle/circle_1"), processors.getOrThrow(DENIZEN_CAMP_WOOD_PROCESSOR)), 1),
			Pair.of(StructurePoolElement.single(name("denizen_camp/totem_circle/circle_2"), processors.getOrThrow(DENIZEN_CAMP_WOOD_PROCESSOR)), 1),
			Pair.of(StructurePoolElement.single(name("denizen_camp/road/road_straight_1"), processors.getOrThrow(DENIZEN_CAMP_ROAD_PROCESSOR)), 3),
			Pair.of(StructurePoolElement.single(name("denizen_camp/road/road_straight_2"), processors.getOrThrow(DENIZEN_CAMP_ROAD_PROCESSOR)), 3),
			Pair.of(StructurePoolElement.single(name("denizen_camp/road/road_straight_3"), processors.getOrThrow(DENIZEN_CAMP_ROAD_PROCESSOR)), 3),
			Pair.of(StructurePoolElement.single(name("denizen_camp/road/road_straight_4"), processors.getOrThrow(DENIZEN_CAMP_ROAD_PROCESSOR)), 3),
			Pair.of(StructurePoolElement.single(name("denizen_camp/road/road_turn_1"), processors.getOrThrow(DENIZEN_CAMP_ROAD_PROCESSOR)), 3),
			Pair.of(StructurePoolElement.single(name("denizen_camp/road/road_turn_2"), processors.getOrThrow(DENIZEN_CAMP_ROAD_PROCESSOR)), 3),
			Pair.of(StructurePoolElement.single(name("denizen_camp/road/road_turn_3"), processors.getOrThrow(DENIZEN_CAMP_ROAD_PROCESSOR)), 3),
			Pair.of(StructurePoolElement.single(name("denizen_camp/road/road_turn_4"), processors.getOrThrow(DENIZEN_CAMP_ROAD_PROCESSOR)), 3)
		), StructureTemplatePool.Projection.RIGID));
		context.register(DENIZEN_CAMP_HANGOUT_POOL, new StructureTemplatePool(emptyPool, ImmutableList.of(
			Pair.of(StructurePoolElement.single(name("denizen_camp/hangout/campfire_1")), 1),
			Pair.of(StructurePoolElement.single(name("denizen_camp/hangout/campfire_2")), 1),
			Pair.of(StructurePoolElement.single(name("denizen_camp/hangout/campfire_3")), 1),
			Pair.of(StructurePoolElement.single(name("denizen_camp/hangout/campfire_4")), 1),
			Pair.of(StructurePoolElement.single(name("denizen_camp/hangout/campfire_5")), 1),
			Pair.of(StructurePoolElement.single(name("denizen_camp/hangout/storage_1"), processors.getOrThrow(DENIZEN_CAMP_WOOD_PROCESSOR)), 1),
			Pair.of(StructurePoolElement.single(name("denizen_camp/hangout/storage_2"), processors.getOrThrow(DENIZEN_CAMP_WOOD_PROCESSOR)), 1),
			Pair.of(StructurePoolElement.single(name("denizen_camp/hangout/storage_3"), processors.getOrThrow(DENIZEN_CAMP_WOOD_PROCESSOR)), 1),
			Pair.of(StructurePoolElement.single(name("denizen_camp/hangout/dweller_farm")), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(DEPLETED_MINE_WALKWAY_POOL, new StructureTemplatePool(emptyPool, ImmutableList.of(
			Pair.of(StructurePoolElement.single(name("depleted_mine/walkway/3way_1"), processors.getOrThrow(DEPLETED_MINE_LANTERN_PROCESSOR)), 1),
			Pair.of(StructurePoolElement.single(name("depleted_mine/walkway/4way_1"), processors.getOrThrow(DEPLETED_MINE_LANTERN_PROCESSOR)), 1),
			Pair.of(StructurePoolElement.single(name("depleted_mine/walkway/stair_1"), processors.getOrThrow(DEPLETED_MINE_LANTERN_PROCESSOR)), 1),
			Pair.of(StructurePoolElement.single(name("depleted_mine/walkway/stair_2"), processors.getOrThrow(DEPLETED_MINE_LANTERN_PROCESSOR)), 1),
			Pair.of(StructurePoolElement.single(name("depleted_mine/walkway/straight_1"), processors.getOrThrow(DEPLETED_MINE_LANTERN_PROCESSOR)), 1),
			Pair.of(StructurePoolElement.single(name("depleted_mine/walkway/straight_2"), processors.getOrThrow(DEPLETED_MINE_LANTERN_PROCESSOR)), 1),
			Pair.of(StructurePoolElement.single(name("depleted_mine/walkway/straight_3"), processors.getOrThrow(DEPLETED_MINE_LANTERN_PROCESSOR)), 1),
			Pair.of(StructurePoolElement.single(name("depleted_mine/walkway/turn_1"), processors.getOrThrow(DEPLETED_MINE_LANTERN_PROCESSOR)), 2),
			Pair.of(StructurePoolElement.single(name("depleted_mine/walkway/turn_2"), processors.getOrThrow(DEPLETED_MINE_LANTERN_PROCESSOR)), 2)
		), StructureTemplatePool.Projection.RIGID));
		context.register(DEPLETED_MINE_CLUTTER_POOL, new StructureTemplatePool(emptyPool, ImmutableList.of(
			Pair.of(StructurePoolElement.single("minecraft:empty"), 3),
			Pair.of(StructurePoolElement.single(name("depleted_mine/clutter/chest_1")), 1),
			Pair.of(StructurePoolElement.single(name("depleted_mine/clutter/dirt_mound_1")), 1),
			Pair.of(StructurePoolElement.single(name("depleted_mine/clutter/dirt_mound_2")), 1),
			Pair.of(StructurePoolElement.single(name("depleted_mine/clutter/dirt_mound_3")), 1),
			Pair.of(StructurePoolElement.single(name("depleted_mine/clutter/dreadrock_mound_1")), 1),
			Pair.of(StructurePoolElement.single(name("depleted_mine/clutter/dreadrock_mound_2")), 1),
			Pair.of(StructurePoolElement.single(name("depleted_mine/clutter/dreadrock_mound_3")), 1),
			Pair.of(StructurePoolElement.single(name("depleted_mine/clutter/logs_1")), 1),
			Pair.of(StructurePoolElement.single(name("depleted_mine/clutter/logs_2")), 1)
		), StructureTemplatePool.Projection.RIGID));
	}

	public static void bootstrapProcessors(BootstrapContext<StructureProcessorList> context) {
		context.register(CATACOMBS_DEGRADATION, new StructureProcessorList(ImmutableList.of(
			new RuleProcessor(ImmutableList.of(
				new ProcessorRule(
					new RandomBlockMatchTest(UGBlocks.DEPTHROCK_BRICKS.get(), 0.5F),
					AlwaysTrueTest.INSTANCE,
					UGBlocks.CRACKED_DEPTHROCK_BRICKS.get().defaultBlockState()
				)
			))
		)));

		context.register(FORGOTTEN_VESTIGE_DEGRADATION, new StructureProcessorList(ImmutableList.of(
			new RuleProcessor(ImmutableList.of(
				new ProcessorRule(
					new RandomBlockMatchTest(UGBlocks.DEPTHROCK_BRICKS.get(), 0.25F),
					AlwaysTrueTest.INSTANCE,
					UGBlocks.CRACKED_DEPTHROCK_BRICKS.get().defaultBlockState()
				),
				new ProcessorRule(
					new RandomBlockMatchTest(UGBlocks.SHIVERSTONE_BRICKS.get(), 0.25F),
					AlwaysTrueTest.INSTANCE,
					UGBlocks.CRACKED_SHIVERSTONE_BRICKS.get().defaultBlockState()
				),
				new ProcessorRule(
					new RandomBlockMatchTest(UGBlocks.CLOGGRUM_BLOCK.get(), 0.5F),
					AlwaysTrueTest.INSTANCE,
					UGBlocks.RAW_CLOGGRUM_BLOCK.get().defaultBlockState()
				)
			))
		)));

		context.register(DENIZEN_CAMP_ROAD_PROCESSOR, new StructureProcessorList(ImmutableList.of(
			new RuleProcessor(ImmutableList.of(
				new ProcessorRule(
					new RandomBlockMatchTest(UGBlocks.COARSE_DEEPSOIL.get(), 0.25F),
					AlwaysTrueTest.INSTANCE,
					UGBlocks.DREADROCK.get().defaultBlockState()
				)
			))
		)));
		context.register(DENIZEN_CAMP_WOOD_PROCESSOR, new StructureProcessorList(ImmutableList.of(
			new RuleProcessor(ImmutableList.of(
				new ProcessorRule(
					new RandomBlockMatchTest(UGBlocks.ANCIENT_ROOT_PLANKS.get(), 0.5F),
					AlwaysTrueTest.INSTANCE,
					UGBlocks.ANCIENT_ROOT.get().defaultBlockState()
				),
				new ProcessorRule(
					new RandomBlockMatchTest(UGBlocks.ANCIENT_ROOT.get(), 0.25F),
					AlwaysTrueTest.INSTANCE,
					UGBlocks.ANCIENT_ROOT_PLANKS.get().defaultBlockState()
				)
			))
		)));

		context.register(DEPLETED_MINE_LANTERN_PROCESSOR, new StructureProcessorList(ImmutableList.of(
			new RuleProcessor(ImmutableList.of(
				new ProcessorRule(
					new RandomBlockMatchTest(UGBlocks.CLOGGRUM_LANTERN.get(), 0.75F),
					AlwaysTrueTest.INSTANCE,
					Blocks.AIR.defaultBlockState()
				)
			))
		)));
	}

	private static String name(String name) {
		return Undergarden.prefix(name).toString();
	}
}