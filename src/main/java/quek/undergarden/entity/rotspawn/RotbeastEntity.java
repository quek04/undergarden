package quek.undergarden.entity.rotspawn;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UndergardenSoundEvents;

public class RotbeastEntity extends AbstractRotspawnEntity {

    private int attackTimer;

    public RotbeastEntity(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_233666_p_()
                .func_233815_a_(Attributes.MAX_HEALTH, 80.0D) //hp
                .func_233815_a_(Attributes.ARMOR, 3.0D) //armor
                .func_233815_a_(Attributes.ATTACK_DAMAGE, 10.0D) //attack damage
                .func_233815_a_(Attributes.MOVEMENT_SPEED, 0.22D) //speed
                .func_233815_a_(Attributes.KNOCKBACK_RESISTANCE, 0.5D); //knockback resist
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        this.attackTimer = 10;
        this.world.setEntityState(this, (byte)4);
        float f = this.getAttackDamage();
        float f1 = f > 0.0F ? f / 2.0F + (float)this.rand.nextInt((int)f) : 0.0F;
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f1);
        if (flag) {
            entityIn.setMotion(entityIn.getMotion().add(0.0D, (double)0.4F, 0.0D));
            this.applyEnchantments(this, entityIn);
        }

        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 0.5F);
        return flag;
    }

    private float getAttackDamage() {
        return (float)this.getAttribute(Attributes.ATTACK_DAMAGE).getValue();
    }

    @OnlyIn(Dist.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return UndergardenSoundEvents.ROTBEAST_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UndergardenSoundEvents.ROTBEAST_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UndergardenSoundEvents.ROTBEAST_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15F, 0.5F);
    }

}
