package quek.undergarden.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.world.entity.player.Player;
import quek.undergarden.registry.UGSoundEvents;

public class UndergardenClient {
	public static void playPortalSound(Minecraft minecraft, Player player) {
		minecraft.getSoundManager().play(SimpleSoundInstance.forLocalAmbience(UGSoundEvents.UNDERGARDEN_PORTAL_TRAVEL.get(), player.getRandom().nextFloat() * 0.4F + 0.8F, 0.25F));
	}
}
