package quek.undergarden.item.armor;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import quek.undergarden.registry.UGArmorMaterials;
import quek.undergarden.registry.UGItems;

import java.util.List;
import java.util.UUID;

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
			tooltip.add(Component.translatable("tooltip.cloggrum_boots").withStyle(ChatFormatting.GRAY));
		}
	}

	@Override
	public ItemAttributeModifiers getDefaultAttributeModifiers() {
		if (this.getMaterial() == UGArmorMaterials.FROSTSTEEL) {
			UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(this.getType());
			return super.getDefaultAttributeModifiers().withModifierAdded(
				Attributes.MOVEMENT_SPEED,
				new AttributeModifier(uuid, "Froststeel slownness", -0.05F,
					AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
				EquipmentSlotGroup.bySlot(this.getEquipmentSlot())
			);
		} else return super.getDefaultAttributeModifiers();
	}
}