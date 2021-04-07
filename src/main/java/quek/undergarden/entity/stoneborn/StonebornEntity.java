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
import quek.undergarden.registry.*;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class StonebornEntity extends MonsterEntity implements IAngerable, INPC, IMerchant {

    protected int timeOutOfUG = 0;
    private static final RangedInteger ANGER_TIME_RANGE = TickRangeConverter.convertRange(20, 39);
    private int angerTime;
    private UUID targetUuid;
    @Nullable
    private PlayerEntity customer;
    @Nullable
    protected MerchantOffers offers;

    public StonebornEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.stepHeight = 1.0F;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new StonebornTradeWithPlayerGoal(this));
        this.goalSelector.addGoal(1, new StonebornLookAtCustomerGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.3D));
        this.goalSelector.addGoal(3, new LookAtGoal(this, LivingEntity.class, 32.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setCallsForHelp());
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
                .createMutableAttribute(Attributes.ARMOR, 10.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 10.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.9D);
    }

    public static boolean canStonebornSpawn(EntityType<? extends MonsterEntity> type, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return worldIn.getDifficulty() != Difficulty.PEACEFUL && randomIn.nextInt(10) == 0 && canSpawnOn(type, worldIn, reason, pos, randomIn);
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
    public ActionResultType func_230254_b_(PlayerEntity player, Hand playerHand) {
        ItemStack itemstack = player.getHeldItem(playerHand);
        if (itemstack.getItem() != UGItems.STONEBORN_SPAWN_EGG.get() && this.isAlive() && !this.hasCustomer() && inUndergarden()) {
            if (this.getOffers().isEmpty()) {
                return ActionResultType.func_233537_a_(this.world.isRemote);
            } else {
                if (!this.world.isRemote) {
                    this.setCustomer(player);
                    this.openMerchantContainer(player, this.getDisplayName(), 1);
                }

                return ActionResultType.func_233537_a_(this.world.isRemote);
            }
        } else {
            return super.func_230254_b_(player, playerHand);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.inUndergarden()) {
            ++this.timeOutOfUG;
            this.addPotionEffect(new EffectInstance(Effects.NAUSEA, 300, 0));
        }
        else {
            this.timeOutOfUG = 0;
        }

        if (this.timeOutOfUG > 300) {
            this.playSound(UGSoundEvents.STONEBORN_CHANT.get(), 1.0F, 1.0F);
            this.remove();
            if (!this.world.isRemote) {
                Explosion.Mode explosionType = this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) ? Explosion.Mode.BREAK : Explosion.Mode.NONE;
                this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 3, explosionType);
            }
        }

        if (!isAggressive()) {
            if (world.getGameTime() % 40 == 0) {
                this.heal(1);
            }
        }
    }

    public boolean inUndergarden() {
        return this.world.getDimensionKey() == UGDimensions.UNDERGARDEN_WORLD && !this.isAIDisabled();
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        super.writeAdditional(nbt);
        this.timeOutOfUG = nbt.getInt("timeOutOfUG");
    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        super.readAdditional(nbt);
        nbt.putInt("timeOutOfUG", this.timeOutOfUG);
    }

    @Override
    public int getAngerTime() {
        return this.angerTime;
    }

    @Override
    public void setAngerTime(int time) {
        this.angerTime = time;
    }

    @Nullable
    @Override
    public UUID getAngerTarget() {
        return this.targetUuid;
    }

    @Override
    public void setAngerTarget(@Nullable UUID target) {
        this.targetUuid = target;
    }

    @Override
    public void func_230258_H__() {
        this.setAngerTime(ANGER_TIME_RANGE.getRandomWithinRange(this.rand));
    }

    @Override
    public void setCustomer(@Nullable PlayerEntity player) {
        this.customer = player;
    }

    @Nullable
    @Override
    public PlayerEntity getCustomer() {
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
                set.add(this.rand.nextInt(newTrades.length));
            }
        } else {
            for(int i = 0; i < newTrades.length; ++i) {
                set.add(i);
            }
        }

        for(Integer integer : set) {
            VillagerTrades.ITrade villagertrades$itrade = newTrades[integer];
            MerchantOffer merchantoffer = villagertrades$itrade.getOffer(this, this.rand);
            if (merchantoffer != null) {
                givenMerchantOffers.add(merchantoffer);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void setClientSideOffers(@Nullable MerchantOffers offers) { }

    @Override
    public void onTrade(MerchantOffer offer) {
        offer.increaseUses();
        this.livingSoundTime = -this.getTalkInterval();
        this.onStonebornTrade(offer);
        if (this.customer instanceof ServerPlayerEntity) {
            UGCriteria.STONEBORN_TRADE.test((ServerPlayerEntity)this.customer, this, offer.getSellingStack());
        }
    }

    protected void onStonebornTrade(MerchantOffer offer) {
        if (offer.getDoesRewardExp()) {
            int i = 3 + this.rand.nextInt(4);
            this.world.addEntity(new ExperienceOrbEntity(this.world, this.getPosX(), this.getPosY() + 0.5D, this.getPosZ(), i));
        }
    }

    @Override
    public void verifySellingItem(ItemStack stack) {
        if (!this.world.isRemote && this.livingSoundTime > -this.getTalkInterval() + 20) {
            this.livingSoundTime = -this.getTalkInterval();
            this.playSound(this.getYesOrNoSound(!stack.isEmpty()), this.getSoundVolume(), this.getSoundPitch());
        }
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public int getXp() {
        return 0;
    }

    @Override
    public void setXP(int xpIn) { }

    @Override
    public boolean hasXPBar() {
        return false;
    }

    @Override
    public SoundEvent getYesSound() {
        return UGSoundEvents.STONEBORN_PLEASED.get();
    }
}
