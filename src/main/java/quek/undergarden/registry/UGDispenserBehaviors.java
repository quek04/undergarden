package quek.undergarden.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import quek.undergarden.entity.Boomgourd;

public class UGDispenserBehaviors {

	public static void register() {
		DispenseItemBehavior bucketBehavior = new DefaultDispenseItemBehavior() {
			private final DefaultDispenseItemBehavior defaultBehavior = new DefaultDispenseItemBehavior();

			public ItemStack execute(BlockSource source, ItemStack stack) {
				BucketItem bucketitem = (BucketItem) stack.getItem();
				BlockPos blockpos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
				Level world = source.level().getLevel();
				if (bucketitem.emptyContents(null, world, blockpos, null)) {
					bucketitem.checkExtraContent(null, world, stack, blockpos);
					return new ItemStack(Items.BUCKET);
				} else {
					return this.defaultBehavior.dispense(source, stack);
				}
			}
		};

		DispenserBlock.registerBehavior(UGItems.VIRULENT_MIX_BUCKET.get(), bucketBehavior);
		DispenserBlock.registerBehavior(UGItems.GWIBLING_BUCKET.get(), bucketBehavior);

		DispenserBlock.registerProjectileBehavior(UGItems.DEPTHROCK_PEBBLE);

		DispenserBlock.registerProjectileBehavior(UGItems.GOO_BALL);

		DispenserBlock.registerProjectileBehavior(UGItems.ROTTEN_BLISTERBERRY);

		DispenserBlock.registerProjectileBehavior(UGItems.BLISTERBOMB);

		DispenserBlock.registerProjectileBehavior(UGBlocks.GRONGLET);

		DispenserBlock.registerBehavior(UGBlocks.BOOMGOURD.get(), new DefaultDispenseItemBehavior() {

			protected ItemStack execute(BlockSource source, ItemStack stack) {
				Level level = source.level().getLevel();
				BlockPos blockpos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
				Boomgourd gourd = new Boomgourd(level, (double) blockpos.getX() + 0.5D, blockpos.getY(), (double) blockpos.getZ() + 0.5D, null);
				level.addFreshEntity(gourd);
				level.playSound(null, gourd.getX(), gourd.getY(), gourd.getZ(), UGSoundEvents.BOOMGOURD_PRIMED.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
				level.gameEvent(null, GameEvent.ENTITY_PLACE, blockpos);
				stack.shrink(1);
				return stack;
			}
		});
	}
}
