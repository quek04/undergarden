package quek.undergarden.entity.monster.stoneborn;

import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidType;
import quek.undergarden.entity.monster.stoneborn.goals.StonebornLookAtCustomerGoal;
import quek.undergarden.entity.monster.stoneborn.goals.StonebornTradeWithPlayerGoal;
import quek.undergarden.entity.monster.stoneborn.trading.StonebornTrades;
import quek.undergarden.registry.UGCriteria;
import quek.undergarden.registry.UGDimensions;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.UUID;

public class Stoneborn extends Monster implements NeutralMob, Npc, Merchant {

	protected int timeOutOfUG = 0;
	private static final UniformInt ANGER_TIME_RANGE = TimeUtil.rangeOfSeconds(20, 39);
	private int angerTime;
	private UUID targetUuid;
	@Nullable
	private Player customer;
	@Nullable
	protected MerchantOffers offers;

	public Stoneborn(EntityType<? extends Monster> type, Level level) {
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
		return Monster.createMobAttributes()
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
	public boolean canDrownInFluidType(FluidType type) {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		if (this.isAggressive()) {
			return UGSoundEvents.STONEBORN_ANGRY.get();
		}
		if (this.hasCustomer()) {
			return UGSoundEvents.STONEBORN_SPEAKING.get();
		}
		if (!this.inUndergarden()) {
			return UGSoundEvents.STONEBORN_CONFUSED.get();
		}
		return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return UGSoundEvents.STONEBORN_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.STONEBORN_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(UGSoundEvents.STONEBORN_STEP.get(), 1.0F, 1.0F);
	}

	protected SoundEvent getYesOrNoSound(boolean getYesSound) {
		return getYesSound ? UGSoundEvents.STONEBORN_PLEASED.get() : UGSoundEvents.STONEBORN_CONFUSED.get();
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (itemstack.getItem() != UGItems.STONEBORN_SPAWN_EGG.get() && this.isAlive() && !this.hasCustomer() && this.inUndergarden()) {
			if (!this.getOffers().isEmpty()) {
				if (!this.level().isClientSide()) {
					this.setTradingPlayer(player);
					this.openTradingScreen(player, this.getDisplayName(), 1);
				}
			}
			return InteractionResult.sidedSuccess(this.level().isClientSide());
		} else {
			return super.mobInteract(player, hand);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.inUndergarden() && !this.isNoAi()) {
			++this.timeOutOfUG;
			this.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0));
		} else {
			this.timeOutOfUG = 0;
		}

		if (this.timeOutOfUG > 300) {
			this.playSound(UGSoundEvents.STONEBORN_CHANT.get(), 1.0F, 1.0F);
			if (!this.level().isClientSide()) {
				this.remove(RemovalReason.KILLED);
				this.level().explode(this, this.getX(), this.getY(), this.getZ(), 3, Level.ExplosionInteraction.MOB);
			}
		}
	}

	public boolean inUndergarden() {
		return this.level().dimension() == UGDimensions.UNDERGARDEN_LEVEL;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		this.timeOutOfUG = tag.getInt("TimeOutOfUndergarden");
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		tag.putInt("TimeOutOfUndergarden", this.timeOutOfUG);
	}

	@Override
	public int getRemainingPersistentAngerTime() {
		return this.angerTime;
	}

	@Override
	public void setRemainingPersistentAngerTime(int time) {
		this.angerTime = time;
	}

	@Nullable
	@Override
	public UUID getPersistentAngerTarget() {
		return this.targetUuid;
	}

	@Override
	public void setPersistentAngerTarget(@Nullable UUID target) {
		this.targetUuid = target;
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setRemainingPersistentAngerTime(ANGER_TIME_RANGE.sample(this.random));
	}

	@Override
	public void setTradingPlayer(@Nullable Player player) {
		this.customer = player;
	}

	@Nullable
	@Override
	public Player getTradingPlayer() {
		return this.customer;
	}

	public boolean hasCustomer() {
		return this.customer != null;
	}

	@Override
	public MerchantOffers getOffers() {
		if (this.offers == null) {
			this.offers = new MerchantOffers();
			this.populateTradeData();
		}

		return this.offers;
	}

	protected void populateTradeData() {
		VillagerTrades.ItemListing[] trades = StonebornTrades.VAGABOND_TRADES.get(1);
		if (trades != null) {
			MerchantOffers merchantoffers = this.getOffers();
			this.addTrades(merchantoffers, trades, 4);
		}
	}

	protected void addTrades(MerchantOffers givenMerchantOffers, VillagerTrades.ItemListing[] newTrades, int maxNumbers) {
		Set<Integer> set = Sets.newHashSet();
		if (newTrades.length > maxNumbers) {
			while (set.size() < maxNumbers) {
				set.add(this.random.nextInt(newTrades.length));
			}
		} else {
			for (int i = 0; i < newTrades.length; ++i) {
				set.add(i);
			}
		}

		for (Integer integer : set) {
			VillagerTrades.ItemListing villagertrades$itrade = newTrades[integer];
			MerchantOffer merchantoffer = villagertrades$itrade.getOffer(this, this.random);
			if (merchantoffer != null) {
				givenMerchantOffers.add(merchantoffer);
			}
		}
	}

	@Override
	public void overrideOffers(@Nullable MerchantOffers offers) {
	}

	@Override
	public void notifyTrade(MerchantOffer offer) {
		offer.increaseUses();
		this.ambientSoundTime = -this.getAmbientSoundInterval();
		this.onStonebornTrade(offer);
		if (this.customer instanceof ServerPlayer player) {
			UGCriteria.STONEBORN_TRADE.get().trigger(player, this, offer.getResult());
		}
	}

	protected void onStonebornTrade(MerchantOffer offer) {
		if (offer.shouldRewardExp()) {
			int i = 3 + this.getRandom().nextInt(4);
			this.level().addFreshEntity(new ExperienceOrb(this.level(), this.getX(), this.getY() + 0.5D, this.getZ(), i));
		}
	}

	@Override
	public void notifyTradeUpdated(ItemStack stack) {
		if (!this.level().isClientSide() && this.ambientSoundTime > -this.getAmbientSoundInterval() + 20) {
			this.ambientSoundTime = -this.getAmbientSoundInterval();
			this.playSound(this.getYesOrNoSound(!stack.isEmpty()), this.getSoundVolume(), this.getVoicePitch());
		}
	}

	@Override
	public int getVillagerXp() {
		return 0;
	}

	@Override
	public void overrideXp(int xpIn) {
	}

	@Override
	public boolean showProgressBar() {
		return false;
	}

	@Override
	public SoundEvent getNotifyTradeSound() {
		return UGSoundEvents.STONEBORN_PLEASED.get();
	}

	@Override
	public boolean isClientSide() {
		return this.level().isClientSide();
	}
}