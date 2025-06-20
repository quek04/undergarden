package quek.undergarden.item.bucket;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidActionResult;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import quek.undergarden.registry.UGDataComponents;

public class BucketDispenseBehavior extends OptionalDispenseItemBehavior {

	private final DefaultDispenseItemBehavior dispenseBehavior = new DefaultDispenseItemBehavior();

	@Override
	protected ItemStack execute(BlockSource source, ItemStack stack) {
		ServerLevel level = source.level();
		Direction facing = source.state().getValue(DispenserBlock.FACING);
		BlockPos pos = source.pos().relative(facing);
		BlockState state = level.getBlockState(pos);

		if (UGBucketItem.isBucketEmpty(stack)) {
			return this.fillBucket(source, stack, level, pos, facing, state);
		} else {
			return this.emptyBucket(source, stack, level, pos, facing);
		}
	}

	private ItemStack fillBucket(BlockSource source, ItemStack stack, ServerLevel level, BlockPos pos, Direction facing, BlockState state) {
		//pickup block
		if (!UGBucketItem.containsBlock(stack) && state.getBlock() instanceof BucketPickup bucketPickup && !(state.getBlock() instanceof LiquidBlock)) {
			var resultStack = bucketPickup.pickupBlock(null, level, pos, state);
			if (!resultStack.isEmpty()) {
				ItemStack usedStack = stack.copy();
				usedStack.setCount(1);
				usedStack.set(UGDataComponents.STORED_BLOCK, state);
				if (stack.getCount() == 1) {
					return usedStack;
				}
				return this.consumeWithRemainder(source, stack, usedStack);
			}
		} else {
			//pickup fluid
			var action = FluidUtil.tryPickUpFluid(stack.copyWithCount(1), null, level, pos, facing.getOpposite());
			var resultStack = action.getResult();

			if (action.isSuccess() && !resultStack.isEmpty()) {
				if (stack.getCount() == 1) {
					return resultStack;
				}
				return this.consumeWithRemainder(source, stack, resultStack);
			}
		}
		return stack;
	}


	private ItemStack emptyBucket(BlockSource source, ItemStack stack, ServerLevel level, BlockPos pos, Direction facing) {
		if (UGBucketItem.containsBlock(stack)) {
			//place block
			BlockState block = stack.get(UGDataComponents.STORED_BLOCK);
			if (block != null && block.getBlock().asItem() instanceof DispensibleContainerItem dispensibleContainerItem) {
				if (dispensibleContainerItem.emptyContents(null, level, pos, null, stack)) {
					dispensibleContainerItem.checkExtraContent(null, level, stack, pos);
					var workBucket = stack.copy();
					workBucket.remove(UGDataComponents.STORED_BLOCK);
					return workBucket;
				} else {
					return this.dispenseBehavior.dispense(source, stack);
				}
			}
		} else if (UGBucketItem.getBucketedEntity(stack).isPresent()) {
			//place entity
			if (stack.getItem() instanceof UGBucketItem bucketItem) {
				if (UGBucketItem.hasFluid(stack)) {
					//fluid can only be placed correctly if the entity is not inside
					ItemStack workBucket = stack.copy();
					workBucket.remove(DataComponents.BUCKET_ENTITY_DATA);
					ItemStack fluidResult = this.dispenseFluid(source, workBucket, level, pos);
					bucketItem.spawnEntityFromBucket(null, source.level(), stack, pos, true);
					return fluidResult;
				} else {
					return bucketItem.spawnEntityFromBucket(null, source.level(), stack, pos, true);
				}
			}
		}
		return this.dispenseFluid(source, stack, level, pos);
	}

	private ItemStack dispenseFluid(BlockSource source, ItemStack stack, ServerLevel level, BlockPos pos) {
		var singleStack = stack.copyWithCount(1);

		var fluidHandler = FluidUtil.getFluidHandler(singleStack);
		if (fluidHandler.isEmpty()) return super.execute(source, stack);

		var fluidStack = fluidHandler.get().drain(FluidType.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
		var result = !fluidStack.isEmpty() ? FluidUtil.tryPlaceFluid(null, level, InteractionHand.MAIN_HAND, pos, stack, fluidStack) : FluidActionResult.FAILURE;

		if (result.isSuccess()) {
			var drainedStack = result.getResult();

			if (drainedStack.getCount() == 1) {
				return drainedStack;
			} else if (!drainedStack.isEmpty() && !source.blockEntity().insertItem(drainedStack).isEmpty()) {
				this.dispense(source, drainedStack);
			}

			drainedStack.shrink(1);
			return drainedStack;
		} else {
			return this.dispense(source, stack);
		}
	}
}
