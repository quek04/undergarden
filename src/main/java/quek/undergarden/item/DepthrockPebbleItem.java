package quek.undergarden.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGItemGroups;

import javax.annotation.Nullable;
import java.util.List;

public class DepthrockPebbleItem extends ArrowItem {

    public DepthrockPebbleItem() {
        super(new Properties()
                .tab(UGItemGroups.GROUP)
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("tooltip.pebble").withStyle(ChatFormatting.GRAY));
    }
}