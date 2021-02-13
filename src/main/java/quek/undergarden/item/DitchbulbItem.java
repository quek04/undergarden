package quek.undergarden.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import quek.undergarden.registry.UGItemGroups;

public class DitchbulbItem extends Item {

    public DitchbulbItem() {
        super(new Properties()
                .group(UGItemGroups.GROUP)
        );
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 800;
    }
}
