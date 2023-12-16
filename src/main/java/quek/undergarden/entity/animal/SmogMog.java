package quek.undergarden.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.ForgeMod;
import quek.undergarden.registry.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmogMog extends Mog {
    public SmogMog(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.1D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.9D)
                .add(ForgeMod.STEP_HEIGHT.get(), 1.0F);
    }

    public static boolean checkSmogMogSpawnRules(EntityType<? extends Animal> entity, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos.below()).is(UGTags.Blocks.SMOG_MOG_SPAWNABLE_ON);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return UGSoundEvents.SMOG_MOG_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return UGSoundEvents.SMOG_MOG_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UGSoundEvents.SMOG_MOG_DEATH.get();
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return UGEntityTypes.SMOG_MOG.get().create(level);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 0.4F;
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nullable Player player, @Nonnull ItemStack stack, Level level, BlockPos pos, int fortune) {
        level.playSound(null, this, SoundEvents.SHEEP_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
        if (!level.isClientSide()) {
            this.setMoss(false);
            int mossAmount = 1 + this.getRandom().nextInt(2);
            if (fortune > 0) {
                mossAmount += this.getRandom().nextInt(fortune);
            }

            List<ItemStack> items = new ArrayList<>();
            for (int i = 0; i < mossAmount; i++) {
                items.add(new ItemStack(UGItems.BLUE_MOGMOSS.get()));
            }
            return items;
        }
        return Collections.emptyList();
    }

    @Override
    public void aiStep() {
        if (this.level().isClientSide()) {
            double x = this.getX();
            double y = this.getY() + (this.isBaby() ? 1.0F : 2.0F);
            double z = this.getZ();
            if (this.isAlive()) {
                level().addParticle(UGParticleTypes.SMOG.get(), x, y, z, 0.0D, 0.05D, 0.0D);
            }
        }
        super.aiStep();
    }
}
