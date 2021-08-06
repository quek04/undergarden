package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import quek.undergarden.Undergarden;
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
        itemBlock(UGBlocks.DEPTHROCK);
        itemBlock(UGBlocks.COAL_ORE);
        itemBlock(UGBlocks.CLOGGRUM_ORE);
        itemBlock(UGBlocks.FROSTSTEEL_ORE);
        itemBlock(UGBlocks.UTHERIUM_ORE);
        itemBlock(UGBlocks.DEEPTURF_BLOCK);
        itemBlock(UGBlocks.DEEPSOIL_FARMLAND);
        itemBlock(UGBlocks.DEEPSOIL);
        itemBlockFlat(UGBlocks.SMOGSTEM_SAPLING);
        itemBlock(UGBlocks.SMOGSTEM_LOG);
        itemBlock(UGBlocks.SMOGSTEM_LEAVES);
        itemBlockFlat(UGBlocks.WIGGLEWOOD_SAPLING);
        itemBlock(UGBlocks.WIGGLEWOOD_LOG);
        itemBlock(UGBlocks.WIGGLEWOOD_LEAVES);
        itemBlock(UGBlocks.SMOGSTEM_PLANKS);
        itemBlock(UGBlocks.WIGGLEWOOD_PLANKS);
        itemBlock(UGBlocks.DEPTHROCK_BRICKS);
        itemBlock(UGBlocks.CRACKED_DEPTHROCK_BRICKS);
        itemBlockFlat(UGBlocks.DEEPTURF);
        itemBlockFlat(UGBlocks.DITCHBULB_PLANT);
        itemBlockFlat(UGBlocks.INDIGO_MUSHROOM);
        itemBlockFlat(UGBlocks.VEIL_MUSHROOM);
        itemBlockFlat(UGBlocks.INK_MUSHROOM);
        itemBlockFlat(UGBlocks.BLOOD_MUSHROOM);
        itemBlock(UGBlocks.GLOOMGOURD);
        itemBlock(UGBlocks.CARVED_GLOOMGOURD);
        itemBlock(UGBlocks.DEPTHROCK_PEBBLES);
        itemBlock(UGBlocks.GLOOM_O_LANTERN);
        itemBlock(UGBlocks.SHARD_O_LANTERN);
        itemBlock(UGBlocks.CLOGGRUM_BLOCK);
        itemBlock(UGBlocks.FROSTSTEEL_BLOCK);
        itemBlock(UGBlocks.UTHERIUM_BLOCK);
        itemBlock(UGBlocks.DEPTHROCK_STAIRS);
        itemBlock(UGBlocks.DEPTHROCK_BRICK_STAIRS);
        itemBlock(UGBlocks.SMOGSTEM_STAIRS);
        itemBlock(UGBlocks.WIGGLEWOOD_STAIRS);
        itemBlock(UGBlocks.DEPTHROCK_SLAB);
        itemBlock(UGBlocks.DEPTHROCK_BRICK_SLAB);
        itemBlock(UGBlocks.SMOGSTEM_SLAB);
        itemBlock(UGBlocks.WIGGLEWOOD_SLAB);
        itemFence(UGBlocks.SMOGSTEM_FENCE, "smogstem_planks");
        itemFence(UGBlocks.WIGGLEWOOD_FENCE, "wigglewood_planks");
        itemBlockFlat(UGBlocks.CLOGGRUM_BARS);
        itemBlock(UGBlocks.SHIVERSTONE);
        itemBlock(UGBlocks.GOO);
        itemBlock(UGBlocks.SMOG_VENT);
        itemBlockFlat(UGBlocks.ASHEN_DEEPTURF);
        itemBlock(UGBlocks.ASHEN_DEEPTURF_BLOCK);
        itemBlock(UGBlocks.REGALIUM_ORE);
        itemBlock(UGBlocks.SHIVERSTONE_BRICKS);
        itemBlock(UGBlocks.SHIVERSTONE_STAIRS);
        itemBlock(UGBlocks.SHIVERSTONE_BRICK_STAIRS);
        itemBlock(UGBlocks.SHIVERSTONE_SLAB);
        itemBlock(UGBlocks.SHIVERSTONE_BRICK_SLAB);
        itemBlock(UGBlocks.REGALIUM_BLOCK);
        itemBlock(UGBlocks.TREMBLECRUST);
        itemBlock(UGBlocks.TREMBLECRUST_BRICKS);
        itemBlock(UGBlocks.CRACKED_TREMBLECRUST_BRICKS);
        itemBlock(UGBlocks.OTHERSIDE_UTHERIUM_ORE);
        itemBlock(UGBlocks.SMOGSTEM_WOOD);
        itemBlock(UGBlocks.WIGGLEWOOD_WOOD);
        itemBlock(UGBlocks.LOOSE_TREMBLECRUST);
        itemBlock(UGBlocks.IRON_ORE);
        itemBlock(UGBlocks.GOLD_ORE);
        itemBlock(UGBlocks.DIAMOND_ORE);
        itemBlock(UGBlocks.SMOGSTEM_FENCE_GATE);
        itemBlock(UGBlocks.WIGGLEWOOD_FENCE_GATE);
        wallInventory("depthrock_wall", new ResourceLocation(Undergarden.MODID, "block/depthrock"));
        wallInventory("depthrock_brick_wall", new ResourceLocation(Undergarden.MODID, "block/depthrock_bricks"));
        wallInventory("shiverstone_brick_wall", new ResourceLocation(Undergarden.MODID, "block/shiverstone_bricks"));
        wallInventory("shiverstone_wall", new ResourceLocation(Undergarden.MODID, "block/shiverstone"));
        wallInventory("tremblecrust_wall", new ResourceLocation(Undergarden.MODID, "block/tremblecrust"));
        wallInventory("tremblecrust_brick_wall", new ResourceLocation(Undergarden.MODID, "block/tremblecrust_bricks"));
        itemBlock(UGBlocks.COARSE_DEEPSOIL);
        itemBlock(UGBlocks.SMOGSTEM_PRESSURE_PLATE);
        itemBlock(UGBlocks.WIGGLEWOOD_PRESSURE_PLATE);
        itemBlock(UGBlocks.DEPTHROCK_PRESSURE_PLATE);
        itemBlock(UGBlocks.SHIVERSTONE_PRESSURE_PLATE);
        itemBlockFlat(UGBlocks.GRONGLE_SAPLING);
        itemBlock(UGBlocks.GRONGLE_LOG);
        itemBlock(UGBlocks.GRONGLE_LEAVES);
        itemBlock(UGBlocks.GRONGLE_WOOD);
        itemBlock(UGBlocks.GRONGLE_SLAB);
        itemFence(UGBlocks.GRONGLE_FENCE, "grongle_planks");
        itemBlock(UGBlocks.GRONGLE_PLANKS);
        itemBlock(UGBlocks.GRONGLE_FENCE_GATE);
        itemBlock(UGBlocks.GRONGLE_STAIRS);
        itemBlock(UGBlocks.GRONGLE_PRESSURE_PLATE);
        itemBlock(UGBlocks.STRIPPED_SMOGSTEM_LOG);
        itemBlock(UGBlocks.STRIPPED_WIGGLEWOOD_LOG);
        itemBlock(UGBlocks.STRIPPED_GRONGLE_LOG);
        itemBlock(UGBlocks.STRIPPED_SMOGSTEM_WOOD);
        itemBlock(UGBlocks.STRIPPED_WIGGLEWOOD_WOOD);
        itemBlock(UGBlocks.STRIPPED_GRONGLE_WOOD);
        itemBlock(UGBlocks.CRACKED_SHIVERSTONE_BRICKS);
        itemBlock(UGBlocks.BLOOD_MUSHROOM_GLOBULE);
        itemBlock(UGBlocks.FORGOTTEN_BLOCK);
        itemBlock(UGBlocks.CHISELED_DEPTHROCK_BRICKS);
        itemBlock(UGBlocks.CHISELED_SHIVERSTONE_BRICKS);
        itemBlock(UGBlocks.FROZEN_DEEPTURF_BLOCK);
        itemBlockFlat(UGBlocks.FROZEN_DEEPTURF);
        itemBlock(UGBlocks.CHISELED_TREMBLECRUST_BRICKS);
        itemBlock(UGBlocks.TREMBLECRUST_STAIRS);
        itemBlock(UGBlocks.TREMBLECRUST_BRICK_STAIRS);
        itemBlock(UGBlocks.TREMBLECRUST_SLAB);
        itemBlock(UGBlocks.TREMBLECRUST_BRICK_SLAB);
        itemBlock(UGBlocks.TREMBLECRUST_PRESSURE_PLATE);
        itemBlock(UGBlocks.GOO_BLOCK);
        itemBlock(UGBlocks.SEDIMENT);
        itemBlock(UGBlocks.SEDIMENT_GLASS);
        itemBlockFlat(UGBlocks.SEDIMENT_GLASS_PANE, "sediment_glass");
        itemBlock(UGBlocks.CLOGGRUM_TILES);
        itemBlock(UGBlocks.CLOGGRUM_TILE_STAIRS);
        itemBlock(UGBlocks.CLOGGRUM_TILE_SLAB);
        itemBlock(UGBlocks.DEPTHROCK_TILES);
        itemBlock(UGBlocks.DEPTHROCK_TILE_STAIRS);
        itemBlock(UGBlocks.DEPTHROCK_TILE_SLAB);
        itemBlockFlat(UGBlocks.HANGING_GRONGLE_LEAVES_TOP, "hanging_grongle_leaves");
        itemBlock(UGBlocks.MOGMOSS_RUG);

        normalItem(UGItems.MAMMOTH_DISC);
        normalItem(UGItems.LIMAX_MAXIMUS_DISC);
        normalItem(UGItems.GLOOMPER_ANTHEM_DISC);
        normalItem(UGItems.RELICT_DISC);
        normalItem(UGItems.GLOOMPER_SECRET_DISC);
        normalItem(UGItems.CATALYST);
        normalItem(UGItems.DEPTHROCK_PEBBLE);
        normalItem(UGItems.TWISTYTWIG);
        normalItem(UGItems.CLOGGRUM_INGOT);
        normalItem(UGItems.CLOGGRUM_NUGGET);
        normalItem(UGItems.FROSTSTEEL_INGOT);
        normalItem(UGItems.FROSTSTEEL_NUGGET);
        normalItem(UGItems.UTHERIC_SHARD);
        normalItem(UGItems.UTHERIUM_INGOT);
        normalItem(UGItems.UTHERIUM_CHUNK);
        normalItem(UGItems.REGALIUM_INGOT);
        normalItem(UGItems.REGALIUM_NUGGET);
        normalItem(UGItems.FORGOTTEN_INGOT);
        normalItem(UGItems.FORGOTTEN_NUGGET);
        torchItem(UGBlocks.SHARD_TORCH);
        normalItem(UGItems.DITCHBULB);
        normalItem(UGItems.GLOOMGOURD_SEEDS);
        normalItem(UGItems.BRUTE_TUSK);
        normalItem(UGItems.MOGMOSS);
        normalItem(UGItems.GLOWING_KELP);
        normalItem(UGItems.GOO_BALL);
        normalItem(UGItems.ROTTEN_BLISTERBERRY);
        normalItem(UGItems.BLISTERBOMB);
        normalItem(UGItems.DROOPFRUIT);

        toolItem(UGItems.CLOGGRUM_SWORD);
        toolItem(UGItems.CLOGGRUM_PICKAXE);
        toolItem(UGItems.CLOGGRUM_AXE);
        toolItem(UGItems.CLOGGRUM_SHOVEL);
        toolItem(UGItems.CLOGGRUM_HOE);

        normalItem(UGItems.MASTICATOR_SCALES);
        normalItem(UGItems.MASTICATED_CHESTPLATE);

        normalItem(UGItems.CLOGGRUM_HELMET);
        normalItem(UGItems.CLOGGRUM_CHESTPLATE);
        normalItem(UGItems.CLOGGRUM_LEGGINGS);
        normalItem(UGItems.CLOGGRUM_BOOTS);

        normalItem(UGItems.FROSTSTEEL_HELMET);
        normalItem(UGItems.FROSTSTEEL_CHESTPLATE);
        normalItem(UGItems.FROSTSTEEL_LEGGINGS);
        normalItem(UGItems.FROSTSTEEL_BOOTS);

        normalItem(UGItems.UTHERIUM_HELMET);
        normalItem(UGItems.UTHERIUM_CHESTPLATE);
        normalItem(UGItems.UTHERIUM_LEGGINGS);
        normalItem(UGItems.UTHERIUM_BOOTS);

        toolItem(UGItems.FROSTSTEEL_SWORD);
        toolItem(UGItems.FROSTSTEEL_PICKAXE);
        toolItem(UGItems.FROSTSTEEL_AXE);
        toolItem(UGItems.FROSTSTEEL_SHOVEL);
        toolItem(UGItems.FROSTSTEEL_HOE);

        toolItem(UGItems.UTHERIUM_SWORD);
        toolItem(UGItems.UTHERIUM_PICKAXE);
        toolItem(UGItems.UTHERIUM_AXE);
        toolItem(UGItems.UTHERIUM_SHOVEL);
        toolItem(UGItems.UTHERIUM_HOE);

        toolItem(UGItems.FORGOTTEN_SWORD);
        toolItem(UGItems.FORGOTTEN_PICKAXE);
        toolItem(UGItems.FORGOTTEN_AXE);
        toolItem(UGItems.FORGOTTEN_SHOVEL);
        toolItem(UGItems.FORGOTTEN_HOE);

        normalItem(UGItems.SMOGSTEM_BOAT);
        normalItem(UGItems.WIGGLEWOOD_BOAT);
        normalItem(UGItems.GRONGLE_BOAT);

        normalItem(UGItems.VIRULENT_MIX_BUCKET);

        normalItem(UGItems.GWIBLING_BUCKET);

        normalItem(UGItems.UNDERBEANS);
        normalItem(UGItems.ROASTED_UNDERBEANS);
        normalItem(UGItems.BLISTERBERRY);
        normalItem(UGItems.GLOOMGOURD_PIE);
        normalItem(UGItems.RAW_DWELLER_MEAT);
        normalItem(UGItems.DWELLER_STEAK);
        normalItem(UGItems.RAW_GWIBLING);
        normalItem(UGItems.COOKED_GWIBLING);
        normalItem(UGItems.RAW_GLOOMPER_LEG);
        normalItem(UGItems.GLOOMPER_LEG);
        normalItem(UGItems.BLOODY_STEW);
        normalItem(UGItems.INDIGO_STEW);
        normalItem(UGItems.INKY_STEW);
        normalItem(UGItems.VEILED_STEW);

        egg(UGItems.DWELLER_SPAWN_EGG);
        egg(UGItems.GWIBLING_SPAWN_EGG);
        egg(UGItems.ROTDWELLER_SPAWN_EGG);
        egg(UGItems.ROTLING_SPAWN_EGG);
        egg(UGItems.ROTWALKER_SPAWN_EGG);
        egg(UGItems.ROTBEAST_SPAWN_EGG);
        egg(UGItems.BRUTE_SPAWN_EGG);
        egg(UGItems.SCINTLING_SPAWN_EGG);
        egg(UGItems.GLOOMPER_SPAWN_EGG);
        egg(UGItems.STONEBORN_SPAWN_EGG);
        egg(UGItems.NARGOYLE_SPAWN_EGG);
        egg(UGItems.MUNCHER_SPAWN_EGG);
        egg(UGItems.SPLOOGIE_SPAWN_EGG);
        egg(UGItems.GWIB_SPAWN_EGG);
        egg(UGItems.MOG_SPAWN_EGG);

        egg(UGItems.MASTICATOR_SPAWN_EGG);
        egg(UGItems.FORGOTTEN_GUARDIAN_SPAWN_EGG);

        sign(UGBlocks.SMOGSTEM_SIGN);
        sign(UGBlocks.WIGGLEWOOD_SIGN);
        sign(UGBlocks.GRONGLE_SIGN);
    }
}
