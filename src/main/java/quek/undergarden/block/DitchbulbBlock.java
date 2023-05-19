package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import quek.undergarden.registry.UGItems;

public class DitchbulbBlock extends UGBushBlock implements BonemealableBlock {

	public static final IntegerProperty AGE = BlockStateProperties.AGE_1;

	protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);

	public DitchbulbBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
		return new ItemStack(UGItems.DITCHBULB.get());
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		super.tick(state, level, pos, random);
		int age = state.getValue(AGE);
		if (random.nextInt(10) == 0 && age != 1 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pos, state, true)) {
			level.setBlock(pos, state.setValue(AGE, 1), 2);
			net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pos, state);
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		int age = state.getValue(AGE);
		boolean maxAge = age == 1;
		if (!maxAge && player.getItemInHand(hand).getItem() == Items.BONE_MEAL) {
			return InteractionResult.PASS;
		} else if (maxAge) {
			popResource(level, pos, new ItemStack(UGItems.DITCHBULB.get(), 1));
			level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
			level.setBlock(pos, state.setValue(AGE, 0), 2);
			return InteractionResult.SUCCESS;
		} else {
			return super.use(state, level, pos, player, hand, result);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return canSupportCenter(level, pos.below(), Direction.UP);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
		return facing == Direction.DOWN && !this.canSurvive(state, level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		double x = (double) pos.getX() + 0.5D;
		double y = (double) pos.getY() + 0.8D;
		double z = (double) pos.getZ() + 0.5D;
		if (state.getValue(AGE) == 1) {
			level.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
		return state.getValue(AGE) < 1;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		level.setBlock(pos, state.setValue(AGE, 1), 2);
	}
}