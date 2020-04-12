package quek.undergarden.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class GwiblingEntity extends AbstractFishEntity {
    public GwiblingEntity(EntityType<? extends AbstractFishEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected ItemStack getFishBucket() {
        return null;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_COD_FLOP;
    }
}
