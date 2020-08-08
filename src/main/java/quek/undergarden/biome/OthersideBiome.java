package quek.undergarden.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.biome.ParticleEffectAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenEntities;
import quek.undergarden.registry.UndergardenSoundEvents;

import java.awt.*;

public class OthersideBiome extends UndergardenBiome {

    public OthersideBiome() {
        super(new DefaultSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_),
                new SurfaceBuilderConfig(UndergardenBlocks.loose_tremblecrust.get().getDefaultState(), UndergardenBlocks.tremblecrust.get().getDefaultState(), UndergardenBlocks.tremblecrust.get().getDefaultState()),
                Category.NONE,
                0.3625F,
                1.225F,
                2F,
                new Color(123, 71, 70).getRGB(),
                UndergardenSoundEvents.OTHERSIDE_AMBIANCE,
                new ParticleEffectAmbience(ParticleTypes.ASH, 0.118093334F)
        );

    }

    @Override
    public void addFeatures() {
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SPRING_FEATURE.withConfiguration(UndergardenBiomeFeatures.VIRULENT_SPRING_CONFIG).withPlacement(Placement.COUNT_VERY_BIASED_RANGE.configure(new CountRangeConfig(20, 8, 16, 255))));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(UndergardenBlocks.virulent_mix.get().getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(1))));
        UndergardenBiomeFeatures.addOthersideOres(this);
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTLING.get(), 100, 4, 8));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTWALKER.get(), 100, 2, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTBEAST.get(), 50, 1, 2));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getSkyColor() {
        return new Color(123, 71, 70).getRGB();
    }
}
