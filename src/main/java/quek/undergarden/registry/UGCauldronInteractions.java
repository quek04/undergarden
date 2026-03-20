package quek.undergarden.registry;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.cauldron.CauldronInteractions;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public interface UGCauldronInteractions extends CauldronInteraction {

	CauldronInteraction FILL_VIRULENT_MIX = (state, world, pos, player, hand, stack) ->
			CauldronInteractions.emptyBucket(world, pos, player, hand, stack, UGBlocks.VIRULENT_MIX_CAULDRON.get().defaultBlockState(), UGSoundEvents.BUCKET_EMPTY_VIRULENT.get());

	CauldronInteraction EMPTY_VIRULENT_MIX = (state, world, pos, player, hand, stack) ->
			CauldronInteractions.fillBucket(state, world, pos, player, hand, stack, new ItemStack(UGItems.VIRULENT_MIX_BUCKET.get()), blockState -> true, UGSoundEvents.BUCKET_FILL_VIRULENT.get());

	CauldronInteraction.Dispatcher VIRULENT_MIX = CauldronInteractions.newDispatcher("virulent_mix");

	static void register() {
		CauldronInteractions.EMPTY.put(UGItems.VIRULENT_MIX_BUCKET.get(), FILL_VIRULENT_MIX);
		CauldronInteractions.WATER.put(UGItems.VIRULENT_MIX_BUCKET.get(), FILL_VIRULENT_MIX);
		CauldronInteractions.LAVA.put(UGItems.VIRULENT_MIX_BUCKET.get(), FILL_VIRULENT_MIX);
		CauldronInteractions.POWDER_SNOW.put(UGItems.VIRULENT_MIX_BUCKET.get(), FILL_VIRULENT_MIX);
		VIRULENT_MIX.put(UGItems.VIRULENT_MIX_BUCKET.get(), FILL_VIRULENT_MIX);

		VIRULENT_MIX.put(Items.BUCKET, EMPTY_VIRULENT_MIX);

		CauldronInteractions.addDefaultInteractions(VIRULENT_MIX);
	}
}
