package quek.undergarden.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.entity.rotspawn.RotspawnMonster;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nullable;
import java.util.Random;

public class Scintling extends Animal {

    public Scintling(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.maxUpStep = 1.0F;
        this.xpReward = 0;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, RotspawnMonster.class, 12.0F, 1.2D, 1.4D));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.5D, Ingredient.of(UGItems.BLISTERBERRY.get()), false));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D);
    }

    public static boolean canScintlingSpawn(EntityType<? extends Animal> type, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos.below()).getBlock() == UGBlocks.DEPTHROCK.get() || level.getBlockState(pos.below()).getBlock() == UGBlocks.ASHEN_DEEPTURF_BLOCK.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UGSoundEvents.SCINTLING_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UGSoundEvents.SCINTLING_DEATH.get();
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) || this.isBaby()) {
            return;
        }

        BlockState goo = UGBlocks.GOO.get().defaultBlockState();

        for(int l = 0; l < 4; ++l) {
            int x = Mth.floor(this.getX() + (double)((float)(l % 2 * 2 - 1) * 0.25F));
            int y = Mth.floor(this.getY());
            int z = Mth.floor(this.getZ() + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
            BlockPos blockpos = new BlockPos(x, y, z);
            if (this.level.isEmptyBlock(blockpos) && goo.canSurvive(this.level, blockpos)) {
                this.level.setBlockAndUpdate(blockpos, goo);
            }
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return UGEntityTypes.SCINTLING.get().create(this.level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return Ingredient.of(UGItems.BLISTERBERRY.get()).test(stack);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) { }
}