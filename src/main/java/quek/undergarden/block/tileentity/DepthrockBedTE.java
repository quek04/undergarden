package quek.undergarden.block.tileentity;

import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import quek.undergarden.registry.UGTileEntities;

public class DepthrockBedTE extends BlockEntity {

    public DepthrockBedTE() {
        super(UGTileEntities.DEPTHROCK_BED.get());
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return new ClientboundBlockEntityDataPacket(this.worldPosition, 11, this.getUpdateTag());
    }
}
