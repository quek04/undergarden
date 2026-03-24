package quek.undergarden.event;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import quek.undergarden.Undergarden;
import quek.undergarden.network.CreateCritParticlePacket;
import quek.undergarden.registry.UGEffects;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGParticleTypes;
import quek.undergarden.registry.UGTags;

public class UndergardenToolEvents {

	protected static void setupToolEvents() {
		NeoForge.EVENT_BUS.addListener(UndergardenToolEvents::forgottenAttackEvent);
		NeoForge.EVENT_BUS.addListener(UndergardenToolEvents::forgottenDigEvent);
		NeoForge.EVENT_BUS.addListener(UndergardenToolEvents::utheriumAttackEvent);
		NeoForge.EVENT_BUS.addListener(UndergardenToolEvents::froststeelAttackEvent);
		NeoForge.EVENT_BUS.addListener(UndergardenToolEvents::froststeelTickEvent);
	}

	private static void forgottenAttackEvent(LivingIncomingDamageEvent event) {
		Entity source = event.getSource().getEntity();
		float damage = event.getAmount();

		if (source instanceof Player player) {
			if (player.getMainHandItem().is(UGItems.FORGOTTEN_SWORD) || player.getMainHandItem().is(UGItems.FORGOTTEN_AXE) || player.getMainHandItem().is(UGItems.FORGOTTEN_BATTLEAXE) || player.getMainHandItem().is(UGItems.FORGOTTEN_SPEAR)) {
				if (BuiltInRegistries.ENTITY_TYPE.getKey(event.getEntity().getType()).getNamespace().equals(Undergarden.MODID) && !event.getEntity().is(Tags.EntityTypes.BOSSES)) {
					event.setAmount(damage * 1.5F);
				}
			}
		}
	}

	private static void forgottenDigEvent(PlayerEvent.BreakSpeed event) {
		Player player = event.getEntity();
		BlockState state = event.getState();

		if (player.getMainHandItem().is(UGItems.FORGOTTEN_PICKAXE) || player.getMainHandItem().is(UGItems.FORGOTTEN_AXE) || player.getMainHandItem().is(UGItems.FORGOTTEN_SHOVEL) || player.getMainHandItem().is(UGItems.FORGOTTEN_HOE)) {
			if (BuiltInRegistries.BLOCK.getKey(state.getBlock()).getNamespace().equals(Undergarden.MODID)) {
				event.setNewSpeed(event.getOriginalSpeed() * 1.5F);
			}
		}
	}

	private static void utheriumAttackEvent(LivingIncomingDamageEvent event) {
		Entity source = event.getSource().getEntity();
		float damage = event.getAmount();

		if (source instanceof Player player) {
			if (player.getMainHandItem().is(UGItems.UTHERIUM_SWORD.get()) || player.getMainHandItem().is(UGItems.UTHERIUM_AXE.get()) || player.getMainHandItem().is(UGItems.UTHERIUM_SPEAR)) {
				if (event.getEntity().is(UGTags.Entities.ROTSPAWN)) {
					event.setAmount(damage * 1.5F);
					if (!event.getEntity().level().isClientSide()) {
						PacketDistributor.sendToPlayersTrackingEntity(event.getEntity(), new CreateCritParticlePacket(event.getEntity().getId(), 2, UGParticleTypes.UTHERIUM_CRIT.get()));
					}
				}
			}
		}
	}

	private static void froststeelAttackEvent(LivingIncomingDamageEvent event) {
		Entity source = event.getSource().getEntity();
		if (source instanceof Player player) {
			if (player.getMainHandItem().is(UGItems.FROSTSTEEL_SWORD.get()) || player.getMainHandItem().is(UGItems.FROSTSTEEL_AXE.get()) || player.getMainHandItem().is(UGItems.FROSTSTEEL_SPEAR)) {
				event.getEntity().addEffect(new MobEffectInstance(UGEffects.CHILLY, 600, 2, false, false));
			}
			if (player.getMainHandItem().is(UGItems.FROSTSTEEL_PICKAXE.get()) || player.getMainHandItem().is(UGItems.FROSTSTEEL_SHOVEL.get())) {
				event.getEntity().addEffect(new MobEffectInstance(UGEffects.CHILLY, 600, 1, false, false));
			}
		}
	}

	private static void froststeelTickEvent(EntityTickEvent.Pre event) {
		Entity entity = event.getEntity();
		if (entity instanceof LivingEntity living) {
			if (living.tickCount % 5 == 0 && living.level().isClientSide() && living.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(UGEffects.CHILLY_MODIFIER) != null) {
				for (int i = 0; i < 5; i++) {
					double d0 = living.getRandom().nextFloat() * 2.0F - 1.0F;
					double d1 = living.getRandom().nextFloat() * 2.0F - 1.0F;
					double d2 = living.getRandom().nextFloat() * 2.0F - 1.0F;
					if (!(d0 * d0 + d1 * d1 + d2 * d2 > 1.0D)) {
						double d3 = living.getX(d0 / 2.0D);
						double d4 = living.getY(0.75D + d1 / 4.0D);
						double d5 = living.getZ(d2 / 2.0D);
						living.level().addParticle(UGParticleTypes.SNOWFLAKE.get(), d3, d4, d5, d0, d1 + 0.2D, d2);
					}
				}
			}
		}
	}
}