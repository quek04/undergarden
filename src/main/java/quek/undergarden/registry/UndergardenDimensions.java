package quek.undergarden.registry;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import quek.undergarden.UndergardenMod;

public class UndergardenDimensions {

    public static final RegistryKey<DimensionType> undergarden = RegistryKey.func_240903_a_(Registry.DIMENSION_TYPE_KEY, name("undergarden"));
    public static final RegistryKey<World> undergarden_w = RegistryKey.func_240903_a_(Registry.WORLD_KEY, name("undergarden"));

    public static final RegistryKey<DimensionType> otherside = RegistryKey.func_240903_a_(Registry.DIMENSION_TYPE_KEY, name("otherside"));
    public static final RegistryKey<World> otherside_w = RegistryKey.func_240903_a_(Registry.WORLD_KEY, name("otherside"));

    private static ResourceLocation name(String name) {
        return new ResourceLocation(UndergardenMod.MODID, name);
    }
}

