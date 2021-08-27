package quek.undergarden.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.entity.rotspawn.AbstractRotspawnEntity;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nullable;
import java.util.Random;

public class DwellerEntity extends AnimalEntity implements IRideable, IEquipable {

    private static final DataParameter<Boolean> SADDLE = EntityDataManager.defineId(DwellerEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.defineId(DwellerEntity.class, DataSerializers.INT);
    private final BoostHelper steering = new BoostHelper(this.entityData, BOOST_TIME, SADDLE);

    public DwellerEntity(EntityType<? extends DwellerEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.5D));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.5D, Ingredient.of(UGItems.UNDERBEANS.get(), UGItems.UNDERBEAN_STICK.get()), false));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, AbstractRotspawnEntity.class, 12.0F, 2.0D, 2.5D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.15D);
    }

    public static boolean canDwellerSpawn(EntityType<? extends AnimalEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return worldIn.getBlockState(pos.below()).is(UGBlocks.DEEPTURF_BLOCK.get()) || worldIn.getBlockState(pos.below()).is(UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return UGSoundEvents.DWELLER_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UGSoundEvents.DWELLER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UGSoundEvents.DWELLER_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(UGSoundEvents.DWELLER_STEP.get(), 0.15F, 0.5F);
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld serverWorld, AgeableEntity ageableEntity) {
        return UGEntityTypes.DWELLER.get().create(level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return Ingredient.of(UGItems.UNDERBEANS.get()).test(stack);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        this.steering.addAdditionalSaveData(nbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        this.steering.readAdditionalSaveData(nbt);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SADDLE, false);
        this.entityData.define(BOOST_TIME, 0);
    }

    @Override
    public void onSyncedDataUpdated(DataParameter<?> data) {
        if (BOOST_TIME.equals(data) && this.level.isClientSide) {
            this.steering.onSynced();
        }
        super.onSyncedDataUpdated(data);
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        boolean isFood = this.isFood(player.getItemInHand(hand));
        if (!isFood && this.isSaddled() && !this.isVehicle() && !player.isSecondaryUseActive()) {
            if (!this.level.isClientSide) {
                player.startRiding(this);
            }

            return ActionResultType.sidedSuccess(this.level.isClientSide);
        }
        else {
            ActionResultType actionresulttype = super.mobInteract(player, hand);
            if (!actionresulttype.consumesAction()) {
                ItemStack itemstack = player.getItemInHand(hand);
                return itemstack.getItem() == Items.SADDLE ? itemstack.interactLivingEntity(player, this, hand) : ActionResultType.PASS;
            }
            else {
                return actionresulttype;
            }
        }
    }

    @Override
    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby();
    }

    @Override
    public void equipSaddle(@Nullable SoundCategory sound) {
        this.steering.setSaddle(true);
        if (sound != null) {
            this.level.playSound(null, this, SoundEvents.PIG_SADDLE, sound, 0.5F, 1.0F);
        }
    }

    @Override
    public boolean isSaddled() {
        return this.steering.hasSaddle();
    }

    @Override
    public boolean boost() {
        return this.steering.boost(this.getRandom());
    }

    @Override
    public void travel(Vector3d vector) {
        this.travel(this, this.steering, vector);
    }

    @Override
    public void travelWithInput(Vector3d vector) {
        super.travel(vector);
    }

    @Override
    public float getSteeringSpeed() {
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 1.1F;
    }

    @Nullable
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    @Override
    public boolean canBeControlledByRider() {
        Entity entity = this.getControllingPassenger();
        if (!(entity instanceof PlayerEntity)) {
            return false;
        }
        else {
            PlayerEntity playerentity = (PlayerEntity)entity;
            return playerentity.getMainHandItem().getItem() == UGItems.UNDERBEAN_STICK.get() || playerentity.getOffhandItem().getItem() == UGItems.UNDERBEAN_STICK.get();
        }
    }

    @Override
    protected void dropEquipment() {
        super.dropEquipment();
        if (this.isSaddled()) {
            this.spawnAtLocation(Items.SADDLE);
        }
    }

    @Override
    public void positionRider(Entity passenger) {
        float ySin = MathHelper.sin(this.yBodyRot * ((float)Math.PI / 180F));
        float yCos = MathHelper.cos(this.yBodyRot * ((float)Math.PI / 180F));
        passenger.setPos(this.getX() + (double)(0.5F * ySin), this.getY() + this.getPassengersRidingOffset() + passenger.getMyRidingOffset() - 0.1F, this.getZ() - (double)(0.5F * yCos));
    }
}