package quek.undergarden.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGEnchantments;
import quek.undergarden.registry.UGTags;

import java.util.concurrent.CompletableFuture;

public class UGEnchantmentTags extends EnchantmentTagsProvider {

	public UGEnchantmentTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
		super(packOutput, provider, Undergarden.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(EnchantmentTags.NON_TREASURE).add(UGEnchantments.SELF_SLING, UGEnchantments.RICOCHET, UGEnchantments.RICOCHET);

		tag(UGTags.Enchantments.SLINGSHOT_EXCLUSIVE).add(UGEnchantments.SELF_SLING, UGEnchantments.RICOCHET);
	}
}
