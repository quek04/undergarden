package quek.undergarden.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import quek.undergarden.UndergardenMod;
import quek.undergarden.registry.UndergardenBiomeFeatures;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenEntities;

public class WigglewoodForestBiome extends UndergardenBiome {

    public WigglewoodForestBiome() {
        super(new DefaultSurfaceBuilder(SurfaceBuilderConfig::deserialize),
                new SurfaceBuilderConfig(UndergardenBlocks.deepturf_block.get().getDefaultState(), UndergardenBlocks.deepsoil.get().getDefaultState(), UndergardenBlocks.depthrock.get().getDefaultState()),
                Category.FOREST,
                0.125F,
                0.05F,
                0.8F
        );
    }

    @Override
    public void addFeatures() {
        UndergardenBiomeFeatures.addNormalStuff(this);
        UndergardenBiomeFeatures.addForestWigglewoodTrees(this);
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(UndergardenEntities.DWELLER.get(), 100, 10, 20));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(UndergardenEntities.BRUTE.get(), 50, 20, 30));
        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(UndergardenEntities.SCINTLING.get(), 100, 1, 10));
        this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(UndergardenEntities.GWIBLING.get(), 20, 5, 10));
    }
}
