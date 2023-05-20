package quek.undergarden.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UGAdvancements extends ForgeAdvancementProvider {

	public UGAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper fileHelper) {
		//TODO: new Otherside Advancements
		super(output, future, fileHelper, List.of(new UndergardenAdvancements()));
	}
}