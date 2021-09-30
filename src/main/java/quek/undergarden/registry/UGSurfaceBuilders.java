package quek.undergarden.registry;

import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.surfacebuilders.FrostfieldsSurfaceBuilder;

public class UGSurfaceBuilders {

    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, Undergarden.MODID);

    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderBaseConfiguration>> FROSTFIELDS = SURFACE_BUILDERS.register("frostfields", () -> new FrostfieldsSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC));
}