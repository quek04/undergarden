package quek.undergarden.biome;

import static quek.undergarden.registry.UndergardenSurfaceBuilders.*;

public class ForgottenFieldBiome extends UndergardenBiome {

    public ForgottenFieldBiome() {
        super(DEFAULT_UNDERGARDEN.get(), default_config.orElseThrow(NullPointerException::new), Category.NONE, 0.1F, 0.2F, 1);
    }

}
