package quek.undergarden;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.DeferredRegister;
import quek.undergarden.client.UndergardenClient;
import quek.undergarden.data.*;
import quek.undergarden.entity.projectile.*;
import quek.undergarden.item.UGSpawnEggItem;
import quek.undergarden.registry.*;

@Mod(Undergarden.MODID)
public class Undergarden {
	
	public static final String MODID = "undergarden";

	public Undergarden() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::gatherData);

		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		forgeBus.addListener(EventPriority.NORMAL, UGStructures::addDimensionalSpacing);

		DeferredRegister<?>[] registers = {
				UGBiomes.BIOMES,
				UGBlocks.BLOCKS,
				UGCarvers.CARVERS,
				UGEffects.EFFECTS,
				UGEntityTypes.ENTITIES,
				UGFeatures.FEATURES,
				UGFluids.FLUIDS,
				UGItems.ITEMS,
				UGParticleTypes.PARTICLES,
				UGPointOfInterests.POI,
				UGPotions.POTIONS,
				UGSoundEvents.SOUNDS,
				UGStructures.STRUCTURES,
				UGSurfaceBuilders.SURFACE_BUILDERS,
				UGBlockEntities.BLOCK_ENTITIES,
				UGTreeDecoratorTypes.TREE_DECORATORS
		};

		for (DeferredRegister<?> register : registers) {
			register.register(bus);
		}
	}

	public void setup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			UGEntityTypes.spawnPlacements();
			UGFeatures.registerConfiguredFeatures();
			UGCarvers.registerConfiguredCarvers();
			UGStructures.registerStructures();
			UGStructures.registerConfiguredStructures();
			UGCriteria.register();
			UGBiomes.toDictionary();
			UGDimensions.registerDimensionStuff();

			AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
			AxeItem.STRIPPABLES.put(UGBlocks.SMOGSTEM_LOG.get(), UGBlocks.STRIPPED_SMOGSTEM_LOG.get());
			AxeItem.STRIPPABLES.put(UGBlocks.SMOGSTEM_WOOD.get(), UGBlocks.STRIPPED_SMOGSTEM_WOOD.get());
			AxeItem.STRIPPABLES.put(UGBlocks.WIGGLEWOOD_LOG.get(), UGBlocks.STRIPPED_WIGGLEWOOD_LOG.get());
			AxeItem.STRIPPABLES.put(UGBlocks.WIGGLEWOOD_WOOD.get(), UGBlocks.STRIPPED_WIGGLEWOOD_WOOD.get());
			AxeItem.STRIPPABLES.put(UGBlocks.GRONGLE_LOG.get(), UGBlocks.STRIPPED_GRONGLE_LOG.get());
			AxeItem.STRIPPABLES.put(UGBlocks.GRONGLE_WOOD.get(), UGBlocks.STRIPPED_GRONGLE_WOOD.get());

			//TODO
