package quek.undergarden.item.tool;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.NonNullList;
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

public class BattleAxeItem extends SwordItem {

    public BattleAxeItem(IItemTier tier, int damage, float speed) {
        super(tier, damage, speed, new Properties()
                .stacksTo(1)
                .defaultDurability(tier.getUses() * 3)
                .tab(UGItemGroups.GROUP)
                .rarity(Rarity.EPIC)
        );
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> list) {
        if(allowdedIn(group)) {
            ItemStack stack = new ItemStack(this);
            stack.enchant(Enchantments.KNOCKBACK, 4);
            list.add(stack);
        }
    }

    @Override
    public void onCraftedBy(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        stack.serializeNBT();
        stack.enchant(Enchantments.KNOCKBACK, 4);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(stack.getItem() == UGItems.FORGOTTEN_BATTLEAXE.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.forgotten_sword").withStyle(TextFormatting.GREEN));
        }
    }
}