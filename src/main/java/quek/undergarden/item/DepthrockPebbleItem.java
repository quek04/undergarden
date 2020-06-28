package quek.undergarden.item;

import net.minecraft.item.ArrowItem;
import quek.undergarden.registry.UndergardenItemGroups;

public class DepthrockPebbleItem extends ArrowItem {

    public DepthrockPebbleItem() {
        super(new Properties()
                .group(UndergardenItemGroups.UNDERGARDEN_ITEMS)
        );
    }

}
