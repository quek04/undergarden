package quek.undergarden.item;

import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGFoods;
import quek.undergarden.registry.UGItemGroups;

public class DroopfruitItem extends BlockNamedItem {

    public DroopfruitItem() {
        super(UGBlocks.DROOPVINE_TOP.get(), (new Item.Properties()).tab(UGItemGroups.GROUP).food(UGFoods.DROOPFRUIT));
    }
}