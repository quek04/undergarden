package quek.undergarden.item.tool;

import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGShields;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class UndergardenShieldItem extends ShieldItem {

    private final UGShields shieldTiers;

    public UndergardenShieldItem(UGShields tier, Rarity rarity) {
        super(new Properties()
                .maxStackSize(1)
                .maxDamage(tier.getMaxUses())
                .group(UGItemGroups.GROUP)
                .rarity(rarity)
        );
        this.shieldTiers = tier;
        DispenserBlock.registerDispenseBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    public UndergardenShieldItem(UGShields tier) {
        super(new Properties()
                .maxStackSize(1)
                .maxDamage(tier.getMaxUses())
                .group(UGItemGroups.GROUP)
        );
        this.shieldTiers = tier;
        DispenserBlock.registerDispenseBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return ActionResult.resultConsume(itemstack);
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return this.shieldTiers.getRepairMaterial().test(repair) || super.getIsRepairable(toRepair, repair);
    }
}
