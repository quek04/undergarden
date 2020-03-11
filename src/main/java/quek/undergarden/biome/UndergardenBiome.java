package quek.undergarden.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public abstract class UndergardenBiome extends Biome {

    public UndergardenBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(new Biome.Builder()
                .surfaceBuilder(surface, config)
                .precipitation(RainType.NONE)
                .category(category)
                .depth(depth)
                .scale(scale)
                .temperature(temp)
        );
    }

}
