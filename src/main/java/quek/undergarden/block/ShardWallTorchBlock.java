package quek.undergarden.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGParticleTypes;
import quek.undergarden.registry.UGTileEntities;

import java.util.Random;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class ShardWallTorchBlock extends WallTorchBlock {

    public ShardWallTorchBlock(Properties properties) {
        super(properties, ParticleTypes.FLAME);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
        return UGTileEntities.SHARD_TORCH.get().create();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        Direction direction = stateIn.getValue(FACING);
        double x = (double)pos.getX() + 0.5D;
        double y = (double)pos.getY() + 0.7D;
        double z = (double)pos.getZ() + 0.5D;
        Direction oppositeDirection = direction.getOpposite();
        worldIn.addParticle(ParticleTypes.SMOKE, x + 0.27D * (double)oppositeDirection.getStepX(), y + 0.22D, z + 0.27D * (double)oppositeDirection.getStepZ(), 0.0D, 0.0D, 0.0D);
        worldIn.addParticle(UGParticleTypes.SHARD.get(), x + 0.27D * (double)oppositeDirection.getStepX(), y + 0.22D, z + 0.27D * (double)oppositeDirection.getStepZ(), 0.0D, 0.0D, 0.0D);
    }
}