package quek.undergarden.block;

import net.minecraft.block.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

public class GloomgourdBlock extends StemGrownBlock {

    public GloomgourdBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getItemInHand(handIn);
        if (itemstack.getItem() == Items.SHEARS) {
            if (!worldIn.isClientSide) {
                Direction direction = hit.getDirection();
                Direction direction1 = direction.getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : direction;
                worldIn.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                worldIn.setBlock(pos, UGBlocks.CARVED_GLOOMGOURD.get().defaultBlockState().setValue(CarvedGloomgourdBlock.FACING, direction1), 11);
                ItemEntity itementity = new ItemEntity(worldIn, (double)pos.getX() + 0.5D + (double)direction1.getStepX() * 0.65D, (double)pos.getY() + 0.1D, (double)pos.getZ() + 0.5D + (double)direction1.getStepZ() * 0.65D, new ItemStack(UGItems.GLOOMGOURD_SEEDS.get(), 4));
                itementity.setDeltaMovement(0.05D * (double)direction1.getStepX() + worldIn.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double)direction1.getStepZ() + worldIn.random.nextDouble() * 0.02D);
                worldIn.addFreshEntity(itementity);
                itemstack.hurtAndBreak(1, player, (playerIn) -> playerIn.broadcastBreakEvent(handIn));
            }

            return ActionResultType.SUCCESS;
        } else {
            return super.use(state, worldIn, pos, player, handIn, hit);
        }
    }

    @Override
    public StemBlock getStem() {
        return UGBlocks.GLOOMGOURD_STEM.get();
    }

    @Override
    public AttachedStemBlock getAttachedStem() {
        return UGBlocks.GLOOMGOURD_STEM_ATTACHED.get();
    }
}