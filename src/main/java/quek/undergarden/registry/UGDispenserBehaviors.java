package quek.undergarden.registry;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import quek.undergarden.entity.Boomgourd;
import quek.undergarden.entity.projectile.Blisterbomb;
import quek.undergarden.entity.projectile.slingshot.DepthrockPebble;
import quek.undergarden.entity.projectile.slingshot.GooBall;
import quek.undergarden.entity.projectile.slingshot.Gronglet;
import quek.undergarden.entity.projectile.slingshot.RottenBlisterberry;

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

		DispenserBlock.registerBehavior(UGItems.DEPTHROCK_PEBBLE.get(), new AbstractProjectileDispenseBehavior() {
			protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
				return Util.make(new DepthrockPebble(level, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stack));
			}
		});

		DispenserBlock.registerBehavior(UGItems.GOO_BALL.get(), new AbstractProjectileDispenseBehavior() {
			protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
				return Util.make(new GooBall(level, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stack));
			}
		});

		DispenserBlock.registerBehavior(UGItems.ROTTEN_BLISTERBERRY.get(), new AbstractProjectileDispenseBehavior() {
			protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
				return Util.make(new RottenBlisterberry(level, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stack));
			}
		});

		DispenserBlock.registerBehavior(UGItems.BLISTERBOMB.get(), new AbstractProjectileDispenseBehavior() {
			protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
				return Util.make(new Blisterbomb(level, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stack));
			}
		});

		DispenserBlock.registerBehavior(UGBlocks.GRONGLET.get(), new AbstractProjectileDispenseBehavior() {
			protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
				return Util.make(new Gronglet(level, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stack));
			}
		});

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
