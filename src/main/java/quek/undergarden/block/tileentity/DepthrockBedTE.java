package quek.undergarden.block.tileentity;

import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import quek.undergarden.registry.UGTileEntities;

public class DepthrockBedTE extends TileEntity {

    public DepthrockBedTE() {
        super(UGTileEntities.DEPTHROCK_BED.get());
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 11, this.getUpdateTag());
    }
}
