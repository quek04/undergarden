package quek.undergarden.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import quek.undergarden.registry.UGEffects;
import quek.undergarden.registry.UGFluids;
import quek.undergarden.registry.UGTags;

import java.util.function.Supplier;

import net.minecraft.block.AbstractBlock.Properties;

public class UGFluidBlock extends FlowingFluidBlock {

    public UGFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties.noCollission().strength(100F).noDrops());
    }

    @Override
    public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if(this.getFluid() == UGFluids.VIRULENT_MIX_SOURCE.get() || this.getFluid() == UGFluids.VIRULENT_MIX_FLOWING.get()) {
            if(entityIn.isAlive() && entityIn instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entityIn;
                if(livingEntity.getType().is(UGTags.Entities.ROTSPAWN) || livingEntity.getType().is(UGTags.Entities.CAVERN_CREATURE)) {

                }
                else if(livingEntity.hasEffect(UGEffects.VIRULENT_RESISTANCE.get())) {

                }
                else livingEntity.addEffect(new EffectInstance(Effects.POISON, 600, 0));
            }
        }
    }
}