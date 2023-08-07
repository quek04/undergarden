package quek.undergarden.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quek.undergarden.registry.UGDimensions;
import quek.undergarden.registry.UGFluids;
import quek.undergarden.registry.UGParticleTypes;

import java.util.Optional;

@Mixin(PointedDripstoneBlock.class)
public abstract class PointedDripstoneMixin {

	@Shadow
	@Nullable
	private static BlockPos findTip(BlockState state, LevelAccessor accessor, BlockPos pos, int maxIterations, boolean isMergedTip) {
		return null;
	}

	@Shadow
	@Nullable
	private static BlockPos findFillableCauldronBelowStalactiteTip(Level level, BlockPos pos, Fluid fluid) {
		return null;
	}

	@Shadow
	private static Optional<PointedDripstoneBlock.FluidInfo> getFluidAboveStalactite(Level level, BlockPos pos, BlockState state) {
		return Optional.empty();
	}

	@Inject(method = "getDripFluid", at = @At(value = "RETURN", ordinal = 0, shift = At.Shift.BEFORE), cancellable = true)
	private static void undergarden$dripVirulentInUndergarden(Level level, Fluid fluid, CallbackInfoReturnable<Fluid> cir) {
		if (level.dimension().equals(UGDimensions.UNDERGARDEN_LEVEL)) {
			cir.setReturnValue(UGFluids.VIRULENT_MIX_SOURCE.get());
		}
	}

	@Inject(method = "canFillCauldron", at = @At("HEAD"), cancellable = true)
	private static void undergarden$allowVirulentDrippingIntoCauldron(Fluid fluid, CallbackInfoReturnable<Boolean> cir) {
		if (fluid == UGFluids.VIRULENT_MIX_SOURCE.get()) cir.setReturnValue(true);
	}

	@ModifyVariable(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", shift = At.Shift.BY, by = -10), index = 14, method = "spawnDripParticle(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/material/Fluid;)V")
	private static ParticleOptions undergarden$spawnProperDripParticle(ParticleOptions particle, Level level, BlockPos pos, BlockState state, Fluid fluid) {
		return fluid.isSame(UGFluids.VIRULENT_MIX_SOURCE.get()) ? UGParticleTypes.DRIPPING_VIRULENT.get() : particle;
	}

	@Inject(method = "maybeTransferFluid", at = @At(value = "INVOKE", target = "Ljava/util/Optional;isEmpty()Z", shift = At.Shift.BEFORE))
	private static void undergarden$dripVirulentMixIntoCauldron(BlockState state, ServerLevel level, BlockPos pos, float chance, CallbackInfo ci) {
		if (getFluidAboveStalactite(level, pos, state).isPresent()) {
			Fluid fluid = getFluidAboveStalactite(level, pos, state).get().fluid();
			float f = 0.05859375F;

			if (fluid.isSame(UGFluids.VIRULENT_MIX_SOURCE.get()) && !(chance >= f)) {
				BlockPos blockpos = findTip(state, level, pos, 11, false);
				if (blockpos != null) {
					BlockPos blockpos1 = findFillableCauldronBelowStalactiteTip(level, blockpos, fluid);
					if (blockpos1 != null) {
						level.levelEvent(1504, blockpos, 0);
						int i = blockpos.getY() - blockpos1.getY();
						int j = 50 + i;
						BlockState blockstate = level.getBlockState(blockpos1);
						level.scheduleTick(blockpos1, blockstate.getBlock(), j);
					}
				}
			}
		}
	}
}
