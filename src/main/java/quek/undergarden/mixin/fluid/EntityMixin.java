package quek.undergarden.mixin.fluid;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityFluidInteraction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quek.undergarden.registry.UGFluids;
import quek.undergarden.registry.UGTags;

@Mixin(Entity.class)
public abstract class EntityMixin {

	@Shadow
	@Final
	public EntityFluidInteraction fluidInteraction;

	@Shadow
	public abstract boolean isPushedByFluid();

	@Shadow
	public abstract void resetFallDistance();

	@Inject(method = "updateFluidInteraction", at = @At("HEAD"))
	public void addVirulentToInteraction(CallbackInfoReturnable<Boolean> cir) {
		if (!this.fluidInteraction.trackerByFluid.containsKey(UGTags.Fluids.VIRULENT)) {
			this.fluidInteraction.trackerByFluid.put(UGTags.Fluids.VIRULENT, new EntityFluidInteraction.Tracker());
		}
	}

	@Inject(method = "updateFluidInteraction", at = @At(value = "RETURN", shift = At.Shift.BEFORE), cancellable = true)
	public void applyVirulentLogic(CallbackInfoReturnable<Boolean> cir) {
		boolean inVirulent = this.fluidInteraction.isInFluid(UGTags.Fluids.VIRULENT);

		if (inVirulent) {
			if (this.isPushedByFluid()) {
				this.resetFallDistance();
				this.fluidInteraction.applyCurrentTo(UGTags.Fluids.VIRULENT, (Entity) (Object) this, UGFluids.VIRULENT_MIX_TYPE.get().motionScale((Entity) (Object) this));
			}
			cir.setReturnValue(true);
		}
	}
}
