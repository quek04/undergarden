package quek.undergarden.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import quek.undergarden.block.UndergardenPortalFrameBlock;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenItemGroups;

public class UndergardenPortalCatalystItem extends Item {

    public UndergardenPortalCatalystItem() {
        super(new Properties()
                .group(UndergardenItemGroups.UNDERGARDEN_ITEMS)
        );
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState blockstate = world.getBlockState(blockpos);
        if (blockstate.getBlock() == UndergardenBlocks.undergarden_portal_frame.get() && !blockstate.get(UndergardenPortalFrameBlock.CATALYST)) {
            if (world.isRemote) {
                return ActionResultType.SUCCESS;
            } else {
                BlockState blockstate1 = blockstate.with(UndergardenPortalFrameBlock.CATALYST, Boolean.valueOf(true));
                Block.nudgeEntitiesWithNewState(blockstate, blockstate1, world, blockpos);
                world.setBlockState(blockpos, blockstate1, 2);
                world.updateComparatorOutputLevel(blockpos, UndergardenBlocks.undergarden_portal_frame.get());
                context.getItem().shrink(1);
                world.playEvent(1503, blockpos, 0);
                BlockPattern.PatternHelper blockpattern$patternhelper = UndergardenPortalFrameBlock.getOrCreatePortalShape().match(world, blockpos);
                if (blockpattern$patternhelper != null) {
                    BlockPos blockpos1 = blockpattern$patternhelper.getFrontTopLeft().add(-3, 0, -3);

                    for(int i = 0; i < 3; ++i) {
                        for(int j = 0; j < 3; ++j) {
                            world.setBlockState(blockpos1.add(i, 0, j), UndergardenBlocks.undergarden_portal.get().getDefaultState(), 2);
                        }
                    }

                    //world.playBroadcastSound(1038, blockpos1.add(1, 0, 1), 0);
                }

                return ActionResultType.SUCCESS;
            }
        } else {
            return ActionResultType.PASS;
        }
    }
}
