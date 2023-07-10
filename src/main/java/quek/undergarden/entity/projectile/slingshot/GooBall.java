package quek.undergarden.entity.projectile.slingshot;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import quek.undergarden.entity.animal.Scintling;
import quek.undergarden.registry.UGDamageSources;
import quek.undergarden.registry.UGEffects;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class GooBall extends SlingshotProjectile {

	public GooBall(EntityType<? extends GooBall> type, Level level) {
		super(type, level);
		this.setDropItem(true);
	}

	public GooBall(Level level, LivingEntity shooter) {
		super(UGEntityTypes.GOO_BALL.get(), shooter, level);
	}

	public GooBall(Level level, double x, double y, double z) {
		super(UGEntityTypes.GOO_BALL.get(), x, y, z, level);
	}

	@Override
	protected Item getDefaultItem() {
		return UGItems.GOO_BALL.get();
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			for (int i = 0; i < 8; ++i) {
				this.level().addParticle(this.makeParticle(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		Entity entity = result.getEntity();
		if (entity instanceof LivingEntity livingEntity) {
			if (livingEntity instanceof Scintling) {
				livingEntity.heal(2);
			} else {
				livingEntity.hurt(this.damageSources().source(UGDamageSources.DEPTHROCK_PEBBLE, this, this.getOwner()), 0.0F);
				livingEntity.addEffect(new MobEffectInstance(UGEffects.GOOEY.get(), 100, 0, false, true));
			}
		}
		this.playSound(SoundEvents.SLIME_BLOCK_BREAK, 1, 1);

		if (!this.level().isClientSide) {
			this.level().broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}
	}
}