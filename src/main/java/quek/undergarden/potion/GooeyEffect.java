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
    public void performEffect(LivingEntity entity, int amplifier) {
        BlockState blockstate = UGBlocks.goo.get().getDefaultState();
        BlockPos pos = new BlockPos(entity.getPosX(), entity.getPosY(), entity.getPosZ());

        if (entity.world.isAirBlock(pos) && blockstate.isValidPosition(entity.world, pos) && !(entity instanceof ScintlingEntity)) {
            entity.world.setBlockState(pos, blockstate);
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration > 0;
    }
}
