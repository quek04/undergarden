package quek.undergarden.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.HashMap;
import java.util.Set;
import java.util.function.Supplier;

public abstract class UGBlockLootTableProvider extends BlockLootSubProvider {

	protected UGBlockLootTableProvider(HolderLookup.Provider provider) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), new HashMap<>(), provider);
	}

	public void dropSelf(Supplier<? extends Block> block) {
		this.dropSelf(block.get());
	}

	public void slab(Supplier<? extends SlabBlock> slab) {
		this.add(slab.get(), this::createSlabItemTable);
	}

	public void dropOther(Supplier<? extends Block> brokenBlock, ItemLike droppedBlock) {
		this.dropOther(brokenBlock.get(), droppedBlock);
	}

	public void dropAsSilk(Supplier<? extends Block> block) {
		this.dropWhenSilkTouch(block.get());
	}

	public void dropWithSilk(Supplier<? extends Block> block, Supplier<? extends ItemLike> drop) {
		this.add(block.get(), (result) -> createSingleItemTableWithSilkTouch(result, drop.get()));
	}

	public void ore(Supplier<? extends Block> block, Supplier<? extends Item> drop) {
		this.add(block.get(), (result) -> createOreDrop(result, drop.get()));
	}

	public void ore(Supplier<? extends Block> block, Item drop) {
		this.add(block.get(), (result) -> createOreDrop(result, drop));
	}

	public void nuggetOre(Supplier<? extends Block> block, Item drop) {
		HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		this.add(block.get(), (ore) -> createSilkTouchDispatchTable(ore, applyExplosionDecay(ore, LootItem.lootTableItem(drop).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))).apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))));
	}
}