package quek.undergarden.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.UndergardenMod;
import quek.undergarden.client.render.OthersideSkyRender;
import quek.undergarden.registry.UndergardenDimensions;
import quek.undergarden.world.gen.OthersideChunkGenerator;
import quek.undergarden.world.gen.OthersideGenerationSettings;
import quek.undergarden.world.layer.OthersideBiomeProvider;
import quek.undergarden.world.layer.OthersideBiomeProviderSettings;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class OthersideDimension extends Dimension {

    private IRenderHandler skyRenderer = new OthersideSkyRender();

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
        OthersideBiomeProviderSettings providerSettings = new OthersideBiomeProviderSettings();
        OthersideBiomeProvider provider = new OthersideBiomeProvider(providerSettings);
        return new OthersideChunkGenerator(world, provider, othersideGen);
    }

    /* TODO: LOOK INTO THIS NOT WORKING RIGHT
    @SubscribeEvent
    public static void teleportEntityEvent(EntityEvent event) {
        Entity entity = event.getEntity();

        if(entity != null) {
            if(entity.dimension == UndergardenMod.otherside_dimension) {
                if(entity.getPosY() < 0) {
                    entity.setPosition(entity.getPosX(), 256, entity.getPosZ());
                }
            }

        }
    }
    */

    /*
    @Nullable
    @OnlyIn(Dist.CLIENT)
    @Override
    public net.minecraftforge.client.IRenderHandler getSkyRenderer() {
        return skyRenderer;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void setSkyRenderer(net.minecraftforge.client.IRenderHandler skyRenderer) {
        this.skyRenderer = skyRenderer;
    }
    */

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
        return 500;
    }


}
