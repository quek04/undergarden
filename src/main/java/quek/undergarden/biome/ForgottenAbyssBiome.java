package quek.undergarden.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SeaGrassConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.HeightWithChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UndergardenBiomeFeatures;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenFeatures;
import quek.undergarden.world.surfacebuilder.UndergardenSurfaceBuilder;

import static net.minecraft.world.biome.DefaultBiomeFeatures.WATER_SPRING_CONFIG;

public class ForgottenAbyssBiome extends Biome {

    public ForgottenAbyssBiome() {
        super((new Biome.Builder()).surfaceBuilder(new UndergardenSurfaceBuilder(
                SurfaceBuilderConfig::deserialize), new SurfaceBuilderConfig(UndergardenBlocks.deepturf_block.get().getDefaultState(), UndergardenBlocks.deepsoil.get().getDefaultState(), UndergardenBlocks.deepsoil.get().getDefaultState())).precipitation(RainType.RAIN).category(Category.NONE).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0).waterColor(342306).waterFogColor(342306).parent(null));

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SPRING_FEATURE.withConfiguration(UndergardenBiomeFeatures.ENCLOSED_WATER_SPRING).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(16, 10, 20, 120))));
    }

    public void addFeatures() {
        UndergardenBiomeFeatures.addPlants(this);
        UndergardenBiomeFeatures.addOres(this);
        UndergardenBiomeFeatures.addLakes(this);
        UndergardenBiomeFeatures.addTrees(this);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getSkyColor() {
        return 1188882;
    }
}
