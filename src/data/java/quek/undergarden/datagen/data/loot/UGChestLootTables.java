package quek.undergarden.datagen.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGBuiltinLootTables;
import quek.undergarden.registry.UGItems;

import java.util.function.BiConsumer;

public record UGChestLootTables(HolderLookup.Provider registries) implements LootTableSubProvider {

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		consumer.accept(ResourceKey.create(Registries.LOOT_TABLE, Undergarden.prefix("chests/catacombs")), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(2.0F, 5.0F))
				.add(LootItem.lootTableItem(UGItems.CLOGGRUM_NUGGET.get()).setWeight(40).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
				.add(LootItem.lootTableItem(UGItems.FROSTSTEEL_NUGGET.get()).setWeight(40).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
				.add(LootItem.lootTableItem(UGItems.UTHERIC_SHARD.get()).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
				.add(LootItem.lootTableItem(UGItems.REGALIUM_CRYSTAL.get()).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
				.add(LootItem.lootTableItem(UGItems.SLOP_BOWL.get()).setWeight(40).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F)))))
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1.0F))
				.add(EmptyLootItem.emptyItem().setWeight(2))
				.add(LootItem.lootTableItem(UGItems.CLOGGRUM_SWORD.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(15.0F, 20.0F))).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.6F, 0.9F))))
				.add(LootItem.lootTableItem(UGItems.CLOGGRUM_AXE.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(15.0F, 20.0F))).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.6F, 0.9F))))
				.add(LootItem.lootTableItem(UGItems.CLOGGRUM_SWORD.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(25.0F, 30.0F)).withOptions(registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentTags.TREASURE))).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.2F, 0.5F))))
				.add(LootItem.lootTableItem(UGItems.CLOGGRUM_AXE.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(25.0F, 30.0F)).withOptions(registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentTags.TREASURE))).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.2F, 0.5F)))))
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1.0F))
				.add(EmptyLootItem.emptyItem().setWeight(8))
