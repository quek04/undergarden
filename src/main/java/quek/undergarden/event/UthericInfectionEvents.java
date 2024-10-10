package quek.undergarden.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import quek.undergarden.component.RogdoriumInfusion;
import quek.undergarden.datamap.BiomeLethality;
import quek.undergarden.network.UthericInfectionPacket;
import quek.undergarden.registry.*;

public class UthericInfectionEvents {

	protected static void init() {
		NeoForge.EVENT_BUS.addListener(UthericInfectionEvents::tickWorldInfection);
		NeoForge.EVENT_BUS.addListener(UthericInfectionEvents::syncUthericInfectionOnLogin);
		NeoForge.EVENT_BUS.addListener(UthericInfectionEvents::syncUthericInfectionOnDimensionChange);
	}

	private static void tickWorldInfection(EntityTickEvent.Pre event) {
		Entity entity = event.getEntity();
		if (entity instanceof LivingEntity livingEntity) {
			if (livingEntity.tickCount % 20 == 0 && !livingEntity.level().isClientSide() && !livingEntity.getType().is(UGTags.Entities.IMMUNE_TO_INFECTION)) {
				double data = livingEntity.getData(UGAttachments.UTHERIC_INFECTION);
				if (data >= 20.0D) {
					livingEntity.hurt(livingEntity.damageSources().source(UGDamageSources.UTHERIC_INFECTION), 2.0F);
				} else {
					BiomeLethality biomeLethality = livingEntity.level().getBiome(livingEntity.blockPosition()).getData(UGDataMaps.BIOME_LETHALITY);
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
					sendSyncPacket(livingEntity);
				}
				if (livingEntity instanceof ServerPlayer player) {
					UGCriteria.UTHERIC_INFECTION.get().trigger(player, livingEntity.getData(UGAttachments.UTHERIC_INFECTION));
				}
			}
		}
	}

	private static void syncUthericInfectionOnLogin(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getEntity().level().isClientSide()) {
			sendSyncPacket(event.getEntity());
		}
	}

	private static void syncUthericInfectionOnDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getEntity().level().isClientSide()) {
			sendSyncPacket(event.getEntity());
		}
	}

	protected static void sendSyncPacket(LivingEntity entity) {
		PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, new UthericInfectionPacket(entity.getId(), entity.getData(UGAttachments.UTHERIC_INFECTION)));
	}
}
