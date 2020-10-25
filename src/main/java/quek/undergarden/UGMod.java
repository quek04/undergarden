package quek.undergarden;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.*;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.data.DataGenerator;
import net.minecraft.dispenser.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;

import org.apache.commons.lang3.tuple.Pair;
import quek.undergarden.client.*;
import quek.undergarden.data.*;
import quek.undergarden.entity.projectile.*;
import quek.undergarden.item.UndergardenSpawnEggItem;
import quek.undergarden.registry.*;

@Mod(UGMod.MODID)
public class UGMod {
	
	public static final String MODID = "undergarden";

	public UGMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::gatherData);

		UGEntityTypes.ENTITIES.register(bus);
		UGBlocks.BLOCKS.register(bus);
		UGItems.ITEMS.register(bus);
		UGFeatures.FEATURES.register(bus);
		UGCarvers.CARVERS.register(bus);
		UGEffects.EFFECTS.register(bus);
		UGFluids.FLUIDS.register(bus);
		UGParticleTypes.PARTICLES.register(bus);
		UGTileEntities.TEs.register(bus);

		final Pair<UGConfig.CommonConfig, ForgeConfigSpec> specPairCommon = new ForgeConfigSpec.Builder().configure(UGConfig.CommonConfig::new);
		final Pair<UGConfig.ClientConfig, ForgeConfigSpec> specPairClient = new ForgeConfigSpec.Builder().configure(UGConfig.ClientConfig::new);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, specPairCommon.getRight());
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, specPairClient.getRight());
	}

	public void setup(FMLCommonSetupEvent event) {
		UGEntityTypes.spawnPlacements();
		UGEntityTypes.entityAttributes();
		UGFeatures.registerConfiguredFeatures();
		UGCarvers.registerConfiguredCarvers();
		UGCriteria.register();

		AxeItem.BLOCK_STRIPPING_MAP = ImmutableMap.<Block, Block>builder()
				.putAll(AxeItem.BLOCK_STRIPPING_MAP)
				.put(UGBlocks.smogstem_log.get(), UGBlocks.stripped_smogstem_log.get())
				.put(UGBlocks.smogstem_wood.get(), UGBlocks.stripped_smogstem_wood.get())
				.put(UGBlocks.wigglewood_log.get(), UGBlocks.stripped_wigglewood_log.get())
				.put(UGBlocks.wigglewood_wood.get(), UGBlocks.stripped_wigglewood_wood.get())
				.put(UGBlocks.grongle_stem.get(), UGBlocks.stripped_grongle_stem.get())
				.put(UGBlocks.grongle_hyphae.get(), UGBlocks.stripped_grongle_hyphae.get())
				.build();

		IDispenseItemBehavior bucketBehavior = new DefaultDispenseItemBehavior() {
			private final DefaultDispenseItemBehavior defaultBehavior = new DefaultDispenseItemBehavior();

			public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
				BucketItem bucketitem = (BucketItem)stack.getItem();
				BlockPos blockpos = source.getBlockPos().offset(source.getBlockState().get(DispenserBlock.FACING));
				World world = source.getWorld();
				if (bucketitem.tryPlaceContainedLiquid(null, world, blockpos, null)) {
					bucketitem.onLiquidPlaced(world, stack, blockpos);
					return new ItemStack(Items.BUCKET);
				} else {
					return this.defaultBehavior.dispense(source, stack);
				}
			}
		};

		DefaultDispenseItemBehavior eggBehavior = new DefaultDispenseItemBehavior() {
			public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
				Direction direction = source.getBlockState().get(DispenserBlock.FACING);
				EntityType<?> type = ((UndergardenSpawnEggItem)stack.getItem()).getType(stack.getTag());
				type.spawn(source.getWorld(), stack, null, source.getBlockPos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
				stack.shrink(1);
				return stack;
			}
		};

		DispenserBlock.registerDispenseBehavior(UGItems.virulent_mix_bucket.get(), bucketBehavior);
		DispenserBlock.registerDispenseBehavior(UGItems.gwibling_bucket.get(), bucketBehavior);

		DispenserBlock.registerDispenseBehavior(UGItems.dweller_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UGItems.gwibling_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UGItems.rotdweller_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UGItems.rotling_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UGItems.rotwalker_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UGItems.rotbeast_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UGItems.brute_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UGItems.scintling_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UGItems.gloomper_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UGItems.stoneborn_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UGItems.masticator_spawn_egg.get(), eggBehavior);

		DispenserBlock.registerDispenseBehavior(UGItems.depthrock_pebble.get(), new ProjectileDispenseBehavior() {
			protected ProjectileEntity getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
				return Util.make(new SlingshotAmmoEntity(worldIn, position.getX(), position.getY(), position.getZ()), (entity) -> entity.setItem(stackIn));
			}
		});

		DispenserBlock.registerDispenseBehavior(UGItems.goo_ball.get(), new ProjectileDispenseBehavior() {
			protected ProjectileEntity getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
				return Util.make(new GooBallEntity(worldIn, position.getX(), position.getY(), position.getZ()), (entity) -> entity.setItem(stackIn));
			}
		});

		DispenserBlock.registerDispenseBehavior(UGItems.rotten_blisterberry.get(), new ProjectileDispenseBehavior() {
			protected ProjectileEntity getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
				return Util.make(new RottenBlisterberryEntity(worldIn, position.getX(), position.getY(), position.getZ()), (entity) -> entity.setItem(stackIn));
			}
		});

		DispenserBlock.registerDispenseBehavior(UGItems.blisterbomb.get(), new ProjectileDispenseBehavior() {
			protected ProjectileEntity getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
				return Util.make(new BlisterbombEntity(worldIn, position.getX(), position.getY(), position.getZ()), (entity) -> entity.setItem(stackIn));
			}
		});
	}

	public void clientSetup(FMLClientSetupEvent event) {
		ClientStuff.registerBlockRenderers();
		ClientStuff.registerEntityRenderers();
		ClientStuff.registerBlockColors();
		ClientStuff.registerItemColors();

		DimensionRenderInfo.field_239208_a_.put(new ResourceLocation(MODID, "undergarden"), new UGDimensionRenderInfo());
		//TODO: OthersideDRI
	}

	public void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();

		if(event.includeClient()) {
			generator.addProvider(new UGBlockStates(generator, event.getExistingFileHelper()));
			generator.addProvider(new UGItemModels(generator, event.getExistingFileHelper()));
			generator.addProvider(new UGLang(generator));
		}
		if(event.includeServer()) {
			generator.addProvider(new UGRecipes(generator));
			generator.addProvider(new UGLootTables(generator));
		}
	}

}
