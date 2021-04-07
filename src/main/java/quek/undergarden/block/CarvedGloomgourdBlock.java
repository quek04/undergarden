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
    public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.is(state.getBlock())) {
            this.trySpawnMinion(worldIn, pos);
        }
    }

    @Override
    public boolean canSpawnGolem(IWorldReader reader, BlockPos pos) {
        return this.getMinionBasePattern().find(reader, pos) != null;
    }

    private void trySpawnMinion(World world, BlockPos pos) {
        BlockPattern.PatternHelper minionPattern = this.getMinionPattern().find(world, pos);
        if (minionPattern != null) {
            for(int i = 0; i < this.getMinionPattern().getHeight(); ++i) {
                CachedBlockInfo cachedblockinfo = minionPattern.getBlock(0, i, 0);
                world.setBlock(cachedblockinfo.getPos(), Blocks.AIR.defaultBlockState(), 2);
                world.levelEvent(2001, cachedblockinfo.getPos(), Block.getId(cachedblockinfo.getState()));
            }

            MinionEntity minionEntity = UGEntityTypes.MINION.get().create(world);
            BlockPos blockpos1 = minionPattern.getBlock(0, 2, 0).getPos();
            minionEntity.moveTo((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 1.0D, (double)blockpos1.getZ() + 0.5D, 0.0F, 0.0F);
            world.addFreshEntity(minionEntity);

            for(ServerPlayerEntity serverplayerentity : world.getEntitiesOfClass(ServerPlayerEntity.class, minionEntity.getBoundingBox().inflate(5.0D))) {
                CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayerentity, minionEntity);
            }

            for(int l = 0; l < this.getMinionPattern().getHeight(); ++l) {
                CachedBlockInfo cachedblockinfo3 = minionPattern.getBlock(0, l, 0);
                world.blockUpdated(cachedblockinfo3.getPos(), Blocks.AIR);
            }
        }
    }
}