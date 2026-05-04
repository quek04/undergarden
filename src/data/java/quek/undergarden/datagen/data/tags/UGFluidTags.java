package quek.undergarden.datagen.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGFluids;
import quek.undergarden.registry.UGTags;

import java.util.concurrent.CompletableFuture;

public class UGFluidTags extends FluidTagsProvider {

	public UGFluidTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
		super(output, future, Undergarden.MODID);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(UGTags.Fluids.VIRULENT).add(UGFluids.VIRULENT_MIX_SOURCE.get(), UGFluids.VIRULENT_MIX_FLOWING.get());
		tag(UGTags.Fluids.SUPPORTS_THORNREED_ADJACENTLY).addTag(FluidTags.WATER);
	}
}
