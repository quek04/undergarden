package quek.undergarden.registry;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import quek.undergarden.UndergardenMod;

public class UndergardenDimensions {

    public static final RegistryKey<DimensionType> undergarden = RegistryKey.func_240903_a_(Registry.DIMENSION_TYPE_KEY, new ResourceLocation(UndergardenMod.MODID, "undergarden"));
    public static final RegistryKey<World> undergarden_w = RegistryKey.func_240903_a_(Registry.WORLD_KEY, new ResourceLocation(UndergardenMod.MODID, "undergarden"));

    public static final RegistryKey<DimensionType> otherside = RegistryKey.func_240903_a_(Registry.DIMENSION_TYPE_KEY, new ResourceLocation(UndergardenMod.MODID, "otherside"));
    public static final RegistryKey<World> otherside_w = RegistryKey.func_240903_a_(Registry.WORLD_KEY, new ResourceLocation(UndergardenMod.MODID, "otherside"));

    /* WHAT DO I DO WITH THIS
    public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = DeferredRegister.create(ForgeRegistries.MOD_DIMENSIONS, UndergardenMod.MODID);

    public static DimensionType undergarden_dimension;
    public static DimensionType otherside_dimension;

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
    */

}

