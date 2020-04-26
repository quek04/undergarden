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

    @Override
    protected void addTranslations() {
        addBlock(UndergardenBlocks.undergarden_portal_frame, "Undergarden Portal Frame");
        addBlock(UndergardenBlocks.undergarden_portal, "Undergarden Portal");
        addBlock(UndergardenBlocks.deepsoil, "Deepsoil");
        addBlock(UndergardenBlocks.deepturf_block, "Deepturf Block");
        addBlock(UndergardenBlocks.depthrock, "Depthrock");
        addBlock(UndergardenBlocks.cobbled_depthrock, "Cobbled Depthrock");
        addBlock(UndergardenBlocks.smogstem_planks, "Smogstem Planks");
        addBlock(UndergardenBlocks.wigglewood_planks, "Wigglewood Planks");
        addBlock(UndergardenBlocks.depthrock_bricks, "Depthrock Bricks");
        addBlock(UndergardenBlocks.cracked_depthrock_bricks, "Cracked Depthrock Bricks");

        addBlock(UndergardenBlocks.underbean_bush, "Underbean Bush");
        addBlock(UndergardenBlocks.smogstem_sapling, "Smogstem Sapling");
        addBlock(UndergardenBlocks.smogstem_log, "Smogstem Log");
        addBlock(UndergardenBlocks.smogstem_leaves, "Smogstem Leaves");
        addBlock(UndergardenBlocks.wigglewood_sapling, "Wigglewood Sapling");
        addBlock(UndergardenBlocks.wigglewood_log, "Wigglewood Log");
        addBlock(UndergardenBlocks.wigglewood_leaves, "Wigglewood Leaves");
        addBlock(UndergardenBlocks.tall_deepturf, "Deepturf");
        addBlock(UndergardenBlocks.shimmerweed, "Shimmerweed");
        addBlock(UndergardenBlocks.ditchbulb_plant, "Ditchbulb Plant");
        addBlock(UndergardenBlocks.indigo_mushroom, "Indigo Mushroom");
        addBlock(UndergardenBlocks.veil_mushroom, "Veiled Mushroom");
        addBlock(UndergardenBlocks.ink_mushroom, "Ink Mushroom");
        addBlock(UndergardenBlocks.blood_mushroom, "Blood Mushroom");
        addBlock(UndergardenBlocks.gloomgourd, "Gloomgourd");
        addBlock(UndergardenBlocks.carved_gloomgourd, "Carved Gloomgourd");
        addBlock(UndergardenBlocks.gloom_o_lantern, "Gloom o'Lantern");

        addBlock(UndergardenBlocks.coal_ore, "Coal Ore");
        addBlock(UndergardenBlocks.cloggrum_ore, "Cloggrum Ore");
        addBlock(UndergardenBlocks.froststeel_ore, "Froststeel Ore");
        addBlock(UndergardenBlocks.utherium_ore, "Utherium Ore");

        addItem(UndergardenItems.undergarden_portal_catalyst, "Undergarden Portal Catalyst");
        addItem(UndergardenItems.smogstem_stick, "Smogstem Stick");
        addItem(UndergardenItems.cloggrum_ingot, "Cloggrum Ingot");
        addItem(UndergardenItems.cloggrum_nugget, "Cloggrum Nugget");
        addItem(UndergardenItems.froststeel_ingot, "Froststeel Ingot");
        addItem(UndergardenItems.froststeel_nugget, "Froststeel Nugget");
        addItem(UndergardenItems.utheric_shard, "Utheric Shard");
        addItem(UndergardenItems.utherium_ingot, "Utherium Ingot");
        addItem(UndergardenItems.utherium_chunk, "Utherium Chunk");
        addItem(UndergardenItems.smogstem_torch, "Smogstem Torch");
        addItem(UndergardenItems.ditchbulb, "Ditchbulb");

        addItem(UndergardenItems.smogstem_sword, "Smogstem Sword");
        addItem(UndergardenItems.smogstem_pickaxe, "Smogstem Pickaxe");
        addItem(UndergardenItems.smogstem_axe, "Smogstem Axe");
        addItem(UndergardenItems.smogstem_shovel, "Smogstem Shovel");

        addItem(UndergardenItems.cloggrum_sword, "Cloggrum Sword");
        addItem(UndergardenItems.cloggrum_pickaxe, "Cloggrum Pickaxe");
        addItem(UndergardenItems.cloggrum_axe, "Cloggrum Axe");
        addItem(UndergardenItems.cloggrum_shovel, "Cloggrum Shovel");

        addItem(UndergardenItems.froststeel_sword, "Froststeel Sword");
        addItem(UndergardenItems.froststeel_pickaxe, "Froststeel Pickaxe");
        addItem(UndergardenItems.froststeel_axe, "Froststeel Axe");
        addItem(UndergardenItems.froststeel_shovel, "Froststeel Shovel");

        addItem(UndergardenItems.utheric_sword, "Utherium Sword");
        addItem(UndergardenItems.utheric_pickaxe, "Utherium Pickaxe");
        addItem(UndergardenItems.utheric_axe, "Utherium Axe");
        addItem(UndergardenItems.utheric_shovel, "Utherium_Shovel");

        addItem(UndergardenItems.cloggrum_shears, "Cloggrum Shears");

        addItem(UndergardenItems.cloggrum_helmet, "Cloggrum Helmet");
        addItem(UndergardenItems.cloggrum_chestplate, "Cloggrum Chestplate");
        addItem(UndergardenItems.cloggrum_leggings, "Cloggrum Leggings");
        addItem(UndergardenItems.cloggrum_boots, "Cloggrum Boots");

        addItem(UndergardenItems.underbeans, "Underbeans");
        addItem(UndergardenItems.gloomgourd_pie, "Gloomgourd Pie");
        addItem(UndergardenItems.raw_dweller_meat, "Raw Dweller Meat");
        addItem(UndergardenItems.dweller_steak, "Dweller Steak");

        addItem(UndergardenItems.dweller_spawn_egg, "Dweller Spawn Egg");
        addItem(UndergardenItems.gwibling_spawn_egg, "Gwibling Spawn Egg");
        addItem(UndergardenItems.rotdweller_spawn_egg, "Rotdweller Spawn Egg");
        addItem(UndergardenItems.rotwalker_spawn_egg, "Rotwalker Spawn Egg");
        addItem(UndergardenItems.rotbeast_spawn_egg, "Rotbeast Spawn Egg");

        addBiome(UndergardenBiomes.FORGOTTEN_ABYSS, "Forgotten Abyss");

        addEntityType(UndergardenEntities.DWELLER, "Dweller");
        addEntityType(UndergardenEntities.ROTDWELLER, "Rotdweller");
        addEntityType(UndergardenEntities.GWIBLING, "Gwibling");
        addEntityType(UndergardenEntities.ROTWALKER, "Rotwalker");
        addEntityType(UndergardenEntities.ROTBEAST, "Rotbeast");

        addItemGroup(UndergardenItemGroups.UNDERGARDEN_BLOCKS, "Undergarden Blocks");
        addItemGroup(UndergardenItemGroups.UNDERGARDEN_FOODS, "Undergarden Foodstuffs");
        addItemGroup(UndergardenItemGroups.UNDERGARDEN_GEAR, "Undergarden Gear");
        addItemGroup(UndergardenItemGroups.UNDERGARDEN_ITEMS, "Undergarden Items");


    }
}
