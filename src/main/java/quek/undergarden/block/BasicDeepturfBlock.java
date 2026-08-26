package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jspecify.annotations.Nullable;
import quek.undergarden.registry.UGBlocks;

import java.util.function.BiConsumer;

public class BasicDeepturfBlock extends Block {

	public BasicDeepturfBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean onTreeGrow(BlockState state, WorldGenLevel level, BiConsumer<BlockPos, BlockState> placeFunction, RandomSource randomSource, BlockPos pos, TreeConfiguration config) {
		placeFunction.accept(pos, UGBlocks.DEEPSOIL.get().defaultBlockState());
		return true;
	}

	@Override
	public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility ability, boolean simulate) {
		if (ability.equals(ItemAbilities.HOE_TILL) && HoeItem.onlyIfAirAbove(context)) {
			return UGBlocks.DEEPSOIL_FARMLAND.get().defaultBlockState();
		}
		return super.getToolModifiedState(state, context, ability, simulate);
	}
}