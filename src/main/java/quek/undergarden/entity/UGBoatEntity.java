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

    private static final DataParameter<Integer> BOAT_TYPE = EntityDataManager.createKey(UGBoatEntity.class, DataSerializers.VARINT);

    public UGBoatEntity(EntityType<? extends BoatEntity> type, World world) {
        super(type, world);
        this.preventEntitySpawning = true;
    }

    public UGBoatEntity(World worldIn, double x, double y, double z) {
        this(UGEntityTypes.BOAT.get(), worldIn);
        this.setPosition(x, y, z);
        this.setMotion(Vector3d.ZERO);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    public UGBoatEntity.Type getUGBoatType() {
        return UGBoatEntity.Type.byId(this.dataManager.get(BOAT_TYPE));
    }

    @Override
    public Item getItemBoat() {
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
        this.dataManager.set(BOAT_TYPE, boatType.ordinal());
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(BOAT_TYPE, Type.SMOGSTEM.ordinal());
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
        compound.putString("Type", this.getUGBoatType().getName());
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
        if (compound.contains("Type", 8)) {
            this.setBoatType(UGBoatEntity.Type.getTypeFromString(compound.getString("Type")));
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
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
