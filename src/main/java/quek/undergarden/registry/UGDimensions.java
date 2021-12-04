package quek.undergarden.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import quek.undergarden.Undergarden;
import quek.undergarden.world.dimension.UGChunkGenerator;

public class UGDimensions {

    public static final ResourceKey<Level> UNDERGARDEN_LEVEL = ResourceKey.create(Registry.DIMENSION_REGISTRY, name("undergarden"));
    public static final ResourceKey<Level> OTHERSIDE_LEVEL = ResourceKey.create(Registry.DIMENSION_REGISTRY, name("otherside"));

    private static ResourceLocation name(String name) {
        return new ResourceLocation(Undergarden.MODID, name);
    }

    public static void registerDimensionStuff() {
        Registry.register(Registry.CHUNK_GENERATOR, name("chunk_generator"), UGChunkGenerator.CODEC);
        //Registry.register(Registry.BIOME_SOURCE, name("biome_provider"), UGBiomeProvider.CODEC);
    }
}