//				.add(LootItem.lootTableItem(UGItems.MAMMOTH_DISC.get()).setWeight(4))
//				.add(LootItem.lootTableItem(UGItems.RELICT_DISC.get()).setWeight(4))
				.add(LootItem.lootTableItem(UGItems.FORGOTTEN_UPGRADE_TEMPLATE.get()).setWeight(3))
				.add(LootItem.lootTableItem(UGItems.FORGOTTEN_NUGGET.get()).setWeight(1)))
		);
		consumer.accept(ResourceKey.create(Registries.LOOT_TABLE, Undergarden.prefix("chests/denizen_camp")), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(2.0F, 4.0F))
				.add(EmptyLootItem.emptyItem().setWeight(5))
				//.add(LootItem.lootTableItem(UGItems.ROGDORIUM.get()).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))))
				.add(LootItem.lootTableItem(UGItems.RAW_DWELLER_MEAT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
				.add(LootItem.lootTableItem(Items.LEATHER).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
				.add(LootItem.lootTableItem(Items.BONE).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
				.add(LootItem.lootTableItem(UGItems.DROOPFRUIT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F)))))
			.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(1.0F, 2.0F))
				.add(LootItem.lootTableItem(UGBlocks.DENIZEN_TOTEM.asItem()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
				.add(LootItem.lootTableItem(Items.STICK).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 16.0F))))
				.add(LootItem.lootTableItem(UGBlocks.ANCIENT_ROOT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 16.0F))))
				.add(LootItem.lootTableItem(UGBlocks.ANCIENT_ROOT_PLANKS).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 16.0F)))))
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1.0F))
				.add(EmptyLootItem.emptyItem().setWeight(10))
				.add(LootItem.lootTableItem(UGItems.DENIZEN_MASK).setWeight(3)))
		);
		consumer.accept(ResourceKey.create(Registries.LOOT_TABLE, Undergarden.prefix("chests/depleted_mine/mound")), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1.0F))
				.add(EmptyLootItem.emptyItem().setWeight(10))
				.add(LootItem.lootTableItem(UGItems.FORGOTTEN_PICKAXE).setWeight(2).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.5F, 0.85F)))))
			.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(2.0F, 3.0F))
				.add(LootItem.lootTableItem(Items.TORCH).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 16.0F))))
				.add(LootItem.lootTableItem(UGBlocks.SMOGSTEM_PLANKS).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 16.0F))))
				.add(LootItem.lootTableItem(Items.COAL).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
				.add(LootItem.lootTableItem(UGItems.RAW_CLOGGRUM).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 16.0F))))
				.add(LootItem.lootTableItem(UGItems.RAW_FROSTSTEEL).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 16.0F))))
				.add(LootItem.lootTableItem(UGItems.REGALIUM_CRYSTAL).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
				.add(LootItem.lootTableItem(UGItems.UTHERIC_CLUSTER).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))))
		);
		consumer.accept(ResourceKey.create(Registries.LOOT_TABLE, Undergarden.prefix("chests/depleted_mine/normal")), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(1.0F, 2.0F))
				.add(EmptyLootItem.emptyItem().setWeight(7))
				.add(LootItem.lootTableItem(UGItems.CLOGGRUM_PICKAXE).setWeight(5).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.25F, 0.95F))))
				.add(LootItem.lootTableItem(UGItems.CLOGGRUM_SHOVEL).setWeight(5).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.25F, 0.95F))))
			)
			.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(1.0F, 3.0F))
				.add(LootItem.lootTableItem(UGItems.DWELLER_STEAK).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))))
				.add(LootItem.lootTableItem(Items.TORCH).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
				.add(LootItem.lootTableItem(Items.STICK).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
				.add(LootItem.lootTableItem(UGBlocks.SMOGSTEM_PLANKS).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))))
				.add(LootItem.lootTableItem(Items.COAL).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 16.0F))))
				.add(LootItem.lootTableItem(UGItems.RAW_CLOGGRUM).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
				.add(LootItem.lootTableItem(UGItems.RAW_FROSTSTEEL).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
			)
		);
		consumer.accept(ResourceKey.create(Registries.LOOT_TABLE, Undergarden.prefix("pots/forgotten_vestige/house")), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(1.0F, 3.0F))
				.add(LootItem.lootTableItem(UGItems.DEPTHROCK_PEBBLE).setWeight(100).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 9.0F))))
				.add(LootItem.lootTableItem(UGItems.RAW_DWELLER_MEAT).setWeight(75).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
				.add(LootItem.lootTableItem(UGItems.RAW_GLOOMPER_LEG).setWeight(75).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
				.add(LootItem.lootTableItem(UGItems.RAW_GWIBLING).setWeight(75).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
				.add(LootItem.lootTableItem(UGItems.DROOPFRUIT).setWeight(75).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
				.add(LootItem.lootTableItem(UGItems.UNDERBEANS).setWeight(75).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
				.add(LootItem.lootTableItem(UGItems.GLOOMGOURD_SEEDS).setWeight(75).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
				.add(LootItem.lootTableItem(UGItems.CLOGGRUM_NUGGET).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))))
//				.add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))))
//				.add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))))
				.add(LootItem.lootTableItem(UGItems.FROSTSTEEL_NUGGET).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))))
				.add(LootItem.lootTableItem(UGItems.REGALIUM_CRYSTAL).setWeight(25).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
				.add(LootItem.lootTableItem(UGItems.UTHERIC_SHARD).setWeight(25).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
				.add(LootItem.lootTableItem(Items.DIAMOND).setWeight(5).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))))
		);

		consumer.accept(UGBuiltinLootTables.UG_BONUS_CHEST, LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(Items.STONE_AXE))
				.add(LootItem.lootTableItem(Items.WOODEN_AXE).setWeight(5)))
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(Items.STONE_PICKAXE))
				.add(LootItem.lootTableItem(Items.WOODEN_PICKAXE).setWeight(5)))
			.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(2.0F, 4.0F))
				.add(LootItem.lootTableItem(UGItems.UNDERBEANS).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 7.0F))))
				.add(LootItem.lootTableItem(UGItems.RAW_GWIBLING).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
				.add(LootItem.lootTableItem(UGItems.RAW_GLOOMPER_LEG).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
				.add(LootItem.lootTableItem(UGItems.RAW_UNDERGAR_FILLET).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
				.add(LootItem.lootTableItem(UGItems.SLOP_BOWL)))
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(4.0F))
				.add(LootItem.lootTableItem(Items.STICK).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 12.0F))))
				.add(LootItem.lootTableItem(UGItems.TWISTYTWIG).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 12.0F))))
				.add(LootItem.lootTableItem(UGBlocks.WIGGLEWOOD_PLANKS).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 12.0F))))
				.add(LootItem.lootTableItem(UGBlocks.SMOGSTEM_PLANKS).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 12.0F))))
				.add(LootItem.lootTableItem(UGBlocks.GRONGLE_PLANKS).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 12.0F))))
				.add(LootItem.lootTableItem(UGBlocks.WIGGLEWOOD_LOG).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
				.add(LootItem.lootTableItem(UGBlocks.SMOGSTEM_LOG).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
				.add(LootItem.lootTableItem(UGBlocks.GRONGLE_LOG).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))))
			//some more fun items as a treat :)
			.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(1.0F, 2.0F))
				.add(LootItem.lootTableItem(UGItems.CLOGGRUM_NUGGET).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))))
				.add(LootItem.lootTableItem(UGItems.DITCHBULB).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
				.add(LootItem.lootTableItem(UGItems.REGALIUM_CRYSTAL))
				.add(LootItem.lootTableItem(UGItems.ROTTEN_BLISTERBERRY))
				.add(LootItem.lootTableItem(UGItems.GOO_BALL))
				.add(LootItem.lootTableItem(UGItems.GRONGLET)))
		);
	}
}