package quek.undergarden.entity.cavern;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import java.util.Random;

public abstract class AbstractCavernCreatureEntity extends Monster {

    protected AbstractCavernCreatureEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static boolean canCreatureSpawn(EntityType<? extends Monster> type, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, Random random) {
        if(checkMonsterSpawnRules(type, level, spawnType, pos, random)) {
            return spawnType == MobSpawnType.SPAWNER || pos.getY() < 32;
        }
        return false;
    }
}