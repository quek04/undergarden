package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import quek.undergarden.registry.UGBuiltinLootTables;

import java.util.function.ToIntFunction;

public interface Droopvine {
	BooleanProperty GLOWY = BooleanProperty.create("glowy");

	static InteractionResult use(Entity sourceEntity, BlockState state, Level level, BlockPos pos) {
		if (state.getValue(GLOWY)) {
			if (level instanceof ServerLevel serverLevel) {
				Block.dropFromBlockInteractLootTable(
					serverLevel,
					UGBuiltinLootTables.HARVEST_DROOPVINE,
					state,
					level.getBlockEntity(pos),
					null,
					sourceEntity,
					(serverlvl, itemStack) -> Block.popResource(serverlvl, pos, itemStack)
				);
				float pitch = Mth.randomBetween(level.getRandom(), 0.8F, 1.2F);
				level.playSound(null, pos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, pitch);
				BlockState newState = state.setValue(GLOWY, false);
				serverLevel.setBlock(pos, newState, 2);
				level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(sourceEntity, newState));
			}
			return InteractionResult.SUCCESS;
		} else {
			return InteractionResult.PASS;
		}
	}

	static ToIntFunction<BlockState> light() {
		return (state) -> state.getValue(GLOWY) ? 10 : 0;
	}
}
