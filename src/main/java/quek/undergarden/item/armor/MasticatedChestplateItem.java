package quek.undergarden.item.armor;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import quek.undergarden.registry.UndergardenItemGroups;

import net.minecraft.item.Item.Properties;

public class MasticatedChestplateItem extends ArmorItem {

    public MasticatedChestplateItem(IArmorMaterial materialIn) {
        super(materialIn, EquipmentSlotType.CHEST, new Properties()
                .rarity(Rarity.EPIC)
                .group(UndergardenItemGroups.GROUP)
        );
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> list) {
        if(isInGroup(group)) {
            ItemStack stack = new ItemStack(this);
            stack.addEnchantment(Enchantments.THORNS, 5);
            list.add(stack);
        }

    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        stack.serializeNBT();
        stack.addEnchantment(Enchantments.THORNS, 5);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlotType slot, String layer) {
        return "undergarden:textures/models/armor/masticated_layer_1.png";
    }

}
