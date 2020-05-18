package quek.undergarden.item.tool;

import com.google.common.collect.Multimap;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import quek.undergarden.registry.UndergardenItemGroups;
import quek.undergarden.registry.UndergardenItemTiers;

public class CloggrumBattleaxeItem extends SwordItem {

    public CloggrumBattleaxeItem() {
        super(UndergardenItemTiers.CLOGGRUM, 5, -3.5F, new Properties()
                .maxStackSize(1)
                .defaultMaxDamage(UndergardenItemTiers.CLOGGRUM.getMaxUses() * 3)
                .group(UndergardenItemGroups.UNDERGARDEN_GEAR)
                .rarity(Rarity.EPIC)
        );
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> list) {
        if(isInGroup(group)) {
            ItemStack stack = new ItemStack(this);
            stack.addEnchantment(Enchantments.KNOCKBACK, 4);
            list.add(stack);
        }

    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        stack.serializeNBT();
        stack.addEnchantment(Enchantments.KNOCKBACK, 4);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
        if(equipmentSlot == EquipmentSlotType.MAINHAND) {
            //multimap.put(SharedMonsterAttributes.ATTACK_KNOCKBACK.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "generic.attackKnockback", 3, AttributeModifier.Operation.ADDITION));
            multimap.put(PlayerEntity.REACH_DISTANCE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "generic.reachDistance", 3, AttributeModifier.Operation.ADDITION));
        }
        return multimap;
    }

}
