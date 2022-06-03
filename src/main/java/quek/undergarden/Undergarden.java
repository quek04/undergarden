package quek.undergarden;

import net.minecraft.Util;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quek.undergarden.client.UndergardenClient;
import quek.undergarden.data.*;
import quek.undergarden.entity.projectile.Blisterbomb;
import quek.undergarden.entity.projectile.slingshot.*;
import quek.undergarden.item.tool.slingshot.AbstractSlingshotAmmoBehavior;
import quek.undergarden.item.tool.slingshot.SlingshotItem;
import quek.undergarden.registry.*;

@Mod(Undergarden.MODID)
public class Undergarden {
	
	public static final String MODID = "undergarden";
	public static final Logger LOGGER = LogManager.getLogger();

	public Undergarden() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::gatherData);

		DeferredRegister<?>[] registers = {
				UGBiomes.BIOMES,
				UGBlockEntities.BLOCK_ENTITIES,
				UGBlocks.BLOCKS,
				UGCarvers.CARVERS,
				UGConfiguredCarvers.CONFIGURED_CARVERS,
				UGConfiguredFeatures.CONFIGURED_FEATURES,
				UGEffects.EFFECTS,
				UGEnchantments.ENCHANTMENTS,
				UGEntityTypes.ENTITIES,
				UGFeatures.FEATURES,
				UGFluids.FLUIDS,
				UGItems.ITEMS,
				UGParticleTypes.PARTICLES,
				UGPlacedFeatures.PLACED_FEATURES,
				UGPointOfInterests.POI,
				UGPotions.POTIONS,
				UGSoundEvents.SOUNDS,
				UGStructures.STRUCTURES,
				UGTreeDecoratorTypes.TREE_DECORATORS,
				UGTrunkPlacerTypes.TRUNK_PLACERS,
		};

		for (DeferredRegister<?> register : registers) {
			register.register(bus);
		}
	}

	public void setup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			UGEntityTypes.spawnPlacements();
			UGCriteria.register();
			UGBiomes.toDictionary();
			UGCauldronInteractions.register();

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

			DispenserBlock.registerBehavior(UGItems.VIRULENT_MIX_BUCKET.get(), bucketBehavior);
			DispenserBlock.registerBehavior(UGItems.GWIBLING_BUCKET.get(), bucketBehavior);

			DispenserBlock.registerBehavior(UGItems.DEPTHROCK_PEBBLE.get(), new AbstractProjectileDispenseBehavior() {
				protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn) {
					return Util.make(new DepthrockPebble(worldIn, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stackIn));
				}
			});

			DispenserBlock.registerBehavior(UGItems.GOO_BALL.get(), new AbstractProjectileDispenseBehavior() {
				protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn) {
					return Util.make(new GooBall(worldIn, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stackIn));
				}
			});

			DispenserBlock.registerBehavior(UGItems.ROTTEN_BLISTERBERRY.get(), new AbstractProjectileDispenseBehavior() {
				protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn) {
					return Util.make(new RottenBlisterberry(worldIn, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stackIn));
				}
			});

			DispenserBlock.registerBehavior(UGItems.BLISTERBOMB.get(), new AbstractProjectileDispenseBehavior() {
				protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn) {
					return Util.make(new Blisterbomb(worldIn, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stackIn));
				}
			});

			DispenserBlock.registerBehavior(UGBlocks.GRONGLET.get(), new AbstractProjectileDispenseBehavior() {
				protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
					return Util.make(new Gronglet(level, position.x(), position.y(), position.z()), (entity) -> entity.setItem(stack));
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
			//other
			fire.setFlammable(UGBlocks.MOGMOSS_RUG.get(), 60, 20);
			fire.setFlammable(UGBlocks.BOOMGOURD.get(), 15, 100);
			fire.setFlammable(UGBlocks.GRONGLET.get(), 100, 100);
		});
	}

	public void clientSetup(FMLClientSetupEvent event) {
		UndergardenClient.registerBlockRenderers();
		event.enqueueWork(() -> {
			Sheets.addWoodType(UGBlocks.SMOGSTEM_WOODTYPE);
			Sheets.addWoodType(UGBlocks.WIGGLEWOOD_WOODTYPE);
			Sheets.addWoodType(UGBlocks.GRONGLE_WOODTYPE);
		});

		ItemProperties.register(UGItems.SLINGSHOT.get(), new ResourceLocation("pull"), (stack, world, entity, seed) -> {
			if (entity == null) {
				return 0.0F;
			} else {
				return entity.getUseItem() != stack ? 0.0F : (float)(stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
			}
		});
		ItemProperties.register(UGItems.SLINGSHOT.get(), new ResourceLocation("rotten_blisterberry"), (stack, level, entity, seed) -> entity != null && entity.getProjectile(stack).is(UGItems.ROTTEN_BLISTERBERRY.get()) ? 1.0F : 0.0F);
		ItemProperties.register(UGItems.SLINGSHOT.get(), new ResourceLocation("goo_ball"), (stack, level, entity, seed) -> entity != null && entity.getProjectile(stack).is(UGItems.GOO_BALL.get()) ? 1.0F : 0.0F);
		ItemProperties.register(UGItems.SLINGSHOT.get(), new ResourceLocation("gronglet"), (stack, level, entity, seed) -> entity != null && entity.getProjectile(stack).is(UGBlocks.GRONGLET.get().asItem()) ? 1.0F : 0.0F);
		ItemProperties.register(UGItems.SLINGSHOT.get(), new ResourceLocation("self_sling"), (stack, level, entity, seed) -> entity != null && EnchantmentHelper.getItemEnchantmentLevel(UGEnchantments.SELF_SLING.get(), stack) > 0 ? 1.0F : 0.0F);
		ItemProperties.register(UGItems.SLINGSHOT.get(), new ResourceLocation("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
		ItemProperties.register(UGItems.CLOGGRUM_SHIELD.get(), new ResourceLocation("blocking"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);

		DimensionSpecialEffects.EFFECTS.put(UGDimensions.UNDERGARDEN_LEVEL.location(), new DimensionSpecialEffects(Float.NaN, true, DimensionSpecialEffects.SkyType.NONE, false, true) {
			@Override
			public Vec3 getBrightnessDependentFogColor(Vec3 fogColor, float brightness) {
				return fogColor;
			}

			@Override
			public boolean isFoggyAt(int x, int y) {
				return false;
			}
		});
		//TODO: OthersideDSE
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
			generator.addProvider(new UGAdvancements(generator, helper));
			generator.addProvider(new UGFluidTags(generator, helper));
		}
	}
}