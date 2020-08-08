package quek.undergarden.biome;

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

public class SmogSpiresBiome extends UndergardenBiome {

    public SmogSpiresBiome() {
        super(new DefaultSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_),
                new SurfaceBuilderConfig(UndergardenBlocks.ashen_deepturf.get().getDefaultState(), UndergardenBlocks.deepsoil.get().getDefaultState(), UndergardenBlocks.depthrock.get().getDefaultState()),
                Category.NONE,
                0.125F,
                0.05F,
                2F,
                new Color(39, 39, 39).getRGB(),
                UndergardenSoundEvents.UNDERGARDEN_AMBIANCE,
                new ParticleEffectAmbience(ParticleTypes.WHITE_ASH, 0.118093334F)
        );
    }

    @Override
    public void addFeatures() {
        UndergardenBiomeFeatures.addSmogVents(this);
        UndergardenBiomeFeatures.addOres(this);
        UndergardenBiomeFeatures.addUnderwaterPlants(this);
        UndergardenBiomeFeatures.addBlockVariants(this);
        UndergardenBiomeFeatures.addSediment(this);
        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(UndergardenEntities.SCINTLING.get(), 50, 1, 10));
        this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(UndergardenEntities.GWIBLING.get(), 20, 5, 10));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTLING.get(), 20, 4, 8));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTWALKER.get(), 10, 2, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTBEAST.get(), 9, 1, 2));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.BLISTERBOMBER.get(), 1, 1,1));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(double posX, double posZ) {
        return new Color(115, 124, 119).getRGB();
    }
}
