package quek.undergarden.block.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class LightbearingPlantBlock extends UndergardenBushBlock {
    public LightbearingPlantBlock(int light) {
        super(Properties.create(Material.TALL_PLANTS)
                .hardnessAndResistance(0f)
                .sound(SoundType.PLANT)
                .doesNotBlockMovement()
                .notSolid()
                .lightValue(light)
        );
    }

    @Override
    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XZ;
    }

    @Override
    public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1;
    }

}
