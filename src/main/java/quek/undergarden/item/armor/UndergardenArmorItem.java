package quek.undergarden.item.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGArmors;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class UndergardenArmorItem extends ArmorItem {

    private static final UUID[] ARMOR_MODIFIERS = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

    public UndergardenArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot) {
        super(materialIn, slot, new Properties()
                .group(UGItemGroups.GROUP)
        );
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(stack.getItem() == UGItems.cloggrum_boots.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.cloggrum_boots").mergeStyle(TextFormatting.GRAY));
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
        if(this.getArmorMaterial() == UGArmors.FROSTSTEEL && equipmentSlot == this.slot) {
            return ImmutableMultimap.of(
                    Attributes.MOVEMENT_SPEED, new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "froststeel_slowness", -0.05, AttributeModifier.Operation.MULTIPLY_BASE),
                    Attributes.ARMOR, new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "froststeel_armor_value", UGArmors.FROSTSTEEL.getDamageReductionAmount(this.slot), AttributeModifier.Operation.ADDITION),
                    Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "froststeel_armor_toughness_value", UGArmors.FROSTSTEEL.getToughness(), AttributeModifier.Operation.ADDITION)
            );
        }
        return super.getAttributeModifiers(equipmentSlot);
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlotType slot, String layer) {
        if (slot == EquipmentSlotType.LEGS) {
            return "undergarden:textures/models/armor/" + material.getName() + "_layer_2.png";
        } else {
            return "undergarden:textures/models/armor/" + material.getName() + "_layer_1.png";
        }
    }

}
