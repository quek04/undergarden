package quek.undergarden.registry;

import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;

public class UGBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Undergarden.MODID);

    public static final ResourceKey<Biome> ANCIENT_SEA = register("ancient_sea");
    public static final ResourceKey<Biome> BARREN_ABYSS = register("barren_abyss");
    public static final ResourceKey<Biome> DEAD_SEA = register("dead_sea");
    public static final ResourceKey<Biome> DENSE_FOREST = register("dense_forest");
    public static final ResourceKey<Biome> FORGOTTEN_FIELD = register("forgotten_field");
    public static final ResourceKey<Biome> FROSTFIELDS = register("frostfields");
    public static final ResourceKey<Biome> GRONGLEGROWTH = register("gronglegrowth");
    public static final ResourceKey<Biome> ICY_SEA = register("icy_sea");
    public static final ResourceKey<Biome> MUSHROOM_BOG = register("mushroom_bog");
    public static final ResourceKey<Biome> SMOGSTEM_FOREST = register("smogstem_forest");
    public static final ResourceKey<Biome> SMOG_SPIRES = register("smog_spires");
    public static final ResourceKey<Biome> WIGGLEWOOD_FOREST = register("wigglewood_forest");

    private static ResourceLocation name(String name) {
        return new ResourceLocation(Undergarden.MODID, name);
    }

    private static ResourceKey<Biome> register(String name) {
        BIOMES.register(name, OverworldBiomes::theVoid);
        return ResourceKey.create(Registry.BIOME_REGISTRY, name(name));
    }
}