package quek.undergarden.item.tool;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;
import quek.undergarden.registry.UndergardenItemGroups;

public class UndergardenShovel extends ShovelItem {
    public UndergardenShovel(IItemTier tier) {
        super(tier, 1.5f, -3, new Properties()
                .maxStackSize(1)
                .defaultMaxDamage(tier.getMaxUses())
                .group(UndergardenItemGroups.UNDERGARDEN_GEAR)
        );
    }
}
