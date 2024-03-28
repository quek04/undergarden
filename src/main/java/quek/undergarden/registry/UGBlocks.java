package quek.undergarden.registry;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.block.*;
import quek.undergarden.client.render.blockentity.UndergardenBEWLR;
import quek.undergarden.item.CarvedGloomgourdItem;
import quek.undergarden.item.tool.slingshot.GrongletItem;
import quek.undergarden.network.UthericInfectionPacket;
import quek.undergarden.world.gen.tree.UGTreeGrowers;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class UGBlocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Undergarden.MODID);

	public static final DeferredBlock<Block> UNDERGARDEN_PORTAL = BLOCKS.register("undergarden_portal", UndergardenPortalBlock::new);

	public static final DeferredBlock<Block> SHARD_TORCH = register("shard_torch", () -> new ShardTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel((state) -> 6)));
	public static final DeferredBlock<Block> SHARD_WALL_TORCH = BLOCKS.register("shard_wall_torch", () -> new ShardWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).lightLevel((state) -> 6)));

	//depthrock
	public static final DeferredBlock<Block> DEPTHROCK = register("depthrock", () -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).strength(1.5F, 6.0F).sound(SoundType.BASALT).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> POLISHED_DEPTHROCK = register("polished_depthrock", () -> new Block(BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get())));
	public static final DeferredBlock<Block> DEPTHROCK_BRICKS = register("depthrock_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get())));
	public static final DeferredBlock<Block> CRACKED_DEPTHROCK_BRICKS = register("cracked_depthrock_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get())));
	public static final DeferredBlock<Block> CHISELED_DEPTHROCK_BRICKS = register("chiseled_depthrock_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get())));
	public static final DeferredBlock<Block> DEPTHROCK_TILES = register("depthrock_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get())));
	public static final DeferredBlock<StairBlock> DEPTHROCK_STAIRS = register("depthrock_stairs", () -> new StairBlock(() -> DEPTHROCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get())));
	public static final DeferredBlock<StairBlock> POLISHED_DEPTHROCK_STAIRS = register("polished_depthrock_stairs", () -> new StairBlock(() -> POLISHED_DEPTHROCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(POLISHED_DEPTHROCK.get())));
	public static final DeferredBlock<StairBlock> DEPTHROCK_BRICK_STAIRS = register("depthrock_brick_stairs", () -> new StairBlock(() -> DEPTHROCK_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(DEPTHROCK_BRICKS.get())));
	public static final DeferredBlock<StairBlock> DEPTHROCK_TILE_STAIRS = register("depthrock_tile_stairs", () -> new StairBlock(() -> DEPTHROCK_TILES.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(DEPTHROCK_TILES.get())));
	public static final DeferredBlock<SlabBlock> DEPTHROCK_SLAB = register("depthrock_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get())));
	public static final DeferredBlock<SlabBlock> POLISHED_DEPTHROCK_SLAB = register("polished_depthrock_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_DEPTHROCK.get())));
	public static final DeferredBlock<SlabBlock> DEPTHROCK_BRICK_SLAB = register("depthrock_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(DEPTHROCK_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> DEPTHROCK_TILE_SLAB = register("depthrock_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get())));
	public static final DeferredBlock<WallBlock> DEPTHROCK_WALL = register("depthrock_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get())));
	public static final DeferredBlock<WallBlock> POLISHED_DEPTHROCK_WALL = register("polished_depthrock_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_DEPTHROCK.get())));
	public static final DeferredBlock<WallBlock> DEPTHROCK_BRICK_WALL = register("depthrock_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(DEPTHROCK_BRICKS.get())));
	public static final DeferredBlock<ButtonBlock> DEPTHROCK_BUTTON = register("depthrock_button", () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).noCollission()));
	public static final DeferredBlock<PressurePlateBlock> DEPTHROCK_PRESSURE_PLATE = register("depthrock_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).noCollission()));

	//shiverstone
	public static final DeferredBlock<Block> SHIVERSTONE = register("shiverstone", () -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.GLOW_LICHEN).strength(3.5F, 12F).sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops().friction(0.98F)));
	public static final DeferredBlock<Block> SHIVERSTONE_BRICKS = register("shiverstone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get())));
	public static final DeferredBlock<Block> CRACKED_SHIVERSTONE_BRICKS = register("cracked_shiverstone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get())));
	public static final DeferredBlock<Block> CHISELED_SHIVERSTONE_BRICKS = register("chiseled_shiverstone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get())));
	public static final DeferredBlock<StairBlock> SHIVERSTONE_STAIRS = register("shiverstone_stairs", () -> new StairBlock(() -> SHIVERSTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get())));
	public static final DeferredBlock<StairBlock> SHIVERSTONE_BRICK_STAIRS = register("shiverstone_brick_stairs", () -> new StairBlock(() -> SHIVERSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> SHIVERSTONE_SLAB = register("shiverstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get())));
	public static final DeferredBlock<SlabBlock> SHIVERSTONE_BRICK_SLAB = register("shiverstone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE_BRICKS.get())));
	public static final DeferredBlock<WallBlock> SHIVERSTONE_WALL = register("shiverstone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get())));
	public static final DeferredBlock<WallBlock> SHIVERSTONE_BRICK_WALL = register("shiverstone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE_BRICKS.get())));
	public static final DeferredBlock<ButtonBlock> SHIVERSTONE_BUTTON = register("shiverstone_button", () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()).noCollission()));
	public static final DeferredBlock<PressurePlateBlock> SHIVERSTONE_PRESSURE_PLATE = register("shiverstone_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()).noCollission()));

	//dreadrock
	public static final DeferredBlock<Block> DREADROCK = register("dreadrock", () -> new DreadrockBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.TERRACOTTA_GREEN).strength(3.0F, 12.0F).sound(UGSoundTypes.DREADROCK).requiresCorrectToolForDrops()));

	//tremblecrust
	public static final DeferredBlock<Block> TREMBLECRUST = register("tremblecrust", () -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.DEEPSLATE).strength(6F, 24F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> LOOSE_TREMBLECRUST = register("loose_tremblecrust", () -> new LooseTremblecrustBlock(BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST.get()).strength(3F, 24F).noLootTable()));
	public static final DeferredBlock<Block> TREMBLECRUST_BRICKS = register("tremblecrust_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST.get()).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> CRACKED_TREMBLECRUST_BRICKS = register("cracked_tremblecrust_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST_BRICKS.get())));
	public static final DeferredBlock<Block> CHISELED_TREMBLECRUST_BRICKS = register("chiseled_tremblecrust_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST_BRICKS.get())));
	public static final DeferredBlock<StairBlock> TREMBLECRUST_STAIRS = register("tremblecrust_stairs", () -> new StairBlock(() -> TREMBLECRUST.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST.get())));
	public static final DeferredBlock<StairBlock> TREMBLECRUST_BRICK_STAIRS = register("tremblecrust_brick_stairs", () -> new StairBlock(() -> TREMBLECRUST_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> TREMBLECRUST_SLAB = register("tremblecrust_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST.get())));
	public static final DeferredBlock<SlabBlock> TREMBLECRUST_BRICK_SLAB = register("tremblecrust_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST_BRICKS.get())));
	public static final DeferredBlock<WallBlock> TREMBLECRUST_WALL = register("tremblecrust_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST.get())));
	public static final DeferredBlock<WallBlock> TREMBLECRUST_BRICK_WALL = register("tremblecrust_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST_BRICKS.get())));
	public static final DeferredBlock<ButtonBlock> TREMBLECRUST_BUTTON = register("tremblecrust_button", () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST.get()).noCollission()));
	public static final DeferredBlock<PressurePlateBlock> TREMBLECRUST_PRESSURE_PLATE = register("tremblecrust_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST.get()).noCollission()));

	//ores
	public static final DeferredBlock<Block> DEPTHROCK_COAL_ORE = register("depthrock_coal_ore", () -> new DropExperienceBlock(UniformInt.of(0, 2), BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> SHIVERSTONE_COAL_ORE = register("shiverstone_coal_ore", () -> new DropExperienceBlock(UniformInt.of(0, 2), BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEPTHROCK_IRON_ORE = register("depthrock_iron_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> SHIVERSTONE_IRON_ORE = register("shiverstone_iron_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEPTHROCK_GOLD_ORE = register("depthrock_gold_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEPTHROCK_DIAMOND_ORE = register("depthrock_diamond_ore", () -> new DropExperienceBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> SHIVERSTONE_DIAMOND_ORE = register("shiverstone_diamond_ore", () -> new DropExperienceBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEPTHROCK_CLOGGRUM_ORE = register("depthrock_cloggrum_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> SHIVERSTONE_CLOGGRUM_ORE = register("shiverstone_cloggrum_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> SHIVERSTONE_FROSTSTEEL_ORE = register("shiverstone_froststeel_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DREADROCK_ROGDORIUM_ORE = register("dreadrock_rogdorium_ore", () -> new DreadrockBlock(BlockBehaviour.Properties.ofFullCopy(DREADROCK.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEPTHROCK_UTHERIUM_ORE = register("depthrock_utherium_ore", () -> new DropExperienceBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> SHIVERSTONE_UTHERIUM_ORE = register("shiverstone_utherium_ore", () -> new DropExperienceBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> TREMBLECRUST_UTHERIUM_ORE = register("tremblecrust_utherium_ore", () -> new DropExperienceBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST.get()).strength(7.0F, 24.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DREADROCK_UTHERIUM_ORE = register("dreadrock_utherium_ore", () -> new DropExperienceBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(DREADROCK.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEPTHROCK_REGALIUM_ORE = register("depthrock_regalium_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> SHIVERSTONE_REGALIUM_ORE = register("shiverstone_regalium_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops()));

	//storage blocks
	public static final DeferredBlock<Block> RAW_CLOGGRUM_BLOCK = register("raw_cloggrum_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
	public static final DeferredBlock<Block> RAW_FROSTSTEEL_BLOCK = register("raw_froststeel_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_STEM).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
	public static final DeferredBlock<Block> RAW_ROGDORIUM_BLOCK = register("raw_rogdorium_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
	public static final DeferredBlock<Block> CLOGGRUM_BLOCK = register("cloggrum_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final DeferredBlock<Block> FROSTSTEEL_BLOCK = register("froststeel_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_STEM).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final DeferredBlock<Block> ROGDORIUM_BLOCK = register("rogdorium_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final DeferredBlock<Block> UTHERIUM_BLOCK = register("utherium_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final DeferredBlock<Block> REGALIUM_BLOCK = register("regalium_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final DeferredBlock<Block> FORGOTTEN_BLOCK = register("forgotten_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).requiresCorrectToolForDrops().strength(50.0F, 1200.0F).sound(SoundType.NETHERITE_BLOCK)));

	//normal blocks
	public static final DeferredBlock<Block> DEEPTURF_BLOCK = register("deepturf_block", () -> new DeepturfBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).randomTicks().strength(0.6F).sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> ASHEN_DEEPTURF_BLOCK = register("ashen_deepturf_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).randomTicks().strength(0.6F).sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> FROZEN_DEEPTURF_BLOCK = register("frozen_deepturf_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).randomTicks().strength(0.6F).sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> DEEPSOIL = register("deepsoil", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)));
	public static final DeferredBlock<Block> COARSE_DEEPSOIL = register("coarse_deepsoil", () -> new Block(BlockBehaviour.Properties.ofFullCopy(DEEPSOIL.get())));
	public static final DeferredBlock<Block> DEEPSOIL_FARMLAND = register("deepsoil_farmland", () -> new DeepsoilFarmlandBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FARMLAND)));
	public static final DeferredBlock<Block> GOO = register("goo", () -> new GooLayerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).replaceable().pushReaction(PushReaction.DESTROY).randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.SNOW).isViewBlocking((state, getter, pos) -> false).sound(SoundType.SLIME_BLOCK).noOcclusion().noCollission()));
	public static final DeferredBlock<Block> GOO_BLOCK = register("goo_block", () -> new GooBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion()));
	public static final DeferredBlock<Block> SMOG_VENT = register("smog_vent", () -> new SmogVentBlock(BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).lightLevel((state) -> 10)));
	public static final DeferredBlock<Block> SEDIMENT = register("sediment", () -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SNARE).mapColor(MapColor.DEEPSLATE).strength(0.5F).sound(SoundType.SAND)));
	public static final DeferredBlock<Block> SEDIMENT_GLASS = register("sediment_glass", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
	public static final DeferredBlock<Block> SEDIMENT_GLASS_PANE = register("sediment_glass_pane", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
	public static final DeferredBlock<Block> CLOGGRUM_BARS = register("cloggrum_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS)));
	public static final DeferredBlock<Block> CLOGGRUM_TILES = register("cloggrum_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CLOGGRUM_BLOCK.get())));
	public static final DeferredBlock<StairBlock> CLOGGRUM_TILE_STAIRS = register("cloggrum_tile_stairs", () -> new StairBlock(() -> CLOGGRUM_TILES.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CLOGGRUM_TILES.get())));
	public static final DeferredBlock<SlabBlock> CLOGGRUM_TILE_SLAB = register("cloggrum_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(CLOGGRUM_TILES.get())));
	public static final DeferredBlock<BedBlock> DEPTHROCK_BED = register("depthrock_bed", () -> new DepthrockBedBlock(BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get())));
	public static final DeferredBlock<WoolCarpetBlock> MOGMOSS_RUG = register("mogmoss_rug", () -> new WoolCarpetBlock(DyeColor.LIME, BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_CARPET)));
	public static final DeferredBlock<WoolCarpetBlock> BLUE_MOGMOSS_RUG = register("blue_mogmoss_rug", () -> new WoolCarpetBlock(DyeColor.BLUE, BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_CARPET)));
	public static final DeferredBlock<Block> CLOGGRUM_LANTERN = register("cloggrum_lantern", () -> new CloggrumLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).pushReaction(PushReaction.DESTROY)));
	public static final DeferredBlock<Block> UTHERIUM_GROWTH = register("utherium_growth", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_RED).lightLevel((state) -> 15)));

	//plants
	public static final DeferredBlock<Block> AMOROUS_BRISTLE = register("amorous_bristle", () -> new UGFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
	public static final DeferredBlock<Block> MISERABELL = register("miserabell", () -> new UGFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CORNFLOWER)));
	public static final DeferredBlock<Block> BUTTERBUNCH = register("butterbunch", () -> new UGFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));
	public static final DeferredBlock<Block> UNDERBEAN_BUSH = BLOCKS.register("underbean_bush", () -> new UnderbeanBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));
	public static final DeferredBlock<Block> BLISTERBERRY_BUSH = BLOCKS.register("blisterberry_bush", () -> new BlisterberryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).lightLevel((state) -> 6)));
	public static final DeferredBlock<Block> DEEPTURF = register("deepturf", () -> new TallDeepturfBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)));
	public static final DeferredBlock<Block> ASHEN_DEEPTURF = register("ashen_deepturf", () -> new TallDeepturfVariantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)));
	public static final DeferredBlock<Block> FROZEN_DEEPTURF = register("frozen_deepturf", () -> new TallDeepturfVariantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)));
	public static final DeferredBlock<Block> TALL_DEEPTURF = register("tall_deepturf", () -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));
	public static final DeferredBlock<Block> SHIMMERWEED = register("shimmerweed", () -> new ShimmerweedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).lightLevel((state) -> 12)));
	public static final DeferredBlock<Block> TALL_SHIMMERWEED = register("tall_shimmerweed", () -> new TallShimmerweedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS).lightLevel((state) -> 14)));
	public static final DeferredBlock<Block> DITCHBULB_PLANT = BLOCKS.register("ditchbulb_plant", () -> new DitchbulbBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).randomTicks().lightLevel((state) -> state.getValue(DitchbulbBlock.AGE) == 1 ? 6 : 0)));
	public static final DeferredBlock<Block> GLOOMGOURD = register("gloomgourd", () -> new GloomgourdBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> CARVED_GLOOMGOURD = register("carved_gloomgourd", () -> new CarvedGloomgourdBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> GLOOM_O_LANTERN = register("gloom_o_lantern", () -> new CarvedGloomgourdBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.WOOD).lightLevel((state) -> 15)));
	public static final DeferredBlock<Block> SHARD_O_LANTERN = register("shard_o_lantern", () -> new CarvedGloomgourdShardBlock(BlockBehaviour.Properties.ofFullCopy(GLOOM_O_LANTERN.get()).lightLevel((state) -> 6)));
	public static final DeferredBlock<Block> BOOMGOURD = register("boomgourd", () -> new BoomgourdBlock(BlockBehaviour.Properties.of().ignitedByLava().mapColor(MapColor.COLOR_PURPLE).strength(1.0F).sound(SoundType.WOOD)));
	public static final ResourceKey<Block> GLOOMGOURD_STEM_KEY = ResourceKey.create(Registries.BLOCK, new ResourceLocation(Undergarden.MODID, "gloomgourd_stem"));
	public static final ResourceKey<Block> ATTACHED_GLOOMGOURD_STEM_KEY = ResourceKey.create(Registries.BLOCK, new ResourceLocation(Undergarden.MODID, "gloomgourd_stem_attached"));
	public static final DeferredBlock<AttachedStemBlock> GLOOMGOURD_STEM_ATTACHED = BLOCKS.register("gloomgourd_stem_attached", () -> new AttachedStemBlock(GLOOMGOURD_STEM_KEY, UGBlocks.GLOOMGOURD.getKey(), UGItems.GLOOMGOURD_SEEDS.getKey(), BlockBehaviour.Properties.ofFullCopy(Blocks.ATTACHED_MELON_STEM)));
	public static final DeferredBlock<StemBlock> GLOOMGOURD_STEM = BLOCKS.register("gloomgourd_stem", () -> new StemBlock(GLOOMGOURD.getKey(), ATTACHED_GLOOMGOURD_STEM_KEY, UGItems.GLOOMGOURD_SEEDS.getKey(), BlockBehaviour.Properties.ofFullCopy(Blocks.MELON_STEM)));

	public static final DeferredBlock<Block> DEPTHROCK_PEBBLES = BLOCKS.register("depthrock_pebbles", () -> new DepthrockPebblesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).instabreak().pushReaction(PushReaction.DESTROY).sound(SoundType.BASALT).noOcclusion().noCollission()));
	public static final DeferredBlock<GlitterkelpBlock> GLITTERKELP = BLOCKS.register("glitterkelp", () -> new GlitterkelpBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.KELP).lightLevel((state) -> 10)));
	public static final DeferredBlock<GlitterkelpPlantBlock> GLITTERKELP_PLANT = BLOCKS.register("glitterkelp_plant", () -> new GlitterkelpPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.KELP_PLANT).lightLevel((state) -> 10)));
	public static final DeferredBlock<DroopvineBlock> DROOPVINE = BLOCKS.register("droopvine", () -> new DroopvineBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAVE_VINES).strength(0.1F).lightLevel(Droopvine.light())));
	public static final DeferredBlock<DroopvinePlantBlock> DROOPVINE_PLANT = BLOCKS.register("droopvine_plant", () -> new DroopvinePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAVE_VINES_PLANT).strength(0.1F).lightLevel(Droopvine.light())));

	//mushroom
	public static final DeferredBlock<Block> INDIGO_MUSHROOM = register("indigo_mushroom", () -> new UGMushroomBlock(UGConfiguredFeatures.HUGE_INDIGO_MUSHROOM, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).noCollission().pushReaction(PushReaction.DESTROY).randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess((state, level, pos) -> true).lightLevel((state) -> 2)));
	public static final DeferredBlock<Block> INDIGO_MUSHROOM_CAP = register("indigo_mushroom_cap", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(0.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> INDIGO_MUSHROOM_STEM = register("indigo_mushroom_stem", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(0.2F).sound(SoundType.WOOD)));

	public static final DeferredBlock<Block> VEIL_MUSHROOM = register("veil_mushroom", () -> new UGMushroomBlock(UGConfiguredFeatures.HUGE_VEIL_MUSHROOM, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess((state, level, pos) -> true)));
	public static final DeferredBlock<Block> VEIL_MUSHROOM_CAP = register("veil_mushroom_cap", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).strength(0.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> VEIL_MUSHROOM_STEM = register("veil_mushroom_stem", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM)));
	public static final DeferredBlock<Block> MUSHROOM_VEIL = register("mushroom_veil", () -> new MushroomVeilBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOW_LICHEN)));

	public static final DeferredBlock<Block> INK_MUSHROOM = register("ink_mushroom", () -> new UGMushroomBlock(UGConfiguredFeatures.HUGE_INK_MUSHROOM, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess((state, level, pos) -> true)));
	public static final DeferredBlock<Block> INK_MUSHROOM_CAP = register("ink_mushroom_cap", () -> new InkCapBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).strength(0.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> SEEPING_INK = register("seeping_ink", () -> new SeepingInkBlock(BlockBehaviour.Properties.of().sound(SoundType.WET_GRASS).instabreak().noOcclusion().noCollission()));
	public static final DeferredBlock<Block> INK_MUSHROOM_STEM = register("ink_mushroom_stem", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM)));

	public static final DeferredBlock<Block> BLOOD_MUSHROOM = register("blood_mushroom", () -> new UGMushroomBlock(UGConfiguredFeatures.HUGE_BLOOD_MUSHROOM, BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess((state, level, pos) -> true)));
	public static final DeferredBlock<Block> BLOOD_MUSHROOM_CAP = register("blood_mushroom_cap", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(0.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> ENGORGED_BLOOD_MUSHROOM_CAP = register("engorged_blood_mushroom_cap", () -> new EngorgedCapBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM_BLOCK)));
	public static final DeferredBlock<Block> BLOOD_MUSHROOM_STEM = register("blood_mushroom_stem", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM)));

	//smogstem
	public static final DeferredBlock<SaplingBlock> SMOGSTEM_SAPLING = register("smogstem_sapling", () -> new UGSaplingBlock(UGTreeGrowers.SMOGSTEM));
	public static final DeferredBlock<RotatedPillarBlock> SMOGSTEM_LOG = register("smogstem_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.COLOR_GRAY).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_SMOGSTEM_LOG = register("stripped_smogstem_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.COLOR_GRAY).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> SMOGSTEM_WOOD = register("smogstem_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.COLOR_GRAY).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_SMOGSTEM_WOOD = register("stripped_smogstem_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.COLOR_GRAY).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> SMOGSTEM_LEAVES = register("smogstem_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().ignitedByLava().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_CYAN).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion().isValidSpawn((state, level, pos, value) -> false).isSuffocating((state, level, pos) -> false).isViewBlocking((state, level, pos) -> false)));
	public static final DeferredBlock<Block> SMOGSTEM_PLANKS = register("smogstem_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS)));
	public static final DeferredBlock<StairBlock> SMOGSTEM_STAIRS = register("smogstem_stairs", () -> new StairBlock(() -> SMOGSTEM_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SMOGSTEM_PLANKS.get())));
	public static final DeferredBlock<SlabBlock> SMOGSTEM_SLAB = register("smogstem_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SMOGSTEM_PLANKS.get())));
	public static final DeferredBlock<FenceBlock> SMOGSTEM_FENCE = register("smogstem_fence", () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FENCE)));
	public static final DeferredBlock<FenceGateBlock> SMOGSTEM_FENCE_GATE = register("smogstem_fence_gate", () -> new FenceGateBlock(UGWoodStuff.SMOGSTEM_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FENCE_GATE)));
	public static final DeferredBlock<DoorBlock> SMOGSTEM_DOOR = register("smogstem_door", () -> new DoorBlock(UGWoodStuff.SMOGSTEM_WOOD_SET, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_DOOR)));
	public static final DeferredBlock<TrapDoorBlock> SMOGSTEM_TRAPDOOR = register("smogstem_trapdoor", () -> new TrapDoorBlock(UGWoodStuff.SMOGSTEM_WOOD_SET, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_TRAPDOOR)));
	public static final DeferredBlock<ButtonBlock> SMOGSTEM_BUTTON = register("smogstem_button", () -> new ButtonBlock(UGWoodStuff.SMOGSTEM_WOOD_SET, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_BUTTON)));
	public static final DeferredBlock<PressurePlateBlock> SMOGSTEM_PRESSURE_PLATE = register("smogstem_pressure_plate", () -> new PressurePlateBlock(UGWoodStuff.SMOGSTEM_WOOD_SET, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PRESSURE_PLATE)));

	public static final DeferredBlock<StandingSignBlock> SMOGSTEM_SIGN = register("smogstem_sign", () -> new UGStandingSignBlock(UGWoodStuff.SMOGSTEM_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SIGN)));
	public static final DeferredBlock<WallSignBlock> SMOGSTEM_WALL_SIGN = BLOCKS.register("smogstem_wall_sign", () -> new UGWallSignBlock(UGWoodStuff.SMOGSTEM_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_WALL_SIGN)));
	public static final DeferredBlock<CeilingHangingSignBlock> SMOGSTEM_HANGING_SIGN = register("smogstem_hanging_sign", () -> new UGCeilingHangingSignBlock(UGWoodStuff.SMOGSTEM_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_HANGING_SIGN)));
	public static final DeferredBlock<WallHangingSignBlock> SMOGSTEM_WALL_HANGING_SIGN = BLOCKS.register("smogstem_wall_hanging_sign", () -> new UGWallHangingSignBlock(UGWoodStuff.SMOGSTEM_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_WALL_HANGING_SIGN)));

	//wigglewood
	public static final DeferredBlock<SaplingBlock> WIGGLEWOOD_SAPLING = register("wigglewood_sapling", () -> new UGSaplingBlock(UGTreeGrowers.WIGGLEWOOD));
	public static final DeferredBlock<RotatedPillarBlock> WIGGLEWOOD_LOG = register("wigglewood_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_BROWN).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_WIGGLEWOOD_LOG = register("stripped_wigglewood_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_BROWN).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> WIGGLEWOOD_WOOD = register("wigglewood_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_BROWN).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_WIGGLEWOOD_WOOD = register("stripped_wigglewood_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_BROWN).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> WIGGLEWOOD_LEAVES = register("wigglewood_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().ignitedByLava().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_PINK).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion().isValidSpawn((state, level, pos, value) -> false).isSuffocating((state, level, pos) -> false).isViewBlocking((state, level, pos) -> false)));
	public static final DeferredBlock<Block> WIGGLEWOOD_PLANKS = register("wigglewood_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)));
	public static final DeferredBlock<StairBlock> WIGGLEWOOD_STAIRS = register("wigglewood_stairs", () -> new StairBlock(() -> SMOGSTEM_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SMOGSTEM_PLANKS.get())));
	public static final DeferredBlock<SlabBlock> WIGGLEWOOD_SLAB = register("wigglewood_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(WIGGLEWOOD_PLANKS.get())));
	public static final DeferredBlock<FenceBlock> WIGGLEWOOD_FENCE = register("wigglewood_fence", () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE)));
	public static final DeferredBlock<FenceGateBlock> WIGGLEWOOD_FENCE_GATE = register("wigglewood_fence_gate", () -> new FenceGateBlock(UGWoodStuff.WIGGLEWOOD_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE_GATE)));
	public static final DeferredBlock<DoorBlock> WIGGLEWOOD_DOOR = register("wigglewood_door", () -> new DoorBlock(UGWoodStuff.WIGGLEWOOD_WOOD_SET, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_DOOR)));
	public static final DeferredBlock<TrapDoorBlock> WIGGLEWOOD_TRAPDOOR = register("wigglewood_trapdoor", () -> new TrapDoorBlock(UGWoodStuff.WIGGLEWOOD_WOOD_SET, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_TRAPDOOR)));
	public static final DeferredBlock<ButtonBlock> WIGGLEWOOD_BUTTON = register("wigglewood_button", () -> new ButtonBlock(UGWoodStuff.WIGGLEWOOD_WOOD_SET, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_BUTTON)));
	public static final DeferredBlock<PressurePlateBlock> WIGGLEWOOD_PRESSURE_PLATE = register("wigglewood_pressure_plate", () -> new PressurePlateBlock(UGWoodStuff.WIGGLEWOOD_WOOD_SET, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PRESSURE_PLATE)));

	public static final DeferredBlock<StandingSignBlock> WIGGLEWOOD_SIGN = register("wigglewood_sign", () -> new UGStandingSignBlock(UGWoodStuff.WIGGLEWOOD_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SIGN)));
	public static final DeferredBlock<WallSignBlock> WIGGLEWOOD_WALL_SIGN = BLOCKS.register("wigglewood_wall_sign", () -> new UGWallSignBlock(UGWoodStuff.WIGGLEWOOD_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WALL_SIGN)));
	public static final DeferredBlock<CeilingHangingSignBlock> WIGGLEWOOD_HANGING_SIGN = register("wigglewood_hanging_sign", () -> new UGCeilingHangingSignBlock(UGWoodStuff.WIGGLEWOOD_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_HANGING_SIGN)));
	public static final DeferredBlock<WallHangingSignBlock> WIGGLEWOOD_WALL_HANGING_SIGN = BLOCKS.register("wigglewood_wall_hanging_sign", () -> new UGWallHangingSignBlock(UGWoodStuff.WIGGLEWOOD_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WALL_HANGING_SIGN)));

	//grongle
	public static final DeferredBlock<Block> GRONGLE_SAPLING = register("grongle_sapling", () -> new UGSaplingBlock(UGTreeGrowers.GRONGLE));
	public static final DeferredBlock<RotatedPillarBlock> GRONGLE_LOG = register("grongle_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GRONGLE_LOG = register("stripped_grongle_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
	public static final DeferredBlock<RotatedPillarBlock> GRONGLE_WOOD = register("grongle_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GRONGLE_WOOD = register("stripped_grongle_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));
	public static final DeferredBlock<Block> GRONGLE_LEAVES = register("grongle_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).sound(SoundType.AZALEA_LEAVES)));
	public static final DeferredBlock<Block> HANGING_GRONGLE_LEAVES = register("hanging_grongle_leaves", () -> new HangingGrongleLeavesBlock(BlockBehaviour.Properties.ofFullCopy(GRONGLE_LEAVES.get()).noCollission().noOcclusion()));
	public static final DeferredBlock<Block> GRONGLE_PLANKS = register("grongle_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
	public static final DeferredBlock<StairBlock> GRONGLE_STAIRS = register("grongle_stairs", () -> new StairBlock(() -> GRONGLE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(GRONGLE_PLANKS.get())));
	public static final DeferredBlock<SlabBlock> GRONGLE_SLAB = register("grongle_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(GRONGLE_PLANKS.get())));
	public static final DeferredBlock<FenceBlock> GRONGLE_FENCE = register("grongle_fence", () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
	public static final DeferredBlock<FenceGateBlock> GRONGLE_FENCE_GATE = register("grongle_fence_gate", () -> new FenceGateBlock(UGWoodStuff.GRONGLE_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
	public static final DeferredBlock<DoorBlock> GRONGLE_DOOR = register("grongle_door", () -> new DoorBlock(UGWoodStuff.GRONGLE_WOOD_SET, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
	public static final DeferredBlock<TrapDoorBlock> GRONGLE_TRAPDOOR = register("grongle_trapdoor", () -> new TrapDoorBlock(UGWoodStuff.GRONGLE_WOOD_SET, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
	public static final DeferredBlock<ButtonBlock> GRONGLE_BUTTON = register("grongle_button", () -> new ButtonBlock(UGWoodStuff.GRONGLE_WOOD_SET, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
	public static final DeferredBlock<PressurePlateBlock> GRONGLE_PRESSURE_PLATE = register("grongle_pressure_plate", () -> new PressurePlateBlock(UGWoodStuff.GRONGLE_WOOD_SET, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));

	public static final DeferredBlock<StandingSignBlock> GRONGLE_SIGN = register("grongle_sign", () -> new UGStandingSignBlock(UGWoodStuff.GRONGLE_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
	public static final DeferredBlock<WallSignBlock> GRONGLE_WALL_SIGN = BLOCKS.register("grongle_wall_sign", () -> new UGWallSignBlock(UGWoodStuff.GRONGLE_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
	public static final DeferredBlock<CeilingHangingSignBlock> GRONGLE_HANGING_SIGN = register("grongle_hanging_sign", () -> new UGCeilingHangingSignBlock(UGWoodStuff.GRONGLE_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
	public static final DeferredBlock<WallHangingSignBlock> GRONGLE_WALL_HANGING_SIGN = BLOCKS.register("grongle_wall_hanging_sign", () -> new UGWallHangingSignBlock(UGWoodStuff.GRONGLE_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));

	public static final DeferredBlock<Block> GRONGLET = register("gronglet", () -> new GrongletBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noTerrainParticles().lightLevel((state) -> 12).noOcclusion().noCollission().strength(0.0F).sound(UGSoundTypes.GRONGLET)));

	//ancient root
	public static final DeferredBlock<Block> ANCIENT_ROOT = register("ancient_root", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<Block> ANCIENT_ROOT_PLANKS = register("ancient_root_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
	public static final DeferredBlock<StairBlock> ANCIENT_ROOT_STAIRS = register("ancient_root_stairs", () -> new StairBlock(() -> ANCIENT_ROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ANCIENT_ROOT_PLANKS.get())));
	public static final DeferredBlock<SlabBlock> ANCIENT_ROOT_SLAB = register("ancient_root_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(ANCIENT_ROOT_PLANKS.get())));
	public static final DeferredBlock<FenceBlock> ANCIENT_ROOT_FENCE = register("ancient_root_fence", () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
	public static final DeferredBlock<FenceGateBlock> ANCIENT_ROOT_FENCE_GATE = register("ancient_root_fence_gate", () -> new FenceGateBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
	public static final DeferredBlock<DoorBlock> ANCIENT_ROOT_DOOR = register("ancient_root_door", () -> new DoorBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_SET, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
	public static final DeferredBlock<TrapDoorBlock> ANCIENT_ROOT_TRAPDOOR = register("ancient_root_trapdoor", () -> new TrapDoorBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_SET, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
	public static final DeferredBlock<ButtonBlock> ANCIENT_ROOT_BUTTON = register("ancient_root_button", () -> new ButtonBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_SET, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
	public static final DeferredBlock<PressurePlateBlock> ANCIENT_ROOT_PRESSURE_PLATE = register("ancient_root_pressure_plate", () -> new PressurePlateBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_SET, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));

	public static final DeferredBlock<StandingSignBlock> ANCIENT_ROOT_SIGN = register("ancient_root_sign", () -> new UGStandingSignBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SIGN)));
	public static final DeferredBlock<WallSignBlock> ANCIENT_ROOT_WALL_SIGN = BLOCKS.register("ancient_root_wall_sign", () -> new UGWallSignBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WALL_SIGN)));
	public static final DeferredBlock<CeilingHangingSignBlock> ANCIENT_ROOT_HANGING_SIGN = register("ancient_root_hanging_sign", () -> new UGCeilingHangingSignBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_HANGING_SIGN)));
	public static final DeferredBlock<WallHangingSignBlock> ANCIENT_ROOT_WALL_HANGING_SIGN = BLOCKS.register("ancient_root_wall_hanging_sign", () -> new UGWallHangingSignBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WALL_HANGING_SIGN)));


	//flower pots
	public static final DeferredBlock<FlowerPotBlock> POTTED_SMOGSTEM_SAPLING = BLOCKS.register("potted_smogstem_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SMOGSTEM_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_WIGGLEWOOD_SAPLING = BLOCKS.register("potted_wigglewood_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, WIGGLEWOOD_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_GRONGLE_SAPLING = BLOCKS.register("potted_grongle_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GRONGLE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_SHIMMERWEED = BLOCKS.register("potted_shimmerweed", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SHIMMERWEED, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).lightLevel((state) -> 12)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_INDIGO_MUSHROOM = BLOCKS.register("potted_indigo_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, INDIGO_MUSHROOM, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_VEIL_MUSHROOM = BLOCKS.register("potted_veil_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, VEIL_MUSHROOM, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_INK_MUSHROOM = BLOCKS.register("potted_ink_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, INK_MUSHROOM, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_BLOOD_MUSHROOM = BLOCKS.register("potted_blood_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLOOD_MUSHROOM, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_AMOROUS_BRISTLE = BLOCKS.register("potted_amorous_bristle", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, AMOROUS_BRISTLE, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_MISERABELL = BLOCKS.register("potted_miserabell", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, MISERABELL, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_BUTTERBUNCH = BLOCKS.register("potted_butterbunch", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BUTTERBUNCH, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));

	//fluids
	public static final DeferredBlock<LiquidBlock> VIRULENT_MIX = BLOCKS.register("virulent_mix", () -> new VirulentMixBlock(
			UGFluids.VIRULENT_MIX_SOURCE, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).replaceable().pushReaction(PushReaction.DESTROY).liquid()));

	//cauldrons
	public static final DeferredBlock<Block> VIRULENT_MIX_CAULDRON = BLOCKS.register("virulent_mix_cauldron", () -> new VirulentMixCauldronBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).lightLevel((state) -> 10).randomTicks()));

	private static <T extends Block> DeferredBlock<T> baseRegister(String name, Supplier<? extends T> block, Function<DeferredBlock<T>, Supplier<? extends Item>> item) {
		DeferredBlock<T> register = BLOCKS.register(name, block);
		UGItems.ITEMS.register(name, item.apply(register));
		return register;
	}

	private static <B extends Block> DeferredBlock<B> register(String name, Supplier<B> block) {
		return baseRegister(name, block, UGBlocks::registerBlockItem);
	}

	private static <T extends Block> Supplier<BlockItem> registerBlockItem(final DeferredBlock<T> block) {
		return () -> {
			if (Objects.requireNonNull(block.get()) == SHARD_TORCH.get()) {
				return new StandingAndWallBlockItem(SHARD_TORCH.get(), SHARD_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN);
			} else if (Objects.requireNonNull(block.get()) == REGALIUM_BLOCK.get() || Objects.requireNonNull(block.get()) == DEPTHROCK_REGALIUM_ORE.get() || Objects.requireNonNull(block.get()) == SHIVERSTONE_REGALIUM_ORE.get()) {
				return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties().rarity(Rarity.UNCOMMON));
			} else if (Objects.requireNonNull(block.get()) == ROGDORIUM_BLOCK.get() || Objects.requireNonNull(block.get()) == RAW_ROGDORIUM_BLOCK.get() || Objects.requireNonNull(block.get()) == DREADROCK_ROGDORIUM_ORE.get()) {
				return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties().rarity(UGItems.ROGDORIUM));
			} else if (Objects.requireNonNull(block.get()) == FORGOTTEN_BLOCK.get()) {
				return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties().rarity(UGItems.FORGOTTEN));
			} else if (Objects.requireNonNull(block.get()) == DEPTHROCK_BED.get()) {
				return new BedItem(Objects.requireNonNull(block.get()), new Item.Properties().stacksTo(1)) {
					@Override
					public void initializeClient(Consumer<IClientItemExtensions> consumer) {
						consumer.accept(new IClientItemExtensions() {
							@Override
							public BlockEntityWithoutLevelRenderer getCustomRenderer() {
								return new UndergardenBEWLR();
							}
						});
					}
				};
			} else if (Objects.requireNonNull(block.get()) == GRONGLET.get()) {
				return new GrongletItem(Objects.requireNonNull(block.get()), new Item.Properties()) {
					@Override
					public void initializeClient(Consumer<IClientItemExtensions> consumer) {
						consumer.accept(new IClientItemExtensions() {
							@Override
							public BlockEntityWithoutLevelRenderer getCustomRenderer() {
								return new UndergardenBEWLR();
							}
						});
					}
				};
			} else if (Objects.requireNonNull(block.get()) == CARVED_GLOOMGOURD.get()) {
				return new CarvedGloomgourdItem(Objects.requireNonNull(block.get()), new Item.Properties());
			} else if (Objects.requireNonNull(block.get()) == SMOGSTEM_SIGN.get()) {
				return new SignItem(new Item.Properties().stacksTo(16), SMOGSTEM_SIGN.get(), SMOGSTEM_WALL_SIGN.get());
			} else if (Objects.requireNonNull(block.get()) == WIGGLEWOOD_SIGN.get()) {
				return new SignItem(new Item.Properties().stacksTo(16), WIGGLEWOOD_SIGN.get(), WIGGLEWOOD_WALL_SIGN.get());
			} else if (Objects.requireNonNull(block.get()) == GRONGLE_SIGN.get()) {
				return new SignItem(new Item.Properties().stacksTo(16), GRONGLE_SIGN.get(), GRONGLE_WALL_SIGN.get());
			} else if (Objects.requireNonNull(block.get()) == ANCIENT_ROOT_SIGN.get()) {
				return new SignItem(new Item.Properties().stacksTo(16), ANCIENT_ROOT_SIGN.get(), ANCIENT_ROOT_WALL_SIGN.get());
			} else if (Objects.requireNonNull(block.get()) == SMOGSTEM_HANGING_SIGN.get()) {
				return new HangingSignItem(SMOGSTEM_HANGING_SIGN.get(), SMOGSTEM_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16));
			} else if (Objects.requireNonNull(block.get()) == WIGGLEWOOD_HANGING_SIGN.get()) {
				return new HangingSignItem(WIGGLEWOOD_HANGING_SIGN.get(), WIGGLEWOOD_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16));
			} else if (Objects.requireNonNull(block.get()) == GRONGLE_HANGING_SIGN.get()) {
				return new HangingSignItem(GRONGLE_HANGING_SIGN.get(), GRONGLE_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16));
			} else if (Objects.requireNonNull(block.get()) == ANCIENT_ROOT_HANGING_SIGN.get()) {
				return new HangingSignItem(ANCIENT_ROOT_HANGING_SIGN.get(), ANCIENT_ROOT_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16));
			} else if (Objects.requireNonNull(block.get()) == UTHERIUM_GROWTH.get()) {
				return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties()) {
					@Override
					public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotID, boolean isSelected) {
						int data = entity.getData(UGAttachments.UTHERIC_INFECTION);
						if (entity instanceof Player player && !level.isClientSide()) {
							if (player.tickCount % 100 == 0 && data < 20) {
								player.setData(UGAttachments.UTHERIC_INFECTION.get(), data + stack.getCount());
								PacketDistributor.TRACKING_ENTITY_AND_SELF.with(player).send(new UthericInfectionPacket(player.getId(), player.getData(UGAttachments.UTHERIC_INFECTION)));
							}
						}
					}
				};
			} else {
				return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());
			}
		};
	}
}