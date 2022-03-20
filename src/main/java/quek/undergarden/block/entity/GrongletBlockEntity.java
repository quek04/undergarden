package quek.undergarden.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGBlockEntities;

public class GrongletBlockEntity extends BlockEntity {

    public GrongletBlockEntity(BlockPos pos, BlockState state) {
        super(UGBlockEntities.GRONGLET.get(), pos, state);
    }

    public static <B extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, B blockEntity) {
        if (level.isClientSide) {

        }
    }
}
