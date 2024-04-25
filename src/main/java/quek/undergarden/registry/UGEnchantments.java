package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.item.enchantment.LongevityEnchantment;
import quek.undergarden.item.enchantment.RicochetEnchantment;
import quek.undergarden.item.enchantment.SelfSlingEnchantment;

public class UGEnchantments {

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, Undergarden.MODID);

	public static final DeferredHolder<Enchantment, Enchantment> RICOCHET = ENCHANTMENTS.register("ricochet", () -> new RicochetEnchantment(
		Enchantment.definition(
			UGTags.Items.SLINGSHOT_ENCHANTABLE,
			1,
			3,
			Enchantment.dynamicCost(1, 10),
			Enchantment.dynamicCost(1, 15),
			1,
			EquipmentSlot.MAINHAND
		)
	));
	public static final DeferredHolder<Enchantment, Enchantment> LONGEVITY = ENCHANTMENTS.register("longevity", () -> new LongevityEnchantment(
		Enchantment.definition(
			UGTags.Items.SLINGSHOT_ENCHANTABLE,
			1,
			3,
			Enchantment.dynamicCost(1, 10),
			Enchantment.dynamicCost(1, 15),
			1,
			EquipmentSlot.MAINHAND
		)
	));
	public static final DeferredHolder<Enchantment, Enchantment> SELF_SLING = ENCHANTMENTS.register("self_sling", () -> new SelfSlingEnchantment(
		Enchantment.definition(
			UGTags.Items.SLINGSHOT_ENCHANTABLE,
			1,
			1,
			Enchantment.constantCost(20),
			Enchantment.constantCost(50),
			1,
			EquipmentSlot.MAINHAND
		)
	));
}