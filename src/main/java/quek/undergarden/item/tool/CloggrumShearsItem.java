package quek.undergarden.item.tool;

import net.minecraft.item.ShearsItem;
import quek.undergarden.registry.UndergardenItemGroups;

public class CloggrumShearsItem extends ShearsItem {

    public CloggrumShearsItem() {
        super(new Properties()
                .group(UndergardenItemGroups.UNDERGARDEN_GEAR)
        );
    }
}
