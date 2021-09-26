package quek.undergarden.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGEffects;
import quek.undergarden.registry.UGFluids;
import quek.undergarden.registry.UGTags;

import java.util.function.Supplier;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class UGFluidBlock extends LiquidBlock {

    public UGFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties.noCollission().strength(100F).noDrops());
    }

    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        if(this.getFluid() == UGFluids.VIRULENT_MIX_SOURCE.get() || this.getFluid() == UGFluids.VIRULENT_MIX_FLOWING.get()) {
            if(entityIn.isAlive() && entityIn instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entityIn;
                if(livingEntity.getType().is(UGTags.Entities.ROTSPAWN) || livingEntity.getType().is(UGTags.Entities.CAVERN_CREATURE)) {

                }
                else if(livingEntity.hasEffect(UGEffects.VIRULENT_RESISTANCE.get())) {

                }
                else livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 0));
            }
        }
    }
}