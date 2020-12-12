package quek.undergarden.registry;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UGMod;
import quek.undergarden.world.gen.surfacebuilders.EldergardenSurfaceBuilder;

public class UGSurfaceBuilders {

    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, UGMod.MODID);

    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> ELDERGARDEN = SURFACE_BUILDERS.register("eldergarden", () -> new EldergardenSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
}