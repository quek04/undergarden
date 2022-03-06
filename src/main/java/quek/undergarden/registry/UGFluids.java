package quek.undergarden.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.block.fluid.VirulentMixFluid;

public class UGFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Undergarden.MODID);

    public static final RegistryObject<FlowingFluid> VIRULENT_MIX_SOURCE = FLUIDS.register("virulent_mix_source", () -> new VirulentMixFluid.Source(UGFluids.VIRULENT_MIX_PROPERTIES));
    public static final RegistryObject<FlowingFluid> VIRULENT_MIX_FLOWING = FLUIDS.register("virulent_mix_flowing", () -> new VirulentMixFluid.Flowing(UGFluids.VIRULENT_MIX_PROPERTIES));

    public static final VirulentMixFluid.Properties VIRULENT_MIX_PROPERTIES = new VirulentMixFluid.Properties(VIRULENT_MIX_SOURCE, VIRULENT_MIX_FLOWING, FluidAttributes.builder(new ResourceLocation(Undergarden.MODID, "fluid/virulent_mix_still"), new ResourceLocation(Undergarden.MODID, "fluid/virulent_mix_flow")).luminosity(10)).bucket(UGItems.VIRULENT_MIX_BUCKET).block(UGBlocks.VIRULENT_MIX);
}