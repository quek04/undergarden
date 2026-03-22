package quek.undergarden.item.tool;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import quek.undergarden.Undergarden;

public class BattleaxeItem extends Item {

	private static final Identifier ATTACK_KNOCKBACK_ID = Undergarden.prefix("attack_knockback");

	public BattleaxeItem(Item.Properties properties) {
		super(properties);
	}

	public static Item.Properties createBattleaxeProperties(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, Item.Properties properties)  {
		return properties
			.durability(material.durability())
			.repairable(material.repairItems())
			.enchantable(material.enchantmentValue())
			.attributes(createAttributes(material, attackDamageBaseline, attackSpeedBaseline))
			.component(DataComponents.WEAPON, new Weapon(1));
	}

	public static ItemAttributeModifiers createAttributes(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
		return ItemAttributeModifiers.builder()
			.add(
				Attributes.ATTACK_DAMAGE,
				new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, attackDamageBaseline + material.attackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND
			)
			.add(
				Attributes.ATTACK_SPEED,
				new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, attackSpeedBaseline, AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND
			)
			.add(
				Attributes.ATTACK_KNOCKBACK,
				new AttributeModifier(ATTACK_KNOCKBACK_ID, 4.0F, AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND
			)
			.build();
	}

	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
		return !enchantment.is(Enchantments.KNOCKBACK) && super.supportsEnchantment(stack, enchantment);
	}
}