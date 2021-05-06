package quek.undergarden.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import quek.undergarden.registry.UGItems;

import java.util.Random;

public class GwiblingEntity extends AbstractFishEntity {

    public GwiblingEntity(EntityType<? extends AbstractFishEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, GwibEntity.class, 8.0F, 1.6D, 1.4D));
    }

    public static boolean canGwiblingSpawn(EntityType<? extends AbstractFishEntity> type, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return worldIn.getBlockState(pos).is(Blocks.WATER) && worldIn.getBlockState(pos.above()).is(Blocks.WATER) && pos.getY() <= 32;
    }

    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(UGItems.GWIBLING_BUCKET.get());
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.COD_HURT;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }
}
