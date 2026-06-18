package quek.undergarden.datagen.data.loot;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import quek.undergarden.block.UnderbeanBushBlock;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGBuiltinLootTables;
import quek.undergarden.registry.UGItems;

import java.util.function.BiConsumer;

public record UGBlockInteractLootTables(HolderLookup.Provider registries) implements LootTableSubProvider {

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
		output.accept(UGBuiltinLootTables.HARVEST_UNDERBEANS, LootTable.lootTable()
			.withPool(LootPool.lootPool().add(
				LootItem.lootTableItem(UGItems.UNDERBEANS)
					.apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
					.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(UGBlocks.UNDERBEAN_BUSH.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(UnderbeanBushBlock.AGE, 3)))))
			.withPool(LootPool.lootPool().add(LootItem.lootTableItem(UGItems.UNDERBEANS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))));

		output.accept(UGBuiltinLootTables.CARVE_GLOOMGOURD, LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(UGItems.GLOOMGOURD_SEEDS).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))))));

		output.accept(UGBuiltinLootTables.HARVEST_DROOPVINE, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(UGItems.DROOPFRUIT))));
	}
}
