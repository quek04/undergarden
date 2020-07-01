package quek.undergarden.block.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraftforge.common.ToolType;

public class UndergardenLogBlock extends RotatedPillarBlock {
    public UndergardenLogBlock() {
        super(Properties.create(Material.WOOD)
                .hardnessAndResistance(2F)
                .sound(SoundType.WOOD)
                .harvestTool(ToolType.AXE)
        );
    }

    @Override
    public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 300;
    }
}
