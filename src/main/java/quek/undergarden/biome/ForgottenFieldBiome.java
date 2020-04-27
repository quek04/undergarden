package quek.undergarden.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.UndergardenMod;
import quek.undergarden.registry.UndergardenBiomeFeatures;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenEntities;

import java.awt.*;

public class ForgottenFieldBiome extends Biome {

    public ForgottenFieldBiome() {
        super((new Biome.Builder()).surfaceBuilder(new DefaultSurfaceBuilder(
                SurfaceBuilderConfig::deserialize),
                new SurfaceBuilderConfig(
                        UndergardenBlocks.deepturf_block.get().getDefaultState(),
                        UndergardenBlocks.deepsoil.get().getDefaultState(),
                        UndergardenBlocks.depthrock.get().getDefaultState()))
                .precipitation(RainType.NONE)
                .category(Category.PLAINS)
                .depth(0.125F)
                .scale(0.05F)
                .temperature(0.8F)
                .downfall(0)
                .waterColor(342306)
                .waterFogColor(342306)
                .parent(null));
    }

    public void addFeatures() {
        //this.addStructure(UndergardenFeatures.DEPTHROCK_RUIN.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)); TODO:FIX
        this.addCarver(GenerationStage.Carving.AIR, createCarver(UndergardenMod.ForgeEventBus.UNDERGARDEN_CAVE, new ProbabilityConfig(.5F)));
        UndergardenBiomeFeatures.addOres(this);
        UndergardenBiomeFeatures.addPlants(this);
        UndergardenBiomeFeatures.addShrooms(this);
        UndergardenBiomeFeatures.addTrees(this);
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SPRING_FEATURE.withConfiguration(UndergardenBiomeFeatures.UNDERGARDEN_SPRING_CONFIG).withPlacement(Placement.COUNT_VERY_BIASED_RANGE.configure(new CountRangeConfig(20, 8, 16, 127))));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(UndergardenEntities.DWELLER.get(), 100, 4, 8));
        this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(UndergardenEntities.GWIBLING.get(), 20, 5, 10));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTWALKER.get(), 10, 2, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTBEAST.get(), 5, 1, 2));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getSkyColor() {
        return new Color(35, 37, 30).getRGB();
    }
}
