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
import quek.undergarden.entity.projectile.BlisterbombEntity;
import quek.undergarden.registry.UndergardenItemGroups;
import quek.undergarden.registry.UndergardenSoundEvents;

public class BlisterbombItem extends Item {

    public BlisterbombItem() {
        super(new Properties()
                .maxStackSize(8)
                .group(UndergardenItemGroups.UNDERGARDEN_GEAR)
        );
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), UndergardenSoundEvents.BLISTERBOMB_THROW, SoundCategory.NEUTRAL, 0.5F, 1F);
        playerIn.getCooldownTracker().setCooldown(this, 50);
        if (!worldIn.isRemote) {
            BlisterbombEntity blisterbomb = new BlisterbombEntity(worldIn, playerIn);
            blisterbomb.setItem(itemstack);
            blisterbomb.shoot(playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.addEntity(blisterbomb);
        }

        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        return ActionResult.resultSuccess(itemstack);
    }

}
