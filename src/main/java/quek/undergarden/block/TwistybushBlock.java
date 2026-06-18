package quek.undergarden.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DryVegetationBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGTags;

public class TwistybushBlock extends VegetationBlock {
	public static final MapCodec<TwistybushBlock> CODEC = simpleCodec(TwistybushBlock::new);

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
}
