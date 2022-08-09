package quek.undergarden.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;

import java.util.function.Consumer;

public class UGFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Undergarden.MODID);
    public static final DeferredRegister<FluidType> TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Undergarden.MODID);

    public static final RegistryObject<FluidType> VIRULENT_MIX_TYPE = TYPES.register("virulent_mix", () -> new FluidType(FluidType.Properties.create().lightLevel(10).density(1500).temperature(600).viscosity(3000).canExtinguish(true)) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        @Override
                        public ResourceLocation getStillTexture() {
                            return new ResourceLocation(Undergarden.MODID, "fluid/virulent_mix_still");
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return new ResourceLocation(Undergarden.MODID, "fluid/virulent_mix_flow");
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return new ResourceLocation("block/water_overlay");
                        }
                    });
                }
        }
    );
    public static final RegistryObject<FlowingFluid> VIRULENT_MIX_SOURCE = FLUIDS.register("virulent_mix_source", () -> new ForgeFlowingFluid.Source(UGFluids.VIRULENT_MIX_PROPERTIES));
    public static final RegistryObject<FlowingFluid> VIRULENT_MIX_FLOWING = FLUIDS.register("virulent_mix_flowing", () -> new ForgeFlowingFluid.Flowing(UGFluids.VIRULENT_MIX_PROPERTIES));

    public static final ForgeFlowingFluid.Properties VIRULENT_MIX_PROPERTIES = new ForgeFlowingFluid.Properties(VIRULENT_MIX_TYPE, VIRULENT_MIX_SOURCE, VIRULENT_MIX_FLOWING).bucket(UGItems.VIRULENT_MIX_BUCKET).block(UGBlocks.VIRULENT_MIX);
}