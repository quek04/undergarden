package quek.undergarden.item.armor;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import quek.undergarden.registry.UGItems;

import java.util.List;

public class UndergardenArmorItem extends ArmorItem {

	public UndergardenArmorItem(Holder<ArmorMaterial> armorMaterial, Type type, Properties properties) {
		super(armorMaterial, type, properties);
	}

	@Override
	public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
		return stack.getItem().asItem() == UGItems.FROSTSTEEL_BOOTS.get();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		if (stack.getItem() == UGItems.CLOGGRUM_BOOTS.get()) {
			tooltip.add(Component.translatable("tooltip.undergarden.cloggrum_boots").withStyle(ChatFormatting.GRAY));
		}
	}

	public static ItemAttributeModifiers createFroststeelAttributes(ArmorItem.Type type, int armor) {
		ResourceLocation armorLocation = ResourceLocation.withDefaultNamespace("armor." + type.getName());
		EquipmentSlotGroup group = EquipmentSlotGroup.bySlot(type.getSlot());
		return ItemAttributeModifiers.builder()
			.add(Attributes.ARMOR, new AttributeModifier(armorLocation, armor, AttributeModifier.Operation.ADD_VALUE), group)
			.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(armorLocation, 4.0F, AttributeModifier.Operation.ADD_VALUE), group)
			.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(armorLocation, 0.05F, AttributeModifier.Operation.ADD_VALUE), group)
			.add(Attributes.MOVEMENT_SPEED, new AttributeModifier(armorLocation, -0.05F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), group)
			.build();
	}
}