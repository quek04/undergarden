package quek.undergarden.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import quek.undergarden.Undergarden;

import java.util.List;

@SuppressWarnings("unused")
public class UGPlacedFeatures {

    //ores
    public static final Holder<PlacedFeature> COAL_ORE = register("coal_ore", UGConfiguredFeatures.COAL_ORE, OrePlacements.commonOrePlacement(30, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
    public static final Holder<PlacedFeature> IRON_ORE = register("iron_ore", UGConfiguredFeatures.IRON_ORE, OrePlacements.commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.belowTop(128), VerticalAnchor.belowTop(-128))));
    public static final Holder<PlacedFeature> GOLD_ORE = register("gold_ore", UGConfiguredFeatures.GOLD_ORE, OrePlacements.commonOrePlacement(2, HeightRangePlacement.triangle(VerticalAnchor.belowTop(64), VerticalAnchor.belowTop(-64))));
    public static final Holder<PlacedFeature> DIAMOND_ORE = register("diamond_ore", UGConfiguredFeatures.DIAMOND_ORE, OrePlacements.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.belowTop(16), VerticalAnchor.belowTop(-16))));
    public static final Holder<PlacedFeature> CLOGGRUM_ORE = register("cloggrum_ore", UGConfiguredFeatures.CLOGGRUM_ORE, OrePlacements.commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-256), VerticalAnchor.aboveBottom(256))));
    public static final Holder<PlacedFeature> FROSTSTEEL_ORE = register("froststeel_ore", UGConfiguredFeatures.FROSTSTEEL_ORE, OrePlacements.commonOrePlacement(15, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-256), VerticalAnchor.aboveBottom(256))));
    public static final Holder<PlacedFeature> UTHERIUM_ORE = register("utherium_ore", UGConfiguredFeatures.UTHERIUM_ORE, OrePlacements.commonOrePlacement(3, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32))));
    public static final Holder<PlacedFeature> REGALIUM_ORE = register("regalium_ore", UGConfiguredFeatures.REGALIUM_ORE, OrePlacements.commonOrePlacement(3, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(12))));
    public static final Holder<PlacedFeature> SHIVERSTONE_ORE = register("shiverstone_ore", UGConfiguredFeatures.SHIVERSTONE_ORE, OrePlacements.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
    public static final Holder<PlacedFeature> DEEPSOIL_ORE = register("deepsoil_ore", UGConfiguredFeatures.DEEPSOIL_ORE, OrePlacements.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
    public static final Holder<PlacedFeature> ICE_ORE = register("ice_ore", UGConfiguredFeatures.ICE_ORE, OrePlacements.commonOrePlacement(20, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
    public static final Holder<PlacedFeature> SEDIMENT_ORE = register("sediment_ore", UGConfiguredFeatures.SEDIMENT_ORE, OrePlacements.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32))));

    //springs
    public static final Holder<PlacedFeature> SPRING = register("spring", UGConfiguredFeatures.SPRING, CountPlacement.of(20), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()), BiomeFilter.biome());
    public static final Holder<PlacedFeature> VIRULENT_MIX_SPRING = register("virulent_mix_spring", UGConfiguredFeatures.VIRULENT_MIX_SPRING, CountPlacement.of(20), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()), BiomeFilter.biome());
    public static final Holder<PlacedFeature> VIRULENT_MIX_BOG_SPRING = register("virulent_mix_bog_spring", UGConfiguredFeatures.VIRULENT_MIX_SPRING, CountPlacement.of(25), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()), BiomeFilter.biome());

    //deltas
    public static final Holder<PlacedFeature> BOG_DELTA = register("bog_delta", UGConfiguredFeatures.BOG_DELTA, CountOnEveryLayerPlacement.of(40), BiomeFilter.biome());
    public static final Holder<PlacedFeature> GRONGLEGROWTH_DELTA = register("gronglegrowth_delta", UGConfiguredFeatures.GRONGLEGROWTH_DELTA, CountOnEveryLayerPlacement.of(40), BiomeFilter.biome());

    //vegetation
    public static final Holder<PlacedFeature> DEEPTURF_PATCH = register("deepturf_patch", UGConfiguredFeatures.DEEPTURF_PATCH, patch(100));
    public static final Holder<PlacedFeature> ASHEN_DEEPTURF_PATCH = register("ashen_deepturf_patch", UGConfiguredFeatures.ASHEN_DEEPTURF_PATCH, patch(100));
    public static final Holder<PlacedFeature> FROZEN_DEEPTURF_PATCH = register("frozen_deepturf_patch", UGConfiguredFeatures.FROZEN_DEEPTURF_PATCH, patch(100));
    public static final Holder<PlacedFeature> SHIMMERWEED_PATCH = register("shimmerweed_patch", UGConfiguredFeatures.SHIMMERWEED_PATCH, patch(75));
    public static final Holder<PlacedFeature> DEPTHROCK_PEBBLE_PATCH = register("depthrock_pebble_patch", UGConfiguredFeatures.DEPTHROCK_PEBBLE_PATCH, patch(75));
    public static final Holder<PlacedFeature> DITCHBULB_PATCH = register("ditchbulb_patch", UGConfiguredFeatures.DITCHBULB_PATCH, patch(75));
    public static final Holder<PlacedFeature> TALL_DEEPTURF_PATCH = register("tall_deepturf_patch", UGConfiguredFeatures.TALL_DEEPTURF_PATCH, patch(100));
    public static final Holder<PlacedFeature> TALL_SHIMMERWEED_PATCH = register("tall_shimmerweed_patch", UGConfiguredFeatures.TALL_SHIMMERWEED_PATCH, patch(50));
    public static final Holder<PlacedFeature> INDIGO_MUSHROOM_PATCH = register("indigo_mushroom_patch", UGConfiguredFeatures.INDIGO_MUSHROOM_PATCH, patch(1));
    public static final Holder<PlacedFeature> VEIL_MUSHROOM_PATCH = register("veil_mushroom_patch", UGConfiguredFeatures.VEIL_MUSHROOM_PATCH, patch(1));
    public static final Holder<PlacedFeature> INK_MUSHROOM_PATCH = register("ink_mushroom_patch", UGConfiguredFeatures.INK_MUSHROOM_PATCH, patch(1));
    public static final Holder<PlacedFeature> BLOOD_MUSHROOM_PATCH = register("blood_mushroom_patch", UGConfiguredFeatures.BLOOD_MUSHROOM_PATCH, patch(1));
    public static final Holder<PlacedFeature> UNDERBEAN_BUSH_PATCH = register("underbean_bush_patch", UGConfiguredFeatures.UNDERBEAN_BUSH_PATCH, patch(5));
    public static final Holder<PlacedFeature> BLISTERBERRY_BUSH_PATCH = register("blisterberry_bush_patch", UGConfiguredFeatures.BLISTERBERRY_BUSH_PATCH, patch(5));
    public static final Holder<PlacedFeature> GLOOMGOURD_PATCH = register("gloomgourd_patch", UGConfiguredFeatures.GLOOMGOURD_PATCH, patch(5));
    public static final Holder<PlacedFeature> DROOPVINE_PATCH = register("droopvine_patch", UGConfiguredFeatures.DROOPVINE, patch(100));
    public static final Holder<PlacedFeature> GLITTERKELP_PATCH = register("glitterkelp_patch", UGConfiguredFeatures.GLITTERKELP, NoiseBasedCountPlacement.of(1000, 80.0D, 0.0D), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(31)), BiomeFilter.biome());

    //tree
    public static final Holder<PlacedFeature> SMOGSTEM_TREE = register("smogstem_tree", UGConfiguredFeatures.SMOGSTEM_TREE, tree(8));
    public static final Holder<PlacedFeature> WIDE_SMOGSTEM_TREE = register("wide_smogstem_tree", UGConfiguredFeatures.WIDE_SMOGSTEM_TREE, tree(2));
    public static final Holder<PlacedFeature> TALL_SMOGSTEM_TREE = register("tall_smogstem_tree", UGConfiguredFeatures.TALL_SMOGSTEM_TREE, tree(4));
    public static final Holder<PlacedFeature> WIGGLEWOOD_TREE = register("wigglewood_tree", UGConfiguredFeatures.WIGGLEWOOD_TREE, tree(8));
    public static final Holder<PlacedFeature> TALL_WIGGLEWOOD_TREE = register("tall_wigglewood_tree", UGConfiguredFeatures.TALL_WIGGLEWOOD_TREE, tree(4));
    public static final Holder<PlacedFeature> GRONGLE_TREE = register("grongle_tree", UGConfiguredFeatures.GRONGLE_TREE, tree(8));
    public static final Holder<PlacedFeature> SMALL_GRONGLE_TREE = register("small_grongle_tree", UGConfiguredFeatures.SMALL_GRONGLE_TREE, tree(8));
    public static final Holder<PlacedFeature> GRONGLE_BUSH = register("grongle_bush", UGConfiguredFeatures.GRONGLE_BUSH, tree(8));

    //huge mushrooms
    public static final Holder<PlacedFeature> HUGE_INDIGO_MUSHROOM = register("huge_indigo_mushroom", UGConfiguredFeatures.HUGE_INDIGO_MUSHROOM, tree(2));
    public static final Holder<PlacedFeature> HUGE_INDIGO_MUSHROOM_SMOGSTEM_FOREST = register("huge_indigo_mushroom_smogstem_forest", UGConfiguredFeatures.HUGE_INDIGO_MUSHROOM, tree(1));
    public static final Holder<PlacedFeature> HUGE_VEIL_MUSHROOM = register("huge_veil_mushroom", UGConfiguredFeatures.HUGE_VEIL_MUSHROOM, tree(2));
    public static final Holder<PlacedFeature> HUGE_INK_MUSHROOM = register("huge_ink_mushroom", UGConfiguredFeatures.HUGE_INK_MUSHROOM, tree(2));
    public static final Holder<PlacedFeature> HUGE_BLOOD_MUSHROOM = register("huge_blood_mushroom", UGConfiguredFeatures.HUGE_BLOOD_MUSHROOM, tree(2));

    //rocks
    public static final Holder<PlacedFeature> DEPTHROCK_ROCK = register("depthrock_rock", UGConfiguredFeatures.DEPTHROCK_ROCK, patch(5));
    public static final Holder<PlacedFeature> SHIVERSTONE_ROCK = register("shiverstone_rock", UGConfiguredFeatures.SHIVERSTONE_ROCK, patch(5));

    //misc
    public static final Holder<PlacedFeature> SMOG_VENT = register("smog_vent", UGConfiguredFeatures.SMOG_VENT, CountOnEveryLayerPlacement.of(8), BiomeFilter.biome());
    public static final Holder<PlacedFeature> ICE_PILLAR = register("ice_pillar", UGConfiguredFeatures.ICE_PILLAR, patch(50));

    private static List<PlacementModifier> tree(int count) {
        return List.of(CountOnEveryLayerPlacement.of(count), BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
    }

    private static List<PlacementModifier> patch(int count) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }

    public static Holder<PlacedFeature> register(String name, Holder<? extends ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> placementModifiers) {
        return PlacementUtils.register(new ResourceLocation(Undergarden.MODID, name).toString(), configuredFeature, placementModifiers);
    }

    public static Holder<PlacedFeature> register(String name, Holder<? extends ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(new ResourceLocation(Undergarden.MODID, name).toString(), configuredFeature, List.of(placementModifiers));
    }

    public static void init() {}
}