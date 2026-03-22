package quek.undergarden.entity.projectile;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import quek.undergarden.component.RogdoriumInfusion;
import quek.undergarden.registry.*;

public class RotbelcherProjectile extends AbstractHurtingProjectile {

	public RotbelcherProjectile(EntityType<? extends RotbelcherProjectile> entityType, Level level) {
		super(entityType, level);
	}

	public RotbelcherProjectile(Level level, LivingEntity owner, Vec3 movement) {
		super(UGEntityTypes.ROTBELCHER_PROJECTILE.get(), owner, movement, level);
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		if (this.level() instanceof ServerLevel level) {
			Entity victim = result.getEntity();
			Entity shooter = this.getOwner();
			LivingEntity livingShooter = shooter instanceof LivingEntity ? (LivingEntity) shooter : null;
			DamageSource damageSource = this.damageSources().spit(this, livingShooter);
			if (victim.hurtServer(level, damageSource, 5.0F)) {
				EnchantmentHelper.doPostAttackEffects(level, victim, damageSource);
				if (victim instanceof LivingEntity livingEntity) {
					if (!this.level().isClientSide() && !livingEntity.is(UGTags.Entities.IMMUNE_TO_INFECTION)) {
						float data = livingEntity.getData(UGAttachments.UTHERIC_INFECTION);
						float b = 0.2F;
						int a = 0;
						if (livingEntity instanceof Player player) {
							for (EquipmentSlot slot : EquipmentSlotGroup.ARMOR) {
								ItemStack armor = player.getInventory().getItem(slot.getIndex(Inventory.INVENTORY_SIZE));
								int infusionAmount = armor.getOrDefault(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.DEFAULT).infusionAmount();
								if (infusionAmount > 0) {
									armor.set(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.setInfusionAmount(infusionAmount - 1));
									a++;
								}
							}
						}
						float t = b / ((1 + a) * 0.18F);
						livingEntity.setData(UGAttachments.UTHERIC_INFECTION, data + t);
					}
				}
			}
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
	}

	@Override
	protected void onHit(HitResult result) {
		super.onHit(result);
		if (!this.level().isClientSide()) {
			this.discard();
		}
	}

	@Override
	protected boolean shouldBurn() {
		return false;
	}

	@Nullable
	@Override
	protected ParticleOptions getTrailParticle() {
		return new ItemParticleOption(ParticleTypes.ITEM, UGItems.UTHERIUM_CRYSTAL.get());
	}

	@Override
	protected boolean canHitEntity(Entity target) {
		return !(target.is(UGTags.Entities.ROTSPAWN)) && super.canHitEntity(target);
	}
}
