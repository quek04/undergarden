package quek.undergarden.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.CaveEdgeConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import quek.undergarden.UndergardenMod;
import quek.undergarden.registry.*;

public class ForgottenFieldBiome extends UndergardenBiome {

    public ForgottenFieldBiome() {
        super(new DefaultSurfaceBuilder(SurfaceBuilderConfig::deserialize),
                new SurfaceBuilderConfig(UndergardenBlocks.deepturf_block.get().getDefaultState(), UndergardenBlocks.deepsoil.get().getDefaultState(), UndergardenBlocks.depthrock.get().getDefaultState()),
                Category.PLAINS,
                0.125F,
                0.05F,
                0.8F);
    }

    @Override
    public void addFeatures() {
        //this.addStructure(UndergardenFeatures.DEPTHROCK_RUIN.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        //this.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, UndergardenFeatures.DEPTHROCK_RUIN.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CARVING_MASK.configure(new CaveEdgeConfig(GenerationStage.Carving.AIR, 5))));
        this.addCarver(GenerationStage.Carving.AIR, createCarver(UndergardenMod.ForgeEventBus.UNDERGARDEN_CAVE, new ProbabilityConfig(.5F)));
        UndergardenBiomeFeatures.addOres(this);
        UndergardenBiomeFeatures.addPlants(this);
        UndergardenBiomeFeatures.addDoubleShimmerweed(this);
        UndergardenBiomeFeatures.addShrooms(this);
        UndergardenBiomeFeatures.addTrees(this);
        UndergardenBiomeFeatures.addGlitterkelp(this);
        UndergardenBiomeFeatures.addGlowingSeaGrass(this);
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SPRING_FEATURE.withConfiguration(UndergardenBiomeFeatures.UNDERGARDEN_SPRING_CONFIG).withPlacement(Placement.COUNT_VERY_BIASED_RANGE.configure(new CountRangeConfig(20, 8, 16, 127))));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(UndergardenEntities.DWELLER.get(), 200, 10, 20));
        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(UndergardenEntities.SCINTLING.get(), 100, 1, 10));
        this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(UndergardenEntities.GWIBLING.get(), 20, 5, 10));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTWALKER.get(), 10, 2, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTBEAST.get(), 5, 1, 2));
    }

}
