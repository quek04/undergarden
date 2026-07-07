package quek.undergarden.datagen.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.data.tags.VanillaItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGTags;

import java.util.concurrent.CompletableFuture;

public class UGItemTags extends ItemTagsProvider {

	public UGItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
		super(output, future, Undergarden.MODID);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		//shared block and item tags
		new UGBlockItemTags() {
			@Override
			protected TagAppender<Block, Block> tag(TagKey<Block> blockTag, TagKey<Item> itemTag) {
				return new VanillaItemTagsProvider.BlockToItemConverter(UGItemTags.this.tag(itemTag));
			}
		}.run();

		//undergarden
		tag(UGTags.Items.SLINGSHOT_ENCHANTABLE).add(UGItems.SLINGSHOT.get());
		tag(UGTags.Items.CLOGGRUM_ITEMS).add(UGItems.CLOGGRUM_SWORD.get(), UGItems.CLOGGRUM_PICKAXE.get(), UGItems.CLOGGRUM_AXE.get(), UGItems.CLOGGRUM_SHOVEL.get(), UGItems.CLOGGRUM_HOE.get(), UGItems.CLOGGRUM_HELMET.get(), UGItems.CLOGGRUM_CHESTPLATE.get(), UGItems.CLOGGRUM_LEGGINGS.get(), UGItems.CLOGGRUM_BOOTS.get());
		tag(UGTags.Items.FROSTSTEEL_ITEMS).add(UGItems.FROSTSTEEL_SWORD.get(), UGItems.FROSTSTEEL_PICKAXE.get(), UGItems.FROSTSTEEL_AXE.get(), UGItems.FROSTSTEEL_SHOVEL.get(), UGItems.FROSTSTEEL_HOE.get(), UGItems.FROSTSTEEL_HELMET.get(), UGItems.FROSTSTEEL_CHESTPLATE.get(), UGItems.FROSTSTEEL_LEGGINGS.get(), UGItems.FROSTSTEEL_BOOTS.get());
		tag(UGTags.Items.UTHERIUM_ITEMS).add(UGItems.UTHERIUM_SWORD.get(), UGItems.UTHERIUM_PICKAXE.get(), UGItems.UTHERIUM_AXE.get(), UGItems.UTHERIUM_SHOVEL.get(), UGItems.UTHERIUM_HOE.get(), UGItems.UTHERIUM_HELMET.get(), UGItems.UTHERIUM_CHESTPLATE.get(), UGItems.UTHERIUM_LEGGINGS.get(), UGItems.UTHERIUM_BOOTS.get());

		tag(UGTags.Items.INFUSER_UTHERIUM_FUELS).add(UGItems.UTHERIUM_CRYSTAL.get());
		tag(UGTags.Items.INFUSER_ROGDORIUM_FUELS).add(UGItems.ROGDORIUM.get());
		tag(UGTags.Items.ACCELERATED_DREADROCK_BREAKING).add(UGItems.FORGOTTEN_PICKAXE.get(), UGItems.UTHERIUM_PICKAXE.get());

		tag(UGTags.Items.CLOGGRUM_TOOL_MATERIALS).addTag(UGTags.Items.INGOTS_CLOGGRUM);
		tag(UGTags.Items.FROSTSTEEL_TOOL_MATERIALS).addTag(UGTags.Items.INGOTS_FROSTSTEEL);
		tag(UGTags.Items.UTHERIC_TOOL_MATERIALS).addTag(UGTags.Items.GEMS_UTHERIUM);
		tag(UGTags.Items.FORGOTTEN_TOOL_MATERIALS).addTag(UGTags.Items.INGOTS_FORGOTTEN_METAL);

		tag(UGTags.Items.REPAIRS_CLOGGRUM_ARMOR).addTag(UGTags.Items.INGOTS_CLOGGRUM);
		tag(UGTags.Items.REPAIRS_FROSTSTEEL_ARMOR).addTag(UGTags.Items.INGOTS_FROSTSTEEL);
		tag(UGTags.Items.REPAIRS_UTHERIC_ARMOR).addTag(UGTags.Items.GEMS_UTHERIUM);
		tag(UGTags.Items.REPAIRS_ANCIENT_ARMOR);

