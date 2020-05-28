package quek.undergarden.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

public abstract class UndergardenBiome extends Biome {

    public UndergardenBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super((new Biome.Builder())
                .surfaceBuilder(surface, config)
                .precipitation(RainType.NONE)
                .category(category)
                .depth(depth)
                .scale(scale)
                .temperature(temp)
                .downfall(0)
                .waterColor(342306)
                .waterFogColor(0)
                .parent((null))
        );
    }

    public abstract void addFeatures();

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getSkyColor() {
        //return new Color(35, 37, 30).getRGB();
        return 0;
    }
}
