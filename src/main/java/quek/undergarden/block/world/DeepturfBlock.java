package quek.undergarden.block.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

import java.util.List;
import java.util.Random;

public class DeepturfBlock extends UndergardenGrassBlock implements IGrowable {

    public DeepturfBlock() {
        super(Properties.create(Material.ORGANIC)
                .hardnessAndResistance(0.6F)
                .sound(SoundType.PLANT)
                .harvestLevel(0)
                .harvestTool(ToolType.SHOVEL)
                .tickRandomly()
        );
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void grow(ServerWorld serverWorld, Random random, BlockPos blockPos, BlockState blockState) {

    }
}
