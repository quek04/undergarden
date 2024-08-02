package quek.undergarden.entity.projectile;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.registry.UGAttachments;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGTags;

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
			if (victim.hurt(damageSource, 5.0F)) {
				if (victim instanceof LivingEntity livingEntity) {
					if (!livingEntity.getType().is(UGTags.Entities.IMMUNE_TO_INFECTION)) {
						int data = livingEntity.getData(UGAttachments.UTHERIC_INFECTION);
						livingEntity.setData(UGAttachments.UTHERIC_INFECTION, data + 1);
					}
				}

				EnchantmentHelper.doPostAttackEffects(level, victim, damageSource);
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
		if (!this.level().isClientSide) {
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
		return new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(UGItems.UTHERIUM_CRYSTAL.get()));
	}
}
