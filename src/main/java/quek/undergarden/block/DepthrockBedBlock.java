package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import quek.undergarden.block.entity.DepthrockBedBlockEntity;
import quek.undergarden.registry.UGDimensions;

import java.util.List;

public class DepthrockBedBlock extends BedBlock {

	protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);

	public DepthrockBedBlock(Properties properties) {
		super(DyeColor.GREEN, properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(PART, BedPart.FOOT).setValue(OCCUPIED, Boolean.FALSE));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext selectionContext) {
		return SHAPE;
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS_SERVER;
		} else {
			if (state.getValue(PART) != BedPart.HEAD) {
				pos = pos.relative(state.getValue(FACING));
				state = level.getBlockState(pos);
				if (!state.is(this)) {
					return InteractionResult.CONSUME;
				}
			}

			BedRule bedRule = level.environmentAttributes().getValue(EnvironmentAttributes.BED_RULE, pos);
			if (level.dimension() != UGDimensions.UNDERGARDEN_LEVEL && bedRule.explodes()) {
				bedRule.errorMessage().ifPresent(player::sendOverlayMessage);
				level.removeBlock(pos, false);
				BlockPos blockPos = pos.relative(state.getValue(FACING).getOpposite());
				if (level.getBlockState(blockPos).is(this)) {
					level.removeBlock(blockPos, false);
				}

				Vec3 boomPos = pos.getCenter();
				level.explode(null, level.damageSources().badRespawnPointExplosion(boomPos), null, boomPos, 5.0F, true, Level.ExplosionInteraction.BLOCK);
				return InteractionResult.SUCCESS_SERVER;
			} else if (state.getValue(OCCUPIED)) {
				if (!this.kickVillagerOutOfBed(level, pos)) {
					player.sendOverlayMessage(Component.translatable("block.minecraft.bed.occupied"));
				}

				return InteractionResult.SUCCESS_SERVER;
			} else {
				player.startSleepInBed(pos).ifLeft(problem -> {
					if (problem.message() != null) {
						player.sendOverlayMessage(problem.message());
					}
				});
				return InteractionResult.SUCCESS_SERVER;
			}
		}
	}

	private boolean kickVillagerOutOfBed(Level pLevel, BlockPos pPos) {
		List<Villager> list = pLevel.getEntitiesOfClass(Villager.class, new AABB(pPos), LivingEntity::isSleeping);
		if (list.isEmpty()) {
			return false;
		} else {
			list.getFirst().stopSleeping();
			return true;
		}
	}

	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
		entity.causeFallDamage(fallDistance, 1.25F, entity.damageSources().fall());
	}

	//stops bouncing
	@Override
	public void updateEntityMovementAfterFallOn(BlockGetter level, Entity entity) {
		entity.setDeltaMovement(entity.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new DepthrockBedBlockEntity(pos, state);
	}
}
