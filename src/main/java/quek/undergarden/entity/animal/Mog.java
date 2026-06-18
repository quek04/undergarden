package quek.undergarden.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.common.IShearable;
import net.neoforged.neoforge.fluids.FluidType;
import org.jspecify.annotations.Nullable;
import quek.undergarden.registry.UGBuiltinLootTables;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGSoundEvents;
import quek.undergarden.registry.UGTags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mog extends Animal implements IShearable {

	private static final EntityDataAccessor<Boolean> HAS_MOSS = SynchedEntityData.defineId(Mog.class, EntityDataSerializers.BOOLEAN);
	private int timeWithoutMoss;

	public Mog(EntityType<? extends Animal> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.5D));
		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new TemptGoal(this, 1.1D, stack -> stack.is(UGTags.Items.MOG_FOOD), false));
		this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Animal.createAnimalAttributes()
			.add(Attributes.MAX_HEALTH, 20.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.1D)
			.add(Attributes.KNOCKBACK_RESISTANCE, 0.9D)
			.add(Attributes.STEP_HEIGHT, 1.0D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return UGSoundEvents.MOG_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return UGSoundEvents.MOG_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.MOG_DEATH.get();
	}

	@Override
	public boolean canDrownInFluidType(FluidType type) {
		return false;
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		return UGEntityTypes.MOG.get().create(level, EntitySpawnReason.BREEDING);
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return stack.is(UGTags.Items.MOG_FOOD);
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.hasMoss()) {
			this.timeWithoutMoss++;
		} else {
			this.timeWithoutMoss = 0;
		}

		if (this.timeWithoutMoss == 6000) {
			this.setMoss(true);
		}
	}

	public boolean hasMoss() {
		return this.getEntityData().get(HAS_MOSS);
	}

	public void setMoss(boolean hasMoss) {
		this.getEntityData().set(HAS_MOSS, hasMoss);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putBoolean("has_moss", this.hasMoss());
		output.putInt("time_without_moss", this.timeWithoutMoss);
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		this.setMoss(input.getBooleanOr("has_moss", true));
		this.timeWithoutMoss = input.getIntOr("time_without_moss", 0);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(HAS_MOSS, true);
	}

	@Override
	public float getAgeScale() {
		return this.isBaby() ? 0.6F : 1.0F;
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason reason, @Nullable SpawnGroupData data) {
		this.setMoss(true);
		return super.finalizeSpawn(level, difficulty, reason, data);
	}

	@Override
	public boolean isShearable(@Nullable Player player, ItemStack item, Level level, BlockPos pos) {
		return this.hasMoss() && this.isAlive() && !this.isBaby();
	}

	@Override
	public List<ItemStack> onSheared(@Nullable Player player, ItemStack item, Level level, BlockPos pos) {
		List<ItemStack> drops = new ArrayList<>();
		level.playSound(null, this, SoundEvents.SHEEP_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
		if (level instanceof ServerLevel serverLevel) {
			this.dropFromShearingLootTable(serverLevel, this.getShearTable(), item, (l, drop) -> drops.add(drop));
			this.setMoss(false);
		}
		return Collections.unmodifiableList(drops);
	}

	public ResourceKey<LootTable> getShearTable() {
		return UGBuiltinLootTables.SHEAR_MOG;
	}
}