package quek.undergarden.block.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class DepthrockPebblesBlock extends Block {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);

    public DepthrockPebblesBlock() {
        super(Properties.create(Material.MISCELLANEOUS)
                .hardnessAndResistance(0F)
                .sound(SoundType.STONE)
                .harvestTool(ToolType.PICKAXE)
                .doesNotBlockMovement()
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XZ;
    }
}