//			HoeItem.TILLABLES.put(UGBlocks.DEEPTURF_BLOCK.get(), Pair.of(HoeItem::onlyIfAirAbove, HoeItem.changeIntoState(UGBlocks.DEEPSOIL_FARMLAND.get().defaultBlockState())));
//			HoeItem.TILLABLES.put(UGBlocks.DEEPSOIL.get(), Pair.of(HoeItem::onlyIfAirAbove, HoeItem.changeIntoState(UGBlocks.DEEPSOIL_FARMLAND.get().defaultBlockState())));
//			HoeItem.TILLABLES.put(UGBlocks.COARSE_DEEPSOIL.get(), Pair.of(HoeItem::onlyIfAirAbove, HoeItem.changeIntoState(UGBlocks.DEEPSOIL.get().defaultBlockState())));

			DispenseItemBehavior bucketBehavior = new DefaultDispenseItemBehavior() {
				private final DefaultDispenseItemBehavior defaultBehavior = new DefaultDispenseItemBehavior();

				public ItemStack execute(BlockSource source, ItemStack stack) {
					BucketItem bucketitem = (BucketItem)stack.getItem();
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

			DefaultDispenseItemBehavior eggBehavior = new DefaultDispenseItemBehavior() {
				public ItemStack execute(BlockSource source, ItemStack stack) {
					Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
					EntityType<?> type = ((UGSpawnEggItem)stack.getItem()).getType(stack.getTag());
					type.spawn(source.getLevel(), stack, null, source.getPos().relative(direction), MobSpawnType.DISPENSER, direction != Direction.UP, false);
					stack.shrink(1);
					return stack;
				}
			};

			DispenserBlock.registerBehavior(UGItems.VIRULENT_MIX_BUCKET.get(), bucketBehavior);
			DispenserBlock.registerBehavior(UGItems.GWIBLING_BUCKET.get(), bucketBehavior);

			for(SpawnEggItem item : UGSpawnEggItem.UGEggs()) {
				DispenserBlock.registerBehavior(item, eggBehavior);
			}

			DispenserBlock.registerBehavior(UGItems.DEPTHROCK_PEBBLE.get(), new AbstractProjectileDispenseBehavior() {
				protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn) {
					return Util.make(new SlingshotAmmoEntity(worldIn, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stackIn));
				}
			});

			DispenserBlock.registerBehavior(UGItems.GOO_BALL.get(), new AbstractProjectileDispenseBehavior() {
				protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn) {
					return Util.make(new GooBallEntity(worldIn, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stackIn));
				}
			});

			DispenserBlock.registerBehavior(UGItems.ROTTEN_BLISTERBERRY.get(), new AbstractProjectileDispenseBehavior() {
				protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn) {
					return Util.make(new RottenBlisterberryEntity(worldIn, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stackIn));
				}
			});

			DispenserBlock.registerBehavior(UGItems.BLISTERBOMB.get(), new AbstractProjectileDispenseBehavior() {
				protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn) {
					return Util.make(new BlisterbombEntity(worldIn, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stackIn));
				}
			});

			PotionBrewing.addMix(Potions.AWKWARD, UGBlocks.BLOOD_MUSHROOM_GLOBULE.get().asItem(), UGPotions.BRITTLENESS.get());
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
			ComposterBlock.add(0.3F, UGItems.GLOOMGOURD_SEEDS.get());
			ComposterBlock.add(0.3F, UGItems.GLOWING_KELP.get());
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
			ComposterBlock.add(0.85F, UGBlocks.INDIGO_MUSHROOM_STALK.get());
			ComposterBlock.add(0.85F, UGBlocks.VEIL_MUSHROOM_CAP.get());
			ComposterBlock.add(0.85F, UGBlocks.VEIL_MUSHROOM_STALK.get());
			ComposterBlock.add(0.85F, UGBlocks.INK_MUSHROOM_CAP.get());
			ComposterBlock.add(0.85F, UGBlocks.BLOOD_MUSHROOM_CAP.get());
			ComposterBlock.add(0.85F, UGBlocks.BLOOD_MUSHROOM_GLOBULE.get());
			ComposterBlock.add(0.85F, UGBlocks.BLOOD_MUSHROOM_STALK.get());

			FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;

			pot.addPlant(UGBlocks.SMOGSTEM_SAPLING.getId(), UGBlocks.POTTED_SMOGSTEM_SAPLING);
			pot.addPlant(UGBlocks.WIGGLEWOOD_SAPLING.getId(), UGBlocks.POTTED_WIGGLEWOOD_SAPLING);
			pot.addPlant(UGBlocks.SHIMMERWEED.getId(), UGBlocks.POTTED_SHIMMERWEED);
			pot.addPlant(UGBlocks.INDIGO_MUSHROOM.getId(), UGBlocks.POTTED_INDIGO_MUSHROOM);
			pot.addPlant(UGBlocks.VEIL_MUSHROOM.getId(), UGBlocks.POTTED_VEIL_MUSHROOM);
			pot.addPlant(UGBlocks.INK_MUSHROOM.getId(), UGBlocks.POTTED_INK_MUSHROOM);
			pot.addPlant(UGBlocks.BLOOD_MUSHROOM.getId(), UGBlocks.POTTED_BLOOD_MUSHROOM);
			pot.addPlant(UGBlocks.GRONGLE_SAPLING.getId(), UGBlocks.POTTED_GRONGLE_SAPLING);

			WoodType.register(UGBlocks.SMOGSTEM_WOODTYPE);
			WoodType.register(UGBlocks.WIGGLEWOOD_WOODTYPE);
			WoodType.register(UGBlocks.GRONGLE_WOODTYPE);
		});
	}

	public void clientSetup(FMLClientSetupEvent event) {
		UndergardenClient.registerBlockRenderers();
		UndergardenClient.registerEntityRenderers();
		UndergardenClient.registerBlockColors();
		UndergardenClient.registerItemColors();
		event.enqueueWork(() -> {
			Sheets.addWoodType(UGBlocks.SMOGSTEM_WOODTYPE);
			Sheets.addWoodType(UGBlocks.WIGGLEWOOD_WOODTYPE);
			Sheets.addWoodType(UGBlocks.GRONGLE_WOODTYPE);
		});
//		ClientRegistry.bindTileEntityRenderer(UGBlockEntities.UNDERGARDEN_SIGN.get(), SignRenderer::new);
//		ClientRegistry.bindTileEntityRenderer(UGBlockEntities.DEPTHROCK_BED.get(), DepthrockBedRender::new);

		ItemProperties.register(UGItems.SLINGSHOT.get(), new ResourceLocation("pull"), (stack, world, entity, seed) -> {
			if (entity == null) {
				return 0.0F;
			} else {
				return entity.getUseItem() != stack ? 0.0F : (float)(stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
			}
		});
		ItemProperties.register(UGItems.SLINGSHOT.get(), new ResourceLocation("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
		ItemProperties.register(UGItems.CLOGGRUM_SHIELD.get(), new ResourceLocation("blocking"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);

		DimensionSpecialEffects.EFFECTS.put(UGDimensions.UNDERGARDEN_DIMENSION.location(), new DimensionSpecialEffects(Float.NaN, false, DimensionSpecialEffects.SkyType.NONE, false, true) {
			@Override
			public Vec3 getBrightnessDependentFogColor(Vec3 vector3d, float sun) {
				return vector3d;
			}

			@Override
			public boolean isFoggyAt(int x, int y) {
				return false;
			}
		});
		//TODO: OthersideDRI
	}

	public void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if(event.includeClient()) {
			generator.addProvider(new UGBlockStates(generator, helper));
			generator.addProvider(new UGItemModels(generator, helper));
			generator.addProvider(new UGLang(generator));
			generator.addProvider(new UGSoundDefinitions(generator, helper));
		}
		if(event.includeServer()) {
			generator.addProvider(new UGRecipes(generator));
			generator.addProvider(new UGLootTables(generator));
			UGBlockTags blockTags = new UGBlockTags(generator, helper);
			generator.addProvider(blockTags);
			generator.addProvider(new UGItemTags(generator, blockTags, helper));
			generator.addProvider(new UGEntityTags(generator, helper));
		}
	}
}