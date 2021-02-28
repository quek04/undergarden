package quek.undergarden.item;

import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.ItemStack;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItemGroups;

public class DitchbulbItem extends BlockNamedItem {

    public DitchbulbItem() {
        super(UGBlocks.DITCHBULB_PLANT.get(), new Properties()
                .group(UGItemGroups.GROUP)
        );
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 800;
    }
}
