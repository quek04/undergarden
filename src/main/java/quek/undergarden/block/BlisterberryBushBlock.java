package quek.undergarden.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import quek.undergarden.registry.UGDamageSources;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;
import quek.undergarden.registry.UGTags;

public class BlisterberryBushBlock extends VegetationBlock implements BonemealableBlock {

	public static final MapCodec<BlisterberryBushBlock> CODEC = simpleCodec(BlisterberryBushBlock::new);
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	protected static final VoxelShape BABY_SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
	protected static final VoxelShape NORMAL_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);

	public BlisterberryBushBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 0));
	}

	@Override
	protected MapCodec<? extends VegetationBlock> codec() {
		return CODEC;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return state.getValue(AGE) == 0 ? BABY_SHAPE : NORMAL_SHAPE;
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
		return new ItemStack(UGItems.BLISTERBERRY.get());
	}

	@Override
	protected boolean isRandomlyTicking(BlockState state) {
		return state.getValue(AGE) < 3;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		super.randomTick(state, level, pos, random);
		int i = state.getValue(AGE);
		if (i < 3 && CommonHooks.canCropGrow(level, pos, state, random.nextInt(5) == 0)) {
			level.setBlock(pos, state.setValue(AGE, i + 1), 2);
			CommonHooks.fireCropGrowPost(level, pos, state);
		}
	}

	@Override
	protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
		if (!entity.is(UGTags.Entities.IMMUNE_TO_BLISTERBERRY_BUSH)) {
			entity.makeStuckInBlock(state, new Vec3(0.8D, 0.75D, 0.8D));
			if (level instanceof ServerLevel serverLevel && state.getValue(AGE) > 0) {
				Vec3 movement = entity.isClientAuthoritative() ? entity.getKnownMovement() : entity.oldPosition().subtract(entity.position());
				if (movement.horizontalDistanceSqr() > 0.0) {
					double xs = Math.abs(movement.x());
					double zs = Math.abs(movement.z());
					if (xs >= 0.003F || zs >= 0.003F) {
						entity.hurtServer(serverLevel, level.damageSources().source(UGDamageSources.BLISTERBERRY_BUSH), 2.0F);
					}
				}
			}
		}
	}

	@Override
	protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		int age = state.getValue(AGE);
		boolean isOld = age == 3;
		return !isOld && stack.is(Items.BONE_MEAL) ? InteractionResult.PASS : super.useItemOn(stack, state, level, pos, player, hand, result);
	}

	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
		int age = state.getValue(AGE);
		boolean isOld = age == 3;
		if (age > 1) {
			int random = 1 + level.getRandom().nextInt(2);
			int random2 = level.getRandom().nextInt(2);
			popResource(level, pos, new ItemStack(UGItems.BLISTERBERRY.get(), random + (isOld ? 1 : 0)));
			popResource(level, pos, new ItemStack(UGItems.ROTTEN_BLISTERBERRY.get(), random2 + (isOld ? 1 : 0)));
			level.playSound(null, pos, UGSoundEvents.PICK_BLISTERBERRY_BUSH.get(), SoundSource.BLOCKS, 1.0F, 0.8F + level.getRandom().nextFloat() * 0.4F);
			BlockState newState = state.setValue(AGE, 1);
			level.setBlock(pos, newState, 2);
			level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
			return InteractionResult.SUCCESS;
		} else {
			return super.useWithoutItem(state, level, pos, player, result);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
		return state.getValue(AGE) < 3;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		int i = Math.min(3, state.getValue(AGE) + 1);
		level.setBlock(pos, state.setValue(AGE, i), 2);
	}
}