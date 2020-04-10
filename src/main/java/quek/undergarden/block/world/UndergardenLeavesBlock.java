package quek.undergarden.block.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.registry.UndergardenBlocks;

import java.util.Random;

public class UndergardenLeavesBlock extends LeavesBlock {

    public static final IntegerProperty DISTANCE = BlockStateProperties.DISTANCE_1_7;
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;

    public UndergardenLeavesBlock() {
        super(Properties.create(Material.LEAVES)
                .hardnessAndResistance(2F)
                .sound(SoundType.PLANT)
                .notSolid()
        );
        this.setDefaultState(this.stateContainer.getBaseState().with(DISTANCE, Integer.valueOf(7)).with(PERSISTENT, Boolean.valueOf(false)));
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return state.get(DISTANCE) == 7 && !state.get(PERSISTENT);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!state.get(PERSISTENT) && state.get(DISTANCE) == 7) {
            spawnDrops(state, worldIn, pos);
            worldIn.removeBlock(pos, false);
        }

    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        worldIn.setBlockState(pos, updateDistance(state, worldIn, pos), 3);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        int i = getDistance(facingState) + 1;
        if (i != 1 || stateIn.get(DISTANCE) != i) {
            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
        }

        return stateIn;
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

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE, PERSISTENT);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return updateDistance(this.getDefaultState().with(PERSISTENT, Boolean.valueOf(true)), context.getWorld(), context.getPos());
    }
}

