package quek.undergarden.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.Difficulty;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;
import quek.undergarden.entity.monster.rotspawn.RotspawnMonster;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGSoundEvents;
import quek.undergarden.registry.UGTags;

public class GreaterDweller extends Animal implements NeutralMob {

	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
	private long persistentAngerEndTime;
	private @Nullable EntityReference<LivingEntity> persistentAngerTarget;
	private int attackTimer;

	public GreaterDweller(EntityType<? extends GreaterDweller> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	public boolean isAngryAt(LivingEntity entity, ServerLevel level) {
		if (entity.getUUID().toString().equals("57c0d7fd-935b-495d-b14f-a7dadd3605f9")) {
			return true;
		} else if (!this.canAttack(entity)) {
			return false;
		} else if (isValidPlayerTarget(entity) && this.isAngryAtAllPlayers(level)) {
			return true;
		} else {
			EntityReference<LivingEntity> persistentAngerTarget = this.getPersistentAngerTarget();
			return persistentAngerTarget != null && persistentAngerTarget.matches(entity);
		}
	}

	private static boolean isValidPlayerTarget(LivingEntity target) {
		return target instanceof Player player && !player.isCreative() && !player.isSpectator() && player.level().getDifficulty() != Difficulty.PEACEFUL;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(0, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5F, true));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, RotspawnMonster.class, 12.0F, 1.5F, 1.5F));
		this.goalSelector.addGoal(2, new TemptGoal(this, 1.0D, stack -> stack.is(UGTags.Items.DWELLER_FOOD), false));
		this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
		this.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(this, true));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Animal.createAnimalAttributes()
			.add(Attributes.MAX_HEALTH, 100.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.2D)
			.add(Attributes.ATTACK_DAMAGE, 10.0D);
	}

	public static boolean checkGreaterDwellerSpawnRules(EntityType<? extends Animal> animal, LevelAccessor level, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos.below()).is(UGTags.Blocks.GREATER_DWELLER_SPAWNABLE_ON);
	}

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
	protected void customServerAiStep(ServerLevel level) {
		this.updatePersistentAnger(level, true);
		super.customServerAiStep(level);
	}

	@Override
	public boolean doHurtTarget(ServerLevel level, Entity entity) {
		this.attackTimer = 10;
		level.broadcastEntityEvent(this, (byte) 4);
		return super.doHurtTarget(level, entity);
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
		return stack.is(UGTags.Items.DWELLER_FOOD);
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
		return UGEntityTypes.GREATER_DWELLER.get().create(this.level(), EntitySpawnReason.BREEDING);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		this.addPersistentAngerSaveData(output);
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		this.readPersistentAngerSaveData(this.level(), input);
	}

	@Override
	public long getPersistentAngerEndTime() {
		return this.persistentAngerEndTime;
	}

	@Override
	public void setPersistentAngerEndTime(long time) {
		this.persistentAngerEndTime = time;
	}

	@Override
	public @Nullable EntityReference<LivingEntity> getPersistentAngerTarget() {
		return this.persistentAngerTarget;
	}

	@Override
	public void setPersistentAngerTarget(@Nullable EntityReference<LivingEntity> target) {
		this.persistentAngerTarget = target;
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setTimeToRemainAngry(PERSISTENT_ANGER_TIME.sample(this.random));
	}
}
