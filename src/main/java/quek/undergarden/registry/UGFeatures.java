package quek.undergarden.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.BushFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.DarkOakFoliagePlacer;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.MegaJungleTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.fml.RegistryObject;
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

    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> UNDERGARDEN_TREE = FEATURES.register(
            "undergarden_tree", () -> new UGTreeFeature(BaseTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<BigMushroomFeatureConfig>> BLOOD_MUSHROOM = FEATURES.register(
            "blood_mushroom", () -> new BigBloodMushroomFeature(BigMushroomFeatureConfig.CODEC));
    public static final RegistryObject<Feature<BigMushroomFeatureConfig>> INDIGO_MUSHROOM = FEATURES.register(
            "indigo_mushroom", () -> new BigIndigoMushroomFeature(BigMushroomFeatureConfig.CODEC));
    public static final RegistryObject<Feature<BigMushroomFeatureConfig>> INK_MUSHROOM = FEATURES.register(
            "ink_mushroom", () -> new BigInkMushroomFeature(BigMushroomFeatureConfig.CODEC));
    public static final RegistryObject<Feature<BigMushroomFeatureConfig>> VEIL_MUSHROOM = FEATURES.register(
            "veil_mushroom", () -> new BigVeilMushroomFeature(BigMushroomFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> GLOWING_KELP = FEATURES.register(
            "glowing_kelp", () -> new GlowingKelpFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> SMOG_VENT = FEATURES.register(
            "smog_vent", () -> new SmogVentFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> DROOPVINE = FEATURES.register(
            "droopvine", () -> new DroopvineFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> ICE_PILLAR = FEATURES.register(
            "ice_pillar", () -> new IcePillarFeature(NoFeatureConfig.CODEC));

    public static final class ConfiguredFeatures {
        static final RuleTest UNDERGARDEN_FILLER = new TagMatchRuleTest(UGTags.Blocks.BASE_STONE_UNDERGARDEN);

        public static final ConfiguredFeature<?, ?> SPRING = Feature.SPRING.configured(
                new LiquidsConfig(
                        Fluids.WATER.defaultFluidState(),
                        false,
                        4,
                        1,
                        ImmutableSet.of(UGBlocks.DEPTHROCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.TREMBLECRUST.get()))
        );
        public static final ConfiguredFeature<?, ?> VIRULENT_SPRING = Feature.SPRING.configured(
                new LiquidsConfig(
                        UGFluids.VIRULENT_MIX_SOURCE.get().defaultFluidState(),
                        false,
                        4,
                        1,
                        ImmutableSet.of(UGBlocks.DEPTHROCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.TREMBLECRUST.get()))
        );

        public static final ConfiguredFeature<?, ?> VIRULENT_LAKE = Feature.LAKE.configured(
                new BlockStateFeatureConfig(UGBlocks.VIRULENT_MIX.get().defaultBlockState())
        );

        public static final ConfiguredFeature<?, ?> BOG_PONDS = Feature.DELTA_FEATURE.configured(
                new BasaltDeltasFeature(
                        UGBlocks.VIRULENT_MIX.get().defaultBlockState(),
                        UGBlocks.COARSE_DEEPSOIL.get().defaultBlockState(),
                        FeatureSpread.of(6, 8),
                        FeatureSpread.of(0, 4))
        );
        public static final ConfiguredFeature<?, ?> GRONGLEGROWTH_PONDS = Feature.DELTA_FEATURE.configured(
                new BasaltDeltasFeature(
                        Blocks.WATER.defaultBlockState(),
                        UGBlocks.SEDIMENT.get().defaultBlockState(),
                        FeatureSpread.of(3, 4),
                        FeatureSpread.of(2, 4))
        );

        public static final ConfiguredFeature<?, ?> LILYPADS = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(Blocks.LILY_PAD.defaultBlockState()),
                        new SimpleBlockPlacer()))
                        .tries(32).needWater().noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> DEEPTURF_PATCH = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.DEEPTURF.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(64).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> ASHEN_DEEPTURF_PATCH = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.ASHEN_DEEPTURF.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(64).whitelist(ImmutableSet.of(UGBlocks.ASHEN_DEEPTURF_BLOCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> FROZEN_DEEPTURF_PATCH = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.FROZEN_DEEPTURF.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(64).whitelist(ImmutableSet.of(UGBlocks.FROZEN_DEEPTURF_BLOCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> SHIMMERWEED_PATCH = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.SHIMMERWEED.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(32).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> PEBBLE_PATCH = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.DEPTHROCK_PEBBLES.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(32).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> DITCHBULB_PATCH = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.DITCHBULB_PLANT.get().defaultBlockState().setValue(DitchbulbBlock.AGE, 1)), new SimpleBlockPlacer()))
                        .tries(16).whitelist(ImmutableSet.of(UGBlocks.DEPTHROCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> TALL_DEEPTURF_PATCH = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.TALL_DEEPTURF.get().defaultBlockState()), new DoublePlantBlockPlacer()))
                        .tries(32).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> TALL_SHIMMERWEED_PATCH = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.TALL_SHIMMERWEED.get().defaultBlockState()), new DoublePlantBlockPlacer()))
                        .tries(16).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> INDIGO_MUSHROOM_PATCH = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.INDIGO_MUSHROOM.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(64).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> VEIL_MUSHROOM_PATCH = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.VEIL_MUSHROOM.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(64).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> INK_MUSHROOM_PATCH = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.INK_MUSHROOM.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(64).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> BLOOD_MUSHROOM_PATCH = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.BLOOD_MUSHROOM.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(64).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> UNDERBEAN_BUSH_PATCH = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.UNDERBEAN_BUSH.get().defaultBlockState().setValue(UnderbeanBushBlock.AGE, 3)), new SimpleBlockPlacer()))
                        .tries(64).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> BLISTERBERRY_BUSH_PATCH = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.BLISTERBERRY_BUSH.get().defaultBlockState().setValue(BlisterberryBushBlock.AGE, 3)), new SimpleBlockPlacer()))
                        .tries(64).whitelist(ImmutableSet.of(UGBlocks.ASHEN_DEEPTURF_BLOCK.get())).noProjection().build()
        );
        public static final ConfiguredFeature<?, ?> GLOOMGOURD_PATCH = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.GLOOMGOURD.get().defaultBlockState()), new SimpleBlockPlacer()))
                        .tries(16).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).noProjection().build()
        );

        public static final ConfiguredFeature<?, ?> COAL_ORE = Feature.ORE.configured(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.COAL_ORE.get().defaultBlockState(), 17)
        );
        public static final ConfiguredFeature<?, ?> IRON_ORE = Feature.ORE.configured(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.IRON_ORE.get().defaultBlockState(), 9)
        );
        public static final ConfiguredFeature<?, ?> GOLD_ORE = Feature.ORE.configured(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.GOLD_ORE.get().defaultBlockState(), 9)
        );
        public static final ConfiguredFeature<?, ?> DIAMOND_ORE = Feature.ORE.configured(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.DIAMOND_ORE.get().defaultBlockState(), 8)
        );
        public static final ConfiguredFeature<?, ?> CLOGGRUM_ORE = Feature.ORE.configured(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.CLOGGRUM_ORE.get().defaultBlockState(), 9)
        );
        public static final ConfiguredFeature<?, ?> FROSTSTEEL_ORE = Feature.ORE.configured(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.FROSTSTEEL_ORE.get().defaultBlockState(), 9)
        );
        public static final ConfiguredFeature<?, ?> UTHERIUM_ORE = Feature.ORE.configured(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.UTHERIUM_ORE.get().defaultBlockState(), 8)
        );
        public static final ConfiguredFeature<?, ?> REGALIUM_ORE = Feature.ORE.configured(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.REGALIUM_ORE.get().defaultBlockState(), 4)
        );
        public static final ConfiguredFeature<?, ?> SHIVERSTONE_PATCH = Feature.ORE.configured(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.SHIVERSTONE.get().defaultBlockState(), 33)
        );
        public static final ConfiguredFeature<?, ?> DEEPSOIL_PATCH = Feature.ORE.configured(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.DEEPSOIL.get().defaultBlockState(), 33)
        );
        public static final ConfiguredFeature<?, ?> ICE_PATCH = Feature.ORE.configured(
                new OreFeatureConfig(UNDERGARDEN_FILLER, Blocks.PACKED_ICE.defaultBlockState(), 33)
        );
        public static final ConfiguredFeature<?, ?> SEDIMENT_PATCH = Feature.ORE.configured(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.SEDIMENT.get().defaultBlockState(), 33)
        );
        public static final ConfiguredFeature<?, ?> SEDIMENT_DISK = Feature.DISK.configured(
                new SphereReplaceConfig(UGBlocks.SEDIMENT.get().defaultBlockState(), FeatureSpread.of(4, 4), 2, ImmutableList.of(UGBlocks.DEEPSOIL.get().defaultBlockState(), UGBlocks.DEEPTURF_BLOCK.get().defaultBlockState(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get().defaultBlockState(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get().defaultBlockState()))
        );

        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SMOGSTEM_TREE = UNDERGARDEN_TREE.get().configured(
                new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.SMOGSTEM_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(UGBlocks.SMOGSTEM_LEAVES.get().defaultBlockState()),
                        new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 2),
                        new SmogstemTrunkPlacer(10, 2, 2),
                        new TwoLayerFeature(1, 1, 2))
                        .ignoreVines().build()
        );
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> WIGGLEWOOD_TREE = UNDERGARDEN_TREE.get().configured(
                new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.WIGGLEWOOD_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(UGBlocks.WIGGLEWOOD_LEAVES.get().defaultBlockState()),
                        new BushFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 0),
                        new ForkyTrunkPlacer(3, 1, 1),
                        new TwoLayerFeature(1, 0, 2))
                        .ignoreVines().build()
        );
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> GRONGLE_TREE = UNDERGARDEN_TREE.get().configured(
                new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.GRONGLE_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(UGBlocks.GRONGLE_LEAVES.get().defaultBlockState()),
                        new DarkOakFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0)),
                        new GrongleTrunkPlacer(10, 2, 19),
                        new TwoLayerFeature(1, 1, 2))
                        .decorators(ImmutableList.of(GrongleLeafDecorator.INSTANCE))
                        .build()
        );
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> GRONGLE_BUSH = UNDERGARDEN_TREE.get().configured(
                new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.GRONGLE_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(UGBlocks.GRONGLE_LEAVES.get().defaultBlockState()),
                        new DarkOakFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0)),
                        new StraightTrunkPlacer(1, 0, 0),
                        new TwoLayerFeature(0, 0, 0))
                        .build()
        );

        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SMOGSTEM_TREE_TALL = UNDERGARDEN_TREE.get().configured(
                new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.SMOGSTEM_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(UGBlocks.SMOGSTEM_LEAVES.get().defaultBlockState()),
                        new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 2),
                        new SmogstemTrunkPlacer(15, 4, 4),
                        new TwoLayerFeature(1, 1, 2))
                        .ignoreVines().build()
        );
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> WIGGLEWOOD_TREE_TALL = UNDERGARDEN_TREE.get().configured(
                new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.WIGGLEWOOD_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(UGBlocks.WIGGLEWOOD_LEAVES.get().defaultBlockState()),
                        new BushFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 0),
                        new ForkyTrunkPlacer(6, 2, 2),
                        new TwoLayerFeature(1, 0, 2))
                        .ignoreVines().build()
        );

        public static final ConfiguredFeature<BigMushroomFeatureConfig, ?> BLOOD_MUSHROOM = UGFeatures.BLOOD_MUSHROOM.get().configured(
                new BigMushroomFeatureConfig(
                        new SimpleBlockStateProvider(UGBlocks.BLOOD_MUSHROOM_CAP.get().defaultBlockState()),
                        new SimpleBlockStateProvider(UGBlocks.BLOOD_MUSHROOM_STALK.get().defaultBlockState()),
                        3)
        );
        public static final ConfiguredFeature<BigMushroomFeatureConfig, ?> INDIGO_MUSHROOM = UGFeatures.INDIGO_MUSHROOM.get().configured(
                new BigMushroomFeatureConfig(
                        new SimpleBlockStateProvider(UGBlocks.INDIGO_MUSHROOM_CAP.get().defaultBlockState()),
                        new SimpleBlockStateProvider(UGBlocks.INDIGO_MUSHROOM_STALK.get().defaultBlockState()),
                        3)
        );
        public static final ConfiguredFeature<BigMushroomFeatureConfig, ?> INK_MUSHROOM = UGFeatures.INK_MUSHROOM.get().configured(
                new BigMushroomFeatureConfig(
                        new SimpleBlockStateProvider(UGBlocks.INK_MUSHROOM_CAP.get().defaultBlockState()),
                        new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.defaultBlockState()),
                        5)
        );
        public static final ConfiguredFeature<BigMushroomFeatureConfig, ?> VEIL_MUSHROOM = UGFeatures.VEIL_MUSHROOM.get().configured(
                new BigMushroomFeatureConfig(
                        new SimpleBlockStateProvider(UGBlocks.VEIL_MUSHROOM_CAP.get().defaultBlockState()),
                        new SimpleBlockStateProvider(UGBlocks.VEIL_MUSHROOM_STALK.get().defaultBlockState()),
                        2)
        );

        public static final ConfiguredFeature<?, ?> DEPTHROCK_BOULDER = Feature.FOREST_ROCK.configured(
                new BlockStateFeatureConfig(UGBlocks.DEPTHROCK.get().defaultBlockState())
        );
        public static final ConfiguredFeature<?, ?> SHIVERSTONE_BOULDER = Feature.FOREST_ROCK.configured(
                new BlockStateFeatureConfig(UGBlocks.SHIVERSTONE.get().defaultBlockState())
        );

        public static final ConfiguredFeature<?, ?> GLOWING_KELP = UGFeatures.GLOWING_KELP.get().configured(
                IFeatureConfig.NONE
        );
        public static final ConfiguredFeature<?, ?> SMOG_VENT = UGFeatures.SMOG_VENT.get().configured(
                IFeatureConfig.NONE
        );
        public static final ConfiguredFeature<?, ?> DROOPVINE = UGFeatures.DROOPVINE.get().configured(
                IFeatureConfig.NONE
        );
        public static final ConfiguredFeature<?, ?> ICE_PILLAR = UGFeatures.ICE_PILLAR.get().configured(
                IFeatureConfig.NONE
        );
    }

    public static void registerConfiguredFeatures() {
        register("spring", ConfiguredFeatures.SPRING.decorated(Placement.RANGE_BIASED.configured(new TopSolidRangeConfig(8, 8, 256))).squared().count(50));
        register("virulent_spring", ConfiguredFeatures.VIRULENT_SPRING.decorated(Placement.RANGE_BIASED.configured(new TopSolidRangeConfig(8, 8, 256))).squared().count(50));
        register("bog_virulent_spring", ConfiguredFeatures.VIRULENT_SPRING.decorated(Placement.RANGE_BIASED.configured(new TopSolidRangeConfig(8, 8, 256))).squared().count(100));

        register("lake_virulent", ConfiguredFeatures.VIRULENT_LAKE.decorated(Placement.WATER_LAKE.configured(new ChanceConfig(8))));
        register("bog_lake_virulent", ConfiguredFeatures.VIRULENT_LAKE.decorated(Placement.WATER_LAKE.configured(new ChanceConfig(2))));

        register("bog_pond", ConfiguredFeatures.BOG_PONDS.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(40))));
        register("gronglegrowth_pond", ConfiguredFeatures.GRONGLEGROWTH_PONDS.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(40))));

        register("lilypads", ConfiguredFeatures.LILYPADS.range(256).count(5));
        register("deepturf_patch", ConfiguredFeatures.DEEPTURF_PATCH.range(256).squared().count(100));
        register("ashen_patch", ConfiguredFeatures.ASHEN_DEEPTURF_PATCH.range(256).squared().count(100));
        register("frozen_patch", ConfiguredFeatures.FROZEN_DEEPTURF_PATCH.range(256).squared().count(100));
        register("shimmerweed_patch", ConfiguredFeatures.SHIMMERWEED_PATCH.range(256).squared().count(75));
        register("pebble_patch", ConfiguredFeatures.PEBBLE_PATCH.range(256).squared().count(75));
        register("ditchbulb_patch", ConfiguredFeatures.DITCHBULB_PATCH.range(256).squared().count(50));

        register("tall_deepturf_patch", ConfiguredFeatures.TALL_DEEPTURF_PATCH.range(256).squared().count(100));
        register("tall_shimmerweed_patch", ConfiguredFeatures.TALL_SHIMMERWEED_PATCH.range(256).squared().count(50));

        register("indigo_patch", ConfiguredFeatures.INDIGO_MUSHROOM_PATCH.range(256).squared().count(1));
        register("veil_patch", ConfiguredFeatures.VEIL_MUSHROOM_PATCH.range(256).squared().count(1));
        register("ink_patch", ConfiguredFeatures.INK_MUSHROOM_PATCH.range(256).squared().count(1));
        register("blood_patch", ConfiguredFeatures.BLOOD_MUSHROOM_PATCH.range(256).squared().count(1));

        register("bog_indigo_patch", ConfiguredFeatures.INDIGO_MUSHROOM_PATCH.range(256).squared().count(25));
        register("bog_veil_patch", ConfiguredFeatures.VEIL_MUSHROOM_PATCH.range(256).squared().count(25));
        register("bog_ink_patch", ConfiguredFeatures.INK_MUSHROOM_PATCH.range(256).squared().count(25));
        register("bog_blood_patch", ConfiguredFeatures.BLOOD_MUSHROOM_PATCH.range(256).squared().count(25));

        register("underbean_patch", ConfiguredFeatures.UNDERBEAN_BUSH_PATCH.range(256).squared().count(5));
        register("blisterberry_patch", ConfiguredFeatures.BLISTERBERRY_BUSH_PATCH.range(256).squared().count(5));
        register("gloomgourd_patch", ConfiguredFeatures.GLOOMGOURD_PATCH.range(256).squared().count(5));

        register("coal_ore", ConfiguredFeatures.COAL_ORE.range(256).squared().count(30));
        register("iron_ore", ConfiguredFeatures.IRON_ORE.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(200, 0, 56))).squared().count(8));
        register("gold_ore", ConfiguredFeatures.GOLD_ORE.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(220, 0, 36))).squared().count(2));
        register("diamond_ore", ConfiguredFeatures.DIAMOND_ORE.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(230, 0, 26))).squared());
        register("cloggrum_ore", ConfiguredFeatures.CLOGGRUM_ORE.range(128).squared().count(20));
        register("froststeel_ore", ConfiguredFeatures.FROSTSTEEL_ORE.range(64).squared().count(15));
        register("utherium_ore", ConfiguredFeatures.UTHERIUM_ORE.range(32).squared().count(5));
        register("regalium_ore", ConfiguredFeatures.REGALIUM_ORE.range(12).squared().count(3));

        register("shiverstone_patch", ConfiguredFeatures.SHIVERSTONE_PATCH.range(256).squared().count(10));
        register("deepsoil_patch", ConfiguredFeatures.DEEPSOIL_PATCH.range(256).squared().count(10));
        register("ice_patch", ConfiguredFeatures.ICE_PATCH.range(256).squared().count(20));
        register("sediment_patch", ConfiguredFeatures.SEDIMENT_PATCH.range(32).squared().count(20));
        register("sediment_disk", ConfiguredFeatures.SEDIMENT_DISK.range(32).squared().count(20));

        register("smogstem_tree", ConfiguredFeatures.SMOGSTEM_TREE.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(8))));
        register("wigglewood_tree", ConfiguredFeatures.WIGGLEWOOD_TREE.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(8))));
        register("grongle_tree", ConfiguredFeatures.GRONGLE_TREE.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(8))));
        register("grongle_bush", ConfiguredFeatures.GRONGLE_BUSH.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(4))));

        register("smogstem_tree_tall", ConfiguredFeatures.SMOGSTEM_TREE_TALL.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(8))));
        register("wigglewood_tree_tall", ConfiguredFeatures.WIGGLEWOOD_TREE_TALL.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(8))));

        register("huge_blood_mushroom", ConfiguredFeatures.BLOOD_MUSHROOM.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(2))));
        register("huge_indigo_mushroom", ConfiguredFeatures.INDIGO_MUSHROOM.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(2))));
        register("huge_ink_mushroom", ConfiguredFeatures.INK_MUSHROOM.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(2))));
        register("huge_veil_mushroom", ConfiguredFeatures.VEIL_MUSHROOM.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(2))));

        register("depthrock_boulder", ConfiguredFeatures.DEPTHROCK_BOULDER.range(256).squared().count(5));
        register("shiverstone_boulder", ConfiguredFeatures.SHIVERSTONE_BOULDER.range(256).squared().count(5));

        register("glowing_kelp", ConfiguredFeatures.GLOWING_KELP.range(30).squared().count(100));
        register("smog_vent", ConfiguredFeatures.SMOG_VENT.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(8))));
        register("droopvine", ConfiguredFeatures.DROOPVINE.range(256).squared().count(100));
        register("ice_pillar", ConfiguredFeatures.ICE_PILLAR.range(256).squared().count(50));
    }

    private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> feature) {
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Undergarden.MODID, name), feature);
    }
}