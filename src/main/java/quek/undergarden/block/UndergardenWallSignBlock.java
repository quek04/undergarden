package quek.undergarden.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.BlockGetter;
import quek.undergarden.block.tileentity.UndergardenSignTE;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class UndergardenWallSignBlock extends WallSignBlock {

    public UndergardenWallSignBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockGetter world) {
        return new UndergardenSignTE();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
