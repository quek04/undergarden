package quek.undergarden.entity.monster.cavern;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class CavernMonster extends Monster {

	protected CavernMonster(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	public static boolean canCreatureSpawn(EntityType<? extends Monster> type, ServerLevelAccessor level, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
		if (checkMonsterSpawnRules(type, level, reason, pos, random)) {
			return EntitySpawnReason.isSpawner(reason) || pos.getY() < 24;
		}
		return false;
	}
}