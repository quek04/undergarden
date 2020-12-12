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
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.BushFoliagePlacer;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UGMod;
import quek.undergarden.block.BlisterberryBushBlock;
import quek.undergarden.block.UnderbeanBushBlock;
import quek.undergarden.world.gen.feature.*;

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

    public static void registerConfiguredFeatures() {
        final RuleTest DEPTHROCK_FILLER = new BlockMatchRuleTest(UGBlocks.DEPTHROCK.get());

        register("spring", Feature.SPRING_FEATURE.withConfiguration(new LiquidsConfig(Fluids.WATER.getDefaultState(), false, 4, 1, ImmutableSet.of(UGBlocks.DEPTHROCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.TREMBLECRUST.get()))).withPlacement(Placement.RANGE_BIASED.configure(new TopSolidRangeConfig(8, 8, 256))).square().func_242731_b(50));
        register("virulent_spring", Feature.SPRING_FEATURE.withConfiguration(new LiquidsConfig(UGFluids.VIRULENT_MIX_SOURCE.get().getDefaultState(), false, 4, 1, ImmutableSet.of(UGBlocks.DEPTHROCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.TREMBLECRUST.get()))).withPlacement(Placement.RANGE_BIASED.configure(new TopSolidRangeConfig(8, 8, 256))).square().func_242731_b(50));
        register("bog_virulent_spring", Feature.SPRING_FEATURE.withConfiguration(new LiquidsConfig(UGFluids.VIRULENT_MIX_SOURCE.get().getDefaultState(), false, 4, 1, ImmutableSet.of(UGBlocks.DEPTHROCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.TREMBLECRUST.get()))).withPlacement(Placement.RANGE_BIASED.configure(new TopSolidRangeConfig(8, 8, 256))).square().func_242731_b(100));

        register("lake_virulent", Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(UGBlocks.VIRULENT_MIX.get().getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(8))));
        register("bog_lake_virulent", Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(UGBlocks.VIRULENT_MIX.get().getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(2))));

        register("bog_pond", Feature.DELTA_FEATURE.withConfiguration(new BasaltDeltasFeature(UGBlocks.VIRULENT_MIX.get().getDefaultState(), UGBlocks.COARSE_DEEPSOIL.get().getDefaultState(), FeatureSpread.func_242253_a(6, 8), FeatureSpread.func_242253_a(0, 4))).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(40))));

        register("lilypads", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_PAD.getDefaultState()), new SimpleBlockPlacer())).tries(32).requiresWater().func_227317_b_().build()).range(256).func_242731_b(5));
        register("deepturf_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.DEEPTURF.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).func_227317_b_().build()).range(256).square().func_242731_b(100));
        register("ashen_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.ASHEN_DEEPTURF.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(UGBlocks.ASHEN_DEEPTURF_BLOCK.get())).func_227317_b_().build()).range(256).square().func_242731_b(100));
        register("shimmerweed_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.SHIMMERWEED.get().getDefaultState()), new SimpleBlockPlacer())).tries(32).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).func_227317_b_().build()).range(256).square().func_242731_b(75));
        register("pebble_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.DEPTHROCK_PEBBLES.get().getDefaultState()), new SimpleBlockPlacer())).tries(32).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get())).func_227317_b_().build()).range(256).square().func_242731_b(75));
        register("ditchbulb_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.DITCHBULB_PLANT.get().getDefaultState()), new SimpleBlockPlacer())).tries(16).whitelist(ImmutableSet.of(UGBlocks.DEPTHROCK.get())).func_227317_b_().build()).range(256).square().func_242731_b(50));

        register("tall_deepturf_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.TALL_DEEPTURF.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(32).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).func_227317_b_().build()).range(256).square().func_242731_b(100));
        register("tall_shimmerweed_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.TALL_SHIMMERWEED.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(16).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).func_227317_b_().build()).range(256).square().func_242731_b(50));

        register("indigo_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.INDIGO_MUSHROOM.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).range(256).square().func_242731_b(1));
        register("veil_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.VEIL_MUSHROOM.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).range(256).square().func_242731_b(1));
        register("ink_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.INK_MUSHROOM.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).range(256).square().func_242731_b(1));
        register("blood_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.BLOOD_MUSHROOM.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).range(256).square().func_242731_b(1));
        register("gronglet_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.GRONGLET.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).range(256).square().func_242731_b(100));

        register("bog_indigo_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.INDIGO_MUSHROOM.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).range(256).square().func_242731_b(25));
        register("bog_veil_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.VEIL_MUSHROOM.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).range(256).square().func_242731_b(25));
        register("bog_ink_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.INK_MUSHROOM.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).range(256).square().func_242731_b(25));
        register("bog_blood_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.BLOOD_MUSHROOM.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).range(256).square().func_242731_b(25));

        register("underbean_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.UNDERBEAN_BUSH.get().getDefaultState().with(UnderbeanBushBlock.AGE, 3)), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).func_227317_b_().build()).range(256).square().func_242731_b(5));
        register("blisterberry_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.BLISTERBERRY_BUSH.get().getDefaultState().with(BlisterberryBushBlock.AGE, 3)), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(UGBlocks.ASHEN_DEEPTURF_BLOCK.get())).func_227317_b_().build()).range(256).square().func_242731_b(5));
        register("gloomgourd_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.GLOOMGOURD.get().getDefaultState()), new SimpleBlockPlacer())).tries(16).whitelist(ImmutableSet.of(UGBlocks.DEEPTURF_BLOCK.get())).func_227317_b_().build()).range(256).square().func_242731_b(5));

        register("coal_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK_FILLER, UGBlocks.COAL_ORE.get().getDefaultState(), 17)).range(256).square().func_242731_b(20));
        register("iron_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK_FILLER, UGBlocks.IRON_ORE.get().getDefaultState(), 9)).range(64).square().func_242731_b(20));
        register("gold_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK_FILLER, UGBlocks.GOLD_ORE.get().getDefaultState(), 9)).range(32).square().func_242731_b(2));
        register("diamond_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK_FILLER, UGBlocks.DIAMOND_ORE.get().getDefaultState(), 8)).range(16).square().func_242731_b(8));
        register("cloggrum_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK_FILLER, UGBlocks.CLOGGRUM_ORE.get().getDefaultState(), 5)).range(128).square().func_242731_b(15));
        register("froststeel_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK_FILLER, UGBlocks.FROSTSTEEL_ORE.get().getDefaultState(), 4)).range(64).square().func_242731_b(3));
        register("utherium_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK_FILLER, UGBlocks.UTHERIUM_ORE.get().getDefaultState(), 8)).range(32).square().func_242731_b(1));
        register("regalium_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK_FILLER, UGBlocks.REGALIUM_ORE.get().getDefaultState(), 4)).range(11).square().func_242731_b(2));

        register("shiverstone_patch", Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK_FILLER, UGBlocks.SHIVERSTONE.get().getDefaultState(), 33)).range(256).square().func_242731_b(10));
        register("deepsoil_patch", Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK_FILLER, UGBlocks.DEEPSOIL.get().getDefaultState(), 33)).range(256).square().func_242731_b(10));

        register("smogstem_tree", UNDERGARDEN_TREE.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.SMOGSTEM_LOG.get().getDefaultState()),
                        new SimpleBlockStateProvider(UGBlocks.SMOGSTEM_LEAVES.get().getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 2),
                        new StraightTrunkPlacer(10, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .setIgnoreVines().build()).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(8))));
        register("wigglewood_tree", UNDERGARDEN_TREE.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.WIGGLEWOOD_LOG.get().getDefaultState()),
                        new SimpleBlockStateProvider(UGBlocks.WIGGLEWOOD_LEAVES.get().getDefaultState()),
                        new BushFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 0),
                        new ForkyTrunkPlacer(3, 0, 0),
                        new TwoLayerFeature(1, 0, 2)))
                        .setIgnoreVines().build()).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(8))));

        register("huge_blood_mushroom", BLOOD_MUSHROOM.get().withConfiguration(
                new BigMushroomFeatureConfig(new SimpleBlockStateProvider(UGBlocks.BLOOD_MUSHROOM_CAP.get().getDefaultState()), new SimpleBlockStateProvider(UGBlocks.BLOOD_MUSHROOM_STALK.get().getDefaultState()), 3)).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(2))));
        register("huge_indigo_mushroom", INDIGO_MUSHROOM.get().withConfiguration(
                new BigMushroomFeatureConfig(new SimpleBlockStateProvider(UGBlocks.INDIGO_MUSHROOM_CAP.get().getDefaultState()), new SimpleBlockStateProvider(UGBlocks.INDIGO_MUSHROOM_STALK.get().getDefaultState()), 3)).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(2))));
        register("huge_ink_mushroom", INK_MUSHROOM.get().withConfiguration(
                new BigMushroomFeatureConfig(new SimpleBlockStateProvider(UGBlocks.INK_MUSHROOM_CAP.get().getDefaultState()), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.getDefaultState()), 5)).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(2))));
        register("huge_veil_mushroom", VEIL_MUSHROOM.get().withConfiguration(
                new BigMushroomFeatureConfig(new SimpleBlockStateProvider(UGBlocks.VEIL_MUSHROOM_CAP.get().getDefaultState()), new SimpleBlockStateProvider(UGBlocks.VEIL_MUSHROOM_STALK.get().getDefaultState()), 2)).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(2))));

        register("glowing_kelp", GLOWING_KELP.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(32).square().func_242731_b(100));
        register("smog_vent", SMOG_VENT.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(8))));
        register("droopvine", DROOPVINE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(256).square().func_242731_b(100));
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(UGMod.MODID, name), feature);
    }
}