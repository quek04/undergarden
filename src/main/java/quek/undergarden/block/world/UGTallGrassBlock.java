package quek.undergarden.block.world;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class UGTallGrassBlock extends UGBushBlock implements IGrowable {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public UGTallGrassBlock() {
        super(Properties.create(Material.TALL_PLANTS)
                .hardnessAndResistance(0F)
                .sound(SoundType.PLANT)
                .doesNotBlockMovement()
                .notSolid()
        );
    }

    public UGTallGrassBlock(int light) {
        super(Properties.create(Material.TALL_PLANTS)
                .hardnessAndResistance(0F)
                .sound(SoundType.PLANT)
                .doesNotBlockMovement()
                .notSolid()
                .setLightLevel((state) -> light)

        );
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld serverWorld, Random rand, BlockPos pos, BlockState state) {
        DoublePlantBlock doubleplantblock = (DoublePlantBlock) (this == UGBlocks.shimmerweed.get() ? UGBlocks.double_shimmerweed.get() : UGBlocks.double_deepturf.get());
        if (doubleplantblock.getDefaultState().isValidPosition(serverWorld, pos) && serverWorld.isAirBlock(pos.up())) {
            doubleplantblock.placeAt(serverWorld, pos, 2);
        }

    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public AbstractBlock.OffsetType getOffsetType() {
        return AbstractBlock.OffsetType.XZ;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

}
