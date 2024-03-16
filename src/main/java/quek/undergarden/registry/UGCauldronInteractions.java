package quek.undergarden.registry;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public interface UGCauldronInteractions extends CauldronInteraction {

	CauldronInteraction FILL_VIRULENT_MIX = (state, world, pos, player, hand, stack) ->
			CauldronInteraction.emptyBucket(world, pos, player, hand, stack, UGBlocks.VIRULENT_MIX_CAULDRON.get().defaultBlockState(), UGSoundEvents.BUCKET_EMPTY_VIRULENT.get());

	CauldronInteraction EMPTY_VIRULENT_MIX = (state, world, pos, player, hand, stack) ->
			CauldronInteraction.fillBucket(state, world, pos, player, hand, stack, new ItemStack(UGItems.VIRULENT_MIX_BUCKET.get()), blockState -> true, UGSoundEvents.BUCKET_FILL_VIRULENT.get());

	CauldronInteraction.InteractionMap VIRULENT_MIX = CauldronInteraction.newInteractionMap("virulent_mix");

	static void register() {
		EMPTY.map().put(UGItems.VIRULENT_MIX_BUCKET.get(), FILL_VIRULENT_MIX);
		WATER.map().put(UGItems.VIRULENT_MIX_BUCKET.get(), FILL_VIRULENT_MIX);
		LAVA.map().put(UGItems.VIRULENT_MIX_BUCKET.get(), FILL_VIRULENT_MIX);
		POWDER_SNOW.map().put(UGItems.VIRULENT_MIX_BUCKET.get(), FILL_VIRULENT_MIX);
		VIRULENT_MIX.map().put(UGItems.VIRULENT_MIX_BUCKET.get(), FILL_VIRULENT_MIX);

		VIRULENT_MIX.map().put(Items.BUCKET, EMPTY_VIRULENT_MIX);

		CauldronInteraction.addDefaultInteractions(VIRULENT_MIX.map());
	}
}
