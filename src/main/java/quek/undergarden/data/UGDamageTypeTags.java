package quek.undergarden.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGDamageSources;

import java.util.concurrent.CompletableFuture;

public class UGDamageTypeTags extends TagsProvider<DamageType> {

	public UGDamageTypeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper helper) {
		super(output, Registries.DAMAGE_TYPE, future, Undergarden.MODID, helper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(DamageTypeTags.BYPASSES_ARMOR).add(UGDamageSources.SHARD_TORCH);
		this.tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(UGDamageSources.SHARD_TORCH);
		this.tag(DamageTypeTags.WITCH_RESISTANT_TO).add(UGDamageSources.SHARD_TORCH);
	}
}
