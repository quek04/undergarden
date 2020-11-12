package quek.undergarden.registry;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UGMod;

public class UGFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, UGMod.MODID);

    public static final RegistryObject<FlowingFluid> virulent_mix_source = FLUIDS.register("virulent_mix_source", () -> new ForgeFlowingFluid.Source(UGFluids.virulent_mix_properties));
    public static final RegistryObject<FlowingFluid> virulent_mix_flowing = FLUIDS.register("virulent_mix_flowing", () -> new ForgeFlowingFluid.Flowing(UGFluids.virulent_mix_properties));

    public static final ForgeFlowingFluid.Properties virulent_mix_properties = new ForgeFlowingFluid.Properties(virulent_mix_source, virulent_mix_flowing, FluidAttributes.builder(new ResourceLocation(UGMod.MODID, "fluid/virulent_mix_still"), new ResourceLocation(UGMod.MODID, "fluid/virulent_mix_flow"))).bucket(UGItems.virulent_mix_bucket).block(UGBlocks.virulent_mix);
}
