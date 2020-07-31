package quek.undergarden.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class UndergardenItemGroups {

    public static final ItemGroup GROUP = new ItemGroup("undergarden_group") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(UndergardenBlocks.deepturf_block.get());
        }
    };

}
