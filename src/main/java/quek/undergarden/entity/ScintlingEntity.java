package quek.undergarden.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.registry.UGBlocks;

import javax.annotation.Nullable;
import java.util.Random;

public class ScintlingEntity extends AnimalEntity {

    public ScintlingEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
        this.stepHeight = 1.0F;
        this.experienceValue = 0;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return AnimalEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 2.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.20D);
    }


    public static boolean canScintlingSpawn(EntityType<? extends AnimalEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return worldIn.getBlockState(pos.down()).getBlock() == UGBlocks.depthrock.get() || worldIn.getBlockState(pos.down()).getBlock() == UGBlocks.ashen_deepturf_block.get();
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this)) {
            return;
        }

        BlockState blockstate = UGBlocks.goo.get().getDefaultState();

        for(int l = 0; l < 4; ++l) {
            int x = MathHelper.floor(this.getPosX() + (double)((float)(l % 2 * 2 - 1) * 0.25F));
            int y = MathHelper.floor(this.getPosY());
            int z = MathHelper.floor(this.getPosZ() + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
            BlockPos blockpos = new BlockPos(x, y, z);
            if (this.world.isAirBlock(blockpos) && blockstate.isValidPosition(this.world, blockpos)) {
                this.world.setBlockState(blockpos, blockstate);
            }
        }
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld serverWorld, AgeableEntity ageableEntity) {
        return null;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {

    }
}
