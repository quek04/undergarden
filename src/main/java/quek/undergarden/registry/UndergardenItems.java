package quek.undergarden.registry;

import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.item.*;
import quek.undergarden.item.armor.*;
import quek.undergarden.item.tool.*;

public class UndergardenItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UndergardenMod.MODID);

    //normal stuff
    public static final RegistryObject<Item> catalyst_item = ITEMS.register("catalyst", CatalystItem::new);
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
            UndergardenBlocks.smogstem_torch.get(), UndergardenBlocks.smogstem_wall_torch.get(), new Item.Properties().group(UndergardenItemGroups.UNDERGARDEN_BLOCKS)));
    public static final RegistryObject<Item> gloomgourd_seeds = ITEMS.register("gloomgourd_seeds", () -> new BlockNamedItem(
            UndergardenBlocks.gloomgourd_stem.get(), (new Item.Properties()).group(UndergardenItemGroups.UNDERGARDEN_ITEMS)));
    public static final RegistryObject<Item> ditchbulb = ITEMS.register("ditchbulb", UndergardenItem::new);
    public static final RegistryObject<Item> brute_tusk = ITEMS.register("brute_tusk", UndergardenItem::new);
    public static final RegistryObject<Item> glowing_kelp = ITEMS.register("glowing_kelp", () -> new BlockItem(UndergardenBlocks.glowing_kelp.get(), (new Item.Properties()).group(UndergardenItemGroups.UNDERGARDEN_BLOCKS)));
    public static final RegistryObject<Item> goo_ball = ITEMS.register("goo_ball", GooBallItem::new);
    public static final RegistryObject<Item> rotten_blisterberry = ITEMS.register("rotten_blisterberry", RottenBlisterberryItem::new);
    public static final RegistryObject<Item> blisterbomb = ITEMS.register("blisterbomb", BlisterbombItem::new);

    //boss loot
    public static final RegistryObject<Item> masticator_scales = ITEMS.register("masticator_scales", () -> new UndergardenItem(Rarity.UNCOMMON));
    public static final RegistryObject<Item> masticated_chestplate = ITEMS.register("masticated_chestplate", () -> new MasticatedChestplateItem(UndergardenArmorMaterials.MASTICATED));
    public static final RegistryObject<Item> cloggrum_battleaxe = ITEMS.register("cloggrum_battleaxe", CloggrumBattleaxeItem::new);

    //tools
    public static final RegistryObject<SwordItem> smogstem_sword = ITEMS.register("smogstem_sword", () -> new UndergardenSwordItem(UndergardenItemTiers.SMOGSTEM));
    public static final RegistryObject<PickaxeItem> smogstem_pickaxe = ITEMS.register("smogstem_pickaxe", () -> new UndergardenPickaxeItem(UndergardenItemTiers.SMOGSTEM));
    public static final RegistryObject<AxeItem> smogstem_axe = ITEMS.register("smogstem_axe", () -> new UndergardenAxeItem(UndergardenItemTiers.SMOGSTEM));
    public static final RegistryObject<ShovelItem> smogstem_shovel = ITEMS.register("smogstem_shovel", () -> new UndergardenShovelItem(UndergardenItemTiers.SMOGSTEM));
    public static final RegistryObject<HoeItem> smogstem_hoe = ITEMS.register("smogstem_hoe", () -> new UndergardenHoeItem(UndergardenItemTiers.SMOGSTEM, -3.0F));

    public static final RegistryObject<SwordItem> cloggrum_sword = ITEMS.register("cloggrum_sword", () -> new UndergardenSwordItem(UndergardenItemTiers.CLOGGRUM));
    public static final RegistryObject<PickaxeItem> cloggrum_pickaxe = ITEMS.register("cloggrum_pickaxe", () -> new UndergardenPickaxeItem(UndergardenItemTiers.CLOGGRUM));
    public static final RegistryObject<AxeItem> cloggrum_axe = ITEMS.register("cloggrum_axe", () -> new UndergardenAxeItem(UndergardenItemTiers.CLOGGRUM));
    public static final RegistryObject<ShovelItem> cloggrum_shovel = ITEMS.register("cloggrum_shovel", () -> new UndergardenShovelItem(UndergardenItemTiers.CLOGGRUM));
    public static final RegistryObject<HoeItem> cloggrum_hoe = ITEMS.register("cloggrum_hoe", () -> new UndergardenHoeItem(UndergardenItemTiers.CLOGGRUM, -2.0F));
    public static final RegistryObject<ShieldItem> cloggrum_shield = ITEMS.register("cloggrum_shield", () -> new UndergardenShieldItem(UndergardenShieldTiers.CLOGGRUM));

    public static final RegistryObject<SwordItem> froststeel_sword = ITEMS.register("froststeel_sword", () -> new UndergardenSwordItem(UndergardenItemTiers.FROSTSTEEL));
    public static final RegistryObject<PickaxeItem> froststeel_pickaxe = ITEMS.register("froststeel_pickaxe", () -> new UndergardenPickaxeItem(UndergardenItemTiers.FROSTSTEEL));
    public static final RegistryObject<AxeItem> froststeel_axe = ITEMS.register("froststeel_axe", () -> new UndergardenAxeItem(UndergardenItemTiers.FROSTSTEEL));
    public static final RegistryObject<ShovelItem> froststeel_shovel = ITEMS.register("froststeel_shovel", () -> new UndergardenShovelItem(UndergardenItemTiers.FROSTSTEEL));
    public static final RegistryObject<HoeItem> froststeel_hoe = ITEMS.register("froststeel_hoe", () -> new UndergardenHoeItem(UndergardenItemTiers.FROSTSTEEL, -1.0F));

    public static final RegistryObject<SwordItem> utheric_sword = ITEMS.register("utheric_sword", () -> new UndergardenSwordItem(UndergardenItemTiers.UTHERIC));
    public static final RegistryObject<PickaxeItem> utheric_pickaxe = ITEMS.register("utheric_pickaxe", () -> new UndergardenPickaxeItem(UndergardenItemTiers.UTHERIC));
    public static final RegistryObject<AxeItem> utheric_axe = ITEMS.register("utheric_axe", () -> new UndergardenAxeItem(UndergardenItemTiers.UTHERIC));
    public static final RegistryObject<ShovelItem> utheric_shovel = ITEMS.register("utheric_shovel", () -> new UndergardenShovelItem(UndergardenItemTiers.UTHERIC));
    public static final RegistryObject<HoeItem> utheric_hoe = ITEMS.register("utheric_hoe", () -> new UndergardenHoeItem(UndergardenItemTiers.UTHERIC, 0.0F));

    public static final RegistryObject<Item> slingshot = ITEMS.register("slingshot", SlingshotItem::new);

    public static final RegistryObject<BucketItem> virulent_mix_bucket = ITEMS.register("virulent_mix_bucket", () -> new BucketItem(
            UndergardenFluids.virulent_mix_source, (new Item.Properties()).group(UndergardenItemGroups.UNDERGARDEN_GEAR).maxStackSize(1)));

    public static final RegistryObject<FishBucketItem> gwibling_bucket = ITEMS.register("gwibling_bucket", () -> new FishBucketItem(
            UndergardenEntities.gwibling, Fluids.WATER, (new Item.Properties()).maxStackSize(1).group(UndergardenItemGroups.UNDERGARDEN_GEAR)));

    //armors
    public static final RegistryObject<Item> cloggrum_helmet = ITEMS.register("cloggrum_helmet", () -> new UndergardenArmorItem(UndergardenArmorMaterials.CLOGGRUM, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> cloggrum_chestplate = ITEMS.register("cloggrum_chestplate", () -> new UndergardenArmorItem(UndergardenArmorMaterials.CLOGGRUM, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> cloggrum_leggings = ITEMS.register("cloggrum_leggings", () -> new UndergardenArmorItem(UndergardenArmorMaterials.CLOGGRUM, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> cloggrum_boots = ITEMS.register("cloggrum_boots", () -> new UndergardenArmorItem(UndergardenArmorMaterials.CLOGGRUM, EquipmentSlotType.FEET));

    public static final RegistryObject<Item> froststeel_helmet = ITEMS.register("froststeel_helmet", () -> new UndergardenArmorItem(UndergardenArmorMaterials.FROSTSTEEL, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> froststeel_chestplate = ITEMS.register("froststeel_chestplate", () -> new UndergardenArmorItem(UndergardenArmorMaterials.FROSTSTEEL, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> froststeel_leggings = ITEMS.register("froststeel_leggings", () -> new UndergardenArmorItem(UndergardenArmorMaterials.FROSTSTEEL, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> froststeel_boots = ITEMS.register("froststeel_boots", () -> new UndergardenArmorItem(UndergardenArmorMaterials.FROSTSTEEL, EquipmentSlotType.FEET));

    public static final RegistryObject<Item> utheric_helmet = ITEMS.register("utheric_helmet", () -> new UndergardenArmorItem(UndergardenArmorMaterials.UTHERIC, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> utheric_chestplate = ITEMS.register("utheric_chestplate", () -> new UndergardenArmorItem(UndergardenArmorMaterials.UTHERIC, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> utheric_leggings = ITEMS.register("utheric_leggings", () -> new UndergardenArmorItem(UndergardenArmorMaterials.UTHERIC, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> utheric_boots = ITEMS.register("utheric_boots", () -> new UndergardenArmorItem(UndergardenArmorMaterials.UTHERIC, EquipmentSlotType.FEET));

    //foods
    public static final RegistryObject<Item> underbeans = ITEMS.register("underbeans", () -> new BlockNamedItem(
            UndergardenBlocks.underbean_bush.get(), (new Item.Properties()).group(UndergardenItemGroups.UNDERGARDEN_ITEMS).food(UndergardenFoods.UNDERBEANS)));
    public static final RegistryObject<Item> blisterberry = ITEMS.register("blisterberry", () -> new BlockNamedItem(
            UndergardenBlocks.blisterberry_bush.get(), (new Item.Properties()).group(UndergardenItemGroups.UNDERGARDEN_ITEMS).food(UndergardenFoods.BLISTERBERRY)));
    public static final RegistryObject<Item> gloomgourd_pie = ITEMS.register("gloomgourd_pie", () -> new UndergardenItem(UndergardenFoods.GLOOMGOURD_PIE));
    public static final RegistryObject<Item> raw_dweller_meat = ITEMS.register("raw_dweller_meat", () -> new UndergardenItem(UndergardenFoods.RAW_DWELLER));
    public static final RegistryObject<Item> dweller_steak = ITEMS.register("dweller_steak", () -> new UndergardenItem(UndergardenFoods.COOKED_DWELLER));
    public static final RegistryObject<Item> raw_gwibling = ITEMS.register("raw_gwibling", () -> new UndergardenItem(UndergardenFoods.RAW_GWIBLING));
    public static final RegistryObject<Item> cooked_gwibling = ITEMS.register("cooked_gwibling", () -> new UndergardenItem(UndergardenFoods.COOKED_GWIBLING));

    //spawn eggs
    public static final RegistryObject<Item> dweller_spawn_egg = ITEMS.register("dweller_spawn_egg", () -> new UndergardenSpawnEggItem(UndergardenEntities.dweller, 4804417, 16776960));
    public static final RegistryObject<Item> gwibling_spawn_egg = ITEMS.register("gwibling_spawn_egg", () -> new UndergardenSpawnEggItem(UndergardenEntities.gwibling, 10064737, 15845330));
    public static final RegistryObject<Item> rotdweller_spawn_egg = ITEMS.register("rotdweller_spawn_egg", () -> new UndergardenSpawnEggItem(UndergardenEntities.rotdweller, 72451, 10963256));
    public static final RegistryObject<Item> rotwalker_spawn_egg = ITEMS.register("rotwalker_spawn_egg", () -> new UndergardenSpawnEggItem(UndergardenEntities.rotwalker, 5988164, 10963256));
    public static final RegistryObject<Item> rotbeast_spawn_egg = ITEMS.register("rotbeast_spawn_egg", () -> new UndergardenSpawnEggItem(UndergardenEntities.rotbeast, 3751199,7153174));
    public static final RegistryObject<Item> brute_spawn_egg = ITEMS.register("brute_spawn_egg", () -> new UndergardenSpawnEggItem(UndergardenEntities.brute, 7035982, 4012083));
    public static final RegistryObject<Item> scintling_spawn_egg = ITEMS.register("scintling_spawn_egg", () -> new UndergardenSpawnEggItem(UndergardenEntities.scintling, 8556655, 6314558));
    public static final RegistryObject<Item> blisterbomber_spawn_egg = ITEMS.register("blisterbomber_spawn_egg", () -> new UndergardenSpawnEggItem(UndergardenEntities.blisterbomber, 3375672, 16034133));
    public static final RegistryObject<Item> gloomper_spawn_egg = ITEMS.register("gloomper_spawn_egg", () -> new UndergardenSpawnEggItem(UndergardenEntities.gloomper, 4138045, 6579581));

    public static final RegistryObject<Item> masticator_spawn_egg = ITEMS.register("masticator_spawn_egg", () -> new UndergardenSpawnEggItem(UndergardenEntities.masticator, 2366466, 15881511));
}