package quek.undergarden.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.entity.rotspawn.AbstractRotspawnEntity;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSounds;

import javax.annotation.Nullable;
import java.util.Random;

public class DwellerEntity extends AnimalEntity {

    public DwellerEntity(EntityType<? extends DwellerEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 2.5D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, AbstractRotspawnEntity.class, 12.0F, 2.0D, 2.5D));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.25D, Ingredient.fromItems(UGItems.underbeans.get()), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 3.0F));
        this.goalSelector.addGoal(6, new LookAtGoal(this, DwellerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return AnimalEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 15.0D) //hp
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.15D); //speed
    }

    public static boolean canDwellerSpawn(EntityType<? extends AnimalEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return worldIn.getBlockState(pos.down()).getBlock() == UGBlocks.deepturf_block.get() || worldIn.getBlockState(pos.down()).getBlock() == UGBlocks.ashen_deepturf.get();
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld serverWorld, AgeableEntity ageableEntity) {
        return UGEntityTypes.DWELLER.get().create(world);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return Ingredient.fromItems(UGItems.underbeans.get()).test(stack);
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? sizeIn.height * 0.95F : 1.3F;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return UGSounds.DWELLER_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UGSounds.DWELLER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UGSounds.DWELLER_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 0.5F);
    }

}
