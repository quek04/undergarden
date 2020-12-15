package quek.undergarden.registry;

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
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UGMod;
import quek.undergarden.block.BlisterberryBushBlock;
import quek.undergarden.block.UnderbeanBushBlock;
import quek.undergarden.world.gen.feature.*;
import quek.undergarden.world.gen.trunkplacer.*;

public class UGFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, UGMod.MODID);

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
            "glowing_kelp", () -> new GlowingKelpFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<Feature<NoFeatureConfig>> SMOG_VENT = FEATURES.register(
            "smog_vent", () -> new SmogVentFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<Feature<NoFeatureConfig>> DROOPVINE = FEATURES.register(
            "droopvine", () -> new DroopvineFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<Feature<NoFeatureConfig>> ICE_PILLAR = FEATURES.register(
            "ice_pillar", () -> new IcePillarFeature(NoFeatureConfig.field_236558_a_));

    public static final class ConfiguredFeatures {
        static final RuleTest UNDERGARDEN_FILLER = new TagMatchRuleTest(UGTags.Blocks.BASE_STONE_UNDERGARDEN);

        public static final ConfiguredFeature<?, ?> SPRING = Feature.SPRING_FEATURE.withConfiguration(
                new LiquidsConfig(
                        Fluids.WATER.getDefaultState(),
                        false,
                        4,
                        1,
                        ImmutableSet.of(UGBlocks.DEPTHROCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.TREMBLECRUST.get()))
        );
        public static final ConfiguredFeature<?, ?> VIRULENT_SPRING = Feature.SPRING_FEATURE.withConfiguration(
                new LiquidsConfig(
                        UGFluids.VIRULENT_MIX_SOURCE.get().getDefaultState(),
                        false,
                        4,
                        1,
                        ImmutableSet.of(UGBlocks.DEPTHROCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.TREMBLECRUST.get()))
        );

        public static final ConfiguredFeature<?, ?> VIRULENT_LAKE = Feature.LAKE.withConfiguration(
                new BlockStateFeatureConfig(UGBlocks.VIRULENT_MIX.get().getDefaultState())
        );

        public static final ConfiguredFeature<?, ?> BOG_PONDS = Feature.DELTA_FEATURE.withConfiguration(
                new BasaltDeltasFeature(
                        UGBlocks.VIRULENT_MIX.get().getDefaultState(),
                        UGBlocks.COARSE_DEEPSOIL.get().getDefaultState(),
                        FeatureSpread.func_242253_a(6, 8),
                        FeatureSpread.func_242253_a(0, 4))
        );

        public static final ConfiguredFeature<?, ?> LILYPADS = Feature.RANDOM_PATCH.withConfiguration(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(Blocks.LILY_PAD.getDefaultState()),
                        new SimpleBlockPlacer()))
                        .tries(32).requiresWater().func_227317_b_().build()
        );
        public static final ConfiguredFeature<?, ?> DEEPTURF_PATCH = Feature.RANDOM_PATCH.withConfiguration(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.DEEPTURF.get().getDefaultState()), new SimpleBlockPlacer()))
                        .tries(64).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).func_227317_b_().build()
        );
        public static final ConfiguredFeature<?, ?> ASHEN_DEEPTURF_PATCH = Feature.RANDOM_PATCH.withConfiguration(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.ASHEN_DEEPTURF.get().getDefaultState()), new SimpleBlockPlacer()))
                        .tries(64).whitelist(ImmutableSet.of(UGBlocks.ASHEN_DEEPTURF_BLOCK.get())).func_227317_b_().build()
        );
        public static final ConfiguredFeature<?, ?> SHIMMERWEED_PATCH = Feature.RANDOM_PATCH.withConfiguration(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.SHIMMERWEED.get().getDefaultState()), new SimpleBlockPlacer()))
                        .tries(32).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).func_227317_b_().build()
        );
        public static final ConfiguredFeature<?, ?> PEBBLE_PATCH = Feature.RANDOM_PATCH.withConfiguration(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.DEPTHROCK_PEBBLES.get().getDefaultState()), new SimpleBlockPlacer()))
                        .tries(32).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get())).func_227317_b_().build()
        );
        public static final ConfiguredFeature<?, ?> DITCHBULB_PATCH = Feature.RANDOM_PATCH.withConfiguration(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.DITCHBULB_PLANT.get().getDefaultState()), new SimpleBlockPlacer()))
                        .tries(16).whitelist(ImmutableSet.of(UGBlocks.DEPTHROCK.get())).func_227317_b_().build()
        );
        public static final ConfiguredFeature<?, ?> TALL_DEEPTURF_PATCH = Feature.RANDOM_PATCH.withConfiguration(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.TALL_DEEPTURF.get().getDefaultState()), new DoublePlantBlockPlacer()))
                        .tries(32).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).func_227317_b_().build()
        );
        public static final ConfiguredFeature<?, ?> TALL_SHIMMERWEED_PATCH = Feature.RANDOM_PATCH.withConfiguration(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.TALL_SHIMMERWEED.get().getDefaultState()), new DoublePlantBlockPlacer()))
                        .tries(16).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).func_227317_b_().build()
        );
        public static final ConfiguredFeature<?, ?> INDIGO_MUSHROOM_PATCH = Feature.RANDOM_PATCH.withConfiguration(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.INDIGO_MUSHROOM.get().getDefaultState()), new SimpleBlockPlacer()))
                        .tries(64).func_227317_b_().build()
        );
        public static final ConfiguredFeature<?, ?> VEIL_MUSHROOM_PATCH = Feature.RANDOM_PATCH.withConfiguration(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.VEIL_MUSHROOM.get().getDefaultState()), new SimpleBlockPlacer()))
                        .tries(64).func_227317_b_().build()
        );
        public static final ConfiguredFeature<?, ?> INK_MUSHROOM_PATCH = Feature.RANDOM_PATCH.withConfiguration(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.INK_MUSHROOM.get().getDefaultState()), new SimpleBlockPlacer()))
                        .tries(64).func_227317_b_().build()
        );
        public static final ConfiguredFeature<?, ?> BLOOD_MUSHROOM_PATCH = Feature.RANDOM_PATCH.withConfiguration(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.BLOOD_MUSHROOM.get().getDefaultState()), new SimpleBlockPlacer()))
                        .tries(64).func_227317_b_().build()
        );
        public static final ConfiguredFeature<?, ?> GRONGLET_PATCH = Feature.RANDOM_PATCH.withConfiguration(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.GRONGLET.get().getDefaultState()), new SimpleBlockPlacer()))
                        .tries(64).func_227317_b_().build()
        );
        public static final ConfiguredFeature<?, ?> UNDERBEAN_BUSH_PATCH = Feature.RANDOM_PATCH.withConfiguration(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.UNDERBEAN_BUSH.get().getDefaultState().with(UnderbeanBushBlock.AGE, 3)), new SimpleBlockPlacer()))
                        .tries(64).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).func_227317_b_().build()
        );
        public static final ConfiguredFeature<?, ?> BLISTERBERRY_BUSH_PATCH = Feature.RANDOM_PATCH.withConfiguration(
                (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.BLISTERBERRY_BUSH.get().getDefaultState().with(BlisterberryBushBlock.AGE, 3)), new SimpleBlockPlacer()))
                        .tries(64).whitelist(ImmutableSet.of(UGBlocks.ASHEN_DEEPTURF_BLOCK.get())).func_227317_b_().build()
        );
        public static final ConfiguredFeature<?, ?> GLOOMGOURD_PATCH = Feature.RANDOM_PATCH.withConfiguration(
                (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.GLOOMGOURD.get().getDefaultState()), new SimpleBlockPlacer()))
                        .tries(16).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).func_227317_b_().build()
        );

        public static final ConfiguredFeature<?, ?> COAL_ORE = Feature.ORE.withConfiguration(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.COAL_ORE.get().getDefaultState(), 17)
        );
        public static final ConfiguredFeature<?, ?> IRON_ORE = Feature.ORE.withConfiguration(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.IRON_ORE.get().getDefaultState(), 9)
        );
        public static final ConfiguredFeature<?, ?> GOLD_ORE = Feature.ORE.withConfiguration(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.GOLD_ORE.get().getDefaultState(), 9)
        );
        public static final ConfiguredFeature<?, ?> DIAMOND_ORE = Feature.ORE.withConfiguration(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.DIAMOND_ORE.get().getDefaultState(), 8)
        );
        public static final ConfiguredFeature<?, ?> CLOGGRUM_ORE = Feature.ORE.withConfiguration(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.CLOGGRUM_ORE.get().getDefaultState(), 9)
        );
        public static final ConfiguredFeature<?, ?> FROSTSTEEL_ORE = Feature.ORE.withConfiguration(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.FROSTSTEEL_ORE.get().getDefaultState(), 9)
        );
        public static final ConfiguredFeature<?, ?> UTHERIUM_ORE = Feature.ORE.withConfiguration(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.UTHERIUM_ORE.get().getDefaultState(), 8)
        );
        public static final ConfiguredFeature<?, ?> REGALIUM_ORE = Feature.ORE.withConfiguration(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.REGALIUM_ORE.get().getDefaultState(), 4)
        );
        public static final ConfiguredFeature<?, ?> SHIVERSTONE_PATCH = Feature.ORE.withConfiguration(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.SHIVERSTONE.get().getDefaultState(), 33)
        );
        public static final ConfiguredFeature<?, ?> DEEPSOIL_PATCH = Feature.ORE.withConfiguration(
                new OreFeatureConfig(UNDERGARDEN_FILLER, UGBlocks.DEEPSOIL.get().getDefaultState(), 33)
        );
        public static final ConfiguredFeature<?, ?> ICE_PATCH = Feature.ORE.withConfiguration(
                new OreFeatureConfig(UNDERGARDEN_FILLER, Blocks.PACKED_ICE.getDefaultState(), 33)
        );

        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SMOGSTEM_TREE = UNDERGARDEN_TREE.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.SMOGSTEM_WOOD.get().getDefaultState()),
                        new SimpleBlockStateProvider(UGBlocks.SMOGSTEM_LEAVES.get().getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 2),
                        new SmogstemTrunkPlacer(10, 2, 2),
                        new TwoLayerFeature(1, 1, 2)))
                        .setIgnoreVines().build()
        );
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> WIGGLEWOOD_TREE = UNDERGARDEN_TREE.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.WIGGLEWOOD_LOG.get().getDefaultState()),
                        new SimpleBlockStateProvider(UGBlocks.WIGGLEWOOD_LEAVES.get().getDefaultState()),
                        new BushFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 0),
                        new ForkyTrunkPlacer(3, 1, 1),
                        new TwoLayerFeature(1, 0, 2)))
                        .setIgnoreVines().build()
        );

        public static final ConfiguredFeature<BigMushroomFeatureConfig, ?> BLOOD_MUSHROOM = UGFeatures.BLOOD_MUSHROOM.get().withConfiguration(
                new BigMushroomFeatureConfig(
                        new SimpleBlockStateProvider(UGBlocks.BLOOD_MUSHROOM_CAP.get().getDefaultState()),
                        new SimpleBlockStateProvider(UGBlocks.BLOOD_MUSHROOM_STALK.get().getDefaultState()),
                        3)
        );
        public static final ConfiguredFeature<BigMushroomFeatureConfig, ?> INDIGO_MUSHROOM = UGFeatures.INDIGO_MUSHROOM.get().withConfiguration(
                new BigMushroomFeatureConfig(
                        new SimpleBlockStateProvider(UGBlocks.INDIGO_MUSHROOM_CAP.get().getDefaultState()),
                        new SimpleBlockStateProvider(UGBlocks.INDIGO_MUSHROOM_STALK.get().getDefaultState()),
                        3)
        );
        public static final ConfiguredFeature<BigMushroomFeatureConfig, ?> INK_MUSHROOM = UGFeatures.INK_MUSHROOM.get().withConfiguration(
                new BigMushroomFeatureConfig(
                        new SimpleBlockStateProvider(UGBlocks.INK_MUSHROOM_CAP.get().getDefaultState()),
                        new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.getDefaultState()),
                        5)
        );
        public static final ConfiguredFeature<BigMushroomFeatureConfig, ?> VEIL_MUSHROOM = UGFeatures.VEIL_MUSHROOM.get().withConfiguration(
                new BigMushroomFeatureConfig(
                        new SimpleBlockStateProvider(UGBlocks.VEIL_MUSHROOM_CAP.get().getDefaultState()),
                        new SimpleBlockStateProvider(UGBlocks.VEIL_MUSHROOM_STALK.get().getDefaultState()),
                        2)
        );

        public static final ConfiguredFeature<?, ?> DEPTHROCK_BOULDER = Feature.FOREST_ROCK.withConfiguration(
                new BlockStateFeatureConfig(UGBlocks.DEPTHROCK.get().getDefaultState())
        );
        public static final ConfiguredFeature<?, ?> SHIVERSTONE_BOULDER = Feature.FOREST_ROCK.withConfiguration(
                new BlockStateFeatureConfig(UGBlocks.SHIVERSTONE.get().getDefaultState())
        );

        public static final ConfiguredFeature<?, ?> GLOWING_KELP = UGFeatures.GLOWING_KELP.get().withConfiguration(
                IFeatureConfig.NO_FEATURE_CONFIG
        );
        public static final ConfiguredFeature<?, ?> SMOG_VENT = UGFeatures.SMOG_VENT.get().withConfiguration(
                IFeatureConfig.NO_FEATURE_CONFIG
        );
        public static final ConfiguredFeature<?, ?> DROOPVINE = UGFeatures.DROOPVINE.get().withConfiguration(
                IFeatureConfig.NO_FEATURE_CONFIG
        );
        public static final ConfiguredFeature<?, ?> ICE_PILLAR = UGFeatures.ICE_PILLAR.get().withConfiguration(
                IFeatureConfig.NO_FEATURE_CONFIG
        );
    }

    public static void registerConfiguredFeatures() {
        register("spring", ConfiguredFeatures.SPRING.withPlacement(Placement.RANGE_BIASED.configure(new TopSolidRangeConfig(8, 8, 256))).square().func_242731_b(50));
        register("virulent_spring", ConfiguredFeatures.VIRULENT_SPRING.withPlacement(Placement.RANGE_BIASED.configure(new TopSolidRangeConfig(8, 8, 256))).square().func_242731_b(50));
        register("bog_virulent_spring", ConfiguredFeatures.VIRULENT_SPRING.withPlacement(Placement.RANGE_BIASED.configure(new TopSolidRangeConfig(8, 8, 256))).square().func_242731_b(100));

        register("lake_virulent", ConfiguredFeatures.VIRULENT_LAKE.withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(8))));
        register("bog_lake_virulent", ConfiguredFeatures.VIRULENT_LAKE.withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(2))));

        register("bog_pond", ConfiguredFeatures.BOG_PONDS.withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(40))));

        register("lilypads", ConfiguredFeatures.LILYPADS.range(256).func_242731_b(5));
        register("deepturf_patch", ConfiguredFeatures.DEEPTURF_PATCH.range(256).square().func_242731_b(100));
        register("ashen_patch", ConfiguredFeatures.ASHEN_DEEPTURF_PATCH.range(256).square().func_242731_b(100));
        register("shimmerweed_patch", ConfiguredFeatures.SHIMMERWEED_PATCH.range(256).square().func_242731_b(75));
        register("pebble_patch", ConfiguredFeatures.PEBBLE_PATCH.range(256).square().func_242731_b(75));
        register("ditchbulb_patch", ConfiguredFeatures.DITCHBULB_PATCH.range(256).square().func_242731_b(50));

        register("tall_deepturf_patch", ConfiguredFeatures.TALL_DEEPTURF_PATCH.range(256).square().func_242731_b(100));
        register("tall_shimmerweed_patch", ConfiguredFeatures.TALL_SHIMMERWEED_PATCH.range(256).square().func_242731_b(50));

        register("indigo_patch", ConfiguredFeatures.INDIGO_MUSHROOM_PATCH.range(256).square().func_242731_b(1));
        register("veil_patch", ConfiguredFeatures.VEIL_MUSHROOM_PATCH.range(256).square().func_242731_b(1));
        register("ink_patch", ConfiguredFeatures.INK_MUSHROOM_PATCH.range(256).square().func_242731_b(1));
        register("blood_patch", ConfiguredFeatures.BLOOD_MUSHROOM_PATCH.range(256).square().func_242731_b(1));
        register("gronglet_patch", ConfiguredFeatures.GRONGLET_PATCH.range(256).square().func_242731_b(100));

        register("bog_indigo_patch", ConfiguredFeatures.INDIGO_MUSHROOM_PATCH.range(256).square().func_242731_b(25));
        register("bog_veil_patch", ConfiguredFeatures.VEIL_MUSHROOM_PATCH.range(256).square().func_242731_b(25));
        register("bog_ink_patch", ConfiguredFeatures.INK_MUSHROOM_PATCH.range(256).square().func_242731_b(25));
        register("bog_blood_patch", ConfiguredFeatures.BLOOD_MUSHROOM_PATCH.range(256).square().func_242731_b(25));

        register("underbean_patch", ConfiguredFeatures.UNDERBEAN_BUSH_PATCH.range(256).square().func_242731_b(5));
        register("blisterberry_patch", ConfiguredFeatures.BLISTERBERRY_BUSH_PATCH.range(256).square().func_242731_b(5));
        register("gloomgourd_patch", ConfiguredFeatures.GLOOMGOURD_PATCH.range(256).square().func_242731_b(5));

        register("coal_ore", ConfiguredFeatures.COAL_ORE.range(256).square().func_242731_b(30));
        register("iron_ore", ConfiguredFeatures.IRON_ORE.withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(200, 0, 16))).square().func_242731_b(10));
        register("gold_ore", ConfiguredFeatures.GOLD_ORE.withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(220, 0, 16))).square().func_242731_b(5));
        register("diamond_ore", ConfiguredFeatures.DIAMOND_ORE.withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(230, 0, 16))).square().func_242731_b(3));
        register("cloggrum_ore", ConfiguredFeatures.CLOGGRUM_ORE.range(128).square().func_242731_b(20));
        register("froststeel_ore", ConfiguredFeatures.FROSTSTEEL_ORE.range(64).square().func_242731_b(10));
        register("utherium_ore", ConfiguredFeatures.UTHERIUM_ORE.range(32).square().func_242731_b(5));
        register("regalium_ore", ConfiguredFeatures.REGALIUM_ORE.range(11).square().func_242731_b(3));

        register("shiverstone_patch", ConfiguredFeatures.SHIVERSTONE_PATCH.range(256).square().func_242731_b(10));
        register("deepsoil_patch", ConfiguredFeatures.DEEPSOIL_PATCH.range(256).square().func_242731_b(10));
        register("ice_patch", ConfiguredFeatures.ICE_PATCH.range(256).square().func_242731_b(20));

        register("smogstem_tree", ConfiguredFeatures.SMOGSTEM_TREE.withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(8))));
        register("wigglewood_tree", ConfiguredFeatures.WIGGLEWOOD_TREE.withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(8))));

        register("huge_blood_mushroom", ConfiguredFeatures.BLOOD_MUSHROOM.withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(2))));
        register("huge_indigo_mushroom", ConfiguredFeatures.INDIGO_MUSHROOM.withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(2))));
        register("huge_ink_mushroom", ConfiguredFeatures.INK_MUSHROOM.withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(2))));
        register("huge_veil_mushroom", ConfiguredFeatures.VEIL_MUSHROOM.withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(2))));

        register("depthrock_boulder", ConfiguredFeatures.DEPTHROCK_BOULDER.range(256).square().func_242731_b(5));
        register("shiverstone_boulder", ConfiguredFeatures.SHIVERSTONE_BOULDER.range(256).square().func_242731_b(5));

        register("glowing_kelp", ConfiguredFeatures.GLOWING_KELP.range(32).square().func_242731_b(100));
        register("smog_vent", ConfiguredFeatures.SMOG_VENT.withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(8))));
        register("droopvine", ConfiguredFeatures.DROOPVINE.range(256).square().func_242731_b(100));
        register("ice_pillar", ConfiguredFeatures.ICE_PILLAR.range(256).square().func_242731_b(20));
    }

    private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> feature) {
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(UGMod.MODID, name), feature);
    }
}