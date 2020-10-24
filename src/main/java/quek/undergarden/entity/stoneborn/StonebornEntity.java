package quek.undergarden.entity.stoneborn;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.entity.rotspawn.AbstractRotspawnEntity;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenEntities;
import quek.undergarden.registry.UndergardenItems;
import quek.undergarden.registry.UndergardenSoundEvents;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import net.minecraft.entity.ai.goal.Goal.Flag;

public class StonebornEntity extends MonsterEntity implements IAngerable, INPC, IMerchant {

    private static final DataParameter<Boolean> isChild = EntityDataManager.createKey(StonebornEntity.class, DataSerializers.BOOLEAN);
    private static final UUID BABY_SPEED_MODIFIER_IDENTIFIER = UUID.fromString("766bfa64-11f3-11ea-8d71-362b9e155667");
    private static final AttributeModifier BABY_SPEED_MODIFIER = new AttributeModifier(BABY_SPEED_MODIFIER_IDENTIFIER, "Baby speed boost", 0.2F, AttributeModifier.Operation.MULTIPLY_BASE);

    private int timeInOverworld = 0;
    private UUID uuid;
    @Nullable
    private PlayerEntity customer;
    @Nullable
    protected MerchantOffers offers;

    public StonebornEntity(EntityType<? extends StonebornEntity> type, World worldIn) {
        super(type, worldIn);
        this.stepHeight = 1.0F;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new StonebornEntity.TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(0, new StonebornEntity.LookAtCustomerGoal(this));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.0D, Ingredient.fromItems(UndergardenBlocks.regalium_block.get(), UndergardenItems.regalium_ingot.get(), UndergardenItems.regalium_nugget.get()), false));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 0.3D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, LivingEntity.class, 32.0F));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractRotspawnEntity.class, true));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return AgeableEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
                .createMutableAttribute(Attributes.ARMOR, 10.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.9D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 10.0D)
                ;
    }

    public static boolean canStonebornSpawn(EntityType<? extends MonsterEntity> type, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return worldIn.getDifficulty() != Difficulty.PEACEFUL && canSpawnOn(type, worldIn, reason, pos, randomIn);
    }

    @Override
    public void func_241847_a(ServerWorld world, LivingEntity entityLivingIn) { //on kill
        super.func_241847_a(world, entityLivingIn);
        if(entityLivingIn.getCreatureAttribute() == UndergardenEntities.ROTSPAWN || entityLivingIn instanceof PlayerEntity) {
            this.playSound(UndergardenSoundEvents.STONEBORN_CHUCKLE, 1.0F, 1.0F);
        }
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if(isAggressive()) {
            return UndergardenSoundEvents.STONEBORN_ANGRY;
        }
        if(hasCustomer()) {
            return UndergardenSoundEvents.STONEBORN_SPEAKING;
        }
        if(customer != null) {
            if(customer.getHeldItemMainhand().getItem() == UndergardenItems.regalium_ingot.get() || customer.getHeldItemMainhand().getItem() == UndergardenItems.regalium_nugget.get() || customer.getHeldItemMainhand().getItem() == UndergardenBlocks.regalium_block.get().asItem() || customer.getHeldItemOffhand().getItem() == UndergardenItems.regalium_ingot.get() || customer.getHeldItemOffhand().getItem() == UndergardenItems.regalium_nugget.get() || customer.getHeldItemOffhand().getItem() == UndergardenBlocks.regalium_block.get().asItem()) {
                return UndergardenSoundEvents.STONEBORN_AWE;
            }
        }
        if(inOverworld()) {
            return UndergardenSoundEvents.STONEBORN_CONFUSED;
        }
        return null;
    }

    @Override
    public SoundEvent getYesSound() {
        return UndergardenSoundEvents.STONEBORN_PLEASED;
    }

    protected SoundEvent getVillagerYesNoSound(boolean getYesSound) {
        return getYesSound ? UndergardenSoundEvents.STONEBORN_PLEASED : UndergardenSoundEvents.STONEBORN_CONFUSED;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UndergardenSoundEvents.STONEBORN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UndergardenSoundEvents.STONEBORN_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(UndergardenSoundEvents.STONEBORN_STEP, 1.0F, 1.0F);
    }

    @Override
    protected boolean isDespawnPeaceful() {
        return false;
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return !this.isNoDespawnRequired();
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        if (this.isChild()) {
            compound.putBoolean("IsBaby", true);
        }

        compound.putInt("TimeInOverworld", this.timeInOverworld);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setChild(compound.getBoolean("IsBaby"));
        this.timeInOverworld = compound.getInt("TimeInOverworld");
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(isChild, false);
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {
        super.notifyDataManagerChange(key);
        if (isChild.equals(key)) {
            this.recalculateSize();
        }
    }

    @Override
    public void setChild(boolean childStoneborn) {
        this.getDataManager().set(isChild, childStoneborn);
        if (!this.world.isRemote) {
            ModifiableAttributeInstance modifiableattributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
            modifiableattributeinstance.removeModifier(BABY_SPEED_MODIFIER);
            if (childStoneborn) {
                modifiableattributeinstance.applyNonPersistentModifier(BABY_SPEED_MODIFIER);
            }
        }

    }

    @Override
    public boolean isChild() {
        return this.getDataManager().get(isChild);
    }

    public boolean inOverworld() {
        return !this.world.getDimensionType().isPiglinSafe() && !this.isAIDisabled();
    }

    @Override
    protected void updateAITasks() {
        if (this.inOverworld()) {
            ++this.timeInOverworld;
            this.addPotionEffect(new EffectInstance(Effects.NAUSEA, 200, 0));
        } else {
            this.timeInOverworld = 0;
        }

        if (this.timeInOverworld > 300) {
            this.remove(false);
            Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 3, explosion$mode);
        }

        if (!isAggressive()) {
            if (world.getGameTime() % 40 == 0) {
                this.heal(1);
            }
        }

    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? sizeIn.height * 0.90F : 2.3F;
    }

    @Override
    public int getAngerTime() {
        return 0;
    }

    @Override
    public void setAngerTime(int i) {

    }

    @Nullable
    @Override
    public UUID getAngerTarget() {
        return this.uuid;
    }

    @Override
    public void setAngerTarget(@Nullable UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void func_230258_H__() {

    }

    @Override
    public ActionResultType func_230254_b_(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getHeldItem(p_230254_2_);
        if (!this.isAggressive() && itemstack.getItem() != UndergardenItems.stoneborn_spawn_egg.get() && this.isAlive() && !this.hasCustomer() && !this.isChild()) {
            if (p_230254_2_ == Hand.MAIN_HAND) {
                p_230254_1_.addStat(Stats.TALKED_TO_VILLAGER);
            }

            if (this.getOffers().isEmpty()) {
                return ActionResultType.func_233537_a_(this.world.isRemote);
            } else {
                if (!this.world.isRemote) {
                    this.setCustomer(p_230254_1_);
                    this.openMerchantContainer(p_230254_1_, this.getDisplayName(), 1);
                }

                return ActionResultType.func_233537_a_(this.world.isRemote);
            }
        } else {
            return super.func_230254_b_(p_230254_1_, p_230254_2_);
        }
    }

    public boolean hasCustomer() {
        return this.customer != null;
    }

    @Override
    public void setCustomer(@Nullable PlayerEntity playerEntity) {
        this.customer = playerEntity;
    }

    @Nullable
    @Override
    public PlayerEntity getCustomer() {
        return this.customer;
    }

    @Override
    public MerchantOffers getOffers() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
            this.populateTradeData();
        }

        return this.offers;
    }

    public static final Int2ObjectMap<VillagerTrades.ITrade[]> stonebornTrades;

    private static Int2ObjectMap<VillagerTrades.ITrade[]> gatAsIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> p_221238_0_) {
        return new Int2ObjectOpenHashMap(p_221238_0_);
    }

    static {
        stonebornTrades = gatAsIntMap(ImmutableMap.of(
                1, new VillagerTrades.ITrade[]{
                        new ItemsForRegaliumTrade(UndergardenItems.blisterbomb.get(), 5, 1, 3, 1),
                        new ItemsForRegaliumTrade(UndergardenItems.underbeans.get(), 1, 24, 64, 1),
                        new ItemsForRegaliumTrade(Items.BUCKET, 20, 1, 1, 5),
                        new ItemsForRegaliumTrade(Items.SHEARS, 10, 1, 1, 5),
                        new ItemsForRegaliumTrade(Items.FLINT, 5, 1, 4, 5),

                        new RegaliumForItemsTrade(Items.IRON_INGOT, 4, 1, 24, 3),
                        new RegaliumForItemsTrade(Items.GOLD_INGOT, 5, 1, 24, 3),
                        new RegaliumForItemsTrade(Items.DIAMOND, 10, 1, 24, 3),
                        new RegaliumForItemsTrade(Items.NETHERITE_INGOT, 20, 1, 24, 3),
                        new RegaliumForItemsTrade(Items.REDSTONE, 16, 1, 24, 3),
                        new RegaliumForItemsTrade(Items.LAPIS_LAZULI, 3, 3, 24, 3)
                }
        ));
    }

    protected void populateTradeData() {
        VillagerTrades.ITrade[] trades = stonebornTrades.get(1);
        if (trades != null) {
            MerchantOffers merchantoffers = this.getOffers();
            this.addTrades(merchantoffers, trades, 5);
            int i = this.rand.nextInt(trades.length);
            VillagerTrades.ITrade villagertrades$itrade = trades[i];
            MerchantOffer merchantoffer = villagertrades$itrade.getOffer(this, this.rand);
            if (merchantoffer != null) {
                merchantoffers.add(merchantoffer);
            }

        }
    }

    protected void addTrades(MerchantOffers givenMerchantOffers, VillagerTrades.ITrade[] newTrades, int maxNumbers) {
        Set<Integer> set = Sets.newHashSet();
        if (newTrades.length > maxNumbers) {
            while (set.size() < maxNumbers) {
                set.add(this.rand.nextInt(newTrades.length));
            }
        } else {
            for (int i = 0; i < newTrades.length; ++i) {
                set.add(i);
            }
        }

        for (Integer integer : set) {
            VillagerTrades.ITrade villagertrades$itrade = newTrades[integer];
            MerchantOffer merchantoffer = villagertrades$itrade.getOffer(this, this.rand);
            if (merchantoffer != null) {
                givenMerchantOffers.add(merchantoffer);
            }
        }

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void setClientSideOffers(MerchantOffers offers) {
    }

    @Override
    public void onTrade(MerchantOffer offer) {
        offer.increaseUses();
        this.livingSoundTime = -this.getTalkInterval();
        this.onVillagerTrade(offer);
        if (this.customer instanceof ServerPlayerEntity) {
            //CriteriaTriggers.VILLAGER_TRADE.test((ServerPlayerEntity)this.customer, this, offer.getSellingStack());
        }

    }

    protected void onVillagerTrade(MerchantOffer offer) {
        if (offer.getDoesRewardExp()) {
            int i = 3 + this.rand.nextInt(4);
            this.world.addEntity(new ExperienceOrbEntity(this.world, this.getPosX(), this.getPosY() + 0.5D, this.getPosZ(), i));
        }

    }

    @Override
    public void verifySellingItem(ItemStack stack) {
        if (!this.world.isRemote && this.livingSoundTime > -this.getTalkInterval() + 20) {
            this.livingSoundTime = -this.getTalkInterval();
            this.playSound(this.getVillagerYesNoSound(!stack.isEmpty()), this.getSoundVolume(), this.getSoundPitch());
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
    public void setXP(int i) {

    }

    @Override
    public boolean hasXPBar() {
        return false;
    }

    static class ItemsForRegaliumTrade implements VillagerTrades.ITrade {
        private final ItemStack tradeItem;
        private final int regaliumCount;
        private final int tradeItemCount;
        private final int maxUses;
        private final int xpValue;
        private final float priceMultiplier;

        public ItemsForRegaliumTrade(Block tradeItem, int regalium, int tradeItemCount, int maxUses, int xpValue) {
            this(new ItemStack(tradeItem), regalium, tradeItemCount, maxUses, xpValue);
        }

        public ItemsForRegaliumTrade(Item tradeItem, int regalium, int tradeItemCount, int xpValue) {
            this(new ItemStack(tradeItem), regalium, tradeItemCount, 12, xpValue);
        }

        public ItemsForRegaliumTrade(Item tradeItem, int regalium, int tradeItemCount, int maxUses, int xpValue) {
            this(new ItemStack(tradeItem), regalium, tradeItemCount, maxUses, xpValue);
        }

        public ItemsForRegaliumTrade(ItemStack tradeItem, int regalium, int tradeItemCount, int maxUses, int xpValue) {
            this(tradeItem, regalium, tradeItemCount, maxUses, xpValue, 0.05F);
        }

        public ItemsForRegaliumTrade(ItemStack tradeItem, int regalium, int tradeItemCount, int maxUses, int xpValue, float priceMultiplier) {
            this.tradeItem = tradeItem;
            this.regaliumCount = regalium;
            this.tradeItemCount = tradeItemCount;
            this.maxUses = maxUses;
            this.xpValue = xpValue;
            this.priceMultiplier = priceMultiplier;
        }

        public MerchantOffer getOffer(Entity trader, Random rand) {
            return new MerchantOffer(new ItemStack(UndergardenItems.regalium_nugget.get(), this.regaliumCount), new ItemStack(this.tradeItem.getItem(), this.tradeItemCount), this.maxUses, this.xpValue, this.priceMultiplier);
        }
    }

    static class RegaliumForItemsTrade implements VillagerTrades.ITrade {
        private final Item tradeItem;
        private final int regaliumCount;
        private final int count;
        private final int maxUses;
        private final int xpValue;
        private final float priceMultiplier;

        public RegaliumForItemsTrade(IItemProvider itemProvider, int regalium, int tradeItemCount, int maxUses, int xpValue) {
            this.tradeItem = itemProvider.asItem();
            this.regaliumCount = regalium;
            this.count = tradeItemCount;
            this.maxUses = maxUses;
            this.xpValue = xpValue;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(Entity trader, Random rand) {
            return new MerchantOffer(new ItemStack(this.tradeItem, this.count), (new ItemStack(UndergardenItems.regalium_nugget.get(), this.regaliumCount)), this.maxUses, this.xpValue, this.priceMultiplier);
        }
    }

    public static class TradeWithPlayerGoal extends Goal {
        private final StonebornEntity stoneborn;

        public TradeWithPlayerGoal(StonebornEntity stonebornEntity) {
            this.stoneborn = stonebornEntity;
            this.setMutexFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
        }

        public boolean shouldExecute() {
            if (!this.stoneborn.isAlive()) {
                return false;
            } else if (this.stoneborn.isInWater()) {
                return false;
            } else if (!this.stoneborn.isOnGround()) { //not on ground
                return false;
            } else if (this.stoneborn.velocityChanged) {
                return false;
            } else {
                PlayerEntity playerEntity = this.stoneborn.getCustomer();
                if (playerEntity == null) {
                    return false;
                } else if (this.stoneborn.getDistanceSq(playerEntity) > 16.0D) {
                    return false;
                } else {
                    return playerEntity.openContainer != null;
                }
            }
        }

        public void startExecuting() {
            this.stoneborn.getNavigator().clearPath();
        }

        public void resetTask() {
            this.stoneborn.setCustomer(null);
        }
    }

    public static class LookAtCustomerGoal extends LookAtGoal {
        private final StonebornEntity stoneborn;

        public LookAtCustomerGoal(StonebornEntity stonebornEntity) {
            super(stonebornEntity, PlayerEntity.class, 8.0F);
            this.stoneborn = stonebornEntity;
        }

        public boolean shouldExecute() {
            if (this.stoneborn.hasCustomer()) {
                this.closestEntity = this.stoneborn.getCustomer();
                return true;
            } else {
                return false;
            }
        }
    }
}
