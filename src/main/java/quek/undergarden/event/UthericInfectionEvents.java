package quek.undergarden.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import quek.undergarden.Undergarden;
import quek.undergarden.component.RogdoriumInfusion;
import quek.undergarden.datamap.UthericInfectionLethality;
import quek.undergarden.network.UthericInfectionPacket;
import quek.undergarden.registry.*;

public class UthericInfectionEvents {

	public static final double MAX_INFECTION = 20.0D;

	protected static void init() {
		NeoForge.EVENT_BUS.addListener(UthericInfectionEvents::tickUthericInfection);
		NeoForge.EVENT_BUS.addListener(UthericInfectionEvents::onEntityHit);
		NeoForge.EVENT_BUS.addListener(UthericInfectionEvents::syncUthericInfectionOnLogin);
		NeoForge.EVENT_BUS.addListener(UthericInfectionEvents::syncUthericInfectionOnDimensionChange);
	}

	private static void tickUthericInfection(EntityTickEvent.Pre event) {
		Entity entity = event.getEntity();
		if (entity instanceof LivingEntity livingEntity) {
			if (livingEntity instanceof Player player && player.getAbilities().invulnerable) {
				return;
			}
			if (livingEntity.tickCount % 20 == 0 && !livingEntity.level().isClientSide() && !livingEntity.getType().is(UGTags.Entities.IMMUNE_TO_INFECTION)) {
				double data = livingEntity.getData(UGAttachments.UTHERIC_INFECTION);
				if (data >= MAX_INFECTION) {
					float baseDamage = 2.0F;
					float previousDamage = livingEntity.getData(UGAttachments.PREVIOUS_UTHERIC_INFECTION_DAMAGE);
					livingEntity.setData(UGAttachments.PREVIOUS_UTHERIC_INFECTION_DAMAGE.get(), previousDamage += baseDamage);
					float amount = baseDamage * previousDamage;
					livingEntity.hurt(livingEntity.damageSources().source(UGDamageSources.UTHERIC_INFECTION), amount);
					Undergarden.LOGGER.debug("damage: {}", amount);
				} else {
					UthericInfectionLethality biomeLethality = livingEntity.level().getBiome(livingEntity.blockPosition()).getData(UGDataMaps.BIOME_LETHALITY);
					float b = biomeLethality == null ? 0.0F : biomeLethality.lethality();
					int a = 0;
					if (b > 0.0F) {
						if (livingEntity instanceof Player player) {
							for (int i = 0; i < 4; i++) {
								ItemStack armor = player.getInventory().getArmor(i);
								int infusionAmount = armor.getOrDefault(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.DEFAULT).infusionAmount();
								if (infusionAmount > 0) {
									armor.set(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.setInfusionAmount(infusionAmount - 1));
									a++;
								}
							}
						}
						double t = b / ((1 + a) * 0.18D);
						livingEntity.setData(UGAttachments.UTHERIC_INFECTION, data + t);
					} else if (livingEntity.tickCount % 400 == 0 && data > 0.0D) {
						livingEntity.setData(UGAttachments.UTHERIC_INFECTION, data - 1.0D);
					} else if (data < 0.0D) {
						livingEntity.setData(UGAttachments.UTHERIC_INFECTION, 0.0D);
					}
					sendInfectionSyncPacket(livingEntity);
				}
				if (livingEntity instanceof ServerPlayer player) {
					UGCriteria.UTHERIC_INFECTION.get().trigger(player, livingEntity.getData(UGAttachments.UTHERIC_INFECTION));
				}
			}
		}
	}

	private static void onEntityHit(LivingIncomingDamageEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof LivingEntity livingEntity) {
			if (!livingEntity.level().isClientSide() && !livingEntity.getType().is(UGTags.Entities.IMMUNE_TO_INFECTION)) {
				double data = livingEntity.getData(UGAttachments.UTHERIC_INFECTION);
				if (event.getSource().getEntity() != null) {
					UthericInfectionLethality entityLethality = event.getSource().getEntity().getType().builtInRegistryHolder().getData(UGDataMaps.ENTITY_LETHALITY);
					if (entityLethality != null) {
						float b = entityLethality.lethality();
						int a = 0;
						if (livingEntity instanceof Player player) {
							for (int i = 0; i < 4; i++) {
								ItemStack armor = player.getInventory().getArmor(i);
								int infusionAmount = armor.getOrDefault(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.DEFAULT).infusionAmount();
								if (infusionAmount > 0) {
									armor.set(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.setInfusionAmount(infusionAmount - 1));
									a++;
								}
							}
						}
						double t = b / ((1 + a) * 0.18D);
						livingEntity.setData(UGAttachments.UTHERIC_INFECTION, data + t);

						sendInfectionSyncPacket(livingEntity);
					}
				}
			}
		}
	}

	private static void syncUthericInfectionOnLogin(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getEntity().level().isClientSide()) {
			sendInfectionSyncPacket(event.getEntity());
		}
	}

	private static void syncUthericInfectionOnDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getEntity().level().isClientSide()) {
			sendInfectionSyncPacket(event.getEntity());
		}
	}

	public static void sendInfectionSyncPacket(Entity entity) {
		PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, new UthericInfectionPacket(
			entity.getId(),
			entity.getData(UGAttachments.UTHERIC_INFECTION),
			entity.getData(UGAttachments.PREVIOUS_UTHERIC_INFECTION_DAMAGE)
		));
	}
}