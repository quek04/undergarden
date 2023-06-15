package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import quek.undergarden.block.entity.UndergardenHangingSignBlockEntity;

public class UGWallHangingSignBlock extends WallHangingSignBlock {
	public UGWallHangingSignBlock(Properties properties, WoodType type) {
		super(properties, type);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new UndergardenHangingSignBlockEntity(pos, state);
	}
}
