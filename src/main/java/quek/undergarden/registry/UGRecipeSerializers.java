package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.recipe.InfuserConversionRecipe;
import quek.undergarden.recipe.ItemInfusingRecipe;

public class UGRecipeSerializers {

	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Undergarden.MODID);

	public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<InfuserConversionRecipe>> INFUSER_CONVERSION = RECIPE_SERIALIZERS.register("infuser_conversion", () -> InfuserConversionRecipe.SERIALIZER);
	public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ItemInfusingRecipe>> ITEM_INFUSING = RECIPE_SERIALIZERS.register("item_infusing", () -> ItemInfusingRecipe.SERIALIZER);
}
