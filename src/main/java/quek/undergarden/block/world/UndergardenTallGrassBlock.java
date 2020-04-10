package quek.undergarden.block.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class UndergardenTallGrassBlock extends UndergardenBushBlock {
    public UndergardenTallGrassBlock() {
        super(Properties.create(Material.TALL_PLANTS)
                .hardnessAndResistance(0f)
                .sound(SoundType.PLANT)
                .doesNotBlockMovement()
                .notSolid()
        );
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    @Override
    public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1;
    }

}
