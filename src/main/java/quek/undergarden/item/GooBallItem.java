package quek.undergarden.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import quek.undergarden.entity.projectile.GooBallEntity;
import quek.undergarden.registry.UGEffects;
import quek.undergarden.registry.UGFoods;
import quek.undergarden.registry.UGItemGroups;

public class GooBallItem extends Item {

    public GooBallItem() {
        super(new Properties()
                .food(UGFoods.GOO_BALL)
                .maxStackSize(16)
                .group(UGItemGroups.GROUP)
        );
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        ItemStack itemStack = super.onItemUseFinish(stack, worldIn, entityLiving);
        entityLiving.addPotionEffect(new EffectInstance(UGEffects.gooey.get(), 600, 0, false, true));
        return itemStack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (playerIn.isCrouching()) {
            if (playerIn.canEat(this.getFood().canEatWhenFull())) {
                playerIn.setActiveHand(handIn);
                return ActionResult.resultConsume(itemstack);
            } else {
                return ActionResult.resultFail(itemstack);
            }
        }
        else {
            worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            playerIn.getCooldownTracker().setCooldown(this, 20);
            if (!worldIn.isRemote) {
                GooBallEntity gooBall = new GooBallEntity(worldIn, playerIn);
                gooBall.setItem(itemstack);
                gooBall.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                worldIn.addEntity(gooBall);
            }

            playerIn.addStat(Stats.ITEM_USED.get(this));
            if (!playerIn.abilities.isCreativeMode) {
                itemstack.shrink(1);
            }

            return ActionResult.resultSuccess(itemstack);
        }
    }
}
