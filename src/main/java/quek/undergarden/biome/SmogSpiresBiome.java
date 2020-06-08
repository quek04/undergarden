package quek.undergarden.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import quek.undergarden.UndergardenMod;
import quek.undergarden.registry.UndergardenBiomeFeatures;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenEntities;

public class SmogSpiresBiome extends UndergardenBiome {

    public SmogSpiresBiome() {
        super(new DefaultSurfaceBuilder(SurfaceBuilderConfig::deserialize),
                new SurfaceBuilderConfig(UndergardenBlocks.ashen_deepturf.get().getDefaultState(), UndergardenBlocks.deepsoil.get().getDefaultState(), UndergardenBlocks.depthrock.get().getDefaultState()),
                Category.NONE,
                0.225F,
                0.010F,
                2F
        );
    }

    @Override
    public void addFeatures() {
        UndergardenBiomeFeatures.addSmogVents(this);
        UndergardenBiomeFeatures.addOres(this);
        UndergardenBiomeFeatures.addUnderwaterPlants(this);
        UndergardenBiomeFeatures.addBlockVariants(this);
        UndergardenBiomeFeatures.addSediment(this);
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(UndergardenEntities.DWELLER.get(), 100, 15, 30));
        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(UndergardenEntities.SCINTLING.get(), 50, 1, 10));
        this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(UndergardenEntities.GWIBLING.get(), 20, 5, 10));
    }
}
