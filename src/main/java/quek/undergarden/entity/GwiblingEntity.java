package quek.undergarden.entity;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;

import java.util.Random;

public class GwiblingEntity extends AbstractFish {

    public GwiblingEntity(EntityType<? extends AbstractFish> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, GwibEntity.class, 8.0F, 1.6D, 1.4D));
    }

    public static boolean canGwiblingSpawn(EntityType<? extends AbstractFish> type, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random randomIn) {
        return worldIn.getBlockState(pos).is(Blocks.WATER) && worldIn.getBlockState(pos.above()).is(Blocks.WATER) && pos.getY() <= 32;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(UGItems.GWIBLING_BUCKET.get());
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UGSoundEvents.GWIBLING_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return UGSoundEvents.GWIBLING_HURT.get();
    }

    @Override
    protected SoundEvent getFlopSound() {
        return UGSoundEvents.GWIBLING_FLOP.get();
    }
}
