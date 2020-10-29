package quek.undergarden.item;

import net.minecraft.item.ArrowItem;
import quek.undergarden.registry.UGItemGroups;

import net.minecraft.item.Item.Properties;

public class DepthrockPebbleItem extends ArrowItem {

    public DepthrockPebbleItem() {
        super(new Properties()
                .group(UGItemGroups.GROUP)
        );
    }

}
