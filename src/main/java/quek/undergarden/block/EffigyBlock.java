package quek.undergarden.block;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.*;
import quek.undergarden.registry.UGDimensions;
import quek.undergarden.registry.UGItems;

import java.util.Optional;

public class EffigyBlock extends RespawnAnchorBlock {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    private static final ImmutableList<Vector3i> field_242676_b = ImmutableList.of(new Vector3i(0, 0, -1), new Vector3i(-1, 0, 0), new Vector3i(0, 0, 1), new Vector3i(1, 0, 0), new Vector3i(-1, 0, -1), new Vector3i(1, 0, -1), new Vector3i(-1, 0, 1), new Vector3i(1, 0, 1));
    private static final ImmutableList<Vector3i> field_242677_c = (new ImmutableList.Builder<Vector3i>()).addAll(field_242676_b).addAll(field_242676_b.stream().map(Vector3i::down).iterator()).addAll(field_242676_b.stream().map(Vector3i::up).iterator()).add(new Vector3i(0, 1, 0)).build();

    public EffigyBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(CHARGES, 1));
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().rotateY());
    }
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(CHARGES);
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getHeldItem(handIn);
        if (handIn == Hand.MAIN_HAND && !(itemstack.getItem() == UGItems.REGALIUM_INGOT.get()) && player.getHeldItem(Hand.OFF_HAND).getItem() == UGItems.REGALIUM_INGOT.get()) {
            return ActionResultType.PASS;
        } else if (itemstack.getItem() == UGItems.REGALIUM_INGOT.get() && state.get(CHARGES)==0) {
            worldIn.setBlockState(pos, state.with(CHARGES, 1));
            if (!player.abilities.isCreativeMode) {
                itemstack.shrink(1);
            }

            return ActionResultType.func_233537_a_(worldIn.isRemote);
        } else if (!(worldIn.getDimensionKey() == UGDimensions.UNDERGARDEN_WORLD)) {
            if (!worldIn.isRemote) {
                this.triggerExplosion(state, worldIn, pos);
            }

            return ActionResultType.func_233537_a_(worldIn.isRemote);
        } else {
            if (!worldIn.isRemote) {
                ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)player;
                if (serverplayerentity.func_241141_L_() != worldIn.getDimensionKey() || !serverplayerentity.func_241140_K_().equals(pos)) {
                    serverplayerentity.func_242111_a(worldIn.getDimensionKey(), pos, 0.0F, false, true);
                    worldIn.playSound((PlayerEntity)null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    return ActionResultType.SUCCESS;
                }
            }

            return ActionResultType.CONSUME;
        }
    }

    private void triggerExplosion(BlockState state, World world, final BlockPos pos2) {
        world.removeBlock(pos2, false);
        boolean flag = Direction.Plane.HORIZONTAL.getDirectionValues().map(pos2::offset).anyMatch((posIn) -> {
            return isNearWater(posIn, world);
        });
        final boolean flag1 = flag || world.getFluidState(pos2.up()).isTagged(FluidTags.WATER);
        ExplosionContext explosioncontext = new ExplosionContext() {
            public Optional<Float> getExplosionResistance(Explosion explosion, IBlockReader reader, BlockPos pos, BlockState state, FluidState fluid) {
                return pos.equals(pos2) && flag1 ? Optional.of(Blocks.WATER.getExplosionResistance()) : super.getExplosionResistance(explosion, reader, pos, state, fluid);
            }
        };
        world.createExplosion((Entity)null, DamageSource.func_233546_a_(), explosioncontext, (double)pos2.getX() + 0.5D, (double)pos2.getY() + 0.5D, (double)pos2.getZ() + 0.5D, 5.0F, false, Explosion.Mode.DESTROY);
    }

    private static boolean isNearWater(BlockPos pos, World world) {
        FluidState fluidstate = world.getFluidState(pos);
        if (!fluidstate.isTagged(FluidTags.WATER)) {
            return false;
        } else if (fluidstate.isSource()) {
            return true;
        } else {
            float f = (float)fluidstate.getLevel();
            if (f < 2.0F) {
                return false;
            } else {
                FluidState fluidstate1 = world.getFluidState(pos.down());
                return !fluidstate1.isTagged(FluidTags.WATER);
            }
        }
    }

    public static Optional<Vector3d> findRespawnPoint(EntityType<?> entity, ICollisionReader reader, BlockPos pos) {
        Optional<Vector3d> optional = func_242678_a(entity, reader, pos, true);
        return optional.isPresent() ? optional : func_242678_a(entity, reader, pos, false);
    }

    private static Optional<Vector3d> func_242678_a(EntityType<?> type, ICollisionReader collisionReader, BlockPos pos, boolean checkCanSpawn) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(Vector3i vector3i : field_242677_c) {
            blockpos$mutable.setPos(pos).func_243531_h(vector3i);
            Vector3d vector3d = TransportationHelper.func_242379_a(type, collisionReader, blockpos$mutable, checkCanSpawn);
            if (vector3d != null) {
                return Optional.of(vector3d);
            }
        }

        return Optional.empty();
    }
}
