package quek.undergarden.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGDamageSources;
import quek.undergarden.registry.UGTags;

import java.util.Random;

public class FrozenDeepturfBlock extends UGPlantBlock {

    public FrozenDeepturfBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.is(UGBlocks.FROZEN_DEEPTURF_BLOCK.get());
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void performBonemeal(ServerWorld serverWorld, Random rand, BlockPos pos, BlockState state) { }

    @Override
    public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (!entityIn.getType().is(UGTags.Entities.ROTSPAWN) && !worldIn.isClientSide && (entityIn.xOld != entityIn.getX() || entityIn.zOld != entityIn.getZ())) {
            double d0 = Math.abs(entityIn.getX() - entityIn.xOld);
            double d1 = Math.abs(entityIn.getZ() - entityIn.zOld);
            if (d0 >= (double) 0.003F || d1 >= (double) 0.003F) {
                entityIn.hurt(UGDamageSources.FROZEN_DEEPTURF, 1.0F);
            }
        }
    }
}