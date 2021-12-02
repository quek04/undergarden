package quek.undergarden.registry;

import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;

public class UGBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Undergarden.MODID);

    public static final ResourceKey<Biome> BARREN_ABYSS = register("barren_abyss");
    public static final ResourceKey<Biome> DENSE_FOREST = register("dense_forest");
    public static final ResourceKey<Biome> FORGOTTEN_FIELD = register("forgotten_field");
    public static final ResourceKey<Biome> FROSTFIELDS = register("frostfields");
    public static final ResourceKey<Biome> GRONGLEGROWTH = register("gronglegrowth");
    public static final ResourceKey<Biome> MUSHROOM_BOG = register("mushroom_bog");
    public static final ResourceKey<Biome> SMOGSTEM_FOREST = register("smogstem_forest");
    public static final ResourceKey<Biome> SMOG_SPIRES = register("smog_spires");
    public static final ResourceKey<Biome> WIGGLEWOOD_FOREST = register("wigglewood_forest");

    public static void toDictionary() {
        BiomeDictionary.addTypes(BARREN_ABYSS, BiomeDictionary.Type.COLD, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.WASTELAND);
        BiomeDictionary.addTypes(DENSE_FOREST, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.WET, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.FOREST);
        BiomeDictionary.addTypes(FORGOTTEN_FIELD, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WET, BiomeDictionary.Type.PLAINS);
        BiomeDictionary.addTypes(FROSTFIELDS, BiomeDictionary.Type.COLD, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.WET, BiomeDictionary.Type.PLAINS);
        BiomeDictionary.addTypes(GRONGLEGROWTH, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.WET, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.MUSHROOM, BiomeDictionary.Type.FOREST);
        BiomeDictionary.addTypes(MUSHROOM_BOG, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.WET, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.MUSHROOM, BiomeDictionary.Type.SWAMP);
        BiomeDictionary.addTypes(SMOGSTEM_FOREST, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WET, BiomeDictionary.Type.FOREST);
        BiomeDictionary.addTypes(SMOG_SPIRES, BiomeDictionary.Type.HOT, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SPOOKY);
        BiomeDictionary.addTypes(WIGGLEWOOD_FOREST, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WET, BiomeDictionary.Type.FOREST);
    }

    private static ResourceLocation name(String name) {
        return new ResourceLocation(Undergarden.MODID, name);
    }

    private static ResourceKey<Biome> register(String name) {
        BIOMES.register(name, OverworldBiomes::theVoid);
        return ResourceKey.create(Registry.BIOME_REGISTRY, name(name));
    }
}