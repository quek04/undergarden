package quek.undergarden.event;

import net.minecraft.commands.Commands;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.living.EnderManAngerEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingKnockBackEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;
import quek.undergarden.UGRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.block.portal.UndergardenPortalVisuals;
import quek.undergarden.command.InfectionCommand;
import quek.undergarden.entity.animal.dweller.Dweller;
import quek.undergarden.entity.monster.denizen.Denizen;
import quek.undergarden.entity.monster.stoneborn.trading.StonebornTrade;
import quek.undergarden.entity.monster.stoneborn.trading.StonebornTradeSet;
import quek.undergarden.item.bucket.ItemAccessBucketHandler;
import quek.undergarden.network.CreateCritParticlePacket;
import quek.undergarden.network.UndergardenPortalSoundPacket;
import quek.undergarden.registry.*;
import quek.undergarden.world.gen.UGNoiseBasedChunkGenerator;

import java.util.List;

public class UndergardenCommonEvents {

	public static void initCommonEvents(IEventBus bus) {
		UndergardenToolEvents.setupToolEvents();
		UthericInfectionEvents.init();
		UndergardenSpawnEvents.init();
		bus.addListener(UndergardenCommonEvents::registerPackets);
		bus.addListener(UndergardenCommonEvents::registerCapabilities);
		bus.addListener(UndergardenCommonEvents::registerBETypes);
		bus.addListener(UndergardenCommonEvents::setup);
		bus.addListener(UndergardenCommonEvents::registerEntityAttributes);
		bus.addListener(UndergardenCommonEvents::registerSpawnPlacements);
		bus.addListener(UndergardenCommonEvents::registerDataMaps);
		bus.addListener(UGCreativeModeTabs::registerBuckets);

		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::registerCommands);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::tickPortalLogic);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::blockToolInteractions);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::applyBrittleness);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::applyFeatherweight);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::cancelPlayerFallDamageOnDweller);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::lookedAtEndermanWithGloomgourd);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::registerPotionRecipes);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::angerDenizensWhenCampfireIsBroken);
		NeoForge.EVENT_BUS.addListener(UndergardenCommonEvents::ignoreEffects);
		NeoForge.EVENT_BUS.addListener(OnDatapackSyncEvent.class, event -> event.sendRecipes(UGRecipeTypes.INFUSING.get()));

