package quek.undergarden.datagen.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class UGEntityLootTables extends EntityLootSubProvider {

	public UGEntityLootTables(HolderLookup.Provider provider) {
		super(FeatureFlags.REGISTRY.allFlags(), provider);
	}

	@Override
	public void generate() {
		this.add(UGEntityTypes.ROTLING.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.UTHERIC_SHARD.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
				)
			)
		);
		this.add(UGEntityTypes.ROTWALKER.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.UTHERIC_SHARD.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
				)
			)
		);
		this.add(UGEntityTypes.ROTBEAST.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.UTHERIC_SHARD.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 8.0F)))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
				)
			)
		);
		this.add(UGEntityTypes.ROTBELCHER.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.UTHERIC_SHARD.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F)))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
				)
			)
		);
		this.add(UGEntityTypes.DWELLER.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(Items.LEATHER)
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
				)
			)
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.RAW_DWELLER_MEAT.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
					.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))
			)
		);
		this.add(UGEntityTypes.GREATER_DWELLER.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(Items.LEATHER)
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 4.0F)))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
				)
			)
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.RAW_DWELLER_MEAT.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F)))
					.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))
			)
		);
		this.add(UGEntityTypes.GWIBLING.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.RAW_GWIBLING.get())
					.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
				.when(LootItemKilledByPlayerCondition.killedByPlayer())
			)
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(Items.BONE_MEAL))
				.when(LootItemRandomChanceCondition.randomChance(0.05F))
			)
		);
		this.add(UGEntityTypes.BRUTE.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.BRUTE_TUSK.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
				)
			)
		);
		this.add(UGEntityTypes.SCINTLING.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.GOO_BALL.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 2.0F)))
				)
			)
		);
		this.add(UGEntityTypes.GLOOMPER.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(Items.LEATHER)
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
				)
			)
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.RAW_GLOOMPER_LEG.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
					.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
				)
			)
		);
		this.add(UGEntityTypes.STONEBORN.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.DEPTHROCK_PEBBLE.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(1.0F, 2.0F)))
				)
			)
		);
		this.add(UGEntityTypes.NARGOYLE.get(), LootTable.lootTable());
		this.add(UGEntityTypes.MUNCHER.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(0.0F, 3.0F))
				.add(LootItem.lootTableItem(UGItems.CLOGGRUM_NUGGET.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
				)
			)
			.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(0.0F, 1.0F))
				.add(LootItem.lootTableItem(UGItems.FROSTSTEEL_NUGGET.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
				)
			)
		);
		this.add(UGEntityTypes.SPLOOGIE.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.DEPTHROCK_PEBBLE.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(1.0F, 2.0F)))
				)
			)
		);
		this.add(UGEntityTypes.GWIB.get(), LootTable.lootTable());
		this.add(UGEntityTypes.MOG.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.DEPTHROCK_PEBBLE.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(1.0F, 2.0F)))
				)
			)
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.MOGMOSS.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
				)
			)
		);

		this.add(UGEntityTypes.SMOG_MOG.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.DEPTHROCK_PEBBLE.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(1.0F, 2.0F)))
				)
			)
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.BLUE_MOGMOSS.get())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
				)
			)
		);

		this.add(UGEntityTypes.FORGOTTEN.get(), LootTable.lootTable());

		this.add(UGEntityTypes.DENIZEN.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(UGItems.DENIZEN_MASK.get()))
				.when(LootItemKilledByPlayerCondition.killedByPlayer())
				.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.05F, 0.01F))
			)
		);

		this.add(UGEntityTypes.FORGOTTEN_GUARDIAN.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(UGItems.FORGOTTEN_NUGGET.get())
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 16.0F)))
				)
			)
		);

		this.add(UGEntityTypes.MYSTERIOUS_POT.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(1.0F, 3.0F))
				.add(LootItem.lootTableItem(UGItems.CLOGGRUM_NUGGET).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))))
//				.add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))))
//				.add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))))
				.add(LootItem.lootTableItem(UGItems.FROSTSTEEL_NUGGET).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))))
				.add(LootItem.lootTableItem(UGItems.REGALIUM_CRYSTAL).setWeight(25).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
				.add(LootItem.lootTableItem(UGItems.UTHERIC_SHARD).setWeight(25).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
				.add(LootItem.lootTableItem(Items.DIAMOND).setWeight(5))
				.add(LootItem.lootTableItem(UGItems.MAMMOTH_DISC.get()).setWeight(4))
				.add(LootItem.lootTableItem(UGItems.RELICT_DISC.get()).setWeight(4))
			)
		);

		this.add(UGEntityTypes.UNDERGAR.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(UGItems.RAW_UNDERGAR_FILLET)
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
					.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
					.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 2.0F)))
				)
			)
		);
	}

	@Override
	protected Stream<EntityType<?>> getKnownEntityTypes() {
		return UGEntityTypes.ENTITY_TYPES.getEntries().stream().map(Supplier::get);
	}
}
