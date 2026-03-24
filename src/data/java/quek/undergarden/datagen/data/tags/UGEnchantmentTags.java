package quek.undergarden.datagen.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGEnchantments;
import quek.undergarden.registry.UGTags;

import java.util.concurrent.CompletableFuture;

public class UGEnchantmentTags extends EnchantmentTagsProvider {

	public UGEnchantmentTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
		super(packOutput, provider, Undergarden.MODID);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(EnchantmentTags.NON_TREASURE).add(UGEnchantments.SELF_SLING, UGEnchantments.RICOCHET, UGEnchantments.RICOCHET);
		this.tag(UGTags.Enchantments.SLINGSHOT_EXCLUSIVE).add(UGEnchantments.SELF_SLING, UGEnchantments.RICOCHET);
	}
}
