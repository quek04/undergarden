package quek.undergarden.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.entity.rotspawn.AbstractRotspawnEntity;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.Random;

public class ScintlingEntity extends AnimalEntity {

    public ScintlingEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
        this.maxUpStep = 1.0F;
        this.xpReward = 0;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, AbstractRotspawnEntity.class, 12.0F, 1.2D, 1.4D));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.5D, Ingredient.of(UGItems.BLISTERBERRY.get()), false));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D);
    }


    public static boolean canScintlingSpawn(EntityType<? extends AnimalEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return worldIn.getBlockState(pos.below()).getBlock() == UGBlocks.DEPTHROCK.get() || worldIn.getBlockState(pos.below()).getBlock() == UGBlocks.ASHEN_DEEPTURF_BLOCK.get();
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) || this.isBaby()) {
            return;
        }

        BlockState blockstate = UGBlocks.GOO.get().defaultBlockState();

        for(int l = 0; l < 4; ++l) {
            int x = MathHelper.floor(this.getX() + (double)((float)(l % 2 * 2 - 1) * 0.25F));
            int y = MathHelper.floor(this.getY());
            int z = MathHelper.floor(this.getZ() + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
            BlockPos blockpos = new BlockPos(x, y, z);
            if (this.level.isEmptyBlock(blockpos) && blockstate.canSurvive(this.level, blockpos)) {
                this.level.setBlockAndUpdate(blockpos, blockstate);
            }
        }
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld serverWorld, AgeableEntity ageableEntity) {
        return UGEntityTypes.SCINTLING.get().create(level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return Ingredient.of(UGItems.BLISTERBERRY.get()).test(stack);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {

    }
}