package quek.undergarden.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGItemGroups;

import javax.annotation.Nullable;
import java.util.List;

public class DepthrockPebbleItem extends ArrowItem {

    public DepthrockPebbleItem() {
        super(new Properties()
                .group(UGItemGroups.GROUP)
        );
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.pebble").mergeStyle(TextFormatting.GRAY));
    }

}