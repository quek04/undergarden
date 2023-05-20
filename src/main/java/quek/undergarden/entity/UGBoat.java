package quek.undergarden.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.network.NetworkHooks;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class UGBoat extends Boat {

	private static final EntityDataAccessor<Integer> BOAT_TYPE = SynchedEntityData.defineId(UGBoat.class, EntityDataSerializers.INT);

	public UGBoat(EntityType<? extends Boat> type, Level level) {
		super(type, level);
		this.blocksBuilding = true;
	}

	public UGBoat(Level level, double x, double y, double z) {
		this(UGEntityTypes.BOAT.get(), level);
		this.setPos(x, y, z);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	public UGBoat.Type getUGBoatType() {
		return UGBoat.Type.byId(this.entityData.get(BOAT_TYPE));
	}

	@Override
	public Item getDropItem() {
		return switch (this.getUGBoatType()) {
			case SMOGSTEM -> UGItems.SMOGSTEM_BOAT.get();
			case WIGGLEWOOD -> UGItems.WIGGLEWOOD_BOAT.get();
			case GRONGLE -> UGItems.GRONGLE_BOAT.get();
		};
	}

	public void setUGBoatType(UGBoat.Type boatType) {
		this.entityData.set(BOAT_TYPE, boatType.ordinal());
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(BOAT_TYPE, Type.SMOGSTEM.ordinal());
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		compound.putString("Type", this.getUGBoatType().getName());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		if (compound.contains("Type", 8)) {
			this.setUGBoatType(UGBoat.Type.getTypeFromString(compound.getString("Type")));
		}
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public enum Type {
		SMOGSTEM(UGBlocks.SMOGSTEM_PLANKS.get(), "smogstem"),
		WIGGLEWOOD(UGBlocks.WIGGLEWOOD_PLANKS.get(), "wigglewood"),
		GRONGLE(UGBlocks.GRONGLE_PLANKS.get(), "grongle");

		private final String name;
		private final Block block;

		Type(Block block, String name) {
			this.name = name;
			this.block = block;
		}

		public String getName() {
			return this.name;
		}

		public Block asPlank() {
			return this.block;
		}

		public String toString() {
			return this.name;
		}

		public static UGBoat.Type byId(int id) {
			UGBoat.Type[] aUGBoatEntity$type = values();
			if (id < 0 || id >= aUGBoatEntity$type.length) {
				id = 0;
			}

			return aUGBoatEntity$type[id];
		}

		public static UGBoat.Type getTypeFromString(String nameIn) {
			UGBoat.Type[] boatTypeArray = values();

			for (Type type : boatTypeArray) {
				if (type.getName().equals(nameIn)) {
					return type;
				}
			}

			return boatTypeArray[0];
		}
	}
}