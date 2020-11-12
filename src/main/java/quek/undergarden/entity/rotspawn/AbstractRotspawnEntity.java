package quek.undergarden.entity.rotspawn;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.entity.DwellerEntity;
import quek.undergarden.entity.stoneborn.StonebornEntity;
import quek.undergarden.registry.UGEntityTypes;

import java.util.Random;

public abstract class AbstractRotspawnEntity extends MonsterEntity {

    protected AbstractRotspawnEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, StonebornEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, DwellerEntity.class, true));
    }

    public static boolean canRotspawnSpawn(EntityType<? extends MonsterEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return randomIn.nextInt(10) == 0 && canMonsterSpawnInLight(type, worldIn, reason, pos, randomIn);
    }

    @Override
    public void func_241847_a(ServerWorld world, LivingEntity entityLivingIn) { // on kill
        super.func_241847_a(world, entityLivingIn);
        if ((this.world.getDifficulty() == Difficulty.NORMAL || this.world.getDifficulty() == Difficulty.HARD) && entityLivingIn instanceof DwellerEntity) {
            if (this.world.getDifficulty() != Difficulty.HARD && this.rand.nextBoolean()) {
                return;
            }

            DwellerEntity dweller = (DwellerEntity)entityLivingIn;
            RotDwellerEntity rotDweller = UGEntityTypes.ROTDWELLER.get().create(this.world);

            rotDweller.copyLocationAndAnglesFrom(dweller);
            dweller.remove();
            rotDweller.onInitialSpawn(world, this.world.getDifficultyForLocation(new BlockPos(rotDweller.getPositionVec())), SpawnReason.CONVERSION, null, null);
            if (dweller.hasCustomName()) {
                rotDweller.setCustomName(dweller.getCustomName());
                rotDweller.setCustomNameVisible(dweller.isCustomNameVisible());
            }
            if (this.isNoDespawnRequired()) {
                rotDweller.enablePersistence();
            }
            if(dweller.isChild()) {
                rotDweller.setChild(true);
            }
            rotDweller.setInvulnerable(this.isInvulnerable());
            this.world.addEntity(rotDweller);
        }
    }
}
