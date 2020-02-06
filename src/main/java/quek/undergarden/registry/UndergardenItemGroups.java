package quek.undergarden.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class UndergardenItemGroups {

    public static final ItemGroup UNDERGARDEN_BLOCKS = new ItemGroup("undergarden_blocks") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(UndergardenBlocks.depthrock.get());
        }
    };

    public static final ItemGroup UNDERGARDEN_ITEMS = new ItemGroup("undergarden_items") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(UndergardenItems.smogstem_stick.get());
        }
    };

    public static final ItemGroup UNDERGARDEN_GEAR = new ItemGroup("undergarden_gear") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(UndergardenItems.cloggrum_sword.get());

        }
    };

    public static final ItemGroup UNDERGARDEN_FOODS = new ItemGroup("undergarden_foods") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(UndergardenItems.underbeans.get());
        }
    };
}
