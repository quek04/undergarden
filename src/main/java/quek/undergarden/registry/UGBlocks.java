package quek.undergarden.registry;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.block.*;
import quek.undergarden.client.render.blockentity.UndergardenBEWLR;
import quek.undergarden.item.CarvedGloomgourdItem;
import quek.undergarden.item.tool.slingshot.GrongletItem;
import quek.undergarden.world.gen.tree.GrongleTree;
import quek.undergarden.world.gen.tree.SmogstemTree;
import quek.undergarden.world.gen.tree.WigglewoodTree;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class UGBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Undergarden.MODID);

	public static final RegistryObject<Block> UNDERGARDEN_PORTAL = BLOCKS.register("undergarden_portal", UndergardenPortalBlock::new);

	public static final RegistryObject<Block> SHARD_TORCH = register("shard_torch", () -> new ShardTorchBlock(BlockBehaviour.Properties.copy(Blocks.TORCH).lightLevel((state) -> 6)));
	public static final RegistryObject<Block> SHARD_WALL_TORCH = BLOCKS.register("shard_wall_torch", () -> new ShardWallTorchBlock(BlockBehaviour.Properties.copy(Blocks.WALL_TORCH).lightLevel((state) -> 6)));

	//depthrock
	public static final RegistryObject<Block> DEPTHROCK = register("depthrock", () -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).strength(1.5F, 6.0F).sound(SoundType.BASALT).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_DEPTHROCK = register("polished_depthrock", () -> new Block(BlockBehaviour.Properties.copy(DEPTHROCK.get())));
	public static final RegistryObject<Block> DEPTHROCK_BRICKS = register("depthrock_bricks", () -> new Block(BlockBehaviour.Properties.copy(DEPTHROCK.get())));
	public static final RegistryObject<Block> CRACKED_DEPTHROCK_BRICKS = register("cracked_depthrock_bricks", () -> new Block(BlockBehaviour.Properties.copy(DEPTHROCK.get())));
	public static final RegistryObject<Block> CHISELED_DEPTHROCK_BRICKS = register("chiseled_depthrock_bricks", () -> new Block(BlockBehaviour.Properties.copy(DEPTHROCK.get())));
	public static final RegistryObject<Block> DEPTHROCK_TILES = register("depthrock_tiles", () -> new Block(BlockBehaviour.Properties.copy(DEPTHROCK.get())));
	public static final RegistryObject<StairBlock> DEPTHROCK_STAIRS = register("depthrock_stairs", () -> new StairBlock(() -> DEPTHROCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(DEPTHROCK.get())));
	public static final RegistryObject<StairBlock> POLISHED_DEPTHROCK_STAIRS = register("polished_depthrock_stairs", () -> new StairBlock(() -> POLISHED_DEPTHROCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(POLISHED_DEPTHROCK.get())));
	public static final RegistryObject<StairBlock> DEPTHROCK_BRICK_STAIRS = register("depthrock_brick_stairs", () -> new StairBlock(() -> DEPTHROCK_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(DEPTHROCK_BRICKS.get())));
	public static final RegistryObject<StairBlock> DEPTHROCK_TILE_STAIRS = register("depthrock_tile_stairs", () -> new StairBlock(() -> DEPTHROCK_TILES.get().defaultBlockState(), BlockBehaviour.Properties.copy(DEPTHROCK_TILES.get())));
	public static final RegistryObject<SlabBlock> DEPTHROCK_SLAB = register("depthrock_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(DEPTHROCK.get())));
	public static final RegistryObject<SlabBlock> POLISHED_DEPTHROCK_SLAB = register("polished_depthrock_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(POLISHED_DEPTHROCK.get())));
	public static final RegistryObject<SlabBlock> DEPTHROCK_BRICK_SLAB = register("depthrock_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(DEPTHROCK_BRICKS.get())));
	public static final RegistryObject<SlabBlock> DEPTHROCK_TILE_SLAB = register("depthrock_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(DEPTHROCK.get())));
	public static final RegistryObject<WallBlock> DEPTHROCK_WALL = register("depthrock_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(DEPTHROCK.get())));
	public static final RegistryObject<WallBlock> POLISHED_DEPTHROCK_WALL = register("polished_depthrock_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(POLISHED_DEPTHROCK.get())));
	public static final RegistryObject<WallBlock> DEPTHROCK_BRICK_WALL = register("depthrock_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(DEPTHROCK_BRICKS.get())));
	public static final RegistryObject<ButtonBlock> DEPTHROCK_BUTTON = register("depthrock_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(DEPTHROCK.get()).noCollission(), BlockSetType.STONE, 20, false));
	public static final RegistryObject<PressurePlateBlock> DEPTHROCK_PRESSURE_PLATE = register("depthrock_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.copy(DEPTHROCK.get()).noCollission(), BlockSetType.STONE));

	//shiverstone
	public static final RegistryObject<Block> SHIVERSTONE = register("shiverstone", () -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.GLOW_LICHEN).strength(3.5F, 12F).sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops().friction(0.98F)));
	public static final RegistryObject<Block> SHIVERSTONE_BRICKS = register("shiverstone_bricks", () -> new Block(BlockBehaviour.Properties.copy(SHIVERSTONE.get())));
	public static final RegistryObject<Block> CRACKED_SHIVERSTONE_BRICKS = register("cracked_shiverstone_bricks", () -> new Block(BlockBehaviour.Properties.copy(SHIVERSTONE.get())));
	public static final RegistryObject<Block> CHISELED_SHIVERSTONE_BRICKS = register("chiseled_shiverstone_bricks", () -> new Block(BlockBehaviour.Properties.copy(SHIVERSTONE.get())));
	public static final RegistryObject<StairBlock> SHIVERSTONE_STAIRS = register("shiverstone_stairs", () -> new StairBlock(() -> SHIVERSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(SHIVERSTONE.get())));
	public static final RegistryObject<StairBlock> SHIVERSTONE_BRICK_STAIRS = register("shiverstone_brick_stairs", () -> new StairBlock(() -> SHIVERSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(SHIVERSTONE_BRICKS.get())));
	public static final RegistryObject<SlabBlock> SHIVERSTONE_SLAB = register("shiverstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SHIVERSTONE.get())));
	public static final RegistryObject<SlabBlock> SHIVERSTONE_BRICK_SLAB = register("shiverstone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SHIVERSTONE_BRICKS.get())));
	public static final RegistryObject<WallBlock> SHIVERSTONE_WALL = register("shiverstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SHIVERSTONE.get())));
	public static final RegistryObject<WallBlock> SHIVERSTONE_BRICK_WALL = register("shiverstone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SHIVERSTONE_BRICKS.get())));
	public static final RegistryObject<ButtonBlock> SHIVERSTONE_BUTTON = register("shiverstone_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(SHIVERSTONE.get()).noCollission(), BlockSetType.STONE, 20, false));
	public static final RegistryObject<PressurePlateBlock> SHIVERSTONE_PRESSURE_PLATE = register("shiverstone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.copy(SHIVERSTONE.get()).noCollission(), BlockSetType.STONE));

	//tremblecrust
	public static final RegistryObject<Block> TREMBLECRUST = register("tremblecrust", () -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.DEEPSLATE).strength(6F, 24F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> LOOSE_TREMBLECRUST = register("loose_tremblecrust", () -> new LooseTremblecrustBlock(BlockBehaviour.Properties.copy(TREMBLECRUST.get()).strength(3F, 24F).noLootTable()));
	public static final RegistryObject<Block> TREMBLECRUST_BRICKS = register("tremblecrust_bricks", () -> new Block(BlockBehaviour.Properties.copy(TREMBLECRUST.get()).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CRACKED_TREMBLECRUST_BRICKS = register("cracked_tremblecrust_bricks", () -> new Block(BlockBehaviour.Properties.copy(TREMBLECRUST_BRICKS.get())));
	public static final RegistryObject<Block> CHISELED_TREMBLECRUST_BRICKS = register("chiseled_tremblecrust_bricks", () -> new Block(BlockBehaviour.Properties.copy(TREMBLECRUST_BRICKS.get())));
	public static final RegistryObject<StairBlock> TREMBLECRUST_STAIRS = register("tremblecrust_stairs", () -> new StairBlock(() -> TREMBLECRUST.get().defaultBlockState(), BlockBehaviour.Properties.copy(TREMBLECRUST.get())));
	public static final RegistryObject<StairBlock> TREMBLECRUST_BRICK_STAIRS = register("tremblecrust_brick_stairs", () -> new StairBlock(() -> TREMBLECRUST_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(TREMBLECRUST_BRICKS.get())));
	public static final RegistryObject<SlabBlock> TREMBLECRUST_SLAB = register("tremblecrust_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(TREMBLECRUST.get())));
	public static final RegistryObject<SlabBlock> TREMBLECRUST_BRICK_SLAB = register("tremblecrust_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(TREMBLECRUST_BRICKS.get())));
	public static final RegistryObject<WallBlock> TREMBLECRUST_WALL = register("tremblecrust_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(TREMBLECRUST.get())));
	public static final RegistryObject<WallBlock> TREMBLECRUST_BRICK_WALL = register("tremblecrust_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(TREMBLECRUST_BRICKS.get())));
	public static final RegistryObject<ButtonBlock> TREMBLECRUST_BUTTON = register("tremblecrust_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(TREMBLECRUST.get()).noCollission(), BlockSetType.STONE, 20, false));
	public static final RegistryObject<PressurePlateBlock> TREMBLECRUST_PRESSURE_PLATE = register("tremblecrust_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.copy(TREMBLECRUST.get()).noCollission(), BlockSetType.STONE));

	//ores
	public static final RegistryObject<Block> DEPTHROCK_COAL_ORE = register("depthrock_coal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops(), UniformInt.of(0, 2)));
	public static final RegistryObject<Block> SHIVERSTONE_COAL_ORE = register("shiverstone_coal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops(), UniformInt.of(0, 2)));
	public static final RegistryObject<Block> DEPTHROCK_IRON_ORE = register("depthrock_iron_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> SHIVERSTONE_IRON_ORE = register("shiverstone_iron_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> DEPTHROCK_GOLD_ORE = register("depthrock_gold_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> DEPTHROCK_DIAMOND_ORE = register("depthrock_diamond_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops(), UniformInt.of(4, 8)));
	public static final RegistryObject<Block> SHIVERSTONE_DIAMOND_ORE = register("shiverstone_diamond_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops(), UniformInt.of(4, 8)));
	public static final RegistryObject<Block> DEPTHROCK_CLOGGRUM_ORE = register("depthrock_cloggrum_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> SHIVERSTONE_CLOGGRUM_ORE = register("shiverstone_cloggrum_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> SHIVERSTONE_FROSTSTEEL_ORE = register("shiverstone_froststeel_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> DEPTHROCK_UTHERIUM_ORE = register("depthrock_utherium_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops(), UniformInt.of(4, 8)));
	public static final RegistryObject<Block> SHIVERSTONE_UTHERIUM_ORE = register("shiverstone_utherium_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops(), UniformInt.of(4, 8)));
	public static final RegistryObject<Block> TREMBLECRUST_UTHERIUM_ORE = register("tremblecrust_utherium_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(TREMBLECRUST.get()).strength(7.0F, 24.0F).requiresCorrectToolForDrops(), UniformInt.of(4, 8)));
	public static final RegistryObject<Block> DEPTHROCK_REGALIUM_ORE = register("depthrock_regalium_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> SHIVERSTONE_REGALIUM_ORE = register("shiverstone_regalium_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops()));

	//storage blocks
	public static final RegistryObject<Block> RAW_CLOGGRUM_BLOCK = register("raw_cloggrum_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
	public static final RegistryObject<Block> RAW_FROSTSTEEL_BLOCK = register("raw_froststeel_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_STEM).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
	public static final RegistryObject<Block> CLOGGRUM_BLOCK = register("cloggrum_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> FROSTSTEEL_BLOCK = register("froststeel_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_STEM).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> UTHERIUM_BLOCK = register("utherium_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> REGALIUM_BLOCK = register("regalium_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> FORGOTTEN_BLOCK = register("forgotten_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).requiresCorrectToolForDrops().strength(50.0F, 1200.0F).sound(SoundType.NETHERITE_BLOCK)));

	//normal blocks
	public static final RegistryObject<Block> DEEPTURF_BLOCK = register("deepturf_block", () -> new DeepturfBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).randomTicks().strength(0.6F).sound(SoundType.GRASS)));
	public static final RegistryObject<Block> ASHEN_DEEPTURF_BLOCK = register("ashen_deepturf_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).randomTicks().strength(0.6F).sound(SoundType.GRASS)));
	public static final RegistryObject<Block> FROZEN_DEEPTURF_BLOCK = register("frozen_deepturf_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).randomTicks().strength(0.6F).sound(SoundType.GRASS)));
	public static final RegistryObject<Block> DEEPSOIL = register("deepsoil", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
	public static final RegistryObject<Block> COARSE_DEEPSOIL = register("coarse_deepsoil", () -> new Block(BlockBehaviour.Properties.copy(DEEPSOIL.get())));
	public static final RegistryObject<Block> DEEPSOIL_FARMLAND = register("deepsoil_farmland", () -> new DeepsoilFarmlandBlock(BlockBehaviour.Properties.copy(Blocks.FARMLAND)));
	public static final RegistryObject<Block> GOO = register("goo", () -> new GooLayerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).replaceable().pushReaction(PushReaction.DESTROY).randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.SNOW).isViewBlocking((state, getter, pos) -> false).sound(SoundType.SLIME_BLOCK).noOcclusion().noCollission()));
	public static final RegistryObject<Block> GOO_BLOCK = register("goo_block", () -> new GooBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion()));
	public static final RegistryObject<Block> SMOG_VENT = register("smog_vent", () -> new SmogVentBlock(BlockBehaviour.Properties.copy(DEPTHROCK.get()).lightLevel((state) -> 10)));
	public static final RegistryObject<Block> SEDIMENT = register("sediment", () -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SNARE).mapColor(MapColor.DEEPSLATE).strength(0.5F).sound(SoundType.SAND)));
	public static final RegistryObject<Block> SEDIMENT_GLASS = register("sediment_glass", () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
	public static final RegistryObject<Block> SEDIMENT_GLASS_PANE = register("sediment_glass_pane", () -> new IronBarsBlock(BlockBehaviour.Properties.copy(Blocks.GLASS_PANE)));
	public static final RegistryObject<Block> CLOGGRUM_BARS = register("cloggrum_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BARS)));
	public static final RegistryObject<Block> CLOGGRUM_TILES = register("cloggrum_tiles", () -> new Block(BlockBehaviour.Properties.copy(CLOGGRUM_BLOCK.get())));
	public static final RegistryObject<StairBlock> CLOGGRUM_TILE_STAIRS = register("cloggrum_tile_stairs", () -> new StairBlock(() -> CLOGGRUM_TILES.get().defaultBlockState(), BlockBehaviour.Properties.copy(CLOGGRUM_TILES.get())));
	public static final RegistryObject<SlabBlock> CLOGGRUM_TILE_SLAB = register("cloggrum_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CLOGGRUM_TILES.get())));
	public static final RegistryObject<BedBlock> DEPTHROCK_BED = register("depthrock_bed", () -> new DepthrockBedBlock(BlockBehaviour.Properties.copy(DEPTHROCK.get())));
	public static final RegistryObject<WoolCarpetBlock> MOGMOSS_RUG = register("mogmoss_rug", () -> new WoolCarpetBlock(DyeColor.LIME, BlockBehaviour.Properties.copy(Blocks.GREEN_CARPET)));
	public static final RegistryObject<Block> CLOGGRUM_LANTERN = register("cloggrum_lantern", () -> new CloggrumLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).pushReaction(PushReaction.DESTROY)));

	//plants
	public static final RegistryObject<Block> AMOROUS_BRISTLE = register("amorous_bristle", () -> new UGFlowerBlock(BlockBehaviour.Properties.copy(Blocks.POPPY)));
	public static final RegistryObject<Block> MISERABELL = register("miserabell", () -> new UGFlowerBlock(BlockBehaviour.Properties.copy(Blocks.CORNFLOWER)));
	public static final RegistryObject<Block> BUTTERBUNCH = register("butterbunch", () -> new UGFlowerBlock(BlockBehaviour.Properties.copy(Blocks.DANDELION)));
	public static final RegistryObject<Block> UNDERBEAN_BUSH = BLOCKS.register("underbean_bush", () -> new UnderbeanBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
	public static final RegistryObject<Block> BLISTERBERRY_BUSH = BLOCKS.register("blisterberry_bush", () -> new BlisterberryBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH).lightLevel((state) -> 6)));
	public static final RegistryObject<Block> DEEPTURF = register("deepturf", () -> new UGPlantBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)));
	public static final RegistryObject<Block> ASHEN_DEEPTURF = register("ashen_deepturf", () -> new AshenTallDeepturfBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)));
	public static final RegistryObject<Block> FROZEN_DEEPTURF = register("frozen_deepturf", () -> new FrozenDeepturfBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)));
	public static final RegistryObject<Block> TALL_DEEPTURF = register("tall_deepturf", () -> new UGDoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)));
	public static final RegistryObject<Block> SHIMMERWEED = register("shimmerweed", () -> new UGPlantBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).lightLevel((state) -> 12)));
	public static final RegistryObject<Block> TALL_SHIMMERWEED = register("tall_shimmerweed", () -> new UGDoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).lightLevel((state) -> 14)));
	public static final RegistryObject<Block> DITCHBULB_PLANT = BLOCKS.register("ditchbulb_plant", () -> new DitchbulbBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).randomTicks().lightLevel((state) -> state.getValue(DitchbulbBlock.AGE) == 1 ? 6 : 0)));
	public static final RegistryObject<StemGrownBlock> GLOOMGOURD = register("gloomgourd", () -> new GloomgourdBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> CARVED_GLOOMGOURD = register("carved_gloomgourd", () -> new CarvedGloomgourdBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> GLOOM_O_LANTERN = register("gloom_o_lantern", () -> new CarvedGloomgourdBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.WOOD).lightLevel((state) -> 15)));
	public static final RegistryObject<Block> SHARD_O_LANTERN = register("shard_o_lantern", () -> new CarvedGloomgourdShardBlock(BlockBehaviour.Properties.copy(GLOOM_O_LANTERN.get()).lightLevel((state) -> 6)));
	public static final RegistryObject<Block> BOOMGOURD = register("boomgourd", () -> new BoomgourdBlock(BlockBehaviour.Properties.of().ignitedByLava().mapColor(MapColor.COLOR_PURPLE).strength(1.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<StemBlock> GLOOMGOURD_STEM = BLOCKS.register("gloomgourd_stem", () -> new StemBlock(GLOOMGOURD.get(), UGItems.GLOOMGOURD_SEEDS, BlockBehaviour.Properties.copy(Blocks.MELON_STEM)));
	public static final RegistryObject<AttachedStemBlock> GLOOMGOURD_STEM_ATTACHED = BLOCKS.register("gloomgourd_stem_attached", () -> new AttachedStemBlock(GLOOMGOURD.get(), UGItems.GLOOMGOURD_SEEDS, BlockBehaviour.Properties.copy(Blocks.ATTACHED_MELON_STEM)));
	public static final RegistryObject<Block> DEPTHROCK_PEBBLES = BLOCKS.register("depthrock_pebbles", () -> new DepthrockPebblesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).instabreak().pushReaction(PushReaction.DESTROY).sound(SoundType.BASALT).noOcclusion().noCollission()));
	public static final RegistryObject<GlitterkelpBlock> GLITTERKELP = BLOCKS.register("glitterkelp", () -> new GlitterkelpBlock(BlockBehaviour.Properties.copy(Blocks.KELP).lightLevel((state) -> 10)));
	public static final RegistryObject<GlitterkelpPlantBlock> GLITTERKELP_PLANT = BLOCKS.register("glitterkelp_plant", () -> new GlitterkelpPlantBlock(BlockBehaviour.Properties.copy(Blocks.KELP_PLANT).lightLevel((state) -> 10)));
	public static final RegistryObject<DroopvineBlock> DROOPVINE = BLOCKS.register("droopvine", () -> new DroopvineBlock(BlockBehaviour.Properties.copy(Blocks.CAVE_VINES).strength(0.1F).lightLevel(Droopvine.light())));
	public static final RegistryObject<DroopvinePlantBlock> DROOPVINE_PLANT = BLOCKS.register("droopvine_plant", () -> new DroopvinePlantBlock(BlockBehaviour.Properties.copy(Blocks.CAVE_VINES_PLANT).strength(0.1F).lightLevel(Droopvine.light())));

	//mushroom
	public static final RegistryObject<Block> INDIGO_MUSHROOM = register("indigo_mushroom", () -> new UGMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).noCollission().pushReaction(PushReaction.DESTROY).randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess((state, level, pos) -> true).lightLevel((state) -> 2), UGConfiguredFeatures.HUGE_INDIGO_MUSHROOM));
	public static final RegistryObject<Block> INDIGO_MUSHROOM_CAP = register("indigo_mushroom_cap", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(0.2F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> INDIGO_MUSHROOM_STEM = register("indigo_mushroom_stem", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(0.2F).sound(SoundType.WOOD)));

	public static final RegistryObject<Block> VEIL_MUSHROOM = register("veil_mushroom", () -> new UGMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess((state, level, pos) -> true), UGConfiguredFeatures.HUGE_VEIL_MUSHROOM));
	public static final RegistryObject<Block> VEIL_MUSHROOM_CAP = register("veil_mushroom_cap", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).strength(0.2F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> VEIL_MUSHROOM_STEM = register("veil_mushroom_stem", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM)));
	public static final RegistryObject<Block> MUSHROOM_VEIL = register("mushroom_veil", () -> new MushroomVeilBlock(BlockBehaviour.Properties.copy(Blocks.GLOW_LICHEN)));

	public static final RegistryObject<Block> INK_MUSHROOM = register("ink_mushroom", () -> new UGMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess((state, level, pos) -> true), UGConfiguredFeatures.HUGE_INK_MUSHROOM));
	public static final RegistryObject<Block> INK_MUSHROOM_CAP = register("ink_mushroom_cap", () -> new InkCapBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).strength(0.2F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> SEEPING_INK = register("seeping_ink", () -> new SeepingInkBlock(BlockBehaviour.Properties.of().sound(SoundType.WET_GRASS).instabreak().noOcclusion().noCollission()));
	public static final RegistryObject<Block> INK_MUSHROOM_STEM = register("ink_mushroom_stem", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM)));

	public static final RegistryObject<Block> BLOOD_MUSHROOM = register("blood_mushroom", () -> new UGMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess((state, level, pos) -> true), UGConfiguredFeatures.HUGE_BLOOD_MUSHROOM));
	public static final RegistryObject<Block> BLOOD_MUSHROOM_CAP = register("blood_mushroom_cap", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(0.2F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> ENGORGED_BLOOD_MUSHROOM_CAP = register("engorged_blood_mushroom_cap", () -> new EngorgedCapBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM_BLOCK)));
	public static final RegistryObject<Block> BLOOD_MUSHROOM_STEM = register("blood_mushroom_stem", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM)));

	//smogstem
	public static final RegistryObject<SaplingBlock> SMOGSTEM_SAPLING = register("smogstem_sapling", () -> new UGSaplingBlock(new SmogstemTree()));
	public static final RegistryObject<RotatedPillarBlock> SMOGSTEM_LOG = register("smogstem_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.COLOR_GRAY).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_SMOGSTEM_LOG = register("stripped_smogstem_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.COLOR_GRAY).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> SMOGSTEM_WOOD = register("smogstem_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.COLOR_GRAY).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_SMOGSTEM_WOOD = register("stripped_smogstem_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.COLOR_GRAY).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> SMOGSTEM_LEAVES = register("smogstem_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().ignitedByLava().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_CYAN).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion().isValidSpawn((state, level, pos, value) -> false).isSuffocating((state, level, pos) -> false).isViewBlocking((state, level, pos) -> false)));
	public static final RegistryObject<Block> SMOGSTEM_PLANKS = register("smogstem_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WARPED_PLANKS)));
	public static final RegistryObject<StairBlock> SMOGSTEM_STAIRS = register("smogstem_stairs", () -> new StairBlock(() -> SMOGSTEM_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(SMOGSTEM_PLANKS.get())));
	public static final RegistryObject<SlabBlock> SMOGSTEM_SLAB = register("smogstem_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SMOGSTEM_PLANKS.get())));
	public static final RegistryObject<FenceBlock> SMOGSTEM_FENCE = register("smogstem_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_FENCE)));
	public static final RegistryObject<FenceGateBlock> SMOGSTEM_FENCE_GATE = register("smogstem_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_FENCE_GATE), UGWoodStuff.SMOGSTEM_WOOD_TYPE));
	public static final RegistryObject<DoorBlock> SMOGSTEM_DOOR = register("smogstem_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_DOOR), UGWoodStuff.SMOGSTEM_WOOD_SET));
	public static final RegistryObject<TrapDoorBlock> SMOGSTEM_TRAPDOOR = register("smogstem_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_TRAPDOOR), UGWoodStuff.SMOGSTEM_WOOD_SET));
	public static final RegistryObject<ButtonBlock> SMOGSTEM_BUTTON = register("smogstem_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_BUTTON), UGWoodStuff.SMOGSTEM_WOOD_SET, 30, true));
	public static final RegistryObject<PressurePlateBlock> SMOGSTEM_PRESSURE_PLATE = register("smogstem_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.WARPED_PRESSURE_PLATE), UGWoodStuff.SMOGSTEM_WOOD_SET));

	public static final RegistryObject<StandingSignBlock> SMOGSTEM_SIGN = register("smogstem_sign", () -> new UGStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_SIGN), UGWoodStuff.SMOGSTEM_WOOD_TYPE));
	public static final RegistryObject<WallSignBlock> SMOGSTEM_WALL_SIGN = BLOCKS.register("smogstem_wall_sign", () -> new UGWallSignBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_WALL_SIGN), UGWoodStuff.SMOGSTEM_WOOD_TYPE));
	public static final RegistryObject<CeilingHangingSignBlock> SMOGSTEM_HANGING_SIGN = register("smogstem_hanging_sign", () -> new UGCeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_HANGING_SIGN), UGWoodStuff.SMOGSTEM_WOOD_TYPE));
	public static final RegistryObject<WallHangingSignBlock> SMOGSTEM_WALL_HANGING_SIGN = BLOCKS.register("smogstem_wall_hanging_sign", () -> new UGWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_WALL_HANGING_SIGN), UGWoodStuff.SMOGSTEM_WOOD_TYPE));

	//wigglewood
	public static final RegistryObject<SaplingBlock> WIGGLEWOOD_SAPLING = register("wigglewood_sapling", () -> new UGSaplingBlock(new WigglewoodTree()));
	public static final RegistryObject<RotatedPillarBlock> WIGGLEWOOD_LOG = register("wigglewood_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_BROWN).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_WIGGLEWOOD_LOG = register("stripped_wigglewood_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_BROWN).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> WIGGLEWOOD_WOOD = register("wigglewood_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_BROWN).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_WIGGLEWOOD_WOOD = register("stripped_wigglewood_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_BROWN).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> WIGGLEWOOD_LEAVES = register("wigglewood_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().ignitedByLava().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_PINK).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion().isValidSpawn((state, level, pos, value) -> false).isSuffocating((state, level, pos) -> false).isViewBlocking((state, level, pos) -> false)));
	public static final RegistryObject<Block> WIGGLEWOOD_PLANKS = register("wigglewood_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SPRUCE_PLANKS)));
	public static final RegistryObject<StairBlock> WIGGLEWOOD_STAIRS = register("wigglewood_stairs", () -> new StairBlock(() -> SMOGSTEM_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(SMOGSTEM_PLANKS.get())));
	public static final RegistryObject<SlabBlock> WIGGLEWOOD_SLAB = register("wigglewood_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(WIGGLEWOOD_PLANKS.get())));
	public static final RegistryObject<FenceBlock> WIGGLEWOOD_FENCE = register("wigglewood_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_FENCE)));
	public static final RegistryObject<FenceGateBlock> WIGGLEWOOD_FENCE_GATE = register("wigglewood_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_FENCE_GATE), UGWoodStuff.WIGGLEWOOD_WOOD_TYPE));
	public static final RegistryObject<DoorBlock> WIGGLEWOOD_DOOR = register("wigglewood_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_DOOR), UGWoodStuff.WIGGLEWOOD_WOOD_SET));
	public static final RegistryObject<TrapDoorBlock> WIGGLEWOOD_TRAPDOOR = register("wigglewood_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_TRAPDOOR), UGWoodStuff.WIGGLEWOOD_WOOD_SET));
	public static final RegistryObject<ButtonBlock> WIGGLEWOOD_BUTTON = register("wigglewood_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_BUTTON), UGWoodStuff.WIGGLEWOOD_WOOD_SET, 30, true));
	public static final RegistryObject<PressurePlateBlock> WIGGLEWOOD_PRESSURE_PLATE = register("wigglewood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.SPRUCE_PRESSURE_PLATE), UGWoodStuff.WIGGLEWOOD_WOOD_SET));

	public static final RegistryObject<StandingSignBlock> WIGGLEWOOD_SIGN = register("wigglewood_sign", () -> new UGStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_SIGN), UGWoodStuff.WIGGLEWOOD_WOOD_TYPE));
	public static final RegistryObject<WallSignBlock> WIGGLEWOOD_WALL_SIGN = BLOCKS.register("wigglewood_wall_sign", () -> new UGWallSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_SIGN), UGWoodStuff.WIGGLEWOOD_WOOD_TYPE));
	public static final RegistryObject<CeilingHangingSignBlock> WIGGLEWOOD_HANGING_SIGN = register("wigglewood_hanging_sign", () -> new UGCeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_HANGING_SIGN), UGWoodStuff.WIGGLEWOOD_WOOD_TYPE));
	public static final RegistryObject<WallHangingSignBlock> WIGGLEWOOD_WALL_HANGING_SIGN = BLOCKS.register("wigglewood_wall_hanging_sign", () -> new UGWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_HANGING_SIGN), UGWoodStuff.WIGGLEWOOD_WOOD_TYPE));

	//grongle
	public static final RegistryObject<Block> GRONGLE_SAPLING = register("grongle_sapling", () -> new UGSaplingBlock(new GrongleTree()));
	public static final RegistryObject<RotatedPillarBlock> GRONGLE_LOG = register("grongle_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_GRONGLE_LOG = register("stripped_grongle_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
	public static final RegistryObject<RotatedPillarBlock> GRONGLE_WOOD = register("grongle_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_GRONGLE_WOOD = register("stripped_grongle_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
	public static final RegistryObject<Block> GRONGLE_LEAVES = register("grongle_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).sound(SoundType.AZALEA_LEAVES)));
	public static final RegistryObject<Block> HANGING_GRONGLE_LEAVES = register("hanging_grongle_leaves", () -> new HangingGrongleLeavesBlock(BlockBehaviour.Properties.copy(GRONGLE_LEAVES.get()).noCollission().noOcclusion()));
	public static final RegistryObject<Block> GRONGLE_PLANKS = register("grongle_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
	public static final RegistryObject<StairBlock> GRONGLE_STAIRS = register("grongle_stairs", () -> new StairBlock(() -> GRONGLE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(GRONGLE_PLANKS.get())));
	public static final RegistryObject<SlabBlock> GRONGLE_SLAB = register("grongle_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GRONGLE_PLANKS.get())));
	public static final RegistryObject<FenceBlock> GRONGLE_FENCE = register("grongle_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
	public static final RegistryObject<FenceGateBlock> GRONGLE_FENCE_GATE = register("grongle_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), UGWoodStuff.GRONGLE_WOOD_TYPE));
	public static final RegistryObject<DoorBlock> GRONGLE_DOOR = register("grongle_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), UGWoodStuff.GRONGLE_WOOD_SET));
	public static final RegistryObject<TrapDoorBlock> GRONGLE_TRAPDOOR = register("grongle_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), UGWoodStuff.GRONGLE_WOOD_SET));
	public static final RegistryObject<ButtonBlock> GRONGLE_BUTTON = register("grongle_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), UGWoodStuff.GRONGLE_WOOD_SET, 30, true));
	public static final RegistryObject<PressurePlateBlock> GRONGLE_PRESSURE_PLATE = register("grongle_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), UGWoodStuff.GRONGLE_WOOD_SET));

	public static final RegistryObject<StandingSignBlock> GRONGLE_SIGN = register("grongle_sign", () -> new UGStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), UGWoodStuff.GRONGLE_WOOD_TYPE));
	public static final RegistryObject<WallSignBlock> GRONGLE_WALL_SIGN = BLOCKS.register("grongle_wall_sign", () -> new UGWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), UGWoodStuff.GRONGLE_WOOD_TYPE));
	public static final RegistryObject<CeilingHangingSignBlock> GRONGLE_HANGING_SIGN = register("grongle_hanging_sign", () -> new UGCeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), UGWoodStuff.GRONGLE_WOOD_TYPE));
	public static final RegistryObject<WallHangingSignBlock> GRONGLE_WALL_HANGING_SIGN = BLOCKS.register("grongle_wall_hanging_sign", () -> new UGWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), UGWoodStuff.GRONGLE_WOOD_TYPE));

	public static final RegistryObject<Block> GRONGLET = register("gronglet", () -> new GrongletBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).lightLevel((state) -> 12).noOcclusion().noCollission().strength(0.0F).sound(UGSoundTypes.GRONGLET)));

	//flower pots
	public static final RegistryObject<FlowerPotBlock> POTTED_SMOGSTEM_SAPLING = BLOCKS.register("potted_smogstem_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SMOGSTEM_SAPLING, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_WIGGLEWOOD_SAPLING = BLOCKS.register("potted_wigglewood_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, WIGGLEWOOD_SAPLING, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_GRONGLE_SAPLING = BLOCKS.register("potted_grongle_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GRONGLE_SAPLING, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_SHIMMERWEED = BLOCKS.register("potted_shimmerweed", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SHIMMERWEED, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).lightLevel((state) -> 12)));
	public static final RegistryObject<FlowerPotBlock> POTTED_INDIGO_MUSHROOM = BLOCKS.register("potted_indigo_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, INDIGO_MUSHROOM, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_VEIL_MUSHROOM = BLOCKS.register("potted_veil_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, VEIL_MUSHROOM, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_INK_MUSHROOM = BLOCKS.register("potted_ink_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, INK_MUSHROOM, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_BLOOD_MUSHROOM = BLOCKS.register("potted_blood_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLOOD_MUSHROOM, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_AMOROUS_BRISTLE = BLOCKS.register("potted_amorous_bristle", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, AMOROUS_BRISTLE, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_MISERABELL = BLOCKS.register("potted_miserabell", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, MISERABELL, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_BUTTERBUNCH = BLOCKS.register("potted_butterbunch", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BUTTERBUNCH, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));

	//fluids
	public static final RegistryObject<LiquidBlock> VIRULENT_MIX = BLOCKS.register("virulent_mix", () -> new VirulentMixBlock(
			UGFluids.VIRULENT_MIX_SOURCE, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).replaceable().pushReaction(PushReaction.DESTROY).liquid()));

	//cauldrons
	public static final RegistryObject<Block> VIRULENT_MIX_CAULDRON = BLOCKS.register("virulent_mix_cauldron", () -> new VirulentMixCauldronBlock(
			BlockBehaviour.Properties.copy(Blocks.CAULDRON).lightLevel((state) -> 10).randomTicks()));

	private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
		RegistryObject<T> register = BLOCKS.register(name, block);
		UGItems.ITEMS.register(name, item.apply(register));
		return register;
	}

	private static <B extends Block> RegistryObject<B> register(String name, Supplier<? extends Block> block) {
		return (RegistryObject<B>) baseRegister(name, block, UGBlocks::registerBlockItem);
	}

	private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
		return () -> {
			if (Objects.requireNonNull(block.get()) == SHARD_TORCH.get()) {
				return new StandingAndWallBlockItem(SHARD_TORCH.get(), SHARD_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN);
			} else if (Objects.requireNonNull(block.get()) == REGALIUM_BLOCK.get() || Objects.requireNonNull(block.get()) == DEPTHROCK_REGALIUM_ORE.get() || Objects.requireNonNull(block.get()) == SHIVERSTONE_REGALIUM_ORE.get()) {
				return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties().rarity(Rarity.UNCOMMON));
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
			}  else if (Objects.requireNonNull(block.get()) == SMOGSTEM_HANGING_SIGN.get()) {
				return new HangingSignItem(SMOGSTEM_HANGING_SIGN.get(), SMOGSTEM_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16));
			} else if (Objects.requireNonNull(block.get()) == WIGGLEWOOD_HANGING_SIGN.get()) {
				return new HangingSignItem(WIGGLEWOOD_HANGING_SIGN.get(), WIGGLEWOOD_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16));
			} else if (Objects.requireNonNull(block.get()) == GRONGLE_HANGING_SIGN.get()) {
				return new HangingSignItem(GRONGLE_HANGING_SIGN.get(), GRONGLE_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16));
			} else {
				return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());
			}
		};
	}
}