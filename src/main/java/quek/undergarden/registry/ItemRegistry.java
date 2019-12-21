package quek.undergarden.registry;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.item.UndergardenItem;
import quek.undergarden.item.tool.UndergardenAxe;
import quek.undergarden.item.tool.UndergardenPickaxe;
import quek.undergarden.item.tool.UndergardenShovel;
import quek.undergarden.item.tool.UndergardenSword;

import static quek.undergarden.Undergarden.MODID;
import static quek.undergarden.registry.UndergardenToolMaterials.SMOGSTEM;

@SuppressWarnings("unused")
public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> smogstem_stick = ITEMS.register("smogstem_stick", UndergardenItem::new);
    public static final RegistryObject<Item> cloggrum_ingot = ITEMS.register("cloggrum_ingot", UndergardenItem::new);

    //tools
    public static final RegistryObject<Item> smogstem_sword = ITEMS.register("smogstem_sword", () -> new UndergardenSword(SMOGSTEM));
    public static final RegistryObject<Item> smogstem_pickaxe = ITEMS.register("smogstem_pickaxe", () -> new UndergardenPickaxe(SMOGSTEM));
    public static final RegistryObject<Item> smogstem_axe = ITEMS.register("smogstem_axe", () -> new UndergardenAxe(SMOGSTEM));
    public static final RegistryObject<Item> smogstem_shovel = ITEMS.register("smogstem_shovel", () -> new UndergardenShovel(SMOGSTEM));
}