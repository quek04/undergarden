package quek.undergarden.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacementTypes;
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
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.living.EnderManAngerEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingKnockBackEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import quek.undergarden.Undergarden;
import quek.undergarden.block.portal.UndergardenPortalVisuals;
import quek.undergarden.entity.Forgotten;
import quek.undergarden.entity.Minion;
import quek.undergarden.entity.animal.*;
import quek.undergarden.entity.animal.dweller.Dweller;
import quek.undergarden.entity.boss.ForgottenGuardian;
import quek.undergarden.entity.cavern.CavernMonster;
import quek.undergarden.entity.cavern.Muncher;
import quek.undergarden.entity.cavern.Nargoyle;
import quek.undergarden.entity.cavern.Sploogie;
import quek.undergarden.entity.projectile.slingshot.*;
import quek.undergarden.entity.rotspawn.Rotbeast;
import quek.undergarden.entity.rotspawn.Rotling;
import quek.undergarden.entity.rotspawn.RotspawnMonster;
import quek.undergarden.entity.rotspawn.Rotwalker;
import quek.undergarden.entity.stoneborn.Stoneborn;
import quek.undergarden.item.tool.slingshot.AbstractSlingshotAmmoBehavior;
import quek.undergarden.item.tool.slingshot.SlingshotItem;
import quek.undergarden.network.CreateCritParticlePacket;
import quek.undergarden.network.UndergardenPortalSoundPacket;
import quek.undergarden.registry.*;

public class UndergardenCommonEvents {

