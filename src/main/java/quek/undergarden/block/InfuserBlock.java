package quek.undergarden.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;
import quek.undergarden.block.entity.InfuserBlockEntity;
import quek.undergarden.registry.UGBlockEntities;
import quek.undergarden.registry.UGParticleTypes;

public class InfuserBlock extends BaseEntityBlock {

	public static final MapCodec<InfuserBlock> CODEC = simpleCodec(InfuserBlock::new);

	public static final EnumProperty<InfuserState> STATE = EnumProperty.create("state", InfuserState.class);

	private static final VoxelShape SHAPE = Shapes.or(
		Block.box(0.0D, 0.0D, 0.0D, 4.0D, 12.0D, 4.0D),
		Block.box(0.0D, 0.0D, 12.0D, 4.0D, 12.0D, 16.0D),
		Block.box(12.0D, 0.0D, 0.0D, 16.0D, 12.0D, 4.0D),
		Block.box(12.0D, 0.0D, 12.0D, 16.0D, 12.0D, 16.0D),
		Block.box(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D),
		Block.box(2.0D, 8.0D, 3.0D, 14.0D, 16.0D, 13.0D)
	);

	public InfuserBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(STATE, InfuserState.INACTIVE));
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STATE);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(STATE, InfuserState.INACTIVE);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return UGBlockEntities.INFUSER.get().create(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
		return level instanceof ServerLevel serverLevel ? createTickerHelper(blockEntityType, UGBlockEntities.INFUSER.get(), (level1, pos, state1, entity) -> InfuserBlockEntity.serverTick(serverLevel, pos, state1, entity)) : null;
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		double x = (double) pos.getX() + 0.5D;
		double y = (double) pos.getY() + 1.2D;
		double z = (double) pos.getZ() + 0.5D;
		if (state.getValue(STATE) == InfuserState.INFUSING_UTHERIUM) {
			level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
			level.addParticle(UGParticleTypes.SHARD.get(), x, y, z, 0.0D, 0.0D, 0.0D);
		}
		if (state.getValue(STATE) == InfuserState.INFUSING_ROGDORIUM) {
			level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
			level.addParticle(UGParticleTypes.ROGDORIUM_SPARKLE.get(), x, y, z, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		} else {
			this.openContainer(level, pos, player);
			return InteractionResult.CONSUME;
		}
	}

	protected void openContainer(Level level, BlockPos pos, Player player) {
		BlockEntity blockentity = level.getBlockEntity(pos);
		if (blockentity instanceof InfuserBlockEntity) {
			player.openMenu((MenuProvider)blockentity);
			//player.awardStat(Stats.INTERACT_WITH_FURNACE);
		}
	}

	@Override
	protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
		Containers.updateNeighboursAfterDestroy(state, level, pos);
	}

	@Override
	protected boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
		return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
	}
}