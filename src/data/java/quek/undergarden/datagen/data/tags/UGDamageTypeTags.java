package quek.undergarden.datagen.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGDamageSources;

import java.util.concurrent.CompletableFuture;

public class UGDamageTypeTags extends KeyTagProvider<DamageType> {

	public UGDamageTypeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
		super(output, Registries.DAMAGE_TYPE, future, Undergarden.MODID);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(DamageTypeTags.BYPASSES_ARMOR).add(UGDamageSources.SHARD_TORCH, UGDamageSources.UTHERIC_INFECTION);
		this.tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(UGDamageSources.SHARD_TORCH, UGDamageSources.UTHERIC_INFECTION);
		this.tag(DamageTypeTags.WITCH_RESISTANT_TO).add(UGDamageSources.SHARD_TORCH);
		this.tag(DamageTypeTags.NO_KNOCKBACK).add(UGDamageSources.UTHERIC_INFECTION);
		this.tag(DamageTypeTags.BYPASSES_RESISTANCE).add(UGDamageSources.UTHERIC_INFECTION);
	}
}
