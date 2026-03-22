package quek.undergarden.entity.projectile.slingshot;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import quek.undergarden.block.GrongletBlock;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;

public class Gronglet extends SlingshotProjectile {

	public Gronglet(EntityType<? extends ThrowableItemProjectile> type, Level level) {
		super(type, level);
	}

	public Gronglet(double x, double y, double z, Level level, ItemStack stack) {
		super(UGEntityTypes.GRONGLET.get(), x, y, z, level, stack);
	}

	public Gronglet(LivingEntity shooter, Level level, ItemStack stack) {
		super(UGEntityTypes.GRONGLET.get(), shooter, level, stack);
	}

	@Override
	protected Item getDefaultItem() {
		return UGItems.GRONGLET.get();
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		if (this.level() instanceof ServerLevel serverLevel && this.ricochetTimes == 0) {
			BlockPos pos = result.getBlockPos();
			Direction direction = result.getDirection();
			if (this.getItem().getItem() instanceof BlockItem block && block.getBlock().defaultBlockState().setValue(GrongletBlock.FACING, direction).canSurvive(this.level(), pos.relative(direction)) && this.level().getBlockState(pos.relative(direction)).isAir()) {
				this.level().setBlock(pos.relative(direction), block.getBlock().defaultBlockState().setValue(GrongletBlock.FACING, direction), 2);
				this.level().playSound(null, pos, UGSoundEvents.GRONGLET_PLACE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
			} else {
				this.spawnAtLocation(serverLevel, this.getItem());
			}
			this.discard();
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		Entity entity = result.getEntity();
		this.level().playSound(null, result.getEntity().blockPosition(), UGSoundEvents.GRONGLET_PLACE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
		if (entity instanceof Player player && this.getItem().has(DataComponents.EQUIPPABLE)) {
			if (this.getItem().get(DataComponents.EQUIPPABLE).equipOnTarget(player, player, this.getItem()).consumesAction()) {
				this.discard();
				return;
			}
		}
		if (this.level() instanceof ServerLevel serverLevel) {
			this.spawnAtLocation(serverLevel, this.getItem());
		}
	}
}
