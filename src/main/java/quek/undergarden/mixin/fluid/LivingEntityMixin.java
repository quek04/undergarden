package quek.undergarden.mixin.fluid;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.extensions.ILivingEntityExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quek.undergarden.registry.UGFluids;
import quek.undergarden.registry.UGTags;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ILivingEntityExtension {

	@Shadow
	private int noJumpDelay;

	@Shadow
	public abstract void jumpFromGround();

	public LivingEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getFluidJumpThreshold()D", shift = At.Shift.AFTER))
	public void floatInVirulent(CallbackInfo ci) {
		double fluidHeight = this.getFluidHeight(UGTags.Fluids.VIRULENT);
		double fluidJumpThreshold = this.getFluidJumpThreshold();
		if (!this.fluidInteraction.isInFluid(UGTags.Fluids.VIRULENT) || this.onGround() && !(fluidHeight > fluidJumpThreshold)) {
			if ((this.onGround() || this.fluidInteraction.isInFluid(UGTags.Fluids.VIRULENT) && fluidHeight <= fluidJumpThreshold) && this.noJumpDelay == 0) {
				this.jumpFromGround();
				this.noJumpDelay = 10;
			}
		} else {
			this.jumpInFluid(UGFluids.VIRULENT_MIX_TYPE.get());
		}
	}

	@WrapOperation(method = "shouldTravelInFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isInWater()Z"))
	public boolean travelInVirulent(LivingEntity instance, Operation<Boolean> original) {
		return original.call(instance) || this.fluidInteraction.isInFluid(UGTags.Fluids.VIRULENT);
	}

	@WrapOperation(method = "travelInLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getFluidHeight(Lnet/minecraft/tags/TagKey;)D"))
	public double travelInVirulent2(LivingEntity instance, TagKey<Fluid> tagKey, Operation<Double> original) {
		if (this.fluidInteraction.isInFluid(UGTags.Fluids.VIRULENT)) {
			return this.getFluidHeight(UGTags.Fluids.VIRULENT);
		}
		return original.call(instance, tagKey);
	}
}
