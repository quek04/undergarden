package quek.undergarden.entity.stoneborn.goals;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import quek.undergarden.block.EffigyBlock;
import quek.undergarden.data.provider.UGBlockLootTableProvider;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nullable;
import java.util.Random;

public class StonebornStealFromEffigyGoal extends MoveToBlockGoal {
    private final Block block;
    private final MobEntity entity;

    public StonebornStealFromEffigyGoal(Block blockIn, CreatureEntity creature, double speed, int yMax) {
        super(creature, speed, 24, yMax);
        this.block = blockIn;
        this.entity = creature;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean shouldExecute() {
        if (this.creature.world.getBlockState(this.destinationBlock).getBlock() == UGBlocks.STONEBORN_EFFIGY.get()) {
            return this.creature.world.getBlockState(this.destinationBlock).get(EffigyBlock.CHARGES) > 0;
        }
        return false;
    }

    private boolean func_220729_m() {
        return this.destinationBlock != null && this.shouldMoveTo(this.creature.world, this.destinationBlock) ? true : this.searchForDestination();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        super.resetTask();
        this.entity.fallDistance = 1.0F;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        super.startExecuting();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        super.tick();
        World world = this.entity.world;
        BlockPos blockpos = this.entity.getPosition();
        BlockPos blockpos1 = this.findTarget(blockpos, world);
        if (this.getIsAboveDestination() && blockpos1 != null) {
            if (world.getBlockState(blockpos1).get(EffigyBlock.CHARGES) > 0) {
                world.setBlockState(blockpos1, UGBlocks.STONEBORN_EFFIGY.get().getDefaultState().with(EffigyBlock.CHARGES, 0));
                world.playSound(null, this.creature.getPosX(), this.creature.getPosY(), this.creature.getPosZ(), UGSoundEvents.STONEBORN_CHUCKLE.get(), SoundCategory.NEUTRAL, 1.0F, 1.0F);
            }
        }

    }

    @Nullable
    private BlockPos findTarget(BlockPos pos, IBlockReader worldIn) {
        if (worldIn.getBlockState(pos).isIn(this.block)) {
            return pos;
        } else {
            BlockPos[] ablockpos = new BlockPos[]{pos.down(), pos.west(), pos.east(), pos.north(), pos.south(), pos.down().down()};

            for(BlockPos blockpos : ablockpos) {
                if (worldIn.getBlockState(blockpos).isIn(this.block)) {
                    return blockpos;
                }
            }

            return null;
        }
    }

    /**
     * Return true to set given position as destination
     */
    protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
        IChunk ichunk = worldIn.getChunk(pos.getX() >> 4, pos.getZ() >> 4, ChunkStatus.FULL, false);
        if (ichunk == null) {
            return false;
        } else {
            return ichunk.getBlockState(pos).isIn(this.block) && ichunk.getBlockState(pos.up()).isAir() && ichunk.getBlockState(pos.up(2)).isAir();
        }
    }
}
