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
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.foliageplacer.*;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.trunkplacer.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.block.world.*;
import quek.undergarden.world.gen.feature.*;

public class UndergardenFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, UndergardenMod.MODID);

    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> undergarden_tree = FEATURES.register(
            "undergarden_tree", () -> new UndergardenTreeFeature(BaseTreeFeatureConfig.CODEC));

    public static final RegistryObject<Feature<NoFeatureConfig>> glowing_kelp = FEATURES.register(
            "glowing_kelp", () -> new GlowingKelpFeature(NoFeatureConfig.field_236558_a_));

    public static final RegistryObject<Feature<NoFeatureConfig>> smog_vent = FEATURES.register(
            "smog_vent", () -> new SmogVentFeature(NoFeatureConfig.field_236558_a_));

    public static final RegistryObject<Feature<NoFeatureConfig>> enigmatic_statue = FEATURES.register(
            "enigmatic_statue", () -> new EnigmaticStatueFeature(NoFeatureConfig.field_236558_a_));

    public static final RegistryObject<Feature<NoFeatureConfig>> droopvine = FEATURES.register(
            "droopvine", () -> new DroopvineFeature(NoFeatureConfig.field_236558_a_));

    public static void registerConfiguredFeatures() {
        final RuleTest depthrock_filler = new BlockMatchRuleTest(UndergardenBlocks.depthrock.get());

        register("spring", Feature.SPRING_FEATURE.withConfiguration(new LiquidsConfig(Fluids.WATER.getDefaultState(), false, 4, 1, ImmutableSet.of(UndergardenBlocks.depthrock.get(), UndergardenBlocks.deepsoil.get(), UndergardenBlocks.tremblecrust.get()))).withPlacement(Placement.field_242908_m.configure(new TopSolidRangeConfig(8, 8, 256))).func_242728_a().func_242731_b(50));
        register("virulent_spring", Feature.SPRING_FEATURE.withConfiguration(new LiquidsConfig(UndergardenFluids.virulent_mix_source.get().getDefaultState(), false, 4, 1, ImmutableSet.of(UndergardenBlocks.depthrock.get(), UndergardenBlocks.deepsoil.get(), UndergardenBlocks.tremblecrust.get()))).withPlacement(Placement.field_242908_m.configure(new TopSolidRangeConfig(8, 8, 256))).func_242728_a().func_242731_b(50));

        register("deepturf_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UndergardenBlocks.tall_deepturf.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(100));
        register("ashen_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UndergardenBlocks.ashen_tall_deepturf.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(UndergardenBlocks.ashen_deepturf.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(100));
        register("shimmerweed_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UndergardenBlocks.shimmerweed.get().getDefaultState()), new SimpleBlockPlacer())).tries(32).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(75));
        register("pebble_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UndergardenBlocks.depthrock_pebbles.get().getDefaultState()), new SimpleBlockPlacer())).tries(32).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get(), UndergardenBlocks.ashen_deepturf.get(), UndergardenBlocks.depthrock.get(), UndergardenBlocks.shiverstone.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(75));
        register("ditchbulb_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UndergardenBlocks.ditchbulb_plant.get().getDefaultState()), new SimpleBlockPlacer())).tries(16).whitelist(ImmutableSet.of(UndergardenBlocks.depthrock.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(50));
        register("seagrass_patch", Feature.SIMPLE_BLOCK.withConfiguration(new BlockWithContextConfig(UndergardenBlocks.glowing_sea_grass.get().getDefaultState(), ImmutableList.of(UndergardenBlocks.depthrock.get().getDefaultState(), UndergardenBlocks.deepsoil.get().getDefaultState()), ImmutableList.of(Blocks.WATER.getDefaultState()), ImmutableList.of(Blocks.WATER.getDefaultState())))).func_242733_d(32).func_242728_a().func_242731_b(100);

        register("double_deepturf_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UndergardenBlocks.double_deepturf.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(32).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(100));
        register("double_shimmerweed_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UndergardenBlocks.double_shimmerweed.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(16).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(50));

        register("indigo_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UndergardenBlocks.indigo_mushroom.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(1));
        register("veil_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UndergardenBlocks.veil_mushroom.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(1));
        register("ink_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UndergardenBlocks.ink_mushroom.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(1));
        register("blood_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UndergardenBlocks.blood_mushroom.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(1));
        register("gronglet_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UndergardenBlocks.gronglet.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(100));

        register("underbean_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UndergardenBlocks.underbean_bush.get().getDefaultState().with(BeanBushBlock.AGE, 3)), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(5));
        register("blisterberry_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UndergardenBlocks.blisterberry_bush.get().getDefaultState().with(BlisterberryBushBlock.AGE, 3)), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(UndergardenBlocks.ashen_deepturf.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(5));
        register("gloomgourd_patch", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(UndergardenBlocks.gloomgourd.get().getDefaultState()), new SimpleBlockPlacer())).tries(16).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build()).func_242733_d(256).func_242728_a().func_242731_b(5));

        register("coal_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UndergardenBlocks.coal_ore.get().getDefaultState(), 17)).func_242733_d(256).func_242728_a().func_242731_b(20));
        register("iron_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UndergardenBlocks.iron_ore.get().getDefaultState(), 9)).func_242733_d(64).func_242728_a().func_242731_b(20));
        register("gold_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UndergardenBlocks.gold_ore.get().getDefaultState(), 9)).func_242733_d(32).func_242728_a().func_242731_b(2));
        register("diamond_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UndergardenBlocks.diamond_ore.get().getDefaultState(), 8)).func_242733_d(16).func_242728_a().func_242731_b(8));
        register("cloggrum_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UndergardenBlocks.cloggrum_ore.get().getDefaultState(), 5)).func_242733_d(128).func_242728_a().func_242731_b(15));
        register("froststeel_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UndergardenBlocks.froststeel_ore.get().getDefaultState(), 4)).func_242733_d(64).func_242728_a().func_242731_b(3));
        register("utherium_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UndergardenBlocks.utherium_ore.get().getDefaultState(), 8)).func_242733_d(32).func_242728_a().func_242731_b(1));
        register("regalium_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UndergardenBlocks.regalium_ore.get().getDefaultState(), 4)).func_242733_d(11).func_242728_a().func_242731_b(2));

        register("shiverstone_patch", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UndergardenBlocks.shiverstone.get().getDefaultState(), 33)).func_242733_d(256).func_242728_a().func_242731_b(10));
        register("deepsoil_patch", Feature.ORE.withConfiguration(new OreFeatureConfig(depthrock_filler, UndergardenBlocks.deepsoil.get().getDefaultState(), 33)).func_242733_d(256).func_242728_a().func_242731_b(10));

        register("smogstem_tree", undergarden_tree.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UndergardenBlocks.smogstem_log.get().getDefaultState()),
                        new SimpleBlockStateProvider(UndergardenBlocks.smogstem_leaves.get().getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 2),
                        new StraightTrunkPlacer(10, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .setIgnoreVines().build()).withPlacement(Placement.field_242897_C.configure(new FeatureSpreadConfig(8))));
        register("wigglewood_tree", undergarden_tree.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UndergardenBlocks.wigglewood_log.get().getDefaultState()),
                        new SimpleBlockStateProvider(UndergardenBlocks.wigglewood_leaves.get().getDefaultState()),
                        new BushFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 0),
                        new ForkyTrunkPlacer(3, 0, 0),
                        new TwoLayerFeature(1, 0, 2)))
                        .setIgnoreVines().build()).withPlacement(Placement.field_242897_C.configure(new FeatureSpreadConfig(8))));

        register("glowing_kelp", glowing_kelp.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).func_242733_d(32).func_242728_a().func_242731_b(100));
        register("smog_vent", smog_vent.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.field_242897_C.configure(new FeatureSpreadConfig(8))));
        register("enigmatic_statue", enigmatic_statue.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).func_242733_d(256).func_242728_a().func_242731_b(100));
        register("droopvine", droopvine.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).func_242733_d(256).func_242728_a().func_242731_b(100));
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(UndergardenMod.MODID, name), feature);
    }
}
