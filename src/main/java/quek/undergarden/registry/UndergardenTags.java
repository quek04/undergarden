package quek.undergarden.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.UndergardenMod;

public class UndergardenTags {

    public static class Items {

        public static final ITag<Item> SMOGSTEM_PLANKS = tag("smogstem_planks");
        public static final ITag<Item> WIGGLEWOOD_PLANKS = tag("wigglewood_planks");
        public static final ITag<Item> MUSHROOMS = tag("mushrooms");

        public static final ITag<Item> CLOGGRUM_ITEMS = tag("cloggrum_items");
        public static final ITag<Item> FROSTSTEEL_ITEMS = tag("froststeel_items");
        public static final ITag<Item> UTHERIUM_ITEMS = tag("utherium_items");

        private static ITag<Item> tag(String name) {
            return ItemTags.getCollection().getOrCreate(new ResourceLocation(UndergardenMod.MODID, name));
        }

    }

    public static class Blocks {

        public static final ITag<Block> PORTAL_FRAME_BLOCKS = tag("portal_frame_blocks");

        private static ITag<Block> tag(String name) {
            return BlockTags.getCollection().getOrCreate(new ResourceLocation(UndergardenMod.MODID, name));
        }

    }

}
