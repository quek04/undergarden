package quek.undergarden.block.tileentity;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;
import quek.undergarden.registry.UGTileEntities;

public class UndergardenSignTE extends SignTileEntity {
    @Override
    public TileEntityType<?> getType() {
        return UGTileEntities.UNDERGARDEN_SIGN.get();
    }
}