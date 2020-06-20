package quek.undergarden.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import quek.undergarden.UndergardenMod;

public class UndergardenFluidAttributes {

    public static final FluidAttributes.Builder virulent = FluidAttributes.builder(new ResourceLocation(UndergardenMod.MODID, "fluid/virulent_mix_still"), new ResourceLocation(UndergardenMod.MODID, "fluid/virulent_mix_flow"))
            .color(0xFF58195F)
            .viscosity(900)
            .overlay(new ResourceLocation("block/water_overlay"))
            ;
    public static final ForgeFlowingFluid.Properties virulent_properties = new ForgeFlowingFluid.Properties(() -> UndergardenFluids.virulent_source, () -> UndergardenFluids.virulent_flowing, virulent)
            .block(UndergardenBlocks.virulent_mix)
            .bucket(UndergardenItems.virulent_mix_bucket)
            .canMultiply()
            .explosionResistance(100)
            ;
}
