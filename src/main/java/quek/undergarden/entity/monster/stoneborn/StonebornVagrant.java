package quek.undergarden.entity.monster.stoneborn;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import quek.undergarden.entity.monster.stoneborn.goals.StonebornLookAtCustomerGoal;
import quek.undergarden.entity.monster.stoneborn.goals.StonebornTradeWithPlayerGoal;
import quek.undergarden.entity.monster.stoneborn.trading.StonebornTrades;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.UUID;

public class StonebornVagrant extends AbstractStoneborn implements NeutralMob {
	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
	private int remainingPersistentAngerTime;
	@Nullable
	private UUID persistentAngerTarget;

	public StonebornVagrant(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new StonebornTradeWithPlayerGoal(this));
		this.goalSelector.addGoal(1, new StonebornLookAtCustomerGoal(this));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.3D));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, LivingEntity.class, 32.0F));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
		this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers());
		this.targetSelector.addGoal(2, new ResetUniversalAngerTargetGoal<>(this, true));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 50.0D)
				.add(Attributes.ARMOR, 10.0D)
				.add(Attributes.ATTACK_DAMAGE, 10.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.9D)
				.add(Attributes.STEP_HEIGHT, 1.0D);
	}

	public static boolean canStonebornSpawn(EntityType<? extends Monster> entity, LevelAccessor level, MobSpawnType mobSpawnType, BlockPos pos, RandomSource random) {
		return level.getDifficulty() != Difficulty.PEACEFUL && random.nextInt(10) == 0 && checkMobSpawnRules(entity, level, mobSpawnType, pos, random);
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
		if (!itemstack.is(UGItems.STONEBORN_SPAWN_EGG) && this.isAlive() && !this.isTrading() && !this.isBaby() && this.inUndergarden() && !this.isAggressive()) {
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

	@Override
	public int getRemainingPersistentAngerTime() {
		return this.remainingPersistentAngerTime;
	}

	@Override
	public void setRemainingPersistentAngerTime(int remainingPersistentAngerTime) {
		this.remainingPersistentAngerTime = remainingPersistentAngerTime;
	}

	@Override
	public @org.jetbrains.annotations.Nullable UUID getPersistentAngerTarget() {
		return this.persistentAngerTarget;
	}

	@Override
	public void setPersistentAngerTarget(@org.jetbrains.annotations.Nullable UUID persistentAngerTarget) {
		this.persistentAngerTarget = persistentAngerTarget;
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
	}

	@Override
	public boolean showProgressBar() {
		return false;
	}
}