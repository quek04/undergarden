package quek.undergarden.world;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;

import static quek.undergarden.registry.UndergardenBiomes.*;

import java.util.Set;

public class UndergardenBiomeProvider extends BiomeProvider {

    private final Biome biome;

    private static final Set<Biome> biomes = ImmutableSet.of(forgotten_field.get());

    public UndergardenBiomeProvider(UndergardenBiomeProviderSettings settings) {
        super(biomes);
        this.biome = settings.getBiome();

        getBiomesToSpawnIn().clear();
        getBiomesToSpawnIn().add(forgotten_field.get());
    }



    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return biome;
    }

    @Override
    public boolean hasStructure(Structure<?> structureIn) {
        return false;
    }

    @Override
    public Set<BlockState> getSurfaceBlocks() {
        if (this.topBlocksCache.isEmpty()) {
            for(Biome biome : biomes) {
                this.topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
            }
        }

        return this.topBlocksCache;
    }
}
