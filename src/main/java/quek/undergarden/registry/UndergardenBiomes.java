package quek.undergarden.registry;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.biome.*;

public class UndergardenBiomes {

    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, UndergardenMod.MODID);

    public static final RegistryObject<Biome> FORGOTTEN_FIELD = BIOMES.register("forgotten_field", ForgottenFieldBiome::new);
    public static final RegistryObject<Biome> SMOGSTEM_FOREST = BIOMES.register("smogstem_forest", SmogstemForestBiome::new);
    public static final RegistryObject<Biome> BARREN_ABYSS = BIOMES.register("barren_abyss", BarrenAbyssBiome::new);

    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(FORGOTTEN_FIELD.get(), BiomeDictionary.Type.PLAINS);
        BiomeDictionary.addTypes(SMOGSTEM_FOREST.get(), BiomeDictionary.Type.FOREST);
        BiomeDictionary.addTypes(BARREN_ABYSS.get(), BiomeDictionary.Type.DEAD);
    }

    public static void addBiomeFeatures() {
        for(Biome biome : ForgeRegistries.BIOMES.getValues()) {
            if (biome instanceof UndergardenBiome) {
                ((UndergardenBiome)biome).addFeatures();
            }
        }
    }
}
