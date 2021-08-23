package quek.undergarden.item.tool;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGItemTiers;

public class CloggrumBattleaxeItem extends SwordItem {

    public CloggrumBattleaxeItem() {
        super(UGItemTiers.CLOGGRUM, 5, -3.5F, new Properties()
                .stacksTo(1)
                .defaultDurability(UGItemTiers.CLOGGRUM.getUses() * 3)
                .tab(UGItemGroups.GROUP)
                .rarity(Rarity.EPIC)
        );
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> list) {
        if(allowdedIn(group)) {
            ItemStack stack = new ItemStack(this);
            stack.enchant(Enchantments.KNOCKBACK, 4);
            list.add(stack);
        }

    }

    @Override
    public void onCraftedBy(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        stack.serializeNBT();
        stack.enchant(Enchantments.KNOCKBACK, 4);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return false;
    }

}