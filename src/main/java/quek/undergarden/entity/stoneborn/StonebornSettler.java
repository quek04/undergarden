package quek.undergarden.entity.stoneborn;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
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
import quek.undergarden.Undergarden;
import quek.undergarden.entity.stoneborn.goals.StonebornLookAtCustomerGoal;
import quek.undergarden.entity.stoneborn.goals.StonebornTradeWithPlayerGoal;
import quek.undergarden.entity.stoneborn.trading.StonebornTrades;
import quek.undergarden.registry.StonebornJobs;
import quek.undergarden.registry.UGEntityDataSerializers;
import quek.undergarden.registry.UGItems;

public class StonebornSettler extends AbstractStoneborn implements StonebornDataHolder {
	private static final EntityDataAccessor<StonebornData> STONEBORN_DATA = SynchedEntityData.defineId(StonebornSettler.class, UGEntityDataSerializers.STONEBORN_DATA.get());
	private int stonebornXp;

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
	public StonebornData getStonebornData() {
		return this.entityData.get(STONEBORN_DATA);
	}

	@Override
	public void setStonebornData(StonebornData data) {
		StonebornData stonebornData = this.getStonebornData();
		if (stonebornData.getJob() != data.getJob()) {
			this.offers = null;
		}
		this.entityData.set(STONEBORN_DATA, data);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(STONEBORN_DATA, new StonebornData(StonebornJobs.NONE.get(), 1));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		StonebornData.CODEC
			.encodeStart(NbtOps.INSTANCE, this.getStonebornData())
			.resultOrPartial(Undergarden.LOGGER::error)
			.ifPresent(tag -> compound.put("StonebornData", tag));
		compound.putInt("Xp", this.stonebornXp);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("StonebornData")) {
			StonebornData.CODEC
				.parse(NbtOps.INSTANCE, compound.get("StonebornData"))
				.resultOrPartial(Undergarden.LOGGER::error)
				.ifPresent(data -> this.entityData.set(STONEBORN_DATA, data));
		}

		if (compound.contains("Xp")) {
			this.stonebornXp = compound.getInt("Xp");
		}
	}

	@Override
	protected void rewardTradeXp(MerchantOffer offer) {
		int i = 3 + this.random.nextInt(4);
		this.stonebornXp = this.stonebornXp + offer.getXp();
		//this.lastTradedPlayer = this.getTradingPlayer();
		if (this.shouldIncreaseLevel()) {
//			this.updateMerchantTimer = 40;
//			this.increaseProfessionLevelOnUpdate = true;
			i += 5;
		}

		if (offer.shouldRewardExp()) {
			this.level().addFreshEntity(new ExperienceOrb(this.level(), this.getX(), this.getY() + 0.5, this.getZ(), i));
		}
	}

	private boolean shouldIncreaseLevel() {
		int level = this.getStonebornData().getLevel();
		return StonebornData.canLevelUp(level) && this.stonebornXp >= StonebornData.getMaxXpPerLevel(level);
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