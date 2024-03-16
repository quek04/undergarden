package quek.undergarden.registry;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import quek.undergarden.Undergarden;
import quek.undergarden.block.fluid.VirulentMixFluid;

import java.util.function.Consumer;

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
					.sound(SoundActions.BUCKET_FILL, UGSoundEvents.BUCKET_FILL_VIRULENT.get())) {
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
							return new ResourceLocation(Undergarden.MODID, "fluid/virulent_mix_flow");
						}

						@Override
						public @NotNull Vector3f modifyFogColor(Camera camera, float partialTicks, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
							return new Vector3f(57 / 255F, 25 / 255F, 80 / 255F);
						}

						@Override
						public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTicks, float nearDistance, float farDistance, FogShape shape) {
							RenderSystem.setShaderFogStart(0.0F);
							RenderSystem.setShaderFogEnd(3.0F);
						}
					});
				}
			}
	);
	public static final DeferredHolder<Fluid, VirulentMixFluid.Source> VIRULENT_MIX_SOURCE = FLUIDS.register("virulent_mix_source", () -> new VirulentMixFluid.Source(UGFluids.VIRULENT_MIX_PROPERTIES));
	public static final DeferredHolder<Fluid, VirulentMixFluid.Flowing> VIRULENT_MIX_FLOWING = FLUIDS.register("virulent_mix_flowing", () -> new VirulentMixFluid.Flowing(UGFluids.VIRULENT_MIX_PROPERTIES));

	public static final BaseFlowingFluid.Properties VIRULENT_MIX_PROPERTIES = new BaseFlowingFluid.Properties(VIRULENT_MIX_TYPE, VIRULENT_MIX_SOURCE, VIRULENT_MIX_FLOWING).bucket(UGItems.VIRULENT_MIX_BUCKET).block(UGBlocks.VIRULENT_MIX);
}