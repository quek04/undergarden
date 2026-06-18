package quek.undergarden.mixin;

import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quek.undergarden.client.UndergardenClient;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<S extends LivingEntityRenderState> {

	@Inject(method = "isShaking", at = @At("HEAD"), cancellable = true, remap = false)
	public void undergarden$shakeWhileChilly(S state, CallbackInfoReturnable<Boolean> cir) {
		if (state.getRenderDataOrDefault(UndergardenClient.CHILLY, false)) {
			cir.setReturnValue(true);
		}
	}
}
