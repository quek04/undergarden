package quek.undergarden.registry;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.world.*;

import java.util.function.BiFunction;

import static quek.undergarden.Undergarden.MODID;

public class UndergardenDimensions {

    public static final DeferredRegister<BiomeProviderType<?,?>> BIOME_PROVIDER_TYPES = new DeferredRegister<>(ForgeRegistries.BIOME_PROVIDER_TYPES, MODID);
    public static final DeferredRegister<ChunkGeneratorType<?,?>> CHUNK_GENERATOR_TYPES = new DeferredRegister<>(ForgeRegistries.CHUNK_GENERATOR_TYPES, MODID);
    public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, MODID);

    public static final RegistryObject<BiomeProviderType<UndergardenBiomeProviderSettings, UndergardenBiomeProvider>> UNDERGARDEN_BIOMES = BIOME_PROVIDER_TYPES.register(
            "undergarden_biomes", () -> new BiomeProviderType<>(UndergardenBiomeProvider::new, UndergardenBiomeProviderSettings::new));

    public static final RegistryObject<ChunkGeneratorType<UndergardenGenerationSettings, UndergardenChunkGenerator>> UNDERGARDEN_GEN = CHUNK_GENERATOR_TYPES.register(
            "undergarden_gen", () -> new ChunkGeneratorType<>(UndergardenChunkGenerator::new, true, UndergardenGenerationSettings::new));

    public static final RegistryObject<ModDimension> UNDERGARDEN_DIMENSION = MOD_DIMENSIONS.register("undergarden_dimension", () -> new ModDimension() {
        @Override
        public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
            return UndergardenDimension::new;
        }
            }
    );


}
