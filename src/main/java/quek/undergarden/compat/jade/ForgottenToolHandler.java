package quek.undergarden.compat.jade;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGTags;
import snownee.jade.addon.harvest.SimpleToolHandler;

import java.util.List;

public class ForgottenToolHandler extends SimpleToolHandler {
	protected ForgottenToolHandler(Identifier uid, List<ItemStack> tools) {
		super(uid, tools, true);
	}

	@Override
	public ItemStack test(BlockState state, Level world, BlockPos pos) {
		if (!state.is(UGTags.Blocks.NEEDS_FORGOTTEN_TOOL)) return ItemStack.EMPTY;
		return super.test(state, world, pos);
	}
}
