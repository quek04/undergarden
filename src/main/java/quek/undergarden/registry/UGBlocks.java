package quek.undergarden.registry;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusConfig;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.block.*;
import quek.undergarden.world.gen.tree.GrongleTree;
import quek.undergarden.world.gen.tree.SmogstemTree;
import quek.undergarden.world.gen.tree.WigglewoodTree;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class UGBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Undergarden.MODID);

    public static final RegistryObject<Block> UNDERGARDEN_PORTAL = BLOCKS.register("undergarden_portal", UndergardenPortalBlock::new);

    public static final RegistryObject<Block> SHARD_TORCH = BLOCKS.register("shard_torch", () -> new ShardTorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH).lightLevel((state) -> 6)));
    public static final RegistryObject<Block> SHARD_WALL_TORCH = BLOCKS.register("shard_wall_torch", () -> new ShardWallTorchBlock(AbstractBlock.Properties.copy(Blocks.WALL_TORCH).lightLevel((state) -> 6)));

    //depthrock
    public static final RegistryObject<Block> DEPTHROCK = register("depthrock", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE).sound(SoundType.BASALT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEPTHROCK_BRICKS = register("depthrock_bricks", () -> new Block(AbstractBlock.Properties.copy(DEPTHROCK.get())));
    public static final RegistryObject<Block> CRACKED_DEPTHROCK_BRICKS = register("cracked_depthrock_bricks", () -> new Block(AbstractBlock.Properties.copy(DEPTHROCK.get())));
    public static final RegistryObject<Block> CHISELED_DEPTHROCK_BRICKS = register("chiseled_depthrock_bricks", () -> new Block(AbstractBlock.Properties.copy(DEPTHROCK.get())));
    public static final RegistryObject<Block> DEPTHROCK_TILES = register("depthrock_tiles", () -> new Block(AbstractBlock.Properties.copy(DEPTHROCK.get())));
    public static final RegistryObject<StairsBlock> DEPTHROCK_STAIRS = register("depthrock_stairs", () -> new StairsBlock(() -> UGBlocks.DEPTHROCK.get().defaultBlockState(), AbstractBlock.Properties.copy(UGBlocks.DEPTHROCK.get()).noOcclusion()));
    public static final RegistryObject<StairsBlock> DEPTHROCK_BRICK_STAIRS = register("depthrock_brick_stairs", () -> new StairsBlock(() -> UGBlocks.DEPTHROCK_BRICKS.get().defaultBlockState(), AbstractBlock.Properties.copy(UGBlocks.DEPTHROCK_BRICKS.get()).noOcclusion()));
    public static final RegistryObject<StairsBlock> DEPTHROCK_TILE_STAIRS = register("depthrock_tile_stairs", () -> new StairsBlock(() -> UGBlocks.DEPTHROCK_TILES.get().defaultBlockState(), AbstractBlock.Properties.copy(UGBlocks.DEPTHROCK.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> DEPTHROCK_SLAB = register("depthrock_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(UGBlocks.DEPTHROCK.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> DEPTHROCK_BRICK_SLAB = register("depthrock_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(UGBlocks.DEPTHROCK_BRICKS.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> DEPTHROCK_TILE_SLAB = register("depthrock_tile_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(UGBlocks.DEPTHROCK.get()).noOcclusion()));
    public static final RegistryObject<WallBlock> DEPTHROCK_WALL = register("depthrock_wall", () -> new WallBlock(AbstractBlock.Properties.copy(DEPTHROCK.get()).noOcclusion()));
    public static final RegistryObject<WallBlock> DEPTHROCK_BRICK_WALL = register("depthrock_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(DEPTHROCK_BRICKS.get()).noOcclusion()));
    public static final RegistryObject<StoneButtonBlock> DEPTHROCK_BUTTON = register("depthrock_button", () -> new StoneButtonBlock(AbstractBlock.Properties.copy(UGBlocks.DEPTHROCK.get()).noOcclusion().noCollission()));
    public static final RegistryObject<PressurePlateBlock> DEPTHROCK_PRESSURE_PLATE = register("depthrock_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.copy(UGBlocks.DEPTHROCK.get()).noOcclusion().noCollission()));

    //shiverstone
    public static final RegistryObject<Block> SHIVERSTONE = register("shiverstone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(3.5F, 12F).sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops().friction(0.98F)));
    public static final RegistryObject<Block> SHIVERSTONE_BRICKS = register("shiverstone_bricks", () -> new Block(AbstractBlock.Properties.copy(SHIVERSTONE.get())));
    public static final RegistryObject<Block> CRACKED_SHIVERSTONE_BRICKS = register("cracked_shiverstone_bricks", () -> new Block(AbstractBlock.Properties.copy(SHIVERSTONE.get())));
    public static final RegistryObject<Block> CHISELED_SHIVERSTONE_BRICKS = register("chiseled_shiverstone_bricks", () -> new Block(AbstractBlock.Properties.copy(SHIVERSTONE.get())));
    public static final RegistryObject<StairsBlock> SHIVERSTONE_STAIRS = register("shiverstone_stairs", () -> new StairsBlock(() -> UGBlocks.SHIVERSTONE.get().defaultBlockState(), AbstractBlock.Properties.copy(UGBlocks.SHIVERSTONE.get()).noOcclusion()));
    public static final RegistryObject<StairsBlock> SHIVERSTONE_BRICK_STAIRS = register("shiverstone_brick_stairs", () -> new StairsBlock(() -> UGBlocks.SHIVERSTONE_BRICKS.get().defaultBlockState(), AbstractBlock.Properties.copy(UGBlocks.SHIVERSTONE_BRICKS.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> SHIVERSTONE_SLAB = register("shiverstone_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(UGBlocks.SHIVERSTONE.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> SHIVERSTONE_BRICK_SLAB = register("shiverstone_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(UGBlocks.SHIVERSTONE_BRICKS.get()).noOcclusion()));
    public static final RegistryObject<WallBlock> SHIVERSTONE_WALL = register("shiverstone_wall", () -> new WallBlock(AbstractBlock.Properties.copy(SHIVERSTONE.get()).noOcclusion()));
    public static final RegistryObject<WallBlock> SHIVERSTONE_BRICK_WALL = register("shiverstone_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(SHIVERSTONE_BRICKS.get()).noOcclusion()));
    public static final RegistryObject<StoneButtonBlock> SHIVERSTONE_BUTTON = register("shiverstone_button", () -> new StoneButtonBlock(AbstractBlock.Properties.copy(UGBlocks.SHIVERSTONE.get()).noOcclusion().noCollission()));
    public static final RegistryObject<PressurePlateBlock> SHIVERSTONE_PRESSURE_PLATE = register("shiverstone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.copy(UGBlocks.SHIVERSTONE.get()).noOcclusion().noCollission()));

    //tremblecrust
    public static final RegistryObject<Block> TREMBLECRUST = register("tremblecrust", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(6F, 24F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(3).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LOOSE_TREMBLECRUST = register("loose_tremblecrust", () -> new LooseTremblecrustBlock(AbstractBlock.Properties.copy(TREMBLECRUST.get()).strength(3F, 24F).noDrops()));
    public static final RegistryObject<Block> TREMBLECRUST_BRICKS = register("tremblecrust_bricks", () -> new Block(AbstractBlock.Properties.copy(TREMBLECRUST.get()).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRACKED_TREMBLECRUST_BRICKS = register("cracked_tremblecrust_bricks", () -> new Block(AbstractBlock.Properties.copy(TREMBLECRUST_BRICKS.get())));
    public static final RegistryObject<Block> CHISELED_TREMBLECRUST_BRICKS = register("chiseled_tremblecrust_bricks", () -> new Block(AbstractBlock.Properties.copy(TREMBLECRUST_BRICKS.get())));
    public static final RegistryObject<StairsBlock> TREMBLECRUST_STAIRS = register("tremblecrust_stairs", () -> new StairsBlock(() -> UGBlocks.TREMBLECRUST.get().defaultBlockState(), AbstractBlock.Properties.copy(UGBlocks.TREMBLECRUST.get()).noOcclusion()));
    public static final RegistryObject<StairsBlock> TREMBLECRUST_BRICK_STAIRS = register("tremblecrust_brick_stairs", () -> new StairsBlock(() -> UGBlocks.TREMBLECRUST_BRICKS.get().defaultBlockState(), AbstractBlock.Properties.copy(UGBlocks.TREMBLECRUST_BRICKS.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> TREMBLECRUST_SLAB = register("tremblecrust_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(UGBlocks.TREMBLECRUST.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> TREMBLECRUST_BRICK_SLAB = register("tremblecrust_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(UGBlocks.TREMBLECRUST_BRICKS.get()).noOcclusion()));
    public static final RegistryObject<WallBlock> TREMBLECRUST_WALL = register("tremblecrust_wall", () -> new WallBlock(AbstractBlock.Properties.copy(TREMBLECRUST.get()).noOcclusion()));
    public static final RegistryObject<WallBlock> TREMBLECRUST_BRICK_WALL = register("tremblecrust_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(TREMBLECRUST_BRICKS.get()).noOcclusion()));
    public static final RegistryObject<StoneButtonBlock> TREMBLECRUST_BUTTON = register("tremblecrust_button", () -> new StoneButtonBlock(AbstractBlock.Properties.copy(UGBlocks.TREMBLECRUST.get()).noOcclusion().noCollission()));
    public static final RegistryObject<PressurePlateBlock> TREMBLECRUST_PRESSURE_PLATE = register("tremblecrust_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.copy(UGBlocks.TREMBLECRUST.get()).noOcclusion().noCollission()));

    //ores
    public static final RegistryObject<Block> COAL_ORE = register("coal_ore", () -> new UGOreBlock(AbstractBlock.Properties.copy(DEPTHROCK.get()).strength(3.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> IRON_ORE = register("iron_ore", () -> new UGOreBlock(AbstractBlock.Properties.copy(DEPTHROCK.get()).strength(3.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GOLD_ORE = register("gold_ore", () -> new UGOreBlock(AbstractBlock.Properties.copy(DEPTHROCK.get()).strength(3.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DIAMOND_ORE = register("diamond_ore", () -> new UGOreBlock(AbstractBlock.Properties.copy(DEPTHROCK.get()).strength(3.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(3).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CLOGGRUM_ORE = register("cloggrum_ore", () -> new UGOreBlock(AbstractBlock.Properties.copy(DEPTHROCK.get()).strength(3.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FROSTSTEEL_ORE = register("froststeel_ore", () -> new UGOreBlock(AbstractBlock.Properties.copy(SHIVERSTONE.get()).strength(4.5F, 12.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> UTHERIUM_ORE = register("utherium_ore", () -> new UGOreBlock(AbstractBlock.Properties.copy(DEPTHROCK.get()).strength(3.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(3).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> OTHERSIDE_UTHERIUM_ORE = register("otherside_utherium_ore", () -> new UGOreBlock(AbstractBlock.Properties.copy(TREMBLECRUST.get()).strength(7.0F, 24.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> REGALIUM_ORE = register("regalium_ore", () -> new UGOreBlock(AbstractBlock.Properties.copy(DEPTHROCK.get()).strength(3.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4).requiresCorrectToolForDrops()));

    //storage blocks
    public static final RegistryObject<Block> CLOGGRUM_BLOCK = register("cloggrum_block", () -> new Block(AbstractBlock.Properties.copy(Blocks.IRON_BLOCK).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FROSTSTEEL_BLOCK = register("froststeel_block", () -> new Block(AbstractBlock.Properties.copy(Blocks.IRON_BLOCK).harvestTool(ToolType.PICKAXE).harvestLevel(3).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> UTHERIUM_BLOCK = register("utherium_block", () -> new Block(AbstractBlock.Properties.copy(Blocks.IRON_BLOCK).harvestTool(ToolType.PICKAXE).harvestLevel(3).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> REGALIUM_BLOCK = register("regalium_block", () -> new Block(AbstractBlock.Properties.copy(Blocks.IRON_BLOCK).harvestTool(ToolType.PICKAXE).harvestLevel(4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FORGOTTEN_BLOCK = register("forgotten_block", () -> new Block(AbstractBlock.Properties.copy(Blocks.NETHERITE_BLOCK).harvestTool(ToolType.PICKAXE).harvestLevel(4).requiresCorrectToolForDrops()));

    //normal blocks
    public static final RegistryObject<Block> DEEPTURF_BLOCK = register("deepturf_block", () -> new DeepturfBlock(AbstractBlock.Properties.copy(Blocks.GRASS_BLOCK).harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<Block> ASHEN_DEEPTURF_BLOCK = register("ashen_deepturf_block", () -> new Block(AbstractBlock.Properties.copy(DEEPTURF_BLOCK.get())));
    public static final RegistryObject<Block> FROZEN_DEEPTURF_BLOCK = register("frozen_deepturf_block", () -> new Block(AbstractBlock.Properties.copy(DEEPTURF_BLOCK.get())));
    public static final RegistryObject<Block> DEEPSOIL = register("deepsoil", () -> new Block(AbstractBlock.Properties.copy(DEEPTURF_BLOCK.get()).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> COARSE_DEEPSOIL = register("coarse_deepsoil", () -> new Block(AbstractBlock.Properties.copy(DEEPSOIL.get())));
    public static final RegistryObject<Block> DEEPSOIL_FARMLAND = register("deepsoil_farmland", () -> new DeepsoilFarmlandBlock(AbstractBlock.Properties.copy(Blocks.FARMLAND)));
    public static final RegistryObject<Block> GOO = register("goo", () -> new GooLayerBlock(AbstractBlock.Properties.copy(Blocks.SNOW).sound(SoundType.SLIME_BLOCK).noOcclusion().noCollission().harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<Block> GOO_BLOCK = register("goo_block", () -> new GooBlock(AbstractBlock.Properties.copy(Blocks.SLIME_BLOCK).sound(SoundType.SLIME_BLOCK).noOcclusion().harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<Block> SMOG_VENT = register("smog_vent", () -> new SmogVentBlock(AbstractBlock.Properties.copy(DEPTHROCK.get())));
    public static final RegistryObject<Block> SEDIMENT = register("sediment", () -> new Block(AbstractBlock.Properties.copy(Blocks.SAND).harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<Block> SEDIMENT_GLASS = register("sediment_glass", () -> new GlassBlock(AbstractBlock.Properties.copy(Blocks.GLASS)));
    public static final RegistryObject<Block> SEDIMENT_GLASS_PANE = register("sediment_glass_pane", () -> new PaneBlock(AbstractBlock.Properties.copy(Blocks.GLASS_PANE)));
    public static final RegistryObject<Block> CLOGGRUM_BARS = register("cloggrum_bars", () -> new PaneBlock(AbstractBlock.Properties.copy(Blocks.IRON_BARS)));
    public static final RegistryObject<Block> CLOGGRUM_TILES = register("cloggrum_tiles", () -> new Block(AbstractBlock.Properties.copy(CLOGGRUM_BLOCK.get())));
    public static final RegistryObject<StairsBlock> CLOGGRUM_TILE_STAIRS = register("cloggrum_tile_stairs", () -> new StairsBlock(() -> CLOGGRUM_TILES.get().defaultBlockState(), AbstractBlock.Properties.copy(CLOGGRUM_TILES.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> CLOGGRUM_TILE_SLAB = register("cloggrum_tile_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(CLOGGRUM_TILES.get()).noOcclusion()));

    //plants
    public static final RegistryObject<Block> UNDERBEAN_BUSH = BLOCKS.register("underbean_bush", () -> new UnderbeanBushBlock(AbstractBlock.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> BLISTERBERRY_BUSH = BLOCKS.register("blisterberry_bush", () -> new BlisterberryBushBlock(AbstractBlock.Properties.copy(Blocks.SWEET_BERRY_BUSH).lightLevel((state) -> 6)));
    public static final RegistryObject<Block> DEEPTURF = register("deepturf", () -> new UGPlantBlock(AbstractBlock.Properties.copy(Blocks.GRASS)));
    public static final RegistryObject<Block> ASHEN_DEEPTURF = register("ashen_deepturf", () -> new AshenTallDeepturfBlock(AbstractBlock.Properties.copy(Blocks.GRASS)));
    public static final RegistryObject<Block> FROZEN_DEEPTURF = register("frozen_deepturf", () -> new FrozenDeepturfBlock(AbstractBlock.Properties.copy(Blocks.GRASS)));
    public static final RegistryObject<Block> TALL_DEEPTURF = register("tall_deepturf", () -> new UGDoublePlantBlock(AbstractBlock.Properties.copy(Blocks.TALL_GRASS)));
    public static final RegistryObject<Block> SHIMMERWEED = register("shimmerweed", () -> new UGPlantBlock(AbstractBlock.Properties.copy(Blocks.GRASS).lightLevel((state) -> 12)));
    public static final RegistryObject<Block> TALL_SHIMMERWEED = register("tall_shimmerweed", () -> new UGDoublePlantBlock(AbstractBlock.Properties.copy(Blocks.TALL_GRASS).lightLevel((state) -> 14)));
    public static final RegistryObject<Block> DITCHBULB_PLANT = BLOCKS.register("ditchbulb_plant", () -> new DitchbulbBlock(AbstractBlock.Properties.copy(Blocks.TALL_GRASS).randomTicks().lightLevel((state) -> state.getValue(DitchbulbBlock.AGE) == 1 ? 6 : 0)));
    public static final RegistryObject<StemGrownBlock> GLOOMGOURD = register("gloomgourd", () -> new GloomgourdBlock(AbstractBlock.Properties.copy(Blocks.PUMPKIN)));
    public static final RegistryObject<Block> CARVED_GLOOMGOURD = BLOCKS.register("carved_gloomgourd", () -> new CarvedGloomgourdBlock(AbstractBlock.Properties.copy(Blocks.CARVED_PUMPKIN)));
    public static final RegistryObject<Block> GLOOM_O_LANTERN = register("gloom_o_lantern", () -> new CarvedGloomgourdBlock(AbstractBlock.Properties.copy(Blocks.JACK_O_LANTERN).lightLevel((state) -> 15)));
    public static final RegistryObject<Block> SHARD_O_LANTERN = register("shard_o_lantern", () -> new CarvedGloomgourdShardBlock(AbstractBlock.Properties.copy(GLOOM_O_LANTERN.get()).lightLevel((state) -> 6)));
    public static final RegistryObject<StemBlock> GLOOMGOURD_STEM = BLOCKS.register("gloomgourd_stem", () -> new UGStemBlock(GLOOMGOURD.get(), AbstractBlock.Properties.copy(Blocks.PUMPKIN_STEM)));
    public static final RegistryObject<AttachedStemBlock> GLOOMGOURD_STEM_ATTACHED = BLOCKS.register("gloomgourd_stem_attached", () -> new UGAttachedStemBlock(GLOOMGOURD.get(), AbstractBlock.Properties.copy(Blocks.ATTACHED_PUMPKIN_STEM)));
    public static final RegistryObject<Block> DEPTHROCK_PEBBLES = register("depthrock_pebbles", () -> new DepthrockPebblesBlock(AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.BASALT).noOcclusion().noCollission().strength(0F)));
    public static final RegistryObject<GlowingKelpTopBlock> GLOWING_KELP = BLOCKS.register("glowing_kelp", () -> new GlowingKelpTopBlock(AbstractBlock.Properties.copy(Blocks.KELP).lightLevel((state) -> 10)));
    public static final RegistryObject<Block> GLOWING_KELP_PLANT = BLOCKS.register("glowing_kelp_plant", () -> new GlowingKelpBlock(AbstractBlock.Properties.copy(Blocks.KELP_PLANT).lightLevel((state) -> 10)));
    public static final RegistryObject<Block> DROOPVINE_TOP = BLOCKS.register("droopvine_top", () -> new DroopvineTopBlock(AbstractBlock.Properties.copy(Blocks.WEEPING_VINES).strength(0.1F).noDrops(), Direction.DOWN, false, 0.1D));
    public static final RegistryObject<Block> DROOPVINE = BLOCKS.register("droopvine", () -> new DroopvineBlock(AbstractBlock.Properties.copy(Blocks.WEEPING_VINES_PLANT).strength(0.1F).lightLevel((state) -> state.getValue(DroopvineBlock.GLOWY) ? 10 : 0), Direction.DOWN, false));

    //mushroom
    public static final RegistryObject<Block> INDIGO_MUSHROOM = register("indigo_mushroom", () -> new UGMushroomBlock(AbstractBlock.Properties.copy(Blocks.RED_MUSHROOM).lightLevel((state) -> 2)));
    public static final RegistryObject<Block> INDIGO_MUSHROOM_CAP = register("indigo_mushroom_cap", () -> new HugeMushroomBlock(AbstractBlock.Properties.copy(Blocks.RED_MUSHROOM_BLOCK)));
    public static final RegistryObject<Block> INDIGO_MUSHROOM_STALK = register("indigo_mushroom_stalk", () -> new HugeMushroomBlock(AbstractBlock.Properties.copy(Blocks.RED_MUSHROOM_BLOCK)));

    public static final RegistryObject<Block> VEIL_MUSHROOM = register("veil_mushroom", () -> new UGMushroomBlock(AbstractBlock.Properties.copy(Blocks.RED_MUSHROOM)));
    public static final RegistryObject<Block> VEIL_MUSHROOM_CAP = register("veil_mushroom_cap", () -> new HugeMushroomBlock(AbstractBlock.Properties.copy(Blocks.RED_MUSHROOM_BLOCK)));
    public static final RegistryObject<Block> VEIL_MUSHROOM_STALK = register("veil_mushroom_stalk", () -> new HugeMushroomBlock(AbstractBlock.Properties.copy(Blocks.RED_MUSHROOM_BLOCK)));
    public static final RegistryObject<Block> MUSHROOM_VEIL = BLOCKS.register("mushroom_veil", () -> new MushroomVeilBlock(AbstractBlock.Properties.copy(Blocks.WEEPING_VINES).noDrops(), Direction.DOWN, VoxelShapes.block(), false));
    public static final RegistryObject<AbstractTopPlantBlock> MUSHROOM_VEIL_TOP = BLOCKS.register("mushroom_veil_top", () -> new MushroomVeilTopBlock(AbstractBlock.Properties.copy(Blocks.WEEPING_VINES_PLANT).noDrops(), Direction.DOWN, VoxelShapes.block(), false, 0.1D));

    public static final RegistryObject<Block> INK_MUSHROOM = register("ink_mushroom", () -> new UGMushroomBlock(AbstractBlock.Properties.copy(Blocks.RED_MUSHROOM)));
    public static final RegistryObject<Block> INK_MUSHROOM_CAP = register("ink_mushroom_cap", () -> new HugeMushroomBlock(AbstractBlock.Properties.copy(Blocks.RED_MUSHROOM_BLOCK)));
    public static final RegistryObject<Block> SEEPING_INK = BLOCKS.register("seeping_ink", () -> new SeepingInkBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WET_GRASS).instabreak().noDrops().noOcclusion().noCollission()));

    public static final RegistryObject<Block> BLOOD_MUSHROOM = register("blood_mushroom", () -> new UGMushroomBlock(AbstractBlock.Properties.copy(Blocks.RED_MUSHROOM)));
    public static final RegistryObject<Block> BLOOD_MUSHROOM_CAP = register("blood_mushroom_cap", () -> new HugeMushroomBlock(AbstractBlock.Properties.copy(Blocks.RED_MUSHROOM_BLOCK)));
    public static final RegistryObject<Block> BLOOD_MUSHROOM_GLOBULE = register("blood_mushroom_globule", () -> new Block(AbstractBlock.Properties.copy(Blocks.RED_MUSHROOM_BLOCK).sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<Block> BLOOD_MUSHROOM_STALK = register("blood_mushroom_stalk", () -> new HugeMushroomBlock(AbstractBlock.Properties.copy(Blocks.MUSHROOM_STEM)));

    //smogstem
    public static final RegistryObject<SaplingBlock> SMOGSTEM_SAPLING = register("smogstem_sapling", () -> new UGSaplingBlock(new SmogstemTree()));
    public static final RegistryObject<RotatedPillarBlock> SMOGSTEM_LOG = register("smogstem_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_SMOGSTEM_LOG = register("stripped_smogstem_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> SMOGSTEM_WOOD = register("smogstem_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_SMOGSTEM_WOOD = register("stripped_smogstem_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> SMOGSTEM_LEAVES = register("smogstem_leaves", () -> new LeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> SMOGSTEM_PLANKS = register("smogstem_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<StairsBlock> SMOGSTEM_STAIRS = register("smogstem_stairs", () -> new StairsBlock(() -> UGBlocks.SMOGSTEM_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(UGBlocks.SMOGSTEM_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> SMOGSTEM_SLAB = register("smogstem_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(UGBlocks.SMOGSTEM_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<FenceBlock> SMOGSTEM_FENCE = register("smogstem_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(UGBlocks.SMOGSTEM_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<FenceGateBlock> SMOGSTEM_FENCE_GATE = register("smogstem_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.copy(UGBlocks.SMOGSTEM_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<DoorBlock> SMOGSTEM_DOOR = register("smogstem_door", () -> new DoorBlock(AbstractBlock.Properties.copy(UGBlocks.SMOGSTEM_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> SMOGSTEM_TRAPDOOR = register("smogstem_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(UGBlocks.SMOGSTEM_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<WoodButtonBlock> SMOGSTEM_BUTTON = register("smogstem_button", () -> new WoodButtonBlock(AbstractBlock.Properties.copy(UGBlocks.SMOGSTEM_PLANKS.get()).noOcclusion().noCollission()));
    public static final RegistryObject<PressurePlateBlock> SMOGSTEM_PRESSURE_PLATE = register("smogstem_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(UGBlocks.SMOGSTEM_PLANKS.get()).noOcclusion().noCollission()));

    public static final WoodType SMOGSTEM_WOODTYPE = WoodType.create(new ResourceLocation(Undergarden.MODID, "smogstem").toString());
    public static final RegistryObject<StandingSignBlock> SMOGSTEM_SIGN = BLOCKS.register("smogstem_sign", () -> new UndergardenStandingSignBlock(AbstractBlock.Properties.copy(Blocks.OAK_SIGN), SMOGSTEM_WOODTYPE));
    public static final RegistryObject<WallSignBlock> SMOGSTEM_WALL_SIGN = BLOCKS.register("smogstem_wall_sign", () -> new UndergardenWallSignBlock(AbstractBlock.Properties.copy(Blocks.OAK_WALL_SIGN), SMOGSTEM_WOODTYPE));

    //wigglewood
    public static final RegistryObject<SaplingBlock> WIGGLEWOOD_SAPLING = register("wigglewood_sapling", () -> new UGSaplingBlock(new WigglewoodTree()));
    public static final RegistryObject<RotatedPillarBlock> WIGGLEWOOD_LOG = register("wigglewood_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_WIGGLEWOOD_LOG = register("stripped_wigglewood_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> WIGGLEWOOD_WOOD = register("wigglewood_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_WIGGLEWOOD_WOOD = register("stripped_wigglewood_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> WIGGLEWOOD_LEAVES = register("wigglewood_leaves", () -> new LeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> WIGGLEWOOD_PLANKS = register("wigglewood_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<StairsBlock> WIGGLEWOOD_STAIRS = register("wigglewood_stairs", () -> new StairsBlock(() -> UGBlocks.SMOGSTEM_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(UGBlocks.SMOGSTEM_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> WIGGLEWOOD_SLAB = register("wigglewood_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(UGBlocks.WIGGLEWOOD_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<FenceBlock> WIGGLEWOOD_FENCE = register("wigglewood_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(UGBlocks.WIGGLEWOOD_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<FenceGateBlock> WIGGLEWOOD_FENCE_GATE = register("wigglewood_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.copy(UGBlocks.WIGGLEWOOD_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<DoorBlock> WIGGLEWOOD_DOOR = register("wigglewood_door", () -> new DoorBlock(AbstractBlock.Properties.copy(UGBlocks.WIGGLEWOOD_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> WIGGLEWOOD_TRAPDOOR = register("wigglewood_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(UGBlocks.WIGGLEWOOD_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<WoodButtonBlock> WIGGLEWOOD_BUTTON = register("wigglewood_button", () -> new WoodButtonBlock(AbstractBlock.Properties.copy(UGBlocks.WIGGLEWOOD_PLANKS.get()).noOcclusion().noCollission()));
    public static final RegistryObject<PressurePlateBlock> WIGGLEWOOD_PRESSURE_PLATE = register("wigglewood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(UGBlocks.WIGGLEWOOD_PLANKS.get()).noOcclusion().noCollission()));

    public static final WoodType WIGGLEWOOD_WOODTYPE = WoodType.create(new ResourceLocation(Undergarden.MODID, "wigglewood").toString());
    public static final RegistryObject<StandingSignBlock> WIGGLEWOOD_SIGN = BLOCKS.register("wigglewood_sign", () -> new UndergardenStandingSignBlock(AbstractBlock.Properties.copy(Blocks.OAK_SIGN), WIGGLEWOOD_WOODTYPE));
    public static final RegistryObject<WallSignBlock> WIGGLEWOOD_WALL_SIGN = BLOCKS.register("wigglewood_wall_sign", () -> new UndergardenWallSignBlock(AbstractBlock.Properties.copy(Blocks.OAK_WALL_SIGN), WIGGLEWOOD_WOODTYPE));

    //grongle
    public static final RegistryObject<Block> GRONGLE_SAPLING = register("grongle_sapling", () -> new UGSaplingBlock(new GrongleTree()));
    public static final RegistryObject<RotatedPillarBlock> GRONGLE_LOG = register("grongle_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_GRONGLE_LOG = register("stripped_grongle_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> GRONGLE_WOOD = register("grongle_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_GRONGLE_WOOD = register("stripped_grongle_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> GRONGLE_LEAVES = register("grongle_leaves", () -> new LeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<AbstractBodyPlantBlock> HANGING_GRONGLE_LEAVES = BLOCKS.register("hanging_grongle_leaves", () -> new HangingGrongleLeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES).noCollission(), Direction.DOWN, false));
    public static final RegistryObject<AbstractTopPlantBlock> HANGING_GRONGLE_LEAVES_TOP = register("hanging_grongle_leaves_top", () -> new HangingGrongleLeavesTopBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES).noCollission(), Direction.DOWN, false, 0.0D));
    public static final RegistryObject<Block> GRONGLE_PLANKS = register("grongle_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.CRIMSON_PLANKS)));
    public static final RegistryObject<StairsBlock> GRONGLE_STAIRS = register("grongle_stairs", () -> new StairsBlock(() -> UGBlocks.GRONGLE_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(UGBlocks.GRONGLE_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> GRONGLE_SLAB = register("grongle_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(UGBlocks.GRONGLE_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<FenceBlock> GRONGLE_FENCE = register("grongle_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(UGBlocks.GRONGLE_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<FenceGateBlock> GRONGLE_FENCE_GATE = register("grongle_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.copy(UGBlocks.GRONGLE_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<DoorBlock> GRONGLE_DOOR = register("grongle_door", () -> new DoorBlock(AbstractBlock.Properties.copy(UGBlocks.GRONGLE_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> GRONGLE_TRAPDOOR = register("grongle_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(UGBlocks.GRONGLE_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<WoodButtonBlock> GRONGLE_BUTTON = register("grongle_button", () -> new WoodButtonBlock(AbstractBlock.Properties.copy(UGBlocks.GRONGLE_PLANKS.get()).noOcclusion().noCollission()));
    public static final RegistryObject<PressurePlateBlock> GRONGLE_PRESSURE_PLATE = register("grongle_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(UGBlocks.GRONGLE_PLANKS.get()).noOcclusion().noCollission()));

    public static final WoodType GRONGLE_WOODTYPE = WoodType.create(new ResourceLocation(Undergarden.MODID, "grongle").toString());
    public static final RegistryObject<StandingSignBlock> GRONGLE_SIGN = BLOCKS.register("grongle_sign", () -> new UndergardenStandingSignBlock(AbstractBlock.Properties.copy(Blocks.OAK_SIGN), GRONGLE_WOODTYPE));
    public static final RegistryObject<WallSignBlock> GRONGLE_WALL_SIGN = BLOCKS.register("grongle_wall_sign", () -> new UndergardenWallSignBlock(AbstractBlock.Properties.copy(Blocks.OAK_WALL_SIGN), GRONGLE_WOODTYPE));

    //flower pots
    public static final RegistryObject<FlowerPotBlock> POTTED_SMOGSTEM_SAPLING = BLOCKS.register("potted_smogstem_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SMOGSTEM_SAPLING, AbstractBlock.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<FlowerPotBlock> POTTED_WIGGLEWOOD_SAPLING = BLOCKS.register("potted_wigglewood_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, WIGGLEWOOD_SAPLING, AbstractBlock.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<FlowerPotBlock> POTTED_GRONGLE_SAPLING = BLOCKS.register("potted_grongle_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GRONGLE_SAPLING, AbstractBlock.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<FlowerPotBlock> POTTED_SHIMMERWEED = BLOCKS.register("potted_shimmerweed", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SHIMMERWEED, AbstractBlock.Properties.copy(Blocks.FLOWER_POT).lightLevel((state) -> 12)));
    public static final RegistryObject<FlowerPotBlock> POTTED_INDIGO_MUSHROOM = BLOCKS.register("potted_indigo_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, INDIGO_MUSHROOM, AbstractBlock.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<FlowerPotBlock> POTTED_VEIL_MUSHROOM = BLOCKS.register("potted_veil_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, VEIL_MUSHROOM, AbstractBlock.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<FlowerPotBlock> POTTED_INK_MUSHROOM = BLOCKS.register("potted_ink_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, INK_MUSHROOM, AbstractBlock.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<FlowerPotBlock> POTTED_BLOOD_MUSHROOM = BLOCKS.register("potted_blood_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLOOD_MUSHROOM, AbstractBlock.Properties.copy(Blocks.FLOWER_POT)));

    //fluids
    public static final RegistryObject<FlowingFluidBlock> VIRULENT_MIX = BLOCKS.register("virulent_mix", () -> new UGFluidBlock(
            UGFluids.VIRULENT_MIX_SOURCE, AbstractBlock.Properties.of(Material.WATER)));

    private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> register = BLOCKS.register(name, block);
        UGItems.ITEMS.register(name, item.apply(register));
        return register;
    }

    private static <B extends Block> RegistryObject<B> register(String name, Supplier<? extends Block> block) {
        return (RegistryObject<B>)baseRegister(name, block, UGBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
        return () -> new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties().tab(UGItemGroups.GROUP));
    }
}