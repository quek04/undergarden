package quek.undergarden.registry;

import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import quek.undergarden.Undergarden;

import java.util.Collections;
import java.util.Set;

public class UGBuiltinLootTables {

	private static final Set<ResourceKey<LootTable>> BUILTIN_LOOT_TABLES = Sets.newHashSet();

	public static final ResourceKey<LootTable> CARVE_GLOOMGOURD = register("carve/gloomgourd");
	public static final ResourceKey<LootTable> HARVEST_DROOPVINE = register("harvest/droopvine");
	public static final ResourceKey<LootTable> HARVEST_UNDERBEANS = register("harvest/underbeans");

	public static final ResourceKey<LootTable> SHEAR_MOG = register("shearing/mog");
	public static final ResourceKey<LootTable> SHEAR_SMOG_MOG = register("shearing/smog_mog");

	public static final ResourceKey<LootTable> UG_BONUS_CHEST = register("chests/undergarden_bonus_chest");

	private static ResourceKey<LootTable> register(String id) {
		return register(ResourceKey.create(Registries.LOOT_TABLE, Undergarden.prefix(id)));
	}

	private static ResourceKey<LootTable> register(ResourceKey<LootTable> id) {
		if (BUILTIN_LOOT_TABLES.add(id)) {
			return id;
		} else {
			throw new IllegalArgumentException(id + " is already a registered built-in loot table");
		}
	}

	public static Set<ResourceKey<LootTable>> getBuiltinLootTables() {
		return Collections.unmodifiableSet(BUILTIN_LOOT_TABLES);
	}
}
