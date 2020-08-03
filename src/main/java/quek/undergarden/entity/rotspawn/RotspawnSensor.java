package quek.undergarden.entity.rotspawn;

import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.monster.HoglinEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.registry.UndergardenTags;

import java.util.Optional;
import java.util.Set;

public class RotspawnSensor extends Sensor<AbstractRotspawnEntity> {

    @Override
    public Set<MemoryModuleType<?>> getUsedMemories() {
        return ImmutableSet.of(
                MemoryModuleType.VISIBLE_MOBS,
                MemoryModuleType.NEAREST_REPELLENT
                );
    }

    @Override
    protected void update(ServerWorld world, AbstractRotspawnEntity rotspawn) {
        Brain<?> brain = rotspawn.getBrain();
        brain.setMemory(MemoryModuleType.NEAREST_REPELLENT, valid_repellent(world, rotspawn));
    }

    private Optional<BlockPos> valid_repellent(ServerWorld world, AbstractRotspawnEntity rotspawn) {
        return BlockPos.func_239584_a_(rotspawn.func_233580_cy_(), 8, 4, (pos) -> world.getBlockState(pos).isIn(UndergardenTags.Blocks.ROTSPAWN_REPELLENT));
    }
}
