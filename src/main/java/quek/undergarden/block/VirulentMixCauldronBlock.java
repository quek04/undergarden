package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import quek.undergarden.registry.UGCauldronInteractions;
import quek.undergarden.registry.UGEffects;
import quek.undergarden.registry.UGTags;

public class VirulentMixCauldronBlock extends AbstractCauldronBlock {

	public VirulentMixCauldronBlock(Properties properties) {
		super(properties, UGCauldronInteractions.VIRULENT_MIX);
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
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (entity.isAlive() && entity instanceof LivingEntity livingEntity && this.isEntityInsideContent(state, pos, entity)) {
			if (livingEntity.getType().is(UGTags.Entities.IMMUNE_TO_VIRULENT_MIX)
					|| livingEntity.hasEffect(UGEffects.VIRULENT_RESISTANCE.get())) return;

			livingEntity.addEffect(new MobEffectInstance(UGEffects.VIRULENCE.get(), 200, 0));
		}
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter getter, BlockPos pos, Player player) {
		return new ItemStack(Items.CAULDRON);
	}
}