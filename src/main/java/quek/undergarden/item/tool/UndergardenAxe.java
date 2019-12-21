package quek.undergarden.item.tool;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import quek.undergarden.registry.UndergardenItemGroups;

public class UndergardenAxe extends AxeItem {
    public UndergardenAxe(IItemTier tier) {
        super(tier, 6, -3.2f, new Properties()
                .maxStackSize(1)
                .defaultMaxDamage(tier.getMaxUses())
                .group(UndergardenItemGroups.UNDERGARDEN_GEAR)
        );
    }
}
