package quek.undergarden.entity.rotspawn;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import quek.undergarden.entity.MogEntity;
import quek.undergarden.entity.stoneborn.StonebornEntity;

import java.util.Random;

public abstract class AbstractRotspawnEntity extends MonsterEntity {

    protected AbstractRotspawnEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, StonebornEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AnimalEntity.class, 10, true, false, (target) -> !(target instanceof MogEntity)));
    }

    public static boolean canRotspawnSpawn(EntityType<? extends MonsterEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return randomIn.nextInt(10) == 0 && checkMonsterSpawnRules(type, worldIn, reason, pos, randomIn);
    }
}