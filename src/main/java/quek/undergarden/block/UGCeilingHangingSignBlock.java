package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import quek.undergarden.block.entity.UndergardenHangingSignBlockEntity;

public class UGCeilingHangingSignBlock extends CeilingHangingSignBlock {
	public UGCeilingHangingSignBlock(WoodType type, Properties properties) {
		super(type, properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new UndergardenHangingSignBlockEntity(pos, state);
	}
}