		tag(UGTags.Items.BRUTE_FOOD).add(UGItems.DROOPFRUIT.get());
		tag(UGTags.Items.DWELLER_FOOD).add(UGItems.UNDERBEANS.get());
		tag(UGTags.Items.DWELLER_TEMPT_ITEMS).addTag(UGTags.Items.DWELLER_FOOD).add(UGItems.UNDERBEAN_STICK.get(), UGItems.ROGDORIUM_INFUSED_UNDERBEANS.get());
		tag(UGTags.Items.DWELLER_GROWTH_ITEMS).add(UGItems.ROGDORIUM_INFUSED_UNDERBEANS.get());
		tag(UGTags.Items.GREATER_DWELLER_FOOD).addTag(UGTags.Items.DWELLER_FOOD).add(UGBlocks.PUFF_MUSHROOM.asItem());
		tag(UGTags.Items.GLOOMPER_FOOD).add(UGBlocks.GLOOMGOURD.asItem());
		tag(UGTags.Items.MOG_FOOD).add(UGItems.DEPTHROCK_PEBBLE.get());
		tag(UGTags.Items.SCINTLING_FOOD).add(UGItems.BLISTERBERRY.get());

		//undergarden common
		tag(UGTags.Items.CROPS_GLOOMGOURD).add(UGBlocks.GLOOMGOURD.asItem());
		tag(UGTags.Items.SEEDS_GLOOMGOURD).add(UGItems.GLOOMGOURD_SEEDS.get());
		tag(UGTags.Items.TOOLS_JAVELIN).add(UGItems.JAVELIN.get());
		tag(UGTags.Items.TOOLS_BATTLEAXE).add(UGItems.CLOGGRUM_BATTLEAXE.get(), UGItems.FORGOTTEN_BATTLEAXE.get());

		tag(UGTags.Items.RAW_MATERIALS_CLOGGRUM).add(UGItems.RAW_CLOGGRUM.get());
		tag(UGTags.Items.RAW_MATERIALS_FROSTSTEEL).add(UGItems.RAW_FROSTSTEEL.get());

		tag(UGTags.Items.INGOTS_CLOGGRUM).add(UGItems.CLOGGRUM_INGOT.get());
		tag(UGTags.Items.INGOTS_FROSTSTEEL).add(UGItems.FROSTSTEEL_INGOT.get());
		tag(UGTags.Items.GEMS_UTHERIUM).add(UGItems.UTHERIUM_CRYSTAL.get());
		tag(UGTags.Items.GEMS_REGALIUM).add(UGItems.REGALIUM_CRYSTAL.get());
		tag(UGTags.Items.INGOTS_ROGDORIUM).add(UGItems.ROGDORIUM.get());
		tag(UGTags.Items.INGOTS_FORGOTTEN_METAL).add(UGItems.FORGOTTEN_INGOT.get());

		tag(UGTags.Items.NUGGETS_CLOGGRUM).add(UGItems.CLOGGRUM_NUGGET.get());
		tag(UGTags.Items.NUGGETS_FROSTSTEEL).add(UGItems.FROSTSTEEL_NUGGET.get());
		tag(UGTags.Items.NUGGETS_ROGDORIUM).add(UGItems.ROGDORIUM_NUGGET.get());
		tag(UGTags.Items.NUGGETS_FORGOTTEN_METAL).add(UGItems.FORGOTTEN_NUGGET.get());

