package quek.undergarden.datagen.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import quek.undergarden.registry.UGBuiltinLootTables;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UGLootTables extends LootTableProvider {

	public UGLootTables(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, UGBuiltinLootTables.getBuiltinLootTables(), List.of(
			new SubProviderEntry(UGBlockInteractLootTables::new, LootContextParamSets.BLOCK_INTERACT),
			new SubProviderEntry(UGBlockLootTables::new, LootContextParamSets.BLOCK),
			new SubProviderEntry(UGChestLootTables::new, LootContextParamSets.CHEST),
			new SubProviderEntry(UGEntityLootTables::new, LootContextParamSets.ENTITY),
			new SubProviderEntry(UGShearingLootTables::new, LootContextParamSets.SHEARING)), provider);
	}
}