package quek.undergarden.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;

public class Gwibling extends AbstractFish {

    public Gwibling(EntityType<? extends AbstractFish> type, Level level) {
        super(type, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    public static boolean canGwiblingSpawn(EntityType<? extends AbstractFish> type, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos).is(Blocks.WATER) && level.getBlockState(pos.above()).is(Blocks.WATER);
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