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

public class DenseForestBiome extends UndergardenBiome {

    public DenseForestBiome() {
        super(new DefaultSurfaceBuilder(SurfaceBuilderConfig::deserialize),
                new SurfaceBuilderConfig(UndergardenBlocks.deepturf_block.get().getDefaultState(), UndergardenBlocks.deepsoil.get().getDefaultState(), UndergardenBlocks.depthrock.get().getDefaultState()),
                Category.FOREST,
                0.125F,
                0.020F,
                0.8F
        );
    }

    @Override
    public void addFeatures() {
        UndergardenBiomeFeatures.addNormalStuff(this);
        UndergardenBiomeFeatures.addDoubleDeepturf(this);
        UndergardenBiomeFeatures.addDoubleShimmerweed(this);
        UndergardenBiomeFeatures.addDenseForestTrees(this);
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(UndergardenEntities.DWELLER.get(), 50, 10, 20));
        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(UndergardenEntities.SCINTLING.get(), 50, 1, 10));
        this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(UndergardenEntities.GWIBLING.get(), 20, 5, 10));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTWALKER.get(), 20, 2, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTBEAST.get(), 10, 1, 2));
    }
}
