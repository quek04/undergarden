package quek.undergarden.item.tool;

import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import quek.undergarden.registry.UndergardenItemGroups;

public class UndergardenSword extends SwordItem {
    public UndergardenSword(IItemTier tier) {
        super(tier, 3, -2.4f, new Properties()
                .maxStackSize(1)
                .defaultMaxDamage(tier.getMaxUses())
                .group(UndergardenItemGroups.UNDERGARDEN_GEAR)
        );
    }
}
