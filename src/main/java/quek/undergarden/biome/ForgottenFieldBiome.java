package quek.undergarden.biome;

import static quek.undergarden.registry.UndergardenSurfaceBuilders.DEFAULT_UNDERGARDEN;
import static quek.undergarden.registry.UndergardenSurfaceBuilders.default_undergarden_config;

public class ForgottenFieldBiome extends UndergardenBiome {

    public ForgottenFieldBiome() {
        super(DEFAULT_UNDERGARDEN.get(), default_undergarden_config, Category.NONE, 0.1F, 0.2F, 1);
    }

}
