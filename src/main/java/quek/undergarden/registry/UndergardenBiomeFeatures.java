package quek.undergarden.registry;

import com.google.common.collect.ImmutableSet;
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
import net.minecraft.world.gen.placement.*;

public class UndergardenBiomeFeatures {

    public static final OreFeatureConfig.FillerBlockType DEPTHROCK = OreFeatureConfig.FillerBlockType.create("DEPTHROCK", "depthrock", new BlockMatcher(UndergardenBlocks.depthrock.get()));

    private static final BlockState SMOGSTEM_LOG = UndergardenBlocks.smogstem_log.get().getDefaultState();
    private static final BlockState SMOGSTEM_LEAVES = UndergardenBlocks.smogstem_leaves.get().getDefaultState();
    private static final BlockState WIGGLEWOOD_LOG = UndergardenBlocks.wigglewood_log.get().getDefaultState();
    private static final BlockState WIGGLEWOOD_LEAVES = UndergardenBlocks.wigglewood_leaves.get().getDefaultState();
    private static final BlockState TALL_DEEPTURF = UndergardenBlocks.tall_deepturf.get().getDefaultState();
    private static final BlockState DOUBLE_DEEPTURF = UndergardenBlocks.double_deepturf.get().getDefaultState();
    private static final BlockState SHIMMERWEED = UndergardenBlocks.shimmerweed.get().getDefaultState();
    private static final BlockState DOUBLE_SHIMMERWEED = UndergardenBlocks.double_shimmerweed.get().getDefaultState();
    private static final BlockState DITCHBULB_PLANT = UndergardenBlocks.ditchbulb_plant.get().getDefaultState();
    private static final BlockState BEAN_BUSH = UndergardenBlocks.underbean_bush.get().getDefaultState();
    private static final BlockState INDIGO_SHROOM = UndergardenBlocks.indigo_mushroom.get().getDefaultState();
    private static final BlockState VEIL_SHROOM = UndergardenBlocks.veil_mushroom.get().getDefaultState();
    private static final BlockState INK_SHROOM = UndergardenBlocks.ink_mushroom.get().getDefaultState();
    private static final BlockState BLOOD_SHROOM = UndergardenBlocks.blood_mushroom.get().getDefaultState();
    private static final BlockState GLOOMGOURD = UndergardenBlocks.gloomgourd.get().getDefaultState();
    private static final BlockState UG_COAL_ORE = UndergardenBlocks.coal_ore.get().getDefaultState();
    private static final BlockState CLOGGRUM_ORE = UndergardenBlocks.cloggrum_ore.get().getDefaultState();
    private static final BlockState FROSTSTEEL_ORE = UndergardenBlocks.froststeel_ore.get().getDefaultState();
    private static final BlockState UTHERIUM_ORE = UndergardenBlocks.utherium_ore.get().getDefaultState();

    public static final TreeFeatureConfig SMOGSTEM_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(SMOGSTEM_LOG), new SimpleBlockStateProvider(SMOGSTEM_LEAVES), new BlobFoliagePlacer(2, 0))).baseHeight(9).heightRandA(3).foliageHeight(2).ignoreVines().setSapling(UndergardenBlocks.smogstem_sapling.get()).build();
    public static final TreeFeatureConfig WIGGLEWOOD_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(WIGGLEWOOD_LOG), new SimpleBlockStateProvider(WIGGLEWOOD_LEAVES), new AcaciaFoliagePlacer(2, 0))).baseHeight(3).heightRandA(1).heightRandB(4).trunkHeight(0).setSapling(UndergardenBlocks.wigglewood_sapling.get()).build();
    public static final BlockClusterFeatureConfig INDIGO_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(INDIGO_SHROOM), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig VEIL_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(VEIL_SHROOM), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig INK_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(INK_SHROOM), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig BLOOD_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BLOOD_SHROOM), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig TALL_DEEPTURF_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TALL_DEEPTURF), new SimpleBlockPlacer())).tries(256).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build();
    public static final BlockClusterFeatureConfig DOUBLE_DEEPTURF_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(DOUBLE_DEEPTURF), new DoublePlantBlockPlacer()).tries(128).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build());
    public static final BlockClusterFeatureConfig SHIMMERWEED_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(SHIMMERWEED), new SimpleBlockPlacer()).tries(128).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build());
    public static final BlockClusterFeatureConfig DOUBLE_SHIMMERWEED_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(DOUBLE_SHIMMERWEED), new DoublePlantBlockPlacer()).tries(64).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build());
    public static final BlockClusterFeatureConfig DITCHBULB_PLANT_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(DITCHBULB_PLANT), new SimpleBlockPlacer()).tries(32).whitelist(ImmutableSet.of(UndergardenBlocks.depthrock.get())).func_227317_b_().build());
    public static final BlockClusterFeatureConfig BEAN_BUSH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BEAN_BUSH), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build();
    public static final BlockClusterFeatureConfig GLOOMGOURD_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GLOOMGOURD), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build();

    public static final LiquidsConfig UNDERGARDEN_SPRING_CONFIG = new LiquidsConfig(Fluids.WATER.getDefaultState(), false, 4, 1, ImmutableSet.of(UndergardenBlocks.depthrock.get(), UndergardenBlocks.deepsoil.get()));
    public static final LiquidsConfig UTHERIC_SPRING_CONFIG = new LiquidsConfig(Fluids.LAVA.getDefaultState(), false, 4, 1, ImmutableSet.of(UndergardenBlocks.depthrock.get()));

    public static void addPlants(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(TALL_DEEPTURF_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(40))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(SHIMMERWEED_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(20))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(BEAN_BUSH_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(GLOOMGOURD_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(32))));
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DITCHBULB_PLANT_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(10))));
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
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.SMOGSTEM_TREE.get().withConfiguration(SMOGSTEM_TREE_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(5, 10))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.WIGGLEWOOD_TREE.get().withConfiguration(WIGGLEWOOD_TREE_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(10, 5))));
    }

    public static void addForestSmogstemTrees(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.SMOGSTEM_TREE.get().withConfiguration(SMOGSTEM_TREE_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(300, 300))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.FANCY_SMOGSTEM_TREE.get().withConfiguration(SMOGSTEM_TREE_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(5, 50))));
    }

    public static void addOres(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK, UG_COAL_ORE, 17)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 128))));
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK, CLOGGRUM_ORE, 5)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(15, 0, 0, 96))));
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK, FROSTSTEEL_ORE, 4)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(3, 0, 0, 32))));
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK, UTHERIUM_ORE, 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 0, 0, 64))));
    }

}
