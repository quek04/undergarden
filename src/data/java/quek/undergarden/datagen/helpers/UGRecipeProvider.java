package quek.undergarden.datagen.helpers;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.neoforged.neoforge.common.Tags;
import quek.undergarden.Undergarden;
import quek.undergarden.datagen.assets.UGBlockFamilies;
import quek.undergarden.datagen.helpers.builder.InfusingRecipeBuilder;
import quek.undergarden.datagen.helpers.builder.ItemInfusingRecipeBuilder;
import quek.undergarden.recipe.InfusingBookCategory;
import quek.undergarden.recipe.InfusingRecipe;
import quek.undergarden.registry.UGItems;

import java.util.List;
import java.util.function.Supplier;

public abstract class UGRecipeProvider extends RecipeProvider {

	public UGRecipeProvider(RecipeOutput output, HolderLookup.Provider provider) {
		super(provider, output);
	}

	@Override
	protected void generateForEnabledBlockFamilies(FeatureFlagSet flagSet) {
		UGBlockFamilies.getAllFamilies().forEach(blockFamily -> this.generateRecipes(blockFamily, flagSet));
	}

	public ShapelessRecipeBuilder makePlanks(HolderGetter<Item> getter, Supplier<? extends Block> plankOut, TagKey<Item> logIn) {
		return ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.BUILDING_BLOCKS, plankOut.get(), 4)
			.requires(logIn)
			.group("planks")
			.unlockedBy("has_log", has(logIn));
	}

	public ShapelessRecipeBuilder makePlanks(HolderGetter<Item> getter, Supplier<? extends Block> plankOut, Supplier<? extends Block> logIn) {
		return ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.BUILDING_BLOCKS, plankOut.get(), 4)
			.requires(logIn.get())
			.group("planks")
			.unlockedBy("has_log", has(logIn.get()));
	}

	public ShapedRecipeBuilder makeWood(HolderGetter<Item> getter, Supplier<? extends Block> woodOut, Supplier<? extends Block> logIn) {
		return ShapedRecipeBuilder.shaped(getter, RecipeCategory.BUILDING_BLOCKS, woodOut.get(), 3)
			.pattern("MM")
			.pattern("MM")
			.define('M', logIn.get())
			.unlockedBy("has_" + BuiltInRegistries.BLOCK.getKey(logIn.get()).getPath(), has(logIn.get()));
	}

	public ShapedRecipeBuilder makeIngotToBlock(HolderGetter<Item> getter, Supplier<? extends Block> blockOut, Supplier<? extends Item> ingotIn) {
		return ShapedRecipeBuilder.shaped(getter, RecipeCategory.BUILDING_BLOCKS, blockOut.get())
			.pattern("###")
			.pattern("###")
			.pattern("###")
			.define('#', ingotIn.get())
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(ingotIn.get()).getPath(), has(ingotIn.get()));
	}

	public ShapelessRecipeBuilder makeBlockToIngot(HolderGetter<Item> getter, Supplier<? extends Item> ingotOut, Supplier<? extends Block> blockIn) {
		return ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.MISC, ingotOut.get(), 9)
			.requires(blockIn.get())
			.unlockedBy("has_" + BuiltInRegistries.BLOCK.getKey(blockIn.get()).getPath(), has(blockIn.get()));
	}

	public ShapedRecipeBuilder makeNuggetToIngot(HolderGetter<Item> getter, Supplier<? extends Item> ingotOut, Supplier<? extends Item> nuggetIn) {
		return ShapedRecipeBuilder.shaped(getter, RecipeCategory.MISC, ingotOut.get(), 1)
			.pattern("NNN")
			.pattern("NNN")
			.pattern("NNN")
			.define('N', nuggetIn.get())
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(nuggetIn.get()).getPath(), has(nuggetIn.get()));
	}

	public ShapelessRecipeBuilder makeIngotToNugget(HolderGetter<Item> getter, Supplier<? extends Item> nuggetOut, Supplier<? extends Item> ingotIn) {
		return ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.MISC, nuggetOut.get(), 9)
			.requires(ingotIn.get())
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(ingotIn.get()).getPath(), has(ingotIn.get()));
	}

	public ShapedRecipeBuilder makeSword(HolderGetter<Item> getter, Supplier<? extends Item> swordOut, Supplier<? extends Item> materialIn) {
		return ShapedRecipeBuilder.shaped(getter, RecipeCategory.COMBAT, swordOut.get())
			.pattern("#")
			.pattern("#")
			.pattern("/")
			.define('#', materialIn.get())
			.define('/', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(materialIn.get()).getPath(), has(materialIn.get()));
	}

	public ShapedRecipeBuilder makePickaxe(HolderGetter<Item> getter, Supplier<? extends Item> pickaxeOut, Supplier<? extends Item> materialIn) {
		return ShapedRecipeBuilder.shaped(getter, RecipeCategory.TOOLS, pickaxeOut.get())
			.pattern("###")
			.pattern(" / ")
			.pattern(" / ")
			.define('#', materialIn.get())
			.define('/', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(materialIn.get()).getPath(), has(materialIn.get()));
	}

	public ShapedRecipeBuilder makeAxe(HolderGetter<Item> getter, Supplier<? extends Item> axeOut, Supplier<? extends Item> materialIn) {
		return ShapedRecipeBuilder.shaped(getter, RecipeCategory.TOOLS, axeOut.get())
			.pattern("##")
			.pattern("#/")
			.pattern(" /")
			.define('#', materialIn.get())
			.define('/', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(materialIn.get()).getPath(), has(materialIn.get()));
	}

	public ShapedRecipeBuilder makeShovel(HolderGetter<Item> getter, Supplier<? extends Item> shovelOut, Supplier<? extends Item> materialIn) {
		return ShapedRecipeBuilder.shaped(getter, RecipeCategory.TOOLS, shovelOut.get())
			.pattern("#")
			.pattern("/")
			.pattern("/")
			.define('#', materialIn.get())
			.define('/', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(materialIn.get()).getPath(), has(materialIn.get()));
	}

	public ShapedRecipeBuilder makeHoe(HolderGetter<Item> getter, Supplier<? extends Item> hoeOut, Supplier<? extends Item> materialIn) {
		return ShapedRecipeBuilder.shaped(getter, RecipeCategory.TOOLS, hoeOut.get())
			.pattern("##")
			.pattern(" /")
			.pattern(" /")
			.define('#', materialIn.get())
			.define('/', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(materialIn.get()).getPath(), has(materialIn.get()));
	}

	public ShapedRecipeBuilder makeSpear(HolderGetter<Item> getter, Supplier<? extends Item> spearOut, Supplier<? extends Item> materialIn) {
		return ShapedRecipeBuilder.shaped(getter, RecipeCategory.COMBAT, spearOut.get())
			.pattern("  X")
			.pattern(" / ")
			.pattern("/  ")
			.define('X', materialIn.get())
			.define('/', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(materialIn.get()).getPath(), has(materialIn.get()));
	}

	public ShapedRecipeBuilder makeHelmet(HolderGetter<Item> getter, Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
		return ShapedRecipeBuilder.shaped(getter, RecipeCategory.COMBAT, helmetOut.get())
			.pattern("MMM")
			.pattern("M M")
			.define('M', materialIn.get())
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(materialIn.get()).getPath(), has(materialIn.get()));
	}

	public ShapedRecipeBuilder makeChestplate(HolderGetter<Item> getter, Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
		return ShapedRecipeBuilder.shaped(getter, RecipeCategory.COMBAT, helmetOut.get())
			.pattern("M M")
			.pattern("MMM")
			.pattern("MMM")
			.define('M', materialIn.get())
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(materialIn.get()).getPath(), has(materialIn.get()));
	}

	public ShapedRecipeBuilder makeLeggings(HolderGetter<Item> getter, Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
		return ShapedRecipeBuilder.shaped(getter, RecipeCategory.COMBAT, helmetOut.get())
			.pattern("MMM")
			.pattern("M M")
			.pattern("M M")
			.define('M', materialIn.get())
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(materialIn.get()).getPath(), has(materialIn.get()));
	}

	public ShapedRecipeBuilder makeBoots(HolderGetter<Item> getter, Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
		return ShapedRecipeBuilder.shaped(getter, RecipeCategory.COMBAT, helmetOut.get())
			.pattern("M M")
			.pattern("M M")
			.define('M', materialIn.get())
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(materialIn.get()).getPath(), has(materialIn.get()));
	}

	public ShapelessRecipeBuilder makeStew(HolderGetter<Item> getter, Supplier<? extends Item> stewOut, Supplier<? extends Block> mushroomIn) {
		return ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.FOOD, stewOut.get())
			.requires(Items.BOWL)
			.requires(mushroomIn.get(), 3)
			.unlockedBy("has_" + BuiltInRegistries.BLOCK.getKey(mushroomIn.get()).getPath(), has(mushroomIn.get()));
	}

	public ShapedRecipeBuilder makeBoat(HolderGetter<Item> getter, Supplier<? extends Item> boatOut, Supplier<? extends Block> planksIn) {
		return ShapedRecipeBuilder.shaped(getter, RecipeCategory.TRANSPORTATION, boatOut.get())
			.pattern("P P")
			.pattern("PPP")
			.define('P', planksIn.get())
			.group("boat")
			.unlockedBy("in_water", insideOf(Blocks.WATER));
	}

	public ShapelessRecipeBuilder makeChestBoat(HolderGetter<Item> getter, Supplier<? extends Item> chestBoatOut, Supplier<? extends Item> boatIn) {
		return ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.TRANSPORTATION, chestBoatOut.get())
			.requires(boatIn.get())
			.requires(Tags.Items.CHESTS_WOODEN)
			.group("chest_boat")
			.unlockedBy("has_boat", has(ItemTags.BOATS));
	}

	protected ShapedRecipeBuilder makeHangingSign(HolderGetter<Item> getter, Supplier<? extends CeilingHangingSignBlock> result, Supplier<? extends Block> log) {
		return ShapedRecipeBuilder.shaped(getter, RecipeCategory.DECORATIONS, result.get(), 6)
			.pattern("| |")
			.pattern("###")
			.pattern("###")
			.define('#', log.get())
			.define('|', Items.IRON_CHAIN)
			.unlockedBy("has_" + BuiltInRegistries.BLOCK.getKey(log.get()).getPath(), has(log.get()));
	}

	public void ore(ItemLike result, List<ItemLike> ingredients, float xp, String group, RecipeOutput consumer) {
		oreSmeltingRecipe(result, ingredients, xp, group, consumer);
		oreBlastingRecipe(result, ingredients, xp, group, consumer);
	}

	private void oreSmeltingRecipe(ItemLike result, List<ItemLike> ingredients, float xp, String group, RecipeOutput consumer) {
		for (ItemLike ingredient : ingredients) {
			smeltingRecipe(result, ingredient, CookingBookCategory.BLOCKS, xp).group(group).save(consumer, name("smelt_" + BuiltInRegistries.ITEM.getKey(ingredient.asItem()).getPath()));
		}
	}

	public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, ItemLike ingredient, CookingBookCategory category, float exp) {
		return SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.MISC, category, result, exp, 200)
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(ingredient.asItem()), has(ingredient));
	}

	public SimpleCookingRecipeBuilder smeltingRecipeTag(HolderGetter<Item> getter, ItemLike result, TagKey<Item> ingredient, CookingBookCategory category, float exp) {
		return SimpleCookingRecipeBuilder.smelting(Ingredient.of(getter.getOrThrow(ingredient)), RecipeCategory.MISC, category, result, exp, 200)
			.unlockedBy("has_" + ingredient, has(ingredient));
	}

	private void oreBlastingRecipe(ItemLike result, List<ItemLike> ingredients, float xp, String group, RecipeOutput consumer) {
		for (ItemLike ingredient : ingredients) {
			blastingRecipe(result, ingredient, CookingBookCategory.BLOCKS, xp).group(group).save(consumer, name("blast_" + BuiltInRegistries.ITEM.getKey(ingredient.asItem()).getPath()));
		}
	}

	public SimpleCookingRecipeBuilder blastingRecipe(ItemLike result, ItemLike ingredient, CookingBookCategory category, float exp) {
		return SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), RecipeCategory.MISC, category, result, exp, 100)
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(ingredient.asItem()), has(ingredient));
	}

	public SimpleCookingRecipeBuilder blastingRecipeTag(HolderGetter<Item> getter, ItemLike result, TagKey<Item> ingredient, CookingBookCategory category, float exp) {
		return SimpleCookingRecipeBuilder.blasting(Ingredient.of(getter.getOrThrow(ingredient)), RecipeCategory.MISC, category, result, exp, 100)
			.unlockedBy("has_" + ingredient, has(ingredient));
	}

	public SimpleCookingRecipeBuilder smokingRecipe(ItemLike result, ItemLike ingredient, float exp) {
		return SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.MISC, result, exp, 100)
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(ingredient.asItem()), has(ingredient));
	}

	public SimpleCookingRecipeBuilder campfireRecipe(ItemLike result, ItemLike ingredient, float exp) {
		return SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, 600)
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(ingredient.asItem()), has(ingredient));
	}

	public SmithingTransformRecipeBuilder smithingRecipe(Supplier<Item> input, Supplier<Item> upgradeItem, Supplier<Item> templateItem, Supplier<Item> result) {
		return SmithingTransformRecipeBuilder.smithing(Ingredient.of(templateItem.get()), Ingredient.of(input.get()), Ingredient.of(upgradeItem.get()), RecipeCategory.MISC, result.get())
			.unlocks("has_" + BuiltInRegistries.ITEM.getKey(upgradeItem.get()), has(upgradeItem.get()));
	}

	public SmithingTransformRecipeBuilder smithingForgotten(Supplier<Item> input, Supplier<Item> result) {
		return smithingRecipe(input, UGItems.FORGOTTEN_INGOT, UGItems.FORGOTTEN_UPGRADE_TEMPLATE, result);
	}

	public SingleItemRecipeBuilder stonecutting(Supplier<Block> input, ItemLike result) {
		return stonecutting(input, result, 1);
	}

	public SingleItemRecipeBuilder stonecutting(Supplier<Block> input, ItemLike result, int resultAmount) {
		return SingleItemRecipeBuilder.stonecutting(Ingredient.of(input.get()), RecipeCategory.BUILDING_BLOCKS, result, resultAmount)
			.unlockedBy("has_" + BuiltInRegistries.BLOCK.getKey(input.get()), has(input.get()));
	}

	public ItemInfusingRecipeBuilder itemInfusing(Ingredient ingredient, InfusingBookCategory bookCategory, float experience, int infusingTime) {
		return ItemInfusingRecipeBuilder.infusing(ingredient, bookCategory, experience, infusingTime);
	}

	public InfusingRecipeBuilder infusing(ItemLike result, ItemLike ingredient, InfusingBookCategory bookCategory, InfusingRecipe.SlotType type, float experience, int infusingTime) {
		return InfusingRecipeBuilder.infusing(Ingredient.of(ingredient), bookCategory, new ItemStackTemplate(result.asItem()), experience, infusingTime, type).unlockedBy("has_item", has(ingredient));
	}

	public InfusingRecipeBuilder infusingPurifying(ItemLike result, ItemLike ingredient, float experience, int infusingTime) {
		return infusing(result, ingredient, InfusingBookCategory.PURIFYING, InfusingRecipe.SlotType.ROGDORIUM, experience, infusingTime);
	}

	public InfusingRecipeBuilder infusingCorrupting(ItemLike result, ItemLike ingredient, float experience, int infusingTime) {
		return infusing(result, ingredient, InfusingBookCategory.CORRUPTING, InfusingRecipe.SlotType.UTHERIUM, experience, infusingTime);
	}

	public static ResourceKey<Recipe<?>> name(String name) {
		return ResourceKey.create(Registries.RECIPE, Undergarden.prefix(name));
	}
}