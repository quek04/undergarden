package quek.undergarden.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.RegistryAccess;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.item.crafting.RecipeMap;
import org.jspecify.annotations.Nullable;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGSoundEvents;

public class UndergardenClient {

	public static final int DEFAULT_TINT_COLOR = -10783397;
	public static final int GLOOMGOURD_STEM_TINT = -13226686;

	public static final ContextKey<Float> UTHERIUM_INFECTION = new ContextKey<>(Undergarden.prefix("utherium_infection"));
	public static final ContextKey<Boolean> CHILLY = new ContextKey<>(Undergarden.prefix("chilly"));

	public static RecipeMap RECIPE_MAP = RecipeMap.EMPTY;

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