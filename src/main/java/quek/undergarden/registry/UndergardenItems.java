package quek.undergarden.registry;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.item.*;
import quek.undergarden.item.armor.UndergardenArmorItem;
import quek.undergarden.item.tool.*;

@SuppressWarnings("unused")
public class UndergardenItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, UndergardenMod.MODID);

    public static final RegistryObject<Item> undergarden_portal_catalyst = ITEMS.register("undergarden_portal_catalyst", UndergardenPortalCatalystItem::new);
    public static final RegistryObject<Item> smogstem_stick = ITEMS.register("smogstem_stick", UndergardenItem::new);
    public static final RegistryObject<Item> cloggrum_ingot = ITEMS.register("cloggrum_ingot", UndergardenItem::new);
    public static final RegistryObject<Item> cloggrum_nugget = ITEMS.register("cloggrum_nugget", UndergardenItem::new);
    public static final RegistryObject<Item> froststeel_ingot = ITEMS.register("froststeel_ingot", UndergardenItem::new);
    public static final RegistryObject<Item> froststeel_nugget = ITEMS.register("froststeel_nugget", UndergardenItem::new);
    public static final RegistryObject<Item> utheric_shard = ITEMS.register("utheric_shard", UndergardenItem::new);
    public static final RegistryObject<Item> utherium_ingot = ITEMS.register("utherium_ingot", UndergardenItem::new);
    public static final RegistryObject<Item> utherium_chunk = ITEMS.register("utherium_chunk", UndergardenItem::new);
    public static final RegistryObject<Item> smogstem_torch = ITEMS.register("smogstem_torch", () -> new WallOrFloorItem(
            UndergardenBlocks.smogstem_torch.get(), UndergardenBlocks.smogstem_wall_torch.get(), new Item.Properties().group(UndergardenItemGroups.UNDERGARDEN_BLOCKS)));

    //tools
    public static final RegistryObject<SwordItem> smogstem_sword = ITEMS.register("smogstem_sword", () -> new UndergardenSword(UndergardenItemTiers.SMOGSTEM));
    public static final RegistryObject<PickaxeItem> smogstem_pickaxe = ITEMS.register("smogstem_pickaxe", () -> new UndergardenPickaxe(UndergardenItemTiers.SMOGSTEM));
    public static final RegistryObject<AxeItem> smogstem_axe = ITEMS.register("smogstem_axe", () -> new UndergardenAxe(UndergardenItemTiers.SMOGSTEM));
    public static final RegistryObject<ShovelItem> smogstem_shovel = ITEMS.register("smogstem_shovel", () -> new UndergardenShovel(UndergardenItemTiers.SMOGSTEM));

    public static final RegistryObject<SwordItem> cloggrum_sword = ITEMS.register("cloggrum_sword", () -> new UndergardenSword(UndergardenItemTiers.CLOGGRUM));
    public static final RegistryObject<PickaxeItem> cloggrum_pickaxe = ITEMS.register("cloggrum_pickaxe", () -> new UndergardenPickaxe(UndergardenItemTiers.CLOGGRUM));
    public static final RegistryObject<AxeItem> cloggrum_axe = ITEMS.register("cloggrum_axe", () -> new UndergardenAxe(UndergardenItemTiers.CLOGGRUM));
    public static final RegistryObject<ShovelItem> cloggrum_shovel = ITEMS.register("cloggrum_shovel", () -> new UndergardenShovel(UndergardenItemTiers.CLOGGRUM));

    public static final RegistryObject<SwordItem> froststeel_sword = ITEMS.register("froststeel_sword", () -> new UndergardenSword(UndergardenItemTiers.FROSTSTEEL));
    public static final RegistryObject<PickaxeItem> froststeel_pickaxe = ITEMS.register("froststeel_pickaxe", () -> new UndergardenPickaxe(UndergardenItemTiers.FROSTSTEEL));
    public static final RegistryObject<AxeItem> froststeel_axe = ITEMS.register("froststeel_axe", () -> new UndergardenAxe(UndergardenItemTiers.FROSTSTEEL));
    public static final RegistryObject<ShovelItem> froststeel_shovel = ITEMS.register("froststeel_shovel", () -> new UndergardenShovel(UndergardenItemTiers.FROSTSTEEL));

    public static final RegistryObject<SwordItem> utheric_sword = ITEMS.register("utheric_sword", () -> new UndergardenSword(UndergardenItemTiers.UTHERIC));
    public static final RegistryObject<PickaxeItem> utheric_pickaxe = ITEMS.register("utheric_pickaxe", () -> new UndergardenPickaxe(UndergardenItemTiers.UTHERIC));
    public static final RegistryObject<AxeItem> utheric_axe = ITEMS.register("utheric_axe", () -> new UndergardenAxe(UndergardenItemTiers.UTHERIC));
    public static final RegistryObject<ShovelItem> utheric_shovel = ITEMS.register("utheric_shovel", () -> new UndergardenShovel(UndergardenItemTiers.UTHERIC));

    //armors
    public static final RegistryObject<Item> cloggrum_helmet = ITEMS.register("cloggrum_helmet", () -> new UndergardenArmorItem(UndergardenArmorMaterials.CLOGGRUM, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> cloggrum_chestplate = ITEMS.register("cloggrum_chestplate", () -> new UndergardenArmorItem(UndergardenArmorMaterials.CLOGGRUM, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> cloggrum_leggings = ITEMS.register("cloggrum_leggings", () -> new UndergardenArmorItem(UndergardenArmorMaterials.CLOGGRUM, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> cloggrum_boots = ITEMS.register("cloggrum_boots", () -> new UndergardenArmorItem(UndergardenArmorMaterials.CLOGGRUM, EquipmentSlotType.FEET));

    //foods
    public static final RegistryObject<Item> underbeans = ITEMS.register("underbeans", () -> new BlockNamedItem(UndergardenBlocks.underbean_bush.get(), (new Item.Properties()).group(UndergardenItemGroups.UNDERGARDEN_FOODS).food(UndergardenFoods.UNDERBEANS)));
    public static final RegistryObject<Item> raw_dweller_meat = ITEMS.register("raw_dweller_meat", () -> new UndergardenItem(UndergardenFoods.RAW_DWELLER));
    public static final RegistryObject<Item> dweller_steak = ITEMS.register("dweller_steak", () -> new UndergardenItem(UndergardenFoods.COOKED_DWELLER));

    //spawn eggs
    //public static final RegistryObject<Item> rotwalker_spawn_egg = ITEMS.register("rotwalker_spawn_egg", () -> new UndergardenSpawnEgg(UndergardenEntities.rotwalker, 1, 1));
    //public static final RegistryObject<Item> rotbeast_spawn_egg = ITEMS.register("rotbeast_spawn_egg", () -> new UndergardenSpawnEgg(UndergardenEntities.rotbeast, 1,1));

}