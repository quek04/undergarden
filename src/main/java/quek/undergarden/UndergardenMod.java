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

@Mod(UndergardenMod.MODID)
public class UndergardenMod {
	
	public static final String MODID = "undergarden";

	public UndergardenMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::gatherData);

		UndergardenEntities.ENTITIES.register(bus);
		UndergardenBlocks.BLOCKS.register(bus);
		UndergardenItems.ITEMS.register(bus);
		UndergardenFeatures.FEATURES.register(bus);
		UndergardenWorldCarvers.CARVERS.register(bus);
		UndergardenEffects.EFFECTS.register(bus);
		UndergardenFluids.FLUIDS.register(bus);
		UndergardenParticles.PARTICLES.register(bus);
		UndergardenTEs.TEs.register(bus);

		final Pair<UndergardenConfig.CommonConfig, ForgeConfigSpec> specPairCommon = new ForgeConfigSpec.Builder().configure(UndergardenConfig.CommonConfig::new);
		final Pair<UndergardenConfig.ClientConfig, ForgeConfigSpec> specPairClient = new ForgeConfigSpec.Builder().configure(UndergardenConfig.ClientConfig::new);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, specPairCommon.getRight());
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, specPairClient.getRight());
	}

	public void setup(FMLCommonSetupEvent event) {
		UndergardenEntities.spawnPlacements();
		UndergardenEntities.entityAttributes();
		UndergardenFeatures.registerConfiguredFeatures();
		UndergardenWorldCarvers.registerConfiguredCarvers();

		AxeItem.BLOCK_STRIPPING_MAP = ImmutableMap.<Block, Block>builder()
				.putAll(AxeItem.BLOCK_STRIPPING_MAP)
				.put(UndergardenBlocks.smogstem_log.get(), UndergardenBlocks.stripped_smogstem_log.get())
				.put(UndergardenBlocks.wigglewood_log.get(), UndergardenBlocks.stripped_wigglewood_log.get())
				.put(UndergardenBlocks.grongle_stem.get(), UndergardenBlocks.stripped_grongle_stem.get())
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

		DispenserBlock.registerDispenseBehavior(UndergardenItems.virulent_mix_bucket.get(), bucketBehavior);
		DispenserBlock.registerDispenseBehavior(UndergardenItems.gwibling_bucket.get(), bucketBehavior);

		DispenserBlock.registerDispenseBehavior(UndergardenItems.dweller_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UndergardenItems.gwibling_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UndergardenItems.rotdweller_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UndergardenItems.rotling_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UndergardenItems.rotwalker_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UndergardenItems.rotbeast_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UndergardenItems.brute_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UndergardenItems.scintling_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UndergardenItems.blisterbomber_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UndergardenItems.gloomper_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UndergardenItems.stoneborn_spawn_egg.get(), eggBehavior);
		DispenserBlock.registerDispenseBehavior(UndergardenItems.masticator_spawn_egg.get(), eggBehavior);

		DispenserBlock.registerDispenseBehavior(UndergardenItems.depthrock_pebble.get(), new ProjectileDispenseBehavior() {
			protected ProjectileEntity getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
				return Util.make(new SlingshotAmmoEntity(worldIn, position.getX(), position.getY(), position.getZ()), (entity) -> entity.setItem(stackIn));
			}
		});

		DispenserBlock.registerDispenseBehavior(UndergardenItems.goo_ball.get(), new ProjectileDispenseBehavior() {
			protected ProjectileEntity getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
				return Util.make(new GooBallEntity(worldIn, position.getX(), position.getY(), position.getZ()), (entity) -> entity.setItem(stackIn));
			}
		});

		DispenserBlock.registerDispenseBehavior(UndergardenItems.rotten_blisterberry.get(), new ProjectileDispenseBehavior() {
			protected ProjectileEntity getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
				return Util.make(new RottenBlisterberryEntity(worldIn, position.getX(), position.getY(), position.getZ()), (entity) -> entity.setItem(stackIn));
			}
		});

		DispenserBlock.registerDispenseBehavior(UndergardenItems.blisterbomb.get(), new ProjectileDispenseBehavior() {
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

		DimensionRenderInfo.field_239208_a_.put(new ResourceLocation(MODID, "undergarden"), new UndergardenDimensionRenderInfo());
		//TODO: OthersideDRI
	}

	public void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();

		if(event.includeClient()) {
			generator.addProvider(new UndergardenBlockStates(generator, event.getExistingFileHelper()));
			generator.addProvider(new UndergardenItemModels(generator, event.getExistingFileHelper()));
			generator.addProvider(new UndergardenLang(generator));
		}
		if(event.includeServer()) {
			generator.addProvider(new UndergardenRecipes(generator));
			generator.addProvider(new UndergardenLootTables(generator));
		}
	}

}
