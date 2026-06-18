package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class UGSaplingBlock extends SaplingBlock {

	public UGSaplingBlock(TreeGrower tree, BlockBehaviour.Properties properties) {
		super(tree, properties);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!level.isAreaLoaded(pos, 1))
			return;
		//remove light check so our trees grow in any light level
		this.advanceTree(level, pos, state, random);
	}
}