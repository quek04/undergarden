package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.data.LanguageProvider;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.*;

import java.util.function.Supplier;

public class UGLang extends LanguageProvider {

    public UGLang(DataGenerator gen) {
        super(gen, Undergarden.MODID, "en_us");
    }

    private void addItemGroup(ItemGroup group, String name) {
        add(group.getDisplayName().getString(), name);
    }

    private void addAdvTitle(String advancementTitle, String name) {
        add("advancement." + advancementTitle + ".title", name);
    }

    private void addAdvDesc(String advancementTitle, String name) {
        add("advancement." + advancementTitle + ".desc", name);
    }

    private void addSubtitle(String category, String subtitleName, String name) {
        add("subtitles." + category + "." + subtitleName, name);
    }

    private void addBiome(RegistryKey<Biome> biomeKey, String name) {
        add("biome.undergarden." + biomeKey.location().getPath(), name);
    }

    private void addDeath(String deathName, String name) {
        add("death.attack." + deathName, name);
    }

    private void addPotion(Supplier<? extends Potion> potion, String name) {
        add("item.minecraft.potion.effect." + potion.get().getRegistryName().getPath(), "Potion of " + name);
        add("item.minecraft.splash_potion.effect." + potion.get().getRegistryName().getPath(), "Splash Potion of " + name);
        add("item.minecraft.lingering_potion.effect." + potion.get().getRegistryName().getPath(), "Lingering Potion of " + name);
        add("item.minecraft.tipped_arrow.effect." + potion.get().getRegistryName().getPath(), "Arrow of " + name);
    }

