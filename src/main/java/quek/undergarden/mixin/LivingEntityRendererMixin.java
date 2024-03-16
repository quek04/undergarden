package quek.undergarden.mixin;

import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quek.undergarden.effect.ChillyEffect;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<T extends LivingEntity> {

	@Inject(method = "isShaking", at = @At("HEAD"), cancellable = true, remap = false)
	public void undergarden$shakeWhileChilly(T entity, CallbackInfoReturnable<Boolean> cir) {
		if (entity.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(ChillyEffect.MOVEMENT_SPEED_MODIFIER_UUID) != null) {
			cir.setReturnValue(true);
		}
	}
}
