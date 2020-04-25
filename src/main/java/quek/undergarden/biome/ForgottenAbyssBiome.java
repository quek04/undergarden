package quek.undergarden.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.UndergardenMod;
import quek.undergarden.registry.UndergardenBiomeFeatures;
import quek.undergarden.registry.UndergardenBlocks;

public class ForgottenAbyssBiome extends Biome {

    public ForgottenAbyssBiome() {
        super((new Biome.Builder()).surfaceBuilder(new DefaultSurfaceBuilder(
                SurfaceBuilderConfig::deserialize),
                new SurfaceBuilderConfig(
                        UndergardenBlocks.deepturf_block.get().getDefaultState(),
                        UndergardenBlocks.deepsoil.get().getDefaultState(),
                        UndergardenBlocks.depthrock.get().getDefaultState()))
                .precipitation(RainType.RAIN)
                .category(Category.NONE)
                .depth(0.1F)
                .scale(0.2F)
                .temperature(0.8F)
                .downfall(0)
                .waterColor(342306)
                .waterFogColor(342306)
                .parent(null));
    }

    public void addFeatures() {
        UndergardenBiomeFeatures.addOres(this);
        UndergardenBiomeFeatures.addPlants(this);
        UndergardenBiomeFeatures.addShrooms(this);
        UndergardenBiomeFeatures.addLakes(this);
        UndergardenBiomeFeatures.addTrees(this);
        this.addCarver(GenerationStage.Carving.AIR, createCarver(UndergardenMod.ForgeEventBus.UNDERGARDEN_CAVE, new ProbabilityConfig(.5F)));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getSkyColor() {
        return 1188882;
    }
}
