package quek.undergarden.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGFoods;
import quek.undergarden.registry.UGItemGroups;

public class DroopvineItem extends BlockItem {

    public DroopvineItem() {
        super(UGBlocks.DROOPVINE_TOP.get(), (new Item.Properties()).group(UGItemGroups.GROUP).food(UGFoods.DROOPVINE));
    }
}