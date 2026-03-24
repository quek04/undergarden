package quek.undergarden.entity.monster.denizen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import quek.undergarden.entity.monster.rotspawn.RotspawnMonster;
import quek.undergarden.entity.projectile.ThrownJavelin;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGPointOfInterests;

import java.util.Optional;
import java.util.function.IntFunction;

public class Denizen extends Monster implements RangedAttackMob {
	private static final EntityDataAccessor<Integer> TYPE_ID = SynchedEntityData.defineId(Denizen.class, EntityDataSerializers.INT);

	private static final EntityDimensions SHORT = EntityDimensions.scalable(0.85F, 1.9F).withEyeHeight(1.75F);
	private static final EntityDimensions SHORT_SITTING = EntityDimensions.scalable(0.85F, 1.4F).withEyeHeight(1.2F);
	private static final EntityDimensions TALL = EntityDimensions.scalable(0.85F, 3.3F).withEyeHeight(3.0F);
	private static final EntityDimensions TALL_SITTING = EntityDimensions.scalable(0.85F, 1.8F).withEyeHeight(1.4F);

	@Nullable
	private LivingEntity stareTarget;
	@Nullable
	private BlockPos satAtCampfire;

	public Denizen(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new DenizenStareAtPlayerGoal(this));
		this.goalSelector.addGoal(2, new DenizenJavelinAttackGoal(this, 1.0F, 40, 10.0F));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(3, new DenizenWanderGoal(this, 0.6D));

		this.goalSelector.addGoal(4, new DenizenChillByCampfireGoal(this));
		this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Denizen.class, 8.0F) {
			@Override
			public boolean canUse() {
				return Denizen.this.getStareTarget() == null && super.canUse();
			}
		});
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new DenizenStareDownTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, RotspawnMonster.class, false));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Monster.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 20.0D)
			.add(Attributes.ATTACK_DAMAGE, 5.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.3D);
	}

	@Override
	public Vec3 getVehicleAttachmentPoint(Entity entity) {
		return new Vec3(0.0F, -0.5F, 0.0F);
	}

	@Override
	protected EntityDimensions getDefaultDimensions(Pose pose) {
		return switch (this.getVariant()) {
			case SHORT -> this.hasPose(Pose.SITTING) ? SHORT_SITTING : SHORT;
			case TALL -> this.hasPose(Pose.SITTING) ? TALL_SITTING : TALL;
		};
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putString("type", this.getVariant().getSerializedName());
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		this.setVariant(Type.byName(input.getStringOr("type", Type.SHORT.getSerializedName())));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(TYPE_ID, 0);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
		if (TYPE_ID.equals(accessor)) {
			this.refreshDimensions();
		}
		super.onSyncedDataUpdated(accessor);
	}

	public void setVariant(Type variant) {
		this.getEntityData().set(TYPE_ID, variant.getId());
	}

	public Type getVariant() {
		return Type.byId(this.entityData.get(TYPE_ID));
	}

	@Nullable
	public LivingEntity getStareTarget() {
		return this.stareTarget;
	}

	public void setStareTarget(@Nullable LivingEntity target) {
		this.stareTarget = target;
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		if (random.nextBoolean()) {
			if (random.nextBoolean()) {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(UGItems.JAVELIN.get()));
			} else if (random.nextBoolean()) {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_SWORD));
			} else this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_AXE));

		}
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
		boolean flag = super.hurtServer(level, source, amount);
		if (flag && this.hasPose(Pose.SITTING)) {
			this.setPose(Pose.STANDING);
		}
		return flag;
	}

	@Override
	public void onRemovedFromLevel() {
		super.onRemovedFromLevel();
		if (this.level() instanceof ServerLevel level) {
			this.resetCampfireLogic(level);
		}
	}

	public @Nullable BlockPos getCampfire() {
		return this.satAtCampfire;
	}

	public void setCampfire(@Nullable BlockPos pos) {
		this.satAtCampfire = pos;
	}

	public void resetCampfireLogic(ServerLevel level) {
		if (this.satAtCampfire != null) {
			Optional<Holder<PoiType>> maybeCampfire = level.getPoiManager().getType(this.satAtCampfire);
			if (maybeCampfire.isPresent() && maybeCampfire.get().is(UGPointOfInterests.DENIZEN_RESTING_BLOCKS.getKey())) {
				level.getPoiManager().release(this.satAtCampfire);
				//Undergarden.LOGGER.debug("Denizen released campfire at {} (spots free: {})", this.satAtCampfire, ((ServerLevel) this.level()).getPoiManager().getFreeTickets(this.satAtCampfire));
			}
		}
		this.setPose(Pose.STANDING);
		this.satAtCampfire = null;
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason reason, @Nullable SpawnGroupData data) {
		data = super.finalizeSpawn(level, difficulty, reason, data);
		if (level.getRandom().nextBoolean()) {
			this.setVariant(Type.TALL);
		} else {
			this.setVariant(Type.SHORT);
		}
		this.populateDefaultEquipmentSlots(level.getRandom(), difficulty);
		return data;
	}

	@Override
	public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
		ThrownJavelin javelin = new ThrownJavelin(this.level(), this, new ItemStack(UGItems.JAVELIN.get()));
		double x = pTarget.getX() - this.getX();
		double y = pTarget.getY(0.3333333333333333) - javelin.getY();
		double z = pTarget.getZ() - this.getZ();
		double d3 = Math.sqrt(x * x + z * z);
		javelin.shoot(x, y + d3 * 0.2F, z, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
		this.playSound(SoundEvents.ARROW_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.level().addFreshEntity(javelin);
	}

	public enum Type implements StringRepresentable {
		SHORT(0, "short"),
		TALL(1, "tall");

		public static final StringRepresentable.EnumCodec<Type> CODEC = StringRepresentable.fromEnum(Type::values);
		private static final IntFunction<Type> BY_ID = ByIdMap.continuous(Type::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
		private final int id;
		private final String name;
		Type(int id, String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}

		public int getId() {
			return this.id;
		}

		public static Type byName(String name) {
			return CODEC.byName(name, SHORT);
		}

		public static Type byId(int index) {
			return BY_ID.apply(index);
		}
	}
}