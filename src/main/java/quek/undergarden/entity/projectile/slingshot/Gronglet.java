package quek.undergarden.entity.projectile.slingshot;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import quek.undergarden.block.GrongletBlock;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGSoundEvents;

public class Gronglet extends SlingshotProjectile {

	public Gronglet(Level level, double x, double y, double z) {
		super(UGEntityTypes.GRONGLET.get(), x, y, z, level);
	}

	public Gronglet(LivingEntity shooter, Level level) {
		super(UGEntityTypes.GRONGLET.get(), shooter, level);
	}

	public Gronglet(EntityType<Gronglet> type, Level level) {
		super(type, level);
		this.setDropItem(false);
	}

	@Override
	protected Item getDefaultItem() {
		return UGBlocks.GRONGLET.get().asItem();
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		if (!this.level().isClientSide() && this.ricochetTimes == 0) {
			BlockPos pos = result.getBlockPos();
			Direction direction = result.getDirection();
			if (UGBlocks.GRONGLET.get().defaultBlockState().setValue(GrongletBlock.FACING, direction).canSurvive(this.level(), pos.relative(direction)) && this.level().getBlockState(pos.relative(direction)).isAir()) {
				this.level().setBlock(pos.relative(direction), UGBlocks.GRONGLET.get().defaultBlockState().setValue(GrongletBlock.FACING, direction), 2);
				this.level().playSound(null, pos, UGSoundEvents.GRONGLET_PLACE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
			} else {
				this.spawnAtLocation(new ItemStack(this.getDefaultItem()));
			}
			this.discard();
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		Entity entity = result.getEntity();
		if (entity instanceof Player player && !player.hasItemInSlot(EquipmentSlot.HEAD)) {
			player.setItemSlot(EquipmentSlot.HEAD, new ItemStack(this.getDefaultItem()));
		} else {
			this.spawnAtLocation(new ItemStack(this.getDefaultItem()));
		}
		this.level().playSound(null, result.getEntity().blockPosition(), UGSoundEvents.GRONGLET_PLACE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
		this.discard();
	}
}
