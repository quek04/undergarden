package quek.undergarden.block;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import quek.undergarden.entity.Minion;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;

import javax.annotation.Nullable;

public class CarvedGloomgourdBlock extends CarvedPumpkinBlock {

    @Nullable
    private BlockPattern minionBasePattern;
    @Nullable
    private BlockPattern minionPattern;

    public CarvedGloomgourdBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    private BlockPattern getMinionBasePattern() {
        if (this.minionBasePattern == null) {
            this.minionBasePattern = BlockPatternBuilder.start().aisle(" ", "#").where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(UGBlocks.FORGOTTEN_BLOCK.get()))).build();
        }

        return this.minionBasePattern;
    }

    private BlockPattern getMinionPattern() {
        if (this.minionPattern == null) {
            this.minionPattern = BlockPatternBuilder.start().aisle("^", "#").where('^', BlockInWorld.hasState(BlockStatePredicate.forBlock(UGBlocks.CARVED_GLOOMGOURD.get()))).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(UGBlocks.FORGOTTEN_BLOCK.get()))).build();
        }

        return this.minionPattern;
    }

    @Override
    public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.is(state.getBlock())) {
            this.trySpawnMinion(worldIn, pos);
        }
    }

    @Override
    public boolean canSpawnGolem(LevelReader reader, BlockPos pos) {
        return this.getMinionBasePattern().find(reader, pos) != null;
    }

    private void trySpawnMinion(Level world, BlockPos pos) {
        BlockPattern.BlockPatternMatch minionPattern = this.getMinionPattern().find(world, pos);
        if (minionPattern != null) {
            for(int i = 0; i < this.getMinionPattern().getHeight(); ++i) {
                BlockInWorld cachedblockinfo = minionPattern.getBlock(0, i, 0);
                world.setBlock(cachedblockinfo.getPos(), Blocks.AIR.defaultBlockState(), 2);
                world.levelEvent(2001, cachedblockinfo.getPos(), Block.getId(cachedblockinfo.getState()));
            }

            Minion minionEntity = UGEntityTypes.MINION.get().create(world);
            BlockPos blockpos1 = minionPattern.getBlock(0, 2, 0).getPos();
            minionEntity.moveTo((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 1.0D, (double)blockpos1.getZ() + 0.5D, 0.0F, 0.0F);
            world.addFreshEntity(minionEntity);

            for(ServerPlayer serverplayerentity : world.getEntitiesOfClass(ServerPlayer.class, minionEntity.getBoundingBox().inflate(5.0D))) {
                CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayerentity, minionEntity);
            }

            for(int l = 0; l < this.getMinionPattern().getHeight(); ++l) {
                BlockInWorld cachedblockinfo3 = minionPattern.getBlock(0, l, 0);
                world.blockUpdated(cachedblockinfo3.getPos(), Blocks.AIR);
            }
        }
    }
}