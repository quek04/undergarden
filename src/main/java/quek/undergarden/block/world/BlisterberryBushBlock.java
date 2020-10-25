package quek.undergarden.block.world;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

import java.util.Random;

public class BlisterberryBushBlock extends Block implements IGrowable, IPlantable {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;

    public BlisterberryBushBlock() {
        super(Properties.create(Material.PLANTS)
                .hardnessAndResistance(0F)
                .sound(SoundType.PLANT)
                .tickRandomly()
                .doesNotBlockMovement()
                .setLightLevel((state) -> 6)
        );
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0));
    }

    public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return block == UGBlocks.ashen_deepturf_block.get();
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    @Override
    public BlockState getPlant(IBlockReader world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() != this) return getDefaultState();
        return state;
    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(UGItems.blisterberry.get());
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        int i = state.get(AGE);
        if (i < 3 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(5) == 0)) {
            worldIn.setBlockState(pos, state.with(AGE, i + 1), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }

    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn.getType() != UGEntityTypes.scintling) {
            entityIn.setMotionMultiplier(state, new Vector3d(0.8F, 0.75D, 0.8F));
            if (!worldIn.isRemote && state.get(AGE) > 0 && (entityIn.lastTickPosX != entityIn.getPosX() || entityIn.lastTickPosZ != entityIn.getPosZ())) {
                double d0 = Math.abs(entityIn.getPosX() - entityIn.lastTickPosX);
                double d1 = Math.abs(entityIn.getPosZ() - entityIn.lastTickPosZ);
                if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
                    entityIn.attackEntityFrom(DamageSource.GENERIC, 2.0F);
                }
            }

        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        int age = state.get(AGE);
        boolean flag = age == 3;
        if (!flag && player.getHeldItem(handIn).getItem() == Items.BONE_MEAL) {
            return ActionResultType.PASS;
        } else if (age > 1) {
            int random = 1 + worldIn.rand.nextInt(2);
            int random2 = worldIn.rand.nextInt(2);
            spawnAsEntity(worldIn, pos, new ItemStack(UGItems.blisterberry.get(), random + (flag ? 1 : 0)));
            spawnAsEntity(worldIn, pos, new ItemStack(UGItems.rotten_blisterberry.get(), random2 + (flag ? 1 : 0)));
            worldIn.playSound(null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
            worldIn.setBlockState(pos, state.with(AGE, 1), 2);
            return ActionResultType.SUCCESS;
        } else {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return state.get(AGE) < 3;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        int i = Math.min(3, state.get(AGE) + 1);
        worldIn.setBlockState(pos, state.with(AGE, i), 2);
    }
}
