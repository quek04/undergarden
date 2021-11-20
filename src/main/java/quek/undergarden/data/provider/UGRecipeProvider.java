package quek.undergarden.data.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SignBlock;
import net.minecraftforge.common.Tags;
import quek.undergarden.Undergarden;
import quek.undergarden.data.UGRecipes;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class UGRecipeProvider extends RecipeProvider {

    public UGRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    public ShapelessRecipeBuilder makePlanks(Supplier<? extends Block> plankOut, Tag.Named<Item> logIn) {
        return ShapelessRecipeBuilder.shapeless(plankOut.get(), 4)
                .requires(logIn)
                .unlockedBy("has_" + logIn.getName(), has(logIn));
    }

    public ShapedRecipeBuilder makeDoor(Supplier<? extends Block> doorOut, Supplier<? extends Block> plankIn) {
        return ShapedRecipeBuilder.shaped(doorOut.get(), 3)
                .pattern("PP")
                .pattern("PP")
                .pattern("PP")
                .define('P', plankIn.get())
                .unlockedBy("has_" + plankIn.get().getRegistryName().getPath(), has(plankIn.get()));
    }

    public ShapedRecipeBuilder makeTrapdoor(Supplier<? extends Block> trapdoorOut, Supplier<? extends Block> plankIn) {
        return ShapedRecipeBuilder.shaped(trapdoorOut.get(), 2)
                .pattern("PPP")
                .pattern("PPP")
                .define('P', plankIn.get())
                .unlockedBy("has_" + plankIn.get().getRegistryName().getPath(), has(plankIn.get()));
    }

    public ShapelessRecipeBuilder makeButton(Supplier<? extends Block> buttonOut, Supplier<? extends Block> blockIn) {
        return ShapelessRecipeBuilder.shapeless(buttonOut.get())
                .requires(blockIn.get())
                .unlockedBy("has_" + blockIn.get().getRegistryName().getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makePressurePlate(Supplier<? extends Block> pressurePlateOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(pressurePlateOut.get())
                .pattern("BB")
                .define('B', blockIn.get())
                .unlockedBy("has_" + blockIn.get().getRegistryName().getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeStairs(Supplier<? extends Block> stairsOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(stairsOut.get(), 4)
                .pattern("M  ")
                .pattern("MM ")
                .pattern("MMM")
                .define('M', blockIn.get())
                .unlockedBy("has_" + blockIn.get().getRegistryName().getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeSlab(Supplier<? extends Block> slabOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(slabOut.get(), 6)
                .pattern("MMM")
                .define('M', blockIn.get())
                .unlockedBy("has_" + blockIn.get().getRegistryName().getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeWall(Supplier<? extends Block> wallOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(wallOut.get(), 6)
                .pattern("MMM")
                .pattern("MMM")
                .define('M', blockIn.get())
                .unlockedBy("has_" + blockIn.get().getRegistryName().getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeFence(Supplier<? extends Block> fenceOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(fenceOut.get(), 6)
                .pattern("M/M")
                .pattern("M/M")
                .define('M', blockIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + blockIn.get().getRegistryName().getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeFenceGate(Supplier<? extends Block> fenceGateOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(fenceGateOut.get())
                .pattern("/M/")
                .pattern("/M/")
                .define('M', blockIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + blockIn.get().getRegistryName().getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeBricks(Supplier<? extends Block> bricksOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(bricksOut.get(), 4)
                .pattern("MM")
                .pattern("MM")
                .define('M', blockIn.get())
                .unlockedBy("has_" + blockIn.get().getRegistryName().getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeChiseledBricks(Supplier<? extends Block> bricksOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(bricksOut.get())
                .pattern("M")
                .pattern("M")
                .define('M', blockIn.get())
                .unlockedBy("has_" + blockIn.get().getRegistryName().getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeWood(Supplier<? extends Block> woodOut, Supplier<? extends Block> logIn) {
        return ShapedRecipeBuilder.shaped(woodOut.get(), 3)
                .pattern("MM")
                .pattern("MM")
                .define('M', logIn.get())
                .unlockedBy("has_" + logIn.get().getRegistryName().getPath(), has(logIn.get()));
    }

    public ShapedRecipeBuilder makeIngotToBlock(Supplier<? extends Block> blockOut, Supplier<? extends Item> ingotIn) {
        return ShapedRecipeBuilder.shaped(blockOut.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ingotIn.get())
                .unlockedBy("has_" + ingotIn.get().getRegistryName().getPath(), has(ingotIn.get()));
    }

    public ShapelessRecipeBuilder makeBlockToIngot(Supplier<? extends Item> ingotOut, Supplier<? extends Block> blockIn) {
        return ShapelessRecipeBuilder.shapeless(ingotOut.get(), 9)
                .requires(blockIn.get())
                .unlockedBy("has_" + blockIn.get().getRegistryName().getPath(), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeNuggetToIngot(Supplier<? extends Item> ingotOut, Supplier<? extends Item> nuggetIn) {
        return ShapedRecipeBuilder.shaped(ingotOut.get(), 1)
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .define('N', nuggetIn.get())
                .unlockedBy("has_" + nuggetIn.get().getRegistryName().getPath(), has(nuggetIn.get()));
    }

    public ShapelessRecipeBuilder makeIngotToNugget(Supplier<? extends Item> nuggetOut, Supplier<? extends Item> ingotIn) {
        return ShapelessRecipeBuilder.shapeless(nuggetOut.get(), 9)
                .requires(ingotIn.get())
                .unlockedBy("has_" + ingotIn.get().getRegistryName().getPath(), has(ingotIn.get()));
    }

    public ShapedRecipeBuilder makeSword(Supplier<? extends Item> swordOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(swordOut.get())
                .pattern("#")
                .pattern("#")
                .pattern("/")
                .define('#', materialIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + materialIn.get().getRegistryName().getPath(), has(materialIn.get()));
    }

    public ShapedRecipeBuilder makePickaxe(Supplier<? extends Item> pickaxeOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(pickaxeOut.get())
                .pattern("###")
                .pattern(" / ")
                .pattern(" / ")
                .define('#', materialIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + materialIn.get().getRegistryName().getPath(), has(materialIn.get()));
    }

    public ShapedRecipeBuilder makeAxe(Supplier<? extends Item> axeOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(axeOut.get())
                .pattern("##")
                .pattern("#/")
                .pattern(" /")
                .define('#', materialIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + materialIn.get().getRegistryName().getPath(), has(materialIn.get()));
    }

    public ShapedRecipeBuilder makeShovel(Supplier<? extends Item> shovelOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(shovelOut.get())
                .pattern("#")
                .pattern("/")
                .pattern("/")
                .define('#', materialIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + materialIn.get().getRegistryName().getPath(), has(materialIn.get()));
    }

    public ShapedRecipeBuilder makeHoe(Supplier<? extends Item> hoeOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(hoeOut.get())
                .pattern("##")
                .pattern(" /")
                .pattern(" /")
                .define('#', materialIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + materialIn.get().getRegistryName().getPath(), has(materialIn.get()));
    }

    public ShapedRecipeBuilder makeHelmet(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(helmetOut.get())
                .pattern("MMM")
                .pattern("M M")
                .define('M', materialIn.get())
                .unlockedBy("has_" + materialIn.get().getRegistryName().getPath(), has(materialIn.get()));
    }

    public ShapedRecipeBuilder makeChestplate(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(helmetOut.get())
                .pattern("M M")
                .pattern("MMM")
                .pattern("MMM")
                .define('M', materialIn.get())
                .unlockedBy("has_" + materialIn.get().getRegistryName().getPath(), has(materialIn.get()));
    }

    public ShapedRecipeBuilder makeLeggings(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(helmetOut.get())
                .pattern("MMM")
                .pattern("M M")
                .pattern("M M")
                .define('M', materialIn.get())
                .unlockedBy("has_" + materialIn.get().getRegistryName().getPath(), has(materialIn.get()));
    }

    public ShapedRecipeBuilder makeBoots(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(helmetOut.get())
                .pattern("M M")
                .pattern("M M")
                .define('M', materialIn.get())
                .unlockedBy("has_" + materialIn.get().getRegistryName().getPath(), has(materialIn.get()));
    }

    public ShapelessRecipeBuilder makeStew(Supplier<? extends Item> stewOut, Supplier<? extends Block> mushroomIn) {
        return ShapelessRecipeBuilder.shapeless(stewOut.get())
                .requires(Items.BOWL)
                .requires(mushroomIn.get(), 3)
                .unlockedBy("has_" + mushroomIn.get().getRegistryName().getPath(), has(mushroomIn.get()));
    }

    public ShapedRecipeBuilder makeBoat(Supplier<? extends Item> boatOut, Supplier<? extends Block> planksIn) {
        return ShapedRecipeBuilder.shaped(boatOut.get())
                .pattern("P P")
                .pattern("PPP")
                .define('P', planksIn.get())
                .unlockedBy("in_water", insideOf(Blocks.WATER));
    }

    public ShapedRecipeBuilder makeSign(Supplier<? extends SignBlock> signOut, Supplier<? extends Block> planksIn) {
        return ShapedRecipeBuilder.shaped(signOut.get(), 3)
                .pattern("PPP")
                .pattern("PPP")
                .pattern(" / ")
                .define('P', planksIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + planksIn.get().getRegistryName().getPath(), has(planksIn.get()));
    }

    public void ore(ItemLike result, List<ItemLike> ingredients, float xp, String group, Consumer<FinishedRecipe> consumer) {
        oreSmeltingRecipe(result, ingredients, xp, group, consumer);
        oreBlastingRecipe(result, ingredients, xp, group, consumer);
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, ItemLike ingredient, float exp) {
        return smeltingRecipe(result, ingredient, exp, 1);
    }

    private void oreSmeltingRecipe(ItemLike result, List<ItemLike> ingredients, float xp, String group, Consumer<FinishedRecipe> consumer) {
        for(ItemLike ingredient : ingredients) {
            smeltingRecipe(result, ingredient, xp, 1).group(group).save(consumer, new ResourceLocation(Undergarden.MODID, "smelt_" + ingredient.asItem().getRegistryName().getPath()));
        }
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, ItemLike ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemStack(ingredient, count)), result, exp, 200)
                .unlockedBy("has_" + ingredient.asItem().getRegistryName(), has(ingredient));
    }

    public SimpleCookingRecipeBuilder smeltingRecipeTag(ItemLike result, Tag.Named<Item> ingredient, float exp) {
        return smeltingRecipeTag(result, ingredient, exp, 1);
    }

    public SimpleCookingRecipeBuilder smeltingRecipeTag(ItemLike result, Tag.Named<Item> ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), result, exp, 200)
                .unlockedBy("has_" + ingredient, has(ingredient));
    }

    public SimpleCookingRecipeBuilder blastingRecipe(ItemLike result, ItemLike ingredient, float exp) {
        return blastingRecipe(result, ingredient, exp, 1);
    }

    private void oreBlastingRecipe(ItemLike result, List<ItemLike> ingredients, float xp, String group, Consumer<FinishedRecipe> consumer) {
        for(ItemLike ingredient : ingredients) {
            blastingRecipe(result, ingredient, xp, 1).group(group).save(consumer, new ResourceLocation(Undergarden.MODID, "blast_" + ingredient.asItem().getRegistryName().getPath()));
        }
    }

    public SimpleCookingRecipeBuilder blastingRecipe(ItemLike result, ItemLike ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.blasting(Ingredient.of(new ItemStack(ingredient, count)), result, exp, 100)
                .unlockedBy("has_" + ingredient.asItem().getRegistryName(), has(ingredient));
    }

    public SimpleCookingRecipeBuilder blastingRecipeTag(ItemLike result, Tag.Named<Item> ingredient, float exp) {
        return blastingRecipeTag(result, ingredient, exp, 1);
    }

    public SimpleCookingRecipeBuilder blastingRecipeTag(ItemLike result, Tag.Named<Item> ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), result, exp, 100)
                .unlockedBy("has_" + ingredient, has(ingredient));
    }

    public SimpleCookingRecipeBuilder smokingRecipe(ItemLike result, ItemLike ingredient, float exp) {
        return smokingRecipe(result, ingredient, exp, 1);
    }

    public SimpleCookingRecipeBuilder smokingRecipe(ItemLike result, ItemLike ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.cooking(Ingredient.of(new ItemStack(ingredient, count)), result, exp, 100, RecipeSerializer.SMOKING_RECIPE)
                .unlockedBy("has_" + ingredient.asItem().getRegistryName(), has(ingredient));
    }

    public UpgradeRecipeBuilder smithingRecipe(Supplier<Item> input, Supplier<Item> upgradeItem, Supplier<Item> result) {
        return UpgradeRecipeBuilder.smithing(Ingredient.of(input.get()), Ingredient.of(upgradeItem.get()), result.get())
                .unlocks("has_" + upgradeItem.get().getRegistryName(), has(upgradeItem.get()));
    }

    public UpgradeRecipeBuilder smithingForgotten(Supplier<Item> input, Supplier<Item> result) {
        return smithingRecipe(input, UGItems.FORGOTTEN_INGOT, result);
    }

    public SingleItemRecipeBuilder stonecuttingRecipe(Supplier<Block> input, ItemLike result) {
        return SingleItemRecipeBuilder.stonecutting(Ingredient.of(input.get()), result)
                .unlockedBy("has_" + input.get().getRegistryName(), has(input.get()));
    }

    public SingleItemRecipeBuilder stonecuttingRecipe(Supplier<Block> input, ItemLike result, int resultAmount) {
        return SingleItemRecipeBuilder.stonecutting(Ingredient.of(input.get()), result, resultAmount)
                .unlockedBy("has_" + input.get().getRegistryName(), has(input.get()));
    }

    public SingleItemRecipeBuilder depthrockStonecuttingRecipe(ItemLike result) {
        return stonecuttingRecipe(UGBlocks.DEPTHROCK, result);
    }

    public SingleItemRecipeBuilder depthrockStonecuttingRecipe(ItemLike result, int resultAmount) {
        return stonecuttingRecipe(UGBlocks.DEPTHROCK, result, resultAmount);
    }

    public SingleItemRecipeBuilder shiverstoneStonecuttingRecipe(ItemLike result) {
        return stonecuttingRecipe(UGBlocks.SHIVERSTONE, result);
    }

    public SingleItemRecipeBuilder shiverstoneStonecuttingRecipe(ItemLike result, int resultAmount) {
        return stonecuttingRecipe(UGBlocks.SHIVERSTONE, result, resultAmount);
    }

    public SingleItemRecipeBuilder tremblecrustStonecuttingRecipe(ItemLike result) {
        return stonecuttingRecipe(UGBlocks.TREMBLECRUST, result);
    }

    public SingleItemRecipeBuilder tremblecrustStonecuttingRecipe(ItemLike result, int resultAmount) {
        return stonecuttingRecipe(UGBlocks.TREMBLECRUST, result, resultAmount);
    }
}