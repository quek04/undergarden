package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import quek.undergarden.registry.UGDamageSources;
import quek.undergarden.registry.UGTags;

import java.util.Random;

public class CarvedGloomgourdShardBlock extends CarvedGloomgourdBlock {

    public CarvedGloomgourdShardBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        pLevel.getBlockTicks().scheduleTick(pPos, this, 20);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        //if(pLevel.getGameTime() % 20 == 0) {
        pLevel.getEntitiesOfClass(LivingEntity.class, new AABB(
                        pPos.getX() - 4,
                        pPos.getY() - 4,
                        pPos.getZ() - 4,
                        pPos.getX() + 4,
                        pPos.getY() + 4,
                        pPos.getZ() + 4),
                entity -> entity.getType().is(UGTags.Entities.ROTSPAWN)).forEach(entity -> entity.hurt(UGDamageSources.SHARD_TORCH, 4));
        //}
        pLevel.getBlockTicks().scheduleTick(pPos, this, 20);
    }
}