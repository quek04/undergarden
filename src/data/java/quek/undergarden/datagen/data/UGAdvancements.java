package quek.undergarden.datagen.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UGAdvancements extends AdvancementProvider {

	public UGAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		//TODO: new Otherside Advancements
		super(output, provider, List.of(new UndergardenAdvancements()));
	}
}