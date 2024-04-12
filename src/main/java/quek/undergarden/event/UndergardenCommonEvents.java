package quek.undergarden.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.ToolAction;
import net.neoforged.neoforge.common.ToolActions;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.SpawnPlacementRegisterEvent;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import net.neoforged.neoforge.network.PacketDistributor;
import quek.undergarden.entity.Minion;
import quek.undergarden.entity.animal.*;
import quek.undergarden.entity.animal.dweller.Dweller;
import quek.undergarden.entity.monster.Forgotten;
import quek.undergarden.entity.monster.boss.ForgottenGuardian;
import quek.undergarden.entity.monster.cavern.CavernMonster;
import quek.undergarden.entity.monster.cavern.Muncher;
import quek.undergarden.entity.monster.cavern.Nargoyle;
import quek.undergarden.entity.monster.cavern.Sploogie;
import quek.undergarden.entity.monster.denizen.Denizen;
import quek.undergarden.entity.monster.rotspawn.Rotbeast;
import quek.undergarden.entity.monster.rotspawn.Rotling;
import quek.undergarden.entity.monster.rotspawn.RotspawnMonster;
import quek.undergarden.entity.monster.rotspawn.Rotwalker;
import quek.undergarden.entity.monster.stoneborn.Stoneborn;
import quek.undergarden.entity.projectile.slingshot.*;
import quek.undergarden.item.tool.slingshot.AbstractSlingshotAmmoBehavior;
import quek.undergarden.item.tool.slingshot.SlingshotItem;
import quek.undergarden.network.UthericInfectionPacket;
import quek.undergarden.registry.*;

import java.util.List;

public class UndergardenCommonEvents {

