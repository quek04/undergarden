package quek.undergarden.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.EventHooks;
import org.jspecify.annotations.Nullable;
import quek.undergarden.entity.monster.rotspawn.RotspawnMonster;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGSoundEvents;
import quek.undergarden.registry.UGTags;

public class Scintling extends Animal {

	public Scintling(EntityType<? extends Animal> type, Level level) {
		super(type, level);
		this.xpReward = 0;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, RotspawnMonster.class, 12.0F, 1.2D, 1.4D));
		this.goalSelector.addGoal(1, new TemptGoal(this, 1.5D, stack -> stack.is(UGTags.Items.SCINTLING_FOOD), false));
		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Animal.createAnimalAttributes()
				.add(Attributes.MAX_HEALTH, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.STEP_HEIGHT, 1.0D);
	}

	public static boolean canScintlingSpawn(EntityType<? extends Animal> type, LevelAccessor level, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos.below()).is(UGTags.Blocks.SCINTLING_SPAWNABLE_ON);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return UGSoundEvents.SCINTLING_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.SCINTLING_DEATH.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (this.level() instanceof ServerLevel serverLevel) {
			if (this.isBaby() || !EventHooks.canEntityGrief(serverLevel, this)) {
				return;
			}

			BlockState goo = UGBlocks.GOO.get().defaultBlockState();

			for (int l = 0; l < 4; ++l) {
				int x = Mth.floor(this.getX() + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
				int y = Mth.floor(this.getY());
				int z = Mth.floor(this.getZ() + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
				BlockPos blockpos = new BlockPos(x, y, z);
				if (this.level().isEmptyBlock(blockpos) && goo.canSurvive(this.level(), blockpos)) {
					this.level().setBlockAndUpdate(blockpos, goo);
				}
			}
		}
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 45) {
			for (int i = 0; i < 7; i++) {
				double xa = this.getRandom().nextGaussian() * 0.01;
				double ya = this.getRandom().nextGaussian() * 0.01;
				double za = this.getRandom().nextGaussian() * 0.01;
				this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0), this.getRandomY() + 0.2, this.getRandomZ(1.0), xa, ya, za);
			}
		} else {
			super.handleEntityEvent(id);
		}
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		return UGEntityTypes.SCINTLING.get().create(this.level(), EntitySpawnReason.BREEDING);
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return stack.is(UGTags.Items.SCINTLING_FOOD);
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(UGSoundEvents.SCINTLING_STEP.get(), 0.3F, 1.0F);
	}
}