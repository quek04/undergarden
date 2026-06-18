package quek.undergarden.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.debug.EntityHitboxDebugRenderer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import quek.undergarden.entity.animal.MysteriousPot;

@Mixin(EntityHitboxDebugRenderer.class)
public class EntityRenderDispatcherMixin {

	@WrapOperation(method = "emitGizmos", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isInvisible()Z"))
	public boolean neverShowInactivePotHitbox(Entity instance, Operation<Boolean> original) {
		if (instance instanceof MysteriousPot pot && !pot.isActive()) return true;
		return original.call(instance);
	}
}
