package quek.undergarden.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.List;

public class UGHoeItem extends HoeItem {

	public UGHoeItem(Tier tier, int attackDamage, float attackSpeed) {
		super(tier, attackDamage, attackSpeed, new Properties()
				.stacksTo(1)
				.durability(tier.getUses())
				.rarity(UGSwordItem.isForgotten(tier))
		);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		if (stack.getItem() == UGItems.FORGOTTEN_HOE.get()) {
			tooltip.add(Component.translatable("tooltip.forgotten_tool").withStyle(ChatFormatting.GREEN));
		}
	}
}