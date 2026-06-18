package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.timeline.Timeline;
import quek.undergarden.UGRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.monster.stoneborn.trading.StonebornTrade;

public class UGTags {

	public static class Items {

		public static final TagKey<Item> SLINGSHOT_ENCHANTABLE = tag("enchantable/slingshot");

		public static final TagKey<Item> MUSHROOMS = tag("mushrooms");
		public static final TagKey<Item> CLOGGRUM_ITEMS = tag("cloggrum_items");
		public static final TagKey<Item> FROSTSTEEL_ITEMS = tag("froststeel_items");
		public static final TagKey<Item> UTHERIUM_ITEMS = tag("utherium_items");
		public static final TagKey<Item> SMOGSTEM_LOGS = tag("smogstem_logs");
		public static final TagKey<Item> WIGGLEWOOD_LOGS = tag("wigglewood_logs");
		public static final TagKey<Item> GRONGLE_LOGS = tag("grongle_logs");
		public static final TagKey<Item> INFUSER_UTHERIUM_FUELS = tag("infuser_utherium_fuels");
		public static final TagKey<Item> INFUSER_ROGDORIUM_FUELS = tag("infuser_rogdorium_fuels");
		public static final TagKey<Item> ACCELERATED_DREADROCK_BREAKING = tag("accelerated_dreadrock_breaking");

		public static final TagKey<Item> CLOGGRUM_TOOL_MATERIALS = tag("cloggrum_tool_materials");
		public static final TagKey<Item> FROSTSTEEL_TOOL_MATERIALS = tag("froststeel_tool_materials");
		public static final TagKey<Item> UTHERIC_TOOL_MATERIALS = tag("utheric_tool_materials");
		public static final TagKey<Item> FORGOTTEN_TOOL_MATERIALS = tag("forgotten_tool_materials");

		public static final TagKey<Item> REPAIRS_CLOGGRUM_ARMOR = tag("repairs_cloggrum_armor");
		public static final TagKey<Item> REPAIRS_FROSTSTEEL_ARMOR = tag("repairs_froststeel_armor");
		public static final TagKey<Item> REPAIRS_UTHERIC_ARMOR = tag("repairs_utheric_armor");
		public static final TagKey<Item> REPAIRS_ANCIENT_ARMOR = tag("repairs_ancient_armor");

		public static final TagKey<Item> BRUTE_FOOD = tag("brute_food");
		public static final TagKey<Item> DWELLER_FOOD = tag("dweller_food");
		public static final TagKey<Item> DWELLER_TEMPT_ITEMS = tag("dweller_tempt_items");
		public static final TagKey<Item> GLOOMPER_FOOD = tag("gloomper_food");
		public static final TagKey<Item> MOG_FOOD = tag("mog_food");
		public static final TagKey<Item> SCINTLING_FOOD = tag("scintling_food");

		public static final TagKey<Item> RAW_MATERIALS_CLOGGRUM = commonTag("raw_materials/cloggrum");
		public static final TagKey<Item> RAW_MATERIALS_FROSTSTEEL = commonTag("raw_materials/froststeel");

		public static final TagKey<Item> INGOTS_CLOGGRUM = commonTag("ingots/cloggrum");
		public static final TagKey<Item> INGOTS_FROSTSTEEL = commonTag("ingots/froststeel");
		public static final TagKey<Item> GEMS_UTHERIUM = commonTag("gems/utherium");
		public static final TagKey<Item> GEMS_REGALIUM = commonTag("gems/regalium");
		public static final TagKey<Item> INGOTS_ROGDORIUM = commonTag("ingots/rogdorium");
		public static final TagKey<Item> INGOTS_FORGOTTEN_METAL = commonTag("ingots/forgotten_metal");

		public static final TagKey<Item> NUGGETS_CLOGGRUM = commonTag("nuggets/cloggrum");
		public static final TagKey<Item> NUGGETS_FROSTSTEEL = commonTag("nuggets/froststeel");
		public static final TagKey<Item> NUGGETS_ROGDORIUM = commonTag("nuggets/rogdorium");
		public static final TagKey<Item> NUGGETS_FORGOTTEN_METAL = commonTag("nuggets/forgotten_metal");

		public static final TagKey<Item> ORES_CLOGGRUM = commonTag("ores/cloggrum");
		public static final TagKey<Item> ORES_FROSTSTEEL = commonTag("ores/froststeel");
		public static final TagKey<Item> ORES_UTHERIUM = commonTag("ores/utherium");
		public static final TagKey<Item> ORES_REGALIUM = commonTag("ores/regalium");
		public static final TagKey<Item> ORES_ROGDORIUM = commonTag("ores/rogdorium");

