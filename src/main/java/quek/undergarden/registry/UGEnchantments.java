package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.item.enchantment.LongevityEnchantment;
import quek.undergarden.item.enchantment.RicochetEnchantment;
import quek.undergarden.item.enchantment.SelfSlingEnchantment;
import quek.undergarden.item.tool.slingshot.SlingshotItem;

public class UGEnchantments {

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, Undergarden.MODID);

	public static final EnchantmentCategory SLINGSHOT = EnchantmentCategory.create("slingshot", (item -> item instanceof SlingshotItem));

	public static final DeferredHolder<Enchantment, Enchantment> RICOCHET = ENCHANTMENTS.register("ricochet", () -> new RicochetEnchantment(Enchantment.Rarity.UNCOMMON, SLINGSHOT, EquipmentSlot.MAINHAND));
	public static final DeferredHolder<Enchantment, Enchantment> LONGEVITY = ENCHANTMENTS.register("longevity", () -> new LongevityEnchantment(Enchantment.Rarity.COMMON, SLINGSHOT, EquipmentSlot.MAINHAND));
	public static final DeferredHolder<Enchantment, Enchantment> SELF_SLING = ENCHANTMENTS.register("self_sling", () -> new SelfSlingEnchantment(Enchantment.Rarity.RARE, SLINGSHOT, EquipmentSlot.MAINHAND));
}
