package quek.undergarden.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import quek.undergarden.block.tileentity.UndergardenSignTE;

public class UndergardenStandingSignBlock extends StandingSignBlock {

    public UndergardenStandingSignBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader world) {
        return new UndergardenSignTE();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
