package quek.undergarden.entity.monster.stoneborn;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import quek.undergarden.entity.monster.stoneborn.goals.StonebornLookAtCustomerGoal;
import quek.undergarden.entity.monster.stoneborn.goals.StonebornTradeWithPlayerGoal;
import quek.undergarden.entity.monster.stoneborn.trading.StonebornTrades;
import quek.undergarden.registry.UGItems;

public class StonebornSettler extends AbstractStoneborn {

	public StonebornSettler(EntityType<? extends Monster> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new StonebornTradeWithPlayerGoal(this));
		this.goalSelector.addGoal(1, new StonebornLookAtCustomerGoal(this));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.3D));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, LivingEntity.class, 32.0F));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
//		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
//		this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers());
//		this.targetSelector.addGoal(2, new ResetUniversalAngerTargetGoal<>(this, true));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 50.0D)
			.add(Attributes.ARMOR, 10.0D)
			.add(Attributes.ATTACK_DAMAGE, 10.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.3D)
			.add(Attributes.KNOCKBACK_RESISTANCE, 0.9D)
			.add(Attributes.STEP_HEIGHT, 1.0D);
	}

	@Override
	protected void rewardTradeXp(MerchantOffer offer) {
		if (offer.shouldRewardExp()) {
			int i = 3 + this.random.nextInt(4);
			this.level().addFreshEntity(new ExperienceOrb(this.level(), this.getX(), this.getY() + 0.5, this.getZ(), i));
		}
	}

	@Override
	protected void updateTrades() {
		VillagerTrades.ItemListing[] trades = StonebornTrades.VAGRANT_TRADES.get(1);
		if (trades != null) {
			MerchantOffers offers = this.getOffers();
			this.addOffersFromItemListings(offers, trades, 5);
		}
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (!itemstack.is(UGItems.STONEBORN_VAGRANT_SPAWN_EGG) && this.isAlive() && !this.isTrading() && !this.isBaby() && this.inUndergarden() && !this.isAggressive()) {
//			if (hand == InteractionHand.MAIN_HAND) {
//				player.awardStat(Stats.TALKED_TO_VILLAGER);
//			}

			if (!this.level().isClientSide) {
				if (this.getOffers().isEmpty()) {
					return InteractionResult.CONSUME;
				}

				this.setTradingPlayer(player);
				this.openTradingScreen(player, this.getDisplayName(), 1);
			}

			return InteractionResult.sidedSuccess(this.level().isClientSide);
		} else {
			return super.mobInteract(player, hand);
		}
	}
}