		public static final TagKey<Item> STORAGE_BLOCKS_CLOGGRUM = commonTag("storage_blocks/cloggrum");
		public static final TagKey<Item> STORAGE_BLOCKS_FROSTSTEEL = commonTag("storage_blocks/froststeel");
		public static final TagKey<Item> STORAGE_BLOCKS_UTHERIUM = commonTag("storage_blocks/utherium");
		public static final TagKey<Item> STORAGE_BLOCKS_REGALIUM = commonTag("storage_blocks/regalium");
		public static final TagKey<Item> STORAGE_BLOCKS_ROGDORIUM = commonTag("storage_blocks/rogdorium");
		public static final TagKey<Item> STORAGE_BLOCKS_FORGOTTEN_METAL = commonTag("storage_blocks/forgotten_metal");

		public static final TagKey<Item> STORAGE_BLOCKS_RAW_CLOGGRUM = commonTag("storage_blocks/raw_cloggrum");
		public static final TagKey<Item> STORAGE_BLOCKS_RAW_FROSTSTEEL = commonTag("storage_blocks/raw_froststeel");

		private static TagKey<Item> tag(String name) {
			return ItemTags.create(Undergarden.prefix(name));
		}

		private static TagKey<Item> commonTag(String name) {
			return ItemTags.create(Identifier.fromNamespaceAndPath("c", name));
		}
	}

	public static class Blocks {

		public static final TagKey<Block> BASE_STONE_UNDERGARDEN = tag("base_stone_undergarden");
		public static final TagKey<Block> DEPTHROCK_ORE_REPLACEABLES = tag("depthrock_ore_replaceables");
		public static final TagKey<Block> SHIVERSTONE_ORE_REPLACEABLES = tag("shiverstone_ore_replaceables");
		public static final TagKey<Block> DREADROCK_ORE_REPLACEABLES = tag("dreadrock_ore_replaceables");
		public static final TagKey<Block> TREMBLECRUST_ORE_REPLACEABLES = tag("tremblecrust_ore_replaceables");
		public static final TagKey<Block> UNDERGARDEN_CARVER_REPLACEABLES = tag("undergarden_carver_replaceables");
		public static final TagKey<Block> MUSHROOMS = tag("mushrooms");
		public static final TagKey<Block> PORTAL_FRAME_BLOCKS = tag("portal_frame_blocks");
		public static final TagKey<Block> SMOGSTEM_LOGS = tag("smogstem_logs");
		public static final TagKey<Block> WIGGLEWOOD_LOGS = tag("wigglewood_logs");
		public static final TagKey<Block> GRONGLE_LOGS = tag("grongle_logs");
		public static final TagKey<Block> MUNCHER_BREAKABLES = tag("muncher_breakables");

		public static final TagKey<Block> SCINTLING_SPAWNABLE_ON = tag("scintling_spawnable_on");
		public static final TagKey<Block> SMOG_MOG_SPAWNABLE_ON = tag("smog_mog_spawnable_on");
		public static final TagKey<Block> GREATER_DWELLER_SPAWNABLE_ON = tag("greater_dweller_spawnable_on");

		public static final TagKey<Block> SUPPORTS_THORNREED = tag("supports_thornreed");
		public static final TagKey<Block> SUPPORTS_THORNREED_ADJACENTLY = tag("supports_thornreed_adjacently");

		public static final TagKey<Block> SUPPORTS_TWISTYBUSH = tag("supports_twistybush");

		public static final TagKey<Block> NEEDS_FORGOTTEN_TOOL = tag("needs_forgotten_tool");
		public static final TagKey<Block> INCORRECT_FOR_FORGOTTEN_TOOL = tag("incorrect_for_forgotten_tool");

		public static final TagKey<Block> ORES_CLOGGRUM = commonTag("ores/cloggrum");
		public static final TagKey<Block> ORES_FROSTSTEEL = commonTag("ores/froststeel");
		public static final TagKey<Block> ORES_UTHERIUM = commonTag("ores/utherium");
		public static final TagKey<Block> ORES_REGALIUM = commonTag("ores/regalium");
		public static final TagKey<Block> ORES_ROGDORIUM = commonTag("ores/rogdorium");

		public static final TagKey<Block> STORAGE_BLOCKS_CLOGGRUM = commonTag("storage_blocks/cloggrum");
		public static final TagKey<Block> STORAGE_BLOCKS_FROSTSTEEL = commonTag("storage_blocks/froststeel");
		public static final TagKey<Block> STORAGE_BLOCKS_UTHERIUM = commonTag("storage_blocks/utherium");
		public static final TagKey<Block> STORAGE_BLOCKS_REGALIUM = commonTag("storage_blocks/regalium");
		public static final TagKey<Block> STORAGE_BLOCKS_ROGDORIUM = commonTag("storage_blocks/rogdorium");
		public static final TagKey<Block> STORAGE_BLOCKS_FORGOTTEN_METAL = commonTag("storage_blocks/forgotten_metal");

