package quek.undergarden.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGCreativeModeTabs;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.List;

public class UGAxeItem extends AxeItem {
    public UGAxeItem(Tier tier, float attackDamage, float attackSpeed) {
        super(tier, attackDamage, attackSpeed, new Properties()
                .stacksTo(1)
                .defaultDurability(tier.getUses())
                .tab(UGCreativeModeTabs.GROUP)
                .rarity(UGSwordItem.isForgotten(tier))
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(stack.getItem() == UGItems.UTHERIUM_AXE.get()) {
            tooltip.add(Component.translatable("tooltip.utheric_sword").withStyle(ChatFormatting.RED));
        }
        if(stack.getItem() == UGItems.FROSTSTEEL_AXE.get()) {
            tooltip.add(Component.translatable("tooltip.froststeel_sword").withStyle(ChatFormatting.AQUA));
        }
        if(stack.getItem() == UGItems.FORGOTTEN_AXE.get()) {
            tooltip.add(Component.translatable("tooltip.forgotten_sword").withStyle(ChatFormatting.GREEN));
            tooltip.add(Component.translatable("tooltip.forgotten_tool").withStyle(ChatFormatting.GREEN));
        }
    }
}