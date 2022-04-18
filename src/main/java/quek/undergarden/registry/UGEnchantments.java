package quek.undergarden.registry;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.item.enchantment.LongevityEnchantment;
import quek.undergarden.item.enchantment.RicochetEnchantment;
import quek.undergarden.item.enchantment.SelfSlingEnchantment;
import quek.undergarden.item.tool.slingshot.SlingshotItem;

public class UGEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Undergarden.MODID);

    public static final EnchantmentCategory SLINGSHOT = EnchantmentCategory.create("slingshot", (item -> item instanceof SlingshotItem));

    public static final RegistryObject<Enchantment> RICOCHET = ENCHANTMENTS.register("ricochet", () -> new RicochetEnchantment(Enchantment.Rarity.UNCOMMON, SLINGSHOT, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> LONGEVITY = ENCHANTMENTS.register("longevity", () -> new LongevityEnchantment(Enchantment.Rarity.COMMON, SLINGSHOT, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> SELF_SLING = ENCHANTMENTS.register("self_sling", () -> new SelfSlingEnchantment(Enchantment.Rarity.RARE, SLINGSHOT, EquipmentSlot.MAINHAND));
}
