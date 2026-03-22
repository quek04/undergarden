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
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemUtil;
import quek.undergarden.registry.UGDataComponents;

public class BucketDispenseBehavior extends OptionalDispenseItemBehavior {

	private final DefaultDispenseItemBehavior dispenseBehavior = new DefaultDispenseItemBehavior();

	@Override
	protected ItemStack execute(BlockSource source, ItemStack stack) {
		ServerLevel level = source.level();
		Direction facing = source.state().getValue(DispenserBlock.FACING);
		BlockPos pos = source.pos().relative(facing);
		BlockState state = level.getBlockState(pos);

		var containingHandler = new ItemStacksResourceHandler(2);
		containingHandler.set(0, ItemResource.of(stack), stack.getCount());
		var itemAccess = ItemAccess.forHandlerIndex(containingHandler, 0).oneByOne();

		var resourceHandler = itemAccess.getCapability(Capabilities.Fluid.ITEM);
		if (resourceHandler == null) {
			return super.execute(source, stack);
		}

		if (UGBucketItem.isBucketEmpty(stack)) {
			return this.fillBucket(source, stack, level, pos, facing, state, containingHandler, resourceHandler);
		} else {
			return this.emptyBucket(source, stack, level, pos, containingHandler, resourceHandler);
		}
	}

	private ItemStack fillBucket(BlockSource source, ItemStack stack, ServerLevel level, BlockPos pos, Direction facing, BlockState state, ItemStacksResourceHandler containingHandler, ResourceHandler<FluidResource> handler) {
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
			if (!FluidUtil.tryPickupFluid(handler, null, source.level(), pos, facing.getOpposite()).isEmpty()) {
				var stack0 = ItemUtil.getStack(containingHandler, 0);
				var stack1 = ItemUtil.getStack(containingHandler, 1);

				// Grow by 1 to match the shrink in consumeWithRemainder
				stack0.grow(1);
				return this.consumeWithRemainder(source, stack, stack1);
			}
		}
		return stack;
	}


	private ItemStack emptyBucket(BlockSource source, ItemStack stack, ServerLevel level, BlockPos pos, ItemStacksResourceHandler containingHandler, ResourceHandler<FluidResource> handler) {
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
					ItemStack fluidResult = this.dispenseFluid(source, workBucket, level, pos, containingHandler, handler);
					bucketItem.spawnEntityFromBucket(null, level, stack, pos, true);
					return fluidResult;
				} else {
					return bucketItem.spawnEntityFromBucket(null, level, stack, pos, true);
				}
			}
		}
		return this.dispenseFluid(source, stack, level, pos, containingHandler, handler);
	}

	private ItemStack dispenseFluid(BlockSource source, ItemStack stack, ServerLevel level, BlockPos pos, ItemStacksResourceHandler containingHandler, ResourceHandler<FluidResource> handler) {
		if (!FluidUtil.tryPlaceFluid(handler, null, level, InteractionHand.MAIN_HAND, pos).isEmpty()) {
			var stack0 = ItemUtil.getStack(containingHandler, 0);
			var stack1 = ItemUtil.getStack(containingHandler, 1);

			// Grow by 1 to match the shrink in consumeWithRemainder
			stack0.grow(1);
			return this.consumeWithRemainder(source, stack, stack1);
		} else {
			return super.execute(source, stack);
		}
	}
}
