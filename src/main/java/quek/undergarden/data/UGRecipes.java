package quek.undergarden.data;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import quek.undergarden.Undergarden;
import quek.undergarden.data.provider.UGRecipeProvider;
import quek.undergarden.recipe.InfusingBookCategory;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGTags;

import java.util.concurrent.CompletableFuture;

public class UGRecipes extends UGRecipeProvider {

	public UGRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, provider);
	}

	@Override
	protected void buildRecipes(RecipeOutput output) {
		makePlanks(UGBlocks.SMOGSTEM_PLANKS, UGTags.Items.SMOGSTEM_LOGS).save(output);
		makePlanks(UGBlocks.WIGGLEWOOD_PLANKS, UGTags.Items.WIGGLEWOOD_LOGS).save(output);
		makePlanks(UGBlocks.GRONGLE_PLANKS, UGTags.Items.GRONGLE_LOGS).save(output);
		makePlanks(UGBlocks.ANCIENT_ROOT_PLANKS, UGBlocks.ANCIENT_ROOT).save(output);

		makeBricks(UGBlocks.POLISHED_DEPTHROCK, UGBlocks.DEPTHROCK).save(output);
		makeBricks(UGBlocks.DEPTHROCK_BRICKS, UGBlocks.POLISHED_DEPTHROCK).save(output);
		makeBricks(UGBlocks.SHIVERSTONE_BRICKS, UGBlocks.SHIVERSTONE).save(output);
		makeBricks(UGBlocks.TREMBLECRUST_BRICKS, UGBlocks.TREMBLECRUST).save(output);
		makeBricks(UGBlocks.DEPTHROCK_TILES, UGBlocks.DEPTHROCK_BRICKS).save(output);
		makeBricks(UGBlocks.DREADROCK_BRICKS, UGBlocks.DREADROCK).save(output);

		makeChiseledBricks(UGBlocks.CHISELED_DEPTHROCK_BRICKS, UGBlocks.DEPTHROCK_BRICK_SLAB).save(output);
		makeChiseledBricks(UGBlocks.CHISELED_SHIVERSTONE_BRICKS, UGBlocks.SHIVERSTONE_BRICK_SLAB).save(output);
		makeChiseledBricks(UGBlocks.CHISELED_TREMBLECRUST_BRICKS, UGBlocks.TREMBLECRUST_BRICK_SLAB).save(output);

		makeWood(UGBlocks.SMOGSTEM_WOOD, UGBlocks.SMOGSTEM_LOG).save(output);
		makeWood(UGBlocks.STRIPPED_SMOGSTEM_WOOD, UGBlocks.STRIPPED_SMOGSTEM_LOG).save(output);
		makeWood(UGBlocks.WIGGLEWOOD_WOOD, UGBlocks.WIGGLEWOOD_LOG).save(output);
		makeWood(UGBlocks.STRIPPED_WIGGLEWOOD_WOOD, UGBlocks.STRIPPED_WIGGLEWOOD_LOG).save(output);
		makeWood(UGBlocks.GRONGLE_WOOD, UGBlocks.GRONGLE_LOG).save(output);
		makeWood(UGBlocks.STRIPPED_GRONGLE_WOOD, UGBlocks.STRIPPED_GRONGLE_LOG).save(output);

		makeBoat(UGItems.SMOGSTEM_BOAT, UGBlocks.SMOGSTEM_PLANKS).save(output);
		makeBoat(UGItems.WIGGLEWOOD_BOAT, UGBlocks.WIGGLEWOOD_PLANKS).save(output);
		makeBoat(UGItems.GRONGLE_BOAT, UGBlocks.GRONGLE_PLANKS).save(output);
		makeBoat(UGItems.ANCIENT_ROOT_BOAT, UGBlocks.ANCIENT_ROOT_PLANKS).save(output);

		makeChestBoat(UGItems.SMOGSTEM_CHEST_BOAT, UGItems.SMOGSTEM_BOAT).save(output);
		makeChestBoat(UGItems.WIGGLEWOOD_CHEST_BOAT, UGItems.WIGGLEWOOD_BOAT).save(output);
		makeChestBoat(UGItems.GRONGLE_CHEST_BOAT, UGItems.GRONGLE_BOAT).save(output);
		makeChestBoat(UGItems.ANCIENT_ROOT_CHEST_BOAT, UGItems.ANCIENT_ROOT_BOAT).save(output);

		makeSign(UGBlocks.SMOGSTEM_SIGN, UGBlocks.SMOGSTEM_PLANKS).save(output);
		makeSign(UGBlocks.WIGGLEWOOD_SIGN, UGBlocks.WIGGLEWOOD_PLANKS).save(output);
		makeSign(UGBlocks.GRONGLE_SIGN, UGBlocks.GRONGLE_PLANKS).save(output);
		makeSign(UGBlocks.ANCIENT_ROOT_SIGN, UGBlocks.ANCIENT_ROOT_PLANKS).save(output);

		makeHangingSign(UGBlocks.SMOGSTEM_HANGING_SIGN, UGBlocks.STRIPPED_SMOGSTEM_LOG).save(output);
		makeHangingSign(UGBlocks.WIGGLEWOOD_HANGING_SIGN, UGBlocks.STRIPPED_WIGGLEWOOD_LOG).save(output);
		makeHangingSign(UGBlocks.GRONGLE_HANGING_SIGN, UGBlocks.STRIPPED_GRONGLE_LOG).save(output);
		makeHangingSign(UGBlocks.ANCIENT_ROOT_HANGING_SIGN, UGBlocks.ANCIENT_ROOT).save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, Blocks.STICKY_PISTON)
			.pattern("G")
			.pattern("P")
			.define('G', UGItems.GOO_BALL.get())
			.define('P', Blocks.PISTON)
			.unlockedBy("has_goo_ball", has(UGItems.GOO_BALL.get()))
			.save(output, name("sticky_piston_from_goo_ball"));

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.STONECUTTER)
			.pattern(" I ")
			.pattern("SSS")
			.define('I', Items.IRON_INGOT)
			.define('S', UGBlocks.DEPTHROCK.get())
			.unlockedBy("has_depthrock", has(UGBlocks.DEPTHROCK.get()))
			.save(output, name("stonecutter_from_depthrock"));

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.STONECUTTER)
			.pattern(" I ")
			.pattern("SSS")
			.define('I', Items.IRON_INGOT)
			.define('S', UGBlocks.SHIVERSTONE.get())
			.unlockedBy("has_shiverstone", has(UGBlocks.SHIVERSTONE.get()))
			.save(output, name("stonecutter_from_shiverstone"));

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.STONECUTTER)
			.pattern(" I ")
			.pattern("SSS")
			.define('I', Items.IRON_INGOT)
			.define('S', UGBlocks.TREMBLECRUST.get())
			.unlockedBy("has_tremblecrust", has(UGBlocks.TREMBLECRUST.get()))
			.save(output, name("stonecutter_from_tremblecrust"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UGItems.UNDERBEAN_STICK.get())
			.requires(Items.STICK)
			.requires(UGItems.UNDERBEANS.get())
			.unlockedBy("has_underbeans", has(UGItems.UNDERBEANS.get()))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UGBlocks.MOGMOSS_RUG.get(), 3)
			.pattern("MM")
			.define('M', UGItems.MOGMOSS.get())
			.unlockedBy("has_mogmoss", has(UGItems.MOGMOSS.get()))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UGBlocks.BLUE_MOGMOSS_RUG.get(), 3)
			.pattern("MM")
			.define('M', UGItems.BLUE_MOGMOSS.get())
			.unlockedBy("has_blue_mogmoss", has(UGItems.BLUE_MOGMOSS.get()))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UGBlocks.DEPTHROCK_BED.get())
			.pattern("MMM")
			.pattern("DDD")
			.define('M', UGItems.MOGMOSS.get())
			.define('D', UGBlocks.DEPTHROCK.get())
			.unlockedBy("has_mogmoss", has(UGItems.MOGMOSS.get()))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UGItems.TWISTYTWIG.get(), 4)
			.pattern("P ")
			.pattern(" P")
			.define('P', UGBlocks.WIGGLEWOOD_PLANKS.get())
			.unlockedBy("has_wigglewood_planks", has(UGBlocks.WIGGLEWOOD_PLANKS.get()))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.SCAFFOLDING, 6)
			.pattern("STS")
			.pattern("S S")
			.pattern("S S")
			.define('S', Tags.Items.RODS_WOODEN)
			.define('T', UGItems.TWISTYTWIG.get())
			.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
			.unlockedBy("has_twistytwig", has(UGItems.TWISTYTWIG.get()))
			.save(output, name("undergarden_scaffolding"));

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UGBlocks.GLOOM_O_LANTERN.get())
			.pattern("G")
			.pattern("T")
			.define('G', UGBlocks.CARVED_GLOOMGOURD.get())
			.define('T', Items.TORCH)
			.unlockedBy("has_carved_gourd", has(UGBlocks.CARVED_GLOOMGOURD.get()))
			.unlockedBy("has_torch", has(Items.TORCH))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UGBlocks.SHARD_O_LANTERN.get())
			.pattern("G")
			.pattern("T")
			.define('G', UGBlocks.CARVED_GLOOMGOURD.get())
			.define('T', UGBlocks.SHARD_TORCH.get())
			.unlockedBy("has_carved_gourd", has(UGBlocks.CARVED_GLOOMGOURD.get()))
			.unlockedBy("has_shard_torch", has(UGBlocks.SHARD_TORCH.get()))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UGBlocks.CLOGGRUM_BARS.get(), 16)
			.pattern("CCC")
			.pattern("CCC")
			.define('C', UGItems.CLOGGRUM_INGOT.get())
			.unlockedBy("has_cloggrum_ingot", has(UGItems.CLOGGRUM_INGOT.get()))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, UGBlocks.COARSE_DEEPSOIL.get(), 4)
			.pattern("DP")
			.pattern("PD")
			.define('D', UGBlocks.DEEPSOIL.get())
			.define('P', UGItems.DEPTHROCK_PEBBLE.get())
			.unlockedBy("has_deepsoil", has(UGBlocks.DEEPSOIL.get()))
			.unlockedBy("has_pebble", has(UGItems.DEPTHROCK_PEBBLE.get()))
			.save(output);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, UGBlocks.DEEPSOIL.get())
			.requires(UGBlocks.COARSE_DEEPSOIL.get())
			.unlockedBy("has_coarse_deepsoil", has(UGBlocks.COARSE_DEEPSOIL.get()))
			.save(output, name("coarse_deepsoil_to_normal"));

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UGItems.SLINGSHOT.get())
			.pattern("STS")
			.pattern("SSS")
			.pattern(" S ")
			.define('S', Tags.Items.RODS_WOODEN)
			.define('T', UGItems.TWISTYTWIG.get())
			.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
			.unlockedBy("has_twistytwig", has(UGItems.TWISTYTWIG.get()))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UGItems.SPEAR.get())
			.pattern("  R")
			.pattern(" S ")
			.pattern("S  ")
			.define('R', UGItems.ROGDORIUM.get())
			.define('S', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_rogdorium", has(UGTags.Items.INGOTS_ROGDORIUM))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UGItems.BLISTERBOMB.get())
			.pattern(" T ")
			.pattern("BBB")
			.pattern("BBB")
			.define('T', UGItems.TWISTYTWIG.get())
			.define('B', UGItems.ROTTEN_BLISTERBERRY.get())
			.unlockedBy("has_twistytwig", has(UGItems.TWISTYTWIG.get()))
			.unlockedBy("has_blisterberry", has(UGItems.ROTTEN_BLISTERBERRY.get()))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UGItems.CLOGGRUM_SHIELD.get())
			.pattern("CSC")
			.pattern("CCC")
			.pattern(" C ")
			.define('S', ItemTags.PLANKS)
			.define('C', UGItems.CLOGGRUM_INGOT.get())
			.unlockedBy("has_scales", has(UGItems.CLOGGRUM_INGOT.get()))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UGBlocks.SHARD_TORCH.get(), 1)
			.pattern("C")
			.pattern("S")
			.define('C', UGItems.UTHERIC_SHARD.get())
			.define('S', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_shard", has(UGItems.UTHERIC_SHARD.get()))
			.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.TORCH, 2)
			.pattern("D")
			.pattern("S")
			.define('D', UGItems.DITCHBULB_PASTE.get())
			.define('S', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_ditchbulb_paste", has(UGItems.DITCHBULB_PASTE.get()))
			.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
			.save(output, name("torch_ditchbulb_paste"));

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, UGItems.CATALYST.get())
			.pattern("GIG")
			.pattern("IDI")
			.pattern("GIG")
			.define('G', Items.GOLD_INGOT)
			.define('I', Items.IRON_INGOT)
			.define('D', Items.DIAMOND)
			.unlockedBy("has_gold", has(Items.GOLD_INGOT))
			.unlockedBy("has_iron", has(Items.IRON_INGOT))
			.unlockedBy("has_diamond", has(Items.DIAMOND))
			.save(output);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, UGItems.GLOOMGOURD_PIE.get())
			.requires(UGTags.Items.MUSHROOMS)
			.requires(UGBlocks.GLOOMGOURD.get())
			.requires(UGItems.GLITTERKELP.get())
			.unlockedBy("has_gloomgourd", has(UGBlocks.GLOOMGOURD.get()))
			.save(output);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UGItems.GLOOMGOURD_SEEDS.get(), 4)
			.requires(UGBlocks.GLOOMGOURD.get())
			.unlockedBy("has_gloomgourd", has(UGBlocks.GLOOMGOURD.get()))
			.save(output);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BONE_MEAL, 4)
			.requires(UGItems.BRUTE_TUSK.get())
			.unlockedBy("has_tusk", has(UGItems.BRUTE_TUSK.get()))
			.save(output, name("tusk_to_bonemeal"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.RED_DYE)
			.requires(UGBlocks.BLOOD_MUSHROOM.get())
			.unlockedBy("has_blood_mushroom", has(UGBlocks.BLOOD_MUSHROOM.get()))
			.save(output, name("blood_mushroom_to_dye"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BLACK_DYE)
			.requires(UGBlocks.INK_MUSHROOM.get())
			.unlockedBy("has_ink_mushroom", has(UGBlocks.INK_MUSHROOM.get()))
			.save(output, name("ink_mushroom_to_dye"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BLUE_DYE)
			.requires(UGBlocks.INDIGO_MUSHROOM.get())
			.unlockedBy("has_indigo_mushroom", has(UGBlocks.INDIGO_MUSHROOM.get()))
			.save(output, name("indigo_mushroom_to_dye"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.WHITE_DYE)
			.requires(UGBlocks.VEIL_MUSHROOM.get())
			.unlockedBy("has_veil_mushroom", has(UGBlocks.VEIL_MUSHROOM.get()))
			.save(output, name("veil_mushroom_to_dye"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.PINK_DYE)
			.requires(UGBlocks.AMOROUS_BRISTLE.get())
			.unlockedBy("has_amorous_bristle", has(UGBlocks.AMOROUS_BRISTLE.get()))
			.save(output, name("amorous_bristle_to_dye"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.LIGHT_BLUE_DYE)
			.requires(UGBlocks.MISERABELL.get())
			.unlockedBy("has_miserabell", has(UGBlocks.MISERABELL.get()))
			.save(output, name("miserabell_to_dye"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.YELLOW_DYE)
			.requires(UGBlocks.BUTTERBUNCH.get())
			.unlockedBy("has_butterbunch", has(UGBlocks.BUTTERBUNCH.get()))
			.save(output, name("butterbunch_to_dye"));

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.LEAD, 2)
			.pattern("TT ")
			.pattern("TG ")
			.pattern("  T")
			.define('T', UGItems.TWISTYTWIG.get())
			.define('G', UGItems.GOO_BALL.get())
			.unlockedBy("has_twistytwig", has(UGItems.TWISTYTWIG.get()))
			.unlockedBy("has_goo_ball", has(UGItems.GOO_BALL.get()))
			.save(output, name("undergarden_lead"));

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UGBlocks.SEDIMENT_GLASS_PANE.get(), 16)
			.pattern("GGG")
			.pattern("GGG")
			.define('G', UGBlocks.SEDIMENT_GLASS.get())
			.unlockedBy("has_sediment_glass", has(UGBlocks.SEDIMENT_GLASS.get()))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, UGBlocks.CLOGGRUM_TILES.get(), 4)
			.pattern("CC")
			.pattern("CC")
			.define('C', UGItems.CLOGGRUM_INGOT.get())
			.unlockedBy("has_cloggrum_ingot", has(UGItems.CLOGGRUM_INGOT.get()))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UGBlocks.CLOGGRUM_LANTERN.get())
			.pattern("NNN")
			.pattern("NTN")
			.pattern("NNN")
			.define('N', UGItems.CLOGGRUM_NUGGET.get())
			.define('T', Items.TORCH)
			.unlockedBy("has_cloggrum_ingot", has(UGItems.CLOGGRUM_INGOT.get()))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, UGBlocks.BOOMGOURD.get())
			.pattern("BBB")
			.pattern("BGB")
			.pattern("BBB")
			.define('B', UGItems.BLISTERBOMB.get())
			.define('G', UGBlocks.GLOOMGOURD.get())
			.unlockedBy("has_gloomgourd", has(UGBlocks.GLOOMGOURD.get()))
			.unlockedBy("has_blisterbomb", has(UGItems.BLISTERBOMB.get()))
			.save(output);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UGItems.DITCHBULB_PASTE.get())
			.requires(UGItems.DITCHBULB.get())
			.unlockedBy("has_ditchbulb", has(UGItems.DITCHBULB.get()))
			.save(output);

		makeIngotToBlock(UGBlocks.CLOGGRUM_BLOCK, UGItems.CLOGGRUM_INGOT).save(output);
		makeIngotToBlock(UGBlocks.FROSTSTEEL_BLOCK, UGItems.FROSTSTEEL_INGOT).save(output);
		makeIngotToBlock(UGBlocks.UTHERIUM_BLOCK, UGItems.UTHERIUM_CRYSTAL).save(output);
		makeIngotToBlock(UGBlocks.REGALIUM_BLOCK, UGItems.REGALIUM_CRYSTAL).save(output);
		makeIngotToBlock(UGBlocks.ROGDORIUM_BLOCK, UGItems.ROGDORIUM).save(output);
		makeIngotToBlock(UGBlocks.FORGOTTEN_BLOCK, UGItems.FORGOTTEN_INGOT).save(output);
		makeIngotToBlock(UGBlocks.DEPTHROCK, UGItems.DEPTHROCK_PEBBLE).save(output, name("pebbles_to_depthrock"));
		makeIngotToBlock(UGBlocks.GOO_BLOCK, UGItems.GOO_BALL).save(output);

		makeBlockToIngot(UGItems.CLOGGRUM_INGOT, UGBlocks.CLOGGRUM_BLOCK).save(output, name("cloggrum_block_to_ingot"));
		makeBlockToIngot(UGItems.FROSTSTEEL_INGOT, UGBlocks.FROSTSTEEL_BLOCK).save(output, name("froststeel_block_to_ingot"));
		makeBlockToIngot(UGItems.UTHERIUM_CRYSTAL, UGBlocks.UTHERIUM_BLOCK).save(output, name("utherium_block_to_crystal"));
		makeBlockToIngot(UGItems.REGALIUM_CRYSTAL, UGBlocks.REGALIUM_BLOCK).save(output, name("regalium_block_to_crystal"));
		makeBlockToIngot(UGItems.ROGDORIUM, UGBlocks.ROGDORIUM_BLOCK).save(output, name("rogdorium_block_to_crystal"));
		makeBlockToIngot(UGItems.FORGOTTEN_INGOT, UGBlocks.FORGOTTEN_BLOCK).save(output, name("forgotten_block_to_ingot"));
		makeBlockToIngot(UGItems.GOO_BALL, UGBlocks.GOO_BLOCK).save(output, name("goo_block_to_ball"));

		makeIngotToNugget(UGItems.CLOGGRUM_NUGGET, UGItems.CLOGGRUM_INGOT).save(output, name("cloggrum_ingot_to_nugget"));
		makeIngotToNugget(UGItems.FROSTSTEEL_NUGGET, UGItems.FROSTSTEEL_INGOT).save(output, name("froststeel_ingot_to_nugget"));
		makeIngotToNugget(UGItems.ROGDORIUM_NUGGET, UGItems.ROGDORIUM).save(output, name("rogdorium_to_nugget"));
		makeIngotToNugget(UGItems.FORGOTTEN_NUGGET, UGItems.FORGOTTEN_INGOT).save(output, name("forgotten_ingot_to_nugget"));

		makeNuggetToIngot(UGItems.CLOGGRUM_INGOT, UGItems.CLOGGRUM_NUGGET).save(output, name("cloggrum_nugget_to_ingot"));
		makeNuggetToIngot(UGItems.FROSTSTEEL_INGOT, UGItems.FROSTSTEEL_NUGGET).save(output, name("froststeel_nugget_to_ingot"));
		makeNuggetToIngot(UGItems.ROGDORIUM, UGItems.ROGDORIUM_NUGGET).save(output, name("rogdorium_nugget_to_ingot"));
		makeNuggetToIngot(UGItems.FORGOTTEN_INGOT, UGItems.FORGOTTEN_NUGGET).save(output, name("forgotten_nugget_to_ingot"));
		makeNuggetToIngot(UGItems.UTHERIC_CLUSTER, UGItems.UTHERIC_SHARD).save(output);

		makeIngotToBlock(UGBlocks.RAW_CLOGGRUM_BLOCK, UGItems.RAW_CLOGGRUM).save(output);
		makeIngotToBlock(UGBlocks.RAW_FROSTSTEEL_BLOCK, UGItems.RAW_FROSTSTEEL).save(output);

		makeBlockToIngot(UGItems.RAW_CLOGGRUM, UGBlocks.RAW_CLOGGRUM_BLOCK).save(output, name("raw_cloggrum_from_block"));
		makeBlockToIngot(UGItems.RAW_FROSTSTEEL, UGBlocks.RAW_FROSTSTEEL_BLOCK).save(output, name("raw_froststeel_from_block"));

		makeSword(UGItems.CLOGGRUM_SWORD, UGItems.CLOGGRUM_INGOT).save(output);
		makeSword(UGItems.FROSTSTEEL_SWORD, UGItems.FROSTSTEEL_INGOT).save(output);
		makeSword(UGItems.UTHERIUM_SWORD, UGItems.UTHERIUM_CRYSTAL).save(output);

		makePickaxe(UGItems.CLOGGRUM_PICKAXE, UGItems.CLOGGRUM_INGOT).save(output);
		makePickaxe(UGItems.FROSTSTEEL_PICKAXE, UGItems.FROSTSTEEL_INGOT).save(output);
		makePickaxe(UGItems.UTHERIUM_PICKAXE, UGItems.UTHERIUM_CRYSTAL).save(output);

		makeAxe(UGItems.CLOGGRUM_AXE, UGItems.CLOGGRUM_INGOT).save(output);
		makeAxe(UGItems.FROSTSTEEL_AXE, UGItems.FROSTSTEEL_INGOT).save(output);
		makeAxe(UGItems.UTHERIUM_AXE, UGItems.UTHERIUM_CRYSTAL).save(output);

		makeShovel(UGItems.CLOGGRUM_SHOVEL, UGItems.CLOGGRUM_INGOT).save(output);
		makeShovel(UGItems.FROSTSTEEL_SHOVEL, UGItems.FROSTSTEEL_INGOT).save(output);
		makeShovel(UGItems.UTHERIUM_SHOVEL, UGItems.UTHERIUM_CRYSTAL).save(output);

		makeHoe(UGItems.CLOGGRUM_HOE, UGItems.CLOGGRUM_INGOT).save(output);
		makeHoe(UGItems.FROSTSTEEL_HOE, UGItems.FROSTSTEEL_INGOT).save(output);
		makeHoe(UGItems.UTHERIUM_HOE, UGItems.UTHERIUM_CRYSTAL).save(output);

		makeHelmet(UGItems.CLOGGRUM_HELMET, UGItems.CLOGGRUM_INGOT).save(output);
		makeChestplate(UGItems.CLOGGRUM_CHESTPLATE, UGItems.CLOGGRUM_INGOT).save(output);
		makeLeggings(UGItems.CLOGGRUM_LEGGINGS, UGItems.CLOGGRUM_INGOT).save(output);
		makeBoots(UGItems.CLOGGRUM_BOOTS, UGItems.CLOGGRUM_INGOT).save(output);

		makeHelmet(UGItems.FROSTSTEEL_HELMET, UGItems.FROSTSTEEL_INGOT).save(output);
		makeChestplate(UGItems.FROSTSTEEL_CHESTPLATE, UGItems.FROSTSTEEL_INGOT).save(output);
		makeLeggings(UGItems.FROSTSTEEL_LEGGINGS, UGItems.FROSTSTEEL_INGOT).save(output);
		makeBoots(UGItems.FROSTSTEEL_BOOTS, UGItems.FROSTSTEEL_INGOT).save(output);

		makeHelmet(UGItems.UTHERIUM_HELMET, UGItems.UTHERIUM_CRYSTAL).save(output);
		makeChestplate(UGItems.UTHERIUM_CHESTPLATE, UGItems.UTHERIUM_CRYSTAL).save(output);
		makeLeggings(UGItems.UTHERIUM_LEGGINGS, UGItems.UTHERIUM_CRYSTAL).save(output);
		makeBoots(UGItems.UTHERIUM_BOOTS, UGItems.UTHERIUM_CRYSTAL).save(output);

		makeStairs(UGBlocks.DEPTHROCK_STAIRS, UGBlocks.DEPTHROCK).save(output);
		makeStairs(UGBlocks.DEPTHROCK_BRICK_STAIRS, UGBlocks.DEPTHROCK_BRICKS).save(output);
		makeStairs(UGBlocks.SMOGSTEM_STAIRS, UGBlocks.SMOGSTEM_PLANKS).save(output);
		makeStairs(UGBlocks.WIGGLEWOOD_STAIRS, UGBlocks.WIGGLEWOOD_PLANKS).save(output);
		makeStairs(UGBlocks.GRONGLE_STAIRS, UGBlocks.GRONGLE_PLANKS).save(output);
		makeStairs(UGBlocks.SHIVERSTONE_STAIRS, UGBlocks.SHIVERSTONE).save(output);
		makeStairs(UGBlocks.SHIVERSTONE_BRICK_STAIRS, UGBlocks.SHIVERSTONE_BRICKS).save(output);
		makeStairs(UGBlocks.TREMBLECRUST_STAIRS, UGBlocks.TREMBLECRUST).save(output);
		makeStairs(UGBlocks.TREMBLECRUST_BRICK_STAIRS, UGBlocks.TREMBLECRUST_BRICKS).save(output);
		makeStairs(UGBlocks.CLOGGRUM_TILE_STAIRS, UGBlocks.CLOGGRUM_TILES).save(output);
		makeStairs(UGBlocks.DEPTHROCK_TILE_STAIRS, UGBlocks.DEPTHROCK_TILES).save(output);
		makeStairs(UGBlocks.POLISHED_DEPTHROCK_STAIRS, UGBlocks.POLISHED_DEPTHROCK).save(output);
		makeStairs(UGBlocks.ANCIENT_ROOT_STAIRS, UGBlocks.ANCIENT_ROOT_PLANKS).save(output);
		makeStairs(UGBlocks.DREADROCK_STAIRS, UGBlocks.DREADROCK).save(output);
		makeStairs(UGBlocks.DREADROCK_BRICK_STAIRS, UGBlocks.DREADROCK_BRICKS).save(output);

		makeSlab(UGBlocks.DEPTHROCK_SLAB, UGBlocks.DEPTHROCK).save(output);
		makeSlab(UGBlocks.DEPTHROCK_BRICK_SLAB, UGBlocks.DEPTHROCK_BRICKS).save(output);
		makeSlab(UGBlocks.SMOGSTEM_SLAB, UGBlocks.SMOGSTEM_PLANKS).save(output);
		makeSlab(UGBlocks.WIGGLEWOOD_SLAB, UGBlocks.WIGGLEWOOD_PLANKS).save(output);
		makeSlab(UGBlocks.GRONGLE_SLAB, UGBlocks.GRONGLE_PLANKS).save(output);
		makeSlab(UGBlocks.SHIVERSTONE_SLAB, UGBlocks.SHIVERSTONE).save(output);
		makeSlab(UGBlocks.SHIVERSTONE_BRICK_SLAB, UGBlocks.SHIVERSTONE_BRICKS).save(output);
		makeSlab(UGBlocks.TREMBLECRUST_SLAB, UGBlocks.TREMBLECRUST).save(output);
		makeSlab(UGBlocks.TREMBLECRUST_BRICK_SLAB, UGBlocks.TREMBLECRUST_BRICKS).save(output);
		makeSlab(UGBlocks.CLOGGRUM_TILE_SLAB, UGBlocks.CLOGGRUM_TILES).save(output);
		makeSlab(UGBlocks.DEPTHROCK_TILE_SLAB, UGBlocks.DEPTHROCK_TILES).save(output);
		makeSlab(UGBlocks.POLISHED_DEPTHROCK_SLAB, UGBlocks.POLISHED_DEPTHROCK).save(output);
		makeSlab(UGBlocks.ANCIENT_ROOT_SLAB, UGBlocks.ANCIENT_ROOT_PLANKS).save(output);
		makeSlab(UGBlocks.DREADROCK_SLAB, UGBlocks.DREADROCK).save(output);
		makeSlab(UGBlocks.DREADROCK_BRICK_SLAB, UGBlocks.DREADROCK_BRICKS).save(output);

		makeWall(UGBlocks.DEPTHROCK_WALL, UGBlocks.DEPTHROCK).save(output);
		makeWall(UGBlocks.DEPTHROCK_BRICK_WALL, UGBlocks.DEPTHROCK_BRICKS).save(output);
		makeWall(UGBlocks.SHIVERSTONE_WALL, UGBlocks.SHIVERSTONE).save(output);
		makeWall(UGBlocks.SHIVERSTONE_BRICK_WALL, UGBlocks.SHIVERSTONE_BRICKS).save(output);
		makeWall(UGBlocks.TREMBLECRUST_WALL, UGBlocks.TREMBLECRUST).save(output);
		makeWall(UGBlocks.TREMBLECRUST_BRICK_WALL, UGBlocks.TREMBLECRUST_BRICKS).save(output);
		makeWall(UGBlocks.POLISHED_DEPTHROCK_WALL, UGBlocks.POLISHED_DEPTHROCK).save(output);
		makeWall(UGBlocks.DREADROCK_WALL, UGBlocks.DREADROCK).save(output);
		makeWall(UGBlocks.DREADROCK_BRICK_WALL, UGBlocks.DREADROCK_BRICKS).save(output);

		makeFence(UGBlocks.SMOGSTEM_FENCE, UGBlocks.SMOGSTEM_PLANKS).save(output);
		makeFence(UGBlocks.WIGGLEWOOD_FENCE, UGBlocks.WIGGLEWOOD_PLANKS).save(output);
		makeFence(UGBlocks.GRONGLE_FENCE, UGBlocks.GRONGLE_PLANKS).save(output);
		makeFence(UGBlocks.ANCIENT_ROOT_FENCE, UGBlocks.ANCIENT_ROOT_PLANKS).save(output);

		makeFenceGate(UGBlocks.SMOGSTEM_FENCE_GATE, UGBlocks.SMOGSTEM_PLANKS).save(output);
		makeFenceGate(UGBlocks.WIGGLEWOOD_FENCE_GATE, UGBlocks.WIGGLEWOOD_PLANKS).save(output);
		makeFenceGate(UGBlocks.GRONGLE_FENCE_GATE, UGBlocks.GRONGLE_PLANKS).save(output);
		makeFenceGate(UGBlocks.ANCIENT_ROOT_FENCE_GATE, UGBlocks.ANCIENT_ROOT_PLANKS).save(output);

		makeDoor(UGBlocks.SMOGSTEM_DOOR, UGBlocks.SMOGSTEM_PLANKS).save(output);
		makeDoor(UGBlocks.WIGGLEWOOD_DOOR, UGBlocks.WIGGLEWOOD_PLANKS).save(output);
		makeDoor(UGBlocks.GRONGLE_DOOR, UGBlocks.GRONGLE_PLANKS).save(output);
		makeDoor(UGBlocks.ANCIENT_ROOT_DOOR, UGBlocks.ANCIENT_ROOT_PLANKS).save(output);

		makeTrapdoor(UGBlocks.SMOGSTEM_TRAPDOOR, UGBlocks.SMOGSTEM_PLANKS).save(output);
		makeTrapdoor(UGBlocks.WIGGLEWOOD_TRAPDOOR, UGBlocks.WIGGLEWOOD_PLANKS).save(output);
		makeTrapdoor(UGBlocks.GRONGLE_TRAPDOOR, UGBlocks.GRONGLE_PLANKS).save(output);
		makeTrapdoor(UGBlocks.ANCIENT_ROOT_TRAPDOOR, UGBlocks.ANCIENT_ROOT_PLANKS).save(output);

		makeButton(UGBlocks.SMOGSTEM_BUTTON, UGBlocks.SMOGSTEM_PLANKS).save(output);
		makeButton(UGBlocks.WIGGLEWOOD_BUTTON, UGBlocks.WIGGLEWOOD_PLANKS).save(output);
		makeButton(UGBlocks.GRONGLE_BUTTON, UGBlocks.GRONGLE_PLANKS).save(output);
		makeButton(UGBlocks.ANCIENT_ROOT_BUTTON, UGBlocks.ANCIENT_ROOT_PLANKS).save(output);
		makeButton(UGBlocks.DEPTHROCK_BUTTON, UGBlocks.DEPTHROCK).save(output);
		makeButton(UGBlocks.SHIVERSTONE_BUTTON, UGBlocks.SHIVERSTONE).save(output);
		makeButton(UGBlocks.TREMBLECRUST_BUTTON, UGBlocks.TREMBLECRUST).save(output);
		makeButton(UGBlocks.DREADROCK_BUTTON, UGBlocks.DREADROCK).save(output);

		makePressurePlate(UGBlocks.SMOGSTEM_PRESSURE_PLATE, UGBlocks.SMOGSTEM_PLANKS).save(output);
		makePressurePlate(UGBlocks.WIGGLEWOOD_PRESSURE_PLATE, UGBlocks.WIGGLEWOOD_PLANKS).save(output);
		makePressurePlate(UGBlocks.GRONGLE_PRESSURE_PLATE, UGBlocks.GRONGLE_PLANKS).save(output);
		makePressurePlate(UGBlocks.ANCIENT_ROOT_PRESSURE_PLATE, UGBlocks.ANCIENT_ROOT_PLANKS).save(output);
		makePressurePlate(UGBlocks.DEPTHROCK_PRESSURE_PLATE, UGBlocks.DEPTHROCK).save(output);
		makePressurePlate(UGBlocks.SHIVERSTONE_PRESSURE_PLATE, UGBlocks.SHIVERSTONE).save(output);
		makePressurePlate(UGBlocks.TREMBLECRUST_PRESSURE_PLATE, UGBlocks.TREMBLECRUST).save(output);
		makePressurePlate(UGBlocks.DREADROCK_PRESSURE_PLATE, UGBlocks.DREADROCK).save(output);

		makeStew(UGItems.BLOODY_STEW, UGBlocks.BLOOD_MUSHROOM).save(output);
		makeStew(UGItems.INKY_STEW, UGBlocks.INK_MUSHROOM).save(output);
		makeStew(UGItems.INDIGO_STEW, UGBlocks.INDIGO_MUSHROOM).save(output);
		makeStew(UGItems.VEILED_STEW, UGBlocks.VEIL_MUSHROOM).save(output);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, UGItems.SLOP_BOWL.get())
			.requires(Items.BOWL)
			.requires(UGItems.UNDERBEANS.get(), 2)
			.requires(UGItems.MOGMOSS.get(), 2)
			.unlockedBy("has_underbeans", has(UGItems.UNDERBEANS.get()))
			.unlockedBy("has_mogmoss", has(UGItems.MOGMOSS.get()))
			.save(output);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, UGItems.SLOP_BOWL.get())
			.requires(Items.BOWL)
			.requires(UGItems.UNDERBEANS.get(), 2)
			.requires(UGItems.BLUE_MOGMOSS.get(), 2)
			.unlockedBy("has_underbeans", has(UGItems.UNDERBEANS.get()))
			.unlockedBy("has_blue_mogmoss", has(UGItems.BLUE_MOGMOSS.get()))
			.save(output, name("slop_bowl_blue_moss"));

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UGItems.FORGOTTEN_UPGRADE_TEMPLATE.get(), 2)
			.pattern("DTD")
			.pattern("DRD")
			.pattern("DDD")
			.define('D', Tags.Items.GEMS_DIAMOND)
			.define('R', UGBlocks.DEPTHROCK.get())
			.define('T', UGItems.FORGOTTEN_UPGRADE_TEMPLATE.get())
			.unlockedBy("has_template", has(UGItems.FORGOTTEN_UPGRADE_TEMPLATE.get()))
			.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UGBlocks.INFUSER.get())
			.pattern("UDR")
			.pattern("D D")
			.pattern("D D")
			.define('D', UGBlocks.DREADROCK.get())
			.define('R', UGTags.Items.INGOTS_ROGDORIUM)
			.define('U', UGTags.Items.GEMS_UTHERIUM)
			.unlockedBy("has_dreadrock", has(UGBlocks.DREADROCK.get()))
			.save(output);

		smithingForgotten(UGItems.CLOGGRUM_SWORD, UGItems.FORGOTTEN_SWORD).save(output, name("forgotten_sword_smithing"));
		smithingForgotten(UGItems.CLOGGRUM_PICKAXE, UGItems.FORGOTTEN_PICKAXE).save(output, name("forgotten_pickaxe_smithing"));
		smithingForgotten(UGItems.CLOGGRUM_AXE, UGItems.FORGOTTEN_AXE).save(output, name("forgotten_axe_smithing"));
		smithingForgotten(UGItems.CLOGGRUM_SHOVEL, UGItems.FORGOTTEN_SHOVEL).save(output, name("forgotten_shovel_smithing"));
		smithingForgotten(UGItems.CLOGGRUM_HOE, UGItems.FORGOTTEN_HOE).save(output, name("forgotten_hoe_smithing"));
		smithingForgotten(UGItems.CLOGGRUM_BATTLEAXE, UGItems.FORGOTTEN_BATTLEAXE).save(output, name("forgotten_battleaxe_smithing"));

		smeltingRecipe(UGBlocks.CRACKED_DEPTHROCK_BRICKS.get(), UGBlocks.DEPTHROCK_BRICKS.get(), 0.1F).save(output, name("smelt_depthrock_bricks"));
		smeltingRecipe(UGBlocks.CRACKED_SHIVERSTONE_BRICKS.get(), UGBlocks.SHIVERSTONE_BRICKS.get(), 0.1F).save(output, name("smelt_shiverstone_bricks"));
		smeltingRecipe(UGBlocks.CRACKED_TREMBLECRUST_BRICKS.get(), UGBlocks.TREMBLECRUST_BRICKS.get(), 0.1F).save(output, name("smelt_tremblecrust_bricks"));

		smeltingRecipe(UGBlocks.SEDIMENT_GLASS.get(), UGBlocks.SEDIMENT.get(), 0.1F).save(output);

		smeltingRecipe(Items.DIAMOND, UGItems.CATALYST.get(), 0.0F).save(output, name("smelt_catalyst"));
		blastingRecipe(Items.DIAMOND, UGItems.CATALYST.get(), 0.0F).save(output, name("blast_catalyst"));

		ore(Items.COAL, ImmutableList.of(UGBlocks.DEPTHROCK_COAL_ORE.get(), UGBlocks.SHIVERSTONE_COAL_ORE.get()), 0.1F, "coal", output);
		ore(Items.IRON_INGOT, ImmutableList.of(UGBlocks.DEPTHROCK_IRON_ORE.get(), UGBlocks.SHIVERSTONE_IRON_ORE.get()), 0.7F, "iron_ingot", output);
		ore(Items.GOLD_INGOT, ImmutableList.of(UGBlocks.DEPTHROCK_GOLD_ORE.get()), 1.0F, "gold_ingot", output);
		ore(Items.DIAMOND, ImmutableList.of(UGBlocks.DEPTHROCK_DIAMOND_ORE.get(), UGBlocks.SHIVERSTONE_DIAMOND_ORE.get()), 1.0F, "diamond", output);
		ore(UGItems.CLOGGRUM_INGOT.get(), ImmutableList.of(UGItems.RAW_CLOGGRUM.get(), UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get(), UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get()), 0.7F, "undergarden:cloggrum_ingot", output);
		ore(UGItems.FROSTSTEEL_INGOT.get(), ImmutableList.of(UGItems.RAW_FROSTSTEEL.get(), UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get()), 0.7F, "undergarden:froststeel_ingot", output);
		ore(UGItems.UTHERIC_CLUSTER.get(), ImmutableList.of(UGBlocks.DEPTHROCK_UTHERIUM_ORE.get(), UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get(), UGBlocks.DREADROCK_UTHERIUM_ORE.get()), 1.0F, "undergarden:utherium", output);
		ore(UGItems.REGALIUM_CRYSTAL.get(), ImmutableList.of(UGBlocks.DEPTHROCK_REGALIUM_ORE.get(), UGBlocks.SHIVERSTONE_REGALIUM_ORE.get()), 1.0F, "undergarden:regalium_crystal", output);
		ore(UGItems.ROGDORIUM.get(), ImmutableList.of(UGBlocks.DREADROCK_ROGDORIUM_ORE.get()), 0.7F, "undergarden:rogdorium_crystal", output);

		smeltingRecipeTag(UGItems.CLOGGRUM_NUGGET.get(), UGTags.Items.CLOGGRUM_ITEMS, 0.1F).save(output, name("smelt_cloggrum_item"));
		blastingRecipeTag(UGItems.CLOGGRUM_NUGGET.get(), UGTags.Items.CLOGGRUM_ITEMS, 0.1F).save(output, name("blast_cloggrum_item"));

		smeltingRecipeTag(UGItems.FROSTSTEEL_INGOT.get(), UGTags.Items.FROSTSTEEL_ITEMS, 0.1F).save(output, name("smelt_froststeel_item"));
		blastingRecipeTag(UGItems.FROSTSTEEL_INGOT.get(), UGTags.Items.FROSTSTEEL_ITEMS, 0.1F).save(output, name("blast_froststeel_item"));

		smeltingRecipeTag(UGItems.UTHERIUM_CRYSTAL.get(), UGTags.Items.UTHERIUM_ITEMS, 0.1F).save(output, name("smelt_utherium_item"));
		blastingRecipeTag(UGItems.UTHERIUM_CRYSTAL.get(), UGTags.Items.UTHERIUM_ITEMS, 0.1F).save(output, name("blast_utherium_item"));

		smeltingRecipe(UGItems.DWELLER_STEAK.get(), UGItems.RAW_DWELLER_MEAT.get(), 0.35F).save(output, name("smelt_dweller_meat"));
		smokingRecipe(UGItems.DWELLER_STEAK.get(), UGItems.RAW_DWELLER_MEAT.get(), 0.35F).save(output, name("smoke_dweller_meat"));
		campfireRecipe(UGItems.DWELLER_STEAK.get(), UGItems.RAW_DWELLER_MEAT.get(), 0.35F).save(output, name("campfire_dweller_meat"));

		smeltingRecipe(UGItems.COOKED_GWIBLING.get(), UGItems.RAW_GWIBLING.get(), 0.35F).save(output, name("smelt_gwibling"));
		smokingRecipe(UGItems.COOKED_GWIBLING.get(), UGItems.RAW_GWIBLING.get(), 0.35F).save(output, name("smoke_gwibling"));
		campfireRecipe(UGItems.COOKED_GWIBLING.get(), UGItems.RAW_GWIBLING.get(), 0.35F).save(output, name("campfire_gwibling"));

		smeltingRecipe(UGItems.GLOOMPER_LEG.get(), UGItems.RAW_GLOOMPER_LEG.get(), 0.35F).save(output, name("smelt_gloomper_leg"));
		smokingRecipe(UGItems.GLOOMPER_LEG.get(), UGItems.RAW_GLOOMPER_LEG.get(), 0.35F).save(output, name("smoke_gloomper_leg"));
		campfireRecipe(UGItems.GLOOMPER_LEG.get(), UGItems.RAW_GLOOMPER_LEG.get(), 0.35F).save(output, name("campfire_gloomper_leg"));

		smeltingRecipe(Items.DRIED_KELP, UGItems.GLITTERKELP.get(), 0.1F).save(output, name("smelt_glitterkelp"));
		smokingRecipe(Items.DRIED_KELP, UGItems.GLITTERKELP.get(), 0.1F).save(output, name("smoke_glitterkelp"));
		campfireRecipe(Items.DRIED_KELP, UGItems.GLITTERKELP.get(), 0.1F).save(output, name("campfire_glitterkelp"));

		smeltingRecipe(UGItems.ROASTED_UNDERBEANS.get(), UGItems.UNDERBEANS.get(), 0.35F).save(output, name("smelt_underbeans"));
		smokingRecipe(UGItems.ROASTED_UNDERBEANS.get(), UGItems.UNDERBEANS.get(), 0.35F).save(output, name("smoke_underbeans"));
		campfireRecipe(UGItems.ROASTED_UNDERBEANS.get(), UGItems.UNDERBEANS.get(), 0.35F).save(output, name("campfire_underbeans"));

		depthrockStonecutting(UGBlocks.CHISELED_DEPTHROCK_BRICKS.get()).save(output, name("chiseled_depthrock_bricks_stonecutting"));
		depthrockStonecutting(UGBlocks.DEPTHROCK_BRICK_SLAB.get(), 2).save(output, name("depthrock_brick_slab_stonecutting"));
		depthrockStonecutting(UGBlocks.DEPTHROCK_BRICK_STAIRS.get()).save(output, name("depthrock_brick_stairs_stonecutting"));
		depthrockStonecutting(UGBlocks.DEPTHROCK_BRICK_WALL.get()).save(output, name("depthrock_brick_wall_stonecutting"));
		depthrockStonecutting(UGBlocks.DEPTHROCK_BRICKS.get()).save(output, name("depthrock_bricks_stonecutting"));
		depthrockStonecutting(UGBlocks.DEPTHROCK_SLAB.get(), 2).save(output, name("depthrock_slab_stonecutting"));
		depthrockStonecutting(UGBlocks.DEPTHROCK_STAIRS.get()).save(output, name("depthrock_stairs_stonecutting"));
		depthrockStonecutting(UGBlocks.DEPTHROCK_WALL.get()).save(output, name("depthrock_wall_stonecutting"));
		depthrockStonecutting(UGBlocks.DEPTHROCK_TILES.get()).save(output, name("depthrock_tiles_stonecutting"));
		depthrockStonecutting(UGBlocks.DEPTHROCK_TILE_STAIRS.get()).save(output, name("depthrock_tile_stairs_stonecutting"));
		depthrockStonecutting(UGBlocks.DEPTHROCK_TILE_SLAB.get(), 2).save(output, name("depthrock_tile_slab_stonecutting"));
		depthrockStonecutting(UGBlocks.POLISHED_DEPTHROCK.get()).save(output, name("polished_depthrock_stonecutting"));
		depthrockStonecutting(UGBlocks.POLISHED_DEPTHROCK_SLAB.get(), 2).save(output, name("polished_depthrock_slab_stonecutting"));
		depthrockStonecutting(UGBlocks.POLISHED_DEPTHROCK_STAIRS.get()).save(output, name("polished_depthrock_stairs_stonecutting"));
		depthrockStonecutting(UGBlocks.POLISHED_DEPTHROCK_WALL.get()).save(output, name("polished_depthrock_wall_stonecutting"));
		depthrockStonecutting(UGItems.DEPTHROCK_PEBBLE.get(), 9).save(output, name("depthrock_pebble_stonecutting"));
		depthrockBricksStonecutting(UGBlocks.CHISELED_DEPTHROCK_BRICKS.get()).save(output, name("depthrock_bricks_to_chiseled_depthrock_bricks_stonecutting"));
		depthrockBricksStonecutting(UGBlocks.DEPTHROCK_BRICK_SLAB.get(), 2).save(output, name("depthrock_bricks_to_depthrock_brick_slab_stonecutting"));
		depthrockBricksStonecutting(UGBlocks.DEPTHROCK_BRICK_STAIRS.get()).save(output, name("depthrock_bricks_to_depthrock_brick_stairs_stonecutting"));
		depthrockBricksStonecutting(UGBlocks.DEPTHROCK_BRICK_WALL.get()).save(output, name("depthrock_bricks_to_depthrock_brick_wall_stonecutting"));
		depthrockTilesStonecutting(UGBlocks.DEPTHROCK_TILE_STAIRS.get()).save(output, name("depthrock_tiles_to_depthrock_tile_stairs_stonecutting"));
		depthrockTilesStonecutting(UGBlocks.DEPTHROCK_TILE_SLAB.get(), 2).save(output, name("depthrock_tiles_to_depthrock_tile_slab_stonecutting"));
		polishedDepthrockStonecutting(UGBlocks.POLISHED_DEPTHROCK_SLAB.get(), 2).save(output, name("polished_depthrock_to_polished_depthrock_slab_stonecutting"));
		polishedDepthrockStonecutting(UGBlocks.POLISHED_DEPTHROCK_STAIRS.get()).save(output, name("polished_depthrock_to_polished_depthrock_stairs_stonecutting"));
		polishedDepthrockStonecutting(UGBlocks.POLISHED_DEPTHROCK_WALL.get()).save(output, name("polished_depthrock_to_polished_depthrock_wall_stonecutting"));

		shiverstoneStonecutting(UGBlocks.CHISELED_SHIVERSTONE_BRICKS.get()).save(output, name("chiseled_shiverstone_bricks_stonecutting"));
		shiverstoneStonecutting(UGBlocks.SHIVERSTONE_BRICK_SLAB.get(), 2).save(output, name("shiverstone_brick_slab_stonecutting"));
		shiverstoneStonecutting(UGBlocks.SHIVERSTONE_BRICK_STAIRS.get()).save(output, name("shiverstone_brick_stairs_stonecutting"));
		shiverstoneStonecutting(UGBlocks.SHIVERSTONE_BRICK_WALL.get()).save(output, name("shiverstone_brick_wall_stonecutting"));
		shiverstoneStonecutting(UGBlocks.SHIVERSTONE_BRICKS.get()).save(output, name("shiverstone_bricks_stonecutting"));
		shiverstoneStonecutting(UGBlocks.SHIVERSTONE_SLAB.get(), 2).save(output, name("shiverstone_slab_stonecutting"));
		shiverstoneStonecutting(UGBlocks.SHIVERSTONE_STAIRS.get()).save(output, name("shiverstone_stairs_stonecutting"));
		shiverstoneStonecutting(UGBlocks.SHIVERSTONE_WALL.get()).save(output, name("shiverstone_wall_stonecutting"));
		shiverstoneBricksStonecutting(UGBlocks.CHISELED_SHIVERSTONE_BRICKS.get()).save(output, name("shiverstone_bricks_to_chiseled_shiverstone_bricks_stonecutting"));
		shiverstoneBricksStonecutting(UGBlocks.SHIVERSTONE_BRICK_SLAB.get(), 2).save(output, name("shiverstone_bricks_to_shiverstone_brick_slab_stonecutting"));
		shiverstoneBricksStonecutting(UGBlocks.SHIVERSTONE_BRICK_STAIRS.get()).save(output, name("shiverstone_bricks_to_shiverstone_brick_stairs_stonecutting"));
		shiverstoneBricksStonecutting(UGBlocks.SHIVERSTONE_BRICK_WALL.get()).save(output, name("shiverstone_bricks_to_shiverstone_brick_wall_stonecutting"));

		tremblecrustStonecutting(UGBlocks.CHISELED_TREMBLECRUST_BRICKS.get()).save(output, name("chiseled_tremblecrust_bricks_stonecutting"));
		tremblecrustStonecutting(UGBlocks.TREMBLECRUST_BRICK_SLAB.get(), 2).save(output, name("tremblecrust_brick_slab_stonecutting"));
		tremblecrustStonecutting(UGBlocks.TREMBLECRUST_BRICK_STAIRS.get()).save(output, name("tremblecrust_brick_stairs_stonecutting"));
		tremblecrustStonecutting(UGBlocks.TREMBLECRUST_BRICK_WALL.get()).save(output, name("tremblecrust_brick_wall_stonecutting"));
		tremblecrustStonecutting(UGBlocks.TREMBLECRUST_BRICKS.get()).save(output, name("tremblecrust_bricks_stonecutting"));
		tremblecrustStonecutting(UGBlocks.TREMBLECRUST_SLAB.get(), 2).save(output, name("tremblecrust_slab_stonecutting"));
		tremblecrustStonecutting(UGBlocks.TREMBLECRUST_STAIRS.get()).save(output, name("tremblecrust_stairs_stonecutting"));
		tremblecrustStonecutting(UGBlocks.TREMBLECRUST_WALL.get()).save(output, name("tremblecrust_wall_stonecutting"));
		tremblecrustBricksStonecutting(UGBlocks.CHISELED_TREMBLECRUST_BRICKS.get()).save(output, name("tremblecrust_bricks_to_chiseled_tremblecrust_bricks_stonecutting"));
		tremblecrustBricksStonecutting(UGBlocks.TREMBLECRUST_BRICK_SLAB.get(), 2).save(output, name("tremblecrust_bricks_to_tremblecrust_brick_slab_stonecutting"));
		tremblecrustBricksStonecutting(UGBlocks.TREMBLECRUST_BRICK_STAIRS.get()).save(output, name("tremblecrust_bricks_to_tremblecrust_brick_stairs_stonecutting"));
		tremblecrustBricksStonecutting(UGBlocks.TREMBLECRUST_BRICK_WALL.get()).save(output, name("tremblecrust_bricks_to_tremblecrust_brick_wall_stonecutting"));

		dreadrockStonecutting(UGBlocks.DREADROCK_BRICK_SLAB.get(), 2).save(output, name("dreadrock_brick_slab_stonecutting"));
		dreadrockStonecutting(UGBlocks.DREADROCK_BRICK_STAIRS.get()).save(output, name("dreadrock_brick_stairs_stonecutting"));
		dreadrockStonecutting(UGBlocks.DREADROCK_BRICK_WALL.get()).save(output, name("dreadrock_brick_wall_stonecutting"));
		dreadrockStonecutting(UGBlocks.DREADROCK_BRICKS.get()).save(output, name("dreadrock_bricks_stonecutting"));
		dreadrockStonecutting(UGBlocks.DREADROCK_SLAB.get(), 2).save(output, name("dreadrock_slab_stonecutting"));
		dreadrockStonecutting(UGBlocks.DREADROCK_STAIRS.get()).save(output, name("dreadrock_stairs_stonecutting"));
		dreadrockStonecutting(UGBlocks.DREADROCK_WALL.get()).save(output, name("dreadrock_wall_stonecutting"));
		dreadrockBricksStonecutting(UGBlocks.DREADROCK_BRICK_SLAB.get(), 2).save(output, name("dreadrock_bricks_to_dreadrock_brick_slab_stonecutting"));
		dreadrockBricksStonecutting(UGBlocks.DREADROCK_BRICK_STAIRS.get()).save(output, name("dreadrock_to_dreadrock_stairs_stonecutting"));
		dreadrockBricksStonecutting(UGBlocks.DREADROCK_BRICK_WALL.get()).save(output, name("dreadrock_bricks_to_dreadrock_brick_wall_stonecutting"));

		itemInfusing(Ingredient.of(Tags.Items.ARMORS), InfusingBookCategory.MISC, 0.0F, 200).save(output, name("armor_infusing"));
		infusingPurifying(UGBlocks.DENIZEN_TOTEM, UGBlocks.ANCIENT_ROOT, 1.0F, 200).save(output, name("denizen_totem_infusing"));
		infusingPurifying(UGItems.UTHERIUM_CRYSTAL, UGItems.UTHERIC_CLUSTER, 1.0F, 200).save(output, name("utheric_cluster_to_utherium_crystal_infusing"));
		infusingPurifying(UGBlocks.ROGDORIC_GRONGLET, UGBlocks.GRONGLET, 1.0F, 200).save(output);

		infusingCorrupting(UGItems.CORRUPT_CATALYST, UGItems.CATALYST, 10.0F, 600).save(output, name("corrupt_catalyst_infusing"));
		infusingCorrupting(UGBlocks.UTHERIC_GRONGLET, UGBlocks.GRONGLET, 1.0F, 200).save(output);
	}

	private ResourceLocation name(String name) {
		return ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, name);
	}
}