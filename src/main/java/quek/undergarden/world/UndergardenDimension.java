package quek.undergarden.world;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import quek.undergarden.registry.UndergardenBiomes;
import quek.undergarden.registry.UndergardenDimensions;

import javax.annotation.Nullable;

public class UndergardenDimension extends Dimension {

    public UndergardenDimension(World world, DimensionType type) {
        super(world, type, 0);
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator() {
        ChunkGeneratorType<UndergardenGenerationSettings, UndergardenChunkGenerator> undergardenChunkGen = UndergardenDimensions.UNDERGARDEN_GEN.get();
        UndergardenGenerationSettings genSettings = undergardenChunkGen.createSettings();
        BiomeProviderType<UndergardenBiomeProviderSettings, UndergardenBiomeProvider> undergardenBiomeProv = UndergardenDimensions.UNDERGARDEN_BIOMES.get();
        return undergardenChunkGen.create(this.world, undergardenBiomeProv.create(undergardenBiomeProv.func_226840_a_(this.world.getWorldInfo())), genSettings);
    }

    @Nullable
    @Override
    public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
        return null;
    }

    @Nullable
    @Override
    public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
        return null;
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 0;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        return null;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }
}