	public static void initCommonEvents(IEventBus bus) {
		UndergardenToolEvents.setupToolEvents();
		bus.addListener(UndergardenCommonEvents::registerPackets);
		bus.addListener(UndergardenCommonEvents::registerBETypes);
		bus.addListener(UndergardenCommonEvents::setup);
		bus.addListener(UndergardenCommonEvents::registerEntityAttributes);
		bus.addListener(UndergardenCommonEvents::registerSpawnPlacements);

		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::tickPortalLogic);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::blockToolInteractions);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::applyBrittleness);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::applyFeatherweight);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::cancelPlayerFallDamageOnDweller);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::lookedAtEndermanWithGloomgourd);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::registerPotionRecipes);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::ignoreEffects);
	}

	private static void registerPackets(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar(Undergarden.MODID).versioned("1.0.0").optional();
		registrar.playToClient(CreateCritParticlePacket.TYPE, CreateCritParticlePacket.STREAM_CODEC, CreateCritParticlePacket::handle);
		registrar.playToClient(UndergardenPortalSoundPacket.TYPE, UndergardenPortalSoundPacket.STREAM_CODEC, (payload, context) -> UndergardenPortalSoundPacket.handle(context));
	}

	private static void registerBETypes(BlockEntityTypeAddBlocksEvent event) {
		event.modify(BlockEntityType.SIGN,
			UGBlocks.SMOGSTEM_SIGN.get(), UGBlocks.SMOGSTEM_WALL_SIGN.get(),
			UGBlocks.WIGGLEWOOD_SIGN.get(), UGBlocks.WIGGLEWOOD_WALL_SIGN.get(),
			UGBlocks.GRONGLE_SIGN.get(), UGBlocks.GRONGLE_WALL_SIGN.get());

		event.modify(BlockEntityType.HANGING_SIGN,
			UGBlocks.SMOGSTEM_HANGING_SIGN.get(), UGBlocks.SMOGSTEM_WALL_HANGING_SIGN.get(),
			UGBlocks.WIGGLEWOOD_HANGING_SIGN.get(), UGBlocks.WIGGLEWOOD_WALL_HANGING_SIGN.get(),
			UGBlocks.GRONGLE_HANGING_SIGN.get(), UGBlocks.GRONGLE_WALL_HANGING_SIGN.get());
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

	private static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(UGEntityTypes.GWIBLING.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Gwibling::canGwiblingSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(UGEntityTypes.DWELLER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(UGEntityTypes.ROTLING.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RotspawnMonster::canRotspawnSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(UGEntityTypes.ROTWALKER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RotspawnMonster::canRotspawnSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(UGEntityTypes.ROTBEAST.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RotspawnMonster::canRotspawnSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(UGEntityTypes.BRUTE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(UGEntityTypes.SCINTLING.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Scintling::canScintlingSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(UGEntityTypes.GLOOMPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(UGEntityTypes.STONEBORN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Stoneborn::canStonebornSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(UGEntityTypes.NARGOYLE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CavernMonster::canCreatureSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(UGEntityTypes.MUNCHER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CavernMonster::canCreatureSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(UGEntityTypes.SPLOOGIE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CavernMonster::canCreatureSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(UGEntityTypes.GWIB.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Gwib::canGwibSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(UGEntityTypes.MOG.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(UGEntityTypes.SMOG_MOG.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SmogMog::checkSmogMogSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(UGEntityTypes.FORGOTTEN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(UGEntityTypes.FORGOTTEN_GUARDIAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
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
		event.put(UGEntityTypes.FORGOTTEN.get(), Forgotten.createAttributes().build());
	}

	private static void registerPotionRecipes(RegisterBrewingRecipesEvent event) {
		PotionBrewing.Builder builder = event.getBuilder();

		builder.addMix(Potions.AWKWARD, UGItems.BLOOD_GLOBULE.get(), UGPotions.BRITTLENESS);
		builder.addMix(UGPotions.BRITTLENESS, Items.REDSTONE, UGPotions.LONG_BRITTLENESS);
		builder.addMix(UGPotions.BRITTLENESS, Items.GLOWSTONE_DUST, UGPotions.STRONG_BRITTLENESS);

		builder.addMix(Potions.AWKWARD, UGBlocks.VEIL_MUSHROOM.get().asItem(), UGPotions.FEATHERWEIGHT);
		builder.addMix(UGPotions.FEATHERWEIGHT, Items.REDSTONE, UGPotions.LONG_FEATHERWEIGHT);
		builder.addMix(UGPotions.FEATHERWEIGHT, Items.GLOWSTONE_DUST, UGPotions.STRONG_FEATHERWEIGHT);

		builder.addMix(Potions.AWKWARD, UGBlocks.GLOOMGOURD.get().asItem(), UGPotions.VIRULENT_RESISTANCE);
		builder.addMix(UGPotions.VIRULENT_RESISTANCE, Items.REDSTONE, UGPotions.LONG_VIRULENT_RESISTANCE);

		builder.addMix(Potions.AWKWARD, UGItems.DROOPFRUIT.get(), UGPotions.GLOWING);
		builder.addMix(UGPotions.GLOWING, Items.REDSTONE, UGPotions.LONG_GLOWING);
	}

	private static void tickPortalLogic(PlayerTickEvent.Pre event) {
		if (event.getEntity().level().isClientSide()) {
			UndergardenPortalVisuals.handlePortalVisuals(event.getEntity());
		}
	}

	private static void blockToolInteractions(BlockEvent.BlockToolModificationEvent event) {
		ItemAbility action = event.getItemAbility();
		BlockState state = event.getState();
		UseOnContext context = event.getContext();
		if (!event.isSimulated()) {
			if (action == ItemAbilities.AXE_STRIP) {
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
			if (action == ItemAbilities.HOE_TILL && (context.getClickedFace() != Direction.DOWN && context.getLevel().getBlockState(context.getClickedPos().above()).isAir())) {
				if (state.is(UGBlocks.DEEPTURF_BLOCK.get()) || state.is(UGBlocks.DEEPSOIL.get()) || state.is(UGBlocks.ASHEN_DEEPTURF_BLOCK.get()) || state.is(UGBlocks.FROZEN_DEEPTURF_BLOCK.get())) {
					event.setFinalState(UGBlocks.DEEPSOIL_FARMLAND.get().defaultBlockState());
				}
				if (state.is(UGBlocks.COARSE_DEEPSOIL.get())) {
					event.setFinalState(UGBlocks.DEEPSOIL.get().defaultBlockState());
				}
			}
		}
	}

	private static void ignoreEffects(MobEffectEvent.Applicable event) {
		if (event.getEffectInstance() != null) {
			if (event.getEffectInstance().is(UGEffects.GOOEY) && event.getEntity().getType().is(UGTags.Entities.IMMUNE_TO_GOOEY_EFFECT)) {
				event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
			}
		}
	}

	private static void applyBrittleness(LivingIncomingDamageEvent event) {
		LivingEntity entity = event.getEntity();
		DamageSource source = event.getSource();
		float damage = event.getAmount();

		if (entity.hasEffect(UGEffects.BRITTLENESS) && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
			int amplifier = (entity.getEffect(UGEffects.BRITTLENESS).getAmplifier() + 1) + (entity.getArmorValue() / 4) * 2;

			event.setAmount(damage + amplifier);
		}
	}

	private static void applyFeatherweight(LivingKnockBackEvent event) {
		LivingEntity entity = event.getEntity();

		if (entity.hasEffect(UGEffects.FEATHERWEIGHT)) {
			int amplifier = (entity.getEffect(UGEffects.FEATHERWEIGHT).getAmplifier() + 2);

			event.setStrength(event.getStrength() * amplifier);
		}
	}

	private static void cancelPlayerFallDamageOnDweller(LivingIncomingDamageEvent event) {
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
}
