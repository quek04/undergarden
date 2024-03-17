package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import quek.undergarden.registry.UGParticleTypes;

public class TallShimmerweedBlock extends DoublePlantBlock {
	public TallShimmerweedBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
			double x = (double) pos.getX() + random.nextFloat();
			double y = (double) pos.getY() + random.nextFloat();
			double z = (double) pos.getZ() + random.nextFloat();
			double xSpeed = (double) random.nextFloat() * -0.9D * (double) random.nextFloat();
			double zSpeed = (double) random.nextFloat() * -0.9D * (double) random.nextFloat();
			level.addParticle(UGParticleTypes.SHIMMER.get(), x, y, z, xSpeed, 0.0D, zSpeed);
		}
	}
}
