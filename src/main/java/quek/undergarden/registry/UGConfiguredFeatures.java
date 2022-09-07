package quek.undergarden.registry;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
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
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.block.BlisterberryBushBlock;
import quek.undergarden.block.DitchbulbBlock;
import quek.undergarden.block.UnderbeanBushBlock;
import quek.undergarden.world.gen.treedecorator.GrongleLeafDecorator;
import quek.undergarden.world.gen.treedecorator.GrongletTrunkDecorator;
import quek.undergarden.world.gen.treedecorator.ReplaceLeafDecorator;
import quek.undergarden.world.gen.trunkplacer.SingleForkingTrunkPlacer;
import quek.undergarden.world.gen.trunkplacer.SmogstemTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class UGConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Undergarden.MODID);

    //ore tags
    public static final RuleTest BASE_STONE_UNDERGARDEN = new TagMatchTest(UGTags.Blocks.BASE_STONE_UNDERGARDEN);
    public static final RuleTest DEPTHROCK_ORE_REPLACEABLES = new TagMatchTest(UGTags.Blocks.DEPTHROCK_ORE_REPLACEABLES);
    public static final RuleTest SHIVERSTONE_ORE_REPLACEABLES = new TagMatchTest(UGTags.Blocks.SHIVERSTONE_ORE_REPLACEABLES);
    public static final RuleTest TREMBLECRUST_ORE_REPLACEABLES = new TagMatchTest(UGTags.Blocks.TREMBLECRUST_ORE_REPLACEABLES);

    //ores
    public static final RegistryObject<ConfiguredFeature<?, ?>> COAL_ORE = CONFIGURED_FEATURES.register("coal_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_COAL_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_COAL_ORE.get().defaultBlockState())), 17)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> IRON_ORE = CONFIGURED_FEATURES.register("iron_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_IRON_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_IRON_ORE.get().defaultBlockState())), 9, 0.5F)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> GOLD_ORE = CONFIGURED_FEATURES.register("gold_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_GOLD_ORE.get().defaultBlockState())), 9, 0.5F)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> DIAMOND_ORE = CONFIGURED_FEATURES.register("diamond_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_DIAMOND_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_DIAMOND_ORE.get().defaultBlockState())), 8, 0.5F)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CLOGGRUM_ORE = CONFIGURED_FEATURES.register("cloggrum_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get().defaultBlockState())), 9)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> FROSTSTEEL_ORE = CONFIGURED_FEATURES.register("froststeel_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get().defaultBlockState())), 9)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> UTHERIUM_ORE = CONFIGURED_FEATURES.register("utherium_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_UTHERIUM_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get().defaultBlockState()), OreConfiguration.target(TREMBLECRUST_ORE_REPLACEABLES, UGBlocks.TREMBLECRUST_UTHERIUM_ORE.get().defaultBlockState())), 8, 0.5F)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> REGALIUM_ORE = CONFIGURED_FEATURES.register("regalium_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_REGALIUM_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_REGALIUM_ORE.get().defaultBlockState())), 4)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> SHIVERSTONE_ORE = CONFIGURED_FEATURES.register("shiverstone_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(BASE_STONE_UNDERGARDEN, UGBlocks.SHIVERSTONE.get().defaultBlockState(), 33)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> DEEPSOIL_ORE = CONFIGURED_FEATURES.register("deepsoil_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(BASE_STONE_UNDERGARDEN, UGBlocks.DEEPSOIL.get().defaultBlockState(), 33)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ICE_ORE = CONFIGURED_FEATURES.register("ice_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(BASE_STONE_UNDERGARDEN, Blocks.PACKED_ICE.defaultBlockState(), 33)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> SEDIMENT_ORE = CONFIGURED_FEATURES.register("sediment_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(BASE_STONE_UNDERGARDEN, UGBlocks.SEDIMENT.get().defaultBlockState(), 33)));

    //deltas
    public static final RegistryObject<ConfiguredFeature<?, ?>> BOG_DELTA = CONFIGURED_FEATURES.register("bog_delta", () -> new ConfiguredFeature<>(UGFeatures.DELTA.get(), new DeltaFeatureConfiguration(UGBlocks.VIRULENT_MIX.get().defaultBlockState(), UGBlocks.COARSE_DEEPSOIL.get().defaultBlockState(), UniformInt.of(6, 8), UniformInt.of(2, 4))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> GRONGLEGROWTH_DELTA = CONFIGURED_FEATURES.register("gronglegrowth_delta", () -> new ConfiguredFeature<>(UGFeatures.DELTA.get(), new DeltaFeatureConfiguration(Blocks.WATER.defaultBlockState(), UGBlocks.SEDIMENT.get().defaultBlockState(), UniformInt.of(3, 4), UniformInt.of(2, 4))));

    //vegetation
    public static final RegistryObject<ConfiguredFeature<?, ?>> AMOROUS_BRISTLE_PATCH = CONFIGURED_FEATURES.register("amorous_bristle_patch", () -> new ConfiguredFeature<>(Feature.FLOWER, patch(UGBlocks.AMOROUS_BRISTLE.get(), 64)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> MISERABELL_PATCH = CONFIGURED_FEATURES.register("miserabell_patch", () -> new ConfiguredFeature<>(Feature.FLOWER, patch(UGBlocks.MISERABELL.get(), 64)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> BUTTERBUNCH_PATCH = CONFIGURED_FEATURES.register("butterbunch_patch", () -> new ConfiguredFeature<>(Feature.FLOWER, patch(UGBlocks.BUTTERBUNCH.get(), 64)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> DEEPTURF_PATCH = CONFIGURED_FEATURES.register("deepturf_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.DEEPTURF.get(), 64)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ASHEN_DEEPTURF_PATCH = CONFIGURED_FEATURES.register("ashen_deepturf_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.ASHEN_DEEPTURF.get(), 64)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> FROZEN_DEEPTURF_PATCH = CONFIGURED_FEATURES.register("frozen_deepturf_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.FROZEN_DEEPTURF.get(), 64)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> SHIMMERWEED_PATCH = CONFIGURED_FEATURES.register("shimmerweed_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.SHIMMERWEED.get(), 32)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> DEPTHROCK_PEBBLE_PATCH = CONFIGURED_FEATURES.register("depthrock_pebble_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.DEPTHROCK_PEBBLES.get(), 32, List.of(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get(), UGBlocks.SEDIMENT.get(), UGBlocks.COARSE_DEEPSOIL.get()))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> DITCHBULB_PATCH = CONFIGURED_FEATURES.register("ditchbulb_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.DITCHBULB_PLANT.get().defaultBlockState().setValue(DitchbulbBlock.AGE, 1), 16, List.of(UGBlocks.DEPTHROCK.get()))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> TALL_DEEPTURF_PATCH = CONFIGURED_FEATURES.register("tall_deepturf_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.TALL_DEEPTURF.get(), 32)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> TALL_SHIMMERWEED_PATCH = CONFIGURED_FEATURES.register("tall_shimmerweed_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.TALL_SHIMMERWEED.get(), 32)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> INDIGO_MUSHROOM_PATCH = CONFIGURED_FEATURES.register("indigo_mushroom_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.INDIGO_MUSHROOM.get(), 64)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> VEIL_MUSHROOM_PATCH = CONFIGURED_FEATURES.register("veil_mushroom_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.VEIL_MUSHROOM.get(), 64)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> INK_MUSHROOM_PATCH = CONFIGURED_FEATURES.register("ink_mushroom_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.INK_MUSHROOM.get(), 64)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> BLOOD_MUSHROOM_PATCH = CONFIGURED_FEATURES.register("blood_mushroom_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.BLOOD_MUSHROOM.get(), 64)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> UNDERBEAN_BUSH_PATCH = CONFIGURED_FEATURES.register("underbean_bush_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.UNDERBEAN_BUSH.get().defaultBlockState().setValue(UnderbeanBushBlock.AGE, 3), 64, List.of(UGBlocks.DEEPTURF_BLOCK.get()))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> BLISTERBERRY_BUSH_PATCH = CONFIGURED_FEATURES.register("blisterberry_bush_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.BLISTERBERRY_BUSH.get().defaultBlockState().setValue(BlisterberryBushBlock.AGE, 3), 64, List.of(UGBlocks.ASHEN_DEEPTURF_BLOCK.get()))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> GLOOMGOURD_PATCH = CONFIGURED_FEATURES.register("gloomgourd_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, patch(UGBlocks.GLOOMGOURD.get(), 16, List.of(UGBlocks.DEEPTURF_BLOCK.get()))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> DROOPVINE = CONFIGURED_FEATURES.register("droopvine", () -> new ConfiguredFeature<>(UGFeatures.DROOPVINE.get(), FeatureConfiguration.NONE));
    public static final RegistryObject<ConfiguredFeature<?, ?>> GLITTERKELP = CONFIGURED_FEATURES.register("glitterkelp", () -> new ConfiguredFeature<>(UGFeatures.GLITTERKELP.get(), FeatureConfiguration.NONE));

    //tree
    public static final RegistryObject<ConfiguredFeature<?, ?>> SMOGSTEM_TREE = CONFIGURED_FEATURES.register("smogstem_tree", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.SMOGSTEM_LOG.get()), new SmogstemTrunkPlacer(10, 2, 2, 1), BlockStateProvider.simple(UGBlocks.SMOGSTEM_LEAVES.get()), new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> WIDE_SMOGSTEM_TREE = CONFIGURED_FEATURES.register("wide_smogstem_tree", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.SMOGSTEM_LOG.get()), new SmogstemTrunkPlacer(10, 2, 2, 2), BlockStateProvider.simple(UGBlocks.SMOGSTEM_LEAVES.get()), new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> TALL_SMOGSTEM_TREE = CONFIGURED_FEATURES.register("tall_smogstem_tree", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.SMOGSTEM_LOG.get()), new SmogstemTrunkPlacer(15, 4, 4, 2), BlockStateProvider.simple(UGBlocks.SMOGSTEM_LEAVES.get()), new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> SMOGSTEM_BUSH = CONFIGURED_FEATURES.register("smogstem_bush", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.SMOGSTEM_LOG.get()), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(UGBlocks.SMOGSTEM_LEAVES.get()), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(0, 0, 0)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> WIGGLEWOOD_TREE = CONFIGURED_FEATURES.register("wigglewood_tree", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.WIGGLEWOOD_LOG.get()), new ForkingTrunkPlacer(3, 1, 1), BlockStateProvider.simple(UGBlocks.WIGGLEWOOD_LEAVES.get()), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 0), new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> TALL_WIGGLEWOOD_TREE = CONFIGURED_FEATURES.register("tall_wigglewood_tree", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.WIGGLEWOOD_LOG.get()), new ForkingTrunkPlacer(6, 1, 1), BlockStateProvider.simple(UGBlocks.WIGGLEWOOD_LEAVES.get()), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 0), new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> GRONGLE_TREE = CONFIGURED_FEATURES.register("grongle_tree", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.GRONGLE_LOG.get()), new MegaJungleTrunkPlacer(10, 2, 19), BlockStateProvider.simple(UGBlocks.GRONGLE_LEAVES.get()), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).decorators(ImmutableList.of(GrongleLeafDecorator.INSTANCE, GrongletTrunkDecorator.INSTANCE)).build()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> SMALL_GRONGLE_TREE = CONFIGURED_FEATURES.register("small_grongle_tree", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.GRONGLE_LOG.get()), new StraightTrunkPlacer(5, 2, 19), BlockStateProvider.simple(UGBlocks.GRONGLE_LEAVES.get()), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).decorators(ImmutableList.of(GrongleLeafDecorator.INSTANCE, GrongletTrunkDecorator.INSTANCE)).build()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> GRONGLE_BUSH = CONFIGURED_FEATURES.register("grongle_bush", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.GRONGLE_LOG.get()), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(UGBlocks.GRONGLE_LEAVES.get()), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new TwoLayersFeatureSize(0, 0, 0)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));

    //huge mushrooms
    public static final RegistryObject<ConfiguredFeature<?, ?>> HUGE_INDIGO_MUSHROOM = CONFIGURED_FEATURES.register("huge_indigo_mushroom", () -> new ConfiguredFeature<>(Feature.HUGE_BROWN_MUSHROOM, new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(UGBlocks.INDIGO_MUSHROOM_CAP.get().defaultBlockState()), BlockStateProvider.simple(UGBlocks.INDIGO_MUSHROOM_STEM.get().defaultBlockState()), 3)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> HUGE_VEIL_MUSHROOM = CONFIGURED_FEATURES.register("huge_veil_mushroom", () -> new ConfiguredFeature<>(UGFeatures.VEIL_MUSHROOM.get(), new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(UGBlocks.VEIL_MUSHROOM_CAP.get().defaultBlockState()), BlockStateProvider.simple(UGBlocks.VEIL_MUSHROOM_STEM.get().defaultBlockState()), 2)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> HUGE_INK_MUSHROOM = CONFIGURED_FEATURES.register("huge_ink_mushroom", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.MUSHROOM_STEM), new SingleForkingTrunkPlacer(6, 2, 2), BlockStateProvider.simple(UGBlocks.INK_MUSHROOM_CAP.get()), new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).decorators(ImmutableList.of(new AttachedToLeavesDecorator(0.2F, 1, 0, BlockStateProvider.simple(UGBlocks.SEEPING_INK.get()), 1, List.of(Direction.DOWN)))).build()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> HUGE_BLOOD_MUSHROOM = CONFIGURED_FEATURES.register("huge_blood_mushroom", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.BLOOD_MUSHROOM_STEM.get()), new DarkOakTrunkPlacer(6, 2, 2), BlockStateProvider.simple(UGBlocks.BLOOD_MUSHROOM_CAP.get()), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())).dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).decorators(ImmutableList.of(new ReplaceLeafDecorator(0.2F, BlockStateProvider.simple(UGBlocks.BLOOD_MUSHROOM_GLOBULE.get())))).build()));

    //rocks
    public static final RegistryObject<ConfiguredFeature<?, ?>> DEPTHROCK_ROCK = CONFIGURED_FEATURES.register("depthrock_rock", () -> new ConfiguredFeature<>(Feature.FOREST_ROCK, new BlockStateConfiguration(UGBlocks.DEPTHROCK.get().defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> SHIVERSTONE_ROCK = CONFIGURED_FEATURES.register("shiverstone_rock", () -> new ConfiguredFeature<>(Feature.FOREST_ROCK, new BlockStateConfiguration(UGBlocks.SHIVERSTONE.get().defaultBlockState())));

    //misc
    public static final RegistryObject<ConfiguredFeature<?, ?>> SMOG_VENT = CONFIGURED_FEATURES.register("smog_vent", () -> new ConfiguredFeature<>(UGFeatures.SMOG_VENT.get(), FeatureConfiguration.NONE));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ICE_PILLAR = CONFIGURED_FEATURES.register("ice_pillar", () -> new ConfiguredFeature<>(UGFeatures.ICE_PILLAR.get(), FeatureConfiguration.NONE));

    private static RandomPatchConfiguration patch(Block block, int tries) {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block))));
    }

    private static RandomPatchConfiguration patch(Block block, int tries, List<Block> whitelist) {
        return FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block)), whitelist, tries);
    }

    private static RandomPatchConfiguration patch(BlockState block, int tries, List<Block> whitelist) {
        return FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block)), whitelist, tries);
    }
}