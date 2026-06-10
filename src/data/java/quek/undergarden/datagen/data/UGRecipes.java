package quek.undergarden.datagen.data;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import quek.undergarden.datagen.helpers.UGRecipeProvider;
import quek.undergarden.recipe.InfusingBookCategory;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGTags;

public class UGRecipes extends UGRecipeProvider {

	public UGRecipes(RecipeOutput output, HolderLookup.Provider provider) {
		super(output, provider);
	}

	@Override
	protected void buildRecipes() {
		HolderGetter<Item> getter = this.registries.lookupOrThrow(Registries.ITEM);

		this.generateForEnabledBlockFamilies(FeatureFlags.REGISTRY.allFlags());

		makePlanks(getter, UGBlocks.SMOGSTEM_PLANKS, UGTags.Items.SMOGSTEM_LOGS).save(this.output);
		makePlanks(getter, UGBlocks.WIGGLEWOOD_PLANKS, UGTags.Items.WIGGLEWOOD_LOGS).save(this.output);
		makePlanks(getter, UGBlocks.GRONGLE_PLANKS, UGTags.Items.GRONGLE_LOGS).save(this.output);
		makePlanks(getter, UGBlocks.ANCIENT_ROOT_PLANKS, UGBlocks.ANCIENT_ROOT).save(this.output);

		makeWood(getter, UGBlocks.SMOGSTEM_WOOD, UGBlocks.SMOGSTEM_LOG).save(this.output);
		makeWood(getter, UGBlocks.STRIPPED_SMOGSTEM_WOOD, UGBlocks.STRIPPED_SMOGSTEM_LOG).save(this.output);
		makeWood(getter, UGBlocks.WIGGLEWOOD_WOOD, UGBlocks.WIGGLEWOOD_LOG).save(this.output);
		makeWood(getter, UGBlocks.STRIPPED_WIGGLEWOOD_WOOD, UGBlocks.STRIPPED_WIGGLEWOOD_LOG).save(this.output);
		makeWood(getter, UGBlocks.GRONGLE_WOOD, UGBlocks.GRONGLE_LOG).save(this.output);
		makeWood(getter, UGBlocks.STRIPPED_GRONGLE_WOOD, UGBlocks.STRIPPED_GRONGLE_LOG).save(this.output);

		makeBoat(getter, UGItems.SMOGSTEM_BOAT, UGBlocks.SMOGSTEM_PLANKS).save(this.output);
		makeBoat(getter, UGItems.WIGGLEWOOD_BOAT, UGBlocks.WIGGLEWOOD_PLANKS).save(this.output);
		makeBoat(getter, UGItems.GRONGLE_BOAT, UGBlocks.GRONGLE_PLANKS).save(this.output);
		makeBoat(getter, UGItems.ANCIENT_ROOT_BOAT, UGBlocks.ANCIENT_ROOT_PLANKS).save(this.output);

		makeChestBoat(getter, UGItems.SMOGSTEM_CHEST_BOAT, UGItems.SMOGSTEM_BOAT).save(this.output);
		makeChestBoat(getter, UGItems.WIGGLEWOOD_CHEST_BOAT, UGItems.WIGGLEWOOD_BOAT).save(this.output);
		makeChestBoat(getter, UGItems.GRONGLE_CHEST_BOAT, UGItems.GRONGLE_BOAT).save(this.output);
		makeChestBoat(getter, UGItems.ANCIENT_ROOT_CHEST_BOAT, UGItems.ANCIENT_ROOT_BOAT).save(this.output);

		makeHangingSign(getter, UGBlocks.SMOGSTEM_HANGING_SIGN, UGBlocks.STRIPPED_SMOGSTEM_LOG).save(this.output);
		makeHangingSign(getter, UGBlocks.WIGGLEWOOD_HANGING_SIGN, UGBlocks.STRIPPED_WIGGLEWOOD_LOG).save(this.output);
		makeHangingSign(getter, UGBlocks.GRONGLE_HANGING_SIGN, UGBlocks.STRIPPED_GRONGLE_LOG).save(this.output);
		makeHangingSign(getter, UGBlocks.ANCIENT_ROOT_HANGING_SIGN, UGBlocks.ANCIENT_ROOT).save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.BUILDING_BLOCKS, UGBlocks.DIRTY_SEDIMENT_STONE_BRICKS, 8)
			.pattern("###")
			.pattern("#D#")
			.pattern("###")
			.define('#', UGBlocks.SEDIMENT_STONE_BRICKS)
			.define('D', UGBlocks.DEEPSOIL)
			.unlockedBy("has_sediment_stone_bricks", has(UGBlocks.SEDIMENT_STONE_BRICKS))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.BUILDING_BLOCKS, UGBlocks.SEDIMENT_STONE)
			.pattern("##")
			.pattern("##")
			.define('#', UGBlocks.SEDIMENT)
			.unlockedBy("has_sediment", has(UGBlocks.SEDIMENT))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.REDSTONE, Blocks.STICKY_PISTON)
			.pattern("G")
			.pattern("P")
			.define('G', UGItems.GOO_BALL.get())
			.define('P', Blocks.PISTON)
			.unlockedBy("has_goo_ball", has(UGItems.GOO_BALL.get()))
			.save(this.output, name("sticky_piston_from_goo_ball"));

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.DECORATIONS, Blocks.STONECUTTER)
			.pattern(" I ")
			.pattern("SSS")
			.define('I', Items.IRON_INGOT)
			.define('S', UGBlocks.DEPTHROCK.get())
			.unlockedBy("has_depthrock", has(UGBlocks.DEPTHROCK.get()))
			.save(this.output, name("stonecutter_from_depthrock"));

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.DECORATIONS, Blocks.STONECUTTER)
			.pattern(" I ")
			.pattern("SSS")
			.define('I', Items.IRON_INGOT)
			.define('S', UGBlocks.SHIVERSTONE.get())
			.unlockedBy("has_shiverstone", has(UGBlocks.SHIVERSTONE.get()))
			.save(this.output, name("stonecutter_from_shiverstone"));

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.DECORATIONS, Blocks.STONECUTTER)
			.pattern(" I ")
			.pattern("SSS")
			.define('I', Items.IRON_INGOT)
			.define('S', UGBlocks.TREMBLECRUST.get())
			.unlockedBy("has_tremblecrust", has(UGBlocks.TREMBLECRUST.get()))
			.save(this.output, name("stonecutter_from_tremblecrust"));

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.MISC, UGItems.UNDERBEAN_STICK.get())
			.requires(Items.STICK)
			.requires(UGItems.UNDERBEANS.get())
			.unlockedBy("has_underbeans", has(UGItems.UNDERBEANS.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.DECORATIONS, UGBlocks.MOGMOSS_RUG.get(), 3)
			.pattern("MM")
			.define('M', UGItems.MOGMOSS.get())
			.unlockedBy("has_mogmoss", has(UGItems.MOGMOSS.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.DECORATIONS, UGBlocks.BLUE_MOGMOSS_RUG.get(), 3)
			.pattern("MM")
			.define('M', UGItems.BLUE_MOGMOSS.get())
			.unlockedBy("has_blue_mogmoss", has(UGItems.BLUE_MOGMOSS.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.DECORATIONS, UGBlocks.DEPTHROCK_BED.get())
			.pattern("MMM")
			.pattern("DDD")
			.define('M', UGItems.MOGMOSS.get())
			.define('D', UGBlocks.DEPTHROCK.get())
			.unlockedBy("has_mogmoss", has(UGItems.MOGMOSS.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.MISC, UGItems.TWISTYTWIG.get(), 4)
			.pattern("P ")
			.pattern(" P")
			.define('P', UGBlocks.WIGGLEWOOD_PLANKS.get())
			.unlockedBy("has_wigglewood_planks", has(UGBlocks.WIGGLEWOOD_PLANKS.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.DECORATIONS, Items.SCAFFOLDING, 6)
			.pattern("STS")
			.pattern("S S")
			.pattern("S S")
			.define('S', Tags.Items.RODS_WOODEN)
			.define('T', UGItems.TWISTYTWIG.get())
			.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
			.save(this.output, name("undergarden_scaffolding"));

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.DECORATIONS, UGBlocks.GLOOM_O_LANTERN.get())
			.pattern("G")
			.pattern("T")
			.define('G', UGBlocks.CARVED_GLOOMGOURD.get())
			.define('T', Items.TORCH)
			.unlockedBy("has_carved_gourd", has(UGBlocks.CARVED_GLOOMGOURD.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.DECORATIONS, UGBlocks.SHARD_O_LANTERN.get())
			.pattern("G")
			.pattern("T")
			.define('G', UGBlocks.CARVED_GLOOMGOURD.get())
			.define('T', UGBlocks.SHARD_TORCH.get())
			.unlockedBy("has_carved_gourd", has(UGBlocks.CARVED_GLOOMGOURD.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.DECORATIONS, UGBlocks.CLOGGRUM_BARS.get(), 16)
			.pattern("CCC")
			.pattern("CCC")
			.define('C', UGItems.CLOGGRUM_INGOT.get())
			.unlockedBy("has_cloggrum_ingot", has(UGItems.CLOGGRUM_INGOT.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.BUILDING_BLOCKS, UGBlocks.COARSE_DEEPSOIL.get(), 4)
			.pattern("DP")
			.pattern("PD")
			.define('D', UGBlocks.DEEPSOIL.get())
			.define('P', UGItems.DEPTHROCK_PEBBLE.get())
			.unlockedBy("has_deepsoil", has(UGBlocks.DEEPSOIL.get()))
			.save(this.output);

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.BUILDING_BLOCKS, UGBlocks.DEEPSOIL.get())
			.requires(UGBlocks.COARSE_DEEPSOIL.get())
			.unlockedBy("has_coarse_deepsoil", has(UGBlocks.COARSE_DEEPSOIL.get()))
			.save(this.output, name("coarse_deepsoil_to_normal"));

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.COMBAT, UGItems.SLINGSHOT.get())
			.pattern("STS")
			.pattern("SSS")
			.pattern(" S ")
			.define('S', Tags.Items.RODS_WOODEN)
			.define('T', UGItems.TWISTYTWIG.get())
			.unlockedBy("has_twistytwig", has(UGItems.TWISTYTWIG.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.COMBAT, UGItems.JAVELIN.get(), 4)
			.pattern("  R")
			.pattern(" S ")
			.pattern("S  ")
			.define('R', UGItems.ROGDORIUM.get())
			.define('S', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_rogdorium", has(UGTags.Items.INGOTS_ROGDORIUM))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.MISC, UGItems.BLISTERBOMB.get())
			.pattern(" T ")
			.pattern("BBB")
			.pattern("BBB")
			.define('T', UGItems.TWISTYTWIG.get())
			.define('B', UGItems.ROTTEN_BLISTERBERRY.get())
			.unlockedBy("has_blisterberry", has(UGItems.ROTTEN_BLISTERBERRY.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.COMBAT, UGItems.CLOGGRUM_SHIELD.get())
			.pattern("CSC")
			.pattern("CCC")
			.pattern(" C ")
			.define('S', ItemTags.PLANKS)
			.define('C', UGItems.CLOGGRUM_INGOT.get())
			.unlockedBy("has_scales", has(UGItems.CLOGGRUM_INGOT.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.DECORATIONS, UGBlocks.SHARD_TORCH.get(), 1)
			.pattern("C")
			.pattern("S")
			.define('C', UGItems.UTHERIC_SHARD.get())
			.define('S', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_shard", has(UGItems.UTHERIC_SHARD.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.DECORATIONS, Items.TORCH, 2)
			.pattern("D")
			.pattern("S")
			.define('D', UGItems.DITCHBULB_PASTE.get())
			.define('S', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_ditchbulb_paste", has(UGItems.DITCHBULB_PASTE.get()))
			.save(this.output, name("torch_ditchbulb_paste"));

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.TOOLS, UGItems.CATALYST.get())
			.pattern("CSC")
			.pattern("SES")
			.pattern("CSC")
			.define('C', Tags.Items.INGOTS_COPPER)
			.define('S', Tags.Items.STONES)
			.define('E', Tags.Items.ENDER_PEARLS)
			.unlockedBy("has_ender_pearl", has(Tags.Items.ENDER_PEARLS))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.TOOLS, UGItems.CRUMBLING_CATALYST.get())
			.pattern("CDC")
			.pattern("DFD")
			.pattern("CDC")
			.define('C', UGTags.Items.INGOTS_CLOGGRUM)
			.define('D', UGBlocks.DEPTHROCK)
			.define('F', UGTags.Items.NUGGETS_FORGOTTEN_METAL)
			.unlockedBy("has_forgotten_nugget", has(UGTags.Items.NUGGETS_FORGOTTEN_METAL))
			.save(this.output);

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.FOOD, UGItems.GLOOMGOURD_PIE.get())
			.requires(Items.SUGAR)
			.requires(UGBlocks.GLOOMGOURD.get())
			.requires(UGItems.GLITTERKELP.get())
			.unlockedBy("has_gloomgourd", has(UGBlocks.GLOOMGOURD.get()))
			.save(this.output);

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.MISC, UGItems.GLOOMGOURD_SEEDS.get(), 4)
			.requires(UGBlocks.GLOOMGOURD.get())
			.unlockedBy("has_gloomgourd", has(UGBlocks.GLOOMGOURD.get()))
			.save(this.output);

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.MISC, Items.BONE_MEAL, 4)
			.requires(UGItems.BRUTE_TUSK.get())
			.unlockedBy("has_tusk", has(UGItems.BRUTE_TUSK.get()))
			.save(this.output, name("tusk_to_bonemeal"));

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.MISC, Items.RED_DYE)
			.requires(UGBlocks.BLOOD_MUSHROOM.get())
			.unlockedBy("has_blood_mushroom", has(UGBlocks.BLOOD_MUSHROOM.get()))
			.save(this.output, name("blood_mushroom_to_dye"));

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.MISC, Items.BLACK_DYE)
			.requires(UGBlocks.INK_MUSHROOM.get())
			.unlockedBy("has_ink_mushroom", has(UGBlocks.INK_MUSHROOM.get()))
			.save(this.output, name("ink_mushroom_to_dye"));

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.MISC, Items.BLUE_DYE)
			.requires(UGBlocks.INDIGO_MUSHROOM.get())
			.unlockedBy("has_indigo_mushroom", has(UGBlocks.INDIGO_MUSHROOM.get()))
			.save(this.output, name("indigo_mushroom_to_dye"));

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.MISC, Items.WHITE_DYE)
			.requires(UGBlocks.VEIL_MUSHROOM.get())
			.unlockedBy("has_veil_mushroom", has(UGBlocks.VEIL_MUSHROOM.get()))
			.save(this.output, name("veil_mushroom_to_dye"));

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.MISC, Items.PINK_DYE)
			.requires(UGBlocks.AMOROUS_BRISTLE.get())
			.unlockedBy("has_amorous_bristle", has(UGBlocks.AMOROUS_BRISTLE.get()))
			.save(this.output, name("amorous_bristle_to_dye"));

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.MISC, Items.LIGHT_BLUE_DYE)
			.requires(UGBlocks.MISERABELL.get())
			.unlockedBy("has_miserabell", has(UGBlocks.MISERABELL.get()))
			.save(this.output, name("miserabell_to_dye"));

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.MISC, Items.YELLOW_DYE)
			.requires(UGBlocks.BUTTERBUNCH.get())
			.unlockedBy("has_butterbunch", has(UGBlocks.BUTTERBUNCH.get()))
			.save(this.output, name("butterbunch_to_dye"));

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.DECORATIONS, UGBlocks.SEDIMENT_GLASS_PANE.get(), 16)
			.pattern("GGG")
			.pattern("GGG")
			.define('G', UGBlocks.SEDIMENT_GLASS.get())
			.unlockedBy("has_sediment_glass", has(UGBlocks.SEDIMENT_GLASS.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.BUILDING_BLOCKS, UGBlocks.CLOGGRUM_TILES.get(), 4)
			.pattern("CC")
			.pattern("CC")
			.define('C', UGItems.CLOGGRUM_INGOT.get())
			.unlockedBy("has_cloggrum", has(UGItems.CLOGGRUM_INGOT.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.BUILDING_BLOCKS, UGBlocks.CLOGGRUM_PILLAR.get(), 3)
			.pattern("C")
			.pattern("C")
			.pattern("C")
			.define('C', UGItems.CLOGGRUM_INGOT.get())
			.unlockedBy("has_cloggrum", has(UGItems.CLOGGRUM_INGOT.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.BUILDING_BLOCKS, UGBlocks.CLOGGRUM_LADDER.get(), 3)
			.pattern("C C")
			.pattern("CCC")
			.pattern("C C")
			.define('C', UGItems.CLOGGRUM_INGOT.get())
			.unlockedBy("has_cloggrum", has(UGItems.CLOGGRUM_INGOT.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.BUILDING_BLOCKS, UGBlocks.CLOGGRUM_GRATE.get(), 4)
			.pattern(" C ")
			.pattern("C C")
			.pattern(" C ")
			.define('C', UGItems.CLOGGRUM_INGOT.get())
			.unlockedBy("has_cloggrum", has(UGItems.CLOGGRUM_INGOT.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.DECORATIONS, UGBlocks.CLOGGRUM_LANTERN.get())
			.pattern("NNN")
			.pattern("NTN")
			.pattern("NNN")
			.define('N', UGItems.CLOGGRUM_NUGGET.get())
			.define('T', Items.TORCH)
			.unlockedBy("has_cloggrum_ingot", has(UGItems.CLOGGRUM_INGOT.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.BUILDING_BLOCKS, UGBlocks.BOOMGOURD.get())
			.pattern("BBB")
			.pattern("BGB")
			.pattern("BBB")
			.define('B', UGItems.BLISTERBOMB.get())
			.define('G', UGBlocks.GLOOMGOURD.get())
			.unlockedBy("has_gloomgourd", has(UGBlocks.GLOOMGOURD.get()))
			.unlockedBy("has_blisterbomb", has(UGItems.BLISTERBOMB.get()))
			.save(this.output);

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.MISC, UGItems.DITCHBULB_PASTE.get())
			.requires(UGItems.DITCHBULB.get())
			.unlockedBy("has_ditchbulb", has(UGItems.DITCHBULB.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.TOOLS, UGItems.CLOGGRUM_BUCKET.get())
			.pattern("C C")
			.pattern(" C ")
			.define('C', UGTags.Items.INGOTS_CLOGGRUM)
			.unlockedBy("has_cloggrum_ingot", has(UGItems.CLOGGRUM_INGOT.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.MISC, Items.PAPER, 3)
			.pattern("TTT")
			.define('T', UGItems.THORNREED)
			.unlockedBy("has_thornreed", has(UGItems.THORNREED.get()))
			.save(this.output, name("paper_from_thornreed"));

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.MISC, Items.SUGAR)
			.requires(UGItems.THORNREED)
			.group("sugar")
			.unlockedBy("has_thornreed", has(UGItems.THORNREED.get()))
			.save(this.output, name("sugar_from_thornreed"));

		makeIngotToBlock(getter, UGBlocks.CLOGGRUM_BLOCK, UGItems.CLOGGRUM_INGOT).save(this.output);
		makeIngotToBlock(getter, UGBlocks.FROSTSTEEL_BLOCK, UGItems.FROSTSTEEL_INGOT).save(this.output);
		makeIngotToBlock(getter, UGBlocks.UTHERIUM_BLOCK, UGItems.UTHERIUM_CRYSTAL).save(this.output);
		makeIngotToBlock(getter, UGBlocks.REGALIUM_BLOCK, UGItems.REGALIUM_CRYSTAL).save(this.output);
		makeIngotToBlock(getter, UGBlocks.ROGDORIUM_BLOCK, UGItems.ROGDORIUM).save(this.output);
		makeIngotToBlock(getter, UGBlocks.FORGOTTEN_BLOCK, UGItems.FORGOTTEN_INGOT).save(this.output);
		makeIngotToBlock(getter, UGBlocks.DEPTHROCK, UGItems.DEPTHROCK_PEBBLE).save(this.output, name("pebbles_to_depthrock"));
		makeIngotToBlock(getter, UGBlocks.GOO_BLOCK, UGItems.GOO_BALL).save(this.output);

		makeBlockToIngot(getter, UGItems.CLOGGRUM_INGOT, UGBlocks.CLOGGRUM_BLOCK).save(this.output, name("cloggrum_block_to_ingot"));
		makeBlockToIngot(getter, UGItems.FROSTSTEEL_INGOT, UGBlocks.FROSTSTEEL_BLOCK).save(this.output, name("froststeel_block_to_ingot"));
		makeBlockToIngot(getter, UGItems.UTHERIUM_CRYSTAL, UGBlocks.UTHERIUM_BLOCK).save(this.output, name("utherium_block_to_crystal"));
		makeBlockToIngot(getter, UGItems.REGALIUM_CRYSTAL, UGBlocks.REGALIUM_BLOCK).save(this.output, name("regalium_block_to_crystal"));
		makeBlockToIngot(getter, UGItems.ROGDORIUM, UGBlocks.ROGDORIUM_BLOCK).save(this.output, name("rogdorium_block_to_crystal"));
		makeBlockToIngot(getter, UGItems.FORGOTTEN_INGOT, UGBlocks.FORGOTTEN_BLOCK).save(this.output, name("forgotten_block_to_ingot"));
		makeBlockToIngot(getter, UGItems.GOO_BALL, UGBlocks.GOO_BLOCK).save(this.output, name("goo_block_to_ball"));

		makeIngotToNugget(getter, UGItems.CLOGGRUM_NUGGET, UGItems.CLOGGRUM_INGOT).save(this.output, name("cloggrum_ingot_to_nugget"));
		makeIngotToNugget(getter, UGItems.FROSTSTEEL_NUGGET, UGItems.FROSTSTEEL_INGOT).save(this.output, name("froststeel_ingot_to_nugget"));
		makeIngotToNugget(getter, UGItems.ROGDORIUM_NUGGET, UGItems.ROGDORIUM).save(this.output, name("rogdorium_to_nugget"));
		makeIngotToNugget(getter, UGItems.FORGOTTEN_NUGGET, UGItems.FORGOTTEN_INGOT).save(this.output, name("forgotten_ingot_to_nugget"));

		makeNuggetToIngot(getter, UGItems.CLOGGRUM_INGOT, UGItems.CLOGGRUM_NUGGET).save(this.output, name("cloggrum_nugget_to_ingot"));
		makeNuggetToIngot(getter, UGItems.FROSTSTEEL_INGOT, UGItems.FROSTSTEEL_NUGGET).save(this.output, name("froststeel_nugget_to_ingot"));
		makeNuggetToIngot(getter, UGItems.ROGDORIUM, UGItems.ROGDORIUM_NUGGET).save(this.output, name("rogdorium_nugget_to_ingot"));
		makeNuggetToIngot(getter, UGItems.FORGOTTEN_INGOT, UGItems.FORGOTTEN_NUGGET).save(this.output, name("forgotten_nugget_to_ingot"));
		makeNuggetToIngot(getter, UGItems.UTHERIC_CLUSTER, UGItems.UTHERIC_SHARD).save(this.output);

		makeIngotToBlock(getter, UGBlocks.RAW_CLOGGRUM_BLOCK, UGItems.RAW_CLOGGRUM).save(this.output);
		makeIngotToBlock(getter, UGBlocks.RAW_FROSTSTEEL_BLOCK, UGItems.RAW_FROSTSTEEL).save(this.output);

		makeBlockToIngot(getter, UGItems.RAW_CLOGGRUM, UGBlocks.RAW_CLOGGRUM_BLOCK).save(this.output, name("raw_cloggrum_from_block"));
		makeBlockToIngot(getter, UGItems.RAW_FROSTSTEEL, UGBlocks.RAW_FROSTSTEEL_BLOCK).save(this.output, name("raw_froststeel_from_block"));

		makeSword(getter, UGItems.CLOGGRUM_SWORD, UGItems.CLOGGRUM_INGOT).save(this.output);
		makeSword(getter, UGItems.FROSTSTEEL_SWORD, UGItems.FROSTSTEEL_INGOT).save(this.output);
		makeSword(getter, UGItems.UTHERIUM_SWORD, UGItems.UTHERIUM_CRYSTAL).save(this.output);

		makePickaxe(getter, UGItems.CLOGGRUM_PICKAXE, UGItems.CLOGGRUM_INGOT).save(this.output);
		makePickaxe(getter, UGItems.FROSTSTEEL_PICKAXE, UGItems.FROSTSTEEL_INGOT).save(this.output);
		makePickaxe(getter, UGItems.UTHERIUM_PICKAXE, UGItems.UTHERIUM_CRYSTAL).save(this.output);

		makeAxe(getter, UGItems.CLOGGRUM_AXE, UGItems.CLOGGRUM_INGOT).save(this.output);
		makeAxe(getter, UGItems.FROSTSTEEL_AXE, UGItems.FROSTSTEEL_INGOT).save(this.output);
		makeAxe(getter, UGItems.UTHERIUM_AXE, UGItems.UTHERIUM_CRYSTAL).save(this.output);

		makeShovel(getter, UGItems.CLOGGRUM_SHOVEL, UGItems.CLOGGRUM_INGOT).save(this.output);
		makeShovel(getter, UGItems.FROSTSTEEL_SHOVEL, UGItems.FROSTSTEEL_INGOT).save(this.output);
		makeShovel(getter, UGItems.UTHERIUM_SHOVEL, UGItems.UTHERIUM_CRYSTAL).save(this.output);

		makeHoe(getter, UGItems.CLOGGRUM_HOE, UGItems.CLOGGRUM_INGOT).save(this.output);
		makeHoe(getter, UGItems.FROSTSTEEL_HOE, UGItems.FROSTSTEEL_INGOT).save(this.output);
		makeHoe(getter, UGItems.UTHERIUM_HOE, UGItems.UTHERIUM_CRYSTAL).save(this.output);

		makeSpear(getter, UGItems.CLOGGRUM_SPEAR, UGItems.CLOGGRUM_INGOT).save(this.output);
		makeSpear(getter, UGItems.FROSTSTEEL_SPEAR, UGItems.FROSTSTEEL_INGOT).save(this.output);
		makeSpear(getter, UGItems.UTHERIUM_SPEAR, UGItems.UTHERIUM_CRYSTAL).save(this.output);

		makeHelmet(getter, UGItems.CLOGGRUM_HELMET, UGItems.CLOGGRUM_INGOT).save(this.output);
		makeChestplate(getter, UGItems.CLOGGRUM_CHESTPLATE, UGItems.CLOGGRUM_INGOT).save(this.output);
		makeLeggings(getter, UGItems.CLOGGRUM_LEGGINGS, UGItems.CLOGGRUM_INGOT).save(this.output);
		makeBoots(getter, UGItems.CLOGGRUM_BOOTS, UGItems.CLOGGRUM_INGOT).save(this.output);

		makeHelmet(getter, UGItems.FROSTSTEEL_HELMET, UGItems.FROSTSTEEL_INGOT).save(this.output);
		makeChestplate(getter, UGItems.FROSTSTEEL_CHESTPLATE, UGItems.FROSTSTEEL_INGOT).save(this.output);
		makeLeggings(getter, UGItems.FROSTSTEEL_LEGGINGS, UGItems.FROSTSTEEL_INGOT).save(this.output);
		makeBoots(getter, UGItems.FROSTSTEEL_BOOTS, UGItems.FROSTSTEEL_INGOT).save(this.output);

		makeHelmet(getter, UGItems.UTHERIUM_HELMET, UGItems.UTHERIUM_CRYSTAL).save(this.output);
		makeChestplate(getter, UGItems.UTHERIUM_CHESTPLATE, UGItems.UTHERIUM_CRYSTAL).save(this.output);
		makeLeggings(getter, UGItems.UTHERIUM_LEGGINGS, UGItems.UTHERIUM_CRYSTAL).save(this.output);
		makeBoots(getter, UGItems.UTHERIUM_BOOTS, UGItems.UTHERIUM_CRYSTAL).save(this.output);

		makeStew(getter, UGItems.BLOODY_STEW, UGBlocks.BLOOD_MUSHROOM).save(this.output);
		makeStew(getter, UGItems.INKY_STEW, UGBlocks.INK_MUSHROOM).save(this.output);
		makeStew(getter, UGItems.INDIGO_STEW, UGBlocks.INDIGO_MUSHROOM).save(this.output);
		makeStew(getter, UGItems.VEILED_STEW, UGBlocks.VEIL_MUSHROOM).save(this.output);

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.FOOD, UGItems.SLOP_BOWL.get())
			.requires(Items.BOWL)
			.requires(UGItems.UNDERBEANS.get(), 2)
			.requires(UGItems.MOGMOSS.get(), 2)
			.unlockedBy("has_underbeans", has(UGItems.UNDERBEANS.get()))
			.unlockedBy("has_mogmoss", has(UGItems.MOGMOSS.get()))
			.save(this.output);

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.FOOD, UGItems.SLOP_BOWL.get())
			.requires(Items.BOWL)
			.requires(UGItems.UNDERBEANS.get(), 2)
			.requires(UGItems.BLUE_MOGMOSS.get(), 2)
			.unlockedBy("has_underbeans", has(UGItems.UNDERBEANS.get()))
			.unlockedBy("has_blue_mogmoss", has(UGItems.BLUE_MOGMOSS.get()))
			.save(this.output, name("slop_bowl_blue_moss"));

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.MISC, UGItems.FORGOTTEN_UPGRADE_TEMPLATE.get(), 2)
			.pattern("DTD")
			.pattern("DRD")
			.pattern("DDD")
			.define('D', Tags.Items.GEMS_DIAMOND)
			.define('R', UGBlocks.DEPTHROCK.get())
			.define('T', UGItems.FORGOTTEN_UPGRADE_TEMPLATE.get())
			.unlockedBy("has_template", has(UGItems.FORGOTTEN_UPGRADE_TEMPLATE.get()))
			.save(this.output);

		ShapedRecipeBuilder.shaped(getter, RecipeCategory.DECORATIONS, UGBlocks.INFUSER.get())
			.pattern("UDR")
			.pattern("D D")
			.pattern("D D")
			.define('D', UGBlocks.DREADROCK.get())
			.define('R', UGTags.Items.INGOTS_ROGDORIUM)
			.define('U', UGItems.UTHERIC_CLUSTER)
			.unlockedBy("has_dreadrock", has(UGBlocks.DREADROCK.get()))
			.save(this.output);

		smithingForgotten(UGItems.CLOGGRUM_SWORD, UGItems.FORGOTTEN_SWORD).save(this.output, name("forgotten_sword_smithing"));
		smithingForgotten(UGItems.CLOGGRUM_PICKAXE, UGItems.FORGOTTEN_PICKAXE).save(this.output, name("forgotten_pickaxe_smithing"));
		smithingForgotten(UGItems.CLOGGRUM_AXE, UGItems.FORGOTTEN_AXE).save(this.output, name("forgotten_axe_smithing"));
		smithingForgotten(UGItems.CLOGGRUM_SHOVEL, UGItems.FORGOTTEN_SHOVEL).save(this.output, name("forgotten_shovel_smithing"));
		smithingForgotten(UGItems.CLOGGRUM_HOE, UGItems.FORGOTTEN_HOE).save(this.output, name("forgotten_hoe_smithing"));
		smithingForgotten(UGItems.CLOGGRUM_BATTLEAXE, UGItems.FORGOTTEN_BATTLEAXE).save(this.output, name("forgotten_battleaxe_smithing"));
		smithingForgotten(UGItems.CLOGGRUM_SPEAR, UGItems.FORGOTTEN_SPEAR).save(this.output, name("forgotten_spear_smithing"));

		smeltingRecipe(UGBlocks.SEDIMENT_GLASS.get(), UGBlocks.SEDIMENT.get(), CookingBookCategory.BLOCKS, 0.1F).save(this.output);

		ore(Items.COAL, ImmutableList.of(UGBlocks.DEPTHROCK_COAL_ORE.get(), UGBlocks.SHIVERSTONE_COAL_ORE.get()), 0.1F, "coal", this.output);
		ore(Items.IRON_INGOT, ImmutableList.of(UGBlocks.DEPTHROCK_IRON_ORE.get(), UGBlocks.SHIVERSTONE_IRON_ORE.get()), 0.7F, "iron_ingot", this.output);
		ore(Items.GOLD_INGOT, ImmutableList.of(UGBlocks.DEPTHROCK_GOLD_ORE.get()), 1.0F, "gold_ingot", this.output);
		ore(Items.DIAMOND, ImmutableList.of(UGBlocks.DEPTHROCK_DIAMOND_ORE.get(), UGBlocks.SHIVERSTONE_DIAMOND_ORE.get()), 1.0F, "diamond", this.output);
		ore(UGItems.CLOGGRUM_INGOT.get(), ImmutableList.of(UGItems.RAW_CLOGGRUM.get(), UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get(), UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get()), 0.7F, "undergarden:cloggrum_ingot", this.output);
		ore(UGItems.FROSTSTEEL_INGOT.get(), ImmutableList.of(UGItems.RAW_FROSTSTEEL.get(), UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get()), 0.7F, "undergarden:froststeel_ingot", this.output);
		ore(UGItems.UTHERIC_CLUSTER.get(), ImmutableList.of(UGBlocks.DEPTHROCK_UTHERIUM_ORE.get(), UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get(), UGBlocks.DREADROCK_UTHERIUM_ORE.get()), 1.0F, "undergarden:utherium", this.output);
		ore(UGItems.REGALIUM_CRYSTAL.get(), ImmutableList.of(UGBlocks.DEPTHROCK_REGALIUM_ORE.get(), UGBlocks.SHIVERSTONE_REGALIUM_ORE.get()), 1.0F, "undergarden:regalium_crystal", this.output);
		ore(UGItems.ROGDORIUM.get(), ImmutableList.of(UGBlocks.DREADROCK_ROGDORIUM_ORE.get()), 0.7F, "undergarden:rogdorium_crystal", this.output);

		smeltingRecipeTag(getter, UGItems.CLOGGRUM_NUGGET.get(), UGTags.Items.CLOGGRUM_ITEMS, CookingBookCategory.MISC, 0.1F).save(this.output, name("smelt_cloggrum_item"));
		blastingRecipeTag(getter, UGItems.CLOGGRUM_NUGGET.get(), UGTags.Items.CLOGGRUM_ITEMS, CookingBookCategory.MISC, 0.1F).save(this.output, name("blast_cloggrum_item"));

		smeltingRecipeTag(getter, UGItems.FROSTSTEEL_INGOT.get(), UGTags.Items.FROSTSTEEL_ITEMS, CookingBookCategory.MISC, 0.1F).save(this.output, name("smelt_froststeel_item"));
		blastingRecipeTag(getter, UGItems.FROSTSTEEL_INGOT.get(), UGTags.Items.FROSTSTEEL_ITEMS, CookingBookCategory.MISC, 0.1F).save(this.output, name("blast_froststeel_item"));

		smeltingRecipeTag(getter, UGItems.UTHERIUM_CRYSTAL.get(), UGTags.Items.UTHERIUM_ITEMS, CookingBookCategory.MISC, 0.1F).save(this.output, name("smelt_utherium_item"));
		blastingRecipeTag(getter, UGItems.UTHERIUM_CRYSTAL.get(), UGTags.Items.UTHERIUM_ITEMS, CookingBookCategory.MISC, 0.1F).save(this.output, name("blast_utherium_item"));

		smeltingRecipe(UGItems.DWELLER_STEAK.get(), UGItems.RAW_DWELLER_MEAT.get(), CookingBookCategory.FOOD, 0.35F).save(this.output, name("smelt_dweller_meat"));
		smokingRecipe(UGItems.DWELLER_STEAK.get(), UGItems.RAW_DWELLER_MEAT.get(), 0.35F).save(this.output, name("smoke_dweller_meat"));
		campfireRecipe(UGItems.DWELLER_STEAK.get(), UGItems.RAW_DWELLER_MEAT.get(), 0.35F).save(this.output, name("campfire_dweller_meat"));

		smeltingRecipe(UGItems.COOKED_GWIBLING.get(), UGItems.RAW_GWIBLING.get(), CookingBookCategory.FOOD, 0.35F).save(this.output, name("smelt_gwibling"));
		smokingRecipe(UGItems.COOKED_GWIBLING.get(), UGItems.RAW_GWIBLING.get(), 0.35F).save(this.output, name("smoke_gwibling"));
		campfireRecipe(UGItems.COOKED_GWIBLING.get(), UGItems.RAW_GWIBLING.get(), 0.35F).save(this.output, name("campfire_gwibling"));

		smeltingRecipe(UGItems.GLOOMPER_LEG.get(), UGItems.RAW_GLOOMPER_LEG.get(), CookingBookCategory.FOOD, 0.35F).save(this.output, name("smelt_gloomper_leg"));
		smokingRecipe(UGItems.GLOOMPER_LEG.get(), UGItems.RAW_GLOOMPER_LEG.get(), 0.35F).save(this.output, name("smoke_gloomper_leg"));
		campfireRecipe(UGItems.GLOOMPER_LEG.get(), UGItems.RAW_GLOOMPER_LEG.get(), 0.35F).save(this.output, name("campfire_gloomper_leg"));

		smeltingRecipe(Items.DRIED_KELP, UGItems.GLITTERKELP.get(), CookingBookCategory.FOOD, 0.1F).save(this.output, name("smelt_glitterkelp"));
		smokingRecipe(Items.DRIED_KELP, UGItems.GLITTERKELP.get(), 0.1F).save(this.output, name("smoke_glitterkelp"));
		campfireRecipe(Items.DRIED_KELP, UGItems.GLITTERKELP.get(), 0.1F).save(this.output, name("campfire_glitterkelp"));

		smeltingRecipe(UGItems.ROASTED_UNDERBEANS.get(), UGItems.UNDERBEANS.get(), CookingBookCategory.FOOD, 0.35F).save(this.output, name("smelt_underbeans"));
		smokingRecipe(UGItems.ROASTED_UNDERBEANS.get(), UGItems.UNDERBEANS.get(), 0.35F).save(this.output, name("smoke_underbeans"));
		campfireRecipe(UGItems.ROASTED_UNDERBEANS.get(), UGItems.UNDERBEANS.get(), 0.35F).save(this.output, name("campfire_underbeans"));

		smeltingRecipe(UGItems.COOKED_UNDERGAR_FILLET, UGItems.RAW_UNDERGAR_FILLET, CookingBookCategory.FOOD, 0.35F).save(this.output, name("smelt_raw_undergar_fillet"));
		smokingRecipe(UGItems.COOKED_UNDERGAR_FILLET, UGItems.RAW_UNDERGAR_FILLET, 0.35F).save(this.output, name("smoke_raw_undergar_fillet"));
		campfireRecipe(UGItems.COOKED_UNDERGAR_FILLET, UGItems.RAW_UNDERGAR_FILLET, 0.35F).save(this.output, name("campfire_raw_undergar_fillet"));

		smeltingRecipe(UGBlocks.SMOOTH_SEDIMENT_STONE.get(), UGBlocks.SEDIMENT_STONE.get(), CookingBookCategory.BLOCKS, 0.1F).save(this.output);

		stonecutting(UGBlocks.DEPTHROCK, UGItems.DEPTHROCK_PEBBLE.get(), 9).save(this.output, name("depthrock_pebbles_from_depthrock_stonecutting"));

		itemInfusing(Ingredient.of(getter.getOrThrow(Tags.Items.ARMORS)), InfusingBookCategory.MISC, 0.0F, 200).save(this.output, name("armor_infusing"));
		infusingPurifying(UGBlocks.DENIZEN_TOTEM, UGBlocks.ANCIENT_ROOT, 1.0F, 200).save(this.output, name("denizen_totem_infusing"));
		infusingPurifying(UGItems.UTHERIUM_CRYSTAL, UGItems.UTHERIC_CLUSTER, 1.0F, 200).save(this.output, name("utheric_cluster_to_utherium_crystal_infusing"));
		infusingPurifying(UGBlocks.ROGDORIC_GRONGLET, UGBlocks.GRONGLET, 1.0F, 200).save(this.output);

		infusingCorrupting(UGBlocks.UTHERIC_GRONGLET, UGBlocks.GRONGLET, 1.0F, 200).save(this.output);
	}
}