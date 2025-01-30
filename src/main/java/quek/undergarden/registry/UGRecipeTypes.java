package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.recipe.InfusingRecipe;

public class UGRecipeTypes {

	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, Undergarden.MODID);

	public static final DeferredHolder<RecipeType<?>, RecipeType<InfusingRecipe>> INFUSING = RECIPE_TYPES.register("infusing", () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "infusing")));
}
