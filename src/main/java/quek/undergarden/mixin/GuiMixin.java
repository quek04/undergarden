package quek.undergarden.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.PlayerRideableJumping;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import quek.undergarden.entity.animal.dweller.Dweller;

@Mixin(Gui.class)
public class GuiMixin {

	@Nullable
	@WrapOperation(method = "nextContextualInfoState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;jumpableVehicle()Lnet/minecraft/world/entity/PlayerRideableJumping;"))
	public PlayerRideableJumping meow(LocalPlayer instance, Operation<PlayerRideableJumping> original) {
		if (instance.jumpableVehicle() instanceof Dweller) {
			return null;
		}
		return original.call(instance);
	}
}
