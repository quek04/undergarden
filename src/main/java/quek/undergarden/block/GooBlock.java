package quek.undergarden.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import quek.undergarden.entity.ScintlingEntity;
import quek.undergarden.registry.UndergardenEffects;
import quek.undergarden.registry.UndergardenItems;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class GooBlock extends Block {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_15;
    protected static final VoxelShape SHAPE = makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public GooBlock() {
        super(Properties.create(Material.SNOW)
                .hardnessAndResistance(1F, 0F)
                .doesNotBlockMovement()
                .tickRandomly()
                .sound(SoundType.SLIME)
                .notSolid()
                .harvestTool(ToolType.SHOVEL)
        );
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0));
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if(entityIn instanceof PlayerEntity && ((PlayerEntity) entityIn).inventory.armorInventory.get(0).getItem() == UndergardenItems.cloggrum_boots.get() && !((PlayerEntity) entityIn).isPotionActive(UndergardenEffects.gooey.get())) {

        }
        else if(!(entityIn instanceof ScintlingEntity) && entityIn.isOnGround()) {
            entityIn.setMotionMultiplier(state, new Vector3d(0.45D, 0.45D, 0.45D));
        }

    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return worldIn.getBlockState(blockpos).isSolidSide(worldIn, blockpos, Direction.UP);
    }

    @Override
    public boolean isTransparent(BlockState state) {
        return true;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (!state.isValidPosition(worldIn, pos)) {
            worldIn.removeBlock(pos, false);
        }
        if(rand.nextFloat() < 100F + (float)state.get(AGE) * 0.50F) {
            worldIn.removeBlock(pos, false);
        }
    }
}
