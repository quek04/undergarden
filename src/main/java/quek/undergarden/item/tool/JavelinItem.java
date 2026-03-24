package quek.undergarden.item.tool;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import quek.undergarden.entity.projectile.ThrownJavelin;

public class JavelinItem extends Item implements ProjectileItem {

	public JavelinItem(Properties properties) {
		super(properties);
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack stack) {
		return ItemUseAnimation.TRIDENT;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 72000;
	}

	@Override
	public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
		if (entity instanceof Player player) {
			int useTime = this.getUseDuration(stack, entity) - timeLeft;
			if (useTime >= 10) {
				if (!level.isClientSide()) {
					ItemStack useStack = stack.consumeAndReturn(1, player);
					ThrownJavelin javelin = new ThrownJavelin(level, player, useStack);
					javelin.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.0F, 1.0F);

					if (player.hasInfiniteMaterials()) {
						javelin.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
					}

					level.addFreshEntity(javelin);
					level.playSound(null, javelin, SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
				}

				player.awardStat(Stats.ITEM_USED.get(this));
			}
		}
		return false;
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		player.startUsingItem(hand);
		return InteractionResult.CONSUME;
	}

	@Override
	public boolean canPerformAction(ItemInstance stack, ItemAbility itemAbility) {
		return ItemAbilities.DEFAULT_TRIDENT_ACTIONS.contains(itemAbility);
	}

	@Override
	public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
		ThrownJavelin javelin = new ThrownJavelin(level, pos.x(), pos.y(), pos.z(), stack);
		javelin.pickup = AbstractArrow.Pickup.ALLOWED;
		return javelin;
	}
}