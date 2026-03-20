package quek.undergarden.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGCauldronInteractions;
import quek.undergarden.registry.UGEffects;
import quek.undergarden.registry.UGTags;

public class VirulentMixCauldronBlock extends AbstractCauldronBlock {

	public static final MapCodec<VirulentMixCauldronBlock> CODEC = simpleCodec(VirulentMixCauldronBlock::new);

	public VirulentMixCauldronBlock(Properties properties) {
		super(properties, UGCauldronInteractions.VIRULENT_MIX);
	}

	@Override
	protected MapCodec<? extends AbstractCauldronBlock> codec() {
		return CODEC;
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
	protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
		return 3;
	}

	@Override
	protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
		if (entity.isAlive() && entity instanceof LivingEntity livingEntity) {
			if (livingEntity.is(UGTags.Entities.IMMUNE_TO_VIRULENT_MIX) || livingEntity.hasEffect(UGEffects.VIRULENT_RESISTANCE)) return;
			livingEntity.addEffect(new MobEffectInstance(UGEffects.VIRULENCE, 200, 0));
		}
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
		return new ItemStack(Items.CAULDRON);
	}
}