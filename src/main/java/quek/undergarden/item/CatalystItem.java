package quek.undergarden.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import quek.undergarden.block.UndergardenPortalBlock;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGDimensions;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGSoundEvents;

import net.minecraft.item.Item.Properties;

public class CatalystItem extends Item {

    public CatalystItem() {
        super(new Properties()
                .tab(UGItemGroups.GROUP)
                .stacksTo(1)
                .rarity(Rarity.RARE)
        );
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        if(context.getPlayer() != null) {
            if(context.getPlayer().level.dimension() == UGDimensions.UNDERGARDEN_WORLD || context.getPlayer().level.dimension() == World.OVERWORLD) {
                for(Direction direction : Direction.Plane.VERTICAL) {
                    BlockPos framePos = context.getClickedPos().relative(direction);
                    if(((UndergardenPortalBlock) UGBlocks.UNDERGARDEN_PORTAL.get()).trySpawnPortal(context.getLevel(), framePos)) {
                        context.getLevel().playSound(context.getPlayer(), framePos, UGSoundEvents.UNDERGARDEN_PORTAL_ACTIVATE.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
                        return ActionResultType.CONSUME;
                    }
                    else return ActionResultType.FAIL;
                }
            }
        }
        return ActionResultType.FAIL;
    }
}