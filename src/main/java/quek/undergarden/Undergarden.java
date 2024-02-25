package quek.undergarden;

import net.minecraft.DetectedVersion;
import net.minecraft.Util;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.*;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegisterEvent;
import quek.undergarden.capability.IUndergardenPortal;
import quek.undergarden.data.*;
import quek.undergarden.entity.Boomgourd;
import quek.undergarden.entity.projectile.Blisterbomb;
import quek.undergarden.entity.projectile.slingshot.*;
import quek.undergarden.item.tool.slingshot.AbstractSlingshotAmmoBehavior;
import quek.undergarden.item.tool.slingshot.SlingshotItem;
import quek.undergarden.network.UGPacketHandler;
import quek.undergarden.registry.*;
import quek.undergarden.world.gen.UGNoiseBasedChunkGenerator;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mod(Undergarden.MODID)
public class Undergarden {

	public static final String MODID = "undergarden";

	public Undergarden() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::gatherData);
		MinecraftForge.EVENT_BUS.addListener(this::portalTick);
		bus.addListener((Consumer<RegisterEvent>) event -> Registry.register(BuiltInRegistries.CHUNK_GENERATOR, new ResourceLocation(MODID, "noise"), UGNoiseBasedChunkGenerator.CODEC));

		DeferredRegister<?>[] registers = {
				UGBlockEntities.BLOCK_ENTITIES,
				UGBlocks.BLOCKS,
				UGCarvers.CARVERS,
				UGEffects.EFFECTS,
				UGEnchantments.ENCHANTMENTS,
				UGEntityTypes.ENTITIES,
				UGFeatures.FEATURES,
				UGFluids.FLUIDS,
				UGFoliagePlacers.FOLIAGE_PLACERS,
				UGItems.ITEMS,
				UGParticleTypes.PARTICLES,
				UGPointOfInterests.POI,
				UGPotions.POTIONS,
				UGSoundEvents.SOUNDS,
				UGStructureProcessors.PROCESSORS,
				UGStructures.STRUCTURES,
				UGCreativeModeTabs.TABS,
				UGTreeDecoratorTypes.TREE_DECORATORS,
				UGTrunkPlacerTypes.TRUNK_PLACERS,
				UGFluids.TYPES
		};

		for (DeferredRegister<?> register : registers) {
			register.register(bus);
		}

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, UndergardenConfig.COMMON_SPEC);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, UndergardenConfig.CLIENT_SPEC);
	}

	public void setup(FMLCommonSetupEvent event) {
		FluidInteractionRegistry.addInteraction(UGFluids.VIRULENT_MIX_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
				ForgeMod.WATER_TYPE.get(),
				fluidState -> UGBlocks.DEPTHROCK.get().defaultBlockState()
		));
		FluidInteractionRegistry.addInteraction(UGFluids.VIRULENT_MIX_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
				ForgeMod.LAVA_TYPE.get(),
				fluidState -> fluidState.isSource() ? Blocks.OBSIDIAN.defaultBlockState() : UGBlocks.SHIVERSTONE.get().defaultBlockState()
		));
		event.enqueueWork(() -> {
			UGPacketHandler.init();
			UGCriteria.register();
			UGCauldronInteractions.register();

			DispenseItemBehavior bucketBehavior = new DefaultDispenseItemBehavior() {
				private final DefaultDispenseItemBehavior defaultBehavior = new DefaultDispenseItemBehavior();

				public ItemStack execute(BlockSource source, ItemStack stack) {
					BucketItem bucketitem = (BucketItem) stack.getItem();
					BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
					Level world = source.getLevel();
					if (bucketitem.emptyContents(null, world, blockpos, null)) {
						bucketitem.checkExtraContent(null, world, stack, blockpos);
						return new ItemStack(Items.BUCKET);
					} else {
						return this.defaultBehavior.dispense(source, stack);
					}
				}
			};

			DispenserBlock.registerBehavior(UGItems.VIRULENT_MIX_BUCKET.get(), bucketBehavior);
			DispenserBlock.registerBehavior(UGItems.GWIBLING_BUCKET.get(), bucketBehavior);

			DispenserBlock.registerBehavior(UGItems.DEPTHROCK_PEBBLE.get(), new AbstractProjectileDispenseBehavior() {
				protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
					return Util.make(new DepthrockPebble(level, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stack));
				}
			});

			DispenserBlock.registerBehavior(UGItems.GOO_BALL.get(), new AbstractProjectileDispenseBehavior() {
				protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
					return Util.make(new GooBall(level, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stack));
				}
			});

			DispenserBlock.registerBehavior(UGItems.ROTTEN_BLISTERBERRY.get(), new AbstractProjectileDispenseBehavior() {
				protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
					return Util.make(new RottenBlisterberry(level, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stack));
				}
			});

			DispenserBlock.registerBehavior(UGItems.BLISTERBOMB.get(), new AbstractProjectileDispenseBehavior() {
				protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
					return Util.make(new Blisterbomb(level, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stack));
				}
			});

			DispenserBlock.registerBehavior(UGBlocks.GRONGLET.get(), new AbstractProjectileDispenseBehavior() {
				protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
					return Util.make(new Gronglet(level, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stack));
				}
			});

			DispenserBlock.registerBehavior(UGBlocks.BOOMGOURD.get(), new DefaultDispenseItemBehavior() {

				protected ItemStack execute(BlockSource source, ItemStack stack) {
					Level level = source.getLevel();
					BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
					Boomgourd gourd = new Boomgourd(level, (double) blockpos.getX() + 0.5D, blockpos.getY(), (double) blockpos.getZ() + 0.5D, null);
					level.addFreshEntity(gourd);
					level.playSound(null, gourd.getX(), gourd.getY(), gourd.getZ(), UGSoundEvents.BOOMGOURD_PRIMED.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
					level.gameEvent(null, GameEvent.ENTITY_PLACE, blockpos);
					stack.shrink(1);
					return stack;
				}
			});

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

			ComposterBlock.add(0.1F, UGItems.DROOPFRUIT.get());
			ComposterBlock.add(0.1F, UGItems.UNDERBEANS.get());
			ComposterBlock.add(0.2F, UGItems.BLISTERBERRY.get());
			ComposterBlock.add(0.2F, UGItems.ROTTEN_BLISTERBERRY.get());
			ComposterBlock.add(0.2F, UGItems.BLOOD_GLOBULE.get());
			ComposterBlock.add(0.3F, UGItems.GLOOMGOURD_SEEDS.get());
			ComposterBlock.add(0.3F, UGItems.GLITTERKELP.get());
			ComposterBlock.add(0.3F, UGBlocks.SMOGSTEM_LEAVES.get());
			ComposterBlock.add(0.3F, UGBlocks.WIGGLEWOOD_LEAVES.get());
			ComposterBlock.add(0.3F, UGBlocks.GRONGLE_LEAVES.get());
			ComposterBlock.add(0.3F, UGBlocks.SMOGSTEM_SAPLING.get());
			ComposterBlock.add(0.3F, UGBlocks.WIGGLEWOOD_SAPLING.get());
			ComposterBlock.add(0.3F, UGBlocks.GRONGLE_SAPLING.get());
			ComposterBlock.add(0.3F, UGBlocks.DEEPTURF.get());
			ComposterBlock.add(0.3F, UGBlocks.SHIMMERWEED.get());
			ComposterBlock.add(0.5F, UGBlocks.TALL_DEEPTURF.get());
			ComposterBlock.add(0.5F, UGItems.DITCHBULB.get());
			ComposterBlock.add(0.5F, UGBlocks.TALL_SHIMMERWEED.get());
			ComposterBlock.add(0.65F, UGBlocks.INDIGO_MUSHROOM.get());
			ComposterBlock.add(0.65F, UGBlocks.VEIL_MUSHROOM.get());
			ComposterBlock.add(0.65F, UGBlocks.INK_MUSHROOM.get());
			ComposterBlock.add(0.65F, UGBlocks.INDIGO_MUSHROOM.get());
			ComposterBlock.add(0.65F, UGBlocks.GLOOMGOURD.get());
			ComposterBlock.add(0.65F, UGBlocks.CARVED_GLOOMGOURD.get());
			ComposterBlock.add(0.85F, UGBlocks.INDIGO_MUSHROOM_CAP.get());
			ComposterBlock.add(0.85F, UGBlocks.INDIGO_MUSHROOM_STEM.get());
			ComposterBlock.add(0.85F, UGBlocks.VEIL_MUSHROOM_CAP.get());
			ComposterBlock.add(0.85F, UGBlocks.VEIL_MUSHROOM_STEM.get());
			ComposterBlock.add(0.85F, UGBlocks.INK_MUSHROOM_CAP.get());
			ComposterBlock.add(0.85F, UGBlocks.BLOOD_MUSHROOM_CAP.get());
			ComposterBlock.add(0.85F, UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP.get());
			ComposterBlock.add(0.85F, UGBlocks.BLOOD_MUSHROOM_STEM.get());

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

	public void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			Sheets.addWoodType(UGWoodStuff.SMOGSTEM_WOOD_TYPE);
			Sheets.addWoodType(UGWoodStuff.WIGGLEWOOD_WOOD_TYPE);
			Sheets.addWoodType(UGWoodStuff.GRONGLE_WOOD_TYPE);

			ItemProperties.register(UGItems.SLINGSHOT.get(), new ResourceLocation("pull"), (stack, world, entity, seed) -> {
				if (entity == null) {
					return 0.0F;
				} else {
					return entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
				}
			});
			ItemProperties.register(UGItems.SLINGSHOT.get(), new ResourceLocation("rotten_blisterberry"), (stack, level, entity, seed) -> entity != null && entity.getProjectile(stack).is(UGItems.ROTTEN_BLISTERBERRY.get()) ? 1.0F : 0.0F);
			ItemProperties.register(UGItems.SLINGSHOT.get(), new ResourceLocation("goo_ball"), (stack, level, entity, seed) -> entity != null && entity.getProjectile(stack).is(UGItems.GOO_BALL.get()) ? 1.0F : 0.0F);
			ItemProperties.register(UGItems.SLINGSHOT.get(), new ResourceLocation("gronglet"), (stack, level, entity, seed) -> entity != null && entity.getProjectile(stack).is(UGBlocks.GRONGLET.get().asItem()) ? 1.0F : 0.0F);
			ItemProperties.register(UGItems.SLINGSHOT.get(), new ResourceLocation("self_sling"), (stack, level, entity, seed) -> entity != null && stack.getEnchantmentLevel(UGEnchantments.SELF_SLING.get()) > 0 ? 1.0F : 0.0F);
			ItemProperties.register(UGItems.SLINGSHOT.get(), new ResourceLocation("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
			ItemProperties.register(UGItems.CLOGGRUM_SHIELD.get(), new ResourceLocation("blocking"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
		});
	}

	public void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		generator.addProvider(event.includeClient(), new UGBlockStates(output, helper));
		generator.addProvider(event.includeClient(), new UGItemModels(output, helper));
		generator.addProvider(event.includeClient(), new UGLang(output));
		generator.addProvider(event.includeClient(), new UGSoundDefinitions(output, helper));


		generator.addProvider(event.includeServer(), new UGRecipes(output));
		generator.addProvider(event.includeServer(), new UGLootTables(output));
		UGBlockTags blockTags = new UGBlockTags(output, provider, helper);
		generator.addProvider(event.includeServer(), blockTags);
		generator.addProvider(event.includeServer(), new UGItemTags(output, provider, blockTags.contentsGetter(), helper));
		generator.addProvider(event.includeServer(), new UGEntityTags(output, provider, helper));
		generator.addProvider(event.includeServer(), new UGAdvancements(output, provider, helper));
		generator.addProvider(event.includeServer(), new UGFluidTags(output, provider, helper));
		DatapackBuiltinEntriesProvider datapackProvider = new UGRegistries(output, provider);
		CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();
		generator.addProvider(event.includeServer(), datapackProvider);
		generator.addProvider(event.includeServer(), new UGBiomeTags(output, lookupProvider, helper));
		generator.addProvider(event.includeServer(), new UGDamageTypeTags(output, lookupProvider, helper));
		generator.addProvider(event.includeServer(), new UGStructureUpdater("structures", output, helper));

		generator.addProvider(true, new PackMetadataGenerator(output).add(PackMetadataSection.TYPE, new PackMetadataSection(
				Component.literal("Undergarden resources"),
				DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
				Arrays.stream(PackType.values()).collect(Collectors.toMap(Function.identity(), DetectedVersion.BUILT_IN::getPackVersion)))));

	}

	public void portalTick(LivingEvent.LivingTickEvent event) {
		LivingEntity entity = event.getEntity();
		if (entity instanceof Player player) {
			player.getCapability(UndergardenCapabilities.UNDERGARDEN_PORTAL_CAPABILITY).ifPresent(IUndergardenPortal::handleUndergardenPortal);
		}
	}
}