package quek.undergarden.potion;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.BlockPos;
import quek.undergarden.entity.ScintlingEntity;
import quek.undergarden.registry.UGBlocks;

public class GooeyEffect extends Effect {

    public GooeyEffect() {
        super(EffectType.HARMFUL, 7827026);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        BlockState blockstate = UGBlocks.GOO.get().defaultBlockState();
        BlockPos pos = new BlockPos(entity.getX(), entity.getY(), entity.getZ());

        if (entity.level.isEmptyBlock(pos) && blockstate.canSurvive(entity.level, pos) && !(entity instanceof ScintlingEntity)) {
            entity.level.setBlockAndUpdate(pos, blockstate);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }
}
