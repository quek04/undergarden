package quek.undergarden.block;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.*;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.world.IWorldReader;
import quek.undergarden.entity.MinionEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;

import javax.annotation.Nullable;

public class CarvedGloomgourdBlock extends CarvedPumpkinBlock {

    @Nullable
    private BlockPattern minionBasePattern;
    @Nullable
    private BlockPattern minionPattern;

    public CarvedGloomgourdBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    private BlockPattern getMinionBasePattern() {
        if (this.minionBasePattern == null) {
            this.minionBasePattern = BlockPatternBuilder.start().aisle(" ", "#").where('#', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(UGBlocks.FORGOTTEN_BLOCK.get()))).build();
        }

        return this.minionBasePattern;
    }

    private BlockPattern getMinionPattern() {
        if (this.minionPattern == null) {
            this.minionPattern = BlockPatternBuilder.start().aisle("^", "#").where('^', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(UGBlocks.CARVED_GLOOMGOURD.get()))).where('#', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(UGBlocks.FORGOTTEN_BLOCK.get()))).build();
        }

        return this.minionPattern;
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.isIn(state.getBlock())) {
            this.trySpawnMinion(worldIn, pos);
        }
    }

    @Override
    public boolean canDispenserPlace(IWorldReader reader, BlockPos pos) {
        return this.getMinionBasePattern().match(reader, pos) != null;
    }

    private void trySpawnMinion(World world, BlockPos pos) {
        BlockPattern.PatternHelper minionPattern = this.getMinionPattern().match(world, pos);
        if (minionPattern != null) {
            for(int i = 0; i < this.getMinionPattern().getThumbLength(); ++i) {
                CachedBlockInfo cachedblockinfo = minionPattern.translateOffset(0, i, 0);
                world.setBlockState(cachedblockinfo.getPos(), Blocks.AIR.getDefaultState(), 2);
                world.playEvent(2001, cachedblockinfo.getPos(), Block.getStateId(cachedblockinfo.getBlockState()));
            }

            MinionEntity minionEntity = UGEntityTypes.MINION.get().create(world);
            BlockPos blockpos1 = minionPattern.translateOffset(0, 2, 0).getPos();
            minionEntity.setLocationAndAngles((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 1.0D, (double)blockpos1.getZ() + 0.5D, 0.0F, 0.0F);
            world.addEntity(minionEntity);

            for(ServerPlayerEntity serverplayerentity : world.getEntitiesWithinAABB(ServerPlayerEntity.class, minionEntity.getBoundingBox().grow(5.0D))) {
                CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayerentity, minionEntity);
            }

            for(int l = 0; l < this.getMinionPattern().getThumbLength(); ++l) {
                CachedBlockInfo cachedblockinfo3 = minionPattern.translateOffset(0, l, 0);
                world.func_230547_a_(cachedblockinfo3.getPos(), Blocks.AIR);
            }
        }
    }
}