package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class UGSaplingBlock extends SaplingBlock {

	public UGSaplingBlock(AbstractTreeGrower tree) {
		super(tree, Properties.of()
				.mapColor(MapColor.PLANT)
				.pushReaction(PushReaction.DESTROY)
				.strength(0F)
				.randomTicks()
				.sound(SoundType.GRASS)
				.noOcclusion()
				.noCollission()
		);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		super.randomTick(state, level, pos, random);
		if (!level.isAreaLoaded(pos, 1))
			return;
		//remove light check so our trees grow in any light level
		this.advanceTree(level, pos, state, random);
	}
}