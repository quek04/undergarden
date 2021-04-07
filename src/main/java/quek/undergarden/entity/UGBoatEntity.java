package quek.undergarden.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class UGBoatEntity extends BoatEntity {

    private static final DataParameter<Integer> BOAT_TYPE = EntityDataManager.defineId(UGBoatEntity.class, DataSerializers.INT);

    public UGBoatEntity(EntityType<? extends BoatEntity> type, World world) {
        super(type, world);
        this.blocksBuilding = true;
    }

    public UGBoatEntity(World worldIn, double x, double y, double z) {
        this(UGEntityTypes.BOAT.get(), worldIn);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vector3d.ZERO);
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
    protected void addAdditionalSaveData(CompoundNBT compound) {
        compound.putString("Type", this.getUGBoatType().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT compound) {
        if (compound.contains("Type", 8)) {
            this.setBoatType(UGBoatEntity.Type.getTypeFromString(compound.getString("Type")));
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
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
