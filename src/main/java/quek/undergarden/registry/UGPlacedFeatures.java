package quek.undergarden.registry;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import quek.undergarden.Undergarden;

import java.util.List;

public class UGPlacedFeatures {

    //ores
    public static final PlacedFeature COAL_ORE = register("coal_ore", UGConfiguredFeatures.COAL_ORE.placed(OrePlacements.commonOrePlacement(30, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));
    public static final PlacedFeature IRON_ORE = register("iron_ore", UGConfiguredFeatures.IRON_ORE.placed(OrePlacements.commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.belowTop(128), VerticalAnchor.belowTop(-128)))));
    public static final PlacedFeature GOLD_ORE = register("gold_ore", UGConfiguredFeatures.GOLD_ORE.placed(OrePlacements.commonOrePlacement(2, HeightRangePlacement.triangle(VerticalAnchor.belowTop(64), VerticalAnchor.belowTop(-64)))));
    public static final PlacedFeature DIAMOND_ORE = register("diamond_ore", UGConfiguredFeatures.DIAMOND_ORE.placed(OrePlacements.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.belowTop(16), VerticalAnchor.belowTop(-16)))));
    public static final PlacedFeature CLOGGRUM_ORE = register("cloggrum_ore", UGConfiguredFeatures.CLOGGRUM_ORE.placed(OrePlacements.commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-256), VerticalAnchor.aboveBottom(256)))));
    public static final PlacedFeature FROSTSTEEL_ORE = register("froststeel_ore", UGConfiguredFeatures.FROSTSTEEL_ORE.placed(OrePlacements.commonOrePlacement(15, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-256), VerticalAnchor.aboveBottom(256)))));
    public static final PlacedFeature UTHERIUM_ORE = register("utherium_ore", UGConfiguredFeatures.UTHERIUM_ORE.placed(OrePlacements.commonOrePlacement(3, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32)))));
    public static final PlacedFeature REGALIUM_ORE = register("regalium_ore", UGConfiguredFeatures.REGALIUM_ORE.placed(OrePlacements.commonOrePlacement(3, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(12)))));
    public static final PlacedFeature SHIVERSTONE_ORE = register("shiverstone_ore", UGConfiguredFeatures.SHIVERSTONE_ORE.placed(OrePlacements.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));
    public static final PlacedFeature DEEPSOIL_ORE = register("deepsoil_ore", UGConfiguredFeatures.DEEPSOIL_ORE.placed(OrePlacements.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));
    public static final PlacedFeature ICE_ORE = register("ice_ore", UGConfiguredFeatures.ICE_ORE.placed(OrePlacements.commonOrePlacement(20, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));
    public static final PlacedFeature SEDIMENT_ORE = register("sediment_ore", UGConfiguredFeatures.SEDIMENT_ORE.placed(OrePlacements.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32)))));

    //springs
    public static final PlacedFeature SPRING = register("spring", UGConfiguredFeatures.SPRING.placed(CountPlacement.of(50), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()), BiomeFilter.biome()));
    public static final PlacedFeature VIRULENT_MIX_SPRING = register("virulent_mix_spring", UGConfiguredFeatures.VIRULENT_MIX_SPRING.placed(CountPlacement.of(50), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()), BiomeFilter.biome()));
    public static final PlacedFeature VIRULENT_MIX_BOG_SPRING = register("virulent_mix_bog_spring", UGConfiguredFeatures.VIRULENT_MIX_SPRING.placed(CountPlacement.of(100), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()), BiomeFilter.biome()));

    //deltas
    public static final PlacedFeature BOG_DELTA = register("bog_delta", UGConfiguredFeatures.BOG_DELTA.placed(CountOnEveryLayerPlacement.of(40), BiomeFilter.biome()));
    public static final PlacedFeature GRONGLEGROWTH_DELTA = register("gronglegrowth_delta", UGConfiguredFeatures.GRONGLEGROWTH_DELTA.placed(CountOnEveryLayerPlacement.of(40), BiomeFilter.biome()));

    //vegetation
    public static final PlacedFeature DEEPTURF_PATCH = register("deepturf_patch", UGConfiguredFeatures.DEEPTURF_PATCH.placed(patch(100)));
    public static final PlacedFeature ASHEN_DEEPTURF_PATCH = register("ashen_deepturf_patch", UGConfiguredFeatures.ASHEN_DEEPTURF_PATCH.placed(patch(100)));
    public static final PlacedFeature FROZEN_DEEPTURF_PATCH = register("frozen_deepturf_patch", UGConfiguredFeatures.FROZEN_DEEPTURF_PATCH.placed(patch(100)));
    public static final PlacedFeature SHIMMERWEED_PATCH = register("shimmerweed_patch", UGConfiguredFeatures.SHIMMERWEED_PATCH.placed(patch(75)));
    public static final PlacedFeature DEPTHROCK_PEBBLE_PATCH = register("depthrock_pebble_patch", UGConfiguredFeatures.DEPTHROCK_PEBBLE_PATCH.placed(patch(75)));
    public static final PlacedFeature DITCHBULB_PATCH = register("ditchbulb_patch", UGConfiguredFeatures.DITCHBULB_PATCH.placed(patch(75)));
    public static final PlacedFeature TALL_DEEPTURF_PATCH = register("tall_deepturf_patch", UGConfiguredFeatures.TALL_DEEPTURF_PATCH.placed(patch(100)));
    public static final PlacedFeature TALL_SHIMMERWEED_PATCH = register("tall_shimmerweed_patch", UGConfiguredFeatures.TALL_SHIMMERWEED_PATCH.placed(patch(50)));
    public static final PlacedFeature INDIGO_MUSHROOM_PATCH = register("indigo_mushroom_patch", UGConfiguredFeatures.INDIGO_MUSHROOM_PATCH.placed(patch(1)));
    public static final PlacedFeature VEIL_MUSHROOM_PATCH = register("veil_mushroom_patch", UGConfiguredFeatures.VEIL_MUSHROOM_PATCH.placed(patch(1)));
    public static final PlacedFeature INK_MUSHROOM_PATCH = register("ink_mushroom_patch", UGConfiguredFeatures.INK_MUSHROOM_PATCH.placed(patch(1)));
    public static final PlacedFeature BLOOD_MUSHROOM_PATCH = register("blood_mushroom_patch", UGConfiguredFeatures.BLOOD_MUSHROOM_PATCH.placed(patch(1)));
    public static final PlacedFeature UNDERBEAN_BUSH_PATCH = register("underbean_bush_patch", UGConfiguredFeatures.UNDERBEAN_BUSH_PATCH.placed(patch(5)));
    public static final PlacedFeature BLISTERBERRY_BUSH_PATCH = register("blisterberry_bush_patch", UGConfiguredFeatures.BLISTERBERRY_BUSH_PATCH.placed(patch(5)));
    public static final PlacedFeature GLOOMGOURD_PATCH = register("gloomgourd_patch", UGConfiguredFeatures.GLOOMGOURD_PATCH.placed(patch(5)));
    public static final PlacedFeature DROOPVINE_PATCH = register("droopvine_patch", UGConfiguredFeatures.DROOPVINE.placed(patch(100)));
    public static final PlacedFeature GLITTERKELP_PATCH = register("glitterkelp_patch", UGConfiguredFeatures.GLITTERKELP.placed(NoiseBasedCountPlacement.of(80, 80.0D, 0.0D), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(31)), BiomeFilter.biome()));

    //tree
    public static final PlacedFeature SMOGSTEM_TREE = register("smogstem_tree", UGConfiguredFeatures.SMOGSTEM_TREE.placed(tree(8)));
    public static final PlacedFeature TALL_SMOGSTEM_TREE = register("tall_smogstem_tree", UGConfiguredFeatures.TALL_SMOGSTEM_TREE.placed(tree(4)));
    public static final PlacedFeature WIGGLEWOOD_TREE = register("wigglewood_tree", UGConfiguredFeatures.WIGGLEWOOD_TREE.placed(tree(8)));
    public static final PlacedFeature TALL_WIGGLEWOOD_TREE = register("tall_wigglewood_tree", UGConfiguredFeatures.TALL_WIGGLEWOOD_TREE.placed(tree(4)));
    public static final PlacedFeature GRONGLE_TREE = register("grongle_tree", UGConfiguredFeatures.GRONGLE_TREE.placed(tree(8)));
    public static final PlacedFeature SMALL_GRONGLE_TREE = register("small_grongle_tree", UGConfiguredFeatures.SMALL_GRONGLE_TREE.placed(tree(8)));
    public static final PlacedFeature GRONGLE_BUSH = register("grongle_bush", UGConfiguredFeatures.GRONGLE_BUSH.placed(tree(8)));

    //huge mushrooms
    public static final PlacedFeature HUGE_INDIGO_MUSHROOM = register("huge_indigo_mushroom", UGConfiguredFeatures.HUGE_INDIGO_MUSHROOM.placed(tree(2)));
    public static final PlacedFeature HUGE_VEIL_MUSHROOM = register("huge_veil_mushroom", UGConfiguredFeatures.HUGE_VEIL_MUSHROOM.placed(tree(2)));
    public static final PlacedFeature HUGE_INK_MUSHROOM = register("huge_ink_mushroom", UGConfiguredFeatures.HUGE_INK_MUSHROOM.placed(tree(2)));
    public static final PlacedFeature HUGE_BLOOD_MUSHROOM = register("huge_blood_mushroom", UGConfiguredFeatures.HUGE_BLOOD_MUSHROOM.placed(tree(2)));

    //rocks
    public static final PlacedFeature DEPTHROCK_ROCK = register("depthrock_rock", UGConfiguredFeatures.DEPTHROCK_ROCK.placed(patch(5)));
    public static final PlacedFeature SHIVERSTONE_ROCK = register("shiverstone_rock", UGConfiguredFeatures.SHIVERSTONE_ROCK.placed(patch(5)));

    //misc
    public static final PlacedFeature SMOG_VENT = register("smog_vent", UGConfiguredFeatures.SMOG_VENT.placed(tree(8)));
    public static final PlacedFeature ICE_PILLAR = register("ice_pillar", UGConfiguredFeatures.ICE_PILLAR.placed(patch(50)));

    private static List<PlacementModifier> tree(int count) {
        return List.of(CountOnEveryLayerPlacement.of(count), BiomeFilter.biome());
    }

    private static List<PlacementModifier> patch(int count) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }

    private static PlacedFeature register(String name, PlacedFeature feature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Undergarden.MODID, name), feature);
    }
}
