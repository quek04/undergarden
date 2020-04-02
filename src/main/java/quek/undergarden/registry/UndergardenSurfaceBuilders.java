package quek.undergarden.registry;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.world.DefaultUndergardenSurfaceBuilder;

import java.util.function.Supplier;

import static quek.undergarden.Undergarden.MODID;

public class UndergardenSurfaceBuilders {

    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = new DeferredRegister<>(ForgeRegistries.SURFACE_BUILDERS, MODID);

    //public static final SurfaceBuilderConfig default_config = new SurfaceBuilderConfig(UndergardenBlocks.deepturf.get().getDefaultState(), UndergardenBlocks.depthrock.get().getDefaultState(), UndergardenBlocks.deepsoil.get().getDefaultState());

    public static final LazyOptional<SurfaceBuilderConfig> default_config = LazyOptional.of(() -> new SurfaceBuilderConfig(UndergardenBlocks.deepturf.get().getDefaultState(), UndergardenBlocks.depthrock.get().getDefaultState(), UndergardenBlocks.deepsoil.get().getDefaultState()));

    public static final SurfaceBuilder<SurfaceBuilderConfig> surface = new DefaultUndergardenSurfaceBuilder(SurfaceBuilderConfig::deserialize);

    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> DEFAULT_UNDERGARDEN = SURFACE_BUILDERS.register(
            "default_undergarden", () -> surface);
}
