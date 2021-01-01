package quek.undergarden.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import quek.undergarden.entity.projectile.MinionProjectileEntity;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGTags;

public class MinionEntity extends GolemEntity implements IRangedAttackMob {

    public MinionEntity(EntityType<? extends GolemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 0.5D, 5, 10.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, MobEntity.class, 10, true, false, (entity) ->
                entity instanceof IMob || entity.getType().isContained(UGTags.Entities.ROTSPAWN) || entity.getType().isContained(UGTags.Entities.CAVERN_CREATURE))
        );
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return GolemEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.ARMOR, 10.0D)
                .createMutableAttribute(Attributes.ARMOR_TOUGHNESS, 5.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2D);
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        MinionProjectileEntity projectile = new MinionProjectileEntity(this.world, this);
        double xDistance = target.getPosX() - this.getPosX();
        double yDistance = target.getPosYHeight(0.3333333333333333D) - projectile.getPosY();
        double zDistance = target.getPosZ() - this.getPosZ();
        double yMath = MathHelper.sqrt((xDistance * xDistance) + (zDistance * zDistance));
        projectile.shoot(xDistance, yDistance + yMath * 0.1D, zDistance, 1.6F, 1.0F);
        this.playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1.0F, 0.5F);
        this.world.addEntity(projectile);
    }

    @Override
    protected ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (item != UGItems.FORGOTTEN_NUGGET.get()) {
            return ActionResultType.PASS;
        } else {
            float health = this.getHealth();
            this.heal(5.0F);
            if (this.getHealth() == health) {
                return ActionResultType.PASS;
            } else {
                this.playSound(SoundEvents.ENTITY_IRON_GOLEM_REPAIR, 1.0F, 2.0F);
                if (!player.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }

                return ActionResultType.func_233537_a_(this.world.isRemote);
            }
        }
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }
}