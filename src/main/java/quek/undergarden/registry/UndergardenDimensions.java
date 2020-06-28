package quek.undergarden.registry;

import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.world.OthersideDimension;
import quek.undergarden.world.UndergardenDimension;
import quek.undergarden.world.gen.UndergardenChunkGenerator;
import quek.undergarden.world.gen.UndergardenGenerationSettings;

import java.util.function.BiFunction;

public class UndergardenDimensions {

    public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = DeferredRegister.create(ForgeRegistries.MOD_DIMENSIONS, UndergardenMod.MODID);

    public static final RegistryObject<ModDimension> UNDERGARDEN = MOD_DIMENSIONS.register("undergarden", () -> new ModDimension() {
        @Override
        public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
            return UndergardenDimension::new;
        }
    });

    public static final RegistryObject<ModDimension> OTHERSIDE = MOD_DIMENSIONS.register("otherside", () -> new ModDimension() {
        @Override
        public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
            return OthersideDimension::new;
        }
    });

}
