package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEffects;
import quek.undergarden.registry.UGFluids;
import quek.undergarden.registry.UGTags;

import java.util.function.Supplier;

public class UGFluidBlock extends LiquidBlock {

    public UGFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties.noCollission().strength(100F).noDrops());
        super.fluid = supplier.get();
    }

    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        if(this.getFluid() == UGFluids.VIRULENT_MIX_SOURCE.get() || this.getFluid() == UGFluids.VIRULENT_MIX_FLOWING.get()) {
            if(entityIn.isAlive() && entityIn instanceof LivingEntity livingEntity) {
//                if(livingEntity.getType().is(UGTags.Entities.ROTSPAWN) || livingEntity.getType().is(UGTags.Entities.CAVERN_CREATURE)) {
//
//                }
//                else if(livingEntity.hasEffect(UGEffects.VIRULENT_RESISTANCE.get())) {
//
//                }
//                else livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 0));
                if(!livingEntity.getType().is(UGTags.Entities.ROTSPAWN) || !livingEntity.getType().is(UGTags.Entities.CAVERN_CREATURE) || !livingEntity.hasEffect(UGEffects.VIRULENT_RESISTANCE.get())) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 0));
                }
            }
        }
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (this.shouldSpreadLiquid(pLevel, pPos, pState)) {
            pLevel.getLiquidTicks().scheduleTick(pPos, pState.getFluidState().getType(), this.fluid.getTickDelay(pLevel));
        }

    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (this.shouldSpreadLiquid(pLevel, pPos, pState)) {
            pLevel.getLiquidTicks().scheduleTick(pPos, pState.getFluidState().getType(), this.fluid.getTickDelay(pLevel));
        }

    }

    private boolean shouldSpreadLiquid(Level pLevel, BlockPos pPos, BlockState pState) {
        if (this.fluid.is(FluidTags.LAVA)) {
            boolean flag = pLevel.getBlockState(pPos.below()).is(Blocks.SOUL_SOIL);

            for(Direction direction : POSSIBLE_FLOW_DIRECTIONS) {
                BlockPos blockpos = pPos.relative(direction.getOpposite());
                if (pLevel.getFluidState(blockpos).is(FluidTags.WATER)) {
                    Block block = pLevel.getFluidState(pPos).isSource() ? Blocks.OBSIDIAN : UGBlocks.DEPTHROCK.get();
                    pLevel.setBlockAndUpdate(pPos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(pLevel, pPos, pPos, block.defaultBlockState()));
                    this.fizz(pLevel, pPos);
                    return false;
                }

                if (flag && pLevel.getBlockState(blockpos).is(Blocks.BLUE_ICE)) {
                    pLevel.setBlockAndUpdate(pPos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(pLevel, pPos, pPos, Blocks.BASALT.defaultBlockState()));
                    this.fizz(pLevel, pPos);
                    return false;
                }
            }
        }
        return true;
    }

    private void fizz(LevelAccessor pLevel, BlockPos pPos) {
        pLevel.levelEvent(1501, pPos, 0);
    }
}