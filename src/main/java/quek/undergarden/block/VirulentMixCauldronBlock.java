package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

import quek.undergarden.registry.UGCauldronInteraction;
import quek.undergarden.registry.UGEffects;
import quek.undergarden.registry.UGSoundEvents;
import quek.undergarden.registry.UGTags;

import java.util.Random;

public class VirulentMixCauldronBlock extends AbstractCauldronBlock {

    public VirulentMixCauldronBlock(Properties properties) {
        super(properties, UGCauldronInteraction.VIRULENT_MIX);
    }

    @Override
    public boolean isFull(BlockState state) {
        return true;
    }

    @Override
    protected double getContentHeight(BlockState state) {
        return 0.9375D;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return 3;
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if(entity.isAlive() && entity instanceof LivingEntity livingEntity) {
            if(livingEntity.getType().is(UGTags.Entities.IMMUNE_TO_VIRULENT_MIX)) {}
            else if(livingEntity.hasEffect(UGEffects.VIRULENT_RESISTANCE.get())) {}
            else livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 0));
        }
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, Random rand) {
        if (rand.nextInt(200) == 0) {
            world.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), UGSoundEvents.VIRULENT_BUBBLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
        }
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(Items.CAULDRON);
    }
}