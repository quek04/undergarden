package quek.undergarden.entity.monster;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.function.IntFunction;

public class Denizen extends Monster implements VariantHolder<Denizen.Type> {
	private static final EntityDataAccessor<Integer> TYPE_ID = SynchedEntityData.defineId(Denizen.class, EntityDataSerializers.INT);
	public Denizen(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.6D));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Monster.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 20.0D)
			.add(Attributes.ATTACK_DAMAGE, 5.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.3D);
	}

	@Override
	public float getMyRidingOffset(Entity entity) {
		return -0.5F;
	}

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
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(TYPE_ID, 0);
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
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		data = super.finalizeSpawn(level, difficulty, reason, data, tag);
		if (level.getRandom().nextBoolean()) {
			this.setVariant(Type.TALL);
		} else {
			this.setVariant(Type.SHORT);
		}
		return data;
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