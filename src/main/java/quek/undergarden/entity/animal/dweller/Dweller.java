package quek.undergarden.entity.animal.dweller;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import org.jspecify.annotations.Nullable;
import quek.undergarden.entity.monster.rotspawn.RotspawnMonster;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;
import quek.undergarden.registry.UGTags;

import java.util.List;

public class Dweller extends Animal implements ItemSteerable, PlayerRideableJumping {

	private static final EntityDataAccessor<Integer> BOOST_TIME = SynchedEntityData.defineId(Dweller.class, EntityDataSerializers.INT);
	private final DwellerItemBasedSteering steering = new DwellerItemBasedSteering(this.getEntityData(), BOOST_TIME);
	private float playerJumpPendingScale;
	private boolean isJumping;
	private int wildJumpCooldown;
	@Nullable
	private PanicGoal panicGoal;

	@Nullable
	private DwellerAvoidEntityGoal<?> avoidGoal;

	public Dweller(EntityType<? extends Dweller> type, Level level) {
		super(type, level);
		this.wildJumpCooldown = 300 + this.getRandom().nextInt(500);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, this.panicGoal = new PanicGoal(this, 2.5D));
		this.goalSelector.addGoal(1, new TemptGoal(this, 1.5D, stack -> stack.is(UGTags.Items.DWELLER_TEMPT_ITEMS), false));
		this.goalSelector.addGoal(0, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(2, this.avoidGoal = new DwellerAvoidEntityGoal<>(this, RotspawnMonster.class, 12.0F, 2.0D, 2.5D));
		this.goalSelector.addGoal(2, new DwellerJumpGoal(this));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Animal.createAnimalAttributes()
				.add(Attributes.MAX_HEALTH, 15.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.STEP_HEIGHT, 1.0D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return UGSoundEvents.DWELLER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return UGSoundEvents.DWELLER_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.DWELLER_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(UGSoundEvents.DWELLER_STEP.get(), 0.15F, 1.0F);
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		return UGEntityTypes.DWELLER.get().create(this.level(), EntitySpawnReason.BREEDING);
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return stack.is(UGTags.Items.DWELLER_FOOD);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putInt("jump_cooldown", this.getWildJumpCooldown());
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		this.setWildJumpCooldown(input.getIntOr("jump_cooldown", 300 + this.getRandom().nextInt(500)));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(BOOST_TIME, 0);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
		if (BOOST_TIME.equals(data) && this.level().isClientSide()) {
			this.steering.onSynced();
		}
		super.onSyncedDataUpdated(data);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.wildJumpCooldown > 0 && !this.canJump() && !this.isVehicle()) {
			this.wildJumpCooldown--;
		}
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		boolean isFood = this.isFood(player.getItemInHand(hand));
		if (!isFood && this.isSaddled() && !this.isVehicle() && !player.isSecondaryUseActive()) {
			player.startRiding(this);

			return InteractionResult.SUCCESS;
		} else {
			if (this.isSaddled() && player.isSecondaryUseActive() && player.getItemInHand(hand).isEmpty() && this.level() instanceof ServerLevel sl) {
				this.spawnAtLocation(sl, this.getItemBySlot(EquipmentSlot.SADDLE));
				this.setItemSlot(EquipmentSlot.SADDLE, ItemStack.EMPTY);
				this.playSound(UGSoundEvents.DWELLER_SADDLE_REMOVE.get());
				return InteractionResult.SUCCESS;
			}
			InteractionResult interactionResult = super.mobInteract(player, hand);
			if (!interactionResult.consumesAction()) {
				ItemStack itemStack = player.getItemInHand(hand);
				return this.isEquippableInSlot(itemStack, EquipmentSlot.SADDLE)
					? itemStack.interactLivingEntity(player, this, hand)
					: InteractionResult.PASS;
			} else {
				return interactionResult;
			}
		}
	}

