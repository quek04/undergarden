package quek.undergarden.registry;

import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.item.*;
import quek.undergarden.item.tool.*;

import static quek.undergarden.Undergarden.MODID;
import static quek.undergarden.registry.UndergardenEntities.*;
import static quek.undergarden.registry.UndergardenToolMaterials.*;

@SuppressWarnings("unused")
public class UndergardenItems {

    public static final Tag<Item> SMOGSTEM_PLANKS = new ItemTags.Wrapper(new ResourceLocation(MODID, "smogstem_planks"));

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> smogstem_stick = ITEMS.register("smogstem_stick", UndergardenItem::new);
    public static final RegistryObject<Item> cloggrum_ingot = ITEMS.register("cloggrum_ingot", UndergardenItem::new);
    public static final RegistryObject<Item> utheric_shard = ITEMS.register("utheric_shard", UndergardenItem::new);

    //tools
    public static final RegistryObject<Item> smogstem_sword = ITEMS.register("smogstem_sword", () -> new UndergardenSword(SMOGSTEM));
    public static final RegistryObject<Item> smogstem_pickaxe = ITEMS.register("smogstem_pickaxe", () -> new UndergardenPickaxe(SMOGSTEM));
    public static final RegistryObject<Item> smogstem_axe = ITEMS.register("smogstem_axe", () -> new UndergardenAxe(SMOGSTEM));
    public static final RegistryObject<Item> smogstem_shovel = ITEMS.register("smogstem_shovel", () -> new UndergardenShovel(SMOGSTEM));

    public static final RegistryObject<Item> cloggrum_sword = ITEMS.register("cloggrum_sword", () -> new UndergardenSword(CLOGGRUM));
    public static final RegistryObject<Item> cloggrum_pickaxe = ITEMS.register("cloggrum_pickaxe", () -> new UndergardenPickaxe(CLOGGRUM));
    public static final RegistryObject<Item> cloggrum_axe = ITEMS.register("cloggrum_axe", () -> new UndergardenAxe(CLOGGRUM));
    public static final RegistryObject<Item> cloggrum_shovel = ITEMS.register("cloggrum_shovel", () -> new UndergardenShovel(CLOGGRUM));

    public static final RegistryObject<Item> utheric_sword = ITEMS.register("utheric_sword", () -> new UndergardenSword(UTHERIC));
    public static final RegistryObject<Item> utheric_pickaxe = ITEMS.register("utheric_pickaxe", () -> new UndergardenPickaxe(UTHERIC));
    public static final RegistryObject<Item> utheric_axe = ITEMS.register("utheric_axe", () -> new UndergardenAxe(UTHERIC));
    public static final RegistryObject<Item> utheric_shovel = ITEMS.register("utheric_shovel", () -> new UndergardenShovel(UTHERIC));

    //spawn eggs
    public static final RegistryObject<Item> rotwalker_spawn_egg = ITEMS.register("rotwalker_spawn_egg", () -> new UndergardenSpawnEgg(rotwalker, 1, 1));

}