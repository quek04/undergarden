package quek.undergarden.item.tool;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class BattleaxeItem extends SwordItem {

    private static final UUID ATTACK_KNOCKBACK_UUID = UUID.fromString("20D3EB3F-226F-4325-873E-9B0932E4E5C6");

    public BattleaxeItem(Tier tier, int attackDamage, float attackSpeed) {
        super(tier, attackDamage, attackSpeed, new Properties()
                .stacksTo(1)
                .defaultDurability(tier.getUses() * 3)
                .tab(UGItemGroups.GROUP)
                .rarity(Rarity.EPIC)
        );
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND) {
            return ImmutableMultimap.of(
                    Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", super.getDamage(), AttributeModifier.Operation.ADDITION),
                    Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -3.4F, AttributeModifier.Operation.ADDITION),
                    Attributes.ATTACK_KNOCKBACK, new AttributeModifier(ATTACK_KNOCKBACK_UUID, "Weapon modifier", 4.0F, AttributeModifier.Operation.ADDITION)
            );
        }
        else return super.getAttributeModifiers(slot, stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if(stack.getItem() == UGItems.FORGOTTEN_BATTLEAXE.get()) {
            tooltip.add(new TranslatableComponent("tooltip.forgotten_sword").withStyle(ChatFormatting.GREEN));
        }
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.category.canEnchant(stack.getItem()) && enchantment != Enchantments.KNOCKBACK;
    }
}