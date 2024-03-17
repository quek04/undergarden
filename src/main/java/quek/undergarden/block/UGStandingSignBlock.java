package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import quek.undergarden.registry.UGBlockEntities;

public class UGStandingSignBlock extends StandingSignBlock {

	public UGStandingSignBlock(WoodType type, Properties properties) {
		super(type, properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return UGBlockEntities.UNDERGARDEN_SIGN.get().create(pPos, pState);
	}

}
