package quek.undergarden.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import quek.undergarden.UndergardenMod;
import quek.undergarden.registry.UndergardenBlocks;

public class CatalystSlotBlock extends Block {

    public static final BooleanProperty CATALYST = BlockStateProperties.EYE;

    public CatalystSlotBlock() {
        super(Properties.create(Material.IRON)
                .hardnessAndResistance(5.0F, 12F)
                .sound(SoundType.METAL)
                .harvestLevel(3)
                .tickRandomly()
        );
        this.setDefaultState(this.stateContainer.getBaseState().with(CATALYST, Boolean.FALSE));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(CATALYST);
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (oldState.getBlock() != state.getBlock()) {
            if (worldIn.dimension.getType() == DimensionType.OVERWORLD || worldIn.dimension.getType() == UndergardenMod.undergarden_dimension || ((UndergardenPortalBlock) UndergardenBlocks.undergarden_portal.get()).trySpawnPortal(worldIn, pos)) {
                if (!state.isValidPosition(worldIn, pos)) {
                    worldIn.removeBlock(pos, false);
                }
                else {
                    worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn) + worldIn.rand.nextInt(10));
                }
            }
        }
    }
}