	@Override
	public boolean canUseSlot(EquipmentSlot slot) {
		return slot != EquipmentSlot.SADDLE ? super.canUseSlot(slot) : this.isAlive() && !this.isBaby();
	}

	@Override
	protected boolean canDispenserEquipIntoSlot(EquipmentSlot slot) {
		return slot == EquipmentSlot.SADDLE || super.canDispenserEquipIntoSlot(slot);
	}

	@Override
	protected Holder<SoundEvent> getEquipSound(EquipmentSlot slot, ItemStack stack, Equippable equippable) {
		return slot == EquipmentSlot.SADDLE ? UGSoundEvents.DWELLER_SADDLE : super.getEquipSound(slot, stack, equippable);
	}

	@Override
	public boolean boost() {
		return this.steering.boost(this.getRandom());
	}

	@Override
	protected float getRiddenSpeed(Player player) {
		return (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED) * this.steering.boostFactor();
	}

	@Override
	public @Nullable LivingEntity getControllingPassenger() {
		return this.isSaddled() && this.getFirstPassenger() instanceof Player player && player.isHolding(UGItems.UNDERBEAN_STICK.get())
			? player
			: super.getControllingPassenger();
	}

	@Override
	protected int calculateFallDamage(double fallDistance, float damageModifier) {
		return super.calculateFallDamage(fallDistance, damageModifier) - 10;
	}

	//[VanillaCopy] of Entity.collide, but change the collision box to encapsulate the player if being ridden to prevent them from suffocating in walls.
	//honestly, vanilla should do this too
	@Override
	public Vec3 collide(Vec3 vec) {
		AABB aabb = this.getPassengers().isEmpty() ? this.getBoundingBox() : EntityDimensions.scalable(1.2F, 2.65F).makeBoundingBox(this.position());
		List<VoxelShape> list = this.level().getEntityCollisions(this, aabb.expandTowards(vec));
		Vec3 vec3 = vec.lengthSqr() == 0.0D ? vec : collideBoundingBox(this, vec, aabb, this.level(), list);
		boolean flag = vec.x() != vec3.x();
		boolean flag1 = vec.y() != vec3.y();
		boolean flag2 = vec.z() != vec3.z();
		boolean flag3 = this.onGround() || flag1 && vec.y() < 0.0D;
		float stepHeight = (float) this.getAttribute(Attributes.STEP_HEIGHT).getValue();
		if (stepHeight > 0.0F && flag3 && (flag || flag2)) {
			Vec3 vec31 = collideBoundingBox(this, new Vec3(vec.x(), stepHeight, vec.z()), aabb, this.level(), list);
			Vec3 vec32 = collideBoundingBox(this, new Vec3(0.0D, stepHeight, 0.0D), aabb.expandTowards(vec.x(), 0.0D, vec.z()), this.level(), list);
			if (vec32.y() < (double) stepHeight) {
				Vec3 vec33 = collideBoundingBox(this, new Vec3(vec.x(), 0.0D, vec.z()), aabb.move(vec32), this.level(), list).add(vec32);
				if (vec33.horizontalDistanceSqr() > vec31.horizontalDistanceSqr()) {
					vec31 = vec33;
				}
			}

			if (vec31.horizontalDistanceSqr() > vec3.horizontalDistanceSqr()) {
				return vec31.add(collideBoundingBox(this, new Vec3(0.0D, -vec31.y() + vec.y(), 0.0D), aabb.move(vec31), this.level(), list));
			}
		}

		return vec3;
	}

	@Override
	public void positionRider(Entity passenger, Entity.MoveFunction callback) {
		float ySin = Mth.sin(this.yBodyRot * Mth.DEG_TO_RAD);
		float yCos = Mth.cos(this.yBodyRot * Mth.DEG_TO_RAD);
		Vec3 passengerPosition = this.getPassengerRidingPosition(passenger);
		callback.accept(passenger, this.getX() + (double) (0.5F * ySin), passengerPosition.y() + passenger.getVehicleAttachmentPoint(this).y(), this.getZ() - (double) (0.5F * yCos));
	}

