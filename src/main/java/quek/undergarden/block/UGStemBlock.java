package quek.undergarden.block;

import net.minecraft.block.*;
import net.minecraft.world.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.StemGrownBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class UGStemBlock extends StemBlock {

    private final StemGrownBlock crop;

    public UGStemBlock(StemGrownBlock crop, BlockBehaviour.Properties properties) {
        super(crop, properties);
        this.crop = crop;
    }


    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return state.getBlock() == UGBlocks.DEEPSOIL_FARMLAND.get();
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        return mayPlaceOn(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
        if (!worldIn.isAreaLoaded(pos, 1))
            return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        float f = CropBlock.getGrowthSpeed(this, worldIn, pos);
        if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0)) {
            int i = state.getValue(AGE);
            if (i < 7) {
                worldIn.setBlock(pos, state.setValue(AGE, i + 1), 2);
            } else {
                Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                BlockPos blockpos = pos.relative(direction);
                BlockState blockstate = worldIn.getBlockState(blockpos.below());
                Block block = blockstate.getBlock();
                if (worldIn.isEmptyBlock(blockpos) && (blockstate.canSustainPlant(worldIn, blockpos.below(), Direction.UP, this) || block == UGBlocks.DEEPSOIL_FARMLAND.get() || block == UGBlocks.DEEPSOIL.get() || block == UGBlocks.COARSE_DEEPSOIL.get() || block == UGBlocks.DEEPTURF_BLOCK.get())) {
                    worldIn.setBlockAndUpdate(blockpos, this.crop.defaultBlockState());
                    worldIn.setBlockAndUpdate(pos, this.crop.getAttachedStem().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, direction));
                }
            }
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }
    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    protected Item getSeedItem() {
        return UGItems.GLOOMGOURD_SEEDS.get();
    }
}
