package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

import java.util.Random;

public class DroopvineBlock extends GrowingPlantHeadBlock implements Droopvine {

    protected static final VoxelShape SHAPE = Block.box(4.0D, 5.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public DroopvineBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, 0.1D);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(GLOWY, false));
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(Random random) {
        return 1;
    }

    @Override
    protected boolean canGrowInto(BlockState state) {
        return state.isAir();
    }

    @Override
    protected Block getBodyBlock() {
        return UGBlocks.DROOPVINE_PLANT.get();
    }

    @Override
    protected BlockState updateBodyAfterConvertedFromHead(BlockState head, BlockState body) {
        return body.setValue(GLOWY, head.getValue(GLOWY));
    }

    @Override
    protected BlockState getGrowIntoState(BlockState state, Random random) {
        return super.getGrowIntoState(state, random).setValue(GLOWY, random.nextBoolean());
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(UGItems.DROOPFRUIT.get());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        return Droopvine.use(state, level, pos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(GLOWY);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClient) {
        return !state.getValue(GLOWY);
    }

    @Override
    public boolean isBonemealSuccess(Level level, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, Random random, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(GLOWY, true), 2);
    }
}