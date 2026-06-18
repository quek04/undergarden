package quek.undergarden.entity.projectile.slingshot;

import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import quek.undergarden.entity.projectile.slingshot.effect.HitEffect;
import quek.undergarden.registry.UGDataComponents;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public final class SlingshotProjectile extends ThrowableItemProjectile {

	private boolean ricochet;
	private int ricochetTimes = 0;
	private int airTime = 1;

	public SlingshotProjectile(EntityType<? extends ThrowableItemProjectile> type, Level level) {
		super(type, level);
	}

	public SlingshotProjectile(LivingEntity shooter, Level level, ItemStack stack) {
		super(UGEntityTypes.SLINGSHOT_PROJECTILE.get(), shooter, level, stack);
	}

	@Override
	protected Item getDefaultItem() {
		return UGItems.DEPTHROCK_PEBBLE.get();
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().getGameTime() % 5 == 0) {
			this.airTime++;
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		BlockState blockstate = this.level().getBlockState(result.getBlockPos());
		if (!blockstate.getCollisionShape(this.level(), result.getBlockPos()).isEmpty()) {
			this.playStepSound(result.getBlockPos(), blockstate);
			if (this.level() instanceof ServerLevel serverLevel) {
				if (this.ricochet) {
					Vec3 delta = this.getDeltaMovement();
					Direction direction = result.getDirection();
					float velocity = (float) delta.length() / 2.0F;
					if (direction == Direction.UP || direction == Direction.DOWN) {
						this.shoot(delta.x(), delta.reverse().y(), delta.z(), velocity, 1.0F);
					} else if (direction == Direction.WEST || direction == Direction.EAST) {
						this.shoot(delta.reverse().x(), delta.reverse().y(), delta.z(), velocity, 1.0F);
					} else {
						this.shoot(delta.x(), delta.reverse().y(), delta.reverse().z(), velocity, 1.0F);
					}
					this.ricochetTimes--;
					if (this.ricochetTimes == 0) {
						this.finishHit(serverLevel, result);
					}
				} else {
					this.finishHit(serverLevel, result);
				}
			}
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		if (this.level() instanceof ServerLevel serverLevel) {
			this.finishHit(serverLevel, result);
		}
	}

	private void finishHit(ServerLevel level, HitResult result) {
		boolean finishedHit = false;
		if (this.getItem().has(UGDataComponents.SLINGSHOT_AMMO)) {
			var ammo = this.getItem().get(UGDataComponents.SLINGSHOT_AMMO);
			for (HitEffect effect : ammo.hitEffects()) {
				if (effect.apply(level, this.getItem(), this, result)) {
					finishedHit = true;
					break;
				}
			}

			if (finishedHit) {
				ammo.hitSound().ifPresent(event -> this.playSound(event.value()));
				level.broadcastEntityEvent(this, (byte) 3);

				if (this.getOwner() instanceof Player player && !player.isCreative() && ammo.dropAsItem()) {
					this.spawnAtLocation(level, this.getItem());
				}
			}
		}
		this.discard();
	}

	public int getAirTime() {
		return this.airTime;
	}

	public void setRicochetTimes(int times) {
		this.ricochet = true;
		this.ricochetTimes = times;
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			var ammo = this.getItem().get(UGDataComponents.SLINGSHOT_AMMO);
			for (int i = 0; i < ammo.breakParticleCount().sample(this.getRandom()); ++i) {
				this.level().addParticle(this.makeParticle(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	private ParticleOptions makeParticle() {
		return new ItemParticleOption(ParticleTypes.ITEM, this.getItem().getItem());
	}
}
