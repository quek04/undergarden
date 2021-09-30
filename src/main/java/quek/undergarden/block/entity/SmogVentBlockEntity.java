package quek.undergarden.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGBlockEntities;

public class SmogVentBlockEntity extends BlockEntity {

    public SmogVentBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(UGBlockEntities.SMOG_VENT.get(), pWorldPosition, pBlockState);
    }

    public static <B extends BlockEntity>void tick(Level level, BlockPos pos, BlockState state, B blockEntity) {
        double x = (double)pos.getX() + 0.5D;
        double y = (double)pos.getY() + 1D;
        double z = (double)pos.getZ() + 0.5D;
        if(level.isEmptyBlock(pos.above())) {
            level.addParticle(ParticleTypes.LARGE_SMOKE, x, y, z, 0.0D, 0.05D, 0.0D);
        }
    }
}