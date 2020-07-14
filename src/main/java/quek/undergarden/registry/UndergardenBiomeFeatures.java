package quek.undergarden.registry;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.PineFoliagePlacer;
import net.minecraft.world.gen.placement.*;
import quek.undergarden.UndergardenMod;

public class UndergardenBiomeFeatures {

    public static final OreFeatureConfig.FillerBlockType DEPTHROCK = OreFeatureConfig.FillerBlockType.create("DEPTHROCK", "depthrock", new BlockMatcher(UndergardenBlocks.depthrock.get()));
    public static final OreFeatureConfig.FillerBlockType TREMBLECRUST = OreFeatureConfig.FillerBlockType.create("TREMBLECRUST", "tremblecrust", new BlockMatcher(UndergardenBlocks.tremblecrust.get()));

    private static final BlockState DEPTHROCK_BLOCK = UndergardenBlocks.depthrock.get().getDefaultState();
    private static final BlockState DEEPSOIL = UndergardenBlocks.deepsoil.get().getDefaultState();
    private static final BlockState DEEPTURF = UndergardenBlocks.deepturf_block.get().getDefaultState();
    private static final BlockState SHIVERSTONE = UndergardenBlocks.shiverstone.get().getDefaultState();
    private static final BlockState SMOGSTEM_LOG = UndergardenBlocks.smogstem_log.get().getDefaultState();
    private static final BlockState SMOGSTEM_LEAVES = UndergardenBlocks.smogstem_leaves.get().getDefaultState();
    private static final BlockState WIGGLEWOOD_LOG = UndergardenBlocks.wigglewood_log.get().getDefaultState();
    private static final BlockState WIGGLEWOOD_LEAVES = UndergardenBlocks.wigglewood_leaves.get().getDefaultState();
    private static final BlockState TALL_DEEPTURF = UndergardenBlocks.tall_deepturf.get().getDefaultState();
    private static final BlockState TALL_ASHEN_DEEPTURF = UndergardenBlocks.ashen_tall_deepturf.get().getDefaultState();
    private static final BlockState DOUBLE_DEEPTURF = UndergardenBlocks.double_deepturf.get().getDefaultState();
    private static final BlockState SHIMMERWEED = UndergardenBlocks.shimmerweed.get().getDefaultState();
    private static final BlockState DOUBLE_SHIMMERWEED = UndergardenBlocks.double_shimmerweed.get().getDefaultState();
    private static final BlockState DITCHBULB_PLANT = UndergardenBlocks.ditchbulb_plant.get().getDefaultState();
    private static final BlockState DEPTHROCK_PEBBLES = UndergardenBlocks.depthrock_pebbles.get().getDefaultState();
    private static final BlockState BEAN_BUSH = UndergardenBlocks.underbean_bush.get().getDefaultState();
    private static final BlockState BLISTERBERRY_BUSH = UndergardenBlocks.blisterberry_bush.get().getDefaultState();
    private static final BlockState INDIGO_SHROOM = UndergardenBlocks.indigo_mushroom.get().getDefaultState();
    private static final BlockState VEIL_SHROOM = UndergardenBlocks.veil_mushroom.get().getDefaultState();
    private static final BlockState INK_SHROOM = UndergardenBlocks.ink_mushroom.get().getDefaultState();
    private static final BlockState BLOOD_SHROOM = UndergardenBlocks.blood_mushroom.get().getDefaultState();
    private static final BlockState GLOOMGOURD = UndergardenBlocks.gloomgourd.get().getDefaultState();
    private static final BlockState UG_COAL_ORE = UndergardenBlocks.coal_ore.get().getDefaultState();
    private static final BlockState CLOGGRUM_ORE = UndergardenBlocks.cloggrum_ore.get().getDefaultState();
    private static final BlockState FROSTSTEEL_ORE = UndergardenBlocks.froststeel_ore.get().getDefaultState();
    private static final BlockState UTHERIUM_ORE = UndergardenBlocks.utherium_ore.get().getDefaultState();
    private static final BlockState OTHERSIDE_UTHERIUM_ORE = UndergardenBlocks.otherside_utherium_ore.get().getDefaultState();
    private static final BlockState REGALIUM_ORE = UndergardenBlocks.regalium_ore.get().getDefaultState();
    private static final BlockState GLOWING_SEAGRASS = UndergardenBlocks.glowing_sea_grass.get().getDefaultState();

