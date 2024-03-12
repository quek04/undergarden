package quek.undergarden.registry;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import quek.undergarden.Undergarden;
import quek.undergarden.block.BlisterberryBushBlock;
import quek.undergarden.block.DepthrockPebblesBlock;
import quek.undergarden.block.DitchbulbBlock;
import quek.undergarden.block.UnderbeanBushBlock;
import quek.undergarden.world.gen.foliageplacer.VeilFoliagePlacer;
import quek.undergarden.world.gen.treedecorator.GrongleLeafDecorator;
import quek.undergarden.world.gen.treedecorator.GrongletTrunkDecorator;
import quek.undergarden.world.gen.treedecorator.ReplaceLeafDecorator;
import quek.undergarden.world.gen.trunkplacer.SingleForkingTrunkPlacer;
import quek.undergarden.world.gen.trunkplacer.SmogstemTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class UGConfiguredFeatures {

	//ore tags
	public static final RuleTest BASE_STONE_UNDERGARDEN = new TagMatchTest(UGTags.Blocks.BASE_STONE_UNDERGARDEN);
	public static final RuleTest DEPTHROCK_ORE_REPLACEABLES = new TagMatchTest(UGTags.Blocks.DEPTHROCK_ORE_REPLACEABLES);
	public static final RuleTest SHIVERSTONE_ORE_REPLACEABLES = new TagMatchTest(UGTags.Blocks.SHIVERSTONE_ORE_REPLACEABLES);
	public static final RuleTest DREADROCK_ORE_REPLACEABLES = new TagMatchTest(UGTags.Blocks.DREADROCK_ORE_REPLACEABLES);
	public static final RuleTest TREMBLECRUST_ORE_REPLACEABLES = new TagMatchTest(UGTags.Blocks.TREMBLECRUST_ORE_REPLACEABLES);

	//ores
	public static final ResourceKey<ConfiguredFeature<?, ?>> COAL_ORE = create("coal_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> IRON_ORE = create("iron_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> GOLD_ORE = create("gold_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DIAMOND_ORE = create("diamond_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CLOGGRUM_ORE = create("cloggrum_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> FROSTSTEEL_ORE = create("froststeel_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ROGDORIUM_ORE = create("rogdorium_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> UTHERIUM_ORE = create("utherium_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> REGALIUM_ORE = create("regalium_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SHIVERSTONE_ORE = create("shiverstone_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSOIL_ORE = create("deepsoil_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ICE_ORE = create("ice_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SEDIMENT_ORE = create("sediment_ore");

	//deltas
	public static final ResourceKey<ConfiguredFeature<?, ?>> BOG_DELTA = create("bog_delta");
	public static final ResourceKey<ConfiguredFeature<?, ?>> GRONGLEGROWTH_DELTA = create("gronglegrowth_delta");

	//vegetation
	public static final ResourceKey<ConfiguredFeature<?, ?>> AMOROUS_BRISTLE_PATCH = create("amorous_bristle_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> MISERABELL_PATCH = create("miserabell_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> BUTTERBUNCH_PATCH = create("butterbunch_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPTURF_PATCH = create("deepturf_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ASHEN_DEEPTURF_PATCH = create("ashen_deepturf_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> FROZEN_DEEPTURF_PATCH = create("frozen_deepturf_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SHIMMERWEED_PATCH = create("shimmerweed_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DEPTHROCK_PEBBLE_PATCH = create("depthrock_pebble_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DITCHBULB_PATCH = create("ditchbulb_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_DEEPTURF_PATCH = create("tall_deepturf_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_SHIMMERWEED_PATCH = create("tall_shimmerweed_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> INDIGO_MUSHROOM_PATCH = create("indigo_mushroom_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> VEIL_MUSHROOM_PATCH = create("veil_mushroom_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> INK_MUSHROOM_PATCH = create("ink_mushroom_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> BLOOD_MUSHROOM_PATCH = create("blood_mushroom_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> UNDERBEAN_BUSH_PATCH = create("underbean_bush_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> BLISTERBERRY_BUSH_PATCH = create("blisterberry_bush_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> GLOOMGOURD_PATCH = create("gloomgourd_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DROOPVINE = create("droopvine");
	public static final ResourceKey<ConfiguredFeature<?, ?>> GLITTERKELP = create("glitterkelp");

	//tree
	public static final ResourceKey<ConfiguredFeature<?, ?>> SMOGSTEM_TREE = create("smogstem_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WIDE_SMOGSTEM_TREE = create("wide_smogstem_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_SMOGSTEM_TREE = create("tall_smogstem_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SMOGSTEM_BUSH = create("smogstem_bush");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WIGGLEWOOD_TREE = create("wigglewood_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_WIGGLEWOOD_TREE = create("tall_wigglewood_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> GRONGLE_TREE = create("grongle_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_GRONGLE_TREE = create("small_grongle_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> GRONGLE_BUSH = create("grongle_bush");

	//huge mushrooms
	public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_INDIGO_MUSHROOM = create("huge_indigo_mushroom");
	public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_VEIL_MUSHROOM = create("huge_veil_mushroom");
	public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_INK_MUSHROOM = create("huge_ink_mushroom");
	public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_BLOOD_MUSHROOM = create("huge_blood_mushroom");

	//rocks
	public static final ResourceKey<ConfiguredFeature<?, ?>> DEPTHROCK_ROCK = create("depthrock_rock");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SHIVERSTONE_ROCK = create("shiverstone_rock");

	//misc
	public static final ResourceKey<ConfiguredFeature<?, ?>> SMOG_VENT = create("smog_vent");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ICE_PILLAR = create("ice_pillar");
	public static final ResourceKey<ConfiguredFeature<?, ?>> UTHERIUM_GROWTH_COLUMNS = create("utherium_growth_columns");

	public static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Undergarden.MODID, name));
	}

	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		context.register(COAL_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_COAL_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_COAL_ORE.get().defaultBlockState())), 17)));
		context.register(IRON_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_IRON_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_IRON_ORE.get().defaultBlockState())), 9, 0.5F)));
		context.register(GOLD_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_GOLD_ORE.get().defaultBlockState())), 9, 0.5F)));
		context.register(DIAMOND_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_DIAMOND_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_DIAMOND_ORE.get().defaultBlockState())), 8, 0.5F)));
		context.register(CLOGGRUM_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get().defaultBlockState())), 9)));
		context.register(FROSTSTEEL_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get().defaultBlockState())), 9)));
		context.register(ROGDORIUM_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DREADROCK_ORE_REPLACEABLES, UGBlocks.DREADROCK_ROGDORIUM_ORE.get().defaultBlockState())), 9)));
		context.register(UTHERIUM_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_UTHERIUM_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get().defaultBlockState()), OreConfiguration.target(TREMBLECRUST_ORE_REPLACEABLES, UGBlocks.TREMBLECRUST_UTHERIUM_ORE.get().defaultBlockState()), OreConfiguration.target(DREADROCK_ORE_REPLACEABLES, UGBlocks.DREADROCK_UTHERIUM_ORE.get().defaultBlockState())), 8, 0.5F)));
		context.register(REGALIUM_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_REGALIUM_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_REGALIUM_ORE.get().defaultBlockState())), 4)));
		context.register(SHIVERSTONE_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(BASE_STONE_UNDERGARDEN, UGBlocks.SHIVERSTONE.get().defaultBlockState(), 33)));
		context.register(DEEPSOIL_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(BASE_STONE_UNDERGARDEN, UGBlocks.DEEPSOIL.get().defaultBlockState(), 33)));
		context.register(ICE_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(BASE_STONE_UNDERGARDEN, Blocks.PACKED_ICE.defaultBlockState(), 33)));
		context.register(SEDIMENT_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(BASE_STONE_UNDERGARDEN, UGBlocks.SEDIMENT.get().defaultBlockState(), 33)));

		//deltas
		context.register(BOG_DELTA, new ConfiguredFeature<>(UGFeatures.DELTA.get(), new DeltaFeatureConfiguration(UGBlocks.VIRULENT_MIX.get().defaultBlockState(), UGBlocks.COARSE_DEEPSOIL.get().defaultBlockState(), UniformInt.of(6, 8), UniformInt.of(2, 4))));
		context.register(GRONGLEGROWTH_DELTA, new ConfiguredFeature<>(UGFeatures.DELTA.get(), new DeltaFeatureConfiguration(Blocks.WATER.defaultBlockState(), UGBlocks.SEDIMENT.get().defaultBlockState(), UniformInt.of(3, 4), UniformInt.of(2, 4))));

		//vegetation
		context.register(AMOROUS_BRISTLE_PATCH, new ConfiguredFeature<>(Feature.FLOWER, patch(UGBlocks.AMOROUS_BRISTLE.get(), 64)));
		context.register(MISERABELL_PATCH, new ConfiguredFeature<>(Feature.FLOWER, patch(UGBlocks.MISERABELL.get(), 64)));
		context.register(BUTTERBUNCH_PATCH, new ConfiguredFeature<>(Feature.FLOWER, patch(UGBlocks.BUTTERBUNCH.get(), 64)));
		context.register(DEEPTURF_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.DEEPTURF.get(), 64)));
		context.register(ASHEN_DEEPTURF_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.ASHEN_DEEPTURF.get(), 64)));
		context.register(FROZEN_DEEPTURF_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.FROZEN_DEEPTURF.get(), 64)));
		context.register(SHIMMERWEED_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.SHIMMERWEED.get(), 32)));
		context.register(DEPTHROCK_PEBBLE_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH, pebble(UGBlocks.DEPTHROCK_PEBBLES.get(), List.of(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get(), UGBlocks.SEDIMENT.get(), UGBlocks.COARSE_DEEPSOIL.get()))));
		context.register(DITCHBULB_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.DITCHBULB_PLANT.get().defaultBlockState().setValue(DitchbulbBlock.AGE, 1), 16, List.of(UGBlocks.DEPTHROCK.get()))));
		context.register(TALL_DEEPTURF_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.TALL_DEEPTURF.get(), 32)));
		context.register(TALL_SHIMMERWEED_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.TALL_SHIMMERWEED.get(), 32)));
		context.register(INDIGO_MUSHROOM_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.INDIGO_MUSHROOM.get(), 64)));
		context.register(VEIL_MUSHROOM_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.VEIL_MUSHROOM.get(), 64)));
		context.register(INK_MUSHROOM_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.INK_MUSHROOM.get(), 64)));
		context.register(BLOOD_MUSHROOM_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.BLOOD_MUSHROOM.get(), 64)));
		context.register(UNDERBEAN_BUSH_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.UNDERBEAN_BUSH.get().defaultBlockState().setValue(UnderbeanBushBlock.AGE, 3), 64, List.of(UGBlocks.DEEPTURF_BLOCK.get()))));
		context.register(BLISTERBERRY_BUSH_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.BLISTERBERRY_BUSH.get().defaultBlockState().setValue(BlisterberryBushBlock.AGE, 3), 64, List.of(UGBlocks.ASHEN_DEEPTURF_BLOCK.get()))));
		context.register(GLOOMGOURD_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.GLOOMGOURD.get(), 16, List.of(UGBlocks.DEEPTURF_BLOCK.get()))));
		context.register(DROOPVINE, new ConfiguredFeature<>(UGFeatures.DROOPVINE.get(), FeatureConfiguration.NONE));
		context.register(GLITTERKELP, new ConfiguredFeature<>(UGFeatures.GLITTERKELP.get(), FeatureConfiguration.NONE));

		//tree
		context.register(SMOGSTEM_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.SMOGSTEM_LOG.get()), new SmogstemTrunkPlacer(10, 2, 2, 1), BlockStateProvider.simple(UGBlocks.SMOGSTEM_LEAVES.get()), new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
		context.register(WIDE_SMOGSTEM_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.SMOGSTEM_LOG.get()), new SmogstemTrunkPlacer(10, 2, 2, 2), BlockStateProvider.simple(UGBlocks.SMOGSTEM_LEAVES.get()), new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
		context.register(TALL_SMOGSTEM_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.SMOGSTEM_LOG.get()), new SmogstemTrunkPlacer(15, 4, 4, 2), BlockStateProvider.simple(UGBlocks.SMOGSTEM_LEAVES.get()), new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
		context.register(SMOGSTEM_BUSH, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.SMOGSTEM_LOG.get()), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(UGBlocks.SMOGSTEM_LEAVES.get()), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(0, 0, 0)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
		context.register(WIGGLEWOOD_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.WIGGLEWOOD_LOG.get()), new ForkingTrunkPlacer(3, 1, 1), BlockStateProvider.simple(UGBlocks.WIGGLEWOOD_LEAVES.get()), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 0), new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
		context.register(TALL_WIGGLEWOOD_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.WIGGLEWOOD_LOG.get()), new ForkingTrunkPlacer(6, 1, 1), BlockStateProvider.simple(UGBlocks.WIGGLEWOOD_LEAVES.get()), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 0), new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
		context.register(GRONGLE_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.GRONGLE_LOG.get()), new MegaJungleTrunkPlacer(10, 2, 19), BlockStateProvider.simple(UGBlocks.GRONGLE_LEAVES.get()), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).decorators(ImmutableList.of(GrongleLeafDecorator.INSTANCE, GrongletTrunkDecorator.INSTANCE)).build()));
		context.register(SMALL_GRONGLE_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.GRONGLE_LOG.get()), new StraightTrunkPlacer(5, 2, 19), BlockStateProvider.simple(UGBlocks.GRONGLE_LEAVES.get()), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).decorators(ImmutableList.of(GrongleLeafDecorator.INSTANCE, GrongletTrunkDecorator.INSTANCE)).build()));
		context.register(GRONGLE_BUSH, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.GRONGLE_LOG.get()), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(UGBlocks.GRONGLE_LEAVES.get()), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new TwoLayersFeatureSize(0, 0, 0)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));

		//huge mushrooms
		context.register(HUGE_INDIGO_MUSHROOM, new ConfiguredFeature<>(Feature.HUGE_BROWN_MUSHROOM, new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(UGBlocks.INDIGO_MUSHROOM_CAP.get().defaultBlockState()), BlockStateProvider.simple(UGBlocks.INDIGO_MUSHROOM_STEM.get().defaultBlockState()), 3)));
		context.register(HUGE_VEIL_MUSHROOM, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.VEIL_MUSHROOM_STEM.get()), new StraightTrunkPlacer(9, 1, 1), BlockStateProvider.simple(UGBlocks.VEIL_MUSHROOM_CAP.get()), new VeilFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 1)).dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
		context.register(HUGE_INK_MUSHROOM, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.INK_MUSHROOM_STEM.get()), new SingleForkingTrunkPlacer(6, 2, 2), BlockStateProvider.simple(UGBlocks.INK_MUSHROOM_CAP.get()), new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).decorators(ImmutableList.of(new AttachedToLeavesDecorator(0.2F, 1, 0, BlockStateProvider.simple(UGBlocks.SEEPING_INK.get()), 1, List.of(Direction.DOWN)))).build()));
		context.register(HUGE_BLOOD_MUSHROOM, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.BLOOD_MUSHROOM_STEM.get()), new DarkOakTrunkPlacer(6, 2, 2), BlockStateProvider.simple(UGBlocks.BLOOD_MUSHROOM_CAP.get()), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())).dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).decorators(ImmutableList.of(new ReplaceLeafDecorator(0.2F, BlockStateProvider.simple(UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP.get())))).build()));

		//rocks
		context.register(DEPTHROCK_ROCK, new ConfiguredFeature<>(Feature.FOREST_ROCK, new BlockStateConfiguration(UGBlocks.DEPTHROCK.get().defaultBlockState())));
		context.register(SHIVERSTONE_ROCK, new ConfiguredFeature<>(Feature.FOREST_ROCK, new BlockStateConfiguration(UGBlocks.SHIVERSTONE.get().defaultBlockState())));

		//misc
		context.register(SMOG_VENT, new ConfiguredFeature<>(UGFeatures.SMOG_VENT.get(), FeatureConfiguration.NONE));
		context.register(ICE_PILLAR, new ConfiguredFeature<>(UGFeatures.ICE_PILLAR.get(), FeatureConfiguration.NONE));
		context.register(UTHERIUM_GROWTH_COLUMNS, new ConfiguredFeature<>(UGFeatures.UTHERIUM_GROWTH_COLUMNS.get(), new ColumnFeatureConfiguration(UniformInt.of(1, 3), UniformInt.of(5, 10))));
	}

	private static RandomPatchConfiguration patch(Block block, int tries) {
		return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block))));
	}

	private static RandomPatchConfiguration patch(Block block, int tries, List<Block> whitelist) {
		return FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block)), whitelist, tries);
	}

	private static RandomPatchConfiguration pebble(Block block, List<Block> whitelist) {
		return FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new RandomizedIntStateProvider(BlockStateProvider.simple(block), DepthrockPebblesBlock.PEBBLES, UniformInt.of(1, 2))), whitelist, 32);
	}

	private static RandomPatchConfiguration patch(BlockState block, int tries, List<Block> whitelist) {
		return FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block)), whitelist, tries);
	}
}