//		if (ModList.get().isLoaded("create")) {
//			UGCreateCompat.init(bus);
//		}

		bus.addListener(RegisterEvent.class, event -> {
			if (event.getRegistry() == BuiltInRegistries.CHUNK_GENERATOR) {
				Registry.register(BuiltInRegistries.CHUNK_GENERATOR, Undergarden.prefix("noise"), UGNoiseBasedChunkGenerator.CODEC);
			}
		});
		bus.addListener(NewRegistryEvent.class, event -> event.register(UGRegistries.HIT_EFFECT_TYPE));
		bus.addListener(DataPackRegistryEvent.NewRegistry.class, event -> {
			event.dataPackRegistry(UGRegistries.Keys.STONEBORN_TRADE, StonebornTrade.CODEC, StonebornTrade.CODEC);
			event.dataPackRegistry(UGRegistries.Keys.STONEBORN_TRADE_SET, StonebornTradeSet.CODEC, StonebornTradeSet.CODEC);
		});
	}

	private static void registerPackets(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar(Undergarden.MODID).versioned("1.0.0").optional();
		registrar.playToClient(CreateCritParticlePacket.TYPE, CreateCritParticlePacket.STREAM_CODEC, CreateCritParticlePacket::handle);
		registrar.playToClient(UndergardenPortalSoundPacket.TYPE, UndergardenPortalSoundPacket.STREAM_CODEC, (payload, context) -> UndergardenPortalSoundPacket.handle(context));
	}

	private static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerItem(Capabilities.Fluid.ITEM, (object, context) -> new ItemAccessBucketHandler(context, UGDataComponents.STORED_FLUID.get(), FluidType.BUCKET_VOLUME), UGItems.CLOGGRUM_BUCKET);
	}

	private static void registerBETypes(BlockEntityTypeAddBlocksEvent event) {
		event.modify(BlockEntityType.SIGN,
			UGBlocks.SMOGSTEM_SIGN.get(), UGBlocks.SMOGSTEM_WALL_SIGN.get(),
			UGBlocks.WIGGLEWOOD_SIGN.get(), UGBlocks.WIGGLEWOOD_WALL_SIGN.get(),
			UGBlocks.GRONGLE_SIGN.get(), UGBlocks.GRONGLE_WALL_SIGN.get(),
			UGBlocks.ANCIENT_ROOT_SIGN.get(), UGBlocks.ANCIENT_ROOT_WALL_SIGN.get());

		event.modify(BlockEntityType.HANGING_SIGN,
			UGBlocks.SMOGSTEM_HANGING_SIGN.get(), UGBlocks.SMOGSTEM_WALL_HANGING_SIGN.get(),
			UGBlocks.WIGGLEWOOD_HANGING_SIGN.get(), UGBlocks.WIGGLEWOOD_WALL_HANGING_SIGN.get(),
			UGBlocks.GRONGLE_HANGING_SIGN.get(), UGBlocks.GRONGLE_WALL_HANGING_SIGN.get(),
			UGBlocks.ANCIENT_ROOT_HANGING_SIGN.get(), UGBlocks.ANCIENT_ROOT_WALL_HANGING_SIGN.get());
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
			UGRecipePropertySets.registerPropertySets();

			FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;

			pot.addPlant(UGBlocks.SMOGSTEM_SAPLING.getId(), UGBlocks.POTTED_SMOGSTEM_SAPLING);
			pot.addPlant(UGBlocks.WIGGLEWOOD_SAPLING.getId(), UGBlocks.POTTED_WIGGLEWOOD_SAPLING);
			pot.addPlant(UGBlocks.SHIMMERWEED.getId(), UGBlocks.POTTED_SHIMMERWEED);
			pot.addPlant(UGBlocks.INDIGO_MUSHROOM.getId(), UGBlocks.POTTED_INDIGO_MUSHROOM);
			pot.addPlant(UGBlocks.VEIL_MUSHROOM.getId(), UGBlocks.POTTED_VEIL_MUSHROOM);
			pot.addPlant(UGBlocks.INK_MUSHROOM.getId(), UGBlocks.POTTED_INK_MUSHROOM);
			pot.addPlant(UGBlocks.BLOOD_MUSHROOM.getId(), UGBlocks.POTTED_BLOOD_MUSHROOM);
			pot.addPlant(UGBlocks.PUFF_MUSHROOM.getId(), UGBlocks.POTTED_PUFF_MUSHROOM);
			pot.addPlant(UGBlocks.GRONGLE_SAPLING.getId(), UGBlocks.POTTED_GRONGLE_SAPLING);
			pot.addPlant(UGBlocks.AMOROUS_BRISTLE.getId(), UGBlocks.POTTED_AMOROUS_BRISTLE);
			pot.addPlant(UGBlocks.MISERABELL.getId(), UGBlocks.POTTED_MISERABELL);
			pot.addPlant(UGBlocks.BUTTERBUNCH.getId(), UGBlocks.POTTED_BUTTERBUNCH);

			WoodType.register(UGWoodStuff.SMOGSTEM_WOOD_TYPE);
			WoodType.register(UGWoodStuff.WIGGLEWOOD_WOOD_TYPE);
			WoodType.register(UGWoodStuff.GRONGLE_WOOD_TYPE);
			WoodType.register(UGWoodStuff.ANCIENT_ROOT_WOOD_TYPE);

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
			fire.setFlammable(UGBlocks.UTHERIC_GRONGLET.get(), 100, 100);
			fire.setFlammable(UGBlocks.ROGDORIC_GRONGLET.get(), 100, 100);
		});
	}

	@SuppressWarnings("unchecked") //entities added this way will always extend LivingEntity
	private static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		UGEntityTypes.ATTRIBUTES.forEach(
			(type, builder) ->
				event.put(
					(EntityType<? extends LivingEntity>) type.value(),
					builder.get().build()
				)
		);
	}

	@SuppressWarnings("unchecked") //PAIN
	private static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		UGEntityTypes.SPAWN_PREDICATES.forEach(
			(type, info) ->
				event.register((EntityType<Entity>) type.value(),
					info.placement(),
					info.heightmap(),
					(SpawnPlacements.SpawnPredicate<Entity>) info.predicate(),
					RegisterSpawnPlacementsEvent.Operation.REPLACE)
		);
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
			if (event.getEffectInstance().is(UGEffects.GOOEY) && event.getEntity().is(UGTags.Entities.IMMUNE_TO_GOOEY_EFFECT)) {
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
		if (!event.isCanceled() && event.getEntity().level() instanceof ServerLevel level) {
			EnderMan enderMan = event.getEntity();
			Player player = event.getPlayer();
			if (!player.isCreative() && player.getItemBySlot(EquipmentSlot.HEAD).is(UGBlocks.CARVED_GLOOMGOURD.asItem()) && !event.getEntity().isAngryAt(player, level) && enderMan.isLookingAtMe(player, 0.025, true, false, enderMan.getEyeY())) {
				level.getEntitiesOfClass(EnderMan.class, event.getEntity().getBoundingBox().inflate(64.0F), checked -> checked.hasLineOfSight(event.getPlayer())).forEach(checked -> checked.setTarget(player));
			}
		}
	}

	public static void angerDenizensWhenCampfireIsBroken(BreakBlockEvent event) {
		if (!event.getPlayer().isCreative() && UGPointOfInterests.DENIZEN_RESTING_BLOCKS.get().matchingStates().contains(event.getState()) && !event.getLevel().isClientSide()) {
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

	public static void registerDataMaps(RegisterDataMapTypesEvent event) {
		event.register(UGDataMaps.BIOME_LETHALITY);
		event.register(UGDataMaps.ENTITY_LETHALITY);
	}

	private static void registerCommands(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("undergarden").then(InfectionCommand.register()));
	}
}