package quek.undergarden.data.provider;

import net.minecraft.block.Block;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.data.ForgeRecipeProvider;
import quek.undergarden.registry.UGItems;

import java.util.function.Supplier;

public class UGRecipeProvider extends ForgeRecipeProvider implements IConditionBuilder {

    public UGRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    public ShapelessRecipeBuilder makePlanks(Supplier<? extends Block> plankOut, Supplier<? extends Block> logIn) {
        return ShapelessRecipeBuilder.shapelessRecipe(plankOut.get(), 4)
                .addIngredient(logIn.get())
                .addCriterion("has_" + logIn.get().getRegistryName().getPath(), hasItem(logIn.get()));
    }

    public ShapedRecipeBuilder makeDoor(Supplier<? extends Block> doorOut, Supplier<? extends Block> plankIn) {
        return ShapedRecipeBuilder.shapedRecipe(doorOut.get(), 3)
                .patternLine("PP")
                .patternLine("PP")
                .patternLine("PP")
                .key('P', plankIn.get())
                .addCriterion("has_" + plankIn.get().getRegistryName().getPath(), hasItem(plankIn.get()));
    }

    public ShapedRecipeBuilder makeTrapdoor(Supplier<? extends Block> trapdoorOut, Supplier<? extends Block> plankIn) {
        return ShapedRecipeBuilder.shapedRecipe(trapdoorOut.get(), 2)
                .patternLine("PPP")
                .patternLine("PPP")
                .key('P', plankIn.get())
                .addCriterion("has_" + plankIn.get().getRegistryName().getPath(), hasItem(plankIn.get()));
    }

    public ShapelessRecipeBuilder makeButton(Supplier<? extends Block> buttonOut, Supplier<? extends Block> blockIn) {
        return ShapelessRecipeBuilder.shapelessRecipe(buttonOut.get())
                .addIngredient(blockIn.get())
                .addCriterion("has_" + blockIn.get().getRegistryName().getPath(), hasItem(blockIn.get()));
    }

    public ShapedRecipeBuilder makePressurePlate(Supplier<? extends Block> pressurePlateOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shapedRecipe(pressurePlateOut.get())
                .patternLine("BB")
                .key('B', blockIn.get())
                .addCriterion("has_" + blockIn.get().getRegistryName().getPath(), hasItem(blockIn.get()));
    }

