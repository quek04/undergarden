package quek.undergarden.datagen.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.tags.TimelineTags;
import net.minecraft.world.timeline.Timeline;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGTags;

import java.util.concurrent.CompletableFuture;

public class UGTimelineTags extends KeyTagProvider<Timeline> {

	public UGTimelineTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
		super(output, Registries.TIMELINE, future, Undergarden.MODID);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(UGTags.Timelines.IN_UNDERGARDEN).addTag(TimelineTags.UNIVERSAL);
	}
}
