package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGParticleTypes;

public class UGPlantBlock extends UGBushBlock implements BonemealableBlock {

	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

	public UGPlantBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		DoublePlantBlock doubleplantblock = (DoublePlantBlock) (this == UGBlocks.SHIMMERWEED.get() ? UGBlocks.TALL_SHIMMERWEED.get() : UGBlocks.TALL_DEEPTURF.get());
		if (doubleplantblock.defaultBlockState().canSurvive(level, pos) && level.isEmptyBlock(pos.above())) {
			DoublePlantBlock.placeAt(level, doubleplantblock.defaultBlockState(), pos, 2);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		Vec3 offset = state.getOffset(level, pos);
		return SHAPE.move(offset.x, offset.y, offset.z);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (this == UGBlocks.SHIMMERWEED.get()) {
			double x = (double) pos.getX() + random.nextFloat();
			double y = (double) pos.getY() + 0.2D + random.nextFloat();
			double z = (double) pos.getZ() + random.nextFloat();
			double xSpeed = (double) random.nextFloat() * -0.9D * (double) random.nextFloat();
			double zSpeed = (double) random.nextFloat() * -0.9D * (double) random.nextFloat();
			level.addParticle(UGParticleTypes.SHIMMER.get(), x, y, z, xSpeed, 0.0D, zSpeed);
		}
	}
}