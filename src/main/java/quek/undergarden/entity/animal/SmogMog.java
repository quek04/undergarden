package quek.undergarden.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jspecify.annotations.Nullable;
import quek.undergarden.registry.*;

public class SmogMog extends Mog {
	public SmogMog(EntityType<? extends Animal> type, Level level) {
		super(type, level);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Animal.createAnimalAttributes()
				.add(Attributes.MAX_HEALTH, 30.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.1D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.9D);
	}

	public static boolean checkSmogMogSpawnRules(EntityType<? extends Animal> entity, LevelAccessor level, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos.below()).is(UGTags.Blocks.SMOG_MOG_SPAWNABLE_ON);
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader level) {
		return level.getBlockState(pos.below()).is(UGTags.Blocks.SMOG_MOG_SPAWNABLE_ON) ? 10.0F : level.getPathfindingCostFromLightLevels(pos);
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
		return UGEntityTypes.SMOG_MOG.get().create(level, EntitySpawnReason.BREEDING);
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

	public ResourceKey<LootTable> getShearTable() {
		return UGBuiltinLootTables.SHEAR_SMOG_MOG;
	}
}
