package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import quek.undergarden.registry.UGEffects;
import quek.undergarden.registry.UGSoundEvents;
import quek.undergarden.registry.UGTags;

import java.util.Optional;

public class VirulentMixBlock extends LiquidBlock {

	public VirulentMixBlock(FlowingFluid supplier, Properties properties) {
		super(supplier, properties.noCollision().strength(100F).noLootTable().lightLevel((state) -> 10));
	}

	@Override
	protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
		if (entity.isAlive() && entity instanceof LivingEntity livingEntity) {
			if (livingEntity.is(UGTags.Entities.IMMUNE_TO_VIRULENT_MIX) || livingEntity.hasEffect(UGEffects.VIRULENT_RESISTANCE))
				return;
			livingEntity.addEffect(new MobEffectInstance(UGEffects.VIRULENCE, 200, 0));
		}
	}

	@Override
	public Optional<SoundEvent> getPickupSound(BlockState state) {
		return Optional.of(UGSoundEvents.BUCKET_FILL_VIRULENT.get());
	}
}