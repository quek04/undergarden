package quek.undergarden.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;

import net.minecraft.world.storage.WorldInfo;
import quek.undergarden.biome.provider.UndergardenBiomeProvider;
import quek.undergarden.biome.provider.UndergardenBiomeProviderSettings;
import quek.undergarden.registry.UndergardenBiomes;
import quek.undergarden.registry.UndergardenDimensions;
import quek.undergarden.world.gen.UndergardenChunkGenerator;
import quek.undergarden.world.gen.UndergardenGenerationSettings;
import quek.undergarden.world.layer.UndergardenSingleBiomeProvider;

import javax.annotation.Nullable;

public class UndergardenDimension extends Dimension {

    public UndergardenDimension(World world, DimensionType dimensionType) {
        super(world, dimensionType, 0);
    }

    public static boolean isTheUndergarden(@Nullable World world) {
        if (world == null) return false;
        return world.dimension.getType().getModType() == UndergardenDimensions.UNDERGARDEN.get();
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator() {
        UndergardenGenerationSettings undergardenGenerationSettings = new UndergardenGenerationSettings();
        UndergardenBiomeProviderSettings settings = new UndergardenBiomeProviderSettings();
        WorldInfo worldInfo = this.world.getWorldInfo();
        settings.setWorldInfo(worldInfo);
        UndergardenBiomeProvider provider = new UndergardenBiomeProvider(settings);
        return new UndergardenChunkGenerator(world, provider, undergardenGenerationSettings);
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
        return 0.5F;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        return new Vec3d(.101, .105, .094);
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }

    @Override
    public boolean shouldMapSpin(String entity, double x, double z, double rotation) {
        return true;
    }
}
