package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import quek.undergarden.entity.Boomgourd;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nullable;

public class BoomgourdBlock extends TntBlock {

    public BoomgourdBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction direction, @Nullable LivingEntity entity) {
        if (!level.isClientSide) {
            Boomgourd boomgourd = new Boomgourd(level, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, entity);
            level.addFreshEntity(boomgourd);
            level.playSound(null, boomgourd.getX(), boomgourd.getY(), boomgourd.getZ(), UGSoundEvents.BOOMGOURD_PRIMED.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(entity, GameEvent.PRIME_FUSE, pos);
        }
    }

    @Override
    public void wasExploded(Level level, BlockPos pos, Explosion explosion) {
        if (!level.isClientSide) {
            Boomgourd boomgourd = new Boomgourd(level, (double)pos.getX() + 0.5D, pos.getY(), (double)pos.getZ() + 0.5D, explosion.getSourceMob());
            int fuse = boomgourd.getFuse();
            boomgourd.setFuse((short)(level.random.nextInt(fuse / 4) + fuse / 8));
            level.addFreshEntity(boomgourd);
        }
    }
}