package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import quek.undergarden.UGMod;
import quek.undergarden.data.provider.UGItemModelProvider;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

public class UGItemModels extends UGItemModelProvider {

    public UGItemModels(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override
    public String getName() {
        return "Undergarden Item Models";
    }

    @Override
    protected void registerModels() {
        itemBlock(UGBlocks.depthrock);
        itemBlock(UGBlocks.coal_ore);
        itemBlock(UGBlocks.cloggrum_ore);
        itemBlock(UGBlocks.froststeel_ore);
        itemBlock(UGBlocks.utherium_ore);
        itemBlock(UGBlocks.deepturf_block);
        itemBlock(UGBlocks.deepsoil_farmland);
        itemBlock(UGBlocks.deepsoil);
        itemBlockFlat(UGBlocks.smogstem_sapling);
        itemBlock(UGBlocks.smogstem_log);
        itemBlock(UGBlocks.smogstem_leaves);
        itemBlockFlat(UGBlocks.wigglewood_sapling);
        itemBlock(UGBlocks.wigglewood_log);
        itemBlock(UGBlocks.wigglewood_leaves);
        itemBlock(UGBlocks.smogstem_planks);
        itemBlock(UGBlocks.wigglewood_planks);
        itemBlock(UGBlocks.depthrock_bricks);
        itemBlock(UGBlocks.cracked_depthrock_bricks);
        itemBlockFlat(UGBlocks.deepturf);
        itemBlockFlat(UGBlocks.ditchbulb_plant);
        itemBlockFlat(UGBlocks.indigo_mushroom);
        itemBlockFlat(UGBlocks.veil_mushroom);
        itemBlockFlat(UGBlocks.ink_mushroom);
        itemBlockFlat(UGBlocks.blood_mushroom);
        itemBlock(UGBlocks.gloomgourd);
        itemBlock(UGBlocks.carved_gloomgourd);
        itemBlock(UGBlocks.depthrock_pebbles);
        itemBlock(UGBlocks.gloom_o_lantern);
        itemBlock(UGBlocks.cloggrum_block);
        itemBlock(UGBlocks.froststeel_block);
        itemBlock(UGBlocks.utherium_block);
        itemBlock(UGBlocks.depthrock_stairs);
        itemBlock(UGBlocks.depthrock_brick_stairs);
        itemBlock(UGBlocks.smogstem_stairs);
        itemBlock(UGBlocks.wigglewood_stairs);
        itemBlock(UGBlocks.depthrock_slab);
        itemBlock(UGBlocks.depthrock_brick_slab);
        itemBlock(UGBlocks.smogstem_slab);
        itemBlock(UGBlocks.wigglewood_slab);
        itemFence(UGBlocks.smogstem_fence, "smogstem_planks");
        itemFence(UGBlocks.wigglewood_fence, "wigglewood_planks");
        itemBlockFlat(UGBlocks.cloggrum_bars);
        itemBlock(UGBlocks.shiverstone);
        itemBlock(UGBlocks.goo);
        itemBlock(UGBlocks.smog_vent);
        itemBlockFlat(UGBlocks.ashen_deepturf);
        itemBlock(UGBlocks.ashen_deepturf_block);
        itemBlock(UGBlocks.regalium_ore);
        itemBlock(UGBlocks.shiverstone_bricks);
        itemBlock(UGBlocks.shiverstone_stairs);
        itemBlock(UGBlocks.shiverstone_brick_stairs);
        itemBlock(UGBlocks.shiverstone_slab);
        itemBlock(UGBlocks.shiverstone_brick_slab);
        itemBlock(UGBlocks.regalium_block);
        itemBlock(UGBlocks.tremblecrust);
        itemBlock(UGBlocks.tremblecrust_bricks);
        itemBlock(UGBlocks.otherside_utherium_ore);
        itemBlock(UGBlocks.smogstem_wood);
        itemBlock(UGBlocks.wigglewood_wood);
        itemBlock(UGBlocks.loose_tremblecrust);
        itemBlock(UGBlocks.iron_ore);
        itemBlock(UGBlocks.gold_ore);
        itemBlock(UGBlocks.diamond_ore);
        itemBlock(UGBlocks.smogstem_fence_gate);
        itemBlock(UGBlocks.wigglewood_fence_gate);
        wallInventory("depthrock_wall", new ResourceLocation(UGMod.MODID, "block/depthrock"));
        wallInventory("depthrock_brick_wall", new ResourceLocation(UGMod.MODID, "block/depthrock_bricks"));
        wallInventory("shiverstone_brick_wall", new ResourceLocation(UGMod.MODID, "block/shiverstone_bricks"));
        wallInventory("shiverstone_wall", new ResourceLocation(UGMod.MODID, "block/shiverstone"));
        itemBlock(UGBlocks.coarse_deepsoil);
        itemBlock(UGBlocks.smogstem_pressure_plate);
        itemBlock(UGBlocks.wigglewood_pressure_plate);
        itemBlock(UGBlocks.depthrock_pressure_plate);
        itemBlock(UGBlocks.shiverstone_pressure_plate);
        itemBlockFlat(UGBlocks.gronglet);
        itemBlock(UGBlocks.grongle_stem);
        itemBlock(UGBlocks.grongle_cap);
        itemBlock(UGBlocks.grongle_hyphae);
        itemBlock(UGBlocks.grongle_slab);
        itemFence(UGBlocks.grongle_fence, "grongle_planks");
        itemBlock(UGBlocks.grongle_planks);
        itemBlock(UGBlocks.grongle_fence_gate);
        itemBlock(UGBlocks.grongle_stairs);
        itemBlock(UGBlocks.grongle_pressure_plate);
        itemBlock(UGBlocks.stripped_smogstem_log);
        itemBlock(UGBlocks.stripped_wigglewood_log);
        itemBlock(UGBlocks.stripped_grongle_stem);
        itemBlock(UGBlocks.stripped_smogstem_wood);
        itemBlock(UGBlocks.stripped_wigglewood_wood);
        itemBlock(UGBlocks.stripped_grongle_hyphae);
        itemBlock(UGBlocks.cracked_shiverstone_bricks);
        itemBlock(UGBlocks.blood_mushroom_globule);
        itemBlock(UGBlocks.forgotten_block);
        itemBlock(UGBlocks.chiseled_depthrock_bricks);
        itemBlock(UGBlocks.chiseled_shiverstone_bricks);

        normalItem(UGItems.catalyst_item);
        normalItem(UGItems.depthrock_pebble);
        normalItem(UGItems.twistytwig);
        normalItem(UGItems.cloggrum_ingot);
        normalItem(UGItems.cloggrum_nugget);
        normalItem(UGItems.froststeel_ingot);
        normalItem(UGItems.froststeel_nugget);
        normalItem(UGItems.utheric_shard);
        normalItem(UGItems.utherium_ingot);
        normalItem(UGItems.utherium_chunk);
        normalItem(UGItems.regalium_ingot);
        normalItem(UGItems.regalium_nugget);
        normalItem(UGItems.forgotten_ingot);
        normalItem(UGItems.forgotten_nugget);
        torchItem(UGItems.shard_torch);
        normalItem(UGItems.ditchbulb);
        normalItem(UGItems.gloomgourd_seeds);
        normalItem(UGItems.brute_tusk);
        normalItem(UGItems.glowing_kelp);
        normalItem(UGItems.goo_ball);
        normalItem(UGItems.rotten_blisterberry);
        normalItem(UGItems.blisterbomb);
        normalItem(UGItems.droopvine_item);

        toolItem(UGItems.cloggrum_sword);
        toolItem(UGItems.cloggrum_pickaxe);
        toolItem(UGItems.cloggrum_axe);
        toolItem(UGItems.cloggrum_shovel);
        toolItem(UGItems.cloggrum_hoe);

        normalItem(UGItems.masticator_scales);
        normalItem(UGItems.masticated_chestplate);

        normalItem(UGItems.cloggrum_helmet);
        normalItem(UGItems.cloggrum_chestplate);
        normalItem(UGItems.cloggrum_leggings);
        normalItem(UGItems.cloggrum_boots);

        normalItem(UGItems.froststeel_helmet);
        normalItem(UGItems.froststeel_chestplate);
        normalItem(UGItems.froststeel_leggings);
        normalItem(UGItems.froststeel_boots);

        normalItem(UGItems.utheric_helmet);
        normalItem(UGItems.utheric_chestplate);
        normalItem(UGItems.utheric_leggings);
        normalItem(UGItems.utheric_boots);

        toolItem(UGItems.froststeel_sword);
        toolItem(UGItems.froststeel_pickaxe);
        toolItem(UGItems.froststeel_axe);
        toolItem(UGItems.froststeel_shovel);
        toolItem(UGItems.froststeel_hoe);

        toolItem(UGItems.utheric_sword);
        toolItem(UGItems.utheric_pickaxe);
        toolItem(UGItems.utheric_axe);
        toolItem(UGItems.utheric_shovel);
        toolItem(UGItems.utheric_hoe);

        toolItem(UGItems.forgotten_sword);
        toolItem(UGItems.forgotten_pickaxe);
        toolItem(UGItems.forgotten_axe);
        toolItem(UGItems.forgotten_shovel);
        toolItem(UGItems.forgotten_hoe);

        normalItem(UGItems.smogstem_boat);
        normalItem(UGItems.wigglewood_boat);
        normalItem(UGItems.grongle_boat);

        normalItem(UGItems.virulent_mix_bucket);

        normalItem(UGItems.gwibling_bucket);

        normalItem(UGItems.underbeans);
        normalItem(UGItems.blisterberry);
        normalItem(UGItems.gloomgourd_pie);
        normalItem(UGItems.raw_dweller_meat);
        normalItem(UGItems.dweller_steak);
        normalItem(UGItems.raw_gwibling);
        normalItem(UGItems.cooked_gwibling);
        normalItem(UGItems.raw_gloomper_leg);
        normalItem(UGItems.gloomper_leg);
        normalItem(UGItems.bloody_stew);
        normalItem(UGItems.indigo_stew);
        normalItem(UGItems.inky_stew);
        normalItem(UGItems.veiled_stew);

        egg(UGItems.dweller_spawn_egg);
        egg(UGItems.gwibling_spawn_egg);
        egg(UGItems.rotdweller_spawn_egg);
        egg(UGItems.rotling_spawn_egg);
        egg(UGItems.rotwalker_spawn_egg);
        egg(UGItems.rotbeast_spawn_egg);
        egg(UGItems.brute_spawn_egg);
        egg(UGItems.scintling_spawn_egg);
        egg(UGItems.gloomper_spawn_egg);
        egg(UGItems.stoneborn_spawn_egg);
        egg(UGItems.nargoyle_spawn_egg);

        egg(UGItems.masticator_spawn_egg);
    }
}
