package quek.undergarden.block.tileentity;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import quek.undergarden.entity.rotspawn.AbstractRotspawnEntity;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGParticleTypes;
import quek.undergarden.registry.UGTileEntities;

import java.util.Random;

public class ShardTorchTE extends TileEntity implements ITickableTileEntity {

    public ShardTorchTE() {
        super(UGTileEntities.shard_torch_te.get());
    }

    @Override
    public void tick() {
        if(world.getGameTime() % 20 == 0) { // every second
            world.getEntitiesWithinAABB(AbstractRotspawnEntity.class, new AxisAlignedBB(pos.getX() + -4, pos.getY() + -4, pos.getZ() + -4, pos.getX() + 4, pos.getY() + 4, pos.getZ() + 4), entity -> entity.getCreatureAttribute() == UGEntityTypes.ROTSPAWN).forEach(entity -> entity.attackEntityFrom(DamageSource.GENERIC, 4));
        }
    }
}
