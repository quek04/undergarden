package quek.undergarden.item.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGArmorMaterials;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class UndergardenArmorItem extends ArmorItem {

	public UndergardenArmorItem(ArmorMaterial material, Type slot) {
		super(material, slot, new Properties()
		);
	}

	@Override
	public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
		return stack.getItem().asItem() == UGItems.FROSTSTEEL_BOOTS.get();
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
		if (stack.getItem() == UGItems.CLOGGRUM_BOOTS.get()) {
			tooltip.add(Component.translatable("tooltip.cloggrum_boots").withStyle(ChatFormatting.GRAY));
		}
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		if (this.getMaterial() == UGArmorMaterials.FROSTSTEEL && slot == this.getEquipmentSlot()) {
			UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(this.getType());
			return ImmutableMultimap.of(
					Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Froststeel slowness", -0.05, AttributeModifier.Operation.MULTIPLY_BASE),
					Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", UGArmorMaterials.FROSTSTEEL.getDefenseForType(this.getType()), AttributeModifier.Operation.ADDITION),
					Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", UGArmorMaterials.FROSTSTEEL.getToughness(), AttributeModifier.Operation.ADDITION),
					Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", UGArmorMaterials.FROSTSTEEL.getKnockbackResistance(), AttributeModifier.Operation.ADDITION)
			);
		}
		return super.getDefaultAttributeModifiers(slot);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String layer) {
		if (slot == EquipmentSlot.LEGS) {
			return "undergarden:textures/armor/" + material.getName() + "_layer_2.png";
		} else {
			return "undergarden:textures/armor/" + material.getName() + "_layer_1.png";
		}
	}
}