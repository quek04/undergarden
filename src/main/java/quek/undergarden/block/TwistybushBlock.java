package quek.undergarden.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import quek.undergarden.registry.UGTags;

public class TwistybushBlock extends VegetationBlock {
	public static final MapCodec<TwistybushBlock> CODEC = simpleCodec(TwistybushBlock::new);
	private static final VoxelShape SHAPE = Block.column(12.0, 0.0, 13.0);

	public TwistybushBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends VegetationBlock> codec() {
		return CODEC;
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.is(UGTags.Blocks.SUPPORTS_TWISTYBUSH);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}