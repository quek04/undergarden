package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import quek.undergarden.registry.UGBlocks;

import java.util.function.BiConsumer;

public class BasicDeepturfBlock extends Block {

	public BasicDeepturfBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean onTreeGrow(BlockState state, WorldGenLevel level, BiConsumer<BlockPos, BlockState> placeFunction, RandomSource randomSource, BlockPos pos, TreeConfiguration config) {
		placeFunction.accept(pos, UGBlocks.DEEPSOIL.get().defaultBlockState());
		return true;
	}
}