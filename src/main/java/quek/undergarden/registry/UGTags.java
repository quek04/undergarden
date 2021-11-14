package quek.undergarden.registry;

import net.minecraft.tags.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import quek.undergarden.Undergarden;

public class UGTags {

    public static class Items {

        public static final Tag.Named<Item> MUSHROOMS = tag("mushrooms");
        public static final Tag.Named<Item> CLOGGRUM_ITEMS = tag("cloggrum_items");
        public static final Tag.Named<Item> FROSTSTEEL_ITEMS = tag("froststeel_items");
        public static final Tag.Named<Item> UTHERIUM_ITEMS = tag("utherium_items");
        public static final Tag.Named<Item> SMOGSTEM_LOGS = tag("smogstem_logs");
        public static final Tag.Named<Item> WIGGLEWOOD_LOGS = tag("wigglewood_logs");
        public static final Tag.Named<Item> GRONGLE_LOGS = tag("grongle_logs");

        private static Tag.Named<Item> tag(String name) {
            return ItemTags.createOptional(new ResourceLocation(Undergarden.MODID, name));
        }
    }

    public static class Blocks {

        public static final Tag.Named<Block> BASE_STONE_UNDERGARDEN = tag("base_stone_undergarden");
        public static final Tag.Named<Block> MUSHROOMS = tag("mushrooms");
        public static final Tag.Named<Block> PORTAL_FRAME_BLOCKS = tag("portal_frame_blocks");
        public static final Tag.Named<Block> SMOGSTEM_LOGS = tag("smogstem_logs");
        public static final Tag.Named<Block> WIGGLEWOOD_LOGS = tag("wigglewood_logs");
        public static final Tag.Named<Block> GRONGLE_LOGS = tag("grongle_logs");
        public static final Tag.Named<Block> MUNCHER_BREAKABLES = tag("muncher_breakables");

        private static Tag.Named<Block> tag(String name) {
            return BlockTags.createOptional(new ResourceLocation(Undergarden.MODID, name));
        }
    }

    public static class Entities {

        public static final Tag.Named<EntityType<?>> ROTSPAWN = tag("rotspawn");
        public static final Tag.Named<EntityType<?>> CAVERN_CREATURE = tag("cavern_creature");

        private static Tag.Named<EntityType<?>> tag(String name) {
            return EntityTypeTags.createOptional(new ResourceLocation(Undergarden.MODID, name));
        }
    }

    public static class Fluids {

        public static final Tag.Named<Fluid> VIRULENT = tag("virulent");

        private static Tag.Named<Fluid> tag(String name) {
            return FluidTags.createOptional(new ResourceLocation(Undergarden.MODID, name));
        }
    }
}