package quek.undergarden.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;

import net.minecraft.world.storage.WorldInfo;
import quek.undergarden.registry.UndergardenBiomes;

public class UndergardenBiomeProviderSettings implements IBiomeProviderSettings {

    private Biome biome = UndergardenBiomes.forgotten_field.get();

    public UndergardenBiomeProviderSettings(WorldInfo info) {
    }

    public UndergardenBiomeProviderSettings setBiome(Biome biomeIn) {
        this.biome = biomeIn;
        return this;
    }

    public Biome getBiome() {
        return this.biome;
    }

}
