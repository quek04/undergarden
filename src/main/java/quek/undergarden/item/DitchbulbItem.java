package quek.undergarden.item;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItemGroups;

import net.minecraft.world.item.Item.Properties;

public class DitchbulbItem extends ItemNameBlockItem {

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
