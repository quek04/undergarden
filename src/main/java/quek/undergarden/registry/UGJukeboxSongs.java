package quek.undergarden.registry;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.neoforge.registries.DeferredHolder;
import quek.undergarden.Undergarden;

public class UGJukeboxSongs {

	public static final ResourceKey<JukeboxSong> MAMMOTH = create("mammoth");
	public static final ResourceKey<JukeboxSong> LIMAX_MAXIMUS = create("limax_maximus");
	public static final ResourceKey<JukeboxSong> RELICT = create("relict");
	public static final ResourceKey<JukeboxSong> GLOOMPER_ANTHEM = create("gloomper_anthem");
	public static final ResourceKey<JukeboxSong> GLOOMPER_SECRET = create("gloomper_secret");

	private static ResourceKey<JukeboxSong> create(String name) {
		return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, name));
	}

	private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, DeferredHolder<SoundEvent, SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
		context.register(key, new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", key.location())), (float)lengthInSeconds, comparatorOutput));
	}

	public static void bootstrap(BootstrapContext<JukeboxSong> context) {
		register(context, MAMMOTH, UGSoundEvents.MAMMOTH_DISC, 193, 0);
		register(context, LIMAX_MAXIMUS, UGSoundEvents.LIMAX_MAXIMUS_DISC, 161, 1);
		register(context, RELICT, UGSoundEvents.RELICT_DISC, 187, 2);
		register(context, GLOOMPER_ANTHEM, UGSoundEvents.GLOOMPER_ANTHEM_DISC, 204, 3);
		register(context, GLOOMPER_SECRET, UGSoundEvents.GLOOMPER_SECRET_DISC, 159, 15);
	}
}
