package quek.undergarden.item;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import quek.undergarden.block.CatalystSlotBlock;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenItemGroups;

public class CatalystItem extends Item {

    public CatalystItem() {
        super(new Properties()
                .group(UndergardenItemGroups.UNDERGARDEN_ITEMS)
        );
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState blockstate = world.getBlockState(blockpos);
        if (blockstate.getBlock() == UndergardenBlocks.catalyst_slot.get() && !blockstate.get(CatalystSlotBlock.CATALYST)) {
            if (!world.isRemote) {
                BlockState slotActivated = blockstate.with(CatalystSlotBlock.CATALYST, Boolean.TRUE);
                world.setBlockState(blockpos, slotActivated, 2);
                context.getItem().shrink(1);
                world.playEvent(1503, blockpos, 0);

            }
            return ActionResultType.SUCCESS;
        } else {
            return ActionResultType.PASS;
        }
    }

}
