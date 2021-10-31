package quek.undergarden.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class UGBoatEntity extends Boat {

    private static final EntityDataAccessor<Integer> BOAT_TYPE = SynchedEntityData.defineId(UGBoatEntity.class, EntityDataSerializers.INT);

    public UGBoatEntity(EntityType<? extends Boat> type, Level world) {
        super(type, world);
        this.blocksBuilding = true;
    }

    public UGBoatEntity(Level worldIn, double x, double y, double z) {
        this(UGEntityTypes.BOAT.get(), worldIn);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public UGBoatEntity.Type getUGBoatType() {
        return UGBoatEntity.Type.byId(this.entityData.get(BOAT_TYPE));
    }

    @Override
    public Item getDropItem() {
        switch(this.getUGBoatType()) {
            case SMOGSTEM:
            default:
                return UGItems.SMOGSTEM_BOAT.get();
            case WIGGLEWOOD:
                return UGItems.WIGGLEWOOD_BOAT.get();
            case GRONGLE:
                return UGItems.GRONGLE_BOAT.get();
        }
    }

    public void setBoatType(UGBoatEntity.Type boatType) {
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
            this.setBoatType(UGBoatEntity.Type.getTypeFromString(compound.getString("Type")));
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public enum Type {
        SMOGSTEM(UGBlocks.SMOGSTEM_PLANKS.get(), "smogstem"),
        WIGGLEWOOD(UGBlocks.WIGGLEWOOD_PLANKS.get(), "wigglewood"),
        GRONGLE(UGBlocks.GRONGLE_PLANKS.get(), "grongle")
        ;

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

        /**
         * Get a boat type by it's enum ordinal
         */
        public static UGBoatEntity.Type byId(int id) {
            UGBoatEntity.Type[] aUGBoatEntity$type = values();
            if (id < 0 || id >= aUGBoatEntity$type.length) {
                id = 0;
            }

            return aUGBoatEntity$type[id];
        }

        public static UGBoatEntity.Type getTypeFromString(String nameIn) {
            UGBoatEntity.Type[] boatTypeArray = values();

            for (Type type : boatTypeArray) {
                if (type.getName().equals(nameIn)) {
                    return type;
                }
            }

            return boatTypeArray[0];
        }
    }
}
