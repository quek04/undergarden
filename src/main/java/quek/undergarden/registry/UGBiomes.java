package quek.undergarden.registry;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import quek.undergarden.UGMod;

public class UGBiomes {

    public static final RegistryKey<Biome> barren_abyss = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("barren_abyss"));
    public static final RegistryKey<Biome> dense_forest = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("dense_forest"));
    public static final RegistryKey<Biome> forgotten_field = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("forgotten_field"));
    public static final RegistryKey<Biome> gronglegrowth = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("gronglegrowth"));
    public static final RegistryKey<Biome> mushroom_bog = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("mushroom_bog"));
    public static final RegistryKey<Biome> smog_spires = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("smog_spires"));
    public static final RegistryKey<Biome> smogstem_forest = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("smogstem_forest"));
    public static final RegistryKey<Biome> wigglewood_forest = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("wigglewood_forest"));

    private static ResourceLocation name(String name) {
        return new ResourceLocation(UGMod.MODID, name);
    }
}
