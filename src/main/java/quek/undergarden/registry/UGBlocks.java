package quek.undergarden.registry;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusConfig;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UGMod;
import quek.undergarden.block.*;
import quek.undergarden.world.gen.tree.SmogstemTree;
import quek.undergarden.world.gen.tree.WigglewoodTree;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class UGBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, UGMod.MODID);

    public static final RegistryObject<Block> UNDERGARDEN_PORTAL = BLOCKS.register("undergarden_portal", UndergardenPortalBlock::new);

    public static final RegistryObject<Block> SHARD_TORCH = BLOCKS.register("shard_torch", () -> new ShardTorchBlock(AbstractBlock.Properties.from(Blocks.TORCH).setLightLevel((state) -> 6)));
    public static final RegistryObject<Block> SHARD_WALL_TORCH = BLOCKS.register("shard_wall_torch", () -> new ShardWallTorchBlock(AbstractBlock.Properties.from(Blocks.WALL_TORCH).setLightLevel((state) -> 6)));
    public static final RegistryObject<Block> CLOGGRUM_BARS = registerBlock("cloggrum_bars", () -> new PaneBlock(AbstractBlock.Properties.from(Blocks.IRON_BARS)));

    //depthrock
    public static final RegistryObject<Block> DEPTHROCK = registerBlock("depthrock", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE).sound(SoundType.BASALT).harvestLevel(1).setRequiresTool()));
    public static final RegistryObject<Block> DEPTHROCK_BRICKS = registerBlock("depthrock_bricks", () -> new Block(AbstractBlock.Properties.from(DEPTHROCK.get())));
    public static final RegistryObject<Block> CRACKED_DEPTHROCK_BRICKS = registerBlock("cracked_depthrock_bricks", () -> new Block(AbstractBlock.Properties.from(DEPTHROCK.get())));
    public static final RegistryObject<Block> CHISELED_DEPTHROCK_BRICKS = registerBlock("chiseled_depthrock_bricks", () -> new Block(AbstractBlock.Properties.from(DEPTHROCK.get())));
    public static final RegistryObject<StairsBlock> DEPTHROCK_STAIRS = registerBlock("depthrock_stairs", () -> new StairsBlock(() -> UGBlocks.DEPTHROCK.get().getDefaultState(), AbstractBlock.Properties.from(UGBlocks.DEPTHROCK.get()).notSolid()));
    public static final RegistryObject<StairsBlock> DEPTHROCK_BRICK_STAIRS = registerBlock("depthrock_brick_stairs", () -> new StairsBlock(() -> UGBlocks.DEPTHROCK_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(UGBlocks.DEPTHROCK_BRICKS.get()).notSolid()));
    public static final RegistryObject<SlabBlock> DEPTHROCK_SLAB = registerBlock("depthrock_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UGBlocks.DEPTHROCK.get()).notSolid()));
    public static final RegistryObject<SlabBlock> DEPTHROCK_BRICK_SLAB = registerBlock("depthrock_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UGBlocks.DEPTHROCK_BRICKS.get()).notSolid()));
    public static final RegistryObject<WallBlock> DEPTHROCK_WALL = registerBlock("depthrock_wall", () -> new WallBlock(AbstractBlock.Properties.from(DEPTHROCK.get()).notSolid()));
    public static final RegistryObject<WallBlock> DEPTHROCK_BRICK_WALL = registerBlock("depthrock_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(DEPTHROCK_BRICKS.get()).notSolid()));
    public static final RegistryObject<StoneButtonBlock> DEPTHROCK_BUTTON = registerBlock("depthrock_button", () -> new StoneButtonBlock(AbstractBlock.Properties.from(UGBlocks.DEPTHROCK.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<PressurePlateBlock> DEPTHROCK_PRESSURE_PLATE = registerBlock("depthrock_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.from(UGBlocks.DEPTHROCK.get()).notSolid().doesNotBlockMovement()));

    //shiverstone
    public static final RegistryObject<Block> SHIVERSTONE = registerBlock("shiverstone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.5F, 12F).sound(SoundType.NETHER_BRICK).harvestLevel(2).setRequiresTool().slipperiness(0.98F)));
    public static final RegistryObject<Block> SHIVERSTONE_BRICKS = registerBlock("shiverstone_bricks", () -> new Block(AbstractBlock.Properties.from(SHIVERSTONE.get())));
    public static final RegistryObject<Block> CRACKED_SHIVERSTONE_BRICKS = registerBlock("cracked_shiverstone_bricks", () -> new Block(AbstractBlock.Properties.from(SHIVERSTONE.get())));
    public static final RegistryObject<Block> CHISELED_SHIVERSTONE_BRICKS = registerBlock("chiseled_shiverstone_bricks", () -> new Block(AbstractBlock.Properties.from(SHIVERSTONE.get())));
    public static final RegistryObject<StairsBlock> SHIVERSTONE_STAIRS = registerBlock("shiverstone_stairs", () -> new StairsBlock(() -> UGBlocks.SHIVERSTONE.get().getDefaultState(), AbstractBlock.Properties.from(UGBlocks.SHIVERSTONE.get()).notSolid()));
    public static final RegistryObject<StairsBlock> SHIVERSTONE_BRICK_STAIRS = registerBlock("shiverstone_brick_stairs", () -> new StairsBlock(() -> UGBlocks.SHIVERSTONE_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(UGBlocks.SHIVERSTONE_BRICKS.get()).notSolid()));
    public static final RegistryObject<SlabBlock> SHIVERSTONE_SLAB = registerBlock("shiverstone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UGBlocks.SHIVERSTONE.get()).notSolid()));
    public static final RegistryObject<SlabBlock> SHIVERSTONE_BRICK_SLAB = registerBlock("shiverstone_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UGBlocks.SHIVERSTONE_BRICKS.get()).notSolid()));
    public static final RegistryObject<WallBlock> SHIVERSTONE_WALL = registerBlock("shiverstone_wall", () -> new WallBlock(AbstractBlock.Properties.from(SHIVERSTONE.get()).notSolid()));
    public static final RegistryObject<WallBlock> SHIVERSTONE_BRICK_WALL = registerBlock("shiverstone_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(SHIVERSTONE_BRICKS.get()).notSolid()));
    public static final RegistryObject<StoneButtonBlock> SHIVERSTONE_BUTTON = registerBlock("shiverstone_button", () -> new StoneButtonBlock(AbstractBlock.Properties.from(UGBlocks.SHIVERSTONE.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<PressurePlateBlock> SHIVERSTONE_PRESSURE_PLATE = registerBlock("shiverstone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.from(UGBlocks.SHIVERSTONE.get()).notSolid().doesNotBlockMovement()));

    //tremblecrust
    public static final RegistryObject<Block> TREMBLECRUST = registerBlock("tremblecrust", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(6F, 24F).sound(SoundType.NETHERRACK).harvestTool(ToolType.PICKAXE).harvestLevel(3).setRequiresTool()));
    public static final RegistryObject<Block> LOOSE_TREMBLECRUST = registerBlock("loose_tremblecrust", () -> new LooseTremblecrustBlock(AbstractBlock.Properties.from(TREMBLECRUST.get()).noDrops()));
    public static final RegistryObject<Block> TREMBLECRUST_BRICKS = registerBlock("tremblecrust_bricks", () -> new Block(AbstractBlock.Properties.from(TREMBLECRUST.get()).setRequiresTool()));

    //terrain
    public static final RegistryObject<Block> DEEPTURF_BLOCK = registerBlock("deepturf_block", () -> new DeepturfBlock(AbstractBlock.Properties.from(Blocks.GRASS_BLOCK).harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<Block> ASHEN_DEEPTURF_BLOCK = registerBlock("ashen_deepturf_block", () -> new Block(AbstractBlock.Properties.from(DEEPTURF_BLOCK.get())));
    public static final RegistryObject<Block> DEEPSOIL = registerBlock("deepsoil", () -> new Block(AbstractBlock.Properties.from(DEEPTURF_BLOCK.get()).sound(SoundType.GROUND)));
    public static final RegistryObject<Block> COARSE_DEEPSOIL = registerBlock("coarse_deepsoil", () -> new Block(AbstractBlock.Properties.from(DEEPSOIL.get())));
    public static final RegistryObject<Block> DEEPSOIL_FARMLAND = registerBlock("deepsoil_farmland", () -> new DeepsoilFarmlandBlock(AbstractBlock.Properties.from(Blocks.FARMLAND)));
    public static final RegistryObject<Block> GOO = registerBlock("goo", () -> new GooBlock(AbstractBlock.Properties.from(Blocks.SNOW).sound(SoundType.SLIME).notSolid().doesNotBlockMovement().harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<Block> SMOG_VENT = registerBlock("smog_vent", () -> new SmogVentBlock(AbstractBlock.Properties.from(DEPTHROCK.get())));

    //plants
    public static final RegistryObject<Block> UNDERBEAN_BUSH = BLOCKS.register("underbean_bush", () -> new UnderbeanBushBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> BLISTERBERRY_BUSH = BLOCKS.register("blisterberry_bush", () -> new BlisterberryBushBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH).setLightLevel((state) -> 6)));
    public static final RegistryObject<Block> DEEPTURF = registerBlock("deepturf", () -> new UGPlantBlock(AbstractBlock.Properties.from(Blocks.GRASS)));
    public static final RegistryObject<Block> ASHEN_DEEPTURF = registerBlock("ashen_deepturf", () -> new AshenTallDeepturfBlock(AbstractBlock.Properties.from(Blocks.GRASS)));
    public static final RegistryObject<Block> TALL_DEEPTURF = registerBlock("tall_deepturf", () -> new UGDoublePlantBlock(AbstractBlock.Properties.from(Blocks.TALL_GRASS)));
    public static final RegistryObject<Block> SHIMMERWEED = registerBlock("shimmerweed", () -> new UGPlantBlock(AbstractBlock.Properties.from(Blocks.GRASS).setLightLevel((state) -> 12)));
    public static final RegistryObject<Block> TALL_SHIMMERWEED = registerBlock("tall_shimmerweed", () -> new UGDoublePlantBlock(AbstractBlock.Properties.from(Blocks.TALL_GRASS).setLightLevel((state) -> 14)));
    public static final RegistryObject<Block> DITCHBULB_PLANT = registerBlock("ditchbulb_plant", () -> new DitchbulbBlock(AbstractBlock.Properties.from(Blocks.TALL_GRASS).setLightLevel((state) -> 6)));
    public static final RegistryObject<StemGrownBlock> GLOOMGOURD = registerBlock("gloomgourd", () -> new GloomgourdBlock(AbstractBlock.Properties.from(Blocks.PUMPKIN)));
    public static final RegistryObject<Block> CARVED_GLOOMGOURD = BLOCKS.register("carved_gloomgourd", () -> new CarvedGloomgourdBlock(AbstractBlock.Properties.from(Blocks.CARVED_PUMPKIN)));
    public static final RegistryObject<Block> GLOOM_O_LANTERN = registerBlock("gloom_o_lantern", () -> new CarvedGloomgourdBlock(AbstractBlock.Properties.from(Blocks.JACK_O_LANTERN).setLightLevel((state) -> 15)));
    public static final RegistryObject<StemBlock> GLOOMGOURD_STEM = BLOCKS.register("gloomgourd_stem", () -> new UGStemBlock(GLOOMGOURD.get(), AbstractBlock.Properties.from(Blocks.PUMPKIN_STEM)));
    public static final RegistryObject<AttachedStemBlock> GLOOMGOURD_STEM_ATTACHED = BLOCKS.register("gloomgourd_stem_attached", () -> new UGAttachedStemBlock(GLOOMGOURD.get(), AbstractBlock.Properties.from(Blocks.ATTACHED_PUMPKIN_STEM)));
    public static final RegistryObject<Block> DEPTHROCK_PEBBLES = registerBlock("depthrock_pebbles", () -> new DepthrockPebblesBlock(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.BASALT).notSolid().doesNotBlockMovement().hardnessAndResistance(0F)));
    public static final RegistryObject<GlowingKelpTopBlock> GLOWING_KELP = BLOCKS.register("glowing_kelp", () -> new GlowingKelpTopBlock(AbstractBlock.Properties.from(Blocks.KELP).setLightLevel((state) -> 10)));
    public static final RegistryObject<Block> GLOWING_KELP_PLANT = BLOCKS.register("glowing_kelp_plant", () -> new GlowingKelpBlock(AbstractBlock.Properties.from(Blocks.KELP_PLANT).setLightLevel((state) -> 10)));
    public static final RegistryObject<Block> DROOPVINE_TOP = BLOCKS.register("droopvine_top", () -> new DroopvineTopBlock(AbstractBlock.Properties.from(Blocks.WEEPING_VINES).setLightLevel((state) -> 10), Direction.DOWN, false, 0.1D));
    public static final RegistryObject<Block> DROOPVINE = BLOCKS.register("droopvine", () -> new DroopvineBlock(AbstractBlock.Properties.from(Blocks.WEEPING_VINES_PLANT).setLightLevel(DroopvineBlock.glowIfGlowy()), Direction.DOWN, false));

    //mushroom
    public static final RegistryObject<Block> INDIGO_MUSHROOM = registerBlock("indigo_mushroom", () -> new UGMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM).setLightLevel((state) -> 2)));
    public static final RegistryObject<Block> INDIGO_MUSHROOM_CAP = registerBlock("indigo_mushroom_cap", () -> new HugeMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM_BLOCK)));
    public static final RegistryObject<Block> INDIGO_MUSHROOM_STALK = registerBlock("indigo_mushroom_stalk", () -> new HugeMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM_BLOCK)));

    public static final RegistryObject<Block> VEIL_MUSHROOM = registerBlock("veil_mushroom", () -> new UGMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM)));
    public static final RegistryObject<Block> VEIL_MUSHROOM_CAP = registerBlock("veil_mushroom_cap", () -> new HugeMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM_BLOCK)));
    public static final RegistryObject<Block> VEIL_MUSHROOM_STALK = registerBlock("veil_mushroom_stalk", () -> new HugeMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM_BLOCK)));
    public static final RegistryObject<Block> MUSHROOM_VEIL = BLOCKS.register("mushroom_veil", () -> new MushroomVeilBlock(AbstractBlock.Properties.from(Blocks.WEEPING_VINES).noDrops(), Direction.DOWN, VoxelShapes.fullCube(), false));
    public static final RegistryObject<AbstractTopPlantBlock> MUSHROOM_VEIL_TOP = BLOCKS.register("mushroom_veil_top", () -> new MushroomVeilTopBlock(AbstractBlock.Properties.from(Blocks.WEEPING_VINES_PLANT).noDrops(), Direction.DOWN, VoxelShapes.fullCube(), false, 0.1D));

    public static final RegistryObject<Block> INK_MUSHROOM = registerBlock("ink_mushroom", () -> new UGMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM)));
    public static final RegistryObject<Block> INK_MUSHROOM_CAP = registerBlock("ink_mushroom_cap", () -> new HugeMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM_BLOCK)));
    public static final RegistryObject<Block> SEEPING_INK = BLOCKS.register("seeping_ink", () -> new SeepingInkBlock(AbstractBlock.Properties.create(Material.WOOD).zeroHardnessAndResistance().noDrops().notSolid().doesNotBlockMovement()));

    public static final RegistryObject<Block> BLOOD_MUSHROOM = registerBlock("blood_mushroom", () -> new UGMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM)));
    public static final RegistryObject<Block> BLOOD_MUSHROOM_CAP = registerBlock("blood_mushroom_cap", () -> new HugeMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM_BLOCK)));
    public static final RegistryObject<Block> BLOOD_MUSHROOM_GLOBULE = registerBlock("blood_mushroom_globule", () -> new Block(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM_BLOCK).sound(SoundType.SLIME)));
    public static final RegistryObject<Block> BLOOD_MUSHROOM_STALK = registerBlock("blood_mushroom_stalk", () -> new HugeMushroomBlock(AbstractBlock.Properties.from(Blocks.MUSHROOM_STEM)));

    //ores
    public static final RegistryObject<Block> COAL_ORE = registerBlock("coal_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(DEPTHROCK.get()).harvestLevel(0)));
    public static final RegistryObject<Block> IRON_ORE = registerBlock("iron_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(DEPTHROCK.get()).harvestLevel(1)));
    public static final RegistryObject<Block> GOLD_ORE = registerBlock("gold_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(DEPTHROCK.get()).harvestLevel(2)));
    public static final RegistryObject<Block> DIAMOND_ORE = registerBlock("diamond_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(DEPTHROCK.get()).harvestLevel(3)));
    public static final RegistryObject<Block> CLOGGRUM_ORE = registerBlock("cloggrum_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(DEPTHROCK.get()).harvestLevel(1)));
    public static final RegistryObject<Block> FROSTSTEEL_ORE = registerBlock("froststeel_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(DEPTHROCK.get()).harvestLevel(2)));
    public static final RegistryObject<Block> UTHERIUM_ORE = registerBlock("utherium_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(DEPTHROCK.get()).harvestLevel(3)));
    public static final RegistryObject<Block> OTHERSIDE_UTHERIUM_ORE = registerBlock("otherside_utherium_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(TREMBLECRUST.get()).harvestLevel(4)));
    public static final RegistryObject<Block> REGALIUM_ORE = registerBlock("regalium_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(DEPTHROCK.get()).harvestLevel(4)));

    //storage blocks
    public static final RegistryObject<Block> CLOGGRUM_BLOCK = registerBlock("cloggrum_block", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestLevel(2)));
    public static final RegistryObject<Block> FROSTSTEEL_BLOCK = registerBlock("froststeel_block", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestLevel(3)));
    public static final RegistryObject<Block> UTHERIUM_BLOCK = registerBlock("utherium_block", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestLevel(3)));
    public static final RegistryObject<Block> REGALIUM_BLOCK = registerBlock("regalium_block", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestLevel(4)));
    public static final RegistryObject<Block> FORGOTTEN_BLOCK = registerBlock("forgotten_block", () -> new Block(AbstractBlock.Properties.from(Blocks.NETHERITE_BLOCK).harvestLevel(4)));

    //smogstem
    public static final RegistryObject<SaplingBlock> SMOGSTEM_SAPLING = registerBlock("smogstem_sapling", () -> new UGSaplingBlock(new SmogstemTree()));
    public static final RegistryObject<RotatedPillarBlock> SMOGSTEM_LOG = registerBlock("smogstem_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_SMOGSTEM_LOG = registerBlock("stripped_smogstem_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> SMOGSTEM_WOOD = registerBlock("smogstem_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_SMOGSTEM_WOOD = registerBlock("stripped_smogstem_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> SMOGSTEM_LEAVES = registerBlock("smogstem_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> SMOGSTEM_PLANKS = registerBlock("smogstem_planks", () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
    public static final RegistryObject<StairsBlock> SMOGSTEM_STAIRS = registerBlock("smogstem_stairs", () -> new StairsBlock(() -> UGBlocks.SMOGSTEM_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(UGBlocks.SMOGSTEM_PLANKS.get()).notSolid()));
    public static final RegistryObject<SlabBlock> SMOGSTEM_SLAB = registerBlock("smogstem_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UGBlocks.SMOGSTEM_PLANKS.get()).notSolid()));
    public static final RegistryObject<FenceBlock> SMOGSTEM_FENCE = registerBlock("smogstem_fence", () -> new FenceBlock(AbstractBlock.Properties.from(UGBlocks.SMOGSTEM_PLANKS.get()).notSolid()));
    public static final RegistryObject<FenceGateBlock> SMOGSTEM_FENCE_GATE = registerBlock("smogstem_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(UGBlocks.SMOGSTEM_PLANKS.get()).notSolid()));
    public static final RegistryObject<DoorBlock> SMOGSTEM_DOOR = registerBlock("smogstem_door", () -> new DoorBlock(AbstractBlock.Properties.from(UGBlocks.SMOGSTEM_PLANKS.get()).notSolid()));
    public static final RegistryObject<TrapDoorBlock> SMOGSTEM_TRAPDOOR = registerBlock("smogstem_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.from(UGBlocks.SMOGSTEM_PLANKS.get()).notSolid()));
    public static final RegistryObject<WoodButtonBlock> SMOGSTEM_BUTTON = registerBlock("smogstem_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(UGBlocks.SMOGSTEM_PLANKS.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<PressurePlateBlock> SMOGSTEM_PRESSURE_PLATE = registerBlock("smogstem_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.from(UGBlocks.SMOGSTEM_PLANKS.get()).notSolid().doesNotBlockMovement()));

    //wigglewood
    public static final RegistryObject<SaplingBlock> WIGGLEWOOD_SAPLING = registerBlock("wigglewood_sapling", () -> new UGSaplingBlock(new WigglewoodTree()));
    public static final RegistryObject<RotatedPillarBlock> WIGGLEWOOD_LOG = registerBlock("wigglewood_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_WIGGLEWOOD_LOG = registerBlock("stripped_wigglewood_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> WIGGLEWOOD_WOOD = registerBlock("wigglewood_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_WIGGLEWOOD_WOOD = registerBlock("stripped_wigglewood_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> WIGGLEWOOD_LEAVES = registerBlock("wigglewood_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> WIGGLEWOOD_PLANKS = registerBlock("wigglewood_planks", () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
    public static final RegistryObject<StairsBlock> WIGGLEWOOD_STAIRS = registerBlock("wigglewood_stairs", () -> new StairsBlock(() -> UGBlocks.SMOGSTEM_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(UGBlocks.SMOGSTEM_PLANKS.get()).notSolid()));
    public static final RegistryObject<SlabBlock> WIGGLEWOOD_SLAB = registerBlock("wigglewood_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UGBlocks.WIGGLEWOOD_PLANKS.get()).notSolid()));
    public static final RegistryObject<FenceBlock> WIGGLEWOOD_FENCE = registerBlock("wigglewood_fence", () -> new FenceBlock(AbstractBlock.Properties.from(UGBlocks.WIGGLEWOOD_PLANKS.get()).notSolid()));
    public static final RegistryObject<FenceGateBlock> WIGGLEWOOD_FENCE_GATE = registerBlock("wigglewood_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(UGBlocks.WIGGLEWOOD_PLANKS.get()).notSolid()));
    public static final RegistryObject<DoorBlock> WIGGLEWOOD_DOOR = registerBlock("wigglewood_door", () -> new DoorBlock(AbstractBlock.Properties.from(UGBlocks.WIGGLEWOOD_PLANKS.get()).notSolid()));
    public static final RegistryObject<TrapDoorBlock> WIGGLEWOOD_TRAPDOOR = registerBlock("wigglewood_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.from(UGBlocks.WIGGLEWOOD_PLANKS.get()).notSolid()));
    public static final RegistryObject<WoodButtonBlock> WIGGLEWOOD_BUTTON = registerBlock("wigglewood_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(UGBlocks.WIGGLEWOOD_PLANKS.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<PressurePlateBlock> WIGGLEWOOD_PRESSURE_PLATE = registerBlock("wigglewood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.from(UGBlocks.WIGGLEWOOD_PLANKS.get()).notSolid().doesNotBlockMovement()));

    //grongle
    public static final RegistryObject<Block> GRONGLET = registerBlock("gronglet", () -> new GrongletBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_FUNGUS).sound(SoundType.SHROOMLIGHT), () -> Feature.HUGE_FUNGUS.withConfiguration(new HugeFungusConfig(UGBlocks.DEEPTURF_BLOCK.get().getDefaultState(), UGBlocks.GRONGLE_STEM.get().getDefaultState(), UGBlocks.GRONGLE_CAP.get().getDefaultState(), Blocks.SHROOMLIGHT.getDefaultState(), true))));
    public static final RegistryObject<RotatedPillarBlock> GRONGLE_STEM = registerBlock("grongle_stem", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_STEM).sound(SoundType.SHROOMLIGHT)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_GRONGLE_STEM = registerBlock("stripped_grongle_stem", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_CRIMSON_STEM).sound(SoundType.SHROOMLIGHT)));
    public static final RegistryObject<RotatedPillarBlock> GRONGLE_HYPHAE = registerBlock("grongle_hyphae", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_STEM).sound(SoundType.SHROOMLIGHT)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_GRONGLE_HYPHAE = registerBlock("stripped_grongle_hyphae", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_STEM).sound(SoundType.SHROOMLIGHT)));
    public static final RegistryObject<Block> GRONGLE_CAP = registerBlock("grongle_cap", () -> new Block(AbstractBlock.Properties.from(Blocks.WARPED_WART_BLOCK).sound(SoundType.SHROOMLIGHT)));
    public static final RegistryObject<Block> GRONGLE_PLANKS = registerBlock("grongle_planks", () -> new Block(AbstractBlock.Properties.from(Blocks.CRIMSON_PLANKS)));
    public static final RegistryObject<StairsBlock> GRONGLE_STAIRS = registerBlock("grongle_stairs", () -> new StairsBlock(() -> UGBlocks.GRONGLE_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(UGBlocks.GRONGLE_PLANKS.get()).notSolid()));
    public static final RegistryObject<SlabBlock> GRONGLE_SLAB = registerBlock("grongle_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UGBlocks.GRONGLE_PLANKS.get()).notSolid()));
    public static final RegistryObject<FenceBlock> GRONGLE_FENCE = registerBlock("grongle_fence", () -> new FenceBlock(AbstractBlock.Properties.from(UGBlocks.GRONGLE_PLANKS.get()).notSolid()));
    public static final RegistryObject<FenceGateBlock> GRONGLE_FENCE_GATE = registerBlock("grongle_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(UGBlocks.GRONGLE_PLANKS.get()).notSolid()));
    public static final RegistryObject<DoorBlock> GRONGLE_DOOR = registerBlock("grongle_door", () -> new DoorBlock(AbstractBlock.Properties.from(UGBlocks.GRONGLE_PLANKS.get()).notSolid()));
    public static final RegistryObject<TrapDoorBlock> GRONGLE_TRAPDOOR = registerBlock("grongle_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.from(UGBlocks.GRONGLE_PLANKS.get()).notSolid()));
    public static final RegistryObject<WoodButtonBlock> GRONGLE_BUTTON = registerBlock("grongle_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(UGBlocks.GRONGLE_PLANKS.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<PressurePlateBlock> GRONGLE_PRESSURE_PLATE = registerBlock("grongle_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.from(UGBlocks.GRONGLE_PLANKS.get()).notSolid().doesNotBlockMovement()));

    //fluids
    public static final RegistryObject<FlowingFluidBlock> VIRULENT_MIX = BLOCKS.register("virulent_mix", () -> new UGFluidBlock(
            UGFluids.VIRULENT_MIX_SOURCE, AbstractBlock.Properties.create(Material.WATER)));

    private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> register = BLOCKS.register(name, block);
        UGItems.ITEMS.register(name, item.apply(register));
        return register;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends Block> block) {
        return (RegistryObject<T>)baseRegister(name, block, UGBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
        return () -> new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties().group(UGItemGroups.GROUP));
    }
}