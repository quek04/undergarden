package quek.undergarden.data;

import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import quek.undergarden.UGMod;
import quek.undergarden.data.provider.UGRecipeProvider;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGTags;

import java.util.function.Consumer;

public class UGRecipes extends UGRecipeProvider {

    public UGRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        makePlanks(UGBlocks.smogstem_planks, UGBlocks.smogstem_log).build(consumer);
        makePlanks(UGBlocks.smogstem_planks, UGBlocks.stripped_smogstem_log).build(consumer, name("smogstem_planks_from_stripped_log"));
        makePlanks(UGBlocks.smogstem_planks, UGBlocks.smogstem_wood).build(consumer, name("smogstem_planks_from_wood"));
        makePlanks(UGBlocks.smogstem_planks, UGBlocks.stripped_smogstem_wood).build(consumer, name("smogstem_planks_from_stripped_wood"));

        makePlanks(UGBlocks.wigglewood_planks, UGBlocks.wigglewood_log).build(consumer);
        makePlanks(UGBlocks.wigglewood_planks, UGBlocks.stripped_wigglewood_log).build(consumer, name("wigglewood_planks_from_stripped_log"));
        makePlanks(UGBlocks.wigglewood_planks, UGBlocks.wigglewood_wood).build(consumer, name("wigglewood_planks_from_wood"));
        makePlanks(UGBlocks.wigglewood_planks, UGBlocks.stripped_wigglewood_wood).build(consumer, name("wigglewood_planks_from_stripped_wood"));

        makePlanks(UGBlocks.grongle_planks, UGBlocks.grongle_stem).build(consumer);
        makePlanks(UGBlocks.grongle_planks, UGBlocks.stripped_grongle_stem).build(consumer, name("grongle_planks_from_stripped_stem"));
        makePlanks(UGBlocks.grongle_planks, UGBlocks.grongle_hyphae).build(consumer, name("grongle_planks_from_hyphae"));
        makePlanks(UGBlocks.grongle_planks, UGBlocks.stripped_grongle_hyphae).build(consumer, name("grongle_planks_from_stripped_hyphae"));

        makeBricks(UGBlocks.depthrock_bricks, UGBlocks.depthrock).build(consumer);
        makeBricks(UGBlocks.shiverstone_bricks, UGBlocks.shiverstone).build(consumer);
        makeBricks(UGBlocks.tremblecrust_bricks, UGBlocks.tremblecrust).build(consumer);

        makeChiseledBricks(UGBlocks.chiseled_depthrock_bricks, UGBlocks.depthrock_brick_slab).build(consumer);
        makeChiseledBricks(UGBlocks.chiseled_shiverstone_bricks, UGBlocks.shiverstone_brick_slab).build(consumer);

        makeWood(UGBlocks.smogstem_wood, UGBlocks.smogstem_log).build(consumer);
        makeWood(UGBlocks.stripped_smogstem_wood, UGBlocks.stripped_smogstem_log).build(consumer);
        makeWood(UGBlocks.wigglewood_wood, UGBlocks.wigglewood_log).build(consumer);
        makeWood(UGBlocks.stripped_wigglewood_wood, UGBlocks.stripped_wigglewood_log).build(consumer);
        makeWood(UGBlocks.grongle_hyphae, UGBlocks.grongle_stem).build(consumer);
        makeWood(UGBlocks.stripped_grongle_hyphae, UGBlocks.stripped_grongle_stem).build(consumer);

