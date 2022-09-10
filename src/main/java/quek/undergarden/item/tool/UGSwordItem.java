package quek.undergarden.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGCreativeModeTabs;
import quek.undergarden.registry.UGItemTiers;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.List;

public class UGSwordItem extends SwordItem {
    public UGSwordItem(Tier tier, int attackDamage, float attackSpeed) {
        super(tier, attackDamage, attackSpeed, new Properties()
                .stacksTo(1)
                .defaultDurability(tier.getUses())
                .tab(UGCreativeModeTabs.GROUP)
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
            tooltip.add(Component.translatable("tooltip.froststeel_sword").withStyle(ChatFormatting.AQUA));
        }
        if(stack.getItem() == UGItems.UTHERIUM_SWORD.get()) {
            tooltip.add(Component.translatable("tooltip.utheric_sword").withStyle(ChatFormatting.RED));
        }
        if(stack.getItem() == UGItems.FORGOTTEN_SWORD.get()) {
            tooltip.add(Component.translatable("tooltip.forgotten_sword").withStyle(ChatFormatting.GREEN));
        }
    }
}