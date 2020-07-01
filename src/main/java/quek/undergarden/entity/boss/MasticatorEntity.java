package quek.undergarden.entity.boss;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

import javax.annotation.Nullable;

public class MasticatorEntity extends MonsterEntity {

    private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);

    public MasticatorEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.stepHeight = 1.0F;
        this.experienceValue = 25;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.4D));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_233666_p_()
                .func_233815_a_(Attributes.field_233818_a_, 150.0D) //hp
                .func_233815_a_(Attributes.field_233826_i_, 10.0D) //armor
                .func_233815_a_(Attributes.field_233827_j_, 5.0D) //armor toughness
                .func_233815_a_(Attributes.field_233823_f_, 10.0D) //attack damage
                .func_233815_a_(Attributes.field_233824_g_, 2.0D) //attack knockback
                .func_233815_a_(Attributes.field_233821_d_, 0.40D) //speed
                .func_233815_a_(Attributes.field_233820_c_, 1.0D) //knockback resist
                .func_233815_a_(Attributes.field_233819_b_, 64.0D); //follow range
    }

    @Override
    public void setCustomName(@Nullable ITextComponent name) {
        super.setCustomName(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (this.isAlive()) {
            double d0 = this.getAttackTarget() != null ? 0.35D : 0.3D;
            double d1 = this.getAttribute(Attributes.field_233821_d_).getBaseValue();
            this.getAttribute(Attributes.field_233821_d_).setBaseValue(MathHelper.lerp(0.1D, d1, d0));

            if (this.collidedHorizontally && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this)) {
                boolean flag = false;
                AxisAlignedBB axisalignedbb = this.getBoundingBox().grow(0.2D);

                for(BlockPos blockpos : BlockPos.getAllInBoxMutable(MathHelper.floor(axisalignedbb.minX), MathHelper.floor(axisalignedbb.minY), MathHelper.floor(axisalignedbb.minZ), MathHelper.floor(axisalignedbb.maxX), MathHelper.floor(axisalignedbb.maxY), MathHelper.floor(axisalignedbb.maxZ))) {
                    BlockState blockstate = this.world.getBlockState(blockpos);
                    Block block = blockstate.getBlock();
                    if (block instanceof LeavesBlock) {
                        flag = this.world.destroyBlock(blockpos, true, this) || flag;
                    }
                }

                if (!flag && this.onGround) {
                    this.jump();
                }
            }

        }
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    protected void constructKnockBackVector(LivingEntity entityIn) {
        this.launch(entityIn);
        entityIn.velocityChanged = true;
    }

    private void launch(Entity p_213688_1_) {
        double d0 = p_213688_1_.getPosX() - this.getPosX();
        double d1 = p_213688_1_.getPosZ() - this.getPosZ();
        double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
        p_213688_1_.addVelocity(d0 / d2 * 4.0D, 0.2D, d1 / d2 * 4.0D);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_RAVAGER_STEP, 0.20F, 0.5F);
    }
}
