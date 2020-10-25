package quek.undergarden.item.tool;

import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGShields;

import javax.annotation.Nullable;

public class UGShieldItem extends ShieldItem {

    private final UGShields shieldTiers;

    public UGShieldItem(UGShields tier, Rarity rarity) {
        super(new Properties()
                .maxStackSize(1)
                .maxDamage(tier.getMaxUses())
                .group(UGItemGroups.GROUP)
                .rarity(rarity)
        );
        this.shieldTiers = tier;
        DispenserBlock.registerDispenseBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    public UGShieldItem(UGShields tier) {
        super(new Properties()
                .maxStackSize(1)
                .maxDamage(tier.getMaxUses())
                .group(UGItemGroups.GROUP)
        );
        this.shieldTiers = tier;
        DispenserBlock.registerDispenseBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return this.shieldTiers.getRepairMaterial().test(repair);
    }
}
