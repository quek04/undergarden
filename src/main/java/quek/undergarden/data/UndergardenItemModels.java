package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import quek.undergarden.data.provider.UndergardenItemModelProvider;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenItems;

public class UndergardenItemModels extends UndergardenItemModelProvider {

    public UndergardenItemModels(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override
    public String getName() {
        return "Undergarden Item Models";
    }

    @Override
    protected void registerModels() {
        itemBlock(UndergardenBlocks.depthrock);
        itemBlock(UndergardenBlocks.cobbled_depthrock);
        itemBlock(UndergardenBlocks.coal_ore);
        itemBlock(UndergardenBlocks.cloggrum_ore);
        itemBlock(UndergardenBlocks.froststeel_ore);
        itemBlock(UndergardenBlocks.utherium_ore);
        itemBlock(UndergardenBlocks.deepturf_block);
        itemBlock(UndergardenBlocks.deepsoil_farmland);
        itemBlock(UndergardenBlocks.deepsoil);
        itemBlockFlat(UndergardenBlocks.smogstem_sapling);
        itemBlock(UndergardenBlocks.smogstem_log);
        itemBlock(UndergardenBlocks.smogstem_leaves);
        itemBlockFlat(UndergardenBlocks.wigglewood_sapling);
        itemBlock(UndergardenBlocks.wigglewood_log);
        itemBlock(UndergardenBlocks.wigglewood_leaves);
        itemBlock(UndergardenBlocks.smogstem_planks);
        itemBlock(UndergardenBlocks.wigglewood_planks);
        itemBlock(UndergardenBlocks.depthrock_bricks);
        itemBlock(UndergardenBlocks.cracked_depthrock_bricks);
        itemBlockFlat(UndergardenBlocks.tall_deepturf);
        itemBlockFlat(UndergardenBlocks.ditchbulb_plant);
        itemBlockFlat(UndergardenBlocks.indigo_mushroom);
        itemBlockFlat(UndergardenBlocks.veil_mushroom);
        itemBlockFlat(UndergardenBlocks.ink_mushroom);
        itemBlockFlat(UndergardenBlocks.blood_mushroom);
        itemBlock(UndergardenBlocks.gloomgourd);
        itemBlock(UndergardenBlocks.carved_gloomgourd);
        itemBlock(UndergardenBlocks.depthrock_pebbles);
        itemBlock(UndergardenBlocks.gloom_o_lantern);
        itemBlock(UndergardenBlocks.cloggrum_block);
        itemBlock(UndergardenBlocks.froststeel_block);
        itemBlock(UndergardenBlocks.utherium_block);
        itemBlock(UndergardenBlocks.depthrock_stairs);
        itemBlock(UndergardenBlocks.cobbled_depthrock_stairs);
        itemBlock(UndergardenBlocks.depthrock_brick_stairs);
        itemBlock(UndergardenBlocks.smogstem_stairs);
        itemBlock(UndergardenBlocks.wigglewood_stairs);
        itemBlock(UndergardenBlocks.depthrock_slab);
        itemBlock(UndergardenBlocks.cobbled_depthrock_slab);
        itemBlock(UndergardenBlocks.depthrock_brick_slab);
        itemBlock(UndergardenBlocks.smogstem_slab);
        itemBlock(UndergardenBlocks.wigglewood_slab);
        itemWall(UndergardenBlocks.cobbled_depthrock_wall, "cobbled_depthrock");
        itemWall(UndergardenBlocks.depthrock_brick_wall, "depthrock_bricks");
        itemFence(UndergardenBlocks.smogstem_fence, "smogstem_planks");
        itemFence(UndergardenBlocks.wigglewood_fence, "wigglewood_planks");
        itemBlockFlat(UndergardenBlocks.cloggrum_bars);
        itemBlockFlat(UndergardenBlocks.glowing_sea_grass);
        itemBlock(UndergardenBlocks.shiverstone);
        itemBlock(UndergardenBlocks.goo);
        itemBlock(UndergardenBlocks.smog_vent);
        itemBlockFlat(UndergardenBlocks.ashen_tall_deepturf);
        itemBlock(UndergardenBlocks.ashen_deepturf);
        itemBlock(UndergardenBlocks.regalium_ore);

        normalItem(UndergardenItems.catalyst_item);
        normalItem(UndergardenItems.depthrock_pebble);
        normalItem(UndergardenItems.smogstem_stick);
        normalItem(UndergardenItems.twistytwig);
        normalItem(UndergardenItems.cloggrum_ingot);
        normalItem(UndergardenItems.cloggrum_nugget);
        normalItem(UndergardenItems.froststeel_ingot);
        normalItem(UndergardenItems.froststeel_nugget);
        normalItem(UndergardenItems.utheric_shard);
        normalItem(UndergardenItems.utherium_ingot);
        normalItem(UndergardenItems.utherium_chunk);
        normalItem(UndergardenItems.regalium_ingot);
        normalItem(UndergardenItems.regalium_nugget);
        normalItem(UndergardenItems.smogstem_torch);
        normalItem(UndergardenItems.ditchbulb);
        normalItem(UndergardenItems.gloomgourd_seeds);
        normalItem(UndergardenItems.brute_tusk);
        normalItem(UndergardenItems.glowing_kelp);
        normalItem(UndergardenItems.goo_ball);

        toolItem(UndergardenItems.smogstem_sword);
        toolItem(UndergardenItems.smogstem_pickaxe);
        toolItem(UndergardenItems.smogstem_axe);
        toolItem(UndergardenItems.smogstem_shovel);

        toolItem(UndergardenItems.cloggrum_sword);
        toolItem(UndergardenItems.cloggrum_pickaxe);
        toolItem(UndergardenItems.cloggrum_axe);
        toolItem(UndergardenItems.cloggrum_shovel);

        normalItem(UndergardenItems.masticator_scales);
        normalItem(UndergardenItems.masticated_chestplate);

        normalItem(UndergardenItems.cloggrum_helmet);
        normalItem(UndergardenItems.cloggrum_chestplate);
        normalItem(UndergardenItems.cloggrum_leggings);
        normalItem(UndergardenItems.cloggrum_boots);

        toolItem(UndergardenItems.froststeel_sword);
        toolItem(UndergardenItems.froststeel_pickaxe);
        toolItem(UndergardenItems.froststeel_axe);
        toolItem(UndergardenItems.froststeel_shovel);

        toolItem(UndergardenItems.utheric_sword);
        toolItem(UndergardenItems.utheric_pickaxe);
        toolItem(UndergardenItems.utheric_axe);
        toolItem(UndergardenItems.utheric_shovel);

        normalItem(UndergardenItems.underbeans);
        normalItem(UndergardenItems.blisterberry);
        normalItem(UndergardenItems.gloomgourd_pie);
        normalItem(UndergardenItems.raw_dweller_meat);
        normalItem(UndergardenItems.dweller_steak);

        egg(UndergardenItems.dweller_spawn_egg);
        egg(UndergardenItems.gwibling_spawn_egg);
        egg(UndergardenItems.rotdweller_spawn_egg);
        egg(UndergardenItems.rotwalker_spawn_egg);
        egg(UndergardenItems.rotbeast_spawn_egg);
        egg(UndergardenItems.brute_spawn_egg);
        egg(UndergardenItems.scintling_spawn_egg);

        egg(UndergardenItems.masticator_spawn_egg);
    }


}
