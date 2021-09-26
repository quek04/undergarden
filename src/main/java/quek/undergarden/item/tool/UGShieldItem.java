package quek.undergarden.item.tool;

import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShieldItem;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGShieldTiers;

import javax.annotation.Nullable;

import net.minecraft.world.item.Item.Properties;

public class UGShieldItem extends ShieldItem {

    private final UGShieldTiers shieldTiers;

    public UGShieldItem(UGShieldTiers tier, Rarity rarity) {
        super(new Properties()
                .stacksTo(1)
                .durability(tier.getMaxUses())
                .tab(UGItemGroups.GROUP)
                .rarity(rarity)
        );
        this.shieldTiers = tier;
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }

    public UGShieldItem(UGShieldTiers tier) {
        super(new Properties()
                .stacksTo(1)
                .durability(tier.getMaxUses())
                .tab(UGItemGroups.GROUP)
        );
        this.shieldTiers = tier;
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return true;
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return this.shieldTiers.getRepairMaterial().test(repair);
    }
}