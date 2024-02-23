package quek.undergarden.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import quek.undergarden.registry.*;

import javax.annotation.Nullable;

public class SmogMog extends Mog {
	public SmogMog(EntityType<? extends Animal> type, Level level) {
		super(type, level);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Animal.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 30.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.1D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.9D);
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor accessor, MobSpawnType type) {
		return true;
	}

	public static boolean checkSmogMogSpawnRules(EntityType<? extends Animal> entity, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos.below()).is(UGTags.Blocks.SMOG_MOG_SPAWNABLE_ON);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return UGSoundEvents.SMOG_MOG_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return UGSoundEvents.SMOG_MOG_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.SMOG_MOG_DEATH.get();
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		return UGEntityTypes.SMOG_MOG.get().create(level);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 0.4F;
	}

	@Override
	public void aiStep() {
		if (this.level().isClientSide() && this.tickCount % 2 == 0) {
			double x = this.getX();
			double y = this.getY() + this.getBbHeight();
			double z = this.getZ();
			if (this.isAlive()) {
				this.level().addAlwaysVisibleParticle(UGParticleTypes.SMOG.get(), x, y, z, 0.0D, 0.05D, 0.0D);
			}
		}
		super.aiStep();
	}

	@Override
	public Item getMossItem() {
		return UGItems.BLUE_MOGMOSS.get();
	}
}
