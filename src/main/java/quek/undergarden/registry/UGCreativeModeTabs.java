package quek.undergarden.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class UGCreativeModeTabs {

    public static final CreativeModeTab GROUP = new CreativeModeTab("undergarden_group") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(UGBlocks.DEEPTURF_BLOCK.get());
        }
    }.setEnchantmentCategories(UGEnchantments.SLINGSHOT);
}