package quek.undergarden.event;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
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
		if (!event.isCanceled() && event.getSource().getWeaponItem() != null) {
			float damage = event.getAmount();
			ItemStack weapon = event.getSource().getWeaponItem();
			if (weapon.is(UGItems.FORGOTTEN_SWORD) || weapon.is(UGItems.FORGOTTEN_AXE) || weapon.is(UGItems.FORGOTTEN_BATTLEAXE)) {
				if (BuiltInRegistries.ENTITY_TYPE.getKey(event.getEntity().getType()).getNamespace().equals(Undergarden.MODID) && !event.getEntity().getType().is(Tags.EntityTypes.BOSSES)) {
					event.setAmount(damage * 1.5F);
				}
			}
		}
	}

	private static void forgottenDigEvent(PlayerEvent.BreakSpeed event) {
		if (!event.isCanceled()) {
			BlockState state = event.getState();
			ItemStack tool = event.getEntity().getMainHandItem();

			if (tool.is(UGItems.FORGOTTEN_PICKAXE) || tool.is(UGItems.FORGOTTEN_AXE) || tool.is(UGItems.FORGOTTEN_SHOVEL) || tool.is(UGItems.FORGOTTEN_HOE)) {
				if (BuiltInRegistries.BLOCK.getKey(state.getBlock()).getNamespace().equals(Undergarden.MODID)) {
					event.setNewSpeed(event.getOriginalSpeed() * 1.5F);
				}
			}
		}
	}

	private static void utheriumAttackEvent(LivingIncomingDamageEvent event) {
		if (!event.isCanceled() && event.getSource().getWeaponItem() != null) {
			float damage = event.getAmount();
			ItemStack weapon = event.getSource().getWeaponItem();
			if (weapon.is(UGItems.UTHERIUM_SWORD.get()) || weapon.is(UGItems.UTHERIUM_AXE.get())) {
				if (event.getEntity().getType().is(UGTags.Entities.ROTSPAWN)) {
					event.setAmount(damage * 1.5F);
					if (!event.getEntity().level().isClientSide()) {
						PacketDistributor.sendToPlayersTrackingEntity(event.getEntity(), new CreateCritParticlePacket(event.getEntity().getId(), 2, UGParticleTypes.UTHERIUM_CRIT.get()));
					}
				}
			}
		}
	}

	private static void froststeelAttackEvent(LivingIncomingDamageEvent event) {
		if (!event.isCanceled() && event.getSource().getWeaponItem() != null) {
			ItemStack weapon = event.getSource().getWeaponItem();
			if (weapon.is(UGItems.FROSTSTEEL_SWORD.get()) || weapon.is(UGItems.FROSTSTEEL_AXE.get())) {
				event.getEntity().addEffect(new MobEffectInstance(UGEffects.CHILLY, 600, 2, false, false));
			}
			if (weapon.is(UGItems.FROSTSTEEL_PICKAXE.get()) || weapon.is(UGItems.FROSTSTEEL_SHOVEL.get())) {
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
						living.level().addParticle(UGParticleTypes.SNOWFLAKE.get(), false, d3, d4, d5, d0, d1 + 0.2D, d2);
					}
				}
			}
		}
	}
}