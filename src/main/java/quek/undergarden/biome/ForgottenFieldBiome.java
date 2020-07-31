package quek.undergarden.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.*;

public class ForgottenFieldBiome extends UndergardenBiome {

    public ForgottenFieldBiome() {
        super(new DefaultSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_),
                new SurfaceBuilderConfig(UndergardenBlocks.deepturf_block.get().getDefaultState(), UndergardenBlocks.deepsoil.get().getDefaultState(), UndergardenBlocks.depthrock.get().getDefaultState()),
                Category.PLAINS,
                0.125F,
                 0.05F,
                0.8F
        );
    }

    @Override
    public void addFeatures() {
        UndergardenBiomeFeatures.addNormalStuff(this);
        UndergardenBiomeFeatures.addDoubleShimmerweed(this);
        UndergardenBiomeFeatures.addTrees(this);
        //this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, UndergardenFeatures.ENIGMATIC_STATUE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(100))));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(UndergardenEntities.DWELLER.get(), 200, 10, 20));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(UndergardenEntities.GLOOMPER.get(), 200, 10, 20));
        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(UndergardenEntities.SCINTLING.get(), 100, 1, 10));
        this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(UndergardenEntities.GWIBLING.get(), 20, 5, 10));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTWALKER.get(), 10, 2, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTBEAST.get(), 5, 1, 2));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(double posX, double posZ) {
        return 5993819;
    }

}
