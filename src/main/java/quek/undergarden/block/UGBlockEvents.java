package quek.undergarden.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGBlocks;

@Mod.EventBusSubscriber(modid = Undergarden.MODID)
public class UGBlockEvents {

	@SubscribeEvent
	public static void blockToolInteractions(BlockEvent.BlockToolModificationEvent event) {
		ToolAction action = event.getToolAction();
		BlockState state = event.getState();
		UseOnContext context = event.getContext();
		if (!event.isSimulated()) {
			if (action == ToolActions.AXE_STRIP) {
				if (state.is(UGBlocks.SMOGSTEM_LOG.get())) {
					event.setFinalState(UGBlocks.STRIPPED_SMOGSTEM_LOG.get().withPropertiesOf(state));
				}
				if (state.is(UGBlocks.SMOGSTEM_WOOD.get())) {
					event.setFinalState(UGBlocks.STRIPPED_SMOGSTEM_WOOD.get().withPropertiesOf(state));
				}
				if (state.is(UGBlocks.WIGGLEWOOD_LOG.get())) {
					event.setFinalState(UGBlocks.STRIPPED_WIGGLEWOOD_LOG.get().withPropertiesOf(state));
				}
				if (state.is(UGBlocks.WIGGLEWOOD_WOOD.get())) {
					event.setFinalState(UGBlocks.STRIPPED_WIGGLEWOOD_WOOD.get().withPropertiesOf(state));
				}
				if (state.is(UGBlocks.GRONGLE_LOG.get())) {
					event.setFinalState(UGBlocks.STRIPPED_GRONGLE_LOG.get().withPropertiesOf(state));
				}
				if (state.is(UGBlocks.GRONGLE_WOOD.get())) {
					event.setFinalState(UGBlocks.STRIPPED_GRONGLE_WOOD.get().withPropertiesOf(state));
				}
			}
			if (action == ToolActions.HOE_TILL && (context.getClickedFace() != Direction.DOWN && context.getLevel().getBlockState(context.getClickedPos().above()).isAir())) {
				if (state.is(UGBlocks.DEEPTURF_BLOCK.get()) || state.is(UGBlocks.DEEPSOIL.get()) || state.is(UGBlocks.ASHEN_DEEPTURF_BLOCK.get()) || state.is(UGBlocks.FROZEN_DEEPTURF_BLOCK.get())) {
					event.setFinalState(UGBlocks.DEEPSOIL_FARMLAND.get().defaultBlockState());
				}
				if (state.is(UGBlocks.COARSE_DEEPSOIL.get())) {
					event.setFinalState(UGBlocks.DEEPSOIL.get().defaultBlockState());
				}
			}
		}
	}
}