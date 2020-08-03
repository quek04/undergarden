package quek.undergarden.entity.rotspawn;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.HoglinEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.entity.DwellerEntity;
import quek.undergarden.registry.UndergardenEntities;
import quek.undergarden.registry.UndergardenSensorTypes;

import java.util.Random;

public abstract class AbstractRotspawnEntity extends MonsterEntity {

    protected static final ImmutableList<? extends SensorType<? extends Sensor<? super AbstractRotspawnEntity>>> sensorTypes = ImmutableList.of(
            UndergardenSensorTypes.rotspawn_sensor,
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.NEAREST_PLAYERS
    );
    protected static final ImmutableList<? extends MemoryModuleType<?>> memoryModuleTypes = ImmutableList.of(
            MemoryModuleType.MOBS,
            MemoryModuleType.VISIBLE_MOBS,
            MemoryModuleType.NEAREST_VISIBLE_PLAYER,
            MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER,
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.PATH,
            MemoryModuleType.ATTACK_TARGET,
            MemoryModuleType.ATTACK_COOLING_DOWN,
            MemoryModuleType.AVOID_TARGET,
            MemoryModuleType.NEAREST_REPELLENT,
            MemoryModuleType.PACIFIED
    );

    protected AbstractRotspawnEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected Brain.BrainCodec<AbstractRotspawnEntity> getBrainCodec() {
        return Brain.func_233705_a_(memoryModuleTypes, sensorTypes);
    }

    @Override
    protected Brain<?> createBrain(Dynamic<?> dynamic) {
        return RotspawnTasks.getRotspawnTasks(getBrainCodec().func_233748_a_(dynamic));
    }

    @Override
    public Brain<AbstractRotspawnEntity> getBrain() {
        return (Brain<AbstractRotspawnEntity>)super.getBrain();
    }

    @Override
    protected void updateAITasks() {
        this.world.getProfiler().startSection("rotspawnBrain");
        this.getBrain().tick((ServerWorld)this.world, this);
        this.world.getProfiler().endSection();
        RotspawnTasks.stuff(this);
    }

    public static boolean canRotspawnSpawn(EntityType<? extends MonsterEntity> type, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return worldIn.getDifficulty() != Difficulty.PEACEFUL && randomIn.nextInt(10) == 0 && canSpawnOn(type, worldIn, reason, pos, randomIn);
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return UndergardenEntities.ROTSPAWN;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    @Override
    public void onKillEntity(LivingEntity entityLivingIn) {
        super.onKillEntity(entityLivingIn);
        if ((this.world.getDifficulty() == Difficulty.NORMAL || this.world.getDifficulty() == Difficulty.HARD) && entityLivingIn instanceof DwellerEntity) {
            if (this.world.getDifficulty() != Difficulty.HARD && this.rand.nextBoolean()) {
                return;
            }

            DwellerEntity dweller = (DwellerEntity)entityLivingIn;
            RotDwellerEntity rotDweller = UndergardenEntities.ROTDWELLER.get().create(this.world);

            rotDweller.copyLocationAndAnglesFrom(dweller);
            dweller.remove();
            rotDweller.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(rotDweller.getPositionVec())), SpawnReason.CONVERSION, null, null);
            if (dweller.hasCustomName()) {
                rotDweller.setCustomName(dweller.getCustomName());
                rotDweller.setCustomNameVisible(dweller.isCustomNameVisible());
            }

            if (this.isNoDespawnRequired()) {
                rotDweller.enablePersistence();
            }
            rotDweller.setInvulnerable(this.isInvulnerable());
            this.world.addEntity(rotDweller);
        }
    }
}
