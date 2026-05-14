package quek.undergarden.item.armor;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorType;
import quek.undergarden.registry.UGArmorMaterials;

public class FroststeelBootsItem extends Item {

	public FroststeelBootsItem(Properties properties) {
		super(properties);
	}

	@Override
	public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
		return true;
	}

	public static ItemAttributeModifiers createFroststeelAttributes(ArmorType type) {
		Identifier modifierId = Identifier.withDefaultNamespace("armor." + type.getName());
		return UGArmorMaterials.FROSTSTEEL.createAttributes(type)
			.withModifierAdded(Attributes.MOVEMENT_SPEED, new AttributeModifier(modifierId, -0.05F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.bySlot(type.getSlot()));
	}
}
