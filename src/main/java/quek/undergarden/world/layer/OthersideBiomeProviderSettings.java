package quek.undergarden.world.layer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import quek.undergarden.registry.UndergardenBiomes;

public class OthersideBiomeProviderSettings implements IBiomeProviderSettings {

    private Biome biome = UndergardenBiomes.OTHERSIDE.get();

    public OthersideBiomeProviderSettings() {

    }

    public OthersideBiomeProviderSettings setBiome(Biome biomeIn) {
        this.biome = biomeIn;
        return this;
    }

    public Biome getBiome() {
        return this.biome;
    }
}
