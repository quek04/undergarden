package quek.undergarden.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.block.entity.InfuserBlockEntity;
import quek.undergarden.registry.UGBlockEntities;

public class InfuserBlock extends BaseEntityBlock {

	public static final MapCodec<InfuserBlock> CODEC = simpleCodec(InfuserBlock::new);

	public InfuserBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return UGBlockEntities.INFUSER.get().create(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
		return level.isClientSide ? null : createTickerHelper(blockEntityType, UGBlockEntities.INFUSER.get(), InfuserBlockEntity::serverTick);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (level.isClientSide) {
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
	protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock())) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof InfuserBlockEntity) {
				if (level instanceof ServerLevel) {
					Containers.dropContents(level, pos, (InfuserBlockEntity)blockEntity);
					((InfuserBlockEntity)blockEntity).getRecipesToAwardAndPopExperience((ServerLevel)level, Vec3.atCenterOf(pos));
				}

				super.onRemove(state, level, pos, newState, isMoving);
				level.updateNeighbourForOutputSignal(pos, this);
			} else {
				super.onRemove(state, level, pos, newState, isMoving);
			}
		}
	}

	@Override
	protected boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	protected int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
		return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
	}
}