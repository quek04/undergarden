package quek.undergarden.entity.monster.denizen;

import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.entity.projectile.ThrownSpear;
import quek.undergarden.registry.UGItems;

import java.util.function.IntFunction;

public class Denizen extends Monster implements VariantHolder<Denizen.Type>, RangedAttackMob {
	private static final EntityDataAccessor<Integer> TYPE_ID = SynchedEntityData.defineId(Denizen.class, EntityDataSerializers.INT);

	private static final EntityDimensions SHORT = EntityDimensions.scalable(0.7F, 2.0F);
	private static final EntityDimensions SHORT_SITTING = EntityDimensions.scalable(0.7F, 1.5F);
	private static final EntityDimensions TALL = EntityDimensions.scalable(0.7F, 3.5F);
	private static final EntityDimensions TALL_SITTING = EntityDimensions.scalable(0.7F, 2.0F);

	@Nullable
	private LivingEntity stareTarget;
	private DenizenChillByCampfireGoal campfireGoal;

	public Denizen(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new DenizenStareAtPlayerGoal(this));
		this.goalSelector.addGoal(2, new DenizenSpearAttackGoal(this, 1.0F, 40, 10.0F));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(3, new DenizenWanderGoal(this, 0.6D));

		this.goalSelector.addGoal(4, this.campfireGoal = new DenizenChillByCampfireGoal(this));
		this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Denizen.class, 8.0F) {
			@Override
			public boolean canUse() {
				return Denizen.this.getStareTarget() == null && super.canUse();
			}
		});
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new DenizenStareDownTargetGoal(this));
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

	/*@Override
	public EntityDimensions getDimensions(Pose pose) {
		return switch (this.getVariant()) {
			case SHORT -> this.hasPose(Pose.SITTING) ? SHORT_SITTING : SHORT;
			case TALL -> this.hasPose(Pose.SITTING) ? TALL_SITTING : TALL;
		};
	}*/

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putString("Type", this.getVariant().getSerializedName());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setVariant(Type.byName(tag.getString("Type")));
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

	@Override
	public void setVariant(Type variant) {
		this.getEntityData().set(TYPE_ID, variant.getId());
	}

	@Override
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
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(UGItems.SPEAR.get()));
			} else if (random.nextBoolean()) {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_SWORD));
			} else this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_AXE));

		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		boolean flag = super.hurt(source, amount);
		if (flag && this.hasPose(Pose.SITTING)) {
			this.setPose(Pose.STANDING);
		}
		return flag;
	}

	@Override
	public void onRemovedFromLevel() {
		super.onRemovedFromLevel();
		if (this.level() instanceof ServerLevel) {
			this.campfireGoal.stop();
		}
	}



	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData data) {
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
		ThrownSpear spear = new ThrownSpear(this.level(), this, new ItemStack(UGItems.SPEAR.get()));
		double x = pTarget.getX() - this.getX();
		double y = pTarget.getY(0.3333333333333333) - spear.getY();
		double z = pTarget.getZ() - this.getZ();
		double d3 = Math.sqrt(x * x + z * z);
		spear.shoot(x, y + d3 * 0.2F, z, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
		this.playSound(SoundEvents.ARROW_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.level().addFreshEntity(spear);
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