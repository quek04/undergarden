package quek.undergarden.registry;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
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
    private static final BlockState SHIMMERWEED = UndergardenBlocks.shimmerweed.get().getDefaultState();
    private static final BlockState BEAN_BUSH = UndergardenBlocks.underbean_bush.get().getDefaultState();
    private static final BlockState INDIGO_SHROOM = UndergardenBlocks.indigo_mushroom.get().getDefaultState();
    private static final BlockState VEIL_SHROOM = UndergardenBlocks.veil_mushroom.get().getDefaultState();
    private static final BlockState INK_SHROOM = UndergardenBlocks.ink_mushroom.get().getDefaultState();
    private static final BlockState BLOOD_SHROOM = UndergardenBlocks.blood_mushroom.get().getDefaultState();
    private static final BlockState UG_COAL_ORE = UndergardenBlocks.coal_ore.get().getDefaultState();
    private static final BlockState CLOGGRUM_ORE = UndergardenBlocks.cloggrum_ore.get().getDefaultState();
    private static final BlockState FROSTSTEEL_ORE = UndergardenBlocks.froststeel_ore.get().getDefaultState();
    private static final BlockState UTHERIUM_ORE = UndergardenBlocks.utherium_ore.get().getDefaultState();
    private static final BlockState WATER = Blocks.WATER.getDefaultState();
    private static final BlockState LAVA = Blocks.LAVA.getDefaultState();

    public static final TreeFeatureConfig SMOGSTEM_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(SMOGSTEM_LOG), new SimpleBlockStateProvider(SMOGSTEM_LEAVES), new BlobFoliagePlacer(2, 0))).baseHeight(9).heightRandA(3).foliageHeight(2).ignoreVines().setSapling(UndergardenBlocks.smogstem_sapling.get()).build();
    public static final TreeFeatureConfig WIGGLEWOOD_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(WIGGLEWOOD_LOG), new SimpleBlockStateProvider(WIGGLEWOOD_LEAVES), new AcaciaFoliagePlacer(2, 0))).baseHeight(3).heightRandA(1).heightRandB(4).trunkHeight(0).setSapling(UndergardenBlocks.wigglewood_sapling.get()).build();
    public static final BlockClusterFeatureConfig INDIGO_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(INDIGO_SHROOM), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig VEIL_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(VEIL_SHROOM), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig INK_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(INK_SHROOM), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig BLOOD_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BLOOD_SHROOM), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig TALL_DEEPTURF_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TALL_DEEPTURF), new SimpleBlockPlacer())).tries(256).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build();
    public static final BlockClusterFeatureConfig SHIMMERWEED_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(SHIMMERWEED), new SimpleBlockPlacer()).tries(128).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build());
    public static final BlockClusterFeatureConfig BEAN_BUSH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BEAN_BUSH), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(UndergardenBlocks.deepturf_block.get())).func_227317_b_().build();

    public static final LiquidsConfig ENCLOSED_WATER_SPRING = new LiquidsConfig(Fluids.WATER.getDefaultState(), false, 10, 0, ImmutableSet.of(UndergardenBlocks.depthrock.get(), UndergardenBlocks.deepsoil.get()));

    public static void addPlants(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(TALL_DEEPTURF_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(40))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(SHIMMERWEED_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(20))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(BEAN_BUSH_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));

    }

    public static void addShrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(INDIGO_MUSHROOM_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(4))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(VEIL_MUSHROOM_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(3))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(INK_MUSHROOM_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(2))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(BLOOD_MUSHROOM_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));

    }

    public static void addTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.SMOGSTEM_TREE.get().withConfiguration(SMOGSTEM_TREE_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(20, 10))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UndergardenFeatures.WIGGLEWOOD_TREE.get().withConfiguration(WIGGLEWOOD_TREE_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(10, 20))));
    }

    public static void addOres(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK, UG_COAL_ORE, 17)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 128))));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK, CLOGGRUM_ORE, 5)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(15, 0, 0, 96))));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK, FROSTSTEEL_ORE, 4)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(3, 0, 0, 32))));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DEPTHROCK, UTHERIUM_ORE, 2)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 0, 0, 128))));
    }

    public static void addLakes(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(WATER)).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(4))));
    }
}
