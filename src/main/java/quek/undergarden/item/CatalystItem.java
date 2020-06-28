package quek.undergarden.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import quek.undergarden.block.UndergardenPortalBlock;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenItemGroups;

public class CatalystItem extends Item {

    public CatalystItem() {
        super(new Properties()
                .group(UndergardenItemGroups.UNDERGARDEN_GEAR)
        );
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if(((UndergardenPortalBlock)UndergardenBlocks.undergarden_portal.get()).trySpawnPortal(context.getWorld(), context.getPos())) {
            return ActionResultType.CONSUME;
        }
        else return ActionResultType.FAIL;
    }

}
