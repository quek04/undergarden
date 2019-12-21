package quek.undergarden.item.tool;

import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraftforge.common.ToolType;
import quek.undergarden.registry.UndergardenItemGroups;

public class UndergardenPickaxe extends PickaxeItem {
    public UndergardenPickaxe(IItemTier tier) {
        super(tier, 1, -2.8f, new Properties()
                .maxStackSize(1)
                .defaultMaxDamage(tier.getMaxUses())
                .group(UndergardenItemGroups.UNDERGARDEN_GEAR)
        );
    }
}
