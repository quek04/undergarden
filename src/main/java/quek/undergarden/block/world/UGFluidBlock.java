package quek.undergarden.block.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import quek.undergarden.registry.UGBlocks;
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
                if(livingEntity.getCreatureAttribute() != UGEntityTypes.ROTSPAWN) {
                    livingEntity.addPotionEffect(new EffectInstance(Effects.POISON, 600, 0));
                }
            }
        }
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (this.reactWithNeighbors(worldIn, pos, state)) {
            worldIn.getPendingFluidTicks().scheduleTick(pos, state.getFluidState().getFluid(), this.getFluid().getTickRate(worldIn));
        }
    }

    private boolean reactWithNeighbors(World worldIn, BlockPos pos, BlockState state) {
        if (super.getFluid().isIn(FluidTags.LAVA)) {
            boolean flag = worldIn.getBlockState(pos.down()).isIn(Blocks.SOUL_SOIL);

            for(Direction direction : Direction.values()) {
                if (direction != Direction.DOWN) {
                    BlockPos blockpos = pos.offset(direction);
                    if (worldIn.getFluidState(blockpos).isTagged(FluidTags.WATER)) {
                        Block block = worldIn.getFluidState(pos).isSource() ? Blocks.OBSIDIAN : UGBlocks.depthrock.get();
                        worldIn.setBlockState(pos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, block.getDefaultState()));
                        this.triggerMixEffects(worldIn, pos);
                        return false;
                    }

                    if (flag && worldIn.getBlockState(blockpos).isIn(Blocks.BLUE_ICE)) {
                        worldIn.setBlockState(pos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, Blocks.BASALT.getDefaultState()));
                        this.triggerMixEffects(worldIn, pos);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void triggerMixEffects(IWorld worldIn, BlockPos pos) {
        worldIn.playEvent(1501, pos, 0);
    }
}
