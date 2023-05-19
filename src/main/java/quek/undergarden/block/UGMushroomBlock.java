package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.level.SaplingGrowTreeEvent;
import net.minecraftforge.eventbus.api.Event;
import quek.undergarden.registry.UGBlocks;

import java.util.Optional;

public class UGMushroomBlock extends UGBushBlock implements BonemealableBlock {

	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
	private final ResourceKey<ConfiguredFeature<?, ?>> giantMushroom;

	public UGMushroomBlock(BlockBehaviour.Properties properties, ResourceKey<ConfiguredFeature<?, ?>> giantMushroom) {
		super(properties);
		this.giantMushroom = giantMushroom;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (random.nextInt(25) == 0) {
			int i = 5;

			for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, -1, -4), pos.offset(4, 1, 4))) {
				if (level.getBlockState(blockpos).getBlock() == this) {
					--i;
					if (i <= 0) {
						return;
					}
				}
			}

			BlockPos blockpos1 = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);

			for (int k = 0; k < 4; ++k) {
				if (level.isEmptyBlock(blockpos1) && state.canSurvive(level, blockpos1)) {
					pos = blockpos1;
				}

				blockpos1 = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
			}

			if (level.isEmptyBlock(blockpos1) && state.canSurvive(level, blockpos1)) {
				level.setBlock(blockpos1, state, 2);
			}
		}

	}

	@Override
	public boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.isSolidRender(level, pos);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockPos blockpos = pos.below();
		BlockState blockstate = level.getBlockState(blockpos);
		Block block = blockstate.getBlock();
		if (block != UGBlocks.DEEPTURF_BLOCK.get() && block != UGBlocks.DEEPSOIL.get()) {
			return level.getRawBrightness(pos, 0) < 13 && blockstate.canSustainPlant(level, blockpos, net.minecraft.core.Direction.UP, this);
		} else {
			return true;
		}
	}

	public void growMushroom(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
		Optional<? extends Holder<ConfiguredFeature<?, ?>>> optional = level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(this.giantMushroom);
		if (optional.isPresent()) {
			SaplingGrowTreeEvent event = ForgeEventFactory.blockGrowFeature(level, random, pos, optional.get());
			if (event.getResult().equals(Event.Result.DENY)) return;
			level.removeBlock(pos, false);
			if (!event.getFeature().value().place(level, level.getChunkSource().getGenerator(), random, pos)) {
				level.setBlock(pos, state, 3);
			}
		}
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader reader, BlockPos blockPos, BlockState blockState, boolean b) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos blockPos, BlockState blockState) {
		return (double) random.nextFloat() < 0.4D;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		this.growMushroom(level, pos, state, random);
	}
}