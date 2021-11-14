package quek.undergarden.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.blockplacers.DoublePlantPlacer;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.block.BlisterberryBushBlock;
import quek.undergarden.block.DitchbulbBlock;
import quek.undergarden.block.UnderbeanBushBlock;
import quek.undergarden.world.gen.feature.*;
import quek.undergarden.world.gen.treedecorator.GrongleLeafDecorator;
import quek.undergarden.world.gen.trunkplacer.GrongleTrunkPlacer;
import quek.undergarden.world.gen.trunkplacer.SmogstemTrunkPlacer;

public class UGFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Undergarden.MODID);

//    public static final RegistryObject<Feature<TreeConfiguration>> UNDERGARDEN_TREE = FEATURES.register(
//            "undergarden_tree", () -> new UGTreeFeature(TreeConfiguration.CODEC));
    public static final RegistryObject<Feature<HugeMushroomFeatureConfiguration>> BLOOD_MUSHROOM = FEATURES.register(
            "blood_mushroom", () -> new HugeBloodMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<HugeMushroomFeatureConfiguration>> INK_MUSHROOM = FEATURES.register(
            "ink_mushroom", () -> new HugeInkMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<HugeMushroomFeatureConfiguration>> VEIL_MUSHROOM = FEATURES.register(
            "veil_mushroom", () -> new HugeVeilMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> GLOWING_KELP = FEATURES.register(
            "glowing_kelp", () -> new GlowingKelpFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SMOG_VENT = FEATURES.register(
            "smog_vent", () -> new SmogVentFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> DROOPVINE = FEATURES.register(
            "droopvine", () -> new DroopvineFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> ICE_PILLAR = FEATURES.register(
            "ice_pillar", () -> new IcePillarFeature(NoneFeatureConfiguration.CODEC));

    public static final class ConfiguredFeatures {
        static final RuleTest UNDERGARDEN_FILLER = new TagMatchTest(UGTags.Blocks.BASE_STONE_UNDERGARDEN);

        public static final ConfiguredFeature<?, ?> SPRING = Feature.SPRING.configured(
                new SpringConfiguration(
                        Fluids.WATER.defaultFluidState(),
                        false,
                        4,
                        1,
                        ImmutableSet.of(UGBlocks.DEPTHROCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.TREMBLECRUST.get()))
        );
        public static final ConfiguredFeature<?, ?> VIRULENT_SPRING = Feature.SPRING.configured(
                new SpringConfiguration(
                        UGFluids.VIRULENT_MIX_SOURCE.get().defaultFluidState(),
                        false,
                        4,
                        1,
                        ImmutableSet.of(UGBlocks.DEPTHROCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.TREMBLECRUST.get()))
        );

        public static final ConfiguredFeature<?, ?> VIRULENT_LAKE = Feature.LAKE.configured(
                new BlockStateConfiguration(UGBlocks.VIRULENT_MIX.get().defaultBlockState())
        );

        public static final ConfiguredFeature<?, ?> BOG_PONDS = Feature.DELTA_FEATURE.configured(
                new DeltaFeatureConfiguration(
                        UGBlocks.VIRULENT_MIX.get().defaultBlockState(),
                        UGBlocks.COARSE_DEEPSOIL.get().defaultBlockState(),
                        UniformInt.of(6, 8),
                        UniformInt.of(2, 4))
        );
        public static final ConfiguredFeature<?, ?> GRONGLEGROWTH_PONDS = Feature.DELTA_FEATURE.configured(
                new DeltaFeatureConfiguration(
                        Blocks.WATER.defaultBlockState(),
                        UGBlocks.SEDIMENT.get().defaultBlockState(),
                        UniformInt.of(3, 4),
                        UniformInt.of(2, 4))
        );

        public static final ConfiguredFeature<?, ?> LILYPADS = Feature.RANDOM_PATCH.configured(
                (new RandomPatchConfiguration.GrassConfigurationBuilder(
                        new SimpleStateProvider(Blocks.LILY_PAD.defaultBlockState()),
                        new SimpleBlockPlacer()))
                        .tries(32).needWater().noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> DEEPTURF_PATCH = Feature.RANDOM_PATCH.configured(
                (new RandomPatchConfiguration.GrassConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.DEEPTURF.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(64).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> ASHEN_DEEPTURF_PATCH = Feature.RANDOM_PATCH.configured(
                (new RandomPatchConfiguration.GrassConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.ASHEN_DEEPTURF.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(64).whitelist(ImmutableSet.of(UGBlocks.ASHEN_DEEPTURF_BLOCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> FROZEN_DEEPTURF_PATCH = Feature.RANDOM_PATCH.configured(
                (new RandomPatchConfiguration.GrassConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.FROZEN_DEEPTURF.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(64).whitelist(ImmutableSet.of(UGBlocks.FROZEN_DEEPTURF_BLOCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> SHIMMERWEED_PATCH = Feature.RANDOM_PATCH.configured(
                (new RandomPatchConfiguration.GrassConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.SHIMMERWEED.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(32).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> PEBBLE_PATCH = Feature.RANDOM_PATCH.configured(
                (new RandomPatchConfiguration.GrassConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.DEPTHROCK_PEBBLES.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(32).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> DITCHBULB_PATCH = Feature.RANDOM_PATCH.configured(
                (new RandomPatchConfiguration.GrassConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.DITCHBULB_PLANT.get().defaultBlockState().setValue(DitchbulbBlock.AGE, 1)), new SimpleBlockPlacer()))
                        .tries(16).whitelist(ImmutableSet.of(UGBlocks.DEPTHROCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> TALL_DEEPTURF_PATCH = Feature.RANDOM_PATCH.configured(
                (new RandomPatchConfiguration.GrassConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.TALL_DEEPTURF.get().defaultBlockState()), new DoublePlantPlacer()))
                        .tries(32).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> TALL_SHIMMERWEED_PATCH = Feature.RANDOM_PATCH.configured(
                (new RandomPatchConfiguration.GrassConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.TALL_SHIMMERWEED.get().defaultBlockState()), new DoublePlantPlacer()))
                        .tries(16).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> INDIGO_MUSHROOM_PATCH = Feature.RANDOM_PATCH.configured(
                (new RandomPatchConfiguration.GrassConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.INDIGO_MUSHROOM.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(64).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> VEIL_MUSHROOM_PATCH = Feature.RANDOM_PATCH.configured(
                (new RandomPatchConfiguration.GrassConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.VEIL_MUSHROOM.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(64).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> INK_MUSHROOM_PATCH = Feature.RANDOM_PATCH.configured(
                (new RandomPatchConfiguration.GrassConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.INK_MUSHROOM.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(64).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> BLOOD_MUSHROOM_PATCH = Feature.RANDOM_PATCH.configured(
                (new RandomPatchConfiguration.GrassConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.BLOOD_MUSHROOM.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(64).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> UNDERBEAN_BUSH_PATCH = Feature.RANDOM_PATCH.configured(
                (new RandomPatchConfiguration.GrassConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.UNDERBEAN_BUSH.get().defaultBlockState().setValue(UnderbeanBushBlock.AGE, 3)), new SimpleBlockPlacer()))
                        .tries(64).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> BLISTERBERRY_BUSH_PATCH = Feature.RANDOM_PATCH.configured(
                (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(UGBlocks.BLISTERBERRY_BUSH.get().defaultBlockState().setValue(BlisterberryBushBlock.AGE, 3)), new SimpleBlockPlacer()))
                        .tries(64).whitelist(ImmutableSet.of(UGBlocks.ASHEN_DEEPTURF_BLOCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> GLOOMGOURD_PATCH = Feature.RANDOM_PATCH.configured(
                (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(UGBlocks.GLOOMGOURD.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(16).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).noProjection().build()
        );

        public static final ConfiguredFeature<?, ?> COAL_ORE = Feature.ORE.configured(
                new OreConfiguration(UNDERGARDEN_FILLER, UGBlocks.COAL_ORE.get().defaultBlockState(), 17)
        );
        public static final ConfiguredFeature<?, ?> IRON_ORE = Feature.ORE.configured(
                new OreConfiguration(UNDERGARDEN_FILLER, UGBlocks.IRON_ORE.get().defaultBlockState(), 9)
        );
        public static final ConfiguredFeature<?, ?> GOLD_ORE = Feature.ORE.configured(
                new OreConfiguration(UNDERGARDEN_FILLER, UGBlocks.GOLD_ORE.get().defaultBlockState(), 9)
        );
        public static final ConfiguredFeature<?, ?> DIAMOND_ORE = Feature.ORE.configured(
                new OreConfiguration(UNDERGARDEN_FILLER, UGBlocks.DIAMOND_ORE.get().defaultBlockState(), 8)
        );
        public static final ConfiguredFeature<?, ?> CLOGGRUM_ORE = Feature.ORE.configured(
                new OreConfiguration(UNDERGARDEN_FILLER, UGBlocks.CLOGGRUM_ORE.get().defaultBlockState(), 9)
        );
        public static final ConfiguredFeature<?, ?> FROSTSTEEL_ORE = Feature.ORE.configured(
                new OreConfiguration(UNDERGARDEN_FILLER, UGBlocks.FROSTSTEEL_ORE.get().defaultBlockState(), 9)
        );
        public static final ConfiguredFeature<?, ?> UTHERIUM_ORE = Feature.ORE.configured(
                new OreConfiguration(UNDERGARDEN_FILLER, UGBlocks.UTHERIUM_ORE.get().defaultBlockState(), 8)
        );
        public static final ConfiguredFeature<?, ?> REGALIUM_ORE = Feature.ORE.configured(
                new OreConfiguration(UNDERGARDEN_FILLER, UGBlocks.REGALIUM_ORE.get().defaultBlockState(), 4)
        );
        public static final ConfiguredFeature<?, ?> SHIVERSTONE_PATCH = Feature.ORE.configured(
                new OreConfiguration(UNDERGARDEN_FILLER, UGBlocks.SHIVERSTONE.get().defaultBlockState(), 33)
        );
        public static final ConfiguredFeature<?, ?> DEEPSOIL_PATCH = Feature.ORE.configured(
                new OreConfiguration(UNDERGARDEN_FILLER, UGBlocks.DEEPSOIL.get().defaultBlockState(), 33)
        );
        public static final ConfiguredFeature<?, ?> ICE_PATCH = Feature.ORE.configured(
                new OreConfiguration(UNDERGARDEN_FILLER, Blocks.PACKED_ICE.defaultBlockState(), 33)
        );
        public static final ConfiguredFeature<?, ?> SEDIMENT_PATCH = Feature.ORE.configured(
                new OreConfiguration(UNDERGARDEN_FILLER, UGBlocks.SEDIMENT.get().defaultBlockState(), 33)
        );
        public static final ConfiguredFeature<?, ?> SEDIMENT_DISK = Feature.DISK.configured(
                new DiskConfiguration(UGBlocks.SEDIMENT.get().defaultBlockState(), UniformInt.of(4, 4), 2, ImmutableList.of(UGBlocks.DEEPSOIL.get().defaultBlockState(), UGBlocks.DEEPTURF_BLOCK.get().defaultBlockState(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get().defaultBlockState(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get().defaultBlockState()))
        );

        public static final ConfiguredFeature<TreeConfiguration, ?> SMOGSTEM_TREE = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.SMOGSTEM_LOG.get().defaultBlockState()),
                        new SmogstemTrunkPlacer(10, 2, 2),
                        new SimpleStateProvider(UGBlocks.SMOGSTEM_LEAVES.get().defaultBlockState()),
                        new SimpleStateProvider(UGBlocks.SMOGSTEM_SAPLING.get().defaultBlockState()),
                        new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                        new TwoLayersFeatureSize(1, 1, 2))
                        .ignoreVines().build()
        );
        public static final ConfiguredFeature<TreeConfiguration, ?> WIGGLEWOOD_TREE = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.WIGGLEWOOD_LOG.get().defaultBlockState()),
                        new ForkingTrunkPlacer(3, 1, 1),
                        new SimpleStateProvider(UGBlocks.WIGGLEWOOD_LEAVES.get().defaultBlockState()),
                        new SimpleStateProvider(UGBlocks.WIGGLEWOOD_SAPLING.get().defaultBlockState()),
                        new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 0),
                        new TwoLayersFeatureSize(1, 0, 2))
                        .ignoreVines().build()
        );
        public static final ConfiguredFeature<TreeConfiguration, ?> GRONGLE_TREE = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.GRONGLE_LOG.get().defaultBlockState()),
                        new GrongleTrunkPlacer(10, 2, 19),
                        new SimpleStateProvider(UGBlocks.GRONGLE_LEAVES.get().defaultBlockState()),
                        new SimpleStateProvider(UGBlocks.GRONGLE_SAPLING.get().defaultBlockState()),
                        new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 1, 2))
                        .decorators(ImmutableList.of(GrongleLeafDecorator.INSTANCE))
                        .build()
        );
        public static final ConfiguredFeature<TreeConfiguration, ?> GRONGLE_TREE_SMALL = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.GRONGLE_LOG.get().defaultBlockState()),
                        new StraightTrunkPlacer(5, 2, 19),
                        new SimpleStateProvider(UGBlocks.GRONGLE_LEAVES.get().defaultBlockState()),
                        new SimpleStateProvider(UGBlocks.GRONGLE_SAPLING.get().defaultBlockState()),
                        new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .decorators(ImmutableList.of(GrongleLeafDecorator.INSTANCE))
                        .build()
        );
        public static final ConfiguredFeature<TreeConfiguration, ?> GRONGLE_BUSH = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.GRONGLE_LOG.get().defaultBlockState()),
                        new StraightTrunkPlacer(1, 0, 0),
                        new SimpleStateProvider(UGBlocks.GRONGLE_LEAVES.get().defaultBlockState()),
                        new SimpleStateProvider(UGBlocks.GRONGLE_SAPLING.get().defaultBlockState()),
                        new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(0, 0, 0))
                        .build()
        );

        public static final ConfiguredFeature<TreeConfiguration, ?> SMOGSTEM_TREE_TALL = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.SMOGSTEM_LOG.get().defaultBlockState()),
                        new SmogstemTrunkPlacer(15, 4, 4),
                        new SimpleStateProvider(UGBlocks.SMOGSTEM_LEAVES.get().defaultBlockState()),
                        new SimpleStateProvider(UGBlocks.SMOGSTEM_SAPLING.get().defaultBlockState()),
                        new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                        new TwoLayersFeatureSize(1, 1, 2))
                        .ignoreVines().build()
        );
        public static final ConfiguredFeature<TreeConfiguration, ?> WIGGLEWOOD_TREE_TALL = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        new SimpleStateProvider(UGBlocks.WIGGLEWOOD_LOG.get().defaultBlockState()),
                        new ForkingTrunkPlacer(6, 2, 2),
                        new SimpleStateProvider(UGBlocks.WIGGLEWOOD_LEAVES.get().defaultBlockState()),
                        new SimpleStateProvider(UGBlocks.WIGGLEWOOD_SAPLING.get().defaultBlockState()),
                        new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 0),
                        new TwoLayersFeatureSize(1, 0, 2))
                        .ignoreVines().build()
        );

        public static final ConfiguredFeature<HugeMushroomFeatureConfiguration, ?> BLOOD_MUSHROOM = UGFeatures.BLOOD_MUSHROOM.get().configured(
                new HugeMushroomFeatureConfiguration(
                        new SimpleStateProvider(UGBlocks.BLOOD_MUSHROOM_CAP.get().defaultBlockState().setValue(HugeMushroomBlock.DOWN, false)),
                        new SimpleStateProvider(UGBlocks.BLOOD_MUSHROOM_STALK.get().defaultBlockState().setValue(HugeMushroomBlock.UP, false).setValue(HugeMushroomBlock.DOWN, false)),
                        3)
        );
        public static final ConfiguredFeature<HugeMushroomFeatureConfiguration, ?> INDIGO_MUSHROOM = Feature.HUGE_BROWN_MUSHROOM.configured(
                new HugeMushroomFeatureConfiguration(
                        new SimpleStateProvider(UGBlocks.INDIGO_MUSHROOM_CAP.get().defaultBlockState().setValue(HugeMushroomBlock.UP, true)),
                        new SimpleStateProvider(UGBlocks.INDIGO_MUSHROOM_STALK.get().defaultBlockState().setValue(HugeMushroomBlock.UP, false).setValue(HugeMushroomBlock.DOWN, false)),
                        3)
        );
        public static final ConfiguredFeature<HugeMushroomFeatureConfiguration, ?> INK_MUSHROOM = UGFeatures.INK_MUSHROOM.get().configured(
                new HugeMushroomFeatureConfiguration(
                        new SimpleStateProvider(UGBlocks.INK_MUSHROOM_CAP.get().defaultBlockState().setValue(HugeMushroomBlock.UP, true)),
                        new SimpleStateProvider(Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, false).setValue(HugeMushroomBlock.DOWN, false)),
                        5)
        );
        public static final ConfiguredFeature<HugeMushroomFeatureConfiguration, ?> VEIL_MUSHROOM = UGFeatures.VEIL_MUSHROOM.get().configured(
                new HugeMushroomFeatureConfiguration(
                        new SimpleStateProvider(UGBlocks.VEIL_MUSHROOM_CAP.get().defaultBlockState().setValue(HugeMushroomBlock.DOWN, false)),
                        new SimpleStateProvider(UGBlocks.VEIL_MUSHROOM_STALK.get().defaultBlockState().setValue(HugeMushroomBlock.UP, false).setValue(HugeMushroomBlock.DOWN, false)),
                        2)
        );

        public static final ConfiguredFeature<?, ?> DEPTHROCK_BOULDER = Feature.FOREST_ROCK.configured(
                new BlockStateConfiguration(UGBlocks.DEPTHROCK.get().defaultBlockState())
        );
        public static final ConfiguredFeature<?, ?> SHIVERSTONE_BOULDER = Feature.FOREST_ROCK.configured(
                new BlockStateConfiguration(UGBlocks.SHIVERSTONE.get().defaultBlockState())
        );

        public static final ConfiguredFeature<?, ?> GLOWING_KELP = UGFeatures.GLOWING_KELP.get().configured(
                FeatureConfiguration.NONE
        );
        public static final ConfiguredFeature<?, ?> SMOG_VENT = UGFeatures.SMOG_VENT.get().configured(
                FeatureConfiguration.NONE
        );
        public static final ConfiguredFeature<?, ?> DROOPVINE = UGFeatures.DROOPVINE.get().configured(
                FeatureConfiguration.NONE
        );
        public static final ConfiguredFeature<?, ?> ICE_PILLAR = UGFeatures.ICE_PILLAR.get().configured(
                FeatureConfiguration.NONE
        );
    }

    public static void registerConfiguredFeatures() {
        register("spring", ConfiguredFeatures.SPRING.range(new RangeDecoratorConfiguration(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.belowTop(8), 8))).squared().count(50));
        register("virulent_spring", ConfiguredFeatures.VIRULENT_SPRING.range(new RangeDecoratorConfiguration(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.belowTop(8), 8))).squared().count(50));
        register("bog_virulent_spring", ConfiguredFeatures.VIRULENT_SPRING.range(new RangeDecoratorConfiguration(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.belowTop(8), 8))).squared().count(100));

        register("lake_virulent", ConfiguredFeatures.VIRULENT_LAKE.range(Features.Decorators.FULL_RANGE).squared().rarity(8));
        register("bog_lake_virulent", ConfiguredFeatures.VIRULENT_LAKE.range(Features.Decorators.FULL_RANGE).squared().rarity(2));

        register("bog_pond", ConfiguredFeatures.BOG_PONDS.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(40))));
        register("gronglegrowth_pond", ConfiguredFeatures.GRONGLEGROWTH_PONDS.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(40))));

        register("lilypads", ConfiguredFeatures.LILYPADS.range(Features.Decorators.FULL_RANGE).count(5));
        register("deepturf_patch", ConfiguredFeatures.DEEPTURF_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(100));
        register("ashen_patch", ConfiguredFeatures.ASHEN_DEEPTURF_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(100));
        register("frozen_patch", ConfiguredFeatures.FROZEN_DEEPTURF_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(100));
        register("shimmerweed_patch", ConfiguredFeatures.SHIMMERWEED_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(75));
        register("pebble_patch", ConfiguredFeatures.PEBBLE_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(75));
        register("ditchbulb_patch", ConfiguredFeatures.DITCHBULB_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(50));

        register("tall_deepturf_patch", ConfiguredFeatures.TALL_DEEPTURF_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(100));
        register("tall_shimmerweed_patch", ConfiguredFeatures.TALL_SHIMMERWEED_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(50));

        register("indigo_patch", ConfiguredFeatures.INDIGO_MUSHROOM_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(1));
        register("veil_patch", ConfiguredFeatures.VEIL_MUSHROOM_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(1));
        register("ink_patch", ConfiguredFeatures.INK_MUSHROOM_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(1));
        register("blood_patch", ConfiguredFeatures.BLOOD_MUSHROOM_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(1));

        register("bog_indigo_patch", ConfiguredFeatures.INDIGO_MUSHROOM_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(25));
        register("bog_veil_patch", ConfiguredFeatures.VEIL_MUSHROOM_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(25));
        register("bog_ink_patch", ConfiguredFeatures.INK_MUSHROOM_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(25));
        register("bog_blood_patch", ConfiguredFeatures.BLOOD_MUSHROOM_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(25));

        register("underbean_patch", ConfiguredFeatures.UNDERBEAN_BUSH_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(5));
        register("blisterberry_patch", ConfiguredFeatures.BLISTERBERRY_BUSH_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(5));
        register("gloomgourd_patch", ConfiguredFeatures.GLOOMGOURD_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(5));

        register("coal_ore", ConfiguredFeatures.COAL_ORE.range(Features.Decorators.FULL_RANGE).squared().count(30));
        register("iron_ore", ConfiguredFeatures.IRON_ORE.rangeUniform(VerticalAnchor.absolute(200), VerticalAnchor.top()).squared().count(8));
        register("gold_ore", ConfiguredFeatures.GOLD_ORE.rangeUniform(VerticalAnchor.absolute(220), VerticalAnchor.top()).squared().count(2));
        register("diamond_ore", ConfiguredFeatures.DIAMOND_ORE.rangeUniform(VerticalAnchor.absolute(230), VerticalAnchor.top()).squared());
        register("cloggrum_ore", ConfiguredFeatures.CLOGGRUM_ORE.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)).squared().count(20));
        register("froststeel_ore", ConfiguredFeatures.FROSTSTEEL_ORE.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64)).squared().count(15));
        register("utherium_ore", ConfiguredFeatures.UTHERIUM_ORE.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32)).squared().count(5));
        register("regalium_ore", ConfiguredFeatures.REGALIUM_ORE.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(12)).squared().count(3));

        register("shiverstone_patch", ConfiguredFeatures.SHIVERSTONE_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(10));
        register("deepsoil_patch", ConfiguredFeatures.DEEPSOIL_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(10));
        register("ice_patch", ConfiguredFeatures.ICE_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(20));
        register("sediment_patch", ConfiguredFeatures.SEDIMENT_PATCH.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32)).squared().count(20));
        register("sediment_disk", ConfiguredFeatures.SEDIMENT_DISK.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32)).squared().count(20));

        register("smogstem_tree", ConfiguredFeatures.SMOGSTEM_TREE.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(8))));
        register("wigglewood_tree", ConfiguredFeatures.WIGGLEWOOD_TREE.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(8))));
        register("grongle_tree", ConfiguredFeatures.GRONGLE_TREE.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(8))));
        register("grongle_tree_small", ConfiguredFeatures.GRONGLE_TREE_SMALL.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(8))));
        register("grongle_bush", ConfiguredFeatures.GRONGLE_BUSH.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(8))));

        register("smogstem_tree_tall", ConfiguredFeatures.SMOGSTEM_TREE_TALL.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(4))));
        register("wigglewood_tree_tall", ConfiguredFeatures.WIGGLEWOOD_TREE_TALL.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(4))));

        register("huge_blood_mushroom", ConfiguredFeatures.BLOOD_MUSHROOM.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(2))));
        register("huge_indigo_mushroom", ConfiguredFeatures.INDIGO_MUSHROOM.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(2))));
        register("huge_ink_mushroom", ConfiguredFeatures.INK_MUSHROOM.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(2))));
        register("huge_veil_mushroom", ConfiguredFeatures.VEIL_MUSHROOM.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(2))));

        register("depthrock_boulder", ConfiguredFeatures.DEPTHROCK_BOULDER.range(Features.Decorators.FULL_RANGE).squared().count(5));
        register("shiverstone_boulder", ConfiguredFeatures.SHIVERSTONE_BOULDER.range(Features.Decorators.FULL_RANGE).squared().count(5));

        register("glowing_kelp", ConfiguredFeatures.GLOWING_KELP.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(30)).squared().count(100));
        register("smog_vent", ConfiguredFeatures.SMOG_VENT.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(8))));
        register("droopvine", ConfiguredFeatures.DROOPVINE.range(Features.Decorators.FULL_RANGE).squared().count(100));
        register("ice_pillar", ConfiguredFeatures.ICE_PILLAR.range(Features.Decorators.FULL_RANGE).squared().count(50));
    }

    private static <FC extends FeatureConfiguration> void register(String name, ConfiguredFeature<FC, ?> feature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Undergarden.MODID, name), feature);
    }
}