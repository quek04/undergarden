package quek.undergarden.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.UndergardenMod;

public class UndergardenTags {

    private static final TagRegistry<Item> itemCollection = new TagRegistry<>();
    private static final TagRegistry<Block> blockCollection = new TagRegistry<>();

    public static class Items {

        public static final ITag.INamedTag<Item> SMOGSTEM_PLANKS = makeWrapperTag("smogstem_planks");
        public static final ITag.INamedTag<Item> WIGGLEWOOD_PLANKS = makeWrapperTag("wigglewood_planks");
        public static final ITag.INamedTag<Item> MUSHROOMS = makeWrapperTag("mushrooms");

        public static final ITag.INamedTag<Item> CLOGGRUM_ITEMS = makeWrapperTag("cloggrum_items");
        public static final ITag.INamedTag<Item> FROSTSTEEL_ITEMS = makeWrapperTag("froststeel_items");
        public static final ITag.INamedTag<Item> UTHERIUM_ITEMS = makeWrapperTag("utherium_items");

        private static ITag.INamedTag<Item> makeWrapperTag(String name) {
            return itemCollection.func_232937_a_(new ResourceLocation(UndergardenMod.MODID, name).toString());
        }

    }

    public static class Blocks {

        public static final ITag.INamedTag<Block> PORTAL_FRAME_BLOCKS = makeWrapperTag("portal_frame_blocks");

        private static ITag.INamedTag<Block> makeWrapperTag(String name) {
            return blockCollection.func_232937_a_(new ResourceLocation(UndergardenMod.MODID, name).toString());
        }

    }

}
