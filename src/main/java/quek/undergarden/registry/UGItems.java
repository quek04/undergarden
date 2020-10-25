package quek.undergarden.registry;

import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UGMod;
import quek.undergarden.item.*;
import quek.undergarden.item.armor.*;
import quek.undergarden.item.tool.*;

public class UGItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UGMod.MODID);

    //normal stuff
    public static final RegistryObject<Item> catalyst_item = ITEMS.register("catalyst", () -> new UndergardenItem(Rarity.RARE));
    public static final RegistryObject<Item> depthrock_pebble = ITEMS.register("depthrock_pebble", DepthrockPebbleItem::new);
    public static final RegistryObject<Item> smogstem_stick = ITEMS.register("smogstem_stick", UndergardenItem::new);
    public static final RegistryObject<Item> twistytwig = ITEMS.register("twistytwig", UndergardenItem::new);
    public static final RegistryObject<Item> cloggrum_ingot = ITEMS.register("cloggrum_ingot", UndergardenItem::new);
    public static final RegistryObject<Item> cloggrum_nugget = ITEMS.register("cloggrum_nugget", UndergardenItem::new);
    public static final RegistryObject<Item> froststeel_ingot = ITEMS.register("froststeel_ingot", UndergardenItem::new);
    public static final RegistryObject<Item> froststeel_nugget = ITEMS.register("froststeel_nugget", UndergardenItem::new);
    public static final RegistryObject<Item> utheric_shard = ITEMS.register("utheric_shard", UndergardenItem::new);
    public static final RegistryObject<Item> utherium_ingot = ITEMS.register("utherium_ingot", UndergardenItem::new);
    public static final RegistryObject<Item> utherium_chunk = ITEMS.register("utherium_chunk", UndergardenItem::new);
    public static final RegistryObject<Item> regalium_ingot = ITEMS.register("regalium_ingot", () -> new UndergardenItem(Rarity.UNCOMMON));
    public static final RegistryObject<Item> regalium_nugget = ITEMS.register("regalium_nugget", () -> new UndergardenItem(Rarity.UNCOMMON));
    public static final RegistryObject<Item> smogstem_torch = ITEMS.register("smogstem_torch", () -> new WallOrFloorItem(
            UGBlocks.smogstem_torch.get(), UGBlocks.smogstem_wall_torch.get(), new Item.Properties().group(UGItemGroups.GROUP)));
    public static final RegistryObject<Item> shard_torch = ITEMS.register("shard_torch", () -> new WallOrFloorItem(
            UGBlocks.shard_torch.get(), UGBlocks.shard_wall_torch.get(), new Item.Properties().group(UGItemGroups.GROUP)));
    public static final RegistryObject<Item> gloomgourd_seeds = ITEMS.register("gloomgourd_seeds", () -> new BlockNamedItem(
            UGBlocks.gloomgourd_stem.get(), (new Item.Properties()).group(UGItemGroups.GROUP)));
    public static final RegistryObject<Item> ditchbulb = ITEMS.register("ditchbulb", UndergardenItem::new);
    public static final RegistryObject<Item> brute_tusk = ITEMS.register("brute_tusk", UndergardenItem::new);
    public static final RegistryObject<Item> glowing_kelp = ITEMS.register("glowing_kelp", () -> new BlockItem(UGBlocks.glowing_kelp.get(), (new Item.Properties()).group(UGItemGroups.GROUP)));
    public static final RegistryObject<Item> goo_ball = ITEMS.register("goo_ball", GooBallItem::new);
    public static final RegistryObject<Item> rotten_blisterberry = ITEMS.register("rotten_blisterberry", RottenBlisterberryItem::new);
    public static final RegistryObject<Item> blisterbomb = ITEMS.register("blisterbomb", BlisterbombItem::new);
    public static final RegistryObject<Item> droopvine_item = ITEMS.register("droopvine_item", DroopvineItem::new);
    public static final RegistryObject<Item> carved_gloomgourd = ITEMS.register("carved_gloomgourd", CarvedGloomgourdItem::new);

    //boss loot
    public static final RegistryObject<Item> masticator_scales = ITEMS.register("masticator_scales", () -> new UndergardenItem(Rarity.UNCOMMON));
    public static final RegistryObject<Item> masticated_chestplate = ITEMS.register("masticated_chestplate", () -> new MasticatedChestplateItem(UGArmors.MASTICATED));
    public static final RegistryObject<Item> cloggrum_battleaxe = ITEMS.register("cloggrum_battleaxe", CloggrumBattleaxeItem::new);

    //tools
    public static final RegistryObject<SwordItem> smogstem_sword = ITEMS.register("smogstem_sword", () -> new UndergardenSwordItem(UGTools.SMOGSTEM));
    public static final RegistryObject<PickaxeItem> smogstem_pickaxe = ITEMS.register("smogstem_pickaxe", () -> new UndergardenPickaxeItem(UGTools.SMOGSTEM));
    public static final RegistryObject<AxeItem> smogstem_axe = ITEMS.register("smogstem_axe", () -> new UndergardenAxeItem(UGTools.SMOGSTEM, 6));
    public static final RegistryObject<ShovelItem> smogstem_shovel = ITEMS.register("smogstem_shovel", () -> new UndergardenShovelItem(UGTools.SMOGSTEM));
    public static final RegistryObject<HoeItem> smogstem_hoe = ITEMS.register("smogstem_hoe", () -> new UndergardenHoeItem(UGTools.SMOGSTEM, 0, -3.0F));

    public static final RegistryObject<SwordItem> cloggrum_sword = ITEMS.register("cloggrum_sword", () -> new UndergardenSwordItem(UGTools.CLOGGRUM));
    public static final RegistryObject<PickaxeItem> cloggrum_pickaxe = ITEMS.register("cloggrum_pickaxe", () -> new UndergardenPickaxeItem(UGTools.CLOGGRUM));
    public static final RegistryObject<AxeItem> cloggrum_axe = ITEMS.register("cloggrum_axe", () -> new UndergardenAxeItem(UGTools.CLOGGRUM, 3));
    public static final RegistryObject<ShovelItem> cloggrum_shovel = ITEMS.register("cloggrum_shovel", () -> new UndergardenShovelItem(UGTools.CLOGGRUM));
    public static final RegistryObject<HoeItem> cloggrum_hoe = ITEMS.register("cloggrum_hoe", () -> new UndergardenHoeItem(UGTools.CLOGGRUM, -2, -2.0F));
    public static final RegistryObject<ShieldItem> cloggrum_shield = ITEMS.register("cloggrum_shield", () -> new UndergardenShieldItem(UGShields.CLOGGRUM));

    public static final RegistryObject<SwordItem> froststeel_sword = ITEMS.register("froststeel_sword", () -> new UndergardenSwordItem(UGTools.FROSTSTEEL));
    public static final RegistryObject<PickaxeItem> froststeel_pickaxe = ITEMS.register("froststeel_pickaxe", () -> new UndergardenPickaxeItem(UGTools.FROSTSTEEL));
    public static final RegistryObject<AxeItem> froststeel_axe = ITEMS.register("froststeel_axe", () -> new UndergardenAxeItem(UGTools.FROSTSTEEL, 4));
    public static final RegistryObject<ShovelItem> froststeel_shovel = ITEMS.register("froststeel_shovel", () -> new UndergardenShovelItem(UGTools.FROSTSTEEL));
    public static final RegistryObject<HoeItem> froststeel_hoe = ITEMS.register("froststeel_hoe", () -> new UndergardenHoeItem(UGTools.FROSTSTEEL, -2, -1.0F));

    public static final RegistryObject<SwordItem> utheric_sword = ITEMS.register("utheric_sword", () -> new UndergardenSwordItem(UGTools.UTHERIC));
    public static final RegistryObject<PickaxeItem> utheric_pickaxe = ITEMS.register("utheric_pickaxe", () -> new UndergardenPickaxeItem(UGTools.UTHERIC));
    public static final RegistryObject<AxeItem> utheric_axe = ITEMS.register("utheric_axe", () -> new UndergardenAxeItem(UGTools.UTHERIC, 3.5F));
    public static final RegistryObject<ShovelItem> utheric_shovel = ITEMS.register("utheric_shovel", () -> new UndergardenShovelItem(UGTools.UTHERIC));
    public static final RegistryObject<HoeItem> utheric_hoe = ITEMS.register("utheric_hoe", () -> new UndergardenHoeItem(UGTools.UTHERIC, -3, 0.0F));

    public static final RegistryObject<Item> slingshot = ITEMS.register("slingshot", SlingshotItem::new);

    public static final RegistryObject<BucketItem> virulent_mix_bucket = ITEMS.register("virulent_mix_bucket", () -> new BucketItem(
            UGFluids.virulent_mix_source, (new Item.Properties()).group(UGItemGroups.GROUP).maxStackSize(1)));

    public static final RegistryObject<FishBucketItem> gwibling_bucket = ITEMS.register("gwibling_bucket", () -> new FishBucketItem(
            UGEntityTypes.gwibling, Fluids.WATER, (new Item.Properties()).maxStackSize(1).group(UGItemGroups.GROUP)));

    //armors
    public static final RegistryObject<Item> cloggrum_helmet = ITEMS.register("cloggrum_helmet", () -> new UndergardenArmorItem(UGArmors.CLOGGRUM, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> cloggrum_chestplate = ITEMS.register("cloggrum_chestplate", () -> new UndergardenArmorItem(UGArmors.CLOGGRUM, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> cloggrum_leggings = ITEMS.register("cloggrum_leggings", () -> new UndergardenArmorItem(UGArmors.CLOGGRUM, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> cloggrum_boots = ITEMS.register("cloggrum_boots", () -> new UndergardenArmorItem(UGArmors.CLOGGRUM, EquipmentSlotType.FEET));

    public static final RegistryObject<Item> froststeel_helmet = ITEMS.register("froststeel_helmet", () -> new UndergardenArmorItem(UGArmors.FROSTSTEEL, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> froststeel_chestplate = ITEMS.register("froststeel_chestplate", () -> new UndergardenArmorItem(UGArmors.FROSTSTEEL, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> froststeel_leggings = ITEMS.register("froststeel_leggings", () -> new UndergardenArmorItem(UGArmors.FROSTSTEEL, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> froststeel_boots = ITEMS.register("froststeel_boots", () -> new UndergardenArmorItem(UGArmors.FROSTSTEEL, EquipmentSlotType.FEET));

    public static final RegistryObject<Item> utheric_helmet = ITEMS.register("utheric_helmet", () -> new UndergardenArmorItem(UGArmors.UTHERIC, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> utheric_chestplate = ITEMS.register("utheric_chestplate", () -> new UndergardenArmorItem(UGArmors.UTHERIC, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> utheric_leggings = ITEMS.register("utheric_leggings", () -> new UndergardenArmorItem(UGArmors.UTHERIC, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> utheric_boots = ITEMS.register("utheric_boots", () -> new UndergardenArmorItem(UGArmors.UTHERIC, EquipmentSlotType.FEET));

    //foods
    public static final RegistryObject<Item> underbeans = ITEMS.register("underbeans", () -> new BlockNamedItem(
            UGBlocks.underbean_bush.get(), (new Item.Properties()).group(UGItemGroups.GROUP).food(UGFoods.UNDERBEANS)));
    public static final RegistryObject<Item> blisterberry = ITEMS.register("blisterberry", () -> new BlockNamedItem(
            UGBlocks.blisterberry_bush.get(), (new Item.Properties()).group(UGItemGroups.GROUP).food(UGFoods.BLISTERBERRY)));
    public static final RegistryObject<Item> gloomgourd_pie = ITEMS.register("gloomgourd_pie", () -> new UndergardenItem(UGFoods.GLOOMGOURD_PIE));
    public static final RegistryObject<Item> raw_dweller_meat = ITEMS.register("raw_dweller_meat", () -> new UndergardenItem(UGFoods.RAW_DWELLER));
    public static final RegistryObject<Item> dweller_steak = ITEMS.register("dweller_steak", () -> new UndergardenItem(UGFoods.COOKED_DWELLER));
    public static final RegistryObject<Item> raw_gwibling = ITEMS.register("raw_gwibling", () -> new UndergardenItem(UGFoods.RAW_GWIBLING));
    public static final RegistryObject<Item> cooked_gwibling = ITEMS.register("cooked_gwibling", () -> new UndergardenItem(UGFoods.COOKED_GWIBLING));

    //spawn eggs
    public static final RegistryObject<Item> dweller_spawn_egg = ITEMS.register("dweller_spawn_egg", () -> new UndergardenSpawnEggItem(UGEntityTypes.dweller, 4804417, 16776960));
    public static final RegistryObject<Item> gwibling_spawn_egg = ITEMS.register("gwibling_spawn_egg", () -> new UndergardenSpawnEggItem(UGEntityTypes.gwibling, 10064737, 15845330));
    public static final RegistryObject<Item> rotdweller_spawn_egg = ITEMS.register("rotdweller_spawn_egg", () -> new UndergardenSpawnEggItem(UGEntityTypes.rotdweller, 4804417, 16776960));
    public static final RegistryObject<Item> rotling_spawn_egg = ITEMS.register("rotling_spawn_egg", () -> new UndergardenSpawnEggItem(UGEntityTypes.rotling, 5988164, 10963256));
    public static final RegistryObject<Item> rotwalker_spawn_egg = ITEMS.register("rotwalker_spawn_egg", () -> new UndergardenSpawnEggItem(UGEntityTypes.rotwalker, 5988164, 10963256));
    public static final RegistryObject<Item> rotbeast_spawn_egg = ITEMS.register("rotbeast_spawn_egg", () -> new UndergardenSpawnEggItem(UGEntityTypes.rotbeast, 5988164,10963256));
    public static final RegistryObject<Item> brute_spawn_egg = ITEMS.register("brute_spawn_egg", () -> new UndergardenSpawnEggItem(UGEntityTypes.brute, 7035982, 4012083));
    public static final RegistryObject<Item> scintling_spawn_egg = ITEMS.register("scintling_spawn_egg", () -> new UndergardenSpawnEggItem(UGEntityTypes.scintling, 8556655, 6314558));
    public static final RegistryObject<Item> gloomper_spawn_egg = ITEMS.register("gloomper_spawn_egg", () -> new UndergardenSpawnEggItem(UGEntityTypes.gloomper, 4138045, 6579581));
    public static final RegistryObject<Item> stoneborn_spawn_egg = ITEMS.register("stoneborn_spawn_egg", () -> new UndergardenSpawnEggItem(UGEntityTypes.stoneborn, 2437409, 9502615));

    public static final RegistryObject<Item> masticator_spawn_egg = ITEMS.register("masticator_spawn_egg", () -> new UndergardenSpawnEggItem(UGEntityTypes.masticator, 2366466, 15881511));
}