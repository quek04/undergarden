package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.data.LanguageProvider;
import quek.undergarden.UndergardenMod;
import quek.undergarden.registry.*;

public class UndergardenLang extends LanguageProvider {

    public UndergardenLang(DataGenerator gen) {
        super(gen, UndergardenMod.MODID, "en_us");
    }

    protected void addItemGroup(ItemGroup group, String name) {
        add(group.getTranslationKey(), name);
    }

    protected void addAdvTitle(String advancementTitle, String name) {
        add("advancement." + advancementTitle + ".title", name);
    }

    protected void addAdvDesc(String advancementTitle, String name) {
        add("advancement." + advancementTitle + ".desc", name);
    }

    protected void addSubtitle(String category, String subtitleName, String name) {
        add("subtitles." + category + "." + subtitleName, name);
    }

    @Override
    protected void addTranslations() {
        addBlock(UndergardenBlocks.undergarden_portal, "Undergarden Portal");
        addBlock(UndergardenBlocks.deepsoil, "Deepsoil");
        addBlock(UndergardenBlocks.deepsoil_farmland, "Deepsoil Farmland");
        addBlock(UndergardenBlocks.coarse_deepsoil, "Coarse Deepsoil");
        addBlock(UndergardenBlocks.deepturf_block, "Deepturf Block");
        addBlock(UndergardenBlocks.ashen_deepturf, "Ashen Deepturf Block");
        addBlock(UndergardenBlocks.depthrock, "Depthrock");
        addBlock(UndergardenBlocks.smogstem_planks, "Smogstem Planks");
        addBlock(UndergardenBlocks.wigglewood_planks, "Wigglewood Planks");
        addBlock(UndergardenBlocks.depthrock_bricks, "Depthrock Bricks");
        addBlock(UndergardenBlocks.cracked_depthrock_bricks, "Cracked Depthrock Bricks");
        addBlock(UndergardenBlocks.shiverstone, "Shiverstone");
        addBlock(UndergardenBlocks.shiverstone_bricks, "Shiverstone Bricks");
        addBlock(UndergardenBlocks.goo, "Scintling Goo");
        addBlock(UndergardenBlocks.smog_vent, "Smog Vent");

        addBlock(UndergardenBlocks.tremblecrust, "Tremblecrust");
        addBlock(UndergardenBlocks.loose_tremblecrust, "Loose Tremblecrust");
        addBlock(UndergardenBlocks.tremblecrust_bricks, "Tremblecrust Bricks");

        addBlock(UndergardenBlocks.underbean_bush, "Underbean Bush");
        addBlock(UndergardenBlocks.blisterberry_bush, "Blisterberry Bush");
        addBlock(UndergardenBlocks.smogstem_sapling, "Smogstem Sapling");
        addBlock(UndergardenBlocks.smogstem_log, "Smogstem Log");
        addBlock(UndergardenBlocks.smogstem_wood, "Smogstem Wood");
        addBlock(UndergardenBlocks.smogstem_leaves, "Smogstem Leaves");
        addBlock(UndergardenBlocks.wigglewood_sapling, "Wigglewood Sapling");
        addBlock(UndergardenBlocks.wigglewood_log, "Wigglewood Log");
        addBlock(UndergardenBlocks.wigglewood_wood, "Wigglewood.. Wood");
        addBlock(UndergardenBlocks.wigglewood_leaves, "Wigglewood Leaves");
        addBlock(UndergardenBlocks.tall_deepturf, "Deepturf");
        addBlock(UndergardenBlocks.ashen_tall_deepturf, "Ashen Deepturf");
        addBlock(UndergardenBlocks.double_deepturf, "Tall Deepturf");
        addBlock(UndergardenBlocks.shimmerweed, "Shimmerweed");
        addBlock(UndergardenBlocks.double_shimmerweed, "Tall Shimmerweed");
        addBlock(UndergardenBlocks.ditchbulb_plant, "Ditchbulb Plant");
        addBlock(UndergardenBlocks.indigo_mushroom, "Indigo Mushroom");
        addBlock(UndergardenBlocks.veil_mushroom, "Veiled Mushroom");
        addBlock(UndergardenBlocks.ink_mushroom, "Ink Mushroom");
        addBlock(UndergardenBlocks.blood_mushroom, "Blood Mushroom");
        addBlock(UndergardenBlocks.gloomgourd, "Gloomgourd");
        addBlock(UndergardenBlocks.carved_gloomgourd, "Carved Gloomgourd");
        addBlock(UndergardenBlocks.gloomgourd_stem, "Gloomgourd Stem");
        addBlock(UndergardenBlocks.depthrock_pebbles, "Depthrock Pebbles");
        addBlock(UndergardenBlocks.glowing_kelp, "Glitterkelp");
        addBlock(UndergardenBlocks.glowing_kelp_plant, "Glitterkelp");
        addBlock(UndergardenBlocks.glowing_sea_grass, "Glowing Seagrass");
        addBlock(UndergardenBlocks.droopvine_top, "Droopvine");
        //addBlock(UndergardenBlocks.droopvine, "Droopvine");

        addBlock(UndergardenBlocks.coal_ore, "Undergarden Coal Ore");
        addBlock(UndergardenBlocks.iron_ore, "Undergarden Iron Ore");
        addBlock(UndergardenBlocks.gold_ore, "Undergarden Gold Ore");
        addBlock(UndergardenBlocks.diamond_ore, "Undergarden Diamond Ore");
        addBlock(UndergardenBlocks.cloggrum_ore, "Cloggrum Ore");
        addBlock(UndergardenBlocks.froststeel_ore, "Froststeel Ore");
        addBlock(UndergardenBlocks.utherium_ore, "Utherium Ore");
        addBlock(UndergardenBlocks.otherside_utherium_ore, "Utherium Ore");
        addBlock(UndergardenBlocks.regalium_ore, "Regalium Ore");

        addBlock(UndergardenBlocks.cloggrum_block, "Cloggrum Block");
        addBlock(UndergardenBlocks.froststeel_block, "Froststeel Block");
        addBlock(UndergardenBlocks.utherium_block, "Utherium Block");
        addBlock(UndergardenBlocks.regalium_block, "Regalium Block");
        addBlock(UndergardenBlocks.gloom_o_lantern, "Gloom o'Lantern");
        addBlock(UndergardenBlocks.cloggrum_bars, "Cloggrum Bars");

        addBlock(UndergardenBlocks.depthrock_stairs, "Depthrock Stairs");
        addBlock(UndergardenBlocks.depthrock_brick_stairs, "Depthrock Brick Stairs");
        addBlock(UndergardenBlocks.smogstem_stairs, "Smogstem Stairs");
        addBlock(UndergardenBlocks.wigglewood_stairs, "Wigglewood Stairs");
        addBlock(UndergardenBlocks.shiverstone_stairs, "Shiverstone Stairs");
        addBlock(UndergardenBlocks.shiverstone_brick_stairs, "Shiverstone Brick Stairs");

        addBlock(UndergardenBlocks.depthrock_slab, "Depthrock Slab");
        addBlock(UndergardenBlocks.depthrock_brick_slab, "Depthrock Brick Slab");
        addBlock(UndergardenBlocks.smogstem_slab, "Smogstem Slab");
        addBlock(UndergardenBlocks.wigglewood_slab, "Wigglewood Slab");
        addBlock(UndergardenBlocks.shiverstone_slab, "Shiverstone Slab");
        addBlock(UndergardenBlocks.shiverstone_brick_slab, "Shiverstone Brick Slab");

        addBlock(UndergardenBlocks.depthrock_brick_wall, "Depthrock Brick Wall");
        addBlock(UndergardenBlocks.shiverstone_brick_wall, "Shiverstone Brick Wall");

        addBlock(UndergardenBlocks.smogstem_fence, "Smogstem Fence");
        addBlock(UndergardenBlocks.wigglewood_fence, "Wigglewood Fence");

        addBlock(UndergardenBlocks.smogstem_fence_gate, "Smogstem Fence Gate");
        addBlock(UndergardenBlocks.wigglewood_fence_gate, "Wigglewood Fence Gate");

        addBlock(UndergardenBlocks.smogstem_door, "Smogstem Door");
        addBlock(UndergardenBlocks.wigglewood_door, "Wigglewood Door");

        addBlock(UndergardenBlocks.smogstem_trapdoor, "Smogstem Trapdoor");
        addBlock(UndergardenBlocks.wigglewood_trapdoor, "Wigglewood Trapdoor");

        addBlock(UndergardenBlocks.smogstem_button, "Smogstem Button");
        addBlock(UndergardenBlocks.wigglewood_button, "Wigglewood Button");
        addBlock(UndergardenBlocks.depthrock_button, "Depthrock Button");
        addBlock(UndergardenBlocks.shiverstone_button, "Shiverstone Button");

        addBlock(UndergardenBlocks.smogstem_pressure_plate, "Smogstem Pressure Plate");
        addBlock(UndergardenBlocks.wigglewood_pressure_plate, "Wigglewood Pressure Plate");
        addBlock(UndergardenBlocks.depthrock_pressure_plate, "Depthrock Pressure Plate");
        addBlock(UndergardenBlocks.shiverstone_pressure_plate, "Shiverstone Pressure Plate");

        addItem(UndergardenItems.catalyst_item, "Catalyst");
        addItem(UndergardenItems.depthrock_pebble, "Depthrock Pebble");
        addItem(UndergardenItems.smogstem_stick, "Smogstem Stick");
        addItem(UndergardenItems.twistytwig, "Twistytwig");
        addItem(UndergardenItems.cloggrum_ingot, "Cloggrum Ingot");
        addItem(UndergardenItems.cloggrum_nugget, "Cloggrum Nugget");
        addItem(UndergardenItems.froststeel_ingot, "Froststeel Ingot");
        addItem(UndergardenItems.froststeel_nugget, "Froststeel Nugget");
        addItem(UndergardenItems.utheric_shard, "Utheric Shard");
        addItem(UndergardenItems.utherium_ingot, "Utherium Ingot");
        addItem(UndergardenItems.utherium_chunk, "Utherium Chunk");
        addItem(UndergardenItems.regalium_ingot, "Regalium Ingot");
        addItem(UndergardenItems.regalium_nugget, "Regalium Nugget");
        addItem(UndergardenItems.smogstem_torch, "Smogstem Torch");
        addItem(UndergardenItems.shard_torch, "Shard Torch");
        addItem(UndergardenItems.gloomgourd_seeds, "Gloomgourd Seeds");
        addItem(UndergardenItems.ditchbulb, "Ditchbulb");
        addItem(UndergardenItems.brute_tusk, "Brute Tusk");
        addItem(UndergardenItems.goo_ball, "Scintling Goo Ball");
        addItem(UndergardenItems.rotten_blisterberry, "Rotten Blisterberry");
        addItem(UndergardenItems.blisterbomb, "Blisterbomb");
        //addItem(UndergardenItems.droopvine_item, "Droopvine");

        addItem(UndergardenItems.masticator_scales, "Masticator Scales");
        addItem(UndergardenItems.masticated_chestplate, "Masticated Chestplate");
        addItem(UndergardenItems.cloggrum_battleaxe, "Cloggrum Battle Axe");

        addItem(UndergardenItems.smogstem_sword, "Smogstem Sword");
        addItem(UndergardenItems.smogstem_pickaxe, "Smogstem Pickaxe");
        addItem(UndergardenItems.smogstem_axe, "Smogstem Axe");
        addItem(UndergardenItems.smogstem_shovel, "Smogstem Shovel");
        addItem(UndergardenItems.smogstem_hoe, "Smogstem Hoe");

        addItem(UndergardenItems.cloggrum_sword, "Cloggrum Sword");
        addItem(UndergardenItems.cloggrum_pickaxe, "Cloggrum Pickaxe");
        addItem(UndergardenItems.cloggrum_axe, "Cloggrum Axe");
        addItem(UndergardenItems.cloggrum_shovel, "Cloggrum Shovel");
        addItem(UndergardenItems.cloggrum_hoe, "Cloggrum Hoe");
        addItem(UndergardenItems.cloggrum_shield, "Cloggrum Shield");

        addItem(UndergardenItems.froststeel_sword, "Froststeel Sword");
        addItem(UndergardenItems.froststeel_pickaxe, "Froststeel Pickaxe");
        addItem(UndergardenItems.froststeel_axe, "Froststeel Axe");
        addItem(UndergardenItems.froststeel_shovel, "Froststeel Shovel");
        addItem(UndergardenItems.froststeel_hoe, "Froststeel Hoe");

        addItem(UndergardenItems.utheric_sword, "Utherium Sword");
        addItem(UndergardenItems.utheric_pickaxe, "Utherium Pickaxe");
        addItem(UndergardenItems.utheric_axe, "Utherium Axe");
        addItem(UndergardenItems.utheric_shovel, "Utherium Shovel");
        addItem(UndergardenItems.utheric_hoe, "Utheric Hoe");

        addItem(UndergardenItems.slingshot, "Slingshot");

        addItem(UndergardenItems.virulent_mix_bucket, "Virulent Mix Bucket");

        addItem(UndergardenItems.gwibling_bucket, "Bucket of Gwibling");

        addItem(UndergardenItems.cloggrum_helmet, "Cloggrum Helmet");
        addItem(UndergardenItems.cloggrum_chestplate, "Cloggrum Chestplate");
        addItem(UndergardenItems.cloggrum_leggings, "Cloggrum Leggings");
        addItem(UndergardenItems.cloggrum_boots, "Cloggrum Boots");

        addItem(UndergardenItems.froststeel_helmet, "Froststeel Helmet");
        addItem(UndergardenItems.froststeel_chestplate, "Froststeel Chestplate");
        addItem(UndergardenItems.froststeel_leggings, "Froststeel Leggings");
        addItem(UndergardenItems.froststeel_boots, "Froststeel Boots");

        addItem(UndergardenItems.utheric_helmet, "Utherium Helmet");
        addItem(UndergardenItems.utheric_chestplate, "Utherium Chestplate");
        addItem(UndergardenItems.utheric_leggings, "Utherium Leggings");
        addItem(UndergardenItems.utheric_boots, "Utherium Boots");

        addItem(UndergardenItems.underbeans, "Underbeans");
        addItem(UndergardenItems.blisterberry, "Blisterberry");
        addItem(UndergardenItems.gloomgourd_pie, "Gloomgourd Pie");
        addItem(UndergardenItems.raw_dweller_meat, "Raw Dweller Meat");
        addItem(UndergardenItems.dweller_steak, "Dweller Steak");
        addItem(UndergardenItems.raw_gwibling, "Raw Gwibling");
        addItem(UndergardenItems.cooked_gwibling, "Cooked Gwibling");

        addItem(UndergardenItems.dweller_spawn_egg, "Dweller Spawn Egg");
        addItem(UndergardenItems.gwibling_spawn_egg, "Gwibling Spawn Egg");
        addItem(UndergardenItems.rotdweller_spawn_egg, "Rotdweller Spawn Egg");
        addItem(UndergardenItems.rotling_spawn_egg, "Rotling Spawn Egg");
        addItem(UndergardenItems.rotwalker_spawn_egg, "Rotwalker Spawn Egg");
        addItem(UndergardenItems.rotbeast_spawn_egg, "Rotbeast Spawn Egg");
        addItem(UndergardenItems.brute_spawn_egg, "Brute Spawn Egg");
        addItem(UndergardenItems.scintling_spawn_egg, "Scintling Spawn Egg");
        addItem(UndergardenItems.blisterbomber_spawn_egg, "Blisterbomber Spawn Egg");
        addItem(UndergardenItems.gloomper_spawn_egg, "Gloomper Spawn Egg");
        addItem(UndergardenItems.stoneborn_spawn_egg, "Stoneborn Spawn Egg");

        addItem(UndergardenItems.masticator_spawn_egg, "Masticator Spawn Egg");

        addBiome(UndergardenBiomes.FORGOTTEN_FIELD, "Forgotten Field");
        addBiome(UndergardenBiomes.SMOGSTEM_FOREST, "Smogstem Forest");
        addBiome(UndergardenBiomes.BARREN_ABYSS, "Barren Abyss");
        addBiome(UndergardenBiomes.WIGGLEWOOD_FOREST, "Wigglewood Forest");
        addBiome(UndergardenBiomes.DENSE_FOREST, "Dense Forest");
        addBiome(UndergardenBiomes.SMOG_SPIRES, "Smog Spires");
        addBiome(UndergardenBiomes.OTHERSIDE, "The Otherside");

        addEntityType(UndergardenEntities.DWELLER, "Dweller");
        addEntityType(UndergardenEntities.ROTDWELLER, "Rotdweller");
        addEntityType(UndergardenEntities.GWIBLING, "Gwibling");
        addEntityType(UndergardenEntities.ROTLING, "Rotling");
        addEntityType(UndergardenEntities.ROTWALKER, "Rotwalker");
        addEntityType(UndergardenEntities.ROTBEAST, "Rotbeast");
        addEntityType(UndergardenEntities.BRUTE, "Brute");
        addEntityType(UndergardenEntities.SCINTLING, "Scintling");
        addEntityType(UndergardenEntities.BLISTERBOMBER, "Blisterbomber");
        addEntityType(UndergardenEntities.GLOOMPER, "Gloomper");
        addEntityType(UndergardenEntities.STONEBORN, "Stoneborn");

        addEntityType(UndergardenEntities.MASTICATOR, "Masticator");

        addItemGroup(UndergardenItemGroups.GROUP, "The Undergarden");

        addEffect(UndergardenEffects.gooey, "Gooey");

        addAdvTitle("undergarden", "The Undergarden");
        addAdvDesc("undergarden", "Enter The Undergarden. The forgotten land awaits...");

        addAdvTitle("gourd_lord", "Gourd Lord");
        addAdvDesc("gourd_lord", "Acquire a stack of Gloomgourds.");

        addAdvTitle("slingshot", "Your New Best Friend");
        addAdvDesc("slingshot", "Craft a Slingshot.");

        addAdvTitle("slay_rotling", "Slay Rotling");
        addAdvDesc("slay_rotling", "Slay the weakest of the Rotspawn, the Rotling.");

        addAdvTitle("shard_torch", "Warding Device");
        addAdvDesc("shard_torch", "Craft a Shard Torch, a torch that can damage Rotspawn in its vicinity.");

        addAdvTitle("rotbane", "Rotbane");
        addAdvDesc("rotbane", "Slay all kinds of Rotspawn.");

        addAdvTitle("subterranean_cartographer", "Subterranean Cartographer");
        addAdvDesc("subterranean_cartographer", "Discover every Undergarden biome.");

        addAdvTitle("deep_extraction", "Deep Extraction");
        addAdvDesc("deep_extraction", "Acquire a Cloggrum Ingot.");

        addAdvTitle("glorious_beans", "Glorious Beans!");
        addAdvDesc("glorious_beans", "Find and pick an Underbean Bush.");

        addAdvTitle("slay_masticator", "Sprankton");
        addAdvDesc("slay_masticator", "Slay the Masticator.");

        addAdvTitle("masticated_armory", "Masticated Armory");
        addAdvDesc("masticated_armory", "Craft the Masticated Chestplate.");

        add("tooltip.cloggrum_sword", "High damage, low durability.");
        add("tooltip.froststeel_sword", "Slows targets.");
        add("tooltip.utheric_sword", "Deals 10 damage to Rotspawn.");
        add("tooltip.utheric_axe", "Bonus damage to passive animals.");
        add("tooltip.slingshot", "Uses Depthrock Pebbles as ammo.");
        add("tooltip.cloggrum_boots", "Scintling Goo doesn't slow you down when worn.");

        addSubtitle("item", "undergarden_portal_activate", "Undergarden Portal activates");
        addSubtitle("item", "blisterbomb", "Blisterbomb thrown");

        addSubtitle("entity", "dweller_living", "Dweller grumbles");
        addSubtitle("entity", "dweller_hurt", "Dweller hurts");
        addSubtitle("entity", "dweller_death", "Dweller dies");

        addSubtitle("entity", "rotwalker_living", "Rotwalker groans");
        addSubtitle("entity", "rotwalker_hurt", "Rotwalker hurts");
        addSubtitle("entity", "rotwalker_death", "Rotwalker dies");

        addSubtitle("entity", "rotbeast_living", "Rotbeast groans");
        addSubtitle("entity", "rotbeast_hurt", "Rotbeast hurts");
        addSubtitle("entity", "rotbeast_death", "Rotbeast dies");

        addSubtitle("entity", "brute_living", "Brute exhales");
        addSubtitle("entity", "brute_hurt", "Brute hurts");
        addSubtitle("entity", "brute_death", "Brute dies");
        addSubtitle("entity", "brute_angry", "Brute exhales angrily");

        addSubtitle("entity", "gloomper_living", "Gloomper croaks");
        addSubtitle("entity", "gloomper_hurt", "Gloomper hurts");
        addSubtitle("entity", "gloomper_death", "Gloomper dies");

        addSubtitle("entity", "stoneborn_step", "Stoneborn steps");
        addSubtitle("entity", "stoneborn_speaking", "Stoneborn speaks");
        addSubtitle("entity", "stoneborn_pleased", "Stoneborn pleased");
        addSubtitle("entity", "stoneborn_awe", "Stoneborn awed");
        addSubtitle("entity", "stoneborn_chuckle", "Stoneborn chuckles");
        addSubtitle("entity", "stoneborn_hurt", "Stoneborn hurts");
        addSubtitle("entity", "stoneborn_angry", "Stoneborn angered");
        addSubtitle("entity", "stoneborn_growl", "Stoneborn growls");
        addSubtitle("entity", "stoneborn_confused", "Stoneborn confused");
        addSubtitle("entity", "stoneborn_chant", "Stoneborn chanting");
        addSubtitle("entity", "stoneborn_death", "Stoneborn dies");

        addSubtitle("entity", "rotling_living", "Rotling groans");
        addSubtitle("entity", "rotling_hurt", "Rotling hurts");
        addSubtitle("entity", "rotling_death", "Rotling dies");

        addSubtitle("ambient", "undergarden_portal_ambient", "Undergarden Portal beckons");
    }
}
