package quek.undergarden.item.armor;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import quek.undergarden.registry.UGItemGroups;

import net.minecraft.item.Item.Properties;

public class MasticatedChestplateItem extends ArmorItem {

    public MasticatedChestplateItem(IArmorMaterial materialIn) {
        super(materialIn, EquipmentSlotType.CHEST, new Properties()
                .rarity(Rarity.EPIC)
                .tab(UGItemGroups.GROUP)
        );
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> list) {
        if(allowdedIn(group)) {
            ItemStack stack = new ItemStack(this);
            stack.enchant(Enchantments.THORNS, 5);
            list.add(stack);
        }

    }

    @Override
    public void onCraftedBy(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        stack.serializeNBT();
        stack.enchant(Enchantments.THORNS, 5);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return false;
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlotType slot, String layer) {
        return "undergarden:textures/models/armor/masticated_layer_1.png";
    }
}