package quek.undergarden.entity.stoneborn;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.registry.UndergardenSoundEvents;

import java.util.UUID;

public class StonebornEntity extends MonsterEntity {

    private static final DataParameter<Boolean> isChild = EntityDataManager.createKey(StonebornEntity.class, DataSerializers.BOOLEAN);
    private static final UUID BABY_SPEED_MODIFIER_IDENTIFIER = UUID.fromString("766bfa64-11f3-11ea-8d71-362b9e155667");
    private static final AttributeModifier BABY_SPEED_MODIFIER = new AttributeModifier(BABY_SPEED_MODIFIER_IDENTIFIER, "Baby speed boost", 0.2F, AttributeModifier.Operation.MULTIPLY_BASE);

    protected static final ImmutableList<SensorType<? extends Sensor<? super StonebornEntity>>> sensorTypes = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.NEAREST_PLAYERS,
            SensorType.field_234129_b_, //nearest item
            SensorType.HURT_BY,
            SensorType.INTERACTABLE_DOORS
    );
    protected static final ImmutableList<MemoryModuleType<?>> memoryModuleTypes = ImmutableList.of(
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.INTERACTABLE_DOORS,
            MemoryModuleType.field_225462_q, //opened doors
            MemoryModuleType.MOBS,
            MemoryModuleType.VISIBLE_MOBS,
            MemoryModuleType.NEAREST_VISIBLE_PLAYER,
            MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER,
            MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.INTERACTION_TARGET,
            MemoryModuleType.PATH,
            MemoryModuleType.AVOID_TARGET,
            MemoryModuleType.ADMIRING_ITEM,
            MemoryModuleType.ADMIRING_DISABLED,
            MemoryModuleType.NEAREST_VISIBLE_NEMESIS,
            MemoryModuleType.ATE_RECENTLY
    );

    private int timeInOverworld = 0;
    private final Inventory inventory = new Inventory(8);

    public StonebornEntity(EntityType<? extends StonebornEntity> type, World worldIn) {
        super(type, worldIn);
        ((GroundPathNavigator)this.getNavigator()).setBreakDoors(true);
        this.setCanPickUpLoot(true);
        this.stepHeight = 1.0F;
    }

    @Override
    protected Brain.BrainCodec<StonebornEntity> getBrainCodec() {
        return Brain.func_233705_a_(memoryModuleTypes, sensorTypes);
    }

    @Override
    protected Brain<?> createBrain(Dynamic<?> dynamic) {
        return StonebornTasks.getStonebornTasks(this, getBrainCodec().func_233748_a_(dynamic));
    }

    @Override
    public Brain<StonebornEntity> getBrain() {
        return (Brain<StonebornEntity>)super.getBrain();
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return AgeableEntity.func_233666_p_()
                .func_233815_a_(Attributes.MAX_HEALTH, 30.0D)
                .func_233815_a_(Attributes.MOVEMENT_SPEED, 0.5D)
                .func_233815_a_(Attributes.KNOCKBACK_RESISTANCE, 0.9D);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    /* TODO: wait for screem to make the stoneborn sounds

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UndergardenSoundEvents.;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UndergardenSoundEvents.;
    }
    */

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(UndergardenSoundEvents.STONEBORN_STEP, 0.5F, 1.0F);
    }

    @Override
    protected boolean isDespawnPeaceful() {
        return false;
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return !this.isNoDespawnRequired();
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        if (this.isChild()) {
            compound.putBoolean("IsBaby", true);
        }

        compound.putInt("TimeInOverworld", this.timeInOverworld);
        compound.put("Inventory", this.inventory.write());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setChild(compound.getBoolean("IsBaby"));
        this.timeInOverworld = compound.getInt("TimeInOverworld");
        this.inventory.read(compound.getList("Inventory", 10));
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(isChild, false);
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {
        super.notifyDataManagerChange(key);
        if (isChild.equals(key)) {
            this.recalculateSize();
        }
    }

    @Override
    public void setChild(boolean childStoneborn) {
        this.getDataManager().set(isChild, childStoneborn);
        if (!this.world.isRemote) {
            ModifiableAttributeInstance modifiableattributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
            modifiableattributeinstance.removeModifier(BABY_SPEED_MODIFIER);
            if (childStoneborn) {
                modifiableattributeinstance.func_233767_b_(BABY_SPEED_MODIFIER);
            }
        }

    }

    @Override
    public boolean isChild() {
        return this.getDataManager().get(isChild);
    }

    public boolean inOverworld() {
        return !this.world.func_230315_m_().func_241509_i_() && !this.isAIDisabled();
    }

    @Override
    protected void updateAITasks() {
        this.world.getProfiler().startSection("stonebornBrain");
        this.getBrain().tick((ServerWorld)this.world, this);
        this.world.getProfiler().endSection();
        StonebornTasks.stuff(this);
        if (this.inOverworld()) {
            ++this.timeInOverworld;
            this.addPotionEffect(new EffectInstance(Effects.NAUSEA, 200, 0));
        } else {
            this.timeInOverworld = 0;
        }

        if (this.timeInOverworld > 300) {
            this.remove(false);
            this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 3, Explosion.Mode.BREAK);
        }

    }

    @Override
    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropSpecialItems(source, looting, recentlyHitIn);
        this.inventory.func_233543_f_().forEach(this::entityDropItem);
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? sizeIn.height * 0.90F : 2.3F;
    }
}
