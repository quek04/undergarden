package quek.undergarden.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.List;

public class BattleAxeItem extends SwordItem {

    public BattleAxeItem(Tier tier, int attackDamage, float attackSpeed) {
        super(tier, attackDamage, attackSpeed, new Properties()
                .stacksTo(1)
                .defaultDurability(tier.getUses() * 3)
                .tab(UGItemGroups.GROUP)
                .rarity(Rarity.EPIC)
        );
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list) {
        if(allowdedIn(tab)) {
            ItemStack stack = new ItemStack(this);
            stack.enchant(Enchantments.KNOCKBACK, 4);
            list.add(stack);
        }
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        stack.serializeNBT();
        stack.enchant(Enchantments.KNOCKBACK, 4);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if(stack.getItem() == UGItems.FORGOTTEN_BATTLEAXE.get()) {
            tooltip.add(new TranslatableComponent("tooltip.forgotten_sword").withStyle(ChatFormatting.GREEN));
        }
    }
}