package quek.undergarden.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.registry.UGArmorMaterials;

public class DenizenMaskItem extends ArmorItem {
	public DenizenMaskItem(Properties properties) {
		super(UGArmorMaterials.DENIZEN_MASK, Type.HELMET, properties);
	}
	@Override
	public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		return "undergarden:textures/armor/denizen_mask.png";
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return ImmutableMultimap.of();
	}
}