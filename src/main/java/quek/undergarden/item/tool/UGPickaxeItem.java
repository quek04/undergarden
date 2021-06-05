package quek.undergarden.item.tool;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.List;

public class UGPickaxeItem extends PickaxeItem {
    public UGPickaxeItem(IItemTier tier) {
        super(tier, 1, -2.8F, new Properties()
                .stacksTo(1)
                .defaultDurability(tier.getUses())
                .tab(UGItemGroups.GROUP)
                .rarity(UGSwordItem.isForgotten(tier))
        );
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(stack.getItem() == UGItems.FROSTSTEEL_PICKAXE.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.froststeel_sword").withStyle(TextFormatting.AQUA));
        }
        if(stack.getItem() == UGItems.FORGOTTEN_PICKAXE.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.forgotten_tool").withStyle(TextFormatting.GREEN));
        }
    }
}