		//vanilla
		tag(ItemTags.BOATS).add(UGItems.WIGGLEWOOD_BOAT.get(), UGItems.SMOGSTEM_BOAT.get(), UGItems.GRONGLE_BOAT.get(), UGItems.ANCIENT_ROOT_BOAT.get());
		tag(ItemTags.CHEST_BOATS).add(UGItems.WIGGLEWOOD_CHEST_BOAT.get(), UGItems.SMOGSTEM_CHEST_BOAT.get(), UGItems.GRONGLE_CHEST_BOAT.get(), UGItems.ANCIENT_ROOT_CHEST_BOAT.get());
		tag(ItemTags.FISHES).add(UGItems.RAW_GWIBLING.get(), UGItems.COOKED_GWIBLING.get());
		tag(ItemTags.COALS).add(UGItems.DITCHBULB_PASTE.get());
		tag(ItemTags.BEACON_PAYMENT_ITEMS).add(UGItems.CLOGGRUM_INGOT.get(), UGItems.FROSTSTEEL_INGOT.get(), UGItems.UTHERIUM_CRYSTAL.get(), UGItems.REGALIUM_CRYSTAL.get(), UGItems.ROGDORIUM.get(), UGItems.FORGOTTEN_INGOT.get());
		tag(ItemTags.FOX_FOOD).add(UGItems.BLISTERBERRY.get());
		tag(ItemTags.STONE_TOOL_MATERIALS).add(UGBlocks.DEPTHROCK.asItem(), UGBlocks.SHIVERSTONE.asItem(), UGBlocks.TREMBLECRUST.asItem(), UGBlocks.DREADROCK.asItem());
		tag(ItemTags.STONE_CRAFTING_MATERIALS).add(UGBlocks.DEPTHROCK.asItem(), UGBlocks.SHIVERSTONE.asItem(), UGBlocks.TREMBLECRUST.asItem(), UGBlocks.DREADROCK.asItem());
		tag(ItemTags.CLUSTER_MAX_HARVESTABLES).add(UGItems.CLOGGRUM_PICKAXE.get(), UGItems.FROSTSTEEL_PICKAXE.get(), UGItems.UTHERIUM_PICKAXE.get(), UGItems.FORGOTTEN_PICKAXE.get());
		tag(ItemTags.SWORDS).add(UGItems.CLOGGRUM_SWORD.get(), UGItems.FROSTSTEEL_SWORD.get(), UGItems.UTHERIUM_SWORD.get(), UGItems.FORGOTTEN_SWORD.get(), UGItems.CLOGGRUM_BATTLEAXE.get(), UGItems.FORGOTTEN_BATTLEAXE.get());
		tag(ItemTags.AXES).add(UGItems.CLOGGRUM_AXE.get(), UGItems.FROSTSTEEL_AXE.get(), UGItems.UTHERIUM_AXE.get(), UGItems.FORGOTTEN_AXE.get());
		tag(ItemTags.PICKAXES).add(UGItems.CLOGGRUM_PICKAXE.get(), UGItems.FROSTSTEEL_PICKAXE.get(), UGItems.UTHERIUM_PICKAXE.get(), UGItems.FORGOTTEN_PICKAXE.get());
		tag(ItemTags.SHOVELS).add(UGItems.CLOGGRUM_SHOVEL.get(), UGItems.FROSTSTEEL_SHOVEL.get(), UGItems.UTHERIUM_SHOVEL.get(), UGItems.FORGOTTEN_SHOVEL.get());
		tag(ItemTags.HOES).add(UGItems.CLOGGRUM_HOE.get(), UGItems.FROSTSTEEL_HOE.get(), UGItems.UTHERIUM_HOE.get(), UGItems.FORGOTTEN_HOE.get());
		tag(ItemTags.SPEARS).add(UGItems.CLOGGRUM_SPEAR.get(), UGItems.FROSTSTEEL_SPEAR.get(), UGItems.UTHERIUM_SPEAR.get(), UGItems.FORGOTTEN_SPEAR.get());
		tag(ItemTags.FOOT_ARMOR).add(UGItems.CLOGGRUM_BOOTS.get(), UGItems.FROSTSTEEL_BOOTS.get(), UGItems.UTHERIUM_BOOTS.get());
		tag(ItemTags.LEG_ARMOR).add(UGItems.CLOGGRUM_LEGGINGS.get(), UGItems.FROSTSTEEL_LEGGINGS.get(), UGItems.UTHERIUM_LEGGINGS.get(), UGItems.ANCIENT_LEGGINGS.get());
		tag(ItemTags.CHEST_ARMOR).add(UGItems.CLOGGRUM_CHESTPLATE.get(), UGItems.FROSTSTEEL_CHESTPLATE.get(), UGItems.UTHERIUM_CHESTPLATE.get(), UGItems.ANCIENT_CHESTPLATE.get());
		tag(ItemTags.HEAD_ARMOR).add(UGItems.CLOGGRUM_HELMET.get(), UGItems.FROSTSTEEL_HELMET.get(), UGItems.UTHERIUM_HELMET.get(), UGItems.ANCIENT_HELMET.get());
		tag(ItemTags.TRIM_MATERIALS).add(UGItems.CLOGGRUM_INGOT.get(), UGItems.FROSTSTEEL_INGOT.get(), UGItems.UTHERIUM_CRYSTAL.get(), UGItems.REGALIUM_CRYSTAL.get(), UGItems.FORGOTTEN_INGOT.get(), UGItems.ROGDORIUM.get());
		tag(ItemTags.FURNACE_MINECART_FUEL).add(UGItems.DITCHBULB_PASTE.get());
		tag(ItemTags.MEAT).add(UGItems.RAW_DWELLER_MEAT.get(), UGItems.DWELLER_STEAK.get(), UGItems.RAW_GLOOMPER_LEG.get(), UGItems.GLOOMPER_LEG.get());
		tag(ItemTags.WOLF_FOOD).add(UGItems.RAW_GWIBLING.get(), UGItems.COOKED_GWIBLING.get(), UGItems.RAW_UNDERGAR_FILLET.get(), UGItems.COOKED_UNDERGAR_FILLET.get());
		tag(ItemTags.OCELOT_FOOD).add(UGItems.RAW_GWIBLING.get(), UGItems.RAW_UNDERGAR_FILLET.get());
		tag(ItemTags.CAT_FOOD).add(UGItems.RAW_GWIBLING.get(), UGItems.RAW_UNDERGAR_FILLET.get());
		tag(ItemTags.CHICKEN_FOOD).add(UGItems.GLOOMGOURD_SEEDS.get());
		tag(ItemTags.NAUTILUS_BUCKET_FOOD).add(UGItems.GWIBLING_BUCKET.get());
		tag(ItemTags.NAUTILUS_FOOD).add(UGItems.RAW_UNDERGAR_FILLET.get(), UGItems.COOKED_UNDERGAR_FILLET.get());
		tag(ItemTags.PARROT_FOOD).add(UGItems.GLOOMGOURD_SEEDS.get());
		tag(ItemTags.MAP_INVISIBILITY_EQUIPMENT).add(UGBlocks.CARVED_GLOOMGOURD.asItem());
		tag(ItemTags.METAL_NUGGETS).add(UGItems.CLOGGRUM_NUGGET.get(), UGItems.FROSTSTEEL_NUGGET.get(), UGItems.ROGDORIUM_NUGGET.get(), UGItems.FORGOTTEN_NUGGET.get());

