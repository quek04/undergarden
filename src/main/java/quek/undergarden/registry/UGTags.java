package quek.undergarden.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.versions.forge.ForgeVersion;
import quek.undergarden.Undergarden;

public class UGTags {

    public static class Items {

        public static final TagKey<Item> MUSHROOMS = tag("mushrooms");
        public static final TagKey<Item> CLOGGRUM_ITEMS = tag("cloggrum_items");
        public static final TagKey<Item> FROSTSTEEL_ITEMS = tag("froststeel_items");
        public static final TagKey<Item> UTHERIUM_ITEMS = tag("utherium_items");
        public static final TagKey<Item> SMOGSTEM_LOGS = tag("smogstem_logs");
        public static final TagKey<Item> WIGGLEWOOD_LOGS = tag("wigglewood_logs");
        public static final TagKey<Item> GRONGLE_LOGS = tag("grongle_logs");
        public static final TagKey<Item> SLINGSHOT_AMMO = tag("slingshot_ammo");

        public static final TagKey<Item> INGOTS_CLOGGRUM = forgeTag("ingots/cloggrum");
        public static final TagKey<Item> INGOTS_FROSTSTEEL = forgeTag("ingots/froststeel");
        public static final TagKey<Item> INGOTS_UTHERIUM = forgeTag("ingots/utherium");
        public static final TagKey<Item> INGOTS_REGALIUM = forgeTag("ingots/regalium");
        public static final TagKey<Item> INGOTS_FORGOTTEN = forgeTag("ingots/forgotten_metal");

        public static final TagKey<Item> NUGGETS_CLOGGRUM = forgeTag("nuggets/cloggrum");
        public static final TagKey<Item> NUGGETS_FROSTSTEEL = forgeTag("nuggets/froststeel");
        public static final TagKey<Item> NUGGETS_FORGOTTEN = forgeTag("nuggets/forgotten_metal");

        public static final TagKey<Item> ORES_CLOGGRUM = forgeTag("ores/cloggrum");
        public static final TagKey<Item> ORES_FROSTSTEEL = forgeTag("ores/froststeel");
        public static final TagKey<Item> ORES_UTHERIUM = forgeTag("ores/utherium");
        public static final TagKey<Item> ORES_REGALIUM = forgeTag("ores/regalium");

        public static final TagKey<Item> STORAGE_BLOCKS_CLOGGRUM = forgeTag("storage_blocks/cloggrum");
        public static final TagKey<Item> STORAGE_BLOCKS_FROSTSTEEL = forgeTag("storage_blocks/froststeel");
        public static final TagKey<Item> STORAGE_BLOCKS_UTHERIUM = forgeTag("storage_blocks/utherium");
        public static final TagKey<Item> STORAGE_BLOCKS_REGALIUM = forgeTag("storage_blocks/regalium");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Undergarden.MODID, name));
        }
        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation(ForgeVersion.MOD_ID, name));
        }
    }

    public static class Blocks {

        public static final TagKey<Block> BASE_STONE_UNDERGARDEN = tag("base_stone_undergarden");
        public static final TagKey<Block> DEPTHROCK_ORE_REPLACEABLES = tag("depthrock_ore_replaceables");
        public static final TagKey<Block> SHIVERSTONE_ORE_REPLACEABLES = tag("shiverstone_ore_replaceables");
        public static final TagKey<Block> TREMBLECRUST_ORE_REPLACEABLES = tag("tremblecrust_ore_replaceables");
        public static final TagKey<Block> MUSHROOMS = tag("mushrooms");
        public static final TagKey<Block> PORTAL_FRAME_BLOCKS = tag("portal_frame_blocks");
        public static final TagKey<Block> SMOGSTEM_LOGS = tag("smogstem_logs");
        public static final TagKey<Block> WIGGLEWOOD_LOGS = tag("wigglewood_logs");
        public static final TagKey<Block> GRONGLE_LOGS = tag("grongle_logs");
        public static final TagKey<Block> MUNCHER_BREAKABLES = tag("muncher_breakables");

        public static final TagKey<Block> ORES_CLOGGRUM = forgeTag("ores/cloggrum");
        public static final TagKey<Block> ORES_FROSTSTEEL = forgeTag("ores/froststeel");
        public static final TagKey<Block> ORES_UTHERIUM = forgeTag("ores/utherium");
        public static final TagKey<Block> ORES_REGALIUM = forgeTag("ores/regalium");

        public static final TagKey<Block> STORAGE_BLOCKS_CLOGGRUM = forgeTag("storage_blocks/cloggrum");
        public static final TagKey<Block> STORAGE_BLOCKS_FROSTSTEEL = forgeTag("storage_blocks/froststeel");
        public static final TagKey<Block> STORAGE_BLOCKS_UTHERIUM = forgeTag("storage_blocks/utherium");
        public static final TagKey<Block> STORAGE_BLOCKS_REGALIUM = forgeTag("storage_blocks/regalium");
        public static final TagKey<Block> STORAGE_BLOCKS_RAW_CLOGGRUM = forgeTag("storage_blocks/raw_cloggrum");
        public static final TagKey<Block> STORAGE_BLOCKS_RAW_FROSTSTEEL = forgeTag("storage_blocks/raw_froststeel");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Undergarden.MODID, name));
        }
        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation(ForgeVersion.MOD_ID, name));
        }
    }

    public static class Entities {

        public static final TagKey<EntityType<?>> ROTSPAWN = tag("rotspawn");
        public static final TagKey<EntityType<?>> CAVERN_CREATURE = tag("cavern_creature");
        public static final TagKey<EntityType<?>> IMMUNE_TO_VIRULENT_MIX = tag("immune_to_virulent_mix");

        private static TagKey<EntityType<?>> tag(String name) {
            return EntityTypeTags.create(new ResourceLocation(Undergarden.MODID, name).toString());
        }
    }

    public static class Fluids {

        public static final TagKey<Fluid> VIRULENT = tag("virulent");

        private static TagKey<Fluid> tag(String name) {
            return FluidTags.create(new ResourceLocation(Undergarden.MODID, name));
        }
    }

    public static class Biomes {

        public static final TagKey<Biome> HAS_CATACOMBS = tag("has_structure/catacombs");

        private static TagKey<Biome> tag(String name) {
            return BiomeTags.create(new ResourceLocation(Undergarden.MODID, name).toString());
        }
    }
}