        makeBoat(UGItems.smogstem_boat, UGBlocks.smogstem_planks).build(consumer);
        makeBoat(UGItems.wigglewood_boat, UGBlocks.wigglewood_planks).build(consumer);
        makeBoat(UGItems.grongle_boat, UGBlocks.grongle_planks).build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UGItems.twistytwig.get(), 4)
                .patternLine("P ")
                .patternLine(" P")
                .key('P', UGBlocks.wigglewood_planks.get())
                .addCriterion("has_wigglewood_planks", hasItem(UGBlocks.wigglewood_planks.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Blocks.SCAFFOLDING, 6)
                .patternLine("STS")
                .patternLine("S S")
                .patternLine("S S")
                .key('S', Tags.Items.RODS_WOODEN)
                .key('T', UGItems.twistytwig.get())
                .addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN))
                .addCriterion("has_twistytwig", hasItem(UGItems.twistytwig.get()))
                .build(consumer, name("undergarden_scaffolding"));

        ShapedRecipeBuilder.shapedRecipe(UGBlocks.gloom_o_lantern.get())
                .patternLine("G")
                .patternLine("T")
                .key('G', UGBlocks.carved_gloomgourd.get())
                .key('T', Items.TORCH)
                .addCriterion("has_carved_gourd", hasItem(UGBlocks.carved_gloomgourd.get()))
                .addCriterion("has_torch", hasItem(Items.TORCH))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UGBlocks.cloggrum_bars.get(), 16)
                .patternLine("CCC")
                .patternLine("CCC")
                .key('C', UGItems.cloggrum_ingot.get())
                .addCriterion("has_cloggrum_ingot", hasItem(UGItems.cloggrum_ingot.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UGBlocks.coarse_deepsoil.get(), 4)
                .patternLine("DP")
                .patternLine("PD")
                .key('D', UGBlocks.deepsoil.get())
                .key('P', UGItems.depthrock_pebble.get())
                .addCriterion("has_deepsoil", hasItem(UGBlocks.deepsoil.get()))
                .addCriterion("has_pebble", hasItem(UGItems.depthrock_pebble.get()))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(UGBlocks.deepsoil.get())
                .addIngredient(UGBlocks.coarse_deepsoil.get())
                .addCriterion("has_coarse_deepsoil", hasItem(UGBlocks.coarse_deepsoil.get()))
                .build(consumer, name("coarse_deepsoil_to_normal"));

        ShapedRecipeBuilder.shapedRecipe(UGItems.slingshot.get())
                .patternLine("STS")
                .patternLine("SSS")
                .patternLine(" S ")
                .key('S', Tags.Items.RODS_WOODEN)
                .key('T', UGItems.twistytwig.get())
                .addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN))
                .addCriterion("has_twistytwig", hasItem(UGItems.twistytwig.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UGItems.blisterbomb.get())
                .patternLine(" T ")
                .patternLine("BBB")
                .patternLine("BBB")
                .key('T', UGItems.twistytwig.get())
                .key('B', UGItems.rotten_blisterberry.get())
                .addCriterion("has_twistytwig", hasItem(UGItems.twistytwig.get()))
                .addCriterion("has_blisterberry", hasItem(UGItems.rotten_blisterberry.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UGItems.cloggrum_shield.get())
                .patternLine("CSC")
                .patternLine("CCC")
                .patternLine(" C ")
                .key('S', UGBlocks.smogstem_planks.get())
                .key('C', UGItems.cloggrum_ingot.get())
                .addCriterion("has_scales", hasItem(UGItems.cloggrum_ingot.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UGItems.shard_torch.get(), 1)
                .patternLine("C")
                .patternLine("S")
                .key('C', UGItems.utheric_shard.get())
                .key('S', Tags.Items.RODS_WOODEN)
                .addCriterion("has_shard", hasItem(UGItems.utheric_shard.get()))
                .addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Blocks.TORCH, 2)
                .patternLine("D")
                .patternLine("S")
                .key('D', UGItems.ditchbulb.get())
                .key('S', Tags.Items.RODS_WOODEN)
                .addCriterion("has_ditchbulb", hasItem(UGItems.ditchbulb.get()))
                .addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN))
                .build(consumer, name("smogstem_torch_ditchbulb"));

        ShapedRecipeBuilder.shapedRecipe(UGItems.catalyst_item.get())
                .patternLine("GIG")
                .patternLine("IDI")
                .patternLine("GIG")
                .key('G', Items.GOLD_INGOT)
                .key('I', Items.IRON_INGOT)
                .key('D', Items.DIAMOND)
                .addCriterion("has_gold", hasItem(Items.GOLD_INGOT))
                .addCriterion("has_iron", hasItem(Items.IRON_INGOT))
                .addCriterion("has_diamond", hasItem(Items.DIAMOND))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(UGItems.gloomgourd_pie.get())
                .addIngredient(UGTags.Items.MUSHROOMS)
                .addIngredient(UGBlocks.gloomgourd.get())
                .addIngredient(UGItems.glowing_kelp.get())
                .addCriterion("has_mushroom", hasItem(UGTags.Items.MUSHROOMS))
                .addCriterion("has_gloomgourd", hasItem(UGBlocks.gloomgourd.get()))
                .addCriterion("has_kelp", hasItem(UGItems.glowing_kelp.get()))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(UGItems.gloomgourd_seeds.get(), 4)
                .addIngredient(UGBlocks.gloomgourd.get())
                .addCriterion("has_gloomgourd", hasItem(UGBlocks.gloomgourd.get()))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(Items.BONE_MEAL, 4)
                .addIngredient(UGItems.brute_tusk.get())
                .addCriterion("has_tusk", hasItem(UGItems.brute_tusk.get()))
                .build(consumer, name("tusk_to_bonemeal"));

        ShapelessRecipeBuilder.shapelessRecipe(Items.RED_DYE, 9)
                .addIngredient(UGBlocks.blood_mushroom_globule.get())
                .addCriterion("has_globule", hasItem(UGBlocks.blood_mushroom_globule.get()))
                .build(consumer, name("globule_to_dye"));

        ShapelessRecipeBuilder.shapelessRecipe(Items.RED_DYE)
                .addIngredient(UGBlocks.blood_mushroom.get())
                .addCriterion("has_blood_mushroom", hasItem(UGBlocks.blood_mushroom.get()))
                .build(consumer, name("blood_mushroom_to_dye"));

        ShapelessRecipeBuilder.shapelessRecipe(Items.BLACK_DYE)
                .addIngredient(UGBlocks.ink_mushroom.get())
                .addCriterion("has_ink_mushroom", hasItem(UGBlocks.ink_mushroom.get()))
                .build(consumer, name("ink_mushroom_to_dye"));

        ShapelessRecipeBuilder.shapelessRecipe(Items.BLUE_DYE)
                .addIngredient(UGBlocks.indigo_mushroom.get())
                .addCriterion("has_indigo_mushroom", hasItem(UGBlocks.indigo_mushroom.get()))
                .build(consumer, name("indigo_mushroom_to_dye"));

        ShapelessRecipeBuilder.shapelessRecipe(Items.WHITE_DYE)
                .addIngredient(UGBlocks.veil_mushroom.get())
                .addCriterion("has_veil_mushroom", hasItem(UGBlocks.veil_mushroom.get()))
                .build(consumer, name("veil_mushroom_to_dye"));

        ShapedRecipeBuilder.shapedRecipe(UGItems.utherium_chunk.get())
                .patternLine("SS")
                .patternLine("SS")
                .key('S', UGItems.utheric_shard.get())
                .addCriterion("has_shard", hasItem(UGItems.utheric_shard.get()));

        makeIngotToBlock(UGBlocks.cloggrum_block, UGItems.cloggrum_ingot).build(consumer);
        makeIngotToBlock(UGBlocks.froststeel_block, UGItems.froststeel_ingot).build(consumer);
        makeIngotToBlock(UGBlocks.utherium_block, UGItems.utherium_ingot).build(consumer);
        makeIngotToBlock(UGBlocks.regalium_block, UGItems.regalium_ingot).build(consumer);
        makeIngotToBlock(UGBlocks.forgotten_block, UGItems.forgotten_ingot).build(consumer);
        makeIngotToBlock(UGBlocks.depthrock, UGItems.depthrock_pebble).build(consumer, name("pebbles_to_depthrock"));

        makeBlockToIngot(UGItems.cloggrum_ingot, UGBlocks.cloggrum_block).build(consumer, name("cloggrum_block_to_ingot"));
        makeBlockToIngot(UGItems.froststeel_ingot, UGBlocks.froststeel_block).build(consumer, name("froststeel_block_to_ingot"));
        makeBlockToIngot(UGItems.utherium_ingot, UGBlocks.utherium_block).build(consumer, name("utherium_block_to_ingot"));
        makeBlockToIngot(UGItems.regalium_ingot, UGBlocks.regalium_block).build(consumer, name("regalium_block_to_ingot"));
        makeBlockToIngot(UGItems.forgotten_ingot, UGBlocks.forgotten_block).build(consumer, name("forgotten_block_to_ingot"));

        makeIngotToNugget(UGItems.cloggrum_nugget, UGItems.cloggrum_ingot).build(consumer, name("cloggrum_ingot_to_nugget"));
        makeIngotToNugget(UGItems.froststeel_nugget, UGItems.froststeel_ingot).build(consumer, name("froststeel_ingot_to_nugget"));
        makeIngotToNugget(UGItems.utherium_chunk, UGItems.utherium_ingot).build(consumer, name("utherium_ingot_to_nugget"));
        makeIngotToNugget(UGItems.regalium_nugget, UGItems.regalium_ingot).build(consumer, name("regalium_ingot_to_nugget"));
        makeIngotToNugget(UGItems.forgotten_nugget, UGItems.forgotten_ingot).build(consumer, name("forgotten_ingot_to_nugget"));

        makeNuggetToIngot(UGItems.cloggrum_ingot, UGItems.cloggrum_nugget).build(consumer, name("cloggrum_nugget_to_ingot"));
        makeNuggetToIngot(UGItems.froststeel_ingot, UGItems.froststeel_nugget).build(consumer, name("froststeel_nugget_to_ingot"));
        makeNuggetToIngot(UGItems.utherium_ingot, UGItems.utherium_chunk).build(consumer, name("utherium_chunk_to_ingot"));
        makeNuggetToIngot(UGItems.regalium_ingot, UGItems.regalium_nugget).build(consumer, name("regalium_chunk_to_ingot"));
        makeNuggetToIngot(UGItems.forgotten_ingot, UGItems.forgotten_nugget).build(consumer, name("forgotten_chunk_to_ingot"));

        makeSword(UGItems.cloggrum_sword, UGItems.cloggrum_ingot).build(consumer);
        makeSword(UGItems.froststeel_sword, UGItems.froststeel_ingot).build(consumer);
        makeSword(UGItems.utheric_sword, UGItems.utherium_ingot).build(consumer);

        makePickaxe(UGItems.cloggrum_pickaxe, UGItems.cloggrum_ingot).build(consumer);
        makePickaxe(UGItems.froststeel_pickaxe, UGItems.froststeel_ingot).build(consumer);
        makePickaxe(UGItems.utheric_pickaxe, UGItems.utherium_ingot).build(consumer);

        makeAxe(UGItems.cloggrum_axe, UGItems.cloggrum_ingot).build(consumer);
        makeAxe(UGItems.froststeel_axe, UGItems.froststeel_ingot).build(consumer);
        makeAxe(UGItems.utheric_axe, UGItems.utherium_ingot).build(consumer);

        makeShovel(UGItems.cloggrum_shovel, UGItems.cloggrum_ingot).build(consumer);
        makeShovel(UGItems.froststeel_shovel, UGItems.froststeel_ingot).build(consumer);
        makeShovel(UGItems.utheric_shovel, UGItems.utherium_ingot).build(consumer);

        makeHoe(UGItems.cloggrum_hoe, UGItems.cloggrum_ingot).build(consumer);
        makeHoe(UGItems.froststeel_hoe, UGItems.froststeel_ingot).build(consumer);
        makeHoe(UGItems.utheric_hoe, UGItems.utherium_ingot).build(consumer);

        makeChestplate(UGItems.masticated_chestplate, UGItems.masticator_scales).build(consumer);

        makeHelmet(UGItems.cloggrum_helmet, UGItems.cloggrum_ingot).build(consumer);
        makeChestplate(UGItems.cloggrum_chestplate, UGItems.cloggrum_ingot).build(consumer);
        makeLeggings(UGItems.cloggrum_leggings, UGItems.cloggrum_ingot).build(consumer);
        makeBoots(UGItems.cloggrum_boots, UGItems.cloggrum_ingot).build(consumer);

        makeHelmet(UGItems.froststeel_helmet, UGItems.froststeel_ingot).build(consumer);
        makeChestplate(UGItems.froststeel_chestplate, UGItems.froststeel_ingot).build(consumer);
        makeLeggings(UGItems.froststeel_leggings, UGItems.froststeel_ingot).build(consumer);
        makeBoots(UGItems.froststeel_boots, UGItems.froststeel_ingot).build(consumer);

        makeHelmet(UGItems.utheric_helmet, UGItems.utherium_ingot).build(consumer);
        makeChestplate(UGItems.utheric_chestplate, UGItems.utherium_ingot).build(consumer);
        makeLeggings(UGItems.utheric_leggings, UGItems.utherium_ingot).build(consumer);
        makeBoots(UGItems.utheric_boots, UGItems.utherium_ingot).build(consumer);

        makeStairs(UGBlocks.depthrock_stairs, UGBlocks.depthrock).build(consumer);
        makeStairs(UGBlocks.depthrock_brick_stairs, UGBlocks.depthrock_bricks).build(consumer);
        makeStairs(UGBlocks.smogstem_stairs, UGBlocks.smogstem_planks).build(consumer);
        makeStairs(UGBlocks.wigglewood_stairs, UGBlocks.wigglewood_planks).build(consumer);
        makeStairs(UGBlocks.grongle_stairs, UGBlocks.grongle_planks).build(consumer);
        makeStairs(UGBlocks.shiverstone_stairs, UGBlocks.shiverstone).build(consumer);
        makeStairs(UGBlocks.shiverstone_brick_stairs, UGBlocks.shiverstone_bricks).build(consumer);

        makeSlab(UGBlocks.depthrock_slab, UGBlocks.depthrock).build(consumer);
        makeSlab(UGBlocks.depthrock_brick_slab, UGBlocks.depthrock_bricks).build(consumer);
        makeSlab(UGBlocks.smogstem_slab, UGBlocks.smogstem_planks).build(consumer);
        makeSlab(UGBlocks.wigglewood_slab, UGBlocks.wigglewood_planks).build(consumer);
        makeSlab(UGBlocks.grongle_slab, UGBlocks.grongle_planks).build(consumer);
        makeSlab(UGBlocks.shiverstone_slab, UGBlocks.shiverstone).build(consumer);
        makeSlab(UGBlocks.shiverstone_brick_slab, UGBlocks.shiverstone_bricks).build(consumer);

        makeWall(UGBlocks.depthrock_wall, UGBlocks.depthrock).build(consumer);
        makeWall(UGBlocks.depthrock_brick_wall, UGBlocks.depthrock_bricks).build(consumer);
        makeWall(UGBlocks.shiverstone_wall, UGBlocks.shiverstone).build(consumer);
        makeWall(UGBlocks.shiverstone_brick_wall, UGBlocks.shiverstone_bricks).build(consumer);

        makeFence(UGBlocks.smogstem_fence, UGBlocks.smogstem_planks).build(consumer);
        makeFence(UGBlocks.wigglewood_fence, UGBlocks.wigglewood_planks).build(consumer);
        makeFence(UGBlocks.grongle_fence, UGBlocks.grongle_planks).build(consumer);

        makeFenceGate(UGBlocks.smogstem_fence_gate, UGBlocks.smogstem_planks).build(consumer);
        makeFenceGate(UGBlocks.wigglewood_fence_gate, UGBlocks.wigglewood_planks).build(consumer);
        makeFenceGate(UGBlocks.grongle_fence_gate, UGBlocks.grongle_planks).build(consumer);

        makeDoor(UGBlocks.smogstem_door, UGBlocks.smogstem_planks).build(consumer);
        makeDoor(UGBlocks.wigglewood_door, UGBlocks.wigglewood_planks).build(consumer);
        makeDoor(UGBlocks.grongle_door, UGBlocks.grongle_planks).build(consumer);

        makeTrapdoor(UGBlocks.smogstem_trapdoor, UGBlocks.smogstem_planks).build(consumer);
        makeTrapdoor(UGBlocks.wigglewood_trapdoor, UGBlocks.wigglewood_planks).build(consumer);
        makeTrapdoor(UGBlocks.grongle_trapdoor, UGBlocks.grongle_planks).build(consumer);

        makeButton(UGBlocks.smogstem_button, UGBlocks.smogstem_planks).build(consumer);
        makeButton(UGBlocks.wigglewood_button, UGBlocks.wigglewood_planks).build(consumer);
        makeButton(UGBlocks.grongle_button, UGBlocks.grongle_planks).build(consumer);
        makeButton(UGBlocks.depthrock_button, UGBlocks.depthrock).build(consumer);
        makeButton(UGBlocks.shiverstone_button, UGBlocks.shiverstone).build(consumer);

        makePressurePlate(UGBlocks.smogstem_pressure_plate, UGBlocks.smogstem_planks).build(consumer);
        makePressurePlate(UGBlocks.wigglewood_pressure_plate, UGBlocks.wigglewood_planks).build(consumer);
        makePressurePlate(UGBlocks.grongle_pressure_plate, UGBlocks.grongle_planks).build(consumer);
        makePressurePlate(UGBlocks.depthrock_pressure_plate, UGBlocks.depthrock).build(consumer);
        makePressurePlate(UGBlocks.shiverstone_pressure_plate, UGBlocks.shiverstone).build(consumer);

        makeStew(UGItems.bloody_stew, UGBlocks.blood_mushroom).build(consumer);
        makeStew(UGItems.inky_stew, UGBlocks.ink_mushroom).build(consumer);
        makeStew(UGItems.indigo_stew, UGBlocks.indigo_mushroom).build(consumer);
        makeStew(UGItems.veiled_stew, UGBlocks.veil_mushroom).build(consumer);

        smeltingRecipe(UGBlocks.cracked_depthrock_bricks.get(), UGBlocks.depthrock_bricks.get(), .1F).build(consumer, "smelt_depthrock_bricks");
        smeltingRecipe(UGBlocks.cracked_shiverstone_bricks.get(), UGBlocks.shiverstone_bricks.get(), .1F).build(consumer, "smelt_shiverstone_bricks");

        smeltingRecipe(Items.IRON_INGOT, UGItems.catalyst_item.get(), 1F).build(consumer, "smelt_catalyst");

        smeltingRecipe(Items.COAL, UGBlocks.coal_ore.get(), .7F).build(consumer, name("smelt_undergarden_coal"));
        blastingRecipe(Items.COAL, UGBlocks.coal_ore.get(), .7F).build(consumer, name("blast_undergarden_coal"));
        smeltingRecipe(Items.IRON_INGOT, UGBlocks.iron_ore.get(), .7F).build(consumer, name("smelt_undergarden_iron"));
        blastingRecipe(Items.IRON_INGOT, UGBlocks.iron_ore.get(), .7F).build(consumer, name("blast_undergarden_iron"));
        smeltingRecipe(Items.GOLD_INGOT, UGBlocks.gold_ore.get(), .7F).build(consumer, name("smelt_undergarden_gold"));
        blastingRecipe(Items.GOLD_INGOT, UGBlocks.gold_ore.get(), .7F).build(consumer, name("blast_undergarden_gold"));
        smeltingRecipe(Items.DIAMOND, UGBlocks.diamond_ore.get(), .7F).build(consumer, name("smelt_undergarden_diamond"));
        blastingRecipe(Items.DIAMOND, UGBlocks.diamond_ore.get(), .7F).build(consumer, name("blast_undergarden_diamond"));

        smeltingRecipe(UGItems.cloggrum_ingot.get(), UGBlocks.cloggrum_ore.get(), .7F).build(consumer, name("smelt_cloggrum_ore"));
        blastingRecipe(UGItems.cloggrum_ingot.get(), UGBlocks.cloggrum_ore.get(), .7F).build(consumer, name("blast_cloggrum_ore"));
        smeltingRecipe(UGItems.froststeel_ingot.get(), UGBlocks.froststeel_ore.get(), .7F).build(consumer, name("smelt_froststeel_ore"));
        blastingRecipe(UGItems.froststeel_ingot.get(), UGBlocks.froststeel_ore.get(), .7F).build(consumer, name("blast_froststeel_ore"));
        smeltingRecipe(UGItems.utherium_chunk.get(), UGBlocks.utherium_ore.get(), .9F).build(consumer, name("smelt_utherium_ore"));
        blastingRecipe(UGItems.utherium_chunk.get(), UGBlocks.utherium_ore.get(), .9F).build(consumer, name("blast_utherium_ore"));
        smeltingRecipe(UGItems.regalium_ingot.get(), UGBlocks.regalium_ore.get(), 1F).build(consumer, name("smelt_regalium_ore"));
        blastingRecipe(UGItems.regalium_ingot.get(), UGBlocks.regalium_ore.get(), 1F).build(consumer, name("blast_regalium_ore"));

        smeltingRecipeTag(UGItems.cloggrum_nugget.get(), UGTags.Items.CLOGGRUM_ITEMS, .1F).build(consumer, name("smelt_cloggrum_item"));
        blastingRecipeTag(UGItems.cloggrum_nugget.get(), UGTags.Items.CLOGGRUM_ITEMS, .1F).build(consumer, name("blast_cloggrum_item"));

        smeltingRecipeTag(UGItems.froststeel_nugget.get(), UGTags.Items.FROSTSTEEL_ITEMS, .1F).build(consumer, name("smelt_froststeel_item"));
        blastingRecipeTag(UGItems.froststeel_nugget.get(), UGTags.Items.FROSTSTEEL_ITEMS, .1F).build(consumer, name("blast_froststeel_item"));

        smeltingRecipeTag(UGItems.utheric_shard.get(), UGTags.Items.UTHERIUM_ITEMS, .1F).build(consumer, name("smelt_utherium_item"));
        blastingRecipeTag(UGItems.utheric_shard.get(), UGTags.Items.UTHERIUM_ITEMS, .1F).build(consumer, name("blast_utherium_item"));

        smeltingRecipe(UGItems.dweller_steak.get(), UGItems.raw_dweller_meat.get(), .35F).build(consumer, name("smelt_dweller_meat"));
        smokingRecipe(UGItems.dweller_steak.get(), UGItems.raw_dweller_meat.get(), .35F).build(consumer, name("smoke_dweller_meat"));

        smeltingRecipe(UGItems.cooked_gwibling.get(), UGItems.raw_gwibling.get(), .35F).build(consumer, name("smelt_gwibling"));
        smokingRecipe(UGItems.cooked_gwibling.get(), UGItems.raw_gwibling.get(), .35F).build(consumer, name("smoke_gwibling"));

        smeltingRecipe(Items.DRIED_KELP, UGItems.glowing_kelp.get(), 0.1F).build(consumer, name("smelt_glowing_kelp"));
        smokingRecipe(Items.DRIED_KELP, UGItems.glowing_kelp.get(), 0.1F).build(consumer, name("smoke_glowing_kelp"));
    }

    private ResourceLocation name(String name) {
        return new ResourceLocation(UGMod.MODID, name);
    }
}
