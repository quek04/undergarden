package quek.undergarden.data;

import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import quek.undergarden.Undergarden;
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
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        makePlanks(UGBlocks.SMOGSTEM_PLANKS, UGTags.Items.SMOGSTEM_LOGS).save(consumer);
        makePlanks(UGBlocks.WIGGLEWOOD_PLANKS, UGTags.Items.WIGGLEWOOD_LOGS).save(consumer);
        makePlanks(UGBlocks.GRONGLE_PLANKS, UGTags.Items.GRONGLE_LOGS).save(consumer);

        makeBricks(UGBlocks.DEPTHROCK_BRICKS, UGBlocks.DEPTHROCK).save(consumer);
        makeBricks(UGBlocks.SHIVERSTONE_BRICKS, UGBlocks.SHIVERSTONE).save(consumer);
        makeBricks(UGBlocks.TREMBLECRUST_BRICKS, UGBlocks.TREMBLECRUST).save(consumer);

        makeChiseledBricks(UGBlocks.CHISELED_DEPTHROCK_BRICKS, UGBlocks.DEPTHROCK_BRICK_SLAB).save(consumer);
        makeChiseledBricks(UGBlocks.CHISELED_SHIVERSTONE_BRICKS, UGBlocks.SHIVERSTONE_BRICK_SLAB).save(consumer);
        makeChiseledBricks(UGBlocks.CHISELED_TREMBLECRUST_BRICKS, UGBlocks.TREMBLECRUST_BRICK_SLAB).save(consumer);

        makeWood(UGBlocks.SMOGSTEM_WOOD, UGBlocks.SMOGSTEM_LOG).save(consumer);
        makeWood(UGBlocks.STRIPPED_SMOGSTEM_WOOD, UGBlocks.STRIPPED_SMOGSTEM_LOG).save(consumer);
        makeWood(UGBlocks.WIGGLEWOOD_WOOD, UGBlocks.WIGGLEWOOD_LOG).save(consumer);
        makeWood(UGBlocks.STRIPPED_WIGGLEWOOD_WOOD, UGBlocks.STRIPPED_WIGGLEWOOD_LOG).save(consumer);
        makeWood(UGBlocks.GRONGLE_WOOD, UGBlocks.GRONGLE_LOG).save(consumer);
        makeWood(UGBlocks.STRIPPED_GRONGLE_WOOD, UGBlocks.STRIPPED_GRONGLE_LOG).save(consumer);

        makeBoat(UGItems.SMOGSTEM_BOAT, UGBlocks.SMOGSTEM_PLANKS).save(consumer);
        makeBoat(UGItems.WIGGLEWOOD_BOAT, UGBlocks.WIGGLEWOOD_PLANKS).save(consumer);
        makeBoat(UGItems.GRONGLE_BOAT, UGBlocks.GRONGLE_PLANKS).save(consumer);

        makeSign(UGItems.SMOGSTEM_SIGN, UGBlocks.SMOGSTEM_PLANKS).save(consumer);
        makeSign(UGItems.WIGGLEWOOD_SIGN, UGBlocks.WIGGLEWOOD_PLANKS).save(consumer);
        makeSign(UGItems.GRONGLE_SIGN, UGBlocks.GRONGLE_PLANKS).save(consumer);

        ShapedRecipeBuilder.shaped(UGItems.TWISTYTWIG.get(), 4)
                .pattern("P ")
                .pattern(" P")
                .define('P', UGBlocks.WIGGLEWOOD_PLANKS.get())
                .unlockedBy("has_wigglewood_planks", has(UGBlocks.WIGGLEWOOD_PLANKS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Blocks.SCAFFOLDING, 6)
                .pattern("STS")
                .pattern("S S")
                .pattern("S S")
                .define('S', Tags.Items.RODS_WOODEN)
                .define('T', UGItems.TWISTYTWIG.get())
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_twistytwig", has(UGItems.TWISTYTWIG.get()))
                .save(consumer, name("undergarden_scaffolding"));

        ShapedRecipeBuilder.shaped(UGBlocks.GLOOM_O_LANTERN.get())
                .pattern("G")
                .pattern("T")
                .define('G', UGBlocks.CARVED_GLOOMGOURD.get())
                .define('T', Items.TORCH)
                .unlockedBy("has_carved_gourd", has(UGBlocks.CARVED_GLOOMGOURD.get()))
                .unlockedBy("has_torch", has(Items.TORCH))
                .save(consumer);

        ShapedRecipeBuilder.shaped(UGBlocks.SHARD_O_LANTERN.get())
                .pattern("G")
                .pattern("T")
                .define('G', UGBlocks.CARVED_GLOOMGOURD.get())
                .define('T', UGItems.SHARD_TORCH.get())
                .unlockedBy("has_carved_gourd", has(UGBlocks.CARVED_GLOOMGOURD.get()))
                .unlockedBy("has_shard_torch", has(UGItems.SHARD_TORCH.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(UGBlocks.CLOGGRUM_BARS.get(), 16)
                .pattern("CCC")
                .pattern("CCC")
                .define('C', UGItems.CLOGGRUM_INGOT.get())
                .unlockedBy("has_cloggrum_ingot", has(UGItems.CLOGGRUM_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(UGBlocks.COARSE_DEEPSOIL.get(), 4)
                .pattern("DP")
                .pattern("PD")
                .define('D', UGBlocks.DEEPSOIL.get())
                .define('P', UGItems.DEPTHROCK_PEBBLE.get())
                .unlockedBy("has_deepsoil", has(UGBlocks.DEEPSOIL.get()))
                .unlockedBy("has_pebble", has(UGItems.DEPTHROCK_PEBBLE.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(UGBlocks.DEEPSOIL.get())
                .requires(UGBlocks.COARSE_DEEPSOIL.get())
                .unlockedBy("has_coarse_deepsoil", has(UGBlocks.COARSE_DEEPSOIL.get()))
                .save(consumer, name("coarse_deepsoil_to_normal"));

        ShapedRecipeBuilder.shaped(UGItems.SLINGSHOT.get())
                .pattern("STS")
                .pattern("SSS")
                .pattern(" S ")
                .define('S', Tags.Items.RODS_WOODEN)
                .define('T', UGItems.TWISTYTWIG.get())
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_twistytwig", has(UGItems.TWISTYTWIG.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(UGItems.BLISTERBOMB.get())
                .pattern(" T ")
                .pattern("BBB")
                .pattern("BBB")
                .define('T', UGItems.TWISTYTWIG.get())
                .define('B', UGItems.ROTTEN_BLISTERBERRY.get())
                .unlockedBy("has_twistytwig", has(UGItems.TWISTYTWIG.get()))
                .unlockedBy("has_blisterberry", has(UGItems.ROTTEN_BLISTERBERRY.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(UGItems.CLOGGRUM_SHIELD.get())
                .pattern("CSC")
                .pattern("CCC")
                .pattern(" C ")
                .define('S', ItemTags.PLANKS)
                .define('C', UGItems.CLOGGRUM_INGOT.get())
                .unlockedBy("has_scales", has(UGItems.CLOGGRUM_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(UGItems.SHARD_TORCH.get(), 1)
                .pattern("C")
                .pattern("S")
                .define('C', UGItems.UTHERIC_SHARD.get())
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_shard", has(UGItems.UTHERIC_SHARD.get()))
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Blocks.TORCH, 2)
                .pattern("D")
                .pattern("S")
                .define('D', UGItems.DITCHBULB.get())
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_ditchbulb", has(UGItems.DITCHBULB.get()))
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .save(consumer, name("smogstem_torch_ditchbulb"));

        ShapedRecipeBuilder.shaped(UGItems.CATALYST.get())
                .pattern("GIG")
                .pattern("IDI")
                .pattern("GIG")
                .define('G', Items.GOLD_INGOT)
                .define('I', Items.IRON_INGOT)
                .define('D', Items.DIAMOND)
                .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(UGItems.GLOOMGOURD_PIE.get())
                .requires(UGTags.Items.MUSHROOMS)
                .requires(UGBlocks.GLOOMGOURD.get())
                .requires(UGItems.GLOWING_KELP.get())
                .unlockedBy("has_mushroom", has(UGTags.Items.MUSHROOMS))
                .unlockedBy("has_gloomgourd", has(UGBlocks.GLOOMGOURD.get()))
                .unlockedBy("has_kelp", has(UGItems.GLOWING_KELP.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(UGItems.GLOOMGOURD_SEEDS.get(), 4)
                .requires(UGBlocks.GLOOMGOURD.get())
                .unlockedBy("has_gloomgourd", has(UGBlocks.GLOOMGOURD.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(Items.BONE_MEAL, 4)
                .requires(UGItems.BRUTE_TUSK.get())
                .unlockedBy("has_tusk", has(UGItems.BRUTE_TUSK.get()))
                .save(consumer, name("tusk_to_bonemeal"));

        ShapelessRecipeBuilder.shapeless(Items.RED_DYE, 9)
                .requires(UGBlocks.BLOOD_MUSHROOM_GLOBULE.get())
                .unlockedBy("has_globule", has(UGBlocks.BLOOD_MUSHROOM_GLOBULE.get()))
                .save(consumer, name("globule_to_dye"));

        ShapelessRecipeBuilder.shapeless(Items.RED_DYE)
                .requires(UGBlocks.BLOOD_MUSHROOM.get())
                .unlockedBy("has_blood_mushroom", has(UGBlocks.BLOOD_MUSHROOM.get()))
                .save(consumer, name("blood_mushroom_to_dye"));

        ShapelessRecipeBuilder.shapeless(Items.BLACK_DYE)
                .requires(UGBlocks.INK_MUSHROOM.get())
                .unlockedBy("has_ink_mushroom", has(UGBlocks.INK_MUSHROOM.get()))
                .save(consumer, name("ink_mushroom_to_dye"));

        ShapelessRecipeBuilder.shapeless(Items.BLUE_DYE)
                .requires(UGBlocks.INDIGO_MUSHROOM.get())
                .unlockedBy("has_indigo_mushroom", has(UGBlocks.INDIGO_MUSHROOM.get()))
                .save(consumer, name("indigo_mushroom_to_dye"));

        ShapelessRecipeBuilder.shapeless(Items.WHITE_DYE)
                .requires(UGBlocks.VEIL_MUSHROOM.get())
                .unlockedBy("has_veil_mushroom", has(UGBlocks.VEIL_MUSHROOM.get()))
                .save(consumer, name("veil_mushroom_to_dye"));

        ShapedRecipeBuilder.shaped(UGItems.UTHERIUM_CHUNK.get())
                .pattern("SS")
                .pattern("SS")
                .define('S', UGItems.UTHERIC_SHARD.get())
                .unlockedBy("has_shard", has(UGItems.UTHERIC_SHARD.get()))
                .save(consumer, name("shard_to_chunk"));

        ShapedRecipeBuilder.shaped(Items.LEAD, 2)
                .pattern("TT ")
                .pattern("TG ")
                .pattern("  T")
                .define('T', UGItems.TWISTYTWIG.get())
                .define('G', UGItems.GOO_BALL.get())
                .unlockedBy("has_twistytwig", has(UGItems.TWISTYTWIG.get()))
                .unlockedBy("has_goo_ball", has(UGItems.GOO_BALL.get()))
                .save(consumer, name("undergarden_lead"));

        ShapedRecipeBuilder.shaped(UGBlocks.SEDIMENT_GLASS_PANE.get(), 16)
                .pattern("GGG")
                .pattern("GGG")
                .define('G', UGBlocks.SEDIMENT_GLASS.get())
                .unlockedBy("has_sediment_glass", has(UGBlocks.SEDIMENT_GLASS.get()))
                .save(consumer);

        makeIngotToBlock(UGBlocks.CLOGGRUM_BLOCK, UGItems.CLOGGRUM_INGOT).save(consumer);
        makeIngotToBlock(UGBlocks.FROSTSTEEL_BLOCK, UGItems.FROSTSTEEL_INGOT).save(consumer);
        makeIngotToBlock(UGBlocks.UTHERIUM_BLOCK, UGItems.UTHERIUM_INGOT).save(consumer);
        makeIngotToBlock(UGBlocks.REGALIUM_BLOCK, UGItems.REGALIUM_INGOT).save(consumer);
        makeIngotToBlock(UGBlocks.FORGOTTEN_BLOCK, UGItems.FORGOTTEN_INGOT).save(consumer);
        makeIngotToBlock(UGBlocks.DEPTHROCK, UGItems.DEPTHROCK_PEBBLE).save(consumer, name("pebbles_to_depthrock"));
        makeIngotToBlock(UGBlocks.GOO_BLOCK, UGItems.GOO_BALL).save(consumer);

        makeBlockToIngot(UGItems.CLOGGRUM_INGOT, UGBlocks.CLOGGRUM_BLOCK).save(consumer, name("cloggrum_block_to_ingot"));
        makeBlockToIngot(UGItems.FROSTSTEEL_INGOT, UGBlocks.FROSTSTEEL_BLOCK).save(consumer, name("froststeel_block_to_ingot"));
        makeBlockToIngot(UGItems.UTHERIUM_INGOT, UGBlocks.UTHERIUM_BLOCK).save(consumer, name("utherium_block_to_ingot"));
        makeBlockToIngot(UGItems.REGALIUM_INGOT, UGBlocks.REGALIUM_BLOCK).save(consumer, name("regalium_block_to_ingot"));
        makeBlockToIngot(UGItems.FORGOTTEN_INGOT, UGBlocks.FORGOTTEN_BLOCK).save(consumer, name("forgotten_block_to_ingot"));
        makeBlockToIngot(UGItems.GOO_BALL, UGBlocks.GOO_BLOCK).save(consumer, name("goo_block_to_ball"));

        makeIngotToNugget(UGItems.CLOGGRUM_NUGGET, UGItems.CLOGGRUM_INGOT).save(consumer, name("cloggrum_ingot_to_nugget"));
        makeIngotToNugget(UGItems.FROSTSTEEL_NUGGET, UGItems.FROSTSTEEL_INGOT).save(consumer, name("froststeel_ingot_to_nugget"));
        makeIngotToNugget(UGItems.UTHERIUM_CHUNK, UGItems.UTHERIUM_INGOT).save(consumer, name("utherium_ingot_to_nugget"));
        makeIngotToNugget(UGItems.REGALIUM_NUGGET, UGItems.REGALIUM_INGOT).save(consumer, name("regalium_ingot_to_nugget"));
        makeIngotToNugget(UGItems.FORGOTTEN_NUGGET, UGItems.FORGOTTEN_INGOT).save(consumer, name("forgotten_ingot_to_nugget"));

        makeNuggetToIngot(UGItems.CLOGGRUM_INGOT, UGItems.CLOGGRUM_NUGGET).save(consumer, name("cloggrum_nugget_to_ingot"));
        makeNuggetToIngot(UGItems.FROSTSTEEL_INGOT, UGItems.FROSTSTEEL_NUGGET).save(consumer, name("froststeel_nugget_to_ingot"));
        makeNuggetToIngot(UGItems.UTHERIUM_INGOT, UGItems.UTHERIUM_CHUNK).save(consumer, name("utherium_chunk_to_ingot"));
        makeNuggetToIngot(UGItems.REGALIUM_INGOT, UGItems.REGALIUM_NUGGET).save(consumer, name("regalium_chunk_to_ingot"));
        makeNuggetToIngot(UGItems.FORGOTTEN_INGOT, UGItems.FORGOTTEN_NUGGET).save(consumer, name("forgotten_chunk_to_ingot"));

        makeSword(UGItems.CLOGGRUM_SWORD, UGItems.CLOGGRUM_INGOT).save(consumer);
        makeSword(UGItems.FROSTSTEEL_SWORD, UGItems.FROSTSTEEL_INGOT).save(consumer);
        makeSword(UGItems.UTHERIUM_SWORD, UGItems.UTHERIUM_INGOT).save(consumer);

        makePickaxe(UGItems.CLOGGRUM_PICKAXE, UGItems.CLOGGRUM_INGOT).save(consumer);
        makePickaxe(UGItems.FROSTSTEEL_PICKAXE, UGItems.FROSTSTEEL_INGOT).save(consumer);
        makePickaxe(UGItems.UTHERIUM_PICKAXE, UGItems.UTHERIUM_INGOT).save(consumer);

        makeAxe(UGItems.CLOGGRUM_AXE, UGItems.CLOGGRUM_INGOT).save(consumer);
        makeAxe(UGItems.FROSTSTEEL_AXE, UGItems.FROSTSTEEL_INGOT).save(consumer);
        makeAxe(UGItems.UTHERIUM_AXE, UGItems.UTHERIUM_INGOT).save(consumer);

        makeShovel(UGItems.CLOGGRUM_SHOVEL, UGItems.CLOGGRUM_INGOT).save(consumer);
        makeShovel(UGItems.FROSTSTEEL_SHOVEL, UGItems.FROSTSTEEL_INGOT).save(consumer);
        makeShovel(UGItems.UTHERIUM_SHOVEL, UGItems.UTHERIUM_INGOT).save(consumer);

        makeHoe(UGItems.CLOGGRUM_HOE, UGItems.CLOGGRUM_INGOT).save(consumer);
        makeHoe(UGItems.FROSTSTEEL_HOE, UGItems.FROSTSTEEL_INGOT).save(consumer);
        makeHoe(UGItems.UTHERIUM_HOE, UGItems.UTHERIUM_INGOT).save(consumer);

        makeChestplate(UGItems.MASTICATED_CHESTPLATE, UGItems.MASTICATOR_SCALES).save(consumer);

        makeHelmet(UGItems.CLOGGRUM_HELMET, UGItems.CLOGGRUM_INGOT).save(consumer);
        makeChestplate(UGItems.CLOGGRUM_CHESTPLATE, UGItems.CLOGGRUM_INGOT).save(consumer);
        makeLeggings(UGItems.CLOGGRUM_LEGGINGS, UGItems.CLOGGRUM_INGOT).save(consumer);
        makeBoots(UGItems.CLOGGRUM_BOOTS, UGItems.CLOGGRUM_INGOT).save(consumer);

        makeHelmet(UGItems.FROSTSTEEL_HELMET, UGItems.FROSTSTEEL_INGOT).save(consumer);
        makeChestplate(UGItems.FROSTSTEEL_CHESTPLATE, UGItems.FROSTSTEEL_INGOT).save(consumer);
        makeLeggings(UGItems.FROSTSTEEL_LEGGINGS, UGItems.FROSTSTEEL_INGOT).save(consumer);
        makeBoots(UGItems.FROSTSTEEL_BOOTS, UGItems.FROSTSTEEL_INGOT).save(consumer);

        makeHelmet(UGItems.UTHERIUM_HELMET, UGItems.UTHERIUM_INGOT).save(consumer);
        makeChestplate(UGItems.UTHERIUM_CHESTPLATE, UGItems.UTHERIUM_INGOT).save(consumer);
        makeLeggings(UGItems.UTHERIUM_LEGGINGS, UGItems.UTHERIUM_INGOT).save(consumer);
        makeBoots(UGItems.UTHERIUM_BOOTS, UGItems.UTHERIUM_INGOT).save(consumer);

        makeStairs(UGBlocks.DEPTHROCK_STAIRS, UGBlocks.DEPTHROCK).save(consumer);
        makeStairs(UGBlocks.DEPTHROCK_BRICK_STAIRS, UGBlocks.DEPTHROCK_BRICKS).save(consumer);
        makeStairs(UGBlocks.SMOGSTEM_STAIRS, UGBlocks.SMOGSTEM_PLANKS).save(consumer);
        makeStairs(UGBlocks.WIGGLEWOOD_STAIRS, UGBlocks.WIGGLEWOOD_PLANKS).save(consumer);
        makeStairs(UGBlocks.GRONGLE_STAIRS, UGBlocks.GRONGLE_PLANKS).save(consumer);
        makeStairs(UGBlocks.SHIVERSTONE_STAIRS, UGBlocks.SHIVERSTONE).save(consumer);
        makeStairs(UGBlocks.SHIVERSTONE_BRICK_STAIRS, UGBlocks.SHIVERSTONE_BRICKS).save(consumer);
        makeStairs(UGBlocks.TREMBLECRUST_STAIRS, UGBlocks.TREMBLECRUST).save(consumer);
        makeStairs(UGBlocks.TREMBLECRUST_BRICK_STAIRS, UGBlocks.TREMBLECRUST_BRICKS).save(consumer);

        makeSlab(UGBlocks.DEPTHROCK_SLAB, UGBlocks.DEPTHROCK).save(consumer);
        makeSlab(UGBlocks.DEPTHROCK_BRICK_SLAB, UGBlocks.DEPTHROCK_BRICKS).save(consumer);
        makeSlab(UGBlocks.SMOGSTEM_SLAB, UGBlocks.SMOGSTEM_PLANKS).save(consumer);
        makeSlab(UGBlocks.WIGGLEWOOD_SLAB, UGBlocks.WIGGLEWOOD_PLANKS).save(consumer);
        makeSlab(UGBlocks.GRONGLE_SLAB, UGBlocks.GRONGLE_PLANKS).save(consumer);
        makeSlab(UGBlocks.SHIVERSTONE_SLAB, UGBlocks.SHIVERSTONE).save(consumer);
        makeSlab(UGBlocks.SHIVERSTONE_BRICK_SLAB, UGBlocks.SHIVERSTONE_BRICKS).save(consumer);
        makeSlab(UGBlocks.TREMBLECRUST_SLAB, UGBlocks.TREMBLECRUST).save(consumer);
        makeSlab(UGBlocks.TREMBLECRUST_BRICK_SLAB, UGBlocks.TREMBLECRUST_BRICKS).save(consumer);

        makeWall(UGBlocks.DEPTHROCK_WALL, UGBlocks.DEPTHROCK).save(consumer);
        makeWall(UGBlocks.DEPTHROCK_BRICK_WALL, UGBlocks.DEPTHROCK_BRICKS).save(consumer);
        makeWall(UGBlocks.SHIVERSTONE_WALL, UGBlocks.SHIVERSTONE).save(consumer);
        makeWall(UGBlocks.SHIVERSTONE_BRICK_WALL, UGBlocks.SHIVERSTONE_BRICKS).save(consumer);
        makeWall(UGBlocks.TREMBLECRUST_WALL, UGBlocks.TREMBLECRUST).save(consumer);
        makeWall(UGBlocks.TREMBLECRUST_BRICK_WALL, UGBlocks.TREMBLECRUST_BRICKS).save(consumer);

        makeFence(UGBlocks.SMOGSTEM_FENCE, UGBlocks.SMOGSTEM_PLANKS).save(consumer);
        makeFence(UGBlocks.WIGGLEWOOD_FENCE, UGBlocks.WIGGLEWOOD_PLANKS).save(consumer);
        makeFence(UGBlocks.GRONGLE_FENCE, UGBlocks.GRONGLE_PLANKS).save(consumer);

        makeFenceGate(UGBlocks.SMOGSTEM_FENCE_GATE, UGBlocks.SMOGSTEM_PLANKS).save(consumer);
        makeFenceGate(UGBlocks.WIGGLEWOOD_FENCE_GATE, UGBlocks.WIGGLEWOOD_PLANKS).save(consumer);
        makeFenceGate(UGBlocks.GRONGLE_FENCE_GATE, UGBlocks.GRONGLE_PLANKS).save(consumer);

        makeDoor(UGBlocks.SMOGSTEM_DOOR, UGBlocks.SMOGSTEM_PLANKS).save(consumer);
        makeDoor(UGBlocks.WIGGLEWOOD_DOOR, UGBlocks.WIGGLEWOOD_PLANKS).save(consumer);
        makeDoor(UGBlocks.GRONGLE_DOOR, UGBlocks.GRONGLE_PLANKS).save(consumer);

        makeTrapdoor(UGBlocks.SMOGSTEM_TRAPDOOR, UGBlocks.SMOGSTEM_PLANKS).save(consumer);
        makeTrapdoor(UGBlocks.WIGGLEWOOD_TRAPDOOR, UGBlocks.WIGGLEWOOD_PLANKS).save(consumer);
        makeTrapdoor(UGBlocks.GRONGLE_TRAPDOOR, UGBlocks.GRONGLE_PLANKS).save(consumer);

        makeButton(UGBlocks.SMOGSTEM_BUTTON, UGBlocks.SMOGSTEM_PLANKS).save(consumer);
        makeButton(UGBlocks.WIGGLEWOOD_BUTTON, UGBlocks.WIGGLEWOOD_PLANKS).save(consumer);
        makeButton(UGBlocks.GRONGLE_BUTTON, UGBlocks.GRONGLE_PLANKS).save(consumer);
        makeButton(UGBlocks.DEPTHROCK_BUTTON, UGBlocks.DEPTHROCK).save(consumer);
        makeButton(UGBlocks.SHIVERSTONE_BUTTON, UGBlocks.SHIVERSTONE).save(consumer);
        makeButton(UGBlocks.TREMBLECRUST_BUTTON, UGBlocks.TREMBLECRUST).save(consumer);

        makePressurePlate(UGBlocks.SMOGSTEM_PRESSURE_PLATE, UGBlocks.SMOGSTEM_PLANKS).save(consumer);
        makePressurePlate(UGBlocks.WIGGLEWOOD_PRESSURE_PLATE, UGBlocks.WIGGLEWOOD_PLANKS).save(consumer);
        makePressurePlate(UGBlocks.GRONGLE_PRESSURE_PLATE, UGBlocks.GRONGLE_PLANKS).save(consumer);
        makePressurePlate(UGBlocks.DEPTHROCK_PRESSURE_PLATE, UGBlocks.DEPTHROCK).save(consumer);
        makePressurePlate(UGBlocks.SHIVERSTONE_PRESSURE_PLATE, UGBlocks.SHIVERSTONE).save(consumer);
        makePressurePlate(UGBlocks.TREMBLECRUST_PRESSURE_PLATE, UGBlocks.TREMBLECRUST).save(consumer);

        makeStew(UGItems.BLOODY_STEW, UGBlocks.BLOOD_MUSHROOM).save(consumer);
        makeStew(UGItems.INKY_STEW, UGBlocks.INK_MUSHROOM).save(consumer);
        makeStew(UGItems.INDIGO_STEW, UGBlocks.INDIGO_MUSHROOM).save(consumer);
        makeStew(UGItems.VEILED_STEW, UGBlocks.VEIL_MUSHROOM).save(consumer);

        smithingForgotten(UGItems.CLOGGRUM_SWORD, UGItems.FORGOTTEN_SWORD).save(consumer, name("forgotten_sword_smithing"));
        smithingForgotten(UGItems.CLOGGRUM_PICKAXE, UGItems.FORGOTTEN_PICKAXE).save(consumer, name("forgotten_pickaxe_smithing"));
        smithingForgotten(UGItems.CLOGGRUM_AXE, UGItems.FORGOTTEN_AXE).save(consumer, name("forgotten_axe_smithing"));
        smithingForgotten(UGItems.CLOGGRUM_SHOVEL, UGItems.FORGOTTEN_SHOVEL).save(consumer, name("forgotten_shovel_smithing"));
        smithingForgotten(UGItems.CLOGGRUM_HOE, UGItems.FORGOTTEN_HOE).save(consumer, name("forgotten_hoe_smithing"));

        smeltingRecipe(UGBlocks.CRACKED_DEPTHROCK_BRICKS.get(), UGBlocks.DEPTHROCK_BRICKS.get(), .1F).save(consumer, name("smelt_depthrock_bricks"));
        smeltingRecipe(UGBlocks.CRACKED_SHIVERSTONE_BRICKS.get(), UGBlocks.SHIVERSTONE_BRICKS.get(), .1F).save(consumer, name("smelt_shiverstone_bricks"));
        smeltingRecipe(UGBlocks.CRACKED_TREMBLECRUST_BRICKS.get(), UGBlocks.TREMBLECRUST_BRICKS.get(), .1F).save(consumer, name("smelt_tremblecrust_bricks"));

        smeltingRecipe(UGBlocks.SEDIMENT_GLASS.get(), UGBlocks.SEDIMENT.get(), 0.1F).save(consumer);

        smeltingRecipe(Items.DIAMOND, UGItems.CATALYST.get(), 0.0F).save(consumer, name("smelt_catalyst"));

        smeltingRecipe(Items.COAL, UGBlocks.COAL_ORE.get(), .7F).save(consumer, name("smelt_undergarden_coal"));
        blastingRecipe(Items.COAL, UGBlocks.COAL_ORE.get(), .7F).save(consumer, name("blast_undergarden_coal"));
        smeltingRecipe(Items.IRON_INGOT, UGBlocks.IRON_ORE.get(), .7F).save(consumer, name("smelt_undergarden_iron"));
        blastingRecipe(Items.IRON_INGOT, UGBlocks.IRON_ORE.get(), .7F).save(consumer, name("blast_undergarden_iron"));
        smeltingRecipe(Items.GOLD_INGOT, UGBlocks.GOLD_ORE.get(), .7F).save(consumer, name("smelt_undergarden_gold"));
        blastingRecipe(Items.GOLD_INGOT, UGBlocks.GOLD_ORE.get(), .7F).save(consumer, name("blast_undergarden_gold"));
        smeltingRecipe(Items.DIAMOND, UGBlocks.DIAMOND_ORE.get(), .7F).save(consumer, name("smelt_undergarden_diamond"));
        blastingRecipe(Items.DIAMOND, UGBlocks.DIAMOND_ORE.get(), .7F).save(consumer, name("blast_undergarden_diamond"));

        smeltingRecipe(UGItems.CLOGGRUM_INGOT.get(), UGBlocks.CLOGGRUM_ORE.get(), .7F).save(consumer, name("smelt_cloggrum_ore"));
        blastingRecipe(UGItems.CLOGGRUM_INGOT.get(), UGBlocks.CLOGGRUM_ORE.get(), .7F).save(consumer, name("blast_cloggrum_ore"));
        smeltingRecipe(UGItems.FROSTSTEEL_INGOT.get(), UGBlocks.FROSTSTEEL_ORE.get(), .7F).save(consumer, name("smelt_froststeel_ore"));
        blastingRecipe(UGItems.FROSTSTEEL_INGOT.get(), UGBlocks.FROSTSTEEL_ORE.get(), .7F).save(consumer, name("blast_froststeel_ore"));
        smeltingRecipe(UGItems.UTHERIUM_CHUNK.get(), UGBlocks.UTHERIUM_ORE.get(), .9F).save(consumer, name("smelt_utherium_ore"));
        blastingRecipe(UGItems.UTHERIUM_CHUNK.get(), UGBlocks.UTHERIUM_ORE.get(), .9F).save(consumer, name("blast_utherium_ore"));
        smeltingRecipe(UGItems.REGALIUM_INGOT.get(), UGBlocks.REGALIUM_ORE.get(), 1F).save(consumer, name("smelt_regalium_ore"));
        blastingRecipe(UGItems.REGALIUM_INGOT.get(), UGBlocks.REGALIUM_ORE.get(), 1F).save(consumer, name("blast_regalium_ore"));

        smeltingRecipeTag(UGItems.CLOGGRUM_NUGGET.get(), UGTags.Items.CLOGGRUM_ITEMS, .1F).save(consumer, name("smelt_cloggrum_item"));
        blastingRecipeTag(UGItems.CLOGGRUM_NUGGET.get(), UGTags.Items.CLOGGRUM_ITEMS, .1F).save(consumer, name("blast_cloggrum_item"));

        smeltingRecipeTag(UGItems.FROSTSTEEL_NUGGET.get(), UGTags.Items.FROSTSTEEL_ITEMS, .1F).save(consumer, name("smelt_froststeel_item"));
        blastingRecipeTag(UGItems.FROSTSTEEL_NUGGET.get(), UGTags.Items.FROSTSTEEL_ITEMS, .1F).save(consumer, name("blast_froststeel_item"));

        smeltingRecipeTag(UGItems.UTHERIUM_CHUNK.get(), UGTags.Items.UTHERIUM_ITEMS, .1F).save(consumer, name("smelt_utherium_item"));
        blastingRecipeTag(UGItems.UTHERIUM_CHUNK.get(), UGTags.Items.UTHERIUM_ITEMS, .1F).save(consumer, name("blast_utherium_item"));

        smeltingRecipe(UGItems.DWELLER_STEAK.get(), UGItems.RAW_DWELLER_MEAT.get(), .35F).save(consumer, name("smelt_dweller_meat"));
        smokingRecipe(UGItems.DWELLER_STEAK.get(), UGItems.RAW_DWELLER_MEAT.get(), .35F).save(consumer, name("smoke_dweller_meat"));

        smeltingRecipe(UGItems.COOKED_GWIBLING.get(), UGItems.RAW_GWIBLING.get(), .35F).save(consumer, name("smelt_gwibling"));
        smokingRecipe(UGItems.COOKED_GWIBLING.get(), UGItems.RAW_GWIBLING.get(), .35F).save(consumer, name("smoke_gwibling"));

        smeltingRecipe(UGItems.GLOOMPER_LEG.get(), UGItems.RAW_GLOOMPER_LEG.get(), .35F).save(consumer, name("smelt_gloomper_leg"));
        smokingRecipe(UGItems.GLOOMPER_LEG.get(), UGItems.RAW_GLOOMPER_LEG.get(), .35F).save(consumer, name("smoke_gloomper_leg"));

        smeltingRecipe(Items.DRIED_KELP, UGItems.GLOWING_KELP.get(), 0.1F).save(consumer, name("smelt_glowing_kelp"));
        smokingRecipe(Items.DRIED_KELP, UGItems.GLOWING_KELP.get(), 0.1F).save(consumer, name("smoke_glowing_kelp"));

        smeltingRecipe(UGItems.ROASTED_UNDERBEANS.get(), UGItems.UNDERBEANS.get(), 0.35F).save(consumer, name("smelt_underbeans"));
        smokingRecipe(UGItems.ROASTED_UNDERBEANS.get(), UGItems.UNDERBEANS.get(), 0.35F).save(consumer, name("smoke_underbeans"));

        depthrockStonecuttingRecipe(UGBlocks.CHISELED_DEPTHROCK_BRICKS.get()).save(consumer, name("chiseled_depthrock_bricks_stonecutting"));
        depthrockStonecuttingRecipe(UGBlocks.DEPTHROCK_BRICK_SLAB.get(), 2).save(consumer, name("depthrock_brick_slab_stonecutting"));
        depthrockStonecuttingRecipe(UGBlocks.DEPTHROCK_BRICK_STAIRS.get()).save(consumer, name("depthrock_brick_stairs_stonecutting"));
        depthrockStonecuttingRecipe(UGBlocks.DEPTHROCK_BRICK_WALL.get()).save(consumer, name("depthrock_brick_wall_stonecutting"));
        depthrockStonecuttingRecipe(UGBlocks.DEPTHROCK_BRICKS.get()).save(consumer, name("depthrock_bricks_stonecutting"));
        depthrockStonecuttingRecipe(UGBlocks.DEPTHROCK_SLAB.get(), 2).save(consumer, name("depthrock_slab_stonecutting"));
        depthrockStonecuttingRecipe(UGBlocks.DEPTHROCK_STAIRS.get()).save(consumer, name("depthrock_stairs_stonecutting"));
        depthrockStonecuttingRecipe(UGBlocks.DEPTHROCK_WALL.get()).save(consumer, name("depthrock_wall_stonecutting"));

        depthrockStonecuttingRecipe(UGItems.DEPTHROCK_PEBBLE.get(), 9).save(consumer, name("depthrock_pebble_stonecutting"));

        shiverstoneStonecuttingRecipe(UGBlocks.CHISELED_SHIVERSTONE_BRICKS.get()).save(consumer, name("chiseled_shiverstone_bricks_stonecutting"));
        shiverstoneStonecuttingRecipe(UGBlocks.SHIVERSTONE_BRICK_SLAB.get(), 2).save(consumer, name("shiverstone_brick_slab_stonecutting"));
        shiverstoneStonecuttingRecipe(UGBlocks.SHIVERSTONE_BRICK_STAIRS.get()).save(consumer, name("shiverstone_brick_stairs_stonecutting"));
        shiverstoneStonecuttingRecipe(UGBlocks.SHIVERSTONE_BRICK_WALL.get()).save(consumer, name("shiverstone_brick_wall_stonecutting"));
        shiverstoneStonecuttingRecipe(UGBlocks.SHIVERSTONE_BRICKS.get()).save(consumer, name("shiverstone_bricks_stonecutting"));
        shiverstoneStonecuttingRecipe(UGBlocks.SHIVERSTONE_SLAB.get(), 2).save(consumer, name("shiverstone_slab_stonecutting"));
        shiverstoneStonecuttingRecipe(UGBlocks.SHIVERSTONE_STAIRS.get()).save(consumer, name("shiverstone_stairs_stonecutting"));
        shiverstoneStonecuttingRecipe(UGBlocks.SHIVERSTONE_WALL.get()).save(consumer, name("shiverstone_wall_stonecutting"));

        tremblecrustStonecuttingRecipe(UGBlocks.CHISELED_TREMBLECRUST_BRICKS.get()).save(consumer, name("chiseled_tremblecrust_bricks_stonecutting"));
        tremblecrustStonecuttingRecipe(UGBlocks.TREMBLECRUST_BRICK_SLAB.get(), 2).save(consumer, name("tremblecrust_brick_slab_stonecutting"));
        tremblecrustStonecuttingRecipe(UGBlocks.TREMBLECRUST_BRICK_STAIRS.get()).save(consumer, name("tremblecrust_brick_stairs_stonecutting"));
        tremblecrustStonecuttingRecipe(UGBlocks.TREMBLECRUST_BRICK_WALL.get()).save(consumer, name("tremblecrust_brick_wall_stonecutting"));
        tremblecrustStonecuttingRecipe(UGBlocks.TREMBLECRUST_BRICKS.get()).save(consumer, name("tremblecrust_bricks_stonecutting"));
        tremblecrustStonecuttingRecipe(UGBlocks.TREMBLECRUST_SLAB.get(), 2).save(consumer, name("tremblecrust_slab_stonecutting"));
        tremblecrustStonecuttingRecipe(UGBlocks.TREMBLECRUST_STAIRS.get()).save(consumer, name("tremblecrust_stairs_stonecutting"));
        tremblecrustStonecuttingRecipe(UGBlocks.TREMBLECRUST_WALL.get()).save(consumer, name("tremblecrust_wall_stonecutting"));
    }

    private ResourceLocation name(String name) {
        return new ResourceLocation(Undergarden.MODID, name);
    }
}
