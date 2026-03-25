package quek.undergarden.entity.monster.stoneborn;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.Unit;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.neoforged.neoforge.fluids.FluidType;
import org.jspecify.annotations.Nullable;
import quek.undergarden.UGRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.monster.stoneborn.goals.StonebornLookAtCustomerGoal;
import quek.undergarden.entity.monster.stoneborn.goals.StonebornTradeWithPlayerGoal;
import quek.undergarden.entity.monster.stoneborn.trading.StonebornTrade;
import quek.undergarden.entity.monster.stoneborn.trading.StonebornTradeSet;
import quek.undergarden.registry.UGCriteria;
import quek.undergarden.registry.UGDimensions;
import quek.undergarden.registry.UGSoundEvents;
import quek.undergarden.registry.custom.UGStonebornTradeSets;

import java.util.List;
import java.util.Optional;

public class Stoneborn extends Monster implements NeutralMob, Npc, Merchant {

	protected int timeOutOfUG = 0;
	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
	private long persistentAngerEndTime;
	private @Nullable EntityReference<LivingEntity> persistentAngerTarget;
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

	public static boolean canStonebornSpawn(EntityType<? extends Monster> entity, LevelAccessor level, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
		return level.getDifficulty() != Difficulty.PEACEFUL && random.nextInt(10) == 0 && checkMobSpawnRules(entity, level, reason, pos, random);
	}

	@Override
	public boolean canDrownInFluidType(FluidType type) {
		return false;
	}

	@Nullable
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
		if (this.isAlive() && !this.hasCustomer() && this.inUndergarden()) {
			if (!this.level().isClientSide()) {
				if (!this.getOffers().isEmpty()) {

					this.setTradingPlayer(player);
					this.openTradingScreen(player, this.getDisplayName(), 1);
				}
			}
			return InteractionResult.SUCCESS;
		} else {
			return super.mobInteract(player, hand);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.inUndergarden() && !this.isNoAi()) {
			++this.timeOutOfUG;
			this.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 300, 0));
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
	protected void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		this.timeOutOfUG = input.getIntOr("time_out_of_undergarden", 0);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putInt("time_out_of_undergarden", this.timeOutOfUG);
	}

	@Override
	public long getPersistentAngerEndTime() {
		return this.persistentAngerEndTime;
	}

	@Override
	public void setPersistentAngerEndTime(long time) {
		this.persistentAngerEndTime = time;
	}

	@Override
	public @Nullable EntityReference<LivingEntity> getPersistentAngerTarget() {
		return this.persistentAngerTarget;
	}

	@Override
	public void setPersistentAngerTarget(@Nullable EntityReference<LivingEntity> target) {
		this.persistentAngerTarget = target;
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setTimeToRemainAngry(PERSISTENT_ANGER_TIME.sample(this.random));
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

	@Override
	public MerchantOffers getOffers() {
		if (this.level() instanceof ServerLevel serverLevel) {
			if (this.offers == null) {
				this.offers = new MerchantOffers();
				this.addOffersFromTradeSet(serverLevel, this.getOffers(), UGStonebornTradeSets.VAGABOND);
			}

			return this.offers;
		} else {
			throw new IllegalStateException("Cannot load Stoneborn trades on the client");
		}
	}

	protected void addOffersFromTradeSet(ServerLevel level, MerchantOffers offers, ResourceKey<StonebornTradeSet> resourceKey) {
		Optional<StonebornTradeSet> tradeSetOpt = this.registryAccess().lookupOrThrow(UGRegistries.Keys.STONEBORN_TRADE_SET).getOptional(resourceKey);
		if (tradeSetOpt.isEmpty()) {
			Undergarden.LOGGER.debug("Missing expected trade set {}", resourceKey);
		} else {
			StonebornTradeSet tradeSet = tradeSetOpt.get();
			LootContext lootContext = new LootContext.Builder(
				new LootParams.Builder(level)
					.withParameter(LootContextParams.ORIGIN, this.position())
					.withParameter(LootContextParams.THIS_ENTITY, this)
					.withParameter(LootContextParams.ADDITIONAL_COST_COMPONENT_ALLOWED, Unit.INSTANCE)
					.create(LootContextParamSets.VILLAGER_TRADE)
			)
				.create(tradeSet.randomSequence());
			int numberOfOffers = tradeSet.calculateNumberOfTrades(lootContext);
			if (tradeSet.allowDuplicates()) {
				addOffersFromItemListings(lootContext, offers, tradeSet.trades(), numberOfOffers);
			} else {
				addOffersFromItemListingsWithoutDuplicates(lootContext, offers, tradeSet.trades(), numberOfOffers);
			}
		}
	}

	private static void addOffersFromItemListings(LootContext lootContext, MerchantOffers merchantOffers, HolderSet<StonebornTrade> potentialOffers, int numberOfOffers) {
		int offersFound = 0;

		while (offersFound < numberOfOffers) {
			Optional<Holder<StonebornTrade>> trade = potentialOffers.getRandomElement(lootContext.getRandom());
			if (trade.isEmpty()) {
				break;
			}

			MerchantOffer offer = trade.get().value().getOffer(lootContext);
			if (offer != null) {
				merchantOffers.add(offer);
				offersFound++;
			}
		}
	}

	private static void addOffersFromItemListingsWithoutDuplicates(LootContext lootContext, MerchantOffers merchantOffers, HolderSet<StonebornTrade> potentialOffers, int numberOfOffers) {
		List<Holder<StonebornTrade>> leftoverOffers = Lists.newArrayList(potentialOffers);
		int offersFound = 0;

		while (offersFound < numberOfOffers && !leftoverOffers.isEmpty()) {
			Holder<StonebornTrade> trade = leftoverOffers.remove(lootContext.getRandom().nextInt(leftoverOffers.size()));
			MerchantOffer offer = trade.value().getOffer(lootContext);
			if (offer != null) {
				merchantOffers.add(offer);
				offersFound++;
			}
		}
	}

	public boolean hasCustomer() {
		return this.customer != null;
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

	@Override
	public boolean stillValid(Player player) {
		return this.getTradingPlayer() == player && this.isAlive() && player.isWithinEntityInteractionRange(this, 4.0D);
	}
}