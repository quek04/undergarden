package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import quek.undergarden.block.entity.GrongletBlockEntity;
import quek.undergarden.registry.UGBlockEntities;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nullable;
import java.util.Random;

public class GrongletBlock extends BaseEntityBlock implements EntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    private static final VoxelShape UP_SHAPE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 4.0F, 16.0F);
    private static final VoxelShape DOWN_SHAPE = Block.box(0.0F, 12.0F, 0.0F, 16.0F, 16.0F, 16.0F);
    private static final VoxelShape NORTH_SHAPE = Block.box(0.0F, 0.0F, 12.0F, 16.0F, 16.0F, 16.0F);
    private static final VoxelShape EAST_SHAPE = Block.box(0.0F, 0.0F, 0.0F, 4.0F, 16.0F, 16.0F);
    private static final VoxelShape SOUTH_SHAPE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 4.0F);
    private static final VoxelShape WEST_SHAPE = Block.box(12.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F);

    public GrongletBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            default -> UP_SHAPE;
            case DOWN -> DOWN_SHAPE;
            case NORTH -> NORTH_SHAPE;
            case EAST -> EAST_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getClickedFace();
        BlockState state = context.getLevel().getBlockState(context.getClickedPos().relative(direction.getOpposite()));
        return state.is(this) && state.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()) : this.defaultBlockState().setValue(FACING, direction);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        return canSupportCenter(level, pos.relative(direction.getOpposite()), state.getValue(GrongletBlock.FACING));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return state.getValue(FACING).getOpposite() == facing && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, Random random) {
        if (random.nextInt(10) == 0) {
            level.playLocalSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, UGSoundEvents.GRONGLET_AMBIENT.get(), SoundSource.BLOCKS, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return UGBlockEntities.GRONGLET.get().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        return createTickerHelper(blockEntity, UGBlockEntities.GRONGLET.get(), GrongletBlockEntity::tick);
    }

    @Override
    public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction direction, @Nullable LivingEntity igniter) {
        if (!level.isClientSide) {
            Random random = level.random;
            level.playSound(null, pos, UGSoundEvents.GRONGLET_BURN.get(), SoundSource.BLOCKS, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
        }
    }
}