		//neoforge
		tag(Tags.Items.BONES).add(UGItems.BRUTE_TUSK.get());
		tag(Tags.Items.BUCKETS).add(UGItems.CLOGGRUM_BUCKET.get());
		tag(Tags.Items.CROPS).addTag(UGTags.Items.CROPS_GLOOMGOURD);
		tag(Tags.Items.FOODS).add(UGItems.GOO_BALL.get(), UGItems.BLOOD_GLOBULE.get());
		tag(Tags.Items.FOODS_FRUIT).add(UGItems.DROOPFRUIT.get());
		tag(Tags.Items.FOODS_VEGETABLE).add(UGItems.UNDERBEANS.get(), UGItems.ROASTED_UNDERBEANS.get());
		tag(Tags.Items.FOODS_BERRY).add(UGItems.BLISTERBERRY.get(), UGItems.ROTTEN_BLISTERBERRY.get());
		tag(Tags.Items.FOODS_RAW_MEAT).add(UGItems.RAW_DWELLER_MEAT.get(), UGItems.RAW_GLOOMPER_LEG.get());
		tag(Tags.Items.FOODS_COOKED_MEAT).add(UGItems.DWELLER_STEAK.get(), UGItems.GLOOMPER_LEG.get());
		tag(Tags.Items.FOODS_RAW_FISH).add(UGItems.RAW_GWIBLING.get(), UGItems.RAW_UNDERGAR_FILLET.get());
		tag(Tags.Items.FOODS_COOKED_FISH).add(UGItems.COOKED_GWIBLING.get(), UGItems.COOKED_UNDERGAR_FILLET.get());
		tag(Tags.Items.FOODS_PIE).add(UGItems.GLOOMGOURD_PIE.get());
		tag(Tags.Items.FOODS_SOUP).add(UGItems.BLOODY_STEW.get(), UGItems.INKY_STEW.get(), UGItems.INDIGO_STEW.get(), UGItems.VEILED_STEW.get(), UGItems.SLOP_BOWL.get());
		tag(Tags.Items.ANIMAL_FOODS).addTag(UGTags.Items.BRUTE_FOOD).addTag(UGTags.Items.DWELLER_FOOD).addTag(UGTags.Items.GREATER_DWELLER_FOOD).addTag(UGTags.Items.GLOOMPER_FOOD).addTag(UGTags.Items.MOG_FOOD).addTag(UGTags.Items.SCINTLING_FOOD);
		tag(Tags.Items.GEMS).addTag(UGTags.Items.GEMS_UTHERIUM).addTag(UGTags.Items.GEMS_REGALIUM);
		tag(Tags.Items.INGOTS).addTag(UGTags.Items.INGOTS_CLOGGRUM).addTag(UGTags.Items.INGOTS_FROSTSTEEL).addTag(UGTags.Items.INGOTS_FORGOTTEN_METAL).addTag(UGTags.Items.INGOTS_ROGDORIUM);
		tag(Tags.Items.MUSIC_DISCS).add(UGItems.MAMMOTH_DISC.get(), UGItems.LIMAX_MAXIMUS_DISC.get(), UGItems.RELICT_DISC.get(), UGItems.GLOOMPER_ANTHEM_DISC.get(), UGItems.GLOOMPER_SECRET_DISC.get());
		tag(Tags.Items.MUSHROOMS).add(UGBlocks.INK_MUSHROOM.asItem(), UGBlocks.INDIGO_MUSHROOM.asItem(), UGBlocks.VEIL_MUSHROOM.asItem(), UGBlocks.BLOOD_MUSHROOM.asItem(), UGBlocks.PUFF_MUSHROOM.asItem());
		tag(Tags.Items.NUGGETS).addTag(UGTags.Items.NUGGETS_CLOGGRUM).addTag(UGTags.Items.NUGGETS_FROSTSTEEL).addTag(UGTags.Items.NUGGETS_FORGOTTEN_METAL).addTag(UGTags.Items.NUGGETS_ROGDORIUM);
		tag(Tags.Items.RAW_MATERIALS).addTag(UGTags.Items.RAW_MATERIALS_CLOGGRUM).addTag(UGTags.Items.RAW_MATERIALS_FROSTSTEEL);

