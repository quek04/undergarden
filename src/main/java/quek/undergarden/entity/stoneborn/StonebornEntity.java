package quek.undergarden.entity.stoneborn;

import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.entity.stoneborn.goals.StonebornLookAtCustomerGoal;
import quek.undergarden.entity.stoneborn.goals.StonebornTradeWithPlayerGoal;
import quek.undergarden.entity.stoneborn.trading.StonebornTrades;
import quek.undergarden.registry.UGCriteria;
import quek.undergarden.registry.UGDimensions;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class StonebornEntity extends MonsterEntity implements IAngerable, INPC, IMerchant {

    protected int timeOutOfUG = 0;
    private static final RangedInteger ANGER_TIME_RANGE = TickRangeConverter.rangeOfSeconds(20, 39);
    private int angerTime;
    private UUID targetUuid;
    @Nullable
    private PlayerEntity customer;
    @Nullable
    protected MerchantOffers offers;

    public StonebornEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.maxUpStep = 1.0F;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new StonebornTradeWithPlayerGoal(this));
        this.goalSelector.addGoal(1, new StonebornLookAtCustomerGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.3D));
        this.goalSelector.addGoal(3, new LookAtGoal(this, LivingEntity.class, 32.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));

        this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.ARMOR, 10.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.9D);
    }

    public static boolean canStonebornSpawn(EntityType<? extends MonsterEntity> type, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return worldIn.getDifficulty() != Difficulty.PEACEFUL && randomIn.nextInt(10) == 0 && checkMobSpawnRules(type, worldIn, reason, pos, randomIn);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if(isAggressive()) {
            return UGSoundEvents.STONEBORN_ANGRY.get();
        }
        if(hasCustomer()) {
            return UGSoundEvents.STONEBORN_SPEAKING.get();
        }
        if(!inUndergarden()) {
            return UGSoundEvents.STONEBORN_CONFUSED.get();
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UGSoundEvents.STONEBORN_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UGSoundEvents.STONEBORN_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(UGSoundEvents.STONEBORN_STEP.get(), 1.0F, 1.0F);
    }

    protected SoundEvent getYesOrNoSound(boolean getYesSound) {
        return getYesSound ? UGSoundEvents.STONEBORN_PLEASED.get() : UGSoundEvents.STONEBORN_CONFUSED.get();
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand playerHand) {
        ItemStack itemstack = player.getItemInHand(playerHand);
        if (itemstack.getItem() != UGItems.STONEBORN_SPAWN_EGG.get() && this.isAlive() && !this.hasCustomer() && inUndergarden()) {
            if (this.getOffers().isEmpty()) {
                return ActionResultType.sidedSuccess(this.level.isClientSide);
            } else {
                if (!this.level.isClientSide) {
                    this.setTradingPlayer(player);
                    this.openTradingScreen(player, this.getDisplayName(), 1);
                }

                return ActionResultType.sidedSuccess(this.level.isClientSide);
            }
        } else {
            return super.mobInteract(player, playerHand);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.inUndergarden()) {
            ++this.timeOutOfUG;
            this.addEffect(new EffectInstance(Effects.CONFUSION, 300, 0));
        }
        else {
            this.timeOutOfUG = 0;
        }

        if (this.timeOutOfUG > 300) {
            this.playSound(UGSoundEvents.STONEBORN_CHANT.get(), 1.0F, 1.0F);
            this.remove();
            if (!this.level.isClientSide) {
                Explosion.Mode explosionType = this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING) ? Explosion.Mode.BREAK : Explosion.Mode.NONE;
                this.level.explode(this, this.getX(), this.getY(), this.getZ(), 3, explosionType);
            }
        }

        if (!isAggressive()) {
            if (level.getGameTime() % 40 == 0) {
                this.heal(1);
            }
        }
    }

    public boolean inUndergarden() {
        return this.level.dimension() == UGDimensions.UNDERGARDEN_WORLD && !this.isNoAi();
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        this.timeOutOfUG = nbt.getInt("TimeOutOfUndergarden");
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        nbt.putInt("TimeOutOfUndergarden", this.timeOutOfUG);
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
        this.setRemainingPersistentAngerTime(ANGER_TIME_RANGE.randomValue(this.random));
    }

    @Override
    public void setTradingPlayer(@Nullable PlayerEntity player) {
        this.customer = player;
    }

    @Nullable
    @Override
    public PlayerEntity getTradingPlayer() {
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
        VillagerTrades.ITrade[] trades = StonebornTrades.STONEBORN_TRADES.get(1);
        if (trades != null) {
            MerchantOffers merchantoffers = this.getOffers();
            this.addTrades(merchantoffers, trades, 4);
        }
    }

    protected void addTrades(MerchantOffers givenMerchantOffers, VillagerTrades.ITrade[] newTrades, int maxNumbers) {
        Set<Integer> set = Sets.newHashSet();
        if (newTrades.length > maxNumbers) {
            while(set.size() < maxNumbers) {
                set.add(this.random.nextInt(newTrades.length));
            }
        } else {
            for(int i = 0; i < newTrades.length; ++i) {
                set.add(i);
            }
        }

        for(Integer integer : set) {
            VillagerTrades.ITrade villagertrades$itrade = newTrades[integer];
            MerchantOffer merchantoffer = villagertrades$itrade.getOffer(this, this.random);
            if (merchantoffer != null) {
                givenMerchantOffers.add(merchantoffer);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void overrideOffers(@Nullable MerchantOffers offers) { }

    @Override
    public void notifyTrade(MerchantOffer offer) {
        offer.increaseUses();
        this.ambientSoundTime = -this.getAmbientSoundInterval();
        this.onStonebornTrade(offer);
        if (this.customer instanceof ServerPlayerEntity) {
            UGCriteria.STONEBORN_TRADE.test((ServerPlayerEntity)this.customer, this, offer.getResult());
        }
    }

    protected void onStonebornTrade(MerchantOffer offer) {
        if (offer.shouldRewardExp()) {
            int i = 3 + this.random.nextInt(4);
            this.level.addFreshEntity(new ExperienceOrbEntity(this.level, this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }
    }

    @Override
    public void notifyTradeUpdated(ItemStack stack) {
        if (!this.level.isClientSide && this.ambientSoundTime > -this.getAmbientSoundInterval() + 20) {
            this.ambientSoundTime = -this.getAmbientSoundInterval();
            this.playSound(this.getYesOrNoSound(!stack.isEmpty()), this.getSoundVolume(), this.getVoicePitch());
        }
    }

    @Override
    public World getLevel() {
        return this.level;
    }

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int xpIn) { }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return UGSoundEvents.STONEBORN_PLEASED.get();
    }
}
