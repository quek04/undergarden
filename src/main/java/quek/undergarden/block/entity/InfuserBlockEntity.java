package quek.undergarden.block.entity;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import quek.undergarden.block.InfuserBlock;
import quek.undergarden.block.InfuserState;
import quek.undergarden.component.RogdoriumInfusion;
import quek.undergarden.inventory.InfuserMenu;
import quek.undergarden.recipe.InfuserInput;
import quek.undergarden.recipe.InfusingRecipe;
import quek.undergarden.registry.UGBlockEntities;
import quek.undergarden.registry.UGDataComponents;
import quek.undergarden.registry.UGRecipeTypes;
import quek.undergarden.registry.UGTags;

import java.util.List;
import java.util.Map;

public class InfuserBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible {

	private static final int[] SLOTS_FOR_UP = new int[]{0};
	private static final int[] SLOTS_FOR_DOWN = new int[]{3};
	private static final int[] SLOTS_FOR_SIDES = new int[]{1, 2};

	private NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
	int infusingProgress;
	int infusingTotalTime;
	private final ContainerData containerData = new ContainerData() {
		@Override
		public int get(int index) {
			return switch (index) {
				case 0 -> InfuserBlockEntity.this.infusingProgress;
				case 1 -> InfuserBlockEntity.this.infusingTotalTime;
				default -> 0;
			};
		}

		@Override
		public void set(int index, int value) {
			switch (index) {
				case 0 -> InfuserBlockEntity.this.infusingProgress = value;
				case 1 -> InfuserBlockEntity.this.infusingTotalTime = value;
			}
		}

		@Override
		public int getCount() {
			return 2;
		}
	};

	private static final Codec<Map<ResourceKey<Recipe<?>>, Integer>> RECIPES_USED_CODEC = Codec.unboundedMap(Recipe.KEY_CODEC, Codec.INT);
	private final Reference2IntOpenHashMap<ResourceKey<Recipe<?>>> recipesUsed = new Reference2IntOpenHashMap<>();
	private final RecipeManager.CachedCheck<InfuserInput, InfusingRecipe> quickCheck = RecipeManager.createCheck(UGRecipeTypes.INFUSING.get());

	public InfuserBlockEntity(BlockPos pos, BlockState blockState) {
		super(UGBlockEntities.INFUSER.get(), pos, blockState);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(input, this.items);
		this.infusingProgress = input.getIntOr("InfusingTime", 0);
		this.infusingTotalTime = input.getIntOr("InfusingTimeTotal", 0);
		this.recipesUsed.clear();
		this.recipesUsed.putAll(input.read("RecipesUsed", RECIPES_USED_CODEC).orElse(Map.of()));
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		output.putInt("InfusingTime", this.infusingProgress);
		output.putInt("InfusingTimeTotal", this.infusingTotalTime);
		ContainerHelper.saveAllItems(output, this.items);
		output.store("RecipesUsed", RECIPES_USED_CODEC, this.recipesUsed);
	}

	public static void serverTick(ServerLevel level, BlockPos pos, BlockState state, InfuserBlockEntity entity) {
		boolean changed = false;
		boolean forceReset = entity.infusingProgress > 0;

		if (!entity.items.isEmpty()) {
			ItemStack ingredient = entity.getItem(0);
			InfusingRecipe.SlotType type = entity.getSlotTypeFromFuel();
			if (type != null) {
				InfuserInput input = new InfuserInput(ingredient, type);
				RecipeHolder<InfusingRecipe> recipe = entity.quickCheck.getRecipeFor(input, level).orElse(null);
				if (recipe != null) {
					ItemStack result = recipe.value().assemble(input);
					if (!result.isEmpty() && entity.canInfuse(entity.getMaxStackSize(), result)) {
						entity.infusingProgress++;
						if (entity.infusingProgress == entity.infusingTotalTime) {
							entity.infusingProgress = 0;
							entity.infusingTotalTime = getTotalInfusingTime(level, entity);
							entity.infuse(ingredient, result, type);
							entity.setRecipeUsed(recipe);
							changed = true;
						}
					}
					forceReset = false;
				}
			}
		}

		if (forceReset) {
			entity.infusingProgress = 0;
			changed = true;
		}

		if (changed) {
			setChanged(level, pos, state);
		}
	}

	@Override
	public void setChanged() {
		if (this.level != null) {
			var state = this.getBlockState().setValue(InfuserBlock.STATE, this.calculateState());
			this.level.setBlock(this.getBlockPos(), state, 3);
		}
		super.setChanged();
	}

	private InfusingRecipe.@Nullable SlotType getSlotTypeFromFuel() {
		for (InfusingRecipe.SlotType type :InfusingRecipe.SlotType.values()) {
			if (!this.getItem(type.getSlotIndex()).isEmpty()) {
				return type;
			}
		}
		return null;
	}

	private InfuserState calculateState() {
		boolean utherium = !this.getItem(1).isEmpty();
		boolean rogdorium = !this.getItem(2).isEmpty();

		return !utherium && !rogdorium ? InfuserState.INACTIVE : (utherium ? InfuserState.INFUSING_UTHERIUM : InfuserState.INFUSING_ROGDORIUM);
	}

	private boolean canInfuse(int maxStackSize, ItemStack result) {
		ItemStack resultItemStack = this.getItem(3);
		if (resultItemStack.isEmpty()) {
			return true;
		} else if (!ItemStack.isSameItemSameComponents(resultItemStack, result)) {
			return false;
		} else {
			int resultCount = resultItemStack.getCount() + result.count();
			int maxResultCount = Math.min(maxStackSize, result.getMaxStackSize());
			return resultCount <= maxResultCount;
		}
	}

