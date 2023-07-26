package quek.undergarden.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import quek.undergarden.entity.projectile.MinionProjectile;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;
import quek.undergarden.registry.UGTags;

public class Minion extends AbstractGolem implements RangedAttackMob {

	public Minion(EntityType<? extends AbstractGolem> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new RangedAttackGoal(this, 0.5D, 20, 10.0F));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, (entity) ->
				entity instanceof Enemy || entity.getType().is(UGTags.Entities.ROTSPAWN) || entity.getType().is(UGTags.Entities.CAVERN_CREATURE))
		);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.0F;
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return AbstractGolem.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.ARMOR, 10.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 5.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D);
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.MINION_DEATH.get();
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		MinionProjectile projectile = new MinionProjectile(this.level(), this);
		double xDistance = target.getX() - this.getX();
		double yDistance = target.getY(0.3333333333333333D) - projectile.getY();
		double zDistance = target.getZ() - this.getZ();
		double yMath = Mth.sqrt((float) ((xDistance * xDistance) + (zDistance * zDistance)));
		projectile.shoot(xDistance, yDistance + yMath * 0.1D, zDistance, 1.6F, 1.0F);
		this.playSound(UGSoundEvents.MINION_SHOOT.get(), 1.0F, this.getVoicePitch());
		this.level().addFreshEntity(projectile);
	}

	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		Item item = itemstack.getItem();
		if (item != UGItems.FORGOTTEN_NUGGET.get()) {
			return InteractionResult.PASS;
		} else {
			float health = this.getHealth();
			this.heal(5.0F);
			if (this.getHealth() == health) {
				return InteractionResult.PASS;
			} else {
				this.playSound(UGSoundEvents.MINION_REPAIR.get(), 1.0F, 2.0F);
				if (!player.getAbilities().instabuild) {
					itemstack.shrink(1);
				}

				return InteractionResult.sidedSuccess(this.level().isClientSide());
			}
		}
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}
}