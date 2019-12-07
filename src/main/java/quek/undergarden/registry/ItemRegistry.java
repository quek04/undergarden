package quek.undergarden.registry;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.item.UndergardenItem;

import static quek.undergarden.Undergarden.MODID;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> smogstem_stick = ITEMS.register("smogstem_stick", UndergardenItem::new);
}
