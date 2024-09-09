package quek.undergarden.block.entity;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.block.InfuserBlock;
import quek.undergarden.block.InfuserState;
import quek.undergarden.inventory.InfuserMenu;
import quek.undergarden.recipe.InfuserRecipeInput;
import quek.undergarden.recipe.InfusingRecipe;
import quek.undergarden.registry.UGBlockEntities;
import quek.undergarden.registry.UGRecipeTypes;
import quek.undergarden.registry.UGTags;

import java.util.List;

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
				case 0:
					InfuserBlockEntity.this.infusingProgress = value;
					break;
				case 1:
					InfuserBlockEntity.this.infusingTotalTime = value;
					//break;
			}
		}

		@Override
		public int getCount() {
			return 2;
		}
	};

	private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
	private final RecipeManager.CachedCheck<InfuserRecipeInput, InfusingRecipe> quickCheck;

	public InfuserBlockEntity(BlockPos pos, BlockState blockState) {
		super(UGBlockEntities.INFUSER.get(), pos, blockState);
		this.quickCheck = RecipeManager.createCheck(UGRecipeTypes.INFUSING.get());
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable("container.undergarden.infuser");
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
	public void setItem(int index, ItemStack stack) {
		ItemStack itemstack = this.items.get(index);
		boolean flag = !stack.isEmpty() && ItemStack.isSameItemSameComponents(itemstack, stack);
		this.items.set(index, stack);
		stack.limitSize(this.getMaxStackSize(stack));
		if (index == 0 && !flag) {
			this.infusingTotalTime = getTotalInfusingTime(this.level, this);
			this.infusingProgress = 0;
			this.setChanged();
		}
	}

	@Override
	protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
		return new InfuserMenu(containerId, inventory, this, this.containerData);
	}

	@Override
	public int getContainerSize() {
		return this.items.size();
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
			case 1 -> this.items.get(2).isEmpty() && itemStack.is(UGTags.Items.INFUSER_UTHERIUM_FUELS);
			case 2 -> this.items.get(1).isEmpty() && itemStack.is(UGTags.Items.INFUSER_ROGDORIUM_FUELS);
			default -> false;
		};
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return direction == Direction.DOWN && index == 3;
	}

	@Override
	public void setRecipeUsed(@Nullable RecipeHolder<?> recipe) {
		if (recipe != null) {
			ResourceLocation resourcelocation = recipe.id();
			this.recipesUsed.addTo(resourcelocation, 1);
		}
	}

	@Nullable
	@Override
	public RecipeHolder<?> getRecipeUsed() {
		return null;
	}

	@Override
	public void fillStackedContents(StackedContents contents) {
		for (ItemStack stack : this.items) {
			contents.accountStack(stack);
		}
	}

	@Override
	protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(tag, this.items, registries);
		this.infusingProgress = tag.getInt("InfusingTime");
		this.infusingTotalTime = tag.getInt("InfusingTimeTotal");
		CompoundTag compoundtag = tag.getCompound("RecipesUsed");

		for (String s : compoundtag.getAllKeys()) {
			this.recipesUsed.put(ResourceLocation.parse(s), compoundtag.getInt(s));
		}
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		tag.putInt("InfusingTime", this.infusingProgress);
		tag.putInt("InfusingTimeTotal", this.infusingTotalTime);
		ContainerHelper.saveAllItems(tag, this.items, registries);
		CompoundTag compoundtag = new CompoundTag();
		this.recipesUsed.forEach((p_187449_, p_187450_) -> compoundtag.putInt(p_187449_.toString(), p_187450_));
		tag.put("RecipesUsed", compoundtag);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, InfuserBlockEntity blockEntity) {
		boolean changed = false;
		boolean isInfusing = blockEntity.infusingProgress > 0;

		ItemStack utheriumFuel = blockEntity.items.get(1);
		ItemStack rogdoriumFuel = blockEntity.items.get(2);
		ItemStack input = blockEntity.items.get(0);
		boolean inputFull = !input.isEmpty();
		boolean utheriumFuelFull = !utheriumFuel.isEmpty();
		boolean rogdoriumFuelFull = !rogdoriumFuel.isEmpty();

		RecipeHolder<InfusingRecipe> recipe = blockEntity.quickCheck.getRecipeFor(new InfuserRecipeInput(input, utheriumFuelFull), level).orElse(null);
		int i = blockEntity.getMaxStackSize();

		if (inputFull) {
			if (blockEntity.canInfuse(level.registryAccess(), recipe, blockEntity.items, i, blockEntity)) {
				blockEntity.infusingProgress++;
				if (blockEntity.infusingProgress == blockEntity.infusingTotalTime) {
					if (utheriumFuelFull && recipe != null && recipe.value().isUtheriumFuel()) {
						utheriumFuel.shrink(1);
					}
					if (rogdoriumFuelFull && recipe != null && !recipe.value().isUtheriumFuel()) {
						rogdoriumFuel.shrink(1);
					}

					blockEntity.infusingProgress = 0;
					blockEntity.infusingTotalTime = getTotalInfusingTime(level, blockEntity);
					if (blockEntity.infuse(level.registryAccess(), recipe, blockEntity.items, i, blockEntity)) {
						blockEntity.setRecipeUsed(recipe);
					}

					changed = true;
				}
			} else {
				blockEntity.infusingProgress = 0;
			}
		} else if (blockEntity.infusingProgress > 0) {
			blockEntity.infusingProgress = Mth.clamp(blockEntity.infusingProgress - 2, 0, blockEntity.infusingTotalTime);
		}

		if (isInfusing != blockEntity.infusingProgress > 0) {
			changed = true;
			state = state.setValue(InfuserBlock.STATE, !utheriumFuelFull && !rogdoriumFuelFull ? InfuserState.INACTIVE : (utheriumFuelFull ? InfuserState.INFUSING_UTHERIUM : InfuserState.INFUSING_ROGDORIUM));
			level.setBlock(pos, state, 3);
		}

		if (changed) {
			setChanged(level, pos, state);
		}
	}

	private boolean canInfuse(RegistryAccess registryAccess, @javax.annotation.Nullable RecipeHolder<InfusingRecipe> recipe, NonNullList<ItemStack> inventory, int maxStackSize, InfuserBlockEntity infuser) {
		if (!inventory.get(0).isEmpty() && recipe != null) {
			ItemStack result = recipe.value().assemble(new InfuserRecipeInput(infuser.getItem(0), recipe.value().isUtheriumFuel()), registryAccess);

			if (inventory.get(1).isEmpty() && recipe.value().isUtheriumFuel()) {
				return false;
			}
			if (inventory.get(2).isEmpty() && !recipe.value().isUtheriumFuel()) {
				return false;
			}

			if (result.isEmpty()) {
				return false;
			} else {
				ItemStack resultSlot = inventory.get(3);
				if (resultSlot.isEmpty()) {
					return true;
				} else if (!ItemStack.isSameItem(resultSlot, result)) {
					return false;
				} else {
					// Neo fix: make furnace respect stack sizes in furnace recipes
					return resultSlot.getCount() + result.getCount() <= maxStackSize && resultSlot.getCount() + result.getCount() <= resultSlot.getMaxStackSize() || resultSlot.getCount() + result.getCount() <= result.getMaxStackSize(); // Neo fix: make furnace respect stack sizes in furnace recipes
				}
			}
		} else {
			return false;
		}
	}

	private boolean infuse(RegistryAccess registryAccess, @javax.annotation.Nullable RecipeHolder<InfusingRecipe> recipe, NonNullList<ItemStack> inventory, int maxStackSize, InfuserBlockEntity infuser) {
		if (recipe != null && canInfuse(registryAccess, recipe, inventory, maxStackSize, infuser)) {
			ItemStack input = inventory.get(0);
			ItemStack result = recipe.value().assemble(new InfuserRecipeInput(this.getItem(0), recipe.value().isUtheriumFuel()), registryAccess);
			ItemStack output = inventory.get(3);
			if (output.isEmpty()) {
				inventory.set(3, result.copy());
			} else if (output.is(result.getItem())) {
				output.grow(result.getCount());
			}

			input.shrink(1);
			return true;
		} else {
			return false;
		}
	}

	private static int getTotalInfusingTime(Level level, InfuserBlockEntity blockEntity) {
		InfuserRecipeInput recipeInput = new InfuserRecipeInput(blockEntity.getItem(0), !blockEntity.getItem(1).isEmpty());
		return blockEntity.quickCheck.getRecipeFor(recipeInput, level).map(infusingRecipe -> infusingRecipe.value().getInfusingTime()).orElse(200);
	}

	public void awardUsedRecipesAndPopExperience(ServerPlayer player) {
		List<RecipeHolder<?>> list = this.getRecipesToAwardAndPopExperience(player.serverLevel(), player.position());
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

		for (Object2IntMap.Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
			level.getRecipeManager().byKey(entry.getKey()).ifPresent(recipeHolder -> {
				list.add(recipeHolder);
				createExperience(level, popVec, entry.getIntValue(), ((InfusingRecipe)recipeHolder.value()).getExperience());
			});
		}

		return list;
	}

	private static void createExperience(ServerLevel level, Vec3 popVec, int recipeIndex, float experience) {
		int i = Mth.floor((float)recipeIndex * experience);
		float f = Mth.frac((float)recipeIndex * experience);
		if (f != 0.0F && Math.random() < (double)f) {
			i++;
		}

		ExperienceOrb.award(level, popVec, i);
	}
}