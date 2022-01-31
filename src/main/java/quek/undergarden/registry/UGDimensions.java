package quek.undergarden.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import quek.undergarden.Undergarden;

public class UGDimensions {

    public static final ResourceKey<Level> UNDERGARDEN_LEVEL = ResourceKey.create(Registry.DIMENSION_REGISTRY, name("undergarden"));
    public static final ResourceKey<Level> OTHERSIDE_LEVEL = ResourceKey.create(Registry.DIMENSION_REGISTRY, name("otherside"));

    private static ResourceLocation name(String name) {
        return new ResourceLocation(Undergarden.MODID, name);
    }
}