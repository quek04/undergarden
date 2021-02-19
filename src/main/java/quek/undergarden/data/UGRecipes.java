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
        makePlanks(UGBlocks.SMOGSTEM_PLANKS, UGTags.Items.SMOGSTEM_LOGS).build(consumer);
        makePlanks(UGBlocks.WIGGLEWOOD_PLANKS, UGTags.Items.WIGGLEWOOD_LOGS).build(consumer);
        makePlanks(UGBlocks.GRONGLE_PLANKS, UGTags.Items.GRONGLE_STEMS).build(consumer);

        makeBricks(UGBlocks.DEPTHROCK_BRICKS, UGBlocks.DEPTHROCK).build(consumer);
        makeBricks(UGBlocks.SHIVERSTONE_BRICKS, UGBlocks.SHIVERSTONE).build(consumer);
        makeBricks(UGBlocks.TREMBLECRUST_BRICKS, UGBlocks.TREMBLECRUST).build(consumer);

        makeChiseledBricks(UGBlocks.CHISELED_DEPTHROCK_BRICKS, UGBlocks.DEPTHROCK_BRICK_SLAB).build(consumer);
        makeChiseledBricks(UGBlocks.CHISELED_SHIVERSTONE_BRICKS, UGBlocks.SHIVERSTONE_BRICK_SLAB).build(consumer);

        makeWood(UGBlocks.SMOGSTEM_WOOD, UGBlocks.SMOGSTEM_LOG).build(consumer);
        makeWood(UGBlocks.STRIPPED_SMOGSTEM_WOOD, UGBlocks.STRIPPED_SMOGSTEM_LOG).build(consumer);
        makeWood(UGBlocks.WIGGLEWOOD_WOOD, UGBlocks.WIGGLEWOOD_LOG).build(consumer);
        makeWood(UGBlocks.STRIPPED_WIGGLEWOOD_WOOD, UGBlocks.STRIPPED_WIGGLEWOOD_LOG).build(consumer);
        makeWood(UGBlocks.GRONGLE_HYPHAE, UGBlocks.GRONGLE_STEM).build(consumer);
        makeWood(UGBlocks.STRIPPED_GRONGLE_HYPHAE, UGBlocks.STRIPPED_GRONGLE_STEM).build(consumer);

        makeBoat(UGItems.SMOGSTEM_BOAT, UGBlocks.SMOGSTEM_PLANKS).build(consumer);
        makeBoat(UGItems.WIGGLEWOOD_BOAT, UGBlocks.WIGGLEWOOD_PLANKS).build(consumer);
        makeBoat(UGItems.GRONGLE_BOAT, UGBlocks.GRONGLE_PLANKS).build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UGItems.TWISTYTWIG.get(), 4)
                .patternLine("P ")
                .patternLine(" P")
                .key('P', UGBlocks.WIGGLEWOOD_PLANKS.get())
                .addCriterion("has_wigglewood_planks", hasItem(UGBlocks.WIGGLEWOOD_PLANKS.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Blocks.SCAFFOLDING, 6)
                .patternLine("STS")
                .patternLine("S S")
                .patternLine("S S")
                .key('S', Tags.Items.RODS_WOODEN)
                .key('T', UGItems.TWISTYTWIG.get())
                .addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN))
                .addCriterion("has_twistytwig", hasItem(UGItems.TWISTYTWIG.get()))
                .build(consumer, name("undergarden_scaffolding"));

        ShapedRecipeBuilder.shapedRecipe(UGBlocks.GLOOM_O_LANTERN.get())
                .patternLine("G")
                .patternLine("T")
                .key('G', UGBlocks.CARVED_GLOOMGOURD.get())
                .key('T', Items.TORCH)
                .addCriterion("has_carved_gourd", hasItem(UGBlocks.CARVED_GLOOMGOURD.get()))
                .addCriterion("has_torch", hasItem(Items.TORCH))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UGBlocks.CLOGGRUM_BARS.get(), 16)
                .patternLine("CCC")
                .patternLine("CCC")
                .key('C', UGItems.CLOGGRUM_INGOT.get())
                .addCriterion("has_cloggrum_ingot", hasItem(UGItems.CLOGGRUM_INGOT.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UGBlocks.COARSE_DEEPSOIL.get(), 4)
                .patternLine("DP")
                .patternLine("PD")
                .key('D', UGBlocks.DEEPSOIL.get())
                .key('P', UGItems.DEPTHROCK_PEBBLE.get())
                .addCriterion("has_deepsoil", hasItem(UGBlocks.DEEPSOIL.get()))
                .addCriterion("has_pebble", hasItem(UGItems.DEPTHROCK_PEBBLE.get()))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(UGBlocks.DEEPSOIL.get())
                .addIngredient(UGBlocks.COARSE_DEEPSOIL.get())
                .addCriterion("has_coarse_deepsoil", hasItem(UGBlocks.COARSE_DEEPSOIL.get()))
                .build(consumer, name("coarse_deepsoil_to_normal"));

        ShapedRecipeBuilder.shapedRecipe(UGItems.SLINGSHOT.get())
                .patternLine("STS")
                .patternLine("SSS")
                .patternLine(" S ")
                .key('S', Tags.Items.RODS_WOODEN)
                .key('T', UGItems.TWISTYTWIG.get())
                .addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN))
                .addCriterion("has_twistytwig", hasItem(UGItems.TWISTYTWIG.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UGItems.BLISTERBOMB.get())
                .patternLine(" T ")
                .patternLine("BBB")
                .patternLine("BBB")
                .key('T', UGItems.TWISTYTWIG.get())
                .key('B', UGItems.ROTTEN_BLISTERBERRY.get())
                .addCriterion("has_twistytwig", hasItem(UGItems.TWISTYTWIG.get()))
                .addCriterion("has_blisterberry", hasItem(UGItems.ROTTEN_BLISTERBERRY.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UGItems.CLOGGRUM_SHIELD.get())
                .patternLine("CSC")
                .patternLine("CCC")
                .patternLine(" C ")
                .key('S', UGBlocks.SMOGSTEM_PLANKS.get())
                .key('C', UGItems.CLOGGRUM_INGOT.get())
                .addCriterion("has_scales", hasItem(UGItems.CLOGGRUM_INGOT.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UGItems.SHARD_TORCH.get(), 1)
                .patternLine("C")
                .patternLine("S")
                .key('C', UGItems.UTHERIC_SHARD.get())
                .key('S', Tags.Items.RODS_WOODEN)
                .addCriterion("has_shard", hasItem(UGItems.UTHERIC_SHARD.get()))
                .addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Blocks.TORCH, 2)
                .patternLine("D")
                .patternLine("S")
                .key('D', UGItems.DITCHBULB.get())
                .key('S', Tags.Items.RODS_WOODEN)
                .addCriterion("has_ditchbulb", hasItem(UGItems.DITCHBULB.get()))
                .addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN))
                .build(consumer, name("smogstem_torch_ditchbulb"));

        ShapedRecipeBuilder.shapedRecipe(UGItems.CATALYST.get())
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

        ShapelessRecipeBuilder.shapelessRecipe(UGItems.GLOOMGOURD_PIE.get())
                .addIngredient(UGTags.Items.MUSHROOMS)
                .addIngredient(UGBlocks.GLOOMGOURD.get())
                .addIngredient(UGItems.GLOWING_KELP.get())
                .addCriterion("has_mushroom", hasItem(UGTags.Items.MUSHROOMS))
                .addCriterion("has_gloomgourd", hasItem(UGBlocks.GLOOMGOURD.get()))
                .addCriterion("has_kelp", hasItem(UGItems.GLOWING_KELP.get()))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(UGItems.GLOOMGOURD_SEEDS.get(), 4)
                .addIngredient(UGBlocks.GLOOMGOURD.get())
                .addCriterion("has_gloomgourd", hasItem(UGBlocks.GLOOMGOURD.get()))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(Items.BONE_MEAL, 4)
                .addIngredient(UGItems.BRUTE_TUSK.get())
                .addCriterion("has_tusk", hasItem(UGItems.BRUTE_TUSK.get()))
                .build(consumer, name("tusk_to_bonemeal"));

        ShapelessRecipeBuilder.shapelessRecipe(Items.RED_DYE, 9)
                .addIngredient(UGBlocks.BLOOD_MUSHROOM_GLOBULE.get())
                .addCriterion("has_globule", hasItem(UGBlocks.BLOOD_MUSHROOM_GLOBULE.get()))
                .build(consumer, name("globule_to_dye"));

        ShapelessRecipeBuilder.shapelessRecipe(Items.RED_DYE)
                .addIngredient(UGBlocks.BLOOD_MUSHROOM.get())
                .addCriterion("has_blood_mushroom", hasItem(UGBlocks.BLOOD_MUSHROOM.get()))
                .build(consumer, name("blood_mushroom_to_dye"));

        ShapelessRecipeBuilder.shapelessRecipe(Items.BLACK_DYE)
                .addIngredient(UGBlocks.INK_MUSHROOM.get())
                .addCriterion("has_ink_mushroom", hasItem(UGBlocks.INK_MUSHROOM.get()))
                .build(consumer, name("ink_mushroom_to_dye"));

        ShapelessRecipeBuilder.shapelessRecipe(Items.BLUE_DYE)
                .addIngredient(UGBlocks.INDIGO_MUSHROOM.get())
                .addCriterion("has_indigo_mushroom", hasItem(UGBlocks.INDIGO_MUSHROOM.get()))
                .build(consumer, name("indigo_mushroom_to_dye"));

        ShapelessRecipeBuilder.shapelessRecipe(Items.WHITE_DYE)
                .addIngredient(UGBlocks.VEIL_MUSHROOM.get())
                .addCriterion("has_veil_mushroom", hasItem(UGBlocks.VEIL_MUSHROOM.get()))
                .build(consumer, name("veil_mushroom_to_dye"));

        ShapedRecipeBuilder.shapedRecipe(UGItems.UTHERIUM_CHUNK.get())
                .patternLine("SS")
                .patternLine("SS")
                .key('S', UGItems.UTHERIC_SHARD.get())
                .addCriterion("has_shard", hasItem(UGItems.UTHERIC_SHARD.get()))
                .build(consumer, name("shard_to_chunk"));

        ShapedRecipeBuilder.shapedRecipe(Items.LEAD, 4)
                .patternLine("TT ")
                .patternLine("TG ")
                .patternLine("  T")
                .key('T', UGItems.TWISTYTWIG.get())
                .key('G', UGItems.GOO_BALL.get())
                .addCriterion("has_twistytwig", hasItem(UGItems.TWISTYTWIG.get()))
                .addCriterion("has_goo_ball", hasItem(UGItems.GOO_BALL.get()))
                .build(consumer, name("undergarden_lead"));

        makeIngotToBlock(UGBlocks.CLOGGRUM_BLOCK, UGItems.CLOGGRUM_INGOT).build(consumer);
        makeIngotToBlock(UGBlocks.FROSTSTEEL_BLOCK, UGItems.FROSTSTEEL_INGOT).build(consumer);
        makeIngotToBlock(UGBlocks.UTHERIUM_BLOCK, UGItems.UTHERIUM_INGOT).build(consumer);
        makeIngotToBlock(UGBlocks.REGALIUM_BLOCK, UGItems.REGALIUM_INGOT).build(consumer);
        makeIngotToBlock(UGBlocks.FORGOTTEN_BLOCK, UGItems.FORGOTTEN_INGOT).build(consumer);
        makeIngotToBlock(UGBlocks.DEPTHROCK, UGItems.DEPTHROCK_PEBBLE).build(consumer, name("pebbles_to_depthrock"));

        makeBlockToIngot(UGItems.CLOGGRUM_INGOT, UGBlocks.CLOGGRUM_BLOCK).build(consumer, name("cloggrum_block_to_ingot"));
        makeBlockToIngot(UGItems.FROSTSTEEL_INGOT, UGBlocks.FROSTSTEEL_BLOCK).build(consumer, name("froststeel_block_to_ingot"));
        makeBlockToIngot(UGItems.UTHERIUM_INGOT, UGBlocks.UTHERIUM_BLOCK).build(consumer, name("utherium_block_to_ingot"));
        makeBlockToIngot(UGItems.REGALIUM_INGOT, UGBlocks.REGALIUM_BLOCK).build(consumer, name("regalium_block_to_ingot"));
        makeBlockToIngot(UGItems.FORGOTTEN_INGOT, UGBlocks.FORGOTTEN_BLOCK).build(consumer, name("forgotten_block_to_ingot"));

        makeIngotToNugget(UGItems.CLOGGRUM_NUGGET, UGItems.CLOGGRUM_INGOT).build(consumer, name("cloggrum_ingot_to_nugget"));
        makeIngotToNugget(UGItems.FROSTSTEEL_NUGGET, UGItems.FROSTSTEEL_INGOT).build(consumer, name("froststeel_ingot_to_nugget"));
        makeIngotToNugget(UGItems.UTHERIUM_CHUNK, UGItems.UTHERIUM_INGOT).build(consumer, name("utherium_ingot_to_nugget"));
        makeIngotToNugget(UGItems.REGALIUM_NUGGET, UGItems.REGALIUM_INGOT).build(consumer, name("regalium_ingot_to_nugget"));
        makeIngotToNugget(UGItems.FORGOTTEN_NUGGET, UGItems.FORGOTTEN_INGOT).build(consumer, name("forgotten_ingot_to_nugget"));

        makeNuggetToIngot(UGItems.CLOGGRUM_INGOT, UGItems.CLOGGRUM_NUGGET).build(consumer, name("cloggrum_nugget_to_ingot"));
        makeNuggetToIngot(UGItems.FROSTSTEEL_INGOT, UGItems.FROSTSTEEL_NUGGET).build(consumer, name("froststeel_nugget_to_ingot"));
        makeNuggetToIngot(UGItems.UTHERIUM_INGOT, UGItems.UTHERIUM_CHUNK).build(consumer, name("utherium_chunk_to_ingot"));
        makeNuggetToIngot(UGItems.REGALIUM_INGOT, UGItems.REGALIUM_NUGGET).build(consumer, name("regalium_chunk_to_ingot"));
        makeNuggetToIngot(UGItems.FORGOTTEN_INGOT, UGItems.FORGOTTEN_NUGGET).build(consumer, name("forgotten_chunk_to_ingot"));

        makeSword(UGItems.CLOGGRUM_SWORD, UGItems.CLOGGRUM_INGOT).build(consumer);
        makeSword(UGItems.FROSTSTEEL_SWORD, UGItems.FROSTSTEEL_INGOT).build(consumer);
        makeSword(UGItems.UTHERIUM_SWORD, UGItems.UTHERIUM_INGOT).build(consumer);

        makePickaxe(UGItems.CLOGGRUM_PICKAXE, UGItems.CLOGGRUM_INGOT).build(consumer);
        makePickaxe(UGItems.FROSTSTEEL_PICKAXE, UGItems.FROSTSTEEL_INGOT).build(consumer);
        makePickaxe(UGItems.UTHERIUM_PICKAXE, UGItems.UTHERIUM_INGOT).build(consumer);

        makeAxe(UGItems.CLOGGRUM_AXE, UGItems.CLOGGRUM_INGOT).build(consumer);
        makeAxe(UGItems.FROSTSTEEL_AXE, UGItems.FROSTSTEEL_INGOT).build(consumer);
        makeAxe(UGItems.UTHERIUM_AXE, UGItems.UTHERIUM_INGOT).build(consumer);

        makeShovel(UGItems.CLOGGRUM_SHOVEL, UGItems.CLOGGRUM_INGOT).build(consumer);
        makeShovel(UGItems.FROSTSTEEL_SHOVEL, UGItems.FROSTSTEEL_INGOT).build(consumer);
        makeShovel(UGItems.UTHERIUM_SHOVEL, UGItems.UTHERIUM_INGOT).build(consumer);

        makeHoe(UGItems.CLOGGRUM_HOE, UGItems.CLOGGRUM_INGOT).build(consumer);
        makeHoe(UGItems.FROSTSTEEL_HOE, UGItems.FROSTSTEEL_INGOT).build(consumer);
        makeHoe(UGItems.UTHERIUM_HOE, UGItems.UTHERIUM_INGOT).build(consumer);

        makeChestplate(UGItems.MASTICATED_CHESTPLATE, UGItems.MASTICATOR_SCALES).build(consumer);

        makeHelmet(UGItems.CLOGGRUM_HELMET, UGItems.CLOGGRUM_INGOT).build(consumer);
        makeChestplate(UGItems.CLOGGRUM_CHESTPLATE, UGItems.CLOGGRUM_INGOT).build(consumer);
        makeLeggings(UGItems.CLOGGRUM_LEGGINGS, UGItems.CLOGGRUM_INGOT).build(consumer);
        makeBoots(UGItems.CLOGGRUM_BOOTS, UGItems.CLOGGRUM_INGOT).build(consumer);

        makeHelmet(UGItems.FROSTSTEEL_HELMET, UGItems.FROSTSTEEL_INGOT).build(consumer);
        makeChestplate(UGItems.FROSTSTEEL_CHESTPLATE, UGItems.FROSTSTEEL_INGOT).build(consumer);
        makeLeggings(UGItems.FROSTSTEEL_LEGGINGS, UGItems.FROSTSTEEL_INGOT).build(consumer);
        makeBoots(UGItems.FROSTSTEEL_BOOTS, UGItems.FROSTSTEEL_INGOT).build(consumer);

        makeHelmet(UGItems.UTHERIUM_HELMET, UGItems.UTHERIUM_INGOT).build(consumer);
        makeChestplate(UGItems.UTHERIUM_CHESTPLATE, UGItems.UTHERIUM_INGOT).build(consumer);
        makeLeggings(UGItems.UTHERIUM_LEGGINGS, UGItems.UTHERIUM_INGOT).build(consumer);
        makeBoots(UGItems.UTHERIUM_BOOTS, UGItems.UTHERIUM_INGOT).build(consumer);

        makeStairs(UGBlocks.DEPTHROCK_STAIRS, UGBlocks.DEPTHROCK).build(consumer);
        makeStairs(UGBlocks.DEPTHROCK_BRICK_STAIRS, UGBlocks.DEPTHROCK_BRICKS).build(consumer);
        makeStairs(UGBlocks.SMOGSTEM_STAIRS, UGBlocks.SMOGSTEM_PLANKS).build(consumer);
        makeStairs(UGBlocks.WIGGLEWOOD_STAIRS, UGBlocks.WIGGLEWOOD_PLANKS).build(consumer);
        makeStairs(UGBlocks.GRONGLE_STAIRS, UGBlocks.GRONGLE_PLANKS).build(consumer);
        makeStairs(UGBlocks.SHIVERSTONE_STAIRS, UGBlocks.SHIVERSTONE).build(consumer);
        makeStairs(UGBlocks.SHIVERSTONE_BRICK_STAIRS, UGBlocks.SHIVERSTONE_BRICKS).build(consumer);

        makeSlab(UGBlocks.DEPTHROCK_SLAB, UGBlocks.DEPTHROCK).build(consumer);
        makeSlab(UGBlocks.DEPTHROCK_BRICK_SLAB, UGBlocks.DEPTHROCK_BRICKS).build(consumer);
        makeSlab(UGBlocks.SMOGSTEM_SLAB, UGBlocks.SMOGSTEM_PLANKS).build(consumer);
        makeSlab(UGBlocks.WIGGLEWOOD_SLAB, UGBlocks.WIGGLEWOOD_PLANKS).build(consumer);
        makeSlab(UGBlocks.GRONGLE_SLAB, UGBlocks.GRONGLE_PLANKS).build(consumer);
        makeSlab(UGBlocks.SHIVERSTONE_SLAB, UGBlocks.SHIVERSTONE).build(consumer);
        makeSlab(UGBlocks.SHIVERSTONE_BRICK_SLAB, UGBlocks.SHIVERSTONE_BRICKS).build(consumer);

        makeWall(UGBlocks.DEPTHROCK_WALL, UGBlocks.DEPTHROCK).build(consumer);
        makeWall(UGBlocks.DEPTHROCK_BRICK_WALL, UGBlocks.DEPTHROCK_BRICKS).build(consumer);
        makeWall(UGBlocks.SHIVERSTONE_WALL, UGBlocks.SHIVERSTONE).build(consumer);
        makeWall(UGBlocks.SHIVERSTONE_BRICK_WALL, UGBlocks.SHIVERSTONE_BRICKS).build(consumer);

        makeFence(UGBlocks.SMOGSTEM_FENCE, UGBlocks.SMOGSTEM_PLANKS).build(consumer);
        makeFence(UGBlocks.WIGGLEWOOD_FENCE, UGBlocks.WIGGLEWOOD_PLANKS).build(consumer);
        makeFence(UGBlocks.GRONGLE_FENCE, UGBlocks.GRONGLE_PLANKS).build(consumer);

        makeFenceGate(UGBlocks.SMOGSTEM_FENCE_GATE, UGBlocks.SMOGSTEM_PLANKS).build(consumer);
        makeFenceGate(UGBlocks.WIGGLEWOOD_FENCE_GATE, UGBlocks.WIGGLEWOOD_PLANKS).build(consumer);
        makeFenceGate(UGBlocks.GRONGLE_FENCE_GATE, UGBlocks.GRONGLE_PLANKS).build(consumer);

        makeDoor(UGBlocks.SMOGSTEM_DOOR, UGBlocks.SMOGSTEM_PLANKS).build(consumer);
        makeDoor(UGBlocks.WIGGLEWOOD_DOOR, UGBlocks.WIGGLEWOOD_PLANKS).build(consumer);
        makeDoor(UGBlocks.GRONGLE_DOOR, UGBlocks.GRONGLE_PLANKS).build(consumer);

        makeTrapdoor(UGBlocks.SMOGSTEM_TRAPDOOR, UGBlocks.SMOGSTEM_PLANKS).build(consumer);
        makeTrapdoor(UGBlocks.WIGGLEWOOD_TRAPDOOR, UGBlocks.WIGGLEWOOD_PLANKS).build(consumer);
        makeTrapdoor(UGBlocks.GRONGLE_TRAPDOOR, UGBlocks.GRONGLE_PLANKS).build(consumer);

        makeButton(UGBlocks.SMOGSTEM_BUTTON, UGBlocks.SMOGSTEM_PLANKS).build(consumer);
        makeButton(UGBlocks.WIGGLEWOOD_BUTTON, UGBlocks.WIGGLEWOOD_PLANKS).build(consumer);
        makeButton(UGBlocks.GRONGLE_BUTTON, UGBlocks.GRONGLE_PLANKS).build(consumer);
        makeButton(UGBlocks.DEPTHROCK_BUTTON, UGBlocks.DEPTHROCK).build(consumer);
        makeButton(UGBlocks.SHIVERSTONE_BUTTON, UGBlocks.SHIVERSTONE).build(consumer);

        makePressurePlate(UGBlocks.SMOGSTEM_PRESSURE_PLATE, UGBlocks.SMOGSTEM_PLANKS).build(consumer);
        makePressurePlate(UGBlocks.WIGGLEWOOD_PRESSURE_PLATE, UGBlocks.WIGGLEWOOD_PLANKS).build(consumer);
        makePressurePlate(UGBlocks.GRONGLE_PRESSURE_PLATE, UGBlocks.GRONGLE_PLANKS).build(consumer);
        makePressurePlate(UGBlocks.DEPTHROCK_PRESSURE_PLATE, UGBlocks.DEPTHROCK).build(consumer);
        makePressurePlate(UGBlocks.SHIVERSTONE_PRESSURE_PLATE, UGBlocks.SHIVERSTONE).build(consumer);

        makeStew(UGItems.BLOODY_STEW, UGBlocks.BLOOD_MUSHROOM).build(consumer);
        makeStew(UGItems.INKY_STEW, UGBlocks.INK_MUSHROOM).build(consumer);
        makeStew(UGItems.INDIGO_STEW, UGBlocks.INDIGO_MUSHROOM).build(consumer);
        makeStew(UGItems.VEILED_STEW, UGBlocks.VEIL_MUSHROOM).build(consumer);

        smeltingRecipe(UGBlocks.CRACKED_DEPTHROCK_BRICKS.get(), UGBlocks.DEPTHROCK_BRICKS.get(), .1F).build(consumer, name("smelt_depthrock_bricks"));
        smeltingRecipe(UGBlocks.CRACKED_SHIVERSTONE_BRICKS.get(), UGBlocks.SHIVERSTONE_BRICKS.get(), .1F).build(consumer, name("smelt_shiverstone_bricks"));

        smeltingRecipe(Items.DIAMOND, UGItems.CATALYST.get(), 0.0F).build(consumer, name("smelt_catalyst"));

        smeltingRecipe(Items.COAL, UGBlocks.COAL_ORE.get(), .7F).build(consumer, name("smelt_undergarden_coal"));
        blastingRecipe(Items.COAL, UGBlocks.COAL_ORE.get(), .7F).build(consumer, name("blast_undergarden_coal"));
        smeltingRecipe(Items.IRON_INGOT, UGBlocks.IRON_ORE.get(), .7F).build(consumer, name("smelt_undergarden_iron"));
        blastingRecipe(Items.IRON_INGOT, UGBlocks.IRON_ORE.get(), .7F).build(consumer, name("blast_undergarden_iron"));
        smeltingRecipe(Items.GOLD_INGOT, UGBlocks.GOLD_ORE.get(), .7F).build(consumer, name("smelt_undergarden_gold"));
        blastingRecipe(Items.GOLD_INGOT, UGBlocks.GOLD_ORE.get(), .7F).build(consumer, name("blast_undergarden_gold"));
        smeltingRecipe(Items.DIAMOND, UGBlocks.DIAMOND_ORE.get(), .7F).build(consumer, name("smelt_undergarden_diamond"));
        blastingRecipe(Items.DIAMOND, UGBlocks.DIAMOND_ORE.get(), .7F).build(consumer, name("blast_undergarden_diamond"));

        smeltingRecipe(UGItems.CLOGGRUM_INGOT.get(), UGBlocks.CLOGGRUM_ORE.get(), .7F).build(consumer, name("smelt_cloggrum_ore"));
        blastingRecipe(UGItems.CLOGGRUM_INGOT.get(), UGBlocks.CLOGGRUM_ORE.get(), .7F).build(consumer, name("blast_cloggrum_ore"));
        smeltingRecipe(UGItems.FROSTSTEEL_INGOT.get(), UGBlocks.FROSTSTEEL_ORE.get(), .7F).build(consumer, name("smelt_froststeel_ore"));
        blastingRecipe(UGItems.FROSTSTEEL_INGOT.get(), UGBlocks.FROSTSTEEL_ORE.get(), .7F).build(consumer, name("blast_froststeel_ore"));
        smeltingRecipe(UGItems.UTHERIUM_CHUNK.get(), UGBlocks.UTHERIUM_ORE.get(), .9F).build(consumer, name("smelt_utherium_ore"));
        blastingRecipe(UGItems.UTHERIUM_CHUNK.get(), UGBlocks.UTHERIUM_ORE.get(), .9F).build(consumer, name("blast_utherium_ore"));
        smeltingRecipe(UGItems.REGALIUM_INGOT.get(), UGBlocks.REGALIUM_ORE.get(), 1F).build(consumer, name("smelt_regalium_ore"));
        blastingRecipe(UGItems.REGALIUM_INGOT.get(), UGBlocks.REGALIUM_ORE.get(), 1F).build(consumer, name("blast_regalium_ore"));

        smeltingRecipeTag(UGItems.CLOGGRUM_NUGGET.get(), UGTags.Items.CLOGGRUM_ITEMS, .1F).build(consumer, name("smelt_cloggrum_item"));
        blastingRecipeTag(UGItems.CLOGGRUM_NUGGET.get(), UGTags.Items.CLOGGRUM_ITEMS, .1F).build(consumer, name("blast_cloggrum_item"));

        smeltingRecipeTag(UGItems.FROSTSTEEL_NUGGET.get(), UGTags.Items.FROSTSTEEL_ITEMS, .1F).build(consumer, name("smelt_froststeel_item"));
        blastingRecipeTag(UGItems.FROSTSTEEL_NUGGET.get(), UGTags.Items.FROSTSTEEL_ITEMS, .1F).build(consumer, name("blast_froststeel_item"));

        smeltingRecipeTag(UGItems.UTHERIC_SHARD.get(), UGTags.Items.UTHERIUM_ITEMS, .1F).build(consumer, name("smelt_utherium_item"));
        blastingRecipeTag(UGItems.UTHERIC_SHARD.get(), UGTags.Items.UTHERIUM_ITEMS, .1F).build(consumer, name("blast_utherium_item"));

        smeltingRecipe(UGItems.DWELLER_STEAK.get(), UGItems.RAW_DWELLER_MEAT.get(), .35F).build(consumer, name("smelt_dweller_meat"));
        smokingRecipe(UGItems.DWELLER_STEAK.get(), UGItems.RAW_DWELLER_MEAT.get(), .35F).build(consumer, name("smoke_dweller_meat"));

        smeltingRecipe(UGItems.COOKED_GWIBLING.get(), UGItems.RAW_GWIBLING.get(), .35F).build(consumer, name("smelt_gwibling"));
        smokingRecipe(UGItems.COOKED_GWIBLING.get(), UGItems.RAW_GWIBLING.get(), .35F).build(consumer, name("smoke_gwibling"));

        smeltingRecipe(UGItems.GLOOMPER_LEG.get(), UGItems.RAW_GLOOMPER_LEG.get(), .35F).build(consumer, name("smelt_gloomper_leg"));
        blastingRecipe(UGItems.GLOOMPER_LEG.get(), UGItems.RAW_GLOOMPER_LEG.get(), .35F).build(consumer, name("blast_gloomper_leg"));

        smeltingRecipe(Items.DRIED_KELP, UGItems.GLOWING_KELP.get(), 0.1F).build(consumer, name("smelt_glowing_kelp"));
        smokingRecipe(Items.DRIED_KELP, UGItems.GLOWING_KELP.get(), 0.1F).build(consumer, name("smoke_glowing_kelp"));
    }

    private ResourceLocation name(String name) {
        return new ResourceLocation(UGMod.MODID, name);
    }
}
