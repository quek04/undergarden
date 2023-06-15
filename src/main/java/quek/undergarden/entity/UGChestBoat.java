package quek.undergarden.entity;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HasCustomInventoryScreen;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.ContainerEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;

public class UGChestBoat extends UGBoat implements HasCustomInventoryScreen, ContainerEntity {

	private NonNullList<ItemStack> itemStacks = NonNullList.withSize(27, ItemStack.EMPTY);
	@Nullable
	private ResourceLocation lootTable;
	private long lootTableSeed;

	public UGChestBoat(EntityType<? extends UGBoat> type, Level level) {
		super(type, level);
	}

	public UGChestBoat(Level level, double x, double y, double z) {
		this(UGEntityTypes.CHEST_BOAT.get(), level);
		this.setPos(x, y, z);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	@Override
	protected float getSinglePassengerXOffset() {
		return 0.15F;
	}

	@Override
	protected int getMaxPassengers() {
		return 1;
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		this.addChestVehicleSaveData(tag);
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.readChestVehicleSaveData(tag);
	}

	@Override
	public void destroy(DamageSource damageSource) {
		super.destroy(damageSource);
		this.chestVehicleDestroyed(damageSource, this.level(), this);
	}

	@Override
	public void remove(Entity.RemovalReason reason) {
		if (!this.level().isClientSide && reason.shouldDestroy()) {
			Containers.dropContents(this.level(), this, this);
		}

		super.remove(reason);
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		return this.canAddPassenger(player) && !player.isSecondaryUseActive() ? super.interact(player, hand) : this.interactWithContainerVehicle(player);
	}

	@Override
	public void openCustomInventoryScreen(Player player) {
		player.openMenu(this);
		if (!player.level().isClientSide) {
			this.gameEvent(GameEvent.CONTAINER_OPEN, player);
			PiglinAi.angerNearbyPiglins(player, true);
		}

	}

	@Override
	public Item getDropItem() {
		return switch (this.getUGBoatType()) {
			case SMOGSTEM -> UGItems.SMOGSTEM_CHEST_BOAT.get();
			case WIGGLEWOOD -> UGItems.WIGGLEWOOD_CHEST_BOAT.get();
			case GRONGLE -> UGItems.GRONGLE_CHEST_BOAT.get();
		};
	}

	@Override
	public void clearContent() {
		this.clearChestVehicleContent();
	}

	@Override
	public int getContainerSize() {
		return 27;
	}

	@Override
	public ItemStack getItem(int p_219880_) {
		return this.getChestVehicleItem(p_219880_);
	}

	@Override
	public ItemStack removeItem(int p_219882_, int p_219883_) {
		return this.removeChestVehicleItem(p_219882_, p_219883_);
	}

	@Override
	public ItemStack removeItemNoUpdate(int p_219904_) {
		return this.removeChestVehicleItemNoUpdate(p_219904_);
	}

	@Override
	public void setItem(int p_219885_, ItemStack p_219886_) {
		this.setChestVehicleItem(p_219885_, p_219886_);
	}

	@Override
	public SlotAccess getSlot(int p_219918_) {
		return this.getChestVehicleSlot(p_219918_);
	}

	@Override
	public void setChanged() {
	}

	@Override
	public boolean stillValid(Player p_219896_) {
		return this.isChestVehicleStillValid(p_219896_);
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int p_219910_, Inventory p_219911_, Player p_219912_) {
		if (this.lootTable != null && p_219912_.isSpectator()) {
			return null;
		} else {
			this.unpackLootTable(p_219911_.player);
			return ChestMenu.threeRows(p_219910_, p_219911_, this);
		}
	}

	public void unpackLootTable(@Nullable Player p_219914_) {
		this.unpackChestVehicleLootTable(p_219914_);
	}

	@Nullable
	@Override
	public ResourceLocation getLootTable() {
		return this.lootTable;
	}

	@Override
	public void setLootTable(@Nullable ResourceLocation p_219890_) {
		this.lootTable = p_219890_;
	}

	@Override
	public long getLootTableSeed() {
		return this.lootTableSeed;
	}

	@Override
	public void setLootTableSeed(long p_219888_) {
		this.lootTableSeed = p_219888_;
	}

	@Override
	public NonNullList<ItemStack> getItemStacks() {
		return this.itemStacks;
	}

	@Override
	public void clearItemStacks() {
		this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
	}
}