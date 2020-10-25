package quek.undergarden.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGParticleTypes;
import quek.undergarden.registry.UGTileEntities;

import java.util.Random;

public class ShardWallTorchBlock extends WallTorchBlock {

    public ShardWallTorchBlock() {
        super(Properties.create(Material.MISCELLANEOUS)
                        .doesNotBlockMovement()
                        .hardnessAndResistance(0F)
                        .setLightLevel((state) -> 6)
                        .sound(SoundType.WOOD),
                ParticleTypes.FLAME
        );
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return UGTileEntities.shard_torch_te.get().create();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        Direction direction = stateIn.get(HORIZONTAL_FACING);
        double x = (double)pos.getX() + 0.5D;
        double y = (double)pos.getY() + 0.7D;
        double z = (double)pos.getZ() + 0.5D;
        Direction oppositeDirection = direction.getOpposite();
        worldIn.addParticle(ParticleTypes.SMOKE, x + 0.27D * (double)oppositeDirection.getXOffset(), y + 0.22D, z + 0.27D * (double)oppositeDirection.getZOffset(), 0.0D, 0.0D, 0.0D);
        worldIn.addParticle(UGParticleTypes.shard.get(), x + 0.27D * (double)oppositeDirection.getXOffset(), y + 0.22D, z + 0.27D * (double)oppositeDirection.getZOffset(), 0.0D, 0.0D, 0.0D);
    }
}
