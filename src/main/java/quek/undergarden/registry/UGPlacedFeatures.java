package quek.undergarden.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;

import java.util.List;

@SuppressWarnings({"unused"})
public class UGPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Undergarden.MODID);

    //ores
    public static final RegistryObject<PlacedFeature> COAL_ORE = PLACED_FEATURES.register("coal_ore", () -> new PlacedFeature(UGConfiguredFeatures.COAL_ORE.getHolder().get(), OrePlacements.commonOrePlacement(30, PlacementUtils.FULL_RANGE)));
    public static final RegistryObject<PlacedFeature> IRON_ORE = PLACED_FEATURES.register("iron_ore", () -> new PlacedFeature(UGConfiguredFeatures.IRON_ORE.getHolder().get(), OrePlacements.commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.belowTop(128), VerticalAnchor.belowTop(-128)))));
    public static final RegistryObject<PlacedFeature> GOLD_ORE = PLACED_FEATURES.register("gold_ore", () -> new PlacedFeature(UGConfiguredFeatures.GOLD_ORE.getHolder().get(), OrePlacements.commonOrePlacement(2, HeightRangePlacement.triangle(VerticalAnchor.belowTop(64), VerticalAnchor.belowTop(-64)))));
    public static final RegistryObject<PlacedFeature> DIAMOND_ORE = PLACED_FEATURES.register("diamond_ore", () -> new PlacedFeature(UGConfiguredFeatures.DIAMOND_ORE.getHolder().get(), OrePlacements.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.belowTop(16), VerticalAnchor.belowTop(-16)))));
    public static final RegistryObject<PlacedFeature> CLOGGRUM_ORE = PLACED_FEATURES.register("cloggrum_ore", () -> new PlacedFeature(UGConfiguredFeatures.CLOGGRUM_ORE.getHolder().get(), OrePlacements.commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-256), VerticalAnchor.aboveBottom(256)))));
    public static final RegistryObject<PlacedFeature> FROSTSTEEL_ORE = PLACED_FEATURES.register("froststeel_ore", () -> new PlacedFeature(UGConfiguredFeatures.FROSTSTEEL_ORE.getHolder().get(), OrePlacements.commonOrePlacement(15, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-256), VerticalAnchor.aboveBottom(256)))));
    public static final RegistryObject<PlacedFeature> UTHERIUM_ORE = PLACED_FEATURES.register("utherium_ore", () -> new PlacedFeature(UGConfiguredFeatures.UTHERIUM_ORE.getHolder().get(), OrePlacements.commonOrePlacement(3, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32)))));
    public static final RegistryObject<PlacedFeature> REGALIUM_ORE = PLACED_FEATURES.register("regalium_ore", () -> new PlacedFeature(UGConfiguredFeatures.REGALIUM_ORE.getHolder().get(), OrePlacements.commonOrePlacement(3, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(12)))));
    public static final RegistryObject<PlacedFeature> SHIVERSTONE_ORE = PLACED_FEATURES.register("shiverstone_ore", () -> new PlacedFeature(UGConfiguredFeatures.SHIVERSTONE_ORE.getHolder().get(), OrePlacements.commonOrePlacement(10, PlacementUtils.FULL_RANGE)));
    public static final RegistryObject<PlacedFeature> DEEPSOIL_ORE = PLACED_FEATURES.register("deepsoil_ore", () -> new PlacedFeature(UGConfiguredFeatures.DEEPSOIL_ORE.getHolder().get(), OrePlacements.commonOrePlacement(10, PlacementUtils.FULL_RANGE)));
    public static final RegistryObject<PlacedFeature> ICE_ORE = PLACED_FEATURES.register("ice_ore", () -> new PlacedFeature(UGConfiguredFeatures.ICE_ORE.getHolder().get(), OrePlacements.commonOrePlacement(20, PlacementUtils.FULL_RANGE)));
    public static final RegistryObject<PlacedFeature> SEDIMENT_ORE = PLACED_FEATURES.register("sediment_ore", () -> new PlacedFeature(UGConfiguredFeatures.SEDIMENT_ORE.getHolder().get(), OrePlacements.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32)))));

    //springs
    public static final RegistryObject<PlacedFeature> SPRING = PLACED_FEATURES.register("spring", () -> new PlacedFeature(UGConfiguredFeatures.SPRING.getHolder().get(), List.of(CountPlacement.of(20), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> VIRULENT_MIX_SPRING = PLACED_FEATURES.register("virulent_mix_spring", () -> new PlacedFeature(UGConfiguredFeatures.VIRULENT_MIX_SPRING.getHolder().get(), List.of(CountPlacement.of(20), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> VIRULENT_MIX_BOG_SPRING = PLACED_FEATURES.register("virulent_mix_bog_spring", () -> new PlacedFeature(UGConfiguredFeatures.VIRULENT_MIX_SPRING.getHolder().get(), List.of(CountPlacement.of(25), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));

    //deltas
    public static final RegistryObject<PlacedFeature> BOG_DELTA = PLACED_FEATURES.register("bog_delta", () -> new PlacedFeature(UGConfiguredFeatures.BOG_DELTA.getHolder().get(), List.of(CountOnEveryLayerPlacement.of(40), BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> GRONGLEGROWTH_DELTA = PLACED_FEATURES.register("gronglegrowth_delta", () -> new PlacedFeature(UGConfiguredFeatures.GRONGLEGROWTH_DELTA.getHolder().get(), List.of(CountOnEveryLayerPlacement.of(40), BiomeFilter.biome())));

    //vegetation
    public static final RegistryObject<PlacedFeature> AMOROUS_BRISTLE_PATCH = PLACED_FEATURES.register("amorous_bristle_patch", () -> new PlacedFeature(UGConfiguredFeatures.AMOROUS_BRISTLE_PATCH.getHolder().get(), patch(5)));
    public static final RegistryObject<PlacedFeature> MISERABELL_PATCH = PLACED_FEATURES.register("miserabell_patch", () -> new PlacedFeature(UGConfiguredFeatures.MISERABELL_PATCH.getHolder().get(), patch(5)));
    public static final RegistryObject<PlacedFeature> BUTTERBUNCH_PATCH = PLACED_FEATURES.register("butterbunch_patch", () -> new PlacedFeature(UGConfiguredFeatures.BUTTERBUNCH_PATCH.getHolder().get(), patch(5)));
    public static final RegistryObject<PlacedFeature> DEEPTURF_PATCH = PLACED_FEATURES.register("deepturf_patch", () -> new PlacedFeature(UGConfiguredFeatures.DEEPTURF_PATCH.getHolder().get(), patch(100)));
    public static final RegistryObject<PlacedFeature> ASHEN_DEEPTURF_PATCH = PLACED_FEATURES.register("ashen_deepturf_patch", () -> new PlacedFeature(UGConfiguredFeatures.ASHEN_DEEPTURF_PATCH.getHolder().get(), patch(100)));
    public static final RegistryObject<PlacedFeature> FROZEN_DEEPTURF_PATCH = PLACED_FEATURES.register("frozen_deepturf_patch", () -> new PlacedFeature(UGConfiguredFeatures.FROZEN_DEEPTURF_PATCH.getHolder().get(), patch(100)));
    public static final RegistryObject<PlacedFeature> SHIMMERWEED_PATCH = PLACED_FEATURES.register("shimmerweed_patch", () -> new PlacedFeature(UGConfiguredFeatures.SHIMMERWEED_PATCH.getHolder().get(), noise(500, 75.0D, 0.0D)));
    public static final RegistryObject<PlacedFeature> DEPTHROCK_PEBBLE_PATCH = PLACED_FEATURES.register("depthrock_pebble_patch", () -> new PlacedFeature(UGConfiguredFeatures.DEPTHROCK_PEBBLE_PATCH.getHolder().get(), noise(500, 50.0D, 0.D)));
    public static final RegistryObject<PlacedFeature> DITCHBULB_PATCH = PLACED_FEATURES.register("ditchbulb_patch", () -> new PlacedFeature(UGConfiguredFeatures.DITCHBULB_PATCH.getHolder().get(), patch(75)));
    public static final RegistryObject<PlacedFeature> TALL_DEEPTURF_PATCH = PLACED_FEATURES.register("tall_deepturf_patch", () -> new PlacedFeature(UGConfiguredFeatures.TALL_DEEPTURF_PATCH.getHolder().get(), patch(100)));
    public static final RegistryObject<PlacedFeature> TALL_SHIMMERWEED_PATCH = PLACED_FEATURES.register("tall_shimmerweed_patch", () -> new PlacedFeature(UGConfiguredFeatures.TALL_SHIMMERWEED_PATCH.getHolder().get(), noise(500, 75.0D, 0.0D)));
    public static final RegistryObject<PlacedFeature> INDIGO_MUSHROOM_PATCH = PLACED_FEATURES.register("indigo_mushroom_patch", () -> new PlacedFeature(UGConfiguredFeatures.INDIGO_MUSHROOM_PATCH.getHolder().get(), patch(1)));
    public static final RegistryObject<PlacedFeature> VEIL_MUSHROOM_PATCH = PLACED_FEATURES.register("veil_mushroom_patch", () -> new PlacedFeature(UGConfiguredFeatures.VEIL_MUSHROOM_PATCH.getHolder().get(), patch(1)));
    public static final RegistryObject<PlacedFeature> INK_MUSHROOM_PATCH = PLACED_FEATURES.register("ink_mushroom_patch", () -> new PlacedFeature(UGConfiguredFeatures.INK_MUSHROOM_PATCH.getHolder().get(), patch(1)));
    public static final RegistryObject<PlacedFeature> BLOOD_MUSHROOM_PATCH = PLACED_FEATURES.register("blood_mushroom_patch", () -> new PlacedFeature(UGConfiguredFeatures.BLOOD_MUSHROOM_PATCH.getHolder().get(), patch(1)));
    public static final RegistryObject<PlacedFeature> UNDERBEAN_BUSH_PATCH = PLACED_FEATURES.register("underbean_bush_patch", () -> new PlacedFeature(UGConfiguredFeatures.UNDERBEAN_BUSH_PATCH.getHolder().get(), patch(5)));
    public static final RegistryObject<PlacedFeature> BLISTERBERRY_BUSH_PATCH = PLACED_FEATURES.register("blisterberry_bush_patch", () -> new PlacedFeature(UGConfiguredFeatures.BLISTERBERRY_BUSH_PATCH.getHolder().get(), patch(5)));
    public static final RegistryObject<PlacedFeature> GLOOMGOURD_PATCH = PLACED_FEATURES.register("gloomgourd_patch", () -> new PlacedFeature(UGConfiguredFeatures.GLOOMGOURD_PATCH.getHolder().get(), patch(5)));
    public static final RegistryObject<PlacedFeature> DROOPVINE_PATCH = PLACED_FEATURES.register("droopvine_patch", () -> new PlacedFeature(UGConfiguredFeatures.DROOPVINE.getHolder().get(), patch(100)));
    public static final RegistryObject<PlacedFeature> GLITTERKELP_PATCH = PLACED_FEATURES.register("glitterkelp_patch", () -> new PlacedFeature(UGConfiguredFeatures.GLITTERKELP.getHolder().get(), List.of(NoiseBasedCountPlacement.of(1000, 80.0D, 0.0D), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(31)), BiomeFilter.biome())));

    //tree
    public static final RegistryObject<PlacedFeature> SMOGSTEM_TREE = PLACED_FEATURES.register("smogstem_tree", () -> new PlacedFeature(UGConfiguredFeatures.SMOGSTEM_TREE.getHolder().get(), tree(8)));
    public static final RegistryObject<PlacedFeature> WIDE_SMOGSTEM_TREE = PLACED_FEATURES.register("wide_smogstem_tree", () -> new PlacedFeature(UGConfiguredFeatures.WIDE_SMOGSTEM_TREE.getHolder().get(), tree(2)));
    public static final RegistryObject<PlacedFeature> TALL_SMOGSTEM_TREE = PLACED_FEATURES.register("tall_smogstem_tree", () -> new PlacedFeature(UGConfiguredFeatures.TALL_SMOGSTEM_TREE.getHolder().get(), tree(4)));
    public static final RegistryObject<PlacedFeature> WIGGLEWOOD_TREE = PLACED_FEATURES.register("wigglewood_tree", () -> new PlacedFeature(UGConfiguredFeatures.WIGGLEWOOD_TREE.getHolder().get(), tree(8)));
    public static final RegistryObject<PlacedFeature> TALL_WIGGLEWOOD_TREE = PLACED_FEATURES.register("tall_wigglewood_tree", () -> new PlacedFeature(UGConfiguredFeatures.TALL_WIGGLEWOOD_TREE.getHolder().get(), tree(4)));
    public static final RegistryObject<PlacedFeature> GRONGLE_TREE = PLACED_FEATURES.register("grongle_tree", () -> new PlacedFeature(UGConfiguredFeatures.GRONGLE_TREE.getHolder().get(), tree(8)));
    public static final RegistryObject<PlacedFeature> SMALL_GRONGLE_TREE = PLACED_FEATURES.register("small_grongle_tree", () -> new PlacedFeature(UGConfiguredFeatures.SMALL_GRONGLE_TREE.getHolder().get(), tree(8)));
    public static final RegistryObject<PlacedFeature> GRONGLE_BUSH = PLACED_FEATURES.register("grongle_bush", () -> new PlacedFeature(UGConfiguredFeatures.GRONGLE_BUSH.getHolder().get(), tree(8)));

    //huge mushrooms
    public static final RegistryObject<PlacedFeature> HUGE_INDIGO_MUSHROOM = PLACED_FEATURES.register("huge_indigo_mushroom", () -> new PlacedFeature(UGConfiguredFeatures.HUGE_INDIGO_MUSHROOM.getHolder().get(), tree(2)));
    public static final RegistryObject<PlacedFeature> HUGE_INDIGO_MUSHROOM_SMOGSTEM_FOREST = PLACED_FEATURES.register("huge_indigo_mushroom_smogstem_forest", () -> new PlacedFeature(UGConfiguredFeatures.HUGE_INDIGO_MUSHROOM.getHolder().get(), tree(1)));
    public static final RegistryObject<PlacedFeature> HUGE_VEIL_MUSHROOM = PLACED_FEATURES.register("huge_veil_mushroom", () -> new PlacedFeature(UGConfiguredFeatures.HUGE_VEIL_MUSHROOM.getHolder().get(), tree(2)));
    public static final RegistryObject<PlacedFeature> HUGE_INK_MUSHROOM = PLACED_FEATURES.register("huge_ink_mushroom", () -> new PlacedFeature(UGConfiguredFeatures.HUGE_INK_MUSHROOM.getHolder().get(), tree(2)));
    public static final RegistryObject<PlacedFeature> HUGE_BLOOD_MUSHROOM = PLACED_FEATURES.register("huge_blood_mushroom", () -> new PlacedFeature(UGConfiguredFeatures.HUGE_BLOOD_MUSHROOM.getHolder().get(), tree(2)));

    //rocks
    public static final RegistryObject<PlacedFeature> DEPTHROCK_ROCK = PLACED_FEATURES.register("depthrock_rock", () -> new PlacedFeature(UGConfiguredFeatures.DEPTHROCK_ROCK.getHolder().get(), patch(5)));
    public static final RegistryObject<PlacedFeature> SHIVERSTONE_ROCK = PLACED_FEATURES.register("shiverstone_rock", () -> new PlacedFeature(UGConfiguredFeatures.SHIVERSTONE_ROCK.getHolder().get(), patch(5)));

    //misc
    public static final RegistryObject<PlacedFeature> SMOG_VENT = PLACED_FEATURES.register("smog_vent", () -> new PlacedFeature(UGConfiguredFeatures.SMOG_VENT.getHolder().get(), List.of(CountOnEveryLayerPlacement.of(8), BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> ICE_PILLAR = PLACED_FEATURES.register("ice_pillar", () -> new PlacedFeature(UGConfiguredFeatures.ICE_PILLAR.getHolder().get(), patch(50)));

    private static List<PlacementModifier> tree(int count) {
        return List.of(CountOnEveryLayerPlacement.of(count), BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
    }

    private static List<PlacementModifier> patch(int count) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }

    private static List<PlacementModifier> noise(int noiseToCountRatio, double factor, double offset) {
        return List.of(NoiseBasedCountPlacement.of(noiseToCountRatio, factor, offset), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }
}