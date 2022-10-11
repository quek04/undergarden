package quek.undergarden.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import quek.undergarden.registry.UGEffects;

public class VirulenceEffect extends MobEffect {
    public VirulenceEffect() {
        super(MobEffectCategory.HARMFUL, 3550530);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.getHealth() > 2.0F && !entity.hasEffect(UGEffects.VIRULENT_RESISTANCE.get())) {
            entity.hurt(DamageSource.MAGIC, 2.0F);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        int j = 50 >> amplifier;
        if (j > 0) {
            return duration % j == 0;
        } else {
            return true;
        }
    }
}