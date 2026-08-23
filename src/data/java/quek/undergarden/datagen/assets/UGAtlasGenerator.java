package quek.undergarden.datagen.assets;

import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.AtlasIds;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.data.SpriteSourceProvider;
import quek.undergarden.Undergarden;

import java.util.concurrent.CompletableFuture;

public class UGAtlasGenerator extends SpriteSourceProvider {
	public UGAtlasGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider, Undergarden.MODID);
	}

	@Override
	protected void gather() {
		this.atlas(AtlasIds.CELESTIALS).addSource(new SingleFile(Undergarden.prefix("environment/celestial/otherside_vortex")));
	}
}