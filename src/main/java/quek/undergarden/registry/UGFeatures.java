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
import quek.undergarden.block.world.BlisterberryBushBlock;
import quek.undergarden.block.world.UnderbeanBushBlock;
import quek.undergarden.world.gen.feature.DroopvineFeature;
import quek.undergarden.world.gen.feature.GlowingKelpFeature;
import quek.undergarden.world.gen.feature.SmogVentFeature;
import quek.undergarden.world.gen.feature.UGTreeFeature;

public class UGFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, UGMod.MODID);

    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> undergarden_tree = FEATURES.register(
            "undergarden_tree", () -> new UGTreeFeature(BaseTreeFeatureConfig.CODEC));

    public static final RegistryObject<Feature<NoFeatureConfig>> glowing_kelp = FEATURES.register(
            "glowing_kelp", () -> new GlowingKelpFeature(NoFeatureConfig.field_236558_a_));

    public static final RegistryObject<Feature<NoFeatureConfig>> smog_vent = FEATURES.register(
            "smog_vent", () -> new SmogVentFeature(NoFeatureConfig.field_236558_a_));

    public static final RegistryObject<Feature<NoFeatureConfig>> droopvine = FEATURES.register(
            "droopvine", () -> new DroopvineFeature(NoFeatureConfig.field_236558_a_));

    public static void registerConfiguredFeatures() {
        final RuleTest depthrock_filler = new BlockMatchRuleTest(UGBlocks.depthrock.get());

        register("spring", Feature.SPRING_FEATURE.withConfiguration(new LiquidsConfig(Fluids.WATER.getDefaultState(), false, 4, 1, ImmutableSet.of(UGBlocks.depthrock.get(), UGBlocks.deepsoil.get(), UGBlocks.tremblecrust.get()))).withPlacement(Placement.field_242908_m.configure(new TopSolidRangeConfig(8, 8, 256))).func_242728_a().func_242731_b(50));
        register("virulent_spring", Feature.SPRING_FEATURE.withConfiguration(new LiquidsConfig(UGFluids.virulent_mix_source.get().getDefaultState(), false, 4, 1, ImmutableSet.of(UGBlocks.depthrock.get(), UGBlocks.deepsoil.get(), UGBlocks.tremblecrust.get()))).withPlacement(Placement.field_242908_m.configure(new TopSolidRangeConfig(8, 8, 256))).func_242728_a().func_242731_b(50));

        register("lake_virulent", Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(UGBlocks.virulent_mix.get().getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(8))));

        register("lilypads", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_PAD.getDefaultState()), new SimpleBlockPlacer())).tries(32).requiresWater().func_227317_b_().build()).func_242733_d(32).func_242731_b(5));
        register("deepturf_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.deepturf.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(UGBlocks.deepturf_block.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(100));
        register("ashen_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.ashen_deepturf.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(UGBlocks.ashen_deepturf_block.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(100));
        register("shimmerweed_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.shimmerweed.get().getDefaultState()), new SimpleBlockPlacer())).tries(32).whitelist(ImmutableSet.of(UGBlocks.deepturf_block.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(75));
        register("pebble_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.depthrock_pebbles.get().getDefaultState()), new SimpleBlockPlacer())).tries(32).whitelist(ImmutableSet.of(UGBlocks.deepturf_block.get(), UGBlocks.ashen_deepturf_block.get(), UGBlocks.depthrock.get(), UGBlocks.shiverstone.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(75));
        register("ditchbulb_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.ditchbulb_plant.get().getDefaultState()), new SimpleBlockPlacer())).tries(16).whitelist(ImmutableSet.of(UGBlocks.depthrock.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(50));

        register("tall_deepturf_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.tall_deepturf.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(32).whitelist(ImmutableSet.of(UGBlocks.deepturf_block.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(100));
        register("tall_shimmerweed_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.tall_shimmerweed.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(16).whitelist(ImmutableSet.of(UGBlocks.deepturf_block.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(50));

        register("indigo_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.indigo_mushroom.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(1));
        register("veil_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.veil_mushroom.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(1));
        register("ink_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.ink_mushroom.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(1));
        register("blood_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.blood_mushroom.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(1));
        register("gronglet_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.gronglet.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(100));

        register("underbean_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.underbean_bush.get().getDefaultState().with(UnderbeanBushBlock.AGE, 3)), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(UGBlocks.deepturf_block.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(5));
        register("blisterberry_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.blisterberry_bush.get().getDefaultState().with(BlisterberryBushBlock.AGE, 3)), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(UGBlocks.ashen_deepturf_block.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(5));
        register("gloomgourd_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UGBlocks.gloomgourd.get().getDefaultState()), new SimpleBlockPlacer())).tries(16).whitelist(ImmutableSet.of(UGBlocks.deepturf_block.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(5));

        register("coal_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UGBlocks.coal_ore.get().getDefaultState(), 17)).func_242733_d(256).func_242728_a().func_242731_b(20));
        register("iron_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UGBlocks.iron_ore.get().getDefaultState(), 9)).func_242733_d(64).func_242728_a().func_242731_b(20));
        register("gold_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UGBlocks.gold_ore.get().getDefaultState(), 9)).func_242733_d(32).func_242728_a().func_242731_b(2));
        register("diamond_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UGBlocks.diamond_ore.get().getDefaultState(), 8)).func_242733_d(16).func_242728_a().func_242731_b(8));
        register("cloggrum_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UGBlocks.cloggrum_ore.get().getDefaultState(), 5)).func_242733_d(128).func_242728_a().func_242731_b(15));
        register("froststeel_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UGBlocks.froststeel_ore.get().getDefaultState(), 4)).func_242733_d(64).func_242728_a().func_242731_b(3));
        register("utherium_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UGBlocks.utherium_ore.get().getDefaultState(), 8)).func_242733_d(32).func_242728_a().func_242731_b(1));
        register("regalium_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UGBlocks.regalium_ore.get().getDefaultState(), 4)).func_242733_d(11).func_242728_a().func_242731_b(2));

        register("shiverstone_patch", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UGBlocks.shiverstone.get().getDefaultState(), 33)).func_242733_d(256).func_242728_a().func_242731_b(10));
        register("deepsoil_patch", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UGBlocks.deepsoil.get().getDefaultState(), 33)).func_242733_d(256).func_242728_a().func_242731_b(10));

        register("smogstem_tree", undergarden_tree.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.smogstem_log.get().getDefaultState()),
                        new SimpleBlockStateProvider(UGBlocks.smogstem_leaves.get().getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 2),
                        new StraightTrunkPlacer(10, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .setIgnoreVines().build()).withPlacement(Placement.field_242897_C.configure(new FeatureSpreadConfig(8))));
        register("wigglewood_tree", undergarden_tree.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.wigglewood_log.get().getDefaultState()),
                        new SimpleBlockStateProvider(UGBlocks.wigglewood_leaves.get().getDefaultState()),
                        new BushFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 0),
                        new ForkyTrunkPlacer(3, 0, 0),
                        new TwoLayerFeature(1, 0, 2)))
                        .setIgnoreVines().build()).withPlacement(Placement.field_242897_C.configure(new FeatureSpreadConfig(8))));

        register("glowing_kelp", glowing_kelp.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).func_242733_d(32).func_242728_a().func_242731_b(100));
        register("smog_vent", smog_vent.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.field_242897_C.configure(new FeatureSpreadConfig(8))));
        register("droopvine", droopvine.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).func_242733_d(256).func_242728_a().func_242731_b(100));
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(UGMod.MODID, name), feature);
    }
}
