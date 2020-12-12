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

public class CatalystItem extends Item {

    public CatalystItem() {
        super(new Properties()
                .group(UGItemGroups.GROUP)
                .maxStackSize(1)
                .maxDamage(3)
                .rarity(Rarity.RARE)
        );
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if(context.getPlayer() != null) {
            if(context.getPlayer().world.getDimensionKey() == UGDimensions.UNDERGARDEN_WORLD || context.getPlayer().world.getDimensionKey() == World.OVERWORLD) {
                for(Direction direction : Direction.Plane.VERTICAL) {
                    BlockPos framePos = context.getPos().offset(direction);
                    if(((UndergardenPortalBlock) UGBlocks.UNDERGARDEN_PORTAL.get()).trySpawnPortal(context.getWorld(), framePos)) {
                        context.getWorld().playSound(context.getPlayer(), framePos, UGSoundEvents.UNDERGARDEN_PORTAL_ACTIVATE.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
                        context.getItem().damageItem(1, context.getPlayer(), (playerEntity) -> playerEntity.sendBreakAnimation(context.getHand()));
                        return ActionResultType.CONSUME;
                    }
                    else return ActionResultType.FAIL;
                }
            }
        }
        return ActionResultType.FAIL;
    }

}
