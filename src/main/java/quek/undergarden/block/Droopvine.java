package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import quek.undergarden.registry.UGItems;

import java.util.function.ToIntFunction;

public interface Droopvine {
	BooleanProperty GLOWY = BooleanProperty.create("glowy");

	static InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos) {
		if (pState.getValue(GLOWY)) {
			Block.popResource(pLevel, pPos, new ItemStack(UGItems.DROOPFRUIT.get(), 1));
			float pitch = Mth.randomBetween(pLevel.random, 0.8F, 1.2F);
			pLevel.playSound(null, pPos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, pitch);
			pLevel.setBlock(pPos, pState.setValue(GLOWY, Boolean.FALSE), 2);
			return InteractionResult.sidedSuccess(pLevel.isClientSide);
		} else {
			return InteractionResult.PASS;
		}
	}

	static ToIntFunction<BlockState> light() {
		return (state) -> state.getValue(GLOWY) ? 10 : 0;
	}
}
