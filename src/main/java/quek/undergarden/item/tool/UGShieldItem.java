package quek.undergarden.item.tool;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import quek.undergarden.registry.UGCreativeModeTabs;
import quek.undergarden.registry.UGShieldTiers;

public class UGShieldItem extends ShieldItem {

    private final UGShieldTiers shieldTiers;

    public UGShieldItem(UGShieldTiers tier, Rarity rarity) {
        super(new Properties()
                .stacksTo(1)
                .durability(tier.getDurability())
                .tab(UGCreativeModeTabs.GROUP)
                .rarity(rarity)
        );
        this.shieldTiers = tier;
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }

    public UGShieldItem(UGShieldTiers tier) {
        super(new Properties()
                .stacksTo(1)
                .durability(tier.getDurability())
                .tab(UGCreativeModeTabs.GROUP)
        );
        this.shieldTiers = tier;
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return toolAction == ToolActions.SHIELD_BLOCK;
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return this.shieldTiers.getRepairIngredient().test(repair);
    }
}