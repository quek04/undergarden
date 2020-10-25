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
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class DeepturfBlock extends UGGrassBlock implements IGrowable {

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
        return worldIn.getBlockState(pos.up()).isAir();
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.up();

        label48:
        for(int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;

            for(int j = 0; j < i / 16; ++j) {
                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (!worldIn.getBlockState(blockpos1.down()).isIn(this) || worldIn.getBlockState(blockpos1).hasOpaqueCollisionShape(worldIn, blockpos1)) {
                    continue label48;
                }
            }

            BlockState blockstate2 = worldIn.getBlockState(blockpos1);
            if (blockstate2.isIn(deepturfOrShimmerweed(rand).getBlock()) && rand.nextInt(10) == 0) {
                ((IGrowable)deepturfOrShimmerweed(rand).getBlock()).grow(worldIn, rand, blockpos1, blockstate2);
            }

            if (blockstate2.isAir()) {
                if (deepturfOrShimmerweed(rand).isValidPosition(worldIn, blockpos1)) {
                    worldIn.setBlockState(blockpos1, deepturfOrShimmerweed(rand), 3);
                }
            }
        }
    }

    private static BlockState deepturfOrShimmerweed(Random random) {
        if(random.nextInt(10) == 0) {
            return UGBlocks.shimmerweed.get().getDefaultState();
        }
        else return UGBlocks.tall_deepturf.get().getDefaultState();
    }
}
