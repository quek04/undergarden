package quek.undergarden.entity.animal;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGSoundEvents;
import quek.undergarden.registry.UGTags;

public class Brute extends Animal implements NeutralMob {

	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
	private long persistentAngerEndTime;
	private @Nullable EntityReference<LivingEntity> persistentAngerTarget;

	public Brute(EntityType<? extends Animal> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 2.0F, false));
		this.goalSelector.addGoal(2, new TemptGoal(this, 1.0D, stack -> stack.is(UGTags.Items.BRUTE_FOOD), false));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Brute.class, 6.0F));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(2, new ResetUniversalAngerTargetGoal<>(this, true));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Animal.createAnimalAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.23D)
				.add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return UGSoundEvents.BRUTE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return UGSoundEvents.BRUTE_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.BRUTE_DEATH.get();
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

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob entity) {
		return UGEntityTypes.BRUTE.get().create(level, EntitySpawnReason.BREEDING);
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return stack.is(UGTags.Items.BRUTE_FOOD);
	}

	@Override
	public boolean canBeLeashed() {
		return false;
	}
}