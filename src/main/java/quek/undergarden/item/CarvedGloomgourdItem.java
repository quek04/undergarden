package quek.undergarden.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItemGroups;

public class CarvedGloomgourdItem extends BlockItem {

    public CarvedGloomgourdItem() {
        super(UGBlocks.carved_gloomgourd.get(), (new Properties()
                .group(UGItemGroups.GROUP)
        ));
    }

    @Override
    public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
        return EquipmentSlotType.HEAD;
    }
}
