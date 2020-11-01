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
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGFluids;

import java.util.function.Supplier;

public class UGFluidBlock extends FlowingFluidBlock {

    public UGFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties.doesNotBlockMovement().hardnessAndResistance(100F).noDrops());
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if(this.getFluid() == UGFluids.virulent_mix_source.get() || this.getFluid() == UGFluids.virulent_mix_flowing.get()) {
            if(entityIn.isAlive() && entityIn instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entityIn;
                if(livingEntity.getCreatureAttribute() == UGEntityTypes.ROTSPAWN || livingEntity.getCreatureAttribute() == UGEntityTypes.CAVERN_CREATURE) {

                }
                else if(livingEntity.isPotionActive(UGEffects.virulent_resistance.get())) {

                }
                else livingEntity.addPotionEffect(new EffectInstance(Effects.POISON, 600, 0));
            }
        }
    }
}
