package quek.undergarden.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import quek.undergarden.component.RogdoriumInfusion;
import quek.undergarden.component.UndergardenData;
import quek.undergarden.datamap.UthericInfectionLethality;
import quek.undergarden.registry.*;

public class UthericInfectionEvents {

	public static final float MAX_INFECTION = 20.0F;

	protected static void init() {
		NeoForge.EVENT_BUS.addListener(UthericInfectionEvents::tickUthericInfection);
		NeoForge.EVENT_BUS.addListener(UthericInfectionEvents::onEntityHit);
	}

	private static void tickUthericInfection(EntityTickEvent.Pre event) {
		Entity entity = event.getEntity();
		if (entity instanceof LivingEntity livingEntity) {
			if (livingEntity instanceof Player player && player.getAbilities().invulnerable) {
				return;
			}
			if (livingEntity.tickCount % 20 == 0 && livingEntity.level() instanceof ServerLevel level && !livingEntity.is(UGTags.Entities.IMMUNE_TO_INFECTION)) {
				UndergardenData data = livingEntity.getData(UGAttachments.UNDERGARDEN_DATA);
				if (data.uthericInfection() >= MAX_INFECTION) {
					if (livingEntity.tickCount % 60 == 0) {
						int hits = livingEntity.getData(UGAttachments.UNDERGARDEN_DATA).uthericHits();
						float amount = (float) Math.pow(2, hits / 2.0F);
						livingEntity.hurtServer(level, livingEntity.damageSources().source(UGDamageSources.UTHERIC_INFECTION), amount);
						livingEntity.setData(UGAttachments.UNDERGARDEN_DATA, livingEntity.getData(UGAttachments.UNDERGARDEN_DATA).setHits(hits + 1));
						livingEntity.syncData(UGAttachments.UNDERGARDEN_DATA);
					}
				} else {
					float infection = data.uthericInfection();
					float newInfection = infection;
					UthericInfectionLethality biomeLethality = livingEntity.level().getBiome(livingEntity.blockPosition()).getData(UGDataMaps.BIOME_LETHALITY);
					float b = biomeLethality == null ? 0.0F : biomeLethality.lethality();
					int a = 0;
					if (b > 0.0F) {
						if (livingEntity instanceof Player player) {
							for (EquipmentSlot slot : EquipmentSlotGroup.ARMOR) {
								ItemStack armor = player.getInventory().getItem(slot.getIndex(Inventory.INVENTORY_SIZE));
								int infusionAmount = armor.getOrDefault(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.DEFAULT).infusionAmount();
								if (infusionAmount > 0) {
									armor.set(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.setInfusionAmount(infusionAmount - 1));
									a++;
								}
							}
						}
						float t = b / ((1 + a) * 0.18F);
						newInfection = infection + t;
					} else if (livingEntity.tickCount % 400 == 0 && infection > 0.0D) {
						newInfection = infection - 1.0F;
					} else if (data.uthericInfection() < 0.0D) {
						newInfection = 0.0F;
					}
					if (infection != newInfection) {
						livingEntity.setData(UGAttachments.UNDERGARDEN_DATA, data.setInfectionLevel(newInfection));
						livingEntity.syncData(UGAttachments.UNDERGARDEN_DATA);
					}
				}
				if (livingEntity instanceof ServerPlayer player) {
					UGCriteria.UTHERIC_INFECTION.get().trigger(player, livingEntity.getData(UGAttachments.UNDERGARDEN_DATA).uthericInfection());
				}
			}
		}
	}

	private static void onEntityHit(LivingIncomingDamageEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof LivingEntity livingEntity) {
			if (!livingEntity.level().isClientSide() && !livingEntity.is(UGTags.Entities.IMMUNE_TO_INFECTION)) {
				UndergardenData data = livingEntity.getData(UGAttachments.UNDERGARDEN_DATA);
				if (event.getSource().getEntity() != null) {
					UthericInfectionLethality entityLethality = event.getSource().getEntity().getType().builtInRegistryHolder().getData(UGDataMaps.ENTITY_LETHALITY);
					if (entityLethality != null) {
						float b = entityLethality.lethality();
						int a = 0;
						if (livingEntity instanceof Player player) {
							for (EquipmentSlot slot : EquipmentSlotGroup.ARMOR) {
								ItemStack armor = player.getInventory().getItem(slot.getIndex(Inventory.INVENTORY_SIZE));
								int infusionAmount = armor.getOrDefault(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.DEFAULT).infusionAmount();
								if (infusionAmount > 0) {
									armor.set(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.setInfusionAmount(infusionAmount - 1));
									a++;
								}
							}
						}
						float t = b / ((1 + a) * 0.18F);
						livingEntity.setData(UGAttachments.UNDERGARDEN_DATA, data.setInfectionLevel(data.uthericInfection() + t));
						livingEntity.syncData(UGAttachments.UNDERGARDEN_DATA);
					}
				}
			}
		}
	}
}