package quek.undergarden.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import quek.undergarden.registry.UGItems;

import java.util.List;
import java.util.UUID;

public class BattleaxeItem extends SwordItem {

	private static final UUID ATTACK_KNOCKBACK_UUID = UUID.fromString("20D3EB3F-226F-4325-873E-9B0932E4E5C6");

	public BattleaxeItem(Tier tier, Properties properties) {
		super(tier, properties);
	}

	public static ItemAttributeModifiers createAttributes(Tier tier, int damage, float speed) {
		return ItemAttributeModifiers.builder()
			.add(
				Attributes.ATTACK_DAMAGE,
				new AttributeModifier(
					BASE_ATTACK_DAMAGE_UUID,
					"Weapon modifier",
					(float)damage + tier.getAttackDamageBonus(),
					AttributeModifier.Operation.ADD_VALUE
				),
				EquipmentSlotGroup.MAINHAND
			)
			.add(
				Attributes.ATTACK_SPEED,
				new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", speed, AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND
			)
			.add(
				Attributes.ATTACK_KNOCKBACK,
				new AttributeModifier(ATTACK_KNOCKBACK_UUID, "Weapon modifier", 4.0F, AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND
			)
			.build();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		if (stack.getItem() == UGItems.FORGOTTEN_BATTLEAXE.get()) {
			tooltip.add(Component.translatable("tooltip.undergarden.forgotten_weapon").withStyle(ChatFormatting.GREEN));
		}
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment.canEnchant(stack) && enchantment != Enchantments.KNOCKBACK;
	}
}