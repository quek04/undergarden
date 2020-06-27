package quek.undergarden.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockStateProvidingFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UndergardenBiomeFeatures;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenEntities;
import quek.undergarden.registry.UndergardenFeatures;

import java.awt.*;

public class OthersideBiome extends UndergardenBiome {

    public OthersideBiome() {
        super(new DefaultSurfaceBuilder(SurfaceBuilderConfig::deserialize),
                new SurfaceBuilderConfig(UndergardenBlocks.tremblecrust.get().getDefaultState(), UndergardenBlocks.tremblecrust.get().getDefaultState(), UndergardenBlocks.tremblecrust.get().getDefaultState()),
                Category.NONE,
                0.3625F,
                1.225F,
                2F
        );

    }

    @Override
    public void addFeatures() {
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SPRING_FEATURE.withConfiguration(UndergardenBiomeFeatures.VIRULENT_SPRING_CONFIG).withPlacement(Placement.COUNT_VERY_BIASED_RANGE.configure(new CountRangeConfig(20, 8, 16, 255))));
        UndergardenBiomeFeatures.addOthersideOres(this);
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTWALKER.get(), 50, 4, 8));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(UndergardenEntities.ROTBEAST.get(), 50, 4, 8));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getSkyColor() {
        return new Color(123, 71, 70).getRGB();
    }
}
