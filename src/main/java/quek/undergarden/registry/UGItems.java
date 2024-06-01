package quek.undergarden.registry;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.UGBoat;
import quek.undergarden.item.*;
import quek.undergarden.item.armor.UndergardenArmorItem;
import quek.undergarden.item.tool.*;
import quek.undergarden.item.tool.slingshot.DepthrockPebbleItem;
import quek.undergarden.item.tool.slingshot.GooBallItem;
import quek.undergarden.item.tool.slingshot.RottenBlisterberryItem;
import quek.undergarden.item.tool.slingshot.SlingshotItem;

public class UGItems {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Undergarden.MODID);

	public static final Rarity ROGDORIUM = Rarity.create("rogdorium", new ResourceLocation(Undergarden.MODID, "rogdorium"), ChatFormatting.AQUA);
	public static final Rarity FORGOTTEN = Rarity.create("forgotten", new ResourceLocation(Undergarden.MODID, "forgotten"), ChatFormatting.GREEN);

	//discs
	public static final DeferredItem<Item> MAMMOTH_DISC = ITEMS.register("music_disc_mammoth", () -> new RecordItem(0, UGSoundEvents.MAMMOTH_DISC, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3860));
	public static final DeferredItem<Item> LIMAX_MAXIMUS_DISC = ITEMS.register("music_disc_limax_maximus", () -> new RecordItem(1, UGSoundEvents.LIMAX_MAXIMUS_DISC, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3220));
	public static final DeferredItem<Item> RELICT_DISC = ITEMS.register("music_disc_relict", () -> new RecordItem(2, UGSoundEvents.RELICT_DISC, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3740));
	public static final DeferredItem<Item> GLOOMPER_ANTHEM_DISC = ITEMS.register("music_disc_gloomper_anthem", () -> new RecordItem(3, UGSoundEvents.GLOOMPER_ANTHEM_DISC, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4080));
	public static final DeferredItem<Item> GLOOMPER_SECRET_DISC = ITEMS.register("music_disc_gloomper_secret", () -> new RecordItem(15, UGSoundEvents.GLOOMPER_SECRET_DISC, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), 3180));
	public static final DeferredItem<Item> FORGOTTEN_UPGRADE_TEMPLATE = ITEMS.register("forgotten_upgrade_smithing_template", ForgottenSmithingTemplateItem::new);

	//crafting materials
	public static final DeferredItem<Item> RAW_CLOGGRUM = ITEMS.register("raw_cloggrum", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> CLOGGRUM_INGOT = ITEMS.register("cloggrum_ingot", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> CLOGGRUM_NUGGET = ITEMS.register("cloggrum_nugget", () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> RAW_FROSTSTEEL = ITEMS.register("raw_froststeel", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> FROSTSTEEL_INGOT = ITEMS.register("froststeel_ingot", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> FROSTSTEEL_NUGGET = ITEMS.register("froststeel_nugget", () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> UTHERIC_SHARD = ITEMS.register("utheric_shard", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> UTHERIUM_CRYSTAL = ITEMS.register("utherium_crystal", () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> REGALIUM_CRYSTAL = ITEMS.register("regalium_crystal", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

	public static final DeferredItem<Item> ROGDORIUM_CRYSTAL = ITEMS.register("rogdorium_crystal", () -> new Item(new Item.Properties().rarity(ROGDORIUM).food(UGFoods.ROGDORIUM)));

	public static final DeferredItem<Item> FORGOTTEN_INGOT = ITEMS.register("forgotten_ingot", () -> new Item(new Item.Properties().rarity(FORGOTTEN)));
	public static final DeferredItem<Item> FORGOTTEN_NUGGET = ITEMS.register("forgotten_nugget", () -> new Item(new Item.Properties().rarity(FORGOTTEN)));

	public static final DeferredItem<Item> DEPTHROCK_PEBBLE = ITEMS.register("depthrock_pebble", () -> new DepthrockPebbleItem(UGBlocks.DEPTHROCK_PEBBLES.get(), new Item.Properties()));
	public static final DeferredItem<Item> TWISTYTWIG = ITEMS.register("twistytwig", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> DITCHBULB = ITEMS.register("ditchbulb", () -> new ItemNameBlockItem(UGBlocks.DITCHBULB_PLANT.get(), new Item.Properties()));
	public static final DeferredItem<Item> DITCHBULB_PASTE = ITEMS.register("ditchbulb_paste", DitchbulbPasteItem::new);
	public static final DeferredItem<Item> BRUTE_TUSK = ITEMS.register("brute_tusk", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> MOGMOSS = ITEMS.register("mogmoss", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> BLUE_MOGMOSS = ITEMS.register("blue_mogmoss", () -> new Item(new Item.Properties()));

	//tools
	public static final DeferredItem<Item> CLOGGRUM_BATTLEAXE = ITEMS.register("cloggrum_battleaxe", () -> new BattleaxeItem(UGItemTiers.CLOGGRUM, new Item.Properties().attributes(BattleaxeItem.createAttributes(UGItemTiers.CLOGGRUM, 7, -3.4F)).rarity(Rarity.EPIC)));
	public static final DeferredItem<Item> CLOGGRUM_SWORD = ITEMS.register("cloggrum_sword", () -> new UGSwordItem(UGItemTiers.CLOGGRUM, new Item.Properties().attributes(SwordItem.createAttributes(UGItemTiers.CLOGGRUM, 3, -2.4F))));
	public static final DeferredItem<Item> CLOGGRUM_PICKAXE = ITEMS.register("cloggrum_pickaxe", () -> new UGPickaxeItem(UGItemTiers.CLOGGRUM, new Item.Properties().attributes(PickaxeItem.createAttributes(UGItemTiers.CLOGGRUM, 1, -2.8F))));
	public static final DeferredItem<Item> CLOGGRUM_AXE = ITEMS.register("cloggrum_axe", () -> new UGAxeItem(UGItemTiers.CLOGGRUM, new Item.Properties().attributes(AxeItem.createAttributes(UGItemTiers.CLOGGRUM, 5.0F, -3.1F))));
	public static final DeferredItem<Item> CLOGGRUM_SHOVEL = ITEMS.register("cloggrum_shovel", () -> new UGShovelItem(UGItemTiers.CLOGGRUM, new Item.Properties().attributes(ShovelItem.createAttributes(UGItemTiers.CLOGGRUM, 1.5F, -3.0F))));
	public static final DeferredItem<Item> CLOGGRUM_HOE = ITEMS.register("cloggrum_hoe", () -> new UGHoeItem(UGItemTiers.CLOGGRUM, new Item.Properties().attributes(HoeItem.createAttributes(UGItemTiers.CLOGGRUM, -3, -1.0F))));
	public static final DeferredItem<Item> CLOGGRUM_SHIELD = ITEMS.register("cloggrum_shield", UGShieldItem::new);

	public static final DeferredItem<Item> FROSTSTEEL_SWORD = ITEMS.register("froststeel_sword", () -> new UGSwordItem(UGItemTiers.FROSTSTEEL, new Item.Properties().attributes(SwordItem.createAttributes(UGItemTiers.FROSTSTEEL, 3, -2.4F))));
	public static final DeferredItem<Item> FROSTSTEEL_PICKAXE = ITEMS.register("froststeel_pickaxe", () -> new UGPickaxeItem(UGItemTiers.FROSTSTEEL, new Item.Properties().attributes(PickaxeItem.createAttributes(UGItemTiers.FROSTSTEEL, 1, -2.8F))));
	public static final DeferredItem<Item> FROSTSTEEL_AXE = ITEMS.register("froststeel_axe", () -> new UGAxeItem(UGItemTiers.FROSTSTEEL, new Item.Properties().attributes(AxeItem.createAttributes(UGItemTiers.FROSTSTEEL, 6.0F, -3.0F))));
	public static final DeferredItem<Item> FROSTSTEEL_SHOVEL = ITEMS.register("froststeel_shovel", () -> new UGShovelItem(UGItemTiers.FROSTSTEEL, new Item.Properties().attributes(ShovelItem.createAttributes(UGItemTiers.FROSTSTEEL, 1.5F, -3.0F))));
	public static final DeferredItem<Item> FROSTSTEEL_HOE = ITEMS.register("froststeel_hoe", () -> new UGHoeItem(UGItemTiers.FROSTSTEEL, new Item.Properties().attributes(HoeItem.createAttributes(UGItemTiers.FROSTSTEEL, -2, -0.5F))));

	public static final DeferredItem<Item> UTHERIUM_SWORD = ITEMS.register("utherium_sword", () -> new UGSwordItem(UGItemTiers.UTHERIUM, new Item.Properties().attributes(SwordItem.createAttributes(UGItemTiers.UTHERIUM, 3, -2.4F))));
	public static final DeferredItem<Item> UTHERIUM_PICKAXE = ITEMS.register("utherium_pickaxe", () -> new UGPickaxeItem(UGItemTiers.UTHERIUM, new Item.Properties().attributes(PickaxeItem.createAttributes(UGItemTiers.UTHERIUM, 1, -2.8F))));
	public static final DeferredItem<Item> UTHERIUM_AXE = ITEMS.register("utherium_axe", () -> new UGAxeItem(UGItemTiers.UTHERIUM, new Item.Properties().attributes(AxeItem.createAttributes(UGItemTiers.UTHERIUM, 5.0F, -3.0F))));
	public static final DeferredItem<Item> UTHERIUM_SHOVEL = ITEMS.register("utherium_shovel", () -> new UGShovelItem(UGItemTiers.UTHERIUM, new Item.Properties().attributes(ShovelItem.createAttributes(UGItemTiers.UTHERIUM, 1.5F, -3.0F))));
	public static final DeferredItem<Item> UTHERIUM_HOE = ITEMS.register("utherium_hoe", () -> new UGHoeItem(UGItemTiers.UTHERIUM, new Item.Properties().attributes(HoeItem.createAttributes(UGItemTiers.UTHERIUM, -3, 0.0F))));

	public static final DeferredItem<Item> FORGOTTEN_BATTLEAXE = ITEMS.register("forgotten_battleaxe", () -> new BattleaxeItem(UGItemTiers.FORGOTTEN, new Item.Properties().attributes(BattleaxeItem.createAttributes(UGItemTiers.FORGOTTEN, 7, -3.4F)).rarity(Rarity.EPIC)));
	public static final DeferredItem<Item> FORGOTTEN_SWORD = ITEMS.register("forgotten_sword", () -> new UGSwordItem(UGItemTiers.FORGOTTEN, new Item.Properties().attributes(SwordItem.createAttributes(UGItemTiers.FORGOTTEN, 3, -2.4F)).rarity(FORGOTTEN)));
	public static final DeferredItem<Item> FORGOTTEN_PICKAXE = ITEMS.register("forgotten_pickaxe", () -> new UGPickaxeItem(UGItemTiers.FORGOTTEN, new Item.Properties().attributes(PickaxeItem.createAttributes(UGItemTiers.FORGOTTEN, 1, -2.8F)).rarity(FORGOTTEN)));
	public static final DeferredItem<Item> FORGOTTEN_AXE = ITEMS.register("forgotten_axe", () -> new UGAxeItem(UGItemTiers.FORGOTTEN, new Item.Properties().attributes(AxeItem.createAttributes(UGItemTiers.FORGOTTEN, 5.0F, -3.0F)).rarity(FORGOTTEN)));
	public static final DeferredItem<Item> FORGOTTEN_SHOVEL = ITEMS.register("forgotten_shovel", () -> new UGShovelItem(UGItemTiers.FORGOTTEN, new Item.Properties().attributes(ShovelItem.createAttributes(UGItemTiers.FORGOTTEN, 1.5F, -3.0F)).rarity(FORGOTTEN)));
	public static final DeferredItem<Item> FORGOTTEN_HOE = ITEMS.register("forgotten_hoe", () -> new UGHoeItem(UGItemTiers.FORGOTTEN, new Item.Properties().attributes(HoeItem.createAttributes(UGItemTiers.FORGOTTEN, -3, 0.0F)).rarity(FORGOTTEN)));

	//misc tools
	public static final DeferredItem<Item> CATALYST = ITEMS.register("catalyst", CatalystItem::new);
	public static final DeferredItem<Item> SLINGSHOT = ITEMS.register("slingshot", SlingshotItem::new);
	public static final DeferredItem<Item> SPEAR = ITEMS.register("spear", () -> new SpearItem(new Item.Properties().attributes(SpearItem.createAttributes()).component(DataComponents.TOOL, TridentItem.createToolProperties()).durability(250).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> BLISTERBOMB = ITEMS.register("blisterbomb", BlisterbombItem::new);
	public static final DeferredItem<Item> UNDERBEAN_STICK = ITEMS.register("underbean_on_a_stick", () -> new UnderbeanOnAStickItem(new Item.Properties().stacksTo(1).durability(100)));

	public static final DeferredItem<Item> SMOGSTEM_BOAT = ITEMS.register("smogstem_boat", () -> new UGBoatItem(false, UGBoat.Type.SMOGSTEM, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> SMOGSTEM_CHEST_BOAT = ITEMS.register("smogstem_chest_boat", () -> new UGBoatItem(true, UGBoat.Type.SMOGSTEM, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> WIGGLEWOOD_BOAT = ITEMS.register("wigglewood_boat", () -> new UGBoatItem(false, UGBoat.Type.WIGGLEWOOD, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> WIGGLEWOOD_CHEST_BOAT = ITEMS.register("wigglewood_chest_boat", () -> new UGBoatItem(true, UGBoat.Type.WIGGLEWOOD, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> GRONGLE_BOAT = ITEMS.register("grongle_boat", () -> new UGBoatItem(false, UGBoat.Type.GRONGLE, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> GRONGLE_CHEST_BOAT = ITEMS.register("grongle_chest_boat", () -> new UGBoatItem(true, UGBoat.Type.GRONGLE, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> ANCIENT_ROOT_BOAT = ITEMS.register("ancient_root_boat", () -> new UGBoatItem(false, UGBoat.Type.ANCIENT_ROOT, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> ANCIENT_ROOT_CHEST_BOAT = ITEMS.register("ancient_root_chest_boat", () -> new UGBoatItem(true, UGBoat.Type.ANCIENT_ROOT, new Item.Properties().stacksTo(1)));

	public static final DeferredItem<Item> VIRULENT_MIX_BUCKET = ITEMS.register("virulent_mix_bucket", () -> new UGBucketItem(UGFluids.VIRULENT_MIX_SOURCE.get(), new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));

	public static final DeferredItem<Item> GWIBLING_BUCKET = ITEMS.register("gwibling_bucket", () -> new MobBucketItem(UGEntityTypes.GWIBLING.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().stacksTo(1)));

	//armors
	public static final DeferredItem<Item> CLOGGRUM_HELMET = ITEMS.register("cloggrum_helmet", () -> new UndergardenArmorItem(UGArmorMaterials.CLOGGRUM, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(20))));
	public static final DeferredItem<Item> CLOGGRUM_CHESTPLATE = ITEMS.register("cloggrum_chestplate", () -> new UndergardenArmorItem(UGArmorMaterials.CLOGGRUM, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(20))));
	public static final DeferredItem<Item> CLOGGRUM_LEGGINGS = ITEMS.register("cloggrum_leggings", () -> new UndergardenArmorItem(UGArmorMaterials.CLOGGRUM, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(20))));
	public static final DeferredItem<Item> CLOGGRUM_BOOTS = ITEMS.register("cloggrum_boots", () -> new UndergardenArmorItem(UGArmorMaterials.CLOGGRUM, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(20))));

	public static final DeferredItem<Item> FROSTSTEEL_HELMET = ITEMS.register("froststeel_helmet", () -> new UndergardenArmorItem(UGArmorMaterials.FROSTSTEEL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(25)).attributes(UndergardenArmorItem.createFroststeelAttributes(ArmorItem.Type.HELMET, UGArmorMaterials.FROSTSTEEL.get()))));
	public static final DeferredItem<Item> FROSTSTEEL_CHESTPLATE = ITEMS.register("froststeel_chestplate", () -> new UndergardenArmorItem(UGArmorMaterials.FROSTSTEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(25)).attributes(UndergardenArmorItem.createFroststeelAttributes(ArmorItem.Type.CHESTPLATE, UGArmorMaterials.FROSTSTEEL.get()))));
	public static final DeferredItem<Item> FROSTSTEEL_LEGGINGS = ITEMS.register("froststeel_leggings", () -> new UndergardenArmorItem(UGArmorMaterials.FROSTSTEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(25)).attributes(UndergardenArmorItem.createFroststeelAttributes(ArmorItem.Type.LEGGINGS, UGArmorMaterials.FROSTSTEEL.get()))));
	public static final DeferredItem<Item> FROSTSTEEL_BOOTS = ITEMS.register("froststeel_boots", () -> new UndergardenArmorItem(UGArmorMaterials.FROSTSTEEL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(25)).attributes(UndergardenArmorItem.createFroststeelAttributes(ArmorItem.Type.BOOTS, UGArmorMaterials.FROSTSTEEL.get()))));

	public static final DeferredItem<Item> UTHERIUM_HELMET = ITEMS.register("utherium_helmet", () -> new UndergardenArmorItem(UGArmorMaterials.UTHERIUM, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(30))));
	public static final DeferredItem<Item> UTHERIUM_CHESTPLATE = ITEMS.register("utherium_chestplate", () -> new UndergardenArmorItem(UGArmorMaterials.UTHERIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(30))));
	public static final DeferredItem<Item> UTHERIUM_LEGGINGS = ITEMS.register("utherium_leggings", () -> new UndergardenArmorItem(UGArmorMaterials.UTHERIUM, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(30))));
	public static final DeferredItem<Item> UTHERIUM_BOOTS = ITEMS.register("utherium_boots", () -> new UndergardenArmorItem(UGArmorMaterials.UTHERIUM, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(30))));

	public static final DeferredItem<Item> ANCIENT_HELMET = ITEMS.register("ancient_helmet", () -> new UndergardenArmorItem(UGArmorMaterials.ANCIENT, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
	public static final DeferredItem<Item> ANCIENT_CHESTPLATE = ITEMS.register("ancient_chestplate", () -> new UndergardenArmorItem(UGArmorMaterials.ANCIENT, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
	public static final DeferredItem<Item> ANCIENT_LEGGINGS = ITEMS.register("ancient_leggings", () -> new UndergardenArmorItem(UGArmorMaterials.ANCIENT, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));

	public static final DeferredItem<Item> DENIZEN_MASK = ITEMS.register("denizen_mask", () -> new DenizenMaskItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

	//foods/plants
	public static final DeferredItem<Item> DROOPFRUIT = ITEMS.register("droopvine_item", () -> new ItemNameBlockItem(UGBlocks.DROOPVINE.get(), new Item.Properties().food(UGFoods.DROOPFRUIT)));
	public static final DeferredItem<Item> UNDERBEANS = ITEMS.register("underbeans", () -> new ItemNameBlockItem(UGBlocks.UNDERBEAN_BUSH.get(), new Item.Properties().food(UGFoods.UNDERBEANS)));
	public static final DeferredItem<Item> ROASTED_UNDERBEANS = ITEMS.register("roasted_underbeans", () -> new Item(new Item.Properties().food(UGFoods.ROASTED_UNDERBEANS)));
	public static final DeferredItem<Item> BLISTERBERRY = ITEMS.register("blisterberry", () -> new ItemNameBlockItem(UGBlocks.BLISTERBERRY_BUSH.get(), new Item.Properties().food(UGFoods.BLISTERBERRY)));
	public static final DeferredItem<Item> ROTTEN_BLISTERBERRY = ITEMS.register("rotten_blisterberry", () -> new RottenBlisterberryItem(new Item.Properties().food(UGFoods.ROTTEN_BLISTERBERRY)));
	public static final DeferredItem<Item> GOO_BALL = ITEMS.register("goo_ball", () -> new GooBallItem(new Item.Properties().food(UGFoods.GOO_BALL)));
	public static final DeferredItem<Item> GLOOMGOURD_PIE = ITEMS.register("gloomgourd_pie", () -> new Item(new Item.Properties().food(UGFoods.GLOOMGOURD_PIE)));
	public static final DeferredItem<Item> RAW_DWELLER_MEAT = ITEMS.register("raw_dweller_meat", () -> new Item(new Item.Properties().food(UGFoods.RAW_DWELLER)));
	public static final DeferredItem<Item> DWELLER_STEAK = ITEMS.register("dweller_steak", () -> new Item(new Item.Properties().food(UGFoods.COOKED_DWELLER)));
	public static final DeferredItem<Item> RAW_GWIBLING = ITEMS.register("raw_gwibling", () -> new Item(new Item.Properties().food(UGFoods.RAW_GWIBLING)));
	public static final DeferredItem<Item> COOKED_GWIBLING = ITEMS.register("cooked_gwibling", () -> new Item(new Item.Properties().food(UGFoods.COOKED_GWIBLING)));
	public static final DeferredItem<Item> RAW_GLOOMPER_LEG = ITEMS.register("raw_gloomper_leg", () -> new Item(new Item.Properties().food(UGFoods.RAW_GLOOMPER_LEG)));
	public static final DeferredItem<Item> GLOOMPER_LEG = ITEMS.register("gloomper_leg", () -> new Item(new Item.Properties().food(UGFoods.GLOOMPER_LEG)));
	public static final DeferredItem<Item> GLITTERKELP = ITEMS.register("glitterkelp", () -> new BlockItem(UGBlocks.GLITTERKELP.get(), new Item.Properties()));
	public static final DeferredItem<Item> GLOOMGOURD_SEEDS = ITEMS.register("gloomgourd_seeds", () -> new ItemNameBlockItem(UGBlocks.GLOOMGOURD_STEM.get(), new Item.Properties()));
	public static final DeferredItem<Item> BLOOD_GLOBULE = ITEMS.register("blood_globule", () -> new Item(new Item.Properties().food(UGFoods.BLOOD_GLOBULE)));
	public static final DeferredItem<Item> BLOODY_STEW = ITEMS.register("bloody_stew", () -> new BowlFoodItem(new Item.Properties().food(UGFoods.BLOODY).stacksTo(1)));
	public static final DeferredItem<Item> INKY_STEW = ITEMS.register("inky_stew", () -> new BowlFoodItem(new Item.Properties().food(UGFoods.INKY).stacksTo(1)));
	public static final DeferredItem<Item> INDIGO_STEW = ITEMS.register("indigo_stew", () -> new BowlFoodItem(new Item.Properties().food(UGFoods.INDIGO).stacksTo(1)));
	public static final DeferredItem<Item> VEILED_STEW = ITEMS.register("veiled_stew", () -> new BowlFoodItem(new Item.Properties().food(UGFoods.VEILED).stacksTo(1)));
	public static final DeferredItem<Item> SLOP_BOWL = ITEMS.register("slop_bowl", () -> new BowlFoodItem(new Item.Properties().food(UGFoods.SLOP).stacksTo(1)));

	//spawn eggs
	public static final DeferredItem<DeferredSpawnEggItem> DWELLER_SPAWN_EGG = ITEMS.register("dweller_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.DWELLER, 4804417, 16776960, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> GWIBLING_SPAWN_EGG = ITEMS.register("gwibling_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.GWIBLING, 10064737, 15845330, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> ROTLING_SPAWN_EGG = ITEMS.register("rotling_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.ROTLING, 5590327, 10500660, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> ROTWALKER_SPAWN_EGG = ITEMS.register("rotwalker_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.ROTWALKER, 5590327, 10500660, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> ROTBEAST_SPAWN_EGG = ITEMS.register("rotbeast_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.ROTBEAST, 5590327, 10500660, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> BRUTE_SPAWN_EGG = ITEMS.register("brute_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.BRUTE, 7035982, 4012083, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> SCINTLING_SPAWN_EGG = ITEMS.register("scintling_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.SCINTLING, 8556655, 6314558, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> GLOOMPER_SPAWN_EGG = ITEMS.register("gloomper_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.GLOOMPER, 4138045, 6579581, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> STONEBORN_SPAWN_EGG = ITEMS.register("stoneborn_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.STONEBORN, 3227179, 9502615, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> NARGOYLE_SPAWN_EGG = ITEMS.register("nargoyle_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.NARGOYLE, 3747634, 15508905, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> MUNCHER_SPAWN_EGG = ITEMS.register("muncher_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.MUNCHER, 2366466, 15881511, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> SPLOOGIE_SPAWN_EGG = ITEMS.register("sploogie_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.SPLOOGIE, 10585715, 4559071, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> GWIB_SPAWN_EGG = ITEMS.register("gwib_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.GWIB, 10064737, 4203803, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> MOG_SPAWN_EGG = ITEMS.register("mog_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.MOG, 3227179, 6393396, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> SMOG_MOG_SPAWN_EGG = ITEMS.register("smog_mog_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.SMOG_MOG, 3227179, 3444366, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> FORGOTTEN_SPAWN_EGG = ITEMS.register("forgotten_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.FORGOTTEN, 5393733, 9502615, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> DENIZEN_SPAWN_EGG = ITEMS.register("denizen_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.DENIZEN, 9797222, 13026490, new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> FORGOTTEN_GUARDIAN_SPAWN_EGG = ITEMS.register("forgotten_guardian_spawn_egg", () -> new DeferredSpawnEggItem(UGEntityTypes.FORGOTTEN_GUARDIAN, 8126397, 3170136, new Item.Properties()));
}