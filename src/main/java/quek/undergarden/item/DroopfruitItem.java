package quek.undergarden.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGFoods;
import quek.undergarden.registry.UGItemGroups;

public class DroopfruitItem extends ItemNameBlockItem {

    public DroopfruitItem() {
        super(UGBlocks.DROOPVINE.get(), (new Item.Properties()).tab(UGItemGroups.GROUP).food(UGFoods.DROOPFRUIT));
    }
}