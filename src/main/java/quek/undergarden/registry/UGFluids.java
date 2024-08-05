package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.block.fluid.VirulentMixFluid;

public class UGFluids {

	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, Undergarden.MODID);
	public static final DeferredRegister<FluidType> TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, Undergarden.MODID);

	public static final DeferredHolder<FluidType, FluidType> VIRULENT_MIX_TYPE = TYPES.register("virulent_mix", () -> new FluidType(FluidType.Properties.create()
					.descriptionId("block.undergarden.virulent_mix")
					.motionScale(0.00116666666)
					.canExtinguish(true)
					.supportsBoating(true)
					.lightLevel(10)
					.density(1500)
					.temperature(600)
					.viscosity(1000)
					.sound(SoundActions.BUCKET_EMPTY, UGSoundEvents.BUCKET_EMPTY_VIRULENT.get())
					.sound(SoundActions.BUCKET_FILL, UGSoundEvents.BUCKET_FILL_VIRULENT.get())
					.addDripstoneDripping(PointedDripstoneBlock.LAVA_TRANSFER_PROBABILITY_PER_RANDOM_TICK, UGParticleTypes.DRIPPING_VIRULENT.get(), UGBlocks.VIRULENT_MIX_CAULDRON.get(), SoundEvents.POINTED_DRIPSTONE_DRIP_LAVA_INTO_CAULDRON))
	);
	public static final DeferredHolder<Fluid, VirulentMixFluid.Source> VIRULENT_MIX_SOURCE = FLUIDS.register("virulent_mix_source", () -> new VirulentMixFluid.Source(UGFluids.VIRULENT_MIX_PROPERTIES));
	public static final DeferredHolder<Fluid, VirulentMixFluid.Flowing> VIRULENT_MIX_FLOWING = FLUIDS.register("virulent_mix_flowing", () -> new VirulentMixFluid.Flowing(UGFluids.VIRULENT_MIX_PROPERTIES));

	public static final BaseFlowingFluid.Properties VIRULENT_MIX_PROPERTIES = new BaseFlowingFluid.Properties(VIRULENT_MIX_TYPE, VIRULENT_MIX_SOURCE, VIRULENT_MIX_FLOWING).bucket(UGItems.VIRULENT_MIX_BUCKET).block(UGBlocks.VIRULENT_MIX);
}