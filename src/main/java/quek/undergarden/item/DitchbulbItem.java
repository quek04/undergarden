package quek.undergarden.item;

import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.ItemStack;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItemGroups;

import net.minecraft.item.Item.Properties;

public class DitchbulbItem extends BlockNamedItem {

    public DitchbulbItem() {
        super(UGBlocks.DITCHBULB_PLANT.get(), new Properties()
                .tab(UGItemGroups.GROUP)
        );
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 800;
    }
}
