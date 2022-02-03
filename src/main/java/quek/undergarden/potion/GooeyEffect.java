package quek.undergarden.potion;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.entity.animal.ScintlingEntity;
import quek.undergarden.registry.UGBlocks;

public class GooeyEffect extends MobEffect {

    public GooeyEffect() {
        super(MobEffectCategory.HARMFUL, 7827026);
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