    private static final BlockState WATER = Blocks.WATER.getDefaultState();
    private static final BlockState VIRULENT = UndergardenBlocks.virulent_mix.get().getDefaultState();

    public static final TreeFeatureConfig SMOGSTEM_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(SMOGSTEM_LOG), new SimpleBlockStateProvider(SMOGSTEM_LEAVES), new BlobFoliagePlacer(2, 0))).baseHeight(9).heightRandA(3).foliageHeight(2).ignoreVines().setSapling(UndergardenBlocks.smogstem_sapling.get()).build();
    public static final TreeFeatureConfig WIGGLEWOOD_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(WIGGLEWOOD_LOG), new SimpleBlockStateProvider(WIGGLEWOOD_LEAVES), new PineFoliagePlacer(2, 0))).baseHeight(3).heightRandA(0).heightRandB(0).trunkHeight(1).ignoreVines().setSapling(UndergardenBlocks.wigglewood_sapling.get()).build();

    public static final BlockClusterFeatureConfig INDIGO_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(INDIGO_SHROOM), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig VEIL_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(VEIL_SHROOM), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig INK_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(INK_SHROOM), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig BLOOD_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BLOOD_SHROOM), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig TALL_DEEPTURF_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TALL_DEEPTURF), new SimpleBlockPlacer())).tries(512).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build();
    public static final BlockClusterFeatureConfig TALL_ASHEN_DEEPTURF_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TALL_ASHEN_DEEPTURF), new SimpleBlockPlacer())).tries(512).whitelist(ImmutableSet.of(UndergardenBlocks.ashen_deepturf.get())).func_227317_b_().build();
    public static final BlockClusterFeatureConfig DOUBLE_DEEPTURF_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(DOUBLE_DEEPTURF), new DoublePlantBlockPlacer()).tries(256).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build());
    public static final BlockClusterFeatureConfig SHIMMERWEED_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(SHIMMERWEED), new SimpleBlockPlacer()).tries(256).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build());
    public static final BlockClusterFeatureConfig DOUBLE_SHIMMERWEED_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(DOUBLE_SHIMMERWEED), new DoublePlantBlockPlacer()).tries(128).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build());
    public static final BlockClusterFeatureConfig DITCHBULB_PLANT_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(DITCHBULB_PLANT), new SimpleBlockPlacer()).tries(64).whitelist(ImmutableSet.of(UndergardenBlocks.depthrock.get())).func_227317_b_().build());
    public static final BlockClusterFeatureConfig PEBBLE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(DEPTHROCK_PEBBLES), new SimpleBlockPlacer()).tries(256).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get(), UndergardenBlocks.deepsoil.get(), UndergardenBlocks.ashen_deepturf.get(), UndergardenBlocks.depthrock.get())).func_227317_b_().build());
    public static final BlockClusterFeatureConfig BEAN_BUSH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BEAN_BUSH), new SimpleBlockPlacer())).tries(128).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build();
    public static final BlockClusterFeatureConfig BLISTERBERRY_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BLISTERBERRY_BUSH), new SimpleBlockPlacer())).tries(128).whitelist(ImmutableSet.of(UndergardenBlocks.ashen_deepturf.get())).func_227317_b_().build();
    public static final BlockClusterFeatureConfig GLOOMGOURD_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GLOOMGOURD), new SimpleBlockPlacer())).tries(128).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build();

    public static final LiquidsConfig UNDERGARDEN_SPRING_CONFIG = new LiquidsConfig(Fluids.WATER.getDefaultState(), false, 4, 1, ImmutableSet.of(UndergardenBlocks.depthrock.get(), UndergardenBlocks.deepsoil.get(), UndergardenBlocks.tremblecrust.get()));
    public static final LiquidsConfig VIRULENT_SPRING_CONFIG = new LiquidsConfig(UndergardenFluids.virulent_mix_source.get().getDefaultState(), false, 4, 1, ImmutableSet.of(UndergardenBlocks.depthrock.get(), UndergardenBlocks.tremblecrust.get()));

    public static void addNormalStuff(Biome biome) {
        biome.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(UndergardenWorldCarvers.UNDERGARDEN_CAVE.get(), new ProbabilityConfig(.5F)));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SPRING_FEATURE.withConfiguration(UndergardenBiomeFeatures.UNDERGARDEN_SPRING_CONFIG).withPlacement(Placement.COUNT_VERY_BIASED_RANGE.configure(new CountRangeConfig(20, 8, 16, 255))));
        //biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(WATER)).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(20))));
        //biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(VIRULENT)).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(5))));
        //biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.DROOPWEED.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1000))));
        addOres(biome);
        addBlockVariants(biome);
        addSediment(biome);
        addPlants(biome);
        addUnderwaterPlants(biome);
        addShrooms(biome);
    }

    public static void addPlants(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(TALL_DEEPTURF_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(40))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(SHIMMERWEED_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(20))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(BEAN_BUSH_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(10))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(GLOOMGOURD_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(32))));
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DITCHBULB_PLANT_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(10))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(PEBBLE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(10))));
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(PEBBLE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(80))));
    }

    public static void addSmogVents(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.SMOG_VENT.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(20))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(TALL_ASHEN_DEEPTURF_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(80))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(PEBBLE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(10))));
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(PEBBLE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(80))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(BLISTERBERRY_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(20))));
    }

    public static void addUnderwaterPlants(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.GLOWING_KELP.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1000))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SIMPLE_BLOCK.withConfiguration(new BlockWithContextConfig(GLOWING_SEAGRASS, new BlockState[]{UndergardenBlocks.depthrock.get().getDefaultState(), UndergardenBlocks.deepsoil.get().getDefaultState()}, new BlockState[]{Blocks.WATER.getDefaultState()}, new BlockState[]{Blocks.WATER.getDefaultState()})).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1000))));
    }

    public static void addDoubleDeepturf(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DOUBLE_DEEPTURF_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(20))));
    }

    public static void addDoubleShimmerweed(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DOUBLE_SHIMMERWEED_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(20))));
    }

    public static void addShrooms(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(INDIGO_MUSHROOM_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(4))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(VEIL_MUSHROOM_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(3))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(INK_MUSHROOM_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(2))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(BLOOD_MUSHROOM_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
    }

    public static void addTrees(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.SMOGSTEM_TREE.get().withConfiguration(SMOGSTEM_TREE_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(5, 100))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.WIGGLEWOOD_TREE.get().withConfiguration(WIGGLEWOOD_TREE_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(10, 50))));
    }

    public static void addForestSmogstemTrees(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.SMOGSTEM_TREE.get().withConfiguration(SMOGSTEM_TREE_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(1000, 1000))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.FANCY_SMOGSTEM_TREE.get().withConfiguration(SMOGSTEM_TREE_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(1000, 1000))));
    }

    public static void addForestWigglewoodTrees(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.WIGGLEWOOD_TREE.get().withConfiguration(WIGGLEWOOD_TREE_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(1000, 1000))));
    }

    public static void addDenseForestTrees(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.SMOGSTEM_TREE.get().withConfiguration(SMOGSTEM_TREE_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(3000, 3000))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.FANCY_SMOGSTEM_TREE.get().withConfiguration(SMOGSTEM_TREE_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(5, 500))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.WIGGLEWOOD_TREE.get().withConfiguration(WIGGLEWOOD_TREE_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(3000, 3000))));
    }

    public static void addOres(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK, UG_COAL_ORE, 17)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 256))));
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK, CLOGGRUM_ORE, 5)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(15, 0, 0, 128))));
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK, FROSTSTEEL_ORE, 4)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(3, 0, 0, 64))));
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK, UTHERIUM_ORE, 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 0, 0, 32))));
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK, REGALIUM_ORE, 4)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 0, 0, 11))));
    }

    public static void addOthersideOres(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(TREMBLECRUST, OTHERSIDE_UTHERIUM_ORE, 16)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(4, 0, 0, 256))));
    }

    public static void addBlockVariants(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK, DEEPSOIL, 33)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 256))));
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK, SHIVERSTONE, 33)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 256))));
    }

    public static void addSediment(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.withConfiguration(new SphereReplaceConfig(DEEPSOIL, 7, 2, Lists.newArrayList(DEPTHROCK_BLOCK, DEEPTURF))).withPlacement(Placement.COUNT_TOP_SOLID.configure(new FrequencyConfig(3))));
    }

}
