package quek.undergarden.block.world;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorldReader;
import quek.undergarden.registry.UndergardenBlocks;

import java.util.Random;

public class DroopvineTopBlock extends AbstractTopPlantBlock {

    public static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 1.0D, 1.0D, 16.0D, 16.0D, 16.0D);

    public DroopvineTopBlock() {
        super(Properties.create(Material.PLANTS)
                .doesNotBlockMovement()
                .tickRandomly()
                .setLightLevel((state) -> 10)
                .hardnessAndResistance(0.2F)
                .sound(SoundType.WET_GRASS),
                Direction.DOWN,
                SHAPE,
                false,
                0.1D
        );
    }

    @Override
    protected int func_230332_a_(Random rand) {
        return PlantBlockHelper.func_235515_a_(rand);
    }

    @Override
    protected boolean func_230334_h_(BlockState state) {
        return PlantBlockHelper.func_235514_a_(state);
    }

    @Override
    protected Block func_230330_d_() {
        return UndergardenBlocks.droopvine.get().getDefaultState().with(DroopvineBlock.GLOWY, DroopvineBlock.randomTorF()).getBlock();
    }

    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }
}
