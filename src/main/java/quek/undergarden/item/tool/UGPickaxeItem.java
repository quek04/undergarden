package quek.undergarden.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.List;

public class UGPickaxeItem extends PickaxeItem {
    public UGPickaxeItem(Tier tier, int attackDamage, float attackSpeed) {
        super(tier, attackDamage, attackSpeed, new Properties()
                .stacksTo(1)
                .defaultDurability(tier.getUses())
                .tab(UGItemGroups.GROUP)
                .rarity(UGSwordItem.isForgotten(tier))
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(stack.getItem() == UGItems.FROSTSTEEL_PICKAXE.get()) {
            tooltip.add(new TranslatableComponent("tooltip.froststeel_sword").withStyle(ChatFormatting.AQUA));
        }
        if(stack.getItem() == UGItems.FORGOTTEN_PICKAXE.get()) {
            tooltip.add(new TranslatableComponent("tooltip.forgotten_tool").withStyle(ChatFormatting.GREEN));
        }
    }
}