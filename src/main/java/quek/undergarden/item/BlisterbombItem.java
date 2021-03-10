package quek.undergarden.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import quek.undergarden.entity.projectile.BlisterbombEntity;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGSoundEvents;

import net.minecraft.item.Item.Properties;

public class BlisterbombItem extends Item {

    public BlisterbombItem() {
        super(new Properties()
                .stacksTo(8)
                .tab(UGItemGroups.GROUP)
        );
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), UGSoundEvents.BLISTERBOMB_THROW.get(), SoundCategory.NEUTRAL, 0.5F, 1F);
        playerIn.getCooldowns().addCooldown(this, 50);
        if (!worldIn.isClientSide) {
            BlisterbombEntity blisterbomb = new BlisterbombEntity(worldIn, playerIn);
            blisterbomb.setItem(itemstack);
            blisterbomb.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1.0F);
            worldIn.addFreshEntity(blisterbomb);
        }

        playerIn.awardStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.instabuild) {
            itemstack.shrink(1);
        }

        return ActionResult.success(itemstack);
    }

}