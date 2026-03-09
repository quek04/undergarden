package quek.undergarden.entity.stoneborn;

import com.google.common.collect.Lists;
import com.mojang.logging.LogUtils;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;
import org.slf4j.Logger;
import quek.undergarden.registry.UGCriteria;
import quek.undergarden.registry.UGDimensions;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nullable;
import java.util.ArrayList;

public abstract class AbstractStoneborn extends Monster implements Npc, Merchant {
	protected int timeOutOfUG = 0;
	private static final Logger LOGGER = LogUtils.getLogger();
	@javax.annotation.Nullable
	private Player tradingPlayer;
	@javax.annotation.Nullable
	protected MerchantOffers offers;

	public AbstractStoneborn(EntityType<? extends Monster> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		if (this.isAggressive()) {
			return UGSoundEvents.STONEBORN_ANGRY.get();
		}
		if (this.isTrading()) {
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

	@Override
	public void tick() {
		super.tick();
		if (!this.inUndergarden() && !this.isNoAi()) {
			++this.timeOutOfUG;
			this.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0));
		} else {
			this.timeOutOfUG = 0;
		}

		if (this.timeOutOfUG >= 300) {
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
	public boolean canDrownInFluidType(FluidType type) {
		return false;
	}

//	@Override
//	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @javax.annotation.Nullable SpawnGroupData spawnGroupData) {
//		if (spawnGroupData == null) {
//			spawnGroupData = new AgeableMob.AgeableMobGroupData(false);
//		}
//
//		return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
//	}

	@Override
	public int getVillagerXp() {
		return 0;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
	}

	@Override
	public void setTradingPlayer(@javax.annotation.Nullable Player player) {
		this.tradingPlayer = player;
	}

	@javax.annotation.Nullable
	@Override
	public Player getTradingPlayer() {
		return this.tradingPlayer;
	}

	public boolean isTrading() {
		return this.tradingPlayer != null;
	}

	@Override
	public MerchantOffers getOffers() {
		if (this.level().isClientSide) {
			throw new IllegalStateException("Cannot load Villager offers on the client");
		} else {
			if (this.offers == null) {
				this.offers = new MerchantOffers();
				this.updateTrades();
			}

			return this.offers;
		}
	}

	@Override
	public void overrideOffers(@javax.annotation.Nullable MerchantOffers offers) {
	}

	@Override
	public void overrideXp(int xp) {
	}

	@Override
	public void notifyTrade(MerchantOffer offer) {
		offer.increaseUses();
		this.ambientSoundTime = -this.getAmbientSoundInterval();
		this.rewardTradeXp(offer);
		if (this.tradingPlayer instanceof ServerPlayer) {
			UGCriteria.STONEBORN_TRADE.get().trigger((ServerPlayer) this.tradingPlayer, this, offer.getResult());
		}
	}

	protected abstract void rewardTradeXp(MerchantOffer offer);

	@Override
	public boolean showProgressBar() {
		return true;
	}

	/**
	 * Notifies the merchant of a possible merchant recipe being fulfilled or not. Usually, this is just a sound byte being played depending on whether the suggested {@link net.minecraft.world.item.ItemStack} is not empty.
	 */
	@Override
	public void notifyTradeUpdated(ItemStack stack) {
		if (!this.level().isClientSide && this.ambientSoundTime > -this.getAmbientSoundInterval() + 20) {
			this.ambientSoundTime = -this.getAmbientSoundInterval();
			this.makeSound(this.getTradeUpdatedSound(!stack.isEmpty()));
		}
	}

	@Override
	public SoundEvent getNotifyTradeSound() {
		return UGSoundEvents.STONEBORN_PLEASED.get();
	}

	protected SoundEvent getTradeUpdatedSound(boolean isYesSound) {
		return isYesSound ? UGSoundEvents.STONEBORN_PLEASED.get() : UGSoundEvents.STONEBORN_CONFUSED.get();
	}

	public void playCelebrateSound() {
		this.makeSound(UGSoundEvents.STONEBORN_CHANT.get());
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		if (!this.level().isClientSide) {
			MerchantOffers merchantoffers = this.getOffers();
			if (!merchantoffers.isEmpty()) {
				compound.put(
					"Offers", MerchantOffers.CODEC.encodeStart(this.registryAccess().createSerializationContext(NbtOps.INSTANCE), merchantoffers).getOrThrow()
				);
			}
		}
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Offers")) {
			MerchantOffers.CODEC
				.parse(this.registryAccess().createSerializationContext(NbtOps.INSTANCE), compound.get("Offers"))
				.resultOrPartial(Util.prefix("Failed to load offers: ", LOGGER::warn))
				.ifPresent(offers -> this.offers = offers);
		}
	}

	@Nullable
	@Override
	public Entity changeDimension(DimensionTransition transition) {
		this.stopTrading();
		return super.changeDimension(transition);
	}

	protected void stopTrading() {
		this.setTradingPlayer(null);
	}

	/**
	 * Called when the mob's health reaches 0.
	 */
	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		this.stopTrading();
	}

	protected void addParticlesAroundSelf(ParticleOptions particleOption) {
		for (int i = 0; i < 5; i++) {
			double d0 = this.random.nextGaussian() * 0.02;
			double d1 = this.random.nextGaussian() * 0.02;
			double d2 = this.random.nextGaussian() * 0.02;
			this.level().addParticle(particleOption, this.getRandomX(1.0), this.getRandomY() + 1.0, this.getRandomZ(1.0), d0, d1, d2);
		}
	}

	@Override
	public boolean canBeLeashed() {
		return false;
	}

	protected abstract void updateTrades();

	/**
	 * Adds limited numbers of trades to the given {@link net.minecraft.world.item.trading.MerchantOffers}.
	 */
	protected void addOffersFromItemListings(MerchantOffers givenMerchantOffers, VillagerTrades.ItemListing[] newTrades, int maxNumbers) {
		ArrayList<VillagerTrades.ItemListing> arraylist = Lists.newArrayList(newTrades);
		int i = 0;

		while (i < maxNumbers && !arraylist.isEmpty()) {
			MerchantOffer merchantoffer = arraylist.remove(this.random.nextInt(arraylist.size())).getOffer(this, this.random);
			if (merchantoffer != null) {
				givenMerchantOffers.add(merchantoffer);
				i++;
			}
		}
	}

	@Override
	public Vec3 getRopeHoldPosition(float partialTicks) {
		float f = Mth.lerp(partialTicks, this.yBodyRotO, this.yBodyRot) * (float) (Math.PI / 180.0);
		Vec3 vec3 = new Vec3(0.0, this.getBoundingBox().getYsize() - 1.0, 0.2);
		return this.getPosition(partialTicks).add(vec3.yRot(-f));
	}

	@Override
	public boolean isClientSide() {
		return this.level().isClientSide;
	}
}