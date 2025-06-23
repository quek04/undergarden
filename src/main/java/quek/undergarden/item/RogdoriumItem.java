package quek.undergarden.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import quek.undergarden.event.UthericInfectionEvents;
import quek.undergarden.registry.UGAttachments;

public class RogdoriumItem extends Item {

	private final float percentage;

	public RogdoriumItem(float percentage, Properties properties) {
		super(properties);
		this.percentage = percentage;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (player.getData(UGAttachments.UTHERIC_INFECTION) > 0) {
			player.startUsingItem(hand);
			return InteractionResultHolder.consume(stack);
		} else {
			return InteractionResultHolder.pass(stack);
		}
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 32;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.EAT;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		if (entity instanceof Player player && player.getData(UGAttachments.UTHERIC_INFECTION) > 0) {
			player.setData(UGAttachments.UTHERIC_INFECTION, Math.max(0.0D, player.getData(UGAttachments.UTHERIC_INFECTION) - (UthericInfectionEvents.MAX_INFECTION  * this.percentage)));
		}
		return entity.eat(level, stack, new FoodProperties.Builder().build());
	}
}
