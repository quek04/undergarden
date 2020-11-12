package quek.undergarden.entity.boss;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGSounds;

import javax.annotation.Nullable;

public class ForgottenGuardianEntity extends MonsterEntity {

    private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
    private int attackTimer;

    public ForgottenGuardianEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.stepHeight = 1.0F;
        this.experienceValue = 30;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.5D, false));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 100.0D)
                .createMutableAttribute(Attributes.ARMOR, 20.0D)
                .createMutableAttribute(Attributes.ARMOR_TOUGHNESS, 5.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 20.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 64.0D);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(UGSounds.STONEBORN_STEP, 1.0F, 0.5F);
    }

    @Override
    public boolean isNonBoss() {
        return false;
    }

    @Override
    public void checkDespawn() {
        if (this.world.getDifficulty() == Difficulty.PEACEFUL && this.isDespawnPeaceful()) {
            this.remove();
        } else {
            this.idleTime = 0;
        }
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if (this.attackTimer > 0) {
            --this.attackTimer;
        }

        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        this.attackTimer = 10;
        this.world.setEntityState(this, (byte)4);
        float damage = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float f1 = (int)damage > 0 ? damage / 2.0F + (float)this.rand.nextInt((int)damage) : damage;
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f1);
        if (flag) {
            double x = entityIn.getPosX() - this.getPosX();
            double z = entityIn.getPosZ() - this.getPosZ();
            double modifier = Math.max(x * x + z * z, 0.001D);
            entityIn.setMotion(entityIn.getMotion().add((x / modifier) * 2, 0.2F, (z / modifier) * 2));
            this.applyEnchantments(this, entityIn);
        }

        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 0.5F);
        return flag;
    }

    @Override
    protected void constructKnockBackVector(LivingEntity entityIn) {
        double x = entityIn.getPosX() - this.getPosX();
        double z = entityIn.getPosZ() - this.getPosZ();
        double modifier = Math.max(x * x + z * z, 0.001D);
        entityIn.addVelocity((x / modifier) * 2, 0.2F, (z / modifier) * 2);
        entityIn.velocityChanged = true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        Entity entity = source.getImmediateSource();
        if (entity instanceof ProjectileEntity) {
            return false;
        }
        else return super.attackEntityFrom(source, amount);
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 4) {
            this.attackTimer = 10;
            this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 0.5F);
        }
        else {
            super.handleStatusUpdate(id);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    @Override
    public void setCustomName(@Nullable ITextComponent name) {
        super.setCustomName(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) { }

    @Override
    public boolean addPotionEffect(EffectInstance effectInstanceIn) {
        return false;
    }
}
