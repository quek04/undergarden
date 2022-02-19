package quek.undergarden.registry;

import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.UGBoatEntity;
import quek.undergarden.item.*;
import quek.undergarden.item.armor.MasticatedChestplateItem;
import quek.undergarden.item.armor.UndergardenArmorItem;
import quek.undergarden.item.tool.*;

public class UGItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Undergarden.MODID);

    public static final Rarity FORGOTTEN = Rarity.create("forgotten", ChatFormatting.GREEN);

    //discs
    public static final RegistryObject<Item> MAMMOTH_DISC = ITEMS.register("music_disc_mammoth", () -> new RecordItem(0, UGSoundEvents.MAMMOTH_DISC, new Item.Properties().tab(UGItemGroups.GROUP).rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> LIMAX_MAXIMUS_DISC = ITEMS.register("music_disc_limax_maximus", () -> new RecordItem(1, UGSoundEvents.LIMAX_MAXIMUS_DISC, new Item.Properties().tab(UGItemGroups.GROUP).rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> RELICT_DISC = ITEMS.register("music_disc_relict", () -> new RecordItem(2, UGSoundEvents.RELICT_DISC, new Item.Properties().tab(UGItemGroups.GROUP).rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> GLOOMPER_ANTHEM_DISC = ITEMS.register("music_disc_gloomper_anthem", () -> new RecordItem(3, UGSoundEvents.GLOOMPER_ANTHEM_DISC, new Item.Properties().tab(UGItemGroups.GROUP).rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> GLOOMPER_SECRET_DISC = ITEMS.register("music_disc_gloomper_secret", () -> new RecordItem(15, UGSoundEvents.GLOOMPER_SECRET_DISC, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));

    //crafting materials
    public static final RegistryObject<Item> RAW_CLOGGRUM = ITEMS.register("raw_cloggrum", UGItem::new);
    public static final RegistryObject<Item> CLOGGRUM_INGOT = ITEMS.register("cloggrum_ingot", UGItem::new);
    public static final RegistryObject<Item> CLOGGRUM_NUGGET = ITEMS.register("cloggrum_nugget", UGItem::new);

    public static final RegistryObject<Item> RAW_FROSTSTEEL = ITEMS.register("raw_froststeel", UGItem::new);
    public static final RegistryObject<Item> FROSTSTEEL_INGOT = ITEMS.register("froststeel_ingot", UGItem::new);
    public static final RegistryObject<Item> FROSTSTEEL_NUGGET = ITEMS.register("froststeel_nugget", UGItem::new);

    public static final RegistryObject<Item> UTHERIC_SHARD = ITEMS.register("utheric_shard", UGItem::new);
    public static final RegistryObject<Item> UTHERIUM_CRYSTAL = ITEMS.register("utherium_crystal", UGItem::new);

    public static final RegistryObject<Item> REGALIUM_CRYSTAL = ITEMS.register("regalium_crystal", () -> new UGItem(Rarity.UNCOMMON));

    public static final RegistryObject<Item> FORGOTTEN_INGOT = ITEMS.register("forgotten_ingot", () -> new UGItem(FORGOTTEN));
    public static final RegistryObject<Item> FORGOTTEN_NUGGET = ITEMS.register("forgotten_nugget", () -> new UGItem(FORGOTTEN));

    public static final RegistryObject<Item> DEPTHROCK_PEBBLE = ITEMS.register("depthrock_pebble", DepthrockPebbleItem::new);
    public static final RegistryObject<Item> TWISTYTWIG = ITEMS.register("twistytwig", UGItem::new);
    public static final RegistryObject<Item> DITCHBULB = ITEMS.register("ditchbulb", DitchbulbItem::new);
    public static final RegistryObject<Item> BRUTE_TUSK = ITEMS.register("brute_tusk", UGItem::new);
    public static final RegistryObject<Item> MOGMOSS = ITEMS.register("mogmoss", UGItem::new);

    //boss loot
    public static final RegistryObject<Item> MASTICATOR_SCALES = ITEMS.register("masticator_scales", () -> new UGItem(Rarity.UNCOMMON));
    public static final RegistryObject<Item> MASTICATED_CHESTPLATE = ITEMS.register("masticated_chestplate", () -> new MasticatedChestplateItem(UGArmorMaterials.MASTICATED));

    //tools
    public static final RegistryObject<Item> CLOGGRUM_BATTLEAXE = ITEMS.register("cloggrum_battleaxe", () -> new BattleAxeItem(UGItemTiers.CLOGGRUM, 7, -3.4F));
    public static final RegistryObject<Item> CLOGGRUM_SWORD = ITEMS.register("cloggrum_sword", () -> new UGSwordItem(UGItemTiers.CLOGGRUM, 3, -2.4F));
    public static final RegistryObject<Item> CLOGGRUM_PICKAXE = ITEMS.register("cloggrum_pickaxe", () -> new UGPickaxeItem(UGItemTiers.CLOGGRUM, 1, -2.8F));
    public static final RegistryObject<Item> CLOGGRUM_AXE = ITEMS.register("cloggrum_axe", () -> new UGAxeItem(UGItemTiers.CLOGGRUM, 5.0F, -3.1F));
    public static final RegistryObject<Item> CLOGGRUM_SHOVEL = ITEMS.register("cloggrum_shovel", () -> new UGShovelItem(UGItemTiers.CLOGGRUM, 1.5F, -3.0F));
    public static final RegistryObject<Item> CLOGGRUM_HOE = ITEMS.register("cloggrum_hoe", () -> new UGHoeItem(UGItemTiers.CLOGGRUM, -3, -1.0F));
    public static final RegistryObject<Item> CLOGGRUM_SHIELD = ITEMS.register("cloggrum_shield", () -> new UGShieldItem(UGShieldTiers.CLOGGRUM));

    public static final RegistryObject<Item> FROSTSTEEL_SWORD = ITEMS.register("froststeel_sword", () -> new UGSwordItem(UGItemTiers.FROSTSTEEL, 3, -2.4F));
    public static final RegistryObject<Item> FROSTSTEEL_PICKAXE = ITEMS.register("froststeel_pickaxe", () -> new UGPickaxeItem(UGItemTiers.FROSTSTEEL, 1, -2.8F));
    public static final RegistryObject<Item> FROSTSTEEL_AXE = ITEMS.register("froststeel_axe", () -> new UGAxeItem(UGItemTiers.FROSTSTEEL, 6.0F, -3.0F));
    public static final RegistryObject<Item> FROSTSTEEL_SHOVEL = ITEMS.register("froststeel_shovel", () -> new UGShovelItem(UGItemTiers.FROSTSTEEL, 1.5F, -3.0F));
    public static final RegistryObject<Item> FROSTSTEEL_HOE = ITEMS.register("froststeel_hoe", () -> new UGHoeItem(UGItemTiers.FROSTSTEEL, -2, -0.5F));

    public static final RegistryObject<Item> UTHERIUM_SWORD = ITEMS.register("utherium_sword", () -> new UGSwordItem(UGItemTiers.UTHERIUM, 3, -2.4F));
    public static final RegistryObject<Item> UTHERIUM_PICKAXE = ITEMS.register("utherium_pickaxe", () -> new UGPickaxeItem(UGItemTiers.UTHERIUM, 1, -2.8F));
    public static final RegistryObject<Item> UTHERIUM_AXE = ITEMS.register("utherium_axe", () -> new UGAxeItem(UGItemTiers.UTHERIUM, 5.0F, -3.0F));
    public static final RegistryObject<Item> UTHERIUM_SHOVEL = ITEMS.register("utherium_shovel", () -> new UGShovelItem(UGItemTiers.UTHERIUM, 1.5F, -3.0F));
    public static final RegistryObject<Item> UTHERIUM_HOE = ITEMS.register("utherium_hoe", () -> new UGHoeItem(UGItemTiers.UTHERIUM, -3, 0.0F));

    public static final RegistryObject<Item> FORGOTTEN_BATTLEAXE = ITEMS.register("forgotten_battleaxe", () -> new BattleAxeItem(UGItemTiers.FORGOTTEN, 7, -3.4F));
    public static final RegistryObject<Item> FORGOTTEN_SWORD = ITEMS.register("forgotten_sword", () -> new UGSwordItem(UGItemTiers.FORGOTTEN, 3, -2.4F));
    public static final RegistryObject<Item> FORGOTTEN_PICKAXE = ITEMS.register("forgotten_pickaxe", () -> new UGPickaxeItem(UGItemTiers.FORGOTTEN, 1, -2.8F));
    public static final RegistryObject<Item> FORGOTTEN_AXE = ITEMS.register("forgotten_axe", () -> new UGAxeItem(UGItemTiers.FORGOTTEN, 5.0F, -3.0F));
    public static final RegistryObject<Item> FORGOTTEN_SHOVEL = ITEMS.register("forgotten_shovel", () -> new UGShovelItem(UGItemTiers.FORGOTTEN, 1.5F, -3.0F));
    public static final RegistryObject<Item> FORGOTTEN_HOE = ITEMS.register("forgotten_hoe", () -> new UGHoeItem(UGItemTiers.FORGOTTEN, -3, 0.0F));

    //misc tools
    public static final RegistryObject<Item> CATALYST = ITEMS.register("catalyst", CatalystItem::new);
    public static final RegistryObject<Item> SLINGSHOT = ITEMS.register("slingshot", SlingshotItem::new);
    public static final RegistryObject<Item> BLISTERBOMB = ITEMS.register("blisterbomb", BlisterbombItem::new);
    public static final RegistryObject<Item> GOO_BALL = ITEMS.register("goo_ball", GooBallItem::new);
    public static final RegistryObject<Item> UNDERBEAN_STICK = ITEMS.register("underbean_on_a_stick", () -> new UnderbeanOnAStickItem<>(new Item.Properties().stacksTo(1).durability(100).tab(UGItemGroups.GROUP), UGEntityTypes.DWELLER_TYPE));

    public static final RegistryObject<Item> SMOGSTEM_BOAT = ITEMS.register("smogstem_boat", () -> new UGBoatItem(UGBoatEntity.Type.SMOGSTEM, new Item.Properties().tab(UGItemGroups.GROUP).stacksTo(1)));
    public static final RegistryObject<Item> WIGGLEWOOD_BOAT = ITEMS.register("wigglewood_boat", () -> new UGBoatItem(UGBoatEntity.Type.WIGGLEWOOD, new Item.Properties().tab(UGItemGroups.GROUP).stacksTo(1)));
    public static final RegistryObject<Item> GRONGLE_BOAT = ITEMS.register("grongle_boat", () -> new UGBoatItem(UGBoatEntity.Type.GRONGLE, new Item.Properties().tab(UGItemGroups.GROUP).stacksTo(1)));

    public static final RegistryObject<Item> VIRULENT_MIX_BUCKET = ITEMS.register("virulent_mix_bucket", () -> new BucketItem(UGFluids.VIRULENT_MIX_SOURCE, new Item.Properties().tab(UGItemGroups.GROUP).stacksTo(1)));

    public static final RegistryObject<Item> GWIBLING_BUCKET = ITEMS.register("gwibling_bucket", () -> new MobBucketItem(UGEntityTypes.GWIBLING, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().stacksTo(1).tab(UGItemGroups.GROUP)));

    //armors
    public static final RegistryObject<Item> CLOGGRUM_HELMET = ITEMS.register("cloggrum_helmet", () -> new UndergardenArmorItem(UGArmorMaterials.CLOGGRUM, EquipmentSlot.HEAD));
    public static final RegistryObject<Item> CLOGGRUM_CHESTPLATE = ITEMS.register("cloggrum_chestplate", () -> new UndergardenArmorItem(UGArmorMaterials.CLOGGRUM, EquipmentSlot.CHEST));
    public static final RegistryObject<Item> CLOGGRUM_LEGGINGS = ITEMS.register("cloggrum_leggings", () -> new UndergardenArmorItem(UGArmorMaterials.CLOGGRUM, EquipmentSlot.LEGS));
    public static final RegistryObject<Item> CLOGGRUM_BOOTS = ITEMS.register("cloggrum_boots", () -> new UndergardenArmorItem(UGArmorMaterials.CLOGGRUM, EquipmentSlot.FEET));

    public static final RegistryObject<Item> FROSTSTEEL_HELMET = ITEMS.register("froststeel_helmet", () -> new UndergardenArmorItem(UGArmorMaterials.FROSTSTEEL, EquipmentSlot.HEAD));
    public static final RegistryObject<Item> FROSTSTEEL_CHESTPLATE = ITEMS.register("froststeel_chestplate", () -> new UndergardenArmorItem(UGArmorMaterials.FROSTSTEEL, EquipmentSlot.CHEST));
    public static final RegistryObject<Item> FROSTSTEEL_LEGGINGS = ITEMS.register("froststeel_leggings", () -> new UndergardenArmorItem(UGArmorMaterials.FROSTSTEEL, EquipmentSlot.LEGS));
    public static final RegistryObject<Item> FROSTSTEEL_BOOTS = ITEMS.register("froststeel_boots", () -> new UndergardenArmorItem(UGArmorMaterials.FROSTSTEEL, EquipmentSlot.FEET));

    public static final RegistryObject<Item> UTHERIUM_HELMET = ITEMS.register("utherium_helmet", () -> new UndergardenArmorItem(UGArmorMaterials.UTHERIUM, EquipmentSlot.HEAD));
    public static final RegistryObject<Item> UTHERIUM_CHESTPLATE = ITEMS.register("utherium_chestplate", () -> new UndergardenArmorItem(UGArmorMaterials.UTHERIUM, EquipmentSlot.CHEST));
    public static final RegistryObject<Item> UTHERIUM_LEGGINGS = ITEMS.register("utherium_leggings", () -> new UndergardenArmorItem(UGArmorMaterials.UTHERIUM, EquipmentSlot.LEGS));
    public static final RegistryObject<Item> UTHERIUM_BOOTS = ITEMS.register("utherium_boots", () -> new UndergardenArmorItem(UGArmorMaterials.UTHERIUM, EquipmentSlot.FEET));

    //foods/plants
    public static final RegistryObject<Item> DROOPFRUIT = ITEMS.register("droopvine_item", DroopfruitItem::new);
    public static final RegistryObject<Item> UNDERBEANS = ITEMS.register("underbeans", () -> new ItemNameBlockItem(UGBlocks.UNDERBEAN_BUSH.get(), new Item.Properties().tab(UGItemGroups.GROUP).food(UGFoods.UNDERBEANS)));
    public static final RegistryObject<Item> ROASTED_UNDERBEANS = ITEMS.register("roasted_underbeans", () -> new UGItem(UGFoods.ROASTED_UNDERBEANS));
    public static final RegistryObject<Item> BLISTERBERRY = ITEMS.register("blisterberry", () -> new ItemNameBlockItem(UGBlocks.BLISTERBERRY_BUSH.get(), new Item.Properties().tab(UGItemGroups.GROUP).food(UGFoods.BLISTERBERRY)));
    public static final RegistryObject<Item> ROTTEN_BLISTERBERRY = ITEMS.register("rotten_blisterberry", RottenBlisterberryItem::new);
    public static final RegistryObject<Item> GLOOMGOURD_PIE = ITEMS.register("gloomgourd_pie", () -> new UGItem(UGFoods.GLOOMGOURD_PIE));
    public static final RegistryObject<Item> RAW_DWELLER_MEAT = ITEMS.register("raw_dweller_meat", () -> new UGItem(UGFoods.RAW_DWELLER));
    public static final RegistryObject<Item> DWELLER_STEAK = ITEMS.register("dweller_steak", () -> new UGItem(UGFoods.COOKED_DWELLER));
    public static final RegistryObject<Item> RAW_GWIBLING = ITEMS.register("raw_gwibling", () -> new UGItem(UGFoods.RAW_GWIBLING));
    public static final RegistryObject<Item> COOKED_GWIBLING = ITEMS.register("cooked_gwibling", () -> new UGItem(UGFoods.COOKED_GWIBLING));
    public static final RegistryObject<Item> RAW_GLOOMPER_LEG = ITEMS.register("raw_gloomper_leg", () -> new UGItem(UGFoods.RAW_GLOOMPER_LEG));
    public static final RegistryObject<Item> GLOOMPER_LEG = ITEMS.register("gloomper_leg", () -> new UGItem(UGFoods.GLOOMPER_LEG));
    public static final RegistryObject<Item> GLITTERKELP = ITEMS.register("glitterkelp", () -> new BlockItem(UGBlocks.GLITTERKELP.get(), new Item.Properties().tab(UGItemGroups.GROUP)));
    public static final RegistryObject<Item> GLOOMGOURD_SEEDS = ITEMS.register("gloomgourd_seeds", () -> new ItemNameBlockItem(UGBlocks.GLOOMGOURD_STEM.get(), new Item.Properties().tab(UGItemGroups.GROUP)));
    public static final RegistryObject<Item> BLOODY_STEW = ITEMS.register("bloody_stew", () -> new BowlFoodItem(new Item.Properties().tab(UGItemGroups.GROUP).food(UGFoods.BLOODY).stacksTo(1)));
    public static final RegistryObject<Item> INKY_STEW = ITEMS.register("inky_stew", () -> new BowlFoodItem(new Item.Properties().tab(UGItemGroups.GROUP).food(UGFoods.INKY).stacksTo(1)));
    public static final RegistryObject<Item> INDIGO_STEW = ITEMS.register("indigo_stew", () -> new BowlFoodItem(new Item.Properties().tab(UGItemGroups.GROUP).food(UGFoods.INDIGO).stacksTo(1)));
    public static final RegistryObject<Item> VEILED_STEW = ITEMS.register("veiled_stew", () -> new BowlFoodItem(new Item.Properties().tab(UGItemGroups.GROUP).food(UGFoods.VEILED).stacksTo(1)));

    //spawn eggs
    public static final RegistryObject<ForgeSpawnEggItem> DWELLER_SPAWN_EGG = ITEMS.register("dweller_spawn_egg", () -> new ForgeSpawnEggItem(UGEntityTypes.DWELLER, 4804417, 16776960, new Item.Properties().tab(UGItemGroups.GROUP)));
    public static final RegistryObject<ForgeSpawnEggItem> GWIBLING_SPAWN_EGG = ITEMS.register("gwibling_spawn_egg", () -> new ForgeSpawnEggItem(UGEntityTypes.GWIBLING, 10064737, 15845330, new Item.Properties().tab(UGItemGroups.GROUP)));
    public static final RegistryObject<ForgeSpawnEggItem> ROTLING_SPAWN_EGG = ITEMS.register("rotling_spawn_egg", () -> new ForgeSpawnEggItem(UGEntityTypes.ROTLING, 5590327, 10500660, new Item.Properties().tab(UGItemGroups.GROUP)));
    public static final RegistryObject<ForgeSpawnEggItem> ROTWALKER_SPAWN_EGG = ITEMS.register("rotwalker_spawn_egg", () -> new ForgeSpawnEggItem(UGEntityTypes.ROTWALKER, 5590327, 10500660, new Item.Properties().tab(UGItemGroups.GROUP)));
    public static final RegistryObject<ForgeSpawnEggItem> ROTBEAST_SPAWN_EGG = ITEMS.register("rotbeast_spawn_egg", () -> new ForgeSpawnEggItem(UGEntityTypes.ROTBEAST, 5590327,10500660, new Item.Properties().tab(UGItemGroups.GROUP)));
    public static final RegistryObject<ForgeSpawnEggItem> BRUTE_SPAWN_EGG = ITEMS.register("brute_spawn_egg", () -> new ForgeSpawnEggItem(UGEntityTypes.BRUTE, 7035982, 4012083, new Item.Properties().tab(UGItemGroups.GROUP)));
    public static final RegistryObject<ForgeSpawnEggItem> SCINTLING_SPAWN_EGG = ITEMS.register("scintling_spawn_egg", () -> new ForgeSpawnEggItem(UGEntityTypes.SCINTLING, 8556655, 6314558, new Item.Properties().tab(UGItemGroups.GROUP)));
    public static final RegistryObject<ForgeSpawnEggItem> GLOOMPER_SPAWN_EGG = ITEMS.register("gloomper_spawn_egg", () -> new ForgeSpawnEggItem(UGEntityTypes.GLOOMPER, 4138045, 6579581, new Item.Properties().tab(UGItemGroups.GROUP)));
    public static final RegistryObject<ForgeSpawnEggItem> STONEBORN_SPAWN_EGG = ITEMS.register("stoneborn_spawn_egg", () -> new ForgeSpawnEggItem(UGEntityTypes.STONEBORN, 3227179, 9502615, new Item.Properties().tab(UGItemGroups.GROUP)));
    public static final RegistryObject<ForgeSpawnEggItem> NARGOYLE_SPAWN_EGG = ITEMS.register("nargoyle_spawn_egg", () -> new ForgeSpawnEggItem(UGEntityTypes.NARGOYLE, 3747634, 15508905, new Item.Properties().tab(UGItemGroups.GROUP)));
    public static final RegistryObject<ForgeSpawnEggItem> MUNCHER_SPAWN_EGG = ITEMS.register("muncher_spawn_egg", () -> new ForgeSpawnEggItem(UGEntityTypes.MUNCHER, 2366466, 15881511, new Item.Properties().tab(UGItemGroups.GROUP)));
    public static final RegistryObject<ForgeSpawnEggItem> SPLOOGIE_SPAWN_EGG = ITEMS.register("sploogie_spawn_egg", () -> new ForgeSpawnEggItem(UGEntityTypes.SPLOOGIE, 10585715, 4559071, new Item.Properties().tab(UGItemGroups.GROUP)));
    public static final RegistryObject<ForgeSpawnEggItem> GWIB_SPAWN_EGG = ITEMS.register("gwib_spawn_egg", () -> new ForgeSpawnEggItem(UGEntityTypes.GWIB, 10064737, 4203803, new Item.Properties().tab(UGItemGroups.GROUP)));
    public static final RegistryObject<ForgeSpawnEggItem> MOG_SPAWN_EGG = ITEMS.register("mog_spawn_egg", () -> new ForgeSpawnEggItem(UGEntityTypes.MOG, 3227179, 6393396, new Item.Properties().tab(UGItemGroups.GROUP)));

    public static final RegistryObject<ForgeSpawnEggItem> MASTICATOR_SPAWN_EGG = ITEMS.register("masticator_spawn_egg", () -> new ForgeSpawnEggItem(UGEntityTypes.MASTICATOR, 2366466, 15881511, new Item.Properties().tab(UGItemGroups.GROUP)));
    public static final RegistryObject<ForgeSpawnEggItem> FORGOTTEN_GUARDIAN_SPAWN_EGG = ITEMS.register("forgotten_guardian_spawn_egg", () -> new ForgeSpawnEggItem(UGEntityTypes.FORGOTTEN_GUARDIAN, 8126397, 3170136, new Item.Properties().tab(UGItemGroups.GROUP)));
}