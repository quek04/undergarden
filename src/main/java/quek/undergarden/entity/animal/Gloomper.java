package quek.undergarden.entity.animal;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.JumpControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import quek.undergarden.entity.rotspawn.RotspawnMonster;
import quek.undergarden.registry.*;

import javax.annotation.Nullable;

public class Gloomper extends Animal {

	private int jumpTicks;
	private int jumpDuration;
	private boolean wasOnGround;
	private int currentMoveTypeDuration;

	public Gloomper(EntityType<? extends Animal> type, Level level) {
		super(type, level);
		this.jumpControl = new JumpHelperController(this);
		this.moveControl = new Gloomper.MoveHelperController(this);
		this.setMovementSpeed(0.0D);
		this.setMaxUpStep(1.0F);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 2.5D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, RotspawnMonster.class, 12.0F, 2.0F, 2.5F));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(UGBlocks.GLOOMGOURD.get()), false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Animal.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return UGSoundEvents.GLOOMPER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return UGSoundEvents.GLOOMPER_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.GLOOMPER_DEATH.get();
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		return UGEntityTypes.GLOOMPER.get().create(this.level());
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return Ingredient.of(UGBlocks.GLOOMGOURD.get()).test(stack);
	}

	@Override
	protected float getJumpPower() {
		if (!this.horizontalCollision && (!this.getMoveControl().hasWanted() || !(this.getMoveControl().getWantedY() > this.getY() + 0.5D))) {
			Path path = this.getNavigation().getPath();
			if (path != null && !path.isDone()) {
				Vec3 vector3d = path.getNextEntityPos(this);
				if (vector3d.y() > this.getY() + 0.5D) {
					return 0.5F;
				}
			}

			return this.getMoveControl().getSpeedModifier() <= 0.6D ? 0.2F : 0.3F;
		} else {
			return 0.5F;
		}
	}

	@Override
	protected void jumpFromGround() {
		super.jumpFromGround();
		double d0 = this.getMoveControl().getSpeedModifier();
		if (d0 > 0.0D) {
			double d1 = this.getDeltaMovement().horizontalDistanceSqr();
			if (d1 < 0.01D) {
				this.moveRelative(0.1F, new Vec3(0.0D, 0.0D, 1.0D));
			}
		}

		if (!this.level().isClientSide()) {
			this.level().broadcastEntityEvent(this, (byte) 1);
		}

	}

	public float getJumpCompletion(float delta) {
		return this.jumpDuration == 0 ? 0.0F : ((float) this.jumpTicks + delta) / (float) this.jumpDuration;
	}

	public void setMovementSpeed(double newSpeed) {
		this.getNavigation().setSpeedModifier(newSpeed);
		this.getMoveControl().setWantedPosition(this.getMoveControl().getWantedX(), this.getMoveControl().getWantedY(), this.getMoveControl().getWantedZ(), newSpeed);
	}

	@Override
	public void setJumping(boolean jumping) {
		super.setJumping(jumping);
		if (jumping && !this.isInWater() && !this.wasTouchingWater) {
			this.playSound(UGSoundEvents.GLOOMPER_HOP.get(), this.getSoundVolume(), ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.2F + 1.0F) * 0.8F);
		}

	}

	public void startJumping() {
		this.setJumping(true);
		this.jumpDuration = 10;
		this.jumpTicks = 0;
	}

	@Override
	public void customServerAiStep() {
		if (this.currentMoveTypeDuration > 0) {
			--this.currentMoveTypeDuration;
		}

		if (this.onGround()) {
			if (!this.wasOnGround) {
				this.setJumping(false);
				this.checkLandingDelay();
			}

			Gloomper.JumpHelperController jumpController = (Gloomper.JumpHelperController) this.jumpControl;
			if (!jumpController.isJumping()) {
				if (this.moveControl.hasWanted() && this.currentMoveTypeDuration == 0) {
					Path path = this.navigation.getPath();
					Vec3 vector3d = new Vec3(this.getMoveControl().getWantedX(), this.getMoveControl().getWantedY(), this.getMoveControl().getWantedZ());
					if (path != null && !path.isDone()) {
						vector3d = path.getNextEntityPos(this);
					}

					this.calculateRotationYaw(vector3d.x(), vector3d.z());
					this.startJumping();
				}
			} else if (!jumpController.canJump()) {
				this.enableJumpControl();
			}
		}

		this.wasOnGround = this.onGround();
	}

	@Override
	public boolean canSpawnSprintParticle() {
		return false;
	}

	private void calculateRotationYaw(double x, double z) {
		this.setYRot((float) (Mth.atan2(z - this.getZ(), x - this.getX()) * (double) (180F / (float) Math.PI)) - 90.0F);
	}

	private void enableJumpControl() {
		((Gloomper.JumpHelperController) this.getJumpControl()).setCanJump(true);
	}

	private void disableJumpControl() {
		((Gloomper.JumpHelperController) this.getJumpControl()).setCanJump(false);
	}

	private void updateMoveTypeDuration() {
		if (this.getMoveControl().getSpeedModifier() < 2.2D) {
			this.currentMoveTypeDuration = 10;
		} else {
			this.currentMoveTypeDuration = 1;
		}

	}

	private void checkLandingDelay() {
		this.updateMoveTypeDuration();
		this.disableJumpControl();
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.jumpTicks != this.jumpDuration) {
			++this.jumpTicks;
		} else if (this.jumpDuration != 0) {
			this.jumpTicks = 0;
			this.jumpDuration = 0;
			this.setJumping(false);
		}

	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 1) {
			this.spawnSprintParticle();
			this.jumpDuration = 10;
			this.jumpTicks = 0;
		} else {
			super.handleEntityEvent(id);
		}
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (stack.is(UGItems.GLOOMPER_ANTHEM_DISC.get()) && this.isAlive()) {
			if (!this.level().isClientSide()) {
				this.spawnAtLocation(UGItems.GLOOMPER_SECRET_DISC.get());
				this.kill();
			}
			if (!player.getAbilities().instabuild) {
				stack.shrink(1);
			}
			return InteractionResult.sidedSuccess(this.level().isClientSide());
		} else return super.mobInteract(player, hand);
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		AreaEffectCloud cloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());

		cloud.setParticle(UGParticleTypes.GLOOMPER_FART.get());
		cloud.setRadius(3.0F);
		cloud.setRadiusOnUse(-0.5F);
		cloud.setWaitTime(10);
		cloud.setRadiusPerTick(-cloud.getRadius() / (float) cloud.getDuration());
		cloud.addEffect(new MobEffectInstance(UGEffects.VIRULENCE.get(), 100, 0));

		if (this.getRandom().nextInt(2) == 0) {
			this.playSound(UGSoundEvents.GLOOMPER_FART.get(), 1.0F, 1.0F);
			this.level().addFreshEntity(cloud);
		}

		return super.hurt(source, amount);
	}

	@Override
	public boolean canBeAffected(MobEffectInstance effectInstance) {
		MobEffect effect = effectInstance.getEffect();
		if (effect == UGEffects.VIRULENCE.get()) {
			return false;
		} else return super.canBeAffected(effectInstance);
	}

	public static class JumpHelperController extends JumpControl {
		private final Gloomper gloomper;
		private boolean canJump;

		public JumpHelperController(Gloomper gloomper) {
			super(gloomper);
			this.gloomper = gloomper;
		}

		public boolean isJumping() {
			return this.jump;
		}

		public boolean canJump() {
			return this.canJump;
		}

		public void setCanJump(boolean canJumpIn) {
			this.canJump = canJumpIn;
		}

		@Override
		public void tick() {
			if (this.isJumping()) {
				this.gloomper.startJumping();
				this.jump = false;
			}
		}
	}

	static class MoveHelperController extends MoveControl {
		private final Gloomper gloomper;
		private double nextJumpSpeed;

		public MoveHelperController(Gloomper gloomper) {
			super(gloomper);
			this.gloomper = gloomper;
		}

		@Override
		public void tick() {
			if (this.gloomper.onGround() && !this.gloomper.jumping && !((Gloomper.JumpHelperController) this.gloomper.getJumpControl()).isJumping()) {
				this.gloomper.setMovementSpeed(0.0D);
			} else if (this.hasWanted()) {
				this.gloomper.setMovementSpeed(this.nextJumpSpeed);
			}

			super.tick();
		}

		@Override
		public void setWantedPosition(double x, double y, double z, double speed) {
			if (this.gloomper.isInWater()) {
				speed = 1.5D;
			}

			super.setWantedPosition(x, y, z, speed);
			if (speed > 0.0D) {
				this.nextJumpSpeed = speed;
			}
		}
	}
}