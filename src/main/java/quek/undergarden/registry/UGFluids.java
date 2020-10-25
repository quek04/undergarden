package quek.undergarden.registry;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UGMod;

public class UGFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, UGMod.MODID);

    public static final FlowingFluid virulent_source = new ForgeFlowingFluid.Source(UGFluidAttributes.virulent_properties);
    public static final FlowingFluid virulent_flowing = new ForgeFlowingFluid.Flowing(UGFluidAttributes.virulent_properties);

    public static final RegistryObject<FlowingFluid> virulent_mix_source = FLUIDS.register("virulent_mix_source", () -> virulent_source);
    public static final RegistryObject<FlowingFluid> virulent_mix_flowing = FLUIDS.register("virulent_mix_flowing", () -> virulent_flowing);



}
