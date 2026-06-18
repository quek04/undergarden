package quek.undergarden.inventory;

import net.minecraft.recipebook.ServerPlaceRecipe;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipePropertySet;
import net.minecraft.world.level.Level;
import quek.undergarden.inventory.slot.InfuserResultSlot;
import quek.undergarden.inventory.slot.InfuserRogdoriumFuelSlot;
import quek.undergarden.inventory.slot.InfuserUtheriumFuelSlot;
import quek.undergarden.recipe.InfuserInput;
import quek.undergarden.recipe.InfusingRecipe;
import quek.undergarden.registry.UGMenuTypes;
import quek.undergarden.registry.UGRecipeBookTypes;
import quek.undergarden.registry.UGRecipePropertySets;
import quek.undergarden.registry.UGTags;

import java.util.List;

public class InfuserMenu extends RecipeBookMenu {

	private final Container container;
	private final ContainerData data;
	protected final Level level;
	private final RecipePropertySet acceptedInputs;

	public InfuserMenu(int containerId, Inventory playerInventory) {
		this(containerId, playerInventory, new SimpleContainer(4), new SimpleContainerData(2));
	}

	public InfuserMenu(int containerId, Inventory playerInventory, Container container, ContainerData data) {
		super(UGMenuTypes.INFUSER.get(), containerId);
		checkContainerSize(container, 4);
		checkContainerDataCount(data, 2);
		this.container = container;
		this.data = data;
		this.level = playerInventory.player.level();
		this.acceptedInputs = this.level.recipeAccess().propertySet(UGRecipePropertySets.INFUSING);

		this.addSlot(new Slot(container, 0, 80, 17));
		this.addSlot(new InfuserUtheriumFuelSlot(this, container, 1, 26, 53));
		this.addSlot(new InfuserRogdoriumFuelSlot(this, container, 2, 134, 53));
		this.addSlot(new InfuserResultSlot(playerInventory.player, container, 3, 80, 52));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; k++) {
			this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
		}

		this.addDataSlots(data);
	}

	public boolean isUtheriumFuel(ItemStack stack) {
		return stack.is(UGTags.Items.INFUSER_UTHERIUM_FUELS);
	}

	public boolean isUtheriumFuelFull() {
		return !this.container.getItem(1).isEmpty();
	}

	public boolean isRogdoriumFuel(ItemStack stack) {
		return stack.is(UGTags.Items.INFUSER_ROGDORIUM_FUELS);
	}

	public boolean isRogdoriumFuelFull() {
		return !this.container.getItem(2).isEmpty();
	}

	public float getInfusingProgress() {
		int infusingProgress = this.getInfusingProgressInt();
		int infusingTotalTime = this.getInfusingTotalTimeInt();
		return infusingProgress != 0 && infusingTotalTime != 0 ? Mth.clamp((float) infusingProgress / (float) infusingTotalTime, 0.0F, 1.0F) : 0.0F;
	}

	public int getInfusingProgressInt() {
		return this.data.get(0);
	}

	public int getInfusingTotalTimeInt() {
		return this.data.get(1);
	}

	protected boolean canInfuse(ItemStack stack) {
		return this.acceptedInputs.test(stack);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PostPlaceAction handlePlacement(boolean useMaxItems, boolean allowDroppingItemsToClear, RecipeHolder<?> recipe, ServerLevel level, Inventory inventory) {
		final List<Slot> slotsToClear = List.of(this.getSlot(0), this.getSlot(3));
		return ServerPlaceRecipe.placeRecipe(new ServerPlaceRecipe.CraftingMenuAccess<>() {

			@Override
			public void fillCraftSlotsStackedContents(StackedItemContents stackedContents) {
				InfuserMenu.this.fillCraftSlotsStackedContents(stackedContents);
			}

			@Override
			public void clearCraftingContent() {
				slotsToClear.forEach(s -> s.set(ItemStack.EMPTY));
			}

			@Override
			public boolean recipeMatches(RecipeHolder<InfusingRecipe> recipe) {
				return recipe.value().matches(new InfuserInput(InfuserMenu.this.container.getItem(0), recipe.value().getRecipeSlotType()), level);
			}
		}, 1, 1, List.of(this.getSlot(0)), slotsToClear, inventory, (RecipeHolder<InfusingRecipe>)recipe, useMaxItems, allowDroppingItemsToClear);
	}

	@Override
	public void fillCraftSlotsStackedContents(StackedItemContents contents) {
		if (this.container instanceof StackedContentsCompatible) {
			((StackedContentsCompatible)this.container).fillStackedContents(contents);
		}
	}

	@Override
	public RecipeBookType getRecipeBookType() {
		return UGRecipeBookTypes.INFUSER;
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index == 3) {
				if (!this.moveItemStackTo(itemstack1, 4, 40, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} else if (index != 1 && index != 0 && index != 2) {
				if (this.canInfuse(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (this.isUtheriumFuel(itemstack1) && this.slots.get(2).getItem().isEmpty()) {
					if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
						return ItemStack.EMPTY;
					}
				} else if (this.isRogdoriumFuel(itemstack1) && this.slots.get(1).getItem().isEmpty()) {
					if (!this.moveItemStackTo(itemstack1, 2, 3, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 4 && index < 31) {
					if (!this.moveItemStackTo(itemstack1, 31, 40, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 31 && index < 40 && !this.moveItemStackTo(itemstack1, 4, 31, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 4, 40, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.setByPlayer(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, itemstack1);
		}

		return itemstack;
	}

	@Override
	public boolean stillValid(Player player) {
		return this.container.stillValid(player);
	}
}