package quek.undergarden.item.tool.slingshot;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;
import quek.undergarden.registry.UGSoundEvents;

public abstract class AbstractSlingshotAmmoBehavior {

	/**
	 * Sets the projectile entity to shoot. <br>
	 * Your projectile must extend {@link SlingshotProjectile} to calculate ricochet logic.
	 *
	 * @param level   the level the projectile is being fired from
	 * @param pos     the position the projectile is being fired from
	 * @param shooter the player shooting the projectile
	 * @param stack   the ItemStack that the slingshot is firing
	 * @return the shot projectile
	 */
	public abstract SlingshotProjectile getProjectile(Level level, BlockPos pos, Player shooter, ItemStack stack);

	/**
	 * Sets the sound to play when a projectile is fired. By default, it's set to be UGSoundEvents.SLINGSHOT_SHOOT, but you can set it to be whatever you want.
	 *
	 * @return the sound to play when fired
	 */
	public SoundEvent getFiringSound() {
		return UGSoundEvents.SLINGSHOT_SHOOT.get();
	}

	/**
	 * Does some additional stuff when shooting the projectile. This is called after firing the projectile.
	 *
	 * @param level   the level the projectile is being fired from
	 * @param shooter the player shooting the projectile
	 */
	public void addAdditionalFiringEffects(Level level, Player shooter) {

	}
}
