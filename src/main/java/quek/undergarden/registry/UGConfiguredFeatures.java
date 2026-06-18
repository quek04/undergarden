package quek.undergarden.registry;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Fluids;
import quek.undergarden.Undergarden;
import quek.undergarden.block.*;
import quek.undergarden.world.gen.feature.config.AncientRootConfiguration;
import quek.undergarden.world.gen.feature.config.UtheriumCrystalConfiguration;
import quek.undergarden.world.gen.foliageplacer.VeilFoliagePlacer;
import quek.undergarden.world.gen.treedecorator.GrongleLeafDecorator;
import quek.undergarden.world.gen.treedecorator.GrongletTrunkDecorator;
import quek.undergarden.world.gen.treedecorator.ReplaceLeafDecorator;
import quek.undergarden.world.gen.trunkplacer.SingleForkingTrunkPlacer;
import quek.undergarden.world.gen.trunkplacer.SmogstemTrunkPlacer;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class UGConfiguredFeatures {

	//ore tags
	public static final RuleTest BASE_STONE_UNDERGARDEN = new TagMatchTest(UGTags.Blocks.BASE_STONE_UNDERGARDEN);
	public static final RuleTest DEPTHROCK_ORE_REPLACEABLES = new TagMatchTest(UGTags.Blocks.DEPTHROCK_ORE_REPLACEABLES);
	public static final RuleTest SHIVERSTONE_ORE_REPLACEABLES = new TagMatchTest(UGTags.Blocks.SHIVERSTONE_ORE_REPLACEABLES);
	public static final RuleTest DREADROCK_ORE_REPLACEABLES = new TagMatchTest(UGTags.Blocks.DREADROCK_ORE_REPLACEABLES);
	public static final RuleTest TREMBLECRUST_ORE_REPLACEABLES = new TagMatchTest(UGTags.Blocks.TREMBLECRUST_ORE_REPLACEABLES);

	public static final RuleBasedStateProvider PLACE_BELOW_UNDERGARDEN_TRUNKS = RuleBasedStateProvider.ifTrueThenProvide(
		TreeConfiguration.CAN_PLACE_BELOW_OVERWORLD_TRUNKS, UGBlocks.DEEPSOIL.get()
	);

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
	public static final ResourceKey<ConfiguredFeature<?, ?>> AMOROUS_BRISTLE = create("amorous_bristle");
	public static final ResourceKey<ConfiguredFeature<?, ?>> MISERABELL = create("miserabell");
	public static final ResourceKey<ConfiguredFeature<?, ?>> BUTTERBUNCH = create("butterbunch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPTURF = create("deepturf");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ASHEN_DEEPTURF = create("ashen_deepturf");
	public static final ResourceKey<ConfiguredFeature<?, ?>> FROZEN_DEEPTURF = create("frozen_deepturf");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SHIMMERWEED = create("shimmerweed");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DEPTHROCK_PEBBLE = create("depthrock_pebble");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DITCHBULB = create("ditchbulb");
	public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_DEEPTURF = create("tall_deepturf");
	public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_SHIMMERWEED = create("tall_shimmerweed");
	public static final ResourceKey<ConfiguredFeature<?, ?>> INDIGO_MUSHROOM = create("indigo_mushroom");
	public static final ResourceKey<ConfiguredFeature<?, ?>> VEIL_MUSHROOM = create("veil_mushroom");
	public static final ResourceKey<ConfiguredFeature<?, ?>> INK_MUSHROOM = create("ink_mushroom");
	public static final ResourceKey<ConfiguredFeature<?, ?>> BLOOD_MUSHROOM = create("blood_mushroom");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PUFF_MUSHROOM = create("puff_mushroom");
	public static final ResourceKey<ConfiguredFeature<?, ?>> UNDERBEAN_BUSH = create("underbean_bush");
	public static final ResourceKey<ConfiguredFeature<?, ?>> BLISTERBERRY_BUSH = create("blisterberry_bush");
	public static final ResourceKey<ConfiguredFeature<?, ?>> GLOOMGOURD = create("gloomgourd");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DROOPVINE = create("droopvine");
	public static final ResourceKey<ConfiguredFeature<?, ?>> GLITTERKELP = create("glitterkelp");
	public static final ResourceKey<ConfiguredFeature<?, ?>> THORNREED = create("thornreed");
	public static final ResourceKey<ConfiguredFeature<?, ?>> TWISTYBUSH = create("twistybush");

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
	public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_PUFF_MUSHROOM = create("huge_puff_mushroom");

	//rocks
	public static final ResourceKey<ConfiguredFeature<?, ?>> DEPTHROCK_ROCK = create("depthrock_rock");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SHIVERSTONE_ROCK = create("shiverstone_rock");

	//misc
	public static final ResourceKey<ConfiguredFeature<?, ?>> SMOG_VENT = create("smog_vent");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ICE_PILLAR = create("ice_pillar");
	public static final ResourceKey<ConfiguredFeature<?, ?>> UTHERIUM_GROWTH = create("utherium_growth");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CEILING_UTHERIUM_GROWTH = create("ceiling_utherium_growth");
	public static final ResourceKey<ConfiguredFeature<?, ?>> UTHERIUM_GROWTH_EXTRA = create("utherium_growth_extra");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ANCIENT_ROOT = create("ancient_root");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ANCIENT_ROOT_EXTRA = create("ancient_root_extra");
	public static final ResourceKey<ConfiguredFeature<?, ?>> RUINS = create("ruins");
	public static final ResourceKey<ConfiguredFeature<?, ?>> UG_BONUS_CHEST = create("undergarden_bonus_chest");


	public static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, Undergarden.prefix(name));
	}

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		context.register(COAL_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_COAL_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_COAL_ORE.get().defaultBlockState())), 17)));
		context.register(IRON_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_IRON_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_IRON_ORE.get().defaultBlockState())), 9, 0.5F)));
		context.register(GOLD_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_GOLD_ORE.get().defaultBlockState())), 9, 0.5F)));
		context.register(DIAMOND_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_DIAMOND_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_DIAMOND_ORE.get().defaultBlockState())), 8, 0.5F)));
		context.register(CLOGGRUM_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get().defaultBlockState())), 9)));
		context.register(FROSTSTEEL_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get().defaultBlockState())), 9)));
		context.register(ROGDORIUM_ORE, new ConfiguredFeature<>(Feature.SCATTERED_ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DREADROCK_ORE_REPLACEABLES, UGBlocks.DREADROCK_ROGDORIUM_ORE.get().defaultBlockState())), 9)));
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
		context.register(AMOROUS_BRISTLE, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.AMOROUS_BRISTLE.get())));
		context.register(MISERABELL, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.MISERABELL.get())));
		context.register(BUTTERBUNCH, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.BUTTERBUNCH.get())));
		context.register(DEEPTURF, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.DEEPTURF.get())));
		context.register(ASHEN_DEEPTURF, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.ASHEN_DEEPTURF.get())));
		context.register(FROZEN_DEEPTURF, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.FROZEN_DEEPTURF.get())));
		context.register(SHIMMERWEED, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.SHIMMERWEED.get())));
		context.register(DEPTHROCK_PEBBLE, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, pebble(UGBlocks.DEPTHROCK_PEBBLES.get())));
		context.register(DITCHBULB, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.DITCHBULB_PLANT.get().defaultBlockState().setValue(DitchbulbBlock.AGE, 1))));
		context.register(TALL_DEEPTURF, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.TALL_DEEPTURF.get())));
		context.register(TALL_SHIMMERWEED, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.TALL_SHIMMERWEED.get())));
		context.register(INDIGO_MUSHROOM, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.INDIGO_MUSHROOM.get())));
		context.register(VEIL_MUSHROOM, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.VEIL_MUSHROOM.get())));
		context.register(INK_MUSHROOM, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.INK_MUSHROOM.get())));
		context.register(BLOOD_MUSHROOM, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.BLOOD_MUSHROOM.get())));
		context.register(PUFF_MUSHROOM, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.PUFF_MUSHROOM.get())));
		context.register(UNDERBEAN_BUSH, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.UNDERBEAN_BUSH.get().defaultBlockState().setValue(UnderbeanBushBlock.AGE, 3))));
		context.register(BLISTERBERRY_BUSH, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.BLISTERBERRY_BUSH.get().defaultBlockState().setValue(BlisterberryBushBlock.AGE, 3))));
		context.register(GLOOMGOURD, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.GLOOMGOURD.get())));
		context.register(DROOPVINE, new ConfiguredFeature<>(UGFeatures.DROOPVINE.get(), FeatureConfiguration.NONE));
		context.register(GLITTERKELP, new ConfiguredFeature<>(UGFeatures.GLITTERKELP.get(), FeatureConfiguration.NONE));
		context.register(THORNREED, new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
			List.of(
				BlockColumnConfiguration.layer(
					UniformInt.of(3, 5),
					new RuleBasedStateProvider(
						BlockStateProvider.simple(UGBlocks.THORNREED.get()),
						List.of(
							new RuleBasedStateProvider.Rule(
								BlockPredicate.matchesFluids(Fluids.WATER), BlockStateProvider.simple(UGBlocks.THORNREED.get().defaultBlockState().setValue(ThornreedBlock.WATERLOGGED, true))
							)
						)
					)
				)
			),
			Direction.UP,
			BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE,
			false
		)));
		context.register(TWISTYBUSH, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleBlock(UGBlocks.TWISTYBUSH.get())));

		//tree
		context.register(SMOGSTEM_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(UGBlocks.SMOGSTEM_LOG.get()),
			new SmogstemTrunkPlacer(10, 2, 2, 1),
			BlockStateProvider.simple(UGBlocks.SMOGSTEM_LEAVES.get()),
			new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
			Optional.empty(),
			new TwoLayersFeatureSize(1, 1, 2),
			PLACE_BELOW_UNDERGARDEN_TRUNKS
		).ignoreVines().build()));
		context.register(WIDE_SMOGSTEM_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(UGBlocks.SMOGSTEM_LOG.get()),
			new SmogstemTrunkPlacer(10, 2, 2, 2),
			BlockStateProvider.simple(UGBlocks.SMOGSTEM_LEAVES.get()),
			new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
			Optional.empty(),
			new TwoLayersFeatureSize(1, 1, 2),
			PLACE_BELOW_UNDERGARDEN_TRUNKS
		).ignoreVines().build()));
		context.register(TALL_SMOGSTEM_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(UGBlocks.SMOGSTEM_LOG.get()),
			new SmogstemTrunkPlacer(15, 4, 4, 2),
			BlockStateProvider.simple(UGBlocks.SMOGSTEM_LEAVES.get()),
			new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
			Optional.empty(),
			new TwoLayersFeatureSize(1, 1, 2),
			PLACE_BELOW_UNDERGARDEN_TRUNKS
		).ignoreVines().build()));
		context.register(SMOGSTEM_BUSH, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(UGBlocks.SMOGSTEM_LOG.get()),
			new StraightTrunkPlacer(1, 0, 0),
			BlockStateProvider.simple(UGBlocks.SMOGSTEM_LEAVES.get()),
			new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
			Optional.empty(),
			new TwoLayersFeatureSize(0, 0, 0),
			PLACE_BELOW_UNDERGARDEN_TRUNKS
		).ignoreVines().build()));
		context.register(WIGGLEWOOD_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(UGBlocks.WIGGLEWOOD_LOG.get()),
			new ForkingTrunkPlacer(3, 1, 1),
			BlockStateProvider.simple(UGBlocks.WIGGLEWOOD_LEAVES.get()),
			new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 0),
			Optional.empty(),
			new TwoLayersFeatureSize(1, 0, 2),
			PLACE_BELOW_UNDERGARDEN_TRUNKS
		).ignoreVines().build()));
		context.register(TALL_WIGGLEWOOD_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(UGBlocks.WIGGLEWOOD_LOG.get()),
			new ForkingTrunkPlacer(6, 1, 1),
			BlockStateProvider.simple(UGBlocks.WIGGLEWOOD_LEAVES.get()),
			new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 0),
			Optional.empty(),
			new TwoLayersFeatureSize(1, 0, 2),
			PLACE_BELOW_UNDERGARDEN_TRUNKS
		).ignoreVines().build()));
		context.register(GRONGLE_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(UGBlocks.GRONGLE_LOG.get()),
			new MegaJungleTrunkPlacer(10, 2, 19),
			BlockStateProvider.simple(UGBlocks.GRONGLE_LEAVES.get()),
			new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
			Optional.empty(),
			new TwoLayersFeatureSize(1, 1, 2),
			PLACE_BELOW_UNDERGARDEN_TRUNKS
		).ignoreVines().decorators(ImmutableList.of(GrongleLeafDecorator.INSTANCE, GrongletTrunkDecorator.INSTANCE)).build()));
		context.register(SMALL_GRONGLE_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(UGBlocks.GRONGLE_LOG.get()),
			new StraightTrunkPlacer(5, 2, 19),
			BlockStateProvider.simple(UGBlocks.GRONGLE_LEAVES.get()),
			new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
			Optional.empty(),
			new TwoLayersFeatureSize(1, 0, 1),
			PLACE_BELOW_UNDERGARDEN_TRUNKS
		).ignoreVines().decorators(ImmutableList.of(GrongleLeafDecorator.INSTANCE, GrongletTrunkDecorator.INSTANCE)).build()));
		context.register(GRONGLE_BUSH, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(UGBlocks.GRONGLE_LOG.get()),
			new StraightTrunkPlacer(1, 0, 0),
			BlockStateProvider.simple(UGBlocks.GRONGLE_LEAVES.get()),
			new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
			Optional.empty(),
			new TwoLayersFeatureSize(0, 0, 0),
			PLACE_BELOW_UNDERGARDEN_TRUNKS
		).ignoreVines().build()));

		//huge mushrooms
		context.register(HUGE_INDIGO_MUSHROOM, new ConfiguredFeature<>(Feature.HUGE_BROWN_MUSHROOM, new HugeMushroomFeatureConfiguration(
			BlockStateProvider.simple(UGBlocks.INDIGO_MUSHROOM_CAP.get().defaultBlockState()),
			BlockStateProvider.simple(UGBlocks.INDIGO_MUSHROOM_STEM.get().defaultBlockState()),
			3,
			BlockPredicate.matchesTag(BlockTags.HUGE_BROWN_MUSHROOM_CAN_PLACE_ON)
		)));
		context.register(HUGE_VEIL_MUSHROOM, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(UGBlocks.VEIL_MUSHROOM_STEM.get()),
			new StraightTrunkPlacer(9, 1, 1),
			BlockStateProvider.simple(UGBlocks.VEIL_MUSHROOM_CAP.get()),
			new VeilFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
			Optional.empty(),
			new TwoLayersFeatureSize(1, 0, 1),
			PLACE_BELOW_UNDERGARDEN_TRUNKS
		).build()));
		context.register(HUGE_INK_MUSHROOM, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(UGBlocks.INK_MUSHROOM_STEM.get()),
			new SingleForkingTrunkPlacer(6, 2, 2),
			BlockStateProvider.simple(UGBlocks.INK_MUSHROOM_CAP.get()),
			new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
			Optional.empty(),
			new TwoLayersFeatureSize(1, 0, 2),
			PLACE_BELOW_UNDERGARDEN_TRUNKS
		).decorators(ImmutableList.of(new AttachedToLeavesDecorator(0.2F, 1, 0, BlockStateProvider.simple(UGBlocks.SEEPING_INK.get()), 1, List.of(Direction.DOWN)))).build()));
		context.register(HUGE_BLOOD_MUSHROOM, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(UGBlocks.BLOOD_MUSHROOM_STEM.get()),
			new DarkOakTrunkPlacer(6, 2, 2),
			BlockStateProvider.simple(UGBlocks.BLOOD_MUSHROOM_CAP.get()),
			new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
			Optional.empty(),
			new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()),
			PLACE_BELOW_UNDERGARDEN_TRUNKS
		).decorators(ImmutableList.of(new ReplaceLeafDecorator(0.2F, BlockStateProvider.simple(UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP.get())))).build()));
		context.register(HUGE_PUFF_MUSHROOM, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(UGBlocks.PUFF_MUSHROOM_STEM.get()),
			new CherryTrunkPlacer(
				6,
				2,
				2,
				new WeightedListInt(WeightedList.<IntProvider>builder().add(ConstantInt.of(1), 1).add(ConstantInt.of(2), 1).add(ConstantInt.of(3), 1).build()),
				UniformInt.of(2, 4),
				UniformInt.of(-4, -3),
				UniformInt.of(-1, 0)
			),
			BlockStateProvider.simple(UGBlocks.PUFF_MUSHROOM_CAP.get()),
			new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
			Optional.empty(),
			new TwoLayersFeatureSize(1, 0, 2),
			PLACE_BELOW_UNDERGARDEN_TRUNKS
		).build()));

		//rocks
		context.register(DEPTHROCK_ROCK, new ConfiguredFeature<>(UGFeatures.BOULDER.get(), new BlockStateConfiguration(UGBlocks.DEPTHROCK.get().defaultBlockState())));
		context.register(SHIVERSTONE_ROCK, new ConfiguredFeature<>(UGFeatures.BOULDER.get(), new BlockStateConfiguration(UGBlocks.SHIVERSTONE.get().defaultBlockState())));

		//misc
		context.register(SMOG_VENT, new ConfiguredFeature<>(UGFeatures.SMOG_VENT.get(), FeatureConfiguration.NONE));
		context.register(ICE_PILLAR, new ConfiguredFeature<>(UGFeatures.ICE_PILLAR.get(), FeatureConfiguration.NONE));
		context.register(UTHERIUM_GROWTH, new ConfiguredFeature<>(UGFeatures.UTHERIUM_GROWTH.get(), new UtheriumCrystalConfiguration(
			new ColumnFeatureConfiguration(UniformInt.of(1, 2), UniformInt.of(2, 5)),
			new LargeDripstoneConfiguration(50, UniformInt.of(3, 8), UniformFloat.of(0.4F, 2.0F), 0.2F, UniformFloat.of(0.4F, 0.9F), UniformFloat.of(0.4F, 0.7F), ConstantFloat.of(0.0F), 0, 0.0F),
			0.4F, false)));
		context.register(CEILING_UTHERIUM_GROWTH, new ConfiguredFeature<>(UGFeatures.UTHERIUM_GROWTH.get(), new UtheriumCrystalConfiguration(
			new ColumnFeatureConfiguration(UniformInt.of(1, 2), UniformInt.of(2, 5)),
			new LargeDripstoneConfiguration(50, UniformInt.of(3, 8), UniformFloat.of(0.4F, 2.0F), 0.2F, UniformFloat.of(0.4F, 0.9F), UniformFloat.of(0.4F, 0.7F), ConstantFloat.of(0.0F), 0, 0.0F),
			0.4F, true)));
		context.register(UTHERIUM_GROWTH_EXTRA, new ConfiguredFeature<>(UGFeatures.UTHERIUM_GROWTH.get(), new UtheriumCrystalConfiguration(
			new ColumnFeatureConfiguration(UniformInt.of(1, 3), UniformInt.of(2, 10)),
			new LargeDripstoneConfiguration(50, UniformInt.of(3, 8), UniformFloat.of(0.4F, 2.0F), 0.2F, UniformFloat.of(0.4F, 0.9F), UniformFloat.of(0.4F, 0.7F), ConstantFloat.of(0.0F), 0, 0.0F),
			0.8F, false)));
		context.register(ANCIENT_ROOT, new ConfiguredFeature<>(UGFeatures.ANCIENT_ROOT.get(), new AncientRootConfiguration(25)));
		context.register(ANCIENT_ROOT_EXTRA, new ConfiguredFeature<>(UGFeatures.ANCIENT_ROOT.get(), new AncientRootConfiguration(10)));
		context.register(RUINS, new ConfiguredFeature<>(Feature.BLOCK_PILE, new BlockPileConfiguration(new WeightedStateProvider(WeightedList.<BlockState>builder().add(UGBlocks.DEPTHROCK_BRICKS.get().defaultBlockState(), 5).add(UGBlocks.CRACKED_DEPTHROCK_BRICKS.get().defaultBlockState(), 2).add(UGBlocks.POLISHED_DEPTHROCK.get().defaultBlockState(), 2).build()))));
		context.register(UG_BONUS_CHEST, new ConfiguredFeature<>(UGFeatures.UG_BONUS_CHEST.get(), FeatureConfiguration.NONE));
	}

	private static SimpleBlockConfiguration simpleBlock(Block block) {
		return new SimpleBlockConfiguration(BlockStateProvider.simple(block));
	}

	private static SimpleBlockConfiguration simpleBlock(BlockState block) {
		return new SimpleBlockConfiguration(BlockStateProvider.simple(block));
	}

	private static SimpleBlockConfiguration pebble(Block block) {
		return new SimpleBlockConfiguration(new RandomizedIntStateProvider(BlockStateProvider.simple(block), DepthrockPebblesBlock.PEBBLES, UniformInt.of(1, 2)));
	}
}