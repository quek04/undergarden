package quek.undergarden.block.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import quek.undergarden.fluid.VirulentMixFluid;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenFluids;

import java.util.function.Supplier;

public class UndergardenFluidBlock extends FlowingFluidBlock {

    public UndergardenFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties.doesNotBlockMovement().hardnessAndResistance(100F).noDrops());
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if(this.getFluid() == UndergardenFluids.virulent_mix_source.get() || this.getFluid() == UndergardenFluids.virulent_mix_flowing.get()) {
            if(entityIn.isLiving()) {
                LivingEntity livingEntity = (LivingEntity) entityIn;
                livingEntity.addPotionEffect(new EffectInstance(Effects.POISON, 600, 0));
            }

        }

    }
}
