package quek.undergarden.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import quek.undergarden.UGMod;

import java.awt.*;

public class UGFluidAttributes {

    public static final FluidAttributes.Builder virulent = FluidAttributes.builder(new ResourceLocation(UGMod.MODID, "fluid/virulent_mix_still"), new ResourceLocation(UGMod.MODID, "fluid/virulent_mix_flow"))
            .color(new Color(255, 255, 255).getRGB())
            .viscosity(2000)
            .overlay(new ResourceLocation(UGMod.MODID,"fluid/virulent_mix_still"))

            ;
    public static final ForgeFlowingFluid.Properties virulent_properties = new ForgeFlowingFluid.Properties(() -> UGFluids.virulent_source, () -> UGFluids.virulent_flowing, virulent)
            .block(UGBlocks.virulent_mix)
            .bucket(UGItems.virulent_mix_bucket)
            .canMultiply()
            .explosionResistance(100)
            ;
}
