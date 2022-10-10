package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.*;

import java.util.function.Supplier;

public class UGLang extends LanguageProvider {

    public UGLang(DataGenerator gen) {
        super(gen, Undergarden.MODID, "en_us");
    }

    private void addItemGroup(CreativeModeTab group, String name) {
        add(group.getDisplayName().getString(), name);
    }

    private void addAdvTitle(String advancementTitle, String name) {
        add("advancement.undergarden." + advancementTitle + ".title", name);
    }

    private void addAdvDesc(String advancementTitle, String name) {
        add("advancement.undergarden." + advancementTitle + ".desc", name);
    }

    private void addSubtitle(String category, String subtitleName, String name) {
        add("subtitles." + category + "." + subtitleName, name);
    }

    private void addBiome(ResourceKey<Biome> biomeKey, String name) {
        add("biome.undergarden." + biomeKey.location().getPath(), name);
    }

    private void addDeath(String deathName, String name) {
        add("death.attack." + deathName, name);
    }

    private void addPotion(Supplier<? extends Potion> potion, String name) {
        add("item.minecraft.potion.effect." + ForgeRegistries.POTIONS.getKey(potion.get()).getPath(), "Potion of " + name);
        add("item.minecraft.splash_potion.effect." + ForgeRegistries.POTIONS.getKey(potion.get()).getPath(), "Splash Potion of " + name);
        add("item.minecraft.lingering_potion.effect." + ForgeRegistries.POTIONS.getKey(potion.get()).getPath(), "Lingering Potion of " + name);
        add("item.minecraft.tipped_arrow.effect." + ForgeRegistries.POTIONS.getKey(potion.get()).getPath(), "Arrow of " + name);
    }

