package quek.undergarden.item.tool;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.player.Player;
import net.minecraft.item.*;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class BattleAxeItem extends SwordItem {

    public BattleAxeItem(Tier tier, int damage, float speed) {
        super(tier, damage, speed, new Properties()
                .stacksTo(1)
                .defaultDurability(tier.getUses() * 3)
                .tab(UGItemGroups.GROUP)
                .rarity(Rarity.EPIC)
        );
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> list) {
        if(allowdedIn(group)) {
            ItemStack stack = new ItemStack(this);
            stack.enchant(Enchantments.KNOCKBACK, 4);
            list.add(stack);
        }
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn) {
        stack.serializeNBT();
        stack.enchant(Enchantments.KNOCKBACK, 4);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(stack.getItem() == UGItems.FORGOTTEN_BATTLEAXE.get()) {
            tooltip.add(new TranslatableComponent("tooltip.forgotten_sword").withStyle(ChatFormatting.GREEN));
        }
    }
}