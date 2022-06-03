package quek.undergarden.registry;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Map;
public interface UGCauldronInteraction extends CauldronInteraction {

    CauldronInteraction FILL_VIRULENT_MIX = (state, world, pos, player, hand, stack) ->
            CauldronInteraction.emptyBucket(world, pos, player, hand, stack, UGBlocks.VIRULENT_MIX_CAULDRON.get().defaultBlockState(), UGSoundEvents.BUCKET_EMPTY_VIRULENT.get());

    CauldronInteraction EMPTY_VIRULENT_MIX = (state, world, pos, player, hand, stack) ->
            CauldronInteraction.fillBucket(state, world, pos, player, hand, stack, new ItemStack(UGItems.VIRULENT_MIX_BUCKET.get()), blockState -> true, UGSoundEvents.BUCKET_FILL_VIRULENT.get());

    Map<Item, CauldronInteraction> VIRULENT_MIX = CauldronInteraction.newInteractionMap();

    static void register() {
        EMPTY.put(UGItems.VIRULENT_MIX_BUCKET.get(), FILL_VIRULENT_MIX);
        WATER.put(UGItems.VIRULENT_MIX_BUCKET.get(), FILL_VIRULENT_MIX);
        LAVA.put(UGItems.VIRULENT_MIX_BUCKET.get(), FILL_VIRULENT_MIX);
        POWDER_SNOW.put(UGItems.VIRULENT_MIX_BUCKET.get(), FILL_VIRULENT_MIX);
        VIRULENT_MIX.put(UGItems.VIRULENT_MIX_BUCKET.get(), FILL_VIRULENT_MIX);

        VIRULENT_MIX.put(Items.BUCKET, EMPTY_VIRULENT_MIX);

        CauldronInteraction.addDefaultInteractions(VIRULENT_MIX);
    }
}
