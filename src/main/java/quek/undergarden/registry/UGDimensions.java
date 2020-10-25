package quek.undergarden.registry;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import quek.undergarden.UGMod;

public class UGDimensions {

    public static final RegistryKey<DimensionType> undergarden = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, name("undergarden"));
    public static final RegistryKey<World> undergarden_w = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, name("undergarden"));

    public static final RegistryKey<DimensionType> otherside = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, name("otherside"));
    public static final RegistryKey<World> otherside_w = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, name("otherside"));

    private static ResourceLocation name(String name) {
        return new ResourceLocation(UGMod.MODID, name);
    }
}

