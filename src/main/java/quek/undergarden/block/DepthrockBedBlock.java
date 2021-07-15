package quek.undergarden.block;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import quek.undergarden.block.tileentity.DepthrockBedTE;
import quek.undergarden.registry.UGDimensions;

public class DepthrockBedBlock extends BedBlock {

    public DepthrockBedBlock(Properties properties) {
        super(DyeColor.GREEN, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PART, BedPart.FOOT).setValue(OCCUPIED, Boolean.FALSE));
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        if (world.isClientSide) {
            return ActionResultType.CONSUME;
        } else {
            if (state.getValue(PART) != BedPart.HEAD) {
                pos = pos.relative(state.getValue(FACING));
                state = world.getBlockState(pos);
                if (!state.is(this)) {
                    return ActionResultType.CONSUME;
                }
            }

            if (!canSetSpawn(world)) {
                world.removeBlock(pos, false);
                BlockPos blockpos = pos.relative(state.getValue(FACING).getOpposite());
                if (world.getBlockState(blockpos).is(this)) {
                    world.removeBlock(blockpos, false);
                }

                world.explode(null, DamageSource.badRespawnPointExplosion(), null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, 5.0F, true, Explosion.Mode.DESTROY);
                return ActionResultType.SUCCESS;
            } else if (state.getValue(OCCUPIED)) {
                //if (!this.kickVillagerOutOfBed(world, blockPos)) {
                    player.displayClientMessage(new TranslationTextComponent("block.minecraft.bed.occupied"), true);
                //}

                return ActionResultType.SUCCESS;
            } else {
                player.startSleepInBed(pos).ifLeft((sleepResult) -> {
                    if (sleepResult != null) {
                        player.displayClientMessage(sleepResult.getMessage(), true);
                    }

                });
                return ActionResultType.SUCCESS;
            }
        }
    }

    public static boolean canSetSpawn(World world) {
        return world.dimension() == UGDimensions.UNDERGARDEN_WORLD;
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader world) {
        return new DepthrockBedTE();
    }

    //stops reduced fall damage
    @Override
    public void fallOn(World world, BlockPos pos, Entity entity, float distance) {
        super.fallOn(world, pos, entity, distance);
    }

    //stops bouncing
    @Override
    public void updateEntityAfterFallOn(IBlockReader world, Entity entity) {
        entity.setDeltaMovement(entity.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
    }
}
