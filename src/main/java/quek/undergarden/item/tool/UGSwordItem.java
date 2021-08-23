package quek.undergarden.item.tool;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGItemTiers;

import javax.annotation.Nullable;
import java.util.List;

public class UGSwordItem extends SwordItem {
    public UGSwordItem(IItemTier tier) {
        super(tier, 3, -2.4F, new Properties()
                .stacksTo(1)
                .defaultDurability(tier.getUses())
                .tab(UGItemGroups.GROUP)
                .rarity(isForgotten(tier))
        );
    }

    protected static Rarity isForgotten(IItemTier tier) {
        if(tier.equals(UGItemTiers.FORGOTTEN)) {
            return UGItems.FORGOTTEN;
        }
        else return Rarity.COMMON;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(stack.getItem() == UGItems.FROSTSTEEL_SWORD.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.froststeel_sword").withStyle(TextFormatting.AQUA));
        }
        if(stack.getItem() == UGItems.UTHERIUM_SWORD.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.utheric_sword").withStyle(TextFormatting.RED));
        }
        if(stack.getItem() == UGItems.FORGOTTEN_SWORD.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.forgotten_sword").withStyle(TextFormatting.GREEN));
        }
    }
}