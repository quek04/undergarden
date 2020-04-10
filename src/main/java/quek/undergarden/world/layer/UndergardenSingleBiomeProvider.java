package quek.undergarden.world.layer;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;

public class UndergardenSingleBiomeProvider extends BiomeProvider {

    private final Biome biome;

    public UndergardenSingleBiomeProvider(SingleBiomeProviderSettings settings) {
        super(ImmutableSet.of(settings.getBiome()));
        this.biome = settings.getBiome();
    }

    public Biome getNoiseBiome(int x, int y, int z) {
        return this.biome;
    }
}