    @Override
    protected void addTranslations() {
        addBlock(UGBlocks.UNDERGARDEN_PORTAL, "Undergarden Portal");

        addBlock(UGBlocks.SHARD_TORCH, "Shard Torch");

        addBlock(UGBlocks.DEPTHROCK, "Depthrock");
        addBlock(UGBlocks.POLISHED_DEPTHROCK, "Polished Depthrock");
        addBlock(UGBlocks.DEPTHROCK_BRICKS, "Depthrock Bricks");
        addBlock(UGBlocks.CRACKED_DEPTHROCK_BRICKS, "Cracked Depthrock Bricks");
        addBlock(UGBlocks.CHISELED_DEPTHROCK_BRICKS, "Chiseled Depthrock Bricks");
        addBlock(UGBlocks.DEPTHROCK_TILES, "Depthrock Tiles");
        addBlock(UGBlocks.DEPTHROCK_STAIRS, "Depthrock Stairs");
        addBlock(UGBlocks.POLISHED_DEPTHROCK_STAIRS, "Polished Depthrock Stairs");
        addBlock(UGBlocks.DEPTHROCK_BRICK_STAIRS, "Depthrock Brick Stairs");
        addBlock(UGBlocks.DEPTHROCK_TILE_STAIRS, "Depthrock Tile Stairs");
        addBlock(UGBlocks.DEPTHROCK_SLAB, "Depthrock Slab");
        addBlock(UGBlocks.POLISHED_DEPTHROCK_SLAB, "Polished Depthrock Slab");
        addBlock(UGBlocks.DEPTHROCK_BRICK_SLAB, "Depthrock Brick Slab");
        addBlock(UGBlocks.DEPTHROCK_TILE_SLAB, "Depthrock Tile Slab");
        addBlock(UGBlocks.DEPTHROCK_WALL, "Depthrock Wall");
        addBlock(UGBlocks.POLISHED_DEPTHROCK_WALL, "Polished Depthrock Wall");
        addBlock(UGBlocks.DEPTHROCK_BRICK_WALL, "Depthrock Brick Wall");
        addBlock(UGBlocks.DEPTHROCK_BUTTON, "Depthrock Button");
        addBlock(UGBlocks.DEPTHROCK_PRESSURE_PLATE, "Depthrock Pressure Plate");

        addBlock(UGBlocks.SHIVERSTONE, "Shiverstone");
        addBlock(UGBlocks.SHIVERSTONE_BRICKS, "Shiverstone Bricks");
        addBlock(UGBlocks.CRACKED_SHIVERSTONE_BRICKS, "Cracked Shiverstone Bricks");
        addBlock(UGBlocks.CHISELED_SHIVERSTONE_BRICKS, "Chiseled Shiverstone Bricks");
        addBlock(UGBlocks.SHIVERSTONE_STAIRS, "Shiverstone Stairs");
        addBlock(UGBlocks.SHIVERSTONE_BRICK_STAIRS, "Shiverstone Brick Stairs");
        addBlock(UGBlocks.SHIVERSTONE_SLAB, "Shiverstone Slab");
        addBlock(UGBlocks.SHIVERSTONE_BRICK_SLAB, "Shiverstone Brick Slab");
        addBlock(UGBlocks.SHIVERSTONE_WALL, "Shiverstone Wall");
        addBlock(UGBlocks.SHIVERSTONE_BRICK_WALL, "Shiverstone Brick Wall");
        addBlock(UGBlocks.SHIVERSTONE_BUTTON, "Shiverstone Button");
        addBlock(UGBlocks.SHIVERSTONE_PRESSURE_PLATE, "Shiverstone Pressure Plate");

        addBlock(UGBlocks.TREMBLECRUST, "Tremblecrust");
        addBlock(UGBlocks.LOOSE_TREMBLECRUST, "Loose Tremblecrust");
        addBlock(UGBlocks.TREMBLECRUST_BRICKS, "Tremblecrust Bricks");
        addBlock(UGBlocks.CRACKED_TREMBLECRUST_BRICKS, "Cracked Tremblecrust Bricks");
        addBlock(UGBlocks.CHISELED_TREMBLECRUST_BRICKS, "Chiseled Tremblecrust Bricks");
        addBlock(UGBlocks.TREMBLECRUST_STAIRS, "Tremblecrust Stairs");
        addBlock(UGBlocks.TREMBLECRUST_BRICK_STAIRS, "Tremblecrust Brick Stairs");
        addBlock(UGBlocks.TREMBLECRUST_SLAB, "Tremblecrust Slab");
        addBlock(UGBlocks.TREMBLECRUST_BRICK_SLAB, "Tremblecrust Brick Slab");
        addBlock(UGBlocks.TREMBLECRUST_WALL, "Tremblecrust Wall");
        addBlock(UGBlocks.TREMBLECRUST_BRICK_WALL, "Tremblecrust Brick Wall");
        addBlock(UGBlocks.TREMBLECRUST_BUTTON, "Tremblecrust Button");
        addBlock(UGBlocks.TREMBLECRUST_PRESSURE_PLATE, "Tremblecrust Pressure Plate");

        addBlock(UGBlocks.DEPTHROCK_COAL_ORE, "Depthrock Coal Ore");
        addBlock(UGBlocks.SHIVERSTONE_COAL_ORE, "Shiverstone Coal Ore");
        addBlock(UGBlocks.DEPTHROCK_IRON_ORE, "Depthrock Iron Ore");
        addBlock(UGBlocks.SHIVERSTONE_IRON_ORE, "Shiverstone Iron Ore");
        addBlock(UGBlocks.DEPTHROCK_GOLD_ORE, "Depthrock Gold Ore");
        addBlock(UGBlocks.DEPTHROCK_DIAMOND_ORE, "Depthrock Diamond Ore");
        addBlock(UGBlocks.SHIVERSTONE_DIAMOND_ORE, "Shiverstone Diamond Ore");
        addBlock(UGBlocks.DEPTHROCK_CLOGGRUM_ORE, "Depthrock Cloggrum Ore");
        addBlock(UGBlocks.SHIVERSTONE_CLOGGRUM_ORE, "Shiverstone Cloggrum Ore");
        addBlock(UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE, "Shiverstone Froststeel Ore");
        addBlock(UGBlocks.DEPTHROCK_UTHERIUM_ORE, "Depthrock Utherium Ore");
        addBlock(UGBlocks.SHIVERSTONE_UTHERIUM_ORE, "Shiverstone Utherium Ore");
        addBlock(UGBlocks.TREMBLECRUST_UTHERIUM_ORE, "Tremblecrust Utherium Ore");
        addBlock(UGBlocks.DEPTHROCK_REGALIUM_ORE, "Depthrock Regalium Ore");
        addBlock(UGBlocks.SHIVERSTONE_REGALIUM_ORE, "Shiverstone Regalium Ore");

        addBlock(UGBlocks.RAW_CLOGGRUM_BLOCK, "Block of Raw Cloggrum");
        addBlock(UGBlocks.RAW_FROSTSTEEL_BLOCK, "Block of Raw Froststeel");
        addBlock(UGBlocks.CLOGGRUM_BLOCK, "Block of Cloggrum");
        addBlock(UGBlocks.FROSTSTEEL_BLOCK, "Block of Froststeel");
        addBlock(UGBlocks.UTHERIUM_BLOCK, "Block of Utherium");
        addBlock(UGBlocks.REGALIUM_BLOCK, "Block of Regalium");
        addBlock(UGBlocks.FORGOTTEN_BLOCK, "Block of Forgotten Metal");

        addBlock(UGBlocks.DEEPTURF_BLOCK, "Deepturf Block");
        addBlock(UGBlocks.ASHEN_DEEPTURF_BLOCK, "Ashen Deepturf Block");
        addBlock(UGBlocks.FROZEN_DEEPTURF_BLOCK, "Frozen Deepturf Block");
        addBlock(UGBlocks.DEEPSOIL, "Deepsoil");
        addBlock(UGBlocks.COARSE_DEEPSOIL, "Coarse Deepsoil");
        addBlock(UGBlocks.DEEPSOIL_FARMLAND, "Deepsoil Farmland");
        addBlock(UGBlocks.GOO, "Scintling Goo");
        addBlock(UGBlocks.GOO_BLOCK, "Scintling Goo Block");
        addBlock(UGBlocks.SMOG_VENT, "Smog Vent");
        addBlock(UGBlocks.SEDIMENT, "Sediment");
        addBlock(UGBlocks.SEDIMENT_GLASS, "Sediment Glass");
        addBlock(UGBlocks.SEDIMENT_GLASS_PANE, "Sediment Glass Pane");
        addBlock(UGBlocks.CLOGGRUM_BARS, "Cloggrum Bars");
        addBlock(UGBlocks.CLOGGRUM_TILES, "Cloggrum Tiles");
        addBlock(UGBlocks.CLOGGRUM_TILE_STAIRS, "Cloggrum Tile Stairs");
        addBlock(UGBlocks.CLOGGRUM_TILE_SLAB, "Cloggrum Tile Slab");
        addBlock(UGBlocks.DEPTHROCK_BED, "Depthrock Bed");
        addBlock(UGBlocks.MOGMOSS_RUG, "Mogmoss Rug");
        addBlock(UGBlocks.CLOGGRUM_LANTERN, "Cloggrum Lantern");

        addBlock(UGBlocks.AMOROUS_BRISTLE, "Amorous Bristle");
        addBlock(UGBlocks.MISERABELL, "Miserabell");
        addBlock(UGBlocks.BUTTERBUNCH, "Butterbunch");
        addBlock(UGBlocks.UNDERBEAN_BUSH, "Underbean Bush");
        addBlock(UGBlocks.BLISTERBERRY_BUSH, "Blisterberry Bush");
        addBlock(UGBlocks.DEEPTURF, "Deepturf");
        addBlock(UGBlocks.ASHEN_DEEPTURF, "Ashen Deepturf");
        addBlock(UGBlocks.FROZEN_DEEPTURF, "Frozen Deepturf");
        addBlock(UGBlocks.TALL_DEEPTURF, "Tall Deepturf");
        addBlock(UGBlocks.SHIMMERWEED, "Shimmerweed");
        addBlock(UGBlocks.TALL_SHIMMERWEED, "Tall Shimmerweed");
        addBlock(UGBlocks.POTTED_SHIMMERWEED, "Potted Shimmerweed");
        addBlock(UGBlocks.DITCHBULB_PLANT, "Ditchbulb Plant");
        addBlock(UGBlocks.GLOOMGOURD, "Gloomgourd");
        addBlock(UGBlocks.CARVED_GLOOMGOURD, "Carved Gloomgourd");
        addBlock(UGBlocks.GLOOM_O_LANTERN, "Gloom o'Lantern");
        addBlock(UGBlocks.SHARD_O_LANTERN, "Shard o'Lantern");
        addBlock(UGBlocks.BOOMGOURD, "Boomgourd");
        addBlock(UGBlocks.GLOOMGOURD_STEM, "Gloomgourd Stem");
        addBlock(UGBlocks.DEPTHROCK_PEBBLES, "Depthrock Pebbles");
        addBlock(UGBlocks.GLITTERKELP, "Glitterkelp");
        addBlock(UGBlocks.GLITTERKELP_PLANT, "Glitterkelp");
        addBlock(UGBlocks.DROOPVINE, "Droopvine");
        addBlock(UGBlocks.DROOPVINE_PLANT, "Droopvine");

        addBlock(UGBlocks.INDIGO_MUSHROOM, "Indigo Mushroom");
        addBlock(UGBlocks.POTTED_INDIGO_MUSHROOM, "Potted Indigo Mushroom");
        addBlock(UGBlocks.INDIGO_MUSHROOM_CAP, "Indigo Mushroom Cap");
        addBlock(UGBlocks.INDIGO_MUSHROOM_STEM, "Indigo Mushroom Stem");

        addBlock(UGBlocks.VEIL_MUSHROOM, "Veiled Mushroom");
        addBlock(UGBlocks.POTTED_VEIL_MUSHROOM, "Potted Veiled Mushroom");
        addBlock(UGBlocks.VEIL_MUSHROOM_CAP, "Veil Mushroom Cap");
        addBlock(UGBlocks.VEIL_MUSHROOM_STEM, "Veil Mushroom Stem");
        addBlock(UGBlocks.MUSHROOM_VEIL, "Mushroom Veil");

        addBlock(UGBlocks.INK_MUSHROOM, "Ink Mushroom");
        addBlock(UGBlocks.POTTED_INK_MUSHROOM, "Potted Ink Mushroom");
        addBlock(UGBlocks.INK_MUSHROOM_CAP, "Ink Mushroom Cap");
        addBlock(UGBlocks.SEEPING_INK, "Seeping Ink");

        addBlock(UGBlocks.BLOOD_MUSHROOM, "Blood Mushroom");
        addBlock(UGBlocks.POTTED_BLOOD_MUSHROOM, "Potted Blood Mushroom");
        addBlock(UGBlocks.BLOOD_MUSHROOM_CAP, "Blood Mushroom Cap");
        addBlock(UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP, "Engorged Blood Mushroom Cap");
        addBlock(UGBlocks.BLOOD_MUSHROOM_STEM, "Blood Mushroom Stem");

        addBlock(UGBlocks.SMOGSTEM_SAPLING, "Smogstem Sapling");
        addBlock(UGBlocks.POTTED_SMOGSTEM_SAPLING, "Potted Smogstem Sapling");
        addBlock(UGBlocks.SMOGSTEM_LOG, "Smogstem Log");
        addBlock(UGBlocks.STRIPPED_SMOGSTEM_LOG, "Stripped Smogstem Log");
        addBlock(UGBlocks.SMOGSTEM_WOOD, "Smogstem Wood");
        addBlock(UGBlocks.STRIPPED_SMOGSTEM_WOOD, "Stripped Smogstem Wood");
        addBlock(UGBlocks.SMOGSTEM_LEAVES, "Smogstem Leaves");
        addBlock(UGBlocks.SMOGSTEM_PLANKS, "Smogstem Planks");
        addBlock(UGBlocks.SMOGSTEM_STAIRS, "Smogstem Stairs");
        addBlock(UGBlocks.SMOGSTEM_SLAB, "Smogstem Slab");
        addBlock(UGBlocks.SMOGSTEM_FENCE, "Smogstem Fence");
        addBlock(UGBlocks.SMOGSTEM_FENCE_GATE, "Smogstem Fence Gate");
        addBlock(UGBlocks.SMOGSTEM_DOOR, "Smogstem Door");
        addBlock(UGBlocks.SMOGSTEM_TRAPDOOR, "Smogstem Trapdoor");
        addBlock(UGBlocks.SMOGSTEM_BUTTON, "Smogstem Button");
        addBlock(UGBlocks.SMOGSTEM_PRESSURE_PLATE, "Smogstem Pressure Plate");
        addBlock(UGBlocks.SMOGSTEM_SIGN, "Smogstem Sign");

        addBlock(UGBlocks.WIGGLEWOOD_SAPLING, "Wigglewood Sapling");
        addBlock(UGBlocks.POTTED_WIGGLEWOOD_SAPLING, "Potted Wigglewood Sapling");
        addBlock(UGBlocks.WIGGLEWOOD_LOG, "Wigglewood Log");
        addBlock(UGBlocks.STRIPPED_WIGGLEWOOD_LOG, "Stripped Wigglewood Log");
        addBlock(UGBlocks.WIGGLEWOOD_WOOD, "Wigglewood.. Wood");
        addBlock(UGBlocks.STRIPPED_WIGGLEWOOD_WOOD, "Stripped Wigglewood Wood");
        addBlock(UGBlocks.WIGGLEWOOD_LEAVES, "Wigglewood Leaves");
        addBlock(UGBlocks.WIGGLEWOOD_PLANKS, "Wigglewood Planks");
        addBlock(UGBlocks.WIGGLEWOOD_STAIRS, "Wigglewood Stairs");
        addBlock(UGBlocks.WIGGLEWOOD_SLAB, "Wigglewood Slab");
        addBlock(UGBlocks.WIGGLEWOOD_FENCE, "Wigglewood Fence");
        addBlock(UGBlocks.WIGGLEWOOD_FENCE_GATE, "Wigglewood Fence Gate");
        addBlock(UGBlocks.WIGGLEWOOD_DOOR, "Wigglewood Door");
        addBlock(UGBlocks.WIGGLEWOOD_TRAPDOOR, "Wigglewood Trapdoor");
        addBlock(UGBlocks.WIGGLEWOOD_BUTTON, "Wigglewood Button");
        addBlock(UGBlocks.WIGGLEWOOD_PRESSURE_PLATE, "Wigglewood Pressure Plate");
        addBlock(UGBlocks.WIGGLEWOOD_SIGN, "Wigglewood Sign");

        addBlock(UGBlocks.GRONGLE_SAPLING, "Grongle Sapling");
        addBlock(UGBlocks.POTTED_GRONGLE_SAPLING, "Potted Grongle Sapling");
        addBlock(UGBlocks.GRONGLE_LOG, "Grongle Log");
        addBlock(UGBlocks.STRIPPED_GRONGLE_LOG, "Stripped Grongle Log");
        addBlock(UGBlocks.GRONGLE_WOOD, "Grongle Wood");
        addBlock(UGBlocks.STRIPPED_GRONGLE_WOOD, "Stripped Grongle Wood");
        addBlock(UGBlocks.GRONGLE_LEAVES, "Grongle Leaves");
        addBlock(UGBlocks.HANGING_GRONGLE_LEAVES, "Hanging Grongle Leaves");
        addBlock(UGBlocks.GRONGLE_PLANKS, "Grongle Planks");
        addBlock(UGBlocks.GRONGLE_STAIRS, "Grongle Stairs");
        addBlock(UGBlocks.GRONGLE_SLAB, "Grongle Slab");
        addBlock(UGBlocks.GRONGLE_FENCE, "Grongle Fence");
        addBlock(UGBlocks.GRONGLE_FENCE_GATE, "Grongle Fence Gate");
        addBlock(UGBlocks.GRONGLE_DOOR, "Grongle Door");
        addBlock(UGBlocks.GRONGLE_TRAPDOOR, "Grongle Trapdoor");
        addBlock(UGBlocks.GRONGLE_BUTTON, "Grongle Button");
        addBlock(UGBlocks.GRONGLE_PRESSURE_PLATE, "Grongle Pressure Plate");
        addBlock(UGBlocks.GRONGLE_SIGN, "Grongle Sign");

        addBlock(UGBlocks.GRONGLET, "Gronglet");

        addBlock(UGBlocks.VIRULENT_MIX, "Virulent Mix");

        addBlock(UGBlocks.VIRULENT_MIX_CAULDRON, "Virulent Mix Cauldron");

        addItem(UGItems.MAMMOTH_DISC, "Music Disc");
        add(UGItems.MAMMOTH_DISC.get().getDescriptionId() + ".desc", "Screem - Mammoth");
        addItem(UGItems.LIMAX_MAXIMUS_DISC, "Music Disc");
        add(UGItems.LIMAX_MAXIMUS_DISC.get().getDescriptionId() + ".desc", "Screem - Limax Maximus");
        addItem(UGItems.RELICT_DISC, "Music Disc");
        add(UGItems.RELICT_DISC.get().getDescriptionId() + ".desc", "Screem - Relict");
        addItem(UGItems.GLOOMPER_ANTHEM_DISC, "Music Disc");
        add(UGItems.GLOOMPER_ANTHEM_DISC.get().getDescriptionId() + ".desc", "Screem - Gloomper Anthem");
        addItem(UGItems.GLOOMPER_SECRET_DISC, "Music Disc");
        add(UGItems.GLOOMPER_SECRET_DISC.get().getDescriptionId() + ".desc", "An AI was given an image of a Gloomper and made this song");
        addItem(UGItems.CATALYST, "Catalyst");
        addItem(UGItems.DEPTHROCK_PEBBLE, "Depthrock Pebble");
        addItem(UGItems.TWISTYTWIG, "Twistytwig");
        addItem(UGItems.RAW_CLOGGRUM, "Raw Cloggrum");
        addItem(UGItems.CLOGGRUM_INGOT, "Cloggrum Ingot");
        addItem(UGItems.CLOGGRUM_NUGGET, "Cloggrum Nugget");
        addItem(UGItems.RAW_FROSTSTEEL, "Raw Froststeel");
        addItem(UGItems.FROSTSTEEL_INGOT, "Froststeel Ingot");
        addItem(UGItems.FROSTSTEEL_NUGGET, "Froststeel Nugget");
        addItem(UGItems.UTHERIC_SHARD, "Utheric Shard");
        addItem(UGItems.UTHERIUM_CRYSTAL, "Utherium Crystal");
        addItem(UGItems.REGALIUM_CRYSTAL, "Regalium Crystal");
        addItem(UGItems.FORGOTTEN_INGOT, "Forgotten Ingot");
        addItem(UGItems.FORGOTTEN_NUGGET, "Forgotten Nugget");
        addItem(UGItems.GLOOMGOURD_SEEDS, "Gloomgourd Seeds");
        addItem(UGItems.DITCHBULB, "Ditchbulb");
        addItem(UGItems.DITCHBULB_PASTE, "Ditchbulb Paste");
        addItem(UGItems.BRUTE_TUSK, "Brute Tusk");
        addItem(UGItems.MOGMOSS, "Mogmoss");
        addItem(UGItems.GOO_BALL, "Scintling Goo Ball");
        addItem(UGItems.ROTTEN_BLISTERBERRY, "Rotten Blisterberry");
        addItem(UGItems.BLISTERBOMB, "Blisterbomb");
        addItem(UGItems.UNDERBEAN_STICK, "Underbean on a Stick");

        addItem(UGItems.MASTICATOR_SCALES, "Masticator Scales");
        addItem(UGItems.MASTICATED_CHESTPLATE, "Masticated Chestplate");

        addItem(UGItems.CLOGGRUM_BATTLEAXE, "Cloggrum Battle Axe");
        addItem(UGItems.CLOGGRUM_SWORD, "Cloggrum Sword");
        addItem(UGItems.CLOGGRUM_PICKAXE, "Cloggrum Pickaxe");
        addItem(UGItems.CLOGGRUM_AXE, "Cloggrum Axe");
        addItem(UGItems.CLOGGRUM_SHOVEL, "Cloggrum Shovel");
        addItem(UGItems.CLOGGRUM_HOE, "Cloggrum Hoe");
        addItem(UGItems.CLOGGRUM_SHIELD, "Cloggrum Shield");

        addItem(UGItems.FROSTSTEEL_SWORD, "Froststeel Sword");
        addItem(UGItems.FROSTSTEEL_PICKAXE, "Froststeel Pickaxe");
        addItem(UGItems.FROSTSTEEL_AXE, "Froststeel Axe");
        addItem(UGItems.FROSTSTEEL_SHOVEL, "Froststeel Shovel");
        addItem(UGItems.FROSTSTEEL_HOE, "Froststeel Hoe");

        addItem(UGItems.UTHERIUM_SWORD, "Utherium Sword");
        addItem(UGItems.UTHERIUM_PICKAXE, "Utherium Pickaxe");
        addItem(UGItems.UTHERIUM_AXE, "Utherium Axe");
        addItem(UGItems.UTHERIUM_SHOVEL, "Utherium Shovel");
        addItem(UGItems.UTHERIUM_HOE, "Utherium Hoe");

        addItem(UGItems.FORGOTTEN_BATTLEAXE, "Forgotten Battle Axe");
        addItem(UGItems.FORGOTTEN_SWORD, "Forgotten Sword");
        addItem(UGItems.FORGOTTEN_PICKAXE, "Forgotten Pickaxe");
        addItem(UGItems.FORGOTTEN_AXE, "Forgotten Axe");
        addItem(UGItems.FORGOTTEN_SHOVEL, "Forgotten Shovel");
        addItem(UGItems.FORGOTTEN_HOE, "Forgotten Hoe");

        addItem(UGItems.SMOGSTEM_BOAT, "Smogstem Boat");
        addItem(UGItems.SMOGSTEM_CHEST_BOAT, "Smogstem Boat with Chest");
        addItem(UGItems.WIGGLEWOOD_BOAT, "Wigglewood Boat");
        addItem(UGItems.WIGGLEWOOD_CHEST_BOAT, "Wigglewood Boat with Chest");
        addItem(UGItems.GRONGLE_BOAT, "Grongle Boat");
        addItem(UGItems.GRONGLE_CHEST_BOAT, "Grongle Boat with Chest");

        addItem(UGItems.SLINGSHOT, "Slingshot");

        addItem(UGItems.VIRULENT_MIX_BUCKET, "Virulent Mix Bucket");

        addItem(UGItems.GWIBLING_BUCKET, "Bucket of Gwibling");

        addItem(UGItems.CLOGGRUM_HELMET, "Cloggrum Helmet");
        addItem(UGItems.CLOGGRUM_CHESTPLATE, "Cloggrum Chestplate");
        addItem(UGItems.CLOGGRUM_LEGGINGS, "Cloggrum Leggings");
        addItem(UGItems.CLOGGRUM_BOOTS, "Cloggrum Boots");

        addItem(UGItems.FROSTSTEEL_HELMET, "Froststeel Helmet");
        addItem(UGItems.FROSTSTEEL_CHESTPLATE, "Froststeel Chestplate");
        addItem(UGItems.FROSTSTEEL_LEGGINGS, "Froststeel Leggings");
        addItem(UGItems.FROSTSTEEL_BOOTS, "Froststeel Boots");

        addItem(UGItems.UTHERIUM_HELMET, "Utherium Helmet");
        addItem(UGItems.UTHERIUM_CHESTPLATE, "Utherium Chestplate");
        addItem(UGItems.UTHERIUM_LEGGINGS, "Utherium Leggings");
        addItem(UGItems.UTHERIUM_BOOTS, "Utherium Boots");

        addItem(UGItems.DROOPFRUIT, "Droopfruit");
        addItem(UGItems.UNDERBEANS, "Underbeans");
        addItem(UGItems.ROASTED_UNDERBEANS, "Roasted Underbeans");
        addItem(UGItems.BLISTERBERRY, "Blisterberry");
        addItem(UGItems.GLOOMGOURD_PIE, "Gloomgourd Pie");
        addItem(UGItems.RAW_DWELLER_MEAT, "Raw Dweller Meat");
        addItem(UGItems.DWELLER_STEAK, "Dweller Steak");
        addItem(UGItems.RAW_GWIBLING, "Raw Gwibling");
        addItem(UGItems.COOKED_GWIBLING, "Cooked Gwibling");
        addItem(UGItems.RAW_GLOOMPER_LEG, "Raw Gloomper Leg");
        addItem(UGItems.GLOOMPER_LEG, "Gloomper Leg");
        addItem(UGItems.BLOOD_GLOBULE, "Blood Globule");
        addItem(UGItems.BLOODY_STEW, "Bloody Stew");
        addItem(UGItems.INDIGO_STEW, "Indigo Stew");
        addItem(UGItems.INKY_STEW, "Inky Stew");
        addItem(UGItems.VEILED_STEW, "Veiled Stew");

        addItem(UGItems.DWELLER_SPAWN_EGG, "Dweller Spawn Egg");
        addItem(UGItems.GWIBLING_SPAWN_EGG, "Gwibling Spawn Egg");
        addItem(UGItems.ROTLING_SPAWN_EGG, "Rotling Spawn Egg");
        addItem(UGItems.ROTWALKER_SPAWN_EGG, "Rotwalker Spawn Egg");
        addItem(UGItems.ROTBEAST_SPAWN_EGG, "Rotbeast Spawn Egg");
        addItem(UGItems.BRUTE_SPAWN_EGG, "Brute Spawn Egg");
        addItem(UGItems.SCINTLING_SPAWN_EGG, "Scintling Spawn Egg");
        addItem(UGItems.GLOOMPER_SPAWN_EGG, "Gloomper Spawn Egg");
        addItem(UGItems.STONEBORN_SPAWN_EGG, "Stoneborn Spawn Egg");
        addItem(UGItems.NARGOYLE_SPAWN_EGG, "Nargoyle Spawn Egg");
        addItem(UGItems.MUNCHER_SPAWN_EGG, "Muncher Spawn Egg");
        addItem(UGItems.SPLOOGIE_SPAWN_EGG, "Sploogie Spawn Egg");
        addItem(UGItems.GWIB_SPAWN_EGG, "Gwib Spawn Egg");
        addItem(UGItems.MOG_SPAWN_EGG, "Mog Spawn Egg");
        addItem(UGItems.MASTICATOR_SPAWN_EGG, "Masticator Spawn Egg");
        addItem(UGItems.FORGOTTEN_GUARDIAN_SPAWN_EGG, "Forgotten Guardian Spawn Egg");

        addBiome(UGBiomes.ANCIENT_SEA, "Ancient Sea");
        addBiome(UGBiomes.BARREN_ABYSS, "Barren Abyss");
        addBiome(UGBiomes.BLOOD_MUSHROOM_BOG, "Blood Mushroom Bog");
        addBiome(UGBiomes.DEAD_SEA, "Dead Sea");
        addBiome(UGBiomes.DENSE_FOREST, "Dense Forest");
        addBiome(UGBiomes.FORGOTTEN_FIELD, "Forgotten Field");
        addBiome(UGBiomes.FROSTFIELDS, "Frostfields");
        addBiome(UGBiomes.GRONGLEGROWTH, "Gronglegrowth");
        addBiome(UGBiomes.ICY_SEA, "Icy Sea");
        addBiome(UGBiomes.INDIGO_MUSHROOM_BOG, "Indigo Mushroom Bog");
        addBiome(UGBiomes.INK_MUSHROOM_BOG, "Ink Mushroom Bog");
        addBiome(UGBiomes.SMOGSTEM_FOREST, "Smogstem Forest");
        addBiome(UGBiomes.SMOG_SPIRES, "Smog Spires");
        addBiome(UGBiomes.VEIL_MUSHROOM_BOG, "Veil Mushroom Bog");
        addBiome(UGBiomes.WIGGLEWOOD_FOREST, "Wigglewood Forest");

        addEntityType(UGEntityTypes.BOAT, "Boat");
        addEntityType(UGEntityTypes.CHEST_BOAT, "Boat with Chest");
        addEntityType(UGEntityTypes.MINION, "Forgotten Minion");
        addEntityType(UGEntityTypes.DWELLER, "Dweller");
        addEntityType(UGEntityTypes.GWIBLING, "Gwibling");
        addEntityType(UGEntityTypes.ROTLING, "Rotling");
        addEntityType(UGEntityTypes.ROTWALKER, "Rotwalker");
        addEntityType(UGEntityTypes.ROTBEAST, "Rotbeast");
        addEntityType(UGEntityTypes.BRUTE, "Brute");
        addEntityType(UGEntityTypes.SCINTLING, "Scintling");
        addEntityType(UGEntityTypes.GLOOMPER, "Gloomper");
        addEntityType(UGEntityTypes.STONEBORN, "Stoneborn");
        addEntityType(UGEntityTypes.NARGOYLE, "Nargoyle");
        addEntityType(UGEntityTypes.MUNCHER, "Muncher");
        addEntityType(UGEntityTypes.SPLOOGIE, "Sploogie");
        addEntityType(UGEntityTypes.GWIB, "Gwib");
        addEntityType(UGEntityTypes.MOG, "Mog");

        addEntityType(UGEntityTypes.MASTICATOR, "Masticator");
        addEntityType(UGEntityTypes.FORGOTTEN_GUARDIAN, "Forgotten Guardian");

        addItemGroup(UGCreativeModeTabs.GROUP, "The Undergarden");

        addEffect(UGEffects.GOOEY, "Gooey");
        addEffect(UGEffects.BRITTLENESS, "Brittleness");
        addEffect(UGEffects.FEATHERWEIGHT, "Featherweight");
        addEffect(UGEffects.VIRULENT_RESISTANCE, "Virulent Resistance");
        addEffect(UGEffects.VIRULENCE, "Virulence");

        //JEED compat
        add("effect.undergarden.gooey.description", "Scintling Goo will constantly be placed under victim's feet.");
        add("effect.undergarden.brittleness.description", "The higher the victim's armor value is, the more damage they will take. Amount of damage received scales with potion level.");
        add("effect.undergarden.featherweight.description", "When damaged, victim will receive increased knockback. Amount of knockback scales with potion level.");
        add("effect.undergarden.virulent_resistance.description", "Grants immunity to Virulence.");
        add("effect.undergarden.virulence.description", "Similar to poison, but does more damage at a slower rate. Damage is suppressed by Virulent Resistance.");

        addPotion(UGPotions.BRITTLENESS, "Brittleness");
        addPotion(UGPotions.FEATHERWEIGHT, "Featherweight");
        addPotion(UGPotions.VIRULENT_RESISTANCE, "Virulent Resistance");
        addPotion(UGPotions.GLOWING, "Glowing");

        addEnchantment(UGEnchantments.RICOCHET, "Ricochet");
        addEnchantment(UGEnchantments.LONGEVITY, "Longevity");
        addEnchantment(UGEnchantments.SELF_SLING, "Self Sling");

        //Enchantment Descriptions compat
        add("enchantment.undergarden.ricochet.desc", "Projectiles fired by the Slingshot will bounce off block surfaces.");
        add("enchantment.undergarden.longevity.desc", "Increases max durability of the Slingshot.");
        add("enchantment.undergarden.self_sling.desc", "Instead of firing ammo, the Slingshot fires you. Requires you to be on the ground.");

        addAdvTitle("root", "The Undergarden");

        addAdvTitle("catalyst", "One Ticket Please");
        addAdvDesc("catalyst", "Create the Catalyst.");

        addAdvTitle("enter_undergarden", "Enter the Undergarden");
        addAdvDesc("enter_undergarden", "The forgotten land awaits...");

        addAdvTitle("stack_of_gloomgourds", "Gourd Lord");
        addAdvDesc("stack_of_gloomgourds", "Acquire a stack of Gloomgourds.");

        addAdvTitle("slingshot", "Your New Best Friend");
        addAdvDesc("slingshot", "Craft a Slingshot.");

        addAdvTitle("shoot_slingshot", "Flinging Rocks");
        addAdvDesc("shoot_slingshot", "Shoot a pebble at something.");

        addAdvTitle("shoot_slingshot_goo", "Flying Inconvenience");
        addAdvDesc("shoot_slingshot_goo", "Shoot a Scintling Goo Ball at something to annoy it.");

        addAdvTitle("shoot_slingshot_rotten_blisterberry", "This Isn't Safe");
        addAdvDesc("shoot_slingshot_rotten_blisterberry", "Shoot a Rotten Blisterberry at something, and hopefully keep all your limbs.");

        addAdvTitle("shoot_slingshot_gronglet", "Ethically Questionable");
        addAdvDesc("shoot_slingshot_gronglet", "Shoot a Gronglet! Is this okay..?");

        addAdvTitle("slingshot_20_damage", "Sniping's a Good Job");
        addAdvDesc("slingshot_20_damage", "Deal 20 or more damage to something with a single pebble...!?");

        addAdvTitle("kill_rotling", "Birth Control");
        addAdvDesc("kill_rotling", "Slay the weakest of the Rotspawn, the Rotling.");

        addAdvTitle("shard_torch", "Warding Device");
        addAdvDesc("shard_torch", "Craft a Shard Torch, a torch that can damage Rotspawn in its vicinity.");

        addAdvTitle("kill_all_rotspawn", "Rotbane");
        addAdvDesc("kill_all_rotspawn", "Slay all kinds of Rotspawn.");

        addAdvTitle("all_undergarden_biomes", "Subterranean Cartographer");
        addAdvDesc("all_undergarden_biomes", "Discover every Undergarden biome.");

        addAdvTitle("mine_ore", "Deep Extraction");
        addAdvDesc("mine_ore", "Acquire any Undergarden ore.");

        addAdvTitle("underbeans", "Glorious Beans!");
        addAdvDesc("underbeans", "Find and pick an Underbean Bush.");

        addAdvTitle("stoneborn_trade", "Interdimensional Business");
        addAdvDesc("stoneborn_trade", "Trade with a Stoneborn.");

        addAdvTitle("catch_gwibling", "Weird Fish");
        addAdvDesc("catch_gwibling", "Catch a Gwibling with a bucket.");

        addAdvTitle("catacombs", "Forgotten Halls");
        addAdvDesc("catacombs", "Enter some Catacombs.");

        addAdvTitle("plant_gloomgourd", "Purple Pumpkins");
        addAdvDesc("plant_gloomgourd", "Plant a Gloomgourd seed.");

        addAdvTitle("kill_forgotten_guardian", "Decommissioned");
        addAdvDesc("kill_forgotten_guardian", "Slay a Forgotten Guardian.");

        addAdvTitle("forgotten_ingot", "What Now Is");
        addAdvDesc("forgotten_ingot", "Forge a Forgotten Ingot from a Forgotten Guardian's nuggets.");

        addAdvTitle("forgotten_tools", "Forgotten Arsenal");
        addAdvDesc("forgotten_tools", "Use Forgotten Ingots to upgrade Cloggrum tools and make all 5 Forgotten tools.");

        addAdvTitle("kill_scintling", "Terrible Person");
        addAdvDesc("kill_scintling", "You killed an innocent Scintling. You monster...");

        addAdvTitle("summon_minion", "Buildin' a Sentry");
        addAdvDesc("summon_minion", "Create a Forgotten Minion using a Forgotten Block and a Carved Gloomgourd.");

        addAdvTitle("cloggrum_armor", "Cover Me in Coprolites");
        addAdvDesc("cloggrum_armor", "Obtain a full suit of Cloggrum armor.");

        addAdvTitle("all_ore_blocks", "Collector's Edition");
        addAdvDesc("all_ore_blocks", "Collect one block of every Undergarden ore.");

        addAdvTitle("gloomper_secret_disc", "Death Gloomps");
        addAdvDesc("gloomper_secret_disc", "Obtain the secret music disc.");

        addAdvTitle("forgotten_battleaxe", "Axe of Legends");
        addAdvDesc("forgotten_battleaxe", "Upgrade a Cloggrum Battle Axe with a Forgotten Ingot.");

        //addAdvTitle("otherside_root", "The Otherside");
        //addAdvDesc("otherside_root", "Enter the realm of madness.");

        add("tooltip.froststeel_sword", "Slows targets.");
        add("tooltip.utheric_sword", "Deals 1.5x damage to Rotspawn.");
        add("tooltip.cloggrum_boots", "Scintling Goo doesn't slow you down when worn.");
        add("tooltip.forgotten_sword", "Deals 1.5x damage to non-boss Undergarden mobs.");
        add("tooltip.pebble", "Can be used as Slingshot ammo.");
        add("tooltip.forgotten_tool", "Mines Undergarden blocks 1.5x as fast.");

        addSubtitle("block", "undergarden_portal.ambient", "Undergarden Portal beckons");
        addSubtitle("block", "undergarden_portal.activate", "Undergarden Portal activates");
        addSubtitle("block", "undergarden_portal.travel", "Travelling through Undergarden portal");

        addSubtitle("block", "virulent.flow", "Virulent Mix flows");
        addSubtitle("block", "virulent.bubble", "Virulent Mix bubbles");

        addSubtitle("block", "gronglet.ambient", "Gronglet chirps");
        addSubtitle("block", "gronglet.burn", "Gronglet shrieks");

        addSubtitle("item", "blisterbomb", "Blisterbomb thrown");

        addSubtitle("item", "slingshot.draw", "Slingshot drawn");
        addSubtitle("item", "slingshot.shoot", "Slingshot fired");
        addSubtitle("item", "slingshot.gronglet_shoot", "Slingshot fired");

        addSubtitle("item", "blisterberry_bush.pick", "Blisterberry Bush picked");

        addSubtitle("item", "ditchbulb_paste.use", "Ditchbulb Paste squelches");

        addSubtitle("entity", "boomgourd.primed", "Boomgourd fizzes");

        addSubtitle("entity", "dweller.ambient", "Dweller grumbles");
        addSubtitle("entity", "dweller.hurt", "Dweller hurts");
        addSubtitle("entity", "dweller.death", "Dweller dies");

        addSubtitle("entity", "rotwalker.ambient", "Rotwalker groans");
        addSubtitle("entity", "rotwalker.hurt", "Rotwalker hurts");
        addSubtitle("entity", "rotwalker.death", "Rotwalker dies");

        addSubtitle("entity", "rotbeast.ambient", "Rotbeast groans");
        addSubtitle("entity", "rotbeast.hurt", "Rotbeast hurts");
        addSubtitle("entity", "rotbeast.death", "Rotbeast dies");
        addSubtitle("entity", "rotbeast.attack", "Rotbeast attacks");

        addSubtitle("entity", "brute.ambient", "Brute exhales");
        addSubtitle("entity", "brute.hurt", "Brute hurts");
        addSubtitle("entity", "brute.death", "Brute dies");

        addSubtitle("entity", "gloomper.ambient", "Gloomper croaks");
        addSubtitle("entity", "gloomper.hurt", "Gloomper hurts");
        addSubtitle("entity", "gloomper.death", "Gloomper dies");
        addSubtitle("entity", "gloomper.hop", "Gloomper hops");
        addSubtitle("entity", "gloomper.fart", "Gloomper farts");

        addSubtitle("entity", "stoneborn.speaking", "Stoneborn speaks");
        addSubtitle("entity", "stoneborn.pleased", "Stoneborn pleased");
        addSubtitle("entity", "stoneborn.hurt", "Stoneborn hurts");
        addSubtitle("entity", "stoneborn.angry", "Stoneborn angered");
        addSubtitle("entity", "stoneborn.confused", "Stoneborn confused");
        addSubtitle("entity", "stoneborn.chant", "Stoneborn chanting");
        addSubtitle("entity", "stoneborn.death", "Stoneborn dies");

        addSubtitle("entity", "rotling.ambient", "Rotling groans");
        addSubtitle("entity", "rotling.hurt", "Rotling hurts");
        addSubtitle("entity", "rotling.death", "Rotling dies");

        addSubtitle("entity", "forgotten_guardian.ambient", "Forgotten Guardian creaks");
        addSubtitle("entity", "forgotten_guardian.hurt", "Forgotten Guardian hurts");
        addSubtitle("entity", "forgotten_guardian.death", "Forgotten Guardian dies");
        addSubtitle("entity", "forgotten_guardian.attack", "Forgotten Guardian attacks");
        addSubtitle("entity", "forgotten_guardian.deflect", "Forgotten Guardian deflects");

        addSubtitle("entity", "minion.shoot", "Forgotten Minion shoots");
        addSubtitle("entity", "minion.death", "Forgotten Minion dies");
        addSubtitle("entity", "minion.repair", "Forgotten Minion repaired");

        addSubtitle("entity", "nargoyle.hurt", "Nargoyle hurts");
        addSubtitle("entity", "nargoyle.death", "Nargoyle dies");
        addSubtitle("entity", "nargoyle.attack", "Nargoyle leaps");

        addSubtitle("entity", "muncher.ambient", "Muncher grumbles");
        addSubtitle("entity", "muncher.hurt", "Muncher hurts");
        addSubtitle("entity", "muncher.death", "Muncher dies");
        addSubtitle("entity", "muncher.chew", "Muncher chews");

        addSubtitle("entity", "sploogie.ambient", "Sploogie squeaks");
        addSubtitle("entity", "sploogie.hurt", "Sploogie hurts");
        addSubtitle("entity", "sploogie.death", "Sploogie dies");
        addSubtitle("entity", "sploogie.spit", "Sploogie spits");

        addSubtitle("entity", "masticator.ambient", "Masticator roars");
        addSubtitle("entity", "masticator.hurt", "Masticator hurts");
        addSubtitle("entity", "masticator.death", "Masticator dies");
        addSubtitle("entity", "masticator.eat", "Masticator eats");

        addSubtitle("entity", "gwib.hurt", "Gwib hurts");
        addSubtitle("entity", "gwib.death", "Gwib dies");
        addSubtitle("entity", "gwib.flop", "Gwib flops");

        addSubtitle("entity", "gwibling.hurt", "Gwibling hurts");
        addSubtitle("entity", "gwibling.death", "Gwibling dies");
        addSubtitle("entity", "gwibling.flop", "Gwibling flops");

        addSubtitle("entity", "mog.ambient", "Mog squeaks");
        addSubtitle("entity", "mog.hurt", "Mog hurts");
        addSubtitle("entity", "mog.death", "Mog dies");

        addSubtitle("entity", "scintling.hurt", "Scintling hurts");
        addSubtitle("entity", "scintling.death", "Scintling dies");

        addDeath("blisterberry_bush", "%1$s was poked by a Blisterberry Bush");
        addDeath("blisterberry_bush.player", "%1$s was poked by a Blisterberry Bush whilst trying to escape %2$s");
        addDeath("shard_torch", "%1$s was killed by a Shard Torch's magic");
        addDeath("shard_torch.player", "%1$s was killed by a Shard Torch's magic whilst trying to escape %2$s");
    }
}