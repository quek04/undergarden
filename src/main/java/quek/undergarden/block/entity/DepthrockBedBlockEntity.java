package quek.undergarden.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGBlockEntities;

public class DepthrockBedBlockEntity extends BlockEntity {

	public DepthrockBedBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
		super(UGBlockEntities.DEPTHROCK_BED.get(), pWorldPosition, pBlockState);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
}