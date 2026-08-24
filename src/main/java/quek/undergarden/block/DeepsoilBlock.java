package quek.undergarden.block;

import net.minecraft.core.Holder;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jspecify.annotations.Nullable;

public class DeepsoilBlock extends Block {

	private final Holder<Block> tillInto;

	public DeepsoilBlock(Holder<Block> tillInto, Properties properties) {
		super(properties);
		this.tillInto = tillInto;
	}

	@Override
	public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility ability, boolean simulate) {
		if (ability.equals(ItemAbilities.HOE_TILL) && HoeItem.onlyIfAirAbove(context)) {
			return this.tillInto.value().defaultBlockState();
		}
		return super.getToolModifiedState(state, context, ability, simulate);
	}
}
