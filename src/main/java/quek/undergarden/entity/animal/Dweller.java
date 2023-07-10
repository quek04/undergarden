package quek.undergarden.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.entity.rotspawn.RotspawnMonster;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nullable;

public class Dweller extends Animal implements ItemSteerable, Saddleable {

	private static final EntityDataAccessor<Boolean> SADDLE = SynchedEntityData.defineId(Dweller.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> BOOST_TIME = SynchedEntityData.defineId(Dweller.class, EntityDataSerializers.INT);
	private final ItemBasedSteering steering = new ItemBasedSteering(this.getEntityData(), BOOST_TIME, SADDLE);

	public Dweller(EntityType<? extends Dweller> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 2.5D));
		this.goalSelector.addGoal(1, new TemptGoal(this, 1.5D, Ingredient.of(UGItems.UNDERBEANS.get(), UGItems.UNDERBEAN_STICK.get()), false));
		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, RotspawnMonster.class, 12.0F, 2.0D, 2.5D));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Animal.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 15.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.15D);
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
		return UGEntityTypes.DWELLER.get().create(this.level());
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return Ingredient.of(UGItems.UNDERBEANS.get()).test(stack);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		this.steering.addAdditionalSaveData(tag);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.steering.readAdditionalSaveData(tag);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(SADDLE, false);
		this.getEntityData().define(BOOST_TIME, 0);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
		if (BOOST_TIME.equals(data) && this.level().isClientSide()) {
			this.steering.onSynced();
		}
		super.onSyncedDataUpdated(data);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		boolean isFood = this.isFood(player.getItemInHand(hand));
		if (!isFood && this.isSaddled() && !this.isVehicle() && !player.isSecondaryUseActive()) {
			if (!this.level().isClientSide()) {
				player.startRiding(this);
			}

			return InteractionResult.sidedSuccess(this.level().isClientSide());
		} else {
			if (this.isSaddled() && player.isSecondaryUseActive() && player.getItemInHand(hand).isEmpty()) {
				this.spawnAtLocation(Items.SADDLE);
				this.playSound(UGSoundEvents.DWELLER_SADDLE_REMOVE.get());
				this.steering.setSaddle(false);
				return InteractionResult.sidedSuccess(this.level().isClientSide());
			}
			InteractionResult result = super.mobInteract(player, hand);
			if (!result.consumesAction()) {
				ItemStack itemstack = player.getItemInHand(hand);
				return itemstack.is(Items.SADDLE) ? itemstack.interactLivingEntity(player, this, hand) : InteractionResult.PASS;
			} else {
				return result;
			}
		}
	}

	@Override
	public boolean isSaddleable() {
		return this.isAlive() && !this.isBaby();
	}

	@Override
	public void equipSaddle(@Nullable SoundSource source) {
		this.steering.setSaddle(true);
		if (source != null) {
			this.level().playSound(null, this, SoundEvents.PIG_SADDLE, source, 0.5F, 1.0F);
		}
	}

	@Override
	public boolean isSaddled() {
		return this.steering.hasSaddle();
	}

	@Override
	public boolean boost() {
		return this.steering.boost(this.getRandom());
	}


	@Override
	protected float getRiddenSpeed(Player player) {
		return (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 1.1F;
	}

	@Nullable
	@Override
	public LivingEntity getControllingPassenger() {
		Entity entity = this.getFirstPassenger();
		return entity != null && this.canBeControlledBy(entity) && entity instanceof LivingEntity living ? living : null;
	}

	private boolean canBeControlledBy(Entity entity) {
		if (this.isSaddled() && entity instanceof Player player) {
			return player.getMainHandItem().is(UGItems.UNDERBEAN_STICK.get()) || player.getOffhandItem().is(UGItems.UNDERBEAN_STICK.get());
		} else {
			return false;
		}
	}

	@Override
	protected void dropEquipment() {
		super.dropEquipment();
		if (this.isSaddled()) {
			this.spawnAtLocation(Items.SADDLE);
		}
	}

	@Override
	public void positionRider(Entity passenger, Entity.MoveFunction callback) {
		float ySin = Mth.sin(this.yBodyRot * ((float) Math.PI / 180F));
		float yCos = Mth.cos(this.yBodyRot * ((float) Math.PI / 180F));
		callback.accept(passenger, this.getX() + (double) (0.5F * ySin), this.getY() + this.getPassengersRidingOffset() + passenger.getMyRidingOffset() - 0.1F, this.getZ() - (double) (0.5F * yCos));
	}
}