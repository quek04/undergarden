package quek.undergarden.world;
/*
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.UndergardenConfig;
import quek.undergarden.client.render.OthersideSkyRender;
import quek.undergarden.registry.UndergardenBiomes;
import quek.undergarden.registry.UndergardenDimensions;
import quek.undergarden.world.gen.OthersideChunkGenerator;
import quek.undergarden.world.gen.OthersideGenerationSettings;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class OthersideDimension extends Dimension {

    public OthersideDimension(World world, DimensionType dimensionType) {
        super(world, dimensionType, 0);
    }

    public static boolean isTheOtherside(@Nullable World world) {
        if (world == null) return false;
        return world.dimension.getType().getModType() == UndergardenDimensions.OTHERSIDE.get();
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator() {
        OthersideGenerationSettings othersideGen = new OthersideGenerationSettings();
        SingleBiomeProviderSettings providerSettings = new SingleBiomeProviderSettings(world.getWorldInfo()).setBiome(UndergardenBiomes.OTHERSIDE.get());
        SingleBiomeProvider provider = new SingleBiomeProvider(providerSettings);
        return new OthersideChunkGenerator(world, provider, othersideGen);
    }

    @SubscribeEvent
    public static void teleportEntityEvent(EntityEvent event) {
        if(!UndergardenConfig.disableOthersideTeleport.get()) {
            Entity entity = event.getEntity();

            if(entity != null) {
                if(entity.dimension == UndergardenDimensions.otherside_dimension) {
                    if(entity.getPosY() < 0) {
                        entity.setPosition(entity.getPosX(), 256, entity.getPosZ());
                    }
                }

            }
        }

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public IRenderHandler getSkyRenderer() {
        IRenderHandler skyRenderer = super.getSkyRenderer();
        if (skyRenderer == null) {
            this.setSkyRenderer(skyRenderer = new OthersideSkyRender());
        }
        return skyRenderer;
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
        return true;
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        return new Vec3d(0.66, 0.53, 0.53);
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public boolean doesXZShowFog(int x, int z) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public float getCloudHeight() {
        return 150;
    }

}

 */
