package quek.undergarden.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.feature.*;

public class UGFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Undergarden.MODID);

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

    public static void registerConfiguredFeatures() {
//        register("spring", ConfiguredFeatures.SPRING.range(new RangeDecoratorConfiguration(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.belowTop(8), 8))).squared().count(50));
//        register("virulent_spring", ConfiguredFeatures.VIRULENT_SPRING.range(new RangeDecoratorConfiguration(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.belowTop(8), 8))).squared().count(50));
//        register("bog_virulent_spring", ConfiguredFeatures.VIRULENT_SPRING.range(new RangeDecoratorConfiguration(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.belowTop(8), 8))).squared().count(100));
//
//        register("lake_virulent", ConfiguredFeatures.VIRULENT_LAKE.range(Features.Decorators.FULL_RANGE).squared().rarity(8));
//        register("bog_lake_virulent", ConfiguredFeatures.VIRULENT_LAKE.range(Features.Decorators.FULL_RANGE).squared().rarity(2));
//
//        register("bog_pond", ConfiguredFeatures.BOG_PONDS.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(40))));
//        register("gronglegrowth_pond", ConfiguredFeatures.GRONGLEGROWTH_PONDS.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(40))));
//
//        register("lilypads", ConfiguredFeatures.LILYPADS.range(Features.Decorators.FULL_RANGE).count(5));
//        register("deepturf_patch", ConfiguredFeatures.DEEPTURF_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(100));
//        register("ashen_patch", ConfiguredFeatures.ASHEN_DEEPTURF_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(100));
//        register("frozen_patch", ConfiguredFeatures.FROZEN_DEEPTURF_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(100));
//        register("shimmerweed_patch", ConfiguredFeatures.SHIMMERWEED_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(75));
//        register("pebble_patch", ConfiguredFeatures.PEBBLE_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(75));
//        register("ditchbulb_patch", ConfiguredFeatures.DITCHBULB_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(50));
//
//        register("tall_deepturf_patch", ConfiguredFeatures.TALL_DEEPTURF_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(100));
//        register("tall_shimmerweed_patch", ConfiguredFeatures.TALL_SHIMMERWEED_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(50));
//
//        register("indigo_patch", ConfiguredFeatures.INDIGO_MUSHROOM_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(1));
//        register("veil_patch", ConfiguredFeatures.VEIL_MUSHROOM_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(1));
//        register("ink_patch", ConfiguredFeatures.INK_MUSHROOM_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(1));
//        register("blood_patch", ConfiguredFeatures.BLOOD_MUSHROOM_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(1));
//
//        register("bog_indigo_patch", ConfiguredFeatures.INDIGO_MUSHROOM_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(25));
//        register("bog_veil_patch", ConfiguredFeatures.VEIL_MUSHROOM_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(25));
//        register("bog_ink_patch", ConfiguredFeatures.INK_MUSHROOM_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(25));
//        register("bog_blood_patch", ConfiguredFeatures.BLOOD_MUSHROOM_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(25));
//
//        register("underbean_patch", ConfiguredFeatures.UNDERBEAN_BUSH_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(5));
//        register("blisterberry_patch", ConfiguredFeatures.BLISTERBERRY_BUSH_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(5));
//        register("gloomgourd_patch", ConfiguredFeatures.GLOOMGOURD_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(5));
//
//        register("coal_ore", ConfiguredFeatures.COAL_ORE.range(Features.Decorators.FULL_RANGE).squared().count(30));
//        register("iron_ore", ConfiguredFeatures.IRON_ORE.rangeTriangle(VerticalAnchor.belowTop(128), VerticalAnchor.belowTop(-128)).squared().count(8));
//        register("gold_ore", ConfiguredFeatures.GOLD_ORE.rangeTriangle(VerticalAnchor.belowTop(64), VerticalAnchor.belowTop(-64)).squared().count(2));
//        register("diamond_ore", ConfiguredFeatures.DIAMOND_ORE.rangeUniform(VerticalAnchor.belowTop(16), VerticalAnchor.belowTop(-16)).squared());
//        register("cloggrum_ore", ConfiguredFeatures.CLOGGRUM_ORE.rangeTriangle(VerticalAnchor.aboveBottom(-256), VerticalAnchor.aboveBottom(256)).squared().count(20));
//        register("froststeel_ore", ConfiguredFeatures.FROSTSTEEL_ORE.rangeTriangle(VerticalAnchor.aboveBottom(-256), VerticalAnchor.aboveBottom(256)).squared().count(15));
//        register("utherium_ore", ConfiguredFeatures.UTHERIUM_ORE.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32)).squared().count(3));
//        register("regalium_ore", ConfiguredFeatures.REGALIUM_ORE.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(12)).squared().count(3));
//
//        register("shiverstone_patch", ConfiguredFeatures.SHIVERSTONE_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(10));
//        register("deepsoil_patch", ConfiguredFeatures.DEEPSOIL_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(10));
//        register("ice_patch", ConfiguredFeatures.ICE_PATCH.range(Features.Decorators.FULL_RANGE).squared().count(20));
//        register("sediment_patch", ConfiguredFeatures.SEDIMENT_PATCH.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32)).squared().count(20));
//
//        register("smogstem_tree", ConfiguredFeatures.SMOGSTEM_TREE.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(8))));
//        register("wigglewood_tree", ConfiguredFeatures.WIGGLEWOOD_TREE.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(8))));
//        register("grongle_tree", ConfiguredFeatures.GRONGLE_TREE.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(8))));
//        register("grongle_tree_small", ConfiguredFeatures.GRONGLE_TREE_SMALL.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(8))));
//        register("grongle_bush", ConfiguredFeatures.GRONGLE_BUSH.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(8))));
//
//        register("smogstem_tree_tall", ConfiguredFeatures.SMOGSTEM_TREE_TALL.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(4))));
//        register("wigglewood_tree_tall", ConfiguredFeatures.WIGGLEWOOD_TREE_TALL.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(4))));
//
//        register("huge_blood_mushroom", ConfiguredFeatures.BLOOD_MUSHROOM.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(2))));
//        register("huge_indigo_mushroom", ConfiguredFeatures.INDIGO_MUSHROOM.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(2))));
//        register("huge_ink_mushroom", ConfiguredFeatures.INK_MUSHROOM.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(2))));
//        register("huge_veil_mushroom", ConfiguredFeatures.VEIL_MUSHROOM.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(2))));
//
//        register("depthrock_boulder", ConfiguredFeatures.DEPTHROCK_BOULDER.range(Features.Decorators.FULL_RANGE).squared().count(5));
//        register("shiverstone_boulder", ConfiguredFeatures.SHIVERSTONE_BOULDER.range(Features.Decorators.FULL_RANGE).squared().count(5));
//
//        register("glowing_kelp", ConfiguredFeatures.GLOWING_KELP.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(30)).squared().count(100));
//        register("smog_vent", ConfiguredFeatures.SMOG_VENT.decorated(FeatureDecorator.COUNT_MULTILAYER.configured(new CountConfiguration(8))));
//        register("droopvine", ConfiguredFeatures.DROOPVINE.range(Features.Decorators.FULL_RANGE).squared().count(100));
//        register("ice_pillar", ConfiguredFeatures.ICE_PILLAR.range(Features.Decorators.FULL_RANGE).squared().count(50));
    }
}