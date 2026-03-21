package quek.undergarden.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.RegistryAccess;
import net.minecraft.util.context.ContextKey;
import org.jspecify.annotations.Nullable;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGSoundEvents;

public class UndergardenClient {

	public static final ContextKey<Double> UTHERIUM_INFECTION = new ContextKey<>(Undergarden.prefix("utherium_infection"));

	public static void playPortalSound() {
		Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forLocalAmbience(UGSoundEvents.UNDERGARDEN_PORTAL_TRAVEL.get(), 1.0F, 1.0F));
	}

	@Nullable
	public static RegistryAccess registryAccess() {
		if (Minecraft.getInstance().level != null) {
			return Minecraft.getInstance().level.registryAccess();
		}
		return null;
	}
}
