package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import quek.undergarden.registry.UGEffects;
import quek.undergarden.registry.UGSoundEvents;
import quek.undergarden.registry.UGTags;

import java.util.Optional;
import java.util.function.Supplier;

public class VirulentMixBlock extends LiquidBlock {

    public VirulentMixBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties.noCollission().strength(100F).noDrops().lightLevel((state) -> 10));
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if(entity.isAlive() && entity instanceof LivingEntity livingEntity) {
            if(livingEntity.getType().is(UGTags.Entities.IMMUNE_TO_VIRULENT_MIX)) {}
            else if(livingEntity.hasEffect(UGEffects.VIRULENT_RESISTANCE.get())) {}
            else livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 0));
        }
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(UGSoundEvents.BUCKET_FILL_VIRULENT.get());
    }
}