	public static void initCommonEvents(IEventBus bus) {
		UndergardenToolEvents.setupToolEvents();
		bus.addListener(UndergardenCommonEvents::setup);
		bus.addListener(UndergardenCommonEvents::registerEntityAttributes);
		bus.addListener(UndergardenCommonEvents::registerSpawnPlacements);

		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::tickPortalLogic);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::tickUthericInfection);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::syncUthericInfectionOnLogin);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::syncUthericInfectionOnDimensionChange);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::blockToolInteractions);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::applyBrittleness);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::applyFeatherweight);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::cancelPlayerFallDamageOnDweller);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::lookedAtEndermanWithGloomgourd);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::angerDenizensWhenCampfireIsBroken);
	}

	private static void setup(FMLCommonSetupEvent event) {
		FluidInteractionRegistry.addInteraction(UGFluids.VIRULENT_MIX_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
				NeoForgeMod.WATER_TYPE.value(),
				fluidState -> UGBlocks.DEPTHROCK.get().defaultBlockState()
		));
		FluidInteractionRegistry.addInteraction(UGFluids.VIRULENT_MIX_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
				NeoForgeMod.LAVA_TYPE.value(),
				fluidState -> fluidState.isSource() ? Blocks.OBSIDIAN.defaultBlockState() : UGBlocks.SHIVERSTONE.get().defaultBlockState()
		));
		event.enqueueWork(() -> {
			UGCauldronInteractions.register();
			UGDispenserBehaviors.register();

			PotionBrewing.addMix(Potions.AWKWARD, UGItems.BLOOD_GLOBULE.get(), UGPotions.BRITTLENESS.get());
			PotionBrewing.addMix(UGPotions.BRITTLENESS.get(), Items.REDSTONE, UGPotions.LONG_BRITTLENESS.get());
			PotionBrewing.addMix(UGPotions.BRITTLENESS.get(), Items.GLOWSTONE_DUST, UGPotions.STRONG_BRITTLENESS.get());

			PotionBrewing.addMix(Potions.AWKWARD, UGBlocks.VEIL_MUSHROOM.get().asItem(), UGPotions.FEATHERWEIGHT.get());
			PotionBrewing.addMix(UGPotions.FEATHERWEIGHT.get(), Items.REDSTONE, UGPotions.LONG_FEATHERWEIGHT.get());
			PotionBrewing.addMix(UGPotions.FEATHERWEIGHT.get(), Items.GLOWSTONE_DUST, UGPotions.STRONG_FEATHERWEIGHT.get());

			PotionBrewing.addMix(Potions.AWKWARD, UGBlocks.GLOOMGOURD.get().asItem(), UGPotions.VIRULENT_RESISTANCE.get());
			PotionBrewing.addMix(UGPotions.VIRULENT_RESISTANCE.get(), Items.REDSTONE, UGPotions.LONG_VIRULENT_RESISTANCE.get());

			PotionBrewing.addMix(Potions.AWKWARD, UGItems.DROOPFRUIT.get(), UGPotions.GLOWING.get());
			PotionBrewing.addMix(UGPotions.GLOWING.get(), Items.REDSTONE, UGPotions.LONG_GLOWING.get());

			FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;

			pot.addPlant(UGBlocks.SMOGSTEM_SAPLING.getId(), UGBlocks.POTTED_SMOGSTEM_SAPLING);
			pot.addPlant(UGBlocks.WIGGLEWOOD_SAPLING.getId(), UGBlocks.POTTED_WIGGLEWOOD_SAPLING);
			pot.addPlant(UGBlocks.SHIMMERWEED.getId(), UGBlocks.POTTED_SHIMMERWEED);
			pot.addPlant(UGBlocks.INDIGO_MUSHROOM.getId(), UGBlocks.POTTED_INDIGO_MUSHROOM);
			pot.addPlant(UGBlocks.VEIL_MUSHROOM.getId(), UGBlocks.POTTED_VEIL_MUSHROOM);
			pot.addPlant(UGBlocks.INK_MUSHROOM.getId(), UGBlocks.POTTED_INK_MUSHROOM);
			pot.addPlant(UGBlocks.BLOOD_MUSHROOM.getId(), UGBlocks.POTTED_BLOOD_MUSHROOM);
			pot.addPlant(UGBlocks.GRONGLE_SAPLING.getId(), UGBlocks.POTTED_GRONGLE_SAPLING);
			pot.addPlant(UGBlocks.AMOROUS_BRISTLE.getId(), UGBlocks.POTTED_AMOROUS_BRISTLE);
			pot.addPlant(UGBlocks.MISERABELL.getId(), UGBlocks.POTTED_MISERABELL);
			pot.addPlant(UGBlocks.BUTTERBUNCH.getId(), UGBlocks.POTTED_BUTTERBUNCH);

			WoodType.register(UGWoodStuff.SMOGSTEM_WOOD_TYPE);
			WoodType.register(UGWoodStuff.WIGGLEWOOD_WOOD_TYPE);
			WoodType.register(UGWoodStuff.GRONGLE_WOOD_TYPE);

			SlingshotItem.registerAmmo(UGItems.DEPTHROCK_PEBBLE.get(), new AbstractSlingshotAmmoBehavior() {
				@Override
				public SlingshotProjectile getProjectile(Level level, BlockPos pos, Player shooter, ItemStack stack) {
					return new DepthrockPebble(level, shooter);
				}
			});

			SlingshotItem.registerAmmo(UGItems.ROTTEN_BLISTERBERRY.get(), new AbstractSlingshotAmmoBehavior() {
				@Override
				public SlingshotProjectile getProjectile(Level level, BlockPos pos, Player shooter, ItemStack stack) {
					return new RottenBlisterberry(level, shooter);
				}
			});

			SlingshotItem.registerAmmo(UGItems.GOO_BALL.get(), new AbstractSlingshotAmmoBehavior() {
				@Override
				public SlingshotProjectile getProjectile(Level level, BlockPos pos, Player shooter, ItemStack stack) {
					return new GooBall(level, shooter);
				}
			});

			SlingshotItem.registerAmmo(UGBlocks.GRONGLET.get(), new AbstractSlingshotAmmoBehavior() {
				@Override
				public SlingshotProjectile getProjectile(Level level, BlockPos pos, Player shooter, ItemStack stack) {
					return new Gronglet(shooter, level);
				}

				@Override
				public SoundEvent getFiringSound() {
					return UGSoundEvents.GRONGLET_SHOOT.get();
				}
			});

			FireBlock fire = (FireBlock) Blocks.FIRE;
			//planks
			fire.setFlammable(UGBlocks.SMOGSTEM_PLANKS.get(), 5, 20);
			fire.setFlammable(UGBlocks.WIGGLEWOOD_PLANKS.get(), 5, 20);
			fire.setFlammable(UGBlocks.GRONGLE_PLANKS.get(), 5, 20);
			fire.setFlammable(UGBlocks.ANCIENT_ROOT_PLANKS.get(), 5, 20);
			//slabs
			fire.setFlammable(UGBlocks.SMOGSTEM_SLAB.get(), 5, 20);
			fire.setFlammable(UGBlocks.WIGGLEWOOD_SLAB.get(), 5, 20);
			fire.setFlammable(UGBlocks.GRONGLE_SLAB.get(), 5, 20);
			//fence gates
			fire.setFlammable(UGBlocks.SMOGSTEM_FENCE_GATE.get(), 5, 20);
			fire.setFlammable(UGBlocks.WIGGLEWOOD_FENCE_GATE.get(), 5, 20);
			fire.setFlammable(UGBlocks.GRONGLE_FENCE_GATE.get(), 5, 20);
			//fences
			fire.setFlammable(UGBlocks.SMOGSTEM_FENCE.get(), 5, 20);
			fire.setFlammable(UGBlocks.WIGGLEWOOD_FENCE.get(), 5, 20);
			fire.setFlammable(UGBlocks.GRONGLE_FENCE.get(), 5, 20);
			//stairs
			fire.setFlammable(UGBlocks.SMOGSTEM_STAIRS.get(), 5, 20);
			fire.setFlammable(UGBlocks.WIGGLEWOOD_STAIRS.get(), 5, 20);
			fire.setFlammable(UGBlocks.GRONGLE_STAIRS.get(), 5, 20);
			//logs
			fire.setFlammable(UGBlocks.SMOGSTEM_LOG.get(), 5, 5);
			fire.setFlammable(UGBlocks.WIGGLEWOOD_LOG.get(), 5, 5);
			fire.setFlammable(UGBlocks.GRONGLE_LOG.get(), 5, 5);
			fire.setFlammable(UGBlocks.ANCIENT_ROOT.get(), 5, 5);
			//stripped logs
			fire.setFlammable(UGBlocks.STRIPPED_SMOGSTEM_LOG.get(), 5, 5);
			fire.setFlammable(UGBlocks.STRIPPED_WIGGLEWOOD_LOG.get(), 5, 5);
			fire.setFlammable(UGBlocks.STRIPPED_GRONGLE_LOG.get(), 5, 5);
			//woods
			fire.setFlammable(UGBlocks.SMOGSTEM_WOOD.get(), 5, 5);
			fire.setFlammable(UGBlocks.WIGGLEWOOD_WOOD.get(), 5, 5);
			fire.setFlammable(UGBlocks.GRONGLE_WOOD.get(), 5, 5);
			//stripped woods
			fire.setFlammable(UGBlocks.STRIPPED_SMOGSTEM_WOOD.get(), 5, 5);
			fire.setFlammable(UGBlocks.STRIPPED_WIGGLEWOOD_WOOD.get(), 5, 5);
			fire.setFlammable(UGBlocks.STRIPPED_GRONGLE_WOOD.get(), 5, 5);
			//leaves
			fire.setFlammable(UGBlocks.SMOGSTEM_LEAVES.get(), 30, 60);
			fire.setFlammable(UGBlocks.WIGGLEWOOD_LEAVES.get(), 30, 60);
			fire.setFlammable(UGBlocks.GRONGLE_LEAVES.get(), 30, 60);
			fire.setFlammable(UGBlocks.HANGING_GRONGLE_LEAVES.get(), 30, 60);
			//plants
			fire.setFlammable(UGBlocks.DEEPTURF.get(), 60, 100);
			fire.setFlammable(UGBlocks.ASHEN_DEEPTURF.get(), 60, 100);
			fire.setFlammable(UGBlocks.FROZEN_DEEPTURF.get(), 60, 100);
			fire.setFlammable(UGBlocks.SHIMMERWEED.get(), 60, 100);
			fire.setFlammable(UGBlocks.TALL_DEEPTURF.get(), 60, 100);
			fire.setFlammable(UGBlocks.TALL_SHIMMERWEED.get(), 60, 100);
			fire.setFlammable(UGBlocks.UNDERBEAN_BUSH.get(), 60, 100);
			fire.setFlammable(UGBlocks.BLISTERBERRY_BUSH.get(), 60, 100);
			fire.setFlammable(UGBlocks.ASHEN_DEEPTURF.get(), 60, 100);
			fire.setFlammable(UGBlocks.DITCHBULB_PLANT.get(), 60, 100);
			fire.setFlammable(UGBlocks.DROOPVINE.get(), 15, 60);
			fire.setFlammable(UGBlocks.DROOPVINE_PLANT.get(), 15, 60);
			fire.setFlammable(UGBlocks.AMOROUS_BRISTLE.get(), 60, 100);
			fire.setFlammable(UGBlocks.MISERABELL.get(), 60, 100);
			fire.setFlammable(UGBlocks.BUTTERBUNCH.get(), 60, 100);
			//other
			fire.setFlammable(UGBlocks.MOGMOSS_RUG.get(), 60, 20);
			fire.setFlammable(UGBlocks.BLUE_MOGMOSS_RUG.get(), 60, 20);
			fire.setFlammable(UGBlocks.BOOMGOURD.get(), 15, 100);
			fire.setFlammable(UGBlocks.GRONGLET.get(), 100, 100);
		});
	}

	private static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
		event.register(UGEntityTypes.GWIBLING.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Gwibling::canGwiblingSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(UGEntityTypes.DWELLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(UGEntityTypes.ROTLING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RotspawnMonster::canRotspawnSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(UGEntityTypes.ROTWALKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RotspawnMonster::canRotspawnSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(UGEntityTypes.ROTBEAST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RotspawnMonster::canRotspawnSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(UGEntityTypes.BRUTE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(UGEntityTypes.SCINTLING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Scintling::canScintlingSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(UGEntityTypes.GLOOMPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(UGEntityTypes.STONEBORN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Stoneborn::canStonebornSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(UGEntityTypes.NARGOYLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CavernMonster::canCreatureSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(UGEntityTypes.MUNCHER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CavernMonster::canCreatureSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(UGEntityTypes.SPLOOGIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CavernMonster::canCreatureSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(UGEntityTypes.GWIB.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Gwib::canGwibSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(UGEntityTypes.MOG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(UGEntityTypes.SMOG_MOG.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SmogMog::checkSmogMogSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(UGEntityTypes.FORGOTTEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(UGEntityTypes.DENIZEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
	}

	private static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(UGEntityTypes.ROTLING.get(), Rotling.registerAttributes().build());
		event.put(UGEntityTypes.ROTWALKER.get(), Rotwalker.registerAttributes().build());
		event.put(UGEntityTypes.ROTBEAST.get(), Rotbeast.registerAttributes().build());
		event.put(UGEntityTypes.DWELLER.get(), Dweller.registerAttributes().build());
		event.put(UGEntityTypes.GWIBLING.get(), AbstractFish.createAttributes().build());
		event.put(UGEntityTypes.BRUTE.get(), Brute.registerAttributes().build());
		event.put(UGEntityTypes.SCINTLING.get(), Scintling.registerAttributes().build());
		event.put(UGEntityTypes.GLOOMPER.get(), Gloomper.registerAttributes().build());
		event.put(UGEntityTypes.STONEBORN.get(), Stoneborn.registerAttributes().build());
		event.put(UGEntityTypes.NARGOYLE.get(), Nargoyle.registerAttributes().build());
		event.put(UGEntityTypes.FORGOTTEN_GUARDIAN.get(), ForgottenGuardian.registerAttributes().build());
		event.put(UGEntityTypes.MUNCHER.get(), Muncher.registerAttributes().build());
		event.put(UGEntityTypes.SPLOOGIE.get(), Sploogie.registerAttributes().build());
		event.put(UGEntityTypes.MINION.get(), Minion.registerAttributes().build());
		event.put(UGEntityTypes.GWIB.get(), Gwib.registerAttributes().build());
		event.put(UGEntityTypes.MOG.get(), Mog.registerAttributes().build());
		event.put(UGEntityTypes.SMOG_MOG.get(), SmogMog.registerAttributes().build());
		event.put(UGEntityTypes.FORGOTTEN.get(), Forgotten.registerAttributes().build());
		event.put(UGEntityTypes.DENIZEN.get(), Denizen.registerAttributes().build());
	}

	private static void tickPortalLogic(LivingEvent.LivingTickEvent event) {
		LivingEntity entity = event.getEntity();
		if (entity instanceof Player player) {
			player.getData(UGAttachments.UNDERGARDEN_PORTAL).handleUndergardenPortal(player);
		}
	}

	private static void tickUthericInfection(LivingEvent.LivingTickEvent event) {
		LivingEntity entity = event.getEntity();
		if (entity.tickCount % 20 == 0 && !entity.level().isClientSide() && !entity.getType().is(UGTags.Entities.IMMUNE_TO_INFECTION)) {
			int data = entity.getData(UGAttachments.UTHERIC_INFECTION);
			if (data >= 20) {
				entity.hurt(entity.damageSources().source(UGDamageSources.UTHERIC_INFECTION), 2.0F);
			} else {
				if (entity.level().getBiome(entity.blockPosition()).is(UGTags.Biomes.TICKS_UTHERIC_INFECTION) && entity.tickCount % 400 == 0) {
					entity.setData(UGAttachments.UTHERIC_INFECTION, data + 1);
				} else {
					if (entity.tickCount % 100 == 0) {
						int blocks = countInfectedBlocksNearby(entity.level(), entity.blockPosition(), entity.getRandom());
						if (blocks > 0) {
							entity.setData(UGAttachments.UTHERIC_INFECTION, data + Mth.clamp(Mth.ceil(Mth.sqrt(blocks / 2.0F) + 1), 1, 5));
						} else if (entity.tickCount % 400 == 0 && data > 0) {
							entity.setData(UGAttachments.UTHERIC_INFECTION, data - 1);
						}
					}
				}
				sendSyncPacket(entity);
			}
			if (entity instanceof ServerPlayer player) {
				UGCriteria.UTHERIC_INFECTION.get().trigger(player, entity.getData(UGAttachments.UTHERIC_INFECTION));
				//Logger.getLogger("infection").info("Entity: " + entity.getType() + "\nInfection Level: " + data);
			}
		}
	}

	private static int countInfectedBlocksNearby(Level level, BlockPos playerPos, RandomSource random) {
		int infected = 0;
		for (int i = 0; i <= 20; i++) {
			BlockPos checkBlock = getRandomBlockNearby(random, playerPos, 5);
			if (level.getBlockState(checkBlock).is(UGTags.Blocks.UTHERIC_INFECTION_BLOCKS)) {
				infected++;
			}
		}
		return infected;
	}

	private static BlockPos getRandomBlockNearby(RandomSource random, BlockPos pos, int range) {
		int dx = random.nextInt(range * 2 + 1) - range;
		int dy = random.nextInt(range * 2 + 1) - range;
		int dz = random.nextInt(range * 2 + 1) - range;
		return pos.offset(dx, dy, dz);
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

	private static void sendSyncPacket(LivingEntity infected) {
		PacketDistributor.TRACKING_ENTITY_AND_SELF.with(infected).send(new UthericInfectionPacket(infected.getId(), infected.getData(UGAttachments.UTHERIC_INFECTION)));
	}

	private static void blockToolInteractions(BlockEvent.BlockToolModificationEvent event) {
		ToolAction action = event.getToolAction();
		BlockState state = event.getState();
		UseOnContext context = event.getContext();
		if (!event.isSimulated()) {
			if (action == ToolActions.AXE_STRIP) {
				if (state.is(UGBlocks.SMOGSTEM_LOG.get())) {
					event.setFinalState(UGBlocks.STRIPPED_SMOGSTEM_LOG.get().withPropertiesOf(state));
				}
				if (state.is(UGBlocks.SMOGSTEM_WOOD.get())) {
					event.setFinalState(UGBlocks.STRIPPED_SMOGSTEM_WOOD.get().withPropertiesOf(state));
				}
				if (state.is(UGBlocks.WIGGLEWOOD_LOG.get())) {
					event.setFinalState(UGBlocks.STRIPPED_WIGGLEWOOD_LOG.get().withPropertiesOf(state));
				}
				if (state.is(UGBlocks.WIGGLEWOOD_WOOD.get())) {
					event.setFinalState(UGBlocks.STRIPPED_WIGGLEWOOD_WOOD.get().withPropertiesOf(state));
				}
				if (state.is(UGBlocks.GRONGLE_LOG.get())) {
					event.setFinalState(UGBlocks.STRIPPED_GRONGLE_LOG.get().withPropertiesOf(state));
				}
				if (state.is(UGBlocks.GRONGLE_WOOD.get())) {
					event.setFinalState(UGBlocks.STRIPPED_GRONGLE_WOOD.get().withPropertiesOf(state));
				}
			}
			if (action == ToolActions.HOE_TILL && (context.getClickedFace() != Direction.DOWN && context.getLevel().getBlockState(context.getClickedPos().above()).isAir())) {
				if (state.is(UGBlocks.DEEPTURF_BLOCK.get()) || state.is(UGBlocks.DEEPSOIL.get()) || state.is(UGBlocks.ASHEN_DEEPTURF_BLOCK.get()) || state.is(UGBlocks.FROZEN_DEEPTURF_BLOCK.get())) {
					event.setFinalState(UGBlocks.DEEPSOIL_FARMLAND.get().defaultBlockState());
				}
				if (state.is(UGBlocks.COARSE_DEEPSOIL.get())) {
					event.setFinalState(UGBlocks.DEEPSOIL.get().defaultBlockState());
				}
			}
		}
	}

	private static void applyBrittleness(LivingDamageEvent event) {
		LivingEntity entity = event.getEntity();
		DamageSource source = event.getSource();
		float damage = event.getAmount();

		if (entity.hasEffect(UGEffects.BRITTLENESS.get()) && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
			int amplifier = (entity.getEffect(UGEffects.BRITTLENESS.get()).getAmplifier() + 1) + (entity.getArmorValue() / 4) * 2;

			event.setAmount(damage + amplifier);
		}
	}

	private static void applyFeatherweight(LivingKnockBackEvent event) {
		LivingEntity entity = event.getEntity();

		if (entity.hasEffect(UGEffects.FEATHERWEIGHT.get())) {
			int amplifier = (entity.getEffect(UGEffects.FEATHERWEIGHT.get()).getAmplifier() + 2);

			event.setStrength(event.getStrength() * amplifier);
		}
	}

	private static void cancelPlayerFallDamageOnDweller(LivingAttackEvent event) {
		if (event.getEntity() instanceof Player player && player.getVehicle() instanceof Dweller && event.getSource().is(DamageTypeTags.IS_FALL)) {
			event.setCanceled(true);
		}
	}

	private static void lookedAtEndermanWithGloomgourd(EnderManAngerEvent event) {
		if (!event.isCanceled() && !event.getPlayer().isCreative() && isPlayerLookingAtEnderman(event.getPlayer(), event.getEntity()) && !event.getEntity().isAngryAt(event.getPlayer()) && event.getPlayer().getItemBySlot(EquipmentSlot.HEAD).is(UGBlocks.CARVED_GLOOMGOURD.get().asItem())) {
			event.getEntity().level().getEntitiesOfClass(EnderMan.class, event.getEntity().getBoundingBox().inflate(64.0F), enderMan -> enderMan.hasLineOfSight(event.getPlayer())).forEach(enderMan -> enderMan.setTarget(event.getPlayer()));
		}
	}

	private static boolean isPlayerLookingAtEnderman(Player player, EnderMan enderMan) {
		Vec3 vec3 = player.getViewVector(1.0F).normalize();
		Vec3 vec31 = new Vec3(enderMan.getX() - player.getX(), enderMan.getEyeY() - player.getEyeY(), enderMan.getZ() - player.getZ());
		double d0 = vec31.length();
		vec31 = vec31.normalize();
		double d1 = vec3.dot(vec31);
		return d1 > 1.0D - 0.025D / d0 && player.hasLineOfSight(enderMan);
	}

	public static void angerDenizensWhenCampfireIsBroken(BlockEvent.BreakEvent event) {
		if (!event.getPlayer().isCreative() && UGPointOfInterests.DENIZEN_RESTING_BLOCKS.get().matchingStates().contains(event.getState())) {
			List<Denizen> nearbyDenizens = event.getLevel().getEntitiesOfClass(Denizen.class, new AABB(event.getPos()).inflate(4.0F));

			if (!nearbyDenizens.isEmpty()) {
				UGCriteria.DENIZEN_CAMPFIRE_DESTROYED.get().trigger((ServerPlayer) event.getPlayer(), event.getState());
				for (Denizen denizen : nearbyDenizens) {
					if (denizen.hasPose(Pose.SITTING)) {
						denizen.setTarget(event.getPlayer());
					}
				}
			}
		}
	}
}