	@Override
	public Vec3 getPassengerRidingPosition(Entity entity) {
		return this.position().add(0, 0.4, 0);
	}

	@Override
	protected Vec3 getPassengerAttachmentPoint(Entity pEntity, EntityDimensions pDimensions, float pScale) {
		return new Vec3(0.0F, 1.5F, 0.0F);
	}

	public boolean isJumping() {
		return this.isJumping;
	}

	public void setIsJumping(boolean jumping) {
		this.isJumping = jumping;
	}

	@Override
	protected void tickRidden(Player player, Vec3 vec) {
		super.tickRidden(player, vec);
		this.steering.tickBoost();
		Vec2 vec2 = this.getRiddenRotation(player);
		this.setRot(vec2.y, vec2.x);
		this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
		if (this.isLocalInstanceAuthoritative()) {
			if (this.onGround()) {
				this.setIsJumping(false);
				if (this.playerJumpPendingScale > 0.0F && !this.isJumping()) {
					this.jump(vec.z() > 0.0D);
				}
				this.playerJumpPendingScale = 0.0F;
			}
		}
	}

	protected Vec2 getRiddenRotation(LivingEntity entity) {
		return new Vec2(entity.getXRot() * 0.5F, entity.getYRot());
	}

	public void jump(boolean moveHorizontally) {
		double d0 = this.getBlockJumpFactor();
		double impulse = d0 + (double) this.getJumpBoostPower();
		Vec3 vec3 = this.getDeltaMovement();
		this.setDeltaMovement(vec3.x(), impulse, vec3.z());
		this.setIsJumping(true);
		this.needsSync = true;
		CommonHooks.onLivingJump(this);
		if (moveHorizontally) {
			float f = Mth.sin(this.getYRot() * Mth.DEG_TO_RAD);
			float f1 = Mth.cos(this.getYRot() * Mth.DEG_TO_RAD);
			this.setDeltaMovement(this.getDeltaMovement().add(-0.4F * f, 0.0D, 0.4F * f1));
		}
	}

	@Override
	public void onPlayerJump(int power) {
		if (this.isSaddled() && this.onGround()) {
			this.playerJumpPendingScale = 1.0F;
		}
	}

	@Override
	public boolean canJump() {
		return this.isSaddled() && this.getControllingPassenger() != null;
	}

	@Override
	public void handleStartJump(int pJumpPower) {
		if (this.onGround()) {
			this.playSound(UGSoundEvents.DWELLER_JUMP.get(), this.getSoundVolume(), this.getVoicePitch());
			//ensure we dont play an ambient sound while jumping
			this.ambientSoundTime = -30;
		}
	}

	@Override
	public void handleStopJump() {

	}

	@Nullable
	public PanicGoal getPanicGoal() {
		return this.panicGoal;
	}

	@Nullable
	public DwellerAvoidEntityGoal<?> getAvoidGoal() {
		return this.avoidGoal;
	}

	public int getWildJumpCooldown() {
		return this.wildJumpCooldown;
	}

	public void setWildJumpCooldown(int cooldown) {
		this.wildJumpCooldown = cooldown;
	}

	public static class DwellerAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {

		private boolean running;

		public DwellerAvoidEntityGoal(PathfinderMob pMob, Class<T> pEntityClassToAvoid, float pMaxDistance, double pWalkSpeedModifier, double pSprintSpeedModifier) {
			super(pMob, pEntityClassToAvoid, pMaxDistance, pWalkSpeedModifier, pSprintSpeedModifier);
		}

		@Override
		public void start() {
			super.start();
			this.running = true;
		}

		@Override
		public void stop() {
			super.stop();
			this.running = false;
		}

		public boolean isRunning() {
			return this.running;
		}
	}
}