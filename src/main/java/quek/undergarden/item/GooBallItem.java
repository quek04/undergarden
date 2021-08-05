package quek.undergarden.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import quek.undergarden.entity.projectile.GooBallEntity;
import quek.undergarden.registry.UGFoods;
import quek.undergarden.registry.UGItemGroups;

import net.minecraft.item.Item.Properties;

public class GooBallItem extends Item {

    public GooBallItem() {
        super(new Properties()
                .food(UGFoods.GOO_BALL)
                .stacksTo(16)
                .tab(UGItemGroups.GROUP)
        );
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (playerIn.isCrouching()) {
            playerIn.startUsingItem(handIn);
            return ActionResult.consume(itemstack);
        }
        else {
            worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            playerIn.getCooldowns().addCooldown(this, 20);
            if (!worldIn.isClientSide) {
                GooBallEntity gooBall = new GooBallEntity(worldIn, playerIn);
                gooBall.setItem(itemstack);
                gooBall.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1.0F);
                worldIn.addFreshEntity(gooBall);
            }

            playerIn.awardStat(Stats.ITEM_USED.get(this));
            if (!playerIn.abilities.instabuild) {
                itemstack.shrink(1);
            }

            return ActionResult.success(itemstack);
        }
    }
}