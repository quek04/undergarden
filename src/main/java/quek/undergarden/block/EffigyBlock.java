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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGDimensions;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;

import java.util.Optional;
import java.util.Random;

public class EffigyBlock extends RespawnAnchorBlock {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    private static final ImmutableList<Vector3i> RESPAWN_HORIZONTAL_OFFSETS = ImmutableList.of(new Vector3i(0, 0, -1), new Vector3i(-1, 0, 0), new Vector3i(0, 0, 1), new Vector3i(1, 0, 0), new Vector3i(-1, 0, -1), new Vector3i(1, 0, -1), new Vector3i(-1, 0, 1), new Vector3i(1, 0, 1));
    private static final ImmutableList<Vector3i> RESPAWN_OFFSETS = (new ImmutableList.Builder<Vector3i>()).addAll(RESPAWN_HORIZONTAL_OFFSETS).addAll(RESPAWN_HORIZONTAL_OFFSETS.stream().map(Vector3i::below).iterator()).addAll(RESPAWN_HORIZONTAL_OFFSETS.stream().map(Vector3i::above).iterator()).add(new Vector3i(0, 1, 0)).build();

    public EffigyBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(CHARGE, 0));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getClockWise());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(CHARGE);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getItemInHand(handIn);
        if (handIn == Hand.MAIN_HAND && !(itemstack.getItem() == UGItems.REGALIUM_INGOT.get()) && player.getItemInHand(Hand.OFF_HAND).getItem() == UGItems.REGALIUM_INGOT.get()) {
            return ActionResultType.PASS;
        }
        else if (itemstack.getItem() == UGItems.REGALIUM_INGOT.get() && state.getValue(CHARGE) == 0) {
            worldIn.setBlock(pos, state.setValue(CHARGE, 1), 19);
            if (!player.abilities.invulnerable) {
                itemstack.shrink(1);
            }

            return ActionResultType.sidedSuccess(worldIn.isClientSide);
        }
        else if (state.getValue(CHARGE) == 0) {
            return ActionResultType.PASS;
        }
        else if (!(worldIn.dimension() == UGDimensions.UNDERGARDEN_WORLD)) {
            if (!worldIn.isClientSide) {
                this.triggerExplosion(worldIn, pos);
            }

            return ActionResultType.sidedSuccess(worldIn.isClientSide);
        }
        else {
            if (!worldIn.isClientSide) {
                ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)player;
                if (serverplayerentity.getRespawnDimension() != worldIn.dimension() || !serverplayerentity.getRespawnPosition().equals(pos)) {
                    serverplayerentity.setRespawnPosition(UGDimensions.UNDERGARDEN_WORLD, pos, 0.0F, false, true);
                    worldIn.playSound(null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, UGSoundEvents.EFFIGY_SET_SPAWN.get(), SoundCategory.BLOCKS, 3.0F, 1.0F);
                    return ActionResultType.SUCCESS;
                }
            }

            return ActionResultType.CONSUME;
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    }

    private void triggerExplosion(World world, final BlockPos pos2) {
        world.removeBlock(pos2, false);
        boolean flag = Direction.Plane.HORIZONTAL.stream().map(pos2::relative).anyMatch((posIn) -> {
            return isNearWater(posIn, world);
        });
        final boolean flag1 = flag || world.getFluidState(pos2.above()).is(FluidTags.WATER);
        ExplosionContext explosioncontext = new ExplosionContext() {
            public Optional<Float> getExplosionResistance(Explosion explosion, IBlockReader reader, BlockPos pos, BlockState state, FluidState fluid) {
                return pos.equals(pos2) && flag1 ? Optional.of(Blocks.WATER.getExplosionResistance()) : super.getBlockExplosionResistance(explosion, reader, pos, state, fluid);
            }
        };
        world.explode(null, DamageSource.badRespawnPointExplosion(), explosioncontext, (double)pos2.getX() + 0.5D, (double)pos2.getY() + 0.5D, (double)pos2.getZ() + 0.5D, 5.0F, true, Explosion.Mode.DESTROY);
    }

    private static boolean isNearWater(BlockPos pos, World world) {
        FluidState fluidstate = world.getFluidState(pos);
        if (!fluidstate.is(FluidTags.WATER)) {
            return false;
        } else if (fluidstate.isSource()) {
            return true;
        } else {
            float f = fluidstate.getOwnHeight();
            if (f < 2.0F) {
                return false;
            } else {
                FluidState fluidstate1 = world.getFluidState(pos.below());
                return !fluidstate1.is(FluidTags.WATER);
            }
        }
    }

    public static Optional<Vector3d> findStandUpPosition(EntityType<?> entity, ICollisionReader reader, BlockPos pos) {
        Optional<Vector3d> optional = findStandUpPosition(entity, reader, pos, true);
        return optional.isPresent() ? optional : findStandUpPosition(entity, reader, pos, false);
    }

    private static Optional<Vector3d> findStandUpPosition(EntityType<?> type, ICollisionReader collisionReader, BlockPos pos, boolean checkCanSpawn) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(Vector3i vector3i : RESPAWN_OFFSETS) {
            blockpos$mutable.set(pos).move(vector3i);
            Vector3d vector3d = TransportationHelper.findSafeDismountLocation(type, collisionReader, blockpos$mutable, checkCanSpawn);
            if (vector3d != null) {
                return Optional.of(vector3d);
            }
        }

        return Optional.empty();
    }
}