	private void infuse(ItemStack input, ItemStack result, InfusingRecipe.SlotType type) {
		ItemStack resultItemStack = this.getItem(3);

		if (ItemStack.isSameItem(input, result)) {
			ItemStack rogdoriumSlot = this.getItem(2);
			var component = result.getOrDefault(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.DEFAULT);
			int infusionAmount = component.infusionAmount();
			int infusionMax = component.infusionMax();
			int fuelAmount = rogdoriumSlot.getCount();
			if (infusionMax <= infusionAmount) {
				return;
			}
			result.set(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.setInfusionAmount(Mth.clamp((fuelAmount * 600) + infusionAmount, 0, infusionMax)));
			rogdoriumSlot.shrink(Mth.clamp(fuelAmount, 1, (infusionMax / 600)));
		} else {
			this.getItem(type.getSlotIndex()).shrink(1);
		}
		input.shrink(1);

		if (resultItemStack.isEmpty()) {
			this.setItem(3, result.copy());
		} else {
			resultItemStack.grow(result.getCount());
		}
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable("container.undergarden.infuser");
	}

	@Override
	protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
		return new InfuserMenu(containerId, inventory, this, this.containerData);
	}

	private static int getTotalInfusingTime(ServerLevel level, InfuserBlockEntity entity) {
		InfusingRecipe.SlotType type = entity.getSlotTypeFromFuel();
		if (type != null) {
			InfuserInput input = new InfuserInput(entity.getItem(0), type);
			return entity.quickCheck.getRecipeFor(input, level).map(recipeHolder -> recipeHolder.value().infusingTime()).orElse(200);
		}
		return 200;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		if (side == Direction.DOWN) {
			return SLOTS_FOR_DOWN;
		} else {
			return side == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
		}
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
		return switch (index) {
			case 0 -> true;
			case 1 -> this.getItem(2).isEmpty() && itemStack.is(UGTags.Items.INFUSER_UTHERIUM_FUELS);
			case 2 -> this.getItem(1).isEmpty() && itemStack.is(UGTags.Items.INFUSER_ROGDORIUM_FUELS);
			default -> false;
		};
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return direction == Direction.DOWN && index == 3;
	}

	@Override
	public int getContainerSize() {
		return this.items.size();
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> items) {
		this.items = items;
	}

	@Override
	public void setItem(int slot, ItemStack itemStack) {
		setItem(slot, itemStack, false);
	}

	@Override
	public void setItem(int slot, ItemStack itemStack, boolean insideTransaction) {
		ItemStack oldStack = this.items.get(slot);
		boolean same = !itemStack.isEmpty() && ItemStack.isSameItemSameComponents(oldStack, itemStack);
		this.items.set(slot, itemStack);
		itemStack.limitSize(this.getMaxStackSize(itemStack));
		if (slot == 0 && !same && this.level instanceof ServerLevel serverLevel && !insideTransaction) {
			this.infusingTotalTime = getTotalInfusingTime(serverLevel, this);
			this.infusingProgress = 0;
			this.setChanged();
		}
	}

	@Override
	public void setRecipeUsed(@Nullable RecipeHolder<?> recipeUsed) {
		if (recipeUsed != null) {
			ResourceKey<Recipe<?>> id = recipeUsed.id();
			this.recipesUsed.addTo(id, 1);
		}
	}

	@Override
	public @Nullable RecipeHolder<?> getRecipeUsed() {
		return null;
	}

	@Override
	public void awardUsedRecipes(Player player, List<ItemStack> itemStacks) {
	}

	public void awardUsedRecipesAndPopExperience(ServerPlayer player) {
		List<RecipeHolder<?>> list = this.getRecipesToAwardAndPopExperience(player.level(), player.position());
		player.awardRecipes(list);

		for (RecipeHolder<?> recipeholder : list) {
			if (recipeholder != null) {
				player.triggerRecipeCrafted(recipeholder, this.items);
			}
		}
		this.recipesUsed.clear();
	}

	public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel level, Vec3 popVec) {
		List<RecipeHolder<?>> list = Lists.newArrayList();

		for (Reference2IntMap.Entry<ResourceKey<Recipe<?>>> entry : this.recipesUsed.reference2IntEntrySet()) {
			level.recipeAccess().byKey(entry.getKey()).ifPresent(recipeHolder -> {
				list.add(recipeHolder);
				createExperience(level, popVec, entry.getIntValue(), ((InfusingRecipe) recipeHolder.value()).experience());
			});
		}

		return list;
	}

	private static void createExperience(ServerLevel level, Vec3 popVec, int recipeIndex, float experience) {
		int i = Mth.floor((float) recipeIndex * experience);
		float f = Mth.frac((float) recipeIndex * experience);
		if (f != 0.0F && Math.random() < (double) f) {
			i++;
		}

		ExperienceOrb.award(level, popVec, i);
	}

	@Override
	public void fillStackedContents(StackedItemContents contents) {
		for (ItemStack itemStack : this.items) {
			contents.accountStack(itemStack);
		}
	}

	@Override
	public void preRemoveSideEffects(BlockPos pos, BlockState state) {
		super.preRemoveSideEffects(pos, state);
		if (this.level instanceof ServerLevel serverLevel) {
			this.getRecipesToAwardAndPopExperience(serverLevel, Vec3.atCenterOf(pos));
		}
	}
}