		public static final TagKey<Block> STORAGE_BLOCKS_RAW_CLOGGRUM = commonTag("storage_blocks/raw_cloggrum");
		public static final TagKey<Block> STORAGE_BLOCKS_RAW_FROSTSTEEL = commonTag("storage_blocks/raw_froststeel");

		public static final TagKey<Block> DEPTHROCK_GROUND = commonTag("ore_bearing_ground/depthrock");
		public static final TagKey<Block> DEPTHROCK_ORES = commonTag("ores_in_ground/depthrock");

		public static final TagKey<Block> SHIVERSTONE_GROUND = commonTag("ore_bearing_ground/shiverstone");
		public static final TagKey<Block> SHIVERSTONE_ORES = commonTag("ores_in_ground/shiverstone");

		public static final TagKey<Block> DREADROCK_GROUND = commonTag("ore_bearing_ground/dreadrock");
		public static final TagKey<Block> DREADROCK_ORES = commonTag("ores_in_ground/dreadrock");

		public static final TagKey<Block> TREMBLECRUST_GROUND = commonTag("ore_bearing_ground/tremblecrust");
		public static final TagKey<Block> TREMBLECRUST_ORES = commonTag("ores_in_ground/tremblecrust");

		private static TagKey<Block> tag(String name) {
			return BlockTags.create(Undergarden.prefix(name));
		}

		private static TagKey<Block> commonTag(String name) {
			return BlockTags.create(Identifier.fromNamespaceAndPath("c", name));
		}
	}

	public static class Entities {

		public static final TagKey<EntityType<?>> ROTSPAWN = tag("rotspawn");
		public static final TagKey<EntityType<?>> CAVERN_CREATURE = tag("cavern_creature");
		public static final TagKey<EntityType<?>> IMMUNE_TO_VIRULENT_MIX = tag("immune_to_virulent_mix");
		public static final TagKey<EntityType<?>> IMMUNE_TO_SCINTLING_GOO = tag("immune_to_scintling_goo");
		public static final TagKey<EntityType<?>> IMMUNE_TO_BLISTERBERRY_BUSH = tag("immune_to_blisterberry_bush");
		public static final TagKey<EntityType<?>> IMMUNE_TO_INFECTION = tag("immune_to_utheric_infection");
		public static final TagKey<EntityType<?>> IMMUNE_TO_GOOEY_EFFECT = tag("immune_to_gooey_effect");
		public static final TagKey<EntityType<?>> IMMUNE_TO_THORNREED = tag("immune_to_thornreed");

		private static TagKey<EntityType<?>> tag(String name) {
			return TagKey.create(Registries.ENTITY_TYPE, Undergarden.prefix(name));
		}
	}

	public static class Fluids {

		public static final TagKey<Fluid> VIRULENT = tag("virulent");

		public static final TagKey<Fluid> SUPPORTS_THORNREED_ADJACENTLY = tag("supports_thornreed_adjacently");

		private static TagKey<Fluid> tag(String name) {
			return TagKey.create(Registries.FLUID, Undergarden.prefix(name));
		}
	}

	public static class Biomes {

		public static final TagKey<Biome> IS_UNDERGARDEN = tag("is_undergarden");

		public static final TagKey<Biome> IS_DEPTHS_BIOME = tag("is_depths_biome");

		public static final TagKey<Biome> HAS_CATACOMBS = tag("has_structure/catacombs");
		public static final TagKey<Biome> HAS_FORGOTTEN_VESTIGE = tag("has_structure/forgotten_vestige");
		public static final TagKey<Biome> HAS_DENIZEN_CAMP = tag("has_structure/denizen_camp");
		public static final TagKey<Biome> HAS_DEPLETED_MINE = tag("has_structure/depleted_mine");

		private static TagKey<Biome> tag(String name) {
			return TagKey.create(Registries.BIOME, Undergarden.prefix(name));
		}
	}

	public static class Enchantments {

		public static final TagKey<Enchantment> SLINGSHOT_EXCLUSIVE = tag("exclusive_set/slingshot");

		private static TagKey<Enchantment> tag(String name) {
			return TagKey.create(Registries.ENCHANTMENT, Undergarden.prefix(name));
		}
	}

	public static class Timelines {

		public static final TagKey<Timeline> IN_UNDERGARDEN = tag("in_undergarden");

		private static TagKey<Timeline> tag(String name) {
			return TagKey.create(Registries.TIMELINE, Undergarden.prefix(name));
		}
	}

	public static class StonebornTrades {
		public static final TagKey<StonebornTrade> VAGABOND_TRADES = tag("vagabond");

		private static TagKey<StonebornTrade> tag(String name) {
			return TagKey.create(UGRegistries.Keys.STONEBORN_TRADE, Undergarden.prefix(name));
		}
	}
}