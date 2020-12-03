package quek.undergarden.registry;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import quek.undergarden.UGMod;

public class UGTags {

    public static class Items {

        public static final Tags.IOptionalNamedTag<Item> MUSHROOMS = tag("mushrooms");
        public static final Tags.IOptionalNamedTag<Item> CLOGGRUM_ITEMS = tag("cloggrum_items");
        public static final Tags.IOptionalNamedTag<Item> FROSTSTEEL_ITEMS = tag("froststeel_items");
        public static final Tags.IOptionalNamedTag<Item> UTHERIUM_ITEMS = tag("utherium_items");
        public static final Tags.IOptionalNamedTag<Item> SMOGSTEM_LOGS = tag("smogstem_logs");
        public static final Tags.IOptionalNamedTag<Item> WIGGLEWOOD_LOGS = tag("wigglewood_logs");
        public static final Tags.IOptionalNamedTag<Item> GRONGLE_STEMS = tag("grongle_stems");

        private static Tags.IOptionalNamedTag<Item> tag(String name) {
            return ItemTags.createOptional(new ResourceLocation(UGMod.MODID, name));
        }
    }

    public static class Blocks {

        public static final Tags.IOptionalNamedTag<Block> MUSHROOMS = tag("mushrooms");
        public static final Tags.IOptionalNamedTag<Block> PORTAL_FRAME_BLOCKS = tag("portal_frame_blocks");
        public static final Tags.IOptionalNamedTag<Block> SMOGSTEM_LOGS = tag("smogstem_logs");
        public static final Tags.IOptionalNamedTag<Block> WIGGLEWOOD_LOGS = tag("wigglewood_logs");
        public static final Tags.IOptionalNamedTag<Block> GRONGLE_STEMS = tag("grongle_stems");

        private static Tags.IOptionalNamedTag<Block> tag(String name) {
            return BlockTags.createOptional(new ResourceLocation(UGMod.MODID, name));
        }
    }

    public static class Entities {

        public static final Tags.IOptionalNamedTag<EntityType<?>> ROTSPAWN = tag("rotspawn");
        public static final Tags.IOptionalNamedTag<EntityType<?>> CAVERN_CREATURE = tag("cavern_creature");

        private static Tags.IOptionalNamedTag<EntityType<?>> tag(String name) {
            return EntityTypeTags.createOptional(new ResourceLocation(UGMod.MODID, name));
        }
    }
}