    @Override
    protected void addTranslations() {
        addBlock(UGBlocks.UNDERGARDEN_PORTAL, "Undergarden Portal");

        addBlock(UGBlocks.SHARD_TORCH, "Shard Torch");

        addBlock(UGBlocks.DEPTHROCK, "Depthrock");
        addBlock(UGBlocks.DEPTHROCK_BRICKS, "Depthrock Bricks");
        addBlock(UGBlocks.CRACKED_DEPTHROCK_BRICKS, "Cracked Depthrock Bricks");
        addBlock(UGBlocks.CHISELED_DEPTHROCK_BRICKS, "Chiseled Depthrock Bricks");
        addBlock(UGBlocks.DEPTHROCK_TILES, "Depthrock Tiles");
        addBlock(UGBlocks.DEPTHROCK_STAIRS, "Depthrock Stairs");
        addBlock(UGBlocks.DEPTHROCK_BRICK_STAIRS, "Depthrock Brick Stairs");
        addBlock(UGBlocks.DEPTHROCK_TILE_STAIRS, "Depthrock Tile Stairs");
        addBlock(UGBlocks.DEPTHROCK_SLAB, "Depthrock Slab");
        addBlock(UGBlocks.DEPTHROCK_BRICK_SLAB, "Depthrock Brick Slab");
        addBlock(UGBlocks.DEPTHROCK_TILE_SLAB, "Depthrock Tile Slab");
        addBlock(UGBlocks.DEPTHROCK_WALL, "Depthrock Wall");
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

        addBlock(UGBlocks.COAL_ORE, "Undergarden Coal Ore");
        addBlock(UGBlocks.IRON_ORE, "Undergarden Iron Ore");
        addBlock(UGBlocks.GOLD_ORE, "Undergarden Gold Ore");
        addBlock(UGBlocks.DIAMOND_ORE, "Undergarden Diamond Ore");
        addBlock(UGBlocks.CLOGGRUM_ORE, "Cloggrum Ore");
        addBlock(UGBlocks.FROSTSTEEL_ORE, "Froststeel Ore");
        addBlock(UGBlocks.UTHERIUM_ORE, "Utherium Ore");
        addBlock(UGBlocks.OTHERSIDE_UTHERIUM_ORE, "Otherside Utherium Ore");
        addBlock(UGBlocks.REGALIUM_ORE, "Regalium Ore");

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

        addBlock(UGBlocks.UNDERBEAN_BUSH, "Underbean Bush");
        addBlock(UGBlocks.BLISTERBERRY_BUSH, "Blisterberry Bush");
        addBlock(UGBlocks.DEEPTURF, "Deepturf");
        addBlock(UGBlocks.ASHEN_DEEPTURF, "Ashen Deepturf");
        addBlock(UGBlocks.FROZEN_DEEPTURF, "Frozen Deepturf");
        addBlock(UGBlocks.TALL_DEEPTURF, "Tall Deepturf");
        addBlock(UGBlocks.SHIMMERWEED, "Shimmerweed");
        addBlock(UGBlocks.TALL_SHIMMERWEED, "Tall Shimmerweed");
        addBlock(UGBlocks.DITCHBULB_PLANT, "Ditchbulb Plant");
        addBlock(UGBlocks.GLOOMGOURD, "Gloomgourd");
        addBlock(UGBlocks.CARVED_GLOOMGOURD, "Carved Gloomgourd");
        addBlock(UGBlocks.GLOOM_O_LANTERN, "Gloom o'Lantern");
        addBlock(UGBlocks.SHARD_O_LANTERN, "Shard o'Lantern");
        addBlock(UGBlocks.GLOOMGOURD_STEM, "Gloomgourd Stem");
        addBlock(UGBlocks.DEPTHROCK_PEBBLES, "Depthrock Pebbles");
        addBlock(UGBlocks.GLOWING_KELP, "Glitterkelp");
        addBlock(UGBlocks.GLOWING_KELP_PLANT, "Glitterkelp");
        addBlock(UGBlocks.DROOPVINE_TOP, "Droopvine");
        addBlock(UGBlocks.DROOPVINE, "Droopvine");

        addBlock(UGBlocks.INDIGO_MUSHROOM, "Indigo Mushroom");
        addBlock(UGBlocks.INDIGO_MUSHROOM_CAP, "Indigo Mushroom Cap");
        addBlock(UGBlocks.INDIGO_MUSHROOM_STALK, "Indigo Mushroom Stalk");

        addBlock(UGBlocks.VEIL_MUSHROOM, "Veiled Mushroom");
        addBlock(UGBlocks.VEIL_MUSHROOM_CAP, "Veil Mushroom Cap");
        addBlock(UGBlocks.VEIL_MUSHROOM_STALK, "Veil Mushroom Stalk");
        addBlock(UGBlocks.MUSHROOM_VEIL, "Mushroom Veil");
        addBlock(UGBlocks.MUSHROOM_VEIL_TOP, "Mushroom Veil");

        addBlock(UGBlocks.INK_MUSHROOM, "Ink Mushroom");
        addBlock(UGBlocks.INK_MUSHROOM_CAP, "Ink Mushroom Cap");
        addBlock(UGBlocks.SEEPING_INK, "Seeping Ink");

        addBlock(UGBlocks.BLOOD_MUSHROOM, "Blood Mushroom");
        addBlock(UGBlocks.BLOOD_MUSHROOM_CAP, "Blood Mushroom Cap");
        addBlock(UGBlocks.BLOOD_MUSHROOM_GLOBULE, "Blood Mushroom Globule");
        addBlock(UGBlocks.BLOOD_MUSHROOM_STALK, "Blood Mushroom Stalk");

        addBlock(UGBlocks.SMOGSTEM_SAPLING, "Smogstem Sapling");
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
        addBlock(UGBlocks.GRONGLE_LOG, "Grongle Log");
        addBlock(UGBlocks.STRIPPED_GRONGLE_LOG, "Stripped Grongle Log");
        addBlock(UGBlocks.GRONGLE_WOOD, "Grongle Wood");
        addBlock(UGBlocks.STRIPPED_GRONGLE_WOOD, "Stripped Grongle Wood");
        addBlock(UGBlocks.GRONGLE_LEAVES, "Grongle Leaves");
        addBlock(UGBlocks.HANGING_GRONGLE_LEAVES, "Hanging Grongle Leaves");
        addBlock(UGBlocks.HANGING_GRONGLE_LEAVES_TOP, "Hanging Grongle Leaves");
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
        addItem(UGItems.CLOGGRUM_INGOT, "Cloggrum Ingot");
        addItem(UGItems.CLOGGRUM_NUGGET, "Cloggrum Nugget");
        addItem(UGItems.FROSTSTEEL_INGOT, "Froststeel Ingot");
        addItem(UGItems.FROSTSTEEL_NUGGET, "Froststeel Nugget");
        addItem(UGItems.UTHERIC_SHARD, "Utheric Shard");
        addItem(UGItems.UTHERIUM_INGOT, "Utherium Ingot");
        addItem(UGItems.UTHERIUM_CHUNK, "Utherium Nugget");
        addItem(UGItems.REGALIUM_INGOT, "Regalium Ingot");
        addItem(UGItems.REGALIUM_NUGGET, "Regalium Nugget");
        addItem(UGItems.FORGOTTEN_INGOT, "Forgotten Ingot");
        addItem(UGItems.FORGOTTEN_NUGGET, "Forgotten Nugget");
        addItem(UGItems.GLOOMGOURD_SEEDS, "Gloomgourd Seeds");
        addItem(UGItems.DITCHBULB, "Ditchbulb");
        addItem(UGItems.BRUTE_TUSK, "Brute Tusk");
        addItem(UGItems.MOGMOSS, "Mogmoss");
        addItem(UGItems.GOO_BALL, "Scintling Goo Ball");
        addItem(UGItems.ROTTEN_BLISTERBERRY, "Rotten Blisterberry");
        addItem(UGItems.BLISTERBOMB, "Blisterbomb");

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
        addItem(UGItems.WIGGLEWOOD_BOAT, "Wigglewood Boat");
        addItem(UGItems.GRONGLE_BOAT, "Grongle Boat");

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

        addBiome(UGBiomes.BARREN_ABYSS, "Barren Abyss");
        addBiome(UGBiomes.DENSE_FOREST, "Dense Forest");
        addBiome(UGBiomes.FORGOTTEN_FIELD, "Forgotten Field");
        addBiome(UGBiomes.FROSTFIELDS, "Frostfields");
        addBiome(UGBiomes.GRONGLEGROWTH, "Gronglegrowth");
        addBiome(UGBiomes.MUSHROOM_BOG, "Mushroom Bog");
        addBiome(UGBiomes.SMOGSTEM_FOREST, "Smogstem Forest");
        addBiome(UGBiomes.SMOG_SPIRES, "Smog Spires");
        addBiome(UGBiomes.WIGGLEWOOD_FOREST, "Wigglewood Forest");

        addEntityType(UGEntityTypes.BOAT, "Undergarden Boat");
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

        addItemGroup(UGItemGroups.GROUP, "The Undergarden");

        addEffect(UGEffects.GOOEY, "Gooey");
        addEffect(UGEffects.BRITTLENESS, "Brittleness");
        addEffect(UGEffects.FEATHERWEIGHT, "Featherweight");
        addEffect(UGEffects.VIRULENT_RESISTANCE, "Virulent Resistance");

        addPotion(UGPotions.BRITTLENESS, "Brittleness");
        addPotion(UGPotions.FEATHERWEIGHT, "Featherweight");
        addPotion(UGPotions.VIRULENT_RESISTANCE, "Virulent Resistance");
        addPotion(UGPotions.GLOWING, "Glowing");

        addAdvTitle("undergarden_root", "The Undergarden");

        addAdvTitle("catalyst", "One Ticket Please");
        addAdvDesc("catalyst", "Create the Catalyst.");

        addAdvTitle("undergarden", "Enter the Undergarden");
        addAdvDesc("undergarden", "The forgotten land awaits...");

        addAdvTitle("64gloomgourds", "Gourd Lord");
        addAdvDesc("64gloomgourds", "Acquire a stack of Gloomgourds.");

        addAdvTitle("slingshot", "Your New Best Friend");
        addAdvDesc("slingshot", "Craft a Slingshot.");

        addAdvTitle("shoot_slingshot", "Flinging Rocks");
        addAdvDesc("shoot_slingshot", "Shoot a pebble at something.");

        addAdvTitle("slay_rotling", "Birth Control");
        addAdvDesc("slay_rotling", "Slay the weakest of the Rotspawn, the Rotling.");

        addAdvTitle("shard_torch", "Warding Device");
        addAdvDesc("shard_torch", "Craft a Shard Torch, a torch that can damage Rotspawn in its vicinity.");

        addAdvTitle("slay_all_rotspawn", "Rotbane");
        addAdvDesc("slay_all_rotspawn", "Slay all kinds of Rotspawn.");

        addAdvTitle("all_undergarden_biomes", "Subterranean Cartographer");
        addAdvDesc("all_undergarden_biomes", "Discover every Undergarden biome.");

        addAdvTitle("any_ore", "Deep Extraction");
        addAdvDesc("any_ore", "Acquire any Undergarden ore.");

        addAdvTitle("underbeans", "Glorious Beans!");
        addAdvDesc("underbeans", "Find and pick an Underbean Bush.");

        addAdvTitle("trade_with_stoneborn", "Interdimensional Business");
        addAdvDesc("trade_with_stoneborn", "Trade with a Stoneborn.");

        addAdvTitle("catch_gwibling", "Weird Fish");
        addAdvDesc("catch_gwibling", "Catch a Gwibling with a bucket.");

        addAdvTitle("catacombs", "Forgotten Halls");
        addAdvDesc("catacombs", "Enter some Catacombs.");

        addAdvTitle("plant_gloomgourd", "Purple Pumpkins");
        addAdvDesc("plant_gloomgourd", "Plant a Gloomgourd seed.");

        addAdvTitle("slay_forgotten_guardian", "Decommissioned");
        addAdvDesc("slay_forgotten_guardian", "Slay a Forgotten Guardian.");

        addAdvTitle("forgotten_ingot", "What Now Is");
        addAdvDesc("forgotten_ingot", "Forge a Forgotten Ingot from a Forgotten Guardian's nuggets.");

        addAdvTitle("forgotten_tools", "Forgotten Arsenal");
        addAdvDesc("forgotten_tools", "Use Forgotten Ingots to upgrade Cloggrum tools and make all 5 Forgotten tools.");

        addAdvTitle("slay_scintling", "Terrible Person");
        addAdvDesc("slay_scintling", "You killed an innocent Scintling. You monster...");

        addAdvTitle("summon_minion", "Buildin' a Sentry");
        addAdvDesc("summon_minion", "Create a Forgotten Minion using a Forgotten Block and a Carved Gloomgourd.");

        addAdvTitle("cloggrum_armor", "Cover Me in Coprolites");
        addAdvDesc("cloggrum_armor", "Obtain a full suit of Cloggrum armor.");

        addAdvTitle("all_blocks", "Collector's Edition");
        addAdvDesc("all_blocks", "Collect one block of every Undergarden ore.");

        addAdvTitle("gloomper_secret_disc", "Death Gloomps");
        addAdvDesc("gloomper_secret_disc", "Obtain the secret music disc.");

        addAdvTitle("forgotten_battleaxe", "Axe of Legends");
        addAdvDesc("forgotten_battleaxe", "Upgrade a Cloggrum Battle Axe with a Forgotten Ingot.");

        addAdvTitle("otherside_root", "The Otherside");
        addAdvDesc("otherside_root", "Enter the realm of madness.");

        add("tooltip.froststeel_sword", "Slows targets.");
        add("tooltip.utheric_sword", "Deals 1.5x damage to Rotspawn.");
        add("tooltip.slingshot", "Uses Depthrock Pebbles as ammo.");
        add("tooltip.cloggrum_boots", "Scintling Goo doesn't slow you down when worn.");
        add("tooltip.forgotten_sword", "Deals 2x damage to non-boss Undergarden mobs.");
        add("tooltip.pebble", "Can be used as Slingshot ammo.");
        add("tooltip.forgotten_tool", "Mines Undergarden blocks 1.5x as fast.");

        addSubtitle("item", "blisterbomb", "Blisterbomb thrown");
        addSubtitle("item", "slingshot_draw", "Slingshot drawn");
        addSubtitle("item", "slingshot_shoot", "Slingshot fired");
        addSubtitle("item", "blisterberry_bush_pick_berries", "Blisterberry Bush picked");

        addSubtitle("block", "undergarden_portal_ambient", "Undergarden Portal beckons");
        addSubtitle("block", "undergarden_portal_activate", "Undergarden Portal activates");
        addSubtitle("block", "undergarden_portal_travel", "Travelling through Undergarden portal");

        addSubtitle("entity", "dweller_ambient", "Dweller grumbles");
        addSubtitle("entity", "dweller_hurt", "Dweller hurts");
        addSubtitle("entity", "dweller_death", "Dweller dies");
        addSubtitle("entity", "dweller_step", "Dweller steps");

        addSubtitle("entity", "rotwalker_ambient", "Rotwalker groans");
        addSubtitle("entity", "rotwalker_hurt", "Rotwalker hurts");
        addSubtitle("entity", "rotwalker_death", "Rotwalker dies");
        addSubtitle("entity", "rotwalker_step", "Rotwalker steps");

        addSubtitle("entity", "rotbeast_ambient", "Rotbeast groans");
        addSubtitle("entity", "rotbeast_hurt", "Rotbeast hurts");
        addSubtitle("entity", "rotbeast_death", "Rotbeast dies");
        addSubtitle("entity", "rotbeast_step", "Rotbeast steps");
        addSubtitle("entity", "rotbeast_attack", "Rotbeast attacks");

        addSubtitle("entity", "brute_ambient", "Brute exhales");
        addSubtitle("entity", "brute_hurt", "Brute hurts");
        addSubtitle("entity", "brute_death", "Brute dies");

        addSubtitle("entity", "gloomper_ambient", "Gloomper croaks");
        addSubtitle("entity", "gloomper_hurt", "Gloomper hurts");
        addSubtitle("entity", "gloomper_death", "Gloomper dies");
        addSubtitle("entity", "gloomper_hop", "Gloomper hops");
        addSubtitle("entity", "gloomper_fart", "Gloomper farts");

        addSubtitle("entity", "stoneborn_step", "Stoneborn steps");
        addSubtitle("entity", "stoneborn_speaking", "Stoneborn speaks");
        addSubtitle("entity", "stoneborn_pleased", "Stoneborn pleased");
        addSubtitle("entity", "stoneborn_hurt", "Stoneborn hurts");
        addSubtitle("entity", "stoneborn_angry", "Stoneborn angered");
        addSubtitle("entity", "stoneborn_confused", "Stoneborn confused");
        addSubtitle("entity", "stoneborn_chant", "Stoneborn chanting");
        addSubtitle("entity", "stoneborn_death", "Stoneborn dies");

        addSubtitle("entity", "rotling_ambient", "Rotling groans");
        addSubtitle("entity", "rotling_hurt", "Rotling hurts");
        addSubtitle("entity", "rotling_death", "Rotling dies");
        addSubtitle("entity", "rotling_step", "Rotling steps");

        addSubtitle("entity", "forgotten_guardian_ambient", "Forgotten Guardian creaks");
        addSubtitle("entity", "forgotten_guardian_hurt", "Forgotten Guardian hurts");
        addSubtitle("entity", "forgotten_guardian_death", "Forgotten Guardian dies");
        addSubtitle("entity", "forgotten_guardian_attack", "Forgotten Guardian attacks");
        addSubtitle("entity", "forgotten_guardian_deflect", "Forgotten Guardian deflects");
        addSubtitle("entity", "forgotten_guardian_step", "Forgotten Guardian steps");

        addSubtitle("entity", "minion_shoot", "Forgotten Minion shoots");
        addSubtitle("entity", "minion_death", "Forgotten Minion dies");
        addSubtitle("entity", "minion_repair", "Forgotten Minion repaired");

        addSubtitle("entity", "nargoyle_hurt", "Nargoyle hurts");
        addSubtitle("entity", "nargoyle_death", "Nargoyle dies");
        addSubtitle("entity", "nargoyle_attack", "Nargoyle leaps");

        addSubtitle("entity", "muncher_ambient", "Muncher grumbles");
        addSubtitle("entity", "muncher_hurt", "Muncher hurts");
        addSubtitle("entity", "muncher_death", "Muncher dies");
        addSubtitle("entity", "muncher_chew", "Muncher chews");

        addSubtitle("entity", "sploogie_ambient", "Sploogie squeaks");
        addSubtitle("entity", "sploogie_hurt", "Sploogie hurts");
        addSubtitle("entity", "sploogie_death", "Sploogie dies");
        addSubtitle("entity", "sploogie_spit", "Sploogie spits");

        addSubtitle("entity", "masticator_ambient", "Masticator roars");
        addSubtitle("entity", "masticator_hurt", "Masticator hurts");
        addSubtitle("entity", "masticator_death", "Masticator dies");
        addSubtitle("entity", "masticator_eat", "Masticator eats");
        addSubtitle("entity", "masticator_step", "Masticator steps");

        addSubtitle("entity", "gwib_hurt", "Gwib hurts");
        addSubtitle("entity", "gwib_death", "Gwib dies");
        addSubtitle("entity", "gwib_flop", "Gwib flops");

        addSubtitle("entity", "gwibling_hurt", "Gwibling hurts");
        addSubtitle("entity", "gwibling_death", "Gwibling dies");
        addSubtitle("entity", "gwibling_flop", "Gwibling flops");

        addSubtitle("entity", "mog_ambient", "Mog squeaks");
        addSubtitle("entity", "mog_hurt", "Mog hurts");
        addSubtitle("entity", "mog_death", "Mog dies");

        addDeath("blisterberry_bush", "%1$s was poked by a Blisterberry Bush");
        addDeath("blisterberry_bush.player", "%1$s was poked by a Blisterberry Bush whilst trying to escape %2$s");
        addDeath("shard_torch", "%1$s was killed by a Shard Torch's magic");
        addDeath("shard_torch.player", "%1$s was killed by a Shard Torch's magic whilst trying to escape %2$s");
    }
}