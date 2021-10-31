package quek.undergarden.entity.cavern;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import java.util.Random;

public abstract class AbstractCavernCreatureEntity extends Monster {

    protected AbstractCavernCreatureEntity(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
    }

    public static boolean canCreatureSpawn(EntityType<? extends Monster> type, ServerLevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random randomIn) {
        if(checkMonsterSpawnRules(type, worldIn, reason, pos, randomIn)) {
            return reason == MobSpawnType.SPAWNER || pos.getY() < 32;
        }
        return false;
    }
}