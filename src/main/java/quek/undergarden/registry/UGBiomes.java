package quek.undergarden.registry;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import quek.undergarden.UGMod;

public class UGBiomes {

    public static final RegistryKey<Biome> BARREN_ABYSS = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("barren_abyss"));
    public static final RegistryKey<Biome> DENSE_FOREST = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("dense_forest"));
    public static final RegistryKey<Biome> FORGOTTEN_FIELD = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("forgotten_field"));
    public static final RegistryKey<Biome> FROSTFIELDS = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("frostfields"));
    public static final RegistryKey<Biome> GRONGLEGROWTH = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("gronglegrowth"));
    public static final RegistryKey<Biome> MUSHROOM_BOG = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("mushroom_bog"));
    public static final RegistryKey<Biome> SMOGSTEM_FOREST = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("smogstem_forest"));
    public static final RegistryKey<Biome> SMOG_SPIRES = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("smog_spires"));
    public static final RegistryKey<Biome> WIGGLEWOOD_FOREST = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name("wigglewood_forest"));

    private static ResourceLocation name(String name) {
        return new ResourceLocation(UGMod.MODID, name);
    }
}