package quek.undergarden.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGItemTiers;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.List;

public class UGSwordItem extends SwordItem {
    public UGSwordItem(Tier tier) {
        super(tier, 3, -2.4F, new Properties()
                .stacksTo(1)
                .defaultDurability(tier.getUses())
                .tab(UGItemGroups.GROUP)
                .rarity(isForgotten(tier))
        );
    }

    protected static Rarity isForgotten(Tier tier) {
        if(tier.equals(UGItemTiers.FORGOTTEN)) {
            return UGItems.FORGOTTEN;
        }
        else return Rarity.COMMON;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(stack.getItem() == UGItems.FROSTSTEEL_SWORD.get()) {
            tooltip.add(new TranslatableComponent("tooltip.froststeel_sword").withStyle(ChatFormatting.AQUA));
        }
        if(stack.getItem() == UGItems.UTHERIUM_SWORD.get()) {
            tooltip.add(new TranslatableComponent("tooltip.utheric_sword").withStyle(ChatFormatting.RED));
        }
        if(stack.getItem() == UGItems.FORGOTTEN_SWORD.get()) {
            tooltip.add(new TranslatableComponent("tooltip.forgotten_sword").withStyle(ChatFormatting.GREEN));
        }
    }
}