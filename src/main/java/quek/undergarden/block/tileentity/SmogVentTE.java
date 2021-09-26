package quek.undergarden.block.tileentity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.entity.TickableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import quek.undergarden.registry.UGTileEntities;

public class SmogVentTE extends BlockEntity implements TickableBlockEntity {

    public SmogVentTE() {
        super(UGTileEntities.SMOG_VENT.get());
    }

    @Override
    public void tick() {
        double x = (double)worldPosition.getX() + 0.5D;
        double y = (double)worldPosition.getY() + 1D;
        double z = (double)worldPosition.getZ() + 0.5D;
        if(level.isEmptyBlock(worldPosition.above())) {
            level.addParticle(ParticleTypes.LARGE_SMOKE, x, y, z, 0.0D, 0.05D, 0.0D);
        }
    }
}