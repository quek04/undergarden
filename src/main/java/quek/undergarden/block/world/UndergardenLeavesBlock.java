package quek.undergarden.block.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UndergardenBlocks;

import java.util.Random;

public class UndergardenLeavesBlock extends LeavesBlock {

    public static final IntegerProperty DISTANCE = BlockStateProperties.DISTANCE_1_7;

    public UndergardenLeavesBlock() {
        super(Properties.create(Material.LEAVES)
                .hardnessAndResistance(0.2F)
                .sound(SoundType.PLANT)
                .notSolid()
        );
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn == UndergardenBlocks.smogstem_leaves.get().getDefaultState()) {
            BlockPos blockpos = pos.down();
            BlockState blockstate = worldIn.getBlockState(blockpos);
            double d0 = (double) ((float) pos.getX() + rand.nextFloat());
            double d1 = (double) pos.getY() - 0.05D;
            double d2 = (double) ((float) pos.getZ() + rand.nextFloat());
            worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        worldIn.setBlockState(pos, updateDistance(state, worldIn, pos), 3);
    }

    private static BlockState updateDistance(BlockState state, IWorld worldIn, BlockPos pos) {
        int i = 7;

        try (BlockPos.PooledMutable blockpos$pooledmutable = BlockPos.PooledMutable.retain()) {
            for(Direction direction : Direction.values()) {
                blockpos$pooledmutable.setPos(pos).move(direction);
                i = Math.min(i, getDistance(worldIn.getBlockState(blockpos$pooledmutable)) + 1);
                if (i == 1) {
                    break;
                }
            }
        }

        return state.with(DISTANCE, Integer.valueOf(i));
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        int i = getDistance(facingState) + 1;
        if (i != 1 || stateIn.get(DISTANCE) != i) {
            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
        }

        return stateIn;
    }


    private static int getDistance(BlockState neighbor) {
        if (UndergardenBlocks.smogstem_log.get().getDefaultState() == neighbor.getBlockState()) {
            return 0;
        } else if (UndergardenBlocks.wigglewood_log.get().getDefaultState() == neighbor.getBlockState()) {
            return 0;
        } else {
            return neighbor.getBlock() instanceof UndergardenLeavesBlock ? neighbor.get(DISTANCE) : 7;
        }
    }

    @Override
    public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType < ?>type){
        return false;
    }
}

