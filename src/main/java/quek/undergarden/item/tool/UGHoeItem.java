package quek.undergarden.item.tool;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGItemGroups;

import net.minecraft.world.item.Item.Properties;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.List;

public class UGHoeItem extends HoeItem {

    public UGHoeItem(Tier tier, int attack, float speed) {
        super(tier, attack, speed, new Properties()
                .stacksTo(1)
                .durability(tier.getUses())
                .tab(UGItemGroups.GROUP)
                .rarity(UGSwordItem.isForgotten(tier))
        );
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(stack.getItem() == UGItems.FORGOTTEN_HOE.get()) {
            tooltip.add(new TranslatableComponent("tooltip.forgotten_tool").withStyle(ChatFormatting.GREEN));
        }
    }
}