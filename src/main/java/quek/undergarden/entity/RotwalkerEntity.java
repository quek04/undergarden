package quek.undergarden.entity;

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
import quek.undergarden.registry.UndergardenSoundEvents;

public class RotwalkerEntity extends AbstractRotspawnEntity {

    public RotwalkerEntity(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_233666_p_()
                .func_233815_a_(Attributes.MAX_HEALTH, 40.0D) //hp
                .func_233815_a_(Attributes.ARMOR, 3.0D) //armor
                .func_233815_a_(Attributes.ATTACK_DAMAGE, 5.0D) //attack damage
                .func_233815_a_(Attributes.MOVEMENT_SPEED, 0.23D); //speed
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return UndergardenSoundEvents.ROTWALKER_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UndergardenSoundEvents.ROTWALKER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UndergardenSoundEvents.ROTWALKER_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15F, 0.5F);
    }

}
