package quek.undergarden.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IForgeShearable;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MogEntity extends AnimalEntity implements IForgeShearable {

    private static final DataParameter<Boolean> HAS_MOSS = EntityDataManager.defineId(MogEntity.class, DataSerializers.BOOLEAN);
    //private static final DataParameter<Integer> TIME_WITHOUT_MOSS = EntityDataManager.defineId(MogEntity.class, DataSerializers.INT);
    private int timeWithoutMoss;

    public MogEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.1D, Ingredient.of(UGItems.DEPTHROCK_PEBBLE.get()), false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.1D);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public static boolean canMogSpawn(EntityType<? extends AnimalEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return worldIn.getBlockState(pos.below()).is(UGBlocks.DEEPTURF_BLOCK.get());
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity parent) {
        return UGEntityTypes.MOG.get().create(world);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return Ingredient.of(UGItems.DEPTHROCK_PEBBLE.get()).test(stack);
    }

    @Override
    public void tick() {
        super.tick();
        if(!hasMoss()) {
            timeWithoutMoss++;
        }
        else {
            timeWithoutMoss = 0;
        }

        if(timeWithoutMoss == 6000) {
            setMoss(true);
        }
    }

    public boolean hasMoss() {
        return this.entityData.get(HAS_MOSS);
    }

    public void setMoss(boolean hasMoss) {
        if(hasMoss) {
            this.entityData.set(HAS_MOSS, true);
        }
        else {
            this.entityData.set(HAS_MOSS, false);
        }
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.2F;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Has moss", this.hasMoss());
        this.timeWithoutMoss = nbt.getInt("Time without moss");
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        this.setMoss(nbt.getBoolean("Has moss"));
        nbt.putInt("Time without moss", this.timeWithoutMoss);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HAS_MOSS, true);
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData data, @Nullable CompoundNBT nbt) {
        this.setMoss(true);
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }

    @Override
    public boolean isShearable(@Nonnull ItemStack item, World world, BlockPos pos) {
        return this.hasMoss() && this.isAlive() && !this.isBaby();
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nullable PlayerEntity player, @Nonnull ItemStack item, World world, BlockPos pos, int fortune) {
        world.playSound(null, this, SoundEvents.SHEEP_SHEAR, player == null ? SoundCategory.BLOCKS : SoundCategory.PLAYERS, 1.0F, 1.0F);
        if(!world.isClientSide) {
            this.setMoss(false);
            int mossAmount = 1 + this.random.nextInt(2);

            List<ItemStack> items = new ArrayList<>();
            for(int i = 0; i < mossAmount; i++) {
                items.add(new ItemStack(UGItems.DEPTHROCK_PEBBLE.get())); //TODO moss
            }
            return items;
        }
        return Collections.emptyList();
    }
}
