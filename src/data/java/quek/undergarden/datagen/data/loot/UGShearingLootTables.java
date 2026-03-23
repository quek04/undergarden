package quek.undergarden.datagen.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import quek.undergarden.registry.UGBuiltinLootTables;
import quek.undergarden.registry.UGItems;

import java.util.function.BiConsumer;

public record UGShearingLootTables(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(UGBuiltinLootTables.SHEAR_MOG, LootTable.lootTable()
                .withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(UGItems.MOGMOSS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))))
        );
		output.accept(UGBuiltinLootTables.SHEAR_SMOG_MOG, LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.add(LootItem.lootTableItem(UGItems.BLUE_MOGMOSS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))))
		);
    }
}
