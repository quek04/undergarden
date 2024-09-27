package quek.undergarden.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.entity.monster.rotspawn.RotspawnMonster;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;
import quek.undergarden.registry.UGTags;

import java.util.UUID;

public class GreaterDweller extends Animal implements NeutralMob {

	private static final UniformInt ANGER_TIME_RANGE = TimeUtil.rangeOfSeconds(20, 39);
	private int angerTime;
	private UUID targetUuid;
	private int attackTimer;

	public GreaterDweller(EntityType<? extends GreaterDweller> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(0, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5F, true));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, RotspawnMonster.class, 12.0F, 1.5F, 1.5F));
		this.goalSelector.addGoal(2, new TemptGoal(this, 1.0D, Ingredient.of(UGItems.UNDERBEANS.get()), false));
		this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
		this.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(this, true));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Animal.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 100.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.2D)
			.add(Attributes.ATTACK_DAMAGE, 10.0D);
	}

	public static boolean checkGreaterDwellerSpawnRules(EntityType<? extends Animal> animal, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {;
		return level.getBlockState(pos.below()).is(UGTags.Blocks.GREATER_DWELLER_SPAWNABLE_ON);
	}

	/*@Override
	public boolean checkSpawnRules(LevelAccessor accessor, MobSpawnType type) {
		return true;
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader level) {
		return level.isUnobstructed(this);
	}*/

	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader level) {
		return level.getBlockState(pos.below()).is(UGTags.Blocks.GREATER_DWELLER_SPAWNABLE_ON) ? 10.0F : level.getPathfindingCostFromLightLevels(pos);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return UGSoundEvents.GREATER_DWELLER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return UGSoundEvents.GREATER_DWELLER_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.GREATER_DWELLER_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(UGSoundEvents.DWELLER_STEP.get(), 0.15F, 1.0F);
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (this.attackTimer > 0) {
			--this.attackTimer;
		}
	}

	@Override
	protected void customServerAiStep() {
		this.updatePersistentAnger((ServerLevel)this.level(), true);

		if (this.isAngry()) {
			this.lastHurtByPlayerTime = this.tickCount;
		}
		super.customServerAiStep();
	}

	@Override
	public void setTarget(@Nullable LivingEntity target) {
		if (target instanceof Player) {
			this.setLastHurtByPlayer((Player)target);
		}
		super.setTarget(target);
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		this.attackTimer = 10;
		this.level().broadcastEntityEvent(this, (byte) 4);
		return super.doHurtTarget(entity);
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 4) {
			this.attackTimer = 10;
		} else {
			super.handleEntityEvent(id);
		}
	}

	public int getAttackTimer() {
		return this.attackTimer;
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return Ingredient.of(UGItems.UNDERBEANS.get()).test(stack);
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
		return UGEntityTypes.GREATER_DWELLER.get().create(this.level());
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		this.addPersistentAngerSaveData(compound);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.readPersistentAngerSaveData(this.level(), compound);
	}

	@Override
	public int getRemainingPersistentAngerTime() {
		return this.angerTime;
	}

	@Override
	public void setRemainingPersistentAngerTime(int time) {
		this.angerTime = time;
	}

	@javax.annotation.Nullable
	@Override
	public UUID getPersistentAngerTarget() {
		return this.targetUuid;
	}

	@Override
	public void setPersistentAngerTarget(@javax.annotation.Nullable UUID target) {
		this.targetUuid = target;
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setRemainingPersistentAngerTime(ANGER_TIME_RANGE.sample(this.random));
	}
}
