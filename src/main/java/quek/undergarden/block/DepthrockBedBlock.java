package quek.undergarden.block;

import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import quek.undergarden.block.tileentity.DepthrockBedTE;
import quek.undergarden.registry.UGDimensions;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class DepthrockBedBlock extends BedBlock {

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);

    public DepthrockBedBlock(Properties properties) {
        super(DyeColor.GREEN, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PART, BedPart.FOOT).setValue(OCCUPIED, Boolean.FALSE));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext selectionContext) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult rayTraceResult) {
        if (world.isClientSide) {
            return InteractionResult.CONSUME;
        } else {
            if (state.getValue(PART) != BedPart.HEAD) {
                pos = pos.relative(state.getValue(FACING));
                state = world.getBlockState(pos);
                if (!state.is(this)) {
                    return InteractionResult.CONSUME;
                }
            }

            if (!canSetSpawn(world)) {
                world.removeBlock(pos, false);
                BlockPos blockpos = pos.relative(state.getValue(FACING).getOpposite());
                if (world.getBlockState(blockpos).is(this)) {
                    world.removeBlock(blockpos, false);
                }

                world.explode(null, DamageSource.badRespawnPointExplosion(), null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, 5.0F, true, Explosion.BlockInteraction.DESTROY);
                return InteractionResult.SUCCESS;
            } else if (state.getValue(OCCUPIED)) {
                //if (!this.kickVillagerOutOfBed(world, blockPos)) {
                    player.displayClientMessage(new TranslatableComponent("block.minecraft.bed.occupied"), true);
                //}

                return InteractionResult.SUCCESS;
            } else {
                player.startSleepInBed(pos).ifLeft((sleepResult) -> {
                    if (sleepResult != null) {
                        player.displayClientMessage(sleepResult.getMessage(), true);
                    }

                });
                return InteractionResult.SUCCESS;
            }
        }
    }

    public static boolean canSetSpawn(Level world) {
        return world.dimension() == UGDimensions.UNDERGARDEN_WORLD;
    }

    @Override
    public BlockEntity newBlockEntity(BlockGetter world) {
        return new DepthrockBedTE();
    }

    //stops reduced fall damage
    @Override
    public void fallOn(Level world, BlockPos pos, Entity entity, float distance) {
        super.fallOn(world, pos, entity, distance);
    }

    //stops bouncing
    @Override
    public void updateEntityAfterFallOn(BlockGetter world, Entity entity) {
        entity.setDeltaMovement(entity.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
    }
}
