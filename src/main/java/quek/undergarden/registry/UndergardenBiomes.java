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
    public static final RegistryObject<Biome> UTHERIC_PLAINS = BIOMES.register("utheric_plains", UthericPlainsBiome::new);

    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(FORGOTTEN_FIELD.get(), BiomeDictionary.Type.PLAINS);
        BiomeDictionary.addTypes(SMOGSTEM_FOREST.get(), BiomeDictionary.Type.FOREST);
    }

    public static void addBiomeFeatures() {
        for(Biome biome : ForgeRegistries.BIOMES.getValues()) {
            if (biome instanceof ForgottenFieldBiome) {
                ((ForgottenFieldBiome)biome).addFeatures();
            }
            if (biome instanceof SmogstemForestBiome) {
                ((SmogstemForestBiome)biome).addFeatures();
            }
            if (biome instanceof UthericPlainsBiome) {
                ((UthericPlainsBiome)biome).addFeatures();
            }
        }
    }
}