		tag(Tags.Items.SEEDS).addTag(UGTags.Items.SEEDS_GLOOMGOURD);
		tag(Tags.Items.SLIME_BALLS).add(UGItems.GOO_BALL.get());
		tag(Tags.Items.STRINGS).add(UGItems.TWISTYTWIG.get());
		tag(Tags.Items.TOOLS).addTag(UGTags.Items.TOOLS_JAVELIN).addTag(UGTags.Items.TOOLS_BATTLEAXE);
		tag(Tags.Items.TOOLS_SHIELD).add(UGItems.CLOGGRUM_SHIELD.get());
		tag(Tags.Items.MELEE_WEAPON_TOOLS).add(
			UGItems.CLOGGRUM_SWORD.get(), UGItems.CLOGGRUM_AXE.get(), UGItems.CLOGGRUM_BATTLEAXE.get(), UGItems.CLOGGRUM_SPEAR.get(),
			UGItems.FROSTSTEEL_SWORD.get(), UGItems.FROSTSTEEL_AXE.get(), UGItems.FROSTSTEEL_SPEAR.get(),
			UGItems.UTHERIUM_SWORD.get(), UGItems.UTHERIUM_AXE.get(), UGItems.UTHERIUM_SPEAR.get(),
			UGItems.FORGOTTEN_SWORD.get(), UGItems.FORGOTTEN_AXE.get(), UGItems.FORGOTTEN_BATTLEAXE.get(), UGItems.FORGOTTEN_SPEAR.get());
		tag(Tags.Items.RANGED_WEAPON_TOOLS).add(UGItems.JAVELIN.get());
		tag(Tags.Items.ARMORS).add(UGItems.DENIZEN_MASK.get());
	}
}
