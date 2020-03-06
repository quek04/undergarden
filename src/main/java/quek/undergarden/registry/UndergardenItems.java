package quek.undergarden.registry;

import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.item.*;
import quek.undergarden.item.tool.*;

import static quek.undergarden.Undergarden.MODID;
import static quek.undergarden.registry.UndergardenEntities.*;
import static quek.undergarden.registry.UndergardenFoods.*;
import static quek.undergarden.registry.UndergardenToolMaterials.*;

@SuppressWarnings("unused")
public class UndergardenItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> smogstem_stick = ITEMS.register("smogstem_stick", UndergardenItem::new);
    public static final RegistryObject<Item> cloggrum_ingot = ITEMS.register("cloggrum_ingot", UndergardenItem::new);
    public static final RegistryObject<Item> cloggrum_nugget = ITEMS.register("cloggrum_nugget", UndergardenItem::new);
    public static final RegistryObject<Item> utheric_shard = ITEMS.register("utheric_shard", UndergardenItem::new);
    public static final RegistryObject<Item> utherium_ingot = ITEMS.register("utherium_ingot", UndergardenItem::new);
    public static final RegistryObject<Item> utherium_chunk = ITEMS.register("utherium_chunk", UndergardenItem::new);

    //tools
    public static final RegistryObject<SwordItem> smogstem_sword = ITEMS.register("smogstem_sword", () -> new UndergardenSword(SMOGSTEM));
    public static final RegistryObject<PickaxeItem> smogstem_pickaxe = ITEMS.register("smogstem_pickaxe", () -> new UndergardenPickaxe(SMOGSTEM));
    public static final RegistryObject<AxeItem> smogstem_axe = ITEMS.register("smogstem_axe", () -> new UndergardenAxe(SMOGSTEM));
    public static final RegistryObject<ShovelItem> smogstem_shovel = ITEMS.register("smogstem_shovel", () -> new UndergardenShovel(SMOGSTEM));

    public static final RegistryObject<SwordItem> cloggrum_sword = ITEMS.register("cloggrum_sword", () -> new UndergardenSword(CLOGGRUM));
    public static final RegistryObject<PickaxeItem> cloggrum_pickaxe = ITEMS.register("cloggrum_pickaxe", () -> new UndergardenPickaxe(CLOGGRUM));
    public static final RegistryObject<AxeItem> cloggrum_axe = ITEMS.register("cloggrum_axe", () -> new UndergardenAxe(CLOGGRUM));
    public static final RegistryObject<ShovelItem> cloggrum_shovel = ITEMS.register("cloggrum_shovel", () -> new UndergardenShovel(CLOGGRUM));

    public static final RegistryObject<SwordItem> utheric_sword = ITEMS.register("utheric_sword", () -> new UndergardenSword(UTHERIC));
    public static final RegistryObject<PickaxeItem> utheric_pickaxe = ITEMS.register("utheric_pickaxe", () -> new UndergardenPickaxe(UTHERIC));
    public static final RegistryObject<AxeItem> utheric_axe = ITEMS.register("utheric_axe", () -> new UndergardenAxe(UTHERIC));
    public static final RegistryObject<ShovelItem> utheric_shovel = ITEMS.register("utheric_shovel", () -> new UndergardenShovel(UTHERIC));

    //foods
    public static final RegistryObject<Item> underbeans = ITEMS.register("underbeans", () -> new BlockNamedItem(UndergardenBlocks.underbean_bush.get(), (new Item.Properties()).group(UndergardenItemGroups.UNDERGARDEN_FOODS).food(UNDERBEANS)));
    public static final RegistryObject<Item> raw_dweller_meat = ITEMS.register("raw_dweller_meat", () -> new UndergardenItem(RAW_DWELLER));
    public static final RegistryObject<Item> dweller_steak = ITEMS.register("dweller_steak", () -> new UndergardenItem(COOKED_DWELLER));

    //spawn eggs
    public static final RegistryObject<Item> rotwalker_spawn_egg = ITEMS.register("rotwalker_spawn_egg", () -> new UndergardenSpawnEgg(rotwalker, 1, 1));
    public static final RegistryObject<Item> rotbeast_spawn_egg = ITEMS.register("rotbeast_spawn_egg", () -> new UndergardenSpawnEgg(rotbeast, 1,1));

}