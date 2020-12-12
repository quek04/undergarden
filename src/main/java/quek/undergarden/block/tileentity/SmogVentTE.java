package quek.undergarden.block.tileentity;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import quek.undergarden.registry.UGTileEntities;

public class SmogVentTE extends TileEntity implements ITickableTileEntity {

    public SmogVentTE() {
        super(UGTileEntities.SMOG_VENT.get());
    }

    @Override
    public void tick() {
        double x = (double)pos.getX() + 0.5D;
        double y = (double)pos.getY() + 1D;
        double z = (double)pos.getZ() + 0.5D;
        if(world.isAirBlock(pos.up())) {
            world.addParticle(ParticleTypes.LARGE_SMOKE, x, y, z, 0.0D, 0.05D, 0.0D);
        }
    }
}
