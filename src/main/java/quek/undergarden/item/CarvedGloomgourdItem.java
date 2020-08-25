package quek.undergarden.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenItemGroups;

public class CarvedGloomgourdItem extends BlockItem {

    public CarvedGloomgourdItem() {
        super(UndergardenBlocks.carved_gloomgourd.get(), (new Properties()
                .group(UndergardenItemGroups.GROUP)
        ));
    }

    @Override
    public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
        return EquipmentSlotType.HEAD;
    }
}
