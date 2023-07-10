package quek.undergarden.item;

import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import quek.undergarden.entity.projectile.Blisterbomb;
import quek.undergarden.registry.UGSoundEvents;

public class BlisterbombItem extends Item {

	public BlisterbombItem() {
		super(new Properties()
				.stacksTo(8)
		);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		level.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), UGSoundEvents.BLISTERBOMB_THROW.get(), SoundSource.NEUTRAL, 0.5F, 1F);
		playerIn.getCooldowns().addCooldown(this, 50);
		if (!level.isClientSide) {
			Blisterbomb blisterbomb = new Blisterbomb(level, playerIn);
			blisterbomb.setItem(itemstack);
			blisterbomb.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.5F, 1.0F);
			level.addFreshEntity(blisterbomb);
		}

		playerIn.awardStat(Stats.ITEM_USED.get(this));
		if (!playerIn.getAbilities().instabuild) {
			itemstack.shrink(1);
		}

		return InteractionResultHolder.success(itemstack);
	}

}