package quek.undergarden.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class GooBlock extends Block {

    public GooBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext selectionContext) {
        return Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
    }

    @Override
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.makeStuckInBlock(state, new Vector3d(1.0D, 0.0D, 1.0D));
        super.entityInside(state, world, pos, entity);
    }

}