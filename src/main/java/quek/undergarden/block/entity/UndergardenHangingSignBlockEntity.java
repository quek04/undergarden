package quek.undergarden.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGBlockEntities;

public class UndergardenHangingSignBlockEntity extends HangingSignBlockEntity {
	public UndergardenHangingSignBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
	public BlockEntityType<?> getType() {
		return UGBlockEntities.UNDERGARDEN_HANGING_SIGN.get();
	}
}