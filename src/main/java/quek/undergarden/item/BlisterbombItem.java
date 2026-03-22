package quek.undergarden.item;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import quek.undergarden.entity.projectile.Blisterbomb;
import quek.undergarden.registry.UGSoundEvents;

public class BlisterbombItem extends Item implements ProjectileItem {

	public BlisterbombItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		level.playSound(null, player.blockPosition(), UGSoundEvents.BLISTERBOMB_THROW.get(), SoundSource.NEUTRAL, 0.5F, 1F);
		player.getCooldowns().addCooldown(itemstack, 50);
		if (!level.isClientSide()) {
			Blisterbomb blisterbomb = new Blisterbomb(level, player, itemstack);
			blisterbomb.setItem(itemstack);
			blisterbomb.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
			level.addFreshEntity(blisterbomb);
		}

		player.awardStat(Stats.ITEM_USED.get(this));
		itemstack.consume(1, player);

		return InteractionResult.SUCCESS;
	}

	@Override
	public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
		return new Blisterbomb(level, pos.x(), pos.y(), pos.z(), stack);
	}
}