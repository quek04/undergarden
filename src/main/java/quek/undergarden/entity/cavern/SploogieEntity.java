package quek.undergarden.entity.cavern;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import quek.undergarden.entity.projectile.SlingshotAmmoEntity;
import quek.undergarden.registry.UGSoundEvents;

public class SploogieEntity extends AbstractCavernCreatureEntity implements IRangedAttackMob {

    public SploogieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.0D, 40, 15.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return AbstractCavernCreatureEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return UGSoundEvents.SPLOOGIE_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UGSoundEvents.SPLOOGIE_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UGSoundEvents.SPLOOGIE_DEATH.get();
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        SlingshotAmmoEntity pebble = new SlingshotAmmoEntity(this.level, this);
        double xDistance = target.getX() - this.getX();
        double yDistance = target.getY(0.3333333333333333D) - pebble.getY();
        double zDistance = target.getZ() - this.getZ();
        double yMath = MathHelper.sqrt((xDistance * xDistance) + (zDistance * zDistance));
        pebble.shoot(xDistance, yDistance + yMath * 0.1D, zDistance, 1.6F, 1.0F);
        this.playSound(UGSoundEvents.SPLOOGIE_SPIT.get(), 1.0F, this.getVoicePitch());
        this.level.addFreshEntity(pebble);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.3F;
    }
}