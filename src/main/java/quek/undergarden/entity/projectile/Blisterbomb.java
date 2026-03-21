package quek.undergarden.entity.projectile;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class Blisterbomb extends ThrowableItemProjectile {

	public Blisterbomb(EntityType<? extends Blisterbomb> type, Level level) {
		super(type, level);
	}

	public Blisterbomb(Level level, LivingEntity thrower, ItemStack stack) {
		super(UGEntityTypes.BLISTERBOMB.get(), thrower, level, stack);
	}

	public Blisterbomb(Level level, double x, double y, double z, ItemStack stack) {
		super(UGEntityTypes.BLISTERBOMB.get(), x, y, z, level, stack);
	}

	@Override
	protected Item getDefaultItem() {
		return UGItems.BLISTERBOMB.get();
	}

	@Override
	protected void onHit(HitResult result) {
		if (this.level() instanceof ServerLevel serverLevel) {
			serverLevel.explode(this, this.getX(), this.getY(), this.getZ(), 3.0F, Level.ExplosionInteraction.BLOCK);
			this.discard();
		}
	}
}
