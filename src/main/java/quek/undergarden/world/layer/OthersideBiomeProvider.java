package quek.undergarden.world.layer;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class OthersideBiomeProvider extends BiomeProvider {

    private final Biome biome;

    public OthersideBiomeProvider(OthersideBiomeProviderSettings settings) {
        super(ImmutableSet.of(settings.getBiome()));
        this.biome = settings.getBiome();
    }

    public Biome getNoiseBiome(int x, int y, int z) {
        return this.biome;
    }

    @Nullable
    public BlockPos func_225531_a_(int xIn, int yIn, int zIn, int radiusIn, List<Biome> biomesIn, Random randIn) {
        return biomesIn.contains(this.biome) ? new BlockPos(xIn - radiusIn + randIn.nextInt(radiusIn * 2 + 1), yIn, zIn - radiusIn + randIn.nextInt(radiusIn * 2 + 1)) : null;
    }

    /**
     * Returns the set of biomes contained in cube of side length 2 * radius + 1 centered at (xIn, yIn, zIn)
     */
    public Set<Biome> getBiomes(int xIn, int yIn, int zIn, int radius) {
        return Sets.newHashSet(this.biome);
    }
}
