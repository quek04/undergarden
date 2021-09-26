package quek.undergarden.item.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGArmors;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

import net.minecraft.world.item.Item.Properties;

public class UndergardenArmorItem extends ArmorItem {

    private static final UUID[] ARMOR_MODIFIERS = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

    public UndergardenArmorItem(ArmorMaterial materialIn, EquipmentSlot slot) {
        super(materialIn, slot, new Properties()
                .tab(UGItemGroups.GROUP)
        );
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(stack.getItem() == UGItems.CLOGGRUM_BOOTS.get()) {
            tooltip.add(new TranslatableComponent("tooltip.cloggrum_boots").withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot, ItemStack stack) {
        if(this.getMaterial() == UGArmors.FROSTSTEEL && equipmentSlot == this.slot) {
            return ImmutableMultimap.of(
                    Attributes.MOVEMENT_SPEED, new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "froststeel_slowness", -0.05, AttributeModifier.Operation.MULTIPLY_BASE),
                    Attributes.ARMOR, new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "froststeel_armor_value", UGArmors.FROSTSTEEL.getDefenseForSlot(this.slot), AttributeModifier.Operation.ADDITION),
                    Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "froststeel_armor_toughness_value", UGArmors.FROSTSTEEL.getToughness(), AttributeModifier.Operation.ADDITION)
            );
        }
        return super.getDefaultAttributeModifiers(equipmentSlot);
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlot slot, String layer) {
        if (slot == EquipmentSlot.LEGS) {
            return "undergarden:textures/armor/" + material.getName() + "_layer_2.png";
        } else {
            return "undergarden:textures/armor/" + material.getName() + "_layer_1.png";
        }
    }

}