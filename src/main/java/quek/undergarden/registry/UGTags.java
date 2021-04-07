package quek.undergarden.registry;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;

public class UGTags {

    public static class Items {

        public static final ITag.INamedTag<Item> MUSHROOMS = tag("mushrooms");
        public static final ITag.INamedTag<Item> CLOGGRUM_ITEMS = tag("cloggrum_items");
        public static final ITag.INamedTag<Item> FROSTSTEEL_ITEMS = tag("froststeel_items");
        public static final ITag.INamedTag<Item> UTHERIUM_ITEMS = tag("utherium_items");
        public static final ITag.INamedTag<Item> SMOGSTEM_LOGS = tag("smogstem_logs");
        public static final ITag.INamedTag<Item> WIGGLEWOOD_LOGS = tag("wigglewood_logs");
        public static final ITag.INamedTag<Item> GRONGLE_STEMS = tag("grongle_stems");

        private static ITag.INamedTag<Item> tag(String name) {
            return ItemTags.createOptional(new ResourceLocation(Undergarden.MODID, name));
        }
    }

    public static class Blocks {

        public static final ITag.INamedTag<Block> BASE_STONE_UNDERGARDEN = tag("base_stone_undergarden");
        public static final ITag.INamedTag<Block> MUSHROOMS = tag("mushrooms");
        public static final ITag.INamedTag<Block> PORTAL_FRAME_BLOCKS = tag("portal_frame_blocks");
        public static final ITag.INamedTag<Block> SMOGSTEM_LOGS = tag("smogstem_logs");
        public static final ITag.INamedTag<Block> WIGGLEWOOD_LOGS = tag("wigglewood_logs");
        public static final ITag.INamedTag<Block> GRONGLE_STEMS = tag("grongle_stems");
        public static final ITag.INamedTag<Block> MUNCHER_BREAKABLES = tag("muncher_breakables");

        private static ITag.INamedTag<Block> tag(String name) {
            return BlockTags.createOptional(new ResourceLocation(Undergarden.MODID, name));
        }
    }

    public static class Entities {

        public static final ITag.INamedTag<EntityType<?>> ROTSPAWN = tag("rotspawn");
        public static final ITag.INamedTag<EntityType<?>> CAVERN_CREATURE = tag("cavern_creature");

        private static ITag.INamedTag<EntityType<?>> tag(String name) {
            return EntityTypeTags.createOptional(new ResourceLocation(Undergarden.MODID, name));
        }
    }
}