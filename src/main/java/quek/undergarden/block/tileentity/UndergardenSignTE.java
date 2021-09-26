package quek.undergarden.block.tileentity;

import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import quek.undergarden.registry.UGTileEntities;

public class UndergardenSignTE extends SignBlockEntity {
    @Override
    public BlockEntityType<?> getType() {
        return UGTileEntities.UNDERGARDEN_SIGN.get();
    }
}