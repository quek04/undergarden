package quek.undergarden.registry;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import quek.undergarden.Undergarden;
import quek.undergarden.world.dimension.UGBiomeProvider;
import quek.undergarden.world.dimension.UGChunkGenerator;

public class UGDimensions {

    public static final RegistryKey<DimensionType> UNDERGARDEN_DIMENSION = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, name("undergarden"));
    public static final RegistryKey<World> UNDERGARDEN_WORLD = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, name("undergarden"));

    public static final RegistryKey<DimensionType> OTHERSIDE_DIMENSION = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, name("otherside"));
    public static final RegistryKey<World> OTHERSIDE_WORLD = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, name("otherside"));

    private static ResourceLocation name(String name) {
        return new ResourceLocation(Undergarden.MODID, name);
    }

    public static void registerDimensionStuff() {
        Registry.register(Registry.CHUNK_GENERATOR_CODEC, name("chunk_generator"), UGChunkGenerator.CODEC);
        Registry.register(Registry.BIOME_PROVIDER_CODEC, name("biome_provider"), UGBiomeProvider.CODEC);
    }
}