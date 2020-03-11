package quek.undergarden.registry;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.biome.ForgottenFieldBiome;
import quek.undergarden.biome.UndergardenBiome;

import static quek.undergarden.Undergarden.MODID;

public class UndergardenBiomes {

    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, MODID);

    public static final RegistryObject<UndergardenBiome> forgotten_field = BIOMES.register("forgotten_field", ForgottenFieldBiome::new);

}
