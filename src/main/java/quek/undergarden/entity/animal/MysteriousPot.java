package quek.undergarden.entity.animal;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class MysteriousPot extends PathfinderMob {

	public static final EntityDataAccessor<Boolean> ACTIVE = SynchedEntityData.defineId(MysteriousPot.class, EntityDataSerializers.BOOLEAN);
	private final EntityDimensions ACTIVE_DIMENSIONS = EntityDimensions.fixed(0.8F, 1.25F).withEyeHeight(0.2F);

	protected int hideCooldown;
	private int prevInactiveWiggleTicks;
	private int inactiveWiggleTicks;

	public MysteriousPot(EntityType<? extends PathfinderMob> entityType, Level level) {
		super(entityType, level);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 8.0F)
			.add(Attributes.MOVEMENT_SPEED, 0.3F)
			.add(Attributes.STEP_HEIGHT, 1.0F)
			.add(Attributes.SAFE_FALL_DISTANCE, 5.0F)
			.add(Attributes.FALL_DAMAGE_MULTIPLIER, 2.0F);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, Player.class, entity -> true, 16.0F, 1.25F, 1.75F, EntitySelector.NO_SPECTATORS));
		this.goalSelector.addGoal(1, new HideAgainGoal(this));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(ACTIVE, false);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		if (ACTIVE.equals(key)) {
			this.refreshDimensions();
		}
		super.onSyncedDataUpdated(key);
	}

	@Override
	public boolean canBeCollidedWith(@Nullable Entity other) {
		return !this.isActive();
	}

	@Override
	protected EntityDimensions getDefaultDimensions(Pose pose) {
		return this.isActive() ? ACTIVE_DIMENSIONS : super.getDefaultDimensions(pose);
	}

	@Override
	public void tick() {
		super.tick();

		if (this.hideCooldown > 0) {
			this.hideCooldown--;
		}

		if (this.level().isClientSide()) {
			this.prevInactiveWiggleTicks = this.inactiveWiggleTicks;
			if (this.inactiveWiggleTicks > 0) {
				this.inactiveWiggleTicks--;
			}

			if (!this.isActive() && this.tickCount % 100 == 0 && this.getRandom().nextInt(5) == 0) {
				this.prevInactiveWiggleTicks = 10;
				this.inactiveWiggleTicks = 10;
			}
		}
	}

	public float getInactiveWiggleTicks(float partialTick) {
		return Mth.lerp(partialTick, this.prevInactiveWiggleTicks, this.inactiveWiggleTicks);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putBoolean("active", this.isActive());
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		this.setActive(input.getBooleanOr("active", false));
	}

	public boolean isActive() {
		return this.getEntityData().get(ACTIVE);
	}

	public void setActive(boolean active) {
		this.getEntityData().set(ACTIVE, active);
	}

	@Override
	protected boolean isImmobile() {
		return !this.isActive() || super.isImmobile();
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
		if (!source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
			if (!this.isActive() && amount > 0) {
				float yRot = this.getNearestViewDirection().toYRot();
				if (source.getDirectEntity() != null) {
					double d0 = source.getDirectEntity().getX() - this.getX();
					double d2 = source.getDirectEntity().getZ() - this.getZ();
					float rawRot = ((float)(Mth.atan2(d2, d0) * Mth.RAD_TO_DEG) - 90.0F);
					yRot = Math.round(rawRot / 90) * 90;
				}
				this.setYRot(this.yRotO = yRot);
				this.setYHeadRot(this.yHeadRotO = yRot);
				this.yBodyRot = this.yBodyRotO = yRot;
				this.jumpFromGround();
				this.setActive(true);
				this.hideCooldown = 200;
				return false;
			}
		}
		if (source.getWeaponItem() != null && source.getWeaponItem().is(ItemTags.BREAKS_DECORATED_POTS)) amount *= 2;
		return super.hurtServer(level, source, amount);
	}

	static class HideAgainGoal extends Goal {

		private final MysteriousPot pot;

		public HideAgainGoal(MysteriousPot pot) {
			this.pot = pot;
		}

		@Override
		public boolean canUse() {
			if (this.pot.hideCooldown <= 0 && this.pot.isActive() && this.pot.getNavigation().isDone()) {
				List<Player> nearPlayers = getServerLevel(this.pot).getNearbyPlayers(TargetingConditions.forCombat(), this.pot, this.pot.getBoundingBox().inflate(16.0D, 16.0D, 16.0D));
				if (nearPlayers.isEmpty()) return true;
				for (Player player : nearPlayers) {
					if (this.pot.hasLineOfSight(player)) {
						return false;
					}
				}
				return true;
			}
			return false;
		}

		@Override
		public boolean canContinueToUse() {
			return false;
		}

		@Override
		public void start() {
			this.pot.snapTo(this.pot.blockPosition(), 0.0F, 0.0F);
			this.pot.setActive(false);
		}
	}
}
