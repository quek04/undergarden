package quek.undergarden.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGBlockEntities;

public class GrongletBlockEntity extends BlockEntity {

    public int yaw;

    public GrongletBlockEntity(BlockPos pos, BlockState state) {
        super(UGBlockEntities.GRONGLET.get(), pos, state);
        yaw = -1;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, GrongletBlockEntity blockEntity) {
        if (level.isClientSide) {
            if (blockEntity.yaw == -1) {
                blockEntity.yaw = level.random.nextInt(360);
            }
        }
    }
}