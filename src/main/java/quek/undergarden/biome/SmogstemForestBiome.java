package quek.undergarden.biome;
/*
import net.minecraft.entity.EntityClassification;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.biome.ParticleEffectAmbience;
import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenEntities;
import quek.undergarden.registry.UndergardenSoundEvents;

import java.awt.*;

public class SmogstemForestBiome extends UndergardenBiome {

    public SmogstemForestBiome() {
        super(new DefaultSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_),
                new SurfaceBuilderConfig(UndergardenBlocks.deepturf_block.get().getDefaultState(), UndergardenBlocks.deepsoil.get().getDefaultState(), UndergardenBlocks.depthrock.get().getDefaultState()),
                Category.FOREST,
                0.1F,
                0.2F,
                0.8F,
                new Color(9, 21, 25).getRGB(),
                UndergardenSoundEvents.UNDERGARDEN_AMBIANCE,
                new ParticleEffectAmbience(ParticleTypes.WARPED_SPORE, 0.025F)
        );
    }

    @Override
    public void addFeatures() {
        UndergardenBiomeFeatures.addNormalStuff(this);
        UndergardenBiomeFeatures.addForestSmogstemTrees(this);
        this.addSpawn(EntityClassification.WATER_AMBIENT, new SpawnListEntry(UndergardenEntities.GWIBLING.get(), 10, 3, 6));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(UndergardenEntities.DWELLER.get(), 200, 5, 10));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(UndergardenEntities.GLOOMPER.get(), 200, 5, 10));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(UndergardenEntities.BRUTE.get(), 100, 20, 30));
        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(UndergardenEntities.SCINTLING.get(), 100, 1, 10));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTLING.get(), 20, 4, 8));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTWALKER.get(), 10, 2, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTBEAST.get(), 9, 1, 2));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.STONEBORN.get(), 5, 1, 3));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(double posX, double posZ) {
        return new Color(90, 117, 104).getRGB();
    }

}

 */