package quek.undergarden.entity.rotspawn;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import quek.undergarden.entity.DwellerEntity;
import quek.undergarden.registry.UndergardenEntities;

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
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, DwellerEntity.class, true));
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
