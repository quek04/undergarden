package quek.undergarden.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGItems;

import java.util.List;

public class BattleaxeItem extends SwordItem {

	private static final ResourceLocation ATTACK_KNOCKBACK_ID = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "attack_knockback");

	public BattleaxeItem(Tier tier, Properties properties) {
		super(tier, properties);
	}

	public static ItemAttributeModifiers createAttributes(Tier tier, int damage, float speed) {
		return ItemAttributeModifiers.builder()
			.add(
				Attributes.ATTACK_DAMAGE,
				new AttributeModifier(BASE_ATTACK_DAMAGE_ID, (float) damage + tier.getAttackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND
			)
			.add(
				Attributes.ATTACK_SPEED,
				new AttributeModifier(BASE_ATTACK_SPEED_ID, speed, AttributeModifier.Operation.ADD_VALUE),
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
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		if (stack.getItem() == UGItems.FORGOTTEN_BATTLEAXE.get()) {
			tooltip.add(Component.translatable("tooltip.undergarden.forgotten_weapon").withStyle(ChatFormatting.GREEN));
		}
	}

	/*@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment.canEnchant(stack) && enchantment != Enchantments.KNOCKBACK;
	}*/

	@Override
	public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
		return !enchantment.is(Enchantments.KNOCKBACK);
	}
}