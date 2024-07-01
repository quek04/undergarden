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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
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

	public static final ResourceKey<Structure> CATACOMBS = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs"));
	public static final ResourceKey<StructureSet> CATACOMBS_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs"));

	public static final ResourceKey<StructureTemplatePool> CATACOMBS_START = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/catacombs_entrance"));
	public static final ResourceKey<StructureTemplatePool> CATACOMBS_CHEST = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/chest_pool"));
	public static final ResourceKey<StructureTemplatePool> CATACOMBS_INTERIOR = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/interior_pool"));
	public static final ResourceKey<StructureTemplatePool> CATACOMBS_TUNNEL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/tunnel_pool"));
	public static final ResourceKey<StructureTemplatePool> CATACOMBS_WAY = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/way_pool"));

	public static final ResourceKey<StructureProcessorList> CATACOMBS_DEGRADATION = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs_degradation"));

	public static final ResourceKey<Structure> FORGOTTEN_VESTIGE = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige"));
	public static final ResourceKey<StructureSet> FORGOTTEN_VESTIGE_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige"));

	public static final ResourceKey<StructureTemplatePool> FORGOTTEN_VESTIGE_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige"));

	public static final ResourceKey<StructureProcessorList> FORGOTTEN_VESTIGE_DEGRADATION = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige_degradation"));


	public static void bootstrapStructures(BootstrapContext<Structure> context) {
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
		HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);

		context.register(CATACOMBS, new BiggerJigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(UGTags.Biomes.HAS_CATACOMBS),
				Map.of(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(new MobSpawnSettings.SpawnerData(UGEntityTypes.FORGOTTEN.get(), 1, 1, 1)))),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.BEARD_THIN),
			pools.getOrThrow(CATACOMBS_START),
			Optional.empty(),
			25,
			ConstantHeight.of(VerticalAnchor.aboveBottom(48)),
			Optional.empty(),
			116,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.IGNORE_WATERLOGGING
		));
		context.register(FORGOTTEN_VESTIGE, new BiggerJigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(UGTags.Biomes.HAS_FORGOTTEN_VESTIGE),
				Map.of(),
				GenerationStep.Decoration.UNDERGROUND_STRUCTURES,
				TerrainAdjustment.BEARD_THIN),
			pools.getOrThrow(FORGOTTEN_VESTIGE_POOL),
			Optional.empty(),
			5,
			UniformHeight.of(VerticalAnchor.absolute(32), VerticalAnchor.TOP),
			Optional.empty(),
			10,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.APPLY_WATERLOGGING
		));
	}

	public static void bootstrapSets(BootstrapContext<StructureSet> context) {
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);
		context.register(CATACOMBS_SET, new StructureSet(structures.getOrThrow(CATACOMBS), new RandomSpreadStructurePlacement(24, 12, RandomSpreadType.LINEAR, 276320045)));
		context.register(FORGOTTEN_VESTIGE_SET, new StructureSet(structures.getOrThrow(FORGOTTEN_VESTIGE), new RandomSpreadStructurePlacement(Vec3i.ZERO, StructurePlacement.FrequencyReductionMethod.DEFAULT, 0.85F, 276320046, Optional.empty(), 6, 3, RandomSpreadType.LINEAR)));
	}

	public static void bootstrapPools(BootstrapContext<StructureTemplatePool> context) {
		Holder<StructureTemplatePool> emptyPool = context.lookup(Registries.TEMPLATE_POOL).getOrThrow(Pools.EMPTY);
		HolderGetter<StructureProcessorList> processors = context.lookup(Registries.PROCESSOR_LIST);

		context.register(CATACOMBS_START, new StructureTemplatePool(emptyPool, ImmutableList.of(Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/entrance").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1)), StructureTemplatePool.Projection.RIGID));
		context.register(CATACOMBS_CHEST, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.single("minecraft:empty"), 2),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/chest").toString()), 2),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/chest_forgotten").toString()), 1)
		), StructureTemplatePool.Projection.RIGID));
		context.register(CATACOMBS_INTERIOR, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/interior1").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/interior2").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/interior3").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/interior4").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100)
		), StructureTemplatePool.Projection.RIGID));
		context.register(CATACOMBS_TUNNEL, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/way_pool").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/tunnel1").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/tunnel2").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/tunnel3").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 50),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/tunnel4").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 50),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/tunnel5").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/tunnel6").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/tunnel7").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 50),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/room1").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 50),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/room2").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/room3").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/tunnel_guardian").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 25)
		), StructureTemplatePool.Projection.RIGID));
		context.register(CATACOMBS_WAY, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/entrance").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/4way").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 25),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/3way").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 50),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/2way").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 75),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "catacombs/1way").toString(), processors.getOrThrow(CATACOMBS_DEGRADATION)), 100)
		), StructureTemplatePool.Projection.RIGID));

		context.register(FORGOTTEN_VESTIGE_POOL, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/depthrock/arch_1").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/depthrock/arch_2").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/depthrock/arch_3").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/depthrock/arch_4").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/depthrock/arch_5").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/depthrock/arch_6").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/depthrock/face_1").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/depthrock/face_2").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/depthrock/face_3").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/depthrock/house_1").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/depthrock/house_2").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/depthrock/house_3").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/depthrock/house_4").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/depthrock/house_5").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/depthrock/house_6").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/shiverstone/arch_1").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/shiverstone/arch_2").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/shiverstone/arch_3").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/shiverstone/arch_4").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/shiverstone/arch_5").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1),
				Pair.of(StructurePoolElement.single(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten_vestige/shiverstone/arch_6").toString(), processors.getOrThrow(FORGOTTEN_VESTIGE_DEGRADATION)), 1)
		), StructureTemplatePool.Projection.RIGID));
	}

	public static void bootstrapProcessors(BootstrapContext<StructureProcessorList> context) {
		context.register(CATACOMBS_DEGRADATION, new StructureProcessorList(List.of(
				new RuleProcessor(List.of(
						new ProcessorRule(
								new RandomBlockMatchTest(UGBlocks.DEPTHROCK_BRICKS.get(), 0.5F),
								AlwaysTrueTest.INSTANCE,
								UGBlocks.CRACKED_DEPTHROCK_BRICKS.get().defaultBlockState()
						)
				))
		)));

		context.register(FORGOTTEN_VESTIGE_DEGRADATION, new StructureProcessorList(List.of(
				new RuleProcessor(List.of(
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
	}
}