    public ShapedRecipeBuilder makeStairs(Supplier<? extends Block> stairsOut, Supplier<? extends Block> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(stairsOut.get(), 4)
                .patternLine("M  ")
                .patternLine("MM ")
                .patternLine("MMM")
                .key('M', materialIn.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeSlab(Supplier<? extends Block> slabOut, Supplier<? extends Block> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(slabOut.get(), 6)
                .patternLine("MMM")
                .key('M', materialIn.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeWall(Supplier<? extends Block> wallOut, Supplier<? extends Block> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(wallOut.get(), 6)
                .patternLine("MMM")
                .patternLine("MMM")
                .key('M', materialIn.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeFence(Supplier<? extends Block> fenceOut, Supplier<? extends Block> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(fenceOut.get(), 6)
                .patternLine("M/M")
                .patternLine("M/M")
                .key('M', materialIn.get())
                .key('/', Tags.Items.RODS_WOODEN)
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeFenceGate(Supplier<? extends Block> fenceGateOut, Supplier<? extends Block> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(fenceGateOut.get())
                .patternLine("/M/")
                .patternLine("/M/")
                .key('M', materialIn.get())
                .key('/', Tags.Items.RODS_WOODEN)
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeBricks(Supplier<? extends Block> bricksOut, Supplier<? extends Block> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(bricksOut.get(), 4)
                .patternLine("MM")
                .patternLine("MM")
                .key('M', materialIn.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeChiseledBricks(Supplier<? extends Block> bricksOut, Supplier<? extends Block> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(bricksOut.get())
                .patternLine("M")
                .patternLine("M")
                .key('M', materialIn.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeWood(Supplier<? extends Block> woodOut, Supplier<? extends Block> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(woodOut.get(), 3)
                .patternLine("MM")
                .patternLine("MM")
                .key('M', materialIn.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeIngotToBlock(Supplier<? extends Block> blockOut, Supplier<? extends Item> ingotIn) {
        return ShapedRecipeBuilder.shapedRecipe(blockOut.get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', ingotIn.get())
                .addCriterion("has_" + ingotIn.get().getRegistryName().getPath(), hasItem(ingotIn.get()));
    }

    public ShapelessRecipeBuilder makeBlockToIngot(Supplier<? extends Item> ingotOut, Supplier<? extends Block> blockIn) {
        return ShapelessRecipeBuilder.shapelessRecipe(ingotOut.get(), 9)
                .addIngredient(blockIn.get())
                .addCriterion("has_" + blockIn.get().getRegistryName().getPath(), hasItem(blockIn.get()));
    }

    public ShapedRecipeBuilder makeNuggetToIngot(Supplier<? extends Item> ingotOut, Supplier<? extends Item> nuggetIn) {
        return ShapedRecipeBuilder.shapedRecipe(ingotOut.get(), 1)
                .patternLine("NNN")
                .patternLine("NNN")
                .patternLine("NNN")
                .key('N', nuggetIn.get())
                .addCriterion("has_" + nuggetIn.get().getRegistryName().getPath(), hasItem(nuggetIn.get()));
    }

    public ShapelessRecipeBuilder makeIngotToNugget(Supplier<? extends Item> nuggetOut, Supplier<? extends Item> ingotIn) {
        return ShapelessRecipeBuilder.shapelessRecipe(nuggetOut.get(), 9)
                .addIngredient(ingotIn.get())
                .addCriterion("has_" + ingotIn.get().getRegistryName().getPath(), hasItem(ingotIn.get()));
    }

    public ShapedRecipeBuilder makeSword(Supplier<? extends Item> swordOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(swordOut.get())
                .patternLine("#")
                .patternLine("#")
                .patternLine("/")
                .key('#', materialIn.get())
                .key('/', Tags.Items.RODS_WOODEN)
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makePickaxe(Supplier<? extends Item> pickaxeOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(pickaxeOut.get())
                .patternLine("###")
                .patternLine(" / ")
                .patternLine(" / ")
                .key('#', materialIn.get())
                .key('/', Tags.Items.RODS_WOODEN)
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeTagPickaxe(Supplier<? extends Item> pickaxeOut, ITag.INamedTag<Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(pickaxeOut.get())
                .patternLine("###")
                .patternLine(" / ")
                .patternLine(" / ")
                .key('#', materialIn)
                .key('/', Tags.Items.RODS_WOODEN)
                .addCriterion("has_" + materialIn, hasItem(materialIn));
    }

    public ShapedRecipeBuilder makeAxe(Supplier<? extends Item> axeOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(axeOut.get())
                .patternLine("##")
                .patternLine("#/")
                .patternLine(" /")
                .key('#', materialIn.get())
                .key('/', Tags.Items.RODS_WOODEN)
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeShovel(Supplier<? extends Item> shovelOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(shovelOut.get())
                .patternLine("#")
                .patternLine("/")
                .patternLine("/")
                .key('#', materialIn.get())
                .key('/', Tags.Items.RODS_WOODEN)
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeHoe(Supplier<? extends Item> hoeOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(hoeOut.get())
                .patternLine("##")
                .patternLine(" /")
                .patternLine(" /")
                .key('#', materialIn.get())
                .key('/', Tags.Items.RODS_WOODEN)
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeHelmet(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(helmetOut.get())
                .patternLine("MMM")
                .patternLine("M M")
                .key('M', materialIn.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeChestplate(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(helmetOut.get())
                .patternLine("M M")
                .patternLine("MMM")
                .patternLine("MMM")
                .key('M', materialIn.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeLeggings(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(helmetOut.get())
                .patternLine("MMM")
                .patternLine("M M")
                .patternLine("M M")
                .key('M', materialIn.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeBoots(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(helmetOut.get())
                .patternLine("M M")
                .patternLine("M M")
                .key('M', materialIn.get())
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapelessRecipeBuilder makeStew(Supplier<? extends Item> stewOut, Supplier<? extends Block> mushroomIn) {
        return ShapelessRecipeBuilder.shapelessRecipe(stewOut.get())
                .addIngredient(Items.BOWL)
                .addIngredient(mushroomIn.get(), 3)
                .addCriterion("has_" + mushroomIn.get().getRegistryName().getPath(), hasItem(mushroomIn.get()));
    }

    public ShapedRecipeBuilder makeBoat(Supplier<? extends Item> boatOut, Supplier<? extends Block> planksIn) {
        return ShapedRecipeBuilder.shapedRecipe(boatOut.get())
                .patternLine("P P")
                .patternLine("PPP")
                .key('P', planksIn.get())
                .addCriterion("has_" + planksIn.get().getRegistryName().getPath(), hasItem(planksIn.get()));
    }

    public CookingRecipeBuilder smeltingRecipe(IItemProvider result, IItemProvider ingredient, float exp) {
        return smeltingRecipe(result, ingredient, exp, 1);
    }

    public CookingRecipeBuilder smeltingRecipe(IItemProvider result, IItemProvider ingredient, float exp, int count) {
        return CookingRecipeBuilder.smeltingRecipe(Ingredient.fromStacks(new ItemStack(ingredient, count)), result, exp, 200)
                .addCriterion("has_" + ingredient.asItem().getRegistryName(), hasItem(ingredient));
    }

    public CookingRecipeBuilder smeltingRecipeTag(IItemProvider result, ITag.INamedTag<Item> ingredient, float exp) {
        return smeltingRecipeTag(result, ingredient, exp, 1);
    }

    public CookingRecipeBuilder smeltingRecipeTag(IItemProvider result, ITag.INamedTag<Item> ingredient, float exp, int count) {
        return CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(ingredient), result, exp, 200)
                .addCriterion("has_" + ingredient, hasItem(ingredient));
    }

    public CookingRecipeBuilder blastingRecipe(IItemProvider result, IItemProvider ingredient, float exp) {
        return blastingRecipe(result, ingredient, exp, 1);
    }

    public CookingRecipeBuilder blastingRecipe(IItemProvider result, IItemProvider ingredient, float exp, int count) {
        return CookingRecipeBuilder.blastingRecipe(Ingredient.fromStacks(new ItemStack(ingredient, count)), result, exp, 100)
                .addCriterion("has_" + ingredient.asItem().getRegistryName(), hasItem(ingredient));
    }

    public CookingRecipeBuilder blastingRecipeTag(IItemProvider result, ITag.INamedTag<Item> ingredient, float exp) {
        return blastingRecipeTag(result, ingredient, exp, 1);
    }

    public CookingRecipeBuilder blastingRecipeTag(IItemProvider result, ITag.INamedTag<Item> ingredient, float exp, int count) {
        return CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(ingredient), result, exp, 100)
                .addCriterion("has_" + ingredient, hasItem(ingredient));
    }

    public CookingRecipeBuilder smokingRecipe(IItemProvider result, IItemProvider ingredient, float exp) {
        return smokingRecipe(result, ingredient, exp, 1);
    }

    public CookingRecipeBuilder smokingRecipe(IItemProvider result, IItemProvider ingredient, float exp, int count) {
        return CookingRecipeBuilder.cookingRecipe(Ingredient.fromStacks(new ItemStack(ingredient, count)), result, exp, 100, IRecipeSerializer.SMOKING)
                .addCriterion("has_" + ingredient.asItem().getRegistryName(), hasItem(ingredient));
    }
}
